/*
 * @(#)YAttivitaEsecutiva.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 26/06/2025 at 09:07:52
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 26/06/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.produzione.ordese;
import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.produzione.ordese.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;

public class YAttivitaEsecutiva extends AttivitaEsecutiva {

  
  /**
   * Attributo iTipoTaglio
   */
  protected char iTipoTaglio = '-';

  
  /**
   * YAttivitaEsecutiva
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public YAttivitaEsecutiva() {
    setTipoTaglio('-');
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param tipoTaglio
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setTipoTaglio(char tipoTaglio) {
    this.iTipoTaglio = tipoTaglio;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return char
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public char getTipoTaglio() {
    return iTipoTaglio;
  }

  /**
   * setEqual
   * @param obj
   * @throws CopyException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 26/06/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
  }

}

