package it.testori.thip.magazzino.generalemag;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.CopyException;
import com.thera.thermfw.persist.Copyable;
import com.thera.thermfw.persist.CopyableHelper;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.persist.Proxy;

import it.testori.thip.acquisti.documentoAC.YDocumentoAcqRigaPrm;
import it.testori.thip.acquisti.ordineAC.YOrdineAcquistoRigaPrm;
import it.testori.thip.produzione.documento.YDocumentoPrdRigaVersamento;
import it.testori.thip.produzione.ordese.YAttivitaEsecProdotto;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaLottoPrm;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaPrm;
import it.thera.thip.acquisti.ordineAC.OrdineAcquistoRigaLottoPrm;
import it.thera.thip.acquisti.ordineAC.OrdineAcquistoRigaPrm;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.comuniVenAcq.OrdineRigaLotto;
import it.thera.thip.base.comuniVenAcq.QuantitaInUMRif;
import it.thera.thip.base.documenti.StatoAvanzamento;
import it.thera.thip.cs.ColonneFiltri;
import it.thera.thip.cs.ThipException;
import it.thera.thip.magazzino.generalemag.Lotto;
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
 * 72014	26/06/2025	DSSOF3	 Ottimizzazione
 * 72018	27/06/2025	DSSOF3	 Sistemare settaggio quantita in righe lotto doc acq
 * 72060	23/07/2025	DSSOF3	 Compilazione dati personalizzati lotti.
 * 72242	09/12/2025	DSSOF3	 Se quantitativo non congruo e sto per generare lotti dummy dare errore.
 * 72320	27/01/2026	DSSOF3	 Sistemazione set quantita sui lotti
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

					CreaLottiTestoriUtils pal = ((YDocumentoAcqRigaPrm)docAcqRig).getCreaProposizioneAutLottoTestori();
					pal.setGeneraCodiceLottoAutomatico(false);
					pal.setIdLotto(dettaglio.getIdLotto());

					List lottiAuto = pal.creaLottiAutomatici();
					if(lottiAuto != null && !lottiAuto.isEmpty()) {
						Lotto lotto = (Lotto) lottiAuto.get(0);
						if(lotto instanceof YLotto) { //72060
							((YLotto)lotto).setNumeroRocche(getNumeroRocche());
							((YLotto)lotto).setQuantitaOriginale(dettaglio.getQuantita());
						}
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
				//72242
				((YDocumentoAcqRigaPrm)docAcqRig).controllaProponiPresenzaLottoDummy();
				ErrorMessage em = docAcqRig.checkPresenzaLottoDummy();
				if(em != null) {
					throw new ThipException(em);
				}
				//72242
				int rc = docAcqRig.save();
				if(rc < 0)
					return rc;
			}
		}else if(className.contains("OrdineAcquistoRigaPrm")) {
			OrdineAcquistoRigaPrm ordAcqRig = (OrdineAcquistoRigaPrm) OrdineAcquistoRigaPrm.elementWithKey(OrdineAcquistoRigaPrm.class, thKey, PersistentObject.NO_LOCK);
			if(ordAcqRig != null) {
				//.Creo i lotti
				Iterator iterLotti = getLottiDettaglio().iterator();
				while(iterLotti.hasNext()) {
					CreaLottiTestoriDettaglio dettaglio = (CreaLottiTestoriDettaglio) iterLotti.next();

					CreaLottiTestoriUtils pal = ((YOrdineAcquistoRigaPrm)ordAcqRig).getCreaProposizioneAutLottoTestori();
					pal.setGeneraCodiceLottoAutomatico(false);
					pal.setIdLotto(dettaglio.getIdLotto());

					List lottiAuto = pal.creaLottiAutomatici();
					if(lottiAuto != null && !lottiAuto.isEmpty()) {
						Lotto lotto = (Lotto) lottiAuto.get(0);
						if(lotto instanceof YLotto) { //72060
							((YLotto)lotto).setNumeroRocche(getNumeroRocche());
							((YLotto)lotto).setQuantitaOriginale(dettaglio.getQuantita());
						}
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
					OrdineAcquistoRigaLottoPrm rl = creaRigaLottoOrdineAcquistoRigaPrm(ordAcqRig, lotto);

					ordAcqRig.getRigheLotto().add(rl);
				}
				sistemaLottiOrdineAcquistoRigaPrm(ordAcqRig);
				//72242
				OrdineRigaLotto orlDummy = ordAcqRig.dammiLottoDummy(null);
				if(orlDummy != null) {
					throw new ThipException(new ErrorMessage("BAS0000078","Sistemare le quantita', non e' possibile creare il lotto dummy"));
				}
				//72242
				int rc = ordAcqRig.save();
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

						CreaLottiTestoriUtils pal = ((YAttivitaEsecProdotto)prodotto).getCreaProposizioneAutLottoTestori();
						pal.setGeneraCodiceLottoAutomatico(false);
						pal.setIdLotto(dettaglio.getIdLotto());

						List lottiAuto = pal.creaLottiAutomatici();
						if(lottiAuto != null && !lottiAuto.isEmpty()) {
							Lotto lotto = (Lotto) lottiAuto.get(0);
							if(lotto instanceof YLotto) { //72060
								((YLotto)lotto).setNumeroRocche(getNumeroRocche());
								((YLotto)lotto).setQuantitaOriginale(dettaglio.getQuantita());
							}
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

					CreaLottiTestoriUtils pal = ((YDocumentoPrdRigaVersamento)docPrdRigVrs).getCreaProposizioneAutLottoTestori();
					pal.setGeneraCodiceLottoAutomatico(false);
					pal.setIdLotto(dettaglio.getIdLotto());

					List lottiAuto = pal.creaLottiAutomatici();
					if(lottiAuto != null && !lottiAuto.isEmpty()) {
						Lotto lotto = (Lotto) lottiAuto.get(0);
						if(lotto instanceof YLotto) { //72060
							((YLotto)lotto).setNumeroRocche(getNumeroRocche());
							((YLotto)lotto).setQuantitaOriginale(dettaglio.getQuantita());
						}
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
		OrdineAcquistoRigaLottoPrm lottoDummy = null;
		QuantitaInUMRif totaleQtaOrdinataLotti = (QuantitaInUMRif)Factory.createObject(QuantitaInUMRif.class);
		totaleQtaOrdinataLotti.azzera();
		boolean quadratura = true;

		while (i.hasNext())
		{
			OrdineAcquistoRigaLottoPrm rigaLotto = (OrdineAcquistoRigaLottoPrm)i.next();
			if (rigaLotto!=null){
				if (rigaLotto.getIdLotto().equals(Lotto.LOTTO_DUMMY))
				{
					lottoDummyPresente = true;
					lottoDummy = rigaLotto;
				}
				else {
					if (rigaLotto.getQuantitaOrdinata().getQuantitaInUMPrm()!=null)
						totaleQtaOrdinataLotti = totaleQtaOrdinataLotti.add(rigaLotto.getQuantitaOrdinata());
				}
			}
		}
		quadratura = controllaQuadraturaQtaLottiOrdineAcquistoRigaPrm(ordAcqRig, totaleQtaOrdinataLotti);
		if (ordAcqRig.getRigheLotto().isEmpty() && quadratura &&
				ordAcqRig.getServizioQta().getQuantitaInUMRif().compareTo(BigDecimal.ZERO) == 0){
			quadratura = false;
		}

		if (!quadratura){
			OrdineAcquistoRigaLottoPrm lottoEffettivo = (OrdineAcquistoRigaLottoPrm)ordAcqRig.getUnicoLottoEffettivo();
			if (lottoEffettivo != null && !getArticolo().getArticoloDatiMagaz().isLottoUnitario()){
				lottoEffettivo.getQuantitaOrdinata().setQuantitaInUMPrm(ordAcqRig.getQuantitaOrdinata().getQuantitaInUMPrm());
				lottoEffettivo.getQuantitaOrdinata().setQuantitaInUMRif(ordAcqRig.getQuantitaOrdinata().getQuantitaInUMRif());
			}
			else{
				if (!lottoDummyPresente){
					lottoDummy = (OrdineAcquistoRigaLottoPrm) ordAcqRig.creaLottiDummy(null, null, null);
				}
				if ((!totaleQtaOrdinataLotti.isZero() || lottoDummy.getQuantitaOrdinata().compareTo(ordAcqRig.getQuantitaOrdinata())!=0 ||
						(ordAcqRig.getOldRiga()!=null && ordAcqRig.getStatoAvanzamento()!=ordAcqRig.getOldRiga().getStatoAvanzamento()))){
					lottoDummy.getQuantitaOrdinata().setQuantitaInUMPrm(ordAcqRig.getQuantitaOrdinata().getQuantitaInUMPrm().subtract(totaleQtaOrdinataLotti.getQuantitaInUMPrm()));
					lottoDummy.getQuantitaOrdinata().setQuantitaInUMRif(ordAcqRig.getQuantitaOrdinata().getQuantitaInUMRif().subtract(totaleQtaOrdinataLotti.getQuantitaInUMRif()));
				}
				if (!lottoDummyPresente)
					ordAcqRig.getRigheLotto().add(lottoDummy);
			}
		}else {
			ordAcqRig.getRigheLotto().remove(lottoDummy);
		}
	}

	protected boolean controllaQuadraturaQtaLottiOrdineAcquistoRigaPrm(OrdineAcquistoRigaPrm ordAcqRig, QuantitaInUMRif totaleQtaOrdinataLotti){
		boolean quadratura = true;
		if (totaleQtaOrdinataLotti.absRif().compareTo(ordAcqRig.getQuantitaOrdinata().absRif()) != 0)
			quadratura = false;
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
		//Fix 72018
		QuantitaInUMRif qtaUnitario = new QuantitaInUMRif(lt.getQuantita(),new BigDecimal(0),new BigDecimal(0));
		qtaUnitario.setQuantitaInUMRif(getArticolo().convertiUMArrotondate(qtaUnitario.getQuantitaInUMPrm(), docAcqRig.getUMPrm(), docAcqRig.getUMRif(), docAcqRig.getArticoloVersRichiesta()));
		if(docAcqRig.getUMSec() != null)
			qtaUnitario.setQuantitaInUMSec(getArticolo().convertiUMArrotondate(qtaUnitario.getQuantitaInUMPrm(), docAcqRig.getUMPrm(), docAcqRig.getUMSec(), docAcqRig.getArticoloVersRichiesta()));
		if(docAcqRig.getStatoAvanzamento() == StatoAvanzamento.DEFINITIVO)
			lotto.setQtaAttesaEvasione(lotto.getQtaAttesaEvasione().add(qtaUnitario));
		else
			lotto.setQtaPropostaEvasione(lotto.getQtaPropostaEvasione().add(qtaUnitario));
		//Fix 72018
		return lotto;
	}

	public OrdineAcquistoRigaLottoPrm creaRigaLottoOrdineAcquistoRigaPrm(OrdineAcquistoRigaPrm ordAcqRig, CreaLottiTestoriDettaglio lt) {
		OrdineAcquistoRigaLottoPrm lotto = (OrdineAcquistoRigaLottoPrm)Factory.createObject(OrdineAcquistoRigaLottoPrm.class);
		lotto.setFather(ordAcqRig);
		lotto.setIdAzienda(getIdAzienda());
		lotto.setIdArticolo(ordAcqRig.getIdArticolo());
		lotto.setIdLotto(lt.getIdLotto());
		lotto.getQuantitaOrdinata().setQuantitaInUMPrm(lt.getQuantita());
		lotto.getQuantitaOrdinata().setQuantitaInUMRif(lt.getQuantita());
		return lotto;
	}

	@Override
	public void setEqual(Copyable obj) throws CopyException {
		CopyableHelper.copyObject(this, obj);
	}

}
