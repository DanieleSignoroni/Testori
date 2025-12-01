package it.testori.thip.easycheck;

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
 * Date: 01/12/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    01/12/2025  DSSOF3   Prima stesura
 */

public class InterfacciaEasyCheckTM extends TableManager {

	public static final String ID_AZIENDA = "ID_AZIENDA";

	public static final String STATO = "STATO";

	public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

	public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

	public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

	public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

	public static final String R_NUMERATORE_DOC_PRD = "R_NUMERATORE_DOC_PRD";

	public static final String R_SERIE_DOC_PRD = "R_SERIE_DOC_PRD";

	public static final String R_CAU_DOC_PROD = "R_CAU_DOC_PROD";

	public static final String R_DIPENDENTE_RIL = "R_DIPENDENTE_RIL";

	public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "INTERFACCIA_EASY_CHECK";

	private static TableManager cInstance;

	private static final String CLASS_NAME = it.testori.thip.easycheck.InterfacciaEasyCheck.class.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager) Factory.createObject(InterfacciaEasyCheckTM.class);
		}
		return cInstance;
	}

	public InterfacciaEasyCheckTM() throws SQLException {
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
		addAttribute("IdNumeratoreDocPrd", R_NUMERATORE_DOC_PRD);
		addAttribute("IdSerieDocPrd", R_SERIE_DOC_PRD);
		addAttribute("IdCausaleDocProd", R_CAU_DOC_PROD);
		addAttribute("IdDipendenteRilev", R_DIPENDENTE_RIL);

		addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
		setKeys(ID_AZIENDA);

		setTimestampColumn("TIMESTAMP_AGG");
		((it.thera.thip.cs.DatiComuniEstesiTTM) getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
	}

	private void init() throws SQLException {
		configure(ID_AZIENDA + ", " + R_NUMERATORE_DOC_PRD + ", " + R_SERIE_DOC_PRD + ", " + R_CAU_DOC_PROD + ", "
				+ R_DIPENDENTE_RIL + ", " + STATO + ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG
				+ ", " + TIMESTAMP_AGG);
	}

}
