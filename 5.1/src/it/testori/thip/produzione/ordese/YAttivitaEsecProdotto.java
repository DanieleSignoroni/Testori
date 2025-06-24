package it.testori.thip.produzione.ordese;

import java.util.ArrayList;
import java.util.List;

import com.thera.thermfw.persist.Factory;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.magazzino.generalemag.PersDatiMagazzino;
import it.thera.thip.produzione.ordese.AttivitaEsecLottiPrd;
import it.thera.thip.produzione.ordese.AttivitaEsecProdotto;

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

	@Override
	public void creaLottiAutomatici() {
		if(!CreaLottiTestoriUtils.isArticoloGestioneLottiTestori(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE))
			super.creaLottiAutomatici();
		else {
			if(CreaLottiTestoriUtils.isArticoloGestioneSubbiFeltri(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)) {
				creaLottiAutomaticiTestori();
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void creaLottiAutomaticiTestori() {
		List lottiOrig = new ArrayList();

		CreaLottiTestoriUtils pal = CreaLottiTestoriUtils.creaProposizioneAutLotto(PersDatiMagazzino.TIPO_PRD,
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
}
