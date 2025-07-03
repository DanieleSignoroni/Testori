package it.testori.thip.produzione.raccoltaDati.web;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.URLUtils;
import com.thera.thermfw.web.servlet.BaseServlet;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.testori.thip.produzione.ordese.TipoTaglioPezza;
import it.testori.thip.produzione.ordese.YAttivitaEsecProdotto;
import it.testori.thip.produzione.ordese.YAttivitaEsecutiva;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.produzione.ordese.AttivitaEsecLottiMat;
import it.thera.thip.produzione.ordese.AttivitaEsecMateriale;
import it.thera.thip.produzione.ordese.AttivitaEsecProdotto;
import it.thera.thip.produzione.ordese.AttivitaEsecutiva;
import it.thera.thip.produzione.ordese.OrdineEsecutivo;

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
 * 71XXX    26/06/2025  DSSOF3   Prima stesura
 */

public class CreaLottoTestoriRilevDatiPrdTS extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Override
	protected void processAction(ServletEnvironment se) throws Exception {
		Articolo articolo = recuperaArticolo(se);
		String idLotto = getCodiceLottoPers(se);
		String numeroOrdDoc = getStringParameter(se.getRequest(), "NumeroOrdDoc");
		String annoOrdDoc = getStringParameter(se.getRequest(), "AnnoOrdDoc");
		String numRigaPadre = getStringParameter(se.getRequest(), "NumRigaPadre");

		String key = KeyHelper.buildObjectKey(new String[] {Azienda.getAziendaCorrente(), annoOrdDoc, numeroOrdDoc});
		OrdineEsecutivo ordese = OrdineEsecutivo.elementWithKey(key, PersistentObject.NO_LOCK);
		if(ordese != null) {
			AttivitaEsecutiva attivita = (AttivitaEsecutiva) AttivitaEsecutiva.elementWithKey(AttivitaEsecutiva.class, key+KeyHelper.KEY_SEPARATOR+numRigaPadre, PersistentObject.NO_LOCK);
			AttivitaEsecProdotto prodotto = attivita.getAtvEsecPrdPrimario();
			if(prodotto != null) {
				CreaLottiTestoriUtils pal = null;
				//.Controllo che l'ordine esecutivo abbia un materiale semi-lavorato con un lotto gia' indicato
				if(CreaLottiTestoriUtils.isArticoloGestionePezze(articolo, CreaLottiTestoriUtils.PRODUZIONE)) {
					pal = ((YAttivitaEsecProdotto)prodotto).getCreaProposizioneAutLottoTestori();
					boolean trovato = false;
					String idLottoMateriale = null;
					Iterator iterAtv = ordese.getAttivitaEsecutive().iterator();
					while(iterAtv.hasNext()) {
						AttivitaEsecutiva atvEsec = (AttivitaEsecutiva) iterAtv.next();
						Iterator iterMats = atvEsec.getMateriali().iterator();
						while(iterMats.hasNext()) {
							AttivitaEsecMateriale mat = (AttivitaEsecMateriale) iterMats.next();
							//if(mat.getArticolo().getTipoParte() == ArticoloDatiIdent.SEMIFINITO) {
							Iterator iterLots = mat.getLottiMateriali().iterator();
							while(iterLots.hasNext()) {
								AttivitaEsecLottiMat lottoMat = (AttivitaEsecLottiMat) iterLots.next();
								if(!lottoMat.getIdLotto().equals(Lotto.LOTTO_DUMMY)) {
									trovato = true;
									idLottoMateriale = lottoMat.getIdLotto();
								}
							}
							//}
						}
					}
					if(!trovato) {
						PrintWriter out = se.getResponse().getWriter();
						out.println("<script>");
						out.println("parent.window.alert('Per poter creare il lotto del prodotto finito va prima indicato il lotto sul materiale');");
						out.println("</script>");
						out.close();
						pal = null;
					}else {
						String idLottoTeorico = CreaLottiTestoriUtils.getYearSuffix();
						idLottoTeorico += CreaLottiTestoriUtils.PEZZE;
						//idLottoTeorico += CreaLottiTestoriUtils.PRODUZIONE; potrebbe venire da C/Lav quindi potrebbe non avere la P ma la T
						if(idLottoMateriale.startsWith(idLottoTeorico)) {
							pal.setGeneraCodiceLottoAutomatico(false);
							try {
								String progressivo = null;
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
						}else {
							PrintWriter out = se.getResponse().getWriter();
							out.println("<script>");
							out.println("parent.window.alert('Impossibile creare il lotto padre a partire dal lotto figlio, non e costruito secondo la logica AATPNNNNN ');");
							out.println("</script>");
							out.close();
							pal = null;
						}
					}
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
						String idAzienda = lotto.getCodiceAzienda();
						String idArticolo = lotto.getCodiceArticolo();
						String descLotto = lotto.getDescrizioneLotto();
						String valoriCampi = "&LottoVal=" + URLUtils.get().encode(idLotto) + "&AziendaVal=" + idAzienda;
						valoriCampi += "&ArticoloVal=" + URLUtils.get().encode(idArticolo) + "&DescLotto=" + URLUtils.get().encode(descLotto);
						se.sendRequest(getServletContext(), "it/thera/thip/magazzino/generalemag/SelezionaNuovoLotto.jsp?" + nomiCampi + valoriCampi, true);
					}
				}
			}
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
