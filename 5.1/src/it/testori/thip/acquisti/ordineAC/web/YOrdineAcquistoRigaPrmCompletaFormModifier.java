package it.testori.thip.acquisti.ordineAC.web;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import com.thera.thermfw.web.WebJSTypeList;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.acquisti.ordineAC.OrdineAcquistoRiga;
import it.thera.thip.acquisti.ordineAC.web.OrdineAcquistoRigaPrmCompletaFormModifier;

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

public class YOrdineAcquistoRigaPrmCompletaFormModifier extends OrdineAcquistoRigaPrmCompletaFormModifier {

	@Override
	public void writeHeadElements(JspWriter out) throws IOException {
		super.writeHeadElements(out);
		out.println(WebJSTypeList.getImportForJSLibrary("it/testori/thip/magazzino/generalemag/CreaLottiTestoriUtils.js", getServletEnvironment().getRequest()));
	}
	
	@Override
	public void writeFormEndElements(JspWriter out) throws IOException {
		super.writeFormEndElements(out);
		OrdineAcquistoRiga ordAcqRig =
				(OrdineAcquistoRiga)getBODataCollector().getBo();
		if(ordAcqRig.isOnDB() && !CreaLottiTestoriUtils.isArticoloGestioneLottiTestori(ordAcqRig.getArticolo(), (ordAcqRig.isLavorazioneEsterna() ? CreaLottiTestoriUtils.CONTO_LAVORO : CreaLottiTestoriUtils.ACQUISTO))) {
			out.println("<script>");
			out.println("document.getElementById(\"SezCodificaLottiTestori\").style.display = displayNone;");
			out.println("</script>");
		}
	}

}
