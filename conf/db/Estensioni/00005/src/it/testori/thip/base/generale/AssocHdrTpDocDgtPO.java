/*
 * @(#)AssocHdrTpDocDgtPO.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 04/12/2025 at 11:15:07
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 04/12/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.base.generale;
import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.base.azienda.AziendaEstesa;
import it.thera.thip.base.documentoDgt.TipoDocumentoDigitale;
import com.thera.thermfw.util.PersistentClassHdr;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class AssocHdrTpDocDgtPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   *  instance
   */
  private static AssocHdrTpDocDgt cInstance;

  /**
   * Attributo iDefCompilation
   */
  protected boolean iDefCompilation = true;

  /**
   * Attributo iTipodocdgt
   */
  protected Proxy iTipodocdgt = new Proxy(it.thera.thip.base.documentoDgt.TipoDocumentoDigitale.class);

  /**
   * Attributo iClasse
   */
  protected Proxy iClasse = new Proxy(com.thera.thermfw.util.PersistentClassHdr.class);

  
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
      cInstance = (AssocHdrTpDocDgt)Factory.createObject(AssocHdrTpDocDgt.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return AssocHdrTpDocDgt
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static AssocHdrTpDocDgt elementWithKey(String key, int lockType) throws SQLException {
    return (AssocHdrTpDocDgt)PersistentObject.elementWithKey(AssocHdrTpDocDgt.class, key, lockType);
  }

  /**
   * AssocHdrTpDocDgtPO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public AssocHdrTpDocDgtPO() {
    setDefCompilation(true);
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param defCompilation
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setDefCompilation(boolean defCompilation) {
    this.iDefCompilation = defCompilation;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public boolean getDefCompilation() {
    return iDefCompilation;
  }

  /**
   * Valorizza l'attributo. 
   * @param tipodocdgt
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setTipodocdgt(TipoDocumentoDigitale tipodocdgt) {
    String idAzienda = getIdAzienda();
    if (tipodocdgt != null) {
      idAzienda = KeyHelper.getTokenObjectKey(tipodocdgt.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iTipodocdgt.setObject(tipodocdgt);
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return TipoDocumentoDigitale
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public TipoDocumentoDigitale getTipodocdgt() {
    return (TipoDocumentoDigitale)iTipodocdgt.getObject();
  }

  /**
   * setTipodocdgtKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setTipodocdgtKey(String key) {
    iTipodocdgt.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    setOnDB(false);
  }

  /**
   * getTipodocdgtKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getTipodocdgtKey() {
    return iTipodocdgt.getKey();
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
   * @param idTipoDocumentoDigitale
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdTipoDocumentoDigitale(String idTipoDocumentoDigitale) {
    String key = iTipodocdgt.getKey();
    iTipodocdgt.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idTipoDocumentoDigitale));
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
  public String getIdTipoDocumentoDigitale() {
    String key = iTipodocdgt.getKey();
    String objIdTipoDocumentoDigitale = KeyHelper.getTokenObjectKey(key,2);
    return objIdTipoDocumentoDigitale;
  }

  /**
   * Valorizza l'attributo. 
   * @param classe
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setClasse(PersistentClassHdr classe) {
    this.iClasse.setObject(classe);
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return PersistentClassHdr
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public PersistentClassHdr getClasse() {
    return (PersistentClassHdr)iClasse.getObject();
  }

  /**
   * setClasseKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setClasseKey(String key) {
    iClasse.setKey(key);
    setDirty();
    setOnDB(false);
  }

  /**
   * getClasseKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getClasseKey() {
    return iClasse.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idClassHdr
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdClassHdr(String idClassHdr) {
    iClasse.setKey(idClassHdr);
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
  public String getIdClassHdr() {
    String key = iClasse.getKey();
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
   * 04/12/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    AssocHdrTpDocDgtPO assocHdrTpDocDgtPO = (AssocHdrTpDocDgtPO)obj;
    iTipodocdgt.setEqual(assocHdrTpDocDgtPO.iTipodocdgt);
    iClasse.setEqual(assocHdrTpDocDgtPO.iClasse);
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
    setIdClassHdr(KeyHelper.getTokenObjectKey(key, 2));
    setIdTipoDocumentoDigitale(KeyHelper.getTokenObjectKey(key, 3));
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
    String idClassHdr = getIdClassHdr();
    String idTipoDocumentoDigitale = getIdTipoDocumentoDigitale();
    Object[] keyParts = {idAzienda, idClassHdr, idTipoDocumentoDigitale};
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
    return AssocHdrTpDocDgtTM.getInstance();
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
        String key2 = iTipodocdgt.getKey();
    iTipodocdgt.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
  }

}

