package it.testori.thip.logis.lgb;

import java.sql.SQLException;
import java.util.Vector;

import com.thera.thermfw.common.BaseComponentsCollection;
import com.thera.thermfw.common.BusinessObjectAdapter;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.Proxy;
import com.thera.thermfw.security.Authorizable;

import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.logis.lgb.TestataLista;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 26/11/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    26/11/2025  DSSOF3   Prima stesura
 */

public class SceltaTestataListaSpostaUds extends BusinessObjectAdapter implements Authorizable {

	protected String iCodiceSocieta;

	protected Proxy testataLista = new Proxy(TestataLista.class);

	protected boolean iSposta;
	protected String iChiaviSelezionati;

	public SceltaTestataListaSpostaUds() {
		setCodiceSocieta(Azienda.getAziendaCorrente());
	}

	public String getCodiceSocieta() {
		return iCodiceSocieta;
	}

	public void setCodiceSocieta(String iCodiceSocieta) {
		this.iCodiceSocieta = iCodiceSocieta;
		testataLista.setKey(KeyHelper.replaceTokenObjectKey(testataLista.getKey(), 1, iCodiceSocieta));
	}

	public boolean isSposta() {
		return iSposta;
	}

	public void setSposta(boolean iSposta) {
		this.iSposta = iSposta;
	}

	public String getChiaviSelezionati() {
		return iChiaviSelezionati;
	}

	public void setChiaviSelezionati(String iChiaviSelezionati) {
		this.iChiaviSelezionati = iChiaviSelezionati;
	}

	public String getCodiceTestataLista() {
		return KeyHelper.getTokenObjectKey(testataLista.getKey(), 2);
	}

	public void setCodiceTestataLista(String idCodiceLista) {
		testataLista.setKey(KeyHelper.replaceTokenObjectKey(testataLista.getKey(), 2, idCodiceLista));
	}

	public TestataLista getTestataLista(){
		return (TestataLista) testataLista.getObject();
	}

	public void setTestataLista(TestataLista t){
		testataLista.setObject(t);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = super.checkAll(components);
		if(getTestataLista() != null) {
			Vector elencoUds = TestataLista.getElencoUds(getTestataLista());
			if(elencoUds == null || elencoUds.size() == 0) {
				errors.add(new ErrorMessage("BAS0000078","La lsita selezionata non contiene UDS"));
			}
		}
		return errors;
	}

	@Override
	public int save(boolean force) throws SQLException {
		int rc = 1;
		if(isSposta() && getChiaviSelezionati() != null) {
			
		}
		return rc;
	}
}