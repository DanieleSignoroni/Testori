package it.testori.thip.produzione.raccoltaDati.web;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Iterator;
//import java.util.Iterator;
import java.util.List;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.Column;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.URLUtils;
import com.thera.thermfw.web.servlet.BaseServlet;

import it.testori.thip.base.articolo.YArticoloDatiMagaz;
import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.testori.thip.produzione.ordese.TipoTaglioPezza;
import it.testori.thip.produzione.ordese.YAttivitaEsecProdotto;
import it.testori.thip.produzione.ordese.YAttivitaEsecutiva;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.produzione.ordese.AttivitaEsecLottiMat;
//import it.thera.thip.produzione.ordese.AttivitaEsecLottiPrd;
import it.thera.thip.produzione.ordese.AttivitaEsecMateriale;
//import it.thera.thip.produzione.ordese.AttivitaEsecLottiMat;
//import it.thera.thip.produzione.ordese.AttivitaEsecMateriale;
import it.thera.thip.produzione.ordese.AttivitaEsecProdotto;
import it.thera.thip.produzione.ordese.AttivitaEsecutiva;
import it.thera.thip.produzione.ordese.OrdineEsecutivo;
import it.thera.thip.produzione.raccoltaDati.RilevDatiPrdTS;

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
 * 72060	23/07/2025	DSSOF3   Gestione subbio trattato come pezza, controllo su lotto materiale piu ristretto (no dummy)
 * 72252	15/12/2025	DSSOF3	 Gestione apertura lotto pezze automaticamente, scarto lotti dummy in ricerca da materiale
 * 72269	06/01/2026	DSSOF3	 Nuova gestione lotti feltri
 * 72298	16/01/2026	DSSOF3	 Gestione richiesta pezza greggia se prelevati piu lotti.
 * 72320	28/01/2026	DSSOF3	 Gestione lotto materiale gia' a 15 digits.
 */

public class CreaLottoTestoriRilevDatiPrdTS extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void processAction(ServletEnvironment se) throws Exception {
		Articolo articolo = recuperaArticolo(se);
		String idLotto = getCodiceLottoPers(se);
		String numeroOrdDoc = getStringParameter(se.getRequest(), "NumeroOrdDoc");
		String annoOrdDoc = getStringParameter(se.getRequest(), "AnnoOrdDoc");
		String numRigaPadre = getStringParameter(se.getRequest(), "NumRigaPadre");

		String idAzienda = getStringParameter(se.getRequest(), "IdAzienda");
		String keyLotto = KeyHelper.buildObjectKey(new String[] {idAzienda, articolo.getIdArticolo(), idLotto});
		Lotto lot = (Lotto) Lotto.elementWithKey(Lotto.class, keyLotto, PersistentObject.NO_LOCK);
		String lottoPezzePrompt = getStringParameter(se.getRequest(), "CodiceLottoPezzaGreggiaPrompt"); //72298
		if(lot == null) {
			String key = KeyHelper.buildObjectKey(new String[] {Azienda.getAziendaCorrente(), annoOrdDoc, numeroOrdDoc});
			OrdineEsecutivo ordese = OrdineEsecutivo.elementWithKey(key, PersistentObject.NO_LOCK);
			if(ordese != null) {
				AttivitaEsecutiva attivita = (AttivitaEsecutiva) AttivitaEsecutiva.elementWithKey(AttivitaEsecutiva.class, key+KeyHelper.KEY_SEPARATOR+numRigaPadre, PersistentObject.NO_LOCK);
				AttivitaEsecProdotto prodotto = attivita.getAtvEsecPrdPrimario();
				if(prodotto != null) {
					CreaLottiTestoriUtils pal = null;
					//.Controllo che l'ordine esecutivo abbia un materiale semi-lavorato con un lotto gia' indicato
					if(CreaLottiTestoriUtils.isArticoloGestionePezze(articolo, CreaLottiTestoriUtils.PRODUZIONE)
							|| CreaLottiTestoriUtils.isArticoloGestioneSubbio(articolo, CreaLottiTestoriUtils.PRODUZIONE)) { //72060 Ora i subbi in produzione sono gestiti cosi
						pal = ((YAttivitaEsecProdotto)prodotto).getCreaProposizioneAutLottoTestori();
						boolean trovato = false;
						String idLottoMateriale = null;
						/*Iterator iterAtv = ordese.getAttivitaEsecutive().iterator();
						while(iterAtv.hasNext()) {
							AttivitaEsecutiva atvEsec = (AttivitaEsecutiva) iterAtv.next();
							Iterator iterMats = atvEsec.getMateriali().iterator();
							while(iterMats.hasNext()) {
								AttivitaEsecMateriale mat = (AttivitaEsecMateriale) iterMats.next();
								if((CreaLottiTestoriUtils.isArticoloGestionePezze(mat.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)
										|| CreaLottiTestoriUtils.isArticoloGestioneSubbiFeltri(mat.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE))
										&& !trovato) {
									//if(mat.getArticolo().getTipoParte() == ArticoloDatiIdent.SEMIFINITO) {
									Iterator iterLots = mat.getLottiMateriali().iterator();
									while(iterLots.hasNext()) {
										AttivitaEsecLottiMat lottoMat = (AttivitaEsecLottiMat) iterLots.next();
										if(!lottoMat.getIdLotto().equals(Lotto.LOTTO_DUMMY)) {
											trovato = true;
											idLottoMateriale = lottoMat.getIdLotto();
										}
									}
								}
								//}
							}
						}*/
						RilevDatiPrdTS bo = (RilevDatiPrdTS) se.getSession().getAttribute("RilevDatiPrdTSOldObject");
						if(!trovato) {
							if(bo != null) {
								//int firstIndex = (bo.getCurrentNumPag() - 1) * 20;
								//int lastIndex = firstIndex + 19;
								try {
									for (int i = 1; i < 21; i++) {
										String getIdMateriale = "getMateriale" + String.valueOf(i);
										String getIdLotto = "getIdLotto" + String.valueOf(i);
										String getIdMagazzinoPrl = "getIdMagazzinoPrl" + String.valueOf(i);
										//String getIdConfigurazione = "getIdConfigurazione" + String.valueOf(i);
										//String getIdVersione = "getIdVersione" + String.valueOf(i);
										Class c = Factory.getClass(bo.getClass());
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
													|| CreaLottiTestoriUtils.isArticoloGestioneSubbiFeltri(mat, CreaLottiTestoriUtils.PRODUZIONE))
													&& !trovato) {
												trovato = true;
												idLottoMateriale = idLot;
											}
										}
									}
								}catch (Exception e) {
									e.printStackTrace(Trace.excStream);
								}
							}
						}
						if(!trovato) {
							Iterator iterAtv = bo.getAttivitaEsecutiva().getOrdineEsecutivo().getAttivitaEsecutive().iterator();
							while(iterAtv.hasNext()) {
								AttivitaEsecutiva atvEsec = (AttivitaEsecutiva) iterAtv.next();
								Iterator iterMats = atvEsec.getMateriali().iterator();
								while(iterMats.hasNext()) {
									AttivitaEsecMateriale mat = (AttivitaEsecMateriale) iterMats.next();
									//if(mat.getArticolo().getTipoParte() == ArticoloDatiIdent.SEMIFINITO) {
									Iterator iterLots = mat.getLottiMateriali().iterator();
									while(iterLots.hasNext()) {
										AttivitaEsecLottiMat lottoMat = (AttivitaEsecLottiMat) iterLots.next();
										/*if(!lottoMat.getIdLotto().equals(Lotto.LOTTO_DUMMY)) {
											trovato = true;
											idLottoMateriale = lottoMat.getIdLotto();
										}*/
										if((CreaLottiTestoriUtils.isArticoloGestionePezze(mat.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE)
										        || CreaLottiTestoriUtils.isArticoloGestioneSubbiFeltri(mat.getArticolo(), CreaLottiTestoriUtils.PRODUZIONE))
										        && !lottoMat.getIdLotto().equals(Lotto.LOTTO_DUMMY) //72252
										        && !trovato
										        && (lottoPezzePrompt == null || lottoMat.getIdLotto().equals(lottoPezzePrompt))) { //72298
										    trovato = true;
										    idLottoMateriale = lottoMat.getIdLotto();
										}
									}
									//}
								}
							}
						}
						if(idLottoMateriale != null && idLottoMateriale.equals(Lotto.LOTTO_DUMMY)) { //72060
							trovato = false; //.Se l'unico che ho trovato e' dummy allora blocco altrimenti mi si genera un lotto errato
						}
						if(!trovato) {
							PrintWriter out = se.getResponse().getWriter();
							out.println("<script>");
							if(lottoPezzePrompt == null)
								out.println("parent.window.alert('Per poter creare il lotto del prodotto finito va prima indicato il lotto sul materiale');");
							else
								out.println("parent.window.alert('Il lotto greggio sparato non ha nessuna referenza nell ordine esecutivo');"); //72298
								out.println("</script>");
							out.close();
							pal = null;
						}else {
							//String idLottoTeorico = CreaLottiTestoriUtils.getYearSuffix();
							//idLottoTeorico += CreaLottiTestoriUtils.PEZZE;
							//idLottoTeorico += CreaLottiTestoriUtils.PRODUZIONE; potrebbe venire da C/Lav quindi potrebbe non avere la P ma la T
							//if(idLottoMateriale.startsWith(idLottoTeorico)) {
							pal.setGeneraCodiceLottoAutomatico(false);
							try {
								String progressivo = null;
								//72320 <
								if(idLottoMateriale != null && idLottoMateriale.length() == 15) {
									idLottoMateriale = idLottoMateriale.substring(0, 14);	
								}
								//72320 >
								if(attivita.isAttivitaFinale() 
										&& ((YAttivitaEsecutiva)attivita).getTipoTaglio() != TipoTaglioPezza.NON_SIGNIFICATIVO){
									progressivo = CreaLottiTestoriUtils.getNextLottoProgressivoMonoChar(ordese.getIdAzienda(), idLottoMateriale, ((YAttivitaEsecutiva)attivita).getTipoTaglio());
								}else {
									progressivo = CreaLottiTestoriUtils.getMaxProgressivoPezzeFromLotto(ordese.getIdAzienda(), idLottoMateriale);
								}
								idLotto = idLottoMateriale + progressivo;
								pal.setIdLotto(idLotto);
							} catch (SQLException e) {
								e.printStackTrace(Trace.excStream);
							}
							/*}else {
								PrintWriter out = se.getResponse().getWriter();
								out.println("<script>");
								out.println("parent.window.alert('Impossibile creare il lotto padre a partire dal lotto figlio, non e costruito secondo la logica AATPNNNNN ');");
								out.println("</script>");
								out.close();
								pal = null;
							}*/
						}
					}else if(CreaLottiTestoriUtils.isArticoloGestioneFeltri(articolo, CreaLottiTestoriUtils.PRODUZIONE)) {
						//..se feltri deve lavorare come le pezze, lo so e' un casino
						pal = ((YAttivitaEsecProdotto)prodotto).getCreaProposizioneAutLottoTestori();
						pal.setGeneraCodiceLottoAutomatico(false);
						//72269 rem
						//Iterator iterLottiAtvEsecPrd = prodotto.getLottiProdotti().iterator();
						//72269 fine rem
						String idLottoPartenza = ((YAttivitaEsecProdotto)prodotto).getRadiceLottoFeltro(); //72269 lotto di partenza da qui
						//72269 rem
						//						while(iterLottiAtvEsecPrd.hasNext()) {
						//							AttivitaEsecLottiPrd rl = (AttivitaEsecLottiPrd) iterLottiAtvEsecPrd.next();
						//							if(!rl.getIdLotto().equals(Lotto.LOTTO_DUMMY) && rl.getIdLotto().length() > 8) {
						//								idLottoPartenza = rl.getIdLotto();
						//								continue;
						//							}
						//						}
						//72269 fine rem
						if(idLottoPartenza != null) {
							if(idLottoPartenza.length() > 8) {
								idLottoPartenza = idLottoPartenza.substring(0,8);
							}
							String nuovoProgressivo = CreaLottiTestoriUtils.getMaxProgressivoPezzeFromLotto(Azienda.getAziendaCorrente(),idLottoPartenza);
							idLotto = idLottoPartenza + nuovoProgressivo;
						}else {
							idLotto = CreaLottiTestoriUtils.buildNextIdProgressivoLotto(YArticoloDatiMagaz.PEZZE, CreaLottiTestoriUtils.PRODUZIONE);
							idLotto = idLotto + String.format("%0" + 2 + "d", 1);
						}
						pal.setIdLotto(idLotto);
					}else {
						pal = ((YAttivitaEsecProdotto)prodotto).getCreaProposizioneAutLottoTestori();
					}
					if(pal != null) {
						List lottiAuto = pal.creaLottiAutomatici();
						if(lottiAuto.size() > 0) {
							ConnectionManager.commit();
							Lotto lotto = (Lotto) lottiAuto.get(0);
							String idLottoField = getStringParameter(se.getRequest(), "LottoField");
							String idAziendaField = getStringParameter(se.getRequest(), "AziendaField");
							String idArticoloField = getStringParameter(se.getRequest(), "ArticoloField");
							String nomiCampi = "LottoField=" + idLottoField + "&AziendaField=" + idAziendaField + "&ArticoloField=" + idArticoloField;
							idLotto = lotto.getCodiceLotto();
							idAzienda = lotto.getCodiceAzienda();
							String idArticolo = lotto.getCodiceArticolo();
							String descLotto = lotto.getDescrizioneLotto();
							String valoriCampi = "&LottoVal=" + URLUtils.get().encode(idLotto) + "&AziendaVal=" + idAzienda;
							valoriCampi += "&ArticoloVal=" + URLUtils.get().encode(idArticolo) + "&DescLotto=" + URLUtils.get().encode(descLotto);
							//72252
							if(CreaLottiTestoriUtils.isArticoloGestionePezze(articolo, CreaLottiTestoriUtils.PRODUZIONE)) {
								se.getRequest().setAttribute("YMostraLotto", Column.TRUE_CHAR);
								se.getRequest().setAttribute("YFromRilevDatiPrdTS", Column.TRUE_CHAR);
							}
							//72252
							se.sendRequest(getServletContext(), "it/thera/thip/magazzino/generalemag/SelezionaNuovoLotto.jsp?" + nomiCampi + valoriCampi, true);
						}
					}
				}
			}
		}else {
			se.addErrorMessage(new ErrorMessage("BAS0000078","Per la riga selezionata e' gia' presente un lotto"));
			se.sendRequest(getServletContext(), "it/thera/thip/magazzino/generalemag/AlertErrorMessage.jsp", true);
		}
	}

	public Articolo recuperaArticolo(ServletEnvironment se){
		String idArticolo = getStringParameter(se.getRequest(), "IdArticolo");
		String idAzienda = Azienda.getAziendaCorrente();
		String keyArt = KeyHelper.buildObjectKey(new String[] {idAzienda, idArticolo});
		Articolo art = null;
		try {
			art = (Articolo) Articolo.elementWithKey(Articolo.class, keyArt, PersistentObject.NO_LOCK);
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		return art;
	}

	public String getCodiceLottoPers(ServletEnvironment se) {
		return getStringParameter(se.getRequest(), "IdLotto");
	}

}
