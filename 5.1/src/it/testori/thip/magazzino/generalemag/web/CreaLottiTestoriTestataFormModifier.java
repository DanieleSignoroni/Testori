package it.testori.thip.magazzino.generalemag.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

import javax.servlet.jsp.JspWriter;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.gui.cnr.OpenType;
import com.thera.thermfw.persist.CopyException;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.web.WebFormModifier;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriDettaglio;
import it.testori.thip.magazzino.generalemag.CreaLottiTestoriNuovo;
import it.testori.thip.magazzino.generalemag.CreaLottiTestoriTestata;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaPrm;
import it.thera.thip.cs.ColonneFiltri;

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
 * 71XXX    04/06/2025  DSSOF3   Prima stesura
 */

public class CreaLottiTestoriTestataFormModifier extends WebFormModifier {

	@SuppressWarnings("unchecked")
	@Override
	public void writeHeadElements(JspWriter out) throws IOException {
		CreaLottiTestoriTestata bo = (CreaLottiTestoriTestata) getBODataCollector().getBo();
		if(getMode() == OpenType.NEW) {
			CreaLottiTestoriNuovo prec = (CreaLottiTestoriNuovo) getServletEnvironment().getRequest().getAttribute("CreaLottiTestoriNuovo");
			try {
				bo.setEqual(prec);
			} catch (CopyException e) {
				e.printStackTrace(Trace.excStream);
			}
			String[] params = bo.getChiaveSelezionato().split(ColonneFiltri.LISTA_SEP);
			String className = params[0];
			String thKey = params[1];
			if(className.contains("DocumentoAcquistoRigaPrm")) {
				try {
					DocumentoAcqRigaPrm docAcqRig = (DocumentoAcqRigaPrm) DocumentoAcqRigaPrm.elementWithKey(DocumentoAcqRigaPrm.class, thKey, PersistentObject.NO_LOCK);
					if(docAcqRig != null) {
						bo.setDocumento(docAcqRig.getTestata().getNumeroDocumentoFormattato());
						bo.setSoggetto(docAcqRig.getIdFornitore());
						bo.setArticolo(docAcqRig.getArticolo());
					}
				} catch (SQLException e) {
					e.printStackTrace(Trace.excStream);
				}
			}
			BigDecimal numeroConfezioni = BigDecimal.valueOf(bo.getNumeroConfezioni());
			BigDecimal qtaPerConfezione = bo.getQuantita().divide(numeroConfezioni, 6, RoundingMode.HALF_UP);

			for (int i = 0; i < numeroConfezioni.intValue(); i++) {
				CreaLottiTestoriDettaglio dett = (CreaLottiTestoriDettaglio) Factory.createObject(CreaLottiTestoriDettaglio.class);
				dett.setQuantita(qtaPerConfezione);

				// ID lotto univoco per ogni dettaglio (es: ID base + progressivo)
				dett.setIdLotto(bo.getIdLotto() + String.format("_%02d", i + 1));

				dett.setNumeroRocche(bo.getNumeroRocche());
				bo.getLottiDettaglio().add(dett);
			}

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
