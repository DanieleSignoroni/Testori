package it.testori.thip.base.codificatore;

import java.sql.*;
import it.thera.thip.base.codificatore.*;
import com.thera.thermfw.base.*;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 01/07/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71XXX    01/07/2025  DSSOF3   Prima stesura
 */

public class YVariabileSchemaCodificaTM extends VariabileSchemaCodificaTM {

	public static final String RICERCA_CON_RANGE = "RICERCA_CON_RANGE";

	public static final String TABLE_NAME_EXT = SystemParam.getSchema("THIPPERS") + "YVARIABILI_COD";

	private static final String CLASS_NAME = it.testori.thip.base.codificatore.YVariabileSchemaCodifica.class.getName();

	public YVariabileSchemaCodificaTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		super.initialize();
		setObjClassName(CLASS_NAME);
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		linkTable(TABLE_NAME_EXT);
		addAttributeOnTable("RicercaConRange", RICERCA_CON_RANGE, TABLE_NAME_EXT);

	}

}

