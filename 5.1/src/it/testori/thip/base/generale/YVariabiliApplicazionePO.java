package it.testori.thip.base.generale;

import java.math.BigDecimal;
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
import com.thera.thermfw.persist.TableManager;
import com.thera.thermfw.security.Authorizable;
import com.thera.thermfw.security.Conflictable;

import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.cs.Descrizione;
import it.thera.thip.cs.EntitaAzienda;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 05/12/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72236    05/12/2025  DSSOF3   Prima stesura
 */

public abstract class YVariabiliApplicazionePO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static YVariabiliApplicazione cInstance;

	protected String iCodice;

	protected BigDecimal iValore;

	protected Descrizione iDescrizione;

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (YVariabiliApplicazione) Factory.createObject(YVariabiliApplicazione.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static YVariabiliApplicazione elementWithKey(String key, int lockType) throws SQLException {
		return (YVariabiliApplicazione) PersistentObject.elementWithKey(YVariabiliApplicazione.class, key, lockType);
	}

	public YVariabiliApplicazionePO() {
		iDescrizione = (Descrizione) Factory.createObject(Descrizione.class);
		iDescrizione.setOwner(this);

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

	public void setValore(BigDecimal valore) {
		this.iValore = valore;
		setDirty();
	}

	public BigDecimal getValore() {
		return iValore;
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

	public Descrizione getDescrizione() {
		return iDescrizione;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		YVariabiliApplicazionePO yVariabiliApplicazionePO = (YVariabiliApplicazionePO) obj;
		iDescrizione.setEqual(yVariabiliApplicazionePO.iDescrizione);
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
		Object[] keyParts = { idAzienda, codice };
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return YVariabiliApplicazioneTM.getInstance();
	}

}
