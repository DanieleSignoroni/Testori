package it.testori.thip.base.datiTecnici;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.batch.BatchRunnable;
import com.thera.thermfw.persist.CachedStatement;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.security.Authorizable;

import it.testori.thip.datiTecnici.modpro.InterrogazioneModProGesdatec;
import it.testori.thip.datiTecnici.modpro.InterrogazioneModProGesdatecAttivita;
import it.testori.thip.datiTecnici.modpro.InterrogazioneModProGesdatecMateriale;
import it.testori.thip.datiTecnici.modpro.YTempiRisorseAttivita;
import it.testori.thip.datiTecnici.modpro.YTempiRisorseAtvMinuti;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.generale.PersDatiGen;
import it.thera.thip.datiTecnici.modpro.AttivitaProdMateriale;
import it.thera.thip.datiTecnici.modpro.AttivitaProdRisorsa;
import it.thera.thip.datiTecnici.modpro.AttivitaProduttiva;
import it.thera.thip.datiTecnici.modpro.ModelloProduttivo;
import it.thera.thip.datiTecnici.modpro.ModproEsplosione;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 30/06/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71XXX    30/06/2025  DSSOF3   Prima stesura
 */

public class ImportazioneModProGesdatec extends BatchRunnable implements Authorizable {

	@Override
	protected boolean run() {
		boolean ok = true;
		try {

		}catch (Exception e) {
			output.println("Eccezione non gesita, controllare log applicazione");
			e.printStackTrace(Trace.excStream);
			ok = false;
		}
		return ok;
	}

	//@SuppressWarnings("rawtypes")
	public static InterrogazioneModProGesdatec creaInterrogazioneModelloProduttivoGesdatec(String idCodiceArticoloPadre, String idMaterialePrincipale,
			String idAttivitaPrincipale) {
		InterrogazioneModProGesdatec intt = (InterrogazioneModProGesdatec) Factory.createObject(InterrogazioneModProGesdatec.class);
		intt.setArticoloPadre(getArticoloCurrentCompany(idCodiceArticoloPadre));
		intt.setMaterialePrincipale(getArticoloCurrentCompany(idMaterialePrincipale));
		char[] tipi = new char[3]; 
		tipi[0] = ModelloProduttivo.PRODUZIONE;
		tipi[1] = ModelloProduttivo.ATTREZZAGGIO;
		tipi[2] = ModelloProduttivo.RILAVORAZIONE;

		ModelloProduttivo modProOriginale = null;
		try {
			modProOriginale = ModproEsplosione.trovaModelloProduttivo(Azienda.getAziendaCorrente(),
					intt.getIdArticoloLancio(), PersDatiGen.getCurrentPersDatiGen().getIdStabilimento(), null, null, ModelloProduttivo.GENERICO, tipi);
			if(modProOriginale != null) {
				intt.setModelloProduttivoOrig(modProOriginale);
			}
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		intt.setAltezzaManufatto(intt.getArticoloPadre().getArticoloDatiTecnici().getLunghezza());
		intt.setLunghezzaManufatto(intt.getArticoloPadre().getArticoloDatiTecnici().getLarghezza());

		if(idAttivitaPrincipale != null)
			intt.setIdAttivitaPrincipale(idAttivitaPrincipale);

		/*if(modProOriginale != null) {
			Iterator iterAtvs = modProOriginale.getAttivita().iterator();
			while(iterAtvs.hasNext()) {
				AttivitaProduttiva attivita = (AttivitaProduttiva) iterAtvs.next();
				if(attivita.getDescrizione().getDescrizioneRidotta().equals(InterrogazioneModProGesdatec.FASE_PRINCIPALE)) {
					if(idAttivitaPrincipale != null)
						intt.setIdAttivitaPrincipale(idAttivitaPrincipale);
					else
						intt.setAttivitaPrincipale(attivita.getAttivita());

					//.Calcolo tempo unitario
					if(attivita.getIdAttivita().equals("00780") && attivita.getRisorse().size() > 0) {
						AttivitaProdRisorsa risorsa = (AttivitaProdRisorsa) attivita.getRisorse().get(0);
						if(risorsa.getTempoUnitario() != null) {
							BigDecimal tempoUnitario = risorsa.getTempoUnitario();

							int minuti = tempoUnitario.intValue();

							BigDecimal decimali = tempoUnitario.subtract(BigDecimal.valueOf(minuti)); // es: 0.75
							BigDecimal secondi = decimali.multiply(BigDecimal.valueOf(60)).setScale(0, RoundingMode.HALF_UP); // es: 45

							int secondiInteri = secondi.intValue();
							if(minuti > 0)
								intt.setTempoUnitarioAtv(BigDecimal.valueOf(minuti));
							if(secondiInteri > 0)
								intt.setTempoUnitarioAtvSecondi(secondiInteri);
						}
					}
				}else if(existsAtvParametrica(attivita.getIdAttivita()) && (attivita.getDescrizione().getDescrizioneRidotta().equals(InterrogazioneModProGesdatec.FASE_APPLICAZIONE)
						|| attivita.getDescrizione().getDescrizioneRidotta().equals(InterrogazioneModProGesdatec.FASE_SELEZIONE))){
					InterrogazioneModProGesdatecAttivita atv = (InterrogazioneModProGesdatecAttivita) Factory.createObject(InterrogazioneModProGesdatecAttivita.class);
					atv.setAttivita(attivita.getAttivita());

					if(intt.getMaterialePrincipale() != null) {
						for (Iterator iterator = attivita.getRisorse().iterator(); iterator.hasNext();) {
							AttivitaProdRisorsa risorsa = (AttivitaProdRisorsa) iterator.next();
							String key = KeyHelper.buildObjectKey(new String[] {
									risorsa.getIdAzienda(),
									attivita.getIdAttivita(),intt.getMaterialePrincipale().getIdClasseA()

							});
							YTempiRisorseAttivita tempi = (YTempiRisorseAttivita) Factory.createObject(YTempiRisorseAttivita.class);
							tempi.setKey(key);
							try {
								tempi.retrieve();
							} catch (SQLException e) {
								e.printStackTrace(Trace.excStream);
							}
							for (Iterator iterator2 = tempi.getYTempiRisorseAtvMinuti().iterator(); iterator2.hasNext();) {
								YTempiRisorseAtvMinuti tmp = (YTempiRisorseAtvMinuti) iterator2.next();
								if(Integer.valueOf(intt.getLunghezzaManufatto().intValue()).compareTo(tmp.getFinoA()) <= 0) {
									atv.setQuantita(risorsa.getTempoUnitario().divide(tmp.getMinuti(),6,RoundingMode.DOWN));
									break;
								}
							}
						}
					}

					intt.getRigheAttivita().add(atv);
				}
				Iterator iterMateriali = attivita.getMateriali().iterator();
				while(iterMateriali.hasNext()) {
					AttivitaProdMateriale atvMat = (AttivitaProdMateriale) iterMateriali.next();

					if(atvMat.getSequenzaOrdin() == 10) {
						intt.setMaterialePrincipale(atvMat.getArticolo());
						intt.setInizialeMateriale(String.valueOf(atvMat.getArticolo().getIdArticolo().charAt(0)));
						intt.setIdPoliticaRiordino(atvMat.getArticolo().getIdPoliticaRiordino());
					}else {

						InterrogazioneModProGesdatecMateriale mat = (InterrogazioneModProGesdatecMateriale) Factory.createObject(InterrogazioneModProGesdatecMateriale.class);
						mat.setMateriale(atvMat.getArticolo());
						mat.setUnitaMisura(atvMat.getArticolo().getUMRiferimento());

						mat.setCoeffImpiego(atvMat.getCoeffImpiego());

						intt.getRigheMateriale().add(mat);
					}
				}
			}
			intt.setPriorita(modProOriginale.getPriorita());
		}*/

		intt.setModelloProduttivoOrig(modProOriginale);

		if(intt.getMaterialePrincipale() != null) {
			intt.setAltezzaEffettiva(intt.getMaterialePrincipale().getLunghezza());
		}

		return intt;
	}

	public static InterrogazioneModProGesdatecAttivita creaInterrogazioneModProGesdatecAttivita(InterrogazioneModProGesdatec testata, String idAttivita, BigDecimal quantita) {
		InterrogazioneModProGesdatecAttivita intt = (InterrogazioneModProGesdatecAttivita) Factory.createObject(InterrogazioneModProGesdatecAttivita.class);
		intt.setIdAzienda(testata.getIdAzienda());
		intt.setIdAttivita(idAttivita);
		intt.setQuantita(quantita);
		return intt;
	}

	public static InterrogazioneModProGesdatecMateriale creaInterrogazioneModProGesdatecMateriale(InterrogazioneModProGesdatec testata, String idMateriale, BigDecimal coeffImp) {
		InterrogazioneModProGesdatecMateriale intt = (InterrogazioneModProGesdatecMateriale) Factory.createObject(InterrogazioneModProGesdatecMateriale.class);
		intt.setIdAzienda(testata.getIdAzienda());
		intt.setIdMateriale(idMateriale);
		intt.setCoeffImpiego(coeffImp);
		return intt;
	}

	public static boolean existsAtvParametrica(String idAttivita) {
		String stmt = "SELECT * FROM THIPPERS.YTEMPI_RISORSE_ATTIVITA "
				+ "WHERE ID_AZIENDA = '"+Azienda.getAziendaCorrente()+"' AND R_ATTIVITA = '"+idAttivita+"' ";
		CachedStatement c = null;
		ResultSet r = null;
		try {
			c = new CachedStatement(stmt);
			r = c.executeQuery();
			if(r.next()) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace(Trace.excStream);
		}finally {
			try {
				if(r != null)
					r.close();
				if(c != null)
					c.free();
			}catch (Exception e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return false;
	}

	public static Articolo getArticoloCurrentCompany(String idArticolo) {
		try {
			return (Articolo) Articolo.elementWithKey(Articolo.class, KeyHelper.buildObjectKey(new String[] {
					Azienda.getAziendaCorrente(),idArticolo
			}), PersistentObject.NO_LOCK);
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return null;
	}

	@Override
	protected String getClassAdCollectionName() {
		return "YImpModProGS";
	}

}
