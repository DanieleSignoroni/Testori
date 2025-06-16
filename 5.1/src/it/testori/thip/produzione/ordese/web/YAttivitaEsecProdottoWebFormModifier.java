package it.testori.thip.produzione.ordese.web;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import com.thera.thermfw.web.WebJSTypeList;

import it.testori.thip.base.articolo.YArticoloDatiMagaz;
import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.produzione.ordese.AttivitaEsecProdotto;
import it.thera.thip.produzione.ordese.web.AttivitaEsecProdottoWebFormModifier;

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

public class YAttivitaEsecProdottoWebFormModifier extends AttivitaEsecProdottoWebFormModifier {

	@Override
	public void writeHeadElements(JspWriter out) throws IOException {
		super.writeHeadElements(out);
		out.println(WebJSTypeList.getImportForJSLibrary("it/testori/thip/magazzino/generalemag/CreaLottiTestoriUtils.js", getServletEnvironment().getRequest()));
	}

	@Override
	public void writeFormEndElements(JspWriter out) throws IOException {
		super.writeFormEndElements(out);
		AttivitaEsecProdotto prodotto =
				(AttivitaEsecProdotto)getBODataCollector().getBo();
		if(prodotto.isOnDB() && (!CreaLottiTestoriUtils.isArticoloGestioneLottiTestori(prodotto.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)
				|| prodotto.getArticolo().getArticoloDatiMagaz().getCodAutLotProd() != YArticoloDatiMagaz.PEZZE)) {
			out.println("<script>");
			out.println("document.getElementById(\"SezCodificaLottiTestori\").style.display = displayNone;");
			out.println("</script>");
		}
	}
}
