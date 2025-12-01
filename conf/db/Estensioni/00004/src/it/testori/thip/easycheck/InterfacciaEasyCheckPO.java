/*
 * @(#)InterfacciaEasyCheckPO.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 01/12/2025 at 15:50:24
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 01/12/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.easycheck;
import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.base.azienda.AziendaEstesa;
import it.thera.thip.base.generale.Numeratore;
import it.thera.thip.base.generale.Serie;
import it.thera.thip.produzione.documento.CausaleDocProduzione;
import it.thera.thip.base.dipendente.Dipendente;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class InterfacciaEasyCheckPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   *  instance
   */
  private static InterfacciaEasyCheck cInstance;

  /**
   * Attributo iNumeratoredocprod
   */
  protected Proxy iNumeratoredocprod = new Proxy(it.thera.thip.base.generale.Numeratore.class);

  /**
   * Attributo iSeriedocprod
   */
  protected Proxy iSeriedocprod = new Proxy(it.thera.thip.base.generale.Serie.class);

  /**
   * Attributo iCausaledocprod
   */
  protected Proxy iCausaledocprod = new Proxy(it.thera.thip.produzione.documento.CausaleDocProduzione.class);

  /**
   * Attributo iDipendenterilevazione
   */
  protected Proxy iDipendenterilevazione = new Proxy(it.thera.thip.base.dipendente.Dipendente.class);

  
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
   * 01/12/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (InterfacciaEasyCheck)Factory.createObject(InterfacciaEasyCheck.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return InterfacciaEasyCheck
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static InterfacciaEasyCheck elementWithKey(String key, int lockType) throws SQLException {
    return (InterfacciaEasyCheck)PersistentObject.elementWithKey(InterfacciaEasyCheck.class, key, lockType);
  }

  /**
   * InterfacciaEasyCheckPO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public InterfacciaEasyCheckPO() {
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param numeratoredocprod
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setNumeratoredocprod(Numeratore numeratoredocprod) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (numeratoredocprod != null) {
      idAzienda = KeyHelper.getTokenObjectKey(numeratoredocprod.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    String idNumeratoreDocPrd = getIdNumeratoreDocPrd();
    if (numeratoredocprod != null) {
      idNumeratoreDocPrd = KeyHelper.getTokenObjectKey(numeratoredocprod.getKey(), 2);
    }
    setIdNumeratoreDocPrdInternal(idNumeratoreDocPrd);
    this.iNumeratoredocprod.setObject(numeratoredocprod);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * Restituisce l'attributo. 
   * @return Numeratore
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public Numeratore getNumeratoredocprod() {
    return (Numeratore)iNumeratoredocprod.getObject();
  }

  /**
   * setNumeratoredocprodKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setNumeratoredocprodKey(String key) {
    String oldObjectKey = getKey();
    iNumeratoredocprod.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    String idNumeratoreDocPrd = KeyHelper.getTokenObjectKey(key, 2);
    setIdNumeratoreDocPrdInternal(idNumeratoreDocPrd);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * getNumeratoredocprodKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getNumeratoredocprodKey() {
    return iNumeratoredocprod.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param seriedocprod
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setSeriedocprod(Serie seriedocprod) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (seriedocprod != null) {
      idAzienda = KeyHelper.getTokenObjectKey(seriedocprod.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    String idNumeratoreDocPrd = getIdNumeratoreDocPrd();
    if (seriedocprod != null) {
      idNumeratoreDocPrd = KeyHelper.getTokenObjectKey(seriedocprod.getKey(), 2);
    }
    setIdNumeratoreDocPrdInternal(idNumeratoreDocPrd);
    this.iSeriedocprod.setObject(seriedocprod);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * Restituisce l'attributo. 
   * @return Serie
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public Serie getSeriedocprod() {
    return (Serie)iSeriedocprod.getObject();
  }

  /**
   * setSeriedocprodKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setSeriedocprodKey(String key) {
    String oldObjectKey = getKey();
    iSeriedocprod.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    String idNumeratoreDocPrd = KeyHelper.getTokenObjectKey(key, 2);
    setIdNumeratoreDocPrdInternal(idNumeratoreDocPrd);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * getSeriedocprodKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getSeriedocprodKey() {
    return iSeriedocprod.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idNumeratoreDocPrd
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdNumeratoreDocPrd(String idNumeratoreDocPrd) {
    setIdNumeratoreDocPrdInternal(idNumeratoreDocPrd);
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdNumeratoreDocPrd() {
    String key = iNumeratoredocprod.getKey();
    String objIdNumeratoreDocPrd = KeyHelper.getTokenObjectKey(key,2);
    return objIdNumeratoreDocPrd;
  }

  /**
   * Valorizza l'attributo. 
   * @param idSerieDocPrd
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdSerieDocPrd(String idSerieDocPrd) {
    String key = iSeriedocprod.getKey();
    iSeriedocprod.setKey(KeyHelper.replaceTokenObjectKey(key , 3, idSerieDocPrd));
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdSerieDocPrd() {
    String key = iSeriedocprod.getKey();
    String objIdSerieDocPrd = KeyHelper.getTokenObjectKey(key,3);
    return objIdSerieDocPrd;
  }

  /**
   * Valorizza l'attributo. 
   * @param causaledocprod
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setCausaledocprod(CausaleDocProduzione causaledocprod) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (causaledocprod != null) {
      idAzienda = KeyHelper.getTokenObjectKey(causaledocprod.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iCausaledocprod.setObject(causaledocprod);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * Restituisce l'attributo. 
   * @return CausaleDocProduzione
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public CausaleDocProduzione getCausaledocprod() {
    return (CausaleDocProduzione)iCausaledocprod.getObject();
  }

  /**
   * setCausaledocprodKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setCausaledocprodKey(String key) {
    String oldObjectKey = getKey();
    iCausaledocprod.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * getCausaledocprodKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getCausaledocprodKey() {
    return iCausaledocprod.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idCausaleDocProd
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdCausaleDocProd(String idCausaleDocProd) {
    String key = iCausaledocprod.getKey();
    iCausaledocprod.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idCausaleDocProd));
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdCausaleDocProd() {
    String key = iCausaledocprod.getKey();
    String objIdCausaleDocProd = KeyHelper.getTokenObjectKey(key,2);
    return objIdCausaleDocProd;
  }

  /**
   * Valorizza l'attributo. 
   * @param dipendenterilevazione
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setDipendenterilevazione(Dipendente dipendenterilevazione) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (dipendenterilevazione != null) {
      idAzienda = KeyHelper.getTokenObjectKey(dipendenterilevazione.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iDipendenterilevazione.setObject(dipendenterilevazione);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * Restituisce l'attributo. 
   * @return Dipendente
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public Dipendente getDipendenterilevazione() {
    return (Dipendente)iDipendenterilevazione.getObject();
  }

  /**
   * setDipendenterilevazioneKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setDipendenterilevazioneKey(String key) {
    String oldObjectKey = getKey();
    iDipendenterilevazione.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * getDipendenterilevazioneKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getDipendenterilevazioneKey() {
    return iDipendenterilevazione.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdAzienda(String idAzienda) {
    setIdAziendaInternal(idAzienda);
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
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdAzienda() {
    String key = iAzienda.getKey();
    return key;
  }

  /**
   * Valorizza l'attributo. 
   * @param idDipendenteRilev
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdDipendenteRilev(String idDipendenteRilev) {
    String key = iDipendenterilevazione.getKey();
    iDipendenterilevazione.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idDipendenteRilev));
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdDipendenteRilev() {
    String key = iDipendenterilevazione.getKey();
    String objIdDipendenteRilev = KeyHelper.getTokenObjectKey(key,2);
    return objIdDipendenteRilev;
  }

  /**
   * setEqual
   * @param obj
   * @throws CopyException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    InterfacciaEasyCheckPO interfacciaEasyCheckPO = (InterfacciaEasyCheckPO)obj;
    iNumeratoredocprod.setEqual(interfacciaEasyCheckPO.iNumeratoredocprod);
    iSeriedocprod.setEqual(interfacciaEasyCheckPO.iSeriedocprod);
    iCausaledocprod.setEqual(interfacciaEasyCheckPO.iCausaledocprod);
    iDipendenterilevazione.setEqual(interfacciaEasyCheckPO.iDipendenterilevazione);
  }

  /**
   * checkAll
   * @param components
   * @return Vector
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
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
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setKey(String key) {
    setIdAzienda(key);
  }

  /**
   *  getKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getKey() {
    return getIdAzienda();
  }

  /**
   * isDeletable
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
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
   * 01/12/2025    Wizard     Codice generato da Wizard
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
   * 01/12/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected TableManager getTableManager() throws SQLException {
    return InterfacciaEasyCheckTM.getInstance();
  }

  /**
   * setIdAziendaInternal
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  protected void setIdAziendaInternal(String idAzienda) {
    iAzienda.setKey(idAzienda);
        String key2 = iNumeratoredocprod.getKey();
    iNumeratoredocprod.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
    String key3 = iSeriedocprod.getKey();
    iSeriedocprod.setKey(KeyHelper.replaceTokenObjectKey(key3, 1, idAzienda));
    String key4 = iCausaledocprod.getKey();
    iCausaledocprod.setKey(KeyHelper.replaceTokenObjectKey(key4, 1, idAzienda));
    String key5 = iDipendenterilevazione.getKey();
    iDipendenterilevazione.setKey(KeyHelper.replaceTokenObjectKey(key5, 1, idAzienda));
  }

  /**
   * setIdNumeratoreDocPrdInternal
   * @param idNumeratoreDocPrd
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/12/2025    Wizard     Codice generato da Wizard
   *
   */
  protected void setIdNumeratoreDocPrdInternal(String idNumeratoreDocPrd) {
    String key1 = iNumeratoredocprod.getKey();
    iNumeratoredocprod.setKey(KeyHelper.replaceTokenObjectKey(key1, 2, idNumeratoreDocPrd));
    String key2 = iSeriedocprod.getKey();
    iSeriedocprod.setKey(KeyHelper.replaceTokenObjectKey(key2, 2, idNumeratoreDocPrd));
  }

}

