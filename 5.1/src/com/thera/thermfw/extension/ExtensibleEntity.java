/*
 * @(#)ExtensibleEntity.java
 */

/**
 * ExtensibleEntity
 *
 * <br></br><b>Copyright (C) : Thera s.p.a.</b>
 * @author Wizard 04/06/2010 at 12:14:51
 */
/* Revisions:
 * Number   Date          Owner        Description
 *          04/06/2010    Wizard       Codice generato da Wizard
 * 13355    11/10/2010    CB           Layout a video
 * 13548     11/11/2010   Meziou Anis  Fasi di attuazione della generazione
 * 13585    24/11/2010    Meziou Anis  Relazioni
 * 13733    14/12/2010    Meziou Anis  Componente therm
 * 13734    03/01/2011    Meziou Anis  Components
 * 13735    04/01/2011    Meziou Anis  Collezioni
 * 13736    08/02/2011    Meziou Anis  Generazione
 * 14088    15/03/2011    CB           Layout a video correzioni e implementi
 * 14258    05/04/2011    Meziou Anis  coretto la generazione su websphere
 * 14839    25/07/2011    DF+ES        Correzione di alcuni problemi
 * 14803    26/07/2011    Meziou Anis  add controls su nome attributes
 * 15433    16/01/2012    Meziou Anis  modifica azioni esporta/importa
 * 15501    18/01/2012    Meziou Anis  estendere controls su nome attributes
 * 15587    02/02/2012    Meziou Anis  implementare l'uso di <Alter table>
 * 15802    01/03/2012    Meziou Anis  migliorare la validazione del nome di classe e del nome del package.
 * 15800    05/03/2012    Meziou Anis  Migliorare i meccanismi di validazione prima di generare l'estensione
 * 17011    18/10/2012    Meziou Anis  corretto per attributi inherited
 * 17794    29/04/2013    Meziou Anis  implemento la delete e checkDelete
 * 17793    14/05/2012    Meziou Anis  Introdurre la possibilità di utilizzare i numeratori Therm.
 * 18574    07/10/2013    Mz           Gestione viste per generazione dizionario
 * 22996    11/02/2016    RA    	   Aggiunto controllo su ClasseName per verefica che non esiste un entità con stesso Id.
 * 23811    30/06/2016    RA           Gestione valori predefiniti per qualche attributo
 * 28381    07/12/2018    Mz           Modifiche varie
 * 30122    11/11/2019    Mz           modifiche varie
 * 30451    09/01/2020    Mz           migliorie gestione replacement
 * 32879    04/02/2021    Mz           limitare gestione replacement al solo tipo estesa
 * 33178    19/03/2021    Mz           Gestione excludeFromInsert e excludeFromUpdate
 * 33720    08/10/2021    Mz           Controllo classe già personalizzata
 * 37762	06/02/2023	  AM		   Rimuovere la scheda nls da layoutTabbeds quando il flag nls è falso
 * 42909	16/07/2024	  AM		   Implementazione del LoggablePersistentObject per estensione singola.
 * 44891	20/02/2025	  AM		   migliorie gestione di check Nome su les component e relazione e attributi..
 * 45830	21/05/2025	  AM		   Add attribut FormActionAdapter for manage of Allow customization of the form action adapter in JSP generation
 * 46485	07/07/2025	  AM		   Aggiungere la generazione del file js anche nel caso di estensioni SINGOLA
 */

package com.thera.thermfw.extension;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.regex.*;

import javax.xml.parsers.*;

import org.xml.sax.*;
import com.thera.thermfw.ad.*;
import com.thera.thermfw.base.*;
import com.thera.thermfw.cbs.*;
import com.thera.thermfw.collector.*;
import com.thera.thermfw.common.*;
import com.thera.thermfw.dict.*;
import com.thera.thermfw.extension.web.*;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.security.*;
import com.thera.thermfw.setup.*;
import com.thera.thermfw.type.*;
import com.thera.thermfw.util.*;
import com.thera.thermfw.web.*;
import com.thera.thermfw.web.servlet.FormActionAdapter;
import com.thera.thermfw.xml.*;

public class ExtensibleEntity extends ExtensibleEntityPO {

	//44891 inizio
	public static final String PROPERTIE_FILE_EXT_ENTITY = "com.thera.thermfw.extension.resources.ExtensibleEntity";
	public static final String RELAZIONE_ID = "Relazione";
	public static final String ATTRIBUTO_ID = "Attributo";
	public static final String COMPONENTE_ID = "Componente";
	public static final String COLLECTION_ID = "Collection";
	//44891 fine	
	
  public static final String PROPERTIE_FILE = "com.thera.thermfw.extension.resources.default_";
  public static final String BASE_CLASS_NAME = "BaseClasseName";
//Fix 42909 inizio
  public static final String LOGGABLE_CLASS_NAME = "LoggableClasseName";
  public static final String LOGGABLE_CLASS = "com.thera.thermfw.log.LoggablePersistentObject";
  public static final String DEFAULT_CLASS_EXTENDED = "com.thera.thermfw.persist.PersistentObject";
//Fix 42909 fine
  public static final String DOLIST_CLASS_NAME = "DoListClasseName";
  public static final String ACTION_ADAPTER_CLASS_NAME = "ActionAdapterClassName";

	public static final char NOTHING_GENERATED = '0';
	public static final char TABLES_GENERATED  = '1';
	public static final char SOURCES_GENERATED = '2';
	public static final char CLASSES_GENERATED = '3';
	public static final char FWDATA_GENERATED  = '4';
	public static final char WEBFILES_GENERATED= '5';
	public static final char JSP_GENERATED     = '6';
	public static final char MENU_GENERATED    = '7';

//Constant Attribute ExecutionMode
  public static final char AUTOMATIC = 'A';
  public static final char STEP_BY_STEP = 'S';

  public static final int MAX_SQL_STRING_LENGHT = 1024; //Fix 15433
  public static final String FOLDER_LANG_ID = (Security.getCurrentUser() != null) ? Security.getCurrentUser().getLanguage() : SystemParam.getCompanyDefaultLocale();
  
  public static final String DEFAULT_OWNER_ID = ResourceLoader.getString(BaseExtensionGenerator.EXT_RES, "DefaultOwnerId");//23811

  public String FOLDER_USER_ID = null;
  private String iSchemaName = null;
  private String iTableName = null;
  private String iClassName = null;
  private String iNLSName = null;
  private String iPluralNLSName = null;
  private String iSourcePath = null;
  private String iTableSpace = null;
  private String iOldExtendedClass = null;
//Pagina 2 – Classe - Informazioni avanzate
  private String iInheritedClasseName = null;
  private boolean iOptimisticLock = true;
  private AttributeExtensible iTimestampAttribute = null; //13733
  private String iDOList = null;
  protected String iActionAdapter = null;
  private Integer iWidth = null;
  private Integer iHeight = null;
  //28079 inizio
  protected String iBODataCollector;
  protected String iWebFormCustomizer;
	protected String iViewSelector;
	//28079 fine	
	//Pagina 3 - Attributi
  private List iAttributes = new ArrayList();
//Pagina 4 - Componenti di therm
  private boolean iThermNlsComponent = false;
  private String iThermNlsName = null;
  private String iThermNlsPluralName = null;
  private String iThermNlsSchemaName = null;
  private String iThermNlsTableName = null;
  private List iThermNlsAttributes = new ArrayList();

  private boolean iThermWorkflow = false;
  private String iThermWorkflowName = null;
  private boolean iThermWorkflowExt = false;
// 13733 ini
  public static String DEFAULT_WF_ID_COL_NAME = "WF_ID";
  public static String DEFAULT_WF_NODE_ID_COL_NAME = "WF_NODE_ID";
  public static String DEFAULT_WF_CLASS_ID_COL_NAME = "WF_CLASS_ID";
//  private List iThermWorkflowAttributes = new ArrayList();
  private String iThermNlsNlsName = null;
  private String iThermNlsTitleName = null;
  private String iThermNlsLabel = null;

  private String iThermWorkflowNlsName = null;
  private String iThermWorkflowNlsColumn = null;
  private String iThermWorkflowLabel = null;
  private String iWF_IDColumnName = DEFAULT_WF_ID_COL_NAME;
  private String iWF_NODE_IDColumnName = DEFAULT_WF_NODE_ID_COL_NAME;
  private String iWF_CLASS_IDColumnName = DEFAULT_WF_CLASS_ID_COL_NAME;
  private String iWF_SUB_NODE_IDColumnName = null;

  private String iThermCommentName = null;
  private String iThermCommentNlsName = null;
  private String iThermCommentNlsColumnName = null;
  private String iThermCommentLabel = null;
// 13733 fine
  private boolean iThermComment = false;
  private String iThermCommentColumnName = null;
// 13733 private List iThermCommentAttributes = new ArrayList();

  private boolean iThermExtension = false;
  private String iThermExtensionName = null;
  private boolean iThermExtensionAttribute = false;
  private String iThermExtensionAttributeName = null;
  private List iThermExtensionAttributes = new ArrayList();
//Pagina 5 - Componente
  private List iComponents = new ArrayList(); //13733
//Pagina 6 - Relazioni Fix 13585
  private List iRelations = new ArrayList();
//Pagina 7 - Collezioni Fix 13735
  private List iOneToManyList = new ArrayList();
  private List iManyToManyList = new ArrayList();

  boolean iChild = false;
  String iCollectionName;
  String iChildNlsName;
  String iChildNlsColumnName;
  String iChildLabel;

//Pagina 8 - chiave
  private List iKeyColumns = new ArrayList();
  //Pagina 9 - Layout a video
  private List iLayoutTabbeds = new ArrayList(); //13355
//Pagina 9 - Ordinamento
  private List iOrderColumns = new ArrayList();
//pagina 11 - Struttura applicazione e munù utente
  protected Proxy iFolder = new Proxy(FolderNls.class);
  protected Proxy iUserFolder = new Proxy(UserMenuFolder.class);
//Pagina 11 - Generazione
  private char iExecutionMode = AUTOMATIC;
//  private String iElaboratedSteps="1*"; //13548
  private String iElaboratedSteps = "01*";
  protected char iLastPhaseGenerated = NOTHING_GENERATED;

  protected boolean iLayoutTabbedsCompleted = false; //14088
  protected ExtensibleEntity iOldExtension = null; // 14803
  
//Fix 17793 inizio
	public static final char NESSUNO_NUMERATOR  = 'N';
	public static final char THERM_NUMERATOR    = 'T';
	public static final char PROGRESS_NUMERATOR = 'P';

	protected String iAttrNumerator = null;
	protected char iNumertatorType = NESSUNO_NUMERATOR;
	protected char iVisualNumType = NESSUNO_NUMERATOR;
 protected String iOldInheritedClasseName = null;
	
	//23811 inizio
	protected String iDefaultInheritedClassName;
	protected String iDefaultDOList;
	protected String iDefaultActionAdapter;
	public static final char MODALITA_EXT_DELIVERY 	= 'D';
	public static final char MODALITA_EXT_STANDARD  = 'S';
	public static final char MODALITA_EXT_PERS 			= 'P';
	protected char iModalitaExt = MODALITA_EXT_DELIVERY;
	protected String iAreaExt = null;
	protected String iIconName = null;
	protected Integer iFixNumber = null;
	protected String iFixDescription = null;
	protected boolean createTablesRequired = true;
	protected boolean enableNlsTab = true;
	protected boolean replacementDisabled = true;
	
	protected static String lastFixSql = "SELECT MIN(" + SoftwareFixTM.SOFTWARE_FIX + ")" + 
																		" FROM " + SoftwareFixTM.TABLE_NAME + 
																		" WHERE " + SoftwareFixTM.SOFTWARE_FIX + " BETWEEN 90000 AND 99999";
	protected static CachedStatement lastFixStmt = new CachedStatement(lastFixSql);
	//23811 fine
  
	protected boolean exportDictionnary = true;//27963
	protected List tableKeys = new ArrayList();
	protected boolean checkFixNumberEnabled = false;//28381
	
    protected String entityId = null;//32823
    
  //Fix 45830 inizio
    protected String iFormActionAdapter = null;
    public static final String FORM_ACTION_ADAPTER_CLASS_NAME = "FormActionAdapterClassName";
	public static final String WF_FORM_ACTION_ADAPTER_CLASS_NAME = "WfFormActionAdapterClassName";
	protected String iDefaultFormActionAdapter;
 
	//Fix 45830 fine
	 
  public ExtensibleEntity() {
    setId(new Integer( -1));
    setFolderLanguage(FOLDER_LANG_ID);
    setMenuFolderUserId(FOLDER_USER_ID);
    setFrameworkDataOwnerId(ExtensibleEntity.DEFAULT_OWNER_ID);//23811
  }

  //23811 inizio
	public String getDefaultInheritedClassName() {
		return iDefaultInheritedClassName;
	}

	public void setDefaultInheritedClassName(String defaultInheritedClassName) {
		iDefaultInheritedClassName = defaultInheritedClassName;
		setDirty();
	}

	public String getDefaultDOList() {
		return iDefaultDOList;
	}

	public void setDefaultDOList(String defaultDOList) {
		iDefaultDOList = defaultDOList;
		setDirty();
	}

	public String getDefaultActionAdapter() {
		return iDefaultActionAdapter;
	}

	public void setDefaultActionAdapter(String defaultActionAdapter) {
		iDefaultActionAdapter = defaultActionAdapter;
		setDirty();
	}
	
  /**
  *
  * @param type DynamicEnumType
  */
	public void fillDefaultInheritedClassName(DynamicEnumType type) {
		type.addValue("com.thera.thermfw.persist.PersistentObject", "-");
		List listDefaultInheritedClassName = ExtensionDefaultClassName.getInstance().getDefaultInheritedClassName();
		for (int i = 0; i < listDefaultInheritedClassName.size(); i++) {
			ExtensionDefaultInheritedClassName object = (ExtensionDefaultInheritedClassName)listDefaultInheritedClassName.get(i);
			type.addValue(object.getClassName(), object.getDescription());
		}
	}
	
  /**
  *
  * @param type DynamicEnumType
  */
	public void fillDefaultDOList(DynamicEnumType type) {
		List listDefaultDOList = ExtensionDefaultClassName.getInstance().getDefaultDOList();
		for (int i = 0; i < listDefaultDOList.size(); i++) {
			ExtensionDefaultDOList object = (ExtensionDefaultDOList)listDefaultDOList.get(i);
			type.addValue(object.getClassName(), object.getDescription());
		}
	}
	
  /**
  *
  * @param type DynamicEnumType
  */
	public void fillDefaultActionAdapter(DynamicEnumType type) {
		List listDefaultActionAdapter = ExtensionDefaultClassName.getInstance().getDefaultActionAdapter();
		for (int i = 0; i < listDefaultActionAdapter.size(); i++) {
			ExtensionDefaultActionAdapter object = (ExtensionDefaultActionAdapter)listDefaultActionAdapter.get(i);
			type.addValue(object.getClassName(), object.getDescription());
		}
	}
	
	public char getModalitaExt(){
		return iModalitaExt;
	}

	public void setModalitaExt(char modalita){
		iModalitaExt = modalita;
		setDirty();
	}
	
	public String getAreaExt(){
		return iAreaExt;
	}

	public void setAreaExt(String area){
		iAreaExt = area;
		setDirty();
	}

	public String getIconName(){
		return iIconName;
	}

	public void setIconName(String icona){
		iIconName = icona;
		setDirty();
	}
	
	public Integer getFixNumber(){
		return iFixNumber;
	}

	public void setFixNumber(Integer fixNumber){
		iFixNumber = fixNumber;
		setDirty();
	}
	
	public String getFixDescription() {
		return iFixDescription;
	}

	public void setFixDescription(String desc) {
		iFixDescription = desc;
		setDirty();
	}
	
  public boolean isCreateTablesRequired() {
		return createTablesRequired;
	}
  
	public void setCreateTablesRequired(boolean createTablesRequired) {
		this.createTablesRequired = createTablesRequired;
	}
	
	public boolean isEnableNlsTab() {
		return enableNlsTab;
	}

	public void setEnableNlsTab(boolean enableNlsTab) {
		this.enableNlsTab = enableNlsTab;
	}	
	//23811 fine

	public String getAttrNumerator(){
		return iAttrNumerator;
	}

	public void setAttrNumerator(String attrName){
		iAttrNumerator = attrName;
		setDirty();
	}

	public char getNumertatorType(){
		return iNumertatorType;
	}

	public void setNumertatorType(char type){
		iNumertatorType = type;
		setDirty();
	}

	public char getVisualNumType(){
		return iVisualNumType;
	}

	public void setVisualNumType(char type){
		iVisualNumType = type;
	}
// Fix 17793 fine
// Fix 17794 inizio
  protected boolean iControlDeleteTables = true;
  protected ServletEnvironment iServletDelete = null;

  public boolean withControlDeleteTables() {
    return iControlDeleteTables;
  }

  public void setControlDeleteTables(boolean controlDeleteTables) {
    iControlDeleteTables = controlDeleteTables;
  }

  public ServletEnvironment getServletDelete() {
    return iServletDelete;
  }

  public void setServletDelete(ServletEnvironment servletDelete) {
    iServletDelete = servletDelete;
  }
// Fix 17794 fine

  /**
   * checkDelete
   * @return ErrorMessage
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/06/2010    Wizard     Codice generato da Wizard
   * 29/04/2013    MA         Controlli prima eliminazione (17794)
   */
  public ErrorMessage checkDelete() {
    ErrorMessage error = null;
    if (getLastPhaseGenerated() != NOTHING_GENERATED) {
      try {
        if (withControlDeleteTables())
          error = controlTables();
        if (error == null)
          error = controlDescriptor();
      }
      catch (Exception ex) {
        ex.printStackTrace(Trace.excStream);
        return new ErrorMessage("BAS0000078", ex.getMessage());
      }
    }
    return error;
  }

  /**
   * deleteExtensionWorkflow
   * Fix 17794
   * @param extensibleEntity ExtensibleEntity
   * @return int
   * @throws SQLException
   */
  protected static int deleteExtensionWorkflow(ExtensibleEntity extensibleEntity) throws SQLException {
    int result = ErrorCodes.NO_ROWS_UPDATED;
    if (extensibleEntity.hasThWorkflow()) {
      String where = WfClassTM.CLASS_HDR + "='" + extensibleEntity.getClassName() + "'";
      try {
        Vector wfClasses = WfClass.retrieveList(where, "", true);
        for (Iterator iter = wfClasses.iterator(); iter.hasNext(); ) {
          WfClass item = (WfClass) iter.next();
          int intermediate = item.delete();
          if (intermediate < ErrorCodes.NO_ROWS_UPDATED) {
            return intermediate;
          }
          result += intermediate;
        }
      }
      catch (IllegalAccessException ex) {
        ex.printStackTrace(Trace.excStream);
        throw new SQLException(ex.getMessage());
      }
      catch (InstantiationException ex) {
        ex.printStackTrace(Trace.excStream);
        throw new SQLException(ex.getMessage());
      }
      catch (ClassNotFoundException ex) {
        ex.printStackTrace(Trace.excStream);
        throw new SQLException(ex.getMessage());
      }
    }
    return result;
  }

  /**
   * deleteWorkflow
   * Fix 17794
   * @return int
   * @throws SQLException
   */
  protected int deleteWorkflow() throws SQLException {
    int globalResult = ErrorCodes.NO_ROWS_UPDATED;
    int result = deleteExtensionWorkflow(this);
    if (result < ErrorCodes.NO_ROWS_UPDATED)
      return result;
    globalResult += result;
    for (int i = 0; i < getOneToManyList().size(); i++) {
      result = deleteExtensionWorkflow( (ExtensibleEntity) getOneToManyList().get(i));
      if (result < ErrorCodes.NO_ROWS_UPDATED)
        return result;
      globalResult += result;
    }
    return globalResult;
  }

  /**
   * fileTDDMLDropTables
   * Fix 17794
   * @param generator ExtensionWebGenerator
   * @param selectedValues Vector
   * @return boolean
   * @throws FileNotFoundException
   */
  public boolean fileTDDMLDropTables(ExtensionWebGenerator generator, Vector tables) throws FileNotFoundException {
    //StringBuffer tddmlString = new StringBuffer("");
    String outputFileName = generator.getWorkTDDMLPath() + File.separator + "DROP_" + getTableName() + ".TDDML";
    PrintWriter output = new PrintWriter(new FileOutputStream(outputFileName));
    //output.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n<!DOCTYPE TDDML SYSTEM \"../../../TDDML.dtd\">\n<TDDML>");
    output.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
    output.println("<!DOCTYPE TDDML SYSTEM \"../../../TDDML.dtd\">");
    output.println("<TDDML>");
    for (Iterator iter = tables.iterator(); iter.hasNext(); ) {
      //DictTables item = (DictTables) iter.next();
      //tddmlString = tddmlString.append("<DropTable name=\"" + item.getName() + "\" schema=\"" + item.getSchema() + "\"></DropTable>\n");
    	String[] table = (String[])iter.next();
      output.println("<DropTable name=\"" + table[1] + "\" schema=\"" + table[0] + "\">");
      output.println("</DropTable>");
    }
    //output.println(tddmlString.toString());
    output.println("</TDDML>");
    output.flush();
    output.close();
    return true;
  }

  /**
   * deleteTables
   * Fix 17794
   * @return int
   * @throws SQLException
   */
  protected int deleteTables() throws SQLException{
		int result = ErrorCodes.NO_ROWS_UPDATED;
    ExtensionWebGenerator generator = (ExtensionWebGenerator) Factory.createObject(ExtensionWebGenerator.class);
    try {
      generator.init(this, null);
			Vector allTables = populateExtensionTables();
			Vector selectedValues = new Vector();
			for (int i = 0; i < allTables.size(); i++) {
				DictTables element = DictTables.elementWithKey(((String[])allTables.get(i))[1], PersistentObject.NO_LOCK);
				if (element != null)
					selectedValues.add(element);
			}
			fileTDDMLDropTables(generator, allTables);
			String userTherm=ConnectionManager.getCurrentUser();
			String passwordTherm=ConnectionManager.getCurrentPassword();
			String fileName = generator.getWorkTDDMLPath() + File.separator + "DROP_"+ getTableName() + ".TDDML";
			com.thera.thermfw.setup.gui.ApplyFix fixApplyObj = (com.thera.thermfw.setup.gui.ApplyFix)Factory.createObject(com.thera.thermfw.setup.gui.ApplyFix.class);
			fixApplyObj.setParameters(new String[]{userTherm, passwordTherm, BaseExtensionGenerator.getPatformType(), fileName, "true"});
			PrintWriter output = new PrintWriter(new FileOutputStream(generator.getWorkPath() + File.separator + "TDDML_Report.txt", false));
			fixApplyObj.setFixFileReport(output);
			boolean ok = fixApplyObj.run();
			output.flush();
			output.close();
			//28381 inizio
			File file = new File(fileName);
			if(file.exists()) {
				file.delete();
			}
			//28381 fine
			if (ok){
				while (!selectedValues.isEmpty()){
					DictTables element = (DictTables)selectedValues.get(selectedValues.size()-1);
					int del = element.delete();
					if (del < ErrorCodes.NO_ROWS_UPDATED)
						return del;
					result += del;
					selectedValues.remove(element);
				}
			}
    }
    catch (IOException ex) {
      ex.printStackTrace(Trace.excStream);
      throw new SQLException(ex.getMessage());
    }
    
		return result;
	}

  /**
   * deleteMenuItem
   * Fix 17794
   * @return int
   * @throws SQLException
   */
  protected int deleteMenuItem() throws SQLException{
		int globalResult = ErrorCodes.NO_ROWS_UPDATED;
		//String entityName = getClassName().length() > 15 ? getClassName().substring(0,15) : getClassName(); //32823
		String where = UserMenuItemTM.ENTITY_ID + " = '" + getEntityId() + "'";//32823
    try {
      Vector v = UserMenuItem.retrieveList(where, "", false);
      if (v != null && v.size() > 0) {
        int result = ErrorCodes.NO_ROWS_UPDATED;
        for (Iterator iter = v.iterator(); iter.hasNext(); ) {
          UserMenuItem item = (UserMenuItem) iter.next();
          result = item.delete();
          if (result < ErrorCodes.NO_ROWS_UPDATED) {
            return result;
          }
          globalResult += result;
        }
      }
    }
    catch (ClassNotFoundException ex) {
			ex.printStackTrace(Trace.excStream);
			throw new SQLException(ex.getMessage());
    }
    catch (InstantiationException ex) {
			ex.printStackTrace(Trace.excStream);
			throw new SQLException(ex.getMessage());
    }
    catch (IllegalAccessException ex) {
			ex.printStackTrace(Trace.excStream);
			throw new SQLException(ex.getMessage());
    }
		return globalResult;
	}

  /**
   * deleteExtensionEntity
   * Fix 17794
   * @param extensibleEntity ExtensibleEntity
   * @return int
   * @throws SQLException
   */
  protected static int deleteExtensionEntity(ExtensibleEntity extensibleEntity) throws SQLException{
    //32823 inizio
		//Entity entity = Entity.elementWithKey((extensibleEntity.getClassName().length() > 15 ? extensibleEntity.getClassName().substring(0,15) : extensibleEntity.getClassName()),PersistentObject.OPTIMISTIC_LOCK);
    Entity entity = Entity.elementWithKey(extensibleEntity.getEntityId(), PersistentObject.OPTIMISTIC_LOCK);
		//32823 fine
		if (entity != null)
			return entity.delete();
		return ErrorCodes.NO_ROWS_UPDATED;
	}

  /**
   * deleteEntities
   * Fix 17794
   * @return int
   * @throws SQLException
   */
  protected int deleteEntities() throws SQLException{
		int globalResult = ErrorCodes.NO_ROWS_UPDATED;
		int result = ErrorCodes.NO_ROWS_UPDATED;
		for (int i = 0; i < getOneToManyList().size(); i++){
			result = deleteExtensionEntity((ExtensibleEntity)getOneToManyList().get(i));
			if (result < ErrorCodes.NO_ROWS_UPDATED) return result;
			globalResult += result;
		}
		result = deleteExtensionEntity(this);
		if (result < ErrorCodes.NO_ROWS_UPDATED) return result;
		globalResult += result;
		return globalResult;
	}

  /**
   * deleteExtensionDescriptor
   * Fix 17794
   * @param extensibleEntity ExtensibleEntity
   * @return int
   * @throws SQLException
   */
  protected static int deleteExtensionDescriptor(ExtensibleEntity extensibleEntity)throws SQLException{
		int ret = ErrorCodes.NO_ROWS_UPDATED;
  	PersistentClassHdr descrittore = PersistentClassHdr.elementWithKey(extensibleEntity.getClassName(),PersistentObject.OPTIMISTIC_LOCK);
		if (descrittore != null) {
			int rc = descrittore.delete();
			if(rc < ErrorCodes.NO_ROWS_UPDATED) {
				return rc;
			}
			ret += rc;
		}
  	descrittore = PersistentClassHdr.elementWithKey(extensibleEntity.getNLSRowClassName(),PersistentObject.OPTIMISTIC_LOCK);
		if (descrittore != null) {
			int rc = descrittore.delete();
			if(rc < ErrorCodes.NO_ROWS_UPDATED) {
				return rc;
			}
			ret += rc;
		}
		return ret;
	}

  /**
   * deleteDescriptors
   * Fix 17794
   * @return int
   * @throws SQLException
   */
  protected int deleteDescriptors() throws SQLException{
		int globalResult = ErrorCodes.NO_ROWS_UPDATED;
		int result = deleteExtensionDescriptor(this);
		if (result < ErrorCodes.NO_ROWS_UPDATED) return result;
		globalResult += result;
		for (int i = 0; i < getOneToManyList().size(); i++){
			result = deleteExtensionDescriptor((ExtensibleEntity)getOneToManyList().get(i));
			if (result < ErrorCodes.NO_ROWS_UPDATED) return result;
			globalResult += result;
		}
		if(getExtensionType() == ExtensibleEntity.EXTENDED) {//32879
		  deleteAllReplacement();//28079
		}
		ClassADCollectionManager.clearHashtable();//23811
		return globalResult;
	}

  /**
   * tryToDeleteWebfiles
   * Fix 17794
   * @param extension String
   * @param extensibleEntity ExtensibleEntity
   * @param generator ExtensionWebGenerator
   * @return boolean
   * @throws SQLException
   */
  protected static boolean tryToDeleteWebfiles(String extension, ExtensibleEntity extensibleEntity, ExtensionWebGenerator generator) throws SQLException{
		String completeSourceName = generator.getWorkWebsrcPath() + BaseExtensionGenerator.fileSeparator + BaseExtensionGenerator.correctPath(extensibleEntity.getSourcePath()) + BaseExtensionGenerator.fileSeparator + extensibleEntity.getClassName() + extension;
		String completeDestinName = generator.getRealWebsrcPath() + BaseExtensionGenerator.fileSeparator + BaseExtensionGenerator.correctPath(extensibleEntity.getSourcePath()) + BaseExtensionGenerator.fileSeparator + extensibleEntity.getClassName() + extension;
		File processedFile = new File(completeSourceName);
		if (processedFile.exists())
			if (!	processedFile.delete())
				return false;
		processedFile = new File(completeDestinName);
		if (processedFile.exists())
			if (!processedFile.delete())
				return false;
		return true;
	}

  /**
   * deleteExtensionWebPages
   * Fix 17794
   * @param extensibleEntity ExtensibleEntity
   * @param generator ExtensionWebGenerator
   * @return int
   * @throws SQLException
   */
  protected static int deleteExtensionWebPages(ExtensibleEntity extensibleEntity, ExtensionWebGenerator generator)throws SQLException{
		tryToDeleteWebfiles(ExtensionWebGenerator.HTML_EXT,extensibleEntity,generator);
		tryToDeleteWebfiles(ExtensionWebGenerator.TFML_EXT,extensibleEntity,generator);
		tryToDeleteWebfiles(ExtensionWebGenerator.JSP_EXT,extensibleEntity,generator);
		String[] allLibPath = generator.getRealLibPath();
		String completeSourceName = generator.getWorkSourcePath() + BaseExtensionGenerator.fileSeparator + BaseExtensionGenerator.correctPath(extensibleEntity.getSourcePath()) + BaseExtensionGenerator.fileSeparator + ExtensionWebGenerator.RES_PACKAGE + BaseExtensionGenerator.fileSeparator + extensibleEntity.getClassName() + ExtensionWebGenerator.RESOURCES_EXT;
		File processedFile = new File(completeSourceName);
		if (processedFile.exists())
			processedFile.delete();
		for (int i = 0; i < allLibPath.length; i++){
			String completeDestinName = allLibPath[i] + BaseExtensionGenerator.fileSeparator + BaseExtensionGenerator.correctPath(extensibleEntity.getSourcePath()) + BaseExtensionGenerator.fileSeparator + ExtensionWebGenerator.RES_PACKAGE;
			completeDestinName += BaseExtensionGenerator.fileSeparator + extensibleEntity.getClassName() + ExtensionWebGenerator.RESOURCES_EXT;
			processedFile = new File(completeDestinName);
			if (processedFile.exists())
				processedFile.delete();
		}
		return ErrorCodes.NO_ROWS_UPDATED;
  }

  /**
   * deleteWebPages
   * Fix 17794
   * @return int
   * @throws SQLException
   */
  protected int deleteWebPages() throws SQLException{
		int globalResult = ErrorCodes.NO_ROWS_UPDATED;
		if (getServletDelete() != null){
			ExtensionWebGenerator generator = (ExtensionWebGenerator)Factory.createObject(ExtensionWebGenerator.class);
			try {
				generator.init(this, getServletDelete());
				int result = deleteExtensionWebPages(this, generator);
				if (result < ErrorCodes.NO_ROWS_UPDATED) return result;
				globalResult += result;
				for (int i = 0; i < getOneToManyList().size(); i++){
					result = deleteExtensionWebPages((ExtensibleEntity)getOneToManyList().get(i), generator);
					if (result < ErrorCodes.NO_ROWS_UPDATED) return result;
					globalResult += result;
				}
			}
			catch (IOException ex) {
				ex.printStackTrace();
				throw new SQLException(ex.getMessage());
			}
		}
		return globalResult;
	}

  /**
   * deleteExtensionClasses
   * Fix 17794
   * @param generator ExtensionWebGenerator
   * @param extensibleEntity ExtensibleEntity
   * @return int
   * @throws SQLException
   */
  protected static int deleteExtensionClasses(ExtensionWebGenerator generator, ExtensibleEntity extensibleEntity) throws SQLException {
		String javaClass = generator.getWorkLibPath() + BaseExtensionGenerator.fileSeparator + BaseExtensionGenerator.correctPath(extensibleEntity.getSourcePath()) + BaseExtensionGenerator.fileSeparator + extensibleEntity.getClassName() + ".class";
		String poClass = generator.getWorkLibPath() + BaseExtensionGenerator.fileSeparator + BaseExtensionGenerator.correctPath(extensibleEntity.getSourcePath()) + BaseExtensionGenerator.fileSeparator + extensibleEntity.getClassName() + "PO.class";
		String tmClass = generator.getWorkLibPath() + BaseExtensionGenerator.fileSeparator + BaseExtensionGenerator.correctPath(extensibleEntity.getSourcePath()) + BaseExtensionGenerator.fileSeparator + extensibleEntity.getClassName() + "TM.class";

		File processedFile = new File(javaClass);
		if (processedFile.exists())
			processedFile.delete();

		processedFile = new File(poClass);
		if (processedFile.exists())
			processedFile.delete();

		processedFile = new File(tmClass);
		if (processedFile.exists())
			processedFile.delete();

		String[] paths = generator.getRealLibPath();
		for (int i = 0; i < paths.length; i++){
			String newRoot = paths[i] + BaseExtensionGenerator.fileSeparator + BaseExtensionGenerator.correctPath(extensibleEntity.getSourcePath()) + BaseExtensionGenerator.fileSeparator;
			javaClass = newRoot + extensibleEntity.getClassName() + ".class";
			poClass = newRoot + extensibleEntity.getClassName() + "PO.class";
			tmClass = newRoot + extensibleEntity.getClassName() + "TM.class";
			processedFile = new File(javaClass);
      if (processedFile.exists())
        processedFile.delete();

      processedFile = new File(poClass);
      if (processedFile.exists())
        processedFile.delete();

      processedFile = new File(tmClass);
      if (processedFile.exists())
        processedFile.delete();
		}
		return ErrorCodes.NO_ROWS_UPDATED;
	}

  /**
   * deleteClasses
   * Fix 17794
   * @return int
   * @throws SQLException
   */
  protected int deleteClasses() throws SQLException{
		int globalResult = ErrorCodes.NO_ROWS_UPDATED;
		if (getServletDelete() != null){
      ExtensionWebGenerator generator = (ExtensionWebGenerator) Factory.createObject(ExtensionWebGenerator.class);
      try {
        generator.init(this, getServletDelete());
				int result = deleteExtensionClasses(generator, this);
				if (result < ErrorCodes.NO_ROWS_UPDATED) return result;
				globalResult += result;
				for (int i = 0; i < getOneToManyList().size(); i++){
					result = deleteExtensionClasses(generator, (ExtensibleEntity)getOneToManyList().get(i));
					if (result < ErrorCodes.NO_ROWS_UPDATED) return result;
					globalResult += result;
				}
      }
      catch (IOException ex) {
				ex.printStackTrace();
				throw new SQLException(ex.getMessage());
      }
    }
		return globalResult;
	}

  /**
   * deleteExtensionSources
   * Fix 17794
   * @param generator ExtensionWebGenerator
   * @param extensibleEntity ExtensibleEntity
   * @return int
   * @throws SQLException
   */
  protected static int deleteExtensionSources(ExtensionWebGenerator generator, ExtensibleEntity extensibleEntity) throws SQLException{
		String javaClass = generator.getWorkSourcePath() + BaseExtensionGenerator.fileSeparator + BaseExtensionGenerator.correctPath(extensibleEntity.getSourcePath()) + BaseExtensionGenerator.fileSeparator + extensibleEntity.getClassName() + ".java";
		String poClass = generator.getWorkSourcePath() + BaseExtensionGenerator.fileSeparator + BaseExtensionGenerator.correctPath(extensibleEntity.getSourcePath()) + BaseExtensionGenerator.fileSeparator + extensibleEntity.getClassName() + "PO.java";
		String tmClass = generator.getWorkSourcePath() + BaseExtensionGenerator.fileSeparator + BaseExtensionGenerator.correctPath(extensibleEntity.getSourcePath()) + BaseExtensionGenerator.fileSeparator + extensibleEntity.getClassName() + "TM.java";

		File processedFile = new File(javaClass);
		if (processedFile.exists())
			processedFile.delete();

		processedFile = new File(poClass);
		if (processedFile.exists())
			processedFile.delete();

		processedFile = new File(tmClass);
		if (processedFile.exists())
			processedFile.delete();

	  String fName = generator.getWorkSourcePath() + BaseExtensionGenerator.fileSeparator + BaseExtensionGenerator.correctPath(extensibleEntity.getSourcePath()) + BaseExtensionGenerator.fileSeparator + "backup";
		File file = new File(fName);
		if (file.exists()){
			File[] allBuckupFiles = file.listFiles();
			for (int i = 0; i < allBuckupFiles.length; i++) {
				allBuckupFiles[i].delete();
			}
		}
		return ErrorCodes.NO_ROWS_UPDATED;
  }

  /**
   * deleteSources
   * Fix 17794
   * @return int
   * @throws SQLException
   */
  protected int deleteSources() throws SQLException{
		int globalResult = ErrorCodes.NO_ROWS_UPDATED;
		if (getServletDelete() != null){
			ExtensionWebGenerator generator = (ExtensionWebGenerator) Factory.createObject(ExtensionWebGenerator.class);
			try {
				generator.init(this, getServletDelete());
				int result = deleteExtensionSources(generator, this);
				if (result < ErrorCodes.NO_ROWS_UPDATED) return result;
				globalResult += result;
				for (int i = 0; i < getOneToManyList().size(); i++){
					result = deleteExtensionSources(generator, (ExtensibleEntity)getOneToManyList().get(i));
					if (result < ErrorCodes.NO_ROWS_UPDATED) return result;
					globalResult += result;
				}
			}
			catch (IOException ex) {
				ex.printStackTrace();
				throw new SQLException(ex.getMessage());
			}
		}
		return globalResult;
	}

  /**
   * deleteExtraFiles
   * Fix 17794
   * @return int
   * @throws SQLException
   */
  protected int deleteExtraFiles() throws SQLException {
		deleteTDDML();
		deleteReports();
		deleteWorkFolder();
		return ErrorCodes.NO_ROWS_UPDATED;
	}

  /**
   * deleteWorkFolder
   * Fix 17794
   * @return int
   * @throws SQLException
   */
  protected int deleteWorkFolder() throws SQLException {
		if (getServletDelete() != null){
			ExtensionWebGenerator generator = (ExtensionWebGenerator) Factory.createObject(ExtensionWebGenerator.class);
			try {
				generator.init(this, getServletDelete());
				//String fName = generator.getWorkPath() + generator.fileSeparator + generator.getFixPath() + generator.fileSeparator + generator.formatFixNumber(getId());//23811
				String fName = generator.getWorkPath() + BaseExtensionGenerator.fileSeparator + BaseExtensionGenerator.formatFixNumber(getId());//23811
				File file = new File(fName);
				if (file.exists())
					file.delete();
			}
			catch (IOException ex) {
				ex.printStackTrace();
				throw new SQLException(ex.getMessage());
			}
		}
		return ErrorCodes.NO_ROWS_UPDATED;
	}

  /**
   * deleteTDDML
   * Fix 17794
   * @return int
   * @throws SQLException
   */
  protected int deleteTDDML() throws SQLException{
		if (getServletDelete() != null){
			ExtensionWebGenerator generator = (ExtensionWebGenerator) Factory.createObject(ExtensionWebGenerator.class);
			try {
				generator.init(this, getServletDelete());
				String fName = generator.getWorkTDDMLPath();
				File file = new File(fName);
				if (file.exists()){
          File[] toDelete = file.listFiles();
          for (int i = 0; i < toDelete.length; i++) {
            toDelete[i].delete();
          }
          file.delete();
        }
			}
			catch (IOException ex) {
				ex.printStackTrace();
				throw new SQLException(ex.getMessage());
			}
		}
		return ErrorCodes.NO_ROWS_UPDATED;
	}

  /**
   * deleteReports
   * Fix 17794
   * @return int
   * @throws SQLException
   */
  protected int deleteReports() throws SQLException {
		if (getServletDelete() != null){
			ExtensionWebGenerator generator = (ExtensionWebGenerator) Factory.createObject(ExtensionWebGenerator.class);
			try {
				generator.init(this, getServletDelete());
				String fName = generator.getWorkPath();
				File file = new File(fName);
				if (file.exists()){
					String toDelete =  "Rep" + ((TableBuilder)Factory.createObject(TableBuilder.class)).getGenerationClass() + getClassName() + ".txt";
					File fileToDelete = new File(file, toDelete);
					if (fileToDelete.exists())
						fileToDelete.delete();

					toDelete =  "Rep" + ((WizardBuilder)Factory.createObject(WizardBuilder.class)).getGenerationClass() + getClassName() + ".txt";
					fileToDelete = new File(file, toDelete);
					if (fileToDelete.exists())
						fileToDelete.delete();

					toDelete =  "Rep" + ((ByteCodeGenerator)Factory.createObject(ByteCodeGenerator.class)).getGenerationClass() + getClassName() + ".txt";
					fileToDelete = new File(file, toDelete);
					if (fileToDelete.exists())
						fileToDelete.delete();

					toDelete =  "Rep" + ((FWDataGenerator)Factory.createObject(FWDataGenerator.class)).getGenerationClass() + getClassName() + ".txt";
					fileToDelete = new File(file, toDelete);
					if (fileToDelete.exists())
						fileToDelete.delete();

					toDelete =  "Rep" + generator.getGenerationClass() + getClassName() + ".txt";
					fileToDelete = new File(file, toDelete);
					if (fileToDelete.exists())
						fileToDelete.delete();

					toDelete =  "RepJSP"  + getClassName() + ".txt";
					fileToDelete = new File(file, toDelete);
					if (fileToDelete.exists())
						fileToDelete.delete();

					toDelete =  "Rep" + ((ExtensionUserMenu)Factory.createObject(ExtensionUserMenu.class)).getGenerationClass() + getClassName() + ".txt";
					fileToDelete = new File(file, toDelete);
					if (fileToDelete.exists())
						fileToDelete.delete();
					
					//23811 inizio
					toDelete =  "Rep" + ((ExtensionFixGenerator)Factory.createObject(ExtensionFixGenerator.class)).getGenerationClass() + getClassName() + ".txt";
					fileToDelete = new File(file, toDelete);
					if (fileToDelete.exists())
						fileToDelete.delete();

					//23811 fine
				}
			}
			catch (IOException ex) {
				ex.printStackTrace();
				throw new SQLException(ex.getMessage());
			}
		}
		return ErrorCodes.NO_ROWS_UPDATED;
	}

  /**
   * delete
   * Fix 17794
   * @return int
   * @throws SQLException
   */
  //23811 inizio
  /*
  public int delete() throws SQLException{
		int globalResult = ErrorCodes.NO_ROWS_UPDATED;
		int result = deleteWorkflow();
		if (result < ErrorCodes.NO_ROWS_UPDATED) return result;
		globalResult += result;
		result = deleteMenuItem();
		if (result < ErrorCodes.NO_ROWS_UPDATED) return result;
		globalResult += result;
		result = deleteEntyties();
		if (result < ErrorCodes.NO_ROWS_UPDATED) return result;
		globalResult += result;
		result = deleteDescriptors();
		if (result < ErrorCodes.NO_ROWS_UPDATED) return result;
		globalResult += result;
		result = deleteWebPages();
		if (result < ErrorCodes.NO_ROWS_UPDATED) return result;
		globalResult += result;
		result = deleteClasses();
		if (result < ErrorCodes.NO_ROWS_UPDATED) return result;
		globalResult += result;
		result = deleteSources();
		if (result < ErrorCodes.NO_ROWS_UPDATED) return result;
		globalResult += result;
		result = deleteExtraFiles();
		if (result < ErrorCodes.NO_ROWS_UPDATED) return result;
		globalResult += result;
		if (withControlDeleteTables()){
      result = deleteTables();
			if (result < ErrorCodes.NO_ROWS_UPDATED) return result;
			globalResult += result;
    }
		result = super.delete();
		if (result < ErrorCodes.NO_ROWS_UPDATED) return result;
		globalResult += result;
		return globalResult;
	}
	*/
  
	public int deleteOwnedObjects() throws SQLException	{
		int globalResult = ErrorCodes.NO_ROWS_UPDATED;
		int result = deleteWorkflow();
		if (result < ErrorCodes.NO_ROWS_UPDATED) {
			return result;
		}
		globalResult += result;
		result = deleteMenuItem();
		if (result < ErrorCodes.NO_ROWS_UPDATED) {
			return result;
		}
		globalResult += result;
		result = deleteEntities();
		if (result < ErrorCodes.NO_ROWS_UPDATED) {
			return result;
		}
		globalResult += result;
		result = deleteDescriptors();
		if (result < ErrorCodes.NO_ROWS_UPDATED) {
			return result;
		}
		globalResult += result;
		result = deleteWebPages();
		if (result < ErrorCodes.NO_ROWS_UPDATED) {
			return result;
		}
		globalResult += result;
		result = deleteClasses();
		if (result < ErrorCodes.NO_ROWS_UPDATED) {
			return result;
		}
		globalResult += result;
		result = deleteSources();
		if (result < ErrorCodes.NO_ROWS_UPDATED) {
			return result;
		}
		globalResult += result;
		result = deleteExtraFiles();
		if (result < ErrorCodes.NO_ROWS_UPDATED) {
			return result;
		}
		globalResult += result;
		if (withControlDeleteTables()){
      result = deleteTables();
			if (result < ErrorCodes.NO_ROWS_UPDATED) {
				return result;
			}
			globalResult += result;
    }
		
		return globalResult;
	}
	//23811 fine
  /**
   * controlDescriptor
   * Fix 17794
   * @return ErrorMessage
   * @throws SQLException
   * @throws ClassNotFoundException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  protected ErrorMessage controlDescriptor() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Vector allDescriptors = populateExtensionDescriptors();
		Vector errors = new Vector();
		int position = 0;
		while (errors.isEmpty() && position < allDescriptors.size()){
			errors.addAll(verifyDescriptorDelete((String)allDescriptors.get(position),allDescriptors));
			position++;
		}
		position--;
		if (!errors.isEmpty()){
			StringBuffer params = new StringBuffer((String)errors.get(0));
			for(int j = 1; j < errors.size(); j++){
				params.append("\n" + errors.get(j));
			}
			return new ErrorMessage("EXTN000032",new String[]{(String)allDescriptors.get(position), params.toString()});
		}
		return null;
	}

  /**
   * populateExtensionDescriptors
   * Fix 17794
   * @return Vector
   */
  protected Vector populateExtensionDescriptors(){
		Vector result = new Vector();
		result.add(getClassName());
		for (Iterator iter = getOneToManyList().iterator(); iter.hasNext(); ){
      result.add( ( (ExtensibleEntity) iter.next()).getClassName());
    }
		return result;
	}

  /**
   * verifyDescriptorDelete
   * Fix 17794
   * @param descriptorName String
   * @param populatedDescriptor Vector
   * @return Vector
   * @throws IllegalAccessException
   * @throws InstantiationException
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  protected Vector verifyDescriptorDelete(String descriptorName, Vector populatedDescriptor) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {
		Set result = new HashSet();

		String where = PersistentClassRDTM.CORR_CLASS_NAME + " = '" + descriptorName + "'";
		Vector allDescriptors = PersistentClassRD.retrieveList(where,"",false);
		for (Iterator iter = allDescriptors.iterator(); iter.hasNext(); ) {
			PersistentClassRD item = (PersistentClassRD) iter.next();
			String element = item.getClassName();
			if (!populatedDescriptor.contains(element))
				result.add(element);
		}

		where = PersistentClassCDTM.CORR_CLASS_NAME + " = '" + descriptorName + "'";
		allDescriptors = PersistentClassCD.retrieveList(where,"",false);
		for (Iterator iter = allDescriptors.iterator(); iter.hasNext(); ) {
			PersistentClassCD item = (PersistentClassCD) iter.next();
			String element = item.getClassName();
			if (!populatedDescriptor.contains(element))
				result.add(element);
		}
		return new Vector(result);
	}

  /**
   * controlTables
   * Fix 17794
   * @return ErrorMessage
   */
  protected ErrorMessage controlTables() throws SQLException {
		Vector allTables = populateExtensionTables();
		Vector errors = new Vector();
		String fatherTableCompleteName = "";
		if (getExtensionType() != INDIPENDENTE){
      try {
        ClassADCollection fatherDescriptor = ClassADCollectionManager.collectionWithName(getExtensionClassName());
        AbstractTableManager tm = ( (PersistentObject) Factory.createObject(fatherDescriptor.getBOClassName())).getAbstractTableManager();
        fatherTableCompleteName = tm.getMainTableName();
        StringTokenizer st = new StringTokenizer(fatherTableCompleteName, ".");
        allTables.add(new String[] {st.nextToken(), st.nextToken()});
      }
      catch (NoSuchFieldException ex) {
				ex.printStackTrace();
				throw new SQLException(ex.getMessage());
      }
      catch (NoSuchElementException ex) {
				ex.printStackTrace();
				throw new SQLException(ex.getMessage());
      }
		}

		DatabaseMetaData dbmd = ConnectionManager.getCurrentConnection().getMetaData();
		int position = 0;
		while (errors.isEmpty() && position < allTables.size()){
			String[] tabbleDetail = (String[])allTables.get(position);
			if ((getExtensionType() == INDIPENDENTE) || (!(tabbleDetail[0] + "." + tabbleDetail[1]).equals(fatherTableCompleteName)))
				errors.addAll(verifyTablesDelete(dbmd,tabbleDetail[0],tabbleDetail[1],allTables));
			position++;
		}

		position--;
		if (!errors.isEmpty()){
			StringBuffer params = new StringBuffer(((String[])errors.get(0))[0] + "." + ((String[])errors.get(0))[1]);
			for(int j = 1; j < errors.size(); j++){
				params.append("\n" + ((String[])errors.get(j))[0] + "." + ((String[])errors.get(j))[1]);
			}
			String[] tabbleDetail = (String[])allTables.get(position);
			return new ErrorMessage("EXTN000031",new String[]{tabbleDetail[0] + "." + tabbleDetail[1], params.toString()});
		}
		return null;
  }

	/**
	 * verifyTablesDelete
	 * Fix 17794
	 * @param tableName String
	 * @param schemaName String
	 * @param extensionTables Vector
	 * @param dbmd DatabaseMetaData
	 * @return Vector
	 */
	protected static Vector verifyTablesDelete(DatabaseMetaData dbmd,String schemaName, String tableName,Vector extensionTables) throws SQLException {
		Vector processedTables = new Vector();
		ResultSet rsForeignKeys = dbmd.getExportedKeys(null,schemaName,tableName);
		while (rsForeignKeys.next()){
			String[] foreignKey = new String[]{rsForeignKeys.getString("FKTABLE_SCHEM"), rsForeignKeys.getString("FKTABLE_NAME")};
			if (!isExtensionTables(foreignKey, processedTables) && !isExtensionTables(foreignKey, extensionTables))
				processedTables.add(foreignKey);
		}
		rsForeignKeys.close();

		return processedTables;
	}

  /**
   * isExtensionTables
   * Fix 17794
   * @param foreignKey String[]
   * @param extensionTables Vector
   * @return boolean
   */
  protected static boolean isExtensionTables(String[] foreignKey, Vector extensionTables) {
		String schemaFKey = foreignKey[0];
		String tableFKey = foreignKey[1];
		for (int i = 0; i < extensionTables.size(); i++) {
			String[] element = (String[])extensionTables.get(i);
			String schemaElement = element[0];
			String tableElement = element[1];
			if (schemaElement.equals(schemaFKey) && tableElement.equals(tableFKey))
				return true;
		}
    return false;
  }

  /**
   * populateExtensionTables
   * Fix 17794
   * @return Vector
   */
  protected Vector populateExtensionTables() {
    Vector tuttiTabella = new Vector();
		tuttiTabella.add(new String[]{getSchemaName(), getTableName()});
    if (hasThNlsComponent()){
      tuttiTabella.add(new String[]{getThNlsSchemaName(),getThNlsTableName()});
    }
    for (Iterator iter = getManyToManyList().iterator(); iter.hasNext(); ) {
      CollectionExtensionEntity many2manyCollection = (CollectionExtensionEntity)iter.next();
      tuttiTabella.add(new String[]{getSchemaName(), many2manyCollection.getTableName()});
    }
    for (Iterator iter = getOneToManyList().iterator(); iter.hasNext(); ){
      ExtensibleEntity child = (ExtensibleEntity)iter.next();
      tuttiTabella.add(new String[]{child.getSchemaName(),child.getTableName()});
      if (child.hasThNlsComponent()){
        tuttiTabella.add(new String[]{child.getThNlsSchemaName(), child.getThNlsTableName()});
      }
    }
		return tuttiTabella;
  }

  public String getActionAdapter() {
    return iActionAdapter;
  }

  public Integer getHeight() {
    return iHeight;
  }

  public String getDOList() {
    return iDOList;
  }

  public Integer getWidth() {
    return iWidth;
  }

  public boolean isOptimisticLock() {
    return iOptimisticLock;
  }

  public String getClassName() {
    return iClassName;
  }

  public String getInheritedClasseName() {
    return iInheritedClasseName;
  }

  public String getNLSName() {
    return iNLSName;
  }

  public String getPluralNLSName() {
    return iPluralNLSName;
  }

  public String getSchemaName() {
    return iSchemaName;
  }

  public String getTableName() {
    return iTableName;
  }

  public void setActionAdapter(String actionAdapter) {
    this.iActionAdapter = actionAdapter;
  }

  public void setHeight(Integer height) {
    this.iHeight = height;
  }


  public void setDOList(String dOList) {
    this.iDOList = dOList;
  }

  public void setWidth(Integer width) {
    this.iWidth = width;
  }

  public void setOptimisticLock(boolean optimistecLock) {
    this.iOptimisticLock = optimistecLock;
  }

  public void setClassName(String className) {
    this.iClassName = className;
  }

  public void setInheritedClasseName(String inheritedClasseName) {
    this.iInheritedClasseName = inheritedClasseName;
  }

  public void setNLSName(String nLSName) {
    this.iNLSName = nLSName;
  }

  public void setPluralNLSName(String pluralNameNLS) {
    this.iPluralNLSName = pluralNameNLS;
  }

  public void setSchemaName(String schemaTable) {
    this.iSchemaName = schemaTable;
  }

  public void setTableName(String TableName) {
    this.iTableName = TableName;
  }

  public List getAttributes()
  {
    return iAttributes;
  }

  public boolean hasThNlsComponent()
  {
    return iThermNlsComponent;
  }

  public void setThNlsComponent(boolean has)
  {
    iThermNlsComponent = has;
  }

  public String getThNlsName()
  {
    return iThermNlsName;
  }

  public void setThNlsName(String name)
  {
    iThermNlsName = name;
  }

  public String getThNlsPlural()
  {
    return iThermNlsPluralName;
  }

  public void setThNlsPlural(String name)
  {
    iThermNlsPluralName = name;
  }

  public String getThNlsSchemaName()
  {
    return iThermNlsSchemaName;
  }

  public void setThNlsSchemaName(String name)
  {
    iThermNlsSchemaName = name;
  }

  public String getThNlsTableName()
  {
    return iThermNlsTableName;
  }

  public void setThNlsTableName(String name)
  {
    iThermNlsTableName = name;
  }

  public List getThermNlsAttributes()
  {
    return iThermNlsAttributes;
  }

  public boolean hasThWorkflow()
  {
    return iThermWorkflow;
  }

  public void setThWorkflow(boolean withWorkflow)
  {
    iThermWorkflow = withWorkflow;
  }

  public String getThWorkflowName()
  {
    return iThermWorkflowName;
  }

  public void setThWorkflowName(String name)
  {
    iThermWorkflowName = name;
  }

  public boolean isThWorkflowExtended()
  {
    return iThermWorkflowExt;
  }

  public void setThWorkflowExtended(boolean extended)
  {
    iThermWorkflowExt = extended;
  }
/* 13733 ini
  public List getThermWorkflowAttributes()
  {
    return iThermWorkflowAttributes;
  }
*/
  public String getThermWorkflowNlsName()
  {
    return iThermWorkflowNlsName;
  }

  public String getThermWorkflowNlsColumn()
  {
    return iThermWorkflowNlsColumn;
  }

  public String getThermWorkflowLabel()
  {
    return iThermWorkflowLabel;
  }

  public String getWF_IDColumnName()
  {
    return iWF_IDColumnName;
  }

  public String getWF_NODE_IDColumnName()
  {
    return iWF_NODE_IDColumnName;
  }

  public String getWF_CLASS_IDColumnName()
  {
    return iWF_CLASS_IDColumnName;
  }

  public String getWF_SUB_NODE_IDColumnName()
  {
    return iWF_SUB_NODE_IDColumnName;
  }

  public void setThermWorkflowNlsName(String name)
  {
    iThermWorkflowNlsName = name;
  }

  public void setThermWorkflowNlsColumn(String nlsCol)
  {
    iThermWorkflowNlsColumn = nlsCol;
  }

  public void setThermWorkflowLabel(String label)
  {
    iThermWorkflowLabel = label;
  }

  public void setWF_IDColumnName(String name)
  {
    iWF_IDColumnName = name;
  }

  public void setWF_NODE_IDColumnName(String name)
  {
    iWF_NODE_IDColumnName = name;
  }

  public void setWF_CLASS_IDColumnName(String name)
  {
    iWF_CLASS_IDColumnName = name;
  }

  public void setWF_SUB_NODE_IDColumnName(String name)
  {
    iWF_SUB_NODE_IDColumnName = name;
  }

  public String getThermCommentNlsName()
  {
    return iThermCommentNlsName;
  }

  public String getThermCommentName()
  {
    return iThermCommentName;
  }

  public String getThermCommentNlsColName()
  {
    return iThermCommentNlsColumnName;
  }

  public String getThermCommentLabel()
  {
    return iThermCommentLabel;
  }

  public void setThermCommentNlsName(String name)
  {
    iThermCommentNlsName = name;
  }

  public void setThermCommentName(String name)
  {
    iThermCommentName = name;
  }

  public void setThermCommentNlsColName(String name)
  {
    iThermCommentNlsColumnName = name;
  }

  public void setThermCommentLabel(String label)
  {
    iThermCommentLabel = label;
  }

  public String getThermNlsNlsName()
  {
    return iThermNlsNlsName;
  }

  public String getThermNlsTitleName()
  {
    return iThermNlsTitleName;
  }

  public String getThermNlsLabel()
  {
    return iThermNlsLabel;
  }

  public void setThermNlsNlsName(String name)
  {
    iThermNlsNlsName = name;
  }

  public void setThermNlsTitleName(String name)
  {
    iThermNlsTitleName = name;
  }

  public void setThermNlsLabel(String label)
  {
    iThermNlsLabel = label;
  }

  public List getNlsAttributesDisponible()
  {
    return new ArrayList(getAttributes());
  }
//13733 fine
  public boolean hasThComment()
  {
    return iThermComment;
  }

  public void setThComment(boolean withComment)
  {
    iThermComment = withComment;
  }

  public String getThCommentColumnName()
  {
    return iThermCommentColumnName;
  }

  public void setThCommentColumnName(String name)
  {
    iThermCommentColumnName = name;
  }

/*13733 public List getThermCommentAttributes()
  {
    return iThermCommentAttributes;
  }
*/
  public boolean hasThExtension()
  {
    return iThermExtension;
  }

  public void setThExtension(boolean withExtension)
  {
    iThermExtension = withExtension;
  }

  public String getThExtensionName()
  {
    return iThermExtensionName;
  }

  public void setThExtensionName(String name)
  {
    iThermExtensionName = name;
  }

  public boolean isThExtensionAttribute()
  {
    return iThermExtensionAttribute;
  }

  public void setThExtensionAttribute(boolean extension)
  {
    iThermExtensionAttribute = extension;
  }

  public String getThExtensionAttributeName()
  {
    return iThermExtensionAttributeName;
  }

  public void setThExtensionAttributeName(String name)
  {
    iThermExtensionAttributeName = name;
  }

  public List getThermExtensionAttributes()
  {
    return iThermExtensionAttributes;
  }

  public List getKeyColumns()
  {
/*    iKeyColumns = new ArrayList();
    for (Iterator iter = iAttributes.iterator(); iter.hasNext(); ) {
      AttributeExtensible item = (AttributeExtensible) iter.next();
      if (item.getKeyPosition() >= 0 || item.getInheritenceType() == Inheritable.INHERITED)
        iKeyColumns.add(item);
    }*/
    return iKeyColumns;
  }

  public List getOrderColumns()
  {
/*    iOrderColumns = new ArrayList(iAttributes);
    Collections.sort(iOrderColumns, new Comparator()
    {
      public int compare(Object o1, Object o2)
      {
        return ((AttributeExtensible)o1).getColumnPosition() - ((AttributeExtensible)o2).getColumnPosition();
      }
      public boolean equals(Object obj)
      {
        return compare(this,obj) == 0;
      }
    });*/
    return iOrderColumns;
  }
  public List getKeyColumnsDisponibili()
  {
    ArrayList result = new ArrayList();
    for (Iterator iter = iAttributes.iterator(); iter.hasNext(); ) {
      AttributeExtensible item = (AttributeExtensible) iter.next();
      if (item.isMandatory() || item.getInheritenceType() == Inheritable.INHERITED){
        ExtensionKeyAttribute element = (ExtensionKeyAttribute)Factory.createObject(ExtensionKeyAttribute.class);
        element.setExtensibleEntityId(getId());
        element.setAttributeName(item.getAttributeName());
        element.setPosition(0);
        result.add(element);
      }
    }
    return result;
  }

  // 13585 ini
    public List getRelations()
    {
      return iRelations;
    }

  public boolean isRelation(ExtensionEntityLayoutAttr node)
  {
    for (Iterator iter = getRelations().iterator(); iter.hasNext(); ) {
      if (((ExtensionEntityRelation) iter.next()).getRelationName().equals(node.getAttributeReferenceName()))
        return true;
    }
    return false;
  }
//14258 ini
	public boolean isOneToMany(ExtensionEntityLayoutAttr node){
		for (Iterator iter = getOneToManyList().iterator(); iter.hasNext(); ) {
			if (((ExtensibleEntity) iter.next()).getCollectionName().equals(node.getAttributeReferenceName()))
				return true;
		}
		return false;
	}

	public ExtensibleEntity getChild(String name)	{
		for (Iterator iter = getOneToManyList().iterator(); iter.hasNext(); ) {
			ExtensibleEntity item = (ExtensibleEntity) iter.next();
			if (item.getCollectionName().equals(name))
				return item;
		}
		return null;
	}

	public boolean isManyToMany(ExtensionEntityLayoutAttr node){
		for (Iterator iter = getManyToManyList().iterator(); iter.hasNext(); ) {
			if (((CollectionExtensionEntity) iter.next()).getCollectionName().equals(node.getAttributeReferenceName()))
				return true;
		}
		return false;
	}

	public CollectionExtensionEntity getCollection(String name){
		for (Iterator iter = getManyToManyList().iterator(); iter.hasNext(); ) {
			CollectionExtensionEntity item = (CollectionExtensionEntity) iter.next();
			if (item.getCollectionName().equals(name))
				return item;
		}
		return null;
	}
//14258 fine
// 13585 fine
  // Fix 13733 ini
      public List getComponents()
      {
        return iComponents;
      }

      public boolean isComponent(ExtensionEntityLayoutAttr node)
      {
        for (Iterator iter = getComponents().iterator(); iter.hasNext(); ) {
          if (((ExtensionEntityComponent) iter.next()).getComponentName().equals(node.getAttributeReferenceName()))
            return true;
        }
        return false;
      }
// Fix 13733 fine
// Fix 13735 ini
   public List getOneToManyList()
   {
     return iOneToManyList;
   }

   public List getManyToManyList()
   {
     return iManyToManyList;
   }

   public String getCollectionName() {
     return iCollectionName;
   }

   public String getChildLabel() {
     return iChildLabel;
   }

   public String getChildNlsColumnName() {
     return iChildNlsColumnName;
   }

   public String getChildNlsName() {
     return iChildNlsName;
   }

   public void setCollectionName(String collectionName) {
     this.iCollectionName = collectionName;
   }

   public void setChildLabel(String label) {
     this.iChildLabel = label;
   }

   public void setChildNlsColumnName(String nlsColumnName) {
     this.iChildNlsColumnName = nlsColumnName;
   }

   public void setChildNlsName(String nlsName) {
     this.iChildNlsName = nlsName;
   }

   public boolean isChild()
   {
     return iChild;
   }

   public void setChild(boolean c)
   {
     iChild = c;
   }
// Fix 13735 fine
  public char getExecutionMode()
  {
    return iExecutionMode;
  }

  public void setExecutionMode(char type)
  {
    iExecutionMode = type;
  }

  public String getSourcePath()
  {
    return iSourcePath;
  }

  public void setSourcePath(String path)
  {
    iSourcePath = path;
  }

  public String getTableSpace()
  {
    return iTableSpace;
  }

  public void setTableSpace(String tableSpaceName)
  {
    iTableSpace = tableSpaceName;
  }

  public char getLastPhaseGenerated()
  {
    return iLastPhaseGenerated;
  }

  public void setLastPhaseGenerated(char phase)
  {
    iLastPhaseGenerated = phase;
  }

  public String getOldExtensionClassName()
  {
    return iOldExtendedClass;
  }

  public void setOldExtensionClassName(String newName)
  {
    iOldExtendedClass = newName;
  }
  public String getElaboratedSteps()
  {
   return iElaboratedSteps;
  }
  public void setElaboratedSteps(String elaboratedSteps)
  {
    iElaboratedSteps = elaboratedSteps;
  }
  
  //23811 inizio
	public String getOldInheritedClasseName() {
		return iOldInheritedClasseName;
	}

	public void setOldInheritedClasseName(String oldInheritedClasseName) {
		this.iOldInheritedClasseName = oldInheritedClasseName;
	}
	
	public boolean isInheritedClasseNameChanged(){
		return !Utils.areEqual(iOldInheritedClasseName, iInheritedClasseName);
	}
  //23811 fine

  public ErrorMessage checkExistenceTable()
  {
    ErrorMessage error = null;
//    if (iLastPhaseGenerated > NOTHING_GENERATED) 13548
/*    if (iLastPhaseGenerated == NOTHING_GENERATED)
    {
      Database db = ConnectionManager.getCurrentDatabase();
      try {
        DatabaseMetaData md = db.getDatabaseMetaData(ConnectionManager.getCurrentConnection());
        ResultSet rs = md.getTables(null, getSchemaName(), getTableName(), new String[] {"TABLE"});
        if (rs.next())
        //  return new ErrorMessage("EXTN000001");
          error = new ErrorMessage("EXTN000001");
        rs.close();
      } catch (SQLException ex) {}
    }*/
//14803 ini
    String oldCompleteTableName = iOldExtension == null ? null : (iOldExtension.getSchemaName() + "." + iOldExtension.getTableName());
		String actualCompleteTableName = getSchemaName() + "." + getTableName();
		if (!isOnDB() || (isOnDB() && ((oldCompleteTableName == null && actualCompleteTableName!= null) || !oldCompleteTableName.equals(actualCompleteTableName)))){
			try {
				Database db = ConnectionManager.getCurrentDatabase();
				DatabaseMetaData md = db.getDatabaseMetaData(ConnectionManager.getCurrentConnection());
				ResultSet rs = md.getTables(null, getSchemaName(), getTableName(), new String[] {"TABLE"});
				if (rs.next())
					error = new ErrorMessage("EXTN000001");
				rs.close();
			} catch (Exception ex) {
				ex.printStackTrace(Trace.excStream);
			}
		}
//14803 fine
    return error;
  }

	public ErrorMessage checkExistenceClass()
  {
/*    if (iLastPhaseGenerated == TABLES_GENERATED)
    {
      String className = getSourcePath() + "." + getClassName();
      try {
        Class c = Class.forName(className);
        if (c != null)
          return new ErrorMessage("EXTN000002", className);
      } catch (ClassNotFoundException ex) {
        return null;
      }
    }*/
//14803 ini
    String oldCompleteClassName = iOldExtension == null ? null : (iOldExtension.getSourcePath() + "." + iOldExtension.getClassName());
		String actualCompleteClassName = getSourcePath() + "." + getClassName();
    if (!isOnDB() || (isOnDB() && ((oldCompleteClassName == null && actualCompleteClassName!= null) || !oldCompleteClassName.equals(actualCompleteClassName)))){
			try {
				Class c = Class.forName(actualCompleteClassName);
				if (c != null)
					return new ErrorMessage("EXTN000002", actualCompleteClassName);
			} catch (ClassNotFoundException ex) {
				return null;
			}
		}
//14803 fine
    return null;
  }

  public boolean beforeSave() throws SQLException
  {
    if (!isOnDB() && !isChild()) {//13735
      try {
        setId(new Integer(Numerator.getNextInt("EXTEN_ENTITY")));
      }
      catch(NumeratorException e) {
      }
    }
    return true;
  }

  public int save() throws SQLException
  {
    beforeSave();
    BODataCollector collector = (BODataCollector)Factory.createObject(BODataCollector.class);
    collector.initialize("ExtensibleEntity",true,PersistentObject.NO_LOCK);
    //Fix 37762 inizio
  	if (!this.hasThNlsComponent()) {
  			Iterator tabs = this.getLayoutTabbeds().iterator();
  			while (tabs.hasNext()) {
  				ExtensionEntityLayoutTab tab = (ExtensionEntityLayoutTab) tabs.next();
  				if (tab.getTabbedType().equals(ExtensionEntityLayoutTab.TAB_THERM_NLS)) {
  					tabs.remove();
  				}
  			}
  		}
         //fix 37762 fine
    collector.setBo(this);
    BODCXML xml = (BODCXML)Factory.createObject(BODCXML.class);
    LWElement element = xml.toElement(collector);
    setExtension(element.toXMLString());
		setOldExtension(this);//14803
    return isChild() ? 1 : super.save();//13735
  }

  public boolean initializeOwnedObjects(boolean retFather)
  {
    if (getExtension() == null) return retFather;
    try {
      LWElementBuilder builder = (LWElementBuilder) Factory.createObject(LWElementBuilder.class);
      LWElement element = builder.parseString(getExtension());
      BODCXML xml = (BODCXML) Factory.createObject(BODCXML.class);
      xml.excludeClassAD("ExtensionEntityLayout", "AttributeListAsString");
      BODataCollector collector = xml.toDataCollector(element);
      collector.setOnBORecursive();
      setEqual((ExtensibleEntity) collector.getBo());
      iOldExtendedClass = getExtensionClassName();
      iOldInheritedClasseName = getInheritedClasseName();//23811
// 13735 ini
      setOnDB(true);
//14803 ini
			iOldExtension = (ExtensibleEntity) Factory.createObject(ExtensibleEntity.class);
			iOldExtension.setEqual(this);
//14803 fine
      for (Iterator iter = getOneToManyList().iterator(); iter.hasNext(); ) {
        ExtensibleEntity item = (ExtensibleEntity) iter.next();
//14803 ini
        item.iOldExtension = (ExtensibleEntity) Factory.createObject(ExtensibleEntity.class);
        item.iOldExtension.setEqual(item);
//14803 fine
        retFather = item.initializeOwnedObjects(retFather);
      }
//13735 fine
    } catch (CopyException ex) {
      ex.printStackTrace();
    } catch (NoSuchElementException ex) {
      ex.printStackTrace();
    } catch (NoSuchFieldException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    } catch (ParserConfigurationException ex) {
      ex.printStackTrace();
    } catch (SAXException ex) {
      ex.printStackTrace();
    }
    return retFather;
  }

  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    ExtensibleEntity extensibleEntity = (ExtensibleEntity)obj;
    iAttributes.clear();
    for (Iterator iter = extensibleEntity.iAttributes.iterator(); iter.hasNext(); ) {
      AttributeExtensible itemObj = (AttributeExtensible) iter.next();
      AttributeExtensible element = (AttributeExtensible)Factory.createObject(AttributeExtensible.class);
      element.setEqual(itemObj);
      element.setEstensioneEntita(this);//28079
      iAttributes.add(element);
    }
    iKeyColumns.clear();
    for (Iterator iter = extensibleEntity.iKeyColumns.iterator(); iter.hasNext(); ) {
      ExtensionKeyAttribute itemObj = (ExtensionKeyAttribute) iter.next();
      ExtensionKeyAttribute element = (ExtensionKeyAttribute)Factory.createObject(ExtensionKeyAttribute.class);
      element.setEqual(itemObj);
      element.setEstensioneEntita(this);//28079
      iKeyColumns.add(element);
    }
    iOrderColumns.clear();
    for (Iterator iter = extensibleEntity.iOrderColumns.iterator(); iter.hasNext(); ) {
      ExtensionColumnOrder itemObj = (ExtensionColumnOrder) iter.next();
      ExtensionColumnOrder element = (ExtensionColumnOrder)Factory.createObject(ExtensionColumnOrder.class);
      element.setEqual(itemObj);
      element.setEstensioneEntita(this);//28079
      iOrderColumns.add(element);
    }
    iLayoutTabbeds = (List)((ArrayList)extensibleEntity.iLayoutTabbeds).clone(); //13355
    // Fix 13585 ini
    iRelations.clear();
    for (Iterator iter = extensibleEntity.iRelations.iterator(); iter.hasNext(); ) {
      ExtensionEntityRelation itemObj = (ExtensionEntityRelation) iter.next();
      ExtensionEntityRelation element = (ExtensionEntityRelation)Factory.createObject(ExtensionEntityRelation.class);
      element.setEqual(itemObj);
      element.setExtensionEntity(this);//28079
      iRelations.add(element);
    }
    // Fix 13585 fine
// Fix 13733 ini
    iThermNlsAttributes.clear();
    for (Iterator iter = extensibleEntity.iThermNlsAttributes.iterator(); iter.hasNext(); ) {
      AttributeExtensible itemObj = (AttributeExtensible) iter.next();
      AttributeExtensible element = (AttributeExtensible)Factory.createObject(AttributeExtensible.class);
      element.setEqual(itemObj);
      element.setEstensioneEntita(this);//28079
      iThermNlsAttributes.add(element);
    }
// Fix 13733 fine
// Fix 13734 ini
    iComponents.clear();
    for (Iterator iter = extensibleEntity.iComponents.iterator(); iter.hasNext(); ) {
      ExtensionEntityComponent itemObj = (ExtensionEntityComponent) iter.next();
      ExtensionEntityComponent element = (ExtensionEntityComponent)Factory.createObject(ExtensionEntityComponent.class);
      element.setEqual(itemObj);
      element.setExtensionEntity(this);//28079
      iComponents.add(element);
    }
// Fix 13734 fine
// Fix 13735 ini
    iOneToManyList.clear();
    for (Iterator iter = extensibleEntity.iOneToManyList.iterator(); !isChild() && iter.hasNext(); ) {//13736
      ExtensibleEntity itemObj = (ExtensibleEntity) iter.next();
      ExtensibleEntity element = (ExtensibleEntity)Factory.createObject(ExtensibleEntity.class);
      element.setEqual(itemObj);
      element.setChild(true);//28079
      iOneToManyList.add(element);
    }
    iManyToManyList.clear();
    for (Iterator iter = extensibleEntity.iManyToManyList.iterator(); !isChild() && iter.hasNext(); ) {//13736
      CollectionExtensionEntity itemObj = (CollectionExtensionEntity) iter.next();
      CollectionExtensionEntity element = (CollectionExtensionEntity)Factory.createObject(CollectionExtensionEntity.class);
      element.setEqual(itemObj);
      element.setExtensionEntity(this);//28079
      iManyToManyList.add(element);
    }
// Fix 13735fine
		iFolder.setEqual(extensibleEntity.iFolder);
		iUserFolder.setEqual(extensibleEntity.iUserFolder);
		iOldExtension = extensibleEntity.iOldExtension;//Fix 14803
  }

  public ErrorMessage checkExtensionClassName()
  {
    if (getExtensionType() != INDIPENDENTE && getExtensionClassName() == null)
      return new ErrorMessage("BAS0000000");
//Fix 15800 inizio
		if (getExtensionType() != INDIPENDENTE && isProcessing()){
			ClassADCollection cad = null;
      try {
        cad = ClassADCollectionManager.collectionWithName(getExtensionClassName());
      }
      catch (NoSuchFieldException ex) {
				cad = null;
      }
      catch (NoSuchElementException ex) {
				cad = null;
      }
			if (cad == null) {
				return new ErrorMessage("CBS000002");
			}
		}

//Fix 15800 fine
		//28381 inizio
		if(!isOnDB() && (getExtensionType() == ExtensibleEntity.EXTENDED)) {
			return checkAllreadyReplacedClass();
		}
		//28381 fine
		return null;
  }

  public void setId(Integer id) {
    super.setId(id);

    for (Iterator iter = iAttributes.iterator(); iter.hasNext(); ) {
      AttributeExtensible item = (AttributeExtensible) iter.next();
      item.setExtensibleEntityId(id);
    }

    for (Iterator iter = iKeyColumns.iterator(); iter.hasNext(); ) {
      ExtensionKeyAttribute item = (ExtensionKeyAttribute) iter.next();
      item.setExtensibleEntityId(id);
    }

    for (Iterator iter = iOrderColumns.iterator(); iter.hasNext(); ) {
      ExtensionColumnOrder item = (ExtensionColumnOrder) iter.next();
      item.setExtensibleEntityId(id);
    }
    //13355 inizio
    for (Iterator iter = iLayoutTabbeds.iterator(); iter.hasNext(); ) {
        ExtensionEntityLayoutTab item = (ExtensionEntityLayoutTab) iter.next();
        item.setExtensibleEntityId(id);
      }
    //13355 fine
    //13585 ini
    for (Iterator iter = iRelations.iterator(); iter.hasNext(); ) {
      ExtensionEntityRelation item = (ExtensionEntityRelation) iter.next();
      item.setExtensionEntityId(id);
    }
    //13585 fine
// Fix 13733 ini
    for (Iterator iter = iThermNlsAttributes.iterator(); iter.hasNext(); ) {
      AttributeExtensible item = (AttributeExtensible) iter.next();
      item.setExtensibleEntityId(id);
    }
// Fix 13733 fine
// Fix 13734 ini
    for (Iterator iter = iComponents.iterator(); iter.hasNext(); ) {
      ExtensionEntityComponent item = (ExtensionEntityComponent) iter.next();
      item.setExtensionEntityId(id);
    }
// Fix 13734 fine
// Fix 13735 ini
    for (Iterator iter = iManyToManyList.iterator(); iter.hasNext(); ) {
      CollectionExtensionEntity item = (CollectionExtensionEntity) iter.next();
      item.setExtensionEntityId(id);
    }
// Fix 13735 fine
  }

  public void removeAttributeFromCollection(List collection, AttributeExtensible item)
  {
    for (Iterator iter = collection.iterator(); iter.hasNext(); ) {
      BusinessObject element = (BusinessObject) iter.next();
      if (element.getKey().equals(item.getKey()))
        iter.remove();
    }
  }
  public void removeInheritedAttributes()
  {
    if ((getAttributes() == null) || (getAttributes().isEmpty())) {
      return;
    }
    for (Iterator iter = getAttributes().iterator(); iter.hasNext(); ) {
      AttributeExtensible item = (AttributeExtensible) iter.next();
      if (item.getInheritenceType() == Inheritable.INHERITED){
      	if(isChild() && (item.getKeyPosition() > 0)) {
      		continue;
      	}
        removeAttributeFromCollection(iKeyColumns,item);
        removeAttributeFromCollection(iOrderColumns,item);
        iter.remove();
      }
    }
  }

  public void updateAttributeForInheritence(int size)
  {
    for (Iterator iter = iAttributes.iterator(); iter.hasNext(); ) {
      AttributeExtensible item = (AttributeExtensible) iter.next();
      if (item.getKeyPosition() != -1)
        item.setKeyPosition(item.getKeyPosition() + size);
      if (item.getColumnPosition() != AttributeExtensible.MAX_COL_NUMBER)
        item.setColumnPosition(item.getColumnPosition() + size);
    }
  }

  public void updateKeysForInheritence(int size)
  {
    for (Iterator iter = iKeyColumns.iterator(); iter.hasNext(); ) {
      ExtensionKeyAttribute item = (ExtensionKeyAttribute) iter.next();
      item.setPosition(item.getPosition() + size);
    }
  }

  public void updateColumnsForInheritence(int size)
  {
    for (Iterator iter = iOrderColumns.iterator(); iter.hasNext(); ) {
      ExtensionColumnOrder item = (ExtensionColumnOrder) iter.next();
      item.setPosition(item.getPosition() + size);
    }
  }

  public void updateForInheritedAttributes()
  {
    removeInheritedAttributes();
    int sizeInheritedAttributes = 0;
    if (getExtensionClassName() != null)
    {
      ClassADCollection classDescriptor = null;
      PersistentClassHdr classHDR = getExtendedClass();
      try {
        classDescriptor = ClassADCollectionManager.collectionWithName(getExtensionClassName());
      } 
      catch (NoSuchFieldException ex) {
      } 
      catch (NoSuchElementException ex) {
      }
      List inheritedAttributes = classDescriptor.getKeys();
      
      if (getExtensionType() == SINGOLA) {
        getKeyColumns().clear();
      }
      
      
      for (Iterator iter = inheritedAttributes.iterator(); iter.hasNext(); ) {
        ClassAD item = (ClassAD) iter.next();
        PersistentClassAd ad = getAttributeFormHDR(classHDR,item.getAttributeName());
        //23811 inizio
        AttributeExtensible existentAttribute = getAttributeWithName(item.getAttributeName());
        if(existentAttribute != null) {
        	if(!isChild()) {
        		getAttributes().remove(existentAttribute);
        		removeAttributeFromCollection(iKeyColumns, existentAttribute);
        		removeAttributeFromCollection(iOrderColumns, existentAttribute);
        	}
        }
        //23811 fine
        AttributeExtensible element = (AttributeExtensible)Factory.createObject(AttributeExtensible.class);
        element.setFatherKey(getKey());
        element.setAttributeName(item.getAttributeName());
        element.setBOAttributeName(item.getBOAttributeName());//28381
        element.setAttributeReadOnly(item.isReadOnly());
        element.setAttributeReferenceId(ad.getAttributeRefKey());
        element.setColumnName(item.getColumnName());
        element.setColumnTitleNLS(item.getColumnTitleNLS());
        element.setInheritenceType(Inheritable.INHERITED);
				//Mod. 14839//#### DF// element.setMandatory(item.isMandatory());
				element.setMandatory(true); //Mod. 14839//#### DF
        element.setNLSName(item.getAttributeNameNLS());
        element.setKeyPosition(sizeInheritedAttributes);
        element.setColumnPosition(sizeInheritedAttributes);
        element.setPresentInGrid(false);//28381
        getAttributes().add(sizeInheritedAttributes,element);
        
        ExtensionKeyAttribute keyAttr = (ExtensionKeyAttribute)Factory.createObject(ExtensionKeyAttribute.class);
        keyAttr.setExtensibleEntityId(getId());
        keyAttr.setAttributeName(element.getAttributeName());
        keyAttr.setPosition(sizeInheritedAttributes);
        getKeyColumns().add(sizeInheritedAttributes,keyAttr);
        
        ExtensionColumnOrder attrOrder = (ExtensionColumnOrder)Factory.createObject(ExtensionColumnOrder.class);
        attrOrder.setExtensibleEntityId(getId());
        attrOrder.setAttributeName(element.getAttributeName());
        attrOrder.setColumnName(element.getColumnName());
        attrOrder.setPosition(sizeInheritedAttributes);
        getOrderColumns().add(sizeInheritedAttributes, attrOrder);
        sizeInheritedAttributes++;
      }
      //Fix 18574 inizio
      //questo serve soltanto nel caso in cui il TableManager della classe antenata è basato su una vista
      //e alla chiave dichiarata nella TableManager manca uno o più colonne di quelle dichiarate
      //nella tabella su cui è basata la vista
      try {
        //33178 inizio
        //String completeName = ((PersistentObject) Factory.createObject(classDescriptor.getBOClassName())).getAbstractTableManager().getMainTableName();
        AbstractTableManager tm = ((PersistentObject) Factory.createObject(classDescriptor.getBOClassName())).getAbstractTableManager();
        String completeName = tm.getMainTableName();
        //33178 fine
      	String tablename = TableBuilder.getTableName(completeName);
      	DictTables dictTable = DictTables.elementWithKey(tablename, PersistentObject.NO_LOCK);
      	if(dictTable != null) {
      		for (int i = getKeyColumns().size(); i < dictTable.getKeyColumns().size(); i++) {
      			DictColumns column = (DictColumns)dictTable.getKeyColumns().get(i);
      			AttributeExtensible attribute = createAttribute(column);
      			//33178 inizio
      			attribute.setExcludeFromInsert(true);
      			attribute.setExcludeFromUpdate(true);
      			AttributeRelation attributeRelation = searchAttributeWithColumnName(tm, column.getName());
      			if(attributeRelation != null) {
      			  String boAttributeName = getAttributeName(attributeRelation);
      			  ClassAD classAD = searchAttributeWithBOAttributeName(classDescriptor, boAttributeName);
      			  if(classAD != null) {
      			    attribute.setAttributeName(classAD.getAttributeName());
      			    attribute.setBOAttributeName(classAD.getBOAttributeName());
      			    attribute.setAttributeReferenceId(classAD.getType().getAttributeRef());
      			    attribute.setKeyPosition(classAD.getKeySequence());
      			  }
      			}
      			//33178 fine
      			getAttributes().add(i, attribute);
//          ExtensionKeyAttribute keyAttribute = createKeyAttribute(column);
//          getKeyColumns().add(i, keyAttribute);
      			ExtensionColumnOrder columnOrder = createColumnOrder(column);
      			getOrderColumns().add(i, columnOrder);
      		}
      	}
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
      //Fix 18574 fine
      
    }
    //23811 inizio
    updateAttributeForInheritence(sizeInheritedAttributes);
    updateKeysForInheritence(sizeInheritedAttributes);
    updateColumnsForInheritence(sizeInheritedAttributes);
    //23811 fine
  }
  //33178 inizio
  public AttributeRelation searchAttributeWithColumnName(AbstractTableManager tm, String columnName) {
    Collection attributes = tm.getAttributeRelations().values();
    for (Iterator iterator = attributes.iterator(); iterator.hasNext();) {
      AttributeRelation attribute = (AttributeRelation) iterator.next();
      if(columnName.equals(attribute.getColumnName())) {
        return attribute;
      }
    }
    return null;
  }
  
  public ClassAD searchAttributeWithBOAttributeName(ClassADCollection classDescriptor, String boAttributeName) {
    Collection attributes = classDescriptor.getAttributes().values();
    for (Iterator iterator = attributes.iterator(); iterator.hasNext();) {
      ClassAD attribute = (ClassAD) iterator.next();
      if(attribute.getBOAttributeName() != null) {
        if(boAttributeName.equals(attribute.getBOAttributeName())) {
          return attribute;
        }
      }
      else {
        if(boAttributeName.equals(attribute.getAttributeName())) {
          return attribute;
        }
      }
    }
    return null;
  }
  
  public String getAttributeName(AttributeRelation attribute) {
    Pattern pattern = Pattern.compile("\\[\\S*\\s");  
    Matcher matcher = pattern.matcher(attribute.describe());   
    if(matcher.find()) {
      return matcher.group().substring(1).trim();
    }
    
    return null;
  }
  //33178 fine
  //Fix 18574 inizio
  protected AttributeExtensible createAttribute(DictColumns column) {
    AttributeExtensible attribute = (AttributeExtensible)Factory.createObject(AttributeExtensible.class);
    attribute.setFatherKey(getKey());
    attribute.setColumnName(column.getName());
    attribute.setAttributeName(getAttributeName(column));
    attribute.setKeyPosition(column.getKeySequence());
    attribute.setColumnPosition(column.getKeySequence());
    attribute.setAttributeReferenceId(column.getReferenceId());
    attribute.setDefaultValue(column.getDefaultValue());
    attribute.setAttributeReadOnly(column.isReadOnly());
    attribute.setMandatory(column.getNullType() != DictColumns.NULL);
    attribute.setNLSName(column.getDescription());
    attribute.setColumnTitleNLS(column.getDescription());
    attribute.setInheritenceType(Inheritable.INHERITED);
    attribute.setPresentInGrid(false);
    attribute.setPosition(-1);
    return attribute;
  }
  
  protected ExtensionKeyAttribute createKeyAttribute(DictColumns column) {
    ExtensionKeyAttribute keyAttribute = (ExtensionKeyAttribute)Factory.createObject(ExtensionKeyAttribute.class);
    keyAttribute.setExtensibleEntityId(getId());
    keyAttribute.setAttributeName(getAttributeName(column));
    keyAttribute.setPosition(column.getKeySequence());
    return keyAttribute;
  }
  
  protected ExtensionColumnOrder createColumnOrder(DictColumns column) {
    ExtensionColumnOrder columnOrder = (ExtensionColumnOrder)Factory.createObject(ExtensionColumnOrder.class);
    columnOrder.setExtensibleEntityId(getId());
    columnOrder.setAttributeName(getAttributeName(column));
    columnOrder.setColumnName(column.getName());
    columnOrder.setPosition(column.getKeySequence());
    return columnOrder;
  }
  
  protected String getAttributeName(DictColumns column) {
    return column.getName();
  }
  //Fix 18574 fine

  protected PersistentClassAd getAttributeFormHDR(PersistentClassHdr classHDR, String attrName)
  {
    if (classHDR == null /*|| classHDR.getAttributes() == null || classHDR.getAttributes().isEmpty() Fix 17011*/)
      return null;
    for (Iterator iter = classHDR.getAttributes().iterator(); iter.hasNext(); ) {
      PersistentClassAd item = (PersistentClassAd) iter.next();
      if (item.getAttributeName().equals(attrName))
        return item;
    }
		// Fix 17011 inizio
		if (classHDR.getFatherClassHdr() !=null){
			return getAttributeFormHDR(classHDR.getFatherClassHdr(),attrName);
		}
		// Fix 17011 fine
    return null;
  }

//  public Vector generateDictionnary() throws IOException { Fix 15587
	public Vector generateDictionnary(ServletEnvironment se, boolean withCheckDB) throws IOException {//Fix 15587
    TableBuilder tb = (TableBuilder)Factory.createObject(TableBuilder.class);
    tb.init(this, se);
    //tb.setFileNamePrefix(this.getKey() + "_");//27963 //30122
    tb.setFileNamePrefix(getFilenamePrefix());//30122
    tb.setExportDictionnary(this.isExportDictionnary());
		tb.getReportFileWriter().println("<B>" + tb.getGenerationBeginTime() + "</B>");// Fix 14803
		//28079 inizio
		tb.setCreateTablesRequired(isCreateTablesRequired());
		//28079 fine
		Vector errors = tb.checkAll();
//15587 inizio
		if (!withCheckDB && (isOnlyDatabaseError(errors) || isOnlyDatabaseInformation(errors)))
			errors = new Vector();
//15587 fine
		if (!errors.isEmpty())
			tb.errors.addAll(errors);
    if (!tb.getErrors().isEmpty() || !tb.generate()) {
			tb.reportErrors();
		}
		else {
			//27963 fine
			tableKeys.clear();
			tableKeys.addAll(tb.getTableKeys());
			//27963 fine
			tb.getReportFileWriter().println("<BR><B>" + ResourceLoader.getString(BaseExtensionGenerator.EXT_RES, "GENERATE_DATABASES") + "</B><BR>");// Fix 14803
			if(se != null) {
				setLastPhaseGenerated(TABLES_GENERATED);
			}
		}
		tb.closeReport();
    return tb.getErrors();
  }

  public Vector generateJavaFiles(ServletEnvironment se) throws IOException {
		WizardBuilder builder = (WizardBuilder)Factory.createObject(WizardBuilder.class);
		builder.init(this, se);
		//30451 inizio
		boolean replacementEnabled = !isReplacementDisabled();
		if(getExtensionType() == ExtensibleEntity.EXTENDED) {//32879
		  setAllReplacementEnabled(false);
		  Factory.clearHashtable();
		}
		//30451 fine
		if (!builder.generate()) {
			builder.reportErrors();
		}
		else {
			builder.getReportFileWriter().println("<BR><B>" + ResourceLoader.getString(BaseExtensionGenerator.EXT_RES, "GENERATE_JAVA_FILES") + "</B><BR>");// Fix 14803
			if(se != null) {
				setLastPhaseGenerated(SOURCES_GENERATED);
			}
		}
		//30451 inizio
		if(getExtensionType() == ExtensibleEntity.EXTENDED) {//32879
		  setAllReplacementEnabled(replacementEnabled);
		  Factory.clearHashtable();
		}
		//30451 fine
		builder.closeReport();
		return builder.getErrors();
  }

//	public Vector generateJavaClasses() throws IOException { // 14258
  public Vector generateJavaClasses(ServletEnvironment se) throws IOException {// 14258
    ByteCodeGenerator generator = (ByteCodeGenerator) Factory.createObject(ByteCodeGenerator.class);
//		generator.init(this,null);//14258
    generator.init(this, se);    //14258
    if (!generator.generate()) {
			generator.reportErrors();
    }
    else{
			generator.getReportFileWriter().println("<BR><B>" + ResourceLoader.getString(BaseExtensionGenerator.EXT_RES, "GENERATE_JAVA_CLASSES") + "</B><BR>");// Fix 14803
			if(se != null) {
				setLastPhaseGenerated(CLASSES_GENERATED);
			}
    }
		generator.closeReport();
    return generator.getErrors();
  }

  public Vector generateDescriptors(ServletEnvironment se) throws IOException {
    FWDataGenerator generator = (FWDataGenerator)Factory.createObject(FWDataGenerator.class);
		generator.init(this, se);
		//generator.setFileNamePrefix(this.getKey() + "_");//27963 //30122
		generator.setFileNamePrefix(getFilenamePrefix());//30122
    if (generator.generate()) {
			generator.getReportFileWriter().println("<BR><B>" + ResourceLoader.getString(BaseExtensionGenerator.EXT_RES, "GENERATE_DESCRIPTORS") + "</B><BR>");// Fix 14803
			if(se != null) {
				setLastPhaseGenerated(FWDATA_GENERATED);
			}
    }
		else{
			generator.reportErrors();
		}
		generator.closeReport();
    return generator.getErrors();
  }
  
  public Vector generateWebFiles(ServletEnvironment se)
  {
    ExtensionWebGenerator generator = (ExtensionWebGenerator)Factory.createObject(ExtensionWebGenerator.class);
		try{
			generator.init(this,se);
			generator.getReportFileWriter().println("<B>" + generator.getGenerationBeginTime() + "</B>");
			if (generator.isWorkDirFolderCreated()){
				generator.getReportFileWriter().println("<BR>" + ResourceLoader.getString(TableBuilder.SECURITY_RESOURCE, "FolderCreated") + " : <B><I>" + generator.getWorkPath() + "</I></B>");
			}
			for (Iterator iter = getOneToManyList().iterator(); iter.hasNext(); ) {
				ExtensibleEntity item = (ExtensibleEntity) iter.next();
				if(item.hasStandardTabs()) {
					ExtensionWebGenerator childGenerator = (ExtensionWebGenerator)Factory.createObject(ExtensionWebGenerator.class);
					childGenerator.init(item, se);
					childGenerator.setWorkSourcePath(generator.getWorkSourcePath());
					childGenerator.setWorkLibPath(generator.getWorkLibPath());
					childGenerator.setWorkWebsrcPath(generator.getWorkWebsrcPath());
					childGenerator.setRealLibPath(generator.getRealLibPath());
					childGenerator.setRealWebsrcPath(generator.getRealWebsrcPath());
					childGenerator.setReportFileName(generator.getReportFileName());
					childGenerator.setReportFileWriter(generator.getReportFileWriter());
					childGenerator.generateWebFiles();
					childGenerator.createResourceFile();
					if(shouldCopyResources(se)) {//30122
						childGenerator.copyWebFiles();
						childGenerator.copyResourceFile();
					}
				}
			}
			//28079 inizio
      //generator.generateHTMLFile();
      //generator.generateTFMLFile();
			if(getExtensionType() == EXTENDED) {
				generator.generateXmlFile();
			}
			else {
				generator.generateWebFiles();
			}
      //28079 fine
      generator.createResourceFile();
      if(shouldCopyResources(se)) {//30122
  			//28079 inizio
      	//generator.copyHTMLFile();
      	//generator.copyTFMLFile();
      	if(getExtensionType() == EXTENDED) {
  				generator.copyXmlFile();
  			}
      	else {
      		generator.copyWebFiles();
      	}
      	//28079 fine
      	generator.copyResourceFile();
      }
//14803 ini			setLastPhaseGenerated(WEBFILES_GENERATED);
			if (generator.getErrors().isEmpty()){
				generator.getReportFileWriter().println("<BR><B>" + ResourceLoader.getString(BaseExtensionGenerator.EXT_RES, "GENERATE_WEB_FILES") + "</B><BR>");
				if(se != null) {
					setLastPhaseGenerated(WEBFILES_GENERATED);
				}
      }
			else{
				generator.reportErrors();
			}
//14803 fine
		} catch (FileNotFoundException fnfe){
			generator.addError(new ErrorMessage("BAS0000078",fnfe.getMessage()));
			generator.reportErrors();
		} catch (IOException ioe){
			generator.addError(new ErrorMessage("BAS0000078",ioe.getMessage()));
			generator.reportErrors();
		} catch (NoSuchFieldException nsfe){
			generator.addError(new ErrorMessage("BAS0000078",nsfe.getMessage()));
			generator.reportErrors();
		}	finally{
			generator.closeReport();
		}
		return generator.getErrors();
  }

  public Vector generateJspFile(ServletEnvironment se) throws IOException {
		ExtensionWebGenerator generator = (ExtensionWebGenerator)Factory.createObject(ExtensionWebGenerator.class);
		generator.setGenerationClass("JSP");
	  generator.init(this,se);
		generator.getReportFileWriter().println("<B>" + generator.getGenerationBeginTime() + "</B>");
		if (generator.isWorkDirFolderCreated()){
			generator.getReportFileWriter().println("<BR>" + ResourceLoader.getString(TableBuilder.SECURITY_RESOURCE, "FolderCreated") + " : <B><I>" + generator.getWorkPath() + "</I></B>");
		}
		for (Iterator iter = getOneToManyList().iterator(); iter.hasNext(); ) {
			ExtensibleEntity item = (ExtensibleEntity) iter.next();
			//23811 inizio
			if(!item.hasStandardTabs()) {
				continue;
			}
			//23811 fine
			ExtensionWebGenerator childGenerator = (ExtensionWebGenerator)Factory.createObject(ExtensionWebGenerator.class);
			childGenerator.init(item,se);
			childGenerator.setWorkSourcePath(generator.getWorkSourcePath());
			childGenerator.setWorkLibPath(generator.getWorkLibPath());
			childGenerator.setWorkWebsrcPath(generator.getWorkWebsrcPath());
			childGenerator.setRealLibPath(generator.getRealLibPath());
			childGenerator.setRealWebsrcPath(generator.getRealWebsrcPath());
			childGenerator.setReportFileName(generator.getReportFileName());
			childGenerator.setReportFileWriter(generator.getReportFileWriter());
			if (childGenerator.generate()){
				if(shouldCopyResources(se)) {//30122
					childGenerator.copyJspFiles();
				}
      }
		}
		if(getExtensionType() != EXTENDED) {
			if (generator.generate()){
				if(shouldCopyResources(se)) {//30122
					generator.copyJspFiles();
				}
				if (generator.getErrors().isEmpty()){
					generator.getReportFileWriter().println("<BR><B>" + ResourceLoader.getString(BaseExtensionGenerator.EXT_RES, "GENERATE_JSP_FILE") + "</B><BR>");// Fix 14803
					if(se != null) {
						setLastPhaseGenerated(JSP_GENERATED);
					}
				}
//14803 ini
				else{
					generator.reportErrors();
				}
// 14803 fine
			}
		}
		generator.closeReport();
		return generator.getErrors();
  }

  public Vector generateEntityMenu(ServletEnvironment se) throws IOException {
    ExtensionUserMenu generator = (ExtensionUserMenu)Factory.createObject(ExtensionUserMenu.class);
		generator.init(this, se);
		//generator.setFileNamePrefix(this.getKey() + "_");//27963 //30122
		generator.setFileNamePrefix(getFilenamePrefix());//30122
		if (generator.generate()) {
			generator.getReportFileWriter().println("<BR><B>" + ResourceLoader.getString(BaseExtensionGenerator.EXT_RES, "GENERATE_ENTITY_MENU") + "</B><BR>");// Fix 14803
			if(se != null) {
				setLastPhaseGenerated(MENU_GENERATED);
			}
		}
		else{
			generator.reportErrors();
		}
		generator.closeReport();
		return generator.getErrors();
  }
//15587 inizio
//	public Vector generateNextPhase(ServletEnvironment se) throws IOException {
//		Vector errors = generateDictionnary();
  public Vector generateNextPhase(ServletEnvironment se,boolean withCheckAll) throws IOException {
    Vector errors = generateDictionnary(se, withCheckAll);
//15587 fine
    if (errors == null || errors.isEmpty())
      errors = generateJavaFiles(se);
    if (errors == null || errors.isEmpty())
//			errors = generateJavaClasses();   14258
      errors = generateJavaClasses(se);// 14258
    if (errors == null || errors.isEmpty())
      errors = generateDescriptors(se);
    if (errors == null || errors.isEmpty())
      errors = generateWebFiles(se);
    if (errors == null || errors.isEmpty())
      errors = generateJspFile(se);
    if (errors == null || errors.isEmpty())
      errors = generateEntityMenu(se);
    return errors;
  }

  public AttributeExtensible getAttributeForColumn(String columnName)
  {
  	if(columnName == null) {
  		return null;
  	}
    for (Iterator iter = getAttributes().iterator(); iter.hasNext(); ) {
      AttributeExtensible attribute = (AttributeExtensible) iter.next();
      if (attribute.getColumnName().equals(columnName)) {
        return attribute;
      }
    }

    return null;
  }

  public int getKeySequences(AttributeExtensible attribute)
  {
    int seq = 1;
    for (Iterator iter = getKeyColumns().iterator(); iter.hasNext(); seq++) {
      ExtensionKeyAttribute item = (ExtensionKeyAttribute) iter.next();
      if (item.getAttributeName().equals(attribute.getAttributeName())) {
        return seq;
      }
    }
    return 0;
  }
//13548 fine
//13355 inizio
  public List getLayoutTabbeds(){
	//if (!iLayoutTabbedsCompleted) //14088
		  //completeLayoutTabbeds(); //14088
    return iLayoutTabbeds;
  }

  //14088 inizio
  public void completeLayoutTabbeds(){
	  //boolean findWf = (getThermWorkflowNlsName()!=null && !getThermWorkflowNlsName().equals(""));
	  boolean findComment = (getThermCommentNlsName()!=null && !getThermCommentNlsName().equals(""));
	  boolean findNls = (getThermNlsNlsName()!=null && !getThermNlsNlsName().equals(""));

	  List toRemove = new ArrayList();
	  for (int i=0;i<iLayoutTabbeds.size();i++){
		  ExtensionEntityLayoutTab item = ((ExtensionEntityLayoutTab)iLayoutTabbeds.get(i));
		  /*if (findWf && item.getTabbedType().equals(ExtensionEntityLayoutTab.TAB_THERM_WF))
			  findWf=false;
		  else if (!findWf  && item.getTabbedType().equals(ExtensionEntityLayoutTab.TAB_THERM_WF))
				 toRemove.add(item); */
		//Mod. 14839 inizio////##### DF
			if (item.getTabbedType() == null)
				item.setTabbedType(ExtensionEntityLayoutTab.TAB_STANDARD);
		//Fine mod. 14839//##### DF

		  if (findComment && item.getTabbedType().equals(ExtensionEntityLayoutTab.TAB_THERM_COMMENT))
			  findComment=false;
		  else if (!findComment  && item.getTabbedType().equals(ExtensionEntityLayoutTab.TAB_THERM_COMMENT))
			     toRemove.add(item);
		  if (findNls && item.getTabbedType().equals(ExtensionEntityLayoutTab.TAB_THERM_NLS))
			  findNls=false;
		  else if (!findNls  && item.getTabbedType().equals(ExtensionEntityLayoutTab.TAB_THERM_NLS))
			     toRemove.add(item);
	  }
	  /*
	  if (findWf){
		  ExtensionEntityLayoutTab tab = new ExtensionEntityLayoutTab();
		  tab.setTabbedType(ExtensionEntityLayoutTab.TAB_THERM_WF);-*
		  tab.setDescription(ExtensionEntityLayoutTab.TAB_THERM_WF_LABEL);
		  tab.setId(new Integer(getLayouTabbedsMaxId()+1));
		  iLayoutTabbeds.add(tab);
	  }*/
	  if (findComment){
		  ExtensionEntityLayoutTab tab = new ExtensionEntityLayoutTab();
		  tab.setTabbedType(ExtensionEntityLayoutTab.TAB_THERM_COMMENT);
		  tab.setDescription(ExtensionEntityLayoutTab.TAB_THERM_COMMENT_LABEL);
		  tab.setId(new Integer(getLayouTabbedsMaxId()+1));
		  iLayoutTabbeds.add(tab);
	  }
	  if (findNls){
		  ExtensionEntityLayoutTab tab = new ExtensionEntityLayoutTab();
		  tab.setTabbedType(ExtensionEntityLayoutTab.TAB_THERM_NLS);
		  tab.setDescription(ExtensionEntityLayoutTab.TAB_THERM_NLS_LABEL);
		  tab.setId(new Integer(getLayouTabbedsMaxId()+1));
		  iLayoutTabbeds.add(tab);
	  }

      for (int j=0;j<toRemove.size();j++)  {
    	  ExtensionEntityLayoutTab item = ((ExtensionEntityLayoutTab)toRemove.get(j));
    	  String key = item.getKey();
    	  iLayoutTabbeds.remove(getLayouTabbedWithKey(key));
      }
      
      
      iLayoutTabbedsCompleted=true;
			//Mod. 14439 inizio//######### DF
						Collections.sort(iLayoutTabbeds, new Comparator() {
							public int compare(Object o1, Object o2) {
								return ((ExtensionEntityLayoutTab) o1).getPosition() - ((ExtensionEntityLayoutTab) o2).getPosition();
							}

							public boolean equals(Object obj) {
								return obj == this;
							}
						});
			//fine mod. 14839
		}
  //14088 fine

  public void updateAddLayoutTabbed(ExtensionEntityLayoutTab tabbed) {
    String key = tabbed.getKey();
    ExtensionEntityLayoutTab originalTabbed = getLayouTabbedWithKey(key);
    if (originalTabbed != null) {
      iLayoutTabbeds.remove(getLayouTabbedWithKey(key));
    }
    tabbed.setPosition(iLayoutTabbeds.size());//14088
    iLayoutTabbeds.add(tabbed);
  }

  public ExtensionEntityLayoutTab getLayouTabbedWithKey(String key) {
    for (int i = 0; i < iLayoutTabbeds.size(); i++) { //14088
      ExtensionEntityLayoutTab et = (ExtensionEntityLayoutTab) iLayoutTabbeds.get(i); //14088
      if (et.getKey().equals(key)) {
        return et;
      }
    }
    return null;
  }

  public int getLayouTabbedsMaxId() {
    int max = 0;
    //for (int i = 0; i < getLayoutTabbeds().size(); i++) { //14088
    for (int i = 0; i < iLayoutTabbeds.size(); i++) { //14088
      ExtensionEntityLayoutTab et = (ExtensionEntityLayoutTab) iLayoutTabbeds.get(i); //14088
      if (et.getId().intValue() > max)
        max = et.getId().intValue();
    }
    return max;
  }

  public List getOrderedLayoutTabbeds(){
    //14088 Collections.sort(iLayoutTabbeds,new ExtensionEntityLayoutTabComparator());
  	Collections.sort(iLayoutTabbeds);//23811
    return iLayoutTabbeds;
  }
//13355 fine
//13733 ini
  public ErrorMessage checkThWorkflowName()
  {
    if (hasThWorkflow() && getThWorkflowName()==null)
      return new ErrorMessage("BAS0000000");

    if (hasThWorkflow())
    {
//Fix 15501 inizio
      if (Character.isLowerCase(getThWorkflowName().charAt(0))) {
        return new ErrorMessage("EXTN000017");
      }
//Fix 15501 fine
      if (getThermCommentName()!=null && getThWorkflowName().equals(getThermCommentName()))
        return new ErrorMessage("EXTN000006", getThWorkflowName());

      if (getThNlsName()!=null && getThWorkflowName().equals(getThNlsName()))
        return new ErrorMessage("EXTN000006", getThWorkflowName());

      for (Iterator iter = getAttributes().iterator(); iter.hasNext(); ) {
        AttributeExtensible item = (AttributeExtensible) iter.next();
        if (getThWorkflowName().equals(item.getAttributeName()))
          return new ErrorMessage("EXTN000006", getThWorkflowName());
      }

      for (Iterator iter = getRelations().iterator(); iter.hasNext(); ) {
        ExtensionEntityRelation item = (ExtensionEntityRelation) iter.next();
        if (getThWorkflowName().equals(item.getRelationName()))
          return new ErrorMessage("EXTN000006", getThWorkflowName());
      }
// 14803 ini
			if (isOptimisticLock() && getThWorkflowName().equals(ExtensibleEntityDataCollector.TIMESTAMP_ATTRIBUTE_NAME)){
				return new ErrorMessage("EXTN000006", getThWorkflowName());
			}
			if (getThNlsPlural()!=null && getThWorkflowName().equals(getThNlsPlural())){
        return new ErrorMessage("EXTN000006", getThWorkflowName());
      }
			for (Iterator iter = getComponents().iterator(); iter.hasNext(); ) {
				ExtensionEntityComponent item = (ExtensionEntityComponent) iter.next();
				if (getThWorkflowName().equals(item.getComponentName()))
					return new ErrorMessage("EXTN000006", getThWorkflowName());
			}
			for (Iterator iter = getManyToManyList().iterator(); iter.hasNext(); ) {
        CollectionExtensionEntity item = (CollectionExtensionEntity) iter.next();
        if (getThWorkflowName().equals(item.getCollectionName()))
          return new ErrorMessage("EXTN000006", getThWorkflowName());
			}
			for (Iterator iter = getOneToManyList().iterator(); iter.hasNext(); ) {
				ExtensibleEntity item = (ExtensibleEntity) iter.next();
				if (getThWorkflowName().equals(item.getCollectionName()))
					return new ErrorMessage("EXTN000006", getThWorkflowName());
			}
// 14803 fine
    }
    return null;
  }

  public ErrorMessage checkThNlsName()
  {
  	//23811 inizio
  	if(!isEnableNlsTab()){
  		return null;
  	}
  	//23811 fine
    if (hasThNlsComponent() && getThNlsName()==null)
      return new ErrorMessage("BAS0000000");

    if (hasThNlsComponent())
    {
//Fix 15501 inizio
			if (Character.isLowerCase(getThNlsName().charAt(0))){
				return new ErrorMessage("EXTN000017");
			}
//Fix 15501 fine
      if (getThermCommentName()!=null && getThNlsName().equals(getThermCommentName()))
        return new ErrorMessage("EXTN000006", getThNlsName());

      if (getThWorkflowName()!=null && getThWorkflowName().equals(getThNlsName()))
        return new ErrorMessage("EXTN000006", getThWorkflowName());

      for (Iterator iter = getAttributes().iterator(); iter.hasNext(); ) {
        AttributeExtensible item = (AttributeExtensible) iter.next();
        if (getThNlsName().equals(item.getAttributeName()))
          return new ErrorMessage("EXTN000006", getThNlsName());
      }

      for (Iterator iter = getRelations().iterator(); iter.hasNext(); ) {
        ExtensionEntityRelation item = (ExtensionEntityRelation) iter.next();
        if (getThNlsName().equals(item.getRelationName()))
          return new ErrorMessage("EXTN000006", getThNlsName());
      }
// 14803 ini
			if (isOptimisticLock() && getThNlsName().equals(ExtensibleEntityDataCollector.TIMESTAMP_ATTRIBUTE_NAME)){
				return new ErrorMessage("EXTN000006", getThNlsName());
			}
			if (getThNlsPlural()!=null && getThNlsName().equals(getThNlsPlural())){
				return new ErrorMessage("EXTN000006", getThNlsName());
			}
			for (Iterator iter = getComponents().iterator(); iter.hasNext(); ) {
				ExtensionEntityComponent item = (ExtensionEntityComponent) iter.next();
				if (getThNlsName().equals(item.getComponentName()))
					return new ErrorMessage("EXTN000006", getThNlsName());
			}
			for (Iterator iter = getManyToManyList().iterator(); iter.hasNext(); ) {
				CollectionExtensionEntity item = (CollectionExtensionEntity) iter.next();
				if (getThNlsName().equals(item.getCollectionName()))
					return new ErrorMessage("EXTN000006", getThNlsName());
			}
			for (Iterator iter = getOneToManyList().iterator(); iter.hasNext(); ) {
				ExtensibleEntity item = (ExtensibleEntity) iter.next();
				if (getThNlsName().equals(item.getCollectionName()))
					return new ErrorMessage("EXTN000006", getThNlsName());
			}
// 14803 fine
    }
    return null;
  }

  public ErrorMessage checkThermCommentName()
  {
    if (hasThComment() && getThermCommentName()==null)
      return new ErrorMessage("BAS0000000");

    if (hasThComment())
    {
//Fix 15501 inizio
			if (Character.isLowerCase(getThermCommentName().charAt(0))){
				return new ErrorMessage("EXTN000017");
			}
//Fix 15501 fine
      if (getThWorkflowName()!=null && getThWorkflowName().equals(getThermCommentName()))
        return new ErrorMessage("EXTN000006", getThWorkflowName());

      if (getThNlsName()!=null && getThermCommentName().equals(getThNlsName()))
        return new ErrorMessage("EXTN000006", getThermCommentName());

      for (Iterator iter = getAttributes().iterator(); iter.hasNext(); ) {
        AttributeExtensible item = (AttributeExtensible) iter.next();
        if (getThermCommentName().equals(item.getAttributeName()))
          return new ErrorMessage("EXTN000006", getThermCommentName());
      }

      for (Iterator iter = getRelations().iterator(); iter.hasNext(); ) {
        ExtensionEntityRelation item = (ExtensionEntityRelation) iter.next();
        if (getThermCommentName().equals(item.getRelationName()))
          return new ErrorMessage("EXTN000006", getThermCommentName());
      }
// 14803 ini
			if (isOptimisticLock() && getThermCommentName().equals(ExtensibleEntityDataCollector.TIMESTAMP_ATTRIBUTE_NAME)){
				return new ErrorMessage("EXTN000006", getThermCommentName());
			}
			if (getThNlsPlural()!=null && getThermCommentName().equals(getThNlsPlural())){
				return new ErrorMessage("EXTN000006", getThermCommentName());
			}
			for (Iterator iter = getComponents().iterator(); iter.hasNext(); ) {
				ExtensionEntityComponent item = (ExtensionEntityComponent) iter.next();
				if (getThermCommentName().equals(item.getComponentName()))
					return new ErrorMessage("EXTN000006", getThermCommentName());
			}
			for (Iterator iter = getManyToManyList().iterator(); iter.hasNext(); ) {
				CollectionExtensionEntity item = (CollectionExtensionEntity) iter.next();
				if (getThermCommentName().equals(item.getCollectionName()))
					return new ErrorMessage("EXTN000006", getThermCommentName());
			}
			for (Iterator iter = getOneToManyList().iterator(); iter.hasNext(); ) {
				ExtensibleEntity item = (ExtensibleEntity) iter.next();
				if (getThermCommentName().equals(item.getCollectionName()))
					return new ErrorMessage("EXTN000006", getThermCommentName());
			}
// 14803 fine
    }
    return null;
  }

  public ErrorMessage checkWF_IDColumnName()
  {
    if (hasThWorkflow() && getWF_IDColumnName()==null)
      return new ErrorMessage("BAS0000000");
    if (hasThWorkflow())
    {
      if (getWF_NODE_IDColumnName()!=null && getWF_IDColumnName().equals(getWF_NODE_IDColumnName()))
        return new ErrorMessage("EXTN000007", getWF_IDColumnName());

      if (getWF_CLASS_IDColumnName()!=null && getWF_IDColumnName().equals(getWF_CLASS_IDColumnName()))
        return new ErrorMessage("EXTN000007", getWF_IDColumnName());

      if (getWF_SUB_NODE_IDColumnName()!=null && getWF_IDColumnName().equals(getWF_SUB_NODE_IDColumnName()))
        return new ErrorMessage("EXTN000007", getWF_IDColumnName());

      if (getThCommentColumnName()!=null && getWF_IDColumnName().equals(getThCommentColumnName()))
        return new ErrorMessage("EXTN000007", getWF_IDColumnName());

      for (Iterator iter = getAttributes().iterator(); iter.hasNext(); ) {
        AttributeExtensible item = (AttributeExtensible) iter.next();
        if (getWF_IDColumnName().equals(item.getColumnName()))
          return new ErrorMessage("EXTN000007", getWF_IDColumnName());
      }
// 14803 ini
			if (isOptimisticLock() && getWF_IDColumnName().equals(ExtensibleEntityDataCollector.TIMESTAMP_COLUMN_NAME))
				return new ErrorMessage("EXTN000007", getWF_IDColumnName());
			for (Iterator iter = getComponents().iterator(); iter.hasNext(); ) {
				ExtensionEntityComponent item = (ExtensionEntityComponent) iter.next();
				List componentColumns = item.getComponentColumnsName();
				for (int i = 0; i < componentColumns.size(); i++){
					if (getWF_IDColumnName().equals((String)componentColumns.get(i))){
						return new ErrorMessage("EXTN000007", getWF_IDColumnName());
					}
				}
			}
// 14803 fine
    }
    return null;
  }

  public ErrorMessage checkWF_NODE_IDColumnName()
  {
    if (hasThWorkflow() && getWF_NODE_IDColumnName()==null)
      return new ErrorMessage("BAS0000000");
    if (hasThWorkflow())
    {
      if (getWF_IDColumnName()!=null && getWF_IDColumnName().equals(getWF_NODE_IDColumnName()))
        return new ErrorMessage("EXTN000007", getWF_IDColumnName());

      if (getWF_CLASS_IDColumnName()!=null && getWF_NODE_IDColumnName().equals(getWF_CLASS_IDColumnName()))
        return new ErrorMessage("EXTN000007", getWF_NODE_IDColumnName());

      if (getWF_SUB_NODE_IDColumnName()!=null && getWF_NODE_IDColumnName().equals(getWF_SUB_NODE_IDColumnName()))
        return new ErrorMessage("EXTN000007", getWF_NODE_IDColumnName());

      if (getThCommentColumnName()!=null && getWF_NODE_IDColumnName().equals(getThCommentColumnName()))
        return new ErrorMessage("EXTN000007", getWF_NODE_IDColumnName());

      for (Iterator iter = getAttributes().iterator(); iter.hasNext(); ) {
        AttributeExtensible item = (AttributeExtensible) iter.next();
        if (getWF_NODE_IDColumnName().equals(item.getColumnName()))
          return new ErrorMessage("EXTN000007", getWF_NODE_IDColumnName());
      }
// 14803 ini
			if (isOptimisticLock() && getWF_NODE_IDColumnName().equals(ExtensibleEntityDataCollector.TIMESTAMP_COLUMN_NAME))
				return new ErrorMessage("EXTN000007", getWF_NODE_IDColumnName());
			for (Iterator iter = getComponents().iterator(); iter.hasNext(); ) {
				ExtensionEntityComponent item = (ExtensionEntityComponent) iter.next();
				List componentColumns = item.getComponentColumnsName();
				for (int i = 0; i < componentColumns.size(); i++){
					if (getWF_NODE_IDColumnName().equals((String)componentColumns.get(i))){
						return new ErrorMessage("EXTN000007", getWF_NODE_IDColumnName());
					}
				}
			}
// 14803 fine
    }
    return null;
  }

  public ErrorMessage checkWF_CLASS_IDColumnName()
  {
    if (hasThWorkflow() && getWF_CLASS_IDColumnName()==null)
      return new ErrorMessage("BAS0000000");
    if (hasThWorkflow())
    {
      if (getWF_IDColumnName()!=null && getWF_IDColumnName().equals(getWF_CLASS_IDColumnName()))
        return new ErrorMessage("EXTN000007", getWF_IDColumnName());

      if (getWF_NODE_IDColumnName()!=null && getWF_NODE_IDColumnName().equals(getWF_CLASS_IDColumnName()))
        return new ErrorMessage("EXTN000007", getWF_NODE_IDColumnName());

      if (getWF_SUB_NODE_IDColumnName()!=null && getWF_CLASS_IDColumnName().equals(getWF_SUB_NODE_IDColumnName()))
        return new ErrorMessage("EXTN000007", getWF_CLASS_IDColumnName());

      if (getThCommentColumnName()!=null && getWF_CLASS_IDColumnName().equals(getThCommentColumnName()))
        return new ErrorMessage("EXTN000007", getWF_CLASS_IDColumnName());

      for (Iterator iter = getAttributes().iterator(); iter.hasNext(); ) {
        AttributeExtensible item = (AttributeExtensible) iter.next();
        if (getWF_CLASS_IDColumnName().equals(item.getColumnName()))
          return new ErrorMessage("EXTN000007", getWF_CLASS_IDColumnName());
      }
// 14803 ini
			if (isOptimisticLock() && getWF_CLASS_IDColumnName().equals(ExtensibleEntityDataCollector.TIMESTAMP_COLUMN_NAME))
				return new ErrorMessage("EXTN000007", getWF_CLASS_IDColumnName());
			for (Iterator iter = getComponents().iterator(); iter.hasNext(); ) {
				ExtensionEntityComponent item = (ExtensionEntityComponent) iter.next();
				List componentColumns = item.getComponentColumnsName();
				for (int i = 0; i < componentColumns.size(); i++){
					if (getWF_CLASS_IDColumnName().equals((String)componentColumns.get(i))){
						return new ErrorMessage("EXTN000007", getWF_CLASS_IDColumnName());
					}
				}
			}
// 14803 fine
    }
    return null;
  }

  public ErrorMessage checkWF_SUB_NODE_IDColumnName()
  {
    if (hasThWorkflow() && isThWorkflowExtended() && getWF_SUB_NODE_IDColumnName()==null)
      return new ErrorMessage("BAS0000000");
    if (hasThWorkflow() && isThWorkflowExtended())
    {
      if (getWF_IDColumnName()!=null && getWF_IDColumnName().equals(getWF_SUB_NODE_IDColumnName()))
        return new ErrorMessage("EXTN000007", getWF_SUB_NODE_IDColumnName());

      if (getWF_NODE_IDColumnName()!=null && getWF_NODE_IDColumnName().equals(getWF_SUB_NODE_IDColumnName()))
        return new ErrorMessage("EXTN000007", getWF_SUB_NODE_IDColumnName());

      if (getWF_CLASS_IDColumnName()!=null && getWF_CLASS_IDColumnName().equals(getWF_SUB_NODE_IDColumnName()))
        return new ErrorMessage("EXTN000007", getWF_CLASS_IDColumnName());

      if (getThCommentColumnName()!=null && getWF_SUB_NODE_IDColumnName().equals(getThCommentColumnName()))
        return new ErrorMessage("EXTN000007", getWF_SUB_NODE_IDColumnName());

      for (Iterator iter = getAttributes().iterator(); iter.hasNext(); ) {
        AttributeExtensible item = (AttributeExtensible) iter.next();
        if (getWF_SUB_NODE_IDColumnName().equals(item.getColumnName()))
          return new ErrorMessage("EXTN000007", getWF_SUB_NODE_IDColumnName());
      }
// 14803 ini
			if (isOptimisticLock() && getWF_SUB_NODE_IDColumnName().equals(ExtensibleEntityDataCollector.TIMESTAMP_COLUMN_NAME))
				return new ErrorMessage("EXTN000007", getWF_SUB_NODE_IDColumnName());
			for (Iterator iter = getComponents().iterator(); iter.hasNext(); ) {
				ExtensionEntityComponent item = (ExtensionEntityComponent) iter.next();
				List componentColumns = item.getComponentColumnsName();
				for (int i = 0; i < componentColumns.size(); i++){
					if (getWF_SUB_NODE_IDColumnName().equals((String)componentColumns.get(i))){
						return new ErrorMessage("EXTN000007", getWF_SUB_NODE_IDColumnName());
					}
				}
			}
// 14803 fine
    }
    return null;
  }

  public ErrorMessage checkThCommentColumnName()
  {
    if (hasThComment() && getThCommentColumnName()==null)
      return new ErrorMessage("BAS0000000");
    if (hasThComment())
    {
      if (getWF_IDColumnName()!=null && getWF_IDColumnName().equals(getThCommentColumnName()))
        return new ErrorMessage("EXTN000007", getThCommentColumnName());

      if (getWF_NODE_IDColumnName()!=null && getWF_NODE_IDColumnName().equals(getThCommentColumnName()))
        return new ErrorMessage("EXTN000007", getThCommentColumnName());

      if (getWF_CLASS_IDColumnName()!=null && getWF_CLASS_IDColumnName().equals(getThCommentColumnName()))
        return new ErrorMessage("EXTN000007", getWF_CLASS_IDColumnName());

      if (getWF_SUB_NODE_IDColumnName()!=null && getWF_SUB_NODE_IDColumnName().equals(getThCommentColumnName()))
        return new ErrorMessage("EXTN000007", getWF_SUB_NODE_IDColumnName());

      for (Iterator iter = getAttributes().iterator(); iter.hasNext(); ) {
        AttributeExtensible item = (AttributeExtensible) iter.next();
        if (getThCommentColumnName().equals(item.getColumnName()))
          return new ErrorMessage("EXTN000007", getThCommentColumnName());
      }
// 14803 ini
			if (isOptimisticLock() && getThCommentColumnName().equals(ExtensibleEntityDataCollector.TIMESTAMP_COLUMN_NAME))
				return new ErrorMessage("EXTN000007", getThCommentColumnName());
			for (Iterator iter = getComponents().iterator(); iter.hasNext(); ) {
				ExtensionEntityComponent item = (ExtensionEntityComponent) iter.next();
				List componentColumns = item.getComponentColumnsName();
				for (int i = 0; i < componentColumns.size(); i++){
					if (getThCommentColumnName().equals((String)componentColumns.get(i))){
						return new ErrorMessage("EXTN000007", getThCommentColumnName());
					}
				}
			}
// 14803 fine
    }
    return null;
  }

  public ErrorMessage checkThermWorkflowNlsName()
  {
    if (hasThWorkflow() && getThermWorkflowNlsName() == null)
      return new ErrorMessage("BAS0000000");
    return null;
  }

  public ErrorMessage checkThermWorkflowNlsColumn()
  {
    if (hasThWorkflow() && getThermWorkflowNlsColumn() == null)
      return new ErrorMessage("BAS0000000");
    return null;
  }

  public ErrorMessage checkThermCommentNlsName()
  {
    if (hasThComment() && getThermCommentNlsName() == null)
      return new ErrorMessage("BAS0000000");
    return null;
  }

  public ErrorMessage checkThermCommentNlsColName()
  {
    if (hasThComment() && getThermCommentNlsColName() == null)
      return new ErrorMessage("BAS0000000");
    return null;
  }

  public ErrorMessage checkThermNlsNlsName()
  {
  	//23811 inizio
  	if(!isEnableNlsTab()){
  		return null;
  	}
  	//23811 fine
  	
    if (hasThNlsComponent() && getThermNlsNlsName() == null)
      return new ErrorMessage("BAS0000000");
    return null;
  }

  public ErrorMessage checkThermNlsTitleName()
  {
  	//23811 inizio
  	if(!isEnableNlsTab()){
  		return null;
  	}
  	//23811 fine

    if (hasThNlsComponent() && getThermNlsTitleName() == null)
      return new ErrorMessage("BAS0000000");
    return null;
  }

  public ErrorMessage checkThNlsPlural()
  {
  	//23811 inizio
  	if(!isEnableNlsTab()){
  		return null;
  	}
  	//23811 fine
    if (hasThNlsComponent() && getThNlsPlural() == null)
      return new ErrorMessage("BAS0000000");
// 14803 ini
		if (hasThNlsComponent())
		{
//Fix 15501 inizio
			if (Character.isLowerCase(getThNlsPlural().charAt(0))){
				return new ErrorMessage("EXTN000017");
			}
//Fix 15501 fine
			if (getThermCommentName()!=null && getThNlsPlural().equals(getThermCommentName()))
				return new ErrorMessage("EXTN000006", getThNlsPlural());

			if (getThWorkflowName()!=null && getThWorkflowName().equals(getThNlsPlural()))
				return new ErrorMessage("EXTN000006", getThNlsPlural());

			for (Iterator iter = getAttributes().iterator(); iter.hasNext(); ) {
				AttributeExtensible item = (AttributeExtensible) iter.next();
				if (getThNlsPlural().equals(item.getAttributeName()))
					return new ErrorMessage("EXTN000006", getThNlsPlural());
			}

			for (Iterator iter = getRelations().iterator(); iter.hasNext(); ) {
				ExtensionEntityRelation item = (ExtensionEntityRelation) iter.next();
				if (getThNlsPlural().equals(item.getRelationName()))
					return new ErrorMessage("EXTN000006", getThNlsPlural());
			}
			if (isOptimisticLock() && getThNlsPlural().equals(ExtensibleEntityDataCollector.TIMESTAMP_ATTRIBUTE_NAME)){
				return new ErrorMessage("EXTN000006", getThNlsPlural());
			}
			if (getThNlsName()!=null && getThNlsName().equals(getThNlsPlural())){
				return new ErrorMessage("EXTN000006", getThNlsPlural());
			}
			for (Iterator iter = getComponents().iterator(); iter.hasNext(); ) {
				ExtensionEntityComponent item = (ExtensionEntityComponent) iter.next();
				if (getThNlsPlural().equals(item.getComponentName()))
					return new ErrorMessage("EXTN000006", getThNlsPlural());
			}
			for (Iterator iter = getManyToManyList().iterator(); iter.hasNext(); ) {
				CollectionExtensionEntity item = (CollectionExtensionEntity) iter.next();
				if (getThNlsPlural().equals(item.getCollectionName()))
					return new ErrorMessage("EXTN000006", getThNlsPlural());
			}
			for (Iterator iter = getOneToManyList().iterator(); iter.hasNext(); ) {
				ExtensibleEntity item = (ExtensibleEntity) iter.next();
				if (getThNlsPlural().equals(item.getCollectionName()))
					return new ErrorMessage("EXTN000006", getThNlsPlural());
			}
		}
// 14803 fine
    return null;
  }

  public ErrorMessage checkThNlsSchemaName()
  {
  	//23811 inizio
  	if(!isEnableNlsTab()){
  		return null;
  	}
  	//23811 fine
    if (hasThNlsComponent() && getThNlsSchemaName() == null)
      return new ErrorMessage("BAS0000000");
    return null;
  }

  public ErrorMessage checkThNlsTableName()
  {
  	//23811 inizio
  	if(!isEnableNlsTab()){
  		return null;
  	}
  	//23811 fine
    if (hasThNlsComponent() && getThNlsTableName() == null)
      return new ErrorMessage("BAS0000000");
    return null;
  }
  
  //23811 inizio
  public ErrorMessage checkAreaExt() {
  	if((getModalitaExt() != MODALITA_EXT_STANDARD) && (getAreaExt() == null || getAreaExt().equals(""))){
  		return new ErrorMessage("BAS0000000");  		
  	}
    return null;
  }
  
  public String getWorkFixPath(){
	  TableBuilder generator = (TableBuilder)Factory.createObject(TableBuilder.class);
	    try {
	    	generator.init(this,null);
			String workTDDMLPath = generator.getWorkTDDMLPath(); 
			if(workTDDMLPath != null && !workTDDMLPath.equals("")){
				File tddmlFolder = new File(workTDDMLPath);
				if(tddmlFolder != null && tddmlFolder.exists() && tddmlFolder.isDirectory())
					return tddmlFolder.getParentFile().getCanonicalPath();
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	  return null;
  }
  
  public ErrorMessage checkVisualNumType(){
	  if(getNumertatorType() == THERM_NUMERATOR && getClassName().length() > 18) {
		  return new ErrorMessage("EXTN000035");
	  }
	  return null;
  }
  //23811 fine

  public AttributeExtensible getAttributeFromThermComponent(ExtensionColumnOrder column) {
    String attributeRefId = null;
    String compName = column.getAttributeName().substring(0,column.getAttributeName().indexOf("."));
    if (hasThWorkflow() && compName.equals(getThWorkflowName()))
    {
      if (column.getColumnName().equals(getWF_IDColumnName()))
        attributeRefId = "WfId";
      if (column.getColumnName().equals(getWF_CLASS_IDColumnName()))
        attributeRefId = "WfClassId";
      if (column.getColumnName().equals(getWF_NODE_IDColumnName()))
        attributeRefId = "WfNodeId";
      if (column.getColumnName().equals(getWF_SUB_NODE_IDColumnName()))
        attributeRefId = "WfNodeId";
    }
    else
    if (hasThComment() && compName.equals(getThermCommentName()))
    {
      if (column.getColumnName().equals(getThCommentColumnName()))
        attributeRefId = "CommentHandlerId";
    }
    if (attributeRefId!=null)
    {
      AttributeExtensible result = (AttributeExtensible)Factory.createObject(AttributeExtensible.class);
      result.setEstensioneEntita(this);
      result.setAttributeName(column.getAttributeName());
      result.setAttributeReadOnly(false);
      result.setAttributeReferenceId(attributeRefId);
      result.setColumnName(column.getColumnName());
      result.setColumnPosition(column.getPosition());
      result.setColumnTitleNLS(column.getColumnName());
      result.setDefaultValue(null);
      result.setMandatory(false);
      result.setNLSName(column.getColumnName());
      result.setPresentInGrid(true);
      return result;
    }
    return null;
  }

  public AttributeExtensible getTimestampAttribute()
  {
    if (!isOptimisticLock() || iTimestampAttribute != null)
      return null;
    if (iTimestampAttribute == null)
    {
      iTimestampAttribute = (AttributeExtensible)Factory.createObject(AttributeExtensible.class);
      iTimestampAttribute.setAttributeName(ExtensibleEntityDataCollector.TIMESTAMP_ATTRIBUTE_NAME);
      iTimestampAttribute.setAttributeReadOnly(false);
      iTimestampAttribute.setAttributeReferenceId("Timestamp");
      iTimestampAttribute.setColumnName(ExtensibleEntityDataCollector.TIMESTAMP_COLUMN_NAME);
      iTimestampAttribute.setColumnPosition(findColumnPosition(ExtensibleEntityDataCollector.TIMESTAMP_COLUMN_NAME));
      iTimestampAttribute.setColumnTitleNLS("Timestamp");
      iTimestampAttribute.setExtensibleEntityId(getId());
      iTimestampAttribute.setKeyPosition(0);
      iTimestampAttribute.setMandatory(true);
      iTimestampAttribute.setNLSName("Timestamp");
      iTimestampAttribute.setPresentInGrid(false);
      iTimestampAttribute.setPosition(0);
    }
    return iTimestampAttribute;
  }

  /**
   * findColumnPosition
   *
   * @param string String
   * @return int
   */
  public int findColumnPosition(String columnName) {
    for (int i = 0; i < getOrderColumns().size(); i++) {
      if (((ExtensionColumnOrder)getOrderColumns().get(i)).getColumnName().equals(columnName))
        return ((ExtensionColumnOrder)getOrderColumns().get(i)).getPosition();
    }
    return -1;
  }
// 13733 fine
// 13734 ini
  public AttributeExtensible getAttributeFromComponent(ExtensionColumnOrder column)
  {
    String attributeName = column.getAttributeName();
    if (attributeName.indexOf(".") < 0)
      return null;
    String componentName = attributeName.substring(0,attributeName.indexOf("."));//13735
    attributeName = attributeName.substring(attributeName.indexOf(".")+1);
    for (Iterator iter = getComponents().iterator(); iter.hasNext(); ) {
      ExtensionEntityComponent item = (ExtensionEntityComponent) iter.next();
/* 13735 ini
        PersistentClassHdr hdr = item.getClassHDR();
        for (Iterator iterAttribute = hdr.getAttributes().iterator(); iterAttribute.hasNext(); ) {
          PersistentClassAd itemAttribute = (PersistentClassAd) iterAttribute.next();
          if (itemAttribute.getAttributeName().equals(attributeName))
*/
      if (item.getComponentName().equals(componentName))
      {
        PersistentClassAd itemAttribute = findAttributeWithinHDR(item.getClassHDR(),attributeName);
        if (itemAttribute!=null)
        {
//13735 fine
          return createAttributeExtensibleFromAD(column,itemAttribute);
        }
      }
    }
    return null;
  }

  /**
   * findAttributeWithinHDR
   * Fix 13735
   * @param persistentClassHdr PersistentClassHdr
   * @param attributeName String
   * @return PersistentClassAd
   */
  public PersistentClassAd findAttributeWithinHDR(PersistentClassHdr persistentClassHdr, String attributeName) {
    for (Iterator iter = persistentClassHdr.getAttributes().iterator(); iter.hasNext(); ) {
      PersistentClassAd item = (PersistentClassAd) iter.next();
      if (item.getBOAttributeName().equals(attributeName))
        return item;
    }
    PersistentClassAd result = null;
    for (Iterator iter = persistentClassHdr.getComponents().iterator(); iter.hasNext(); ) {
      PersistentClassCD item = (PersistentClassCD) iter.next();
      result = findAttributeWithinHDR(item.getCorrelatedClassHdr(),attributeName);
      if (result != null)
        return result;
    }
    if (persistentClassHdr.getFatherClassHdr()!=null)
    {
      result = findAttributeWithinHDR(persistentClassHdr.getFatherClassHdr(),attributeName);
      if (result != null)
        return result;
    }
    return null;
  }

  /**
   * createAttributeExtensibleFromAD
   * 13734
   * @param column ExtensionColumnOrder
   * @param itemAttribute PersistentClassAd
   * @return AttributeExtensible
   */
  public AttributeExtensible createAttributeExtensibleFromAD(ExtensionColumnOrder column, PersistentClassAd itemAttribute) {
    AttributeExtensible result = (AttributeExtensible)Factory.createObject(AttributeExtensible.class);
    result.setAttributeName(column.getAttributeName());
    result.setAttributeReadOnly(itemAttribute.isReadOnly());
    result.setAttributeReferenceId(itemAttribute.getAttributeRefKey());
    result.setColumnName(column.getColumnName());
    result.setColumnPosition(column.getPosition());
    result.setColumnTitleNLS(itemAttribute.getDefaultClassAdNls().getNlsColumnTitle());
    result.setEstensioneEntita(this);
    result.setKeyPosition(0);
    result.setMandatory(itemAttribute.isDftMandatory());
    result.setNLSLabel(itemAttribute.getDefaultClassAdNls().getNlsLabel());
    result.setNLSName(itemAttribute.getDefaultClassAdNls().getNlsAttributeName());
    result.setPosition(column.getPosition());
    result.setPresentInGrid(true);
    return result;
  }
// 13734 fine
// 13736 ini
/*  public void fillSchemaName(DynamicEnumType type){
    Hashtable table = IniFile.getSection("Schema");
    for (Enumeration iter = table.keys(); iter.hasMoreElements(); ) {
      String item = (String) iter.nextElement();
      type.addValue(item,item);
    }
  }

  public void fillTableSpace(DynamicEnumType type){
    Hashtable table = IniFile.getSection("Tablespace");
    for (Enumeration iter = table.keys(); iter.hasMoreElements(); ) {
      String item = (String) iter.nextElement();
      type.addValue(item,item);
    }
  }
*/
  public ErrorMessage checkInheritedClasseName()
  {
    if (getInheritedClasseName()!=null)
    {
      Class c = null;
      try {
        c = Class.forName(getInheritedClasseName());
      }
      catch (ClassNotFoundException ex) {
        return new ErrorMessage("CBS000002");
      }
      if ( c != null && !PersistentObject.class.isAssignableFrom(c))
        return new ErrorMessage("EXTN000011","com.thera.thermfw.persist.PersistentObject");
    }
    return null;
  }

  public ErrorMessage checkDOList()
  {
    if (getDOList()!=null)
    {
      Class c = null;
      try {
        c = Class.forName(getDOList());
      }
      catch (ClassNotFoundException ex) {
        return new ErrorMessage("CBS000002");
      }
      if ( c != null && !com.thera.thermfw.gui.cnr.DOList.class.isAssignableFrom(c))
        return new ErrorMessage("EXTN000011","com.thera.thermfw.gui.cnr.DOList");
    }
    return null;
  }

  public ErrorMessage checkActionAdapter()
  {
    if (getActionAdapter()!=null){
      Class c = null;
      try {
        c = Class.forName(getActionAdapter());
      }
      catch (ClassNotFoundException ex) {
        return new ErrorMessage("CBS000002");
      }
      if ( c != null && !com.thera.thermfw.web.servlet.GridActionAdapter.class.isAssignableFrom(c))
        return new ErrorMessage("EXTN000011","com.thera.thermfw.web.servlet.GridActionAdapter");
    }
    return null;
  }
/*  public void fillThNlsSchemaName(DynamicEnumType type){
    Hashtable table = IniFile.getSection("Schema");
    for (Enumeration iter = table.keys(); iter.hasMoreElements(); ) {
      String item = (String) iter.nextElement();
      type.addValue(item,item);
    }
  }*/
  public void setFolder(FolderNls folder)	{
		iFolder.setObject(folder);
	}

	public FolderNls getFolder()	{
		return (FolderNls)iFolder.getObject();
	}

	public void setFolderId(String id)	{
		iFolder.setKey(KeyHelper.replaceTokenObjectKey(iFolder.getKey(), 1, id));
	}

	public String getFolderId()	{
		return KeyHelper.getTokenObjectKey(iFolder.getKey(), 1);
	}

	public void setFolderLanguage(String langId){
		iFolder.setKey(KeyHelper.replaceTokenObjectKey(iFolder.getKey(), 2, langId));
	}

	public String getFolderLanguage(){
		return KeyHelper.getTokenObjectKey(iFolder.getKey(), 2);
	}

	public void setFolderKey(String key) {
		iFolder.setKey(key);
	}

	public String getFolderKey(){
    return iFolder.getKey();
  }

	public void setUserFolder(UserMenuFolder folder)	{
		iUserFolder.setObject(folder);
	}

	public UserMenuFolder getUserFolder()	{
		return (UserMenuFolder)iUserFolder.getObject();
	}

	public void setUserMenuFolderId(String id)	{
		iUserFolder.setKey(KeyHelper.replaceTokenObjectKey(iUserFolder.getKey(),2,id));
	}

	public String getUserMenuFolderId()	{
		return KeyHelper.getTokenObjectKey(iUserFolder.getKey(),2);
	}

	public void setMenuFolderUserId(String id)	{
		iUserFolder.setKey(KeyHelper.replaceTokenObjectKey(iUserFolder.getKey(),1,id));
	}

	public String getMenuFolderUserId()	{
//		return getCurrentPropertiesFileName()== null ? "ADMIN" : ResourceLoader.getString(getCurrentPropertiesFileName(),"UserMenuFolder") == null ? "ADMIN" : ResourceLoader.getString(getCurrentPropertiesFileName(),"UserMenuFolder");14258
//		return KeyHelper.getTokenObjectKey(iUserFolder.getKey(),1);
		ExtensionPathLoader loader = (ExtensionPathLoader)Factory.createObject(ExtensionPathLoader.class);
		return loader.getExtensionUser();
	}

	public void setUserFolderKey(String key) {
		iUserFolder.setKey(key);
	}

	public String getUserFolderKey(){
		return iUserFolder.getKey();
	}

	public String getCurrentPropertiesFileName() {
		String defaultResources = null;
		if (getFrameworkDataOwnerId()!=null){
			defaultResources = ExtensibleEntity.PROPERTIE_FILE + SystemParam.getSchema(getFrameworkDataOwnerId()).substring(0,SystemParam.getSchema(getFrameworkDataOwnerId()).length()-1);
			try {
				ResourceBundle.getBundle(defaultResources);
			}
			catch (MissingResourceException ex){
				defaultResources = ExtensibleEntity.PROPERTIE_FILE + "THERA";
			}
		}
		else{
			defaultResources = ExtensibleEntity.PROPERTIE_FILE + "THERA";
		}
		try {
			ResourceBundle.getBundle(defaultResources);
			return defaultResources;
		}
		catch (MissingResourceException ex){
			return null;
		}
	}
	//14803 ini
	public ErrorMessage checkCollectionName(){
		if (!isChild()){
			return null;
		}
		if (getCollectionName() == null || getCollectionName().trim().length() == 0) {
			return new ErrorMessage("BAS0000000");
		}
		if (getCollectionName().equals(ExtensibleEntityDataCollector.TIMESTAMP_ATTRIBUTE_NAME))
			return new ErrorMessage("EXTN000008");
//Fix 15501 inizio
		if (Character.isLowerCase(getCollectionName().charAt(0))){
			return new ErrorMessage("EXTN000017");
		}
//Fix 15501 fine
		return null;
	}

	public ErrorMessage checkClassName(){
//Fix 15501 inizio
		if (getClassName() == null || getClassName().trim().length() == 0) {
			return new ErrorMessage("BAS0000000");
		}
    if (Character.isLowerCase(getClassName().charAt(0))) {
      return new ErrorMessage("EXTN000017");
    }
//Fix 15501 fine
//Fix 15802 inizio
		JavaIdentifierStringType javaIdentifierStringType = (JavaIdentifierStringType)Factory.createObject(JavaIdentifierStringType.class);
		javaIdentifierStringType.setSize(getClassName().length());
		ErrorMessage em = javaIdentifierStringType.validate(getClassName());
		if (em != null){
			return em;
		}
//Fix 15802 fine
		//Fix 22996 inizio
		//32823 inizio
		//spostao nel metodo checkEntityId
		/*
		if (!isOnDB()){
			try {
				String entityId = getClassName().length() > 15 ? getClassName().substring(0,15) : getClassName();
				Entity entity = Entity.elementWithKey(entityId,PersistentObject.NO_LOCK);
				if (entity != null){
					return new ErrorMessage("EXTN000034", getEntityId());//32823
		    }
			}
			catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}			
		}
		*/
		//32823 fine
		//Fix 22996 fine
		//if (!isOnDB() || (isOnDB() && ((iOldExtension.getClassName() == null && getClassName()!= null) || !iOldExtension.getClassName().equals(getClassName())))){//23811
		if (!isOnDB() || (isOnDB() && (iOldExtension != null) && ((iOldExtension.getClassName() == null && getClassName()!= null) || !iOldExtension.getClassName().equals(getClassName())))){//23811
			try {
				ClassADCollectionManager.collectionWithName(getClassName());
				return new ErrorMessage("EXTN000015", getClassName());
			} 
			catch (Exception ex) {
			  //33720 inizio
			  //return null;
			  return checkReplacements();
			  //33720 fine
			}
		}
		return null;
	}

	public ExtensibleEntity getOldExtension(){
		return iOldExtension;
	}

	public void setOldExtension(ExtensibleEntity old){
		iOldExtension = old;
	}

	public Integer getNextChildId(){
		Integer max = new Integer(0);
		for (Iterator iter = getOneToManyList().iterator(); iter.hasNext(); ) {
      ExtensibleEntity item = (ExtensibleEntity) iter.next();
      if (item.getId().compareTo(max) > 0) {
        max = item.getId();
      }
    }
		return new Integer(max.intValue() + 1);
	}

	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = super.checkAll(components);
    try {
      ErrorMessage em = chekTablesConflict(components);
      if (em != null) {
        errors.add(em);
      }   
//15800 inizio
			if (isProcessing()){
        Vector otherErrors = checkAttributeRefereces();
        if (otherErrors != null && !otherErrors.isEmpty()) {
          errors.addAll(otherErrors);
        }
				otherErrors = checkAllClassHdr();
				if (otherErrors != null && !otherErrors.isEmpty()) {
					errors.addAll(otherErrors);
				}
				em = checkFolderId();
				if (em != null) {
					errors.add(em);
				}
				em = checkUserMenuFolderId();
				if (em != null) {
					errors.add(em);
				}
				setProcessing(false);
				for (Iterator iter = getOneToManyList().iterator(); iter.hasNext(); ) {
					ExtensibleEntity item = (ExtensibleEntity) iter.next();
					item.setProcessing(true);
					otherErrors = item.checkAttributeRefereces();
					if (otherErrors != null && !otherErrors.isEmpty()) {
						errors.addAll(otherErrors);
					}
					otherErrors = item.checkAllClassHdr();
					if (otherErrors != null && !otherErrors.isEmpty()) {
						errors.addAll(otherErrors);
					}
					item.setProcessing(false);
				}
      }
//15800 fine
    }
    catch (NoSuchElementException ex) {
    }
    catch (NoSuchFieldException ex) {
    }
		return new Vector();
	}

  public ErrorMessage chekTablesConflict(BaseComponentsCollection components) throws NoSuchFieldException, NoSuchElementException {
		ClassADCollection cad = ClassADCollectionManager.collectionWithName("ExtensibleEntity");
		String tableNameNlsAttribute = cad.getAttribute("TableName").getAttributeNameNLS();
		String thNlsTableNameNlsAttribute = cad.getAttribute("NlsTableName").getAttributeNameNLS();
		cad = ClassADCollectionManager.collectionWithName("CollectionExtensionEntity");
		String collectionTableNameNlsAttribute = cad.getAttribute("TableName").getAttributeNameNLS();
		if (getThNlsTableName() != null){//compare nlsTableName with tableName, oneToMany tables name, manyToMany tableName
			if (getTableName()!= null && getTableName().equals(getThNlsTableName()))
				return new ErrorMessage("EXTN000016", new String[]{getThNlsTableName(),tableNameNlsAttribute});
			for (Iterator iter = getOneToManyList().iterator(); iter.hasNext(); ) {
				ExtensibleEntity item = (ExtensibleEntity) iter.next();
				if (item.getTableName() != null && item.getTableName().equals(getThNlsTableName()))
					return new ErrorMessage("EXTN000016", new String[]{getThNlsTableName(), item.getChildNlsName() + "." + tableNameNlsAttribute});
				if (item.getThNlsTableName() != null && item.getThNlsTableName().equals(getThNlsTableName()))
					return new ErrorMessage("EXTN000016", new String[]{getThNlsTableName(), item.getChildNlsName() + "." + thNlsTableNameNlsAttribute});
			}
			for (Iterator iter = getManyToManyList().iterator(); iter.hasNext(); ) {
				CollectionExtensionEntity item = (CollectionExtensionEntity) iter.next();
				if (item.getTableName()!=null && item.getTableName().equals(getThNlsTableName()))
					return new ErrorMessage("EXTN000016", new String[]{getThNlsTableName(), item.getCollectionName() + "." + collectionTableNameNlsAttribute});
			}
		}
		if (getTableName() != null){//compare tableName with oneToMany tableName and manyToMany tableName
			for (Iterator iter = getOneToManyList().iterator(); iter.hasNext(); ) {
				ExtensibleEntity item = (ExtensibleEntity) iter.next();
				if (item.getTableName() != null && item.getTableName().equals(getTableName()))
					return new ErrorMessage("EXTN000016", new String[]{getTableName(), item.getChildNlsName() + "." + tableNameNlsAttribute});
				if (item.getThNlsTableName() != null && item.getThNlsTableName().equals(getTableName()))
					return new ErrorMessage("EXTN000016", new String[]{getTableName(), item.getChildNlsName() + "." + thNlsTableNameNlsAttribute});
			}
			for (Iterator iter = getManyToManyList().iterator(); iter.hasNext(); ) {
				CollectionExtensionEntity item = (CollectionExtensionEntity) iter.next();
				if (item.getTableName()!=null && item.getTableName().equals(getTableName()))
					return new ErrorMessage("EXTN000016", new String[]{getTableName(), item.getCollectionName() + "." + collectionTableNameNlsAttribute});
			}
		}
		if (getOneToManyList()!=null && !getOneToManyList().isEmpty()){
			for (Iterator iter = getOneToManyList().iterator(); iter.hasNext(); ) {
				ExtensibleEntity item = (ExtensibleEntity) iter.next();
				String name = item.getTableName();
				String nlsName = item.getThNlsTableName();
				for (Iterator iter1 = getOneToManyList().iterator(); iter1.hasNext(); ) {
					ExtensibleEntity item1 = (ExtensibleEntity) iter1.next();
					if (!item.equals(item1)){
						if (item1.getTableName()!=null && item1.getTableName().equals(name))
							return new ErrorMessage("EXTN000016", new String[]{name, item.getChildNlsName() + "." + tableNameNlsAttribute});
						if (item1.getThNlsTableName()!=null && item1.getThNlsTableName().equals(name))
							return new ErrorMessage("EXTN000016", new String[]{name, item.getChildNlsName() + "." + tableNameNlsAttribute});
						if (nlsName != null){
							if (item1.getTableName()!=null && item1.getTableName().equals(nlsName))
								return new ErrorMessage("EXTN000016", new String[]{nlsName, item.getChildNlsName() + "." + thNlsTableNameNlsAttribute});
							if (item1.getThNlsTableName()!=null && item1.getThNlsTableName().equals(name))
								return new ErrorMessage("EXTN000016", new String[]{nlsName, item.getChildNlsName() + "." + thNlsTableNameNlsAttribute});
						}
					}
				}
				for (Iterator iter2 = getManyToManyList().iterator(); iter2.hasNext(); ) {
					CollectionExtensionEntity item2 = (CollectionExtensionEntity) iter2.next();
					if (item2.getTableName()!=null && item2.getTableName().equals(name))
						return new ErrorMessage("EXTN000016", new String[]{name, item.getChildNlsName() + "." + tableNameNlsAttribute});
					if (nlsName != null && item2.getTableName()!=null && item2.getTableName().equals(nlsName))
						return new ErrorMessage("EXTN000016", new String[]{nlsName, item.getChildNlsName() + "." + thNlsTableNameNlsAttribute});
				}
			}
		}
		if (getManyToManyList()!=null && !getManyToManyList().isEmpty()){
			for (Iterator iter = getManyToManyList().iterator(); iter.hasNext(); ) {
				CollectionExtensionEntity item = (CollectionExtensionEntity) iter.next();
				String name = item.getTableName();
				for (Iterator iter1 = getManyToManyList().iterator(); iter1.hasNext(); ) {
					CollectionExtensionEntity item1 = (CollectionExtensionEntity) iter1.next();
					if (!item.equals(item1)){
						if (item1.getTableName() !=null && item1.getTableName().equals(name))
							return new ErrorMessage("EXTN000016", new String[]{name, item.getCollectionName() + "." + collectionTableNameNlsAttribute});
					}
				}
			}
		}
		return null;
	}

	public void toSQL(SQLExporterStructure exporter, String xml)
	{
		xml = Utils.replace(xml, "<Id>" + getId() + "</Id>", "<Id><LAST_NUM><EXTEN_ENTITY></Id>");
		xml = Utils.replace(xml, "<ExtensibleEntityId>" + getId() + "</ExtensibleEntityId>", "<ExtensibleEntityId><LAST_NUM><EXTEN_ENTITY></ExtensibleEntityId>");
		xml = Utils.replace(xml, "<ExtensionEntityId>" + getId() + "</ExtensionEntityId>", "<ExtensionEntityId><LAST_NUM><EXTEN_ENTITY></ExtensionEntityId>");//Fix 15433
		String key = "EXTEN_ENTITY";
		StringBuffer line = new StringBuffer();
		line.append("(");
		line.append("<NUM><EXTEN_ENTITY>");
		line.append(", ");
		line.append(exporter.getSQLString(getDescription()));
		line.append(", ");
		line.append(exporter.getSQLString(getFrameworkDataOwnerId()));
		line.append(", ");
		line.append(exporter.getSQLString(String.valueOf(getGenrationMode())));
		line.append(", ");
		line.append(exporter.getSQLString(String.valueOf(getExtensionType())));
		line.append(", ");
		line.append(exporter.getSQLString(getExtensionClassName()));
		line.append(", ");
		line.append("''");//Fix 15433
//		line.append(exporter.getSQLLongTextString(xml));Fix 15433
		line.append(")");
		exporter.addElement(key, line.toString());
// Fix 15433 inizio
		List partialExtensions = null;
		while (xml.indexOf("<LAST_NUM><EXTEN_ENTITY>") >= 0){
			partialExtensions = split(xml.substring(0,xml.indexOf("<LAST_NUM><EXTEN_ENTITY>")));
			createPartialExporter(exporter, partialExtensions);
			xml = xml.substring(xml.indexOf("<LAST_NUM><EXTEN_ENTITY>")+"<LAST_NUM><EXTEN_ENTITY>".length());
			exporter.addElement("PARTIAL_EXTENSION", exporter.getSQLLongTextString("<LAST_NUM><EXTEN_ENTITY>"));
		}
		partialExtensions = split(xml);
		createPartialExporter(exporter, partialExtensions);
//Fix 15433 fine
	}
	//14803 fine
//Fix 15433 inizio
	public void createPartialExporter(SQLExporterStructure exporter, List partialXml){
		for(int idx = 0; idx < partialXml.size(); idx++){
			exporter.addElement("PARTIAL_EXTENSION", exporter.getSQLLongTextString((String)partialXml.get(idx)));
		}
	}

	public List split(String xml){
		List ret = new ArrayList();
		int index = 0;
		while (index <  xml.length()){
			String element = null;
			if ((index + MAX_SQL_STRING_LENGHT) < xml.length()){
				element = xml.substring(index,index + MAX_SQL_STRING_LENGHT);
			}
			else{
				element = xml.substring(index);
			}
			ret.add(element);
			index += MAX_SQL_STRING_LENGHT;
		}
		return ret;
	}
  //Fix 15433 fine
	/**
	 * isOnlyDatabaseInformation
	 * Fix 15587
	 * @param errors Vector
	 * @return boolean
	 */
	public static boolean isOnlyDatabaseInformation(Vector errors) {
		if (errors != null && errors.size() == 1){
			String idError = ((ErrorMessage)errors.get(0)).getId();
			return idError.equals("EXTN000018") || idError.equals("EXTN000019");
		}
		return false;
	}
	/**
	 * isOnlyDatabaseInformation
	 * Fix 15587
	 * @param errors Vector
	 * @return boolean
	 */
	public static boolean isOnlyDatabaseError(Vector errors) {
		if (errors != null && errors.size() == 1){
			String idError = ((ErrorMessage)errors.get(0)).getId();
			return idError.equals("EXTN000021");
		}
		return false;
	}

  /**
   * checkSourcePath
   * Fix 15802
   * @return ErrorMessage
   */
  public ErrorMessage checkSourcePath(){
		if (getSourcePath() == null || getSourcePath().length() == 0)
			return new ErrorMessage("BAS0000000");
		if (getSourcePath().charAt(0) == '.' || getSourcePath().charAt(getSourcePath().length()-1) == '.' || getSourcePath().indexOf("..") >=0)
			return new ErrorMessage("EXTN000023");
		StringTokenizer folderPackage = new StringTokenizer(getSourcePath(),".");
		if (folderPackage.countTokens() == 0){
			return new ErrorMessage("EXTN000023");
		}
		JavaIdentifierStringType javaIdentifierStringType = (JavaIdentifierStringType)Factory.createObject(JavaIdentifierStringType.class);
		javaIdentifierStringType.setSize(256);
		while (folderPackage.hasMoreTokens()){
			String tocken = folderPackage.nextToken();
			if ((tocken == null) || (tocken.length() == 0) || (javaIdentifierStringType.validate(tocken) != null))
				return new ErrorMessage("EXTN000023");
		}
		return null;
	}

// Fix 15800 inizio
	protected boolean processing = false;

	public boolean isProcessing() {
		return processing;
	}

	public void setProcessing(boolean active){
		processing = active;
	}

	public Vector checkAttributeRefereces() {
		Vector errors = new Vector();
		for (Iterator iter = getAttributes().iterator(); iter.hasNext(); ) {
			AttributeExtensible item = (AttributeExtensible) iter.next();
			try {
				AttributeRefExtension refExtended = (AttributeRefExtension) AttributeRefExtension.elementWithKey(AttributeRefExtension.class, item.getAttributeReferenceKey(), PersistentObject.NO_LOCK);
				if (refExtended == null) {
					errors.add(new ErrorMessage("EXTN000025",new String[]{item.getAttributeReferenceKey(), item.getAttributeName(), item.getNLSName()}));
				}
			}
			catch (SQLException ex) {
				errors.add(new ErrorMessage("BAS0000071",item.getAttributeReferenceKey() + ":" + ex.getMessage()));
			}
		}
		return errors;
	}

	public Vector checkAllClassHdr() {
		Vector errors = new Vector();
		for (Iterator iter = getComponents().iterator(); iter.hasNext(); ) {
			ExtensionEntityComponent item = (ExtensionEntityComponent) iter.next();
			if (item.getClassHDR() == null){
				errors.add(new ErrorMessage("EXTN000026",new String[]{item.getClassHDRName(), item.getComponentName()}));
			}
		}
		for (Iterator iter = getRelations().iterator(); iter.hasNext(); ) {
			ExtensionEntityRelation item = (ExtensionEntityRelation) iter.next();
			if (item.getClassHdr() == null){
				errors.add(new ErrorMessage("EXTN000027",new String[]{item.getCorrelatedClassName(), item.getRelationName()}));
			}
		}
		for (Iterator iter = getManyToManyList().iterator(); iter.hasNext(); ) {
			CollectionExtensionEntity item = (CollectionExtensionEntity) iter.next();
			if (item.getClassHDR() == null){
				errors.add(new ErrorMessage("EXTN000028",new String[]{item.getClassHDRName(), item.getCollectionName()}));
			}
		}
		return errors;
	}

	public ErrorMessage checkFolderId(){
		if (isProcessing() && getFolderId() != null){
			if (getFolder() == null){
				return new ErrorMessage("EXTN000029", getFolderId());
			}
		}
		return null;
	}

	public ErrorMessage checkUserMenuFolderId(){
		if (isProcessing() && getUserMenuFolderId() != null){
			if (getUserFolder() == null){
				return new ErrorMessage("EXTN000030", getUserMenuFolderId());
			}
		}
		return null;
	}
// Fix 15800 fine

	//23811 inizio
	public void removeInheritedComponents() {
		if (getComponents() == null || getComponents().isEmpty()) {
			return;
		}
		for (Iterator components = getComponents().iterator(); components.hasNext();) {
			ExtensionEntityComponent component = (ExtensionEntityComponent) components.next();
			if (component.getInheritenceType() == Inheritable.INHERITED) {
				removeColumns(component);
				components.remove();
			}
		}
	}

	public void removeInheritedRelations() {
		if (getRelations() == null || getRelations().isEmpty()) {
			return;
		}
		for (Iterator relations = getRelations().iterator(); relations.hasNext();) {
			ExtensionEntityRelation relation = (ExtensionEntityRelation) relations.next();
			if (relation.getInheritenceType() == Inheritable.INHERITED) {
				relations.remove();
			}
		}
	}

	public boolean hasAttributeWithName(String attributeName) {
		return (getAttributeWithName(attributeName) != null);
	}

	public AttributeExtensible getAttributeWithName(String attributeName) {
		if ((getAttributes() != null) && !getAttributes().isEmpty()) {
			for (Iterator attributes = getAttributes().iterator(); attributes.hasNext();) {
				AttributeExtensible attribute = (AttributeExtensible) attributes.next();
				if (attribute != null && attribute.getAttributeName().equals(attributeName)) {
					return attribute;
				}
			}
		}

		return null;
	}

	public boolean hasComponentWithName(String componentName) {
		return (getComponentWithName(componentName) != null);
	}

	public ExtensionEntityComponent getComponentWithName(String componentName) {
		if ((getComponents() != null) && !getComponents().isEmpty()) {
			for (Iterator components = getComponents().iterator(); components.hasNext();) {
				ExtensionEntityComponent component = (ExtensionEntityComponent) components.next();
				if (component != null && component.getComponentName().equals(componentName)) {
					return component;
				}
			}
		}

		return null;
	}

	public boolean hasRelationWithName(String relationName) {
		return (getRelationWithName(relationName) != null);
	}

	public ExtensionEntityRelation getRelationWithName(String relationName) {
		if ((getRelations() != null) && !getRelations().isEmpty()) {
			for (Iterator relations = getRelations().iterator(); relations.hasNext();) {
				ExtensionEntityRelation relation = (ExtensionEntityRelation) relations.next();
				if (relation != null && relation.getRelationName().equals(relationName)) {
					return relation;
				}
			}
		}

		return null;
	}  
	
	public ExtensionEntityLayoutTab getThCommentTab(){
		ExtensionEntityLayoutTab thCommentTab = null;
		if(hasThComment()){
			Iterator tabs = getLayoutTabbeds().iterator();
			while (tabs.hasNext()) {
				ExtensionEntityLayoutTab tab = (ExtensionEntityLayoutTab) tabs.next();
				if(tab.getTabbedType().equals(ExtensionEntityLayoutTab.TAB_THERM_COMMENT)){
					thCommentTab = tab;
					break;
				}
			}
			if(thCommentTab == null){
				ExtensionWebGenerator generator = (ExtensionWebGenerator) Factory.createObject(ExtensionWebGenerator.class);
	      generator.setExtensibleEntity(this);
	      thCommentTab = generator.createCommentTab();
				
		  	getLayoutTabbeds().add(thCommentTab);
			}
		}
		return thCommentTab;
	}

	public ExtensionEntityLayoutTab getThNlsTab(){
		ExtensionEntityLayoutTab thNlsTab = null;
		Iterator tabs = getLayoutTabbeds().iterator();
		while (tabs.hasNext()) {
			ExtensionEntityLayoutTab tab = (ExtensionEntityLayoutTab) tabs.next();
			if(tab.getTabbedType().equals(ExtensionEntityLayoutTab.TAB_THERM_NLS)){
				thNlsTab = tab;
				break;
			}
		}
		if(hasThNlsComponent() && (thNlsTab == null)){
			ExtensionWebGenerator generator = (ExtensionWebGenerator) Factory.createObject(ExtensionWebGenerator.class);
      generator.setExtensibleEntity(this);
      thNlsTab = generator.createNlsTab();
      
      getLayoutTabbeds().add(thNlsTab);
		}
		if(!hasThNlsComponent() && (thNlsTab != null)){
			getLayoutTabbeds().remove(thNlsTab);
		}
		return thNlsTab;
	}

  public void removeColumns(ExtensionEntityComponent extensionEntityComponent) {
    TransientTableManager ttm = null;
    try {
      Class c = Class.forName(extensionEntityComponent.getTTMClassName());
      ttm = (TransientTableManager) c.newInstance();
    }
    catch (IllegalAccessException ex) {
      ex.printStackTrace();
      return;
    }
    catch (InstantiationException ex) {
      ex.printStackTrace();
      return;
    }
    catch (ClassNotFoundException ex) {
      ex.printStackTrace();
      return;
    }
    Hashtable attrRel = ttm.getAttributeRelations();
    Iterator i = attrRel.entrySet().iterator();
    while(i.hasNext()) {
      Map.Entry entry = (Map.Entry)i.next();
      if (entry != null) {
        String attributeName = extensionEntityComponent.getComponentName()+"."+ entry.getKey();
        ExtensionColumnOrder column = (ExtensionColumnOrder)Factory.createObject(ExtensionColumnOrder.class);
        column.setExtensibleEntityId(getId());
        column.setAttributeName(attributeName);
        int position = getOrderColumns().indexOf(column);
        if (position >= 0) {
        	getOrderColumns().remove(position);
        }
      }
    }
  }  
  
  public boolean hasStandardTabs() {
  	for (Iterator iterator = getLayoutTabbeds().iterator(); iterator.hasNext();) {
  		ExtensionEntityLayoutTab tab = (ExtensionEntityLayoutTab) iterator.next();
			if(ExtensionEntityLayoutTab.TAB_STANDARD.equals(tab.getTabbedType())) {
				return true;
			}
		}
  	return false;
  }
  
  protected int deleteDictionnary() throws SQLException{
		int result = ErrorCodes.NO_ROWS_UPDATED;
    ExtensionWebGenerator generator = (ExtensionWebGenerator) Factory.createObject(ExtensionWebGenerator.class);
    try {
      generator.init(this, null);
			Vector allTables = populateExtensionTables();
			Vector selectedValues = new Vector();
			for (int i = 0; i < allTables.size(); i++) {
				DictTables element = DictTables.elementWithKey(((String[])allTables.get(i))[1], PersistentObject.NO_LOCK);
				if (element != null)
					selectedValues.add(element);
			}
			while (!selectedValues.isEmpty()){
				DictTables element = (DictTables)selectedValues.get(selectedValues.size()-1);
				int del = element.delete();
				if (del < ErrorCodes.NO_ROWS_UPDATED)
					return del;
				result += del;
				selectedValues.remove(element);
			}
    }
    catch (IOException ex) {
      ex.printStackTrace(Trace.excStream);
      throw new SQLException(ex.getMessage());
    }
    
		return result;
	}
  
	public int deleteAll() throws SQLException	{
		int globalResult = ErrorCodes.NO_ROWS_UPDATED;
		int result = deleteWorkflow();
		if (result < ErrorCodes.NO_ROWS_UPDATED) {
			return result;
		}
		globalResult += result;
		result = deleteMenuItem();
		if (result < ErrorCodes.NO_ROWS_UPDATED) {
			return result;
		}
		globalResult += result;
		result = deleteEntities();
		if (result < ErrorCodes.NO_ROWS_UPDATED) {
			return result;
		}
		globalResult += result;
		result = deleteDescriptors();
		if (result < ErrorCodes.NO_ROWS_UPDATED) {
			return result;
		}
		globalResult += result;
		result = deleteWebPages();
		if (result < ErrorCodes.NO_ROWS_UPDATED) {
			return result;
		}
		globalResult += result;
		result = deleteClasses();
		if (result < ErrorCodes.NO_ROWS_UPDATED) {
			return result;
		}
		globalResult += result;
		result = deleteSources();
		if (result < ErrorCodes.NO_ROWS_UPDATED) {
			return result;
		}
		globalResult += result;
		result = deleteExtraFiles();
		if (result < ErrorCodes.NO_ROWS_UPDATED) {
			return result;
		}
		globalResult += result;
    result = deleteDictionnary();
		if (result < ErrorCodes.NO_ROWS_UPDATED) {
			return result;
		}
		globalResult += result;
		
		return globalResult;
	}  
  
  public boolean isInstalled() {
  	if(getFixNumber() != null) {
  		SoftwareFix fix = getSoftwareFix();
  		return (fix != null);
  	}
  	return false;
  }
  
  public int uninstall(ServletEnvironment se) throws SQLException {
  	int rc = 0;
  	if(getFixNumber() != null) {
  		SoftwareFix fix = getSoftwareFix();
  		if(fix != null) {
      	int ret = fix.delete();
      	if(ret < ErrorCodes.NO_ROWS_UPDATED) {
      		return ret;
      	}
      	rc += ret;
  		}
  	}
  	int ret = deleteOwnedObjects();
  	if(ret < ErrorCodes.NO_ROWS_UPDATED) {
  		return ret;
  	}
  	rc += ret;
  	//28381 inizio
  	deleteAllClasses(se);
  	deleteAllWebFiles(se);
  	//28381 fine
  	return rc;
  }
  
  public void generateAll(ServletEnvironment se) throws ExtensionException{
		List errors = null;
    try {
			errors = generateDictionnary(se, false);
			if (errors == null || errors.isEmpty()) {
			  errors = generateJavaFiles(se);
			}
			if (errors == null || errors.isEmpty()) {
			  errors = generateJavaClasses(se);
			}
			if (errors == null || errors.isEmpty()) {
			  errors = generateDescriptors(se);
			}
			if (errors == null || errors.isEmpty()) {
			  errors = generateWebFiles(se);
			}
			if (errors == null || errors.isEmpty()) {
			  errors = generateJspFile(se);
			}
			if (errors == null || errors.isEmpty()) {
			  errors = generateEntityMenu(se);
			}	    
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorMessage em = new ErrorMessage("BAS0000078", e.getMessage());
			throw new ExtensionException(em);
		}
    if((errors != null) && !errors.isEmpty()) {
    	throw new ExtensionException(errors);
    }
    
  }
  
  public File generateFix(ServletEnvironment se) throws ExtensionException{
		ExtensionFixGenerator fixGenerator = (ExtensionFixGenerator)Factory.createObject(ExtensionFixGenerator.class);
  	try {
			fixGenerator.init(this, se);
			fixGenerator.clean();
			
			File tddmlDir = new File(fixGenerator.getRepositoryDir(), "tddml");
			export(tddmlDir);
  		generateAll(se);
  		fixGenerator.generate();
			
  		if(!isInstalled()) {
	    	deleteAll();
	    }

			return fixGenerator.getFixDirectory();
		}
  	catch (ExtensionException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorMessage em = new ErrorMessage("BAS0000078", e.getMessage());
			throw new ExtensionException(em);
		}
  	finally {
			fixGenerator.closeReport();
		}
  }
  
  
  public File generateAndExportFix(ServletEnvironment se) throws ExtensionException{
		ExtensionFixGenerator fixGenerator = (ExtensionFixGenerator)Factory.createObject(ExtensionFixGenerator.class);
  	try {
			fixGenerator.init(this, se);
			fixGenerator.clean();
			setCreateTablesRequired(false);
			setReplacementDisabled(false);
			File tddmlDir = new File(fixGenerator.getRepositoryDir(), "tddml");
			export(tddmlDir);
  		generateAll(se);
  		fixGenerator.generate();
			if(!isInstalled()) {
	    	deleteAll();
	    }
			setReplacementDisabled(true);
  		return fixGenerator.export();
		}
  	catch (ExtensionException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorMessage em = new ErrorMessage("BAS0000078", e.getMessage());
			throw new ExtensionException(em);
		}
  	finally {
  		fixGenerator.closeReport();
		}
  }
  
  public int generateAndInstallFix(ServletEnvironment se) throws ExtensionException{
  	ExtensionFixGenerator fixGenerator = (ExtensionFixGenerator)Factory.createObject(ExtensionFixGenerator.class);
  	try {
			
			fixGenerator.init(this, se);
			
			setCreateTablesRequired(false);
			setReplacementDisabled(false);
  		generateAll(se);
			
  		fixGenerator.generate();
  		setReplacementDisabled(true);
  		deleteDictionnary();
  		return fixGenerator.install(se);
		}
  	catch (ExtensionException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorMessage em = new ErrorMessage("BAS0000078", e.getMessage());
			throw new ExtensionException(em);
		}
  	finally {
			fixGenerator.closeReport();
		}
  }

  public SoftwareFix getSoftwareFix() {
  	SoftwareFix fix = null;
  	if(getFixNumber() != null) {
  		String where = SoftwareFixTM.SOFTWARE_FIX  + " = " + getFixNumber();	
  		try {
  			List fixes = SoftwareFix.retrieveList(where, "", false);
  	  	if(!fixes.isEmpty()) {
  	  		fix = (SoftwareFix)fixes.get(0);
  	  	}
  		}
  		catch (Exception e) {
  			e.printStackTrace();
  		}
  	}
  	return fix;
  }
  
  public void ensureFixNumber() {
  	if(getFixNumber() == null) {
			setFixNumber(getNextFixNumber());
  	}
  	if(getFixDescription() == null) {
  		setFixDescription(getDescription());
  	}
  }
  
  public Integer getNextFixNumber() {
  	int nextFixNumber = 0;
  	ResultSet rs = null;
  	synchronized (lastFixStmt) {
    	try {
				rs = lastFixStmt.executeQuery();
				if(rs.next()) {
					nextFixNumber = rs.getInt(1);
					if(rs.wasNull()) {
						nextFixNumber = 99999;
					}
					else {
						nextFixNumber--;
					}
				}
			}
			catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
    	finally {
    		if(rs != null) {
    			try {
						rs.close();
					}
					catch (SQLException e) {
						e.printStackTrace(Trace.excStream);
					}
    		}
    	}
		}
  	
  	return new Integer(nextFixNumber);
  }
  
	public void export(File folder){
		File file = new File(folder, this.getKey() + "_EXTEN_ENTITY.TDDML");//30122
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileOutputStream(file.getPath(), false));
			export(out);
		}
		catch (Exception e) {
			e.printStackTrace(Trace.excStream);
		}
		finally {
			if(out != null) {
				out.close();
			}
		}
	}
	
  public void export(PrintWriter writer) {
		SQLExporterStructure exporter = new SQLExporterStructure();
		BODataCollector collector = (BODataCollector)Factory.createObject(BODataCollector.class);
		collector.initialize("ExtensibleEntity",true,PersistentObject.NO_LOCK);
		BODCXML xml = (BODCXML)Factory.createObject(BODCXML.class);
    collector.setBo(this);
    LWElement element = xml.toElement(collector);
    toSQL(exporter, element.toXMLString());
    List exportKeys = new ArrayList();
    Enumeration enumer = exporter.keys();
    while (enumer.hasMoreElements()) {
      exportKeys.add(enumer.nextElement());
    }

    startTddml(writer);
    
    if (exportKeys.contains("EXTEN_ENTITY")) {
      String prefix = "INSERT INTO <SCH>EXTEN_ENTITY (ID, DESCRIPTION, FW_DATA_OWNER_ID, GENERATION_MODE, EXTENSION_TYPE, EXTENSION_CLASS_NAME, EXTENSION) VALUES ";
      Iterator iter = exporter.lineIterator("EXTEN_ENTITY");
  		writer.println("<SQL>");
  		writer.println("<![CDATA[");
      writer.println(prefix + (String) iter.next() + ";");
  		writer.println("]]>");
  		writer.println("</SQL>");      
    }
    writer.flush();
    
    if (exportKeys.contains("PARTIAL_EXTENSION")) {
    	String suffix = " WHERE ID = <LAST_NUM><EXTEN_ENTITY>";
    	Database[] databases = new Database[] {new DB2Database(), new SQLServerDatabase("", ""), new Oracle8Database("", "")};
    	for (int i = 0; i < databases.length; i++) {
    		writer.println();
				Database database = databases[i];
				String[] platforms = getPlatforms(database);
				String prefix = getExtensionColumnExportPrefix(database);
				Iterator iter = exporter.lineIterator("PARTIAL_EXTENSION");
	      while (iter.hasNext()) {
	    		writer.println("<SQL>");
					for(int j = 0; j < platforms.length; j++) {
						writer.println(platforms[j]);
					}
	    		writer.println("<![CDATA[");
	      	writer.println(prefix + (String) iter.next() + suffix + ";");
	    		writer.println("]]>");
	    		writer.println("</SQL>");
	      	writer.flush();
	      }
			}
    }
    
    endTddml(writer);
  }
	
  public void export(PrintWriter writer, Database database) {
		SQLExporterStructure exporter = new SQLExporterStructure();
		BODataCollector collector = (BODataCollector)Factory.createObject(BODataCollector.class);
		collector.initialize("ExtensibleEntity",true,PersistentObject.NO_LOCK);
		BODCXML xml = (BODCXML)Factory.createObject(BODCXML.class);
    collector.setBo(this);
    LWElement element = xml.toElement(collector);
    toSQL(exporter, element.toXMLString());
    List exportKeys = new ArrayList();
    Enumeration enumer = exporter.keys();
    while (enumer.hasMoreElements()) {
      exportKeys.add(enumer.nextElement());
    }

    startTddml(writer, getPlatforms(database));
    
    if (exportKeys.contains("EXTEN_ENTITY")) {
      String prefix = "INSERT INTO <SCH>EXTEN_ENTITY (ID, DESCRIPTION, FW_DATA_OWNER_ID, GENERATION_MODE, EXTENSION_TYPE, EXTENSION_CLASS_NAME, EXTENSION) VALUES ";
      Iterator iter = exporter.lineIterator("EXTEN_ENTITY");
  		writer.println("<SQL>");
  		writer.println("<![CDATA[");
      writer.println(prefix + (String) iter.next() + ";");
  		writer.println("]]>");
  		writer.println("</SQL>");      
    }
    writer.flush();
    
    if (exportKeys.contains("PARTIAL_EXTENSION")) {
      Iterator iter = exporter.lineIterator("PARTIAL_EXTENSION");
			String prefix = getExtensionColumnExportPrefix(database);
      String suffix = " WHERE ID = <LAST_NUM><EXTEN_ENTITY>";
      while (iter.hasNext()) {
    		writer.println("<SQL>");
    		writer.println("<![CDATA[");
      	writer.println(prefix + (String) iter.next() + suffix + ";");
    		writer.println("]]>");
    		writer.println("</SQL>");
      	writer.flush();
      }
    }
    
    endTddml(writer);
  }
  
  protected String getExtensionColumnExportPrefix(Database database) {
    if (database instanceof SQLServerDatabase) {
      return "UPDATE <SCH>EXTEN_ENTITY SET EXTENSION = CAST(EXTENSION AS NVARCHAR(MAX)) " + database.getConcatOperator() + " ";
    }
    else {
      return "UPDATE <SCH>EXTEN_ENTITY SET EXTENSION = EXTENSION " + database.getConcatOperator() + " ";
    }
  }  
  
	public String[] getPlatforms(Database database) {
		if (database instanceof SQLServerDatabase) {
			return new String[] {"<Platform name=\"SQLServer\"/>"};
		}
		if (database instanceof Oracle8Database) {
			return new String[] {"<Platform name=\"Oracle\"/>"};
		}
		
		return new String[] {"<Platform name=\"DB2\"/>", "<Platform name=\"DB2-400-Long\"/>", "<Platform name=\"DB2-400-Short\"/>"};
	}
	
	public void startTddml(PrintWriter writer, String[] platforms) {
		writer.println("<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>");
		writer.println("<!DOCTYPE TDDML SYSTEM \"../../../TDDML.dtd\">");
		writer.println("<TDDML>");
		for(int i = 0; i < platforms.length; i++) {
			writer.println(platforms[i]);
		}
	}
	
	public void startTddml(PrintWriter writer) {
		writer.println("<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>");
		writer.println("<!DOCTYPE TDDML SYSTEM \"../../../TDDML.dtd\">");
		writer.println("<TDDML>");
	}
	
	public void endTddml(PrintWriter writer) {
		writer.println("</TDDML>");
	}  
  //23811 fine
	
	//27963 inizio
	public void setExportDictionnary(boolean exportDictionnary) {
		this.exportDictionnary = exportDictionnary;
	}
	
	public boolean isExportDictionnary() {
		return this.exportDictionnary;
	}

	public List getTableKeys() {
		return this.tableKeys;
	}
	//27963 fine
	//28079 inizio
//  public AttributeExtensible getAttributeFromColumn(ExtensionColumnOrder column){
//		AttributeExtensible result = getAttributeForColumn(column.getColumnName());
//		if (result == null){
//      result = getAttributeFromThermComponent(column);
//    }
//		if (result == null){
//      result = getAttributeFromComponent(column);
//    }
//		return result;
//  }
//  
	public String getBODataCollector() {
		return iBODataCollector;
	}

	public void setBODataCollector(String dataCollector) {
		this.iBODataCollector = dataCollector;
	}

	public String getWebFormCustomizer() {
		return iWebFormCustomizer;
	}

	public void setWebFormCustomizer(String webFormCustomizer) {
		this.iWebFormCustomizer = webFormCustomizer;
	}

	public String getViewSelector() {
		return iViewSelector;
	}

	public void setViewSelector(String viewSelector) {
		this.iViewSelector = viewSelector;
	}
	
	public boolean isReplacementDisabled() {
		return replacementDisabled;
	}

	public void setReplacementDisabled(boolean replacementDisabled) {
		this.replacementDisabled = replacementDisabled;
	}
	
  protected void deleteAllReplacement()throws SQLException{
  	List allReplacement = getAllReplacement();
		for (Iterator iterator = allReplacement.iterator(); iterator.hasNext();) {
			Replacement replacement = (Replacement) iterator.next();
			if(replacement.retrieve()) {
				replacement.delete();
			}
		}
	}

	protected List<Replacement> getAllReplacement() {
		List<Replacement> allReplacement = new ArrayList<Replacement>();
		if((getExtendedClass() != null) && (getExtensionType() == ExtensibleEntity.EXTENDED)) {//32879
  		Replacement businessObject = (Replacement) Factory.createObject(Replacement.class);
  		businessObject.setOriginalClassName(getExtendedClass().getBOClassName());
  		businessObject.setReplacingClassName(getSourcePath().concat(".").concat(getClassName()));
  		businessObject.setType(Factory.CLASS);
  		businessObject.setDisabled(isReplacementDisabled());
  		allReplacement.add(businessObject);
  		
  		Replacement tableManager = (Replacement) Factory.createObject(Replacement.class);
  		tableManager.setOriginalClassName(getExtendedClass().getBOClassName().concat("TM"));
  		tableManager.setReplacingClassName(getSourcePath().concat(".").concat(getClassName()).concat("TM"));
  		tableManager.setType(Factory.CLASS);
  		tableManager.setDisabled(isReplacementDisabled());
  		allReplacement.add(tableManager);
  		
  		Replacement classHdr = (Replacement) Factory.createObject(Replacement.class);
  		classHdr.setOriginalClassName(getExtensionClassName());
  		classHdr.setReplacingClassName(getClassName());
  		classHdr.setType(Factory.CLASS_HDR);
  		classHdr.setDisabled(isReplacementDisabled());
  		allReplacement.add(classHdr);
  		
		}
		return allReplacement;
	}
	
	public String getNLSRowClassName() {
		String name = getClassName();
		if(name.length() > 22) {
			name.substring(0, 22);
		}
		return name.concat("NLS");
	}
	//28079 fine
	
	//28381 inizio
	public ErrorMessage checkAllreadyReplacedClass() {
		boolean ok = true;
		if(getExtensionClassName() != null) {
			ClassADCollection cad = null;
      try {
        cad = ClassADCollectionManager.collectionWithName(getExtensionClassName());
        if(!cad.getClassName().equals(getExtensionClassName()) && !cad.getClassName().equals(getClassName())) {
        	ok = false;
        }
        if(ok && (cad.getBOClassName() != null)) {
        	String originalBusinessClassName = cad.getBOClassName();
        	String replacingBusinessClassName = Factory.getName(originalBusinessClassName, Factory.CLASS);
        	//verifico se la classe è stata ripmiazzata
        	if(!replacingBusinessClassName.equals(originalBusinessClassName)) {
        		String currentClassName = getSourcePath().concat(".").concat(getClassName());
        		//verifico se la classe ripmiazzata è stata generata da questa estensione
        		ok = currentClassName.equals(replacingBusinessClassName);
        	}
        }
        if(ok) {
        	TableManager superTableManager = cad.getObjectTableManager();
        	if(superTableManager != null) {
        		String originalTableManagerName = superTableManager.getClass().getName();
        		String replacingTableManagerName = Factory.getName(originalTableManagerName, Factory.CLASS);
        		//verifico se la classe è stata ripmiazzata
        		if(!replacingTableManagerName.equals(originalTableManagerName)) {
        			String currentTableManagerName =   getSourcePath().concat(".").concat(getClassName()).concat(WizardBuilder.TABLE_MANAGER_SUFFIX);
          		//verifico se la classe ripmiazzata è stata generata da questa estensione
        			ok = currentTableManagerName.equals(replacingTableManagerName);
        		}
        	}
        }
      }
      catch (NoSuchFieldException ex) {
				cad = null;
      }
      catch (NoSuchElementException ex) {
				cad = null;
      }
		}
		
		return null;
	}

  public void deleteAllClasses(ServletEnvironment se) {
    ByteCodeGenerator generator = (ByteCodeGenerator) Factory.createObject(ByteCodeGenerator.class);
    try {
			generator.init(this, se);
	    generator.deleteAllClassFiles();
		}
    catch (IOException ioe){
			generator.addError(new ErrorMessage("BAS0000078",ioe.getMessage()));
			generator.reportErrors();
		} 
		finally{
			generator.closeReport();
		}
  }
  
  public void deleteAllWebFiles(ServletEnvironment se) {
    ExtensionWebGenerator generator = (ExtensionWebGenerator)Factory.createObject(ExtensionWebGenerator.class);
		try{
			generator.init(this,se);
			for (Iterator iter = getOneToManyList().iterator(); iter.hasNext(); ) {
				ExtensibleEntity item = (ExtensibleEntity) iter.next();
				if(item.hasStandardTabs()) {
					ExtensionWebGenerator childGenerator = (ExtensionWebGenerator)Factory.createObject(ExtensionWebGenerator.class);
					childGenerator.init(item, se);
					childGenerator.setWorkSourcePath(generator.getWorkSourcePath());
					childGenerator.setWorkLibPath(generator.getWorkLibPath());
					childGenerator.setWorkWebsrcPath(generator.getWorkWebsrcPath());
					childGenerator.setRealLibPath(generator.getRealLibPath());
					childGenerator.setRealWebsrcPath(generator.getRealWebsrcPath());
					childGenerator.setReportFileName(generator.getReportFileName());
					childGenerator.setReportFileWriter(generator.getReportFileWriter());
					childGenerator.deleteAllFiles();
				}
			}
			generator.deleteAllFiles();
		} 
		catch (FileNotFoundException fnfe){
			generator.addError(new ErrorMessage("BAS0000078",fnfe.getMessage()));
			generator.reportErrors();
		} 
		catch (IOException ioe){
			generator.addError(new ErrorMessage("BAS0000078",ioe.getMessage()));
			generator.reportErrors();
		} 
		finally{
			generator.closeReport();
		}
  }
  
  public boolean needsJavascript() {
  	////Fix 46485return (getExtensionType() == ExtensibleEntity.INDIPENDENTE) && !isChild();  	
	return (getExtensionType() == ExtensibleEntity.INDIPENDENTE || getExtensionType() == ExtensibleEntity.SINGOLA ) && !isChild();//Fix 46485
  }
  
  public int delete() throws SQLException{
  	int result = super.delete();
  	if (result < ErrorCodes.NO_ROWS_UPDATED) {
			return result;
		}
  	if(getServletDelete() != null) {
  		deleteAllClasses(getServletDelete());
  		deleteAllWebFiles(getServletDelete());
  	}
  	return result;
  }
  
  public boolean isCheckFixNumberEnabled() {
  	return checkFixNumberEnabled;
  }
  
  public void setCheckFixNumberEnabled(boolean checkFixNumberEnabled) {
  	this.checkFixNumberEnabled = checkFixNumberEnabled;
  }
  
  public ErrorMessage checkFixNumber() {
  	if(isCheckFixNumberEnabled() && (getSoftwareFix() != null)) {
  		String text = ResourceLoader.getString("com.thera.thermfw.setup.resources.Setup", "FixAlreadyRun", new Object[] {getFixNumber()});
  		return new ErrorMessage("BAS0000078", text); 
  	}
  	
  	return null;
  }
  
  //30122 inizio
  public String getFilenamePrefix() {
  	if(getFixNumber() != null) {
  		return getFixNumber() + "_";
  	}
  	
  	return this.getKey() + "_";
  }
  
  public boolean shouldCopyResources(ServletEnvironment se) {
  	if(se != null) {
  		return Boolean.valueOf(ResourceLoader.getString(BaseExtensionGenerator.EXT_RES, "resources.copy"));
  	}
  	return false;
  }
  //30122 fine
  //30451 inizio
  public void setAllReplacementEnabled(boolean enabled) {
  	List<Replacement> allReplacement = getAllReplacement();
  	try {
			for (Replacement replacement : allReplacement) {
				if(replacement.retrieve()) {
					replacement.setDisabled(!enabled);
					replacement.save();
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
  }
  //30451 fine
  //31240 inizio
	public void exportAttributeRefs(File file){
		Set<PersistentAttributeRef> attributeRefSet = getExportableAttributeRefSet();
		if(!attributeRefSet.isEmpty()) {
			try {
				PrintWriter out = new PrintWriter(new FileOutputStream(file.getAbsolutePath(), true));
				for (Iterator<PersistentAttributeRef> iterator = attributeRefSet.iterator(); iterator.hasNext();) {
					PersistentAttributeRef persistentAttributeRef = iterator.next();
					AttributeRefExtension.exportData(out, persistentAttributeRef);
				}
				out.flush();
				out.close();
			}
			catch(Exception ex) {
				ex.printStackTrace(Trace.excStream);
			}
		}
	}
	
	public Set<PersistentAttributeRef> getExportableAttributeRefSet(){
		Set<PersistentAttributeRef> exportableAttributeRefList = new HashSet<PersistentAttributeRef>();
		for (Iterator iter = getAttributes().iterator(); iter.hasNext() ; ) {
			AttributeExtensible attribute = (AttributeExtensible) iter.next();
			PersistentAttributeRef attributeRef = attribute.getAttributeReference();
			if(isExportable(attributeRef)){
				exportableAttributeRefList.add(attributeRef);
			}
		}
		return exportableAttributeRefList;
	}
	
	public boolean isExportable(PersistentAttributeRef attributeRef){
		return (getModalitaExt() == ExtensibleEntity.MODALITA_EXT_STANDARD)
				|| Utils.areEqual(attributeRef.getFrameworkDataOwnerId(), getFrameworkDataOwnerId());
	}
  //31240 fine
  
	//32823 inizio
	public void setEntityId(String entityId) {
	  this.entityId = entityId;
	}
	
	public String getEntityId() {
	  if(entityId != null) {
	    return entityId;
	  }
	  
	  if(getClassName() != null) {
	    if(getClassName().length() > 15) {
        return getClassName().substring(0, 15);
      }
	    return getClassName();
	  }
	  
	  return null;
	}
	
	public ErrorMessage checkEntityId() {
	  if (!isOnDB()){
      try {
        Entity entity = Entity.elementWithKey(getEntityId(),PersistentObject.NO_LOCK);
        if (entity != null){
          return new ErrorMessage("EXTN000034", getEntityId());
        }
      }
      catch (SQLException e) {
        e.printStackTrace(Trace.excStream);
      }     
    }
	  return null;
	}
	//32823 fine
	
	//33720 inizio
	public ErrorMessage checkReplacements() {
	  List<Replacement> allReplacement = getAllReplacement();
	  for (Replacement replacement : allReplacement) {
      try {
        if(replacement.retrieve()) {
          return null;
        }
      }
      catch (SQLException e) {
        e.printStackTrace(Trace.excStream);
      }
    }
	  return null;
	}
	//33720 fine
	
	// 44891 inizio
	public boolean isEquelString(String firstString, String secondString) {
		if (firstString != null && ( firstString.equals(secondString) || firstString.equalsIgnoreCase(secondString))) {
			return true;
		}
		return false;
	}
	
	public  Map<String,ErrorMessage> getErrorsForCheck(String secondParam) {
	 	  	Map<String,ErrorMessage> ret = new HashMap<String,ErrorMessage>();
	 	 	ret.put(ATTRIBUTO_ID, new ErrorMessage("THIP40T976",new String [] {ResourceLoader.getString(PROPERTIE_FILE_EXT_ENTITY,ATTRIBUTO_ID),secondParam}));
	 	 	ret.put(RELAZIONE_ID, new ErrorMessage("THIP40T976",new String [] {ResourceLoader.getString(PROPERTIE_FILE_EXT_ENTITY,RELAZIONE_ID),secondParam}));
	 	 	ret.put(COMPONENTE_ID, new ErrorMessage("THIP40T976",new String [] {ResourceLoader.getString(PROPERTIE_FILE_EXT_ENTITY,COMPONENTE_ID),secondParam}));
	 	 	ret.put(COLLECTION_ID, new ErrorMessage("THIP40T976",new String [] {ResourceLoader.getString(PROPERTIE_FILE_EXT_ENTITY,COLLECTION_ID),secondParam}));
	 	 	
	 	 	return ret;	 	
	}
	
	public ErrorMessage checkNomeWithThermComponents(ExtensibleEntity extensionEntity ,String nome, ErrorMessage componenteErr) {
		if (extensionEntity.getThWorkflowName()!=null && isEquelString(extensionEntity.getThWorkflowName(),nome))
	        return componenteErr;

	      if (extensionEntity.getThNlsName()!=null && isEquelString(extensionEntity.getThNlsName(),nome))
	        return componenteErr;

	      if (extensionEntity.getThermCommentName()!=null && isEquelString(extensionEntity.getThermCommentName(),nome))
	        return componenteErr;
	      if (extensionEntity.getThNlsPlural()!=null && isEquelString(extensionEntity.getThNlsPlural(),nome))
	          return componenteErr;

	 return null;	
	}

	public ErrorMessage checkNomeWithRelation(ExtensibleEntity extensionEntity, String nome, ErrorMessage relationErr) {
		for (Iterator iter = extensionEntity.getRelations().iterator(); iter.hasNext();) {
			ExtensionEntityRelation item = (ExtensionEntityRelation) iter.next();
			if (isEquelString(item.getRelationName(), nome))
				return relationErr;
		}
		return null;
	}
	
	public ErrorMessage checkNomeWithComponents(ExtensibleEntity extensionEntity, String nome, ErrorMessage componenteErr) {
		for (Iterator iter = extensionEntity.getComponents().iterator(); iter.hasNext();) {
			ExtensionEntityComponent item = (ExtensionEntityComponent) iter.next();
			if (isEquelString(item.getComponentName(), nome))
				return componenteErr;
		}
		return null;
	}

	public ErrorMessage checkNomeWithAttributs(ExtensibleEntity extensionEntity, String nome, ErrorMessage attributoErr) {
		for (Iterator iter = extensionEntity.getAttributes().iterator(); iter.hasNext();) {
			AttributeExtensible item = (AttributeExtensible) iter.next();
			if (isEquelString(item.getAttributeName(), nome))
				return attributoErr;
		}

		return null;
	}
	
	public ErrorMessage checkNomeWithCollections(ExtensibleEntity extensionEntity ,String nome , ErrorMessage collectionErr ) {
		for (Iterator iter = extensionEntity.getOneToManyList().iterator(); iter.hasNext(); ) {
			ExtensibleEntity item = (ExtensibleEntity) iter.next();
			if (isEquelString(item.getCollectionName(),nome)) {
		    	  return collectionErr ;
		       
			}
		}
		for (Iterator iter = extensionEntity.getManyToManyList().iterator(); iter.hasNext(); ) {
			CollectionExtensionEntity item = (CollectionExtensionEntity) iter.next();
			if (isEquelString(item.getCollectionName(),nome)) {
		    	  return collectionErr ;
			}
		}
		return null ;
	}
	
	
	
	// 44891 fine
	
	// Fix 45830 inizio 
	public String getFormActionAdapter() {
		return iFormActionAdapter;
	}
	
	public void setFormActionAdapter(String formActionAdapter) {
	    this.iFormActionAdapter = formActionAdapter;
	  }
	
	public ErrorMessage checkAssignableFormActionAdapter() {
		String msgAttivo = "";
		String msgNonAttivo = "";
		if (getFormActionAdapter() != null) {
			Class c = LoadClassIfExiste(getFormActionAdapter());
			if (c == null) {
				return new ErrorMessage("CBS000002");
			}
			else 
			{// se calasse esisto
				String defRessourceFile = getCurrentPropertiesFileName();
				if (defRessourceFile != null) {
					 msgAttivo =  ResourceLoader.getString(defRessourceFile, "msgAttivo");
					 msgNonAttivo = ResourceLoader.getString(defRessourceFile, "msgNonAttivo");
				}
				// verificare che class c erediti dalla classe corretta
				if (!hasThWorkflow() && checkInheritedFrom(com.thera.thermfw.cbs.web.WfFormActionAdapter.class,c)) {
					return new ErrorMessage("THIP50T013", new String[]{msgNonAttivo,"com.thera.thermfw.web.servlet.FormActionAdapter"});
					
				} else if (hasThWorkflow() && (checkInheritedFrom(FormActionAdapter.class,c) &&
						!(c.getName().equalsIgnoreCase(com.thera.thermfw.cbs.web.WfFormActionAdapter.class.getName())))) {
					return new ErrorMessage("THIP50T013",new String[]{msgAttivo,"com.thera.thermfw.cbs.web.WfFormActionAdapter"});
				}
			}
		}
		return null;
	}
	
	// verificare che classDiFormAction erediti dalla classe corretta
	public boolean checkInheritedFrom(Class classStandard, Class classDiFormAction) {

		if (classDiFormAction != null && classStandard != null) {
			if (classDiFormAction.getName().equalsIgnoreCase(classStandard.getName())) {
				return true;
			} else {
				Class<?> superClass = classDiFormAction.getSuperclass();
				if (superClass != null && superClass.getName().equalsIgnoreCase(classStandard.getName())) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	
	public String getDefaultFormActionAdapter() {
		return iDefaultFormActionAdapter;
	}

	public void setDefaultFormActionAdapter(String defaultFormActionAdapter) {
		iDefaultFormActionAdapter = defaultFormActionAdapter;
		setDirty();
	}
	
	/**
	 *
	 * @param type DynamicEnumType
	 */
	public void fillDefaultFormActionAdapter(DynamicEnumType type) {
		boolean isPersClasse = false;
		List listDefaultFormActionAdapter = ExtensionDefaultClassName.getInstance().getDefaultFormActionAdapter();
		for (int i = 0; i < listDefaultFormActionAdapter.size(); i++) {
			ExtensionDefaultActionAdapter object = (ExtensionDefaultActionAdapter) listDefaultFormActionAdapter.get(i);
			type.addValue(object.getClassName(), object.getDescription());
			if(getFormActionAdapter() != null && getFormActionAdapter().equalsIgnoreCase(object.getClassName())) {
				isPersClasse = true;
			}
		  }
		
		if(!isPersClasse && getFormActionAdapter() != null) {
			ExtensionDefaultActionAdapter object = (ExtensionDefaultActionAdapter) Factory.createObject(ExtensionDefaultActionAdapter.class);
	  		object.setClassName(getFormActionAdapter());
	  		object.setDescription("-");
	  		type.addValue(object.getClassName(), object.getDescription());
		}
	}

	public Class LoadClassIfExiste(String nomeClasse) {
		Class c = null;
		if (nomeClasse != null) {
			try {
				c = Class.forName(getFormActionAdapter());
			} catch (ClassNotFoundException ex) {
				return null;
			}
		}
		return c;
	}
	
	public ErrorMessage checkThWorkflow() {
		if (getExtensionType() != ExtensibleEntity.EXTENDED) {
			ErrorMessage err = checkAssignableFormActionAdapter();
			if(err != null) {
				err.setForceable(true);
			}
			return err;
		}
		return null;
	}
  // Fix 45830 fine 
}
//13736 fine
