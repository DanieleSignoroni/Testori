package it.testori.thip.datiTecnici.modpro.web;

import java.io.IOException;

import javax.servlet.ServletException;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.servlet.FormActionAdapter;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 07/03/2025
 * <br><br>
 * <b>71XXX    DSSOF3    07/03/2025</b>
 * <p></p>
 */

public class LancioInterrogazioneModProGesdatecFormActionAdapter extends FormActionAdapter {

	private static final long serialVersionUID = 1L;

	public static final String CONFERMA_INTERROGAZIONE = "CONFERMA_INTERROGAZIONE";

	@Override
	protected void otherActions(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
		String action = se.getRequest().getParameter(ACTION).toUpperCase();
		if(action.equals(CONFERMA_INTERROGAZIONE)) {
			save(cadc, se);
		}else {
			super.otherActions(cadc, se);
		}
	}

	@Override
	protected void save(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
		se.sendRequest(getServletContext(), se.getServletPath() + "it.testori.thip.datiTecnici.modpro.web.LancioInterrogazioneModProGesdatecSave", true);
	}

}
