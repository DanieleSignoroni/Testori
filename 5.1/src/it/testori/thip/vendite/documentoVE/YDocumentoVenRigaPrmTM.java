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
 * 72191    31/10/2025  DSSOF3   Prima stesura
 * 72273	08/01/2026	DSSOF3	 Aggiunta valore totale riga in valuta a db.
 */

public class YDocumentoVenRigaPrmTM extends DocumentoVenRigaPrmTM {

	public static final String ID_DET_RIGA_DOC = "ID_DET_RIGA_DOC";

	public static final String R_ANNO_PIANO_RFR = "R_ANNO_PIANO_RFR";

	public static final String R_NUMERO_PIANO_RFR = "R_NUMERO_PIANO_RFR";

	public static final String R_RIGA_PIANO_RFR = "R_RIGA_PIANO_RFR";
	
	public static final String ALTEZZA_RICHIESTA = "ATLEZZA_RICH";
	public static final String LUNGHEZZA_RICHIESTA = "LUNGHEZZA_RICH";
	public static final String ID_AREA_APPLICATIVA = "R_AREA_APPLICATIVA";
	
	public static final String YVLR_RIGA_VA = "YVLR_RIGA_VA"; //72273

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
		
		addAttributeOnTable("AltezzaRichiesta", ALTEZZA_RICHIESTA, TABLE_NAME_EXT);
		addAttributeOnTable("LunghezzaRichiesta", LUNGHEZZA_RICHIESTA, TABLE_NAME_EXT);
		addAttributeOnTable("IdAreaApplicativa", ID_AREA_APPLICATIVA, TABLE_NAME_EXT);
		
		addAttributeOnTable("YValoreRigaVA", YVLR_RIGA_VA, TABLE_NAME_EXT); //72273

		getColumn(TABLE_NAME_EXT, ID_DET_RIGA_DOC).excludeFromUpdate();
		getColumn(TABLE_NAME_EXT, ID_DET_RIGA_DOC).excludeFromInsert();
	}

}