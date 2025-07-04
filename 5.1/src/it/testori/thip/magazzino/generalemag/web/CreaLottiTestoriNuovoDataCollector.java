package it.testori.thip.magazzino.generalemag.web;

import java.util.Vector;

import com.thera.thermfw.collector.BODataCollector;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriNuovo;
import it.thera.thip.base.articolo.Articolo;

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

public class CreaLottiTestoriNuovoDataCollector extends BODataCollector {

	@SuppressWarnings("rawtypes")
	@Override
	protected Vector runCheckAll() {
		CreaLottiTestoriNuovo bo = (CreaLottiTestoriNuovo) getBo();
		Articolo articolo = bo.getArticolo();
		if(articolo != null && articolo.getArticoloDatiMagaz().isGesScadenzaLotto()) {
			getComponentManager("DataScadenza").setMandatory(true);
		}
		return super.runCheckAll();
	}
}
