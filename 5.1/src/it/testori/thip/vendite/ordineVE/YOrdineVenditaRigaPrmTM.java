package it.testori.thip.vendite.ordineVE;

import java.sql.SQLException;

import com.thera.thermfw.base.SystemParam;

import it.thera.thip.vendite.ordineVE.OrdineVenditaRigaPrmTM;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 28/08/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72097    28/08/2025  DSSOF3   Prima stesura
 * 72141	24/09/2025  DSSOF3   Nuova colonna markup
 * 72157	08/10/2025	DSSOF3   Nuova colonna note cambio data consegna
 * 72272	07/01/2026	DSSOF3	 Aggiunta valore totale ordinato in valuta a db.
 * 72273	08/01/2026	DSSOF3	 Aggiunta valore residuo va.
 */

public class YOrdineVenditaRigaPrmTM extends OrdineVenditaRigaPrmTM {

	public static final String ID_DET_RIGA_ORD = "ID_DET_RIGA_ORD";

	public static final String R_COD_CAMBIO_DT_CONS = "R_COD_CAMBIO_DT_CONS";

	public static final String DATA_CONSEGNA_CFM_STORICA = "DATA_CONSEGNA_CFM_STORICA";
	
	public static final String MARKUP = "MARKUP"; //72141
	
	public static final String NOTE_CAMBIO_DT_CONS = "NOTE_CAMBIO_DT_CONS";
	
	public static final String PREZZO_EURO = "PREZZO_EURO";
	public static final String ALTEZZA_RICHIESTA = "ATLEZZA_RICH";
	public static final String LUNGHEZZA_RICHIESTA = "LUNGHEZZA_RICH";
	public static final String ID_AREA_APPLICATIVA = "R_AREA_APPLICATIVA";
	
	public static final String YVLR_ORD_VA = "YVLR_ORD_VA"; //72272
	public static final String YVLR_RES_VA = "YVLR_RES_VA"; //72273

	public static final String TABLE_NAME_EXT = SystemParam.getSchema("THIPPERS") + "YORDVEN_RIG_PRM";

	private static final String CLASS_NAME = it.testori.thip.vendite.ordineVE.YOrdineVenditaRigaPrm.class.getName();

	public YOrdineVenditaRigaPrmTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		super.initialize();
		setObjClassName(CLASS_NAME);
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		linkTable(TABLE_NAME_EXT);
		addAttributeOnTable("DataConsegnaCfmStorica", DATA_CONSEGNA_CFM_STORICA, TABLE_NAME_EXT);
		addAttributeOnTable("IdCodiceCambioDtCons", R_COD_CAMBIO_DT_CONS, TABLE_NAME_EXT);
		
		addAttributeOnTable("Markup", MARKUP, TABLE_NAME_EXT); //72141
		
		addAttributeOnTable("NoteCambioDataCons", NOTE_CAMBIO_DT_CONS, TABLE_NAME_EXT);
		
		addAttributeOnTable("PrezzoEuro", PREZZO_EURO, TABLE_NAME_EXT);
		addAttributeOnTable("AltezzaRichiesta", ALTEZZA_RICHIESTA, TABLE_NAME_EXT);
		addAttributeOnTable("LunghezzaRichiesta", LUNGHEZZA_RICHIESTA, TABLE_NAME_EXT);
		addAttributeOnTable("IdAreaApplicativa", ID_AREA_APPLICATIVA, TABLE_NAME_EXT);
		
		addAttributeOnTable("YValoreOrdinatoVA", YVLR_ORD_VA, TABLE_NAME_EXT); //72272
		addAttributeOnTable("YValoreResiduoVA", YVLR_RES_VA, TABLE_NAME_EXT); //72273

		getColumn(TABLE_NAME_EXT, ID_DET_RIGA_ORD).excludeFromUpdate();
		getColumn(TABLE_NAME_EXT, ID_DET_RIGA_ORD).excludeFromInsert();
	}

}