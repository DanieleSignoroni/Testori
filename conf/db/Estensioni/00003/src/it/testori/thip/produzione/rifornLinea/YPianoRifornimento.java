/*
 * @(#)YPianoRifornimento.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 31/10/2025 at 12:36:49
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 31/10/2025    Wizard     Codice generato da Wizard
 *
 */
package it.testori.thip.produzione.rifornLinea;
import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.produzione.rifornLinea.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;

public class YPianoRifornimento extends PianoRifornimento {

  
  /**
   * Attributo iFlag01
   */
  protected boolean iFlag01 = true;

  
  /**
   * YPianoRifornimento
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 31/10/2025    Wizard     Codice generato da Wizard
   *
   */
  public YPianoRifornimento() {
    setFlag01(true);
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param flag01
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 31/10/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setFlag01(boolean flag01) {
    this.iFlag01 = flag01;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 31/10/2025    Wizard     Codice generato da Wizard
   *
   */
  public boolean getFlag01() {
    return iFlag01;
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
  }

}

