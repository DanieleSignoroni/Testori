/*
 * @(#)YAllegatiOrdVenRigPrmPO.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 04/12/2025 at 11:32:23
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 04/12/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.vendite.ordineVE;
import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.base.azienda.AziendaEstesa;
import it.thera.thip.vendite.ordineVE.OrdineVenditaRigaPrm;
import it.testori.thip.base.generale.AssocHdrTpDocDgt;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class YAllegatiOrdVenRigPrmPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   *  instance
   */
  private static YAllegatiOrdVenRigPrm cInstance;

  /**
   * Attributo iVincolo
   */
  protected char iVincolo = '0';

  /**
   * Attributo iDettaglioRigaOrdine
   */
  protected Integer iDettaglioRigaOrdine = new Integer("0");

  /**
   * Attributo iOrdinevenditarigaprm
   */
  protected Proxy iOrdinevenditarigaprm = new Proxy(it.thera.thip.vendite.ordineVE.OrdineVenditaRigaPrm.class);

  /**
   * Attributo iAssociazionedocumento
   */
  protected Proxy iAssociazionedocumento = new Proxy(it.testori.thip.base.generale.AssocHdrTpDocDgt.class);

  
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
   * 04/12/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (YAllegatiOrdVenRigPrm)Factory.createObject(YAllegatiOrdVenRigPrm.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return YAllegatiOrdVenRigPrm
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static YAllegatiOrdVenRigPrm elementWithKey(String key, int lockType) throws SQLException {
    return (YAllegatiOrdVenRigPrm)PersistentObject.elementWithKey(YAllegatiOrdVenRigPrm.class, key, lockType);
  }

  /**
   * YAllegatiOrdVenRigPrmPO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public YAllegatiOrdVenRigPrmPO() {
    setVincolo('0');
    setDettaglioRigaOrdine(new Integer(0));
    setIdClasse("OrdineVenditaRigaPrm");
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param vincolo
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setVincolo(char vincolo) {
    this.iVincolo = vincolo;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return char
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public char getVincolo() {
    return iVincolo;
  }

  /**
   * Valorizza l'attributo. 
   * @param dettaglioRigaOrdine
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setDettaglioRigaOrdine(Integer dettaglioRigaOrdine) {
    this.iDettaglioRigaOrdine = dettaglioRigaOrdine;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return Integer
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public Integer getDettaglioRigaOrdine() {
    return iDettaglioRigaOrdine;
  }

  /**
   * Valorizza l'attributo. 
   * @param ordinevenditarigaprm
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setOrdinevenditarigaprm(OrdineVenditaRigaPrm ordinevenditarigaprm) {
    String idAzienda = getIdAzienda();
    if (ordinevenditarigaprm != null) {
      idAzienda = KeyHelper.getTokenObjectKey(ordinevenditarigaprm.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iOrdinevenditarigaprm.setObject(ordinevenditarigaprm);
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return OrdineVenditaRigaPrm
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public OrdineVenditaRigaPrm getOrdinevenditarigaprm() {
    return (OrdineVenditaRigaPrm)iOrdinevenditarigaprm.getObject();
  }

  /**
   * setOrdinevenditarigaprmKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setOrdinevenditarigaprmKey(String key) {
    iOrdinevenditarigaprm.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    setOnDB(false);
  }

  /**
   * getOrdinevenditarigaprmKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getOrdinevenditarigaprmKey() {
    return iOrdinevenditarigaprm.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param annoDocumento
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setAnnoDocumento(String annoDocumento) {
    String key = iOrdinevenditarigaprm.getKey();
    iOrdinevenditarigaprm.setKey(KeyHelper.replaceTokenObjectKey(key , 2, annoDocumento));
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
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getAnnoDocumento() {
    String key = iOrdinevenditarigaprm.getKey();
    String objAnnoDocumento = KeyHelper.getTokenObjectKey(key,2);
    return objAnnoDocumento;
    
  }

  /**
   * Valorizza l'attributo. 
   * @param numeroDocumento
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setNumeroDocumento(String numeroDocumento) {
    String key = iOrdinevenditarigaprm.getKey();
    iOrdinevenditarigaprm.setKey(KeyHelper.replaceTokenObjectKey(key , 3, numeroDocumento));
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
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getNumeroDocumento() {
    String key = iOrdinevenditarigaprm.getKey();
    String objNumeroDocumento = KeyHelper.getTokenObjectKey(key,3);
    return objNumeroDocumento;
    
  }

  /**
   * Valorizza l'attributo. 
   * @param numeroRigaDocumento
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setNumeroRigaDocumento(Integer numeroRigaDocumento) {
    String key = iOrdinevenditarigaprm.getKey();
    iOrdinevenditarigaprm.setKey(KeyHelper.replaceTokenObjectKey(key , 4, numeroRigaDocumento));
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
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public Integer getNumeroRigaDocumento() {
    String key = iOrdinevenditarigaprm.getKey();
    String objNumeroRigaDocumento = KeyHelper.getTokenObjectKey(key,4);
    return KeyHelper.stringToIntegerObj(objNumeroRigaDocumento);
  }

  /**
   * Valorizza l'attributo. 
   * @param associazionedocumento
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setAssociazionedocumento(AssocHdrTpDocDgt associazionedocumento) {
    String idAzienda = getIdAzienda();
    if (associazionedocumento != null) {
      idAzienda = KeyHelper.getTokenObjectKey(associazionedocumento.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iAssociazionedocumento.setObject(associazionedocumento);
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return AssocHdrTpDocDgt
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public AssocHdrTpDocDgt getAssociazionedocumento() {
    return (AssocHdrTpDocDgt)iAssociazionedocumento.getObject();
  }

  /**
   * setAssociazionedocumentoKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setAssociazionedocumentoKey(String key) {
    iAssociazionedocumento.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    setOnDB(false);
  }

  /**
   * getAssociazionedocumentoKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getAssociazionedocumentoKey() {
    return iAssociazionedocumento.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
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
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdAzienda() {
    String key = iAzienda.getKey();
    return key;
  }

  /**
   * Valorizza l'attributo. 
   * @param idClasse
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdClasse(String idClasse) {
    String key = iAssociazionedocumento.getKey();
    iAssociazionedocumento.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idClasse));
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
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdClasse() {
    String key = iAssociazionedocumento.getKey();
    String objIdClasse = KeyHelper.getTokenObjectKey(key,2);
    return objIdClasse;
    
  }

  /**
   * Valorizza l'attributo. 
   * @param idTipoDocDgt
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdTipoDocDgt(String idTipoDocDgt) {
    String key = iAssociazionedocumento.getKey();
    iAssociazionedocumento.setKey(KeyHelper.replaceTokenObjectKey(key , 3, idTipoDocDgt));
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
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdTipoDocDgt() {
    String key = iAssociazionedocumento.getKey();
    String objIdTipoDocDgt = KeyHelper.getTokenObjectKey(key,3);
    return objIdTipoDocDgt;
  }

  /**
   * setEqual
   * @param obj
   * @throws CopyException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    YAllegatiOrdVenRigPrmPO yAllegatiOrdVenRigPrmPO = (YAllegatiOrdVenRigPrmPO)obj;
    iOrdinevenditarigaprm.setEqual(yAllegatiOrdVenRigPrmPO.iOrdinevenditarigaprm);
    iAssociazionedocumento.setEqual(yAllegatiOrdVenRigPrmPO.iAssociazionedocumento);
  }

  /**
   * checkAll
   * @param components
   * @return Vector
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
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
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setKey(String key) {
    setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
    setAnnoDocumento(KeyHelper.getTokenObjectKey(key, 2));
    setNumeroDocumento(KeyHelper.getTokenObjectKey(key, 3));
    setNumeroRigaDocumento(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 4)));
    setIdClasse(KeyHelper.getTokenObjectKey(key, 5));
    setIdTipoDocDgt(KeyHelper.getTokenObjectKey(key, 6));
  }

  /**
   *  getKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getKey() {
    String idAzienda = getIdAzienda();
    String annoDocumento = getAnnoDocumento();
    String numeroDocumento = getNumeroDocumento();
    Integer numeroRigaDocumento = getNumeroRigaDocumento();
    String idClasse = getIdClasse();
    String idTipoDocDgt = getIdTipoDocDgt();
    Object[] keyParts = {idAzienda, annoDocumento, numeroDocumento, numeroRigaDocumento, idClasse, idTipoDocDgt};
    return KeyHelper.buildObjectKey(keyParts);
  }

  /**
   * isDeletable
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
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
   * 04/12/2025    Wizard     Codice generato da Wizard
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
   * 04/12/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected TableManager getTableManager() throws SQLException {
    return YAllegatiOrdVenRigPrmTM.getInstance();
  }

  /**
   * setIdAziendaInternal
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  protected void setIdAziendaInternal(String idAzienda) {
    iAzienda.setKey(idAzienda);
        String key2 = iOrdinevenditarigaprm.getKey();
    iOrdinevenditarigaprm.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
    String key3 = iAssociazionedocumento.getKey();
    iAssociazionedocumento.setKey(KeyHelper.replaceTokenObjectKey(key3, 1, idAzienda));
  }

}

