package it.testori.thip.whytex;

import java.io.File;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.batch.BatchJob;
import com.thera.thermfw.batch.BatchOptions;
import com.thera.thermfw.batchload.RunBatchLoader;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Database;
import com.thera.thermfw.persist.ErrorCodes;
import com.thera.thermfw.persist.Factory;

import it.thera.thip.cs.CSVColonna;
import it.thera.thip.cs.CSVFile;
import it.thera.thip.cs.CSVRiga;
import it.thera.thip.cs.GestoreCommit;
import it.thera.thip.cs.ThipException;
import it.thera.thip.datiTecnici.modpro.CMModProSemplificati;
import it.thera.thip.datiTecnici.modpro.CMModproSempl;
import it.thera.thip.datiTecnici.modpro.CMModproSemplTM;

/**
 * Importazione dei dati relativi alla distinta base di un modello produttivo.<br></br>
 * Il lavoro legge il .csv e popola la tabella dei cm modelli produttivi semplificati {@value CMModproSemplTM#TABLE_NAME}.<br>
 * Al termine della popolazioene viene lanciato il lavoro batch che importa i modelli produttivi.<br></br>
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

@SuppressWarnings("unchecked")
public class ImportModProWyhtex extends ImportFileWhytex {

	static {
		cWrappers.put(CMModproSemplTM.R_ARTICOLO_PADRE, new ColumnWrapper("IdArticoloPadre", String.class));
		cWrappers.put(CMModproSemplTM.SEQUENZA_ORDIN, new ColumnWrapper("SequenzaOrdin", Short.class));
		cWrappers.put(CMModproSemplTM.R_ARTICOLO, new ColumnWrapper("IdArticolo", String.class));
		cWrappers.put(CMModproSemplTM.COEFF_IMPIEGO, new ColumnWrapper("CoeffImpiego", BigDecimal.class));
		cWrappers.put(CMModproSemplTM.R_MODELLO_TEMPL, new ColumnWrapper("IdModelloTempl", Integer.class));
	}

	protected RunBatchLoader iRunParameter = null;

	public ImportModProWyhtex() {
		super();
		iRunParameter = (RunBatchLoader) Factory.createObject(RunBatchLoader.class);
		iRunParameter.setOwner(this);
		iRunParameter.setOnlySimulator(true);
		iRunParameter.setEntityId("CMModProSempl");
	}

	public void setDirty() {
	}

	public void setSimulazione(boolean simulazione) {
		iRunParameter.setOnlySimulator(simulazione);
	}

	public boolean isSimulazione() {
		return iRunParameter.getOnlySimulator();
	}

	public RunBatchLoader getRunParameter() {
		return iRunParameter;
	}

	@Override
	protected boolean run() {
		boolean ok = super.run();
		if(ok) {
			if(files != null && files.length == 0) {
				output.println("Nessun file da importare...");
			}else if(files != null){
				pulisciCM();
				for(File file : files) {
					try {
						CSVFile csvFile = parseFile(file, 1);
						if(csvFile != null) {
							completaColonne(csvFile);
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
				if(numRigheInserite > 0) {
					CMModProSemplificati cmSempl = (CMModProSemplificati) Factory.createObject(CMModProSemplificati.class);
					cmSempl.setIdAzienda(getIdAzienda());
					BatchOptions opt = (BatchOptions) Factory.createObject(BatchOptions.class);
					opt.setExecutionMode(BatchOptions.IMMEDIATE_EXECUTION);			
					try {
						cmSempl.getRunParameter().setEqual(getRunParameter());
						opt.initDefaultValues(CMModProSemplificati.class, "CMModProSempl", "RUN");
						cmSempl.setBatchJob(opt.getBatchJob());
						int resSave = cmSempl.save();
						if (resSave >= ErrorCodes.NO_ROWS_UPDATED) {
							ConnectionManager.commit();
							ok = sottomettiEAspettaLavoro(cmSempl.getBatchJob());
						}
						else
							ConnectionManager.rollback();

					} catch (Exception e) {
						e.printStackTrace(Trace.excStream);
					}
				}
			}
		}
		return ok;
	}

	protected void pulisciCM() {
		PreparedStatement ps = null;
		try {
			String del = "DELETE FROM "+CMModproSemplTM.TABLE_NAME+" WHERE "+CMModproSemplTM.DATA_ORIGIN+" = ?"
					+ "AND "+CMModproSemplTM.RUN_ID+" = ?  ";
			Connection connection = ConnectionManager.getCurrentConnection();
			ps = connection.prepareStatement(del);
			ps.setString(1, getRunParameter().getDataOrigin());
			ps.setInt(2, getRunParameter().getRunId());
			int num = ps.executeUpdate();
			if(num > 0) {
				connection.commit();
			}
		}catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(Trace.excStream);
				}
			}
		}
	}

	/**
	 *
	 * @param fileCSV CSVFile
	 * @param errori List
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	protected void importaDati(CSVFile fileCSV, List errori) throws SQLException {
		List righe = fileCSV.getRighe();
		if (!righe.isEmpty()) {
			if (!errori.isEmpty()) {
				printList(errori);
				job.setApplStatus(BatchJob.WITH_WARNING);
			}
			elaboraFile(fileCSV);
		}
	}

	@SuppressWarnings("rawtypes")
	protected void elaboraFile(CSVFile fileCSV) throws SQLException {
		GestoreCommit gestoreCommit = new GestoreCommit(10);
		int numeroRiga = 1;
		for (Iterator ri = fileCSV.getRigheIterator(); ri.hasNext(); ) {
			CSVRiga rigaCSV = (CSVRiga) ri.next();
			elaboraRiga(rigaCSV, new Integer(numeroRiga++));
			gestoreCommit.commit();
			checkPoint();
		}
		if (numRigheConErrori >= 1)
			job.setApplStatus(BatchJob.WITH_WARNING);
		if(numRigheInserite > 0)
			gestoreCommit.fine(true);
		else
			gestoreCommit.fine(false);
	}

	public boolean elaboraRiga(CSVRiga rigaCSV, Integer numeroRiga) {
		boolean ok = false;
		CMModproSempl cm = (CMModproSempl) Factory.createObject(CMModproSempl.class);
		cm.setDataOrigin(getRunParameter().getDataOrigin());
		cm.setRunId(getRunParameter().getRunId());
		cm.setRowId(numeroRiga);
		try {
			aggiornaInternal(cm, rigaCSV, numeroRiga);
			ok = (cm.save() > 0);
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

	@SuppressWarnings("rawtypes")
	protected boolean aggiornaInternal(CMModproSempl cm, CSVRiga rigaCSV, Integer numeroRiga) throws Exception {
		Iterator colonne = rigaCSV.getFileCSV().getColonne().values().iterator();
		while (colonne.hasNext()) {
			CSVColonna colonna = (CSVColonna) colonne.next();
			String nome = colonna.getNomeColonna();
			Object valore = null;
			valore = rigaCSV.getValoreToObject(nome);
			String valoreStr = rigaCSV.getValore(colonna.getPosizione().intValue());
			if (!isEmpty(valoreStr) && (valore == null)) {
				return false;
			}

			ColumnWrapper wrapper = (ColumnWrapper) cWrappers.get(nome);
			if(wrapper != null && wrapper.getJavaType() == String.class && valore == null)
				valore = valore != null ? valore : "";
			if (wrapper != null && valore != null) {
				if(wrapper.getJavaType() == String.class){
					if(((String)valore).length() > wrapper.getJavaSize()){
						valore = ((String)valore).substring(0, wrapper.getJavaSize());
					}
				}
				String setter = "set" + wrapper.getProperty();
				Object target = cm;
				Method setMethod = target.getClass().getMethod(setter, new Class[] {wrapper.getJavaType()});
				setMethod.invoke(target, new Object[] {valore});
			}
		}
		return true;
	}

	protected boolean isEmpty(String value) {
		return value.equals(CSVFile.NULL_VALUE);
	}

	@SuppressWarnings({ "rawtypes" })
	protected void completaColonne(CSVFile fileCSV) throws SQLException {
		String schema = CMModproSemplTM.TABLE_NAME.substring(0, CMModproSemplTM.TABLE_NAME.indexOf("."));
		String table = CMModproSemplTM.TABLE_NAME.substring(CMModproSemplTM.TABLE_NAME.indexOf(".") + 1);
		Connection connection = ConnectionManager.getCurrentConnection();
		Database db = ConnectionManager.getCurrentDatabase();
		DatabaseMetaData metadata = db.getDatabaseMetaData(connection);
		ResultSet rs = metadata.getColumns(connection.getCatalog(), schema, table, null);
		Map colonneDaTM = new Hashtable();
		while (rs.next()) {
			List data = new ArrayList();
			data.add(rs.getString("DATA_TYPE"));
			data.add(rs.getString("COLUMN_SIZE"));
			colonneDaTM.put(rs.getString("COLUMN_NAME").trim(), data);
		}
		Set colonneCSV = fileCSV.getColonne().keySet();
		Iterator colonneCSVIter = colonneCSV.iterator();
		while(colonneCSVIter.hasNext()){
			String nomeCol = (String) colonneCSVIter.next();
			nomeCol = nomeCol.replace("\uFEFF", "").trim();
			if (colonneDaTM.containsKey(nomeCol)) {
				CSVColonna colonna = (CSVColonna)fileCSV.getColonne().get(nomeCol);
				List columnData =  (List)colonneDaTM.get(nomeCol);
				colonna.setTipo(new Integer((String)columnData.get(0)));
				ColumnWrapper wrapper = (ColumnWrapper) cWrappers.get(nomeCol);
				if (wrapper != null) {
					colonna.setJavaClass(wrapper.getJavaType());
					wrapper.setJavaSize(new Integer((String)columnData.get(1)).intValue());
				}
			}
			else {
				throw new ThipException("ColonnaSbagliata" + new Object[] {nomeCol});
			}
		}
	}

	@Override
	protected String getClassAdCollectionName() {
		return "YImpModProWyhtex";
	}

}