package it.testori.thip.magazzino.generalemag.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.persist.Column;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.WebSettingBar;
import com.thera.thermfw.web.WebSettingToolBarButton;
import com.thera.thermfw.web.servlet.GridActionAdapter;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 06/02/2026
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    06/02/2026  DSSOF3   Prima stesura
 */

public class YLottoSaldoRicercaGAA extends GridActionAdapter {

	private static final long serialVersionUID = 1L;

	public static final String PULISCI_FILTRO_MAGAZZINO = "PULISCI_FILTRO_MAGAZZINO";

	@Override
	public void modifySettingBar(WebSettingBar settingBar) {
		super.modifySettingBar(settingBar);
		String thRestrictConditions = settingBar.getServletEnvironment().getRequest().getParameter("thRestrictConditions");
		if(thRestrictConditions != null && thRestrictConditions.contains("YMostraBtnMostraTuttiLotti")) {
			int indexFrom = thRestrictConditions.indexOf("YMostraBtnMostraTuttiLotti") + "YMostraBtnMostraTuttiLotti=".length();
			String valore = thRestrictConditions.substring(indexFrom , indexFrom + 1);
			if(valore != null && valore.equals(String.valueOf(Column.TRUE_CHAR))) {
				WebSettingToolBarButton addToHomepage = new WebSettingToolBarButton(PULISCI_FILTRO_MAGAZZINO,
						"action_submit", "infoArea", "no", "it.testori.thip.magazzino.generalemag.resources.LottiTestori",
						PULISCI_FILTRO_MAGAZZINO, "thermweb/image/gui/cnr/delete.svg", PULISCI_FILTRO_MAGAZZINO, "none", false);
				settingBar.addButton(addToHomepage);
			}
		}
	}

	@Override
	protected void otherActions(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
		String azione = getStringParameter(se.getRequest(), ACTION);
		if(azione != null && azione.equals(PULISCI_FILTRO_MAGAZZINO)) {
			PrintWriter out = se.getResponse().getWriter();
			out.println("<script>");
			out.println("parent.document.forms[0].thSpecificDOList.value = 'it.testori.thip.magazzino.generalemag.web.YLottoSearchDOListNoMagazzino';");
			out.println("parent.runAction('REFRESH_GRID','none','infoArea','no');");
			out.println("</script>");
			out.close();
		}else {
			super.otherActions(cadc, se);
		}
	}
}
