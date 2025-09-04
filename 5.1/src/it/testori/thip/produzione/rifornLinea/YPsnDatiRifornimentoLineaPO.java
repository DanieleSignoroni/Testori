
package it.testori.thip.produzione.rifornLinea;

import java.sql.SQLException;
import java.util.Vector;

import com.thera.thermfw.common.BaseComponentsCollection;
import com.thera.thermfw.common.BusinessObject;
import com.thera.thermfw.common.Deletable;
import com.thera.thermfw.persist.CopyException;
import com.thera.thermfw.persist.Copyable;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.persist.Proxy;
import com.thera.thermfw.persist.TableManager;
import com.thera.thermfw.security.Authorizable;
import com.thera.thermfw.security.Conflictable;

import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.cliente.ClienteVendita;
import it.thera.thip.base.generale.Numeratore;
import it.thera.thip.base.generale.Serie;
import it.thera.thip.cs.EntitaAzienda;
import it.thera.thip.vendite.generaleVE.CausaleDocumentoVendita;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 04/09/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    04/09/2025  DSSOF3   Prima stesura
 */

public abstract class YPsnDatiRifornimentoLineaPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static YPsnDatiRifornimentoLinea cInstance;

	protected Proxy iClienteIntestatario = new Proxy(it.thera.thip.base.cliente.ClienteVendita.class);

	protected Proxy iNumeratoreDocVen = new Proxy(it.thera.thip.base.generale.Numeratore.class);

	protected Proxy iSerieDocVen = new Proxy(it.thera.thip.base.generale.Serie.class);

	protected Proxy iCausaleDocVen = new Proxy(it.thera.thip.vendite.generaleVE.CausaleDocumentoVendita.class);

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (YPsnDatiRifornimentoLinea) Factory.createObject(YPsnDatiRifornimentoLinea.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static YPsnDatiRifornimentoLinea elementWithKey(String key, int lockType) throws SQLException {
		return (YPsnDatiRifornimentoLinea) PersistentObject.elementWithKey(YPsnDatiRifornimentoLinea.class, key,
				lockType);
	}

	public YPsnDatiRifornimentoLineaPO() {
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setClienteIntestatario(ClienteVendita clienteintestatario) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (clienteintestatario != null) {
			idAzienda = KeyHelper.getTokenObjectKey(clienteintestatario.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iClienteIntestatario.setObject(clienteintestatario);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public ClienteVendita getClienteIntestatario() {
		return (ClienteVendita) iClienteIntestatario.getObject();
	}

	public void setClienteIntestatarioKey(String key) {
		String oldObjectKey = getKey();
		iClienteIntestatario.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getClienteIntestatarioKey() {
		return iClienteIntestatario.getKey();
	}

	public void setIdClienteIntestatario(String idClienteIntestatario) {
		String key = iClienteIntestatario.getKey();
		iClienteIntestatario.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idClienteIntestatario));
		setDirty();
	}

	public String getIdClienteIntestatario() {
		String key = iClienteIntestatario.getKey();
		String objIdClienteIntestatario = KeyHelper.getTokenObjectKey(key, 2);
		return objIdClienteIntestatario;
	}

	public void setNumeratoreDocVen(Numeratore numeratoreDocVen) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (numeratoreDocVen != null) {
			idAzienda = KeyHelper.getTokenObjectKey(numeratoreDocVen.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		String idNumeratoreDocVen = getIdNumeratoreDocVen();
		if (numeratoreDocVen != null) {
			idNumeratoreDocVen = KeyHelper.getTokenObjectKey(numeratoreDocVen.getKey(), 2);
		}
		setIdNumeratoreDocVenInternal(idNumeratoreDocVen);
		this.iNumeratoreDocVen.setObject(numeratoreDocVen);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public Numeratore getNumeratoreDocVen() {
		return (Numeratore) iNumeratoreDocVen.getObject();
	}

	public void setNumeratoreDocVenKey(String key) {
		String oldObjectKey = getKey();
		iNumeratoreDocVen.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		String idNumeratoreDocVen = KeyHelper.getTokenObjectKey(key, 2);
		setIdNumeratoreDocVenInternal(idNumeratoreDocVen);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getNumeratoreDocVenKey() {
		return iNumeratoreDocVen.getKey();
	}

	public void setSerieDocVen(Serie serieDocVen) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (serieDocVen != null) {
			idAzienda = KeyHelper.getTokenObjectKey(serieDocVen.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		String idNumeratoreDocVen = getIdNumeratoreDocVen();
		if (serieDocVen != null) {
			idNumeratoreDocVen = KeyHelper.getTokenObjectKey(serieDocVen.getKey(), 2);
		}
		setIdNumeratoreDocVenInternal(idNumeratoreDocVen);
		this.iSerieDocVen.setObject(serieDocVen);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public Serie getSerieDocVen() {
		return (Serie) iSerieDocVen.getObject();
	}

	public void setSerieDocVenKey(String key) {
		String oldObjectKey = getKey();
		iSerieDocVen.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		String idNumeratoreDocVen = KeyHelper.getTokenObjectKey(key, 2);
		setIdNumeratoreDocVenInternal(idNumeratoreDocVen);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getSerieDocVenKey() {
		return iSerieDocVen.getKey();
	}

	public void setIdNumeratoreDocVen(String idNumeratoreDocVen) {
		setIdNumeratoreDocVenInternal(idNumeratoreDocVen);
		setDirty();
	}

	public String getIdNumeratoreDocVen() {
		String key = iNumeratoreDocVen.getKey();
		String objIdNumeratoreDocVen = KeyHelper.getTokenObjectKey(key, 2);
		return objIdNumeratoreDocVen;
	}

	public void setIdSerieDocVen(String idSerieDocVen) {
		String key = iSerieDocVen.getKey();
		iSerieDocVen.setKey(KeyHelper.replaceTokenObjectKey(key, 3, idSerieDocVen));
		setDirty();
	}

	public String getIdSerieDocVen() {
		String key = iSerieDocVen.getKey();
		String objIdSerieDocVen = KeyHelper.getTokenObjectKey(key, 3);
		return objIdSerieDocVen;
	}

	public void setCausaleDocVen(CausaleDocumentoVendita causaleDocVen) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (causaleDocVen != null) {
			idAzienda = KeyHelper.getTokenObjectKey(causaleDocVen.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iCausaleDocVen.setObject(causaleDocVen);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public CausaleDocumentoVendita getCausaleDocVen() {
		return (CausaleDocumentoVendita) iCausaleDocVen.getObject();
	}

	public void setCausaleDocVenKey(String key) {
		String oldObjectKey = getKey();
		iCausaleDocVen.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getCausaleDocVenKey() {
		return iCausaleDocVen.getKey();
	}

	public void setIdAzienda(String idAzienda) {
		setIdAziendaInternal(idAzienda);
		setDirty();
		setOnDB(false);
	}

	public String getIdAzienda() {
		String key = iAzienda.getKey();
		return key;
	}

	public void setIdCausaleDocVen(String idCausaleDocVen) {
		String key = iCausaleDocVen.getKey();
		iCausaleDocVen.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idCausaleDocVen));
		setDirty();
	}

	public String getIdCausaleDocVen() {
		String key = iCausaleDocVen.getKey();
		String objIdCausaleDocVen = KeyHelper.getTokenObjectKey(key, 2);
		return objIdCausaleDocVen;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		YPsnDatiRifornimentoLineaPO yPsnDatiRifornimentoLineaPO = (YPsnDatiRifornimentoLineaPO) obj;
		iClienteIntestatario.setEqual(yPsnDatiRifornimentoLineaPO.iClienteIntestatario);
		iNumeratoreDocVen.setEqual(yPsnDatiRifornimentoLineaPO.iNumeratoreDocVen);
		iSerieDocVen.setEqual(yPsnDatiRifornimentoLineaPO.iSerieDocVen);
		iCausaleDocVen.setEqual(yPsnDatiRifornimentoLineaPO.iCausaleDocVen);
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setIdAzienda(key);
	}

	public String getKey() {
		return getIdAzienda();
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return YPsnDatiRifornimentoLineaTM.getInstance();
	}

	protected void setIdAziendaInternal(String idAzienda) {
		iAzienda.setKey(idAzienda);
		String key2 = iClienteIntestatario.getKey();
		iClienteIntestatario.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
		String key3 = iNumeratoreDocVen.getKey();
		iNumeratoreDocVen.setKey(KeyHelper.replaceTokenObjectKey(key3, 1, idAzienda));
		String key4 = iSerieDocVen.getKey();
		iSerieDocVen.setKey(KeyHelper.replaceTokenObjectKey(key4, 1, idAzienda));
		String key5 = iCausaleDocVen.getKey();
		iCausaleDocVen.setKey(KeyHelper.replaceTokenObjectKey(key5, 1, idAzienda));
	}

	protected void setIdNumeratoreDocVenInternal(String idNumeratoreDocVen) {
		String key1 = iNumeratoreDocVen.getKey();
		iNumeratoreDocVen.setKey(KeyHelper.replaceTokenObjectKey(key1, 2, idNumeratoreDocVen));
		String key2 = iSerieDocVen.getKey();
		iSerieDocVen.setKey(KeyHelper.replaceTokenObjectKey(key2, 2, idNumeratoreDocVen));
	}

}
