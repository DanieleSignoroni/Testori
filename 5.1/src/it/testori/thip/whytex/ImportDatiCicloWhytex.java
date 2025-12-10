package it.testori.thip.whytex;

import java.io.File;
import java.io.FileFilter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.KeyHelper;

import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.generale.PersDatiGen;
import it.thera.thip.cs.CSVFile;
import it.thera.thip.cs.CSVRiga;
import it.thera.thip.datiTecnici.modpro.AttivitaProdRisorsa;
import it.thera.thip.datiTecnici.modpro.AttivitaProduttiva;
import it.thera.thip.datiTecnici.modpro.ModelloProduttivo;
import it.thera.thip.datiTecnici.modpro.ModproEsplosione;

/**
 * Importazione dei dati relativi al ciclo di produzione di un articolo.<br>
 * La classe legge una cartella di 'inbound', processa i file e li sposta in una cartella di 'outbound'.
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 10/12/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72250    10/12/2025  DSSOF3   Prima stesura
 */

public class ImportDatiCicloWhytex extends ImportFileWhytex {

	public ImportDatiCicloWhytex() {
		super();
	}

	@Override
	protected boolean run() {
		setFileFilter((FileFilter) CSV_FILE_FILTER);
		boolean ok = super.run();
		if(ok) {
			if(files != null && files.length == 0) {
				output.println("Nessun file da importare...");
			}else if(files != null){
				for(File file : files) {
					try {
						CSVFile csvFile = parseFile(file, 1);
						if(csvFile != null) {
							output.println("Elaborazione file : "+csvFile.getNomeFile());
							elaboraFile(csvFile);
							output.println("Termine elaborazione file : "+csvFile.getNomeFile());
							if (!moveFileToOutbound(file)) {
								output.println("ATTENZIONE: Fallito lo spostamento del file: " + file.getName());
							}
						}
					}catch (Exception e) {
						output.println("exc : "+e.getMessage());
						e.printStackTrace(Trace.excStream);
					}
				}
			}
		}
		return ok;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean elaboraRiga(CSVRiga rigaCSV, Integer numeroRiga) {
		boolean ok = true;
		try {
			String idArticoloPadre = rigaCSV.getValore(0);
			String idOperazione = rigaCSV.getValore(1);
			String tempo = rigaCSV.getValore(2);
			String risorsa = null;
			if(rigaCSV.getValori().size() > 3) {
				risorsa = rigaCSV.getValore(3);
				if("NULL".equals(risorsa)) {
					risorsa = null;
				}
			}
			ModelloProduttivo modelloProduttivo = null;
			try {
				char[] tipi = new char[3]; 
				tipi[0] = ModelloProduttivo.PRODUZIONE;
				tipi[1] = ModelloProduttivo.ATTREZZAGGIO;
				tipi[2] = ModelloProduttivo.RILAVORAZIONE;
				modelloProduttivo = ModproEsplosione.trovaModelloProduttivo(Azienda.getAziendaCorrente(),
						idArticoloPadre, PersDatiGen.getCurrentPersDatiGen().getIdStabilimento(), null, null, ModelloProduttivo.GENERICO, tipi);
				if(modelloProduttivo != null) {
					BigDecimal tempoUnitarioBD = stringToBigDecimal(tempo);
					Iterator iterAtvProd = modelloProduttivo.getAttivita().iterator();
					while(iterAtvProd.hasNext()) {
						AttivitaProduttiva attivita = (AttivitaProduttiva) iterAtvProd.next();
						if(attivita.getIdOperazione().equals(idOperazione)) {
							if(risorsa == null) {
								settaTempoUnitarioRisorsaPrincipale(attivita, tempoUnitarioBD);
							}else {
								settaTempoUnitarioRisorsa(attivita,risorsa, tempoUnitarioBD);
							}
						}
						attivita.setDirty();
						int rc = attivita.save();
						if(rc > 0) {
							output.println("attivita "+KeyHelper.formatKeyString(attivita.getKey())+" aggiornata correttamente, rc = "+rc);
							numRigheInserite++;
						}else {
							ok = false;
							numRigheConErrori++;
							output.println("attivita "+KeyHelper.formatKeyString(attivita.getKey())+" aggiornata con errore, rc = "+rc);
						}
					}
				}else{
					output.println("Per l'articolo : "+idArticoloPadre+" non e' stato trovato nessun modello generico di produzione");
				}
			} catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}catch (Exception e) {
			e.printStackTrace(Trace.excStream);
			ok = false;
			output.println("Riga CSV :"+numeroRiga+" errore: "+e.getMessage());
			numRigheConErrori++;
		}
		return ok;
	}

	@SuppressWarnings("rawtypes")
	protected void settaTempoUnitarioRisorsa(AttivitaProduttiva attivitaProduttiva, String idRisorsa, BigDecimal tempoUnitario) {
		Iterator iterRisorse = attivitaProduttiva.getRisorse().iterator();
		while(iterRisorse.hasNext()) {
			AttivitaProdRisorsa risorsa = (AttivitaProdRisorsa) iterRisorse.next();
			if(risorsa.getRRisorsa().equals(idRisorsa)) {
				risorsa.setTempoUnitario(tempoUnitario);
				risorsa.setTempoFisso(null);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void settaTempoUnitarioRisorsaPrincipale(AttivitaProduttiva attivitaProduttiva, BigDecimal tempoUnitario) {
		Iterator iterRisorse = attivitaProduttiva.getRisorse().iterator();
		while(iterRisorse.hasNext()) {
			AttivitaProdRisorsa risorsa = (AttivitaProdRisorsa) iterRisorse.next();
			if(risorsa.getPolConsRisorse() == AttivitaProduttiva.PRINCIPALE) {
				risorsa.setTempoUnitario(tempoUnitario);
				risorsa.setTempoFisso(null);
			}
		}
	}

	@Override
	protected String getClassAdCollectionName() {
		return "YImpDatiCicloWyhtex";
	}

}
