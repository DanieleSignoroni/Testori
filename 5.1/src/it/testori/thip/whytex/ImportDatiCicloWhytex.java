package it.testori.thip.whytex;

import java.io.File;

import com.thera.thermfw.base.Trace;

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

public class ImportDatiCicloWhytex extends ImportFileWhytex {

	public ImportDatiCicloWhytex() {
		super();
	}

	@Override
	protected boolean run() {
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
	public boolean elaboraRiga(CSVRiga rigaCSV, Integer integer) {
		return false;
	}

	@Override
	protected String getClassAdCollectionName() {
		return "YImpDatiCicloWyhtex";
	}

}
