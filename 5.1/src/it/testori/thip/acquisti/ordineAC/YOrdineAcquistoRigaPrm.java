package it.testori.thip.acquisti.ordineAC;

import java.util.ArrayList;
import java.util.List;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.acquisti.ordineAC.OrdineAcquistoRigaPrm;
import it.thera.thip.base.comuniVenAcq.OrdineRigaLotto;
import it.thera.thip.base.documenti.StatoAvanzamento;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.magazzino.generalemag.PersDatiMagazzino;

/**
 *
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

public class YOrdineAcquistoRigaPrm extends OrdineAcquistoRigaPrm {

	@Override
	public void creaLottiAutomatici() {
		char tipoProvenienza = isLavorazioneEsterna() ? CreaLottiTestoriUtils.CONTO_LAVORO : CreaLottiTestoriUtils.ACQUISTO;
		if(!CreaLottiTestoriUtils.isArticoloGestioneLottiTestori(getArticolo(), tipoProvenienza))
			super.creaLottiAutomatici();
		else if(CreaLottiTestoriUtils.isArticoloGestioneSubbiFeltri(getArticolo(), tipoProvenienza)) {
			creaLottiAutomaticiTestori();
		}else {
			setStatoAvanzamento(StatoAvanzamento.PROVVISORIO);
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void creaLottiAutomaticiTestori() {
		CreaLottiTestoriUtils pal = getCreaProposizioneAutLottoTestori();
		List lottiAuto = pal.creaLottiAutomatici();
		if(lottiAuto != null && !lottiAuto.isEmpty()) {
			getRigheLotto().clear();
			for (int j = 0; j < lottiAuto.size(); j++) {
				Lotto lt = (Lotto)lottiAuto.get(j);
				OrdineRigaLotto lotto = creaLotto();
				lotto.setFather(this);
				lotto.setIdArticolo(lt.getCodiceArticolo());
				lotto.setIdLotto(lt.getCodiceLotto());
				lotto.getQuantitaOrdinata().setQuantitaInUMRif(getQuantitaOrdinata().getQuantitaInUMRif());
				lotto.getQuantitaOrdinata().setQuantitaInUMPrm(getQuantitaOrdinata().getQuantitaInUMPrm());
				lotto.getQuantitaOrdinata().setQuantitaInUMSec(getQuantitaOrdinata().getQuantitaInUMSec());
				getRigheLotto().add(lotto);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public CreaLottiTestoriUtils getCreaProposizioneAutLottoTestori() {
		List lottiOrig = new ArrayList();
		char tipo = PersDatiMagazzino.TIPO_ACQ;
		if(getCausaleRiga().isLavEsterna())
			tipo = PersDatiMagazzino.TIPO_CL;
		return CreaLottiTestoriUtils.creaProposizioneAutLotto(tipo,
				getNumeroDocumento(),
				getAnnoDocumento(),
				getTestata().getDataDocumento(),
				getNumeroRigaDocumento(),
				null,
				getIdArticolo(),
				getIdVersioneSal(),
				getIdEsternoConfig(),
				getIdMagazzino(),
				getIdCommessa(),
				getIdFornitore(),
				PersDatiMagazzino.CREA_DA_ORDINE,
				lottiOrig,
				null,
				null,
				getQuantitaResiduo().getQuantitaInUMPrm());
	}
}