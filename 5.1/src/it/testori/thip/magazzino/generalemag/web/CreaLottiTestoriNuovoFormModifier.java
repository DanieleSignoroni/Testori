package it.testori.thip.magazzino.generalemag.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

import javax.servlet.jsp.JspWriter;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.gui.cnr.OpenType;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.web.WebFormModifier;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriNuovo;
import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.testori.thip.produzione.ordese.YAttivitaEsecProdotto;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaLottoPrm;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaPrm;
import it.thera.thip.acquisti.ordineAC.OrdineAcquistoRigaLottoPrm;
import it.thera.thip.acquisti.ordineAC.OrdineAcquistoRigaPrm;
import it.thera.thip.cs.ColonneFiltri;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.produzione.documento.DocumentoPrdRigaVersamento;
import it.thera.thip.produzione.ordese.AttivitaEsecProdotto;
import it.thera.thip.produzione.ordese.AttivitaEsecutiva;
import it.thera.thip.produzione.raccoltaDati.RilevDatiPrdTS;

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
 * 72018	27/06/2025	DSSOF3	 Alert in caso di lotti gia' presenti
 * 72032	03/07/2025	DSSOF3	 Gestione rilevazione dati
 */

public class CreaLottiTestoriNuovoFormModifier extends WebFormModifier {

	@SuppressWarnings("rawtypes")
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
						bo.setQuantita(docAcqRig.getServizioQta().getQuantitaInUMPrm());
						char tipoProvenienza = CreaLottiTestoriUtils.ACQUISTO;
						if(docAcqRig.getLavEsterna()) {
							tipoProvenienza = CreaLottiTestoriUtils.CONTO_LAVORO;
						}
						bo.setIdLotto(CreaLottiTestoriUtils.buildNextIdProgressivoLotto(docAcqRig.getArticolo().getArticoloDatiMagaz().getCodAutLotAcq(),tipoProvenienza));
						Iterator iterLotti = docAcqRig.getRigheLotto().iterator();
						while(iterLotti.hasNext()) {
							DocumentoAcqRigaLottoPrm rl = (DocumentoAcqRigaLottoPrm) iterLotti.next();
							if(!rl.getIdLotto().equals(Lotto.LOTTO_DUMMY)) {
								out.println("<script>");
								out.println("window.alert('Non e possibile creare lotti automatici, sono gia presenti dei lotti nella riga');");
								out.println("window.close();");
								out.println("</script>");
								break;
							}
						}
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
						Iterator iterLotti = docAcqRig.getRigheLotto().iterator();
						while(iterLotti.hasNext()) {
							OrdineAcquistoRigaLottoPrm rl = (OrdineAcquistoRigaLottoPrm) iterLotti.next();
							if(!rl.getIdLotto().equals(Lotto.LOTTO_DUMMY)) {
								out.println("<script>");
								out.println("window.alert('Non e possibile creare lotti automatici, sono gia presenti dei lotti nella riga');");
								out.println("window.close();");
								out.println("</script>");
								break;
							}
						}
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
						bo.setIdLotto(CreaLottiTestoriUtils.buildNextIdProgressivoLotto(docAcqRig.getArticolo().getArticoloDatiMagaz().getCodAutLotProd(),tipoProvenienza));
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
						bo.setIdLotto(CreaLottiTestoriUtils.buildNextIdProgressivoLotto(docAcqRig.getArticolo().getArticoloDatiMagaz().getCodAutLotProd(),tipoProvenienza));
					}
				} catch (SQLException e) {
					e.printStackTrace(Trace.excStream);
				}
			//Fix 72032
			}else if(className.contains("RilevDatiPrdTS")) {
				RilevDatiPrdTS rilevDati = (RilevDatiPrdTS) getRequest().getAttribute("myObject");
				if(rilevDati != null) {
					AttivitaEsecutiva atv = rilevDati.getAttivitaEsecutiva();
					if(atv != null && atv.getAtvEsecPrdPrimario() != null) {
						YAttivitaEsecProdotto atvEsecPrd = (YAttivitaEsecProdotto) atv.getAtvEsecPrdPrimario();
						bo.setQuantita(rilevDati.getQtaVrsUmPrm1()); //.La prima che propone al primo lotto
						char tipoProvenienza = CreaLottiTestoriUtils.PRODUZIONE;
						bo.setIdLotto(CreaLottiTestoriUtils.buildNextIdProgressivoLotto(atvEsecPrd.getArticolo().getArticoloDatiMagaz().getCodAutLotProd(),tipoProvenienza));
					}
				}
			}
			//Fix 72032
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
