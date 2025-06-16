/*
 * @(#)YLottoTM.java
 */

/**
 * YLottoTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 16/06/2025 at 15:15:10
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
   * Attributo TELAIO
   */
  public static final String TELAIO = "TELAIO";

  /**
   * Attributo ALTEZZA_MIN
   */
  public static final String ALTEZZA_MIN = "ALTEZZA_MIN";

  /**
   * Attributo ALTEZZA_MAX
   */
  public static final String ALTEZZA_MAX = "ALTEZZA_MAX";

  /**
   * Attributo PESO_KG
   */
  public static final String PESO_KG = "PESO_KG";

  /**
   * Attributo PESO_CALCOLATO_PZ
   */
  public static final String PESO_CALCOLATO_PZ = "PESO_CALCOLATO_PZ";

  /**
   * Attributo PESO_CALCOLATO_AR
   */
  public static final String PESO_CALCOLATO_AR = "PESO_CALCOLATO_AR";

  /**
   * Attributo ST_ETICHETTA
   */
  public static final String ST_ETICHETTA = "ST_ETICHETTA";

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
    addAttributeOnTable("Telaio", TELAIO, TABLE_NAME_EXT);
    addAttributeOnTable("AltezzaMinima", ALTEZZA_MIN, TABLE_NAME_EXT);
    addAttributeOnTable("AltezzaMassima", ALTEZZA_MAX, TABLE_NAME_EXT);
    addAttributeOnTable("PesoKg", PESO_KG, TABLE_NAME_EXT);
    addAttributeOnTable("PesoCalcolatoPezzo", PESO_CALCOLATO_PZ, TABLE_NAME_EXT);
    addAttributeOnTable("PesoCalcolatoArrotondato", PESO_CALCOLATO_AR, TABLE_NAME_EXT);
    addAttributeOnTable("StEtichetta", ST_ETICHETTA, TABLE_NAME_EXT);
    addAttributeOnTable("IdDifettosita", ID_DIFETTOSITA, TABLE_NAME_EXT);
    addAttributeOnTable("IdDifettositaMisure", ID_DIFETTOSITA_MISURE, TABLE_NAME_EXT);
    
    

  }

}

