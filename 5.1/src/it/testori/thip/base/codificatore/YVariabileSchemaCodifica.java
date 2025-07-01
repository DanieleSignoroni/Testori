package it.testori.thip.base.codificatore;

import com.thera.thermfw.persist.*;
import it.thera.thip.base.codificatore.*;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 01/07/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71XXX    01/07/2025  DSSOF3   Prima stesura
 */

public class YVariabileSchemaCodifica extends VariabileSchemaCodifica {

	protected boolean iRicercaConRange = false;

	public YVariabileSchemaCodifica() {
		super();
		setRicercaConRange(false);
	}

	public void setRicercaConRange(boolean ricercaConRange) {
		this.iRicercaConRange = ricercaConRange;
		setDirty();
	}

	public boolean getRicercaConRange() {
		return iRicercaConRange;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
	}

}

