package it.testori.thip.produzione.raccoltaDati.web;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.produzione.ordese.AttivitaEsecProdotto;
import it.thera.thip.produzione.raccoltaDati.web.CheckDatiArticoliServlet;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 04/07/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71XXX    04/07/2025  DSSOF3   Prima stesura
 */

public class YCheckDatiArticoliServlet extends CheckDatiArticoliServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public String initArticoloDatiCheckPrdPers(AttivitaEsecProdotto prodottoUnico) {
		String ret = super.initArticoloDatiCheckPrdPers(prodottoUnico);
		if(ret == null)
			ret = "";
		boolean gesLottiTestori = false;
		if(prodottoUnico != null 
				&& prodottoUnico.getIdArticolo() != null 
				&& (CreaLottiTestoriUtils.isArticoloGestionePezze(prodottoUnico.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)
						|| CreaLottiTestoriUtils.isArticoloGestioneSubbiFeltri(prodottoUnico.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE))) {
			gesLottiTestori = true;
		}
		ret += "&creaLottiTestoriAutomatico="+(gesLottiTestori ? "Y" : "N")+"";
		return ret;
	}
}
