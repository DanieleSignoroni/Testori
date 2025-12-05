package it.testori.thip.magazzino.generalemag.web;

import java.sql.SQLException;
import java.util.Iterator;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.persist.Column;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.type.DateType;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.WebDataCollector;

import it.testori.thip.acquisti.documentoAC.YDocumentoAcqRigaPrm;
import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaLottoPrm;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaLottoSec;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaSec;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.magazzino.generalemag.PersDatiMagazzino;
import it.thera.thip.magazzino.generalemag.ProposizioneAutLotto;
import it.thera.thip.magazzino.generalemag.web.CreaLottoServlet;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 25/08/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72095    25/08/2025  DSSOF3   Prima stesura
 */

public class YCreaLottoServlet extends CreaLottoServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected Lotto creaLotto(ServletEnvironment se) {
		Lotto lt = null;
		char tipo = getStringParameter(se.getRequest(), "Tipo").charAt(0);
		char ambito = getStringParameter(se.getRequest(), "Provenienza").charAt(0);
		if (tipo == PersDatiMagazzino.TIPO_ACQ
				|| tipo == PersDatiMagazzino.TIPO_CL
				|| tipo == PersDatiMagazzino.TIPO_PRD) {

			Articolo art = recuperaArticolo(se);

			// Determino la tipologia Testori in base al tipo
			char tipologiaTestori;
			switch (tipo) {
			case PersDatiMagazzino.TIPO_ACQ:
				tipologiaTestori = CreaLottiTestoriUtils.ACQUISTO;
				break;
			case PersDatiMagazzino.TIPO_CL:
				tipologiaTestori = CreaLottiTestoriUtils.CONTO_LAVORO;
				break;
			case PersDatiMagazzino.TIPO_PRD:
				tipologiaTestori = CreaLottiTestoriUtils.PRODUZIONE;
				break;
			default:
				tipologiaTestori = CreaLottiTestoriUtils.ACQUISTO;
			}

			//.True se il tipo e' acquisto/conto lavoro e viene da un documento acquisto
			boolean fromDocumentoAcq =
					( (tipo == PersDatiMagazzino.TIPO_ACQ || tipo == PersDatiMagazzino.TIPO_CL)
							&& ambito == ProposizioneAutLotto.CREA_DA_DOCUMENTO );

			//.Se vengo da un documento acquisto o un ordine di produzione allora vado a proporre in automatico
			//.in base ai lotti gia' presenti un codice lotto, quindi creo il lotto e lo faccio uscire a video
			if (fromDocumentoAcq && (art != null && (
					CreaLottiTestoriUtils.isArticoloGestionePezze(art, tipologiaTestori)
					|| CreaLottiTestoriUtils.isArticoloGestioneFeltri(art, tipologiaTestori)))) {
				String idLotto = ottieniIdLottoTestori(se);
				lt = creaLottoTestori(se, idLotto);
			}else {
				lt = super.creaLotto(se);
			}
		}else {
			lt = super.creaLotto(se);
		}
		return lt;
	}

	@SuppressWarnings("rawtypes")
	protected String ottieniIdLottoTestori(ServletEnvironment se) {
		char tipo = getStringParameter(se.getRequest(), "Tipo").charAt(0);
		char ambito = getStringParameter(se.getRequest(), "Provenienza").charAt(0);
		String numeroOrdDoc = getStringParameter(se.getRequest(), "NumeroOrdDoc");
		String annoOrdDoc = getStringParameter(se.getRequest(), "AnnoOrdDoc");
		String numRiga = getStringParameter(se.getRequest(), "NumRiga");
		Articolo art = recuperaArticolo(se);
		int max = 0;
		String idLottoPartenza = null;

		//.True se il tipo e' acquisto/conto lavoro e viene da un documento acquisto
		boolean fromDocumentoAcq =
				( (tipo == PersDatiMagazzino.TIPO_ACQ || tipo == PersDatiMagazzino.TIPO_CL)
						&& ambito == ProposizioneAutLotto.CREA_DA_DOCUMENTO );

		// Determino la tipologia Testori in base al tipo
		char tipologiaTestori;
		switch (tipo) {
		case PersDatiMagazzino.TIPO_ACQ:
			tipologiaTestori = CreaLottiTestoriUtils.ACQUISTO;
			break;
		case PersDatiMagazzino.TIPO_CL:
			tipologiaTestori = CreaLottiTestoriUtils.CONTO_LAVORO;
			break;
		case PersDatiMagazzino.TIPO_PRD:
			tipologiaTestori = CreaLottiTestoriUtils.PRODUZIONE;
			break;
		default:
			tipologiaTestori = CreaLottiTestoriUtils.ACQUISTO;
		}
		String keyObj = KeyHelper.buildObjectKey(new String[] {
				Azienda.getAziendaCorrente(),annoOrdDoc,numeroOrdDoc,numRiga
		});
		if(fromDocumentoAcq) {
			try {
				YDocumentoAcqRigaPrm docAcqRig = (YDocumentoAcqRigaPrm) YDocumentoAcqRigaPrm.elementWithKey(YDocumentoAcqRigaPrm.class, keyObj, PersistentObject.NO_LOCK);
				if(docAcqRig != null) {
					Iterator iterLotti = docAcqRig.getRigheLotto().iterator();
					while(iterLotti.hasNext()) {
						DocumentoAcqRigaLottoPrm rl = (DocumentoAcqRigaLottoPrm) iterLotti.next();
						if(!rl.getIdLotto().equals(Lotto.LOTTO_DUMMY) && rl.getIdLotto().length() > 8) {
							String lottoNoRadice = rl.getIdLotto().substring(8,10);
							if(CreaLottiTestoriUtils.isArticoloGestionePezze(art, tipologiaTestori)){
								idLottoPartenza = rl.getIdLotto();
								continue;
							}
							try {
								int value = Integer.parseInt(lottoNoRadice);
								if (value > max) {
									max = value;
									idLottoPartenza = rl.getIdLotto().substring(0,8);
								}
							} catch (NumberFormatException e) {
								e.printStackTrace(Trace.excStream);
							}
						}
					}
					//.Se e' conto lavoro ed e' il primo lotto allora prendo il prefisso dalla riga secondaria
					if(docAcqRig.isDocumentoLavEsterna() && CreaLottiTestoriUtils.isArticoloGestionePezze(art, tipologiaTestori)) {
						Iterator iterRigheSec = docAcqRig.getRigheSecondarie().iterator();
						String idLottoMateriale = null;
						while(iterRigheSec.hasNext()) {
							DocumentoAcqRigaSec rigaSec = (DocumentoAcqRigaSec) iterRigheSec.next();
							if(CreaLottiTestoriUtils.isArticoloGestioneSubbio(rigaSec.getArticolo(), tipologiaTestori)
									|| CreaLottiTestoriUtils.isArticoloGestioneFeltri(rigaSec.getArticolo(), tipologiaTestori)
									|| CreaLottiTestoriUtils.isArticoloGestionePezze(rigaSec.getArticolo(), tipologiaTestori)) {
								Iterator iterLots = rigaSec.getRigheLotto().iterator();
								while(iterLots.hasNext()) {
									DocumentoAcqRigaLottoSec rl = (DocumentoAcqRigaLottoSec) iterLots.next();
									if(!rl.getIdLotto().equals(Lotto.LOTTO_DUMMY))
										idLottoMateriale = rl.getIdLotto();
								}
							}
						}
						if(idLottoMateriale != null) {
							iterLotti = docAcqRig.getRigheLotto().iterator();
							while(iterLotti.hasNext()) {
								DocumentoAcqRigaLottoPrm rl = (DocumentoAcqRigaLottoPrm) iterLotti.next();
								if(!rl.getIdLotto().equals(Lotto.LOTTO_DUMMY) && rl.getIdLotto().contains(idLottoMateriale)) {
									String lottoNoRadice = rl.getIdLotto().replace(idLottoMateriale,"");
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
						}
						return idLottoMateriale + String.format("%0" + 2 + "d", max +1);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		if(CreaLottiTestoriUtils.isArticoloGestionePezze(art, tipologiaTestori)
				&& idLottoPartenza != null) {
			try {
				if(idLottoPartenza.length() > 8) {
					idLottoPartenza = idLottoPartenza.substring(0,8);
				}
				String nuovoProgressivo = CreaLottiTestoriUtils.getMaxProgressivoPezzeFromLotto(Azienda.getAziendaCorrente(),idLottoPartenza);
				return idLottoPartenza + nuovoProgressivo;
			} catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
			
		}
		//.Se il max e' > 0 allora vuol dire che ho gia' un lotto e quindi do' il prossimo partendo dal precedente e mettendo +1
		if(max > 0) {
			return idLottoPartenza + String.format("%0" + 2 + "d", max +1);
		}else {
			//.Altrimenti costruisco io il codice
			char tipoCodifica = '0';
			switch (tipo) {
			case PersDatiMagazzino.TIPO_ACQ:
				tipoCodifica = art.getArticoloDatiMagaz().getCodAutLotAcq();
				break;
			case PersDatiMagazzino.TIPO_CL:
				tipoCodifica = art.getArticoloDatiMagaz().getCodAutLotCl();
				break;
			case PersDatiMagazzino.TIPO_PRD:
				tipoCodifica = art.getArticoloDatiMagaz().getCodAutLotProd();
				break;
			default:
				break;
			}
			char provenienza = '0';
			switch (tipo) {
			case PersDatiMagazzino.TIPO_ACQ:
				provenienza = CreaLottiTestoriUtils.ACQUISTO;
				break;
			case PersDatiMagazzino.TIPO_CL:
				provenienza = CreaLottiTestoriUtils.CONTO_LAVORO;
				break;
			case PersDatiMagazzino.TIPO_PRD:
				provenienza = CreaLottiTestoriUtils.PRODUZIONE;
				break;
			default:
				break;
			}
			String idLotto = CreaLottiTestoriUtils.buildNextIdProgressivoLotto(tipoCodifica,provenienza);
			if(idLotto != null)
				return idLotto + String.format("%0" + 2 + "d", 1);
		}
		return null;
	}

	protected Lotto creaLottoTestori(ServletEnvironment se, String idLotto) {
		Lotto lotto = null;
		try {
			String idArticolo = getStringParameter(se.getRequest(), "IdArticolo");
			String idVersione = getStringParameter(se.getRequest(), "IdVersione");
			String idConfigurazione = getStringParameter(se.getRequest(), "IdConfigurazione");
			//String idLotto = getStringParameter(se.getRequest(), "IdLotto"); //...FIX 30238
			String idMagazzino = getStringParameter(se.getRequest(), "IdMagazzino");
			String tipo = getStringParameter(se.getRequest(), "Tipo");
			String numeroOrdDoc = getStringParameter(se.getRequest(), "NumeroOrdDoc");
			String annoOrdDoc = getStringParameter(se.getRequest(), "AnnoOrdDoc");
			String dataOrdDoc = getStringParameter(se.getRequest(), "DataOrdDoc");
			String numRiga = getStringParameter(se.getRequest(), "NumRiga");
			String numRigaPadre = getStringParameter(se.getRequest(), "NumRigaPadre");
			String provenienza = getStringParameter(se.getRequest(), "Provenienza");
			//Fix 5151 - inizio
			String idFornitore = getStringParameter(se.getRequest(), "IdFornitore");
			//Fix 5151 - fine

			DateType d = new DateType();
			java.sql.Date data = (java.sql.Date)d.stringToObject(dataOrdDoc);

			Integer numRigaInt = null;
			Integer numRigaPadreInt = null;
			Integer idVersioneInt = null;
			if(numRiga != null && !numRiga.equals(""))
				numRigaInt = new Integer(numRiga);
			if(numRigaPadre != null && !numRigaPadre.equals(""))
				numRigaPadreInt = new Integer(numRigaPadre);
			if(idVersione != null && !idVersione.equals(""))
				idVersioneInt = new Integer(idVersione);

			/*if(tipo == null || tipo.equals("")) {
		        se.addErrorMessage("BAS0000038", "Tipo");
		      }
		      if(numeroOrdDoc == null || numeroOrdDoc.equals("")) {
		        se.addErrorMessage("BAS0000038", "Numero ordine-documento");
		      }
		      if(annoOrdDoc == null || annoOrdDoc.equals("")) {
		        se.addErrorMessage("BAS0000038", "Anno ordine-documento");
		      }
		      if(provenienza == null || provenienza.equals("")) {
		        se.addErrorMessage("BAS0000038", "Provenienza");
		      }*/

			if(idLotto == null || idLotto.equals("")) {
				se.addErrorMessage("THIP110421");
			}

			if (se.isErrorListEmpity()) {
				ProposizioneAutLotto pal = ProposizioneAutLotto.creaProposizioneAutLotto(tipo.charAt(0),
						numeroOrdDoc,
						annoOrdDoc,
						data,
						numRigaInt,
						numRigaPadreInt,
						idArticolo,
						idVersioneInt,
						idConfigurazione,
						idMagazzino,
						provenienza.charAt(0));

				//...FIX 3187
				if(tipo.equalsIgnoreCase(""+PersDatiMagazzino.TIPO_VEN))
					lotto = pal.creaLotto(idLotto,idVersioneInt,idConfigurazione,idMagazzino, true);
				else {
					//Fix 5151 - inizio
					pal.setIdFornitore(idFornitore);
					//Fix 5151 - fine
					lotto = pal.creaLotto(idLotto, false);
				}

				completaLotto(se, lotto);//21452
				
				char tipologiaTestori;
				switch (tipo.charAt(0)) {
				case PersDatiMagazzino.TIPO_ACQ:
					tipologiaTestori = CreaLottiTestoriUtils.ACQUISTO;
					break;
				case PersDatiMagazzino.TIPO_CL:
					tipologiaTestori = CreaLottiTestoriUtils.CONTO_LAVORO;
					break;
				case PersDatiMagazzino.TIPO_PRD:
					tipologiaTestori = CreaLottiTestoriUtils.PRODUZIONE;
					break;
				default:
					tipologiaTestori = CreaLottiTestoriUtils.ACQUISTO;
				}

				String collectorName = getStringParameter(se.getRequest(), "thCollectorName");
				if (collectorName == null || collectorName.equals(""))
					collectorName = BODataCollector.class.getName();
				BODataCollector boDC = (BODataCollector) Factory.createObject(collectorName);
				if (boDC instanceof WebDataCollector)
					((WebDataCollector) boDC).setServletEnvironment(se);
				boDC.initialize("Lotto");
				boDC.setBo(lotto);
				if (boDC.save() != BODataCollector.OK)
					se.addErrorMessages(boDC.getErrorList().getErrors());
				else if(CreaLottiTestoriUtils.isArticoloGestionePezze(lotto.getArticolo(), tipologiaTestori))
					se.getRequest().setAttribute("YMostraLotto", Column.TRUE_CHAR);
					
				return lotto;
			}
			else
				return lotto;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}