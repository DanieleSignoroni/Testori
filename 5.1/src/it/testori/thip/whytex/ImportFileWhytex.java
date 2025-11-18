package it.testori.thip.whytex;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.List;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.batch.BatchRunnable;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.security.Authorizable;

import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.cs.CSVFile;
import it.thera.thip.cs.CSVFileParser;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 18/11/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    18/11/2025  DSSOF3   Prima stesura
 */

public class ImportFileWhytex extends BatchRunnable implements Authorizable {

	protected String iIdAzienda;
	protected String iInboundPath;
	protected String iOutboundPath;

	protected FileFilter fileFilter;
	protected File[] files = null;

	@SuppressWarnings("rawtypes")
	protected static Hashtable cWrappers = new Hashtable();

	public ImportFileWhytex() {
		super();
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public ErrorMessage checkInboundPath() {
		ErrorMessage em = null;
		if(getInboundPath() != null) {
			Path path = Paths.get(getInboundPath());
			if(!Files.exists(path)) {
				em = new ErrorMessage("BAS0000078","Il percorso "+getInboundPath()+ " non esiste");
			}else if(!Files.isDirectory(path)) {
				em = new ErrorMessage("BAS0000078","Il percorso "+getInboundPath()+ " non e' una cartella");
			}
		}
		return em;
	}

	public ErrorMessage checkOutboundPath() {
		ErrorMessage em = null;
		if(getInboundPath() != null) {
			Path path = Paths.get(getOutboundPath());
			if(!Files.exists(path)) {
				em = new ErrorMessage("BAS0000078","Il percorso "+getOutboundPath()+ " non esiste");
			}else if(!Files.isDirectory(path)) {
				em = new ErrorMessage("BAS0000078","Il percorso "+getOutboundPath()+ " non e' una cartella");
			}
		}
		return em;
	}

	public String getIdAzienda() {
		return iIdAzienda;
	}

	public void setIdAzienda(String iIdAzienda) {
		this.iIdAzienda = iIdAzienda;
	}

	public String getInboundPath() {
		return iInboundPath;
	}

	public void setInboundPath(String iInboundPath) {
		this.iInboundPath = iInboundPath;
	}

	public String getOutboundPath() {
		return iOutboundPath;
	}

	public void setOutboundPath(String iOutboundPath) {
		this.iOutboundPath = iOutboundPath;
	}

	public FileFilter getFileFilter() {
		return fileFilter;
	}

	public void setFileFilter(FileFilter fileFilter) {
		this.fileFilter = fileFilter;
	}

	@Override
	protected boolean run() {
		boolean isOk = true;
		try {
			retrieveFiles();
		}catch (Exception e) {
			output.println("Exc in retrieve files "+e.getMessage());
			e.printStackTrace(Trace.excStream);
			isOk = false;
		}
		return isOk;
	}

	protected void retrieveFiles() {
		File folder = new File(getInboundPath());
		this.files = getFileFilter() != null ? folder.listFiles(getFileFilter()) : folder.listFiles();
	}

	@SuppressWarnings("rawtypes")
	public CSVFile parseFile(File file, int numRigheIntestazione) {
		CSVFileParser parser = new CSVFileParser();
		parser.setNomeFile(file.getAbsolutePath());
		int numRighe = numRigheIntestazione - 1;
		parser.setNumRigheIntestazione(new Integer(numRighe));
		CSVFile fileCSV = parser.parse();
		List errori = parser.getErrori();
		if (fileCSV == null) {
			//error in columns
			printList(errori);
		}
		return fileCSV;
	}

	/**
	 * printList
	 *
	 * @param list List
	 */
	@SuppressWarnings("rawtypes")
	protected void printList(List list) {
		for (int i = 0; i < list.size(); i++) {
			output.println(list.get(i));
		}
	}

	@Override
	protected String getClassAdCollectionName() {
		return null;
	}

	public static class ColumnWrapper {
		protected int iSqlType;
		protected String iProperty;
		@SuppressWarnings("rawtypes")
		protected Class iJavaType;
		protected int iJavaSize;

		protected ColumnWrapper() {

		}

		protected ColumnWrapper(String property, @SuppressWarnings("rawtypes") Class javaType) {
			this.iProperty = property;
			this.iJavaType = javaType;
		}

		protected ColumnWrapper(int sqlType, String property, @SuppressWarnings("rawtypes") Class javaType) {
			this.iSqlType = sqlType;
			this.iProperty = property;
			this.iJavaType = javaType;
		}

		public void setSqlType(int sqlType) {
			iSqlType = sqlType;
		}

		public int getSqlType() {
			return iSqlType;
		}

		public void setProperty(String property) {
			iProperty = property;
		}

		public String getProperty() {
			return iProperty;
		}

		public void setJavaType(@SuppressWarnings("rawtypes") Class javaType) {
			iJavaType = javaType;
		}

		@SuppressWarnings("rawtypes")
		public Class getJavaType() {
			return iJavaType;
		}
		//Fix 17715
		public void setJavaSize(int javaSize) {
			iJavaSize = javaSize;
		}

		public int getJavaSize() {
			return iJavaSize;
		}
		//Fix 17715
	}

}