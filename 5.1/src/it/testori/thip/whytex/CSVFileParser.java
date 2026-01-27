package it.testori.thip.whytex;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.thera.thermfw.base.*;

import it.thera.thip.cs.CSVColonna;
import it.thera.thip.cs.CSVFile;
import it.thera.thip.cs.CSVRiga;

/**
 * <p>Title: Thip</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: SOFTHER</p>
 * @author MBR
 * @version 1.0
 */
/* Revisions:
 * Date          Owner      Description
 * 14/05/2009    MBR        FIX 10845 - primo implemento
 * 11/13/2011    IElouni    Fix 13726 - aggiornamento metodo parseLine
 * 10/05/2021	 FB			Fix 33563 - Reso modificabile il carattere separatore
 */
public class CSVFileParser {

	public static final String SEPARATORE = ";";
	public static final String TRATTINO = "-";

	public static final String resourceCSVFileParser = "it.thera.thip.cs.resources.CSVFileParser";

	protected String iNomeFile;
	protected Integer iNumRigheIntestazione;
	@SuppressWarnings("rawtypes")
	protected List iErrori = new ArrayList();
	protected CSVFile iFileCSV;
	protected String iSeparatore; //33563

	public CSVFileParser() {
		iNumRigheIntestazione = new Integer(1);
		setSeparatore(SEPARATORE); //33563
	}

	/**
	 *
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	public List getErrori() {
		return iErrori;
	}

	/**
	 *
	 * @return CSVFile
	 */
	public CSVFile getFileCSV() {
		return iFileCSV;
	}

	/**
	 *
	 * @return String
	 */
	public String getNomeFile() {
		return iNomeFile;
	}

	/**
	 *
	 * @return Integer
	 */
	public Integer getNumRigheIntestazione() {
		return iNumRigheIntestazione;
	}

	/**
	 *
	 * @param iErrori List
	 */
	@SuppressWarnings("rawtypes")
	public void setErrori(List errori) {
		this.iErrori = errori;
	}

	/**
	 *
	 * @param iFileCSV CSVFile
	 */
	public void setFileCSV(CSVFile fileCSV) {
		this.iFileCSV = fileCSV;
	}

	/**
	 *
	 * @param iNomeFile String
	 */
	public void setNomeFile(String nomeFile) {
		this.iNomeFile = nomeFile;
	}

	/**
	 *
	 * @param iNumRigheIntestazione Integer
	 */
	public void setNumRigheIntestazione(Integer numRigheIntestazione) {
		this.iNumRigheIntestazione = numRigheIntestazione;
	}

	/**
	 *
	 * @return CSVFile
	 */
	public CSVFile parse() {
		CSVFile fileCSV = new CSVFile();
		fileCSV.setNomeFile(getNomeFile());
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(getNomeFile()),
							StandardCharsets.UTF_8
							)
					);
			boolean ok = true;
			if(getNumRigheIntestazione() > 0)
				ok = aggiungeColonne(fileCSV, reader);

			if (ok) {
				//ok = saltaRigheIntestazione(reader);
				if (ok) { //se ce piu righe
					aggiungeRighe(fileCSV, reader);
				}
			}
			else{//caso di errori
				fileCSV = null;
			}
		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace(Trace.excStream);
		}
		catch (IOException ex) {
			ex.printStackTrace(Trace.excStream);
		}
		finally {
			if (reader != null)
				try {
					reader.close();
				}
			catch (IOException ex1) {
				ex1.printStackTrace(Trace.excStream);
			}
		}
		setFileCSV(fileCSV);
		return fileCSV;
	}

	/**
	 *
	 * @param fileCSV CSVFile
	 * @param valori List
	 * @throws MissingResourceException
	 */
	@SuppressWarnings("rawtypes")
	protected boolean aggiungeColonne(CSVFile fileCSV, BufferedReader reader) throws IOException {
		List valori = parseLine(reader.readLine());
		for (int posizione = 0; posizione < valori.size(); posizione++) {
			String nomeColonna = (String) valori.get(posizione);
			nomeColonna = nomeColonna.replace("\uFEFF", "").trim();
			if (!aggiungeColonna(fileCSV, posizione, nomeColonna)) {
				return false;
			}
		}
		return true;
	}

	/**
	 *
	 * @param fileCSV CSVFile
	 * @param posizione int
	 * @param nomeColonna String
	 * @return boolean
	 * @throws MissingResourceException
	 */
	protected boolean aggiungeColonna(CSVFile fileCSV, int posizione, String nomeColonna) throws MissingResourceException {
		if (isEmpty(nomeColonna)) {
			//Non può esistere una colonna senza nome
			addErrore(ResourceLoader.getString(resourceCSVFileParser, "ColonnaSenzaNome"));
			return false;
		}
		if (fileCSV.getColonna(nomeColonna) != null) {
			//Il nome di una colonna non può essere presente più di una volta
			addErrore(ResourceLoader.getString(resourceCSVFileParser, "ColonnaEsistente"));
			return false;
		}
		CSVColonna colonna = new CSVColonna();
		colonna.setNomeColonna(nomeColonna);
		colonna.setPosizione(new Integer(posizione));
		fileCSV.addColonna(colonna);
		return true;
	}

	/**
	 *
	 * @param reader BufferedReader
	 * @param line String
	 * @return String
	 * @throws IOException
	 */
	protected boolean saltaRigheIntestazione(BufferedReader reader) throws IOException {
		int i = 0;
		while (i++ < getNumRigheIntestazione().intValue()) {
			if (reader.readLine() == null) {
				return false;
			}
		}
		return true;
	}

	/**
	 *
	 * @param fileCSV CSVFile
	 * @param reader BufferedReader
	 * @throws IOException
	 * @throws MissingResourceException
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	protected void aggiungeRighe(CSVFile fileCSV, BufferedReader reader) throws IOException, MissingResourceException {
		int numeroColonne = fileCSV.getColonne().size();
		int numeroRiga = 1;
		String line = reader.readLine();
		while (line != null) {
			List valori = parseLine(line);
			/*if (valori.size() != numeroColonne) {
				addErrore(ResourceLoader.getString(resourceCSVFileParser, "NumeroColonneErrata", new Object[] {new Integer(numeroRiga)}));
			}
			else {
			 */
			aggiungeRiga(fileCSV, valori);
			//}
			line = reader.readLine();
			numeroRiga++;
		}
	}

	/**
	 *
	 * @param fileCSV CSVFile
	 * @param valori List
	 */
	@SuppressWarnings("rawtypes")
	protected void aggiungeRiga(CSVFile fileCSV, List valori) {
		CSVRiga rigaCSV = new CSVRiga();
		for (int i = 0; i < valori.size(); i++) {
			String valore = (String) valori.get(i);
			if (isEmpty(valore) || valore.equals(TRATTINO)) {
				rigaCSV.addValore(CSVFile.NULL_VALUE);
			}
			else {
				rigaCSV.addValore(valore.trim());
			}
		}
		fileCSV.addRiga(rigaCSV);
	}

	/**
	 *
	 * @param errore String
	 */
	@SuppressWarnings("unchecked")
	protected void addErrore(String errore) {
		iErrori.add(errore);
	}

	/**
	 *
	 * @param line String
	 * @return List
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List parseLine(String line) {
		// FIX: Rimuovo il BOM anche se presente nei dati della riga
		if (line != null) {
			line = line.replace("\uFEFF", "");
		}
		List valori = new ArrayList();
		if (!isEmpty(line)) {
			//StringTokenizer tokenizer = new StringTokenizer(line, "" + SEPARATORE, true);
			StringTokenizer tokenizer = new StringTokenizer(line, "" + getSeparatore(), true);
			boolean lastWasDelimiter = true;
			while (tokenizer.hasMoreTokens()) {
				String token = tokenizer.nextToken();
				boolean isDelimiter = isDelimter(token);
				if (isDelimiter) {
					if (lastWasDelimiter) {
						valori.add(null);
					}
				}
				else {
					valori.add(token);
				}
				lastWasDelimiter = isDelimiter;
			}
			//Fix 13726 L'ultima colonna null
			if (lastWasDelimiter) {
				valori.add(null);
			}
			//Fix 13726 Fine
		}

		return valori;
	}

	/**
	 *
	 * @param value String
	 * @return boolean
	 */
	protected boolean isDelimter(String value) {
		//return (value != null) && value.equals(SEPARATORE); //33563
		return (value != null) && value.equals(getSeparatore()); //33563
	}

	/**
	 *
	 * @param value String
	 * @return boolean
	 */
	protected boolean isEmpty(String value) {
		return (value == null) || (value.trim().length() == 0);
	}

	//33563 >
	public void setSeparatore(String sep) {
		this.iSeparatore=sep;
	}
	public String getSeparatore() {
		return this.iSeparatore;
	}
	//33563 <
}
