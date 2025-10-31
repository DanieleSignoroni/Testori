package it.testori.thip.vendite.documentoVE;

import java.sql.*;
import it.thera.thip.vendite.documentoVE.*;
import com.thera.thermfw.base.*;

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

public class YDocumentoVenRigaPrmTM extends DocumentoVenRigaPrmTM {

	public static final String ID_DET_RIGA_DOC = "ID_DET_RIGA_DOC";

	public static final String R_ANNO_PIANO_RFR = "R_ANNO_PIANO_RFR";

	public static final String R_NUMERO_PIANO_RFR = "R_NUMERO_PIANO_RFR";

	public static final String R_RIGA_PIANO_RFR = "R_RIGA_PIANO_RFR";

	public static final String TABLE_NAME_EXT = SystemParam.getSchema("THIPPERS") + "YDOCVEN_RIG_PRM";

	private static final String CLASS_NAME = it.testori.thip.vendite.documentoVE.YDocumentoVenRigaPrm.class.getName();

	public YDocumentoVenRigaPrmTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		super.initialize();
		setObjClassName(CLASS_NAME);
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		linkTable(TABLE_NAME_EXT);
		addAttributeOnTable("AnnoPianoRfr", R_ANNO_PIANO_RFR, TABLE_NAME_EXT);
		addAttributeOnTable("IdNumeroPianoRfr", R_NUMERO_PIANO_RFR, TABLE_NAME_EXT);
		addAttributeOnTable("IdRigaPianoRfr", R_RIGA_PIANO_RFR, "getIntegerObject", TABLE_NAME_EXT);

		getColumn(TABLE_NAME_EXT, ID_DET_RIGA_DOC).excludeFromUpdate();
		getColumn(TABLE_NAME_EXT, ID_DET_RIGA_DOC).excludeFromInsert();
	}

}