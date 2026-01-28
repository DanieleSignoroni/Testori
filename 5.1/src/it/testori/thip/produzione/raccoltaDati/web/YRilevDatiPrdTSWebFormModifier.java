package it.testori.thip.produzione.raccoltaDati.web;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
//import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import com.thera.thermfw.base.Q6Calc;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.testori.thip.produzione.ordese.TipoTaglioPezza;
import it.testori.thip.produzione.ordese.YAttivitaEsecMateriale;
import it.testori.thip.produzione.ordese.YAttivitaEsecutiva;
import it.testori.thip.produzione.ordese.YPersDatiPrdCausaleRilev;
import it.testori.thip.produzione.raccoltaDati.YRilevDatiPrdTS;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.cs.ColonneFiltri;
import it.thera.thip.cs.DatiComuniEstesi;
import it.thera.thip.magazzino.generalemag.Lotto;
//import it.thera.thip.produzione.documento.CausaleDocProduzione;
import it.thera.thip.produzione.ordese.AttivitaEsecLottiMat;
import it.thera.thip.produzione.ordese.AttivitaEsecMateriale;
//import it.thera.thip.produzione.documento.CausaleDocProduzione;
//import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.produzione.ordese.AttivitaEsecProdotto;
import it.thera.thip.produzione.ordese.AttivitaEsecutiva;
import it.thera.thip.produzione.ordese.PersDatiPrdCausaleRilev;
//import it.thera.thip.produzione.ordese.PersDatiPrdCausaleRilev;
import it.thera.thip.produzione.raccoltaDati.RilevDatiPrdTS;
import it.thera.thip.produzione.raccoltaDati.web.RilevDatiPrdTSFormActionAdapter;
import it.thera.thip.produzione.raccoltaDati.web.RilevDatiPrdTSWebFormModifier;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 26/06/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72032    26/06/2025  DSSOF3   Prima stesura
 * 72060	23/07/2025	DSSOF3 	 Gestire apertura lotto (subbio-pezza) in fase di termine dichiarazione versamenti
 * 72065	23/07/2025	DSSOF3   Gestione apertura/chiusura automatica attivita'.
 * 72226	25/11/2025	DSSOF3   Gestione visualizzazione coerente dichiarazione versamenti
 * 72249	10/12/2025	DSSOF3	 Remmare fix precedente, gestione versamento materiali
 * 72269	06/01/2026	DSSOF3	 Nuova gestione lotti feltri
 * 72298	16/01/2026	DSSOF3	 Gestione richiesta pezza greggia se prelevati piu lotti.
 * 72300	19/01/2026	DSSOF3	 Gestione pezze (taglio in larghezza).
 * 72304	20/01/2026	DSSOF3	 Gestione pezze (taglio in larghezza), non svuotare parametro per poi non aprire le form al fine dichiarazione
 * 72320	28/01/2026	DSSOF3	 Gestione richiesta pezza greggia se prelevati piu lotti, includere subbi feltri come pezze greggie scatenanti, migliorie codice.
 */

public class YRilevDatiPrdTSWebFormModifier extends RilevDatiPrdTSWebFormModifier {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void displayLottiFields(JspWriter out) {
		super.displayLottiFields(out);
		try {
			RilevDatiPrdTS bo = (RilevDatiPrdTS) getBODataCollector().getBo();
			if (bo != null  && bo.getIdProdotto1() != null) {
				for (int i = 1; i < 21; i++) {
					String getterPrd = "getProdotto" + String.valueOf(i);
					Class c = Factory.getClass(bo.getClass());
					Method prdMethod = c.getMethod(getterPrd, new Class<?>[0]);
					Articolo prodotto = (Articolo) prdMethod.invoke(bo, new Object[0]);
					boolean gesLottiTestori = false;
					if(prodotto != null 
							&& prodotto.getIdArticolo() != null 
							&& (CreaLottiTestoriUtils.isArticoloGestionePezze(prodotto, CreaLottiTestoriUtils.PRODUZIONE)
									|| CreaLottiTestoriUtils.isArticoloGestioneSubbiFeltri(prodotto, CreaLottiTestoriUtils.PRODUZIONE))) {
						gesLottiTestori = true;
					}
					out.println(" var creaLottiTestoriAutomatico"+i+" = "+gesLottiTestori+";");
					if(CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(bo.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)) {
						out.println("document.getElementById('CreaLottoBut"+i+"').style.display=displayNone;");
						out.println(" var ymostraBottoneCreaLottiStd"+i+" = "+false+";");
					}else if(CreaLottiTestoriUtils.isArticoloGestioneSubbiFeltri(bo.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)
							|| CreaLottiTestoriUtils.isArticoloGestionePezze(bo.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)) {
						out.println("document.getElementById('CreaLottoBut"+i+"').style.display=displayBlock;");
					}
				}
			}
			if(!CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(bo.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)) {
				out.println("document.getElementById('CreaLottiFilatiManufatti').style.display = displayNone;");
			}
		} catch (Exception e) {
			e.printStackTrace(Trace.excStream);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List recuperaLotti(AttivitaEsecProdotto prodotto, BigDecimal quantita) {
		List lotti = super.recuperaLotti(prodotto, quantita);
		YRilevDatiPrdTS bo = (YRilevDatiPrdTS) getRequest().getAttribute("myObject");
		if(bo == null) {
			return lotti;
		}
		if(CreaLottiTestoriUtils.isArticoloGestioneFilatiManufatti(bo.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)) {
			lotti.clear();
			if(bo.getLottiFilatiManufatti() != null && !bo.getLottiFilatiManufatti().isEmpty()) {
				String[] lottiArr = bo.getLottiFilatiManufatti().split(KeyHelper.KEY_SEPARATOR);
				for(String lottoStr : lottiArr) {
					String[] values = lottoStr.split(ColonneFiltri.SEP);
					lotti.add(lotto(values[0], new BigDecimal(values[1])));
				}
				bo.setLottiFilatiManufatti(null);
			}else {
				lotti.add(lotto(Lotto.LOTTO_DUMMY, quantita));
			}
		}else if(CreaLottiTestoriUtils.isArticoloGestioneFeltri(bo.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)) {
			lotti.clear();
			lotti.add(lotto(Lotto.LOTTO_DUMMY, quantita));
		}else if(CreaLottiTestoriUtils.isArticoloGestionePezze(bo.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)) {
			lotti.clear();
			if(bo.getLottiPezzeTaglioLarghezza() != null && !bo.getLottiPezzeTaglioLarghezza().isEmpty()) {
				String[] lottiArr = bo.getLottiPezzeTaglioLarghezza().split(KeyHelper.KEY_SEPARATOR);
				for(String lottoStr : lottiArr) {
					String[] values = lottoStr.split(ColonneFiltri.SEP);
					lotti.add(lotto(values[0], bo.getQtaVrsUmPrm1()));
				}
			}else {
				lotti.add(lotto(Lotto.LOTTO_DUMMY, quantita));
			}
		}
		return lotti;
	}

	/**
	 * Metodo di utilita' che fornisce un array di oggetti da usare nella logica di questa classe.
	 * @param idLotto
	 * @param quantita
	 * @return un array con alla prima posizione una stringa, un bigdecimal alla seconda
	 */
	public static Object[] lotto(String idLotto, BigDecimal quantita) {
		Object[] obj = new Object[2];
		obj[0] = idLotto;
		obj[1] = quantita;
		return obj;
	}

	@Override
	public void writeHeadElements(JspWriter out) throws IOException {
		super.writeHeadElements(out);
		RilevDatiPrdTS rilevDatiPrdTS = (RilevDatiPrdTS) getRequest().getAttribute("myObject");
		//72249
		if(rilevDatiPrdTS != null 
				&& YPersDatiPrdCausaleRilev.isGestioneVersamentoMateriali(rilevDatiPrdTS.getPersDatiPrdCausaleRilev())){
			rilevDatiPrdTS = initRigheMateriali(rilevDatiPrdTS, out);
			out.println("<script>");
			riempiMaterialeConUMSec(rilevDatiPrdTS, out);
			out.println("</script>");
			getServletEnvironment().getSession().setAttribute("RilevDatiPrdTSOldObject", rilevDatiPrdTS);
			getBODataCollector().setBo(rilevDatiPrdTS);
		}
		//72249
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void caricaRigheMateriali(RilevDatiPrdTS bo, JspWriter out) {
		//72249
		if(bo != null 
				&& YPersDatiPrdCausaleRilev.isGestioneVersamentoMateriali(bo.getPersDatiPrdCausaleRilev())){
			String action = (String) getRequest().getAttribute("Action");
			if(action != null && action.equals(YRilevDatiPrdTSFormActionAdapter.VEDI_LOTTI_VERS_MATERIALI)) {
				//se sono qui compilo solo le righe con i lotti mantenendo le quantita' originali
				inizializzaLottiVersatiMateriale(bo,out);
				return;
			}
			int i = 1;
			List listMaterialiValido = new ArrayList() ;
			Iterator iterAtv = bo.getAttivitaEsecutiva().getOrdineEsecutivo().getAttivitaEsecutive().iterator();
			while(iterAtv.hasNext()) {
				AttivitaEsecutiva atv = (AttivitaEsecutiva) iterAtv.next();
				List tmp = atv.getMateriali();
				Iterator it = tmp.iterator();
				while(it.hasNext()) {
					AttivitaEsecMateriale atvEsecMat = (AttivitaEsecMateriale) it.next();
					if(atvEsecMat.getStato() == DatiComuniEstesi.VALIDO) {
						listMaterialiValido.add(atvEsecMat);
					}
				}
			}
			//Fix 32846 -- Fine
			//List materiali = verificaMateriali(bo.getAttivitaEsecutiva().getMateriali(), bo.getPersDatiPrdCausaleRilev()); //Fix 32846
			List materiali = verificaMateriali(listMaterialiValido, bo.getPersDatiPrdCausaleRilev()); //Fix 32846
			if (!materiali.isEmpty()) {
				Iterator iterAtvMat = materiali.iterator();
				try {
					while (iterAtvMat.hasNext()) {
						AttivitaEsecMateriale atvEsecMat = (AttivitaEsecMateriale) iterAtvMat.next();
						Object[] result = initRigaDaOrdineEsec(bo, atvEsecMat, i);
						bo = (RilevDatiPrdTS) result[0];
						i = ((Integer) result[1]).intValue();
					}
					out.println("<script language='JavaScript1.2'>");
					out.println("inizialeTotRighe = " + (i - 1) + ";");
					out.println("lastCurrentLine = " + (i - 1) + ";");
					out.println("lastCurrentLine = (lastCurrentLine < 1) ? 1 : lastCurrentLine;");		//Fix 25976
					out.println("totRighe = " + (i - 1) + ";");
					if (bo.getCurrentNumPag() > 1) {
						iTotaliRigheInitial = ((bo.getCurrentNumPag() - 1) * 20) + (i - 1);
						out.println("totRighe = " + iTotaliRigheInitial + ";");
						out.println("inizialeTotRighe = 20;");
						out.println("lastCurrentLine = 20;");
						out.println("totRighe = 20;");
					}
					out.println("</script>");
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
				if (action != null && action.equals(RilevDatiPrdTSFormActionAdapter.SUCCESSIVO))
					bo.setCurrentNumPag(1);
			}
			else { //Fix 42153 --inizio
				try {
					out.println("<script language='JavaScript1.2'>");
					out.println("inizialeTotRighe = " + (i - 1) + ";");
					out.println("lastCurrentLine = " + (i - 1) + ";");
					out.println("lastCurrentLine = (lastCurrentLine < 1) ? 1 : lastCurrentLine;");
					out.println("totRighe = " + (i - 1) + ";");
					if (bo.getCurrentNumPag() > 1) {
						iTotaliRigheInitial = ((bo.getCurrentNumPag() - 1) * 20) + (i - 1);
						out.println("totRighe = " + iTotaliRigheInitial + ";");
						out.println("inizialeTotRighe = 20;");
						out.println("lastCurrentLine = 20;");
						out.println("totRighe = 20;");
					}
					out.println("</script>");
				}
				catch (Exception ex) {
					ex.printStackTrace(Trace.excStream);
				}
			} //Fix 42153 --fine
		}else {
			super.caricaRigheMateriali(bo, out);
		}
		//72249
	}

	//72249
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void inizializzaLottiVersatiMateriale(RilevDatiPrdTS bo, JspWriter out) {
		if(bo.getMateriali().size() == 0)
			return;
		Articolo materiale = null;
		int index = 0;
		for (int i=0; i < bo.getMateriali().size(); i++) {
			Articolo art = (Articolo) bo.getMateriali().get(new Integer(i));
			if(art.getIdArticolo().equals(bo.getNote())) {
				index = i;
				materiale = art;
				break;
			}
		}
		BigDecimal qta = (BigDecimal) bo.getQtaPrlUmPrm().get(new Integer(index));
		if(materiale != null) {
			AttivitaEsecMateriale atvEsecMat = YAzioneConfermaVersamentoLottiMat.recuperaMaterialeInOrdine(bo.getAttivitaEsecutiva().getOrdineEsecutivo(), materiale);
			if(atvEsecMat != null) {
				List lottiOrdine = new ArrayList();
				List lottiRig = atvEsecMat.getLottiMateriali();
				for (int i = 0; i < lottiRig.size(); i++) {
					AttivitaEsecLottiMat lt = (AttivitaEsecLottiMat) lottiRig.get(i);
					if (!lt.getIdLotto().equals(Lotto.LOTTO_DUMMY)) {
						lottiOrdine.add(lt);
					}
				}
				Object[] result = new Object[2];
				try {
					int i = 1;
					Iterator iter = lottiOrdine.iterator();
					while (iter.hasNext()) {
						AttivitaEsecLottiMat lottomat = (AttivitaEsecLottiMat) iter.next();
						String setterPrd = "setIdMateriale" + String.valueOf(i);
						String setterVers = "setIdVersione" + String.valueOf(i);
						String setterCfg = "setIdConfigurazione" + String.valueOf(i);
						String setterMag = "setIdMagazzinoPrl" + String.valueOf(i);
						String setterQta = "setQtaPrlUmPrm" + String.valueOf(i);
						String setterQtaSec = "setQtaPrlUmSec" + String.valueOf(i);
						String setterIdLot = "setIdLotto" + String.valueOf(i);
						String setterDescMat = "setDescrizioneMateriale" + String.valueOf(i);

						//Fix 26027 inizio
						String setterEster = "setIdEsternoConfig"+String.valueOf(i);
						//Fix 26027 fine

						Class c = Factory.getClass(bo.getClass());
						Method matMethod = c.getMethod(setterPrd, new Class[] { String.class });
						Method versMethod = c.getMethod(setterVers, new Class[] { Integer.class });
						Method cfgMethod = c.getMethod(setterCfg, new Class[] { Integer.class });
						Method magMethod = c.getMethod(setterMag, new Class[] { String.class });
						Method qtaMethod = c.getMethod(setterQta, new Class[] { BigDecimal.class });
						Method qtaSecMethod = c.getMethod(setterQtaSec, new Class[] { BigDecimal.class });
						Method idLotMethod = c.getMethod(setterIdLot, new Class[] { String.class });
						Method descMatMethod = c.getMethod(setterDescMat, new Class[] { String.class });

						//Fix 26027 inizio
						Method EsternMethod = c.getMethod(setterEster, new Class[] { String.class });
						EsternMethod.invoke(bo, new Object[] { atvEsecMat.getIdEsternoConfig()});
						//Fix 26027 fine

						matMethod.invoke(bo, new Object[] { atvEsecMat.getIdArticolo() });
						versMethod.invoke(bo, new Object[] { atvEsecMat.getIdVersione() });
						cfgMethod.invoke(bo, new Object[] { atvEsecMat.getIdConfigurazione() });
						magMethod.invoke(bo, new Object[] { atvEsecMat.getIdMagazzinoPrl() });
						qtaMethod.invoke(bo, new Object[] { qta });
						Articolo articolo = atvEsecMat.getArticolo();
						if (articolo != null && articolo.getIdUMSecMag() != null) {
							BigDecimal qtaSec = articolo.convertiUM(qta, articolo.getUMPrmMag(), articolo.getUMSecMag(),
									atvEsecMat.getVersione());
							if (qtaSec != null) //Fix 39755
								qtaSec = Q6Calc.get().setScale(qtaSec, 2, BigDecimal.ROUND_HALF_UP); //Fix 39755
							qtaSecMethod.invoke(bo, new Object[] { qtaSec });
						}
						if (lottomat.getIdLotto() != null)
							idLotMethod.invoke(bo, new Object[] { lottomat.getIdLotto() });

						if (atvEsecMat.getDescrizione() != null) {
							descMatMethod.invoke(bo, new Object[] { atvEsecMat.getDescrizione().getDescrizione() });
						}
						i++;
						if (i > 20) {
							bo.setCurrentNumPag((i / 20) + bo.getCurrentNumPag());
							bo.setIdAzienda(bo.getIdAzienda());
							i = 1;
						}
					}
					result[0] = bo;
					result[1] = new Integer(i);
					out.println("<script language='JavaScript1.2'>");
					out.println("inizialeTotRighe = " + (i - 1) + ";");
					out.println("lastCurrentLine = " + (i - 1) + ";");
					out.println("lastCurrentLine = (lastCurrentLine < 1) ? 1 : lastCurrentLine;");
					out.println("totRighe = " + (i - 1) + ";");
					if (bo.getCurrentNumPag() > 1) {
						iTotaliRigheInitial = ((bo.getCurrentNumPag() - 1) * 20) + (i - 1);
						out.println("totRighe = " + iTotaliRigheInitial + ";");
						out.println("inizialeTotRighe = 20;");
						out.println("lastCurrentLine = 20;");
						out.println("totRighe = 20;");
					}
					out.println("</script>");
				}
				catch (Exception ex) {
					ex.printStackTrace(Trace.excStream);
				}
			}
		}
	}
	//72249

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List verificaMateriali(List materiali, PersDatiPrdCausaleRilev persDatiPrdCausaleRilev) {
		RilevDatiPrdTS rilevDatiPrdTS = (RilevDatiPrdTS) getRequest().getAttribute("myObject");
		String action = (String) getRequest().getAttribute("Action");
		List materialiVerificati = super.verificaMateriali(materiali, persDatiPrdCausaleRilev);
		//72249
		if(YPersDatiPrdCausaleRilev.isGestioneVersamentoMateriali(persDatiPrdCausaleRilev)){

			//..Verifico che il materiale sia stato prelevato
			List mat = new ArrayList();
			Iterator matIter = materiali.iterator();
			while (matIter.hasNext()) {
				YAttivitaEsecMateriale materiale = (YAttivitaEsecMateriale)matIter.next();
				if(YRilevDatiPrdTSFormActionAdapter.VEDI_LOTTI_VERS_MATERIALI.equals(action)
						&& rilevDatiPrdTS != null && rilevDatiPrdTS.getNote() != null) {
					if(!materiale.getIdArticolo().equals(rilevDatiPrdTS.getNote()))
						continue;
					else
						mat.add(materiale);
				}else if(materiale.materialePrelevato()) {
					mat.add(materiale);
				}
			}

			materialiVerificati = mat;
		}
		//72249
		return materialiVerificati;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List recuperaLottiMat(RilevDatiPrdTS bo, AttivitaEsecMateriale materiale) {
		RilevDatiPrdTS rilevDatiPrdTS = (RilevDatiPrdTS) getRequest().getAttribute("myObject");
		String action = (String) getRequest().getAttribute("Action");
		//72249
		if(YRilevDatiPrdTSFormActionAdapter.VEDI_LOTTI_VERS_MATERIALI.equals(action)
				&& rilevDatiPrdTS != null && rilevDatiPrdTS.getNote() != null) {
			List ret = new ArrayList();
			List lottiRig = materiale.getLottiMateriali();
			for (int i = 0; i < lottiRig.size(); i++) {
				AttivitaEsecLottiMat lt = (AttivitaEsecLottiMat) lottiRig.get(i);
				if (!lt.getIdLotto().equals(Lotto.LOTTO_DUMMY)) {
					ret.add(lotto(lt.getIdLotto(), BigDecimal.ZERO));
				}
			}
			return ret;
		}else if(RilevDatiPrdTSFormActionAdapter.PRODUZIONE.equals(action)
				&& rilevDatiPrdTS != null && YPersDatiPrdCausaleRilev.isGestioneVersamentoMateriali(rilevDatiPrdTS.getPersDatiPrdCausaleRilev())) {
			List ret = new ArrayList();
			List lottiRig = materiale.getLottiMateriali();
			if(lottiRig.size() > 0) {
				AttivitaEsecLottiMat lt = (AttivitaEsecLottiMat) lottiRig.get(0);
				ret.add(lotto(lt.getIdLotto(), BigDecimal.ZERO));
			}
			return ret;
			//72249
		}else {
			return super.recuperaLottiMat(bo, materiale);
		}
	}

	@Override
	public void writeBodyEndElements(JspWriter out) throws IOException {
		super.writeBodyEndElements(out);
		//72060
		String lottoPezzaSubbioKey = (String) getServletEnvironment().getRequest().getAttribute("Y_MOSTRA_LOTTO_SUBBIO_PEZZA");
		if (lottoPezzaSubbioKey != null && !lottoPezzaSubbioKey.equals("")) {
			out.println("<button style=\"display:none;\" id=\"OpenLottoSubbioPezza\" name=\"OpenLottoSubbioPezza \"onclick=\"apriLotti();\"\" type=\"button\"></button>");
			out.println("<script language='JavaScript1.2'>");
			out.println("var lotti = [");
			String[] lotti = lottoPezzaSubbioKey.split(ColonneFiltri.SEP);
			for (int i = 0; i < lotti.length; i++) {
				out.print("'" + lotti[i] + "'");
				if (i < lotti.length - 1) out.print(",");
				//String winName = "newWindPersLottiTst"+i;
				//out.println("openLottoSubbioPezza('" + lotto + "','"+winName+"');");
			}
			out.println("];");
			out.println("apriLotti();");
			out.println("</script>");
		}
		getServletEnvironment().getRequest().setAttribute("Y_MOSTRA_LOTTO_SUBBIO_PEZZA", null);
		//72060
	}

	@Override
	public void writeFormStartElements(JspWriter out) throws IOException {
		super.writeFormStartElements(out);
		//Fix 72065 Inizio
		String jsp = (String) getRequest().getAttribute("JspName");
		if(jsp != null && jsp.indexOf("DichiarazioneVersamenti.jsp") > 0) {
			out.println("<input type=\"hidden\" id=\"PressioneConfNew\" name=\"PressioneConfNew\" value=\"N\"/>");
			out.println("<input type=\"hidden\" id=\"NuovaRilevazione\" name=\"NuovaRilevazione\" value=\"\"/>");
		}
		//Fix 72065 Fine
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void writeFormEndElements(JspWriter out) throws IOException {
		super.writeFormEndElements(out);
		RilevDatiPrdTS bo = (RilevDatiPrdTS) getBODataCollector().getBo();
		String jsp = (String) getRequest().getAttribute("JspName");
		//72298 Inizio <
		if(jsp != null && jsp.indexOf("DichiarazioneVersamenti.jsp") > 0) {
			Articolo artVrs = bo.getArticolo();
			if(artVrs != null
					&& CreaLottiTestoriUtils.isArticoloGestionePezze(artVrs, CreaLottiTestoriUtils.PRODUZIONE)) {
				Iterator iterAtv = bo.getAttivitaEsecutiva().getOrdineEsecutivo().getAttivitaEsecutive().iterator();
				while(iterAtv.hasNext()) {
					YAttivitaEsecutiva atvEsec = (YAttivitaEsecutiva) iterAtv.next();
					Iterator iterMats = atvEsec.getMateriali().iterator();
					while(iterMats.hasNext()) {
						AttivitaEsecMateriale mat = (AttivitaEsecMateriale) iterMats.next();
						Articolo artMat = mat.getArticolo();
						if(artMat != null
								&& (CreaLottiTestoriUtils.isArticoloGestionePezze(artMat, CreaLottiTestoriUtils.PRODUZIONE)
										|| CreaLottiTestoriUtils.isArticoloGestioneSubbiFeltri(mat.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE))) {
							int lotti_no_dummy = 0;
							Iterator iterLots = mat.getLottiMateriali().iterator();
							while(iterLots.hasNext()) {
								AttivitaEsecLottiMat lottoMat = (AttivitaEsecLottiMat) iterLots.next();
								if(!lottoMat.getIdLotto().equals(Lotto.LOTTO_DUMMY)) {
									lotti_no_dummy ++;
								}
							}
							if(lotti_no_dummy > 1) {
								out.println("<script language='JavaScript1.2'>");
								out.println("var promptLottoPezzaGreggia = true;");
								out.println("</script>");
							}
						}
					}
					//72300 <
					if(atvEsec.isAttivitaFinale()
							&& atvEsec.getTipoTaglio() == TipoTaglioPezza.TAGLIO_IN_LARGHEZZA) {
						out.println("<script language='JavaScript1.2'>");
						out.println("document.getElementById('CreaLottiPezzeTaglioLarghezza').parentNode.style.display=displayBlock;");
						out.println("</script>");
					}
					//72300 >
				}
			}
		}
		//72298 Fine >
	}

	//72226
	@Override
	public Object[] initRigaDaOrdineEsec(RilevDatiPrdTS bo, AttivitaEsecProdotto atvEsecPrd, int i) {
		PersDatiPrdCausaleRilev pers = bo.getPersDatiPrdCausaleRilev();
		if (pers == null || pers.getCausaleDocumento() == null) {
			return new Object[] { bo, Integer.valueOf(i) };
		}

		//		CausaleDocProduzione causale = pers.getCausaleDocumento();
		//		int abilita = causale.getAbilitaVersamenti();
		//		char politica = atvEsecPrd.getPoliticaConsVersam();

		//		boolean mismatchVersamAuto   = (abilita == CausaleDocProduzione.VERSAM_AUTO && politica != AttivitaEsecProdotto.AUTOMATICA);
		//		boolean mismatchVersamManual = (abilita == CausaleDocProduzione.VERSAM_MANUALE && politica != AttivitaEsecProdotto.MANUALE);

		//		if (mismatchVersamAuto || mismatchVersamManual) {
		//			return new Object[] { bo, Integer.valueOf(i) };
		//		}
		return super.initRigaDaOrdineEsec(bo, atvEsecPrd, i);
	}
	//72226
}