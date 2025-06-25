package it.testori.thip.produzione.ordese;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.Factory;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.base.articolo.ArticoloDatiIdent;
import it.thera.thip.cs.ThipException;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.magazzino.generalemag.PersDatiMagazzino;
import it.thera.thip.produzione.ordese.AttivitaEsecLottiMat;
import it.thera.thip.produzione.ordese.AttivitaEsecLottiPrd;
import it.thera.thip.produzione.ordese.AttivitaEsecMateriale;
import it.thera.thip.produzione.ordese.AttivitaEsecProdotto;
import it.thera.thip.produzione.ordese.AttivitaEsecutiva;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 16/06/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71XXX    16/06/2025  DSSOF3   Prima stesura
 */
public class YAttivitaEsecProdotto extends AttivitaEsecProdotto {

	protected boolean iGeneraLottiTsAuto;

	public boolean isGeneraLottiTsAuto() {
		return iGeneraLottiTsAuto;
	}

	public void setGeneraLottiTsAuto(boolean iGeneraLottiTsAuto) {
		this.iGeneraLottiTsAuto = iGeneraLottiTsAuto;
	}

	public YAttivitaEsecProdotto() {
		setGeneraLottiTsAuto(false);
	}

	@Override
	public int save() throws SQLException {
		if(isOnDB() && isGeneraLottiTsAuto()) {
			creaLottiAutomaticiTestori();
		}
		int rc = super.save();
		return rc;
	}

	@Override
	public void creaLottiAutomatici() {
		if(!CreaLottiTestoriUtils.isArticoloGestioneLottiTestori(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE))
			super.creaLottiAutomatici();
		else {
			if(CreaLottiTestoriUtils.isArticoloGestioneSubbiFeltri(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)) {
				try {
					creaLottiAutomaticiTestori();
				} catch (ThipException e) {
					e.printStackTrace(Trace.excStream);
				}
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void creaLottiAutomaticiTestori() throws ThipException {

		//.Controllo che l'ordine esecutivo abbia un materiale semi-lavorato con un lotto gia' indicato
		if(CreaLottiTestoriUtils.isArticoloGestionePezze(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)) {
			boolean trovato = false;
			Iterator iterAtv = getAttivitaEsecutiva().getOrdineEsecutivo().getAttivitaEsecutive().iterator();
			while(iterAtv.hasNext()) {
				AttivitaEsecutiva atvEsec = (AttivitaEsecutiva) iterAtv.next();
				Iterator iterMats = atvEsec.getMateriali().iterator();
				while(iterMats.hasNext()) {
					AttivitaEsecMateriale mat = (AttivitaEsecMateriale) iterMats.next();
					if(mat.getArticolo().getTipoParte() == ArticoloDatiIdent.SEMIFINITO) {
						Iterator iterLots = mat.getLottiMateriali().iterator();
						while(iterLots.hasNext()) {
							AttivitaEsecLottiMat lottoMat = (AttivitaEsecLottiMat) iterLots.next();
							if(!lottoMat.getIdLotto().equals(Lotto.LOTTO_DUMMY))
								trovato = true;
						}
					}
				}
			}
			if(!trovato)
				throw new ThipException("Per poter creare il lotto del prodotto finito va prima indicato il lotto sul materiale");
		}
		CreaLottiTestoriUtils pal = getCreaProposizioneAutLottoTestori();
		List lottiAuto = pal.creaLottiAutomatici();
		for (int j = 0; j < lottiAuto.size(); j++) {
			Lotto lt = (Lotto) lottiAuto.get(j);
			AttivitaEsecLottiPrd lotto = (AttivitaEsecLottiPrd)Factory.createObject(AttivitaEsecLottiPrd.class);
			lotto.setIdAzienda(getIdAzienda());
			lotto.setIdAnnoOrdine(getIdAnnoOrdine());
			lotto.setIdNumeroOrdine(getIdNumeroOrdine());
			lotto.setIdRigaAttivita(getIdRigaAttivita());
			lotto.setIdRigaProdotto(getIdRigaProdotto());
			lotto.setIdArticolo(lt.getCodiceArticolo());
			lotto.setIdLotto(lt.getCodiceLotto());
			lotto.setQtaRichiestaUMPrm(getQtaRichiestaLottoAutomaticoUMPrm());
			lotto.setQtaRichiestaUMSec(getQtaRichiestaLottoAutomaticoUMSec());

			getLottiProdotti().add(lotto);
		}
	}

	@SuppressWarnings("rawtypes")
	public CreaLottiTestoriUtils getCreaProposizioneAutLottoTestori() {
		List lottiOrig = new ArrayList();

		return CreaLottiTestoriUtils.creaProposizioneAutLotto(PersDatiMagazzino.TIPO_PRD,
				getIdNumeroOrdine(),
				getIdAnnoOrdine(),
				getAttivitaEsecutiva().getOrdineEsecutivo().getDataOrdine(),
				getIdRigaProdotto(),
				getIdRigaAttivita(),
				getIdArticolo(),
				getIdVersione(),
				getIdEsternoConfig(),
				getIdMagazzinoVrs(),
				getIdCommessa(),
				getAttivitaEsecutiva().getOrdineEsecutivo().getIdCliente(),
				PersDatiMagazzino.CREA_DA_ORDINE,
				lottiOrig,
				null,
				null,
				getQtaResiduaUMPrm());
	}
}
