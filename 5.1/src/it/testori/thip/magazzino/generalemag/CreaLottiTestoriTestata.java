package it.testori.thip.magazzino.generalemag;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.CopyException;
import com.thera.thermfw.persist.Copyable;
import com.thera.thermfw.persist.CopyableHelper;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.persist.Proxy;

import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaLottoPrm;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaPrm;
import it.thera.thip.acquisti.ordineAC.OrdineAcquistoRigaLottoPrm;
import it.thera.thip.acquisti.ordineAC.OrdineAcquistoRigaPrm;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.cs.ColonneFiltri;
import it.thera.thip.magazzino.generalemag.Lotto;

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
 * 71XXX    04/06/2025  DSSOF3   Prima stesura
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
		Iterator iterLotti = getLottiDettaglio().iterator();

		//.Creo i lotti
		while(iterLotti.hasNext()) {
			CreaLottiTestoriDettaglio dettaglio = (CreaLottiTestoriDettaglio) iterLotti.next();

			Lotto lotto = creaLotto(getIdAzienda(), getIdArticolo(), dettaglio.getIdLotto());
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

		//Li propago su chi mi ha chiamato
		String[] params = getChiaveSelezionato().split(ColonneFiltri.LISTA_SEP);
		String className = params[0];
		String thKey = params[1];
		if(className.contains("DocumentoAcquistoRigaPrm")) {
			try {
				DocumentoAcqRigaPrm docAcqRig = (DocumentoAcqRigaPrm) DocumentoAcqRigaPrm.elementWithKey(DocumentoAcqRigaPrm.class, thKey, PersistentObject.NO_LOCK);
				if(docAcqRig != null) {
					for (Iterator iterator = getLottiDettaglio().iterator(); iterator.hasNext();) {
						CreaLottiTestoriDettaglio lotto = (CreaLottiTestoriDettaglio) iterator.next();
						DocumentoAcqRigaLottoPrm rl = creaRigaLottoDocumentoAcquistoRigaPrm(docAcqRig, lotto);

						docAcqRig.getRigheLotto().add(rl);
					}
					int rc = docAcqRig.save();
					if(rc < 0)
						return rc;
				}
			} catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}else if(className.contains("OrdineAcquistoRigaPrm")) {
			try {
				OrdineAcquistoRigaPrm docAcqRig = (OrdineAcquistoRigaPrm) OrdineAcquistoRigaPrm.elementWithKey(OrdineAcquistoRigaPrm.class, thKey, PersistentObject.NO_LOCK);
				if(docAcqRig != null) {
					for (Iterator iterator = getLottiDettaglio().iterator(); iterator.hasNext();) {
						CreaLottiTestoriDettaglio lotto = (CreaLottiTestoriDettaglio) iterator.next();
						OrdineAcquistoRigaLottoPrm rl = creaRigaLottoOrdineAcquistoRigaPrm(docAcqRig, lotto);

						docAcqRig.getRigheLotto().add(rl);
					}
					int rc = docAcqRig.save();
					if(rc < 0)
						return rc;
				}
			} catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return 1;
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

	public Lotto creaLotto(String idAzienda,String idArticolo, String idLotto) {
		Lotto lot = (Lotto) Factory.createObject(Lotto.class);
		lot.setCodiceAzienda(idAzienda);
		lot.setCodiceArticolo(idArticolo);
		lot.setCodiceLotto(idLotto);
		return lot;
	}


	@Override
	public void setEqual(Copyable obj) throws CopyException {
		CopyableHelper.copyObject(this, obj);
	}

}
