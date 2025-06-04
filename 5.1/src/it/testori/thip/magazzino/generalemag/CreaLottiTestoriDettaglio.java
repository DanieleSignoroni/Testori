package it.testori.thip.magazzino.generalemag;

import java.math.BigDecimal;

import com.thera.thermfw.common.BusinessObjectAdapter;
import com.thera.thermfw.persist.Child;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.persist.Proxy;

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

public class CreaLottiTestoriDettaglio extends BusinessObjectAdapter implements Child {

	protected String iIdAzienda;
	protected String iIdLotto;
	protected Integer iNumeroRocche;
	protected BigDecimal iQuantita;

	protected Proxy iTestata = new Proxy(CreaLottiTestoriTestata.class);

	public String getIdAzienda() {
		return iIdAzienda;
	}

	public void setIdAzienda(String iIdAzienda) {
		this.iIdAzienda = iIdAzienda;
	}

	public String getIdLotto() {
		return iIdLotto;
	}

	public void setIdLotto(String iIdLotto) {
		this.iIdLotto = iIdLotto;
	}

	public Integer getNumeroRocche() {
		return iNumeroRocche;
	}

	public void setNumeroRocche(Integer iNumeroRocche) {
		this.iNumeroRocche = iNumeroRocche;
	}

	public BigDecimal getQuantita() {
		return iQuantita;
	}

	public void setQuantita(BigDecimal iQuantita) {
		this.iQuantita = iQuantita;
	}

	@Override
	public String getKey() {
		return getIdLotto();
	}

	@Override
	public void setKey(String key) {
		setIdLotto(key);
	}

	@Override
	public String getFatherKey() {
		return iTestata.getKey();
	}

	@Override
	public void setFatherKey(String key) {
		iTestata.setKey(key);

	}

	@Override
	public void setFather(PersistentObject father) {
		iTestata.setObject(father);
	}

	@Override
	public String getOrderByClause() {
		return "";
	}

}
