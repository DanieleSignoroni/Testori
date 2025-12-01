
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
 * 72XXX    01/12/2025  DSSOF3   Prima stesura
 */

public abstract class InterfacciaEasyCheckPO extends EntitaAzienda	implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static InterfacciaEasyCheck cInstance;

	protected Proxy iNumeratoredocprod = new Proxy(it.thera.thip.base.generale.Numeratore.class);

	protected Proxy iSeriedocprod = new Proxy(it.thera.thip.base.generale.Serie.class);

	protected Proxy iCausaledocprod = new Proxy(it.thera.thip.produzione.documento.CausaleDocProduzione.class);

	protected Proxy iDipendenterilevazione = new Proxy(it.thera.thip.base.dipendente.Dipendente.class);

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
		this.iNumeratoredocprod.setObject(numeratoredocprod);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public Numeratore getNumeratoredocprod() {
		return (Numeratore) iNumeratoredocprod.getObject();
	}

	public void setNumeratoredocprodKey(String key) {
		String oldObjectKey = getKey();
		iNumeratoredocprod.setKey(key);
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
		return iNumeratoredocprod.getKey();
	}

	public void setSeriedocprod(Serie seriedocprod) {
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
		this.iSeriedocprod.setObject(seriedocprod);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public Serie getSeriedocprod() {
		return (Serie) iSeriedocprod.getObject();
	}

	public void setSeriedocprodKey(String key) {
		String oldObjectKey = getKey();
		iSeriedocprod.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		String idNumeratoreDocPrd = KeyHelper.getTokenObjectKey(key, 2);
		setIdNumeratoreDocPrdInternal(idNumeratoreDocPrd);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getSeriedocprodKey() {
		return iSeriedocprod.getKey();
	}

	public void setIdNumeratoreDocPrd(String idNumeratoreDocPrd) {
		setIdNumeratoreDocPrdInternal(idNumeratoreDocPrd);
		setDirty();
	}

	public String getIdNumeratoreDocPrd() {
		String key = iNumeratoredocprod.getKey();
		String objIdNumeratoreDocPrd = KeyHelper.getTokenObjectKey(key, 2);
		return objIdNumeratoreDocPrd;
	}

	public void setIdSerieDocPrd(String idSerieDocPrd) {
		String key = iSeriedocprod.getKey();
		iSeriedocprod.setKey(KeyHelper.replaceTokenObjectKey(key, 3, idSerieDocPrd));
		setDirty();
	}

	public String getIdSerieDocPrd() {
		String key = iSeriedocprod.getKey();
		String objIdSerieDocPrd = KeyHelper.getTokenObjectKey(key, 3);
		return objIdSerieDocPrd;
	}

	public void setCausaledocprod(CausaleDocProduzione causaledocprod) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (causaledocprod != null) {
			idAzienda = KeyHelper.getTokenObjectKey(causaledocprod.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iCausaledocprod.setObject(causaledocprod);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public CausaleDocProduzione getCausaledocprod() {
		return (CausaleDocProduzione) iCausaledocprod.getObject();
	}

	public void setCausaledocprodKey(String key) {
		String oldObjectKey = getKey();
		iCausaledocprod.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getCausaledocprodKey() {
		return iCausaledocprod.getKey();
	}

	public void setIdCausaleDocProd(String idCausaleDocProd) {
		String key = iCausaledocprod.getKey();
		iCausaledocprod.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idCausaleDocProd));
		setDirty();
	}

	public String getIdCausaleDocProd() {
		String key = iCausaledocprod.getKey();
		String objIdCausaleDocProd = KeyHelper.getTokenObjectKey(key, 2);
		return objIdCausaleDocProd;
	}

	public void setDipendenterilevazione(Dipendente dipendenterilevazione) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (dipendenterilevazione != null) {
			idAzienda = KeyHelper.getTokenObjectKey(dipendenterilevazione.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iDipendenterilevazione.setObject(dipendenterilevazione);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public Dipendente getDipendenterilevazione() {
		return (Dipendente) iDipendenterilevazione.getObject();
	}

	public void setDipendenterilevazioneKey(String key) {
		String oldObjectKey = getKey();
		iDipendenterilevazione.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getDipendenterilevazioneKey() {
		return iDipendenterilevazione.getKey();
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
		String key = iDipendenterilevazione.getKey();
		iDipendenterilevazione.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idDipendenteRilev));
		setDirty();
	}

	public String getIdDipendenteRilev() {
		String key = iDipendenterilevazione.getKey();
		String objIdDipendenteRilev = KeyHelper.getTokenObjectKey(key, 2);
		return objIdDipendenteRilev;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		InterfacciaEasyCheckPO interfacciaEasyCheckPO = (InterfacciaEasyCheckPO) obj;
		iNumeratoredocprod.setEqual(interfacciaEasyCheckPO.iNumeratoredocprod);
		iSeriedocprod.setEqual(interfacciaEasyCheckPO.iSeriedocprod);
		iCausaledocprod.setEqual(interfacciaEasyCheckPO.iCausaledocprod);
		iDipendenterilevazione.setEqual(interfacciaEasyCheckPO.iDipendenterilevazione);
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
		String key2 = iNumeratoredocprod.getKey();
		iNumeratoredocprod.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
		String key3 = iSeriedocprod.getKey();
		iSeriedocprod.setKey(KeyHelper.replaceTokenObjectKey(key3, 1, idAzienda));
		String key4 = iCausaledocprod.getKey();
		iCausaledocprod.setKey(KeyHelper.replaceTokenObjectKey(key4, 1, idAzienda));
		String key5 = iDipendenterilevazione.getKey();
		iDipendenterilevazione.setKey(KeyHelper.replaceTokenObjectKey(key5, 1, idAzienda));
	}

	protected void setIdNumeratoreDocPrdInternal(String idNumeratoreDocPrd) {
		String key1 = iNumeratoredocprod.getKey();
		iNumeratoredocprod.setKey(KeyHelper.replaceTokenObjectKey(key1, 2, idNumeratoreDocPrd));
		String key2 = iSeriedocprod.getKey();
		iSeriedocprod.setKey(KeyHelper.replaceTokenObjectKey(key2, 2, idNumeratoreDocPrd));
	}

}
