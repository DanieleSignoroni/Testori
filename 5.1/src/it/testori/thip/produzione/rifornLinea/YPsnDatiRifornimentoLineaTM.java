package it.testori.thip.produzione.rifornLinea;

import java.sql.SQLException;

import com.thera.thermfw.base.SystemParam;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.TableManager;

import it.thera.thip.cs.DatiComuniEstesiTTM;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 04/09/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    04/09/2025  DSSOF3   Prima stesura
 */

public class YPsnDatiRifornimentoLineaTM extends TableManager {

	public static final String ID_AZIENDA = "ID_AZIENDA";

	public static final String STATO = "STATO";

	public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

	public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

	public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

	public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

	public static final String R_CLIENTE_INTESTATARIO = "R_CLIENTE_INTESTATARIO";

	public static final String R_NUMERATORE_DOC_VEN = "R_NUMERATORE_DOC_VEN";

	public static final String R_SERIE_DOC_VEN = "R_SERIE_DOC_VEN";

	public static final String R_CAU_DOC_VEN = "R_CAU_DOC_VEN";

	public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "YPSN_DATI_RIF_LINEA";

	private static TableManager cInstance;

	private static final String CLASS_NAME = it.testori.thip.produzione.rifornLinea.YPsnDatiRifornimentoLinea.class.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager) Factory.createObject(YPsnDatiRifornimentoLineaTM.class);
		}
		return cInstance;
	}

	public YPsnDatiRifornimentoLineaTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		setTableName(TABLE_NAME);
		setObjClassName(CLASS_NAME);
		init();
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		addAttribute("IdAzienda", ID_AZIENDA);
		addAttribute("IdClienteIntestatario", R_CLIENTE_INTESTATARIO);
		addAttribute("IdNumeratoreDocVen", R_NUMERATORE_DOC_VEN);
		addAttribute("IdSerieDocVen", R_SERIE_DOC_VEN);
		addAttribute("IdCausaleDocVen", R_CAU_DOC_VEN);

		addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
		setKeys(ID_AZIENDA);

		setTimestampColumn("TIMESTAMP_AGG");
		((it.thera.thip.cs.DatiComuniEstesiTTM) getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
	}

	private void init() throws SQLException {
		configure();
	}

}