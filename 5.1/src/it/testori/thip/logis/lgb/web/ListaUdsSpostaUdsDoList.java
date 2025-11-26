package it.testori.thip.logis.lgb.web;

import java.util.Arrays;

import com.thera.thermfw.ad.ClassAD;

import it.thera.thip.logis.bas.web.WebSocietaDOList;

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

public class ListaUdsSpostaUdsDoList extends WebSocietaDOList {

	@Override
	public void setRestrictCondition(ClassAD[] attributes, String[] values) {
		ClassAD[] a = Arrays.copyOf(attributes, 0);
		String[] v = Arrays.copyOf(values, 0);
		super.setRestrictCondition(a, v);
		StringBuilder sb = new StringBuilder();
		
		if(specificWhereClause == null)
			specificWhereClause = "";
		
		for (int i = 0; i < attributes.length; i++) {
			if ("Codice".equals(attributes[i].getAttributeName())) {
				if (sb.length() > 0) sb.append(" OR ");
				sb.append("PRIM.CODICE = '").append(values[i]).append("'");
			}
		}

		if (sb.length() > 0)
			specificWhereClause += " (" + sb.toString() + ")";
	}
}