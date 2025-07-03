package it.testori.thip.produzione.raccoltaDati.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import javax.servlet.ServletException;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.servlet.Check;

import it.testori.thip.produzione.ordese.YAttivitaEsecProdotto;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.cs.ColonneFiltri;
import it.thera.thip.produzione.ordese.AttivitaEsecutiva;
import it.thera.thip.produzione.raccoltaDati.RilevDatiPrdTS;
import it.thera.thip.produzione.raccoltaDati.web.RilevDatiPrdTSDataCollector;

/**
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
 * 71XXX    03/07/2025  DSSOF3   Prima stesura
 */

public class YAzioneCreaLottiFilatiManufatti extends Check {

	private static final long serialVersionUID = 1L;

	public static final String CHIAVE_DATI_SESSIONE = "thChiaveDatiSessione";
	protected String iChiaveInSessione;

	@SuppressWarnings("rawtypes")
	public void processAction(ServletEnvironment se) throws ServletException, IOException, SQLException{
		String className = getStringParameter(se.getRequest(),"thClassName");
		String collectorName = getStringParameter(se.getRequest(),"thCollectorName");
		String action = (String) se.getRequest().getAttribute("Action");
		if (collectorName==null || collectorName.equals(""))
			collectorName = BODataCollector.class.getName();

		ClassADCollection cadc = getClassADCollection(className);
		RilevDatiPrdTSDataCollector boDC = (RilevDatiPrdTSDataCollector) Factory.createObject(collectorName);

		iChiaveInSessione = salvaInSessione(se);
		RilevDatiPrdTS boInSessione = (RilevDatiPrdTS) se.getSession().getAttribute(iChiaveInSessione);
		if(boInSessione != null){
			boDC.setBo(boInSessione);
		}
		boDC.initialize(className, true, getCurrentLockType(se));
		setValues(cadc, boDC, se);

		RilevDatiPrdTS bo = (RilevDatiPrdTS) boDC.getBo();
		bo.setCurrentNumPag(Integer.parseInt(((String)boDC.get("CurrentNumPag"))));
		bo.setIdAzienda(Azienda.getAziendaCorrente());
		boDC.completaRilevazione(action);

		String url = getStringParameter(se.getRequest(),"thOldJspName"); //Fix 32789
		if (se.isErrorListEmpity())
		{
			boDC.setAutoCheck(false);
			boDC.setServletEnvironment(se);
			se.getRequest().setAttribute("ActionPage", action);
			actionOnObject(boDC, se);

			//Fix 32789 -- Inizio
			if (url.indexOf("DichiarazionePrelievi.jsp") > 0) {
				Vector errs = boDC.getErrorList().getErrors();
				for (int i = errs.size()-1; i >= 0; i--) {
					ErrorMessage em = (ErrorMessage) errs.get(i);
					if (em.getAttOrGroupName() != null && em.getAttOrGroupName().indexOf("Prodott") >= 0)
						errs.remove(i);
				}
			}
			//Fix 32789 -- Fine
		}
		closeAction(boDC, se);

		String prevJspName = getStringParameter(se.getRequest(),"thPrevJspName");
		se.getRequest().setAttribute("JspName", url);
		se.getRequest().setAttribute("PrevJspName", prevJspName);
		se.getRequest().setAttribute("myObject", bo);
		se.getSession().setAttribute(iChiaveInSessione, bo);

		url = "it/testori/thip/magazzino/generalemag/CreaLottiTestoriNuovo.jsp";
		String chiaveSelezionato = getStringParameter(se.getRequest(), CLASS_NAME) + ColonneFiltri.LISTA_SEP;
		if(bo != null) {
			AttivitaEsecutiva atv = bo.getAttivitaEsecutiva();
			if(atv != null && atv.getAtvEsecPrdPrimario() != null) {
				YAttivitaEsecProdotto atvEsecPrd = (YAttivitaEsecProdotto) atv.getAtvEsecPrdPrimario();
				chiaveSelezionato += atvEsecPrd.getKey();
			}
		}
		url += "?ChiaveSelezionato="+chiaveSelezionato;
		se.sendRequest(getServletContext(), url, true);
	}

	public String salvaInSessione(ServletEnvironment se) {
		if(se == null)
			return null;

		iChiaveInSessione = (String)se.getRequest().getAttribute(CHIAVE_DATI_SESSIONE);
		if(iChiaveInSessione == null)
			iChiaveInSessione = getStringParameter(se.getRequest(),CHIAVE_DATI_SESSIONE);

		if(iChiaveInSessione == null)
			iChiaveInSessione = se.getUser().getId() + "-" + "RilevazioneTS" + "-" + new Date().getTime();

		se.getRequest().setAttribute(CHIAVE_DATI_SESSIONE, iChiaveInSessione);
		return iChiaveInSessione;
	}
}
