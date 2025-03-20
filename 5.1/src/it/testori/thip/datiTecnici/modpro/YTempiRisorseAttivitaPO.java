/*
 * @(#)YTempiRisorseAttivitaPO.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 19/03/2025 at 12:21:01
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 19/03/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.datiTecnici.modpro;
import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.base.azienda.AziendaEstesa;
import it.thera.thip.datiTecnici.modpro.Attivita;
import it.thera.thip.base.risorse.Risorsa;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class YTempiRisorseAttivitaPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   *  instance
   */
  private static YTempiRisorseAttivita cInstance;

  /**
   * Attributo iAttivita
   */
  protected Proxy iAttivita = new Proxy(it.thera.thip.datiTecnici.modpro.Attivita.class);

  /**
   * Attributo iRisorsa
   */
  protected Proxy iRisorsa = new Proxy(it.thera.thip.base.risorse.Risorsa.class);

  /**
   * Attributo iYTempiRisorseAtvMinuti
   */
  protected OneToMany iYTempiRisorseAtvMinuti = new OneToMany(it.testori.thip.datiTecnici.modpro.YTempiRisorseAtvMinuti.class, this, 31, false);

  
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
   * 19/03/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (YTempiRisorseAttivita)Factory.createObject(YTempiRisorseAttivita.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return YTempiRisorseAttivita
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static YTempiRisorseAttivita elementWithKey(String key, int lockType) throws SQLException {
    return (YTempiRisorseAttivita)PersistentObject.elementWithKey(YTempiRisorseAttivita.class, key, lockType);
  }

  /**
   * YTempiRisorseAttivitaPO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public YTempiRisorseAttivitaPO() {
    setTipoRisorsa('M');
    setLivelloRisorsa('-');
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param attivita
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setAttivita(Attivita attivita) {
    String idAzienda = getIdAzienda();
    if (attivita != null) {
      idAzienda = KeyHelper.getTokenObjectKey(attivita.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iAttivita.setObject(attivita);
    setDirty();
    setOnDB(false);
    iYTempiRisorseAtvMinuti.setFatherKeyChanged();
  }

  /**
   * Restituisce l'attributo. 
   * @return Attivita
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public Attivita getAttivita() {
    return (Attivita)iAttivita.getObject();
  }

  /**
   * setAttivitaKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setAttivitaKey(String key) {
    iAttivita.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    setOnDB(false);
    iYTempiRisorseAtvMinuti.setFatherKeyChanged();
  }

  /**
   * getAttivitaKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getAttivitaKey() {
    return iAttivita.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idAttivita
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdAttivita(String idAttivita) {
    String key = iAttivita.getKey();
    iAttivita.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idAttivita));
    setDirty();
    setOnDB(false);
    iYTempiRisorseAtvMinuti.setFatherKeyChanged();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdAttivita() {
    String key = iAttivita.getKey();
    String objIdAttivita = KeyHelper.getTokenObjectKey(key,2);
    return objIdAttivita;
  }

  /**
   * Valorizza l'attributo. 
   * @param risorsa
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setRisorsa(Risorsa risorsa) {
    String idAzienda = getIdAzienda();
    if (risorsa != null) {
      idAzienda = KeyHelper.getTokenObjectKey(risorsa.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iRisorsa.setObject(risorsa);
    setDirty();
    setOnDB(false);
    iYTempiRisorseAtvMinuti.setFatherKeyChanged();
  }

  /**
   * Restituisce l'attributo. 
   * @return Risorsa
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public Risorsa getRisorsa() {
    return (Risorsa)iRisorsa.getObject();
  }

  /**
   * setRisorsaKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setRisorsaKey(String key) {
    iRisorsa.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    setOnDB(false);
    iYTempiRisorseAtvMinuti.setFatherKeyChanged();
  }

  /**
   * getRisorsaKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getRisorsaKey() {
    return iRisorsa.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdAzienda(String idAzienda) {
    setIdAziendaInternal(idAzienda);
    setDirty();
    setOnDB(false);
    iYTempiRisorseAtvMinuti.setFatherKeyChanged();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdAzienda() {
    String key = iAzienda.getKey();
    return key;
  }

  /**
   * Valorizza l'attributo. 
   * @param tipoRisorsa
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setTipoRisorsa(char tipoRisorsa) {
    String key = iRisorsa.getKey();
    Character tipoRisorsaTmp = new Character(tipoRisorsa);
    iRisorsa.setKey(KeyHelper.replaceTokenObjectKey(key , 2, tipoRisorsaTmp));
    setDirty();
    setOnDB(false);
    iYTempiRisorseAtvMinuti.setFatherKeyChanged();
  }

  /**
   * Restituisce l'attributo. 
   * @return char
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public char getTipoRisorsa() {
    String key = iRisorsa.getKey();
    String objTipoRisorsa = KeyHelper.getTokenObjectKey(key,2);
    return KeyHelper.stringToChar(objTipoRisorsa);
    
  }

  /**
   * Valorizza l'attributo. 
   * @param livelloRisorsa
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setLivelloRisorsa(char livelloRisorsa) {
    String key = iRisorsa.getKey();
    Character livelloRisorsaTmp = new Character(livelloRisorsa);
    iRisorsa.setKey(KeyHelper.replaceTokenObjectKey(key , 3, livelloRisorsaTmp));
    setDirty();
    setOnDB(false);
    iYTempiRisorseAtvMinuti.setFatherKeyChanged();
  }

  /**
   * Restituisce l'attributo. 
   * @return char
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public char getLivelloRisorsa() {
    String key = iRisorsa.getKey();
    String objLivelloRisorsa = KeyHelper.getTokenObjectKey(key,3);
    return KeyHelper.stringToChar(objLivelloRisorsa);
    
  }

  /**
   * Valorizza l'attributo. 
   * @param idRisorsa
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdRisorsa(String idRisorsa) {
    String key = iRisorsa.getKey();
    iRisorsa.setKey(KeyHelper.replaceTokenObjectKey(key , 4, idRisorsa));
    setDirty();
    setOnDB(false);
    iYTempiRisorseAtvMinuti.setFatherKeyChanged();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdRisorsa() {
    String key = iRisorsa.getKey();
    String objIdRisorsa = KeyHelper.getTokenObjectKey(key,4);
    return objIdRisorsa;
  }

  /**
   * getYTempiRisorseAtvMinuti
   * @return List
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public List getYTempiRisorseAtvMinuti() {
    return getYTempiRisorseAtvMinutiInternal();
  }

  /**
   * setEqual
   * @param obj
   * @throws CopyException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    YTempiRisorseAttivitaPO yTempiRisorseAttivitaPO = (YTempiRisorseAttivitaPO)obj;
    iAttivita.setEqual(yTempiRisorseAttivitaPO.iAttivita);
    iRisorsa.setEqual(yTempiRisorseAttivitaPO.iRisorsa);
    iYTempiRisorseAtvMinuti.setEqual(yTempiRisorseAttivitaPO.iYTempiRisorseAtvMinuti);
  }

  /**
   * checkAll
   * @param components
   * @return Vector
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
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
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setKey(String key) {
    setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
    setIdAttivita(KeyHelper.getTokenObjectKey(key, 2));
    setTipoRisorsa(KeyHelper.stringToChar(KeyHelper.getTokenObjectKey(key, 3)));
    setLivelloRisorsa(KeyHelper.stringToChar(KeyHelper.getTokenObjectKey(key, 4)));
    setIdRisorsa(KeyHelper.getTokenObjectKey(key, 5));
  }

  /**
   *  getKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getKey() {
    String idAzienda = getIdAzienda();
    String idAttivita = getIdAttivita();
    Character tipoRisorsa = new Character(getTipoRisorsa());
    Character livelloRisorsa = new Character(getLivelloRisorsa());
    String idRisorsa = getIdRisorsa();
    Object[] keyParts = {idAzienda, idAttivita, tipoRisorsa, livelloRisorsa, idRisorsa};
    return KeyHelper.buildObjectKey(keyParts);
  }

  /**
   * isDeletable
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public boolean isDeletable() {
    return checkDelete() == null;
  }

  /**
   * saveOwnedObjects
   * @param rc
   * @return int
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public int saveOwnedObjects(int rc) throws SQLException {
    rc = iYTempiRisorseAtvMinuti.save(rc);
    return rc;
  }

  /**
   * deleteOwnedObjects
   * @return int
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public int deleteOwnedObjects() throws SQLException {
    return getYTempiRisorseAtvMinutiInternal().delete();
  }

  /**
   * initializeOwnedObjects
   * @param result
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public boolean initializeOwnedObjects(boolean result) {
    result = iYTempiRisorseAtvMinuti.initialize(result);
    return result;
  }

  /**
   * toString
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
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
   * 19/03/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected TableManager getTableManager() throws SQLException {
    return YTempiRisorseAttivitaTM.getInstance();
  }

  /**
   * getYTempiRisorseAtvMinutiInternal
   * @return OneToMany
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  protected OneToMany getYTempiRisorseAtvMinutiInternal() {
    if (iYTempiRisorseAtvMinuti.isNew())
        iYTempiRisorseAtvMinuti.retrieve();
    return iYTempiRisorseAtvMinuti;
  }

  /**
   * setIdAziendaInternal
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  protected void setIdAziendaInternal(String idAzienda) {
    iAzienda.setKey(idAzienda);
        String key2 = iAttivita.getKey();
    iAttivita.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
    String key3 = iRisorsa.getKey();
    iRisorsa.setKey(KeyHelper.replaceTokenObjectKey(key3, 1, idAzienda));
  }

}

