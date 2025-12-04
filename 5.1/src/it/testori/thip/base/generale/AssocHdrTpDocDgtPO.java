package it.testori.thip.base.generale;

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
import com.thera.thermfw.util.PersistentClassHdr;

import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.documentoDgt.TipoDocumentoDigitale;
import it.thera.thip.cs.EntitaAzienda;

public abstract class AssocHdrTpDocDgtPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static AssocHdrTpDocDgt cInstance;

	protected boolean iDefCompilation = true;

	protected Proxy iTipodocdgt = new Proxy(it.thera.thip.base.documentoDgt.TipoDocumentoDigitale.class);

	protected Proxy iClasse = new Proxy(com.thera.thermfw.util.PersistentClassHdr.class);

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (AssocHdrTpDocDgt) Factory.createObject(AssocHdrTpDocDgt.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static AssocHdrTpDocDgt elementWithKey(String key, int lockType) throws SQLException {
		return (AssocHdrTpDocDgt) PersistentObject.elementWithKey(AssocHdrTpDocDgt.class, key, lockType);
	}

	public AssocHdrTpDocDgtPO() {
		setDefCompilation(true);
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setDefCompilation(boolean defCompilation) {
		this.iDefCompilation = defCompilation;
		setDirty();
	}

	public boolean getDefCompilation() {
		return iDefCompilation;
	}

	public void setTipodocdgt(TipoDocumentoDigitale tipodocdgt) {
		String idAzienda = getIdAzienda();
		if (tipodocdgt != null) {
			idAzienda = KeyHelper.getTokenObjectKey(tipodocdgt.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iTipodocdgt.setObject(tipodocdgt);
		setDirty();
		setOnDB(false);
	}

	public TipoDocumentoDigitale getTipodocdgt() {
		return (TipoDocumentoDigitale) iTipodocdgt.getObject();
	}

	public void setTipodocdgtKey(String key) {
		iTipodocdgt.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		setOnDB(false);
	}

	public String getTipodocdgtKey() {
		return iTipodocdgt.getKey();
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

	public void setIdTipoDocumentoDigitale(String idTipoDocumentoDigitale) {
		String key = iTipodocdgt.getKey();
		iTipodocdgt.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idTipoDocumentoDigitale));
		setDirty();
		setOnDB(false);
	}

	public String getIdTipoDocumentoDigitale() {
		String key = iTipodocdgt.getKey();
		String objIdTipoDocumentoDigitale = KeyHelper.getTokenObjectKey(key, 2);
		return objIdTipoDocumentoDigitale;
	}

	public void setClasse(PersistentClassHdr classe) {
		this.iClasse.setObject(classe);
		setDirty();
		setOnDB(false);
	}

	public PersistentClassHdr getClasse() {
		return (PersistentClassHdr) iClasse.getObject();
	}

	public void setClasseKey(String key) {
		iClasse.setKey(key);
		setDirty();
		setOnDB(false);
	}

	public String getClasseKey() {
		return iClasse.getKey();
	}

	public void setIdClassHdr(String idClassHdr) {
		iClasse.setKey(idClassHdr);
		setDirty();
		setOnDB(false);
	}

	public String getIdClassHdr() {
		String key = iClasse.getKey();
		return key;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		AssocHdrTpDocDgtPO assocHdrTpDocDgtPO = (AssocHdrTpDocDgtPO) obj;
		iTipodocdgt.setEqual(assocHdrTpDocDgtPO.iTipodocdgt);
		iClasse.setEqual(assocHdrTpDocDgtPO.iClasse);
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
		setIdClassHdr(KeyHelper.getTokenObjectKey(key, 2));
		setIdTipoDocumentoDigitale(KeyHelper.getTokenObjectKey(key, 3));
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		String idClassHdr = getIdClassHdr();
		String idTipoDocumentoDigitale = getIdTipoDocumentoDigitale();
		Object[] keyParts = { idAzienda, idClassHdr, idTipoDocumentoDigitale };
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return AssocHdrTpDocDgtTM.getInstance();
	}

	protected void setIdAziendaInternal(String idAzienda) {
		iAzienda.setKey(idAzienda);
		String key2 = iTipodocdgt.getKey();
		iTipodocdgt.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
	}

}
