package it.testori.thip.datiTecnici.modpro;

import com.thera.thermfw.persist.*;
import it.thera.thip.datiTecnici.modpro.*;
import it.thera.thip.base.azienda.Azienda;

/**
 *
 * <p>Eventuale descrizione della classe</p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 19/03/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71XXX    07/03/2025  AGSOF3   Aggiunto campo solo ordini programmati
 */

public class YAttivitaProduttiva extends AttivitaProduttiva {

	protected boolean iAttivitaDefault = false;

	public YAttivitaProduttiva() {
		super();
		setAttivitaDefault(false);
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setAttivitaDefault(boolean attivitaDefault) {
		this.iAttivitaDefault = attivitaDefault;
		setDirty();
	}

	public boolean getAttivitaDefault() {
		return iAttivitaDefault;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
	}

}

