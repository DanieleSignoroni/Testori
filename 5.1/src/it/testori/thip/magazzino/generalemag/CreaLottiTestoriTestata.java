package it.testori.thip.magazzino.generalemag;

import java.util.ArrayList;
import java.util.List;

import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.Proxy;

import it.thera.thip.base.articolo.Articolo;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 04/06/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71XXX    04/06/2025  DSSOF3   Prima stesura
 */

public class CreaLottiTestoriTestata extends CreaLottiTestoriNuovo {

	protected String iDocumento;
	protected String iSoggetto;

	protected Proxy iArticolo = new Proxy(Articolo.class);

	@SuppressWarnings("rawtypes")
	protected List iLottiDettaglio = new ArrayList();

	@SuppressWarnings("rawtypes")
	public List getLottiDettaglio() {
		return iLottiDettaglio;
	}

	public String getDocumento() {
		return iDocumento;
	}

	public void setDocumento(String iDocumento) {
		this.iDocumento = iDocumento;
	}

	public String getSoggetto() {
		return iSoggetto;
	}

	public void setSoggetto(String iSoggetto) {
		this.iSoggetto = iSoggetto;
	}

	public Articolo getArticolo() {
		return (Articolo)iArticolo.getObject();
	}

	public void setArticolo(Articolo iArticolo) {
		this.iArticolo.setObject(iArticolo);
	}

	public String getArticoloKey() {
		return iArticolo.getKey();
	}

	public void setArticoloKey(String key) {
		iArticolo.setKey(key);
	}

	public String getIdArticolo() {
		String key = iArticolo.getKey();
		String rArticolo = KeyHelper.getTokenObjectKey(key,2);
		return rArticolo;
	}

	public void setIdArticolo(String rArticolo) {
		String key = iArticolo.getKey();
		iArticolo.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rArticolo));
	}

}
