/*
 * @(#)YPsnDatiRifornimentoLineaPO.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 04/09/2025 at 16:16:51
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 04/09/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.produzione.rifornLinea;
import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.base.azienda.AziendaEstesa;
import it.thera.thip.base.cliente.ClienteVendita;
import it.thera.thip.base.generale.Numeratore;
import it.thera.thip.base.generale.Serie;
import it.thera.thip.vendite.generaleVE.CausaleDocumentoVendita;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class YPsnDatiRifornimentoLineaPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   *  instance
   */
  private static YPsnDatiRifornimentoLinea cInstance;

  /**
   * Attributo iClienteintestatario
   */
  protected Proxy iClienteintestatario = new Proxy(it.thera.thip.base.cliente.ClienteVendita.class);

  /**
   * Attributo iNumeratoredocven
   */
  protected Proxy iNumeratoredocven = new Proxy(it.thera.thip.base.generale.Numeratore.class);

  /**
   * Attributo iSeriedocven
   */
  protected Proxy iSeriedocven = new Proxy(it.thera.thip.base.generale.Serie.class);

  /**
   * Attributo iCausaledocven
   */
  protected Proxy iCausaledocven = new Proxy(it.thera.thip.vendite.generaleVE.CausaleDocumentoVendita.class);

  
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
   * 04/09/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (YPsnDatiRifornimentoLinea)Factory.createObject(YPsnDatiRifornimentoLinea.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return YPsnDatiRifornimentoLinea
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static YPsnDatiRifornimentoLinea elementWithKey(String key, int lockType) throws SQLException {
    return (YPsnDatiRifornimentoLinea)PersistentObject.elementWithKey(YPsnDatiRifornimentoLinea.class, key, lockType);
  }

  /**
   * YPsnDatiRifornimentoLineaPO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public YPsnDatiRifornimentoLineaPO() {
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param clienteintestatario
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setClienteintestatario(ClienteVendita clienteintestatario) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (clienteintestatario != null) {
      idAzienda = KeyHelper.getTokenObjectKey(clienteintestatario.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iClienteintestatario.setObject(clienteintestatario);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * Restituisce l'attributo. 
   * @return ClienteVendita
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public ClienteVendita getClienteintestatario() {
    return (ClienteVendita)iClienteintestatario.getObject();
  }

  /**
   * setClienteintestatarioKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setClienteintestatarioKey(String key) {
    String oldObjectKey = getKey();
    iClienteintestatario.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * getClienteintestatarioKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getClienteintestatarioKey() {
    return iClienteintestatario.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idClienteIntestatario
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdClienteIntestatario(String idClienteIntestatario) {
    String key = iClienteintestatario.getKey();
    iClienteintestatario.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idClienteIntestatario));
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdClienteIntestatario() {
    String key = iClienteintestatario.getKey();
    String objIdClienteIntestatario = KeyHelper.getTokenObjectKey(key,2);
    return objIdClienteIntestatario;
  }

  /**
   * Valorizza l'attributo. 
   * @param numeratoredocven
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setNumeratoredocven(Numeratore numeratoredocven) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (numeratoredocven != null) {
      idAzienda = KeyHelper.getTokenObjectKey(numeratoredocven.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    String idNumeratoreDocVen = getIdNumeratoreDocVen();
    if (numeratoredocven != null) {
      idNumeratoreDocVen = KeyHelper.getTokenObjectKey(numeratoredocven.getKey(), 2);
    }
    setIdNumeratoreDocVenInternal(idNumeratoreDocVen);
    this.iNumeratoredocven.setObject(numeratoredocven);
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
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public Numeratore getNumeratoredocven() {
    return (Numeratore)iNumeratoredocven.getObject();
  }

  /**
   * setNumeratoredocvenKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setNumeratoredocvenKey(String key) {
    String oldObjectKey = getKey();
    iNumeratoredocven.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    String idNumeratoreDocVen = KeyHelper.getTokenObjectKey(key, 2);
    setIdNumeratoreDocVenInternal(idNumeratoreDocVen);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * getNumeratoredocvenKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getNumeratoredocvenKey() {
    return iNumeratoredocven.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param seriedocven
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setSeriedocven(Serie seriedocven) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (seriedocven != null) {
      idAzienda = KeyHelper.getTokenObjectKey(seriedocven.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    String idNumeratoreDocVen = getIdNumeratoreDocVen();
    if (seriedocven != null) {
      idNumeratoreDocVen = KeyHelper.getTokenObjectKey(seriedocven.getKey(), 2);
    }
    setIdNumeratoreDocVenInternal(idNumeratoreDocVen);
    this.iSeriedocven.setObject(seriedocven);
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
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public Serie getSeriedocven() {
    return (Serie)iSeriedocven.getObject();
  }

  /**
   * setSeriedocvenKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setSeriedocvenKey(String key) {
    String oldObjectKey = getKey();
    iSeriedocven.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    String idNumeratoreDocVen = KeyHelper.getTokenObjectKey(key, 2);
    setIdNumeratoreDocVenInternal(idNumeratoreDocVen);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * getSeriedocvenKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getSeriedocvenKey() {
    return iSeriedocven.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idNumeratoreDocVen
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdNumeratoreDocVen(String idNumeratoreDocVen) {
    setIdNumeratoreDocVenInternal(idNumeratoreDocVen);
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdNumeratoreDocVen() {
    String key = iNumeratoredocven.getKey();
    String objIdNumeratoreDocVen = KeyHelper.getTokenObjectKey(key,2);
    return objIdNumeratoreDocVen;
  }

  /**
   * Valorizza l'attributo. 
   * @param idSerieDocVen
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdSerieDocVen(String idSerieDocVen) {
    String key = iSeriedocven.getKey();
    iSeriedocven.setKey(KeyHelper.replaceTokenObjectKey(key , 3, idSerieDocVen));
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdSerieDocVen() {
    String key = iSeriedocven.getKey();
    String objIdSerieDocVen = KeyHelper.getTokenObjectKey(key,3);
    return objIdSerieDocVen;
  }

  /**
   * Valorizza l'attributo. 
   * @param causaledocven
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setCausaledocven(CausaleDocumentoVendita causaledocven) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (causaledocven != null) {
      idAzienda = KeyHelper.getTokenObjectKey(causaledocven.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iCausaledocven.setObject(causaledocven);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * Restituisce l'attributo. 
   * @return CausaleDocumentoVendita
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public CausaleDocumentoVendita getCausaledocven() {
    return (CausaleDocumentoVendita)iCausaledocven.getObject();
  }

  /**
   * setCausaledocvenKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setCausaledocvenKey(String key) {
    String oldObjectKey = getKey();
    iCausaledocven.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * getCausaledocvenKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getCausaledocvenKey() {
    return iCausaledocven.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
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
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdAzienda() {
    String key = iAzienda.getKey();
    return key;
  }

  /**
   * Valorizza l'attributo. 
   * @param idCausaleDocVen
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdCausaleDocVen(String idCausaleDocVen) {
    String key = iCausaledocven.getKey();
    iCausaledocven.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idCausaleDocVen));
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdCausaleDocVen() {
    String key = iCausaledocven.getKey();
    String objIdCausaleDocVen = KeyHelper.getTokenObjectKey(key,2);
    return objIdCausaleDocVen;
  }

  /**
   * setEqual
   * @param obj
   * @throws CopyException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    YPsnDatiRifornimentoLineaPO yPsnDatiRifornimentoLineaPO = (YPsnDatiRifornimentoLineaPO)obj;
    iClienteintestatario.setEqual(yPsnDatiRifornimentoLineaPO.iClienteintestatario);
    iNumeratoredocven.setEqual(yPsnDatiRifornimentoLineaPO.iNumeratoredocven);
    iSeriedocven.setEqual(yPsnDatiRifornimentoLineaPO.iSeriedocven);
    iCausaledocven.setEqual(yPsnDatiRifornimentoLineaPO.iCausaledocven);
  }

  /**
   * checkAll
   * @param components
   * @return Vector
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
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
   * 04/09/2025    Wizard     Codice generato da Wizard
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
   * 04/09/2025    Wizard     Codice generato da Wizard
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
   * 04/09/2025    Wizard     Codice generato da Wizard
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
   * 04/09/2025    Wizard     Codice generato da Wizard
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
   * 04/09/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected TableManager getTableManager() throws SQLException {
    return YPsnDatiRifornimentoLineaTM.getInstance();
  }

  /**
   * setIdAziendaInternal
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  protected void setIdAziendaInternal(String idAzienda) {
    iAzienda.setKey(idAzienda);
        String key2 = iClienteintestatario.getKey();
    iClienteintestatario.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
    String key3 = iNumeratoredocven.getKey();
    iNumeratoredocven.setKey(KeyHelper.replaceTokenObjectKey(key3, 1, idAzienda));
    String key4 = iSeriedocven.getKey();
    iSeriedocven.setKey(KeyHelper.replaceTokenObjectKey(key4, 1, idAzienda));
    String key5 = iCausaledocven.getKey();
    iCausaledocven.setKey(KeyHelper.replaceTokenObjectKey(key5, 1, idAzienda));
  }

  /**
   * setIdNumeratoreDocVenInternal
   * @param idNumeratoreDocVen
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/09/2025    Wizard     Codice generato da Wizard
   *
   */
  protected void setIdNumeratoreDocVenInternal(String idNumeratoreDocVen) {
    String key1 = iNumeratoredocven.getKey();
    iNumeratoredocven.setKey(KeyHelper.replaceTokenObjectKey(key1, 2, idNumeratoreDocVen));
    String key2 = iSeriedocven.getKey();
    iSeriedocven.setKey(KeyHelper.replaceTokenObjectKey(key2, 2, idNumeratoreDocVen));
  }

}

