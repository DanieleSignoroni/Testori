package it.testori.thip.vendite.ordineVE;

import java.sql.SQLException;

import com.thera.thermfw.base.SystemParam;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.TableManager;

import it.thera.thip.cs.DatiComuniEstesiTTM;

public class YAllegatiOrdVenRigPrmTM extends TableManager {

	public static final String ID_AZIENDA = "ID_AZIENDA";

	public static final String STATO = "STATO";

	public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

	public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

	public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

	public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

	public static final String ID_ANNO_ORD = "ID_ANNO_ORD";

	public static final String ID_NUMERO_ORD = "ID_NUMERO_ORD";

	public static final String ID_RIGA_ORD = "ID_RIGA_ORD";

	public static final String R_CLASSE = "R_CLASSE";

	public static final String R_TIPO_DOC_DGT = "R_TIPO_DOC_DGT";

	public static final String VINCOLO = "VINCOLO";

	public static final String ID_DET_RIGA_ORD = "ID_DET_RIGA_ORD";

	public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "YALLEGATI_ORD_VEN_RIG_PRM";

	private static TableManager cInstance;

	private static final String CLASS_NAME = it.testori.thip.vendite.ordineVE.YAllegatiOrdVenRigPrm.class.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager) Factory.createObject(YAllegatiOrdVenRigPrmTM.class);
		}
		return cInstance;
	}

	public YAllegatiOrdVenRigPrmTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		setTableName(TABLE_NAME);
		setObjClassName(CLASS_NAME);
		init();
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		addAttribute("Vincolo", VINCOLO);
		addAttribute("DettaglioRigaOrdine", ID_DET_RIGA_ORD, "getIntegerObject");
		addAttribute("IdAzienda", ID_AZIENDA);
		addAttribute("AnnoDocumento", ID_ANNO_ORD);
		addAttribute("NumeroDocumento", ID_NUMERO_ORD);
		addAttribute("NumeroRigaDocumento", ID_RIGA_ORD, "getIntegerObject");
		addAttribute("IdClasse", R_CLASSE);
		addAttribute("IdTipoDocDgt", R_TIPO_DOC_DGT);

		addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
		setKeys(ID_AZIENDA + "," + ID_ANNO_ORD + "," + ID_NUMERO_ORD + "," + ID_RIGA_ORD + "," + R_CLASSE + ","
				+ R_TIPO_DOC_DGT);

		setTimestampColumn("TIMESTAMP_AGG");
		((it.thera.thip.cs.DatiComuniEstesiTTM) getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
	}

	private void init() throws SQLException {
		configure();
	}

}
