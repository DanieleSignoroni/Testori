package it.testori.thip.logis.lgb.web;

import it.thera.thip.base.generale.IntegrazioneThipLogis;
import it.thera.thip.logis.bas.web.WebSocietaDOList;
import it.thera.thip.logis.lgb.TestataListaTM;

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

public class SceltaTestataListaSpostaUdsDoList extends WebSocietaDOList {

	@Override
	public String getSpecificWhereClause() {
		String specWhereClause = super.getSpecificWhereClause();
		specWhereClause += " AND PRIM."+TestataListaTM.CODICE+" LIKE '"+IntegrazioneThipLogis.VENDITA+"%' ";
		return specWhereClause;
	}
}
