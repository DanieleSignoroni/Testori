package it.testori.thip.vendite.ordineVE.web;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.ad.ClassADCollectionManager;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.collector.BaseBOComponentManager;
import com.thera.thermfw.gui.cnr.DOList;
import com.thera.thermfw.gui.cnr.DisplayObject;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.security.Entity;
import com.thera.thermfw.setting.Setting;
import com.thera.thermfw.type.DateType;
import com.thera.thermfw.type.DecimalType;
import com.thera.thermfw.type.Type;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.WebDOList;
import com.thera.thermfw.web.servlet.BaseServlet;

import it.testori.thip.vendite.ordineVE.YAllegatiOrdVenRigPrm;
import it.testori.thip.vendite.ordineVE.YOrdineVenditaRigaPrm;
import it.thera.thip.base.documentoDgt.AssociazioneEntitaTDgtRiga;
import it.thera.thip.base.documentoDgt.AssociazioneEntitaTpDgt;
import it.thera.thip.base.documentoDgt.DocumentoDigitale;
import it.thera.thip.base.documentoDgt.TipoDocumentoAtt;
import it.thera.thip.base.documentoDgt.TipoDocumentoDigitale;
import it.thera.thip.base.documentoDgt.CreaDocumentoDigitale;
import it.thera.thip.vendite.ordineVE.OrdineVenditaRigaPrm;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 04/12/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    04/12/2025  DSSOF3   Prima stesura
 */

public class GeneraDocumentiOrdineVenditaRigaPrm extends BaseServlet {

	private static final long serialVersionUID = 1L;

	public static String PREFIX = "Classificazione";
	public static String PREFIX_DATA = "Data";//Mod.6361
	public static String PREFIX_VAL = "Valore";//Mod.6361
	public static String PREFIX_NOTE = "Note";//Mod.6361
	public static String PREFIX_CLOB = "Clob";  //...FIX07296 - DZ

	@SuppressWarnings("rawtypes")
	@Override
	protected void processAction(ServletEnvironment se) throws Exception {
		PrintWriter out = se.getResponse().getWriter();
		String key = getStringParameter(se.getRequest(), KEY);
		YOrdineVenditaRigaPrm riga = (YOrdineVenditaRigaPrm) YOrdineVenditaRigaPrm.elementWithKey(YOrdineVenditaRigaPrm.class,
				key, PersistentObject.NO_LOCK);
		if(riga != null && riga.getYAllegati().size() > 0) {
			Iterator iterAllegati = riga.getYAllegati().iterator();
			while(iterAllegati.hasNext()) {
				YAllegatiOrdVenRigPrm allegato = (YAllegatiOrdVenRigPrm) iterAllegati.next();
				CreaDocumentoDigitale creaDocDgt = (CreaDocumentoDigitale) Factory.createObject("it.thera.thip.base.documentoDgt.CreaDocumentoDigitale");
				creaDocDgt.setTipoDocDgt(allegato.getAssociazionedocumento().getTipodocdgt());
				creaDocDgt.setAzienda(riga.getIdAzienda());
				creaDocDgt.setIdCliente(riga.getIdCliente());
				creaDocDgt.setAnnoDoc(riga.getAnnoDocumento());
				creaDocDgt.setNumeroDoc(riga.getNumeroDocumento());
				creaDocDgt.setIdArticolo(riga.getIdArticoloEDI());
				creaDocDgt.setValore1(new BigDecimal(riga.getNumeroRigaDocumento()));
				creaDocDgt.setIdComessa(riga.getIdCommessa());
				creaDocDgt.setIdAgente(riga.getIdAgente());
				creaDocDgt.setDataDoc(riga.getTestata().getDataDocumento());
				String baseWhere = creaDocDgt.whereVerificaEsistenzaDocDgt();
				String additionalWhere = creaDocDgt.whereIdenfiticazioneDoc_ClsPers();
				Vector docs = DocumentoDigitale.retrieveList(baseWhere + additionalWhere , "", false);
				if(docs.size() == 0) {
					String idEntita = Entity.findEntityId(OrdineVenditaRigaPrm.class);
					creaDocDgt.setIdEntita(idEntita);
					creaDocDgt.setChiaveEntita(riga.getKey());
					creaDocDgt.setNonCreareOggettoDgt(true);
					DocumentoDigitale d = creaDocDgt.creaDocumentoDigitale();
					if(d != null) {
						int rc = d.save();
						if(rc > 0) {
							ConnectionManager.commit();
						}
					}
				}

			}
		}
		out.close();
	}

	public void valorizzaClassificazione(BODataCollector oggDaSettare,TipoDocumentoDigitale tipoDocumento, AssociazioneEntitaTDgtRiga associaRiga, BODataCollector dati,ServletEnvironment se) {
		Object value = dati.get(associaRiga.getClassAdEntita());
		String ownerCls = associaRiga.getClassName();
		String ownerKey = dati.getKey();
		String ownerAd = associaRiga.getClassAdEntita();
		DisplayObject displayObj = getCurrentDisplayObject(se, ownerCls, ownerAd, ownerKey);
		if(value != null && value.equals("")) {//34178
			value = null;
		}
		if(value != null ||  (value == null && displayObj != null)){//34178
			if(value == null) {//34178 inizio
				value = displayObj.getDBValue(0 + 1);
			}//34178 fine
			switch (associaRiga.getClassificazione())
			{
			case AssociazioneEntitaTDgtRiga.ANNO_DOC:
				tipoDocumento.setAttivaDocumento(TipoDocumentoDigitale.ATTIVA_CLASSIF_FAC);//Fix 17136
				oggDaSettare.set("IdAnnoDocumento", value);
				break;
			case AssociazioneEntitaTDgtRiga.NUMERO_DOC:
				tipoDocumento.setAttivaDocumento(TipoDocumentoDigitale.ATTIVA_CLASSIF_FAC);//Fix 17136
				oggDaSettare.set("IdNumeroDocumento", value);
				break;
			case AssociazioneEntitaTDgtRiga.DATA_DOC:
				tipoDocumento.setAttivaDataDoc(TipoDocumentoDigitale.ATTIVA_CLASSIF_FAC);//Fix 17136
				oggDaSettare.set("DataDocumento", formatDate(value));//Fix 35822
				break;
			case AssociazioneEntitaTDgtRiga.CLIENTE:
				tipoDocumento.setAttivaCliente(TipoDocumentoDigitale.ATTIVA_CLASSIF_FAC);//Fix 17136
				oggDaSettare.set("IdCliente", value);
				break;
			case AssociazioneEntitaTDgtRiga.FORNITORE:
				tipoDocumento.setAttivaFornitore(TipoDocumentoDigitale.ATTIVA_CLASSIF_FAC);//Fix 17136
				oggDaSettare.set("IdFornitore", value);
				break;
			case AssociazioneEntitaTDgtRiga.AGENTE:
				tipoDocumento.setAttivaAgente(TipoDocumentoDigitale.ATTIVA_CLASSIF_FAC);//Fix 17136
				oggDaSettare.set("IdAgente", value);
				break;
			case AssociazioneEntitaTDgtRiga.ARTICOLO:
				tipoDocumento.setAttivaArticolo(TipoDocumentoDigitale.ATTIVA_CLASSIF_FAC);//Fix 17136
				oggDaSettare.set("IdArticolo", value);
				break;
			case AssociazioneEntitaTDgtRiga.COMMESSA:
				tipoDocumento.setAttivaCommessa(TipoDocumentoDigitale.ATTIVA_CLASSIF_FAC);//Fix 17136
				oggDaSettare.set("IdCommessa", value);
				break;
			case AssociazioneEntitaTDgtRiga.ATTIVITA:
				tipoDocumento.setAttivaAttivita(TipoDocumentoDigitale.ATTIVA_CLASSIF_FAC);//Fix 17136
				oggDaSettare.set("IdAttivita", value);
				break;
			case AssociazioneEntitaTDgtRiga.TIPO_RIS:
				tipoDocumento.setAttivaRisorsa(TipoDocumentoDigitale.ATTIVA_CLASSIF_FAC);//Fix 17136
				oggDaSettare.set("TipoRisorsa", value);
				break;
			case AssociazioneEntitaTDgtRiga.LIVELLO_RIS:
				tipoDocumento.setAttivaRisorsa(TipoDocumentoDigitale.ATTIVA_CLASSIF_FAC);//Fixi 17136
				oggDaSettare.set("LivelloRisorsa", value);
				break;
			case AssociazioneEntitaTDgtRiga.RISORSA:
				tipoDocumento.setAttivaRisorsa(TipoDocumentoDigitale.ATTIVA_CLASSIF_FAC);//Fix 17136
				oggDaSettare.set("IdRisorsa", value);
				break;
			case AssociazioneEntitaTDgtRiga.RIFDOC_NUM:
				tipoDocumento.setAttivaRifDoc(TipoDocumentoDigitale.ATTIVA_CLASSIF_FAC);
				oggDaSettare.set("RiferimentoDoc", value);
				break;
			case AssociazioneEntitaTDgtRiga.RIFDOC_DATA:
				tipoDocumento.setAttivaRifDataDoc(TipoDocumentoDigitale.ATTIVA_CLASSIF_FAC);
				oggDaSettare.set("RiferimentoDataDoc", formatDate(value));//Fix 35822
				break;
			case AssociazioneEntitaTDgtRiga.DIPENDENTE:
				tipoDocumento.setAttivaDipendente(TipoDocumentoDigitale.ATTIVA_CLASSIF_FAC);//Fix 17136
				oggDaSettare.set("IdDipendente", value);
				break;
			case AssociazioneEntitaTDgtRiga.CENTRO_LAV:
				tipoDocumento.setAttivaCentroLavoro(TipoDocumentoDigitale.ATTIVA_CLASSIF_FAC);//Fix 17136
				oggDaSettare.set("IdCentroLavoro", value);
				break;
			case AssociazioneEntitaTDgtRiga.BENE:
				tipoDocumento.setAttivaBene(TipoDocumentoDigitale.ATTIVA_CLASSIF_FAC);//Fix 17136
				oggDaSettare.set("IdBene", value);
				break;
			case AssociazioneEntitaTDgtRiga.PROSPECT:
				tipoDocumento.setAttivaAnagBase(TipoDocumentoDigitale.ATTIVA_CLASSIF_FAC);
				oggDaSettare.set("IdAnagraficoBase", new Integer(value.toString()));
				break;
			case AssociazioneEntitaTDgtRiga.CONTATTO:
				tipoDocumento.setAttivaContattoWPU(TipoDocumentoDigitale.ATTIVA_CLASSIF_FAC);//Fix 24947
				oggDaSettare.set("IdContattoWPU", new Integer(value.toString()));
				break;
			default:
				if(associaRiga.getClassificazionePers() != null){
					int idx = associaRiga.getClassificazionePers().intValue();
					valorizzaClasiffPers(oggDaSettare, idx, value);//Fix 35421
				}
				break;
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected DisplayObject getCurrentDisplayObject(ServletEnvironment se, String ownerCls, String ownerAd, String ownerKey) {
		DisplayObject ret = null;
		ClassADCollection collection;
		try {
			collection = ClassADCollectionManager.collectionWithName(ownerCls);
			Setting setting = new Setting();
			setting.setClassADCollectionName(ownerCls);
			setting.setLoad(true);
			setting.getSelectedAttributes().add(collection.getAttribute(ownerAd));
			DOList doList = null;
			if (collection.getDOListClassNameWeb() != null)
				doList = (DOList) Factory.createObject(collection.getDOListClassNameWeb());
			else
				doList = (DOList) Factory.createObject(DOList.class);
			if(doList instanceof WebDOList)
				((WebDOList)doList).setServletEnvironment(se);
			ret = doList.getKeyAccessDOForWeb(setting, ownerKey, true);
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public void valorizzaClasiffPers(BODataCollector oggDaSettare,int idx, Object value){
		String classADName = buildClassADName(idx);
		if (idx <= TipoDocumentoAtt.MAX && idx >= TipoDocumentoAtt.MIN) {
			oggDaSettare.set(classADName, value);
		}
		else if (idx == TipoDocumentoAtt.MIN_DATA) {
			//oggDaSettare.set(classADName, getDateValue(value));
			oggDaSettare.set(classADName, formatDate(value));//Fix 35822
		}
		else if (idx == TipoDocumentoAtt.MID_DATA) {
			//oggDaSettare.set(classADName, getDateValue(value));
			oggDaSettare.set(classADName, formatDate(value));//Fix 35822
		}
		else if (idx == TipoDocumentoAtt.MAX_DATA) {
			//oggDaSettare.set(classADName, getDateValue(value));
			oggDaSettare.set(classADName, formatDate(value));//Fix 35822
		}
		else if (idx == TipoDocumentoAtt.MIN_VAL) {
			BigDecimal val = getBigDecimal(oggDaSettare, value, classADName);
			oggDaSettare.set(classADName, val);
		}
		else if (idx == TipoDocumentoAtt.MAX_VAL) {
			BigDecimal val = getBigDecimal(oggDaSettare, value, classADName);
			oggDaSettare.set(classADName, val);
		}
		else if (idx == TipoDocumentoAtt.MIN_CLOB) {
			oggDaSettare.set(classADName, value);
		}
	}

	public String buildClassADName(int idx)
	{
		if (idx <= TipoDocumentoAtt.MAX.intValue())
			return PREFIX + idx;
		else if (idx <= TipoDocumentoAtt.MAX_DATA)
			return PREFIX_DATA + (idx-TipoDocumentoAtt.INI_DATA);
		else if (idx <= TipoDocumentoAtt.MAX_VAL)
			return PREFIX_VAL + (idx-TipoDocumentoAtt.INI_VAL);
		else if (idx <= TipoDocumentoAtt.MAX_CLOB)  //...FIX07296 - DZ
			return PREFIX_CLOB + (idx-TipoDocumentoAtt.INI_CLOB);
		else if (idx > TipoDocumentoAtt.INI_PERSONALIZZAZIONI && idx <= TipoDocumentoAtt.INI_PERSONALIZZAZIONI + TipoDocumentoAtt.INI_DATA)
			return PREFIX + idx;
		else if (idx > TipoDocumentoAtt.INI_PERSONALIZZAZIONI + TipoDocumentoAtt.INI_DATA && idx <= TipoDocumentoAtt.INI_PERSONALIZZAZIONI + TipoDocumentoAtt.INI_VAL)
			return PREFIX_DATA + idx;
		else if (idx > TipoDocumentoAtt.INI_PERSONALIZZAZIONI + TipoDocumentoAtt.INI_VAL && idx <= TipoDocumentoAtt.INI_PERSONALIZZAZIONI + TipoDocumentoAtt.INI_NOTE)
			return PREFIX_VAL + idx;
		else if (idx > TipoDocumentoAtt.INI_PERSONALIZZAZIONI + TipoDocumentoAtt.INI_NOTE)
			return PREFIX_NOTE + idx;
		return null;
	}

	@SuppressWarnings("static-access")
	public  BigDecimal getBigDecimal(BODataCollector oggDaSettare, Object value , String attributeName) {
		BaseBOComponentManager man = oggDaSettare.getComponentManager(attributeName);
		Type type = null;
		BigDecimal val = new BigDecimal("0.00");
		if(man != null){
			type = man.getType();
		}
		if(type != null && type instanceof DecimalType){
			DecimalType dt = new DecimalType();
			String s = dt.unFormat(value.toString());
			s = s.replace(dt.getDecimalSeparator(), '.');
			val = new BigDecimal(s);
		}
		return val;
	}

	public java.sql.Date formatDate(Object value){
		java.sql.Date sqlDate = null;
		String date = null;
		if(value != null){
			date = value.toString();
		}
		if(date != null && !date.trim().equals("")){
			sqlDate = (java.sql.Date)new DateType().stringToObject(date);
		}
		return sqlDate;
	}

}