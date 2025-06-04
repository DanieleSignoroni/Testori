package it.testori.thip.magazzino.generalemag.web;

import java.util.Enumeration;

import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.collector.CollectionDataCollector;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.web.WebAjaxGridControllerDependent;
import com.thera.thermfw.web.WebAjaxGridControllerRow;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 04/06/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71XXX    04/06/2025  DSSOF3   Prima stesura
 */

public class CreaLottiTestoriDettaglioWebAjaxGridController extends WebAjaxGridControllerDependent {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ErrorMessage load() {
		CollectionDataCollector bodcRighe = (CollectionDataCollector) ((BODataCollector) getFatherBODC()).getSecondaryDataCollector(getClassCD());
		Enumeration en = bodcRighe.getRowsDataCollectors();
		int idRiga = 0;
		rows.clear();//Fix 19068
		while (en.hasMoreElements()) {
			BODataCollector rowBODC = (BODataCollector) en.nextElement();
			String orderByValue = getOrderByClassAD() != null ? rowBODC.getAttribute(getOrderByClassADName()) : null;
			rows.add(new WebAjaxGridControllerRow(idRiga++, rowBODC.getKey(), orderByValue, WebAjaxGridControllerRow.NEW_UPDATE, false, false, false, rowBODC));
		}
		orderRows();
		setUpdated(false);
		return null;
	}
}
