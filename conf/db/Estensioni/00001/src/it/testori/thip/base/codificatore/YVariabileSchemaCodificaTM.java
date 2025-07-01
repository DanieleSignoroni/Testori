/*
 * @(#)YVariabileSchemaCodificaTM.java
 */

/**
 * YVariabileSchemaCodificaTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 01/07/2025 at 09:18:00
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 01/07/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.base.codificatore;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import it.thera.thip.base.codificatore.*;
import com.thera.thermfw.base.*;

public class YVariabileSchemaCodificaTM extends VariabileSchemaCodificaTM {

  
  /**
   * Attributo RICERCA_CON_RANGE
   */
  public static final String RICERCA_CON_RANGE = "RICERCA_CON_RANGE";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME_EXT = SystemParam.getSchema("THIPPERS") + "YVARIABILI_COD";

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.testori.thip.base.codificatore.YVariabileSchemaCodifica.class.getName();

  
  /**
   *  YVariabileSchemaCodificaTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/07/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public YVariabileSchemaCodificaTM() throws SQLException {
    super();
  }

  /**
   *  initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/07/2025    CodeGen     Codice generato da CodeGenerator
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
   * 01/07/2025    Wizard     Codice generato da Wizard
   *
   */
  protected void initializeRelation() throws SQLException {
    super.initializeRelation();
    linkTable(TABLE_NAME_EXT);
    addAttributeOnTable("RicercaConRange", RICERCA_CON_RANGE, TABLE_NAME_EXT);
    
    

  }

}

