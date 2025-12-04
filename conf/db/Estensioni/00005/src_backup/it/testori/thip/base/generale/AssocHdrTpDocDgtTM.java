/*
 * @(#)AssocHdrTpDocDgtTM.java
 */

/**
 * AssocHdrTpDocDgtTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 04/12/2025 at 11:13:50
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 04/12/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.base.generale;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

public class AssocHdrTpDocDgtTM extends TableManager {

  
  /**
   * Attributo ID_AZIENDA
   */
  public static final String ID_AZIENDA = "ID_AZIENDA";

  /**
   * Attributo STATO
   */
  public static final String STATO = "STATO";

  /**
   * Attributo R_UTENTE_CRZ
   */
  public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

  /**
   * Attributo TIMESTAMP_CRZ
   */
  public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

  /**
   * Attributo R_UTENTE_AGG
   */
  public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

  /**
   * Attributo TIMESTAMP_AGG
   */
  public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

  /**
   * Attributo R_CLASS_HDR
   */
  public static final String R_CLASS_HDR = "R_CLASS_HDR";

  /**
   * Attributo R_TIPO_DOC_DGT
   */
  public static final String R_TIPO_DOC_DGT = "R_TIPO_DOC_DGT";

  /**
   * Attributo DEFAULT
   */
  public static final String DEFAULT = "DEFAULT";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "ASSOC_HDR_TP_DOC_DGT";

  /**
   *  instance
   */
  private static TableManager cInstance;

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.testori.thip.base.generale.AssocHdrTpDocDgt.class.getName();

  
  /**
   *  getInstance
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public synchronized static TableManager getInstance() throws SQLException {
    if (cInstance == null) {
      cInstance = (TableManager)Factory.createObject(AssocHdrTpDocDgtTM.class);
    }
    return cInstance;
  }

  /**
   *  AssocHdrTpDocDgtTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public AssocHdrTpDocDgtTM() throws SQLException {
    super();
  }

  /**
   *  initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected void initialize() throws SQLException {
    setTableName(TABLE_NAME);
    setObjClassName(CLASS_NAME);
    init();
  }

  /**
   *  initializeRelation
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  protected void initializeRelation() throws SQLException {
    super.initializeRelation();
    addAttribute("Default", DEFAULT);
    addAttribute("IdAzienda", ID_AZIENDA);
    addAttribute("IdTipoDocumentoDigitale", R_TIPO_DOC_DGT);
    addAttribute("IdClassHdr", R_CLASS_HDR);
    
    addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
    setKeys(ID_AZIENDA + "," + R_CLASS_HDR + "," + R_TIPO_DOC_DGT);

    setTimestampColumn("TIMESTAMP_AGG");
    ((it.thera.thip.cs.DatiComuniEstesiTTM)getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
  }

  /**
   *  init
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  private void init() throws SQLException {
    configure(DEFAULT + ", " + ID_AZIENDA + ", " + R_TIPO_DOC_DGT + ", " + R_CLASS_HDR
         + ", " + STATO + ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG
         + ", " + TIMESTAMP_AGG);
  }

}

