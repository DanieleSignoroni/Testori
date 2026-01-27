package it.testori.thip.base.articolo;

import java.sql.SQLException;

import com.thera.thermfw.base.SystemParam;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.TableManager;

import it.thera.thip.base.articolo.ArticoloTM;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 17/07/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72054    17/07/2025  DSSOF3   Prima stesura
 * 72099	28/08/2025	DSSOF3   Classe servizio cambiare il nome attributo
 * 72144	28/08/2025	GLSOF3	 Cambiato il tipo, da Integer a BigDecimal, all'attributo iAreaPermeabilita
 * 72320	27/01/2026	DSSOF3	 Mappare nuovo attributo
 */

public class YArticoloTM extends ArticoloTM {
	
	public static final String DIAMETRO = "DIAMETRO";

	public static final String LUNGHEZZA = "LUNGHEZZA";

	public static final String R_COD_CONF_IMBOCCATURA = "R_COD_CONF_IMBOCCATURA";

	public static final String R_COD_GIUNZ_LONG_TUBOLARE = "R_COD_GIUNZ_LONG_TUBOLARE";

	public static final String R_COD_CONF_FONDELLO = "R_COD_CONF_FONDELLO";

	public static final String FORO_PIASTRA = "FORO_PIASTRA";

	public static final String R_COD_FIBRA = "R_COD_FIBRA";

	public static final String R_COD_COMPOSIZIONE = "R_COD_COMPOSIZIONE";

	public static final String FILI_TRAMA = "FILI_TRAMA";

	public static final String FILI_ORDITO = "FILI_ORDITO";

	public static final String R_COD_INTRECCIO = "R_COD_INTRECCIO";

	public static final String AREA_PESO = "AREA_PESO";

	public static final String AREA_PERMEABILITA = "AREA_PERMEABILITA";

	public static final String LIFETESS = "LIFETESS";

	public static final String R_COD_GRUPPO_LIFETESS = "R_COD_GRUPPO_LIFETESS";

	public static final String LEFTOVER = "LEFTOVER";

	public static final String R_COD_LEFT_2 = "R_COD_LEFT_2";

	public static final String R_COD_CLASSE_FITTIZIA = "R_COD_CLASSE_FITTIZIA";

	public static final String CLIENTE = "CLIENTE";

	public static final String R_FELTRO_TESS_PREF = "R_FELTRO_TESS_PREF";

	public static final String R_COD_LEFT_2_PREF = "R_COD_LEFT_2_PREF";

	public static final String QTYPREF = "QTYPREF";

	public static final String R_COD_LEFT_FIOCCO_PRINC = "R_COD_LEFT_FIOCCO_PRINC";

	public static final String R_COD_LEFT_FIOCCO_SECOND = "R_COD_LEFT_FIOCCO_SECOND";

	public static final String R_COD_GREGGIO = "R_COD_GREGGIO";

	public static final String R_COD_MASTER = "R_COD_MASTER";

	public static final String PHARMA = "PHARMA";

	public static final String CLEAN_ROOM = "CLEAN_ROOM";

	public static final String CLASSE_SERVIZIO = "CLASSE_SERVIZIO";

	public static final String R_COD_TIPO_CLEAN = "R_COD_TIPO_CLEAN";

	public static final String LEFT1 = "LEFT1";

	public static final String QTY_GREGGIO = "QTY_GREGGIO";
	
	public static final String FLOOR_STOCK_CLAV = "FLOOR_STOCK_CLAV"; //72320

	public static final String TABLE_NAME_EXT = SystemParam.getSchema("THIPPERS") + "YARTICOLI";

	private static TableManager cInstance;
	private static final String CLASS_NAME = it.testori.thip.base.articolo.YArticolo.class.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager)Factory.createObject(YArticoloTM.class);
		}
		return cInstance;
	}


	public YArticoloTM() throws SQLException {
		super();
	}


	protected void initialize() throws SQLException {
		super.initialize();
		setObjClassName(CLASS_NAME);
	}
	
	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		linkTable(TABLE_NAME_EXT);
		addAttributeOnTable("Diametro", DIAMETRO, "getIntegerObject", TABLE_NAME_EXT);
		addAttributeOnTable("YLunghezza", LUNGHEZZA, "getIntegerObject", TABLE_NAME_EXT);
		addAttributeOnTable("ForoPiastra", FORO_PIASTRA, TABLE_NAME_EXT);
		addAttributeOnTable("FiliTrama", FILI_TRAMA, TABLE_NAME_EXT);
		addAttributeOnTable("FiliOrdito", FILI_ORDITO, TABLE_NAME_EXT);
		addAttributeOnTable("AreaPeso", AREA_PESO, "getIntegerObject", TABLE_NAME_EXT);
		//addAttributeOnTable("AreaPermeabilita", AREA_PERMEABILITA, "getIntegerObject", TABLE_NAME_EXT); //72144
		addAttributeOnTable("AreaPermeabilita", AREA_PERMEABILITA, TABLE_NAME_EXT); //72144
		addAttributeOnTable("Lifetess", LIFETESS, TABLE_NAME_EXT);
		addAttributeOnTable("Leftover", LEFTOVER, TABLE_NAME_EXT);
		addAttributeOnTable("Cliente", CLIENTE, TABLE_NAME_EXT);
		addAttributeOnTable("Qtypref", QTYPREF, TABLE_NAME_EXT);
		addAttributeOnTable("IdCodLeftFioccoSecondario", R_COD_LEFT_FIOCCO_SECOND, TABLE_NAME_EXT);
		addAttributeOnTable("Pharma", PHARMA, TABLE_NAME_EXT);
		addAttributeOnTable("CleanRoom", CLEAN_ROOM, TABLE_NAME_EXT);
		//72099 Inizio
		//addAttributeOnTable("ClasseServizio", CLASSE_SERVIZIO, TABLE_NAME_EXT);
		addAttributeOnTable("IdClasseServizio", CLASSE_SERVIZIO, TABLE_NAME_EXT);
		//72099 Fine
		addAttributeOnTable("Left1", LEFT1, TABLE_NAME_EXT);
		addAttributeOnTable("QtyGreggio", QTY_GREGGIO, TABLE_NAME_EXT);
		addAttributeOnTable("IdCodiceConfImboccatura", R_COD_CONF_IMBOCCATURA, TABLE_NAME_EXT);
		addAttributeOnTable("IdCodiceGiunzLongTubolare", R_COD_GIUNZ_LONG_TUBOLARE, TABLE_NAME_EXT);
		addAttributeOnTable("IdCodiceConfFondello", R_COD_CONF_FONDELLO, TABLE_NAME_EXT);
		addAttributeOnTable("IdCodiceFibra", R_COD_FIBRA, TABLE_NAME_EXT);
		addAttributeOnTable("IdCodiceComposizione", R_COD_COMPOSIZIONE, TABLE_NAME_EXT);
		addAttributeOnTable("IdCodiceIntreccio", R_COD_INTRECCIO, TABLE_NAME_EXT);
		addAttributeOnTable("IdCodiceGruppoLifetess", R_COD_GRUPPO_LIFETESS, TABLE_NAME_EXT);
		addAttributeOnTable("IdCodiceLeft2", R_COD_LEFT_2, TABLE_NAME_EXT);
		addAttributeOnTable("IdCodLeft2Preferenziale", R_COD_LEFT_2_PREF, TABLE_NAME_EXT);
		addAttributeOnTable("IdCodClasseFittizia", R_COD_CLASSE_FITTIZIA, TABLE_NAME_EXT);
		addAttributeOnTable("IdCodLeftFioccoPrincipale", R_COD_LEFT_FIOCCO_PRINC, TABLE_NAME_EXT);
		addAttributeOnTable("IdCodiceGreggio", R_COD_GREGGIO, TABLE_NAME_EXT);
		addAttributeOnTable("IdCodiceMaster", R_COD_MASTER, TABLE_NAME_EXT);
		addAttributeOnTable("IdCodiceTipoClean", R_COD_TIPO_CLEAN, TABLE_NAME_EXT);
		addAttributeOnTable("IdFeltroTessPreferenziale", R_FELTRO_TESS_PREF, TABLE_NAME_EXT);
		
		addAttributeOnTable("FloorStockContoLavoro", FLOOR_STOCK_CLAV, TABLE_NAME_EXT); //72320

	}
}