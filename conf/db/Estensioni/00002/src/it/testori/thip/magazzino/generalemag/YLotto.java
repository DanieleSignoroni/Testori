/*
 * @(#)YLotto.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 24/06/2025 at 15:05:16
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 24/06/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.magazzino.generalemag;
import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import java.math.*;
import it.thera.thip.magazzino.generalemag.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;

public class YLotto extends Lotto {

  
  /**
   * Attributo iNumeroRocche
   */
  protected String iNumeroRocche;

  /**
   * Attributo iQuantitaOriginale
   */
  protected BigDecimal iQuantitaOriginale;

  /**
   * Attributo iTelaio
   */
  protected String iTelaio;

  /**
   * Attributo iAltezzaMinima
   */
  protected BigDecimal iAltezzaMinima;

  /**
   * Attributo iAltezzaMassima
   */
  protected BigDecimal iAltezzaMassima;

  /**
   * Attributo iPesoKg
   */
  protected BigDecimal iPesoKg;

  /**
   * Attributo iPesoCalcolatoPezzo
   */
  protected BigDecimal iPesoCalcolatoPezzo;

  /**
   * Attributo iPesoCalcolatoArrotondato
   */
  protected BigDecimal iPesoCalcolatoArrotondato;

  /**
   * Attributo iStEtichetta
   */
  protected boolean iStEtichetta = true;

  /**
   * Attributo iDifettosita
   */
  protected Proxy iDifettosita = new Proxy(it.testori.thip.magazzino.generalemag.YDifettosita.class);

  /**
   * Attributo iDifettositamisure
   */
  protected Proxy iDifettositamisure = new Proxy(it.testori.thip.magazzino.generalemag.YDifettositaMisure.class);

  
  /**
   * YLotto
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public YLotto() {
    setStEtichetta(true);
    setCodiceAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param numeroRocche
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setNumeroRocche(String numeroRocche) {
    this.iNumeroRocche = numeroRocche;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getNumeroRocche() {
    return iNumeroRocche;
  }

  /**
   * Valorizza l'attributo. 
   * @param quantitaOriginale
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setQuantitaOriginale(BigDecimal quantitaOriginale) {
    this.iQuantitaOriginale = quantitaOriginale;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getQuantitaOriginale() {
    return iQuantitaOriginale;
  }

  /**
   * Valorizza l'attributo. 
   * @param telaio
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setTelaio(String telaio) {
    this.iTelaio = telaio;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getTelaio() {
    return iTelaio;
  }

  /**
   * Valorizza l'attributo. 
   * @param altezzaMinima
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setAltezzaMinima(BigDecimal altezzaMinima) {
    this.iAltezzaMinima = altezzaMinima;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getAltezzaMinima() {
    return iAltezzaMinima;
  }

  /**
   * Valorizza l'attributo. 
   * @param altezzaMassima
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setAltezzaMassima(BigDecimal altezzaMassima) {
    this.iAltezzaMassima = altezzaMassima;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getAltezzaMassima() {
    return iAltezzaMassima;
  }

  /**
   * Valorizza l'attributo. 
   * @param pesoKg
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setPesoKg(BigDecimal pesoKg) {
    this.iPesoKg = pesoKg;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getPesoKg() {
    return iPesoKg;
  }

  /**
   * Valorizza l'attributo. 
   * @param pesoCalcolatoPezzo
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setPesoCalcolatoPezzo(BigDecimal pesoCalcolatoPezzo) {
    this.iPesoCalcolatoPezzo = pesoCalcolatoPezzo;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getPesoCalcolatoPezzo() {
    return iPesoCalcolatoPezzo;
  }

  /**
   * Valorizza l'attributo. 
   * @param pesoCalcolatoArrotondato
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setPesoCalcolatoArrotondato(BigDecimal pesoCalcolatoArrotondato) {
    this.iPesoCalcolatoArrotondato = pesoCalcolatoArrotondato;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getPesoCalcolatoArrotondato() {
    return iPesoCalcolatoArrotondato;
  }

  /**
   * Valorizza l'attributo. 
   * @param stEtichetta
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setStEtichetta(boolean stEtichetta) {
    this.iStEtichetta = stEtichetta;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public boolean getStEtichetta() {
    return iStEtichetta;
  }

  /**
   * Valorizza l'attributo. 
   * @param difettosita
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setDifettosita(YDifettosita difettosita) {
    String oldObjectKey = getKey();
    String codiceAzienda = getCodiceAzienda();
    if (difettosita != null) {
      codiceAzienda = KeyHelper.getTokenObjectKey(difettosita.getKey(), 1);
    }
    setCodiceAziendaInternal(codiceAzienda);
    this.iDifettosita.setObject(difettosita);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * Restituisce l'attributo. 
   * @return YDifettosita
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public YDifettosita getDifettosita() {
    return (YDifettosita)iDifettosita.getObject();
  }

  /**
   * setDifettositaKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setDifettositaKey(String key) {
    String oldObjectKey = getKey();
    iDifettosita.setKey(key);
    String codiceAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setCodiceAziendaInternal(codiceAzienda);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * getDifettositaKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getDifettositaKey() {
    return iDifettosita.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idDifettosita
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdDifettosita(String idDifettosita) {
    String key = iDifettosita.getKey();
    iDifettosita.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idDifettosita));
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdDifettosita() {
    String key = iDifettosita.getKey();
    String objIdDifettosita = KeyHelper.getTokenObjectKey(key,2);
    return objIdDifettosita;
  }

  /**
   * Valorizza l'attributo. 
   * @param difettositamisure
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setDifettositamisure(YDifettositaMisure difettositamisure) {
    String oldObjectKey = getKey();
    String codiceAzienda = getCodiceAzienda();
    if (difettositamisure != null) {
      codiceAzienda = KeyHelper.getTokenObjectKey(difettositamisure.getKey(), 1);
    }
    setCodiceAziendaInternal(codiceAzienda);
    this.iDifettositamisure.setObject(difettositamisure);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * Restituisce l'attributo. 
   * @return YDifettositaMisure
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public YDifettositaMisure getDifettositamisure() {
    return (YDifettositaMisure)iDifettositamisure.getObject();
  }

  /**
   * setDifettositamisureKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setDifettositamisureKey(String key) {
    String oldObjectKey = getKey();
    iDifettositamisure.setKey(key);
    String codiceAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setCodiceAziendaInternal(codiceAzienda);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * getDifettositamisureKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getDifettositamisureKey() {
    return iDifettositamisure.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param codiceAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setCodiceAzienda(String codiceAzienda) {
    super.setCodiceAzienda(codiceAzienda);
    
  }

  /**
   * Valorizza l'attributo. 
   * @param idDifettositaMisure
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdDifettositaMisure(String idDifettositaMisure) {
    String key = iDifettositamisure.getKey();
    iDifettositamisure.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idDifettositaMisure));
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdDifettositaMisure() {
    String key = iDifettositamisure.getKey();
    String objIdDifettositaMisure = KeyHelper.getTokenObjectKey(key,2);
    return objIdDifettositaMisure;
  }

  /**
   * setEqual
   * @param obj
   * @throws CopyException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    YLotto yLotto = (YLotto)obj;
    iDifettosita.setEqual(yLotto.iDifettosita);
    iDifettositamisure.setEqual(yLotto.iDifettositamisure);
  }

  /**
   * setCodiceAziendaInternal
   * @param codiceAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 24/06/2025    Wizard     Codice generato da Wizard
   *
   */
  protected void setCodiceAziendaInternal(String codiceAzienda) {
    super.setCodiceAziendaInternal(codiceAzienda);
    if(iDifettosita != null) {
      String key1 = iDifettosita.getKey();
      iDifettosita.setKey(KeyHelper.replaceTokenObjectKey(key1, 1, codiceAzienda));
    }
    if(iDifettositamisure != null) {
      String key2 = iDifettositamisure.getKey();
      iDifettositamisure.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, codiceAzienda));
    }
  }

}

