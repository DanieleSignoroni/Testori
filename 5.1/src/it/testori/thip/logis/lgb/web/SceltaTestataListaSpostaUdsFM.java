package it.testori.thip.logis.lgb.web;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.jsp.JspWriter;

import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.URLUtils;
import com.thera.thermfw.web.WebFormModifier;

import it.testori.thip.logis.lgb.SceltaTestataListaSpostaUds;
import it.thera.thip.logis.lgb.TestataLista;

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

public class SceltaTestataListaSpostaUdsFM extends WebFormModifier {

	@Override
	public void writeHeadElements(JspWriter out) throws IOException {
		SceltaTestataListaSpostaUds bo = (SceltaTestataListaSpostaUds) getBODataCollector().getBo();
		ServletEnvironment se = getServletEnvironment();
		if(se.getSession().getAttribute("TestataLista") != null) {
			bo.setTestataLista((TestataLista) se.getSession().getAttribute("TestataLista"));
			se.getSession().removeAttribute("TestataLista");
			getBODataCollector().getComponent("CodiceTestataLista").getComponentManager().setReadOnly(true);
		}
	}

	@Override
	public void writeBodyStartElements(JspWriter out) throws IOException {
	}

	@Override
	public void writeFormStartElements(JspWriter out) throws IOException {

	}

	@Override
	public void writeFormEndElements(JspWriter out) throws IOException {

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void writeBodyEndElements(JspWriter out) throws IOException {
		SceltaTestataListaSpostaUds bo = (SceltaTestataListaSpostaUds) getBODataCollector().getBo();
		if(bo.getTestataLista() != null) {
			out.println("<script>");
			out.println("document.getElementById('bottoneConferma').parentNode.style.display=displayNone;");
			String servletPath = getServletEnvironment().getServletPath();
			if (servletPath.startsWith("/")) {
				servletPath = servletPath.substring(1);
			}
			Vector elencoUds = TestataLista.getElencoUds(bo.getTestataLista());
			Iterator iterUds = elencoUds.iterator();
			String filtri = "";
			while(iterUds.hasNext()) {
				String codice = (String) iterUds.next();
				filtri += "Codice="+codice+";";
			}
			String src = servletPath;
			src += "com.thera.thermfw.web.servlet.ShowGrid?thGridType=list&thRestrictConditions=" + URLUtils.get().encode(filtri) +"&ClassName=YListaUdsSpostaUds";
			out.println("document.getElementById('righe').src = '"+src+"';");
			out.println("document.getElementById('righe').style.display=displayBlock;");
			out.println("document.getElementById('spostaUds').parentNode.style.display=displayBlock;");
			out.println("document.getElementById('indietro').parentNode.style.display=displayBlock;");
			out.println("</script>");
		}
	}

}