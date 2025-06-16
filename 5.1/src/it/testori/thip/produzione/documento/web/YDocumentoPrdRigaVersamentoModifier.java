package it.testori.thip.produzione.documento.web;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import com.thera.thermfw.web.WebJSTypeList;

import it.testori.thip.base.articolo.YArticoloDatiMagaz;
import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.produzione.documento.DocumentoPrdRigaVersamento;
import it.thera.thip.produzione.documento.web.DocumentoPrdRigaVersamentoModifier;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 16/06/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71XXX    16/06/2025  DSSOF3   Prima stesura
 */

public class YDocumentoPrdRigaVersamentoModifier extends DocumentoPrdRigaVersamentoModifier {

	@Override
	public void writeHeadElements(JspWriter out) throws IOException {
		super.writeHeadElements(out);
		out.println(WebJSTypeList.getImportForJSLibrary("it/testori/thip/magazzino/generalemag/CreaLottiTestoriUtils.js", getServletEnvironment().getRequest()));
	}

	@Override
	public void writeFormEndElements(JspWriter out) throws IOException {
		super.writeFormEndElements(out);
		DocumentoPrdRigaVersamento prodotto =
				(DocumentoPrdRigaVersamento)getBODataCollector().getBo();
		if(prodotto.isOnDB() && (!CreaLottiTestoriUtils.isArticoloGestioneLottiTestori(prodotto.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)
				|| prodotto.getArticolo().getArticoloDatiMagaz().getCodAutLotProd() != YArticoloDatiMagaz.PEZZE)) {
			out.println("<script>");
			out.println("document.getElementById(\"SezCodificaLottiTestori\").style.display = displayNone;");
			out.println("</script>");
		}
	}
}
