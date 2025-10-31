package it.testori.thip.produzione.rifornLinea;

import java.sql.SQLException;

import com.thera.thermfw.base.SystemParam;

import it.thera.thip.produzione.rifornLinea.PianoRifornimentoRigaTM;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 31/10/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    31/10/2025  DSSOF3   Prima stesura
 */

public class YPianoRifornimentoRigaTM extends PianoRifornimentoRigaTM {

	public static final String VARCHAR01 = "VARCHAR01";

	public static final String VARCHAR02 = "VARCHAR02";

	public static final String DATE01 = "DATE01";

	public static final String DATE02 = "DATE02";

	public static final String DECIMAL01 = "DECIMAL01";

	public static final String DECIMAL02 = "DECIMAL02";

	public static final String INTEGER01 = "INTEGER01";

	public static final String INTEGER02 = "INTEGER02";

	public static final String FLAG01 = "FLAG01";

	public static final String FLAG02 = "FLAG02";

	public static final String ENUM1 = "ENUM1";

	public static final String ENUM2 = "ENUM2";

	public static final String TABLE_NAME_EXT = SystemParam.getSchema("THIPPERS") + "YPIANI_RFR_RIG";

	private static final String CLASS_NAME = it.testori.thip.produzione.rifornLinea.YPianoRifornimentoRiga.class.getName();

	public YPianoRifornimentoRigaTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		super.initialize();
		setObjClassName(CLASS_NAME);
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		linkTable(TABLE_NAME_EXT);
		addAttributeOnTable("Flag01", FLAG01, TABLE_NAME_EXT);

		addAttributeOnTable("Varchar01", VARCHAR01, TABLE_NAME_EXT);
		addAttributeOnTable("Varchar02", VARCHAR02, TABLE_NAME_EXT);
		addAttributeOnTable("Date01", DATE01, TABLE_NAME_EXT);
		addAttributeOnTable("Date02", DATE02, TABLE_NAME_EXT);
		addAttributeOnTable("Decimal01", DECIMAL01, TABLE_NAME_EXT);
		addAttributeOnTable("Decimal02", DECIMAL02, TABLE_NAME_EXT);
		addAttributeOnTable("Integer01", INTEGER01, "getIntegerObject", TABLE_NAME_EXT);
		addAttributeOnTable("Integer02", INTEGER02, "getIntegerObject", TABLE_NAME_EXT);
		addAttributeOnTable("Flag01", FLAG01, TABLE_NAME_EXT);
		addAttributeOnTable("Flag02", FLAG02, TABLE_NAME_EXT);
		addAttributeOnTable("Enum1", ENUM1, TABLE_NAME_EXT);
		addAttributeOnTable("Enum2", ENUM2, TABLE_NAME_EXT);

	}

}