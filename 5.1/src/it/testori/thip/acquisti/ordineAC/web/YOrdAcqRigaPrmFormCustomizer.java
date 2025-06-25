package it.testori.thip.acquisti.ordineAC.web;

import it.thera.thip.acquisti.ordineAC.web.OrdAcqRigaPrmFormCustomizer;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 25/06/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71XXX    25/06/2025  DSSOF3   Prima stesura
 */

public class YOrdAcqRigaPrmFormCustomizer extends OrdAcqRigaPrmFormCustomizer {

	@Override
	public String getCustomizationFileName() {
		return "it/testori/thip/acquisti/ordineAC/xml/YOrdAcqRigaPrm.xml";
	}
}
