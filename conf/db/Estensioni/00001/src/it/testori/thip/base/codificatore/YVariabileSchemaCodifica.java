/*
 * @(#)YVariabileSchemaCodifica.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 01/07/2025 at 09:18:01
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 01/07/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.base.codificatore;
import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.base.codificatore.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;

public class YVariabileSchemaCodifica extends VariabileSchemaCodifica {

  
  /**
   * Attributo iRicercaConRange
   */
  protected boolean iRicercaConRange = false;

  
  /**
   * YVariabileSchemaCodifica
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/07/2025    Wizard     Codice generato da Wizard
   *
   */
  public YVariabileSchemaCodifica() {
    setRicercaConRange(false);
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param ricercaConRange
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/07/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setRicercaConRange(boolean ricercaConRange) {
    this.iRicercaConRange = ricercaConRange;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/07/2025    Wizard     Codice generato da Wizard
   *
   */
  public boolean getRicercaConRange() {
    return iRicercaConRange;
  }

  /**
   * setEqual
   * @param obj
   * @throws CopyException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 01/07/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
  }

}

