package it.testori.thip.produzione.raccoltaDati.web;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.testori.thip.produzione.raccoltaDati.YRilevDatiPrdTS;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.cs.ColonneFiltri;
import it.thera.thip.produzione.ordese.AttivitaEsecProdotto;
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
 * 72032    26/06/2025  DSSOF3   Prima stesura
 */

public class YRilevDatiPrdTSWebFormModifier extends RilevDatiPrdTSWebFormModifier {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void displayLottiFields(JspWriter out) {
		super.displayLottiFields(out);
		try {
			RilevDatiPrdTS bo = (RilevDatiPrdTS) getBODataCollector().getBo();
			if (bo != null  && bo.getIdProdotto1() != null) {
				for (int i = 1; i < 21; i++) {
					String getterPrd = "getProdotto" + String.valueOf(i);
					Class c = Factory.getClass(bo.getClass());
					Method prdMethod = c.getMethod(getterPrd, new Class<?>[0]);
					Articolo prodotto = (Articolo) prdMethod.invoke(bo, new Object[0]);
					boolean gesLottiTestori = false;
					if(prodotto != null 
							&& prodotto.getIdArticolo() != null 
							&& (CreaLottiTestoriUtils.isArticoloGestionePezze(prodotto, CreaLottiTestoriUtils.PRODUZIONE)
									|| CreaLottiTestoriUtils.isArticoloGestioneSubbiFeltri(prodotto, CreaLottiTestoriUtils.PRODUZIONE))) {
						gesLottiTestori = true;
					}
					out.println(" var creaLottiTestoriAutomatico"+i+" = "+gesLottiTestori+";");
					if(CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(bo.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)) {
						out.println("document.getElementById('CreaLottoBut"+i+"').style.display=displayNone;");
					}else if(CreaLottiTestoriUtils.isArticoloGestioneSubbiFeltri(bo.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)
							|| CreaLottiTestoriUtils.isArticoloGestionePezze(bo.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)) {
						out.println("document.getElementById('CreaLottoBut"+i+"').style.display=displayBlock;");
					}
				}
			}
			if(!CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(bo.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)) {
				out.println("document.getElementById('CreaLottiFilatiManufatti').style.display = displayNone;");
			}
		} catch (Exception e) {
			e.printStackTrace(Trace.excStream);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List recuperaLotti(AttivitaEsecProdotto prodotto, BigDecimal quantita) {
		List lotti = super.recuperaLotti(prodotto, quantita);
		YRilevDatiPrdTS bo = (YRilevDatiPrdTS) getRequest().getAttribute("myObject");
		if(bo != null && bo.getLottiFilatiManufatti() != null && !bo.getLottiFilatiManufatti().isEmpty()) {
			String[] lottiArr = bo.getLottiFilatiManufatti().split(KeyHelper.KEY_SEPARATOR);
			for(String lottoStr : lottiArr) {
				String[] values = lottoStr.split(ColonneFiltri.SEP);
				String idLotto = values[0];
				BigDecimal qta = new BigDecimal(values[1]);
				Object[] obj = new Object[2];
				obj[0] = idLotto;
				obj[1] = qta;
				lotti.add(obj);
			}
			bo.setLottiFilatiManufatti(null);
		}
		return lotti;
	}
}
