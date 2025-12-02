
package it.testori.thip.easycheck;

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
import it.thera.thip.base.dipendente.Dipendente;
import it.thera.thip.base.generale.Numeratore;
import it.thera.thip.base.generale.Serie;
import it.thera.thip.cs.EntitaAzienda;
import it.thera.thip.produzione.documento.CausaleDocProduzione;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 01/12/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72236    01/12/2025  DSSOF3   Prima stesura
 */

public abstract class InterfacciaEasyCheckPO extends EntitaAzienda	implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static InterfacciaEasyCheck cInstance;

	protected Proxy NumeratoreDocProd = new Proxy(it.thera.thip.base.generale.Numeratore.class);

	protected Proxy iSerieDocProd = new Proxy(it.thera.thip.base.generale.Serie.class);

	protected Proxy iCausaleDocProd = new Proxy(it.thera.thip.produzione.documento.CausaleDocProduzione.class);

	protected Proxy iDipendenteRilevazione = new Proxy(it.thera.thip.base.dipendente.Dipendente.class);

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (InterfacciaEasyCheck) Factory.createObject(InterfacciaEasyCheck.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static InterfacciaEasyCheck elementWithKey(String key, int lockType) throws SQLException {
		return (InterfacciaEasyCheck) PersistentObject.elementWithKey(InterfacciaEasyCheck.class, key, lockType);
	}

	public InterfacciaEasyCheckPO() {
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setNumeratoredocprod(Numeratore numeratoredocprod) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (numeratoredocprod != null) {
			idAzienda = KeyHelper.getTokenObjectKey(numeratoredocprod.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		String idNumeratoreDocPrd = getIdNumeratoreDocPrd();
		if (numeratoredocprod != null) {
			idNumeratoreDocPrd = KeyHelper.getTokenObjectKey(numeratoredocprod.getKey(), 2);
		}
		setIdNumeratoreDocPrdInternal(idNumeratoreDocPrd);
		this.NumeratoreDocProd.setObject(numeratoredocprod);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public Numeratore getNumeratoredocprod() {
		return (Numeratore) NumeratoreDocProd.getObject();
	}

	public void setNumeratoredocprodKey(String key) {
		String oldObjectKey = getKey();
		NumeratoreDocProd.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		String idNumeratoreDocPrd = KeyHelper.getTokenObjectKey(key, 2);
		setIdNumeratoreDocPrdInternal(idNumeratoreDocPrd);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getNumeratoredocprodKey() {
		return NumeratoreDocProd.getKey();
	}

	public void setSerieDocProd(Serie seriedocprod) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (seriedocprod != null) {
			idAzienda = KeyHelper.getTokenObjectKey(seriedocprod.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		String idNumeratoreDocPrd = getIdNumeratoreDocPrd();
		if (seriedocprod != null) {
			idNumeratoreDocPrd = KeyHelper.getTokenObjectKey(seriedocprod.getKey(), 2);
		}
		setIdNumeratoreDocPrdInternal(idNumeratoreDocPrd);
		this.iSerieDocProd.setObject(seriedocprod);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public Serie getSerieDocProd() {
		return (Serie) iSerieDocProd.getObject();
	}

	public void setSerieDocProdKey(String key) {
		String oldObjectKey = getKey();
		iSerieDocProd.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		String idNumeratoreDocPrd = KeyHelper.getTokenObjectKey(key, 2);
		setIdNumeratoreDocPrdInternal(idNumeratoreDocPrd);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getSerieDocProdKey() {
		return iSerieDocProd.getKey();
	}

	public void setIdNumeratoreDocPrd(String idNumeratoreDocPrd) {
		setIdNumeratoreDocPrdInternal(idNumeratoreDocPrd);
		setDirty();
	}

	public String getIdNumeratoreDocPrd() {
		String key = NumeratoreDocProd.getKey();
		String objIdNumeratoreDocPrd = KeyHelper.getTokenObjectKey(key, 2);
		return objIdNumeratoreDocPrd;
	}

	public void setIdSerieDocPrd(String idSerieDocPrd) {
		String key = iSerieDocProd.getKey();
		iSerieDocProd.setKey(KeyHelper.replaceTokenObjectKey(key, 3, idSerieDocPrd));
		setDirty();
	}

	public String getIdSerieDocPrd() {
		String key = iSerieDocProd.getKey();
		String objIdSerieDocPrd = KeyHelper.getTokenObjectKey(key, 3);
		return objIdSerieDocPrd;
	}

	public void setCausaleDocProd(CausaleDocProduzione causaledocprod) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (causaledocprod != null) {
			idAzienda = KeyHelper.getTokenObjectKey(causaledocprod.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iCausaleDocProd.setObject(causaledocprod);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public CausaleDocProduzione getCausaleDocProd() {
		return (CausaleDocProduzione) iCausaleDocProd.getObject();
	}

	public void setCausaleDocProdKey(String key) {
		String oldObjectKey = getKey();
		iCausaleDocProd.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getCausaleDocProdKey() {
		return iCausaleDocProd.getKey();
	}

	public void setIdCausaleDocProd(String idCausaleDocProd) {
		String key = iCausaleDocProd.getKey();
		iCausaleDocProd.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idCausaleDocProd));
		setDirty();
	}

	public String getIdCausaleDocProd() {
		String key = iCausaleDocProd.getKey();
		String objIdCausaleDocProd = KeyHelper.getTokenObjectKey(key, 2);
		return objIdCausaleDocProd;
	}

	public void setDipendenteRilevazione(Dipendente dipendenterilevazione) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (dipendenterilevazione != null) {
			idAzienda = KeyHelper.getTokenObjectKey(dipendenterilevazione.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iDipendenteRilevazione.setObject(dipendenterilevazione);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public Dipendente getDipendenteRilevazione() {
		return (Dipendente) iDipendenteRilevazione.getObject();
	}

	public void setDipendenteRilevazioneKey(String key) {
		String oldObjectKey = getKey();
		iDipendenteRilevazione.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getDipendenteRilevazioneKey() {
		return iDipendenteRilevazione.getKey();
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

	public void setIdDipendenteRilev(String idDipendenteRilev) {
		String key = iDipendenteRilevazione.getKey();
		iDipendenteRilevazione.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idDipendenteRilev));
		setDirty();
	}

	public String getIdDipendenteRilev() {
		String key = iDipendenteRilevazione.getKey();
		String objIdDipendenteRilev = KeyHelper.getTokenObjectKey(key, 2);
		return objIdDipendenteRilev;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		InterfacciaEasyCheckPO interfacciaEasyCheckPO = (InterfacciaEasyCheckPO) obj;
		NumeratoreDocProd.setEqual(interfacciaEasyCheckPO.NumeratoreDocProd);
		iSerieDocProd.setEqual(interfacciaEasyCheckPO.iSerieDocProd);
		iCausaleDocProd.setEqual(interfacciaEasyCheckPO.iCausaleDocProd);
		iDipendenteRilevazione.setEqual(interfacciaEasyCheckPO.iDipendenteRilevazione);
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
		return InterfacciaEasyCheckTM.getInstance();
	}

	protected void setIdAziendaInternal(String idAzienda) {
		iAzienda.setKey(idAzienda);
		String key2 = NumeratoreDocProd.getKey();
		NumeratoreDocProd.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
		String key3 = iSerieDocProd.getKey();
		iSerieDocProd.setKey(KeyHelper.replaceTokenObjectKey(key3, 1, idAzienda));
		String key4 = iCausaleDocProd.getKey();
		iCausaleDocProd.setKey(KeyHelper.replaceTokenObjectKey(key4, 1, idAzienda));
		String key5 = iDipendenteRilevazione.getKey();
		iDipendenteRilevazione.setKey(KeyHelper.replaceTokenObjectKey(key5, 1, idAzienda));
	}

	protected void setIdNumeratoreDocPrdInternal(String idNumeratoreDocPrd) {
		String key1 = NumeratoreDocProd.getKey();
		NumeratoreDocProd.setKey(KeyHelper.replaceTokenObjectKey(key1, 2, idNumeratoreDocPrd));
		String key2 = iSerieDocProd.getKey();
		iSerieDocProd.setKey(KeyHelper.replaceTokenObjectKey(key2, 2, idNumeratoreDocPrd));
	}

}
