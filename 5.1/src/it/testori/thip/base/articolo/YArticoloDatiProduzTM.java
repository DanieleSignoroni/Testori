package it.testori.thip.base.articolo;

import java.sql.SQLException;

import com.thera.thermfw.base.SystemParam;

import it.thera.thip.base.articolo.ArticoloDatiProduzTM;

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
public class YArticoloDatiProduzTM extends ArticoloDatiProduzTM {

	public static final String FLOOR_STOCK_CLAV = "FLOOR_STOCK_CLAV";

	private static final String CLASS_NAME = it.testori.thip.base.articolo.YArticoloDatiProduz.class.getName();

	public static final String TABLE_NAME_EXT = SystemParam.getSchema("THIPPERS") + "YARTICOLI";

	public YArticoloDatiProduzTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		super.initialize();
		setObjClassName(CLASS_NAME);
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		linkTable(TABLE_NAME_EXT);
		addAttributeOnTable("FloorStockContoLavoro", FLOOR_STOCK_CLAV, TABLE_NAME_EXT);

	}

}