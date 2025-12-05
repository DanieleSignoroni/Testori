package it.thera.thip.base.comuniVenAcq.web;

import it.testori.thip.base.articolo.YArticoloCosto;
import it.thera.thip.base.articolo.*;
import it.thera.thip.base.azienda.*;
import it.thera.thip.base.cliente.*;
import it.thera.thip.base.comuniVenAcq.*;
import it.thera.thip.base.documenti.web.*;
import it.thera.thip.base.generale.*;
import it.thera.thip.base.interfca.*;
import it.thera.thip.base.partner.Nazione;
import it.thera.thip.datiTecnici.configuratore.*;
import it.thera.thip.magazzino.saldi.*;

import java.math.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.http.*;
//45896
import com.thera.thermfw.base.Q6Calc;
import com.thera.thermfw.base.Trace;
//import com.thera.thermfw.base.*;
//45896
import com.thera.thermfw.common.*;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.type.*;
import com.thera.thermfw.web.*;

/**
 * Classe che verrà passata come attributo alla JSP che effettua il recupero dei
 * dati dell'articolo per le righe di ordini/documenti.
 *
 * <br><br><b>Copyright (c): Thera SpA</b>
 *
 * @author Enrico Masserdotti 08/11/2002
 */
/*
 * Revisions:
 * Number Date        Owner   Description
 *        08/11/2002  ME      Prima stesura
 *        21/03/2003  ME      Aggiunto metodo isArticoloConfigurato
 *        24/03/2003  ME      Aggiunta gestione recupero costo unitario su
 *                            variazione versione/configurazione
 *        17/04/2003  ME      Modificato metodo impostaNuovaConfigurazione
 *        22/04/2003  ME      Inserita nuova gestione unità di misura:
 *                            eliminato metodo impostaUnitaMisura()
 *        14/05/2003  ME      Spostata nella classe figlia per le vendite la
 *                            gestione delle provvigioni dell'agente
 *        19/05/2003  ME      Aggiunto ricalcolo quantità per gestione righe
 *                            ridotte (metodo getQuantitaRicalcolata)
 *        09/06/2003  ME      Inserita gestione gruppo conti analitica su recupero dati articolo
 *        10/06/2003  ME      Aggiunto metodo impostaCostoUnitarioDaArticolo
 *        20/11/2003  ME      Inserita formattazione per HTML delle descrizioni
 * 01327  27/01/2004  ME      Aggiunti parametri iArticoloValido e iErrorMessageArticoloNonValido
 * 01687  18/03/2004  ME      Aggiunto controllo se nel documento esistono righe
 *                            che hanno già l'articolo selezionato
 * 01692  22/03/2004  ME      Ristrutturazione della fix 1687
 * 01724  26/03/2004  ME      Eliminata NullPointerException in
 *                            impostaPercentualePerditaResiduo()
 *        27/05/2004  ME      Importazione in THIP 1.1: modificato metodo
 *                            impostaCostoUnitarioDaArticolo
 * 02167  28/06/2004  ME      Eliminata ServletException
 * 02333  13/07/2004  DB
 * 02563  11/10/2004  ME      Eliminato utilizzo della request
 * 02844  18/11/2004  ME      Aggiunto metodo condizContenitoriOk
 * 03099  11/01/2005  LP      Implementato proposizione e creazione automatica dei lotti.
 * 03246  07/02/2005  ME      Aggiunto metodo serveVerificaRicalcoloQtaFattoreConv
 * 03475  01/04/2005  LP      Aggiunto gestione del lotto unitario (fix 3251 della 1.1.10)
 * 03843  31/05/2005  MG      Modificato metodo impostaCostoUnitarioDaArticolo
 * 04103  27/07/2005  DZ      Aggiunto attributo iValorizzaArtCfg per bottone ValorizzaPrezzo della Config
 * 05276  04/04/2006  PM      Aggiunto attributo ClassName e metodi criptaValore(),
                              criptaCostoUnitario()
 * 07257  08/05/2007  EP      Aggiunto metodo setConfigurazione
 * 08526  16/01/2008  ME      Aggiunto metodo getNomeClassADIdAssoggettamentoIVA()
 * 08597  25/01/2008  MG      Aggiunto parametro FlagDatiCA
 * 10024  03/11/2008  DBot    Corretta gestione id esterno cfg per carattere "
 * 10675  03/04/2009  MM      Reso protected assoggettamentoIVA
 * 10955  17/06/2009  Gscarta modificate chiamate a convertiUM dell'articolo per passare la versione
 * 11194  28/07/2009  DB
 * 11337  18/09/2009  GScarta
 * 12572  13/05/2010  GScarta Nuova gestione numero imballo
 * 14738  29/06/2011  DBot    Integrazione a standard fix 12572
 * 14727  13/07/2011  RA      Aggiunto metodo getDescrizioneExtArticolo
 * 14670  15/07/2011  Linda   Aggiunto il metodo getAssogetamentoIVATestata() e getDocumentoOrdineTestata() e retrieveDocumentoOrdineTestata()
 * 15400  28/12/2011  Linda   Modificato il metodo impostaConfigurazione()
 * 23578  12/05/2016  MBH     Definizione nuovi metodi da usare in smartGrid
 * 24671  16/12/2016  Jackal  Aggiunto gancio per agevolare personalizzazioni: 
 *                            aggiunto metodo impostaDatiPers
 * 20240  13/06/2017  Jackal  Trasformazione di un metodo da protected a public
 *                            per agevolazione personalizzazioni
 * 27128  10/04/2018  Mekki   Descrizione Estesa del'articolo
 * 30871  09/03/2020  SZ	  6 Decimali.	
 * 31522  09/07/2020  SZ	  Aggiunto attributo del Articolo Intestatario ( IdArticoloIntestatario  ,Descrizione Estesa).
 * 32605  22/12/2020  LTB     Aggiungere la recuperazione di descrizione del articolo intestatario se esiste 
 * 32950  17/02/2021  YBA     Decodifica del articolo deve essere sempre da Articolo (non da articoloIntestatario)
 * 33992  27/07/2021  SZ	  Aggiunto attributo	
 * 37846  16/02/2023  SZ	  rendi il metodo impostaCostoUnitario() public	
 * 45896  22/05/2025  SBR     Fix tecnica compatibilita java 1.8
 */

public class DatiArticolo {

  //----------------------------------------------------------------------------

  protected DocumentoOrdineTestata testata=null;//Fix 14670

  protected HttpServletRequest req;


  protected Articolo articolo;


  private SaldoMag sm;


  //----------------------------------------------------------------------------


  private ArticoloVersione versioneCorrente;


  private ArticoloVersione versioneSaldi;

  // Fix 07257 ini
  //private Configurazione configurazione;
  protected Configurazione configurazione;
  // Fix 07257 fin


  protected ArrayList umrIds = new ArrayList();


  protected ArrayList umrDescrs = new ArrayList();


  protected UnitaMisura umrDefault;


  protected UnitaMisura ump;


  protected UnitaMisura ums;

//Fix 10675 MM
  //private AssoggettamentoIVA assoggettamentoIVA;
  protected AssoggettamentoIVA assoggettamentoIVA;

  protected BigDecimal costoUnitario;


  private char iTipoCostoRiferimento;


  private boolean iArticoloValido;


  private ErrorMessage iErrorMessageArticoloNonValido;


  protected GruppoContiCA gruppoContiCA;


  protected BigDecimal prcPerditaRsd;


  protected String[] chiaveDoc;
  
  
  //Fix 2563 - inizio
  protected String parServeDescrizione;  //Completa-ridotta
  protected String parIntestatario;      //Completa-ridotta
  protected String parDivisione;         //Completa-ridotta
  protected String parIdArticolo;        //Completa-ridotta
  protected String parIdMagazzino;       //Completa-ridotta
  protected String parIdListino;         //Ridotta
  protected String parDataVersione;      //Completa-ridotta
  protected String parDataDocumento;     //Ridotta
  protected String parDataConsegna;      //Ridotta
  protected String parIdUMRif;           //Ridotta
  protected String parQtaUMRif;          //Ridotta
  protected String parIdUMPrmMag;        //Ridotta
  protected String parQtaUMPrmMag;       //Ridotta
  protected String parIdValuta;          //Ridotta
  protected String parTipoRiga;          //Completa-ridotta
  protected String parVersConfig;        //Recupero costo unitario
  protected String parIdVersione;        //Recupero costo unitario
  protected String parIdConfigEsterno;   //Recupero costo unitario
  protected String parIdCausaleRiga;     //...FIX 3099
  protected String parOpenType;          //...FIX 3099

  protected String parFlagDatiCA;     //FIX 8597
  //Vendite - inizio
  protected String parIdAgente;
  protected String parIdSubagente;
  protected String parProvvigione1Agente;
  protected String parProvvigione1Subagente;
  protected String parIdModPagamento;
  //Vendite - fine
  //Acquisti - inizio
  protected String parLavEsterna;
  protected String parOperazione;
  //Acquisti - fine
  //Fix 2563 - fine

  //...FIX04103 - DZ
  protected boolean iValorizzaArtCfg = false;

  //Fix 31522 Inizio
  protected String  parIdArticoloIntestatario;
  protected String  parDescrizioneEstesaArtInt;
  protected String  parDescrizioneArtInt;
  //Fix 31522 Fine

  //32605 inizio
  protected char usaDescrizioneArtInt = Column.FALSE_CHAR;
  protected char usaDescrizioneEstesaArtInt = Column.FALSE_CHAR;
  //32605 fine
  
//Fix 33992 Inizio
  protected boolean parGestOriginePref = false;
  protected String parIdNazioneOrgPref = null;
  protected Date parDataFineValOrgPref = null;
//Fix 33992 Fine
  //Fix 33992 Fine
  
  //----------------------------------------------------------------------------

  /**
   * Fix 1687.
   * Sistassi SQL della query che seleziona le righe con articolo uguale
   * all'articolo selezionato.
   * Da ridefinire negli eredi;
   */
  protected String RIGHE_STESSO_ARTICOLO = null;


  /**
   * Fix 1687.
   * CachedStatement
   */
  protected static CachedStatement cRigheStessoArticolo = null;

  //Fix 2333
  protected boolean iAPrezziExtra = false;
  // Fine Fix 2333


// Fix 05276 PM Inizio
  protected String iClassName = "";
// Fix 05276 PM Fine


  //----------------------------------------------------------------------------

  public DatiArticolo() {
  }


  //Fix 2563 - inizio
/*
  public DatiArticolo(HttpServletRequest req) {
    this.req = req;
  }


  public DatiArticolo(HttpServletRequest req,
                      Articolo articolo) {
    this.req = req;
    this.articolo = articolo;

    impostaDatiArticolo();
  }
*/


  //----------------------------------------------------------------------------


//  public void setRequest(HttpServletRequest req) {
//    this.req = req;
//  }
  //Fix 2563 - fine


  public void setArticolo(Articolo articolo) {
    this.articolo = articolo;
  }


  public void impostaDatiArticolo() {
    Trace.print("SONO IN impostaDatiArticolo di DatiArticolo");

    try {
      impostaVersioni();
      impostaConfigurazione();
      impostaUnitaMisura();
      impostaAssoggettamentoIVA();
      impostaCostoUnitario();
      impostaGruppoContiCA();
      // Fix 2333
      impostaPrezziExtra();
      // Fine Fix 2333
      impostaOriginePreferenziale();
    }
    catch (Exception ex) {
      Trace.print("Exception in DatiArticolo.impostaDatiArticolo()");
      ex.printStackTrace();
    }
  }


  public void impostaVersioni() throws Exception {
     // fix 12572 >
     Integer iparVer = null;
     boolean isVersioneEstesa = this.getArticolo().hasVersioneEstesa() && this.versioneCorrente == null &&
     this.getParIdVersione() != null && !this.getParIdVersione().trim().equals("");
     if (isVersioneEstesa) {
        try {
           iparVer = new Integer(this.getParIdVersione());
        }
        catch (NumberFormatException ex) {
           isVersioneEstesa = false;
           ex.printStackTrace(Trace.excStream);
        }
     }
     // fix 12572 <

     // fix 11337 >
     if (isVersioneEstesa && iparVer != null) { // fix 12572        
  		List versioni = articolo.getArticoloDatiVersioni().getVersioni();
  		Iterator iter = versioni.iterator();
  		while (iter.hasNext()) {
  			ArticoloVersione versioneTmp = (ArticoloVersione)iter.next();
  			if (versioneTmp.getIdVersione().compareTo(iparVer) == 0) {
  				versioneCorrente = versioneTmp;
  				versioneSaldi = versioneTmp;
  				break;
  			}
  		}
  	}
  	else {
    // fix 11337 <
  		DateType dateType = new DateType();
	    //Fix 2563 - inizio
	    String paramValue = getParDataVersione();
	    //Fix 2563 - fine
	
	    Date dataVersione =
	      (paramValue == null || paramValue.length() == 0) ?
	      new Date(Calendar.getInstance().getTime().getTime()) :
	      (Date)dateType.stringToObject(paramValue);
	    versioneCorrente = articolo.getVersioneAtDate(dataVersione);
	    versioneSaldi = versioneCorrente.getVersioneSaldi();
  	} // fix 11337
  }

  public void impostaConfigurazione() throws Exception {
  	// fix 11337 >
  	if (this.getArticolo().hasVersioneEstesa() && this.configurazione == null && this.getParIdConfigEsterno() != null) {
  		impostaNuovaConfigurazione(this.getParIdConfigEsterno(), this.getArticolo().getIdArticolo());
  	}
  	else {
    // fix 11337 >
  	if(configurazione == null){//Fix 15400
  		Integer idConfigurazione = articolo.getIdConfigurazioneStd();
  		if (idConfigurazione != null) {
  			String key =
	        KeyHelper.buildObjectKey(
	          new Object[] {
	            Azienda.getAziendaCorrente(),
	            idConfigurazione
	          }
	        );
	      configurazione = 
	      	Configurazione.elementWithKey(key, PersistentObject.NO_LOCK);
	    }  
  	}//Fix 15400
    } // fix 11337
  }

  /**
   * Metodo che deve essere ridefinito nelle classi eredi
   */
  protected void impostaUnitaMisura() throws Exception {
  }


  protected void impostaAssoggettamentoIVA() throws Exception {

    assoggettamentoIVA = articolo.getAssoggettamentoIVA();
  }


  /**
   * Metodo che deve essere ridefinito nelle classi eredi
   */
  //protected void impostaCostoUnitario() throws Exception {//Fix 37846
  public void impostaCostoUnitario() throws Exception {//Fix 37846
  }


  /**
   * Metodo che imposta il gruppo conti contabilità analitica
   */
  protected void impostaGruppoContiCA() throws Exception {
    gruppoContiCA = articolo.getRiferimVociCA().getGruppoConti();
  }


  /**
   * Metodo che imposta la percentuale perdita residuo.
   * Questo valore deve essere impostato solo se l'articolo ha una linea
   * prodotto.
   * Questo metodo viene richiamato dalle classi eredi specifiche per le righe
   * d'ordine di vendita/acquisto
   */
  public void impostaPercentualePerditaResiduo() throws Exception {
    if (articolo != null) {    //Fix 1724
      LineaProdotto lineaProdotto = articolo.getLineaProdotto();
      if (lineaProdotto != null) {
        prcPerditaRsd = lineaProdotto.getPrcPerditaRsd();
      }
    }
  }


  protected void impostaCostoUnitarioSaldiMagazzino(char tipoCostoRiferimento)
            throws Exception {
    Trace.print("impostaCostoUnitarioSaldiMagazzino-->>"+tipoCostoRiferimento);
    String idConfigurazione = getIdConfigurazione();
    idConfigurazione = (idConfigurazione.length() == 0) ? "0" : idConfigurazione;

    String key =
      KeyHelper.buildObjectKey(
        new String[] {
          Azienda.getAziendaCorrente(),
          getParIdMagazzino(),  //Fix 2563
          articolo.getIdArticolo(),
          getIdVersioneSaldi(),
          idConfigurazione,
          SaldoMag.OPERAZIONE_DUMMY
        }
      );

    Trace.print("chiave saldomag = " + key);
    SaldoMag saldoMag = SaldoMag.elementWithKey(key, PersistentObject.NO_LOCK);
    Trace.print("saldoMag="+saldoMag);
    if (saldoMag != null) {
      DatiSaldo datiSaldo = saldoMag.getDatiSaldo();

      switch (tipoCostoRiferimento) {
        case TipoCostoRiferimento.COSTO_STANDARD:
          costoUnitario = datiSaldo.getCostoStandard();
          break;
        case TipoCostoRiferimento.COSTO_MEDIO:
          costoUnitario = datiSaldo.getCostoMedio();
          break;
        case TipoCostoRiferimento.COSTO_ULTIMO:
          costoUnitario = datiSaldo.getCostoUltimo();
          break;
      }
    }
    else {
      costoUnitario = new BigDecimal(0.0);
    }

    Trace.print("costo unitario="+costoUnitario);
  }


  protected void impostaCostoUnitarioDaArticolo(String idTipoCosto)
            throws Exception {
    costoUnitario = new BigDecimal(0.0);
//MG FIX 3843
    Integer idVersione = (versioneCorrente == null) ? ContenitoreRiga.VERSIONE_DUMMY : versioneCorrente.getIdVersione();
    Integer idConfigurazione = (configurazione == null) ? null : configurazione.getIdConfigurazione();
//MG FIX 3843
    ArticoloCosto articoloCosto =
      ArticoloCosto.elementWithKey(
        Azienda.getAziendaCorrente(),
        articolo.getIdArticolo(),
        idVersione,
        idConfigurazione,
        idTipoCosto
      );
    //72242 Inizio
    if(articoloCosto instanceof YArticoloCosto
    		&& ((YArticoloCosto) articoloCosto).getRilevatoErrore()) {
    	return;
    }
    //72242 Fine
    if (articoloCosto != null) {
      costoUnitario = articoloCosto.getCosto();
    }
  }


  /**
   * Viene richiamato se viene variata la versione
   *
   * @param idVersione Id inserito della nuova versione
   */
  public void impostaNuoveVersioni(String idVersione) {
    try {
      String key =
        KeyHelper.buildObjectKey(
          new String[] {
            Azienda.getAziendaCorrente(),
            articolo.getIdArticolo(),
            idVersione
          }
        );

      versioneCorrente =
        ArticoloVersione.elementWithKey(key, PersistentObject.NO_LOCK);
      versioneSaldi =
        (versioneCorrente == null) ? null : versioneCorrente.getVersioneSaldi();
      // fix 11337 >
      if (this.getArticolo().hasVersioneEstesa()) {
      	versioneSaldi = versioneCorrente;
      }
      // fix 11337 <
    }
    catch (Exception ex) {
      Trace.print("Exception in DatiArticolo.impostaNuovaVersione()");
      ex.printStackTrace();
    }
  }


  /**
   * Viene richiamato se viene variata la configurazione
   *
   * @param idConfigEsterno Id esterno (leggibile) della nuova configurazione
   * @param idArticolo Id dell'articolo legato alla nuova configurazione
   */
  public void impostaNuovaConfigurazione(String idConfigEsterno, String idArticolo) {
    try {
      String where =
        ConfigurazioneTM.ID_AZIENDA + "='" + Azienda.getAziendaCorrente() + "' AND " +
        ConfigurazioneTM.COD_CONFIG + "='" + idConfigEsterno + "' AND " +
        ConfigurazioneTM.R_ARTICOLO + "='" + idArticolo + "'";
      Vector v = Configurazione.retrieveList(where, "", false);
      configurazione = v.isEmpty() ? null : (Configurazione)v.elementAt(0);
    }
    catch (Exception ex) {
      Trace.print("Exception in DatiArticolo.impostaNuovaConfigurazione()");
      ex.printStackTrace();
    }
  }

  
  //Fix 24671 - inizio
  public void impostaDatiPers() {
	  
  }
  //Fix 24671 - fine
  
  //----------------------------------------------------------------------------

//32605 Inizio
  public String getDescrizioneArtEstesa() {
	  //String ret = articolo.getDescrizioneArticoloNLS().getDescrizioneEstesa();
	  //return (ret == null) ? "" : WebElement.formatStringForHTML(ret);
	  
	  if(getDescrizioneEstesaArtInt() != null)
		  return WebElement.formatStringForHTML(getDescrizioneEstesaArtInt());
	  else {
		  String ret = articolo.getDescrizioneArticoloNLS().getDescrizioneEstesa();
		  return (ret == null) ? "" : WebElement.formatStringForHTML(ret);
	  }
  }
  
  //Fix 32950 inizio
  public String getDescrizioneDecodificaArticolo() {  
	  String ret = articolo.getDescrizioneArticoloNLS().getDescrizione();
	  return (ret == null) ? "" : WebElement.formatStringForHTML(ret);
  }
  //Fix 32950 inizio
  
  public String getDescrizioneArticolo() {
	  /*String ret = articolo.getDescrizioneArticoloNLS().getDescrizione();
	    return (ret == null) ? "" : WebElement.formatStringForHTML(ret);*/

	  if(getDescrizioneArtInt() != null)
		  return WebElement.formatStringForHTML(getDescrizioneArtInt());
	  else {
		  String ret = articolo.getDescrizioneArticoloNLS().getDescrizione();
		  return (ret == null) ? "" : WebElement.formatStringForHTML(ret);
	  }
  }
  //32605 Fine
  
  
  //Fix 23578 Inizio
  public String getDescrizioneArticoloUnformatted() {
	  if(getDescrizioneArtInt() != null) //32605
		  return getDescrizioneArtInt(); //32605
	  else {
		  String ret = articolo.getDescrizioneArticoloNLS().getDescrizione();
		  return (ret == null) ? "" : ret;
	  }
  }
  //Fix 23578 Fine
  
  //----------------------------------------------------------------------------
  //Fix14727 Inizio RA
  public String getDescrizioneExtArticolo() throws SQLException{
    return "" ;
  }

  public boolean isEm(String tmp){
    return (tmp != null && !tmp.equals("")) ? false :true;
  }
  //Fix14727 Fine RA
  //----------------------------------------------------------------------------

  public ArticoloVersione getVersioneCorrente() {
    return versioneCorrente;
  }


  public String getIdVersioneCorrente() {
    String ret = (versioneCorrente == null) ?
                    "" :
                    versioneCorrente.getIdVersione().toString();
    return ret;
  }


  public String getDescrVersioneCorrente() {
    String ret =
      (versioneCorrente == null) ?
        "" :
        WebElement.formatStringForHTML(
          versioneCorrente.getDescrizione().getDescrizione()
        );
    return ret;
  }


  public ArticoloVersione getVersioneSaldi() {
    return versioneSaldi;
  }


  public String getIdVersioneSaldi() {
    Integer idVersioneSaldi = versioneCorrente.getIdVersioneSaldi();
    if (idVersioneSaldi == null) {
      idVersioneSaldi = versioneCorrente.getIdVersione();
    }
    return idVersioneSaldi.toString();
  }

  // Fix 07257 ini
  public void setConfigurazione(Configurazione newConfigurazione) {
    configurazione = newConfigurazione;
  }
  // Fix 07257 fin


  public Configurazione getConfigurazione() {
    return configurazione;
  }

  public String getIdConfigurazione() {
    String ret = (configurazione == null) ?
                    "" :
                    configurazione.getIdConfigurazione().toString();
    return ret;
  }


  public String getIdEsternoConfigurazione() {
    String ret = (configurazione == null) ?
                    "" :
                    configurazione.getIdEsternoConfig();
    return ret;
  }

  //Fix 10024 inizio
  public String getIdEsternoConfigurazioneHTML()
  {
     String ret = "";
     if(configurazione != null)
        ret = WebElement.formatStringForHTML(configurazione.getIdEsternoConfig());
     return ret;
  }
  //Fix 10024 fine


  public String getDescrConfigurazione() {
    String ret =
      (configurazione == null) ?
        "" :
        WebElement.formatStringForHTML(
          configurazione.getDescrizione().getDescrizione()
        );
    return ret;
  }

  //...FIX 3099 inizio

  /**
   * FIX04103 - DZ
   * @return boolean
   */
  public boolean getValorizzaArtCfg() {
    boolean ret = false;
    if (configurazione != null && configurazione.getSchemaCfg() != null)
      ret = configurazione.getSchemaCfg().getValorizzaConfig();
    return ret;
  }

  /**
   * setParIdCausaleRiga
   * @param idCausale String
   */
  public void setParIdCausaleRiga(String idCausale) {
    parIdCausaleRiga = idCausale;
  }

  /**
   * getParIdCausaleRiga
   * @return String
   */
  public String getParIdCausaleRiga() {
    return parIdCausaleRiga;
  }

  /**
   * setParOpenType
   * @param openType String
   */
  public void setParOpenType(String openType) {
    parOpenType = openType;
  }

  /**
   * getParOpenType
   * @return String
   */
  public String getParOpenType() {
    return parOpenType;
  }

  /**
   * getChiaveDocumento
   * @return String[]
   */
  public String[] getChiaveDocumento() {
    return chiaveDoc;
  }

  //...FIX 3099 fine

  public UnitaMisura getUMPrimaria() {
    return ump;
  }


  public String getIdUMPrimaria() {
    String ret = (ump == null) ? "" : ump.getIdUnitaMisura();
    return ret;
  }


  public String getDescrUMPrimaria() {
    String ret =
      (ump == null) ?
        "" :
        WebElement.formatStringForHTML(ump.getDescrizione().getDescrizione());
    return ret;
  }


  public String getDescrCompletaUMPrimaria() {
    String ret = getIdUMPrimaria() + " - " + getDescrUMPrimaria();
    return WebElement.formatStringForHTML(ret);
  }


  public UnitaMisura getUMSecondaria() {
    return ums;
  }


  public String getIdUMSecondaria() {
    String ret = (ums == null) ? "" : ums.getIdUnitaMisura();
    return ret;
  }


  public String getDescrUMSecondaria() {
    String ret =
      (ums == null) ?
        "" :
        WebElement.formatStringForHTML(ums.getDescrizione().getDescrizione());
    return ret;
  }


  public String getDescrCompletaUMSecondaria() {
    String ret = getIdUMSecondaria() + " - " + getDescrUMSecondaria();
    return WebElement.formatStringForHTML(ret);
  }


  public UnitaMisura getUMRiferimentoDefault() {
    return umrDefault;
  }


  public String getIdUMRiferimentoDefault() {
    String ret = (umrDefault == null) ? "" : umrDefault.getIdUnitaMisura();
    return ret;
  }


  public ArrayList getListaIdUMRiferimento() {
    return umrIds;
  }


  public ArrayList getListaDescrUMRiferimento() {
    return umrDescrs;
  }


  public AssoggettamentoIVA getAssoggettamentoIVA() {
    return assoggettamentoIVA;
  }


  public String getIdAssoggettamentoIVA() {
    //Fix 8244 PM Inizio
    //String ret = (assoggettamentoIVA == null) ?
    //              "" :
    //              assoggettamentoIVA.getIdAssoggettamentoIVA();
    //return ret;

    String id = "";
    if (assoggettamentoIVA != null)
        id = assoggettamentoIVA.getIdAssoggettamentoIVA();
    //Fix 8526 - inizio
//    return criptaValore(getClassName(), "IdAssoggettamentoIVA", id);
    return criptaValore(getClassName(), getNomeClassADIdAssoggettamentoIVA(), id);
    //Fix 8526 - fine

    //Fix 8244 PM Fine
  }


  public String getDescrAssoggettamentoIVA() {
      //Fix 8244 PM Inizio
    /*String ret =
      (assoggettamentoIVA == null) ?
        "" :
        WebElement.formatStringForHTML(
          assoggettamentoIVA.getDescrizione().getDescrizione()
        );
    return ret;
    */
   String ret = "";
   boolean adNegato = false;
   try
   {
     	 //Fix 8526 - inizio
//       adNegato = DeniedAttributeManager.isDenied(getClassName(), "IdAssoggettamentoIVA");
       adNegato = DeniedAttributeManager.isDenied(getClassName(), getNomeClassADIdAssoggettamentoIVA());
     	 //Fix 8526 - fine
   }
   catch (Exception ex1)
   {}
   if (adNegato)
     ret = "*****";
   else if (assoggettamentoIVA != null)
     ret = WebElement.formatStringForHTML(assoggettamentoIVA.getDescrizione().getDescrizione());
   return ret;
  //Fix 8244 PM Fine
  }


  public String getCostoUnitario() {
    DecimalType dt = new DecimalType();
    //Fix 2563 - inizio
    dt.setScale(6);
    //Fix 2563 - fine

    String ret = (costoUnitario == null) ?
                  "" :
                  dt.objectToString(costoUnitario);
  // Fix 05276 PM Inzio
     ret = criptaCostoUnitario(ret);
  // Fix 05276 PM Fine

    return ret;
  }


  //Fix 2563 - inizio
  public BigDecimal getCostoUnitarioNumerico() {
    return costoUnitario;
  }
  //Fix 2563 - fine


  public GruppoContiCA getGruppoContiCA() {
    return gruppoContiCA;
  }


  public String getIdGruppoContiCA() {
    String ret = (gruppoContiCA == null) ?
                  "" :
                  gruppoContiCA.getIdGruppoConti();
    return ret;
  }


  public String getDescrGruppoContiCA() {
    String ret =
      (gruppoContiCA == null) ?
        "" :
        WebElement.formatStringForHTML(
          gruppoContiCA.getDescrizione().getDescrizione()
        );
    return ret;
  }


  public String getPercentualePerditaResiduo() {
    DecimalType dt = new DecimalType();
    dt.setScale(2);
    String ret =
        (prcPerditaRsd == null) ?
        null :
        dt.format(dt.objectToString(prcPerditaRsd));
    return ret;
  }


  //Fix 2563 - inizio
  public BigDecimal getPercentualePerditaResiduoNumerico() {
    return prcPerditaRsd;
  }
  //Fix 2563 - fine


  public String isArticoloLotto() {
    return (articolo.isArticLotto()) ? "Y" : "N";
  }

  //...FIX 3475 inizio
  /**
   * isLottoUnitario
   * @return String
   */
  public String isLottoUnitario() {
    return (articolo.getArticoloDatiMagaz().isLottoUnitario()) ? "Y" : "N";
  }
  //...FIX 3475 fine

  public boolean isDescrizioneNecessaria() {
    //Fix 2563 - inizio
    return getParServeDescrizione().equalsIgnoreCase("Y");
    //Fix 2563 - fine
  }


  public boolean isArticoloConfigurato() {
    return articolo.isConfigurato();
  }


  public boolean isVersioneVariata() {
    //Fix 2563 - inizio
    return getParVersConfig().equalsIgnoreCase("V");
    //Fix 2563 - fine
  }


  public boolean isConfigurazioneVariata() {
    //Fix 2563 - inizio
    return getParVersConfig().equalsIgnoreCase("C");
    //Fix 2563 - fine
  }


  public void setTipoCostoRiferimento(char c) {
    iTipoCostoRiferimento = c;
  }


  public char getTipoCostoRiferimento() {
    return iTipoCostoRiferimento;
  }


  public void setArticoloValido(boolean b) {
    iArticoloValido = b;
  }


  public boolean isArticoloValido() {
    return iArticoloValido;
  }


  public void setErrorMessageArticoloNonValido(ErrorMessage em) {
    iErrorMessageArticoloNonValido = em;
  }


  public ErrorMessage getErrorMessageArticoloNonValido() {
    return iErrorMessageArticoloNonValido;
  }


  public void setChiaveDocumento(String[] chiaveDoc) {
    this.chiaveDoc = chiaveDoc;
  }


  public String getQuantitaRicalcolata(BigDecimal quant,
                                       UnitaMisura umOrigine,
                                       UnitaMisura umDestinazione) {
    DecimalType dt = new DecimalType();
    //dt.setScale(2);//Fix 30871
	dt.setScale(Q6Calc.get().scale(2));//Fix 30871
    BigDecimal quantRicalcolata = null;
    if (umDestinazione == null)
      quantRicalcolata = new BigDecimal(0.0);
    else {
      // fix 11194
      //quantRicalcolata = articolo.convertiUM(quant, umOrigine, umDestinazione,
      //                                       getVersioneSaldi()); // fix 10955
      quantRicalcolata = articolo.convertiUM(quant, umOrigine, umDestinazione,
                                             this.getVersioneCorrente());
      // fine fix 11194
    }
    /*
    quantRicalcolata = (umDestinazione == null) ?
                       new BigDecimal(0.0) :
                       articolo.convertiUM(quant, umOrigine, umDestinazione);
    */
    return dt.format(dt.objectToString(quantRicalcolata));
  }


  //Fix 2167 - inizio
  public Articolo getArticolo() {
    return articolo;
  }
  //Fix 2167 - fine
  //Fix 2333
  public void impostaPrezziExtra(){
  }

  public boolean isAPrezziExtra(){
    return iAPrezziExtra;
  }
  public void setAPrezziExtra(boolean b){
    iAPrezziExtra = b;
  }
  // Fine Fix 2333



  //Fix 2563 - inizio
  public void setParServeDescrizione(String s) {
    parServeDescrizione = s;
  }


  public String getParServeDescrizione() {
    return parServeDescrizione;
  }


  public void setParIntestatario(String s) {
    parIntestatario = s;
  }


  public String getParIntestatario() {
    return parIntestatario;
  }


  public void setParDivisione(String s) {
    parDivisione = s;
  }


  public String getParDivisione() {
    return parDivisione;
  }


  public void setParIdArticolo(String s) {
    parIdArticolo = s;
  }


  public String getParIdArticolo() {
    return parIdArticolo;
  }


  public void setParIdMagazzino(String s) {
    parIdMagazzino = s;
  }


  public String getParIdMagazzino() {
    return parIdMagazzino;
  }


  public void setParIdListino(String s) {
    parIdListino = s;
  }


  public String getParIdListino() {
    return parIdListino;
  }


  public void setParDataVersione(String s) {
    parDataVersione = s;
  }


  public String getParDataVersione() {
    return parDataVersione;
  }


  public void setParDataDocumento(String s) {
    parDataDocumento = s;
  }


  public String getParDataDocumento() {
    return parDataDocumento;
  }


  public void setParDataConsegna(String s) {
    parDataConsegna = s;
  }


  public String getParDataConsegna() {
    return parDataConsegna;
  }


  public void setParIdUMRif(String s) {
    parIdUMRif = s;
  }


  public String getParIdUMRif() {
    return parIdUMRif;
  }


  public void setParQtaUMRif(String s) {
    parQtaUMRif = s;
  }


  public String getParQtaUMRif() {
    return parQtaUMRif;
  }


  public void setParIdUMPrmMag(String s) {
    parIdUMPrmMag = s;
  }


  public String getParIdUMPrmMag() {
    return parIdUMPrmMag;
  }


  public void setParQtaUMPrmMag(String s) {
    parQtaUMPrmMag = s;
  }


  public String getParQtaUMPrmMag() {
    return parQtaUMPrmMag;
  }


  public void setParIdValuta(String s) {
    parIdValuta = s;
  }


  public String getParIdValuta() {
    return parIdValuta;
  }


  public void setParTipoRiga(String s) {
    parTipoRiga = s;
  }


  public String getParTipoRiga() {
    return parTipoRiga;
  }

//MG FIX 8597 inizio
  public void setParFlagDatiCA(String s) {
    parFlagDatiCA = s;
  }

  public String getParFlagDatiCA() {
    return parFlagDatiCA;
  }
//MG FIX 8597 fine


  public void setParVersConfig(String s) {
    parVersConfig = s;
  }


  public String getParVersConfig() {
    return parVersConfig;
  }


  public void setParIdVersione(String s) {
    parIdVersione = s;
  }


  public String getParIdVersione() {
    return parIdVersione;
  }


  public void setParIdConfigEsterno(String s) {
    parIdConfigEsterno = s;
  }


  public String getParIdConfigEsterno() {
    return parIdConfigEsterno;
  }


  public void setParIdAgente(String s) {
    parIdAgente = s;
  }


  public String getParIdAgente() {
    return parIdAgente;
  }


  public void setParIdSubagente(String s) {
    parIdSubagente = s;
  }


  public String getParIdSubagente() {
    return parIdSubagente;
  }


  public void setParProvvigione1Agente(String s) {
    parProvvigione1Agente = s;
  }


  public String getParProvvigione1Agente() {
    return parProvvigione1Agente;
  }


  public void setParProvvigione1Subagente(String s) {
    parProvvigione1Subagente = s;
  }


  public String getParProvvigione1Subagente() {
    return parProvvigione1Subagente;
  }


  public void setParIdModPagamento(String s) {
    parIdModPagamento = s;
  }


  public String getParIdModPagamento() {
    return parIdModPagamento;
  }


  public void setParLavEsterna(String s) {
    parLavEsterna = s;
  }


  public String getParLavEsterna() {
    return parLavEsterna;
  }


  public void setParOperazione(String s) {
    parOperazione = s;
  }


  public String getParOperazione() {
    return parOperazione;
  }
  //Fix 2563 - fine


  //Fix 2844 - inizio
  /**
   * Verifica se esistono le condizioni per abilitare il tab 'Contenitori'.
   * Da ridefinire negli eredi
   */
  public String condizContenitoriOk() {
    return null;
  }
  //Fix 2844 - fine


  //Fix 3246 - inizio
  /**
   * Verifica se deve abilitare il flag di ricalcolo quantità secondo il fattore
   * di conversione.
   * Lo abilita solo quando è stato scelto un articolo che ha almeno due unità
   * di misura diverse.
   * Vale solo per le righe in modalità completa.
   */
  public boolean abilitaRicalcoloQtaFattoreConv() {
    boolean ret = true;

    UnitaMisura umRif = getUMRiferimentoDefault();
    UnitaMisura umPrm = getUMPrimaria();
    UnitaMisura umSec = getUMSecondaria();

    if (umRif != null && umPrm != null) {
      ret = umRif.equals(umPrm);
      if (ret) {
        if (umSec != null) {
          ret = umRif.equals(umSec);
        }
      }
    }

    return !ret;
  }
  //Fix 3246 - fine

// Fix 05276 PM Inizio
  protected String getClassName()
  {
      return iClassName;
  }

  protected void setClassName(String className)
  {
      iClassName = className;
  }


  protected String criptaCostoUnitario(String costoUnitario)
  {
      return criptaValore(getClassName(), "CostoUnitario", costoUnitario);
  }

  public static String criptaValore(String className, String classAD, String valore)
  {
      // Fix 8244 PM Inizio
      /*
      String ret = valore;
      if (className != null && !className.equals(""))
          ret = DeniedAttributeManager.encryptIfNeeded(className, classAD, valore);
      return ret;
      */
      return DocumentoDatiSessione.criptaSeNecessario(className, classAD, valore);
      // Fix 8244 PM Fine
  }

// Fix 05276 PM Fine

  //Fix 8526 - inizio
  public String getNomeClassADIdAssoggettamentoIVA() {
  	return "IdAssoggettamentoIVA";
  }
  //Fix 8526 - fine

  // fix 11337 >
  public void setVersioneCorrente(ArticoloVersione versione) {
    versioneCorrente = versione;  	
  }
  // fix 11337 <

  //Fix 14670 inizio
  public AssoggettamentoIVA getAssogetamentoIVATestata() {
    try {
      testata = getDocumentoOrdineTestata();
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }
    if (testata != null)
      return testata.getAssoggettamentoIVA();
    return null;
  }

  //Fix 20240 - inizio
//  protected DocumentoOrdineTestata getDocumentoOrdineTestata() throws SQLException {
  public DocumentoOrdineTestata getDocumentoOrdineTestata() throws SQLException {
  //Fix 20240 - fine
    if (testata == null)
      testata = retrieveDocumentoOrdineTestata();
    return testata;
  }

  protected DocumentoOrdineTestata retrieveDocumentoOrdineTestata() throws SQLException {
    return null;
  }

  //Fix 14670 fine
  
  //Fix 27128 --inizio
  public String getDescrizioneEstesaArticolo() {
	  //32605 inizio
	   /* String ret = articolo.getDescrizioneArticoloNLS().getDescrizioneEstesa();
	    return (ret == null) ? "" : WebElement.formatStringForHTML(ret);*/
	  
	  if(getDescrizioneEstesaArtInt() != null)
		  return WebElement.formatStringForHTML(getDescrizioneEstesaArtInt());
	  else {
		  String ret = articolo.getDescrizioneArticoloNLS().getDescrizioneEstesa();
		  return (ret == null) ? "" : WebElement.formatStringForHTML(ret);
	  }
	//32605 fine
  }
  //Fix 27128 --fine
  //Fix 31522 Inizio
  public void setIdArticoloIntestatario(String s) {
	  	parIdArticoloIntestatario = s;
	  }


  public String getIdArticoloIntestatario() {
	    return parIdArticoloIntestatario;
	  }
	  
  public void setDescrizioneEstesaArtInt(String s) {
	  	parDescrizioneEstesaArtInt = s;
	  }


  public String getDescrizioneEstesaArtInt() {
	    return parDescrizioneEstesaArtInt;
	  }
  
  public void setDescrizioneArtInt(String s) {
	  	parDescrizioneArtInt = s;
	  }


  public String getDescrizioneArtInt() {
	    return parDescrizioneArtInt;
	  }
  
  
  //Fix 31522 Fine
  
  //Fix 32605 inizio
  public void setUsaDescrizioneEstesaArtInt(char s) {
	  usaDescrizioneEstesaArtInt = s;
  }

  public char isUsaDescrizioneEstesaArtInt() {
	  return usaDescrizioneEstesaArtInt;
  }

  public void setUsaDescrizioneArtInt(char s) {
	  usaDescrizioneArtInt = s;
  }

  public char isUsaDescrizioneArtInt() {
	  return usaDescrizioneArtInt;
  }
  //Fix 32605 fine
  
  //Fix 33992 Inizio
  
	public boolean isGestOriginePref() {
		return parGestOriginePref;
	}
	public void setGestOriginePref(boolean gestOriginePref) {
		this.parGestOriginePref = gestOriginePref;
	}
	
	public void setIdNazioneOrgPref(String s) {
		parIdNazioneOrgPref = s;
	}

    public String getIdNazioneOrgPref() {
		   return parIdNazioneOrgPref;
	}
	  
	public Date getDataFineValOrgPref() {

			return parDataFineValOrgPref;
		}
	public void setDataFineValOrgPref(Date dataFineValOrgPref) {
			this.parDataFineValOrgPref = dataFineValOrgPref;
	}
	
	public void impostaOriginePreferenziale() {
		if(this.articolo != null && this.articolo.getArticoloDatiVendita() != null ) {
			setGestOriginePref(this.articolo.getArticoloDatiVendita().isGestOriginePref());
			setIdNazioneOrgPref(this.articolo.getArticoloDatiVendita().getIdNazioneOrgPref());
			setDataFineValOrgPref(this.articolo.getArticoloDatiVendita().getDataFineValOrgPref());
		}
	}
  //Fix 33992 Fine
}
