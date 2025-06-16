/*
 * @(#)YLottoTM.java
 */

/**
 * YLottoTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 16/06/2025 at 09:58:05
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 16/06/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.magazzino.generalemag;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import it.thera.thip.magazzino.generalemag.*;
import com.thera.thermfw.base.*;

public class YLottoTM extends LottoTM {

  
  /**
   * Attributo NUMERO_ROCCHE
   */
  public static final String NUMERO_ROCCHE = "NUMERO_ROCCHE";

  /**
   * Attributo QTA_ORIG_LOTTO
   */
  public static final String QTA_ORIG_LOTTO = "QTA_ORIG_LOTTO";

  /**
   * Attributo ID_DIFETTOSITA
   */
  public static final String ID_DIFETTOSITA = "ID_DIFETTOSITA";

  /**
   * Attributo ID_DIFETTOSITA_MISURE
   */
  public static final String ID_DIFETTOSITA_MISURE = "ID_DIFETTOSITA_MISURE";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME_EXT = SystemParam.getSchema("THIPPERS") + "YLOTTI";

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.testori.thip.magazzino.generalemag.YLotto.class.getName();

  
  /**
   *  YLottoTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 16/06/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public YLottoTM() throws SQLException {
    super();
  }

  /**
   *  initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 16/06/2025    CodeGen     Codice generato da CodeGenerator
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
   * 16/06/2025    Wizard     Codice generato da Wizard
   *
   */
  protected void initializeRelation() throws SQLException {
    super.initializeRelation();
    linkTable(TABLE_NAME_EXT);
    addAttributeOnTable("NumeroRocche", NUMERO_ROCCHE, TABLE_NAME_EXT);
    addAttributeOnTable("QuantitaOriginale", QTA_ORIG_LOTTO, TABLE_NAME_EXT);
    addAttributeOnTable("IdDifettosita", ID_DIFETTOSITA, TABLE_NAME_EXT);
    addAttributeOnTable("IdDifettositaMisure", ID_DIFETTOSITA_MISURE, TABLE_NAME_EXT);
    
    

  }

}

