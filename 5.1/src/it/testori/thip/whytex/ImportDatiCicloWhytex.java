package it.testori.thip.whytex;

import java.io.File;
import java.io.FileFilter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;

import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.generale.PersDatiGen;
import it.thera.thip.base.risorse.Risorsa;
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
							elaboraFile(csvFile);
							if (!moveFileToOutbound(file)) {
								output.println("ATTENZIONE: Fallito lo spostamento del file: " + file.getName());
							}
						}
					}catch (Exception e) {
						output.println("exc : "+e.getMessage());
						e.printStackTrace(Trace.excStream);
					}
					output.println();
				}
			}
		}
		output.println("");
		return ok;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean elaboraRiga(CSVRiga rigaCSV, Integer numeroRiga) {
		boolean ok = true;
		try {
			String idArticoloPadre = rigaCSV.getValore(0);
			String sequenzaStr = rigaCSV.getValore(1);
			Integer sequenza;
			try {
				sequenzaStr = sequenzaStr.replace("\uFEFF", "").trim();
				sequenza = Integer.parseInt(sequenzaStr) / 10;

			} catch (NumberFormatException e) {
				e.printStackTrace(Trace.excStream);
				return false;
			}
			String idMatricola = null;
			String tempo = rigaCSV.getValore(3);
			String idRisorsa = rigaCSV.getValore(2);
			if(rigaCSV.getValori().size() > 3) {
				idMatricola = rigaCSV.getValore(4);
				if("NULL".equals(idMatricola)) {
					idMatricola = null;
				}
			}
			ModelloProduttivo modelloProduttivo = null;
			Risorsa risorsa = null;
			if(idMatricola != null) {
				risorsa = (Risorsa) Risorsa.elementWithKey(Risorsa.class, KeyHelper.buildObjectKey(new String[] {
						Azienda.getAziendaCorrente(),String.valueOf(Risorsa.MACCHINE),String.valueOf(Risorsa.MATRICOLA),idMatricola
				}), PersistentObject.NO_LOCK);
			}
			if(risorsa == null) { 
				risorsa = (Risorsa) Risorsa.elementWithKey(Risorsa.class, KeyHelper.buildObjectKey(new String[] {
						Azienda.getAziendaCorrente(),String.valueOf(Risorsa.MACCHINE),String.valueOf(Risorsa.RISORSA),idRisorsa
				}), PersistentObject.NO_LOCK);
			}
			if(risorsa == null) {
				output.println(" err: --> riga "+numeroRiga+", articolo "+idArticoloPadre+", risorsa non trovata con codice matricola = "+idMatricola+", o con codice risorsa = "+idRisorsa);
				return false;
			}
			try {
				char[] tipi = new char[3]; 
				tipi[0] = ModelloProduttivo.PRODUZIONE;
				tipi[1] = ModelloProduttivo.ATTREZZAGGIO;
				tipi[2] = ModelloProduttivo.RILAVORAZIONE;
				modelloProduttivo = ModproEsplosione.trovaModelloProduttivo(Azienda.getAziendaCorrente(),
						idArticoloPadre, PersDatiGen.getCurrentPersDatiGen().getIdStabilimento(), null, null, "N", ModelloProduttivo.GENERICO, tipi, ModelloProduttivo.PRODUZIONE);
				if(modelloProduttivo != null) {
					BigDecimal tempoUnitarioBD = stringToBigDecimal(tempo);
					Iterator iterAtvProd = modelloProduttivo.getAttivita().iterator();
					while(iterAtvProd.hasNext()) {
						AttivitaProduttiva attivita = (AttivitaProduttiva) iterAtvProd.next();
						if(attivita.getIdRigaAttivita().compareTo(sequenza) == 0) {
							if(risorsa == null) {
								settaTempoUnitarioRisorsaPrincipale(attivita, tempoUnitarioBD);
							}else {
								settaTempoUnitarioRisorsa(attivita,risorsa, tempoUnitarioBD);
							}
						}
						attivita.setDirty();
						int rc = attivita.save();
						if(rc > 0) {
							output.println(" msg: --> riga "+numeroRiga+", articolo "+idArticoloPadre+" attivita "+KeyHelper.formatKeyString(attivita.getKey())+" aggiornata correttamente, rc = "+rc);
							numRigheInserite++;
						}else {
							ok = false;
							numRigheConErrori++;
							output.println(" err: --> riga "+numeroRiga+", articolo "+idArticoloPadre+" attivita "+KeyHelper.formatKeyString(attivita.getKey())+" aggiornata con errore, rc = "+rc);
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
	protected void settaTempoUnitarioRisorsa(AttivitaProduttiva attivitaProduttiva, Risorsa risorsa, BigDecimal tempoUnitario) {
		Iterator iterRisorse = attivitaProduttiva.getRisorse().iterator();
		while(iterRisorse.hasNext()) {
			AttivitaProdRisorsa atvProdRsr = (AttivitaProdRisorsa) iterRisorse.next();
			if(risorsa.getKey().equals(risorsa.getKey())) {
				atvProdRsr.setTempoUnitario(tempoUnitario);
				atvProdRsr.setTempoFisso(null);
				atvProdRsr.setRUmTempi("min");
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
				risorsa.setRUmTempi("min");
			}
		}
	}

	@Override
	protected String getClassAdCollectionName() {
		return "YImpDatiCicloWyhtex";
	}

}
