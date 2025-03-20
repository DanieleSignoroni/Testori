/*
 * @(#)YTempiRisorseAttivitaTM.java
 */

/**
 * YTempiRisorseAttivitaTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 19/03/2025 at 12:21:01
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 19/03/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.datiTecnici.modpro;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

public class YTempiRisorseAttivitaTM extends TableManager {

  
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
   * Attributo R_ATTIVITA
   */
  public static final String R_ATTIVITA = "R_ATTIVITA";

  /**
   * Attributo TIPO_RISORSA
   */
  public static final String TIPO_RISORSA = "TIPO_RISORSA";

  /**
   * Attributo LIVELLO_RISORSA
   */
  public static final String LIVELLO_RISORSA = "LIVELLO_RISORSA";

  /**
   * Attributo ID_RISORSA
   */
  public static final String ID_RISORSA = "ID_RISORSA";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "YTEMPI_RISORSE_ATTIVITA";

  /**
   *  instance
   */
  private static TableManager cInstance;

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.testori.thip.datiTecnici.modpro.YTempiRisorseAttivita.class.getName();

  
  /**
   *  getInstance
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public synchronized static TableManager getInstance() throws SQLException {
    if (cInstance == null) {
      cInstance = (TableManager)Factory.createObject(YTempiRisorseAttivitaTM.class);
    }
    return cInstance;
  }

  /**
   *  YTempiRisorseAttivitaTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public YTempiRisorseAttivitaTM() throws SQLException {
    super();
  }

  /**
   *  initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    CodeGen     Codice generato da CodeGenerator
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
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  protected void initializeRelation() throws SQLException {
    super.initializeRelation();
    addAttribute("IdAzienda", ID_AZIENDA);
    addAttribute("IdAttivita", R_ATTIVITA);
    addAttribute("TipoRisorsa", TIPO_RISORSA);
    addAttribute("LivelloRisorsa", LIVELLO_RISORSA);
    addAttribute("IdRisorsa", ID_RISORSA);
    
    addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
    setKeys(ID_AZIENDA + "," + R_ATTIVITA + "," + TIPO_RISORSA + "," + LIVELLO_RISORSA + "," + ID_RISORSA);

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
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  private void init() throws SQLException {
    configure(ID_AZIENDA + ", " + R_ATTIVITA + ", " + TIPO_RISORSA + ", " + LIVELLO_RISORSA
         + ", " + ID_RISORSA + ", " + STATO + ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ
         + ", " + R_UTENTE_AGG + ", " + TIMESTAMP_AGG);
  }

}

