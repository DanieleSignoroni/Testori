package it.testori.thip.magazzino.generalemag;

import java.sql.SQLException;

import com.thera.thermfw.base.SystemParam;

import it.thera.thip.magazzino.generalemag.LottoTM;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 22/07/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72060    22/07/2025  DSSOF3   Prima stesura
 * 72178	21/10/2025	DSSOF3   Aggiunta campi
 * 72273	08/01/2026	DSSOF3	 Aggiunta campo
 */

public class YLottoTM extends LottoTM {

	public static final String NUMERO_ROCCHE = "NUMERO_ROCCHE";

	public static final String QUANTITA_ORIGINALE = "QUANTITA_ORIGINALE";

	public static final String H_MIN = "H_MIN";

	public static final String H_MAX = "H_MAX";

	public static final String PESO_KG = "PESO_KG";

	public static final String PEZZA_GREGGIA = "PEZZA_GREGGIA";

	public static final String PESO_CALCOLATO = "PESO_CALCOLATO";

	public static final String PESO_ANAGRAFICA = "PESO_ANAGRAFICA";

	public static final String R_TIPO_RSR_TELAIO = "R_TIPO_RSR_TELAIO";

	public static final String R_LVL_RSR_TELAIO = "R_LVL_RSR_TELAIO";

	public static final String R_ID_RSR_TELAIO = "R_ID_RSR_TELAIO";

	public static final String R_DIFETTOSITA = "R_DIFETTOSITA";

	public static final String R_DIFETTOSITA_ALTEZZA = "R_DIFETTOSITA_ALTEZZA";
	
	public static final String QTA_BONIFICO = "QTA_BONIFICO"; //72178
	
	public static final String NOTE_PIAN_QLT = "NOTE_PIAN_QLT"; //72273

	public static final String TABLE_NAME_EXT = SystemParam.getSchema("THIPPERS") + "YLOTTI";

	private static final String CLASS_NAME = it.testori.thip.magazzino.generalemag.YLotto.class.getName();

	public YLottoTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		super.initialize();
		setObjClassName(CLASS_NAME);
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		linkTable(TABLE_NAME_EXT);
		addAttributeOnTable("NumeroRocche", NUMERO_ROCCHE, TABLE_NAME_EXT);
		addAttributeOnTable("QuantitaOriginale", QUANTITA_ORIGINALE, TABLE_NAME_EXT);
		addAttributeOnTable("AltezzaMinima", H_MIN, TABLE_NAME_EXT);
		addAttributeOnTable("AltezzaMassima", H_MAX, TABLE_NAME_EXT);
		addAttributeOnTable("PesoKg", PESO_KG, TABLE_NAME_EXT);
		addAttributeOnTable("PezzaGreggia", PEZZA_GREGGIA, TABLE_NAME_EXT);
		addAttributeOnTable("PesoCalcolato", PESO_CALCOLATO, TABLE_NAME_EXT);
		addAttributeOnTable("PesoAnagrafica", PESO_ANAGRAFICA, TABLE_NAME_EXT);
		addAttributeOnTable("IdTipoRisorsaTelaio", R_TIPO_RSR_TELAIO, TABLE_NAME_EXT);
		addAttributeOnTable("IdLivelloRisorsaTelaio", R_LVL_RSR_TELAIO, TABLE_NAME_EXT);
		addAttributeOnTable("IdRisorsaTelaio", R_ID_RSR_TELAIO, TABLE_NAME_EXT);
		addAttributeOnTable("IdDifettosita", R_DIFETTOSITA, TABLE_NAME_EXT);
		addAttributeOnTable("IdDifettositaAltezza", R_DIFETTOSITA_ALTEZZA, TABLE_NAME_EXT);
		
		addAttributeOnTable("QuantitaBonifico", QTA_BONIFICO, TABLE_NAME_EXT); //72178
		
		addAttributeOnTable("NotePianQlt", NOTE_PIAN_QLT, TABLE_NAME_EXT); //72273

	}

}