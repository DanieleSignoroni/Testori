package it.testori.thip.vendite.documentoVE;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.CachedStatement;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.CopyException;
import com.thera.thermfw.persist.Copyable;
import com.thera.thermfw.persist.Database;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.Proxy;

import it.testori.thip.base.articolo.YClasseC;
import it.testori.thip.base.generale.VincoloAssociazioneDocOrdVenRig;
import it.testori.thip.produzione.rifornLinea.YPsnDatiRifornimentoLinea;
import it.testori.thip.vendite.ordineVE.YOrdineVenditaRigaPrm;
import it.thera.thip.base.articolo.ClasseD;
//import it.testori.thip.logis.bas.YCostantiTestori;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.comuniVenAcq.AzioneMagazzino;
import it.thera.thip.base.comuniVenAcq.DocumentoRigaLotto;
import it.thera.thip.base.comuniVenAcq.QuantitaInUMRif;
import it.thera.thip.base.documenti.StatoAvanzamento;
//import it.thera.thip.base.generale.UnitaMisura;
import it.thera.thip.cs.ThipException;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.produzione.rifornLinea.PianoRifornimentoRiga;
import it.thera.thip.vendite.documentoVE.DocumentoVenRigaPrm;
import it.thera.thip.vendite.documentoVE.FatturaVenditaSTAT;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 21/10/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72178    21/10/2025  DSSOF3   Prima stesura
 * 72191	04/11/2025	DSSOF3   Gestione piano rifornimento di linea
 * 72242	05/12/2025	DSSOF3	 Area applicativa in stat fat ven
 * 72247	16/12/2025	DSSOF3	 Gestione obbligatorieta' allegati per spedizione
 * 72273	08/01/2026	DSSOF3	 Aggiunta valore totale ordinato in valuta a db
 */

public class YDocumentoVenRigaPrm extends DocumentoVenRigaPrm {

	//72273
	private static final String STMT_UPDATE_VLR_RIGA_VA = "UPDATE "+YDocumentoVenRigaPrmTM.TABLE_NAME_EXT+"  "
			+ "SET "+YDocumentoVenRigaPrmTM.YVLR_RIGA_VA+" = ? "
			+ "WHERE "+YDocumentoVenRigaPrmTM.ID_AZIENDA+" = ? "
			+ "AND "+YDocumentoVenRigaPrmTM.ID_ANNO_DOC+" = ? "
			+ "AND "+YDocumentoVenRigaPrmTM.ID_NUMERO_DOC+" = ? "
			+ "AND "+YDocumentoVenRigaPrmTM.ID_RIGA_DOC+" = ?";
	public static CachedStatement cUpdateValoreRigaVA = new CachedStatement(STMT_UPDATE_VLR_RIGA_VA);
	//72273

	protected Proxy iPianoRifornimentoRiga = new Proxy(it.thera.thip.produzione.rifornLinea.PianoRifornimentoRiga.class);

	protected String iAltezzaRichiesta;
	protected String iLunghezzaRichiesta;

	protected Proxy iAreaApplicativa = new Proxy(it.thera.thip.base.articolo.ClasseD.class);

	protected BigDecimal iYValoreRigaVA; //72273

	public YDocumentoVenRigaPrm() {
		super();
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setPianoRifornimentoRiga(PianoRifornimentoRiga pianorifornimentoriga) {
		String oldObjectKey = getKey();
		this.iPianoRifornimentoRiga.setObject(pianorifornimentoriga);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public PianoRifornimentoRiga getPianoRifornimentoRiga() {
		return (PianoRifornimentoRiga)iPianoRifornimentoRiga.getObject();
	}

	public void setPianoRifornimentoRigaKey(String key) {
		String oldObjectKey = getKey();
		iPianoRifornimentoRiga.setKey(key);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getPianoRifornimentoRigaKey() {
		return iPianoRifornimentoRiga.getKey();
	}

	public void setIdAzienda(String idAzienda) {
		super.setIdAzienda(idAzienda);
		if(iPianoRifornimentoRiga != null) {
			String key = iPianoRifornimentoRiga.getKey();
			iPianoRifornimentoRiga.setKey(KeyHelper.replaceTokenObjectKey(key , 1, idAzienda));
		}
		if(iAreaApplicativa != null) {
			String key = iAreaApplicativa.getKey();
			iAreaApplicativa.setKey(KeyHelper.replaceTokenObjectKey(key, 1, idAzienda));
		}
	}

	public void setAnnoPianoRfr(String annoPianoRfr) {
		String key = iPianoRifornimentoRiga.getKey();
		iPianoRifornimentoRiga.setKey(KeyHelper.replaceTokenObjectKey(key , 2, annoPianoRfr));
		setDirty();
	}

	public String getAnnoPianoRfr() {
		String key = iPianoRifornimentoRiga.getKey();
		String objAnnoPianoRfr = KeyHelper.getTokenObjectKey(key,2);
		return objAnnoPianoRfr;

	}

	public void setIdNumeroPianoRfr(String idNumeroPianoRfr) {
		String key = iPianoRifornimentoRiga.getKey();
		iPianoRifornimentoRiga.setKey(KeyHelper.replaceTokenObjectKey(key , 3, idNumeroPianoRfr));
		setDirty();
	}

	public String getIdNumeroPianoRfr() {
		String key = iPianoRifornimentoRiga.getKey();
		String objIdNumeroPianoRfr = KeyHelper.getTokenObjectKey(key,3);
		return objIdNumeroPianoRfr;

	}

	public void setIdRigaPianoRfr(Integer idRigaPianoRfr) {
		String key = iPianoRifornimentoRiga.getKey();
		iPianoRifornimentoRiga.setKey(KeyHelper.replaceTokenObjectKey(key , 4, idRigaPianoRfr));
		setDirty();
	}

	public Integer getIdRigaPianoRfr() {
		String key = iPianoRifornimentoRiga.getKey();
		String objIdRigaPianoRfr = KeyHelper.getTokenObjectKey(key,4);
		return KeyHelper.stringToIntegerObj(objIdRigaPianoRfr);
	}

	public String getAltezzaRichiesta() {
		return iAltezzaRichiesta;
	}

	public void setAltezzaRichiesta(String iAltezzaRichiesta) {
		this.iAltezzaRichiesta = iAltezzaRichiesta;
		setDirty();
	}

	public String getLunghezzaRichiesta() {
		return iLunghezzaRichiesta;
	}

	public void setLunghezzaRichiesta(String iLunghezzaRichiesta) {
		this.iLunghezzaRichiesta = iLunghezzaRichiesta;
		setDirty();
	}

	public void setAreaApplicativa(ClasseD classeD) {
		this.iAreaApplicativa.setObject(classeD);
		setDirty();
	}

	public ClasseD getAreaApplicativa() {
		return (ClasseD)iAreaApplicativa.getObject();
	}

	public void setAreaApplicativaKey(String key) {
		iAreaApplicativa.setKey(key);
		setDirty();
	}

	public String getAreaApplicativaKey() {
		return iAreaApplicativa.getKey();
	}

	public void setIdAreaApplicativa(String idAreaApplicativa) {
		iAreaApplicativa.setKey(KeyHelper.replaceTokenObjectKey(iAreaApplicativa.getKey(),2,idAreaApplicativa));
		setDirty();
	}

	public String getIdAreaApplicativa() {
		return KeyHelper.getTokenObjectKey(iAreaApplicativa.getKey(),2);
	}

	//72273
	public BigDecimal getYValoreRigaVA() {
		return iYValoreRigaVA;
	}

	public void setYValoreRigaVA(BigDecimal iYValoreRigaVA) {
		this.iYValoreRigaVA = iYValoreRigaVA;
		setDirty();
	}
	//72273

	/**
	 * Gestione testori --> naz. orig pref presa dal codice master (ovvero classe C)
	 */
	@Override
	public void ricalcolaOriginePreferenziale() {
		super.ricalcolaOriginePreferenziale();
		if(getIdNazioneOrgPref() == null 
				&& getArticolo().getClasseC() != null
				&& ((YClasseC)getArticolo().getClasseC()).getIdNazioneOrgPref() != null) {
			setIdNazioneOrgPref(((YClasseC)getArticolo().getClasseC()).getIdNazioneOrgPref());
		}
	}

	//72247
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List convalida(List errorList) {
		List errors = super.convalida(errorList);
		if(isControlloObbligatorietaAllegatiAttivo()) {
			ErrorMessage em = ((YOrdineVenditaRigaPrm)getRigaOrdine()).checkObbligatorietaAllegati(VincoloAssociazioneDocOrdVenRig.OBBLIGATORIO_PER_SPEDIZIONE);
			if(em != null)
				errors.add(em);
		}
		return errors;
	}

	public boolean isControlloObbligatorietaAllegatiAttivo() {
		YDocumentoVendita testata = (YDocumentoVendita)getTestata();
		if(testata.getCausale().getAzioneMagazzino() != AzioneMagazzino.USCITA) {
			return false;
		}
		if(getRigaOrdine() == null) {
			return false;
		}
		if((YPsnDatiRifornimentoLinea.getCurrentYPsnDatiRifornimentoLinea() != null 
				&& testata.getIdCliente().equals(YPsnDatiRifornimentoLinea.getCurrentYPsnDatiRifornimentoLinea().getIdClienteIntestatario()))){
			return false;
		}
		if(testata.isSerieCausaleTrasferimentoPianoRifornimento()
				|| testata.isSerieCausaleTrasferimentoUdcTraMagazzini()
				|| testata.isSerieCausaleTrasferimentoUdsTraMagazzini()) {
			return false;
		}
		return true;
	}
	//72247

	@Override
	public int delete() throws SQLException {
		if(getPianoRifornimentoRiga() != null) {
			cancellaRigaPianoRifornimento();
		}
		int rcDel = super.delete();
		return rcDel;
	}

	@Override
	public int save() throws SQLException {
		if(!isOnDB() && getIdAreaApplicativa() == null) {
			setIdAreaApplicativa(getArticolo().getIdClasseD());
		}
		int rc = super.save();
		if(rc > 0 && isOnDB()) {
			//72273
			BigDecimal valoreRigaVA = getValoreRigaVA();
			if(valoreRigaVA != null) {
				aggiornaValoreRigaVA(valoreRigaVA);
			}
			//72273
		}
		return rc;
	}

	//72273
	protected int aggiornaValoreRigaVA(BigDecimal val) throws SQLException {
		PreparedStatement ps = cUpdateValoreRigaVA.getStatement();
		Database db = ConnectionManager.getCurrentDatabase();
		ps.setBigDecimal(1, val);
		db.setString(ps, 2, getIdAzienda());
		db.setString(ps, 3, getAnnoDocumento());
		db.setString(ps, 4, getNumeroDocumento());
		db.setString(ps, 5, getNumeroRigaDocumento().toString());
		if (ps.executeUpdate() >= 0)
			return 1;
		return -100;
	}
	//72273

	@Override
	protected void impostaDatiPersRigaStat(FatturaVenditaSTAT rigaStat) {
		super.impostaDatiPersRigaStat(rigaStat);
		if(getIdAreaApplicativa() != null) //72242
			rigaStat.setRClasseD(getIdAreaApplicativa());
	}

	protected int cancellaRigaPianoRifornimento() throws ThipException {
		BODataCollector boDC = (BODataCollector) Factory.createObject(BODataCollector.class);
		boDC.initialize("PianoRifornimentoRiga");
		boDC.setBo(getPianoRifornimentoRiga());
		boDC.setAutoCommit(false);
		int rc = boDC.delete(getPianoRifornimentoRigaKey());
		if(rc != BODataCollector.OK) {
			throw new ThipException(boDC.getErrorList().getErrors());
		}
		return rc;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ErrorMessage checkQuadraturaLotti() {
		//UnitaMisura umPezze = YCostantiTestori.getUnitaMisuraGestionePezze();
		//if(umPezze != null && umPezze.getIdUnitaMisura().equals(getIdUMRif()) && getRigheLotto().size() > 0) {
		ArrayList arr1 = (ArrayList)this.getRigheLotto();
		if (getArticolo().getArticoloDatiMagaz().isArticLotto()){
			ArrayList arr = new ArrayList();
			for(int j=0; j<arr1.size(); j++){
				DocumentoRigaLotto rigalotto = (DocumentoRigaLotto)arr1.get(j);
				if (rigalotto!=null)
					if (getStatoAvanzamento()==StatoAvanzamento.DEFINITIVO &&
					rigalotto.getIdLotto() != null && 
					!rigalotto.getIdLotto().equals(Lotto.LOTTO_DUMMY)){
						arr.add(rigalotto);
					}
					else if (getStatoAvanzamento()!=StatoAvanzamento.DEFINITIVO || isControlloLottoDummyDaEscludere()){
						arr.add(rigalotto);
					}
			}
			Map mLotto = caricaLeQuantitaLotto(arr);
			Map mQuantita = cariaLeQuantitaBig();
			String valCtrl = "1";
			if (getStatoAvanzamento()==StatoAvanzamento.DEFINITIVO && !this.isCollegataAMagazzino()) {
				valCtrl = "2";
			}else if (getStatoAvanzamento()==StatoAvanzamento.DEFINITIVO && this.isCollegataAMagazzino()) {
				valCtrl = "3";
			}

			//..Se le quantita' non sono uguali le sistemo io portando la somma dei lotti sulla riga
			List tot = (ArrayList)mLotto.get(valCtrl);
			if (!controlloQuadraturaQuantita((QuantitaInUMRif)mQuantita.get(valCtrl),tot)) {
				QuantitaInUMRif totListaSpedita = new QuantitaInUMRif();
				QuantitaInUMRif zero = new QuantitaInUMRif();
				zero.azzera();
				totListaSpedita.azzera();
				for (int i=0; i<tot.size(); i++){
					if (tot.get(i)!=null){
						QuantitaInUMRif qta = (QuantitaInUMRif)tot.get(i);
						if (qta!=null && qta.compareTo(zero)!=0)
							totListaSpedita = totListaSpedita.add(qta);
					}
				}
				if(totListaSpedita.getQuantitaInUMRif().compareTo(BigDecimal.ZERO) > 0)
					setQtaInUMVen(totListaSpedita.getQuantitaInUMRif());
				if(totListaSpedita.getQuantitaInUMPrm().compareTo(BigDecimal.ZERO) > 0)
					setQtaInUMPrm(totListaSpedita.getQuantitaInUMPrm());
			}
		}
		//}
		return super.checkQuadraturaLotti();
	}

	@SuppressWarnings("rawtypes")
	public boolean controlloQuadraturaQuantita(QuantitaInUMRif big, List tot){

		QuantitaInUMRif totale = new QuantitaInUMRif();
		totale.azzera();
		if (big!=null && big.compareTo(totale)!=0)
			totale = big;


		QuantitaInUMRif totListaSpedita = new QuantitaInUMRif();
		QuantitaInUMRif zero = new QuantitaInUMRif();
		zero.azzera();
		totListaSpedita.azzera();
		for (int i=0; i<tot.size(); i++){
			if (tot.get(i)!=null){
				QuantitaInUMRif qta = (QuantitaInUMRif)tot.get(i);
				if (qta!=null && qta.compareTo(zero)!=0)
					totListaSpedita = totListaSpedita.add(qta);
			}
		}

		if (totale.compareTo(totListaSpedita)!= 0)
			return false;
		return true;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		YDocumentoVenRigaPrm yDocumentoVenRigaPrm = (YDocumentoVenRigaPrm)obj;
		iPianoRifornimentoRiga.setEqual(yDocumentoVenRigaPrm.iPianoRifornimentoRiga);
		iAreaApplicativa.setEqual(yDocumentoVenRigaPrm.iAreaApplicativa);
	}
}