package it.testori.thip.datiTecnici.modpro;

import java.sql.*;
import it.thera.thip.datiTecnici.modpro.*;
import com.thera.thermfw.base.*;

/**
 *
 * <p>Eventuale descrizione della classe</p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 19/03/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71XXX    07/03/2025  AGSOF3   Aggiunto campo solo ordini programmati
 */

public class YAttivitaProduttivaTM extends AttivitaProduttivaTM {

	public static final String ATTIVITA_DEFAULT = "ATTIVITA_DEFAULT";

	public static final String TABLE_NAME_EXT = SystemParam.getSchema("THIPPERS") + "YMODPRO_ATV";

	private static final String CLASS_NAME = it.testori.thip.datiTecnici.modpro.YAttivitaProduttiva.class.getName();

	public YAttivitaProduttivaTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		super.initialize();
		setObjClassName(CLASS_NAME);
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		linkTable(TABLE_NAME_EXT);
		addAttributeOnTable("AttivitaDefault", ATTIVITA_DEFAULT, TABLE_NAME_EXT);

	}

}

