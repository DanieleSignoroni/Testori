package it.testori.thip.magazzino.generalemag;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.thera.thermfw.base.Q6Calc;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.BusinessObjectAdapter;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.security.Authorizable;

import it.testori.thip.produzione.ordese.YAttivitaEsecProdotto;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.cs.ColonneFiltri;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.produzione.ordese.AttivitaEsecLottiPrd;
import it.thera.thip.produzione.ordese.AttivitaEsecProdotto;

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
 * 72032	03/07/2025	DSSOF3	 Gestione rilevazione dati
 */

public class CreaLottiTestoriNuovo extends BusinessObjectAdapter implements Authorizable {

	protected String iIdAzienda;

	protected BigDecimal iQuantita;

	protected Integer iNumeroConfezioni;

	protected String iNumeroRocche;

	protected String iLottoAcquisto;

	protected Date iDataScadenza;

	protected String iIdLotto;

	protected String iChiaveSelezionato;

	protected String iNote;

	public String iLottiRilevDatiPrdTS; //Fix 72032

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int save(boolean force) throws SQLException {
		//Fix 72032
		String[] params = getChiaveSelezionato().split(ColonneFiltri.LISTA_SEP);
		String className = params[0];
		String thKey = params[1];
		if(className.contains("RilevDatiPrdTS")) {
			AttivitaEsecProdotto prodotto = (AttivitaEsecProdotto) 
					AttivitaEsecProdotto.elementWithKey(AttivitaEsecProdotto.class, thKey, PersistentObject.NO_LOCK);
			if(prodotto != null) {
				int max = 0;
				Iterator iterLotti = prodotto.getLottiProdotti().iterator();
				while(iterLotti.hasNext()) {
					AttivitaEsecLottiPrd rl = (AttivitaEsecLottiPrd) iterLotti.next();
					if(!rl.getIdLotto().equals(Lotto.LOTTO_DUMMY) && rl.getIdLotto().contains(getIdLotto())) {
						String lottoNoRadice = rl.getIdLotto().replace(getIdLotto(), "");
						try {
							int value = Integer.parseInt(lottoNoRadice);
							if (value > max) {
								max = value;
							}
						} catch (NumberFormatException e) {
							e.printStackTrace(Trace.excStream);
						}
					}
				}
				CreaLottiTestoriTestata testata = (CreaLottiTestoriTestata) Factory.createObject(CreaLottiTestoriTestata.class);

				String lottoPartenza = getIdLotto();
				String maxIdLotto = String.valueOf(max == 0 ? 1 : max);
				Integer progressivo = Integer.valueOf(maxIdLotto);
				BigDecimal numeroConfezioni = BigDecimal.valueOf(getNumeroConfezioni());

				int scale = Q6Calc.get().scale(2);
				BigDecimal qtaPerConfezione = getQuantita().divide(numeroConfezioni, scale, RoundingMode.HALF_UP);
				BigDecimal qtaArrotondata = Articolo.arrotondaQuantita(qtaPerConfezione, prodotto.getArticolo().getUMPrmMag(), prodotto.getArticolo());
				BigDecimal tot = getQuantita();
				BigDecimal resto = tot.subtract(qtaArrotondata.multiply(numeroConfezioni));

				for (int i = 0; i < numeroConfezioni.intValue(); i++) {
					CreaLottiTestoriDettaglio dett = (CreaLottiTestoriDettaglio) Factory.createObject(CreaLottiTestoriDettaglio.class);
					dett.setQuantita(qtaArrotondata);

					dett.setIdLotto(lottoPartenza + String.format("%0" + 4 + "d", progressivo));
					dett.setNumeroRocche(getNumeroRocche());
					testata.getLottiDettaglio().add(dett);
					progressivo++;
				}

				if (resto != null) { 
					List<CreaLottiTestoriDettaglio> lotti = testata.getLottiDettaglio();
					if (!lotti.isEmpty()) {
						CreaLottiTestoriDettaglio ultimoLotto = lotti.get(lotti.size() - 1);
						BigDecimal quantitaCorretto = ultimoLotto.getQuantita().add(resto);
						ultimoLotto.setQuantita(quantitaCorretto);
					}

					progressivo++;
				}
				iterLotti = testata.getLottiDettaglio().iterator();
				List lotti = new ArrayList();
				while(iterLotti.hasNext()) {
					CreaLottiTestoriDettaglio dettaglio = (CreaLottiTestoriDettaglio) iterLotti.next();
					CreaLottiTestoriUtils pal = ((YAttivitaEsecProdotto)prodotto).getCreaProposizioneAutLottoTestori();
					pal.setGeneraCodiceLottoAutomatico(false);
					pal.setIdLotto(dettaglio.getIdLotto());

					List lottiAuto = pal.creaLottiAutomatici();
					if(lottiAuto != null && !lottiAuto.isEmpty()) {
						for (Iterator iterator = lottiAuto.iterator(); iterator.hasNext();) {
							Lotto object = (Lotto) iterator.next();
							lotti.add(object.getCodiceLotto()+ColonneFiltri.SEP+dettaglio.getQuantita());
						}
					}
				}
				iLottiRilevDatiPrdTS = (String.join(KeyHelper.KEY_SEPARATOR, lotti));
			}
		}
		//Fix 72032
		return super.save(force);
	}

	public String getIdAzienda() {
		return iIdAzienda;
	}

	public void setIdAzienda(String iIdAzienda) {
		this.iIdAzienda = iIdAzienda;
	}

	public BigDecimal getQuantita() {
		return iQuantita;
	}

	public void setQuantita(BigDecimal iQuantita) {
		this.iQuantita = iQuantita;
	}

	public Integer getNumeroConfezioni() {
		return iNumeroConfezioni;
	}

	public void setNumeroConfezioni(Integer iNumeroConfezioni) {
		this.iNumeroConfezioni = iNumeroConfezioni;
	}

	public String getNumeroRocche() {
		return iNumeroRocche;
	}

	public void setNumeroRocche(String iNumeroRocche) {
		this.iNumeroRocche = iNumeroRocche;
	}

	public String getLottoAcquisto() {
		return iLottoAcquisto;
	}

	public void setLottoAcquisto(String iLottoAcquisto) {
		this.iLottoAcquisto = iLottoAcquisto;
	}

	public Date getDataScadenza() {
		return iDataScadenza;
	}

	public void setDataScadenza(Date iDataScadenza) {
		this.iDataScadenza = iDataScadenza;
	}

	public String getNote() {
		return iNote;
	}

	public void setNote(String iNote) {
		this.iNote = iNote;
	}

	public String getIdLotto() {
		return iIdLotto;
	}

	public void setIdLotto(String iIdLotto) {
		this.iIdLotto = iIdLotto;
	}

	public String getChiaveSelezionato() {
		return iChiaveSelezionato;
	}

	public void setChiaveSelezionato(String iChiaveSelezionato) {
		this.iChiaveSelezionato = iChiaveSelezionato;
	}

}
