package it.testori.thip.magazzino.generalemag.web;

import java.io.IOException;

import javax.servlet.ServletException;

import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.servlet.Save;

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

public class CreaLottiTestoriNuovoSave extends Save {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterProcessAction(BODataCollector boDC, ServletEnvironment se) throws ServletException, IOException {
		//super.afterProcessAction(boDC, se);
		String url = "it/testori/thip/magazzino/generalemag/CreaLottiTestoriTestata.jsp";
		se.getRequest().setAttribute("CreaLottiTestoriNuovo", boDC.getBo());
		se.sendRequest(getServletContext(), url, false);
	}
}
