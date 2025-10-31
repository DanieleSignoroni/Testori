/*
 * @(#)YDocumentoVenRigaPrm.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 31/10/2025 at 12:13:08
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 31/10/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.vendite.documentoVE;
import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.produzione.rifornLinea.PianoRifornimentoRiga;
import it.thera.thip.vendite.documentoVE.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;

public class YDocumentoVenRigaPrm extends DocumentoVenRigaPrm {

  
  /**
   * Attributo iPianorifornimentoriga
   */
  protected Proxy iPianorifornimentoriga = new Proxy(it.thera.thip.produzione.rifornLinea.PianoRifornimentoRiga.class);

  
  /**
   * YDocumentoVenRigaPrm
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 31/10/2025    Wizard     Codice generato da Wizard
   *
   */
  public YDocumentoVenRigaPrm() {
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param pianorifornimentoriga
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 31/10/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setPianorifornimentoriga(PianoRifornimentoRiga pianorifornimentoriga) {
    String oldObjectKey = getKey();
    this.iPianorifornimentoriga.setObject(pianorifornimentoriga);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * Restituisce l'attributo. 
   * @return PianoRifornimentoRiga
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 31/10/2025    Wizard     Codice generato da Wizard
   *
   */
  public PianoRifornimentoRiga getPianorifornimentoriga() {
    return (PianoRifornimentoRiga)iPianorifornimentoriga.getObject();
  }

  /**
   * setPianorifornimentorigaKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 31/10/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setPianorifornimentorigaKey(String key) {
    String oldObjectKey = getKey();
    iPianorifornimentoriga.setKey(key);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * getPianorifornimentorigaKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 31/10/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getPianorifornimentorigaKey() {
    return iPianorifornimentoriga.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 31/10/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdAzienda(String idAzienda) {
    super.setIdAzienda(idAzienda);
    if(iPianorifornimentoriga != null) {
      String key = iPianorifornimentoriga.getKey();
      iPianorifornimentoriga.setKey(KeyHelper.replaceTokenObjectKey(key , 1, idAzienda));
    }
    
  }

  /**
   * Valorizza l'attributo. 
   * @param annoPianoRfr
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 31/10/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setAnnoPianoRfr(String annoPianoRfr) {
    String key = iPianorifornimentoriga.getKey();
    iPianorifornimentoriga.setKey(KeyHelper.replaceTokenObjectKey(key , 2, annoPianoRfr));
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 31/10/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getAnnoPianoRfr() {
    String key = iPianorifornimentoriga.getKey();
    String objAnnoPianoRfr = KeyHelper.getTokenObjectKey(key,2);
    return objAnnoPianoRfr;
    
  }

  /**
   * Valorizza l'attributo. 
   * @param idNumeroPianoRfr
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 31/10/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdNumeroPianoRfr(String idNumeroPianoRfr) {
    String key = iPianorifornimentoriga.getKey();
    iPianorifornimentoriga.setKey(KeyHelper.replaceTokenObjectKey(key , 3, idNumeroPianoRfr));
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 31/10/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdNumeroPianoRfr() {
    String key = iPianorifornimentoriga.getKey();
    String objIdNumeroPianoRfr = KeyHelper.getTokenObjectKey(key,3);
    return objIdNumeroPianoRfr;
    
  }

  /**
   * Valorizza l'attributo. 
   * @param idRigaPianoRfr
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 31/10/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdRigaPianoRfr(Integer idRigaPianoRfr) {
    String key = iPianorifornimentoriga.getKey();
    iPianorifornimentoriga.setKey(KeyHelper.replaceTokenObjectKey(key , 4, idRigaPianoRfr));
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return Integer
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 31/10/2025    Wizard     Codice generato da Wizard
   *
   */
  public Integer getIdRigaPianoRfr() {
    String key = iPianorifornimentoriga.getKey();
    String objIdRigaPianoRfr = KeyHelper.getTokenObjectKey(key,4);
    return KeyHelper.stringToIntegerObj(objIdRigaPianoRfr);
  }

  /**
   * setEqual
   * @param obj
   * @throws CopyException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 31/10/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    YDocumentoVenRigaPrm yDocumentoVenRigaPrm = (YDocumentoVenRigaPrm)obj;
    iPianorifornimentoriga.setEqual(yDocumentoVenRigaPrm.iPianorifornimentoriga);
  }

}

