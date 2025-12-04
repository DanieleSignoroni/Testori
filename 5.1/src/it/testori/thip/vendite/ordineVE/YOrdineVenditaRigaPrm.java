package it.testori.thip.vendite.ordineVE;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.thera.thermfw.common.BaseComponentsCollection;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.CachedStatement;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.CopyException;
import com.thera.thermfw.persist.Copyable;
import com.thera.thermfw.persist.Database;
import com.thera.thermfw.persist.ErrorCodes;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.OneToMany;
import com.thera.thermfw.persist.Proxy;

import it.testori.thip.base.articolo.YArticoloCosto;
import it.testori.thip.base.generale.AssocHdrTpDocDgt;
import it.testori.thip.vendite.generaleVE.GestioneCalcoloCosti;
import it.thera.thip.base.articolo.ArticoloCosto;
import it.thera.thip.base.articolo.ClasseD;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.comuniVenAcq.ContenitoreRiga;
import it.thera.thip.base.comuniVenAcq.DocumentoOrdineRiga;
import it.thera.thip.base.comuniVenAcq.StatoEvasione;
import it.thera.thip.base.comuniVenAcq.TipoRiga;
import it.thera.thip.base.documenti.StatoAvanzamento;
import it.thera.thip.cs.ThipException;
import it.thera.thip.vendite.generaleVE.PersDatiVen;
import it.thera.thip.vendite.ordineVE.OrdineVendita;
import it.thera.thip.vendite.ordineVE.OrdineVenditaRigaPrm;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 28/08/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72097    28/08/2025  DSSOF3   Prima stesura
 * 72105	02/09/2025	DSSOF3   Gestione prima data promessa
 * 72141	24/09/2025  DSSOF3   Introduzione markup, errore articolo costo
 * 72157	08/10/2025	DSSOF3	 Aggiunta note cambio data consegna, modifiche generiche alla gestione date
 */

public class YOrdineVenditaRigaPrm extends OrdineVenditaRigaPrm {

	private static final String STMT_CLEAN_CAU_CAMBIO_DT_CONS = "UPDATE "+YOrdineVenditaRigaPrmTM.TABLE_NAME_EXT+"  "
			+ "SET "+YOrdineVenditaRigaPrmTM.R_COD_CAMBIO_DT_CONS+" = NULL "
			+ "WHERE "+YOrdineVenditaRigaPrmTM.ID_AZIENDA+" = ? "
			+ "AND "+YOrdineVenditaRigaPrmTM.ID_ANNO_ORD+" = ? "
			+ "AND "+YOrdineVenditaRigaPrmTM.ID_NUMERO_ORD+" = ? "
			+ "AND "+YOrdineVenditaRigaPrmTM.ID_RIGA_ORD+" = ?";
	public static CachedStatement cCleanCausaleCambioDtCons = new CachedStatement(STMT_CLEAN_CAU_CAMBIO_DT_CONS);

	//72157
	private static final String STMT_CLEAN_NOTE_CAMBIO_DT_CONS = "UPDATE "+YOrdineVenditaRigaPrmTM.TABLE_NAME_EXT+"  "
			+ "SET "+YOrdineVenditaRigaPrmTM.NOTE_CAMBIO_DT_CONS+" = NULL "
			+ "WHERE "+YOrdineVenditaRigaPrmTM.ID_AZIENDA+" = ? "
			+ "AND "+YOrdineVenditaRigaPrmTM.ID_ANNO_ORD+" = ? "
			+ "AND "+YOrdineVenditaRigaPrmTM.ID_NUMERO_ORD+" = ? "
			+ "AND "+YOrdineVenditaRigaPrmTM.ID_RIGA_ORD+" = ?";
	public static CachedStatement cCleanNoteCambioDtCons = new CachedStatement(STMT_CLEAN_NOTE_CAMBIO_DT_CONS);
	//72157

	protected java.sql.Date iDataConsegnaCfmStorica;

	//72141
	protected BigDecimal iMarkup;

	/**
	 * Attributo di servizio, e' la copia del {@link #getCostoUnitario()}.<br>
	 */
	protected BigDecimal iYCostoUnitario;
	//72141

	protected Proxy iCausaleCambioDataConsegna = new Proxy(
			it.testori.thip.vendite.ordineVE.YCausaleCambioDataCons.class);

	protected String iNoteCambioDataCons; //72157

	protected BigDecimal iPrezzoEuro;

	protected String iAltezzaRichiesta;
	protected String iLunghezzaRichiesta;

	protected Proxy iAreaApplicativa = new Proxy(it.thera.thip.base.articolo.ClasseD.class);

	protected OneToMany iYAllegati = new OneToMany(YAllegatiOrdVenRigPrm.class, this, 15, false);

	public YOrdineVenditaRigaPrm() {
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setDataConsegnaCfmStorica(java.sql.Date dataConsegnaCfmStorica) {
		this.iDataConsegnaCfmStorica = dataConsegnaCfmStorica;
		setDirty();
	}

	public java.sql.Date getDataConsegnaCfmStorica() {
		return iDataConsegnaCfmStorica;
	}

	public void setCausaleCambioDataConsegna(YCausaleCambioDataCons causalecambiodataconsegna) {
		String oldObjectKey = getKey();
		this.iCausaleCambioDataConsegna.setObject(causalecambiodataconsegna);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public YCausaleCambioDataCons getCausaleCambioDataConsegna() {
		return (YCausaleCambioDataCons) iCausaleCambioDataConsegna.getObject();
	}

	public void setCausaleCambioDataConsegnaKey(String key) {
		String oldObjectKey = getKey();
		iCausaleCambioDataConsegna.setKey(key);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getCausaleCambioDataConsegnaKey() {
		return iCausaleCambioDataConsegna.getKey();
	}

	public void setIdAzienda(String idAzienda) {
		super.setIdAzienda(idAzienda);
		if (iCausaleCambioDataConsegna != null) {
			String key = iCausaleCambioDataConsegna.getKey();
			iCausaleCambioDataConsegna.setKey(KeyHelper.replaceTokenObjectKey(key, 1, idAzienda));
		}
		if(iAreaApplicativa != null) {
			String key = iAreaApplicativa.getKey();
			iAreaApplicativa.setKey(KeyHelper.replaceTokenObjectKey(key, 1, idAzienda));
		}
		if (iYAllegati != null) {
			iYAllegati.setFatherKeyChanged();
		}
	}

	@Override
	public void setAnnoDocumento(String annoDocumento) {
		super.setAnnoDocumento(annoDocumento);
		if (iYAllegati != null) {
			iYAllegati.setFatherKeyChanged();
		}
	}

	@Override
	public void setNumeroDocumento(String numeroDocumento) {
		super.setNumeroDocumento(numeroDocumento);
		if (iYAllegati != null) {
			iYAllegati.setFatherKeyChanged();
		}
	}

	@Override
	public void setNumeroRigaDocumento(Integer numeroRigaDocumento) {
		super.setNumeroRigaDocumento(numeroRigaDocumento);
		if (iYAllegati != null) {
			iYAllegati.setFatherKeyChanged();
		}
	}

	public void setIdCodiceCambioDtCons(String idCodiceCambioDtCons) {
		String key = iCausaleCambioDataConsegna.getKey();
		iCausaleCambioDataConsegna.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idCodiceCambioDtCons));
		setDirty();
	}

	public String getIdCodiceCambioDtCons() {
		String key = iCausaleCambioDataConsegna.getKey();
		String objIdCodiceCambioDtCons = KeyHelper.getTokenObjectKey(key, 2);
		return objIdCodiceCambioDtCons;
	}

	//72141
	public void setMarkup(BigDecimal markup) {
		this.iMarkup = markup;
		setDirty();
	}

	public BigDecimal getMarkup() {
		return iMarkup;
	}

	public BigDecimal getYCostoUnitario() {
		return iYCostoUnitario;
	}

	public void setYCostoUnitario(BigDecimal iYCostoUnitario) {
		this.iYCostoUnitario = iYCostoUnitario;
		setDirty();
	}
	//72141

	//72157
	public String getNoteCambioDataCons() {
		return iNoteCambioDataCons;
	}

	public void setNoteCambioDataCons(String iNoteCambioDataCons) {
		this.iNoteCambioDataCons = iNoteCambioDataCons;
		setDirty();
	}
	//72157

	public BigDecimal getPrezzoEuro() {
		return iPrezzoEuro;
	}

	public void setPrezzoEuro(BigDecimal iPrezzoEuro) {
		this.iPrezzoEuro = iPrezzoEuro;
		setDirty();
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

	@Override
	public void completaBO() {
		super.completaBO();
		if(getTipoRiga() == TipoRiga.MERCE)
			caricaAllegati(false);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void caricaAllegati(boolean controlloPresenti) {
		Vector associazioniDef = AssocHdrTpDocDgt.recuperaAssociazioniDefault("OrdineVenditaRigaPrm", true);
		if(associazioniDef != null && associazioniDef.size() > 0) {
			for (Iterator iterator = associazioniDef.iterator(); iterator.hasNext();) {
				AssocHdrTpDocDgt associazione = (AssocHdrTpDocDgt) iterator.next();
				
				if(controlloPresenti && isAssociazioneAllegatoPresente(associazione.getIdTipoDocumentoDigitale()))
					continue;
				
				YAllegatiOrdVenRigPrm allegato = (YAllegatiOrdVenRigPrm) Factory.createObject(YAllegatiOrdVenRigPrm.class);
				allegato.setOrdinevenditarigaprm(this);
				allegato.setAssociazionedocumento(associazione);

				getYAllegati().add(allegato);

			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public boolean isAssociazioneAllegatoPresente(String idTipoDocDgt) {
		Iterator iterAllegati = getYAllegati().iterator();
		while(iterAllegati.hasNext()) {
			YAllegatiOrdVenRigPrm allegato = (YAllegatiOrdVenRigPrm) iterAllegati.next();
			if(allegato.getIdTipoDocDgt().equals(idTipoDocDgt))
				return true;
		}
		return false;
	}

	@Override
	protected void copiaValoriInOldRiga() {
		super.copiaValoriInOldRiga();
		YOrdineVenditaRigaPrm ovr = (YOrdineVenditaRigaPrm) iOldRiga;
		ovr.setDataConsegnaCfmStorica(getDataConsegnaCfmStorica());
		ovr.setCausaleCambioDataConsegna(getCausaleCambioDataConsegna());
	}

	@Override
	public int save() throws SQLException {
		if(getYCostoUnitario() != null) { //72141
			setCostoUnitario(getYCostoUnitario());
		}
		if(!isOnDB()) { //72157
			setDataConsegnaConfermata(null);
			setSettConsegnaConfermata(null);
			setDataConsegnaProduzione(null);
			setSettConsegnaProduzione(null);
			setDataConsegnaCfmStorica(null);
		}
		if(!isOnDB()) {
			//72157 remmare
			//setDataConsegnaCfmStorica(getDataConsegnaConfermata()); //Storicizzo sul nuovo la data consegna confermata
			//72105 Inizio
		}else {
			//72157
			if(isOnDB() 
					&& getDataConsegnaConfermata() != null 
					&& getDataConsegnaCfmStorica() == null
					&& getStatoAvanzamento() == StatoAvanzamento.DEFINITIVO) {
				setDataConsegnaCfmStorica(getDataConsegnaConfermata());
			}
			//72157
			if (getCausaleCambioDataConsegna() != null
					&& getCausaleCambioDataConsegna().isModificaDataOrigine()) {
				setDataConsegnaCfmStorica(getDataConsegnaConfermata());
			}
			DocumentoOrdineRiga ovr = (DocumentoOrdineRiga) iOldRiga;
			if(ovr != null && isQuantitaCambiata() && getStatoEvasione() != StatoEvasione.SALDATO) {
				setDataConsegnaConfermata(null);
				setStatoAvanzamento(StatoAvanzamento.PROVVISORIO);
			}
			if (ovr != null
					&& !datiUguali(ovr.getDataConsegnaRichiesta(), getDataConsegnaRichiesta())
					&& getStatoEvasione() != StatoEvasione.SALDATO){
				setDataConsegnaConfermata(null);
				setStatoAvanzamento(StatoAvanzamento.PROVVISORIO);
			}
		}
		//72105 Fine
		if(!isOnDB() && getIdAreaApplicativa() == null) {
			setIdAreaApplicativa(getArticolo().getIdClasseD());
		}
		
		caricaAllegati(true);

		int rc = super.save();

		//..Se l'utente ha cambiato data consegna tramite il campo della causale allora svuoto la causale (fatto cosi perche' almeno registro i log del campo)
		if(rc > 0 && isOnDB()) {
			DocumentoOrdineRiga ovr = (DocumentoOrdineRiga) iOldRiga;
			if (ovr != null
					&& !datiUguali(ovr.getDataConsegnaConfermata(), getDataConsegnaConfermata())
					&& getCausaleCambioDataConsegna() != null) {
				svuotaCausaleCambioDataConsegna();
			}
			//72157
			if(ovr != null && getNoteCambioDataCons() != null && !getNoteCambioDataCons().isEmpty()) {
				String oldIdCambioCau = ((YOrdineVenditaRigaPrm)ovr).getIdCodiceCambioDtCons();
				boolean cleanNotes = false;
				if((oldIdCambioCau == null && getIdCodiceCambioDtCons() != null)){
					cleanNotes = true;
				}else if(oldIdCambioCau != null && getIdCodiceCambioDtCons() == null) {
					cleanNotes = true;
				}else if(oldIdCambioCau != null && getIdCodiceCambioDtCons() != null && !oldIdCambioCau.equals(getIdCodiceCambioDtCons())) {
					cleanNotes = true;
				}
				if(cleanNotes) {
					svuotaNoteCambioDataConsegna();
				}
			}
			//72157
		}
		return rc;
	}

	@Override
	public int saveOwnedObjects(int rc) throws SQLException {
		rc = super.saveOwnedObjects(rc);
		rc = iYAllegati.save(rc);
		return rc;
	}

	public int deleteOwnedObjects() throws SQLException {
		int ret = super.deleteOwnedObjects();
		if (ret < ErrorCodes.NO_ROWS_UPDATED) {
			return ret;
		}
		return getYAllegatiInternal().delete();
	}

	@SuppressWarnings("rawtypes")
	public List getYAllegati() {
		return getYAllegatiInternal();
	}

	protected OneToMany getYAllegatiInternal() {
		if (iYAllegati.isNew())
			iYAllegati.retrieve();
		return iYAllegati;
	}

	//72157
	@SuppressWarnings("rawtypes")
	@Override
	protected void aggiornaDatiTestata() throws ThipException {
		super.aggiornaDatiTestata();
		OrdineVendita tes = (OrdineVendita) getTestata();
		if(tes.getStatoEvasione() != StatoEvasione.SALDATO) {
			boolean tutteDefinitive = true;
			Iterator iterRighe = getTestata().getRighe().iterator();
			while(iterRighe.hasNext()) {
				YOrdineVenditaRigaPrm riga = (YOrdineVenditaRigaPrm) iterRighe.next();
				if(!riga.getKey().equals(getKey()) 
						&& riga.getStatoAvanzamento() != StatoAvanzamento.DEFINITIVO) {
					tutteDefinitive = false;
				}
			}
			if(getStatoAvanzamento() != StatoAvanzamento.DEFINITIVO) {
				tutteDefinitive = false;
			}

			if(tutteDefinitive)
				tes.setStatoAvanzamento(StatoAvanzamento.DEFINITIVO);
			else
				tes.setStatoAvanzamento(StatoAvanzamento.PROVVISORIO);
		}
	}
	//72157

	//72141
	@Override
	public boolean initializeOwnedObjects(boolean result) {
		boolean ret = super.initializeOwnedObjects(result);
		setYCostoUnitario(getCostoUnitario());
		result = iYAllegati.initialize(result);
		return ret;
	}
	//72141

	protected int svuotaCausaleCambioDataConsegna() throws SQLException {
		PreparedStatement ps = cCleanCausaleCambioDtCons.getStatement();
		Database db = ConnectionManager.getCurrentDatabase();
		db.setString(ps, 1, getIdAzienda());
		db.setString(ps, 2, getAnnoDocumento());
		db.setString(ps, 3, getNumeroDocumento());
		db.setString(ps, 4, getNumeroRigaDocumento().toString());
		ps.execute();
		if (ps.executeUpdate() >= 0)
			return 1;
		return -100;
	}

	//72157
	protected int svuotaNoteCambioDataConsegna() throws SQLException {
		PreparedStatement ps = cCleanNoteCambioDtCons.getStatement();
		Database db = ConnectionManager.getCurrentDatabase();
		db.setString(ps, 1, getIdAzienda());
		db.setString(ps, 2, getAnnoDocumento());
		db.setString(ps, 3, getNumeroDocumento());
		db.setString(ps, 4, getNumeroRigaDocumento().toString());
		ps.execute();
		if (ps.executeUpdate() >= 0)
			return 1;
		return -100;
	}
	//72157

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = super.checkAll(components);

		//..Se la data di consegna e' cambiata  ma non ho indicato un codice di cambio causale non permetto la validazione dell'oggetto
		if(isOnDB()) {
			DocumentoOrdineRiga ovr = (DocumentoOrdineRiga) iOldRiga;
			if (ovr != null
					&& ((YOrdineVenditaRigaPrm)ovr).getDataConsegnaCfmStorica() != null
					&& !datiUguali(ovr.getDataConsegnaConfermata(), getDataConsegnaConfermata())
					&& getCausaleCambioDataConsegna() == null) {
				errors.add(new ErrorMessage("YT_0000001"));
			}
			if((isQuantitaCambiata() || (ovr != null
					&& !datiUguali(ovr.getDataConsegnaRichiesta(), getDataConsegnaRichiesta())))
					&& getRilascioOrdineProd() == RILASCIO_ORD_PRD_RILASCIATO) {
				errors.add(new ErrorMessage("BAS0000078","Ordine gia' rilasciato in produzione, impossibile cambiare quantita o data consegna richiesta"));
			}
		}

		/*ErrorMessage emArtCos = controllaArticoloCosto();
		if(emArtCos != null)
			errors.add(emArtCos);
		 */

		return errors;
	}

	//72141
	public ErrorMessage controllaArticoloCosto() {
		Integer idVersione = (getArticoloVersRichiesta() == null) ? ContenitoreRiga.VERSIONE_DUMMY : getArticoloVersRichiesta().getIdVersione();
		Integer idConfigurazione = (getConfigurazione() == null) ? null : getConfigurazione().getIdConfigurazione();
		ArticoloCosto articoloCosto =
				ArticoloCosto.elementWithKey(
						Azienda.getAziendaCorrente(),
						getIdArticolo(),
						idVersione,
						idConfigurazione,
						PersDatiVen.getCurrentPersDatiVen().getTipoCosto() != null ? PersDatiVen.getCurrentPersDatiVen().getTipoCosto().getIdTipoCosto() : ""
						);
		if (articoloCosto != null 
				&& articoloCosto instanceof YArticoloCosto
				&& ((YArticoloCosto)articoloCosto).getRilevatoErrore()
				&& ((YArticoloCosto)articoloCosto).getMessaggioErrore() != null) {
			return new ErrorMessage(GestioneCalcoloCosti.YT_0000003,new String[] {((YArticoloCosto)articoloCosto).getMessaggioErrore()});
		}
		return null;
	}
	//72141

	@SuppressWarnings("rawtypes")
	public ArrayList caricaQuantitaLottoPublic(List righeLotto) {
		return caricaQuantitaLotto(righeLotto);
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		YOrdineVenditaRigaPrm yOrdineVenditaRigaPrm = (YOrdineVenditaRigaPrm) obj;
		if (yOrdineVenditaRigaPrm.iDataConsegnaCfmStorica != null)
			iDataConsegnaCfmStorica = (java.sql.Date) yOrdineVenditaRigaPrm.iDataConsegnaCfmStorica.clone();
		iCausaleCambioDataConsegna.setEqual(yOrdineVenditaRigaPrm.iCausaleCambioDataConsegna);
		iAreaApplicativa.setEqual(yOrdineVenditaRigaPrm.iAreaApplicativa);
		iYAllegati.setEqual(yOrdineVenditaRigaPrm.iYAllegati);
	}

}