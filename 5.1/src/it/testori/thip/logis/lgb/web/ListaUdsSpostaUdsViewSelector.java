package it.testori.thip.logis.lgb.web;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.ViewSelectorDefault;

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
public class ListaUdsSpostaUdsViewSelector extends ViewSelectorDefault {

	@Override
	public String getShowGridName(ServletEnvironment se, ClassADCollection cadc, boolean searchGrid) {
		return "it/testori/thip/cs/GridNoToolBarNoMenuBar.jsp";
	}
}
