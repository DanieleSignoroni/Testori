package it.testori.thip.magazzino.generalemag.web;

import java.io.IOException;

import javax.servlet.ServletException;

import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.servlet.Save;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriNuovo;
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
 * 71992    04/06/2025  DSSOF3   Prima stesura
 */

public class CreaLottiTestoriNuovoSave extends Save {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterProcessAction(BODataCollector boDC, ServletEnvironment se) throws ServletException, IOException {
		if(se.isErrorListEmpity()) {
			CreaLottiTestoriNuovo bo = (CreaLottiTestoriNuovo) boDC.getBo();
			String[] params = bo.getChiaveSelezionato().split(ColonneFiltri.LISTA_SEP);
			String className = params[0];
			//String thKey = params[1];
			String url = "";
			if(bo.iLottiRilevDatiPrdTS != null && className.contains("RilevDatiPrdTS")) {
				url = "it/testori/thip/produzione/raccoltaDati/YValLottiFilatiManufattiDichVrs.jsp";
				se.getRequest().setAttribute("CreaLottiTestoriNuovo", boDC.getBo());
			}else {
				url = "it/testori/thip/magazzino/generalemag/CreaLottiTestoriCambioJsp.jsp";
				url += "?jspName=it/testori/thip/magazzino/generalemag/CreaLottiTestoriTestata.jsp";
				url += "&"+CLASS_NAME+"="+boDC.getClassADCollection().getClassName();
				se.getRequest().getSession().setAttribute("CreaLottiTestoriNuovo", boDC.getBo());
			}
			se.sendRequest(getServletContext(), url, false);			
		}else {
			super.afterProcessAction(boDC, se);
		}
	}
}