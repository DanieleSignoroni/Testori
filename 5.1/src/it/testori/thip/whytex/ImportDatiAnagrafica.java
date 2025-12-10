package it.testori.thip.whytex;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 10/12/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    10/12/2025  DSSOF3   Prima stesura
 */

public class ImportDatiAnagrafica extends ImportFileWhytex {

	@Override
	protected String getClassAdCollectionName() {
		return "YImpDatiAnagrWyhtex";
	}
}
