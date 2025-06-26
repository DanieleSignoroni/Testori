package it.testori.thip.produzione.raccoltaDati.web;

import java.lang.reflect.Method;

import javax.servlet.jsp.JspWriter;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.Factory;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.produzione.raccoltaDati.RilevDatiPrdTS;
import it.thera.thip.produzione.raccoltaDati.web.RilevDatiPrdTSWebFormModifier;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 26/06/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71XXX    26/06/2025  DSSOF3   Prima stesura
 */

public class YRilevDatiPrdTSWebFormModifier extends RilevDatiPrdTSWebFormModifier {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void displayLottiFields(JspWriter out) {
		super.displayLottiFields(out);
		RilevDatiPrdTS bo = (RilevDatiPrdTS) getBODataCollector().getBo();
		if (bo != null  && bo.getIdProdotto1() != null) {
			try {
				for (int i = 1; i < 21; i++) {
					String getterPrd = "getProdotto" + String.valueOf(i);
					Class c = Factory.getClass(bo.getClass());
					Method prdMethod = c.getMethod(getterPrd, new Class<?>[0]);
					Articolo prodotto = (Articolo) prdMethod.invoke(bo, new Object[0]);
					boolean gesLottiTestori = false;
					if(prodotto != null 
							&& prodotto.getIdArticolo() != null 
							&& CreaLottiTestoriUtils.isArticoloGestioneLottiTestori(prodotto, CreaLottiTestoriUtils.PRODUZIONE)) {
						gesLottiTestori = true;
					}
					out.println(" var creaLottiTestoriAutomatico"+i+" = "+gesLottiTestori+";");
				}
			}catch (Exception e) {
				e.printStackTrace(Trace.excStream);
			}
		}
	}
}
