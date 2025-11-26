package it.testori.thip.logis.lgb;

import com.thera.thermfw.common.BusinessObjectAdapter;
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

	protected String getCodiceTestataLista() {
		return KeyHelper.getTokenObjectKey(testataLista.getKey(), 2);
	}

	protected void setCodiceTestataLista(String idCodiceLista) {
		testataLista.setKey(KeyHelper.replaceTokenObjectKey(testataLista.getKey(), 2, idCodiceLista));
	}

	public TestataLista getTestataLista(){
		return (TestataLista) testataLista.getObject();
	}

	public void setTestataLista(TestataLista t){
		testataLista.setObject(t);
	}
}
