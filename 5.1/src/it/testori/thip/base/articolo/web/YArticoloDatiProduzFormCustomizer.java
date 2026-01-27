package it.testori.thip.base.articolo.web;

import com.thera.thermfw.web.WebFormCustomizerDefault;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 27/01/2026
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72320    27/01/2026  DSSOF3   Prima stesura
 */

public class YArticoloDatiProduzFormCustomizer extends WebFormCustomizerDefault {

	@Override
	public String getCustomizationFileName() {
		return "it/testori/thip/base/articolo/xml/YArticoloDatiProduz.xml";
	}

}
