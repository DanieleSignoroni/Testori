/*
 * @(#)YVariabiliApplicazionePO.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 05/12/2025 at 16:39:33
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 05/12/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.base.generale;
import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.base.azienda.AziendaEstesa;
import java.math.*;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class YVariabiliApplicazionePO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   *  instance
   */
  private static YVariabiliApplicazione cInstance;

  /**
   * Attributo iCodice
   */
  protected String iCodice;

  /**
   * Attributo iValore
   */
  protected BigDecimal iValore;

  /**
   * Attributo iDescrizione
   */
  protected Descrizione iDescrizione;

  
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
   * 05/12/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (YVariabiliApplicazione)Factory.createObject(YVariabiliApplicazione.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return YVariabiliApplicazione
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 05/12/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static YVariabiliApplicazione elementWithKey(String key, int lockType) throws SQLException {
    return (YVariabiliApplicazione)PersistentObject.elementWithKey(YVariabiliApplicazione.class, key, lockType);
  }

  /**
   * YVariabiliApplicazionePO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 05/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public YVariabiliApplicazionePO() {
    iDescrizione = (Descrizione) Factory.createObject(Descrizione.class);
    iDescrizione.setOwner(this);
    
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param codice
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 05/12/2025    Wizard     Codice generato da Wizard
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
   * 05/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getCodice() {
    return iCodice;
  }

  /**
   * Valorizza l'attributo. 
   * @param valore
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 05/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setValore(BigDecimal valore) {
    this.iValore = valore;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 05/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getValore() {
    return iValore;
  }

  /**
   * Valorizza l'attributo. 
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 05/12/2025    Wizard     Codice generato da Wizard
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
   * 05/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdAzienda() {
    String key = iAzienda.getKey();
    return key;
  }

  /**
   * Restituisce l'attributo. 
   * @return Descrizione
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 05/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public Descrizione getDescrizione() {
    return iDescrizione;
  }

  /**
   * setEqual
   * @param obj
   * @throws CopyException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 05/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    YVariabiliApplicazionePO yVariabiliApplicazionePO = (YVariabiliApplicazionePO)obj;
    iDescrizione.setEqual(yVariabiliApplicazionePO.iDescrizione);
  }

  /**
   * checkAll
   * @param components
   * @return Vector
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 05/12/2025    Wizard     Codice generato da Wizard
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
   * 05/12/2025    Wizard     Codice generato da Wizard
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
   * 05/12/2025    Wizard     Codice generato da Wizard
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
   * 05/12/2025    Wizard     Codice generato da Wizard
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
   * 05/12/2025    Wizard     Codice generato da Wizard
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
   * 05/12/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected TableManager getTableManager() throws SQLException {
    return YVariabiliApplicazioneTM.getInstance();
  }

}

