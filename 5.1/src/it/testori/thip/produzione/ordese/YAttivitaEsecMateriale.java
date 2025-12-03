package it.testori.thip.produzione.ordese;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thera.thermfw.persist.Factory;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.cs.ThipException;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.magazzino.generalemag.PersDatiMagazzino;
import it.thera.thip.produzione.ordese.AttivitaEsecLottiMat;
import it.thera.thip.produzione.ordese.AttivitaEsecMateriale;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 27/06/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72018    27/06/2025  DSSOF3   Prima stesura
 * 72236	02/12/2025	DSSOF3   Aggiungere metodo
 */

public class YAttivitaEsecMateriale extends AttivitaEsecMateriale {

	protected boolean iGeneraLottiTsAuto;

	public boolean isGeneraLottiTsAuto() {
		return iGeneraLottiTsAuto;
	}

	public void setGeneraLottiTsAuto(boolean iGeneraLottiTsAuto) {
		this.iGeneraLottiTsAuto = iGeneraLottiTsAuto;
	}

	public YAttivitaEsecMateriale() {
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void creaLottiAutomaticiTestori() throws ThipException {
		CreaLottiTestoriUtils pal = null;
		if(CreaLottiTestoriUtils.isArticoloGestioneSubbiFeltri(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)) {
			pal = getCreaProposizioneAutLottoTestori();
		}
		if(pal != null) {
			List lottiAuto = pal.creaLottiAutomatici();
			if (lottiAuto != null && !lottiAuto.isEmpty()) {
				getLottiMateriali().clear();
				for (int j = 0; j < lottiAuto.size(); j++) {
					Lotto lt = (Lotto)lottiAuto.get(j);
					AttivitaEsecLottiMat lotto = (AttivitaEsecLottiMat)Factory.createObject(AttivitaEsecLottiMat.class);
					lotto.setIdAzienda(getIdAzienda());
					lotto.setIdAnnoOrdine(getIdAnnoOrdine());
					lotto.setIdNumeroOrdine(getIdNumeroOrdine());
					lotto.setIdRigaAttivita(getIdRigaAttivita());
					lotto.setIdRigaMateriale(getIdRigaMateriale());
					lotto.setIdArticolo(lt.getCodiceArticolo());
					lotto.setIdLotto(lt.getCodiceLotto());
					lotto.setQtaRichiestaUMPrm(getQtaRichiestaUMPrm());
					lotto.setQtaRichiestaUMSec(getQtaRichiestaUMSec());
					getLottiMateriali().add(lotto);
				}
			}
			setAggiornaLottoDummy(true);
		}
	}

	@SuppressWarnings("rawtypes")
	public CreaLottiTestoriUtils getCreaProposizioneAutLottoTestori() {
		List lottiOrig = new ArrayList();

		return CreaLottiTestoriUtils.creaProposizioneAutLotto(PersDatiMagazzino.TIPO_PRD,
				getIdNumeroOrdine(),
				getIdAnnoOrdine(),
				getAttivitaEsecutiva().getOrdineEsecutivo().getDataOrdine(),
				getIdRigaMateriale(),
				getIdRigaAttivita(),
				getIdArticolo(),
				getIdVersione(),
				getIdEsternoConfig(),
				getIdMagazzinoPrl(),
				getIdCommessa(),
				getAttivitaEsecutiva().getOrdineEsecutivo().getIdCliente(),
				PersDatiMagazzino.CREA_DA_ORDINE, 	//Non significativo
				lottiOrig,
				null,
				null,
				getQtaResiduaUMPrm()
				);
	}

	//72236
	public AttivitaEsecLottiMat generaNuovoLottoMateriale(Lotto lt) {
		AttivitaEsecLottiMat lotto = (AttivitaEsecLottiMat)Factory.createObject(AttivitaEsecLottiMat.class);
		lotto.setIdAzienda(getIdAzienda());
		lotto.setIdAnnoOrdine(getIdAnnoOrdine());
		lotto.setIdNumeroOrdine(getIdNumeroOrdine());
		lotto.setIdRigaAttivita(getIdRigaAttivita());
		lotto.setIdRigaMateriale(getIdRigaMateriale());
		lotto.setIdArticolo(lt.getCodiceArticolo());
		lotto.setIdLotto(lt.getCodiceLotto());
		lotto.setQtaRichiestaUMPrm(getQtaRichiestaUMPrm());
		lotto.setQtaRichiestaUMSec(getQtaRichiestaUMSec());
		return lotto;
	}
	//72236
}
