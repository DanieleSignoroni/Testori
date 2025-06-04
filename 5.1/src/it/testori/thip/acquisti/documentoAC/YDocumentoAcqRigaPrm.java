package it.testori.thip.acquisti.documentoAC;

import java.util.Iterator;

import com.thera.thermfw.persist.Factory;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaPrm;
import it.thera.thip.base.comuniVenAcq.DocumentoRigaLotto;
import it.thera.thip.base.comuniVenAcq.QuantitaInUMRif;
import it.thera.thip.base.documenti.StatoAvanzamento;

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

public class YDocumentoAcqRigaPrm extends DocumentoAcqRigaPrm {

	@Override
	protected void creaLottiAutomatici() {
		if(!CreaLottiTestoriUtils.isArticoloGestioneLottiTestori(getArticolo(), (getLavEsterna() ? CreaLottiTestoriUtils.CONTO_LAVORO : CreaLottiTestoriUtils.ACQUISTO)))
			super.creaLottiAutomatici();
		else {
			controllaProponiPresenzaLottoDummy();
			setStatoAvanzamento(StatoAvanzamento.PROVVISORIO);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void controllaProponiPresenzaLottoDummy() {
		Iterator i = getRigheLotto().iterator();
		boolean lottoDummyPresente = false;
		DocumentoRigaLotto lottoDummy = null;
		QuantitaInUMRif totaleQtaAttesaLotti = (QuantitaInUMRif)Factory.createObject(QuantitaInUMRif.class);
		QuantitaInUMRif totaleQtaPropostaLotti = (QuantitaInUMRif)Factory.createObject(QuantitaInUMRif.class);
		totaleQtaAttesaLotti.azzera();
		totaleQtaPropostaLotti.azzera();
		boolean quadratura = true;

		while (i.hasNext())
		{
			DocumentoRigaLotto rigaLotto = (DocumentoRigaLotto)i.next();
			if (rigaLotto!=null){
				if (rigaLotto.getIdLotto().equals(LOTTO_DUMMY))
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
		quadratura = controllaQuadraturaQtaLotti(totaleQtaPropostaLotti, totaleQtaAttesaLotti);
		if (getRigheLotto().isEmpty() && quadratura &&
				getServizioQta().getQuantitaInUMRif().compareTo(ZERO_DEC) == 0){
			quadratura = false;
		}

		if (!quadratura){
			DocumentoRigaLotto lottoEffettivo = (DocumentoRigaLotto)getUnicoLottoEffettivo();
			if (lottoEffettivo != null && !getArticolo().getArticoloDatiMagaz().isLottoUnitario()){
				if (this.getStatoAvanzamento()==StatoAvanzamento.PROVVISORIO){
					lottoEffettivo.setQtaPropostaEvasione(getQtaPropostaEvasione());
				}
				if (this.getStatoAvanzamento()==StatoAvanzamento.DEFINITIVO){
					lottoEffettivo.setQtaAttesaEvasione(getQtaAttesaEvasione());
				}
			}
			else{
				if (!lottoDummyPresente){
					lottoDummy = this.creaLottoDummy();
				}
				if (this.getStatoAvanzamento()==StatoAvanzamento.PROVVISORIO &&
						(!totaleQtaPropostaLotti.isZero() || lottoDummy.getQtaPropostaEvasione().compareTo(getQtaPropostaEvasione())!=0 ||
						(this.getOldRiga()!=null && getStatoAvanzamento()!=this.getOldRiga().getStatoAvanzamento()))){
					lottoDummy.setQtaPropostaEvasione(getQtaPropostaEvasione().subtract(totaleQtaPropostaLotti));
				}
				if (this.getStatoAvanzamento()==StatoAvanzamento.DEFINITIVO &&
						(!totaleQtaAttesaLotti.isZero() || lottoDummy.getQtaAttesaEvasione().compareTo(getQtaAttesaEvasione())!=0 ||
						(this.getOldRiga()!=null && this.getStatoAvanzamento()!=this.getOldRiga().getStatoAvanzamento()))){
					lottoDummy.setQtaAttesaEvasione(getQtaAttesaEvasione().subtract(totaleQtaAttesaLotti));
				}
				if (!lottoDummyPresente)
					getRigheLotto().add(lottoDummy);
			}
		}
	}
}
