package it.testori.thip.datiTecnici.modpro;

import com.thera.thermfw.persist.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

/**
 *
 * <p>Eventuale descrizione della classe</p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 13/03/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71868    07/03/2025  AGSOF3   Aggiunto campo solo ordini programmati
 */

public class YPercentualeSfridoTM extends TableManager {

	public static final String ID_AZIENDA = "ID_AZIENDA";

	public static final String STATO = "STATO";

	public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

	public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

	public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

	public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

	public static final String LUNGHEZZA = "LUNGHEZZA";

	public static final String PERCENTUALE = "PERCENTUALE";

	public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "YPERCENTUALE_SFRIDO";

	private static TableManager cInstance;

	private static final String CLASS_NAME = it.testori.thip.datiTecnici.modpro.YPercentualeSfrido.class.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager)Factory.createObject(YPercentualeSfridoTM.class);
		}
		return cInstance;
	}

	public YPercentualeSfridoTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		setTableName(TABLE_NAME);
		setObjClassName(CLASS_NAME);
		init();
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		addAttribute("Lunghezza", LUNGHEZZA);
		addAttribute("Percentuale", PERCENTUALE);
		addAttribute("IdAzienda", ID_AZIENDA);

		addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
		setKeys(ID_AZIENDA + "," + LUNGHEZZA);

		setTimestampColumn("TIMESTAMP_AGG");
		((it.thera.thip.cs.DatiComuniEstesiTTM)getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
	}

	private void init() throws SQLException {
		configure();
	}

}

