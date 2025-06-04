package it.testori.thip.acquisti.ordineAC.web;

import java.io.IOException;

import javax.servlet.ServletException;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.web.ServletEnvironment;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.acquisti.ordineAC.web.OrdineAcquistoRigaPrmFormActionAdapter;
import it.thera.thip.cs.ColonneFiltri;

/**
 *
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

public class YOrdineAcquistoRigaPrmFormActionAdapter extends OrdineAcquistoRigaPrmFormActionAdapter {

	private static final long serialVersionUID = 1L;

	@Override
	protected void otherActions(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
		String azione = getAzione(se);
		if(azione.equals(CreaLottiTestoriUtils.CREA_LOTTI_NUOVO)) {
			creaLottiTestoriNuovo(cadc,se);
		}else {
			super.otherActions(cadc, se);
		}
	}

	protected void creaLottiTestoriNuovo(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
		String url = "it/testori/thip/magazzino/generalemag/CreaLottiTestoriNuovo.jsp";
		String chiaveSelezionato = getStringParameter(se.getRequest(), CLASS_NAME) + ColonneFiltri.LISTA_SEP + getStringParameter(se.getRequest(), KEY);
		url += "?ChiaveSelezionato="+chiaveSelezionato;
		se.sendRequest(getServletContext(), url, false);
	}
}
