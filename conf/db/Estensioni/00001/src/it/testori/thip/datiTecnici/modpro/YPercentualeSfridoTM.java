/*
 * @(#)YPercentualeSfridoTM.java
 */

/**
 * YPercentualeSfridoTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 13/03/2025 at 09:34:38
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 13/03/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.datiTecnici.modpro;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

public class YPercentualeSfridoTM extends TableManager {

  
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
   * Attributo LUNGHEZZA
   */
  public static final String LUNGHEZZA = "LUNGHEZZA";

  /**
   * Attributo PERCENTUALE
   */
  public static final String PERCENTUALE = "PERCENTUALE";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "YPERCENTUALE_SFRIDO";

  /**
   *  instance
   */
  private static TableManager cInstance;

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.testori.thip.datiTecnici.modpro.YPercentualeSfrido.class.getName();

  
  /**
   *  getInstance
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 13/03/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public synchronized static TableManager getInstance() throws SQLException {
    if (cInstance == null) {
      cInstance = (TableManager)Factory.createObject(YPercentualeSfridoTM.class);
    }
    return cInstance;
  }

  /**
   *  YPercentualeSfridoTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 13/03/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public YPercentualeSfridoTM() throws SQLException {
    super();
  }

  /**
   *  initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 13/03/2025    CodeGen     Codice generato da CodeGenerator
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
   * 13/03/2025    Wizard     Codice generato da Wizard
   *
   */
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

  /**
   *  init
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 13/03/2025    Wizard     Codice generato da Wizard
   *
   */
  private void init() throws SQLException {
    configure(LUNGHEZZA + ", " + PERCENTUALE + ", " + ID_AZIENDA + ", " + STATO
         + ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG + ", " + TIMESTAMP_AGG
        );
  }

}

