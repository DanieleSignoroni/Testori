package it.testori.thip.magazzino.generalemag;

import java.sql.*;
import it.thera.thip.magazzino.generalemag.*;
import com.thera.thermfw.base.*;

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

public class YLottoTM extends LottoTM {

	public static final String NUMERO_ROCCHE = "NUMERO_ROCCHE";

	public static final String QTA_ORIG_LOTTO = "QTA_ORIG_LOTTO";

	public static final String ID_DIFETTOSITA = "ID_DIFETTOSITA";

	public static final String ID_DIFETTOSITA_MISURE = "ID_DIFETTOSITA_MISURE";

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
		addAttributeOnTable("QuantitaOriginale", QTA_ORIG_LOTTO, TABLE_NAME_EXT);
		addAttributeOnTable("IdDifettosita", ID_DIFETTOSITA, TABLE_NAME_EXT);
		addAttributeOnTable("IdDifettositaMisure", ID_DIFETTOSITA_MISURE, TABLE_NAME_EXT);

	}

}

