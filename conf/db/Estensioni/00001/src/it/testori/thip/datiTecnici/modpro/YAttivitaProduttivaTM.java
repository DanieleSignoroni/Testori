/*
 * @(#)YAttivitaProduttivaTM.java
 */

/**
 * YAttivitaProduttivaTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 19/03/2025 at 12:03:39
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
import it.thera.thip.datiTecnici.modpro.*;
import com.thera.thermfw.base.*;

public class YAttivitaProduttivaTM extends AttivitaProduttivaTM {

  
  /**
   * Attributo ATTIVITA_DEFAULT
   */
  public static final String ATTIVITA_DEFAULT = "ATTIVITA_DEFAULT";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME_EXT = SystemParam.getSchema("THIPPERS") + "YMODPRO_ATV";

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.testori.thip.datiTecnici.modpro.YAttivitaProduttiva.class.getName();

  
  /**
   *  YAttivitaProduttivaTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public YAttivitaProduttivaTM() throws SQLException {
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
    super.initialize();
    setObjClassName(CLASS_NAME);
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
    linkTable(TABLE_NAME_EXT);
    addAttributeOnTable("AttivitaDefault", ATTIVITA_DEFAULT, TABLE_NAME_EXT);
    
    

  }

}

