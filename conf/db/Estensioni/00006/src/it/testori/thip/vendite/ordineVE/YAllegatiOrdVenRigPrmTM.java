/*
 * @(#)YAllegatiOrdVenRigPrmTM.java
 */

/**
 * YAllegatiOrdVenRigPrmTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 04/12/2025 at 11:32:23
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 04/12/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.vendite.ordineVE;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

public class YAllegatiOrdVenRigPrmTM extends TableManager {

  
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
   * Attributo ID_ANNO_ORD
   */
  public static final String ID_ANNO_ORD = "ID_ANNO_ORD";

  /**
   * Attributo ID_NUMERO_ORD
   */
  public static final String ID_NUMERO_ORD = "ID_NUMERO_ORD";

  /**
   * Attributo ID_RIGA_ORD
   */
  public static final String ID_RIGA_ORD = "ID_RIGA_ORD";

  /**
   * Attributo R_CLASSE
   */
  public static final String R_CLASSE = "R_CLASSE";

  /**
   * Attributo R_TIPO_DOC_DGT
   */
  public static final String R_TIPO_DOC_DGT = "R_TIPO_DOC_DGT";

  /**
   * Attributo VINCOLO
   */
  public static final String VINCOLO = "VINCOLO";

  /**
   * Attributo ID_DET_RIGA_ORD
   */
  public static final String ID_DET_RIGA_ORD = "ID_DET_RIGA_ORD";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "YALLEGATI_ORD_VEN_RIG_PRM";

  /**
   *  instance
   */
  private static TableManager cInstance;

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.testori.thip.vendite.ordineVE.YAllegatiOrdVenRigPrm.class.getName();

  
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
      cInstance = (TableManager)Factory.createObject(YAllegatiOrdVenRigPrmTM.class);
    }
    return cInstance;
  }

  /**
   *  YAllegatiOrdVenRigPrmTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public YAllegatiOrdVenRigPrmTM() throws SQLException {
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
    addAttribute("Vincolo", VINCOLO);
    addAttribute("DettaglioRigaOrdine", ID_DET_RIGA_ORD, "getIntegerObject");
    addAttribute("IdAzienda", ID_AZIENDA);
    addAttribute("AnnoDocumento", ID_ANNO_ORD);
    addAttribute("NumeroDocumento", ID_NUMERO_ORD);
    addAttribute("NumeroRigaDocumento", ID_RIGA_ORD, "getIntegerObject");
    addAttribute("IdClasse", R_CLASSE);
    addAttribute("IdTipoDocDgt", R_TIPO_DOC_DGT);
    
    addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
    setKeys(ID_AZIENDA + "," + ID_ANNO_ORD + "," + ID_NUMERO_ORD + "," + ID_RIGA_ORD + "," + R_CLASSE + "," + R_TIPO_DOC_DGT);

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
    configure(VINCOLO + ", " + ID_DET_RIGA_ORD + ", " + ID_AZIENDA + ", " + ID_ANNO_ORD
         + ", " + ID_NUMERO_ORD + ", " + ID_RIGA_ORD + ", " + R_CLASSE + ", " + R_TIPO_DOC_DGT
         + ", " + STATO + ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG
         + ", " + TIMESTAMP_AGG);
  }

}

