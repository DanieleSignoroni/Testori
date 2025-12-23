package it.testori.thip.produzione.raccoltaDati;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.Factory;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.testori.thip.magazzino.generalemag.YLotto;
import it.testori.thip.produzione.ordese.YAttivitaEsecProdotto;
import it.testori.thip.produzione.ordese.YPersDatiPrdCausaleRilev;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.cs.ColonneFiltri;
import it.thera.thip.cs.DatiComuniEstesi;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.produzione.documento.DocumentoPrdRigaLottoVrs;
import it.thera.thip.produzione.documento.DocumentoPrdRigaVersamento;
import it.thera.thip.produzione.documento.DocumentoProduzione;
import it.thera.thip.produzione.ordese.AttivitaEsecLottiMat;
import it.thera.thip.produzione.ordese.AttivitaEsecMateriale;
import it.thera.thip.produzione.ordese.AttivitaEsecutiva;
import it.thera.thip.produzione.raccoltaDati.RilevDatiPrdTS;
import it.thera.thip.produzione.raccoltaDati.RilevazioneDatiProdTes;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 03/07/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72032    03/07/2025  DSSOF3   Prima stesura
 * 72060	23/07/2025	DSSOF3	 Gestione compilazione dati lotti subbi-pezze
 * 72249	12/12/2025	DSSOF3	 Gestione versamento materiali
 * 72252	15/12/2025	DSSOF3	 Correzione nullPointer per gestione bolla cucita
 * 72267	23/12/2025	DSSOF3	 Aggiunta YQtaPrlUmPrmOrig (1-20).
 */

public class YRilevDatiPrdTS extends RilevDatiPrdTS {

	protected String iLottiFilatiManufatti = null;

	public YRilevDatiPrdTS() {
		super();
	}

	public String getLottiFilatiManufatti() {
		return iLottiFilatiManufatti;
	}

	public void setLottiFilatiManufatti(String iLottiFilatiManufatti) {
		this.iLottiFilatiManufatti = iLottiFilatiManufatti;
	}

	//72267
	@SuppressWarnings("rawtypes")
	public void setYQtaPrlUmPrmOrig(Map map){
		iYQtaPrlUmPrmOrig = map;
	}

	@SuppressWarnings("rawtypes")
	public Map getYQtaPrlUmPrmOrig(){
		return iYQtaPrlUmPrmOrig;
	}

	@SuppressWarnings("unchecked")
	protected void setYQtaPrlUmPrmOrigInternal(BigDecimal qtaPrlUmPrm, int index) {
		index = index + (iCurrentNumPag - 1) * 20;
		if(qtaPrlUmPrm != null)
			iYQtaPrlUmPrmOrig.put(new Integer(index), qtaPrlUmPrm);
		else if (iYQtaPrlUmPrmOrig != null && iYQtaPrlUmPrmOrig.size() > index)
			iYQtaPrlUmPrmOrig.put(new Integer(index), null);
	}

	public void setYQtaPrlUmPrmOrig1(BigDecimal qtaPrlUmPrm) {
		setYQtaPrlUmPrmOrigInternal(qtaPrlUmPrm, 0);
		setDirty();
	}

	public BigDecimal getYQtaPrlUmPrmOrig1() {
		return (BigDecimal)iYQtaPrlUmPrmOrig.get(new Integer(0 + (iCurrentNumPag - 1) * 20));
	}

	public void setYQtaPrlUmPrmOrig2(BigDecimal qtaPrlUmPrm) {
		setYQtaPrlUmPrmOrigInternal(qtaPrlUmPrm, 1);
		setDirty();
	}

	public BigDecimal getYQtaPrlUmPrmOrig2() {
		return (BigDecimal)iYQtaPrlUmPrmOrig.get(new Integer(1 + (iCurrentNumPag - 1) * 20));
	}

	public void setYQtaPrlUmPrmOrig3(BigDecimal qtaPrlUmPrm) {
		setYQtaPrlUmPrmOrigInternal(qtaPrlUmPrm, 2);
		setDirty();
	}

	public BigDecimal getYQtaPrlUmPrmOrig3() {
		return (BigDecimal)iYQtaPrlUmPrmOrig.get(new Integer(2 + (iCurrentNumPag - 1) * 20));
	}

	public void setYQtaPrlUmPrmOrig4(BigDecimal qtaPrlUmPrm) {
		setYQtaPrlUmPrmOrigInternal(qtaPrlUmPrm, 3);
		setDirty();
	}

	public BigDecimal getYQtaPrlUmPrmOrig4() {
		return (BigDecimal)iYQtaPrlUmPrmOrig.get(new Integer(3 + (iCurrentNumPag - 1) * 20));
	}

	public void setYQtaPrlUmPrmOrig5(BigDecimal qtaPrlUmPrm) {
		setYQtaPrlUmPrmOrigInternal(qtaPrlUmPrm, 4);
		setDirty();
	}

	public BigDecimal getYQtaPrlUmPrmOrig5() {
		return (BigDecimal)iYQtaPrlUmPrmOrig.get(new Integer(4 + (iCurrentNumPag - 1) * 20));
	}

	public void setYQtaPrlUmPrmOrig6(BigDecimal qtaPrlUmPrm) {
		setYQtaPrlUmPrmOrigInternal(qtaPrlUmPrm, 5);
		setDirty();
	}

	public BigDecimal getYQtaPrlUmPrmOrig6() {
		return (BigDecimal)iYQtaPrlUmPrmOrig.get(new Integer(5 + (iCurrentNumPag - 1) * 20));
	}

	public void setYQtaPrlUmPrmOrig7(BigDecimal qtaPrlUmPrm) {
		setYQtaPrlUmPrmOrigInternal(qtaPrlUmPrm, 6);
		setDirty();
	}

	public BigDecimal getYQtaPrlUmPrmOrig7() {
		return (BigDecimal)iYQtaPrlUmPrmOrig.get(new Integer(6 + (iCurrentNumPag - 1) * 20));
	}

	public void setYQtaPrlUmPrmOrig8(BigDecimal qtaPrlUmPrm) {
		setYQtaPrlUmPrmOrigInternal(qtaPrlUmPrm, 7);
		setDirty();
	}

	public BigDecimal getYQtaPrlUmPrmOrig8() {
		return (BigDecimal)iYQtaPrlUmPrmOrig.get(new Integer(7 + (iCurrentNumPag - 1) * 20));
	}

	public void setYQtaPrlUmPrmOrig9(BigDecimal qtaPrlUmPrm) {
		setYQtaPrlUmPrmOrigInternal(qtaPrlUmPrm, 8);
		setDirty();
	}

	public BigDecimal getYQtaPrlUmPrmOrig9() {
		return (BigDecimal)iYQtaPrlUmPrmOrig.get(new Integer(8 + (iCurrentNumPag - 1) * 20));
	}

	public void setYQtaPrlUmPrmOrig10(BigDecimal qtaPrlUmPrm) {
		setYQtaPrlUmPrmOrigInternal(qtaPrlUmPrm, 9);
		setDirty();
	}

	public BigDecimal getYQtaPrlUmPrmOrig10() {
		return (BigDecimal)iYQtaPrlUmPrmOrig.get(new Integer(9 + (iCurrentNumPag - 1) * 20));
	}

	public void setYQtaPrlUmPrmOrig11(BigDecimal qtaPrlUmPrm) {
		setYQtaPrlUmPrmOrigInternal(qtaPrlUmPrm, 10);
		setDirty();
	}

	public BigDecimal getYQtaPrlUmPrmOrig11() {
		return (BigDecimal)iYQtaPrlUmPrmOrig.get(new Integer(10 + (iCurrentNumPag - 1) * 20));
	}

	public void setYQtaPrlUmPrmOrig12(BigDecimal qtaPrlUmPrm) {
		setYQtaPrlUmPrmOrigInternal(qtaPrlUmPrm, 11);
		setDirty();
	}

	public BigDecimal getYQtaPrlUmPrmOrig12() {
		return (BigDecimal)iYQtaPrlUmPrmOrig.get(new Integer(11 + (iCurrentNumPag - 1) * 20));
	}

	public void setYQtaPrlUmPrmOrig13(BigDecimal qtaPrlUmPrm) {
		setYQtaPrlUmPrmOrigInternal(qtaPrlUmPrm, 12);
		setDirty();
	}

	public BigDecimal getYQtaPrlUmPrmOrig13() {
		return (BigDecimal)iYQtaPrlUmPrmOrig.get(new Integer(12 + (iCurrentNumPag - 1) * 20));
	}

	public void setYQtaPrlUmPrmOrig14(BigDecimal qtaPrlUmPrm) {
		setYQtaPrlUmPrmOrigInternal(qtaPrlUmPrm, 13);
		setDirty();
	}

	public BigDecimal getYQtaPrlUmPrmOrig14() {
		return (BigDecimal)iYQtaPrlUmPrmOrig.get(new Integer(13 + (iCurrentNumPag - 1) * 20));
	}

	public void setYQtaPrlUmPrmOrig15(BigDecimal qtaPrlUmPrm) {
		setYQtaPrlUmPrmOrigInternal(qtaPrlUmPrm, 14);
		setDirty();
	}

	public BigDecimal getYQtaPrlUmPrmOrig15() {
		return (BigDecimal)iYQtaPrlUmPrmOrig.get(new Integer(14 + (iCurrentNumPag - 1) * 20));
	}

	public void setYQtaPrlUmPrmOrig16(BigDecimal qtaPrlUmPrm) {
		setYQtaPrlUmPrmOrigInternal(qtaPrlUmPrm, 15);
		setDirty();
	}

	public BigDecimal getYQtaPrlUmPrmOrig16() {
		return (BigDecimal)iYQtaPrlUmPrmOrig.get(new Integer(15 + (iCurrentNumPag - 1) * 20));
	}

	public void setYQtaPrlUmPrmOrig17(BigDecimal qtaPrlUmPrm) {
		setYQtaPrlUmPrmOrigInternal(qtaPrlUmPrm, 16);
		setDirty();
	}

	public BigDecimal getYQtaPrlUmPrmOrig17() {
		return (BigDecimal)iYQtaPrlUmPrmOrig.get(new Integer(16 + (iCurrentNumPag - 1) * 20));
	}

	public void setYQtaPrlUmPrmOrig18(BigDecimal qtaPrlUmPrm) {
		setYQtaPrlUmPrmOrigInternal(qtaPrlUmPrm, 17);
		setDirty();
	}

	public BigDecimal getYQtaPrlUmPrmOrig18() {
		return (BigDecimal)iYQtaPrlUmPrmOrig.get(new Integer(17 + (iCurrentNumPag - 1) * 20));
	}

	public void setYQtaPrlUmPrmOrig19(BigDecimal qtaPrlUmPrm) {
		setYQtaPrlUmPrmOrigInternal(qtaPrlUmPrm, 18);
		setDirty();
	}

	public BigDecimal getYQtaPrlUmPrmOrig19() {
		return (BigDecimal)iYQtaPrlUmPrmOrig.get(new Integer(18 + (iCurrentNumPag - 1) * 20));
	}

	public void setYQtaPrlUmPrmOrig20(BigDecimal qtaPrlUmPrm) {
		setYQtaPrlUmPrmOrigInternal(qtaPrlUmPrm, 19);
		setDirty();
	}

	public BigDecimal getYQtaPrlUmPrmOrig20() {
		return (BigDecimal)iYQtaPrlUmPrmOrig.get(new Integer(19 + (iCurrentNumPag - 1) * 20));
	}
	//72267

	@Override
	public int SaveSospensioneFine() throws SQLException {
		for (int i=0; i < getProdotti().size(); i++) {
			Articolo prodotto = (Articolo) getProdotti().get(new Integer(i));
			if(prodotto != null && prodotto.getIdArticolo() != null && CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(prodotto, CreaLottiTestoriUtils.PRODUZIONE)){
				Lotto lottiPrd = (Lotto) getLottiProdotto().get(new Integer(i));
				lottiPrd.retrieve();
				if(lottiPrd != null && !lottiPrd.isOnDB()
						&& getAttivitaEsecutiva() != null //72252
						&& getAttivitaEsecutiva().getAtvEsecPrdPrimario() != null) { //72252
					CreaLottiTestoriUtils pal = ((YAttivitaEsecProdotto)getAttivitaEsecutiva().getAtvEsecPrdPrimario()).getCreaProposizioneAutLottoTestori();
					pal.setGeneraCodiceLottoAutomatico(false);
					pal.setIdLotto(lottiPrd.getCodiceLotto());
					pal.creaLottiAutomatici();
				}
			}
		}
		int rc = super.SaveSospensioneFine();
		return rc;
	}

	//72060 Inizio

	@Override
	public int convalidaRilevazione(RilevazioneDatiProdTes testata, int rc, boolean genDoc) {
		int rcConvalida = super.convalidaRilevazione(testata, rc, genDoc);
		if(rcConvalida > 0) {
			try {
				DocumentoProduzione obj = iRilevazioneTesFine.getDocumentoProduzione();
				if(obj != null) {
					for (int i=0; i < getProdotti().size(); i++) {
						Articolo prodotto = (Articolo) getProdotti().get(new Integer(i));
						if(prodotto != null
								&& (CreaLottiTestoriUtils.isArticoloGestionePezze(prodotto, CreaLottiTestoriUtils.PRODUZIONE)
										|| CreaLottiTestoriUtils.isArticoloGestioneSubbio(prodotto, CreaLottiTestoriUtils.PRODUZIONE))) {
							Lotto lottiPrd = (Lotto) getLottiProdotto().get(new Integer(i));
							lottiPrd.retrieve();
							//.Se subbio-pezza riporto la macchina di lavorazione sul lotto e la pezza greggia sul lotto
							if(lottiPrd != null && lottiPrd.isOnDB() && lottiPrd instanceof YLotto) {
								((YLotto)lottiPrd).setTelaio(obj.getRisorsa());
								((YLotto)lottiPrd).setPezzaGreggia(trovaPezzaGreggia(this));
								((YLotto)lottiPrd).setQuantitaOriginale((BigDecimal) getQtaVrsUmPrm().get(new Integer(i)));
								rcConvalida += ((YLotto)lottiPrd).save();
							}
						}
					}
				}
			}catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return rcConvalida;
	}

	@SuppressWarnings("rawtypes")
	protected String trovaPezzaGreggia(RilevDatiPrdTS bo) {
		try {
			for (int i = 1; i < 21; i++) {
				String getIdMateriale = "getMateriale" + String.valueOf(i);
				String getIdLotto = "getIdLotto" + String.valueOf(i);
				String getIdMagazzinoPrl = "getIdMagazzinoPrl" + String.valueOf(i);
				//String getIdConfigurazione = "getIdConfigurazione" + String.valueOf(i);
				//String getIdVersione = "getIdVersione" + String.valueOf(i);
				Class<?> c = Factory.getClass(bo.getClass());
				Method idMatMethod = c.getMethod(getIdMateriale, new Class[0]);
				Method idLotMethod = c.getMethod(getIdLotto, new Class[0]);
				Method idMagPrlMethod = c.getMethod(getIdMagazzinoPrl, new Class[0]);
				//Method idConfigMethod = c.getMethod(getIdConfigurazione, new Class[0]);
				//Method idVersMethod = c.getMethod(getIdVersione, new Class[0]);
				Object objMat = null;
				objMat = idMatMethod.invoke(bo, new Object[0]);
				Object objLot = idLotMethod.invoke(bo, new Object[0]);
				Object objMag = idMagPrlMethod.invoke(bo, new Object[0]);
				//Object objConfig = idConfigMethod.invoke(bo, new Object[0]);
				//Object objVers = idVersMethod.invoke(bo, new Object[0]);
				if (objMat != null && objLot != null && objMag != null) {
					Articolo mat = (Articolo) objMat;
					//String idMagPrl = (String) objMag;
					String idLot = (String) objLot;
					if((CreaLottiTestoriUtils.isArticoloGestionePezze(mat, CreaLottiTestoriUtils.PRODUZIONE)
							|| CreaLottiTestoriUtils.isArticoloGestioneSubbiFeltri(mat, CreaLottiTestoriUtils.PRODUZIONE))) {
						return idLot;
					}
				}
			}
			Iterator iterAtv = bo.getAttivitaEsecutiva().getOrdineEsecutivo().getAttivitaEsecutive().iterator();
			while(iterAtv.hasNext()) {
				AttivitaEsecutiva atvEsec = (AttivitaEsecutiva) iterAtv.next();
				Iterator iterMats = atvEsec.getMateriali().iterator();
				while(iterMats.hasNext()) {
					AttivitaEsecMateriale mat = (AttivitaEsecMateriale) iterMats.next();
					Iterator iterLots = mat.getLottiMateriali().iterator();
					while(iterLots.hasNext()) {
						AttivitaEsecLottiMat lottoMat = (AttivitaEsecLottiMat) iterLots.next();
						if((CreaLottiTestoriUtils.isArticoloGestionePezze(mat.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)
								|| CreaLottiTestoriUtils.isArticoloGestioneSubbiFeltri(mat.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE))){
							return lottoMat.getIdLotto();
						}
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace(Trace.excStream);
		}
		return null;
	}

	//72060 Fine

	@Override
	public String getJspName() {
		//72249
		if(YPersDatiPrdCausaleRilev.isGestioneVersamentoMateriali(getPersDatiPrdCausaleRilev())){
			return "it/testori/thip/produzione/raccoltaDati/YDichiarazioneVrsMateriali.jsp";
		}else {
			return super.getJspName();
		}
		////72249
	}

	@Override
	public ErrorMessage checkIdLottoProdotto1() {
		ErrorMessage em = super.checkIdLottoProdotto1();
		if(em != null
				&& CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE) && iLottiFilatiManufatti != null
				&& !iLottiFilatiManufatti.isEmpty() 
				&& em.getId().equals("BAS0000000")) {
			em = null;
		}
		return em;
	}

	@Override
	public ErrorMessage checkIdLottoProdotto2() {
		ErrorMessage em = super.checkIdLottoProdotto2();
		if(em != null
				&& CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE) && iLottiFilatiManufatti != null
				&& !iLottiFilatiManufatti.isEmpty() 
				&& em.getId().equals("BAS0000000")) {
			em = null;
		}
		return em;
	}

	@Override
	public ErrorMessage checkIdLottoProdotto3() {
		ErrorMessage em = super.checkIdLottoProdotto3();
		if(em != null
				&& CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE) && iLottiFilatiManufatti != null
				&& !iLottiFilatiManufatti.isEmpty() 
				&& em.getId().equals("BAS0000000")) {
			em = null;
		}
		return em;
	}

	@Override
	public ErrorMessage checkIdLottoProdotto4() {
		ErrorMessage em = super.checkIdLottoProdotto4();
		if(em != null
				&& CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE) && iLottiFilatiManufatti != null
				&& !iLottiFilatiManufatti.isEmpty() 
				&& em.getId().equals("BAS0000000")) {
			em = null;
		}
		return em;
	}

	@Override
	public ErrorMessage checkIdLottoProdotto5() {
		ErrorMessage em = super.checkIdLottoProdotto5();
		if(em != null
				&& CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE) && iLottiFilatiManufatti != null
				&& !iLottiFilatiManufatti.isEmpty() 
				&& em.getId().equals("BAS0000000")) {
			em = null;
		}
		return em;
	}

	@Override
	public ErrorMessage checkIdLottoProdotto6() {
		ErrorMessage em = super.checkIdLottoProdotto6();
		if(em != null
				&& CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE) && iLottiFilatiManufatti != null
				&& !iLottiFilatiManufatti.isEmpty() 
				&& em.getId().equals("BAS0000000")) {
			em = null;
		}
		return em;
	}

	@Override
	public ErrorMessage checkIdLottoProdotto7() {
		ErrorMessage em = super.checkIdLottoProdotto7();
		if(em != null
				&& CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE) && iLottiFilatiManufatti != null
				&& !iLottiFilatiManufatti.isEmpty() 
				&& em.getId().equals("BAS0000000")) {
			em = null;
		}
		return em;
	}

	@Override
	public ErrorMessage checkIdLottoProdotto8() {
		ErrorMessage em = super.checkIdLottoProdotto8();
		if(em != null
				&& CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE) && iLottiFilatiManufatti != null
				&& !iLottiFilatiManufatti.isEmpty() 
				&& em.getId().equals("BAS0000000")) {
			em = null;
		}
		return em;
	}

	@Override
	public ErrorMessage checkIdLottoProdotto9() {
		ErrorMessage em = super.checkIdLottoProdotto9();
		if(em != null
				&& CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE) && iLottiFilatiManufatti != null
				&& !iLottiFilatiManufatti.isEmpty() 
				&& em.getId().equals("BAS0000000")) {
			em = null;
		}
		return em;
	}

	@Override
	public ErrorMessage checkIdLottoProdotto10() {
		ErrorMessage em = super.checkIdLottoProdotto10();
		if(em != null
				&& CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE) && iLottiFilatiManufatti != null
				&& !iLottiFilatiManufatti.isEmpty() 
				&& em.getId().equals("BAS0000000")) {
			em = null;
		}
		return em;
	}

	@Override
	public ErrorMessage checkIdLottoProdotto11() {
		ErrorMessage em = super.checkIdLottoProdotto11();
		if(em != null
				&& CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE) && iLottiFilatiManufatti != null
				&& !iLottiFilatiManufatti.isEmpty() 
				&& em.getId().equals("BAS0000000")) {
			em = null;
		}
		return em;
	}


	@Override
	public ErrorMessage checkIdLottoProdotto12() {
		ErrorMessage em = super.checkIdLottoProdotto12();
		if(em != null
				&& CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE) && iLottiFilatiManufatti != null
				&& !iLottiFilatiManufatti.isEmpty() 
				&& em.getId().equals("BAS0000000")) {
			em = null;
		}
		return em;
	}


	@Override
	public ErrorMessage checkIdLottoProdotto13() {
		ErrorMessage em = super.checkIdLottoProdotto13();
		if(em != null
				&& CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE) && iLottiFilatiManufatti != null
				&& !iLottiFilatiManufatti.isEmpty() 
				&& em.getId().equals("BAS0000000")) {
			em = null;
		}
		return em;
	}

	@Override
	public ErrorMessage checkIdLottoProdotto14() {
		ErrorMessage em = super.checkIdLottoProdotto14();
		if(em != null
				&& CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE) && iLottiFilatiManufatti != null
				&& !iLottiFilatiManufatti.isEmpty() 
				&& em.getId().equals("BAS0000000")) {
			em = null;
		}
		return em;
	}

	@Override
	public ErrorMessage checkIdLottoProdotto15() {
		ErrorMessage em = super.checkIdLottoProdotto15();
		if(em != null
				&& CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE) && iLottiFilatiManufatti != null
				&& !iLottiFilatiManufatti.isEmpty() 
				&& em.getId().equals("BAS0000000")) {
			em = null;
		}
		return em;
	}

	@Override
	public ErrorMessage checkIdLottoProdotto16() {
		ErrorMessage em = super.checkIdLottoProdotto16();
		if(em != null
				&& CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE) && iLottiFilatiManufatti != null
				&& !iLottiFilatiManufatti.isEmpty() 
				&& em.getId().equals("BAS0000000")) {
			em = null;
		}
		return em;
	}

	@Override
	public ErrorMessage checkIdLottoProdotto17() {
		ErrorMessage em = super.checkIdLottoProdotto17();
		if(em != null
				&& CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE) && iLottiFilatiManufatti != null
				&& !iLottiFilatiManufatti.isEmpty() 
				&& em.getId().equals("BAS0000000")) {
			em = null;
		}
		return em;
	}

	@Override
	public ErrorMessage checkIdLottoProdotto18() {
		ErrorMessage em = super.checkIdLottoProdotto18();
		if(em != null
				&& CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE) && iLottiFilatiManufatti != null
				&& !iLottiFilatiManufatti.isEmpty() 
				&& em.getId().equals("BAS0000000")) {
			em = null;
		}
		return em;
	}

	@Override
	public ErrorMessage checkIdLottoProdotto19() {
		ErrorMessage em = super.checkIdLottoProdotto19();
		if(em != null
				&& CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE) && iLottiFilatiManufatti != null
				&& !iLottiFilatiManufatti.isEmpty() 
				&& em.getId().equals("BAS0000000")) {
			em = null;
		}
		return em;
	}

	@Override
	public ErrorMessage checkIdLottoProdotto20() {
		ErrorMessage em = super.checkIdLottoProdotto20();
		if(em != null
				&& CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(getArticolo(), CreaLottiTestoriUtils.PRODUZIONE) && iLottiFilatiManufatti != null
				&& !iLottiFilatiManufatti.isEmpty() 
				&& em.getId().equals("BAS0000000")) {
			em = null;
		}
		return em;
	}

	//72252
	@SuppressWarnings("rawtypes")
	public String recuperaChiaviOggettoLottoSubbioPezzaDaMostrare() {
		if (iRilevazioneTesFine == null || iRilevazioneTesFine.getStato() != DatiComuniEstesi.VALIDO)
			return null;
		else if(iRilevazioneTesFine.getDocumentoProduzione() != null){
			DocumentoProduzione docPrd = iRilevazioneTesFine.getDocumentoProduzione();
			String keys = null;
			if (docPrd != null) {
				Iterator prodotti = docPrd.getVersamentiColl().iterator();
				while(prodotti.hasNext()) {
					DocumentoPrdRigaVersamento prod = (DocumentoPrdRigaVersamento) prodotti.next();
					Iterator iterLottiPrdo = prod.getLottiColl().iterator();
					while(iterLottiPrdo.hasNext()) {
						DocumentoPrdRigaLottoVrs lotto = (DocumentoPrdRigaLottoVrs) iterLottiPrdo.next();
						if(keys == null)
							keys = lotto.getLottoKey();
						else
							keys += ColonneFiltri.SEP + lotto.getLottoKey();
					}
				}
			}
			return keys;
		}else {
			return null;
		}
	}
	//72252

}