package it.testori.thip.logis.lgb.web;

import java.io.IOException;

import javax.servlet.ServletException;

import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.servlet.Save;

import it.testori.thip.logis.lgb.SceltaTestataListaSpostaUds;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 26/11/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    26/11/2025  DSSOF3   Prima stesura
 */

public class SceltaTestataListaSpostaUdsSave extends Save {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterProcessAction(BODataCollector boDC, ServletEnvironment se) throws ServletException, IOException {
		if(se.isErrorListEmpity()) {
			se.getSession().setAttribute("TestataLista", ((SceltaTestataListaSpostaUds)boDC.getBo()).getTestataLista());
		}
		super.afterProcessAction(boDC, se);
	}
}
