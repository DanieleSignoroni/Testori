package it.testori.thip.produzione.raccoltaDati.web;

import java.io.IOException;

import javax.servlet.ServletException;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.web.ServletEnvironment;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.produzione.raccoltaDati.web.RilevDatiPrdTSFormActionAdapter;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 03/07/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71XXX    03/07/2025  DSSOF3   Prima stesura
 */

public class YRilevDatiPrdTSFormActionAdapter extends RilevDatiPrdTSFormActionAdapter {

	private static final long serialVersionUID = 1L;

	public YRilevDatiPrdTSFormActionAdapter() {
		super();
	}

	@Override
	protected void otherActions(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
		String azione = getStringParameter(se.getRequest(), ACTION).toUpperCase();
		if(azione != null && azione.equals(CreaLottiTestoriUtils.CREA_LOTTI_FILATI_MANUFATTI)) {
			azioneCreaLottiFilatiManufatti(azione, se);
		}else {
			super.otherActions(cadc, se);
		}
	}

	protected void azioneCreaLottiFilatiManufatti(String azione, ServletEnvironment se) throws ServletException, IOException {
		se.getRequest().setAttribute("Action", azione);
		se.sendRequest(getServletContext(), se.getServletPath() + Factory.getName("it.testori.thip.produzione.raccoltaDati.web.YAzioneCreaLottiFilatiManufatti", Factory.CLASS), true);
	}

}
