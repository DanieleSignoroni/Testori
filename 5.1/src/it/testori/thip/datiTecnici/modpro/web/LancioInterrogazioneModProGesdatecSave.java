package it.testori.thip.datiTecnici.modpro.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.servlet.Save;

import it.testori.thip.datiTecnici.modpro.LancioInterrogazioneModProGesdatec;
import it.thera.thip.base.documenti.web.DocumentoCambiaJSP;
import it.thera.thip.base.documenti.web.DocumentoDataCollector;
import it.thera.thip.base.documenti.web.DocumentoDatiSessione;
import it.thera.thip.base.documenti.web.DocumentoNavigazioneWeb;
import it.thera.thip.base.generale.PersDatiGen;
import it.thera.thip.datiTecnici.modpro.ModelloProduttivo;
import it.thera.thip.datiTecnici.modpro.ModproEsplosione;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 07/03/2025
 * <br><br>
 * <b>71XXX    DSSOF3    07/03/2025</b>
 * <p></p>
 */

public class LancioInterrogazioneModProGesdatecSave extends Save {

	private static final long serialVersionUID = 1L;

	public static final String MOD_PRO = "MODELLO_PRODUTTIVO";
	public static final String INTERR_BO = "BO_LANCIO_INTERROGAZIONE";

	@Override
	public void actionOnObject(BODataCollector boDC, ServletEnvironment se) {
		int ret = boDC.check();
		if ( ret != BODataCollector.OK) {
			se.addErrorMessages(boDC.getErrorList().getErrors());
		}else {
			LancioInterrogazioneModProGesdatec interrogazione = (LancioInterrogazioneModProGesdatec) boDC.getBo();

			char[] tipi = new char[3]; 
			tipi[0] = ModelloProduttivo.PRODUZIONE;
			tipi[1] = ModelloProduttivo.ATTREZZAGGIO;
			tipi[2] = ModelloProduttivo.RILAVORAZIONE;

			LancioIntModProGesdatecDatiSessione datiSessione = (LancioIntModProGesdatecDatiSessione) 
					LancioIntModProGesdatecDatiSessione.getDocumentoDatiSessione(se,(DocumentoDatiSessione) Factory.createObject(LancioIntModProGesdatecDatiSessione.class));
			datiSessione.setNavigatore((DocumentoNavigazioneWeb) Factory.createObject(LancioIntModProGesdatecNavigatore.class));
			
			try {
				ModelloProduttivo modPro = ModproEsplosione.trovaModelloProduttivo(interrogazione.getIdAzienda(),
						interrogazione.getIdArticolo(), PersDatiGen.getCurrentPersDatiGen().getIdStabilimento(), null, null, ModelloProduttivo.GENERICO, tipi);
				if (modPro != null){
					datiSessione.setModelloProduttivoOriginale(modPro);
				}
			} catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}


			datiSessione.setParametrLancioInterrogazione(interrogazione);
			datiSessione.salvaInSessione(se);

			se.getRequest().setAttribute(DocumentoCambiaJSP.ERRORI_PRESENTI, false);
			se.getRequest().setAttribute(DocumentoCambiaJSP.AZIONE_RICHIESTA, "INTERROGA_MODELLO");
			se.getRequest().setAttribute(DocumentoDatiSessione.CHIAVE_DATI_SESSIONE, datiSessione.getChiaveDatiSessione());
			se.getRequest().setAttribute(DocumentoDataCollector.CARICA_DA_SESSIONE, "true");
			se.getRequest().setAttribute(CLASS_NAME, "YIntModProGesdatec");

		}
	}

	@Override
	public void afterProcessAction(BODataCollector boDC, ServletEnvironment se) throws ServletException, IOException {
		if(se.isErrorListEmpity()) {
			se.sendRequest(getServletContext(), "it/thera/thip/base/documenti/DocumentoCambiaJSP.jsp", false);
		}else {
			super.afterProcessAction(boDC, se);
		}
	}
}
