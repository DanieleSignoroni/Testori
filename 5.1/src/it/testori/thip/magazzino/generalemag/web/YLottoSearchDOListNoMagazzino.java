package it.testori.thip.magazzino.generalemag.web;

import java.util.ArrayList;
import java.util.List;

import com.thera.thermfw.ad.ClassAD;

import it.thera.thip.magazzino.generalemag.web.LottoSearchDOList;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 06/02/2026
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    06/02/2026  DSSOF3   Prima stesura
 */

public class YLottoSearchDOListNoMagazzino extends LottoSearchDOList {

	@Override
	public void setRestrictCondition(ClassAD[] attributes, String[] values) {
		List<ClassAD> attrList = new ArrayList<>();
		List<String> valueList = new ArrayList<>();
		for (int i = 0; i < attributes.length; i++) {
			if (attributes[i] != null && !"CodiceMagazzino".equals(attributes[i].getAttributeName())) {
				attrList.add(attributes[i]);
				valueList.add(values[i]);
			}
		}
		ClassAD[] attributesOk = attrList.toArray(new ClassAD[0]);
		String[] valuesOk = valueList.toArray(new String[0]);

		super.setRestrictCondition(attributesOk, valuesOk);
	}
}
