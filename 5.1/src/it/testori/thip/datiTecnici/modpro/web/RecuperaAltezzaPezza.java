package it.testori.thip.datiTecnici.modpro.web;

import javax.servlet.ServletOutputStream;

import com.thera.thermfw.ad.ClassAD;
import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.servlet.BaseServlet;

import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.azienda.Azienda;

/**
 *
 * <p>Eventuale descrizione della classe</p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 12/03/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71868    07/03/2025  AGSOF3   Aggiunto campo solo ordini programmati
 */

public class RecuperaAltezzaPezza extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void processAction(ServletEnvironment se) throws Exception {
		ServletOutputStream out = se.getResponse().getOutputStream();

		String idArticolo = getStringParameter(se.getRequest(), "IdArticolo");
		String parentClassName = getStringParameter(se.getRequest(), CLASS_NAME);

		Articolo materialePrincipale = (Articolo) Articolo.elementWithKey(Articolo.class, 
				KeyHelper.buildObjectKey(new String[] {Azienda.getAziendaCorrente(),idArticolo}), PersistentObject.NO_LOCK);
		if(materialePrincipale != null) {
			String altezza = "";
			if(materialePrincipale.getArticoloDatiTecnici().getAltezza() != null) {
				ClassADCollection cadc = getClassADCollection(parentClassName);
				ClassAD ad = cadc.getAttribute("AltezzaEffettiva");
				altezza = ad.getType().objectToString(materialePrincipale.getArticoloDatiTecnici().getAltezza());
			}
			out.println("<script language='JavaScript1.2'>");
			out.println("  eval('parent.document.forms[0].' + parent.idFromName['AltezzaEffettiva']).value = '" + altezza + "';");
			if(materialePrincipale.getIdPoliticaRiordino() != null) {
				out.println("  eval('parent.document.forms[0].' + parent.idFromName['IdPoliticaRiordino']).value = '" + materialePrincipale.getIdPoliticaRiordino() + "';");
				out.println("  eval('parent.document.forms[0].' + parent.idFromName['PoliticaRiordino.Descrizione.Descrizione']).value = '" + materialePrincipale.getPoliticaRiordino().getDescrizione().getDescrizione() + "';");
			}
			out.println("</script>");

		}
	}

}
