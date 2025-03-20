package it.testori.thip.datiTecnici.modpro;

import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import java.math.*;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

/**
 *
 * <p>Eventuale descrizione della classe</p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 13/03/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71868    07/03/2025  AGSOF3   Aggiunto campo solo ordini programmati
 */

public abstract class YPercentualeSfridoPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static YPercentualeSfrido cInstance;

	protected BigDecimal iLunghezza;

	protected BigDecimal iPercentuale;

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (YPercentualeSfrido)Factory.createObject(YPercentualeSfrido.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static YPercentualeSfrido elementWithKey(String key, int lockType) throws SQLException {
		return (YPercentualeSfrido)PersistentObject.elementWithKey(YPercentualeSfrido.class, key, lockType);
	}

	public YPercentualeSfridoPO() {
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setLunghezza(BigDecimal lunghezza) {
		this.iLunghezza = lunghezza;
		setDirty();
		setOnDB(false);
	}

	public BigDecimal getLunghezza() {
		return iLunghezza;
	}

	public void setPercentuale(BigDecimal percentuale) {
		this.iPercentuale = percentuale;
		setDirty();
	}

	public BigDecimal getPercentuale() {
		return iPercentuale;
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
		setLunghezza(KeyHelper.stringToBigDecimal(KeyHelper.getTokenObjectKey(key, 2)));
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		java.math.BigDecimal lunghezza = getLunghezza();
		Object[] keyParts = {idAzienda, lunghezza};
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return YPercentualeSfridoTM.getInstance();
	}

}

