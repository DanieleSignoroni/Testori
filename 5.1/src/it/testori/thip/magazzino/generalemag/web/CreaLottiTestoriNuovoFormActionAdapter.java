package it.testori.thip.magazzino.generalemag.web;

import java.io.IOException;

import javax.servlet.ServletException;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.servlet.FormActionAdapter;

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

public class CreaLottiTestoriNuovoFormActionAdapter extends FormActionAdapter {

	private static final long serialVersionUID = 1L;

	@Override
	protected void save(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
		se.sendRequest(getServletContext(), se.getServletPath() + "it.testori.thip.magazzino.generalemag.web.CreaLottiTestoriNuovoSave", false);
	}
}
