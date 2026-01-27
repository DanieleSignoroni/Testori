package it.testori.thip.acquisti.documentoAC;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.Factory;

import it.testori.thip.base.articolo.YArticolo;
import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaLottoPrm;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaLottoSec;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaPrm;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaSec;
import it.thera.thip.base.articolo.ArticoloDatiMagaz;
import it.thera.thip.base.comuniVenAcq.DocumentoOrdineRiga;
import it.thera.thip.base.comuniVenAcq.DocumentoRigaLotto;
import it.thera.thip.base.comuniVenAcq.QuantitaInUMRif;
import it.thera.thip.base.documenti.StatoAvanzamento;
import it.thera.thip.cs.ThipException;
import it.thera.thip.datiTecnici.distinta.EsplosioneNodo;
import it.thera.thip.datiTecnici.modpro.EspNodoArticoloBase;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.magazzino.generalemag.PersDatiMagazzino;
import it.thera.thip.produzione.ordese.AttivitaEsecMateriale;

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
 * 72014	26/06/2025	DSSOF3	 Gestione lotti pezze c/lav
 * 72018	27/06/2025	DSSOF3	 Rimozione settaggio riga provvisoria
 * 72320	27/01/2026	DSSOF3	 Generazione riga secondaria da modello, se su articolo ce' flag 'Floor stock C/Lav' allora non genero la riga
 */

public class YDocumentoAcqRigaPrm extends DocumentoAcqRigaPrm {

	protected boolean iGeneraLottiTsAuto; //72014

	public boolean isGeneraLottiTsAuto() {
		return iGeneraLottiTsAuto;
	}

	public void setGeneraLottiTsAuto(boolean iGeneraLottiTsAuto) {
		this.iGeneraLottiTsAuto = iGeneraLottiTsAuto;
	}

	public YDocumentoAcqRigaPrm() {
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
	public boolean hasGestioneAFloorStock(AttivitaEsecMateriale attMateriale) {
		boolean hasGestioneAFloorStock = super.hasGestioneAFloorStock(attMateriale);
		//72320 <
		if(attMateriale != null
				&& attMateriale.getArticolo() != null
				&& ((YArticolo)attMateriale.getArticolo()).isFloorStockContoLavoro()) {
			hasGestioneAFloorStock = true;
		}
		//72320 >
		return hasGestioneAFloorStock;
	}

	@Override
	protected DocumentoOrdineRiga generaRigaSecondaria(EsplosioneNodo datiRigaSec, int sequenza) throws SQLException {
		DocumentoOrdineRiga rigaSec = super.generaRigaSecondaria(datiRigaSec, sequenza);
		//72320 <
		if(rigaSec != null
				&& rigaSec.getArticolo() != null
				&& ((YArticolo)rigaSec.getArticolo()).isFloorStockContoLavoro()) {
			rigaSec = null;
		}
		//72320 >
		return rigaSec;
	}

	@Override
	public DocumentoOrdineRiga generaRigaSecondariaModello(EspNodoArticoloBase datiRigaSec, int sequenza)
			throws SQLException {
		DocumentoOrdineRiga rigaSec = super.generaRigaSecondariaModello(datiRigaSec, sequenza);
		//72320 <
		if(rigaSec != null
				&& rigaSec.getArticolo() != null
				&& ((YArticolo)rigaSec.getArticolo()).isFloorStockContoLavoro()) {
			rigaSec = null;
		}
		//72320 >
		return rigaSec;
	}

	@Override
	protected void creaLottiAutomatici() {
		char tipoProvenienza = getLavEsterna() ? CreaLottiTestoriUtils.CONTO_LAVORO : CreaLottiTestoriUtils.ACQUISTO;
		if(!CreaLottiTestoriUtils.isArticoloGestioneLottiTestori(getArticolo(), tipoProvenienza)) {
			super.creaLottiAutomatici();
		}else if(CreaLottiTestoriUtils.isArticoloGestioneSubbiFeltri(getArticolo(), tipoProvenienza)
				&& getArticolo().getArticoloDatiMagaz().getGenAutLotAcq() == ArticoloDatiMagaz.CREA_DA_DOCUMENTO) {
			try {
				creaLottiAutomaticiTestori();
			} catch (ThipException e) {
				e.printStackTrace(Trace.excStream);
			}
		}else {
			controllaProponiPresenzaLottoDummy();
			//setStatoAvanzamento(StatoAvanzamento.PROVVISORIO); fix 72018 remmare
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void creaLottiAutomaticiTestori() throws ThipException {
		char tipoProvenienza = getLavEsterna() ? CreaLottiTestoriUtils.CONTO_LAVORO : CreaLottiTestoriUtils.ACQUISTO;
		//.Controllo che sulla riga secondaria sia presente un lotto non DUMMY
		if(CreaLottiTestoriUtils.isArticoloGestionePezze(getArticolo(), tipoProvenienza)) {
			boolean trovato = false;
			Iterator iterRigheSec = getRigheSecondarie().iterator();
			while(iterRigheSec.hasNext()) {
				DocumentoAcqRigaSec rigaSec = (DocumentoAcqRigaSec) iterRigheSec.next();
				Iterator iterLots = rigaSec.getRigheLotto().iterator();
				while(iterLots.hasNext()) {
					DocumentoAcqRigaLottoSec rl = (DocumentoAcqRigaLottoSec) iterLots.next();
					if(!rl.getIdLotto().equals(Lotto.LOTTO_DUMMY))
						trovato = true;
				}
			}
			if(!trovato) {
				throw new ThipException("Per poter creare il lotto della riga primaria va prima indicato il lotto sulla riga secondaria");
			}
		}
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
