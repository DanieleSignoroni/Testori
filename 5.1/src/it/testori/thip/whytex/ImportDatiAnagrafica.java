package it.testori.thip.whytex;

import java.io.File;
import java.io.FileFilter;
import java.math.BigDecimal;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;

import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.cs.CSVFile;
import it.thera.thip.cs.CSVRiga;

/**
 *
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
 * 72XXX    10/12/2025  DSSOF3   Prima stesura
 */

public class ImportDatiAnagrafica extends ImportFileWhytex {

	public ImportDatiAnagrafica() {
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
							if (numRigheConErrori == 0 && !moveFileToOutbound(file)) {
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

	@Override
	public boolean elaboraRiga(CSVRiga rigaCSV, Integer numeroRiga) {
		boolean ok = true;
		try {
			String idArticolo = rigaCSV.getValore(0);
			String peso = rigaCSV.getValore(1);
			String altezza = rigaCSV.getValore(2);
			String altezzaMin = rigaCSV.getValore(3);
			BigDecimal pesoBD = stringToBigDecimal(peso);
			BigDecimal altezzaBD = stringToBigDecimal(altezza);
			BigDecimal altezzaMinBD = stringToBigDecimal(altezzaMin);

			Articolo art = (Articolo) Articolo.elementWithKey(Articolo.class, KeyHelper.buildObjectKey(new String[] {
					Azienda.getAziendaCorrente(),idArticolo
			}), PersistentObject.NO_LOCK);
			if(art != null) {
				art.setPeso(pesoBD);
				art.setLunghezza(altezzaBD);
				art.setAltezza(altezzaMinBD);
				int rc = art.getArticoloDatiTecnici().save();
				if(rc > 0) {
					numRigheInserite++;
				}else {
					ok = false;
					numRigheConErrori++;
				}
			}else {
				output.println("Articolo con codice : "+idArticolo+" non esiste in Panthera");
			}

		}catch (Exception e) {
			e.printStackTrace(Trace.excStream);
			ok = false;
			output.println("Riga CSV :"+numeroRiga+" errore: "+e.getMessage());
			numRigheConErrori++;
		}
		if (ok)
			numRigheInserite++;
		return ok;
	}


	@Override
	protected String getClassAdCollectionName() {
		return "YImpDatiAnagrWyhtex";
	}
}
