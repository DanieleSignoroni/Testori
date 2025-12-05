package it.testori.thip.magazzino.generalemag.web;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import com.thera.thermfw.web.servlet.BaseServlet;

import it.testori.thip.magazzino.generalemag.YLotto;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.magazzino.generalemag.web.LottoFormModifier;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 22/07/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72060    22/07/2025  DSSOF3   Prima stesura
 * 72242	05/12/2025	DSSOF3	 Gestione hightlight dati pers no mandatory
 */

public class YLottoFormModifier extends LottoFormModifier {

	@Override
	public void writeHeadElements(JspWriter out) throws IOException {
		super.writeHeadElements(out);
		Lotto lotto = (Lotto) getBODataCollector().getBo();
		//.Se ho aperto la form dalla dichiarazione versamenti di una pezza o un subbio setto questo flag
		Boolean highLightSubbioPezza = new Boolean(BaseServlet.getStringParameter(getServletEnvironment().getRequest(), "HightLightSubbioPezza"));
		if (highLightSubbioPezza!=null && highLightSubbioPezza.booleanValue() && lotto != null && lotto instanceof YLotto){
			((YLotto)lotto).setCompilazioneRaccoltaDati(true);
			getBODataCollector().loadAttValue();
			getBODataCollector().setOnBORecursive();
		}
	}

	@Override
	public void writeBodyEndElements(JspWriter out) throws IOException {
		super.writeBodyEndElements(out);
		//.Se ho aperto la form dalla dichiarazione versamenti di una pezza o un subbio, mostro il tab dei dati personalizzati
		Boolean highLightSubbioPezza = new Boolean(BaseServlet.getStringParameter(getServletEnvironment().getRequest(), "HightLightSubbioPezza"));
		Boolean highLightSubbioPezzaNoMandatory = new Boolean(BaseServlet.getStringParameter(getServletEnvironment().getRequest(), "HightLightSubbioPezzaNoMandatory")); //72242
		if ((highLightSubbioPezza!=null && highLightSubbioPezza.booleanValue())
				|| (highLightSubbioPezzaNoMandatory!=null && highLightSubbioPezzaNoMandatory.booleanValue())){ //72242
			out.println("<script language='JavaScript1.2'>");
			out.println("setTimeout(myTabbed.showTab('cust_tab1'),1000);");
			out.println("</script>");
		}
	}
}