package it.testori.thip.base.generale.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import javax.ws.rs.core.Response.Status;

import org.json.JSONArray;
import org.json.JSONObject;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.rs.errors.ErrorUtils;

import it.testori.thip.base.articolo.YArticoloDatiMagaz;
import it.testori.thip.easycheck.PezzaGreggiaField;
import it.testori.thip.easycheck.PezzaLavorata;
import it.testori.thip.magazzino.generalemag.YLotto;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.produzione.ordese.AttivitaEsecLottiMat;
import it.thera.thip.produzione.ordese.AttivitaEsecMateriale;
import it.thera.thip.produzione.ordese.AttivitaEsecutiva;
import it.thera.thip.produzione.ordese.AttivitaEsecutivaTM;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 28/11/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    28/11/2025  DSSOF3   Prima stesura
 */

public class EasyCheckService {

	private static EasyCheckService instance;

	public static EasyCheckService getInstance() {
		if(instance == null) {
			instance = (EasyCheckService) Factory.createObject(EasyCheckService.class);
		}
		return instance;
	}

	public JSONObject riceviPezzaLavorata(JSONObject payload) {
		JSONObject response = new JSONObject();
		JSONObject result = new JSONObject();
		Status status = Status.OK;
		Collection<ErrorMessage> errors = new ArrayList<>();

		PezzaLavorata pezza = PezzaLavorata.fromJson(payload, errors);
		if (!errors.isEmpty()) {
			status = Status.BAD_REQUEST;
		} else {

		}

		result.put("errors", ErrorUtils.getInstance().toJSON(errors));
		response.put("response", result);
		response.put("status", status);
		return response;
	}

	public JSONObject ritornaDatiPezzaGreggia(JSONObject payload) {
		JSONObject response = new JSONObject();
		JSONObject result = new JSONObject();
		Status status = Status.OK;
		Collection<ErrorMessage> errors = new ArrayList<>();

		PezzaGreggiaField pezza = PezzaGreggiaField.fromPayload(payload, errors);
		if(errors.isEmpty()) {
			String bollaLavorazione = pezza.getProductionOrder();
			AttivitaEsecutiva atvEsec = leggiAtvEsec(bollaLavorazione);
			if(atvEsec != null 
					&& atvEsec.getMateriali().size() > 0
					&& ((AttivitaEsecMateriale) atvEsec.getMateriali().get(0)).getArticolo().getArticoloDatiMagaz().getCodAutLotProd()  == YArticoloDatiMagaz.PEZZE) {
				AttivitaEsecMateriale materiale = (AttivitaEsecMateriale) atvEsec.getMateriali().get(0);
				if(materiale.getLottiMateriali().size() > 0
						&& !((AttivitaEsecLottiMat)materiale.getLottiMateriali().get(0)).getIdLotto().equals(Lotto.LOTTO_DUMMY)) {

					AttivitaEsecLottiMat atvEsecLottoMat = (AttivitaEsecLottiMat) materiale.getLottiMateriali().get(0);
					YLotto lotto = (YLotto) atvEsecLottoMat.getLotto();

					result.put("success", true);
					result.put("message", "Pezza greggia processata e validata con successo.");
					result.put("pieceHeaderSkid", "");
					
					JSONObject itemDataPanthera = new JSONObject();
					itemDataPanthera.put("pieceCode", materiale.getIdArticolo());
					itemDataPanthera.put("itemDescription", materiale.getArticolo().getDescrizioneArticoloNLS().getDescrizione());
					itemDataPanthera.put("standardWeight", lotto.getPesoKg() != null ? lotto.getPesoKg() : 0);

					itemDataPanthera.put("parameter1", 0);
					itemDataPanthera.put("parameter2", 0); 

					itemDataPanthera.put("minHeight", String.valueOf(lotto.getAltezzaMinima()));
					itemDataPanthera.put("maxHeight", String.valueOf(lotto.getAltezzaMassima()));

					result.put("itemDataPanthera", itemDataPanthera);

					JSONObject pieceDetailData = new JSONObject();
					pieceDetailData.put("pieceCode", materiale.getIdArticolo());
					pieceDetailData.put("totalQuantity", materiale.getQtaResiduaUMPrm());
					pieceDetailData.put("minHeightNumeric", lotto.getAltezzaMinima() != null ? lotto.getAltezzaMinima() : 0);
					pieceDetailData.put("maxHeightNumeric", lotto.getAltezzaMassima() != null ? lotto.getAltezzaMassima() : 0);
					pieceDetailData.put("weight", lotto.getPesoKg() != null ? lotto.getPesoKg() : 0);
					pieceDetailData.put("defectCode", lotto.getIdDifettosita() != null ? lotto.getIdDifettosita() : "NODEF");
					pieceDetailData.put("operatorCode", "");
					pieceDetailData.put("goodQuantity", lotto.getQuantitaOriginale() != null ? lotto.getQuantitaOriginale() : 0);
					pieceDetailData.put("additionalDescription", lotto.getDifettosita() != null ? lotto.getDifettosita().getDescrizione() : "");
					pieceDetailData.put("productionOrder", atvEsec.getNumeroRitorno());

					result.put("pieceDetailData", pieceDetailData);
				}else {
					errors.add(new ErrorMessage("BAS0000078","Il materiale dell'attivita non ha un lotto significativo"));
					status = Status.INTERNAL_SERVER_ERROR;
				}
			}else {
				if(atvEsec == null)
					errors.add(new ErrorMessage("BAS0000078","Non e' stata trovata nessuna attivita con codice: "+bollaLavorazione));
				else if(atvEsec.getMateriali().size() == 0)
					errors.add(new ErrorMessage("BAS0000078","Non e' stata trovato nessun materiale per l'attivita: "+bollaLavorazione));
				else if(((AttivitaEsecMateriale) atvEsec.getMateriali().get(0)).getArticolo().getArticoloDatiMagaz().getCodAutLotProd() != YArticoloDatiMagaz.PEZZE)
					errors.add(new ErrorMessage("BAS0000078","Il materiale dell'attivita non e' una pezza"));

				status = Status.INTERNAL_SERVER_ERROR;
			}
		}

		result.put("errors", ErrorUtils.getInstance().toJSON(errors));
		response.put("response", result);
		response.put("status", status);
		return response;
	}

	@SuppressWarnings("rawtypes")
	public static AttivitaEsecutiva leggiAtvEsec(String bollaLavorazione) {
		String where = AttivitaEsecutivaTM.ID_AZIENDA + " = '" + Azienda.getAziendaCorrente() + "' AND " +
				AttivitaEsecutivaTM.NUM_RITORNO + " = '" + bollaLavorazione + "'";
		try {
			Vector vect = AttivitaEsecutiva.retrieveList(where, "", true);
			if (!vect.isEmpty()) {
				return (AttivitaEsecutiva) vect.firstElement();
			}
		}
		catch (Exception e) {
			e.printStackTrace(Trace.excStream);
		}
		return null;
	}
}