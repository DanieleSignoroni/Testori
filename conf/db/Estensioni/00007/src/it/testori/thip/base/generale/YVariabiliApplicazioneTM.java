/*
 * @(#)YVariabiliApplicazioneTM.java
 */

/**
 * YVariabiliApplicazioneTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 05/12/2025 at 16:39:33
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 05/12/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.base.generale;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

public class YVariabiliApplicazioneTM extends TableManager {

  
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
   * Attributo CODICE
   */
  public static final String CODICE = "CODICE";

  /**
   * Attributo VALORE
   */
  public static final String VALORE = "VALORE";

  /**
   * Attributo DESCR_RIDOTTA
   */
  public static final String DESCR_RIDOTTA = "DESCR_RIDOTTA";

  /**
   * Attributo DESCRIZIONE
   */
  public static final String DESCRIZIONE = "DESCRIZIONE";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "YVARIABILI_APPLICAZIONE";

  /**
   *  instance
   */
  private static TableManager cInstance;

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.testori.thip.base.generale.YVariabiliApplicazione.class.getName();

  
  /**
   *  getInstance
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 05/12/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public synchronized static TableManager getInstance() throws SQLException {
    if (cInstance == null) {
      cInstance = (TableManager)Factory.createObject(YVariabiliApplicazioneTM.class);
    }
    return cInstance;
  }

  /**
   *  YVariabiliApplicazioneTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 05/12/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public YVariabiliApplicazioneTM() throws SQLException {
    super();
  }

  /**
   *  initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 05/12/2025    CodeGen     Codice generato da CodeGenerator
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
   * 05/12/2025    Wizard     Codice generato da Wizard
   *
   */
  protected void initializeRelation() throws SQLException {
    super.initializeRelation();
    addAttribute("Codice", CODICE);
    addAttribute("Valore", VALORE);
    addAttribute("IdAzienda", ID_AZIENDA);
    
    addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
    addComponent("Descrizione", DescrizioneTTM.class);
    setKeys(ID_AZIENDA + "," + CODICE);

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
   * 05/12/2025    Wizard     Codice generato da Wizard
   *
   */
  private void init() throws SQLException {
    configure(CODICE + ", " + VALORE + ", " + ID_AZIENDA + ", " + STATO
         + ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG + ", " + TIMESTAMP_AGG
         + ", " + DESCR_RIDOTTA + ", " + DESCRIZIONE);
  }

}

