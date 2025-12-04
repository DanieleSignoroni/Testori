package it.testori.thip.base.generale;

import java.sql.SQLException;

import com.thera.thermfw.base.SystemParam;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.TableManager;

import it.thera.thip.cs.DatiComuniEstesiTTM;

public class AssocHdrTpDocDgtTM extends TableManager {

	public static final String ID_AZIENDA = "ID_AZIENDA";

	public static final String STATO = "STATO";

	public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

	public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

	public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

	public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

	public static final String R_CLASS_HDR = "R_CLASS_HDR";

	public static final String R_TIPO_DOC_DGT = "R_TIPO_DOC_DGT";

	public static final String DEF_COMPILATION = "DEF_COMPILATION";

	public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "ASSOC_HDR_TP_DOC_DGT";

	private static TableManager cInstance;

	private static final String CLASS_NAME = it.testori.thip.base.generale.AssocHdrTpDocDgt.class.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager) Factory.createObject(AssocHdrTpDocDgtTM.class);
		}
		return cInstance;
	}

	public AssocHdrTpDocDgtTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		setTableName(TABLE_NAME);
		setObjClassName(CLASS_NAME);
		init();
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		addAttribute("DefCompilation", DEF_COMPILATION);
		addAttribute("IdAzienda", ID_AZIENDA);
		addAttribute("IdTipoDocumentoDigitale", R_TIPO_DOC_DGT);
		addAttribute("IdClassHdr", R_CLASS_HDR);

		addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
		setKeys(ID_AZIENDA + "," + R_CLASS_HDR + "," + R_TIPO_DOC_DGT);

		setTimestampColumn("TIMESTAMP_AGG");
		((it.thera.thip.cs.DatiComuniEstesiTTM) getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
	}

	private void init() throws SQLException {
		configure(DEF_COMPILATION + ", " + ID_AZIENDA + ", " + R_TIPO_DOC_DGT + ", " + R_CLASS_HDR + ", " + STATO + ", "
				+ R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG + ", " + TIMESTAMP_AGG);
	}

}
