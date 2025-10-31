/*
 * @(#)YDocumentoVenRigaPrmTM.java
 */

/**
 * YDocumentoVenRigaPrmTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 31/10/2025 at 12:13:08
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 31/10/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.vendite.documentoVE;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import it.thera.thip.vendite.documentoVE.*;
import com.thera.thermfw.base.*;

public class YDocumentoVenRigaPrmTM extends DocumentoVenRigaPrmTM {

  
  /**
   * Attributo ID_DET_RIGA_DOC
   */
  public static final String ID_DET_RIGA_DOC = "ID_DET_RIGA_DOC";

  /**
   * Attributo R_ANNO_PIANO_RFR
   */
  public static final String R_ANNO_PIANO_RFR = "R_ANNO_PIANO_RFR";

  /**
   * Attributo R_NUMERO_PIANO_RFR
   */
  public static final String R_NUMERO_PIANO_RFR = "R_NUMERO_PIANO_RFR";

  /**
   * Attributo R_RIGA_PIANO_RFR
   */
  public static final String R_RIGA_PIANO_RFR = "R_RIGA_PIANO_RFR";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME_EXT = SystemParam.getSchema("THIPPERS") + "YDOCVEN_RIG_PRM";

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.testori.thip.vendite.documentoVE.YDocumentoVenRigaPrm.class.getName();

  
  /**
   *  YDocumentoVenRigaPrmTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 31/10/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public YDocumentoVenRigaPrmTM() throws SQLException {
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
    addAttributeOnTable("AnnoPianoRfr", R_ANNO_PIANO_RFR, TABLE_NAME_EXT);
    addAttributeOnTable("IdNumeroPianoRfr", R_NUMERO_PIANO_RFR, TABLE_NAME_EXT);
    addAttributeOnTable("IdRigaPianoRfr", R_RIGA_PIANO_RFR, "getIntegerObject", TABLE_NAME_EXT);
    
    

    getColumn(TABLE_NAME_EXT, ID_DET_RIGA_DOC).excludeFromUpdate();
    getColumn(TABLE_NAME_EXT, ID_DET_RIGA_DOC).excludeFromInsert();
  }

}

