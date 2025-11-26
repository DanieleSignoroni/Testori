package it.testori.thip.logis.lgb.web;

import java.io.IOException;

import javax.servlet.ServletException;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.servlet.FormActionAdapter;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 26/11/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    26/11/2025  DSSOF3   Prima stesura
 */

public class SceltaTestataListaSpostaUdsFAA extends FormActionAdapter {

	private static final long serialVersionUID = 1L;

	@Override
	protected void save(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
		se.sendRequest(getServletContext(), se.getServletPath() + "it.testori.thip.logis.lgb.web.SceltaTestataListaSpostaUdsSave", true);
	}
}
