/*
 * @(#)YAttivitaEsecutivaTM.java
 */

/**
 * YAttivitaEsecutivaTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 26/06/2025 at 09:07:52
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 26/06/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.produzione.ordese;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import it.thera.thip.produzione.ordese.*;
import com.thera.thermfw.base.*;

public class YAttivitaEsecutivaTM extends AttivitaEsecutivaTM {

  
  /**
   * Attributo TIPO_TAGLIO
   */
  public static final String TIPO_TAGLIO = "TIPO_TAGLIO";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME_EXT = SystemParam.getSchema("THIPPERS") + "YORD_ESEC_ATV";

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.testori.thip.produzione.ordese.YAttivitaEsecutiva.class.getName();

  
  /**
   *  YAttivitaEsecutivaTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/06/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public YAttivitaEsecutivaTM() throws SQLException {
    super();
  }

  /**
   *  initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/06/2025    CodeGen     Codice generato da CodeGenerator
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
   * 26/06/2025    Wizard     Codice generato da Wizard
   *
   */
  protected void initializeRelation() throws SQLException {
    super.initializeRelation();
    linkTable(TABLE_NAME_EXT);
    addAttributeOnTable("TipoTaglio", TIPO_TAGLIO, TABLE_NAME_EXT);
    
    

  }

}

