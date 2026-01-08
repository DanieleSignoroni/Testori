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
 * Date: 13/10/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72166    13/10/2025  DSSOF3   Prima stesura
 * 72191	04/11/2025	DSSOF3   Gestione piano rifornimento di linea
 * 72273	08/01/2026	DSSOF3	 Aggiunta valore totale in valuta a db.
 */

public class YDocumentoVenditaTM extends DocumentoVenditaTM {

	public static final String R_COD_CLIENTE_FINALE = "R_COD_CLIENTE_FINALE";

	public static final String ID_ANNO_PIANO_RFR = "R_ANNO_PIANO_RFR";
	public static final String ID_NUMERO_PIANO_RFR = "R_NUMERO_PIANO_RFR";
	
	public static final String YVLR_TOT_DOC_VA = "YVLR_TOT_DOC_VA"; //72273

	public static final String TABLE_NAME_EXT = SystemParam.getSchema("THIPPERS") + "YDOC_VEN_TES";

	private static final String CLASS_NAME = it.testori.thip.vendite.documentoVE.YDocumentoVendita.class.getName();

	public YDocumentoVenditaTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		super.initialize();
		setObjClassName(CLASS_NAME);
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		linkTable(TABLE_NAME_EXT);
		addAttributeOnTable("IdCodiceClienteFinale", R_COD_CLIENTE_FINALE, "getIntegerObject", TABLE_NAME_EXT);
		
		addAttributeOnTable("AnnoPianoRfr", ID_ANNO_PIANO_RFR, TABLE_NAME_EXT);
		addAttributeOnTable("IdNumeroPianoRfr", ID_NUMERO_PIANO_RFR, TABLE_NAME_EXT);
		
		addAttributeOnTable("YValoreTotDocVA", YVLR_TOT_DOC_VA, TABLE_NAME_EXT); //72273
	}
}