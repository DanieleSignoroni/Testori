package it.testori.thip.magazzino.generalemag.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.jsp.JspWriter;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.gui.cnr.OpenType;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.web.WebFormModifier;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriNuovo;
import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaPrm;
import it.thera.thip.acquisti.ordineAC.OrdineAcquistoRigaPrm;
import it.thera.thip.cs.ColonneFiltri;
import it.thera.thip.produzione.documento.DocumentoPrdRigaVersamento;
import it.thera.thip.produzione.ordese.AttivitaEsecProdotto;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 04/06/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71992    04/06/2025  DSSOF3   Prima stesura
 */

public class CreaLottiTestoriNuovoFormModifier extends WebFormModifier {

	@Override
	public void writeHeadElements(JspWriter out) throws IOException {
		CreaLottiTestoriNuovo bo = (CreaLottiTestoriNuovo) getBODataCollector().getBo();
		if(getMode() == OpenType.NEW) {
			String chiaveSelezionato = getRequest().getParameter("ChiaveSelezionato");
			String[] params = chiaveSelezionato.split(ColonneFiltri.LISTA_SEP);
			String className = params[0];
			String thKey = params[1];
			if(className.contains("DocumentoAcquistoRigaPrm")) {
				try {
					DocumentoAcqRigaPrm docAcqRig = (DocumentoAcqRigaPrm) DocumentoAcqRigaPrm.elementWithKey(DocumentoAcqRigaPrm.class, thKey, PersistentObject.NO_LOCK);
					if(docAcqRig != null) {
						bo.setQuantita(docAcqRig.getQtaInUMPrmMag());
						char tipoProvenienza = CreaLottiTestoriUtils.ACQUISTO;
						if(docAcqRig.getLavEsterna()) {
							tipoProvenienza = CreaLottiTestoriUtils.CONTO_LAVORO;
						}
						bo.setIdLotto(CreaLottiTestoriUtils.buildNextIdProgressivoLotto(docAcqRig.getArticolo().getArticoloDatiMagaz().getCodAutLotAcq(),tipoProvenienza));
					}
				} catch (SQLException e) {
					e.printStackTrace(Trace.excStream);
				}
			}else if(className.contains("OrdineAcquistoRigaPrm")) {
				try {
					OrdineAcquistoRigaPrm docAcqRig = (OrdineAcquistoRigaPrm) OrdineAcquistoRigaPrm.elementWithKey(OrdineAcquistoRigaPrm.class, thKey, PersistentObject.NO_LOCK);
					if(docAcqRig != null) {
						bo.setQuantita(docAcqRig.getQtaInUMPrmMag());
						char tipoProvenienza = CreaLottiTestoriUtils.ACQUISTO;
						if(docAcqRig.isLavorazioneEsterna()) {
							tipoProvenienza = CreaLottiTestoriUtils.CONTO_LAVORO;
						}
						bo.setIdLotto(CreaLottiTestoriUtils.buildNextIdProgressivoLotto(docAcqRig.getArticolo().getArticoloDatiMagaz().getCodAutLotAcq(),tipoProvenienza));
					}
				} catch (SQLException e) {
					e.printStackTrace(Trace.excStream);
				}
			}else if(className.contains("AttivitaEsecProdotto")) {
				try {
					AttivitaEsecProdotto docAcqRig = (AttivitaEsecProdotto) AttivitaEsecProdotto.elementWithKey(AttivitaEsecProdotto.class, thKey, PersistentObject.NO_LOCK);
					if(docAcqRig != null) {
						bo.setQuantita(docAcqRig.getQtaRichiestaUMPrm());
						char tipoProvenienza = CreaLottiTestoriUtils.PRODUZIONE;
						bo.setIdLotto(CreaLottiTestoriUtils.buildNextIdProgressivoLotto(docAcqRig.getArticolo().getArticoloDatiMagaz().getCodAutLotAcq(),tipoProvenienza));
					}
				} catch (SQLException e) {
					e.printStackTrace(Trace.excStream);
				}
			}else if(className.contains("DocumentoPrdRigaVersam")) {
				try {
					DocumentoPrdRigaVersamento docAcqRig = (DocumentoPrdRigaVersamento) DocumentoPrdRigaVersamento.elementWithKey(DocumentoPrdRigaVersamento.class, thKey, PersistentObject.NO_LOCK);
					if(docAcqRig != null) {
						bo.setQuantita(docAcqRig.getServizioQta().getQuantitaInUMPrm());
						char tipoProvenienza = CreaLottiTestoriUtils.PRODUZIONE;
						bo.setIdLotto(CreaLottiTestoriUtils.buildNextIdProgressivoLotto(docAcqRig.getArticolo().getArticoloDatiMagaz().getCodAutLotAcq(),tipoProvenienza));
					}
				} catch (SQLException e) {
					e.printStackTrace(Trace.excStream);
				}
			}
			bo.setChiaveSelezionato(chiaveSelezionato);
		}
	}

	@Override
	public void writeBodyStartElements(JspWriter out) throws IOException {

	}

	@Override
	public void writeFormStartElements(JspWriter out) throws IOException {

	}

	@Override
	public void writeFormEndElements(JspWriter out) throws IOException {

	}

	@Override
	public void writeBodyEndElements(JspWriter out) throws IOException {

	}

}
