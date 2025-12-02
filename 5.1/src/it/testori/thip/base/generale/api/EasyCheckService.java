package it.testori.thip.base.generale.api;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import com.thera.thermfw.base.TimeUtils;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.rs.errors.ErrorUtils;

import it.testori.thip.base.articolo.YArticoloDatiMagaz;
import it.testori.thip.easycheck.InterfacciaEasyCheck;
import it.testori.thip.easycheck.PezzaGreggiaField;
import it.testori.thip.easycheck.PezzaLavorata;
import it.testori.thip.logis.bas.YCostantiTestori;
import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.testori.thip.magazzino.generalemag.YLotto;
import it.testori.thip.produzione.ordese.TipoTaglioPezza;
import it.testori.thip.produzione.ordese.YAttivitaEsecMateriale;
import it.testori.thip.produzione.ordese.YAttivitaEsecProdotto;
import it.testori.thip.produzione.ordese.YAttivitaEsecutiva;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.dipendente.Dipendente;
import it.thera.thip.base.generale.NumeratoreException;
import it.thera.thip.cs.DatiComuniEstesi;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.produzione.documento.CausaleDocProduzione;
import it.thera.thip.produzione.documento.DocumentoProduzione;
import it.thera.thip.produzione.ordese.AttivitaEsecMateriale;
import it.thera.thip.produzione.ordese.AttivitaEsecProdotto;
import it.thera.thip.produzione.ordese.AttivitaEsecutiva;
import it.thera.thip.produzione.ordese.AttivitaEsecutivaTM;
import it.thera.thip.produzione.ordese.OrdineEsecutivo;

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
 * 72236    28/11/2025  DSSOF3   Prima stesura
 */

public class EasyCheckService {

	private static EasyCheckService instance;

	public static EasyCheckService getInstance() {
		if(instance == null) {
			instance = (EasyCheckService) Factory.createObject(EasyCheckService.class);
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public JSONObject riceviPezzaLavorata(JSONObject payload) {
		JSONObject response = new JSONObject();
		JSONObject result = new JSONObject();
		Status status = Status.OK;
		Collection<ErrorMessage> errors = new ArrayList<>();

		PezzaLavorata pezza = PezzaLavorata.fromJson(payload, errors);
		if (!errors.isEmpty()) {
			status = Status.BAD_REQUEST;
		} else {
			String bollaLavorazione = pezza.getProductionOrder();
			AttivitaEsecutiva atvEsec = leggiAtvEsec(bollaLavorazione);
			if(atvEsec != null
					&& atvEsec.getStatoAttivita() != AttivitaEsecutiva.COMPLETATO_ATV) {
				AttivitaEsecMateriale materiale = ((AttivitaEsecMateriale) atvEsec.getMateriali().get(0));
				AttivitaEsecProdotto prodotto = atvEsec.getAtvEsecPrdPrimario();
				try {

					//..carico il lotto sul materiale
					Lotto lottoMateriale = (Lotto) Lotto.elementWithKey(Lotto.class, KeyHelper.buildObjectKey(new String[] {
							Azienda.getAziendaCorrente(), materiale.getIdArticolo(), pezza.getRawPieceCode()
					}), PersistentObject.NO_LOCK);
					materiale.getLottiMateriali().add(((YAttivitaEsecMateriale)materiale).generaNuovoLottoMateriale(lottoMateriale));

					//..carico il lotto sul prodotto
					BODataCollector boDCNewLt = generaLottoPezzaLavorata(pezza, prodotto);
					if(boDCNewLt.getErrorList().getErrors().isEmpty()) {
						Lotto lottoPrd = (Lotto) boDCNewLt.getBo();
						prodotto.getLottiProdotti().add(((YAttivitaEsecProdotto)prodotto).generaNuovoLottoProdotto(lottoPrd));
						//.genero il documento di produzione

						DocumentoProduzione docPrd = creaDocumentoProduzione(atvEsec.getOrdineEsecutivo(), atvEsec, pezza.getNetQuantityMeters(), null, null, null, null);
						docPrd.caricaRighe(DatiComuniEstesi.INCOMPLETO);
						BODataCollector boDCDocPrd = YCostantiTestori.createDataCollector("DocumentoProduzione");
						docPrd.setMinutiRilevati(BigDecimal.ONE);
						boDCDocPrd.setBo(docPrd);
						boDCDocPrd.loadAttValue();
						boDCDocPrd.setAutoCommit(true);//Fix 19148
						int ret = boDCDocPrd.save();
						if (ret == BODataCollector.OK) {

						}else {
							errors.addAll(boDCDocPrd.getErrorList().getErrors());
						}
						//vediamo se tutto ok
						//.cambio i riferimenti al doc prd sul lotto?
					}else {

					}
				}catch (SQLException e) {
					e.printStackTrace(Trace.excStream);
				}

			}else {
				if(atvEsec == null)
					errors.add(new ErrorMessage("BAS0000078","Non e' stata trovata nessuna attivita con codice: "+bollaLavorazione));
				else if(atvEsec.getStatoAttivita() == AttivitaEsecutiva.COMPLETATO_ATV)
					errors.add(new ErrorMessage("BAS0000078","L'attivita e' in stato completato"));
				status = Status.INTERNAL_SERVER_ERROR;
			}
		}

		result.put("errors", ErrorUtils.getInstance().toJSON(errors));
		response.put("response", result);
		response.put("status", status);
		return response;
	}

	public BODataCollector generaLottoPezzaLavorata(PezzaLavorata pezzaLavorata, AttivitaEsecProdotto prodotto) {
		BODataCollector boDC = null;
		try {
			boDC = YCostantiTestori.createDataCollector("Lotto");
			Lotto lt = (Lotto) boDC.getBo();
			lt.setCodiceAzienda(Azienda.getAziendaCorrente());
			lt.setArticolo(prodotto.getArticolo());
			lt.setCodiceLotto(pezzaLavorata.getHeaderID());
			lt.retrieve();
			if(lt instanceof YLotto) {
				((YLotto) lt).setPezzaGreggia(pezzaLavorata.getRawPieceCode());
				((YLotto) lt).setQuantitaBonifico(pezzaLavorata.getAllowance());
				((YLotto) lt).setAltezzaMinima(pezzaLavorata.getMinWidth());
				((YLotto) lt).setAltezzaMassima(pezzaLavorata.getMaxWidth());
				((YLotto) lt).setPesoKg(pezzaLavorata.getPieceWeight());
				((YLotto) lt).setIdDifettosita(pezzaLavorata.getFinalQuality());
				if(pezzaLavorata.getWidthQuality() != null && !pezzaLavorata.getWidthQuality().equals("1"))
					((YLotto) lt).setIdDifettositaAltezza(pezzaLavorata.getWidthQuality());
			}
			lt.setNote(pezzaLavorata.getPieceNote());
			boDC.setAutoCommit(false);
			boDC.save();
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return boDC;
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
					&& ((AttivitaEsecMateriale) atvEsec.getMateriali().get(0)).getArticolo().getArticoloDatiMagaz().getCodAutLotProd()  == YArticoloDatiMagaz.PEZZE
					&& atvEsec.getStatoAttivita() != AttivitaEsecutiva.COMPLETATO_ATV) {
				AttivitaEsecMateriale materiale = ((AttivitaEsecMateriale) atvEsec.getMateriali().get(0));
				AttivitaEsecutiva attivita = materiale.getAttivitaEsecutiva();
				String progressivo = null;
				try {
					YLotto lotto = (YLotto) Lotto.elementWithKey(Lotto.class, KeyHelper.buildObjectKey(new String[] {
							Azienda.getAziendaCorrente(),materiale.getIdArticolo(),pezza.getRawPieceCode()}),PersistentObject.NO_LOCK);
					if(lotto != null) {
						if(attivita.isAttivitaFinale() 
								&& ((YAttivitaEsecutiva)attivita).getTipoTaglio() != TipoTaglioPezza.NON_SIGNIFICATIVO){
							progressivo = CreaLottiTestoriUtils.getMaxLottoProgressivoMonoChar(attivita.getIdAzienda(), lotto.getCodiceLotto(), ((YAttivitaEsecutiva)attivita).getTipoTaglio());
						}else {
							progressivo = CreaLottiTestoriUtils.getMaxProgressivoPezzeFromLottoNonFormattato(attivita.getIdAzienda(), lotto.getCodiceLotto());
						}
						String pieceHeaderSkid = "";
						if(progressivo == null) {
							pieceHeaderSkid = pezza.getRawPieceCode();
						}else {
							pieceHeaderSkid = lotto.getCodiceLotto() + progressivo;
						}

						result.put("success", true);
						result.put("message", "Pezza greggia processata e validata con successo.");
						result.put("pieceHeaderSkid", pieceHeaderSkid);

						JSONObject itemDataPanthera = new JSONObject();
						itemDataPanthera.put("pieceCode", pieceHeaderSkid);
						itemDataPanthera.put("itemDescription", materiale.getArticolo().getDescrizioneArticoloNLS().getDescrizione());
						itemDataPanthera.put("standardWeight", lotto.getPesoKg() != null ? lotto.getPesoKg() : 0);

						itemDataPanthera.put("parameter1", 0);
						itemDataPanthera.put("parameter2", 0); 

						itemDataPanthera.put("minHeight", String.valueOf(lotto.getAltezzaMinima()));
						itemDataPanthera.put("maxHeight", String.valueOf(lotto.getAltezzaMassima()));

						result.put("itemDataPanthera", itemDataPanthera);

					}else {
						errors.add(new ErrorMessage("BAS0000078","Lotto con codice :"+pezza.getRawPieceCode()+" su materiale "+materiale.getIdArticolo() + " non esistente in Panthera"));
						status = Status.BAD_REQUEST;
					}

				}catch (Exception e) {
					errors.add(new ErrorMessage("BAS0000078","Eccezione non gestita: "+e.getMessage()));
					status = Status.INTERNAL_SERVER_ERROR;
					e.printStackTrace(Trace.excStream);
				}
			}else {
				if(atvEsec == null)
					errors.add(new ErrorMessage("BAS0000078","Non e' stata trovata nessuna attivita con codice: "+bollaLavorazione));
				else if(atvEsec.getMateriali().size() == 0)
					errors.add(new ErrorMessage("BAS0000078","Non e' stata trovato nessun materiale per l'attivita: "+bollaLavorazione));
				else if(((AttivitaEsecMateriale) atvEsec.getMateriali().get(0)).getArticolo().getArticoloDatiMagaz().getCodAutLotProd() != YArticoloDatiMagaz.PEZZE)
					errors.add(new ErrorMessage("BAS0000078","Il materiale dell'attivita non e' una pezza"));
				else if(atvEsec.getStatoAttivita() == AttivitaEsecutiva.COMPLETATO_ATV)
					errors.add(new ErrorMessage("BAS0000078","L'attivita e' in stato completato"));
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

	protected DocumentoProduzione creaDocumentoProduzione(OrdineEsecutivo ordEsec,
			AttivitaEsecutiva atvEsec,
			BigDecimal quantita, BigDecimal quantitaScarto,
			String idUMSec, BigDecimal qtaSec, BigDecimal qtaScartoSec) throws NumeratoreException {
		InterfacciaEasyCheck interfaccia = InterfacciaEasyCheck.getCurrentInterfacciaEasyCheck();
		if(interfaccia != null)
			return creaDocumentoProduzione(ordEsec, atvEsec, quantita, quantitaScarto, idUMSec, qtaSec,
					qtaScartoSec, interfaccia.getIdNumeratoreDocPrd(),
					interfaccia.getIdSerieDocPrd(), interfaccia.getCausaleDocProd(), interfaccia.getDipendenteRilevazione());
		return null;
	}

	/**
	 * Metodo per la creazione di un documento di produzione data un'attivita e alcuni dati necessari.<br>
	 * La risorsa sul quale rileva e' quella principale.
	 * @param ordineEsecutivo
	 * @param attivitaEsecutiva
	 * @param quantita
	 * @param quantitaScarto
	 * @param idUMSec
	 * @param qtaSec
	 * @param qtaScartoSec
	 * @return
	 * @throws NumeratoreException 
	 */
	public static DocumentoProduzione creaDocumentoProduzione(OrdineEsecutivo ordEsec,
			AttivitaEsecutiva atvEsec,
			BigDecimal quantita, BigDecimal quantitaScarto,
			String idUMSec, BigDecimal qtaSec, BigDecimal qtaScartoSec,
			String idNumeratore, String idSerie, CausaleDocProduzione cauDoc, Dipendente dichiarante) throws NumeratoreException {
		DocumentoProduzione docPrd = (DocumentoProduzione) Factory.createObject(DocumentoProduzione.class);
		docPrd.setIdAzienda(Azienda.getAziendaCorrente());
		docPrd.setIdAnnoDoc(String.valueOf(TimeUtils.getCurrentYear()));
		docPrd.getNumeratoreHandler().setAnno(String.valueOf(TimeUtils.getCurrentYear()));
		docPrd.getNumeratoreHandler().setIdNumeratore(idNumeratore);
		docPrd.getNumeratoreHandler().setIdSerie(idSerie);
		docPrd.impostaSottoserie();
		docPrd.setNumeroDocFormattato(docPrd.getNumeratoreHandler().getIdProgressivoFormattato());
		docPrd.setIdNumeroDoc(docPrd.getNumeratoreHandler().getIdProgressivo());
		docPrd.setCausale(cauDoc);
		docPrd.setNumeroRitorno(atvEsec.getNumeroRitorno());
		docPrd.setDichiarante(dichiarante);
		docPrd.setRisorsa(atvEsec.getAtvEsecRsrPrincipale().getRisorsa());
		docPrd.setIdAnnoOrdine(ordEsec.getIdAnnoOrdine());
		docPrd.setIdNumeroOrd(ordEsec.getIdNumeroOrdine());
		docPrd.setNumeroOrdFormattato(ordEsec.getNumeroOrdFmt());
		docPrd.setIdRigaAttivita(atvEsec.getIdRigaAttivita());
		docPrd.setRAttivita(atvEsec.getIdAttivita());
		docPrd.setRStabilimento(ordEsec.getIdStabilimento());
		docPrd.setRArticolo(ordEsec.getIdArticolo());
		docPrd.setRVersione(ordEsec.getIdVersione());
		docPrd.setRConfigurazione(ordEsec.getIdConfigurazione());
		docPrd.setRCommessa(ordEsec.getIdCommessa());
		docPrd.setRReparto(atvEsec.getIdReparto());
		docPrd.setCentroCosto(atvEsec.getCentroCosto());
		docPrd.setRCentroLavoro(atvEsec.getIdCentroLavoro());
		docPrd.setNote(null);
		docPrd.setSaldo(DocumentoProduzione.SALDO);
		docPrd.setQuantita(quantita);
		docPrd.setQtaScarto(quantitaScarto);
		if (idUMSec != null) {
			docPrd.setQtaSec(qtaSec);
			docPrd.setQtaScartoSec(qtaScartoSec);
			if (docPrd.getQtaSec() == null || docPrd.getQtaScartoSec() == null)
				docPrd.setRicalcoloQtaFattoreConv(true);
		}
		return docPrd;
	}
}