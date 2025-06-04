package it.testori.thip.magazzino.generalemag;

import java.math.BigDecimal;
import java.sql.Date;

import com.thera.thermfw.common.BusinessObjectAdapter;
import com.thera.thermfw.security.Authorizable;

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

public class CreaLottiTestoriNuovo extends BusinessObjectAdapter implements Authorizable {

	protected String iIdAzienda;

	protected BigDecimal iQuantita;

	protected Integer iNumeroConfezioni;

	protected Integer iNumeroRocche;

	protected String iLottoAcquisto;

	protected Date iDataScadenza;

	protected String iIdLotto;

	protected String iChiaveSelezionato;

	protected String iNote;

	public String getIdAzienda() {
		return iIdAzienda;
	}

	public void setIdAzienda(String iIdAzienda) {
		this.iIdAzienda = iIdAzienda;
	}

	public BigDecimal getQuantita() {
		return iQuantita;
	}

	public void setQuantita(BigDecimal iQuantita) {
		this.iQuantita = iQuantita;
	}

	public Integer getNumeroConfezioni() {
		return iNumeroConfezioni;
	}

	public void setNumeroConfezioni(Integer iNumeroConfezioni) {
		this.iNumeroConfezioni = iNumeroConfezioni;
	}

	public Integer getNumeroRocche() {
		return iNumeroRocche;
	}

	public void setNumeroRocche(Integer iNumeroRocche) {
		this.iNumeroRocche = iNumeroRocche;
	}

	public String getLottoAcquisto() {
		return iLottoAcquisto;
	}

	public void setLottoAcquisto(String iLottoAcquisto) {
		this.iLottoAcquisto = iLottoAcquisto;
	}

	public Date getDataScadenza() {
		return iDataScadenza;
	}

	public void setDataScadenza(Date iDataScadenza) {
		this.iDataScadenza = iDataScadenza;
	}

	public String getNote() {
		return iNote;
	}

	public void setNote(String iNote) {
		this.iNote = iNote;
	}

	public String getIdLotto() {
		return iIdLotto;
	}

	public void setIdLotto(String iIdLotto) {
		this.iIdLotto = iIdLotto;
	}

	public String getChiaveSelezionato() {
		return iChiaveSelezionato;
	}

	public void setChiaveSelezionato(String iChiaveSelezionato) {
		this.iChiaveSelezionato = iChiaveSelezionato;
	}

}
