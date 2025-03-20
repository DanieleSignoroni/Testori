package it.testori.thip.datiTecnici.modpro;

import java.math.BigDecimal;

import com.thera.thermfw.common.BusinessObjectAdapter;
import com.thera.thermfw.persist.Child;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.persist.Proxy;

import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.datiTecnici.modpro.Attivita;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 07/03/2025
 * <br><br>
 * <b>71XXX    DSSOF3    07/03/2025</b>
 * <p></p>
 */

public class InterrogazioneModProGesdatecAttivita extends BusinessObjectAdapter implements Child {

	protected String iIdAzienda;
	
	protected BigDecimal iQuantita;

	protected Proxy iAttivita = new Proxy(it.thera.thip.datiTecnici.modpro.Attivita.class);

	protected Proxy iTestata = new Proxy(InterrogazioneModProGesdatec.class);

	public InterrogazioneModProGesdatecAttivita() {
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public String getIdAzienda() {
		return iIdAzienda;
	}

	public void setIdAzienda(String idAzienda) {
		this.iIdAzienda = idAzienda;
		setIdAziendaInternal(idAzienda);
	}
	
	public BigDecimal getQuantita() {
		return iQuantita;
	}

	public void setQuantita(BigDecimal iQuantita) {
		this.iQuantita = iQuantita;
	}

	protected void setIdAziendaInternal(String idAzienda) {
		String key1 = iAttivita.getKey();
		iAttivita.setKey(KeyHelper.replaceTokenObjectKey(key1, 1, idAzienda));
		String key2 = iTestata.getKey();
		iTestata.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
	}

	public void setAttivita(Attivita attivita) {
		String oldObjectKey = getKey();
		String idAzienda = null;
		if (attivita != null) {
			idAzienda = KeyHelper.getTokenObjectKey(attivita.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iAttivita.setObject(attivita);
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public Attivita getAttivita() {
		return (Attivita) iAttivita.getObject();
	}

	public void setAttivitaKey(String key) {
		String oldObjectKey = getKey();
		iAttivita.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getAttivitaKey() {
		return iAttivita.getKey();
	}

	public void setIdAttivita(String idAttivita) {
		String key = iAttivita.getKey();
		iAttivita.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idAttivita));
	}

	public String getIdAttivita() {
		String key = iAttivita.getKey();
		String objIdAttivita = KeyHelper.getTokenObjectKey(key, 2);
		return objIdAttivita;
	}

	@Override
	public String getKey() {
		return getIdAttivita();
	}

	@Override
	public void setKey(String key) {
		setIdAttivita(key);
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
