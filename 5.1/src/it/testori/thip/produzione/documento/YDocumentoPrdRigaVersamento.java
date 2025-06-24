package it.testori.thip.produzione.documento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.thera.thermfw.persist.Factory;

import it.testori.thip.base.articolo.YArticoloDatiMagaz;
import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.magazzino.generalemag.PersDatiMagazzino;
import it.thera.thip.produzione.documento.DocumentoPrdRigaLottoVrs;
import it.thera.thip.produzione.documento.DocumentoPrdRigaVersamento;
import it.thera.thip.produzione.ordese.AttivitaEsecLottiPrd;

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
public class YDocumentoPrdRigaVersamento extends DocumentoPrdRigaVersamento {

	@Override
	public void creaLottiAutomatici() {
		if(!CreaLottiTestoriUtils.isArticoloGestioneLottiTestori(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE))
			super.creaLottiAutomatici();
		else {
			if(getArticolo().getArticoloDatiMagaz().getCodAutLotProd() == YArticoloDatiMagaz.SUBBI_FELTRI) {
				creaLottiAutomaticiTestori();
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void creaLottiAutomaticiTestori() {
		List lottiOrig = new ArrayList();
		List lottiOrdine = new ArrayList();
		if (getAttivitaEsecProdotto() != null && usaLottiOrd()) {
			List lottiRig = getAttivitaEsecProdotto().getLottiProdotti();
			for (int i = 0; i < lottiRig.size(); i++) {
				AttivitaEsecLottiPrd lt = (AttivitaEsecLottiPrd) lottiRig.get(i);
				if (!lt.getIdLotto().equals(Lotto.LOTTO_DUMMY)) {
					lottiOrig.add(lt.getLotto());
					lottiOrdine.add(lt);
				}
			}
		}

		String idCliente = null;
		if (getDocumentoPrd().getOrdineEsecutivo() != null)
			idCliente = getDocumentoPrd().getOrdineEsecutivo().getIdCliente();
		CreaLottiTestoriUtils pal = CreaLottiTestoriUtils.creaProposizioneAutLotto(PersDatiMagazzino.TIPO_PRD,
				getIdNumeroDoc(),
				getIdAnnoDoc(),
				getDocumentoPrd().getDataDichiarazione(),
				getIdRigaDoc(),
				null,
				getRArticolo(),
				getRVersione(),
				getIdEsternoConfig(),
				getRMagazzino(),
				getRCommessa(),
				idCliente,
				PersDatiMagazzino.CREA_DA_DOCUMENTO,
				lottiOrig,
				lottiOrdine,
				null,
				getQuantitaUmPrm());
		List lottiAuto = pal.creaLottiAutomatici();
		for (int j = 0; j < lottiAuto.size(); j++) {
			Lotto lt = (Lotto) lottiAuto.get(j);
			DocumentoPrdRigaLottoVrs lotto = (DocumentoPrdRigaLottoVrs) Factory.createObject(DocumentoPrdRigaLottoVrs.class);
			lotto.setIdAzienda(getIdAzienda());
			lotto.setIdAnnoDoc(getIdAnnoDoc());
			lotto.setIdNumeroDoc(getIdNumeroDoc());
			lotto.setIdRigaDoc(getIdRigaDoc());
			lotto.setIdArticolo(lt.getCodiceArticolo());
			lotto.setIdLotto(lt.getCodiceLotto());
			lotto.setRUmPrmMag(lt.getArticolo().getIdUMPrmMag());
			lotto.setRUmSecMag(lt.getArticolo().getIdUMSecMag());
			lotto.setQtaVrsUmPrm(getQuantitaUmPrm());
			lotto.setQtaVrsUmSec(getQuantitaUmSec());
			getLottiColl().add(lotto);
		}
	}

	@SuppressWarnings("rawtypes")
	public void aggiornaLottoDummy()
	{
		BigDecimal qtaPrmRigaVrs = getQtaVersataUMPrmPrd() == null ? getQuantitaUmPrm() : getQtaVersataUMPrmPrd();
		BigDecimal qtaSecRigaVrs = getQtaVersataUMSecPrd() == null ? getQuantitaUmSec() : getQtaVersataUMSecPrd();
		if (getArticolo().isArticLotto()) {
			BigDecimal sumPrm = new BigDecimal(0);
			BigDecimal sumSec = new BigDecimal(0);
			List lottiPrdList = getLottiColl();
			for (int i = 0; i < lottiPrdList.size(); i++)
			{
				DocumentoPrdRigaLottoVrs lotto = (DocumentoPrdRigaLottoVrs)lottiPrdList.get(i);
				if (!lotto.getIdLotto().equals(Lotto.LOTTO_DUMMY))
				{
					sumPrm = sumPrm.add(lotto.getQtaVrsUmPrm());
					//lotto.insertQuantiteSec();
					BigDecimal qtaSec = lotto.getQtaVrsUmSec();
					if (qtaSec != null)
						sumSec = sumSec.add(qtaSec);
				}
			}
			if (sumPrm.compareTo(qtaPrmRigaVrs) < 0)
			{
				BigDecimal diffPrm = qtaPrmRigaVrs.subtract(sumPrm);
				DocumentoPrdRigaLottoVrs dummy = ricercaLottoProdottoDummy();
				if (dummy == null)
					dummy = creaLottoProdottoDummy();
				dummy.setQtaVrsUmPrm(diffPrm);
				if (qtaSecRigaVrs != null)
				{
					BigDecimal diffSec = qtaSecRigaVrs.subtract(sumSec);
					dummy.setQtaVrsUmSec(diffSec);
				}
			}
			else if (sumPrm.compareTo(qtaPrmRigaVrs) == 0)
			{
				DocumentoPrdRigaLottoVrs dummy = ricercaLottoProdottoDummy();
				if (dummy != null)
					lottiPrdList.remove(dummy);
			}
		}else if (!getArticolo().isArticLotto()){
			DocumentoPrdRigaLottoVrs dummy = ricercaLottoProdottoDummy();
			if (dummy == null) {
				dummy = creaLottoProdottoDummy();
				dummy.setQtaVrsUmPrm(qtaPrmRigaVrs);
				dummy.setQtaVrsUmSec(qtaSecRigaVrs);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public DocumentoPrdRigaLottoVrs ricercaLottoProdottoDummy(){
		List lottiPrdList = getLottiColl();
		for (int i = 0; i < lottiPrdList.size(); i++)
		{
			DocumentoPrdRigaLottoVrs lotto = (DocumentoPrdRigaLottoVrs)lottiPrdList.get(i);
			if (lotto.getIdLotto().equals(Lotto.LOTTO_DUMMY))
				return lotto;
		}

		return null;
	}

	protected DocumentoPrdRigaLottoVrs creaLottoProdottoDummy() {
		DocumentoPrdRigaLottoVrs lotto = (DocumentoPrdRigaLottoVrs)Factory.createObject(AttivitaEsecLottiPrd.class);
		lotto.setIdAzienda(getIdAzienda());
		lotto.setIdAnnoDoc(getIdAnnoDoc());
		lotto.setIdNumeroDoc(getIdNumeroDoc());
		lotto.setIdRigaDoc(getIdRigaDoc());
		lotto.setIdArticolo(getRArticolo());
		lotto.setIdLotto(Lotto.LOTTO_DUMMY);
		return lotto;
	}
}
