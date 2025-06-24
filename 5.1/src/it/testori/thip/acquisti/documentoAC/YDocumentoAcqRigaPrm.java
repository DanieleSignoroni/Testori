package it.testori.thip.acquisti.documentoAC;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.thera.thermfw.persist.Factory;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaLottoPrm;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaPrm;
import it.thera.thip.base.comuniVenAcq.DocumentoRigaLotto;
import it.thera.thip.base.comuniVenAcq.QuantitaInUMRif;
import it.thera.thip.base.documenti.StatoAvanzamento;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.magazzino.generalemag.PersDatiMagazzino;

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

public class YDocumentoAcqRigaPrm extends DocumentoAcqRigaPrm {

	@Override
	protected void creaLottiAutomatici() {
		char tipoProvenienza = getLavEsterna() ? CreaLottiTestoriUtils.CONTO_LAVORO : CreaLottiTestoriUtils.ACQUISTO;
		if(!CreaLottiTestoriUtils.isArticoloGestioneLottiTestori(getArticolo(), tipoProvenienza)) {
			super.creaLottiAutomatici();
		}else if(CreaLottiTestoriUtils.isArticoloGestioneSubbiFeltri(getArticolo(), tipoProvenienza)) {
			creaLottiAutomaticiTestori();
		}else {
			controllaProponiPresenzaLottoDummy();
			setStatoAvanzamento(StatoAvanzamento.PROVVISORIO);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void creaLottiAutomaticiTestori() {
		CreaLottiTestoriUtils pal = getCreaProposizioneAutLottoTestori();
		List lottiAuto = pal.creaLottiAutomatici();
		if(lottiAuto != null && !lottiAuto.isEmpty()) {
			getRigheLotto().clear();  
			for (int j = 0; j < lottiAuto.size(); j++) {
				Lotto lt = (Lotto)lottiAuto.get(j);
				DocumentoAcqRigaLottoPrm lotto = (DocumentoAcqRigaLottoPrm)Factory.createObject(DocumentoAcqRigaLottoPrm.class);
				lotto.setFather(this);
				lotto.setIdAzienda(getIdAzienda());
				lotto.setIdArticolo(lt.getCodiceArticolo());
				lotto.setIdLotto(lt.getCodiceLotto());
				lotto.setQtaAttesaEvasione(lotto.getQtaAttesaEvasione().add(getQtaAttesaEvasione()));
				lotto.setQtaPropostaEvasione(lotto.getQtaPropostaEvasione().add(getQtaPropostaEvasione()));
				getRigheLotto().add(lotto);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public CreaLottiTestoriUtils getCreaProposizioneAutLottoTestori() {
		BigDecimal qta = new BigDecimal("0");
		if (getStatoAvanzamento() == StatoAvanzamento.PROVVISORIO && getQtaPropostaEvasione() != null){
			qta = getQtaPropostaEvasione().getQuantitaInUMPrm();
		}
		if (getStatoAvanzamento() == StatoAvanzamento.DEFINITIVO && getQtaAttesaEvasione() != null){
			qta = getQtaAttesaEvasione().getQuantitaInUMPrm();
		}
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
				PersDatiMagazzino.CREA_DA_DOCUMENTO,
				lottiOrig,
				null,
				null,
				qta);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void controllaProponiPresenzaLottoDummy() {
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
		}else {
			getRigheLotto().remove(lottoDummy);
		}
	}
}
