/*
 * @(#)YPianoRifornimentoTM.java
 */

/**
 * YPianoRifornimentoTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 31/10/2025 at 12:36:49
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 31/10/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.produzione.rifornLinea;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import it.thera.thip.produzione.rifornLinea.*;
import com.thera.thermfw.base.*;

public class YPianoRifornimentoTM extends PianoRifornimentoTM {

  
  /**
   * Attributo FLAG01
   */
  public static final String FLAG01 = "FLAG01";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME_EXT = SystemParam.getSchema("THIPPERS") + "YPIANI_RFR";

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.testori.thip.produzione.rifornLinea.YPianoRifornimento.class.getName();

  
  /**
   *  YPianoRifornimentoTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 31/10/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public YPianoRifornimentoTM() throws SQLException {
    super();
  }

  /**
   *  initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 31/10/2025    CodeGen     Codice generato da CodeGenerator
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
   * 31/10/2025    Wizard     Codice generato da Wizard
   *
   */
  protected void initializeRelation() throws SQLException {
    super.initializeRelation();
    linkTable(TABLE_NAME_EXT);
    addAttributeOnTable("Flag01", FLAG01, TABLE_NAME_EXT);
    
    

  }

}

