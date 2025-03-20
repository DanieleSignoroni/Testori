/*
 * @(#)YPercentualeSfridoPO.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 13/03/2025 at 09:34:38
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 13/03/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.datiTecnici.modpro;
import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.base.azienda.AziendaEstesa;
import java.math.*;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class YPercentualeSfridoPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   *  instance
   */
  private static YPercentualeSfrido cInstance;

  /**
   * Attributo iLunghezza
   */
  protected BigDecimal iLunghezza;

  /**
   * Attributo iPercentuale
   */
  protected BigDecimal iPercentuale;

  
  /**
   *  retrieveList
   * @param where
   * @param orderBy
   * @param optimistic
   * @return Vector
   * @throws SQLException
   * @throws ClassNotFoundException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 13/03/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (YPercentualeSfrido)Factory.createObject(YPercentualeSfrido.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return YPercentualeSfrido
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 13/03/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static YPercentualeSfrido elementWithKey(String key, int lockType) throws SQLException {
    return (YPercentualeSfrido)PersistentObject.elementWithKey(YPercentualeSfrido.class, key, lockType);
  }

  /**
   * YPercentualeSfridoPO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 13/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public YPercentualeSfridoPO() {
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param lunghezza
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 13/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setLunghezza(BigDecimal lunghezza) {
    this.iLunghezza = lunghezza;
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 13/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getLunghezza() {
    return iLunghezza;
  }

  /**
   * Valorizza l'attributo. 
   * @param percentuale
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 13/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setPercentuale(BigDecimal percentuale) {
    this.iPercentuale = percentuale;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 13/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getPercentuale() {
    return iPercentuale;
  }

  /**
   * Valorizza l'attributo. 
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 13/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdAzienda(String idAzienda) {
    iAzienda.setKey(idAzienda);
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 13/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdAzienda() {
    String key = iAzienda.getKey();
    return key;
  }

  /**
   * setEqual
   * @param obj
   * @throws CopyException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 13/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
  }

  /**
   * checkAll
   * @param components
   * @return Vector
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 13/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public Vector checkAll(BaseComponentsCollection components) {
    Vector errors = new Vector();
    components.runAllChecks(errors);
    return errors;
  }

  /**
   *  setKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 13/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setKey(String key) {
    setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
    setLunghezza(KeyHelper.stringToBigDecimal(KeyHelper.getTokenObjectKey(key, 2)));
  }

  /**
   *  getKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 13/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getKey() {
    String idAzienda = getIdAzienda();
    java.math.BigDecimal lunghezza = getLunghezza();
    Object[] keyParts = {idAzienda, lunghezza};
    return KeyHelper.buildObjectKey(keyParts);
  }

  /**
   * isDeletable
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 13/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public boolean isDeletable() {
    return checkDelete() == null;
  }

  /**
   * toString
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 13/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public String toString() {
    return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
  }

  /**
   *  getTableManager
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 13/03/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected TableManager getTableManager() throws SQLException {
    return YPercentualeSfridoTM.getInstance();
  }

}

