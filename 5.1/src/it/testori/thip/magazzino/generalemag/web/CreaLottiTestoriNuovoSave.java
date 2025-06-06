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
		if(se.isErrorListEmpity()) {
			String url = "it/testori/thip/magazzino/generalemag/CreaLottiTestoriCambioJsp.jsp";
			url += "?jspName=it/testori/thip/magazzino/generalemag/CreaLottiTestoriTestata.jsp";
			url += "&"+CLASS_NAME+"="+boDC.getClassADCollection().getClassName();
			se.getRequest().getSession().setAttribute("CreaLottiTestoriNuovo", boDC.getBo());
			se.sendRequest(getServletContext(), url, false);
		}else {
			super.afterProcessAction(boDC, se);
		}
	}
}
