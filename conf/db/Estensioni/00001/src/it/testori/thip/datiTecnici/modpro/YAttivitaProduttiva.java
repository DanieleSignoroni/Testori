/*
 * @(#)YAttivitaProduttiva.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 19/03/2025 at 12:03:39
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
import it.thera.thip.datiTecnici.modpro.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;

public class YAttivitaProduttiva extends AttivitaProduttiva {

  
  /**
   * Attributo iAttivitaDefault
   */
  protected boolean iAttivitaDefault = false;

  
  /**
   * YAttivitaProduttiva
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public YAttivitaProduttiva() {
    setAttivitaDefault(false);
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param attivitaDefault
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setAttivitaDefault(boolean attivitaDefault) {
    this.iAttivitaDefault = attivitaDefault;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 19/03/2025    Wizard     Codice generato da Wizard
   *
   */
  public boolean getAttivitaDefault() {
    return iAttivitaDefault;
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
  }

}

