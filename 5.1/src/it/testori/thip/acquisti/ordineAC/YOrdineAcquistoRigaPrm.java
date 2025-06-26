package it.testori.thip.acquisti.ordineAC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.thera.thermfw.base.Trace;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.acquisti.ordineAC.OrdineAcquistoRigaLottoSec;
import it.thera.thip.acquisti.ordineAC.OrdineAcquistoRigaPrm;
import it.thera.thip.acquisti.ordineAC.OrdineAcquistoRigaSec;
import it.thera.thip.base.comuniVenAcq.OrdineRigaLotto;
import it.thera.thip.cs.ThipException;
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
 * 72014	26/06/2025	DSSOF3	 Gestione lotti pezze c/lav
 */

public class YOrdineAcquistoRigaPrm extends OrdineAcquistoRigaPrm {

	protected boolean iGeneraLottiTsAuto; //72014

	public boolean isGeneraLottiTsAuto() {
		return iGeneraLottiTsAuto;
	}

	public void setGeneraLottiTsAuto(boolean iGeneraLottiTsAuto) {
		this.iGeneraLottiTsAuto = iGeneraLottiTsAuto;
	}

	public YOrdineAcquistoRigaPrm() {
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
		char tipoProvenienza = isLavorazioneEsterna() ? CreaLottiTestoriUtils.CONTO_LAVORO : CreaLottiTestoriUtils.ACQUISTO;
		if(!CreaLottiTestoriUtils.isArticoloGestioneLottiTestori(getArticolo(), tipoProvenienza))
			super.creaLottiAutomatici();
		else if(CreaLottiTestoriUtils.isArticoloGestioneSubbiFeltri(getArticolo(), tipoProvenienza)) {
			try {
				creaLottiAutomaticiTestori();
			} catch (ThipException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void creaLottiAutomaticiTestori() throws ThipException {
		char tipoProvenienza = isLavorazioneEsterna() ? CreaLottiTestoriUtils.CONTO_LAVORO : CreaLottiTestoriUtils.ACQUISTO;
		CreaLottiTestoriUtils pal = null;
		//.Controllo che sulla riga secondaria sia presente un lotto non DUMMY
		if(CreaLottiTestoriUtils.isArticoloGestionePezze(getArticolo(), tipoProvenienza)) {
			pal = getCreaProposizioneAutLottoTestori();
			boolean trovato = false;
			String idLottoComponente = null;
			Iterator iterRigheSec = getRigheSecondarie().iterator();
			while(iterRigheSec.hasNext()) {
				OrdineAcquistoRigaSec rigaSec = (OrdineAcquistoRigaSec) iterRigheSec.next();
				Iterator iterLots = rigaSec.getRigheLotto().iterator();
				while(iterLots.hasNext()) {
					OrdineAcquistoRigaLottoSec rl = (OrdineAcquistoRigaLottoSec) iterLots.next();
					if(!rl.getIdLotto().equals(Lotto.LOTTO_DUMMY)) {
						trovato = true;
						idLottoComponente = rl.getIdLotto();
					}
				}
			}
			if(!trovato) {
				throw new ThipException("Per poter creare il lotto della riga primaria va prima indicato il lotto sulla riga secondaria");
			}else {
				String idLottoTeorico = CreaLottiTestoriUtils.getYearSuffix();
				idLottoTeorico += CreaLottiTestoriUtils.PEZZE;
				idLottoTeorico += tipoProvenienza;
				if(idLottoComponente.startsWith(idLottoTeorico)) {
					pal.setGeneraCodiceLottoAutomatico(false);
					try {
						String progressivo = CreaLottiTestoriUtils.getMaxProgressivoPezzeFromLotto(getIdAzienda(), idLottoComponente);
						String idLotto = idLottoComponente + progressivo;
						pal.setIdLotto(idLotto);
					} catch (SQLException e) {
						e.printStackTrace(Trace.excStream);
					}
				}else {
					throw new ThipException("Impossibile creare il lotto padre a partire dal lotto figlio, non e' costruito secondo la logica AATPNNNNN ");
				}
			}
		}else {
			pal = getCreaProposizioneAutLottoTestori();
		}
		if(pal != null) {
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