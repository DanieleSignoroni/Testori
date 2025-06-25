package it.testori.thip.acquisti.documentoAC.web;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import com.thera.thermfw.base.ResourceLoader;
import com.thera.thermfw.web.WebJSTypeList;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.acquisti.documentoAC.DocumentoAcquistoRiga;
import it.thera.thip.acquisti.documentoAC.web.DocumentoAcqRigaCompletaFormModifier;

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
 * 71992    04/06/2025  DSSOF3   Prima stesura
 */

public class YDocumentoAcqRigaCompletaFormModifier extends DocumentoAcqRigaCompletaFormModifier {

	public static final String RES_FILE_LOTTI_TESTORI = "it.testori.thip.magazzino.generalemag.resources.LottiTestori";

	@Override
	public void writeHeadElements(JspWriter out) throws IOException {
		super.writeHeadElements(out);
		DocumentoAcquistoRiga docAcqRig =
				(DocumentoAcquistoRiga)getBODataCollector().getBo();
		char tipoProvenienza = docAcqRig.getLavEsterna() ? CreaLottiTestoriUtils.CONTO_LAVORO : CreaLottiTestoriUtils.ACQUISTO;
		boolean creaAuto = false;
		if(docAcqRig.isOnDB() && CreaLottiTestoriUtils.isArticoloGestionePezze(docAcqRig.getArticolo(), tipoProvenienza)) {
			creaAuto = true;
		}
		out.println("<script>");
		out.println(" var creaLottiTestoriAutomatico = "+creaAuto+";");
		out.println("</script>");
		out.println(WebJSTypeList.getImportForJSLibrary("it/testori/thip/magazzino/generalemag/CreaLottiTestoriUtils.js", getServletEnvironment().getRequest()));
	}

	@Override
	public void writeFormEndElements(JspWriter out) throws IOException {
		super.writeFormEndElements(out);
		DocumentoAcquistoRiga docAcqRig =
				(DocumentoAcquistoRiga)getBODataCollector().getBo();
		char tipoProvenienza = docAcqRig.getLavEsterna() ? CreaLottiTestoriUtils.CONTO_LAVORO : CreaLottiTestoriUtils.ACQUISTO;
		if(docAcqRig.isOnDB() &&
				(CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(docAcqRig.getArticolo(), tipoProvenienza))) {
			out.println("<script>");
			out.println("function insertAfter(el, referenceNode) { referenceNode.parentNode.insertBefore(el, referenceNode.nextSibling);}");
			out.println("var btn = document.createElement('Button')");
			out.println("btn.innerHTML='"+ResourceLoader.getString(RES_FILE_LOTTI_TESTORI, "btnCreaLotti")+"';");
			out.println("btn.type='button';");
			out.println("btn.setAttribute('onclick','codificaLottiTestori()');");
			out.println("var ref = document.getElementById('AggiornaLotti');");
			out.println("btn.style.padding='0px 2px'");
			out.println("btn.style.marginLeft=10;");
			out.println("insertAfter(btn,ref);");
			out.println("</script>");
		}
	}
}
