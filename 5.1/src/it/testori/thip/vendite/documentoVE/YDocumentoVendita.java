package it.testori.thip.vendite.documentoVE;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.*;
import it.thera.thip.vendite.documentoVE.*;
import it.thera.thip.vendite.generaleVE.CausaleRigaDocVen;
import it.testori.thip.base.cliente.YClienteFinale;
import it.testori.thip.produzione.rifornLinea.YPianoRifornimento;
import it.testori.thip.produzione.rifornLinea.YPsnDatiRifornimentoLinea;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.generale.CfgLogTxMov;
import it.thera.thip.base.generale.CfgLogTxMovTM;
import it.thera.thip.base.generale.ImportoInValutaEstera;
import it.thera.thip.base.generale.IntegrazioneThipLogis;
import it.thera.thip.cs.ThipException;
import it.thera.thip.logis.fis.CambioUbicazioneUds;
import it.thera.thip.logis.fis.RigaUds;
import it.thera.thip.logis.fis.RigaUdsTM;
import it.thera.thip.logis.fis.TestataUds;
import it.thera.thip.logis.lgb.RigaLista;
import it.thera.thip.logis.lgb.TestataLista;
import it.thera.thip.magazzino.movimenti.CausaleMovVendita;
import it.thera.thip.produzione.rifornLinea.PianoRifornimento;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 13/10/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72166    13/10/2025  DSSOF3   Prima stesura
 * 72191	04/11/2025	DSSOF3   Gestione piano rifornimento di linea
 * 72226	25/11/2025	DSSOF3	 Aggiunto metodo
 * 72273	08/01/2025	DSSOF3   Aggiunto flag per capire se sono in salvataggio da chiusura lista
 */

public class YDocumentoVendita extends DocumentoVendita {

	//72273
	private static final String STMT_UPDATE_VLR_TOT_DOC_VA = "UPDATE "+YDocumentoVenditaTM.TABLE_NAME_EXT+"  "
			+ "SET "+YDocumentoVenditaTM.YVLR_TOT_DOC_VA+" = ? "
			+ "WHERE "+YDocumentoVenditaTM.ID_AZIENDA+" = ? "
			+ "AND "+YDocumentoVenditaTM.ID_ANNO_DOC+" = ? "
			+ "AND "+YDocumentoVenditaTM.ID_NUMERO_DOC+" = ? ";
	public static CachedStatement cUpdateValoreDocVA = new CachedStatement(STMT_UPDATE_VLR_TOT_DOC_VA);
	//72273

	protected Proxy iClienteFinale = new Proxy(YClienteFinale.class);

	protected Proxy iPianoRifornimento = new Proxy(it.thera.thip.produzione.rifornLinea.PianoRifornimento.class);

	protected OneToMany iRigheUdsDocumento = new OneToMany(YUdsOfDocVen.class, this, 7, false);

	protected boolean iSalvataggioDaTtlLogis = false; //72273
	protected BigDecimal iYValoreTotDocVA; //72273

	public YDocumentoVendita() {
		super();
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setClienteFinale(YClienteFinale Cliente){
		this.iClienteFinale.setObject(Cliente);
		setDirty();
	}

	public YClienteFinale getClienteFinale(){
		return (YClienteFinale) iClienteFinale.getObject();
	}

	public void setClienteFinaleKey(String key){
		iClienteFinale.setKey(key);
		setDirty();
	}

	public String getClienteFinaleKey(){
		return iClienteFinale.getKey();
	}

	public void setIdCodiceClienteFinale(Integer rCliente){
		String key = iClienteFinale.getKey();
		iClienteFinale.setKey(KeyHelper.replaceTokenObjectKey(key, 3, rCliente));
		setDirty();
	}

	public Integer getIdCodiceClienteFinale(){
		String key = iClienteFinale.getKey();
		Integer objRCliente = KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 3));
		return objRCliente;
	}

	public void setPianoRifornimento(PianoRifornimento Cliente){
		this.iPianoRifornimento.setObject(Cliente);
		setDirty();
	}

	public PianoRifornimento getPianoRifornimento(){
		return (PianoRifornimento) iPianoRifornimento.getObject();
	}

	public void setPianoRifornimentoKey(String key){
		iPianoRifornimento.setKey(key);
		setDirty();
	}

	public String getPianoRifornimentoKey(){
		return iPianoRifornimento.getKey();
	}

	public void setAnnoPianoRfr(String anno){
		String key = iPianoRifornimento.getKey();
		iPianoRifornimento.setKey(KeyHelper.replaceTokenObjectKey(key, 2, anno));
		setDirty();
	}

	public String getAnnoPianoRfr(){
		String key = iPianoRifornimento.getKey();
		String objAnno = KeyHelper.getTokenObjectKey(key, 2);
		return objAnno;
	}

	public void setIdNumeroPianoRfr(String IdNumeroPianoRfr){
		String key = iPianoRifornimento.getKey();
		iPianoRifornimento.setKey(KeyHelper.replaceTokenObjectKey(key, 3, IdNumeroPianoRfr));
		setDirty();
	}

	public String getIdNumeroPianoRfr(){
		String key = iPianoRifornimento.getKey();
		String objIdNumeroPianoRfr = KeyHelper.getTokenObjectKey(key, 3);
		return objIdNumeroPianoRfr;
	}

	@Override
	public void setIdCliente(String rCliente) {
		super.setIdCliente(rCliente);
		String key = iClienteFinale.getKey();
		iClienteFinale.setKey(KeyHelper.replaceTokenObjectKey(key, 2, rCliente));
	}

	@Override
	public void setIdAzienda(String idAzienda) {
		super.setIdAzienda(idAzienda);
		if(iClienteFinale != null) {
			iClienteFinale.setKey(KeyHelper.replaceTokenObjectKey(iClienteFinale.getKey(), 1, idAzienda));
			iPianoRifornimento.setKey(KeyHelper.replaceTokenObjectKey(iPianoRifornimento.getKey(), 1, idAzienda));
		}
		if (iRigheUdsDocumento != null) {
			iRigheUdsDocumento.setFatherKeyChanged();
		}
	}

	@Override
	public void setAnnoDocumento(String AnnoDocumento) {
		super.setAnnoDocumento(AnnoDocumento);
		if (iRigheUdsDocumento != null) {
			iRigheUdsDocumento.setFatherKeyChanged();
		}
	}

	@Override
	public void setNumeroDocumento(String NumeroDocumento) {
		super.setNumeroDocumento(NumeroDocumento);
		if (iRigheUdsDocumento != null) {
			iRigheUdsDocumento.setFatherKeyChanged();
		}
	}

	@Override
	public boolean initializeOwnedObjects(boolean result) {
		result = super.initializeOwnedObjects(result);
		result = iRigheUdsDocumento.initialize(result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public List getRigheUdsDocumento() {
		return getRigheUdsDocumentoInternal();
	}

	protected OneToMany getRigheUdsDocumentoInternal() {
		if (iRigheUdsDocumento.isNew()) {
			iRigheUdsDocumento.retrieve();
		}
		return iRigheUdsDocumento;
	}

	//72273
	public boolean isSalvataggioDaTtlLogis() {
		return iSalvataggioDaTtlLogis;
	}

	public void setSalvataggioDaTtlLogis(boolean iSalavataggioDaTtlLogis) {
		this.iSalvataggioDaTtlLogis = iSalavataggioDaTtlLogis;
	}

	public BigDecimal getYValoreTotDocVA() {
		return iYValoreTotDocVA;
	}

	public void setYValoreTotDocVA(BigDecimal iYValoreTotDocVA) {
		this.iYValoreTotDocVA = iYValoreTotDocVA;
		setDirty();
	}
	//72273

	@Override
	public int save() throws SQLException {
		int rc = super.save();
		if(rc > 0 && isOnDB()) {
			//72273
			BigDecimal valoreDocumento = getValoreDocumentoInValAz();
			if(valoreDocumento != null) {
				aggiornaValoreTotDocVA(valoreDocumento);
			}
			//72273
		}
		return rc;
	}

	//72273
	public BigDecimal getValoreDocumentoInValAz(){
		ImportoInValutaEstera imp = null;
		if (imp==null)
			imp = new ImportoInValutaEstera();

		imp.setFattCambioOper(getCambio());
		if (getIdValuta()!=null && getTotaleDocumento()!=null && getDataDocumento()!=null){
			imp.convertiEstPrim(getIdValuta(), getValoreDocumento(), getDataDocumento());
			if (imp.getImportaValPrim()!=null){
				return imp.getImportaValPrim();
			}
			else{
				return new BigDecimal(0);
			}
		}
		else
			return new BigDecimal(0);
	}

	protected int aggiornaValoreTotDocVA(BigDecimal val) throws SQLException {
		PreparedStatement ps = cUpdateValoreDocVA.getStatement();
		Database db = ConnectionManager.getCurrentDatabase();
		ps.setBigDecimal(1, val);
		db.setString(ps, 2, getIdAzienda());
		db.setString(ps, 3, getAnnoDocumento());
		db.setString(ps, 4, getNumeroDocumento());
		if (ps.executeUpdate() >= 0)
			return 1;
		return -100;
	}
	//72273

	@Override
	public int saveOwnedObjects(int rc) throws SQLException {
		rc = super.saveOwnedObjects(rc);
		if(rc < 0) {
			return rc;
		}else {
			rc += iRigheUdsDocumento.save(rc);
		}
		return rc;
	}

	@Override
	public int deleteOwnedObjects() throws SQLException {
		int rc =  super.deleteOwnedObjects();
		if(rc < 0) {
			return rc;
		}else {
			rc = getRigheUdsDocumentoInternal().delete();
		}
		return rc;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ErrorMessage convalida(int rc) {
		ErrorMessage error = super.convalida(rc);
		YPianoRifornimento pianoRif = (YPianoRifornimento) getPianoRifornimento();
		if(error == null && pianoRif != null) {
			error = forzaChiusuraPianoRifornimento();
		}
		if(error == null && isSerieCausaleTrasferimentoUdsTraMagazzini()) {
			Vector errors = movimentoUds(true, false);
			if(!errors.isEmpty()) {
				error = (ErrorMessage) errors.get(0);
				rc = -1;
			}
		}
		return error;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List regressione(int rc) {
		List errors = super.regressione(rc);
		if((errors == null || errors.isEmpty()) && isSerieCausaleTrasferimentoUdsTraMagazzini()){ 
			errors = movimentoUds(false, true);
			rc = -1;
		}
		return errors;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Vector movimentoUds(boolean convalida, boolean regressione) {
		Vector errors = new Vector();
		if(getRigheUdsDocumento().size() > 0) {
			Iterator<YUdsOfDocVen> righeUdsDv = getRigheUdsDocumento().iterator();
			while(righeUdsDv.hasNext()) {
				YUdsOfDocVen rigaUdsOfDv = righeUdsDv.next();
				TestataUds tu = rigaUdsOfDv.getTestataUds();
				CambioUbicazioneUds cuds = (CambioUbicazioneUds) Factory.createObject(CambioUbicazioneUds.class);
				cuds.setOperazioneSpostamento(YPsnDatiRifornimentoLinea.getCurrentYPsnDatiRifornimentoLinea().getOperazioneSpostamento());
				cuds.setUds(tu);
				cuds.setUbicazioneSorg(tu.getUbicazione());
				//..determino ora ubicazione destinazione
				CausaleRigaDocVen cauRigDef = getCausale().getCausaleRigaDocumVen();
				Vector elencoFiltri = new Vector();
				String where = null;
				if(convalida) {
					CausaleMovVendita cau = cauRigDef.getCauMagTrasferim();
					where = CfgLogTxMovTM.ID_AZIENDA + "='" + getIdAzienda() + "' AND " +
							CfgLogTxMovTM.ID_CAU_MOV_MAG + "='" + cau.getCodiceCausale() +"' AND " +
							CfgLogTxMovTM.ABILITATO + "='Y'";
				}else if(regressione) {
					CausaleMovVendita cau = cauRigDef.getCauMagazzino();
					where = CfgLogTxMovTM.ID_AZIENDA + "='" + getIdAzienda() + "' AND " +
							CfgLogTxMovTM.ID_CAU_MOV_MAG + "='" + cau.getCodiceCausale() +"' AND " +
							CfgLogTxMovTM.ABILITATO + "='Y'";
				}else {
					//.grave non consentita
					errors.add(new ErrorMessage("BAS0000078","Operazione non consentita"));
				}
				try {
					elencoFiltri = CfgLogTxMov.retrieveList(where,"",false);
					if(elencoFiltri.size() > 0) {
						CfgLogTxMov filtroMovMagaz = (CfgLogTxMov) elencoFiltri.get(0);
						if(filtroMovMagaz.getOperazioneLogisSenzaUDC() != null) {
							cuds.setUbicazioneDest(filtroMovMagaz.getOperazioneLogisSenzaUDC().getUbicazioneDefault());
						}else {
							errors.add(new ErrorMessage("BAS0000078","CfgLogTxMov : "+getKey()+" operazione logis senza UDC null"));
						}
					}else {
						errors.add( new ErrorMessage("BAS0000078","Operazione articolo non trovata"));
					}
					if(errors.isEmpty())
						cuds.getUds().setUbicazione(cuds.getUbicazioneDest());
					errors = cuds.fineNoDB();
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| SQLException e) {
					e.printStackTrace(Trace.excStream);
				}
			}
		}
		return errors;
	}

	public boolean isSerieCausaleTrasferimentoUdsTraMagazzini() {
		YPsnDatiRifornimentoLinea psnDati = YPsnDatiRifornimentoLinea.getCurrentYPsnDatiRifornimentoLinea();
		if(psnDati != null
				&& getIdCau().equals(psnDati.getIdCausaleDocVenTrasfUds())) {
			return true;
		}
		return false;
	}

	public boolean isSerieCausaleTrasferimentoUdcTraMagazzini() {
		YPsnDatiRifornimentoLinea psnDati = YPsnDatiRifornimentoLinea.getCurrentYPsnDatiRifornimentoLinea();
		if(psnDati != null
				&& getIdCau().equals(psnDati.getIdCausaleDocVenTrasfUdc())) {
			return true;
		}
		return false;
	}

	//72226
	public boolean isSerieCausaleTrasferimentoPianoRifornimento() {
		YPsnDatiRifornimentoLinea psnDati = YPsnDatiRifornimentoLinea.getCurrentYPsnDatiRifornimentoLinea();
		if(psnDati != null
				&& getIdCau().equals(psnDati.getIdCausaleDocVen())) {
			return true;
		}
		return false;
	}
	//72226

	/**
	 * Forzo la chiusura del piano rifornimento su connessione separata
	 * @return
	 */
	protected ErrorMessage forzaChiusuraPianoRifornimento() {
		ErrorMessage em = null;
		try {
			YPianoRifornimento pianoRfr = (YPianoRifornimento) getPianoRifornimento();
			if(pianoRfr != null) {
				pianoRfr.setChiusuraForzata(true);
				pianoRfr.setStatoPiano(PianoRifornimento.CONFERMATO);
				pianoRfr.save();
			}
		}catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return em;
	}

	@Override
	public int delete() throws SQLException {
		if(getPianoRifornimento() != null) {
			cancellaPianoRifornimento();
		}
		int rcDel = super.delete();
		return rcDel;
	}

	protected int cancellaPianoRifornimento() throws ThipException {
		BODataCollector boDC = (BODataCollector) Factory.createObject(BODataCollector.class);
		boDC.initialize("PianoRifornimento");
		((YPianoRifornimento)getPianoRifornimento()).setFlag01(true); //..Accendo cosi di la non do l'errore
		boDC.setBo(getPianoRifornimento());
		boDC.setAutoCommit(false);
		int rc = boDC.checkDelete();
		if(rc != BODataCollector.OK) {
			throw new ThipException(boDC.getErrorList().getErrors());
		}else {
			try {
				rc = getPianoRifornimento().delete();
			} catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return rc;
	}

	public TestataLista testataListaLogis(int lockType) {
		TestataLista tl = null;
		String AnnoDocumento="";
		String NumeroDocumento="";
		if (getAnnoDocumento() != null &&
				!getAnnoDocumento().equals("")) {
			AnnoDocumento = getAnnoDocumento();
			if (AnnoDocumento.length() < 4) {
				for(int i=AnnoDocumento.length(); i < 4; i++)
					AnnoDocumento =" "+AnnoDocumento;
			}
		}
		if(getNumeroDocumento() != null && !getNumeroDocumento().equals("")) {
			NumeroDocumento = getNumeroDocumento();
		}
		AnnoDocumento = AnnoDocumento.trim();
		try {
			tl = (TestataLista) TestataLista.elementWithKey(TestataLista.class, KeyHelper.buildObjectKey(new String[] {
					getIdAzienda(),
					(IntegrazioneThipLogis.VENDITA + AnnoDocumento + NumeroDocumento)
			}), lockType);
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return tl;
	}

	@SuppressWarnings("rawtypes")
	public RigaLista rigaListaNumRigaHost(TestataLista tl,Integer numRigaHost) {
		if(tl != null) {
			Iterator iterRls = tl.getRigheLista().iterator();
			while(iterRls.hasNext()){
				RigaLista rl = (RigaLista) iterRls.next();
				if(rl.getNumeroRigaHost().compareTo(numRigaHost) == 0) {
					return rl;
				}
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Vector righeUdsRigaLista(RigaLista rl) {
		Vector righeUds = new Vector();
		String where = " "+RigaUdsTM.COD_SOCIETA+" = '"+rl.getCodiceSocieta()+"' AND "+RigaUdsTM.COD_TESTATA_LISTA+" = '"+rl.getCodiceTestataLista()+"' ";
		where += "AND "+RigaUdsTM.COD_RIGA_LISTA+" = '"+rl.getCodice()+"' ";
		try {
			righeUds = RigaUds.retrieveList(RigaUds.class, where, "", false);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return righeUds;
	}

	@Override
	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		YDocumentoVendita ordineVendita = (YDocumentoVendita) obj;
		iClienteFinale.setEqual(ordineVendita.iClienteFinale);
	}

}