package it.testori.thip.datiTecnici.modpro;

import com.thera.thermfw.common.BusinessObjectAdapter;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.Proxy;
import com.thera.thermfw.security.Authorizable;

import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.articolo.ArticoloVersione;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.datiTecnici.configuratore.Configurazione;
import it.thera.thip.datiTecnici.configuratore.ConfigurazioneProxyEnh;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 07/03/2025
 * <br><br>
 * <b>71XXX    DSSOF3    07/03/2025</b>
 * <p></p>
 */

public class LancioInterrogazioneModProGesdatec extends BusinessObjectAdapter implements Authorizable {

	protected String iIdAzienda;

	protected String iPriorita = "N";

	protected Proxy iArticolo = new Proxy(it.thera.thip.base.articolo.Articolo.class);

	protected Proxy iVersione = new Proxy(it.thera.thip.base.articolo.ArticoloVersione.class);

	protected ConfigurazioneProxyEnh iConfigurazione = new ConfigurazioneProxyEnh(it.thera.thip.datiTecnici.configuratore.Configurazione.class);

	public LancioInterrogazioneModProGesdatec() {
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public String getIdAzienda() {
		return iIdAzienda;
	}

	public void setIdAzienda(String idAzienda) {
		this.iIdAzienda = idAzienda;
		setIdAziendaInternal(idAzienda);
	}

	protected void setIdAziendaInternal(String idAzienda) {
		String key1 = iArticolo.getKey();
		iArticolo.setKey(KeyHelper.replaceTokenObjectKey(key1, 1, idAzienda));
	}

	public void setArticolo(Articolo articolo){
		this.iArticolo.setObject(articolo);
	}

	public Articolo getArticolo() {
		return (Articolo)iArticolo.getObject();
	}

	public void setArticoloKey(String key) {
		iArticolo.setKey(key);
	}

	public String getArticoloKey() {
		return iArticolo.getKey();
	}

	public void setIdArticolo(String idArticolo) {
		String key = iArticolo.getKey();
		iArticolo.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idArticolo));
	}

	public String getIdArticolo() {
		String key = iArticolo.getKey();
		String objIdArticolo = KeyHelper.getTokenObjectKey(key,2);
		return objIdArticolo;
	}

	public void setVersione(ArticoloVersione articoloVersione) {
		String oldObjectKey = getKey();
		String idAzienda = null;
		if (articoloVersione != null) {
			idAzienda = KeyHelper.getTokenObjectKey(articoloVersione.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		String idArticolo = null;
		if (articoloVersione != null) {
			idArticolo = KeyHelper.getTokenObjectKey(articoloVersione.getKey(), 2);
		}
		setIdArticoloInternal(idArticolo);
		this.iVersione.setObject(articoloVersione);
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public ArticoloVersione getVersione() {
		return (ArticoloVersione)iVersione.getObject();
	}

	public void setVersioneKey(String key) {
		String oldObjectKey = getKey();
		iVersione.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		String idArticolo = KeyHelper.getTokenObjectKey(key, 2);
		setIdArticoloInternal(idArticolo);
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getVersioneKey() {
		return iVersione.getKey();
	}

	public void setIdVersione(Integer idVersione) {
		String key = iVersione.getKey();
		iVersione.setKey(KeyHelper.replaceTokenObjectKey(key , 3, idVersione));
	}

	public Integer getIdVersione() {
		String key = iVersione.getKey();
		String objIdVersione = KeyHelper.getTokenObjectKey(key,3);
		return KeyHelper.stringToIntegerObj(objIdVersione);
	}

	protected void setIdArticoloInternal(String idArticolo) {
		String key1 = iVersione.getKey();
		iVersione.setKey(KeyHelper.replaceTokenObjectKey(key1, 2, idArticolo));
		String key2 = iArticolo.getKey();
		iArticolo.setKey(KeyHelper.replaceTokenObjectKey(key2, 2, idArticolo));
		iConfigurazione.setIdArticolo(idArticolo); 
	}

	public void setConfigurazione(Configurazione configurazione) {
		String oldObjectKey = getKey();
		String idAzienda = null;
		if (configurazione != null) {
			idAzienda = KeyHelper.getTokenObjectKey(configurazione.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iConfigurazione.setObject(configurazione);
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public Configurazione getConfigurazione() {
		return (Configurazione)iConfigurazione.getObject();
	}

	public void setConfigurazioneKey(String key) {
		String oldObjectKey = getKey();
		iConfigurazione.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getConfigurazioneKey() {
		return iConfigurazione.getKey();
	}

	public void setIdConfigurazione(Integer idConfigurazione) {
		String key = iConfigurazione.getKey();
		iConfigurazione.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idConfigurazione));
	}

	public Integer getIdConfigurazione() {
		String key = iConfigurazione.getKey();
		String objIdConfigurazione = KeyHelper.getTokenObjectKey(key,2);
		return KeyHelper.stringToIntegerObj(objIdConfigurazione);
	}

	public String getIdEsternoConfig() {
		return iConfigurazione.getIdEsternoConfig();
	}

	public void setIdEsternoConfig(String idEsternoConfig) {
		iConfigurazione.setIdEsternoConfig(idEsternoConfig);
	}

	public void setPriorita(String priorita) {
		this.iPriorita = priorita;
	}

	public String getPriorita() {
		return iPriorita;
	}
}
