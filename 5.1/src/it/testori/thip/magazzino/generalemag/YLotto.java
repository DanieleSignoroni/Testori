package it.testori.thip.magazzino.generalemag;

import com.thera.thermfw.persist.*;
import java.math.*;
import it.thera.thip.magazzino.generalemag.*;
import it.thera.thip.base.azienda.Azienda;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 16/06/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71XXX    16/06/2025  DSSOF3   Prima stesura
 */

public class YLotto extends Lotto {

	protected String iNumeroRocche;

	protected BigDecimal iQuantitaOriginale;

	protected String iTelaio;

	protected BigDecimal iAltezzaMinima;

	protected BigDecimal iAltezzaMassima;

	protected BigDecimal iPesoKg;

	protected BigDecimal iPesoCalcolatoPezzo;

	protected BigDecimal iPesoCalcolatoArrotondato;

	protected boolean iStEtichetta = true;

	protected Proxy iDifettosita = new Proxy(it.testori.thip.magazzino.generalemag.YDifettosita.class);

	protected Proxy iDifettositamisure = new Proxy(it.testori.thip.magazzino.generalemag.YDifettositaMisure.class);

	public YLotto() {
		super();
		setStEtichetta(true);
		setCodiceAzienda(Azienda.getAziendaCorrente());
	}

	public void setNumeroRocche(String numeroRocche) {
		this.iNumeroRocche = numeroRocche;
		setDirty();
	}

	public String getNumeroRocche() {
		return iNumeroRocche;
	}

	public void setQuantitaOriginale(BigDecimal quantitaOriginale) {
		this.iQuantitaOriginale = quantitaOriginale;
		setDirty();
	}

	public BigDecimal getQuantitaOriginale() {
		return iQuantitaOriginale;
	}

	public void setTelaio(String telaio) {
		this.iTelaio = telaio;
		setDirty();
	}

	public String getTelaio() {
		return iTelaio;
	}

	public void setAltezzaMinima(BigDecimal altezzaMinima) {
		this.iAltezzaMinima = altezzaMinima;
		setDirty();
	}

	public BigDecimal getAltezzaMinima() {
		return iAltezzaMinima;
	}

	public void setAltezzaMassima(BigDecimal altezzaMassima) {
		this.iAltezzaMassima = altezzaMassima;
		setDirty();
	}

	public BigDecimal getAltezzaMassima() {
		return iAltezzaMassima;
	}

	public void setPesoKg(BigDecimal pesoKg) {
		this.iPesoKg = pesoKg;
		setDirty();
	}

	public BigDecimal getPesoKg() {
		return iPesoKg;
	}

	public void setPesoCalcolatoPezzo(BigDecimal pesoCalcolatoPezzo) {
		this.iPesoCalcolatoPezzo = pesoCalcolatoPezzo;
		setDirty();
	}

	public BigDecimal getPesoCalcolatoPezzo() {
		return iPesoCalcolatoPezzo;
	}

	public void setPesoCalcolatoArrotondato(BigDecimal pesoCalcolatoArrotondato) {
		this.iPesoCalcolatoArrotondato = pesoCalcolatoArrotondato;
		setDirty();
	}

	public BigDecimal getPesoCalcolatoArrotondato() {
		return iPesoCalcolatoArrotondato;
	}

	public void setStEtichetta(boolean stEtichetta) {
		this.iStEtichetta = stEtichetta;
		setDirty();
	}

	public boolean getStEtichetta() {
		return iStEtichetta;
	}

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

	public YDifettosita getDifettosita() {
		return (YDifettosita)iDifettosita.getObject();
	}

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

	public String getDifettositaKey() {
		return iDifettosita.getKey();
	}

	public void setIdDifettosita(String idDifettosita) {
		String key = iDifettosita.getKey();
		iDifettosita.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idDifettosita));
		setDirty();
	}

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
	 * 16/06/2025    Wizard     Codice generato da Wizard
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

	public YDifettositaMisure getDifettositamisure() {
		return (YDifettositaMisure)iDifettositamisure.getObject();
	}

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

	public String getDifettositamisureKey() {
		return iDifettositamisure.getKey();
	}

	public void setCodiceAzienda(String codiceAzienda) {
		super.setCodiceAzienda(codiceAzienda);

	}

	public void setIdDifettositaMisure(String idDifettositaMisure) {
		String key = iDifettositamisure.getKey();
		iDifettositamisure.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idDifettositaMisure));
		setDirty();
	}

	public String getIdDifettositaMisure() {
		String key = iDifettositamisure.getKey();
		String objIdDifettositaMisure = KeyHelper.getTokenObjectKey(key,2);
		return objIdDifettositaMisure;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		YLotto yLotto = (YLotto)obj;
		iDifettosita.setEqual(yLotto.iDifettosita);
		iDifettositamisure.setEqual(yLotto.iDifettositamisure);
	}

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

