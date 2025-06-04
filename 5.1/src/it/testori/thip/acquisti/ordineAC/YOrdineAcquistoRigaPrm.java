package it.testori.thip.acquisti.ordineAC;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.acquisti.ordineAC.OrdineAcquistoRigaPrm;
import it.thera.thip.base.documenti.StatoAvanzamento;

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
 * 71XXX    04/06/2025  DSSOF3   Prima stesura
 */

public class YOrdineAcquistoRigaPrm extends OrdineAcquistoRigaPrm {

	@Override
	public void creaLottiAutomatici() {
		if(!CreaLottiTestoriUtils.isArticoloGestioneLottiTestori(getArticolo(), (isLavorazioneEsterna() ? CreaLottiTestoriUtils.CONTO_LAVORO : CreaLottiTestoriUtils.ACQUISTO)))
			super.creaLottiAutomatici();
		else {
			setStatoAvanzamento(StatoAvanzamento.PROVVISORIO);
		}
	}
}