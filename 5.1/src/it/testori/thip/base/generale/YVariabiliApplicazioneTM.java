package it.testori.thip.base.generale;

import java.sql.SQLException;

import com.thera.thermfw.base.SystemParam;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.TableManager;

import it.thera.thip.cs.DatiComuniEstesiTTM;
import it.thera.thip.cs.DescrizioneTTM;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 05/12/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72236    05/12/2025  DSSOF3   Prima stesura
 */

public class YVariabiliApplicazioneTM extends TableManager {

	public static final String ID_AZIENDA = "ID_AZIENDA";

	public static final String STATO = "STATO";

	public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

	public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

	public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

	public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

	public static final String CODICE = "CODICE";

	public static final String VALORE = "VALORE";

	public static final String DESCR_RIDOTTA = "DESCR_RIDOTTA";

	public static final String DESCRIZIONE = "DESCRIZIONE";

	public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "YVARIABILI_APPLICAZIONE";

	private static TableManager cInstance;

	private static final String CLASS_NAME = it.testori.thip.base.generale.YVariabiliApplicazione.class.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager) Factory.createObject(YVariabiliApplicazioneTM.class);
		}
		return cInstance;
	}

	public YVariabiliApplicazioneTM() throws SQLException {
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
		addAttribute("Valore", VALORE);
		addAttribute("IdAzienda", ID_AZIENDA);

		addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
		addComponent("Descrizione", DescrizioneTTM.class);
		setKeys(ID_AZIENDA + "," + CODICE);

		setTimestampColumn("TIMESTAMP_AGG");
		((it.thera.thip.cs.DatiComuniEstesiTTM) getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
	}

	private void init() throws SQLException {
		configure();
	}

}
