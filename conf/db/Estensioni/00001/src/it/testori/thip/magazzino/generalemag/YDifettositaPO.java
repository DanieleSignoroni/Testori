/*
 * @(#)YDifettositaPO.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 06/06/2025 at 12:40:29
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 06/06/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.magazzino.generalemag;
import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.base.azienda.AziendaEstesa;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class YDifettositaPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   *  instance
   */
  private static YDifettosita cInstance;

  /**
   * Attributo iCodice
   */
  protected String iCodice;

  /**
   * Attributo iDescrizione
   */
  protected String iDescrizione;

  
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
   * 06/06/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (YDifettosita)Factory.createObject(YDifettosita.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return YDifettosita
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 06/06/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static YDifettosita elementWithKey(String key, int lockType) throws SQLException {
    return (YDifettosita)PersistentObject.elementWithKey(YDifettosita.class, key, lockType);
  }

  /**
   * YDifettositaPO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 06/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public YDifettositaPO() {
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param codice
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 06/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setCodice(String codice) {
    this.iCodice = codice;
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
   * 06/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getCodice() {
    return iCodice;
  }

  /**
   * Valorizza l'attributo. 
   * @param descrizione
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 06/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setDescrizione(String descrizione) {
    this.iDescrizione = descrizione;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 06/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getDescrizione() {
    return iDescrizione;
  }

  /**
   * Valorizza l'attributo. 
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 06/06/2025    Wizard     Codice generato da Wizard
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
   * 06/06/2025    Wizard     Codice generato da Wizard
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
   * 06/06/2025    Wizard     Codice generato da Wizard
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
   * 06/06/2025    Wizard     Codice generato da Wizard
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
   * 06/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setKey(String key) {
    setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
    setCodice(KeyHelper.getTokenObjectKey(key, 2));
  }

  /**
   *  getKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 06/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getKey() {
    String idAzienda = getIdAzienda();
    String codice = getCodice();
    Object[] keyParts = {idAzienda, codice};
    return KeyHelper.buildObjectKey(keyParts);
  }

  /**
   * isDeletable
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 06/06/2025    Wizard     Codice generato da Wizard
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
   * 06/06/2025    Wizard     Codice generato da Wizard
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
   * 06/06/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected TableManager getTableManager() throws SQLException {
    return YDifettositaTM.getInstance();
  }

}

