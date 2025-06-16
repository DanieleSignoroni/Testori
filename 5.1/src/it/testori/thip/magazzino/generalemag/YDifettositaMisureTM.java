package it.testori.thip.magazzino.generalemag;

import com.thera.thermfw.persist.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 16/06/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71XXX    16/06/2025  DSSOF3   Prima stesura
 */

public class YDifettositaMisureTM extends TableManager {

	public static final String ID_AZIENDA = "ID_AZIENDA";

	public static final String CODICE = "CODICE";

	public static final String STATO = "STATO";

	public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

	public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

	public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

	public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

	public static final String DESCRIZIONE = "DESCRIZIONE";

	public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "YDIFETTOSITA_MISURE";

	private static TableManager cInstance;

	private static final String CLASS_NAME = it.testori.thip.magazzino.generalemag.YDifettositaMisure.class.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager)Factory.createObject(YDifettositaMisureTM.class);
		}
		return cInstance;
	}

	public YDifettositaMisureTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		setTableName(TABLE_NAME);
		setObjClassName(CLASS_NAME);
		init();
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		addAttribute("Codice", CODICE);
		addAttribute("Descrizione", DESCRIZIONE);
		addAttribute("IdAzienda", ID_AZIENDA);

		addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
		setKeys(ID_AZIENDA + "," + CODICE);

		setTimestampColumn("TIMESTAMP_AGG");
		((it.thera.thip.cs.DatiComuniEstesiTTM)getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
	}

	private void init() throws SQLException {
		configure();
	}

}

