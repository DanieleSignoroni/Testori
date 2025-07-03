package it.testori.thip.produzione.raccoltaDati;

import it.thera.thip.produzione.raccoltaDati.RilevDatiPrdTS;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 03/07/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72032    03/07/2025  DSSOF3   Prima stesura
 */

public class YRilevDatiPrdTS extends RilevDatiPrdTS {
	
	protected String iLottiFilatiManufatti = null;

	public YRilevDatiPrdTS() {
		super();
	}

	public String getLottiFilatiManufatti() {
		return iLottiFilatiManufatti;
	}

	public void setLottiFilatiManufatti(String iLottiFilatiManufatti) {
		this.iLottiFilatiManufatti = iLottiFilatiManufatti;
	}
	
	

}
