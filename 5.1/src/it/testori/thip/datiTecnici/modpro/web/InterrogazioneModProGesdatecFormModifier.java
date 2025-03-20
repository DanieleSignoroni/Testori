package it.testori.thip.datiTecnici.modpro.web;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.web.WebFormModifier;
import com.thera.thermfw.web.WebJSTypeList;

import it.testori.thip.datiTecnici.modpro.InterrogazioneModProGesdatec;
import it.testori.thip.datiTecnici.modpro.InterrogazioneModProGesdatecAttivita;
import it.testori.thip.datiTecnici.modpro.InterrogazioneModProGesdatecMateriale;
import it.testori.thip.datiTecnici.modpro.LancioInterrogazioneModProGesdatec;
import it.testori.thip.datiTecnici.modpro.YAttivitaProduttiva;
import it.thera.thip.datiTecnici.modpro.AttivitaProdMateriale;
import it.thera.thip.datiTecnici.modpro.AttivitaProduttiva;
import it.thera.thip.datiTecnici.modpro.ModelloProduttivo;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 07/03/2025
 * <br><br>
 * <b>71XXX    DSSOF3    07/03/2025</b>
 * <p></p>
 */

public class InterrogazioneModProGesdatecFormModifier extends WebFormModifier {

	@SuppressWarnings("rawtypes")
	@Override
	public void writeHeadElements(JspWriter out) throws IOException {
		out.println(WebJSTypeList.getImportForCSS("it/testori/thip/datiTecnici/modpro/css/interrogazioneModProGesdatec.css", getRequest()));

		InterrogazioneModProGesdatec bo = (InterrogazioneModProGesdatec) getBODataCollector().getBo();

		LancioIntModProGesdatecDatiSessione datiSessione = (LancioIntModProGesdatecDatiSessione) LancioIntModProGesdatecDatiSessione.getDocumentoDatiSessione(getServletEnvironment());
		if(datiSessione != null) {
			LancioInterrogazioneModProGesdatec parametriLancio = datiSessione.getParametriLancioInterrogazione();
			ModelloProduttivo modProOriginale = datiSessione.getModelloProduttivoOriginale();

			if(modProOriginale != null) {
				Iterator iterAtvs = modProOriginale.getAttivita().iterator();
				while(iterAtvs.hasNext()) {
					AttivitaProduttiva attivita = (AttivitaProduttiva) iterAtvs.next();
					if(attivita.getAttivitaIniziale()) {
						bo.setAttivitaPrincipale(attivita.getAttivita());
					}else{
						InterrogazioneModProGesdatecAttivita atv = (InterrogazioneModProGesdatecAttivita) Factory.createObject(InterrogazioneModProGesdatecAttivita.class);
						atv.setAttivita(attivita.getAttivita());
						bo.getRigheAttivita().add(atv);
					}
					Iterator iterMateriali = attivita.getMateriali().iterator();
					while(iterMateriali.hasNext()) {
						AttivitaProdMateriale atvMat = (AttivitaProdMateriale) iterMateriali.next();

						InterrogazioneModProGesdatecMateriale mat = (InterrogazioneModProGesdatecMateriale) Factory.createObject(InterrogazioneModProGesdatecMateriale.class);
						mat.setMateriale(atvMat.getArticolo());
						mat.setUnitaMisura(atvMat.getArticolo().getUMRiferimento());

						mat.setCoeffImpiego(null); //.Se il modelloOriginale esiste gia' allora calcolo questo

						bo.getRigheMateriale().add(mat);
					}
				}
			}

			//.Agganciamento attivita' template
			String idArticoloRic = parametriLancio.getIdArticolo().substring(2)+ "-" + "TEMPLATE";
			List modelli = bo.recuperaModelliProduttiviTemplate(idArticoloRic);
			if(modelli != null) {
				Iterator iterModelli = modelli.iterator();
				while(iterModelli.hasNext()) {
					ModelloProduttivo modelloTemplate = (ModelloProduttivo) iterModelli.next();
					Iterator iterAtvs = modelloTemplate.getAttivita().iterator();
					while(iterAtvs.hasNext()) {
						AttivitaProduttiva attivita = (AttivitaProduttiva) iterAtvs.next();
						if(attivita instanceof YAttivitaProduttiva && ((YAttivitaProduttiva) attivita).getAttivitaDefault()) {
							InterrogazioneModProGesdatecAttivita atv = (InterrogazioneModProGesdatecAttivita) Factory.createObject(InterrogazioneModProGesdatecAttivita.class);
							atv.setAttivita(attivita.getAttivita());
							bo.getRigheAttivita().add(atv);
						}
					}
				}
			}

			bo.setModelloProduttivoOrig(modProOriginale);

			bo.setArticoloPadre(parametriLancio.getArticolo());

			//bo.setPriorita(parametriLancio.getPriorita());

			getBODataCollector().setBo(bo);

			getBODataCollector().setOnBORecursive();
		}

		out.println("<script>");
		out.println("var servletAltezzaPezza = '"+servletAltezzaPezza()+"';");
		out.println("</script>");
	}

	protected String servletAltezzaPezza() {
		return Factory.createObject(it.testori.thip.datiTecnici.modpro.web.RecuperaAltezzaPezza.class).getClass().getName();
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

	@Override
	public void writeBodyEndElements(JspWriter out) throws IOException {

	}

}
