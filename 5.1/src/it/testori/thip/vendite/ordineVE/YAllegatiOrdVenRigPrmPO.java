package it.testori.thip.vendite.ordineVE;

import java.sql.SQLException;
import java.util.Vector;

import com.thera.thermfw.common.BaseComponentsCollection;
import com.thera.thermfw.common.BusinessObject;
import com.thera.thermfw.common.Deletable;
import com.thera.thermfw.persist.Child;
import com.thera.thermfw.persist.CopyException;
import com.thera.thermfw.persist.Copyable;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.persist.Proxy;
import com.thera.thermfw.persist.TableManager;
import com.thera.thermfw.security.Authorizable;
import com.thera.thermfw.security.Conflictable;

import it.testori.thip.base.generale.AssocHdrTpDocDgt;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.cs.EntitaAzienda;
import it.thera.thip.vendite.ordineVE.OrdineVenditaRigaPrm;

public abstract class YAllegatiOrdVenRigPrmPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable, Child {

	private static YAllegatiOrdVenRigPrm cInstance;

	protected char iVincolo = '0';

	protected Integer iDettaglioRigaOrdine = new Integer("0");

	protected Proxy iOrdinevenditarigaprm = new Proxy(it.thera.thip.vendite.ordineVE.OrdineVenditaRigaPrm.class);

	protected Proxy iAssociazionedocumento = new Proxy(it.testori.thip.base.generale.AssocHdrTpDocDgt.class);

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (YAllegatiOrdVenRigPrm) Factory.createObject(YAllegatiOrdVenRigPrm.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static YAllegatiOrdVenRigPrm elementWithKey(String key, int lockType) throws SQLException {
		return (YAllegatiOrdVenRigPrm) PersistentObject.elementWithKey(YAllegatiOrdVenRigPrm.class, key, lockType);
	}

	public YAllegatiOrdVenRigPrmPO() {
		setVincolo('0');
		setDettaglioRigaOrdine(new Integer(0));
		setIdClasse("OrdineVenditaRigaPrm");
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setVincolo(char vincolo) {
		this.iVincolo = vincolo;
		setDirty();
	}

	public char getVincolo() {
		return iVincolo;
	}

	public void setDettaglioRigaOrdine(Integer dettaglioRigaOrdine) {
		this.iDettaglioRigaOrdine = dettaglioRigaOrdine;
		setDirty();
	}

	public Integer getDettaglioRigaOrdine() {
		return iDettaglioRigaOrdine;
	}

	public void setOrdinevenditarigaprm(OrdineVenditaRigaPrm ordinevenditarigaprm) {
		String idAzienda = getIdAzienda();
		if (ordinevenditarigaprm != null) {
			idAzienda = KeyHelper.getTokenObjectKey(ordinevenditarigaprm.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iOrdinevenditarigaprm.setObject(ordinevenditarigaprm);
		setDirty();
		setOnDB(false);
	}

	public OrdineVenditaRigaPrm getOrdinevenditarigaprm() {
		return (OrdineVenditaRigaPrm) iOrdinevenditarigaprm.getObject();
	}

	public void setOrdinevenditarigaprmKey(String key) {
		iOrdinevenditarigaprm.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		setOnDB(false);
	}

	public String getOrdinevenditarigaprmKey() {
		return iOrdinevenditarigaprm.getKey();
	}

	public void setAnnoDocumento(String annoDocumento) {
		String key = iOrdinevenditarigaprm.getKey();
		iOrdinevenditarigaprm.setKey(KeyHelper.replaceTokenObjectKey(key, 2, annoDocumento));
		setDirty();
		setOnDB(false);
	}

	public String getAnnoDocumento() {
		String key = iOrdinevenditarigaprm.getKey();
		String objAnnoDocumento = KeyHelper.getTokenObjectKey(key, 2);
		return objAnnoDocumento;

	}

	public void setNumeroDocumento(String numeroDocumento) {
		String key = iOrdinevenditarigaprm.getKey();
		iOrdinevenditarigaprm.setKey(KeyHelper.replaceTokenObjectKey(key, 3, numeroDocumento));
		setDirty();
		setOnDB(false);
	}

	public String getNumeroDocumento() {
		String key = iOrdinevenditarigaprm.getKey();
		String objNumeroDocumento = KeyHelper.getTokenObjectKey(key, 3);
		return objNumeroDocumento;

	}

	public void setNumeroRigaDocumento(Integer numeroRigaDocumento) {
		String key = iOrdinevenditarigaprm.getKey();
		iOrdinevenditarigaprm.setKey(KeyHelper.replaceTokenObjectKey(key, 4, numeroRigaDocumento));
		setDirty();
		setOnDB(false);
	}

	public Integer getNumeroRigaDocumento() {
		String key = iOrdinevenditarigaprm.getKey();
		String objNumeroRigaDocumento = KeyHelper.getTokenObjectKey(key, 4);
		return KeyHelper.stringToIntegerObj(objNumeroRigaDocumento);
	}

	public void setAssociazionedocumento(AssocHdrTpDocDgt associazionedocumento) {
		String idAzienda = getIdAzienda();
		if (associazionedocumento != null) {
			idAzienda = KeyHelper.getTokenObjectKey(associazionedocumento.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iAssociazionedocumento.setObject(associazionedocumento);
		setDirty();
		setOnDB(false);
	}

	public AssocHdrTpDocDgt getAssociazionedocumento() {
		return (AssocHdrTpDocDgt) iAssociazionedocumento.getObject();
	}

	public void setAssociazionedocumentoKey(String key) {
		iAssociazionedocumento.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		setOnDB(false);
	}

	public String getAssociazionedocumentoKey() {
		return iAssociazionedocumento.getKey();
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

	public void setIdClasse(String idClasse) {
		String key = iAssociazionedocumento.getKey();
		iAssociazionedocumento.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idClasse));
		setDirty();
		setOnDB(false);
	}

	public String getIdClasse() {
		String key = iAssociazionedocumento.getKey();
		String objIdClasse = KeyHelper.getTokenObjectKey(key, 2);
		return objIdClasse;

	}

	public void setIdTipoDocDgt(String idTipoDocDgt) {
		String key = iAssociazionedocumento.getKey();
		iAssociazionedocumento.setKey(KeyHelper.replaceTokenObjectKey(key, 3, idTipoDocDgt));
		setDirty();
		setOnDB(false);
	}

	public String getIdTipoDocDgt() {
		String key = iAssociazionedocumento.getKey();
		String objIdTipoDocDgt = KeyHelper.getTokenObjectKey(key, 3);
		return objIdTipoDocDgt;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		YAllegatiOrdVenRigPrmPO yAllegatiOrdVenRigPrmPO = (YAllegatiOrdVenRigPrmPO) obj;
		iOrdinevenditarigaprm.setEqual(yAllegatiOrdVenRigPrmPO.iOrdinevenditarigaprm);
		iAssociazionedocumento.setEqual(yAllegatiOrdVenRigPrmPO.iAssociazionedocumento);
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
		setAnnoDocumento(KeyHelper.getTokenObjectKey(key, 2));
		setNumeroDocumento(KeyHelper.getTokenObjectKey(key, 3));
		setNumeroRigaDocumento(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 4)));
		setIdClasse(KeyHelper.getTokenObjectKey(key, 5));
		setIdTipoDocDgt(KeyHelper.getTokenObjectKey(key, 6));
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		String annoDocumento = getAnnoDocumento();
		String numeroDocumento = getNumeroDocumento();
		Integer numeroRigaDocumento = getNumeroRigaDocumento();
		String idClasse = getIdClasse();
		String idTipoDocDgt = getIdTipoDocDgt();
		Object[] keyParts = { idAzienda, annoDocumento, numeroDocumento, numeroRigaDocumento, idClasse, idTipoDocDgt };
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return YAllegatiOrdVenRigPrmTM.getInstance();
	}

	protected void setIdAziendaInternal(String idAzienda) {
		iAzienda.setKey(idAzienda);
		String key2 = iOrdinevenditarigaprm.getKey();
		iOrdinevenditarigaprm.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
		String key3 = iAssociazionedocumento.getKey();
		iAssociazionedocumento.setKey(KeyHelper.replaceTokenObjectKey(key3, 1, idAzienda));
	}

}
