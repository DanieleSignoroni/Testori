package it.testori.thip.magazzino.generalemag;

import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

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

public abstract class YDifettositaMisurePO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static YDifettositaMisure cInstance;

	protected String iCodice;

	protected String iDescrizione;

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (YDifettositaMisure)Factory.createObject(YDifettositaMisure.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static YDifettositaMisure elementWithKey(String key, int lockType) throws SQLException {
		return (YDifettositaMisure)PersistentObject.elementWithKey(YDifettositaMisure.class, key, lockType);
	}

	public YDifettositaMisurePO() {
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setCodice(String codice) {
		this.iCodice = codice;
		setDirty();
		setOnDB(false);
	}

	public String getCodice() {
		return iCodice;
	}

	public void setDescrizione(String descrizione) {
		this.iDescrizione = descrizione;
		setDirty();
	}

	public String getDescrizione() {
		return iDescrizione;
	}

	public void setIdAzienda(String idAzienda) {
		iAzienda.setKey(idAzienda);
		setDirty();
		setOnDB(false);
	}

	public String getIdAzienda() {
		String key = iAzienda.getKey();
		return key;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
		setCodice(KeyHelper.getTokenObjectKey(key, 2));
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		String codice = getCodice();
		Object[] keyParts = {idAzienda, codice};
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return YDifettositaMisureTM.getInstance();
	}

}

