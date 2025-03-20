/*
 * @(#)YTempiRisorseAtvMinutiPO.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 19/03/2025 at 12:21:02
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
import java.math.*;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class YTempiRisorseAtvMinutiPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Child, Conflictable {

  
  /**
   *  instance
   */
  private static YTempiRisorseAtvMinuti cInstance;

  /**
   * Attributo iFinoA
   */
  protected Integer iFinoA;

  /**
   * Attributo iTempo
   */
  protected BigDecimal iTempo;

  /**
   * Attributo iParent
   */
  protected Proxy iParent = new Proxy(it.testori.thip.datiTecnici.modpro.YTempiRisorseAttivita.class);

  
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
      cInstance = (YTempiRisorseAtvMinuti)Factory.createObject(YTempiRisorseAtvMinuti.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return YTempiRisorseAtvMinuti
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static YTempiRisorseAtvMinuti elementWithKey(String key, int lockType) throws SQLException {
    return (YTempiRisorseAtvMinuti)PersistentObject.elementWithKey(YTempiRisorseAtvMinuti.class, key, lockType);
  }

  /**
   * YTempiRisorseAtvMinutiPO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public YTempiRisorseAtvMinutiPO() {
    setTipoRisorsa('M');
    setLivelloRisorsa('-');
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param finoA
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setFinoA(Integer finoA) {
    this.iFinoA = finoA;
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return Integer
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public Integer getFinoA() {
    return iFinoA;
  }

  /**
   * Valorizza l'attributo. 
   * @param tempo
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setTempo(BigDecimal tempo) {
    this.iTempo = tempo;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getTempo() {
    return iTempo;
  }

  /**
   * Valorizza l'attributo. 
   * @param parent
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setParent(YTempiRisorseAttivita parent) {
    String idAzienda = getIdAzienda();
    if (parent != null) {
      idAzienda = KeyHelper.getTokenObjectKey(parent.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iParent.setObject(parent);
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return YTempiRisorseAttivita
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public YTempiRisorseAttivita getParent() {
    return (YTempiRisorseAttivita)iParent.getObject();
  }

  /**
   * setParentKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setParentKey(String key) {
    iParent.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    setOnDB(false);
  }

  /**
   * getParentKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getParentKey() {
    return iParent.getKey();
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
   * @param idAttivita
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdAttivita(String idAttivita) {
    String key = iParent.getKey();
    iParent.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idAttivita));
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
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdAttivita() {
    String key = iParent.getKey();
    String objIdAttivita = KeyHelper.getTokenObjectKey(key,2);
    return objIdAttivita;
    
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
    String key = iParent.getKey();
    Character tipoRisorsaTmp = new Character(tipoRisorsa);
    iParent.setKey(KeyHelper.replaceTokenObjectKey(key , 3, tipoRisorsaTmp));
    setDirty();
    setOnDB(false);
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
    String key = iParent.getKey();
    String objTipoRisorsa = KeyHelper.getTokenObjectKey(key,3);
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
    String key = iParent.getKey();
    Character livelloRisorsaTmp = new Character(livelloRisorsa);
    iParent.setKey(KeyHelper.replaceTokenObjectKey(key , 4, livelloRisorsaTmp));
    setDirty();
    setOnDB(false);
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
    String key = iParent.getKey();
    String objLivelloRisorsa = KeyHelper.getTokenObjectKey(key,4);
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
    String key = iParent.getKey();
    iParent.setKey(KeyHelper.replaceTokenObjectKey(key , 5, idRisorsa));
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
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdRisorsa() {
    String key = iParent.getKey();
    String objIdRisorsa = KeyHelper.getTokenObjectKey(key,5);
    return objIdRisorsa;
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
    YTempiRisorseAtvMinutiPO yTempiRisorseAtvMinutiPO = (YTempiRisorseAtvMinutiPO)obj;
    iParent.setEqual(yTempiRisorseAtvMinutiPO.iParent);
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
    setFinoA(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 6)));
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
    Integer finoA = getFinoA();
    Object[] keyParts = {idAzienda, idAttivita, tipoRisorsa, livelloRisorsa, idRisorsa, finoA};
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
   * getFatherKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getFatherKey() {
    return getParentKey();
  }

  /**
   * setFatherKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setFatherKey(String key) {
    setParentKey(key);
  }

  /**
   * setFather
   * @param father
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setFather(PersistentObject father) {
    iParent.setObject(father);
  }

  /**
   * getOrderByClause
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getOrderByClause() {
    return "";
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
    return YTempiRisorseAtvMinutiTM.getInstance();
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
        String key2 = iParent.getKey();
    iParent.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
  }

}

