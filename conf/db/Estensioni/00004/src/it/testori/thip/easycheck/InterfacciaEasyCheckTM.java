/*
 * @(#)InterfacciaEasyCheckTM.java
 */

/**
 * InterfacciaEasyCheckTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 01/12/2025 at 15:50:24
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 01/12/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.easycheck;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

public class InterfacciaEasyCheckTM extends TableManager {

  
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
   * Attributo R_NUMERATORE_DOC_PRD
   */
  public static final String R_NUMERATORE_DOC_PRD = "R_NUMERATORE_DOC_PRD";

  /**
   * Attributo R_SERIE_DOC_PRD
   */
  public static final String R_SERIE_DOC_PRD = "R_SERIE_DOC_PRD";

  /**
   * Attributo R_CAU_DOC_PROD
   */
  public static final String R_CAU_DOC_PROD = "R_CAU_DOC_PROD";

  /**
   * Attributo R_DIPENDENTE_RIL
   */
  public static final String R_DIPENDENTE_RIL = "R_DIPENDENTE_RIL";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "INTERFACCIA_EASY_CHECK";

  /**
   *  instance
   */
  private static TableManager cInstance;

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.testori.thip.easycheck.InterfacciaEasyCheck.class.getName();

  
  /**
   *  getInstance
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public synchronized static TableManager getInstance() throws SQLException {
    if (cInstance == null) {
      cInstance = (TableManager)Factory.createObject(InterfacciaEasyCheckTM.class);
    }
    return cInstance;
  }

  /**
   *  InterfacciaEasyCheckTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public InterfacciaEasyCheckTM() throws SQLException {
    super();
  }

  /**
   *  initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    CodeGen     Codice generato da CodeGenerator
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
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
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
    ((it.thera.thip.cs.DatiComuniEstesiTTM)getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
  }

  /**
   *  init
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  private void init() throws SQLException {
    configure(ID_AZIENDA + ", " + R_NUMERATORE_DOC_PRD + ", " + R_SERIE_DOC_PRD + ", " + R_CAU_DOC_PROD
         + ", " + R_DIPENDENTE_RIL + ", " + STATO + ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ
         + ", " + R_UTENTE_AGG + ", " + TIMESTAMP_AGG);
  }

}

