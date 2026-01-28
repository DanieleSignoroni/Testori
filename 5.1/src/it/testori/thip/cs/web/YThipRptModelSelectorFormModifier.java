package it.testori.thip.cs.web;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.jsp.JspWriter;

import com.thera.thermfw.base.ResourceLoader;

import it.thera.thip.cs.web.ThipRptModelSelectorFormModifier;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 28/01/2026
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    28/01/2026  DSSOF3   Prima stesura
 */

public class YThipRptModelSelectorFormModifier extends ThipRptModelSelectorFormModifier {

	public static final String RES_PERS = "it.testori.thip.vendite.documentoVE.resources.YReportDdtBollaBatch";

	@SuppressWarnings("rawtypes")
	@Override
	protected void writeSSD(JspWriter out, String classHdrName, Vector models, String action) throws IOException {
		super.writeSSD(out, classHdrName, models, action);
		out.println("setInnerText(document.getElementById('AllegatiDigitaliComLbl'), '" + ResourceLoader.getString(RES_PERS,"AllegatiDigitaliComLbl") + "');");
	}
}
