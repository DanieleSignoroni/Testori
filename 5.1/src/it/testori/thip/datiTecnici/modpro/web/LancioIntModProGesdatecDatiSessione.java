package it.testori.thip.datiTecnici.modpro.web;

import it.testori.thip.datiTecnici.modpro.LancioInterrogazioneModProGesdatec;
import it.thera.thip.base.documenti.web.DocumentoDatiSessione;
import it.thera.thip.datiTecnici.modpro.ModelloProduttivo;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 07/03/2025
 * <br><br>
 * <b>71XXX    DSSOF3    07/03/2025</b>
 * <p></p>
 */

public class LancioIntModProGesdatecDatiSessione extends DocumentoDatiSessione {

	protected LancioInterrogazioneModProGesdatec iParametriLancioInterrogazione = null;
	
	protected ModelloProduttivo iModelloProduttivoOriginale = null;
	
	public LancioInterrogazioneModProGesdatec getParametriLancioInterrogazione() {
		return iParametriLancioInterrogazione;
	}
	public void setParametrLancioInterrogazione(LancioInterrogazioneModProGesdatec iParametriLancioInterrogazioene) {
		this.iParametriLancioInterrogazione = iParametriLancioInterrogazioene;
	}
	public ModelloProduttivo getModelloProduttivoOriginale() {
		return iModelloProduttivoOriginale;
	}
	public void setModelloProduttivoOriginale(ModelloProduttivo iModelloProduttivoOriginale) {
		this.iModelloProduttivoOriginale = iModelloProduttivoOriginale;
	}
	
	
}
