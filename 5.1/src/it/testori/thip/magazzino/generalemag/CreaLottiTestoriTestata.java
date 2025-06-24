package it.testori.thip.magazzino.generalemag;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.thera.thermfw.base.TimeUtils;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.CopyException;
import com.thera.thermfw.persist.Copyable;
import com.thera.thermfw.persist.CopyableHelper;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.persist.Proxy;

import it.testori.thip.produzione.documento.YDocumentoPrdRigaVersamento;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaLottoPrm;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaPrm;
import it.thera.thip.acquisti.documentoAC.DocumentoAcquisto;
import it.thera.thip.acquisti.ordineAC.OrdineAcquistoRigaLottoPrm;
import it.thera.thip.acquisti.ordineAC.OrdineAcquistoRigaPrm;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.comuniVenAcq.DocumentoOrdineRigaLotto;
import it.thera.thip.base.comuniVenAcq.QuantitaInUMRif;
import it.thera.thip.base.documenti.StatoAvanzamento;
import it.thera.thip.cs.ColonneFiltri;
import it.thera.thip.datiTecnici.configuratore.Configurazione;
import it.thera.thip.datiTecnici.configuratore.ConfigurazioneRicEnh;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.magazzino.generalemag.PersDatiMagazzino;
import it.thera.thip.produzione.documento.DocumentoPrdRigaLottoVrs;
import it.thera.thip.produzione.documento.DocumentoPrdRigaVersamento;
import it.thera.thip.produzione.ordese.AttivitaEsecLottiPrd;
import it.thera.thip.produzione.ordese.AttivitaEsecProdotto;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 04/06/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71992    04/06/2025  DSSOF3   Prima stesura
 */

public class CreaLottiTestoriTestata extends CreaLottiTestoriNuovo {

	protected String iDocumento;
	protected String iSoggetto;

	protected Proxy iArticolo = new Proxy(Articolo.class);

	@SuppressWarnings("rawtypes")
	protected List iLottiDettaglio = new ArrayList();

	public CreaLottiTestoriTestata() {
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	@SuppressWarnings("rawtypes")
	public List getLottiDettaglio() {
		return iLottiDettaglio;
	}

	public String getDocumento() {
		return iDocumento;
	}

	public void setDocumento(String iDocumento) {
		this.iDocumento = iDocumento;
	}

	public String getSoggetto() {
		return iSoggetto;
	}

	public void setSoggetto(String iSoggetto) {
		this.iSoggetto = iSoggetto;
	}

	public Articolo getArticolo() {
		return (Articolo)iArticolo.getObject();
	}

	public void setArticolo(Articolo iArticolo) {
		this.iArticolo.setObject(iArticolo);
	}

	public String getArticoloKey() {
		return iArticolo.getKey();
	}

	public void setArticoloKey(String key) {
		iArticolo.setKey(key);
	}

	public String getIdArticolo() {
		String key = iArticolo.getKey();
		String rArticolo = KeyHelper.getTokenObjectKey(key,2);
		return rArticolo;
	}

	public void setIdArticolo(String rArticolo) {
		String key = iArticolo.getKey();
		iArticolo.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rArticolo));
	}

	@Override
	public void setIdAzienda(String iIdAzienda) {
		super.setIdAzienda(iIdAzienda);
		setIdAziendaInternal(iIdAzienda);
	}

	protected void setIdAziendaInternal(String iIdAzienda) {
		String k1 = iArticolo.getKey();
		iArticolo.setKey(KeyHelper.replaceTokenObjectKey(k1, 1, iIdAzienda));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int save(boolean force) throws SQLException {
		//Li propago su chi mi ha chiamato
		String[] params = getChiaveSelezionato().split(ColonneFiltri.LISTA_SEP);
		String className = params[0];
		String thKey = params[1];
		if(className.contains("DocumentoAcquistoRigaPrm")) {
			DocumentoAcqRigaPrm docAcqRig = (DocumentoAcqRigaPrm) DocumentoAcqRigaPrm.elementWithKey(DocumentoAcqRigaPrm.class, thKey, PersistentObject.NO_LOCK);
			if(docAcqRig != null) {
				//.Creo i lotti
				Iterator iterLotti = getLottiDettaglio().iterator();
				while(iterLotti.hasNext()) {
					CreaLottiTestoriDettaglio dettaglio = (CreaLottiTestoriDettaglio) iterLotti.next();

					Date dataApertura = TimeUtils.getCurrentDate();
					char tipo = PersDatiMagazzino.TIPO_ACQ;
					if(docAcqRig.getCausaleRiga().isLavEsterna())
						tipo = PersDatiMagazzino.TIPO_CL;
					char ambito = PersDatiMagazzino.CREA_DA_DOCUMENTO;
					String numeroDocFormattato = docAcqRig.getTestata().getNumeroDocumentoFormattato();
					String idAnnoDocOrd = docAcqRig.getAnnoDocumento();
					String idNumeroDocOrd = docAcqRig.getNumeroDocumento();
					Integer numRiga = docAcqRig.getNumeroRigaDocumento();
					Integer numRigaPadre = null;
					Date dataDocOrd = docAcqRig.getTestata().getDataDocumento();
					String idFornitore = ((DocumentoAcquisto)docAcqRig.getTestata()).getIdFornitore();
					Integer versione = docAcqRig.getIdVersioneRcs();
					String idEsternoConfig = docAcqRig.getIdEsternoConfig();
					String idMagazzino = docAcqRig.getIdMagazzino();
					boolean creaSaldi = true;
					Lotto lotto = creaLotto(dettaglio.getIdLotto(),versione,idEsternoConfig,idMagazzino,creaSaldi
							,dataApertura,tipo,ambito,numeroDocFormattato,idAnnoDocOrd,idNumeroDocOrd,numRiga,numRigaPadre,
							dataDocOrd,idFornitore);
					if(lotto != null) {
						//lotto.setNumeroRocche(getNumeroRocche());
						//lotto.setQuantitaOriginale(dettaglio.getQuantita());
						lotto.setLottoAcquisto(getLottoAcquisto());
						lotto.setDataScadenza(getDataScadenza());
						lotto.setNote(getNote());

						int rc = lotto.save();
						if(rc < 0)
							return rc;
					}
				}
				for (Iterator iterator = getLottiDettaglio().iterator(); iterator.hasNext();) {
					CreaLottiTestoriDettaglio lotto = (CreaLottiTestoriDettaglio) iterator.next();
					DocumentoAcqRigaLottoPrm rl = creaRigaLottoDocumentoAcquistoRigaPrm(docAcqRig, lotto);

					docAcqRig.getRigheLotto().add(rl);
				}
				int rc = docAcqRig.save();
				if(rc < 0)
					return rc;
			}
		}else if(className.contains("OrdineAcquistoRigaPrm")) {
			OrdineAcquistoRigaPrm docAcqRig = (OrdineAcquistoRigaPrm) OrdineAcquistoRigaPrm.elementWithKey(OrdineAcquistoRigaPrm.class, thKey, PersistentObject.NO_LOCK);
			if(docAcqRig != null) {
				//.Creo i lotti
				Iterator iterLotti = getLottiDettaglio().iterator();
				while(iterLotti.hasNext()) {
					CreaLottiTestoriDettaglio dettaglio = (CreaLottiTestoriDettaglio) iterLotti.next();

					Date dataApertura = TimeUtils.getCurrentDate();
					char tipo = PersDatiMagazzino.TIPO_ACQ;
					if(docAcqRig.getCausaleRiga().isLavEsterna())
						tipo = PersDatiMagazzino.TIPO_CL;
					char ambito = PersDatiMagazzino.CREA_DA_ORDINE;
					String numeroDocFormattato = docAcqRig.getTestata().getNumeroDocumentoFormattato();
					String idAnnoDocOrd = docAcqRig.getAnnoDocumento();
					String idNumeroDocOrd = docAcqRig.getNumeroDocumento();
					Integer numRiga = docAcqRig.getNumeroRigaDocumento();
					Integer numRigaPadre = null;
					Date dataDocOrd = docAcqRig.getTestata().getDataDocumento();
					String idFornitore = ((DocumentoAcquisto)docAcqRig.getTestata()).getIdFornitore();
					Integer versione = docAcqRig.getIdVersioneRcs();
					String idEsternoConfig = docAcqRig.getIdEsternoConfig();
					String idMagazzino = docAcqRig.getIdMagazzino();
					boolean creaSaldi = true;
					Lotto lotto = creaLotto(dettaglio.getIdLotto(),versione,idEsternoConfig,idMagazzino,creaSaldi
							,dataApertura,tipo,ambito,numeroDocFormattato,idAnnoDocOrd,idNumeroDocOrd,numRiga,numRigaPadre,
							dataDocOrd,idFornitore);
					if(lotto != null) {
						//lotto.setNumeroRocche(getNumeroRocche());
						//lotto.setQuantitaOriginale(dettaglio.getQuantita());
						lotto.setLottoAcquisto(getLottoAcquisto());
						lotto.setDataScadenza(getDataScadenza());
						lotto.setNote(getNote());

						int rc = lotto.save();
						if(rc < 0)
							return rc;
					}
				}
				for (Iterator iterator = getLottiDettaglio().iterator(); iterator.hasNext();) {
					CreaLottiTestoriDettaglio lotto = (CreaLottiTestoriDettaglio) iterator.next();
					OrdineAcquistoRigaLottoPrm rl = creaRigaLottoOrdineAcquistoRigaPrm(docAcqRig, lotto);

					docAcqRig.getRigheLotto().add(rl);
				}
				sistemaLottiOrdineAcquistoRigaPrm(docAcqRig);
				int rc = docAcqRig.save();
				if(rc < 0)
					return rc;
			}
		}else if(className.contains("AttivitaEsecProdotto")) {
			try {
				AttivitaEsecProdotto prodotto = (AttivitaEsecProdotto) AttivitaEsecProdotto.elementWithKey(AttivitaEsecProdotto.class, thKey, PersistentObject.NO_LOCK);
				if(prodotto != null) {
					//.Creo i lotti
					Iterator iterLotti = getLottiDettaglio().iterator();
					while(iterLotti.hasNext()) {
						CreaLottiTestoriDettaglio dettaglio = (CreaLottiTestoriDettaglio) iterLotti.next();

						Date dataApertura = TimeUtils.getCurrentDate();
						char tipo = PersDatiMagazzino.TIPO_PRD;
						char ambito = PersDatiMagazzino.CREA_DA_ORDINE;
						String numeroDocFormattato = prodotto.getAttivitaEsecutiva().getOrdineEsecutivo().getNumeroOrdFmt();
						String idAnnoDocOrd = prodotto.getIdAnnoOrdine();
						String idNumeroDocOrd = prodotto.getIdNumeroOrdine();
						Integer numRiga = prodotto.getIdRigaProdotto();
						Integer numRigaPadre = prodotto.getIdRigaAttivita();
						Date dataDocOrd = prodotto.getAttivitaEsecutiva().getOrdineEsecutivo().getDataOrdine();
						String idFornitore = null;
						Integer versione = prodotto.getIdVersione();
						String idEsternoConfig = prodotto.getIdConfigOrdine();
						String idMagazzino = prodotto.getIdMagazzinoVrs();
						boolean creaSaldi = true;
						Lotto lotto = creaLotto(dettaglio.getIdLotto(),versione,idEsternoConfig,idMagazzino,creaSaldi
								,dataApertura,tipo,ambito,numeroDocFormattato,idAnnoDocOrd,idNumeroDocOrd,numRiga,numRigaPadre,
								dataDocOrd,idFornitore);
						if(lotto != null) {
							//lotto.setNumeroRocche(getNumeroRocche());
							//lotto.setQuantitaOriginale(dettaglio.getQuantita());
							lotto.setLottoAcquisto(getLottoAcquisto());
							lotto.setDataScadenza(getDataScadenza());
							lotto.setNote(getNote());

							int rc = lotto.save();
							if(rc < 0)
								return rc;
						}
					}
					for (Iterator iterator = getLottiDettaglio().iterator(); iterator.hasNext();) {
						CreaLottiTestoriDettaglio lotto = (CreaLottiTestoriDettaglio) iterator.next();
						AttivitaEsecLottiPrd rl = creaRigaLottoAttivitaEsecProdotto(prodotto, lotto);

						prodotto.getLottiProdotti().add(rl);
					}
					prodotto.setAggiornaLottoDummy(true);
					int rc = prodotto.save();
					if(rc < 0)
						return rc;
				}
			} catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}else if(className.contains("DocumentoPrdRigaVersam")) {
			DocumentoPrdRigaVersamento docPrdRigVrs = (DocumentoPrdRigaVersamento) DocumentoPrdRigaVersamento.elementWithKey(DocumentoPrdRigaVersamento.class, thKey, PersistentObject.NO_LOCK);
			if(docPrdRigVrs != null) {
				//.Creo i lotti
				Iterator iterLotti = getLottiDettaglio().iterator();
				while(iterLotti.hasNext()) {
					CreaLottiTestoriDettaglio dettaglio = (CreaLottiTestoriDettaglio) iterLotti.next();

					Date dataApertura = TimeUtils.getCurrentDate();
					char tipo = PersDatiMagazzino.TIPO_PRD;
					char ambito = PersDatiMagazzino.CREA_DA_ORDINE;
					String numeroDocFormattato = docPrdRigVrs.getDocumentoPrd().getNumeroDocFormattato();
					String idAnnoDocOrd = docPrdRigVrs.getAnnoDocumento();
					String idNumeroDocOrd = docPrdRigVrs.getNumeroDocumento();
					Integer numRiga = docPrdRigVrs.getIdRigaDoc();
					Integer numRigaPadre = null;
					Date dataDocOrd = docPrdRigVrs.getDocumentoPrd().getDataDichiarazione();
					String idFornitore = null;
					Integer versione = docPrdRigVrs.getIdVersioneRcs();
					String idEsternoConfig = docPrdRigVrs.getIdEsternoConfig();
					String idMagazzino = docPrdRigVrs.getIdMagazzino();
					boolean creaSaldi = true;
					Lotto lotto = creaLotto(dettaglio.getIdLotto(),versione,idEsternoConfig,idMagazzino,creaSaldi
							,dataApertura,tipo,ambito,numeroDocFormattato,idAnnoDocOrd,idNumeroDocOrd,numRiga,numRigaPadre,
							dataDocOrd,idFornitore);
					if(lotto != null) {
						//lotto.setNumeroRocche(getNumeroRocche());
						//lotto.setQuantitaOriginale(dettaglio.getQuantita());
						lotto.setLottoAcquisto(getLottoAcquisto());
						lotto.setDataScadenza(getDataScadenza());
						lotto.setNote(getNote());

						int rc = lotto.save();
						if(rc < 0)
							return rc;
					}
				}
				for (Iterator iterator = getLottiDettaglio().iterator(); iterator.hasNext();) {
					CreaLottiTestoriDettaglio lotto = (CreaLottiTestoriDettaglio) iterator.next();
					DocumentoPrdRigaLottoVrs rl = creaRigaLottoDocumentoPrdRigaVrs(docPrdRigVrs, lotto);

					docPrdRigVrs.getLottiColl().add(rl);
				}
				((YDocumentoPrdRigaVersamento)docPrdRigVrs).aggiornaLottoDummy();
				int rc = docPrdRigVrs.save();
				if(rc < 0)
					return rc;
			}
		}
		return 1;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void sistemaLottiOrdineAcquistoRigaPrm(OrdineAcquistoRigaPrm ordAcqRig) {
		Iterator i = ordAcqRig.getRigheLotto().iterator();
		boolean lottoDummyPresente = false;
		DocumentoOrdineRigaLotto lottoDummy = null;
		QuantitaInUMRif totaleQtaAttesaLotti = (QuantitaInUMRif)Factory.createObject(QuantitaInUMRif.class);
		QuantitaInUMRif totaleQtaPropostaLotti = (QuantitaInUMRif)Factory.createObject(QuantitaInUMRif.class);
		totaleQtaAttesaLotti.azzera();
		totaleQtaPropostaLotti.azzera();
		boolean quadratura = true;

		while (i.hasNext())
		{
			DocumentoOrdineRigaLotto rigaLotto = (DocumentoOrdineRigaLotto)i.next();
			if (rigaLotto!=null){
				if (rigaLotto.getIdLotto().equals(Lotto.LOTTO_DUMMY))
				{
					lottoDummyPresente = true;
					lottoDummy = rigaLotto;
				}
				else {
					if (rigaLotto.getQtaAttesaEvasione().getQuantitaInUMPrm()!=null)
						totaleQtaAttesaLotti=totaleQtaAttesaLotti.add(rigaLotto.getQtaAttesaEvasione());
					if(rigaLotto.getQtaPropostaEvasione().getQuantitaInUMPrm()!=null)
						totaleQtaPropostaLotti=totaleQtaPropostaLotti.add(rigaLotto.getQtaPropostaEvasione());
				}
			}
		}
		quadratura = controllaQuadraturaQtaLottiOrdineAcquistoRigaPrm(ordAcqRig, totaleQtaPropostaLotti, totaleQtaAttesaLotti);
		if (ordAcqRig.getRigheLotto().isEmpty() && quadratura &&
				ordAcqRig.getServizioQta().getQuantitaInUMRif().compareTo(BigDecimal.ZERO) == 0){
			quadratura = false;
		}

		if (!quadratura){
			DocumentoOrdineRigaLotto lottoEffettivo = (DocumentoOrdineRigaLotto)ordAcqRig.getUnicoLottoEffettivo();
			if (lottoEffettivo != null && !getArticolo().getArticoloDatiMagaz().isLottoUnitario()){
				if (ordAcqRig.getStatoAvanzamento()==StatoAvanzamento.PROVVISORIO){
					lottoEffettivo.setQtaPropostaEvasione(ordAcqRig.getQtaPropostaEvasione());
				}
				if (ordAcqRig.getStatoAvanzamento()==StatoAvanzamento.DEFINITIVO){
					lottoEffettivo.setQtaAttesaEvasione(ordAcqRig.getQtaAttesaEvasione());
				}
			}
			else{
				if (!lottoDummyPresente){
					lottoDummy = ordAcqRig.creaLottiDummy(null, null, null);
				}
				if (ordAcqRig.getStatoAvanzamento()==StatoAvanzamento.PROVVISORIO &&
						(!totaleQtaPropostaLotti.isZero() || lottoDummy.getQtaPropostaEvasione().compareTo(ordAcqRig.getQtaPropostaEvasione())!=0 ||
						(ordAcqRig.getOldRiga()!=null && ordAcqRig.getStatoAvanzamento()!=ordAcqRig.getOldRiga().getStatoAvanzamento()))){
					lottoDummy.setQtaPropostaEvasione(ordAcqRig.getQtaPropostaEvasione().subtract(totaleQtaPropostaLotti));
				}
				if (ordAcqRig.getStatoAvanzamento()==StatoAvanzamento.DEFINITIVO &&
						(!totaleQtaAttesaLotti.isZero() || lottoDummy.getQtaAttesaEvasione().compareTo(ordAcqRig.getQtaAttesaEvasione())!=0 ||
						(ordAcqRig.getOldRiga()!=null && ordAcqRig.getStatoAvanzamento()!=ordAcqRig.getOldRiga().getStatoAvanzamento()))){
					lottoDummy.setQtaAttesaEvasione(ordAcqRig.getQtaAttesaEvasione().subtract(totaleQtaAttesaLotti));
				}
				if (!lottoDummyPresente)
					ordAcqRig.getRigheLotto().add(lottoDummy);
			}
		}else {
			ordAcqRig.getRigheLotto().remove(lottoDummy);
		}
	}

	protected boolean controllaQuadraturaQtaLottiOrdineAcquistoRigaPrm(OrdineAcquistoRigaPrm ordAcqRig, QuantitaInUMRif totaleQtaPropostaLotti, QuantitaInUMRif totaleQtaAttesaLotti){
		boolean quadratura = true;
		if (ordAcqRig.getStatoAvanzamento()==StatoAvanzamento.PROVVISORIO && ordAcqRig.getQtaPropostaEvasione()!=null){
			if (totaleQtaPropostaLotti.absRif().compareTo(ordAcqRig.getQtaPropostaEvasione().absRif()) != 0)//Fix 21491
				quadratura = false;
		}
		if (ordAcqRig.getStatoAvanzamento()==StatoAvanzamento.DEFINITIVO && ordAcqRig.getQtaAttesaEvasione()!=null){
			if (totaleQtaAttesaLotti.absRif().compareTo(ordAcqRig.getQtaAttesaEvasione().absRif()) != 0) //Fix 21491
				quadratura = false;
		}
		return quadratura;
	}

	public DocumentoPrdRigaLottoVrs creaRigaLottoDocumentoPrdRigaVrs(DocumentoPrdRigaVersamento docPrdRigVrs,
			CreaLottiTestoriDettaglio lt) {
		DocumentoPrdRigaLottoVrs lotto = (DocumentoPrdRigaLottoVrs) Factory.createObject(DocumentoPrdRigaLottoVrs.class);
		lotto.setIdAzienda(docPrdRigVrs.getIdAzienda());
		lotto.setIdAnnoDoc(docPrdRigVrs.getIdAnnoDoc());
		lotto.setIdNumeroDoc(docPrdRigVrs.getIdNumeroDoc());
		lotto.setIdRigaDoc(docPrdRigVrs.getIdRigaDoc());
		lotto.setIdArticolo(docPrdRigVrs.getRArticolo());
		lotto.setIdLotto(lt.getIdLotto());
		lotto.setRUmPrmMag(docPrdRigVrs.getArticolo().getIdUMPrmMag());
		lotto.setRUmSecMag(docPrdRigVrs.getArticolo().getIdUMSecMag());
		lotto.setQtaVrsUmPrm(lt.getQuantita());
		lotto.setQtaVrsUmSec(lt.getQuantita());
		return lotto;
	}

	public AttivitaEsecLottiPrd creaRigaLottoAttivitaEsecProdotto(AttivitaEsecProdotto prodotto,
			CreaLottiTestoriDettaglio lotto) {
		AttivitaEsecLottiPrd rl = (AttivitaEsecLottiPrd)Factory.createObject(AttivitaEsecLottiPrd.class);
		rl.setIdAzienda(getIdAzienda());
		rl.setIdAnnoOrdine(prodotto.getIdAnnoOrdine());
		rl.setIdNumeroOrdine(prodotto.getIdNumeroOrdine());
		rl.setIdRigaAttivita(prodotto.getIdRigaAttivita());
		rl.setIdRigaProdotto(prodotto.getIdRigaProdotto());
		rl.setIdArticolo(prodotto.getIdArticolo());
		rl.setIdLotto(lotto.getIdLotto());
		rl.setQtaRichiestaUMPrm(lotto.getQuantita());
		rl.setQtaRichiestaUMSec(lotto.getQuantita());
		return rl;
	}

	public DocumentoAcqRigaLottoPrm creaRigaLottoDocumentoAcquistoRigaPrm(DocumentoAcqRigaPrm docAcqRig, CreaLottiTestoriDettaglio lt) {
		DocumentoAcqRigaLottoPrm lotto = (DocumentoAcqRigaLottoPrm)Factory.createObject(DocumentoAcqRigaLottoPrm.class);
		lotto.setFather(docAcqRig);
		lotto.setIdAzienda(getIdAzienda());
		lotto.setIdArticolo(docAcqRig.getIdArticolo());
		lotto.setIdLotto(lt.getIdLotto());
		lotto.setQtaInUMPrm(lt.getQuantita());
		lotto.setQtaInUMAcq(lt.getQuantita());
		lotto.getQtaPropostaEvasione().setQuantitaInUMRif(lt.getQuantita());
		lotto.getQtaPropostaEvasione().setQuantitaInUMPrm(lt.getQuantita());
		return lotto;
	}

	public OrdineAcquistoRigaLottoPrm creaRigaLottoOrdineAcquistoRigaPrm(OrdineAcquistoRigaPrm ordAcqRig, CreaLottiTestoriDettaglio lt) {
		OrdineAcquistoRigaLottoPrm lotto = (OrdineAcquistoRigaLottoPrm)Factory.createObject(OrdineAcquistoRigaLottoPrm.class);
		lotto.setFather(ordAcqRig);
		lotto.setIdAzienda(getIdAzienda());
		lotto.setIdArticolo(ordAcqRig.getIdArticolo());
		lotto.setIdLotto(lt.getIdLotto());
		lotto.setQtaInUMPrmMag(lt.getQuantita());
		lotto.setQtaInUMRif(lt.getQuantita());
		return lotto;
	}

	public Lotto creaLotto(String idLotto, Integer versione, 
			String idEsternoConfig, String idMagazzino, boolean creaSaldi,
			Date dataApertura, char tipo, char ambito, String numeroDocFormattato,
			String idAnnoDocOrd, String idNumeroDocOrd, Integer numRiga, Integer numRigaPadre,
			Date dataDocOrd, String idFornitore) {

		try {
			//...Creo un nuovo lotto con il nuovo codice
			Lotto lotto = (Lotto) Factory.createObject(Lotto.class);
			lotto.setCodiceAzienda(getIdAzienda());
			lotto.setCodiceArticolo(getIdArticolo());
			lotto.setCodiceLotto(idLotto);
			lotto.setDataApertura(dataApertura);

			java.sql.Date dataScadenza = calcolaDataScadenzaLotto(dataApertura, lotto.getArticolo().getArticoloDatiMagaz().getGiorniValidita());
			lotto.setDataScadenza(dataScadenza);
			if(lotto.getArticolo().getArticoloDatiMagaz() != null)
				lotto.setGiorniScadenza(lotto.getArticolo().getArticoloDatiMagaz().getGiorniValidita());

			//...Se devo gestire la creazione automatica dei saldi
			lotto.setMagazzino(idMagazzino);
			lotto.setCheckCreaSaldi(creaSaldi);
			lotto.setCheckTuttiSaldi(false);
			lotto.setCheckTuttiMagazzini(false);
			String idRifDocFmt = numeroDocFormattato; //Fix 22277
			if(creaSaldi) {
				if (versione != null)
					lotto.setVersione(versione);
				if (idEsternoConfig != null && !idEsternoConfig.equals("")) { //Fix 4322
					//Fix 4290 inizio
					Configurazione cfg = ConfigurazioneRicEnh.recuperaConfigurazione(getIdAzienda(), getIdArticolo(), idEsternoConfig);
					lotto.setConfigurazione(cfg);
					//Fix 4290 fine
				}
				else
					lotto.setIdConfigurazione(Configurazione.CONFIGURAZIONE_DUMMY);
			}
			//...fine

			//...Inserisco i riferimenti all'ordine o al documento che ha generato il lotto
			if(tipo == PersDatiMagazzino.TIPO_ACQ) {
				lotto.setRifDocAcq(idAnnoDocOrd + "/" + idNumeroDocOrd);
				lotto.setRifRigaDocAcq("" + numRiga);
				lotto.setDataDocAcq(dataDocOrd);
				//Fix 5151 - inizio
				lotto.setCodiceFornitore(idFornitore);
				//Fix 5151 - inizio
				lotto.setRifDocAcqFmt(idRifDocFmt);//Fix 22277

			}
			else if(tipo == PersDatiMagazzino.TIPO_PRD) {
				if(ambito == CreaLottiTestoriUtils.CREA_DA_DOCUMENTO) {
					lotto.setRifDocProd(idAnnoDocOrd + "/" + idNumeroDocOrd);
					lotto.setRifRigaDocProd("" + numRiga); //...Codice riga prodotto
					lotto.setDataDocProd(dataDocOrd);
				}
				else if(ambito == CreaLottiTestoriUtils.CREA_DA_ORDINE) {
					lotto.setRifDocProd(idAnnoDocOrd + "/" + idNumeroDocOrd);
					lotto.setRifRigaDocProd("" + numRigaPadre); //...Codice riga attivita
					lotto.setRifRigaSecProd("" + numRiga); //...Codice riga prodotto
					lotto.setDataDocProd(dataDocOrd);
				}

				lotto.setRifDocProdFmt(idRifDocFmt);//Fix 22277
			}
			else if(tipo == PersDatiMagazzino.TIPO_CL) {
				lotto.setRifDocCLav(idAnnoDocOrd + "/" + idNumeroDocOrd);
				lotto.setRifRigaDocCLav("" + numRiga);
				lotto.setDataDocCLav(dataDocOrd);
				//Fix 5151 - inizio
				lotto.setCodiceFornitore(idFornitore);
				//Fix 5151 - fine
				lotto.setRifDocCLavFmt(idRifDocFmt);//Fix 22277
			}

			return lotto;
		}
		catch (Exception ex) {
			ex.printStackTrace(Trace.excStream);
			return null;
		}
	}

	protected java.sql.Date calcolaDataScadenzaLotto(Date dataApertura, Integer giorniVal) {
		java.sql.Date dataScadenza = null;
		if(giorniVal != null)
			dataScadenza = TimeUtils.addDays(dataApertura, giorniVal.intValue());
		return dataScadenza;
	}

	@Override
	public void setEqual(Copyable obj) throws CopyException {
		CopyableHelper.copyObject(this, obj);
	}

}
