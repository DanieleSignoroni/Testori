/*
 * @(#)YPsnDatiRifornimentoLineaTM.java
 */

/**
 * YPsnDatiRifornimentoLineaTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 04/09/2025 at 16:16:51
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 04/09/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.produzione.rifornLinea;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

public class YPsnDatiRifornimentoLineaTM extends TableManager {

  
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
   * Attributo R_CLIENTE_INTESTATARIO
   */
  public static final String R_CLIENTE_INTESTATARIO = "R_CLIENTE_INTESTATARIO";

  /**
   * Attributo R_NUMERATORE_DOC_VEN
   */
  public static final String R_NUMERATORE_DOC_VEN = "R_NUMERATORE_DOC_VEN";

  /**
   * Attributo R_SERIE_DOC_VEN
   */
  public static final String R_SERIE_DOC_VEN = "R_SERIE_DOC_VEN";

  /**
   * Attributo R_CAU_DOC_VEN
   */
  public static final String R_CAU_DOC_VEN = "R_CAU_DOC_VEN";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "YPSN_DATI_RIF_LINEA";

  /**
   *  instance
   */
  private static TableManager cInstance;

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.testori.thip.produzione.rifornLinea.YPsnDatiRifornimentoLinea.class.getName();

  
  /**
   *  getInstance
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public synchronized static TableManager getInstance() throws SQLException {
    if (cInstance == null) {
      cInstance = (TableManager)Factory.createObject(YPsnDatiRifornimentoLineaTM.class);
    }
    return cInstance;
  }

  /**
   *  YPsnDatiRifornimentoLineaTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public YPsnDatiRifornimentoLineaTM() throws SQLException {
    super();
  }

  /**
   *  initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    CodeGen     Codice generato da CodeGenerator
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
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  protected void initializeRelation() throws SQLException {
    super.initializeRelation();
    addAttribute("IdAzienda", ID_AZIENDA);
    addAttribute("IdClienteIntestatario", R_CLIENTE_INTESTATARIO);
    addAttribute("IdNumeratoreDocVen", R_NUMERATORE_DOC_VEN);
    addAttribute("IdSerieDocVen", R_SERIE_DOC_VEN);
    addAttribute("IdCausaleDocVen", R_CAU_DOC_VEN);
    
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
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  private void init() throws SQLException {
    configure(ID_AZIENDA + ", " + R_CLIENTE_INTESTATARIO + ", " + R_NUMERATORE_DOC_VEN + ", " + R_SERIE_DOC_VEN
         + ", " + R_CAU_DOC_VEN + ", " + STATO + ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ
         + ", " + R_UTENTE_AGG + ", " + TIMESTAMP_AGG);
  }

}

