package it.testori.thip.base.articolo;

import it.thera.thip.base.articolo.ArticoloDatiProduz;

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

public class YArticoloDatiProduz extends ArticoloDatiProduz {

	protected boolean iFloorStockContoLavoro;

	public YArticoloDatiProduz() {
		super();
	}

	public boolean isFloorStockContoLavoro() {
		return iFloorStockContoLavoro;
	}

	public void setFloorStockContoLavoro(boolean iFloorStockContoLavoro) {
		this.iFloorStockContoLavoro = iFloorStockContoLavoro;
		setDirty();
	}

}
