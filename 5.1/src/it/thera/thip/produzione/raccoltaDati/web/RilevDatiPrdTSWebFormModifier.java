package it.thera.thip.produzione.raccoltaDati.web;

import it.thera.thip.base.articolo.*;
import it.thera.thip.base.azienda.*;
import it.thera.thip.base.commessa.*;
import it.thera.thip.base.profilo.*;
import it.thera.thip.base.risorse.*;
import it.thera.thip.cs.*;
import it.thera.thip.cs.web.*;
import it.thera.thip.magazzino.generalemag.*;
import it.thera.thip.magazzino.saldi.*;
import it.thera.thip.produzione.documento.*;
import it.thera.thip.produzione.ordese.*;
import it.thera.thip.produzione.raccoltaDati.*;

import java.io.*;
import java.lang.reflect.*;
import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.servlet.jsp.*;

import com.thera.thermfw.base.*;
import com.thera.thermfw.common.*;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.security.*;
import com.thera.thermfw.type.*;
import com.thera.thermfw.web.*;
import com.thera.thermfw.web.servlet.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author Mekki
 * @version 1.0
 * 12841     15/07/2010    Mekki  Aggiungere azione prossima attività
 * 12974     26/07/2010    Mekki  Ulteriori modifiche
 * 13082     24/08/2010    Mekki  Ulteriori modifiche
 * 13175     17/09/2010    Mekki  Ulteriori modifiche
 * 13264     29/09/2010    Mekki  Ammbiente assoiciato all'utente di login e arrotondare l'ore inizio/fine
 * 13434     25/10/2010    GN     Ulteriori modifiche
 * 13573     14/12/2010    Amara  Nascondere il button Stampa Bolla
 * 13574     20/12/2010    Hammadi Ulteriori modifiche Per mostrare la rilevazione dati 'touch screen' coerente con la risoluzione scelta
 * 14721     12/07/2011    Amara  Aggiungere azione ridotta
 * 14722     29/08/2011    Amara  Aggiungere azione dettaglio
 * 15030     21/09/2011    Amara  Ulteriori modifiche
 * 15087     21/09/2011    GN     Correzione per supporto a dichiarazioni di bolle cucite
 * 15064     14/10/2011    GN     Correzione gestione lotti
 * 15217     25/10/2011    GN     Correzioni relative alla gestione delle righe prelievo e versamento
 * 14725     14/11/2011    Mekki  Aggiungere nel monitor della raccolta dati di produzione il codice articolo, la descrizione e la quantità residua dell'attività di produzione.
 * 15250     30/11/2011    HBT    Ulteriori modifiche
 * 15582     10/01/2012    GN     Completamento fix 15250
 * 15866     21/02/2012    HBT    gestione campi lotti
 * 16109     26/03/2012    GN     Correzioni per gestione errori, pagina indietro e doppia UM
 * 16528     19/06/2012    GN     Corretta proposizione quantità per prodotti non primari
 * 16700     30/07/2012    Mekki  La quantità proposta negli sfridi/rottami e nei sottoprodotti deve considerare la quantità base.
 * 16734     20/08/2012    HBT    Ulteriori modifiche
 * 16735     19/09/2012    HBT    Emetto un messaggio di warning quando, in caso di BloccoInizioAtv impostato a true sulla relativa causale di rilevazione e l'attività precedente non è ancora iniziata
 * 16333     13/07/2012    OC     Copio lo stato saldo nel RilevazioneDatiProdTes
 * 16741     06/09/2012    HBT    gestione rilevazione fermo macchina
 * 17241     14/12/2012    GN     Nei versamenti aggiunta gestione wip con lotti
 * 17550     07/03/2013    GN     Aggiunta gestione righe di scarto
 * 17726     12/04/2013    AA     Correzioni la recuperazione dei prodotti
 * 17729     30/04/2013    AA     I buttoni relativi alle causali siano aggiunti solo se VisualizzaInRilev = true
 * 17808     08/05/2013    OC     Impostare la matricola associata al dichiarante nel caso di rosrsa umane
 * 17815     10/05/2013    AA     Modificato la descrizione : visualizza la desc. della riga prodotto
 * 17955     05/06/2013    OC     Modificato il metodo displayErrori
 * 17963     06/06/2013    AA     Se per una risorsa umana esiste già un'attività in corso un errore forzabile è emessa
 * 18295     22/07/2013    GN     Correzioni relative alle righe scarto
 * 18532     01/10/2013    AA     Correzioni relative alle caso di un'attività intermedia e un prodotto di scarto uguale all'intestatario dell'ordine
 * 18536     07/11/2013    AA     Modificato la gestione dei righe Versamenti & Prelievi
 * 18942     23/12/2013    AA     Correzioni del visualizza dei causale
 * 19104     27/01/2014    TF     Correzioni relative alle quantita
 * 19148     28/02/2014    AA     Ulteriori modifiche
 * 19599     11/04/2014    AA     Varie correzioni
 * 19758     09/05/2014    TF     Modificazione nel metodo hideTempoField(..)
 * 20491     26/01/2015    Mekki  Aggiungere ganci per personalizzazione
 * 20748     28/11/2014    Mekki  Togliere il controllo sull'attività precedente quando è Automatica
 * 22005     20/07/2015    TF     Modificazione nel metodo valorizzaQuantita
 * 22198     16/09/2015    TF     Correzione nel metodo valorizzaQuantita
 * 22630     03/12/2015    AA     Aggiunto l'errore 'THIP40T423'
 * 22890     26/01/2016    TF     Aggiunto l'errore 'THIP40T430'
 * 22513     22/02/2016    Jackal Aggiunti ganci per personalizzazioni
 * 23541     12/05/2016    AA     Correzioni relative al gestione del monitor
 * 23542     13/05/2016    AA     Aggiunto una colonna del codice macchina e la relativa descrizione
 * 23916     15/07/2016    TF     Aggiunto del metodo getAtvPrecNoAuto
 * 24042     31/08/2016    TF     Correzione del controllo sull'attività precedente
 * 24211     12/10/2016    TF     Modifica nel metodo displayProssimeDichInternal
 * 24514     25/11/2016    Mekki  Non proppore la quantità residua quando arrivando dal monitor se sulla relativa causale la checkbox 'Qta residua in avanzamento' non è selezionato
 * 24177     16/11/2016    TF     Prelevare contestualmente all'avanzamento e proporre i materiali da prelevare
 * 24696     27/12/2016    TF     Generare un nuovo lotto di versamento nel caso di generazione auto. loto prd nella creazione di documento Prd.
 * 25207     09/05/2017    Mekki  La quantità materiale proposta non viene calcolata correttamente in presenza di UM intera ed, inoltre, non usa la quantità base.
 * 25733     12/05/2017    Mekki  Se sulla causale di rilevazione la checkbox Qta residua in avanzamento non è selezionata, allora dopo qualunque errore il campo qta buona viene erroneamente sbiancato.
 * 25930     13/06/2017    TF     Modifiche per visualizzare anche le righe prelievi con quantità uguale a zero
 * 25976     19/06/2017    Jackal Inserito controllo di sicurezza su inizializzazione variabile javascript lastCurrentLine
 *                                per evitare che possa arrivare a 0 ed impedire la visualizzazione delle righe doppo la prima
 * 26027     07/07/2017	   MBO	  Valore del campo di recupero "IdEsternoConfig1"
 * 26120     07/08/2017    RM     Modifica nel metodo hideQtaField().
 * 26319	 02/10/2017    RM     Modifiche nel versamenti della rilevazione dati di produzione.
 * 26494	 24/10/2017	   MBO	  Verificare Quentita Rilevazione della Causale Documento "SI"
 * 26730	 08/12/2017	   MBO	  Correzione della funzionalità del 'touch screen' per la quale recuperare la Qta del sottoprodotto.
 * 26907	 01/02/2018    MBO	  Recupera le note inserite per RilevPrd da Inizio a Monitorie.
 * 25921     04/04/2018    Mekki  Gestione doppia unità di misura
 * 27463     28/05/2018    Mekki  Gestione Qta Sec.
 * 27513     31/05/2018    EP	  Aggiunto metodo per personalizzare la quantità di scarto.
 * 27735     13/07/2018    Mekki  Testare che ci sia l'UM secondaria ed evitare l'eccezione di nullPointer
 * 27778     02/08/2018    Houda  Modificato il metodo getErroriInCaricamentoProduzione e il metodo controllaLottiOrdine
 * 27957     25/09/2018    Houda  Modificare il metodo di calcolo de 'qtaBuonaSec'
 * 28226	 15/11/2018	   RA     Gestione riga prodotto a lotti su cui sono stati definiti più di 20 lotti
 * 28595     24/01/2019    Mekki  Prodotto primario senza alcun lotto impostato e su database non ha nemmeno il lotto dummy
 * 29435	 05/07/2019	   TJ	  Memorizzare le note nelle istruzioni di inizio, Sospensione, Ripresa e Fine.
 * 29844     16/09/2019	   TJ	  Gestire il prelievo puntuale dei materiali, gestione lotti, dalla dichiarazione delle bolle cucite.
 * 29945     02/10/2019  Bsondes  Commentate  System.out non servono
 * 30029	 16/10/2019    TJ     Modificare il metodo controllaLottiOrdine(...) per gestire il caso di Modalità prelievo lotto = Manuale
 * 30079	 23/10/2019    TJ     Assegnato la configurazione per un materiale configurato in bolla cucita 
 * 30080	 24/10/2019    TJ     Gestire i secondi in modalità ridotta 
 * 30104	 01/11/2019    TJ	  Controlla  la proposizione automatica dei lotti di produzione in ArticoloDatiMagaz e PersDatiMagazzino
 * 30095	 11/11/2019	   TJ	  Crea una bolla cucita dalla Rilevazione
 * 30236	 27/11/2019	   TJ	  Gestire la risoluzione
 * 30298	 04/12/2019    TJ	  Aggiungi il pulsante sul Monitor per le rilevazioni relative a BolleCucite // Aggiunti codice/descrizione articolo sulla form aperta col pulsante Dettaglio della bolla cucita
 * 30440     23/12/2019    TJ	  Inserire la data nelle rilevazioni in forma ridotta.
 * 30572	 30/01/2020	   TJ	  Gestione dell'operatore,  Indicazione della commessa sulle righe del monitor e sulla rilevazione, Azzeramento campo quantità in caso di SOSPENSIONE
 * 30900	 05/05/2020    TJ	  Gestito stampa bolla alla fine di dichiarazione
 * 31182     04/05/2020    TJ	  Visualizza la descrizione del prodotto finito
 * 31183     06/05/2020    TJ	  Rendi le variabili protected e le variabili static
 * 30965     16/04/2020  Bsondes  6 Decimali .
 * 31322	 04/06/2020	   TJ	  Nascondi il campo tempo solo si RilevaTempo() del causale documento = NON_RILEV
 * 31359	 10/06/2020	   TJ	  Gestito il campo Note in modalità Ridotta, per un'attività Non produttiva
 * 31607     15/07/2020    Mekki  Formattare come HTML il campo Note
 * 32012     14/10/2020	   TJ	  Corretta la gestione di versamento di articolo con lotto unitario e allineare i pulsanti di dichiarazione
 * 32334     10/12/2020	   TJ	  Gestire il flag isOreUomoRsrSec()
 * 32135	 10/12/2020    TJ	  Nella riga di versamento viene proposta la quantità secondaria inserita nella videata precedente, ma non quella ricalcolata.
 * 32789	 21/01/2021    TJ     Gestisci la visibilità del pulsante SUCCESSIVO/CONFERMA con le azioni PAG_PREC e PAG_SUCC
 * 32631	 13/01/2021	 Bsondes  Modifica il metodo hideTempoField() per sia possibile inserire il tempo (ore, minuti e secondi) in caso de tipoDichiarazione = AVANZAMENTO
 * 32648	 13/01/2021	 Bsondes  Sostituire top.document con parent.document.
 * 32846	 03/02/2021	   TJ	  Filtra l'elenco di prodotti / materiali con stato valido
 * 33053	 04/03/2021	   TJ	  Controlla se la quantità è diversa da zero per dividere
 * 33200     29/03/2021   Mekki   Corrgere abilità righe buone e eighe scarti
 * 33267	 05/04/2021	   TJ	  Gestione versamenti nel caso di attività interni di una bolla cucita.
 * 33517	 06/05/2021	   TJ	  Gestione dell'configurazione
 * 33097	 06/05/2021	   TJ	  In dichiarazione di sospensione anche nel caso delle bolle cucit, nella pagina dei versamenti la quantità proposte siano tutte 0
 * 33683	 28/05/2021	   TJ	  Si effettuano dei versamenti con più di 20 lotti, le righe vengono visualizzate  correttamente
 * 33688	 31/05/2021	   TJ	  Mettere la configurazione sempre a capo (cioè sotto l'articolo) se diverso da ""
 * 33801     16/06/2021   Mekki   Corregere le quantità proposte in prelievi qaundo la causale di rilevazione ha il flag 'Quantita residua in prel.' selezionato
 * 34100	 17/09/2021    TJ	  Sostituire le retrieveList con dei PersistentObjectCursor nel recupero del lotti e prossima attivita
 * 34578	 02/11/2021	   TJ	  Usata la scale definita su ThermFw.ini
 * 35044 	 24/12/2022    TJ	  Se un rilevazione non ha valutato l'attività esecutiva, salta il attività e passa a quello successivo
 * 32543	 16/02/2022	   TJ	  Gestisci i secondiRilevati nel calcolaTempo.
 * 36055     14/06/2022    Mekki  Condizionare la chiamata alla initRigheMateriali al fatto che la verificaCondizionePrelievi e la chiamata initRigheProdotti al fatto che la verificaCondizioneVersamenti
 * 36199	 04/07/2022	   TJ	  Modificare la condizione checkAttivitaProdotti in modo da aggiungere la condizione che il tipo dichiarazione sia di 'Versamento'
 * 36033     05/07/2022    Jackal Aggiunti ganci per personalizzazioni
 * 36358	 02/08/2022	   TJ	  Nei documenti di produzione di solo versamento (quindi, senza avanzamento, prelievi e risorse) proporre la quantità residua di versamento, invece, di quella dell'attività.
 * 37418	 26/12/2022	   TJ	  Usato direttamente la quantità secondaria del bo senza ulteriori calcoli
 * 37456     03/01/2023    Mekki  Riportare la commessa  a video anche per le attività non iniziate (Monitor)
 * 37890     21/02/2023    Mekki  Correggere calcola Quantità en UMSec sottoprodotto
 * 38941	 12/06/2023	   TJ	  Usato lotto di movimentazione per per proporre quantità
 * 38994	 19/06/2023	   TJ	  Valorizzato bolla lavorazione se RilevDatiPrdTS aperto da bolla cucita
 * 39336     20/07/2023    Mekki  Chiamare il metodo calcolaQuantitaMat passando come ultimo parametro (calcQtaSempre) true al posto di false
 * 39755     19/09/2023    Mekki  Corretto l'arrotondamento della quantità sec. su ambiente 3 decimali
 * 39680	 21/09/2023	   TJ	  Ricalcolo quantita
 * 40289	 10/11/2023	   TJ	  Aggiunta di metodi per poter personalizzare la quantità proposta.
 * 40311	 10/11/2023    RA	  Modifica usa del metodo equals per tipi BigDecimal con il metodo areEqual del classe Utils
 * 40458     22/11/2023    RA	  Aggiunta di metodi per poter personalizzare l'intestazione.
 * 40486     23/11/2023    RA	  Aggiunto recupero OperatoreDefault da utente rilevazione
 * 40485	 05/12/2023	   TJ	  Alcuni messaggi di avviso possono essere configurati come bloccanti (severità uguale ad 'Errore')
 * 41628	 12/03/2024	   TJ	  Risolto il problema di undefined
 * 42153	 26/04/2024	   Mekki  Modifica metodo verificaMateriali() : non gestisce il caso NESSUNO 
 * 42131	 16/07/2024	   Jackal Aggiunti ganci per personalizzazioni
 * 42882	 17/07/2024	   Mekki  Aggiunta KeyPad su ora inizio e ora fine
 * 43942	 20/11/2024	   TJ	  Nuovo layout
 * 43970	 21/11/2024	   TJ	  Apri il documento di collaudo con l'azione conferma e stampa bolla
 * 44763     17/02/2025    Mekki  Gestione bolla cucita
 * 45164	 13/03/2025	   TJ	  Nel caso di collKey uguale a null il valore dell'input thCollaudoKey sia stringa vuota e non la stringa "null"
 * 45518	 15/04/2025	   Mekki  Modifica metodo initRigaDaOrdineEsec per testare sulla la qta scartato solo che sia diversa da zero
 * 45683	 07/05/2025	   TJ	  Aggiungere varie righe con quantità 1 nel caso di articolo gestito lotti unitario e nel ordine il lotti non sepcificato
 * 46031	 17/06/2025	   TJ	  Mostra gestione matricole in doc. prd.
 * 46782     08/09/2025	   Mekki  Abilita prelievo in sospensione
 */

public class RilevDatiPrdTSWebFormModifier extends ThipWebFormModifier{

  public static final String PRELIEVI = "PRELIEVI"; //Fix 14722
  public static final String VERSAMENTI = "VERSAMENTI"; //Fix 14722
  //Fix 24177 inizio
  protected static final BigDecimal ZERO = new BigDecimal("0");
  protected static final BigDecimal UNO = new BigDecimal("1");
  public static final String ASCENDING = " ASC ";
  public static final String DESCENDING = " DESC ";
  //Fix 24177 fine
  protected BigDecimal iQtaBuona = null; //Fix 12974 //Fix 31183
  // Fix 15250 inizio
  public static String prelieviTitle = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "PrelieviTitolo"));//Fix 31183
  public static String VersamentiTitle = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "VersamentiTitolo"));//Fix 31183
  public static String inizioTitle = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "DichInizio"));//Fix 31183
  public static String ripresaTitle = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "DichRipresa"));//Fix 31183
  public static String sospensioneTitle = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "DichSospensione"));//Fix 31183
  public static String fineTitle = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "DichFine"));//Fix 31183
  public static String monitorTitle = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "MonitorTitle"));//Fix 31183
  public static String ridottaTitle = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "DichRidotta"));//Fix 31183
  public static String VersamentiAvanzamentiTitle = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "VersamentiAvanzamentiTitle")); // Fix 15030 //Fix 31183
  // Fix 15250 fine
  public static String stampaTitle = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "stampaTitle"));//Fix 30900
  
  protected List readOnlyQta = new ArrayList(); //Fix 18295 //Fix 31183
  protected List readOnlyQtaSca = new ArrayList(); //Fix 18295 //Fix 31183
  //public int iTotaliRigheInitial = 0; //Fix 18536 //Fix 19599 //Fix 31183
  protected int iTotaliRigheInitial = 0;//Fix 31183
  protected boolean iEsistListaAttivitaBC = false; //Fix 44763

  public void writeHeadElements(JspWriter out) throws java.io.IOException {
    super.writeHeadElements(out);
    RilevDatiPrdTS rilevDatiPrdTS = (RilevDatiPrdTS) getRequest().getAttribute("myObject");
    String action = (String) getRequest().getAttribute("Action"); //Fix 12974
    List errori = (List) getRequest().getAttribute("ErroriList"); //Fix 13082
    if (rilevDatiPrdTS != null) {
      if(action == null){ // Fix 12974
        if(errori == null || (errori != null && errori.isEmpty())){ //Fix 13082
          rilevDatiPrdTS.setOperatore(null);
          rilevDatiPrdTS.setBollaLavorazione(null);
          rilevDatiPrdTS.setIdMacchina(null);
        }
      }
      else{
        if (rilevDatiPrdTS.getAttivitaEsecutiva() != null) {
          if (rilevDatiPrdTS.getIdRisorsa() == null) { //Fix 12841
            if (action.equals(RilevDatiPrdTSFormActionAdapter.RIPRESA)
                                   || action.equals(RilevDatiPrdTSFormActionAdapter.SOSPENSIONE)
                                   || action.equals(RilevDatiPrdTSFormActionAdapter.FINE)
                                   || action.equals(RilevDatiPrdTSFormActionAdapter.PRODUZIONE)) { //Fix 12974 //Fix 13434
              RilevazioneDatiProdTes rilevazione = rilevDatiPrdTS.cercaRilevazioneIncompleta(); //Fix 13434
              if (rilevazione != null) {
                rilevDatiPrdTS.setRisorsa(rilevazione.getRisorsa());
                rilevDatiPrdTS.setNote(rilevazione.getNote()); //Fix 15866
                rilevDatiPrdTS.setSaldo(rilevazione.getStatoSaldo()); // Fix 16333
              }
              //Fix 16109 inizio
              else {
            	//Fix 32334 -- Inizio  
            	if(rilevDatiPrdTS.getPersDatiPrdCausaleRilev() != null && rilevDatiPrdTS.getPersDatiPrdCausaleRilev().isOreUomoRsrSec()) {
            		Risorsa matricolaOeratore = rilevDatiPrdTS.getOperatoreRisorsa();
            		if(matricolaOeratore != null) {
            			rilevDatiPrdTS.setIdRisorsa(matricolaOeratore.getIdRisorsa());
            			rilevDatiPrdTS.setTipoRisorsa(matricolaOeratore.getTipoRisorsa());
    	                rilevDatiPrdTS.setLivelloRisorsa(Risorsa.MATRICOLA);	
            		}
            	}
            	else {
            	//Fix 32334 --Fine 	
	                Risorsa risoraPrincipale = rilevDatiPrdTS.getIdMacchina() != null ? rilevDatiPrdTS.getMacchina() : rilevDatiPrdTS.getRisorsaPrincipale();
	                rilevDatiPrdTS.setTipoRisorsa(risoraPrincipale.getTipoRisorsa());
	                rilevDatiPrdTS.setLivelloRisorsa(Risorsa.MATRICOLA);
            	}
              }
              //Fix 16109 fine
            }
            else {
              //Fix 13175 --inizio
              //Risorsa risoraPrincipale = rilevDatiPrdTS.getRisorsaPrincipale();
              Risorsa risoraPrincipale = rilevDatiPrdTS.getIdMacchina() != null ? rilevDatiPrdTS.getMacchina() : rilevDatiPrdTS.getRisorsaPrincipale();
              //Fix 13175 --fine
              rilevDatiPrdTS.setTipoRisorsa(risoraPrincipale.getTipoRisorsa());
              rilevDatiPrdTS.setLivelloRisorsa(Risorsa.MATRICOLA);
              /*if (risoraPrincipale.getLivelloRisorsa() == Risorsa.MATRICOLA) {
                rilevDatiPrdTS.setIdRisorsa(risoraPrincipale.getIdRisorsa());
              }*/
            }
          }
        }
        //Fix 14722 inizio
        else if(rilevDatiPrdTS.getBollaCucita() != null){
          if (rilevDatiPrdTS.getIdRisorsa() == null) {
        	//Fix 32334 -- Inizio  
          	if(rilevDatiPrdTS.getPersDatiPrdCausaleRilev() != null && rilevDatiPrdTS.getPersDatiPrdCausaleRilev().isOreUomoRsrSec()) {
          		Risorsa matricolaOeratore = rilevDatiPrdTS.getOperatoreRisorsa();
          		if(matricolaOeratore != null) {
          			rilevDatiPrdTS.setIdRisorsa(matricolaOeratore.getIdRisorsa());
          			rilevDatiPrdTS.setTipoRisorsa(matricolaOeratore.getTipoRisorsa());
  	                rilevDatiPrdTS.setLivelloRisorsa(Risorsa.MATRICOLA);	
          		}
          	}
          	else {
          	//Fix 32334 --Fine  
	            Risorsa risoraPrincipale = rilevDatiPrdTS.getIdMacchina() != null ? rilevDatiPrdTS.getMacchina() : rilevDatiPrdTS.getRisorsaPrincipale();
	            rilevDatiPrdTS.setTipoRisorsa(risoraPrincipale.getTipoRisorsa());
	            rilevDatiPrdTS.setLivelloRisorsa(Risorsa.MATRICOLA);
          	}//Fix 32334
          }
        }
        //Fix 14725 --inizio
        else{
          if (rilevDatiPrdTS.getIdRisorsa() == null && rilevDatiPrdTS.getIdMacchina() != null) {
             Risorsa risorsa = rilevDatiPrdTS.getMacchina();
             if(risorsa != null){
              rilevDatiPrdTS.setTipoRisorsa(risorsa.getTipoRisorsa());
              rilevDatiPrdTS.setLivelloRisorsa(Risorsa.MATRICOLA);
             }
           }
          //Fix 29435 inizio
          if (action.equals(RilevDatiPrdTSFormActionAdapter.NON_PRODUZIONE) && !rilevDatiPrdTS.getPersDatiPrdCausaleRilev().isAbilitaDichRidotta()) {//Fix 31359
              RilevazioneDatiProdTes rilevazione = rilevDatiPrdTS.cercaRilevazioneIncompletaNonProd();
              if (rilevazione != null) {
                rilevDatiPrdTS.setNote(rilevazione.getNote());
              }
           }
           //Fix 29435 fine
        }
        //Fix 14725 --fine
        //Fix 15064 inizio
        if(rilevDatiPrdTS.getIdMacchina() == null && rilevDatiPrdTS.getTipoRisorsa() == Risorsa.MACCHINE &&
           rilevDatiPrdTS.getLivelloRisorsa() == Risorsa.MATRICOLA) { //Fix 13175 //Fix 13574 //Fix 15217
          rilevDatiPrdTS.setIdMacchina(rilevDatiPrdTS.getIdRisorsa());
        }
        //Fix 15064 fine

        //Fix 14722 fine
        //getBODataCollector().setBo(rilevDatiPrdTS); //Fix 13082
        //Fix 13264 --inizio
        if(rilevDatiPrdTS.getPersDatiPrdCausaleRilev() != null){
          char tipoArrotondamento = rilevDatiPrdTS.getPersDatiPrdCausaleRilev().getTipoArrotond();
          short passo = rilevDatiPrdTS.getPersDatiPrdCausaleRilev().getIntervMinArrotond().shortValue();
          rilevDatiPrdTS.setOraInizio(rilevDatiPrdTS.arrotondareOreRilevazione(rilevDatiPrdTS.getOraInizio(), tipoArrotondamento, passo));
          rilevDatiPrdTS.setOraFine(rilevDatiPrdTS.arrotondareOreRilevazione(rilevDatiPrdTS.getOraFine(), tipoArrotondamento, passo));
          rilevDatiPrdTS.setDataFine(rilevDatiPrdTS.getDataFine());//Fix 30440
        }
        //Fix 13264 --fine
        //Fix 32789 -- Inizio
  		if (action.equals(RilevDatiPrdTSFormActionAdapter.PAG_PREC)	|| action.equals(RilevDatiPrdTSFormActionAdapter.PAG_SUCC)) {
  			out.println("<script language='JavaScript1.2'>");
  			out.println("var dispalyBtnSucc = " + false + ";");
  			if (rilevDatiPrdTS.getPersDatiPrdCausaleRilev() != null) {
  				CausaleDocProduzione causaleDocPrd = rilevDatiPrdTS.getPersDatiPrdCausaleRilev().getCausaleDocumento();
  				if (rilevDatiPrdTS.getTipoBolla() == RilevazioneDatiProdTes.BOLLA_SINGOLA) {
  					if ((rilevDatiPrdTS.getPersDatiPrdCausaleRilev().getPosizionePrelievo() == PersDatiPrdCausaleRilev.FINE
  							|| rilevDatiPrdTS.getPersDatiPrdCausaleRilev().getPosizionePrelievo() == PersDatiPrdCausaleRilev.SOSPENSIONE_FINE //Fix 46782
  			        		|| rilevDatiPrdTS.getPersDatiPrdCausaleRilev().isAbilitaDichRidotta()) && rilevDatiPrdTS.getPersDatiPrdCausaleRilev().getTipoDichiarazione() != PersDatiPrdCausaleRilev.PRELIEVO && checkAttivitaProdotti(rilevDatiPrdTS.getAttivitaEsecutiva().getProdotti())) {
  						out.println("dispalyBtnSucc =" + true + ";");
  					}
  				} else if (rilevDatiPrdTS.getTipoBolla() == RilevazioneDatiProdTes.BOLLA_CUCITA) {
  					if ((rilevDatiPrdTS.getPersDatiPrdCausaleRilev().getPosizionePrelievo() == PersDatiPrdCausaleRilev.FINE
  			        		|| rilevDatiPrdTS.getPersDatiPrdCausaleRilev().isAbilitaDichRidotta()) && rilevDatiPrdTS.getPersDatiPrdCausaleRilev().getTipoDichiarazione() != PersDatiPrdCausaleRilev.PRELIEVO) 
  						out.println("dispalyBtnSucc =" + true + ";");
  				}
  			}
  			riempiMaterialeConUMSec(rilevDatiPrdTS, out); //Fix 39680
  			riempiProdottiConUMSec(rilevDatiPrdTS, out); //Fix 39680
  			out.println("</script>");
  		}
        //Fix 32789 -- Fine
      }
      //Fix 13175 --inizio
      AmbienteListaAttivita ambiente = RilevDatiPrdTS.getAmbienteForCurrentUser();
      if(ambiente != null){
         rilevDatiPrdTS.setIdAmbiente(ambiente.getIdAmbiente());
       }
       //Fix 14721 --inizio
       if(rilevDatiPrdTS.getPersDatiPrdCausaleRilev() != null){
    	   //Fix 20491 --inizio
           /*char tipoDichiar = rilevDatiPrdTS.getPersDatiPrdCausaleRilev().getTipoDichiarazione();
           CausaleDocProduzione causaleDoc = rilevDatiPrdTS.getPersDatiPrdCausaleRilev().getCausaleDocumento(); //Fix 16109
           AttivitaEsecutiva atvEsec = rilevDatiPrdTS.getAttivitaEsecutiva(); //Fix 16109
           if (tipoDichiar == PersDatiPrdCausaleRilev.VERSAMENTO ||
               (causaleDoc != null && atvEsec != null && causaleDoc.getAbilitaVersamenti() != CausaleDocProduzione.NO &&
                checkAttivitaProdotti(atvEsec.getProdotti()))) //Fix 16109*/
      	  if (rilevDatiPrdTS.verificaCondizioneVersamenti()) { //Fix 16109 //Fix 20491 --fine
      		rilevDatiPrdTS = initRigheProdotti(rilevDatiPrdTS, out);
      	  }
       }
       //Fix 14721 --fine
       //Fix 14722 --inizio
       if(rilevDatiPrdTS.getTipoBolla() ==  RilevazioneDatiProdTes.BOLLA_CUCITA && action != null
          && (action.equals(RilevDatiPrdTSFormActionAdapter.SUCCESSIVO)
          || action.equals(RilevDatiPrdTSFormActionAdapter.PRODUZIONE))
          && (rilevDatiPrdTS.getTipoTimbratura() == RilevazioneDatiProdRig.SOSPENSIONE
          || rilevDatiPrdTS.getTipoTimbratura() == RilevazioneDatiProdRig.FINE)){ //Fix 15030 //Fix 19148
         if(getRequest().getAttribute("Azione") != null && getRequest().getAttribute("Azione").equals(VERSAMENTI)){ //Fix 15030
           //getServletEnvironment().getSession().setAttribute("myOldObject", rilevDatiPrdTS); //Fix 16109 riga commentata
           if (rilevDatiPrdTS.getBollaCucita() != null && !rilevDatiPrdTS.getBollaCucita().getRighe().isEmpty())
             rilevDatiPrdTS = initRigheProdotti(rilevDatiPrdTS, out);
         }
         else if(getRequest().getAttribute("Azione") != null && getRequest().getAttribute("Azione").equals(PRELIEVI)){ //Fix 15030
             rilevDatiPrdTS = initRigheMateriali(rilevDatiPrdTS, out);
         }
         //Fix 15030 inizio
         else if(rilevDatiPrdTS != null){
           if (rilevDatiPrdTS.verificaCondizioneVersamenti()) //Fix 36055
             rilevDatiPrdTS = initRigheProdotti(rilevDatiPrdTS, out);
           if (rilevDatiPrdTS.verificaCondizionePrelievi()) //Fix 36055
             rilevDatiPrdTS = initRigheMateriali(rilevDatiPrdTS, out);
         }
         //Fix 15030 fine
       }
	   else if(rilevDatiPrdTS != null && rilevDatiPrdTS.verificaCondizionePrelievi() && getRequest().getAttribute("Azione") != null //Fix 24177
      		 && getRequest().getAttribute("Azione").equals(PRELIEVI) && !rilevDatiPrdTS.getMateriali().isEmpty() && !isOldJspNameDichiarazioneVersamenti()){ //Fix 24177
      	 rilevDatiPrdTS = initRigheMateriali(rilevDatiPrdTS, out);//Fix 24177
       }//Fix 24177 fine
       getServletEnvironment().getSession().setAttribute("RilevDatiPrdTSOldObject", rilevDatiPrdTS); //Fix 16109
       //Fix 14722 --fine
       //Fix 13175 --fine
      getBODataCollector().setBo(rilevDatiPrdTS); //Fix 13082
      //Fix 13573 Inizio
      out.println("<script language='JavaScript1.2'>");
      out.println("var enableStampa = true;");
      out.println("</script>");
      if(rilevDatiPrdTS.getPersDatiPrdUtenteRilev() != null){
        //if(rilevDatiPrdTS.getPersDatiPrdUtenteRilev().getReportModelStampaBolla() == null || //Fix 15250
        // rilevDatiPrdTS.getPersDatiPrdUtenteRilev().getReportModelStampaBolla().equals("")){//Fix 15250
        if (rilevDatiPrdTS.getPersDatiPrdUtenteRilev().getReportModelStampaBolla() == null //Fix 15250
            && rilevDatiPrdTS.getPersDatiPrdUtenteRilev().getReportModelStampaBollaCuc() == null) { //Fix 15250
          out.println("<script language='JavaScript1.2'>");
          //out.println("top.document.getElementById('StampaBolla').style.display=displayNone;"); //Fix 32648
          out.println("parent.document.getElementById('StampaBolla').style.display=displayNone;"); //Fix 32648
          out.println("enableStampa = false;");
          out.println("</script>");
        }
      }
   //Fix 13573 Fine

   //Fix 14721 Inizio
     if(rilevDatiPrdTS.getPersDatiPrdCausaleRilev() != null){
       char tipoDichiar = rilevDatiPrdTS.getPersDatiPrdCausaleRilev().getTipoDichiarazione();
       out.println("<script language='JavaScript1.2'>");
       out.println("var tipoDichiarazione = '"+tipoDichiar+"';");
       //Fix 15250 inizio
       if (rilevDatiPrdTS.getIdMateriale1() != null) {
         out.println(" isAbilPrel = true;");
       }
       else {
         out.println(" isAbilPrel = false;");

       }
       //Fix 15250 fine
       out.println("</script>");
     }
   //Fix 14721 Fine
   //Fix 15250 --inizio
   if (rilevDatiPrdTS.getTipoBolla() == RilevazioneDatiProdTes.BOLLA_CUCITA) {
     out.println("<script language='JavaScript1.2'>");
     if (rilevDatiPrdTS.getMateriale1() != null && rilevDatiPrdTS.getMateriale1().isArticLotto()) { //Fix 15582
       out.println(" isArtGestLot = true;");
     }
     else {
       out.println(" isArtGestLot = false;");
     }
     out.println("</script>");
   }
   //Fix 15250 --fine
    }
    //Fix 19148 inizio
    if(rilevDatiPrdTS != null) {
    	BigDecimal totPag = new BigDecimal(0);
    	if(getRequest().getAttribute("Azione") != null) {
    		BigDecimal totaleRighe = getRequest().getAttribute("Azione").equals(VERSAMENTI) ? new BigDecimal(rilevDatiPrdTS.getProdotti().size()) : new BigDecimal(rilevDatiPrdTS.getMateriali().size());
    		if(totaleRighe.compareTo(new BigDecimal(0))>0)
    			totPag = totaleRighe.divide(new BigDecimal(20), 2, BigDecimal.ROUND_UP);
    	}
    	out.println("<script language='JavaScript1.2'>");
  		out.println("var totPag = " + totPag.intValue() + ";");
  		out.println("</script>");
    }
    //Fix 19148 fine
  }

  public void writeBodyEndElements(JspWriter out) throws java.io.IOException {
    super.writeBodyEndElements(out);
    //Fix 13574 inizio
    boolean existUtentRilev = false;
    PersDatiPrdUtenteRilev utenteRilev = (PersDatiPrdUtenteRilev) Factory.createObject(PersDatiPrdUtenteRilev.class);
    utenteRilev.setIdAzienda(Azienda.getAziendaCorrente());
    UtenteAzienda ua = ((ThipUser) Security.getCurrentUser()).getUtenteAzienda();
    utenteRilev.setIdUtenteLgn(ua.getIdUtente());
    try {
      existUtentRilev = utenteRilev.retrieve();
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }
    if ((existUtentRilev) && (utenteRilev.getRisoluzioneVideo() ==PersDatiPrdUtenteRilev.RISOL_800_600)) {
      //Fix 43942 -- Inizio
      if (LayoutUtils.getInstance().isResponsiveLook(getServletEnvironment().getSession()))
        out.print(WebJSTypeList.getImportForCSS("it/thera/thip/produzione/raccoltaDati/Rilevazione800x600Resp.css", getServletEnvironment().getRequest()));
      else
      //Fix 43942 -- Fine   
    	out.print(WebJSTypeList.getImportForCSS("it/thera/thip/produzione/raccoltaDati/Rilevazione800x600.css", getServletEnvironment().getRequest()));
      out.println("<script language='JavaScript1.2'>");
      out.println("resoluzione='"+PersDatiPrdUtenteRilev.RISOL_800_600+"'");
      out.println("</script>");
    }
    else {
      //Fix 43942 -- Inizio
      if (LayoutUtils.getInstance().isResponsiveLook(getServletEnvironment().getSession()))
        out.print(WebJSTypeList.getImportForCSS("it/thera/thip/produzione/raccoltaDati/RilevazioneResp.css", getServletEnvironment().getRequest()));
      else
      //Fix 43942 -- Fine  
        out.println(WebJSTypeList.getImportForCSS("it/thera/thip/produzione/raccoltaDati/Rilevazione.css", getServletEnvironment().getRequest()));
      out.println("<script language='JavaScript1.2'>");
      out.println("resoluzione='"+PersDatiPrdUtenteRilev.RISOL_1024_768+"'");
      out.println("</script>");
    }
    //Fix 13574 fine
   // out.println(WebJSTypeList.getImportForCSS("it/thera/thip/produzione/raccoltaDati/Rilevazione.css", getServletEnvironment().getRequest()));//Fix 13574
    RilevDatiPrdTS bo = (RilevDatiPrdTS) getBODataCollector().getBo();
    bo.setIdAzienda(Azienda.getAziendaCorrente()); //Fix 15582
    String action = (String) getRequest().getAttribute("Action");
    //Fix 30572 -- inizio
	String operatorObligatore = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "OperatorOblig"));
	String operatorNonEsiste = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "OperatorNonEsiste"));
	//Fix 30572 -- fine
    //Fix 15250 inizio
    //String inizioTitle = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "DichInizio"));
    //String ripresaTitle = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "DichRipresa"));
    //String sospensioneTitle = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "DichSospensione"));
    //String fineTitle = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "DichFine"));
    //String monitorTitle = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "MonitorTitle"));
   // String ridottaTitle = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "DichRidotta")); // Fix 14721
    //String prelieviTitle = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "PrelieviTitolo")); // Fix 14721
   // String VersamentiTitle = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "VersamentiTitolo")); // Fix 14721
   // String VersamentiAvanzamentiTitle = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "VersamentiAvanzamentiTitle")); // Fix 15030//Fix 15250 fine
    out.println("<script language='JavaScript1.2'>");    
    if (action == null) {
      //Fix 30572 -- inizio
      out.println("var operatorObligatore = '" + operatorObligatore +"';");
      out.println("var operatorNonEsiste = '" + operatorNonEsiste +"';");
      //Fix 30572 -- fine	
      setTitle(out, ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "NuovoDich"));
      String idOperatoreGenerico = PersDatiPrd.getCurrentPersDatiPrd().getIdDichiaranteGenerico();
      // String idOperatore = bo.getIdOperatore() != null ? bo.getIdOperatore() : idOperatoreGenerico; //Fix 12974 //Fix 15250 inizio
      String idOperatore = bo.getIdOperatore();
      if (idOperatore == null && existUtentRilev && utenteRilev.isRilevAnonima()) {
        idOperatore = idOperatoreGenerico;
      } //Fix 15250 fine
      //40486 inizio 
      else if (idOperatore == null && existUtentRilev) {
        idOperatore = utenteRilev.getOperatoreDefault();
      } 
      //40486 fine
      if(idOperatore != null){ //Fix 12974
        out.println("document.getElementById(\"IdOperatore\").value = '" + WebElement.formatStringForHTML(idOperatore) + "';");
        out.println(" eval('document.forms[0].' + idFromName['BollaLavorazione']).focus();");
      }
      else{
        out.println("document.getElementById(\"IdOperatore\").focus();");
      }
      //Fix 30095 -- inizio
      if(!utenteRilev.isCreaBollaCucita()) {    	  
    	  out.println("document.getElementById('CreaBollaCucita').style.display=displayNone;");    	  
      }
      //Fix 30095 -- fine
      //Fix 12974 --inizio
      checkAttivazioneRilevDatiPrd(out, bo);
      //Fix 12974 --fine
      //Fix 13434 inizio
      String collKey = (String) getServletEnvironment().getRequest().getAttribute("MOSTRA_DOC_COLLAUDO");
      //Fix 43970 -- Inizio
      if(collKey == null || collKey.equals("")) {
    	  collKey = getServletEnvironment().getRequest().getParameter("thCollaudoKey");
      }
      //Fix 43970 -- Fine
      if (collKey != null && !collKey.equals("")) {
        //getServletEnvironment().getRequest().setAttribute("MOSTRA_DOC_COLLAUDO", null);
        out.println("openDocumentoCollaudo('" + collKey + "')");
      }
      getServletEnvironment().getRequest().setAttribute("MOSTRA_DOC_COLLAUDO", null); //Fix 13434
      //Fix 13434 fine
      //Fix 46031 -- Inizio
      String chiaviOggettotMatricolati = (String) getServletEnvironment().getRequest().getAttribute("MOSTRA_GEST_MATRICOLE");
      if(chiaviOggettotMatricolati == null || chiaviOggettotMatricolati.equals("")) {
    	chiaviOggettotMatricolati = getServletEnvironment().getRequest().getParameter("thChiaviOggettotMatricolati");
      }
      if (chiaviOggettotMatricolati != null && !chiaviOggettotMatricolati.equals("")) {
        out.println("openOggettoMatricolati('" + chiaviOggettotMatricolati + "')");
      }
      getServletEnvironment().getRequest().setAttribute("MOSTRA_GEST_MATRICOLE", null);
      //Fix 46031 -- Fine
    }
    //Fix 13434 inizio
    else if (action.equals(RilevDatiPrdTSFormActionAdapter.PRODUZIONE)) {
      azioneProduzione(out, bo, inizioTitle, ripresaTitle, sospensioneTitle, fineTitle, ridottaTitle); //Fix 14721
    }
    //Fix 13434 fine
    else if (action.equals(RilevDatiPrdTSFormActionAdapter.INIZIO) || action.equals(RilevDatiPrdTSFormActionAdapter.PROSSIMA_ATV)) {
      setTitle(out, inizioTitle);
      hideMacchinaField(out, bo); //Fix 13082
    }
    else if (action.equals(RilevDatiPrdTSFormActionAdapter.RIPRESA)) {
      setTitle(out, ripresaTitle);
      hideMacchinaField(out, bo); //Fix 13082
    }
    else if (action.equals(RilevDatiPrdTSFormActionAdapter.SOSPENSIONE)) {
      setTitle(out, sospensioneTitle);
      hideMacchinaField(out, bo); //Fix 13082
    }
    else if (action.equals(RilevDatiPrdTSFormActionAdapter.FINE)) {
      setTitle(out, fineTitle);
      hideMacchinaField(out, bo); //Fix 13082
    }
     //  else if (action.equals(RilevDatiPrdTSFormActionAdapter.NON_PRODUZIONE)) {//Fix 16741
      else if (action.equals(RilevDatiPrdTSFormActionAdapter.NON_PRODUZIONE) || action.equals(RilevDatiPrdTSFormActionAdapter.FERMO)) {//Fix 16741
        azioneNonProduzione(out, bo, inizioTitle, ripresaTitle, sospensioneTitle, fineTitle, ridottaTitle);
        hideMacchinaField(out, bo);//Fix 16741
        //hideDescrizioneMacchina(out, bo);
      }
    else if (action.equals(RilevDatiPrdTSFormActionAdapter.MONITOR)) {
      setTitle(out, monitorTitle);
      hideDescrizioneMacchina(out, bo); //Fix 12841
    }
    // Fix 14721 inizio
    else if (action.equals(RilevDatiPrdTSFormActionAdapter.RIDOTTA)) {
      setTitle(out, ridottaTitle);
      hideMacchinaField(out, bo);
    }
    else if (action.equals(RilevDatiPrdTSFormActionAdapter.SUCCESSIVO)) {
      //Fix 15866 inizio
      String note = bo.getNote() != null ? bo.getNote() : "";
      //out.println(" eval('document.forms[0].' + idFromName['Note']).value = '" + note + "';"); //Fix 31607
      out.println(" eval('document.forms[0].' + idFromName['Note']).value = '" + WebElement.formatStringForHTML(note) + "';"); //Fix 31607
      //Fix 15866 fine
      if(bo != null && bo.getTipoBolla() == RilevazioneDatiProdTes.BOLLA_SINGOLA){ //Fix 14722
        CausaleDocProduzione causaleDoc = bo.getPersDatiPrdCausaleRilev().getCausaleDocumento(); //Fix 15030
        //if (bo.getPersDatiPrdCausaleRilev().getTipoDichiarazione() == PersDatiPrdCausaleRilev.PRELIEVO)//Fix 24177
		String jspName = (String)getServletEnvironment().getRequest().getParameter("thOldJspName");//Fix 24177
		String azione = (String) getRequest().getAttribute("Azione");//Fix 24177
       if (bo.verificaCondizionePrelievi() && azione != null && azione.equals(PRELIEVI) && (!bo.getPersDatiPrdCausaleRilev().isAbilitaDichRidotta() && bo.isPosizionePrelieviValido() || bo.getPersDatiPrdCausaleRilev().isAbilitaDichRidotta())) {//Fix 24177
          setTitle(out, prelieviTitle);
		  riempiMatGestLottiArray(bo, out);
		  riempiMaterialeConUMSec(bo, out); //Fix 39680
		  if ((bo.getPersDatiPrdCausaleRilev().getPosizionePrelievo() == PersDatiPrdCausaleRilev.FINE
				|| bo.getPersDatiPrdCausaleRilev().getPosizionePrelievo() == PersDatiPrdCausaleRilev.SOSPENSIONE_FINE //Fix 46782
        		|| bo.getPersDatiPrdCausaleRilev().isAbilitaDichRidotta()) && bo.getPersDatiPrdCausaleRilev().getTipoDichiarazione() != PersDatiPrdCausaleRilev.PRELIEVO && checkAttivitaProdotti(bo.getAttivitaEsecutiva().getProdotti())) {//Fix 24177
        	 out.println("var dispalyBtnSucc = "+ true +";");//Fix 24177
         }//Fix 24177
		}
        else if (bo.getPersDatiPrdCausaleRilev().getTipoDichiarazione() == PersDatiPrdCausaleRilev.VERSAMENTO ||
            (causaleDoc.getAbilitaVersamenti() != CausaleDocProduzione.NO && checkAttivitaProdotti(bo.getAttivitaEsecutiva().getProdotti()))) { //Fix 15030
          setTitle(out, VersamentiTitle);
          displayLottiFields(out);
        }
        else
          setTitle(out, ridottaTitle);
      }//Fix 14722 inizio
      else if (bo != null && bo.getTipoBolla() == RilevazioneDatiProdTes.BOLLA_CUCITA) {
        String origini = (String)getRequest().getAttribute("Azione");
        if (origini != null && origini.equals(PRELIEVI)) {
          setTitle(out, prelieviTitle+" "+bo.getPersDatiPrdCausaleRilev().getIdCausaleRilevazione());
          riempiMatGestLottiArray(bo, out); //Fix 29844
          riempiMaterialeConUMSec(bo, out); //Fix 39680
          //Fix 29844 --inizio
          if (bo.getTipoTimbratura() == RilevazioneDatiProdRig.INIZIO && bo.getPersDatiPrdCausaleRilev().getPosizionePrelievo() == PersDatiPrdCausaleRilev.INIZIO) 
        	  out.println("var dispalyBtnSucc = "+ false +";");
          //Fix 29844 --fine
        }
        else if (origini != null && origini.equals(VERSAMENTI)){
          //setTitle(out, VersamentiTitle+" "+bo.getPersDatiPrdCausaleRilev().getIdCausaleRilevazione()); //Fix 15030
          setTitle(out, VersamentiAvanzamentiTitle + " " + bo.getPersDatiPrdCausaleRilev().getIdCausaleRilevazione()); //Fix 15030
          displayLottiFields(out); //Fix 15030
        }
      }//Fix 14722 fine
    }
    //Fix 19148 Inizio
    else if (action.equals(RilevDatiPrdTSFormActionAdapter.PAG_PREC) || action.equals(RilevDatiPrdTSFormActionAdapter.PAG_SUCC)) {
        pagPrecSuccPers(bo, out);		//Fix 36033
        String origini = (String)getRequest().getAttribute("Azione"); //Fix 39680
        if (origini != null && origini.equals(VERSAMENTI)) //Fix 39680
          displayLottiFields(out);
    }
    //Fix 19148 Fine
    // Fix 14721 fine
    //Fix 30900 -- inizio
    else if (action.equals(RilevDatiPrdTSFormActionAdapter.STAMPA_BOLLA_FINE)) {
        setTitle(out, stampaTitle);
    }
    //Fix 30900 -- fine
    //Fix 15030 inizio
    List errori = (List) getRequest().getAttribute("ErroriList");
    if(bo != null && bo.getTipoTimbratura() == RilevazioneDatiProdRig.FINE){
      if(bo.getTipoBolla() == RilevazioneDatiProdTes.BOLLA_CUCITA){
        //Fix 15217 blocco commentato
        /*RilevazioneDatiProdTes rilevazione = bo.cercaRilevazioneInCorso();
        if(rilevazione != null){
          out.println("document.getElementById(\"IdRisorsa\").value = '" + WebElement.formatStringForHTML(rilevazione.getIdRisorsa()) + "';");
        }*/
        String origini = (String)getRequest().getAttribute("Azione"); //Fix 16109
        if(bo.getBollaCucita() != null && origini != null && origini.equals(VERSAMENTI) && errori != null) { //Fix 16109
          displayLottiFields(out);
        }
      }
    }

    //List errori = (List) getRequest().getAttribute("ErroriList");
    //Fix 15030 fine
    if (errori != null) {
      displayErrori(out, errori);
    }
    else {
      if (action != null) {
        List errorList = new ArrayList();
        if (action.equals(RilevDatiPrdTSFormActionAdapter.PRODUZIONE)) {
          errorList = getErroriInCaricamentoProduzione(bo, out); //Fix 15217
        }
        //Fix 13082 --inizio
        /*else if (action.equals(RilevDatiPrdTSFormActionAdapter.NON_PRODUZIONE)) {
          errorList = getErroriInCaricamentoNonProd(bo);
        }*/
        else if (action.equals(RilevDatiPrdTSFormActionAdapter.INIZIO) ||
                 action.equals(RilevDatiPrdTSFormActionAdapter.RIPRESA) ||
                 action.equals(RilevDatiPrdTSFormActionAdapter.SOSPENSIONE) ||
                 action.equals(RilevDatiPrdTSFormActionAdapter.FINE) ||
                 action.equals(RilevDatiPrdTSFormActionAdapter.PROSSIMA_ATV)){ //Fix 13264
          if (bo.getBollaLavorazione() != null) {
            if (bo.isMacchinaOccupata()) { //Fix 13264
              errorList.add(new ErrorMessage("THIP30T261"));
            }
            //Fix 13434 inizio
            if ((bo.getTipoTimbratura() == RilevazioneDatiProdRig.INIZIO)) {
              RilevazioneDatiProdTes testata = bo.cercaRilevazioneInCorso();
              if (testata != null) {
                errorList.add(new ErrorMessage("THIP30T232"));
              }
              testata = bo.cercaRilevazioneSospesa();
              if (testata != null) {
                errorList.add(new ErrorMessage("THIP300303"));
              }
              //if (bo.getBollaCucita().getStatoAvanzamento() == BollaCucitaTes.COMPLETATO)
                //errorList.add(new ErrorMessage("THIP40T145"));
            }
            else if(action.equals(RilevDatiPrdTSFormActionAdapter.RIPRESA)){
              RilevazioneDatiProdTes testata = bo.cercaRilevazioneSospesa();
              if (testata == null) {
                errorList.add(new ErrorMessage("THIP300304"));
              }
            }
            //Fix 13434 fine
          }
          else {
            if(bo.getIdMacchina() == null){//Fix 16741
              errorList = getErroriInCaricamentoNonProd(bo);
            }//Fix 16741 inizio
            else{
              errorList = getErroriInCaricamentoFermo(bo);
            }//Fix 16741 fine
          }
        }
        //Fix 13434 inizio
        else if (action.equals(RilevDatiPrdTSFormActionAdapter.NON_PRODUZIONE)) {
          errorList = getErroriInCaricamentoNonProd(bo);
        }
        //Fix 13434 fine
        //Fix 13082 --fine
        //Fix 16741 inizio
        else if (action.equals(RilevDatiPrdTSFormActionAdapter.FERMO)) {
          errorList = getErroriInCaricamentoFermo(bo);
        } //Fix 16741 fine
        //Fix 13264 inizio
        if(action.equals(RilevDatiPrdTSFormActionAdapter.MONITOR)){
          if(bo.getIdAmbiente() == null){
            errorList.add(new ErrorMessage("THIP30T263"));
          }
          if (bo.isMacchinaOccupataAltroOperatore()) {
            errorList.add(new ErrorMessage("THIP30T261"));
          }
        }
        //Fix 13264 fine
        displayErrori(out, errorList);
      }

    }
    //Fix 30900 -- inizio
    if(bo.getPersDatiPrdCausaleRilev() != null) { 
      out.println(" var isStampaBollaFineDich = " + bo.getPersDatiPrdCausaleRilev().isStampaBollaFineDich() +";");
      out.println(" var isAbilitaRicalcoloQta = " + bo.getPersDatiPrdCausaleRilev().isAbilitaRicalcoloQta() +";"); //Fix 39680
    }
    //Fix 39680 -- Inizio
    if(bo != null && bo.getArticolo() != null && bo.getArticolo().getIdUMSecMag() != null) {
    	out.println(" var isArticoloConUMSec = true ;");
    }
    else {
    	out.println(" var isArticoloConUMSec = false ;");
    }
    //Fix 39680 -- Fine
    if(bo.getStampaBolla().equals("Y"))
      out.println(" document.getElementById('StampaBollaTxt').innerText ='" + ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "StampaBollaInCorso") + "';");
    //Fix 30900 -- fine
    out.println("</script>");
    //Fix 15250 inizio
    if (action != null && action.equals(RilevDatiPrdTSFormActionAdapter.INDIETRO)) {
      azioneIndietro(out);
    } //Fix 15250 fine
    
    //Fix 30572 -- inizio 
    if(bo.getTipoBolla() == RilevazioneDatiProdTes.BOLLA_CUCITA) {
    	out.println("<script language='JavaScript1.2'>");
    	out.println("document.getElementById('TrCommessa').style.display=displayNone;");
    	out.println("</script>");
    }
    //Fix 30572 -- fine
    //Fix 33517 -- Inizio
    if(bo.getTipoBolla() == RilevazioneDatiProdTes.BOLLA_CUCITA) {
	    out.println("<script language='JavaScript1.2'>");
		out.println("document.getElementById('TrConfigurazione').style.display=displayNone;");
		out.println("</script>");
    }else {
    	if(bo.getArticolo() != null && bo.getArticolo().getSchemaCfg() != null) {
    		out.println("<script language='JavaScript1.2'>");
    		out.println("document.getElementById('TrConfigurazione').style.display=displayBlock;");
    		out.println("</script>");	
    	}
    }
    //Fix 33517 -- Fine
    //Fix 38994 --Inizio
    String isDaBollaCucita = (String)getServletEnvironment().getRequest().getParameter("DaBollaCucita");
    if(isDaBollaCucita != null && isDaBollaCucita.equals("Y")) {
    	String bollaCucitaKey = (String)getServletEnvironment().getRequest().getParameter("BollaCucitaTesKey");
    	if(bollaCucitaKey != null && !bollaCucitaKey.equals("")) {
    		try {
				BollaCucitaTes bollaCucitaTes = BollaCucitaTes.elementWithKey(bollaCucitaKey, PersistentObject.NO_LOCK);
				if(bollaCucitaTes != null && bollaCucitaTes.getNumeroRitorno() != null) {
					out.println("<script language='JavaScript1.2'>");
					out.println(" eval('document.forms[0].' + idFromName['BollaLavorazione']).value = '" + WebElement.formatStringForHTML(bollaCucitaTes.getNumeroRitorno()) + "';");
		    		out.println("</script>");
				}
			} catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
    	}
    }
    //Fix 38994 -- Fine
  }

  public void setTitle(JspWriter out, String textTitle) throws MissingResourceException, IOException {
	//Fix 32648 -- Inizio  
    /*out.println(" setInnerText(top.document.getElementById('Titolo'), '" + WebElement.formatStringForHTML(textTitle) + "');");
    out.println(" top.document.title = '" + WebElement.formatStringForHTML(textTitle) + "';");*/
	out.println(" setInnerText(parent.document.getElementById('Titolo'), '" + WebElement.formatStringForHTML(textTitle) + "');");
	out.println(" parent.document.title = '" + WebElement.formatStringForHTML(textTitle) + "';");  
	//Fix 32648 -- Fine
  }

  //public void azioneProduzione(JspWriter out, RilevDatiPrdTS bo, String inizioTitle, String ripresaTitle, String sospensioneTitle, String fineTitle) throws IOException { // Fix 14721
  public void azioneProduzione(JspWriter out, RilevDatiPrdTS bo, String inizioTitle, String ripresaTitle, String sospensioneTitle, String fineTitle, String ridottaTitle) throws IOException { // Fix 14721
    changeTitle(out, bo, inizioTitle, ripresaTitle, sospensioneTitle, fineTitle, ridottaTitle);
    if (bo.getTipoTimbratura() == RilevazioneDatiProdRig.INIZIO || bo.getTipoTimbratura() == RilevazioneDatiProdRig.RIPRESA) {
      displaySospendiRilevazioneinCorso(out, bo);
      enableOraInizio(out, bo);
      if(bo.getTipoTimbratura() == RilevazioneDatiProdRig.RIPRESA){
        disableSospesaRilevInCorso(out);
      }
	  //Fix 24177 inizio
      if(bo.getTipoTimbratura() == RilevazioneDatiProdRig.INIZIO)
      	dispalyBtnSucc(bo, out);
      //Fix 24177 fine
    }
    else if (bo.getTipoTimbratura() == RilevazioneDatiProdRig.SOSPENSIONE || bo.getTipoTimbratura() == RilevazioneDatiProdRig.FINE) {
      displayRiprendiRilevazioneSospesa(out, bo);
      valorizzaQuantita(out, bo);
      enableOraFine(out, bo);
      enableDataFine(out, bo); //Fix 30440
      if(bo.getTipoTimbratura() == RilevazioneDatiProdRig.SOSPENSIONE){
        disableRiprendiRilevInCorso(out);
      }
      //Fix 14721 -- inizio
      hideQtaField(out, bo);
      if(bo.getTipoTimbratura() == RilevazioneDatiProdRig.FINE){
        if(bo.getPersDatiPrdCausaleRilev() != null && bo.getPersDatiPrdCausaleRilev().isAbilitaDichRidotta()){
          hideTempoField(out, bo);
          char tipoDichiarazione = bo.getPersDatiPrdCausaleRilev().getTipoDichiarazione();
          if(tipoDichiarazione == PersDatiPrdCausaleRilev.VERSAMENTO ||
             //tipoDichiarazione == PersDatiPrdCausaleRilev.PRELIEVO){//Fix 24177
          	 bo.verificaCondizionePrelievi()) {//Fix 24177
        	 //Fix 32648 -- Inizio
             /*out.println("top.document.getElementById('Conferma').style.display=displayNone;");
             out.println("top.document.getElementById('Successivo').style.display=displayBlock;");
             out.println("top.document.getElementById('ConfermaStampa').style.display=displayNone;"); //Fix 30900
             */
      	     out.println("parent.document.getElementById('Conferma').style.display=displayNone;");
             out.println("parent.document.getElementById('Successivo').style.display=displayBlock;");
             out.println("parent.document.getElementById('ConfermaStampa').style.display=displayNone;"); 
             //Fix 32648 Fine  
          }
        }
      }
      //Fix 14721 -- fine
    }
    changeRisorsaLabel(out, bo);
  }

  //public void azioneNonProduzione(JspWriter out, RilevDatiPrdTS bo, String inizioTitle, String ripresaTitle, String sospensioneTitle, String fineTitle) throws IOException { // Fix 14721
  public void azioneNonProduzione(JspWriter out, RilevDatiPrdTS bo, String inizioTitle, String ripresaTitle, String sospensioneTitle, String fineTitle, String ridottaTitle) throws IOException { // Fix 14721
    changeTitle(out, bo, inizioTitle, ripresaTitle, sospensioneTitle, fineTitle, ridottaTitle); // Fix 14721
    if (bo.getTipoTimbratura() == RilevazioneDatiProdRig.INIZIO || bo.getTipoTimbratura() == RilevazioneDatiProdRig.RIPRESA) {
      enableOraInizio(out, bo);
    }
    else if (bo.getTipoTimbratura() == RilevazioneDatiProdRig.SOSPENSIONE || bo.getTipoTimbratura() == RilevazioneDatiProdRig.FINE) {
      enableOraFine(out, bo);
      enableDataFine(out, bo); //Fix 30440
      if(bo.getPersDatiPrdCausaleRilev() != null && bo.getPersDatiPrdCausaleRilev().isAbilitaDichRidotta()) //Fix 15582
        hideTempoField(out, bo); //Fix 15582
        hideQtaField(out, bo); //Fix 42882
    }
  }

  //public void changeTitle(JspWriter out, RilevDatiPrdTS bo, String inizioTitle, String ripresaTitle, String sospensioneTitle, String fineTitle) throws IOException { //Fix 14721
  public void changeTitle(JspWriter out, RilevDatiPrdTS bo, String inizioTitle, String ripresaTitle, String sospensioneTitle, String fineTitle, String ridottaTitle) throws IOException { //Fix 14721
    if (bo.getTipoTimbratura() == RilevazioneDatiProdRig.INIZIO) {
      setTitle(out, inizioTitle + " " + bo.getDescrizioneCausaleRilev());
    }
    else if (bo.getTipoTimbratura() == RilevazioneDatiProdRig.RIPRESA) {
      setTitle(out, ripresaTitle + " " + bo.getDescrizioneCausaleRilev());
    }
    else if (bo.getTipoTimbratura() == RilevazioneDatiProdRig.SOSPENSIONE) {
      setTitle(out, sospensioneTitle + " " + bo.getDescrizioneCausaleRilev());
    }
    if (bo.getTipoTimbratura() == RilevazioneDatiProdRig.FINE
        && !bo.getPersDatiPrdCausaleRilev().isAbilitaDichRidotta()) { // Fix 14721
      setTitle(out, fineTitle + " " + bo.getDescrizioneCausaleRilev());
    }
    // Fix 14721 inizio
    else if (bo.getTipoTimbratura() == RilevazioneDatiProdRig.FINE
        && bo.getPersDatiPrdCausaleRilev().isAbilitaDichRidotta()) {
        setTitle(out, ridottaTitle + " " + bo.getDescrizioneCausaleRilev());
    }
    // Fix 14721 fine
  }

  public List getErroriInCaricamentoProduzione(RilevDatiPrdTS bo, JspWriter out) throws IOException { //Fix 15217
    List errorList = new ArrayList();
    //Fix 13082 --inizio
    /*else if (bo.getTipoTimbratura() == RilevazioneDatiProdRig.RIPRESA) {
      RilevazioneDatiProdTes testata = bo.cercaRilevazioneInCorso();
      if (testata == null) {
        errorList.add(new ErrorMessage("THIP30T233"));
      }
    }*/
    //Fix 13082 --fine
    if (bo.getTipoTimbratura() == RilevazioneDatiProdRig.SOSPENSIONE || bo.getTipoTimbratura() == RilevazioneDatiProdRig.FINE) { //Fix 13434
      //Fix 15217 inizio
      boolean ridotta = bo.getPersDatiPrdCausaleRilev().isAbilitaDichRidotta(); //Fix 13264
      if (!ridotta) { //Fix 13264 //Fix 13434
        RilevazioneDatiProdTes testata = bo.cercaRilevazioneInCorso();
        if (testata != null){
          bo.setRilevazioneTesFine(testata); //Fix 27778
          out.println("document.getElementById(\"IdRisorsa\").value = '" + WebElement.formatStringForHTML(testata.getIdRisorsa()) + "';");
        }
        else
          errorList.add(new ErrorMessage("THIP30T233"));
      }
      //Fix 15217 fine
      //Fix 12974 --inizio
      if(iQtaBuona != null && iQtaBuona.compareTo(new BigDecimal("0")) == 0 && bo.getTipoBolla() != RilevazioneDatiProdTes.BOLLA_CUCITA){ //Fix 15030
        errorList.add(new ErrorMessage("THIP30T260"));
      }
      //Fix 12974 --fine
    }
    if (bo.getTipoTimbratura() == RilevazioneDatiProdRig.RIPRESA) {
      RilevazioneDatiProdTes testata = bo.cercaRilevazioneSospesa();
      if (testata != null)
        out.println("document.getElementById(\"IdRisorsa\").value = '" + WebElement.formatStringForHTML(testata.getIdRisorsa()) + "';"); //Fix 15217
      else
        errorList.add(new ErrorMessage("THIP300304"));
    }
    //Fix 16735 inizio
    if (bo.getAttivitaEsecutiva() != null) {
      boolean ridotta = bo.getPersDatiPrdCausaleRilev().isAbilitaDichRidotta();
      boolean bloccoInizio = bo.getPersDatiPrdCausaleRilev().isBloccoInizioAttivita();
      if (bloccoInizio && (bo.getTipoTimbratura() == RilevazioneDatiProdRig.INIZIO || ridotta)) {
      	//Fix 24042 inizio
        //AttivitaEsecutiva atvPrec = (AttivitaEsecutiva)bo.getAttivitaEsecutiva().getAtvEsecPrecedente();
      	AttivitaEsecutiva atvEsec = bo.getAttivitaEsecutiva();
      	AttivitaEsecutiva atvPrec = atvEsec.getAtvEsecPrecNoAuto(atvEsec);
        //Fix 24042 fine
        if (atvPrec != null && atvPrec.getPoliticaConsAttivita() == AttivitaEsecutiva.MANUALE && RilevDatiPrdTS.getQtaProdottaAtv(atvPrec).compareTo(new BigDecimal("0")) == 0) { //Fix 20748
          Vector param = new Vector();
          //Fix 24042 inizio
          //String param1 = bo.getAttivitaEsecutiva().getAtvEsecPrecedente().getIdOperazione() + "-" + bo.getAttivitaEsecutiva().getAtvEsecPrecedente().getIdAttivita();
          String param1 = atvPrec.getIdOperazione() + "-" + atvPrec.getIdAttivita();
          //Fix 24042 fine
          param.add(param1);
          ErrorMessage errMes = new ErrorMessage("THIP40T249", true, param);
          errorList.add(errMes);
        }
      }
    }
    //Fix 16735 fine

    return errorList;
  }

  public List getErroriInCaricamentoNonProd(RilevDatiPrdTS bo) {
    List errorList = new ArrayList();
    if (bo.getTipoTimbratura() == RilevazioneDatiProdRig.INIZIO) {
      RilevazioneDatiProdTes testata = bo.cercaRilevazioneInCorsoNonProd();
      if (testata != null) {
        errorList.add(new ErrorMessage("THIP30T234"));
      }
    }
    else if ((bo.getTipoTimbratura() == RilevazioneDatiProdRig.SOSPENSIONE ||
             bo.getTipoTimbratura() == RilevazioneDatiProdRig.FINE) &&
             ! (bo.getPersDatiPrdCausaleRilev() != null && bo.getPersDatiPrdCausaleRilev().isAbilitaDichRidotta())) { //Fix 15582 //Fix 16741
      RilevazioneDatiProdTes testata = bo.cercaRilevazioneInCorsoNonProd();
      if (testata == null) {
        errorList.add(new ErrorMessage("THIP30T235"));
      }
    }
    //Fix 13434 inizio
    else if (bo.getTipoTimbratura() == RilevazioneDatiProdRig.RIPRESA) {
      RilevazioneDatiProdTes testata = bo.cercaRilevazioneSospesaNonProd();
      if (testata == null) {
        errorList.add(new ErrorMessage("THIP300305"));
      }
    }
    //Fix 13434 fine
    return errorList;
  }

  //Fix 15866 inizio
  public void writeFormStartElements(JspWriter out) throws java.io.IOException {
    super.writeFormStartElements(out);
    out.println(WebJSTypeList.getImportForJSLibrary("it/thera/thip/produzione/raccoltaDati/KeyPad.js", getServletEnvironment().getRequest())); //Fix 32648
    out.println(WebJSTypeList.getImportForJSLibrary("it/thera/thip/produzione/raccoltaDati/KeyPadOra.js", getServletEnvironment().getRequest())); //Fix 42882
    String oldAction = (String) getRequest().getAttribute("Action"); //Fix 12974
    out.println("<input type=\"hidden\" id=\"thOldAction\" name=\"thOldAction\" value=\"" + oldAction + "\"/>");
    //Fix 16109 inizio
    String jsp = (String) getRequest().getAttribute("JspName");
    if (jsp == null) //Fix 16528
      jsp = "it/thera/thip/produzione/raccoltaDati/NuovaDichiarazione.jsp"; //Fix 16528
    out.println("<input type=\"hidden\" id=\"thOldJspName\" name=\"thOldJspName\" value=\"" + jsp + "\"/>");
    String prevJsp = (String) getRequest().getAttribute("PrevJspName");
    out.println("<input type=\"hidden\" id=\"thOldJspName\" name=\"thPrevJspName\" value=\"" + prevJsp + "\"/>");
    //Fix 16109 fine
  }
  //Fix 15866 fine

  public void writeFormEndElements(JspWriter out) throws java.io.IOException {
    super.writeFormEndElements(out);
    RilevDatiPrdTS bo = (RilevDatiPrdTS) getBODataCollector().getBo();
    String action = (String) getRequest().getAttribute("Action");
    if (action != null && (action.equals(RilevDatiPrdTSFormActionAdapter.INIZIO) ||
                           action.equals(RilevDatiPrdTSFormActionAdapter.RIPRESA) ||
                           action.equals(RilevDatiPrdTSFormActionAdapter.SOSPENSIONE) ||
                           action.equals(RilevDatiPrdTSFormActionAdapter.FINE) ||
                           action.equals(RilevDatiPrdTSFormActionAdapter.RIDOTTA) || // Fix 14721
                           action.equals(RilevDatiPrdTSFormActionAdapter.PROSSIMA_ATV))) { //Fix 12841
      if(bo.getPersDatiPrdCausaleRilev() == null){ //Fix 12974
        addBottoniCausaliRilevazione(out, bo);
      }
    }
    else if (action != null && (action.equals(RilevDatiPrdTSFormActionAdapter.MONITOR))) {
      displayDichiarazioneInCorso(out, bo);
    }
    //Fix 14722 --inizio
    else if (action != null && (action.equals(RilevDatiPrdTSFormActionAdapter.DETTAGLIO))) {
      displayDettaglioBollaCucita(out, bo);
    }
    //Fix 14722 --fine
    //Fix 13082 --inizio
    else if (action != null && (action.equals(RilevDatiPrdTSFormActionAdapter.PRODUZIONE))) {
      //Fix 13175 --inizio
      //Risorsa risoraPrincipale = bo.getRisorsaPrincipale();
      Risorsa risoraPrincipale = bo.getIdMacchina() != null ? bo.getMacchina() : bo.getRisorsaPrincipale();
      //Fix 29945 Inizio
      /*System.out.print(bo.getIdMacchina());
      System.out.print(bo.getBollaLavorazione());
      System.out.print(bo.getRisorsaKey());
      System.out.print(bo.getAttivitaEsecutiva());
      System.out.print(bo.getRisorsaPrincipale());*/
      //Fix 29945 Fine
      //Fix 13175 --fine
      //Fix 32334 -- Inizio
      if(bo.getPersDatiPrdCausaleRilev() != null && bo.getPersDatiPrdCausaleRilev().isOreUomoRsrSec()) {
    	  Risorsa matricolaOperatore = bo.getOperatoreRisorsa() ;
    	  if(matricolaOperatore != null) {
    		  out.println("<script language='JavaScript1.2'>");
    	      out.println(" eval('document.forms[0].' + idFromName['IdRisorsa']).value = '" + WebElement.formatStringForHTML(matricolaOperatore.getIdRisorsa()) + "';");
    	      out.println("</script>");  
    	  }
      }
      else {
      //Fix 32334 -- Fine	  
	      //Fix 17808 --inizio
	      if(risoraPrincipale.getTipoRisorsa() == Risorsa.RISORSE_UMANE){
	        Risorsa ru = bo.getMatricolaDichiarante();
	        if(ru != null && bo.getRisorse().contains(ru)){
	          out.println("<script language='JavaScript1.2'>");
	          out.println(" eval('document.forms[0].' + idFromName['IdRisorsa']).value = '" + WebElement.formatStringForHTML(ru.getIdRisorsa()) + "';");
	          out.println("</script>");
	        }
	        else if(risoraPrincipale.getLivelloRisorsa() == Risorsa.MATRICOLA){
	        	out.println("<script language='JavaScript1.2'>");
	          out.println(" eval('document.forms[0].' + idFromName['IdRisorsa']).value = '" + WebElement.formatStringForHTML(risoraPrincipale.getIdRisorsa()) + "';");
	          out.println("</script>");
	        }
	      } //Fix 17808 --fine
	      else if (risoraPrincipale.getLivelloRisorsa() == Risorsa.MATRICOLA) {
	        out.println("<script language='JavaScript1.2'>");
	        out.println(" eval('document.forms[0].' + idFromName['IdRisorsa']).value = '" + WebElement.formatStringForHTML(risoraPrincipale.getIdRisorsa()) + "';");
	        out.println("</script>");
	      }
      }//Fix 32334
    }
    //Fix 13082 --fine
    //Fix 43970 -- Inizio
    else if(action != null && (action.equals(RilevDatiPrdTSFormActionAdapter.STAMPA_BOLLA_FINE))) {
    	String collKey = (String) getServletEnvironment().getRequest().getAttribute("MOSTRA_DOC_COLLAUDO");
    	//Fix 45164 -- Inizio
    	if(collKey == null) {
    		collKey = "";
    	}
    	//Fix 45164 -- Fine    	
    	out.println("<input type=\"hidden\" name=\"thCollaudoKey\" value=\"" + collKey + "\">");
    }
    //Fix 43970 -- Fine
    //Fix 14721 --inizio
    out.println("<script language='JavaScript1.2'>");
    out.println("function fillParametriLotto() {");
    out.println("  tipo = \"" + PersDatiMagazzino.TIPO_PRD + "\";");
    out.println("  numeroOrdDoc = eval(\"document.forms[0].\" + idFromName[\"IdNumeroOrdine\"]).value;");
    out.println("  annoOrdDoc = eval(\"document.forms[0].\" + idFromName[\"IdAnnoOrdine\"]).value;");
    if(bo != null && bo.getAttivitaEsecutiva() != null){
      OrdineEsecutivo ord = bo.getAttivitaEsecutiva().getOrdineEsecutivo();
      if(ord != null)
        out.println("  dataOrdDoc = \"" + ord.getDataOrdine() + "\";");
    }
    else
      out.println("  dataOrdDoc = \"\";");
    //out.println("  numRiga = eval(\"document.forms[0].\" + idFromName[\"IdRigaProdotto\"]).value;");
    out.println("  numRiga = \"\";");
    out.println("  numRigaPadre = eval(\"document.forms[0].\" + idFromName[\"IdRigaAttivita\"]).value;");
    out.println("  provenienza = \"" + PersDatiMagazzino.CREA_DA_ORDINE + "\";");
    out.println("  versione = eval(\"document.forms[0].\" + idFromName[\"IdVersionePrd1\"]).value;");
    out.println("  configurazione = eval(\"document.forms[0].\" + idFromName[\"IdEsternoConfigPrd1\"]).value;");
    //out.println("  magazzino = eval(\"document.forms[0].\" + idFromName[\"IdMagazzinoVrs\"]).value;");
    out.println("  magazzino = \"\";");
    //out.println("  commessa = eval(\"document.forms[0].\" + idFromName[\"IdCommessa\"]).value;");
    out.println("  commessa = \"\";");
    out.println("  tipoRiga = \"" + ProposizioneAutLotto.RIGA_VERSAMENTO + "\";");
    out.println("  idRigaOrdMat = \"\";");
    out.println("}");

    out.println("function fillqtaLotto() {");
    out.println("  quantitaLottoPrmField = eval(\"document.forms[0].\"+idFromName[\"LottoProdotto1.QtaRichiestaUMPrm\"]);");
    out.println("  quantitaLottoRifField = null;");
    out.println("  quantitaLottoSecField = eval(\"document.forms[0].\"+idFromName[\"LottoProdotto1.QtaRichiestaUMSec\"]);");
    out.println("}");

    out.println("</script>");
    //Fix 14721 --fine
    //Fix 18536 inizio
    String thChiaveDatiSessione = salvaInSessione(getServletEnvironment());
    out.println("<input type=\"hidden\" name=\"thChiaveDatiSessione\" value=\"" + thChiaveDatiSessione + "\">");
    getServletEnvironment().getSession().setAttribute(thChiaveDatiSessione, bo);
    caricaIndex(out,bo);
    //Fix 18536 fine
    //Fix 46031 -- Inizio
    String chiaviOggettotMatricolati = (String) getServletEnvironment().getRequest().getAttribute("MOSTRA_GEST_MATRICOLE");
	if(chiaviOggettotMatricolati == null) {
	  chiaviOggettotMatricolati = "";
	}    	
	out.println("<input type=\"hidden\" name=\"thChiaviOggettotMatricolati\" value=\"" + chiaviOggettotMatricolati + "\">");
	//Fix 46031 -- Fine
  }

  public void addBottoniCausaliRilevazione(JspWriter out, RilevDatiPrdTS bo) throws IOException {
    Vector causaliRilevazioniProduttive = (bo.getBollaLavorazione() != null ? bo.getCausaliRilevazioniProduttive() : bo.getCausaliRilevazioniNonProduttive());
    //Fix 16741 inizio
    if(bo.getBollaLavorazione() == null && bo.getIdMacchina() != null ){
      causaliRilevazioniProduttive = bo.getCausaliRilevazioniFermo();
    }
    //Fix 16741 fine
    String action = (String) getRequest().getAttribute("Action"); // Fix 14721
    //Fix 13434 inizio
    out.println("<table cellpadding=\"0\" cellspacing=\"0\">");
    out.println("<tr valign=\"top\">");
    out.println("<td style=\"height:30px\"></td>");
    out.println("</tr>");
    out.println("<tr valign=\"top\">");
    out.println("<td width=\"15px\"></td>");
    //Fix 13434 fine
    out.println("<td>");
    //out.println("<table id=\"extraTable\" cellpadding=\"5\" cellspacing=\"5\">");//Fix 13574
    out.println("<table id=\"extraTable\" cellpadding=\"3\" cellspacing=\"3\">"); //Fix 13574
    out.println("<tr valign=\"top\">");
    int j = 0; //Fix 13175
    //Fix 30236 inizio
    int nbrButton = 4 ;
    if(bo.getPersDatiPrdUtenteRilev().getRisoluzioneVideo() == PersDatiPrdUtenteRilev.RISOL_800_600) {
  	  nbrButton = 3 ;
    }
    //Fix 30236 fine
    for (int i = 0; i < causaliRilevazioniProduttive.size(); i++) {
      PersDatiPrdCausaleRilev causaleRilev = (PersDatiPrdCausaleRilev) causaliRilevazioniProduttive.get(i);
      if (j <= nbrButton) //Fix 13175 //Fix 13264 //Fix 30236
        //Fix 14721 inizio
        //addButton(out, causaleRilev);
        {
          if(causaleRilev.isVisualizzaInRilevazione()){ //Fix 17729 Inizio
            if (action != null && action.equals(RilevDatiPrdTSFormActionAdapter.RIDOTTA)){
              if (causaleRilev.isAbilitaDichRidotta()){
                addButton(out, causaleRilev);
                j++; //Fix 15030
              }
            }
            else{
              if (!causaleRilev.isAbilitaDichRidotta()){
                addButton(out, causaleRilev);
                j++; //Fix 15030
              }
            }
          } //Fix 17729 Fine
        } //Fix 14721 fine
        else {
        out.println("</tr>");
        out.println("<tr>");
        //j=-1; //Fix 14722 //Fix 32012 riga commentata
        j=0;//Fix 32012
        //Fix 18942 Inizio
        if(causaleRilev.isVisualizzaInRilevazione()){
          if (action != null && action.equals(RilevDatiPrdTSFormActionAdapter.RIDOTTA)){
            if (causaleRilev.isAbilitaDichRidotta()){
              addButton(out, causaleRilev);
              j++;
            }
          }
          else{
            if (!causaleRilev.isAbilitaDichRidotta()){
              addButton(out, causaleRilev);
              j++;
            }
          }
        }
        //Fix 18942 Fine
      }
      //j++;//Fix 13175 //Fix 15030
    }
    out.println("</tr>");
    //Fix 13434 inizio
    out.println("</table>");//Fix 13175
    out.println("</td>");//Fix 13175
    out.println("</tr>");
    //Fix 13434 fine
    out.println("<tr>"); //Fix 13264
    out.println("<td colspan=\"5\" style=\"height:100%\"></td>"); //Fix 13264
    out.println("</tr>");
    //Fix 13082 --inizio
    out.println("<tr>");
    out.println("<td width=\"5px\">");//Fix 13175
    out.println("</td>");//Fix 13175
    out.println("<td colspan=\"5\">");
    //Fix 13434 inizio
   // out.println("<table cellpadding=\"5\" cellspacing=\"5\">");//Fix 13574
    out.println("<table cellpadding=\"3\" cellspacing=\"3\">");//Fix 13574
    out.println("<tr>");
    out.println("<td><label class=\"labelError\" id=\"ErroriList\"></label></td>");
    out.println("</tr>");
    out.println("</table>");
    out.println("</td>");
    out.println("</tr>");
    out.println("</table>");
    //Fix 13434 fine
    //Fix 13082 --fine
  }

  public void addButton(JspWriter out, PersDatiPrdCausaleRilev causaleRilev) throws java.io.IOException {
    String idCausRilev = causaleRilev.getIdCausaleRilevazione();
    String descrCausRilev = causaleRilev.getDescrizione();
    String azionebutton = (causaleRilev.getTipoDichiarazione() == PersDatiPrdCausaleRilev.NON_PRODUTTIVA ? "nonProduzioneAction()" : "produzioneAction()");
    //Fix 16741 inizio
    if(causaleRilev.getTipoDichiarazione() == PersDatiPrdCausaleRilev.FERMO)
    {
      azionebutton = "fermoAction();";
    }
    //Fix 16741 fine
    //out.println("<td><button id=\"" + idCausRilev + "\" name=\"" + idCausRilev + "\"onclick=\"setCurrentEvent(event);" + azionebutton + "\" style=\"width: 200px\" type=\"button\">" + descrCausRilev + "</button></td>");//Fix 13574
    out.println("<td><button id=\"" + idCausRilev + "\" name=\"" + idCausRilev + "\"onclick=\"setCurrentEvent(event);" + azionebutton + "\" type=\"button\">" + descrCausRilev + "</button></td>"); //Fix 13574

  }

  public void displayErrori(JspWriter out, List errori) throws java.io.IOException {
    if (errori != null && !errori.isEmpty()) {
      Iterator iter = errori.iterator();
      String msgError = "";
      String codErrorsForced = ""; //Fix 16109
      boolean forceErrors = true; //Fix 40485
      while (iter.hasNext()) {
        ErrorMessage error = (ErrorMessage) iter.next();
        if (error.getAttOrGroupLabel() != null) {
          msgError += error.getAttOrGroupLabel() + ": " + error.getLongText() + "<BR>";//Fix 16734
        }
        else {
          msgError += error.getLongText() + "<BR>";//Fix 16734
        }
        //Fix 16109 inizio
        //if (error.getId().equals("THIP300302") ||  error.getId().equals("THIP40T171")) {//Fix 16735
        //if (error.getId().equals("THIP300302") ||  error.getId().equals("THIP40T171") || error.getId().equals("THIP40T254")) {//Fix 16735 // Fix 17955
          if (error.getId().equals("THIP300302") ||  error.getId().equals("THIP40T171") || error.getId().equals("THIP40T254") || error.getId().equals("THIP40T286") || error.getId().equals("THIP40T287") || error.getId().equals("THIP40T423") || error.getId().equals("THIP40T430")) { // Fix 17955 //Fix 17963 //Fix 22630 //Fix 22890
          if (!codErrorsForced.equals(""))
            codErrorsForced += KeyHelper.KEY_SEPARATOR;
          codErrorsForced += error.getId();
          //Fix 40485 -- Inizio
          if(error.getSeverity() == ErrorMessage.ERROR) {
        	forceErrors = false;
          }
          //Fix 40485 -- Fine
        }
        if (error.getId().equals("THIP300301")) {
          if (!codErrorsForced.equals(""))
            codErrorsForced += KeyHelper.KEY_SEPARATOR;
          //codErrorsForced += error.getId() + KeyHelper.KEY_SEPARATOR + "THIP300302" +  KeyHelper.KEY_SEPARATOR + "THIP40T171" +  KeyHelper.KEY_SEPARATOR + "THIP40T254"; //Fix 16735
          codErrorsForced += error.getId() + KeyHelper.KEY_SEPARATOR + "THIP300302" +  KeyHelper.KEY_SEPARATOR + "THIP40T171" +  KeyHelper.KEY_SEPARATOR + "THIP40T254" +  KeyHelper.KEY_SEPARATOR + "THIP40T286"  + KeyHelper.KEY_SEPARATOR + "THIP40T287" + KeyHelper.KEY_SEPARATOR + "THIP40T423" + KeyHelper.KEY_SEPARATOR + "THIP40T430"; // Fix 17955 //Fix 17963 //Fix 22630 //Fix 22890
          //Fix 40485 -- Inizio
          if(error.getSeverity() == ErrorMessage.ERROR) {
        	forceErrors = false;
          }
          //Fix 40485 -- Fine
        }
        //Fix 16109 fine
        //Fix 15250 inizio
      }
      //if (!codErrorsForced.equals("")) //Fix 16109 //Fix 40485
      if (!codErrorsForced.equals("") && forceErrors) //Fix 40485
        out.println(" document.getElementById('thCodErrorsForced').value ='" + codErrorsForced + "';"); ; //Fix 16109

      out.println("var msgErr ='" + WebElement.formatStringForHTML(msgError) + "';");
      //Fix 16734 inizio modifica per andare a capo correttamente in Chrome
      out.println("document.getElementById(\"ErroriList\").innerHTML = msgErr;");
      //Fix 16734 fine
      //Fix 13434 inizio
      out.println("if (document.getElementById(\"ErroriList\") != null)");
      out.println("  window.scrollTo(0, topPosition(document.getElementById(\"ErroriList\")))");
      out.println("function topPosition(obj) {");
      out.println("  var curtop = 0;");
      out.println("  if (obj) {");
      out.println("    do {");
      out.println("      curtop += obj.offsetTop;");
      out.println("     } while (obj = obj.offsetParent);");
      out.println("  }");
      out.println("  return curtop;");
      out.println("}");
      //Fix 13434 fine
    }
  }

  public void displayDichiarazioneInCorso(JspWriter out, RilevDatiPrdTS bo) throws IOException {
    impostaAttributiBOPers(bo);		//Fix 42131
	  
	List dichiarazioneInCorsoList = bo.cercaDichiarazioneInCorso();
    //Fix 30236 inizio
    String width = "\"width:117px\"";
    if(bo.getPersDatiPrdUtenteRilev().getRisoluzioneVideo()== PersDatiPrdUtenteRilev.RISOL_800_600) {
    	width = "\"width:100px\"" ;
    }
    //Fix 30236 fine
    //Fix 13434 inizio
    out.println("<table cellpadding=\"0\" cellspacing=\"0\" style=\"width:100%\">");
    out.println("<tr valign=\"top\">");
    //out.println("<td style=\"height:15px\"></td>");//Fix 13574
    out.println("<td style=\"height:0px\"></td>"); //Fix 13574
    out.println("<tr>");
    out.println("<td width=\"15px\"></td>");
    out.println("<td>");
    //Fix 13434 fine
    out.println("<td>");
    //out.println("  <table id=\"extraTable\" cellpadding=\"5\" cellspacing=\"0\" class=\"mytable\" style=\"height: 100%; width: 100%\">");//Fix 13574
    //Fix 24211 inizio
    //out.println("  <table id=\"extraTable\" cellpadding=\"3\" cellspacing=\"3\" class=\"monitorListTable\" style=\"height: 100%; width: 100%\">"); //Fix 13574
    //out.println("  <table id=\"extraTable\" cellpadding=\"1\" cellspacing=\"3\" class=\"monitorListTable\" style=\"height: 100%; width: 100%\">"); //Fix 13574
    out.println("  <table id=\"extraTable\" cellpadding=\"1\" cellspacing=\"3\" class=\"monitorListTable\">"); //Fix 30236
    //Fix 24211 fine
    String sospensioneBut = ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "ButSospensione");
    String fineBut = ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "ButFine");
    String ripresaBut = ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "ButRipresa");
    String butDettaglio = ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "ButDettaglio");//Fix 30298
    //Fix 12841 --inizio
    //Fix 13175 --inizio
    //String idAmbiente = getIdAmbiente(dichiarazioneInCorsoList);
    AmbienteListaAttivita ambiente = RilevDatiPrdTS.getAmbienteForCurrentUser();
    String idAmbiente = ambiente != null ? ambiente.getIdAmbiente() : "";
    //Fix 13175 --fine
    //Fix 12841 --fine
    //Fix 30298 inizio
    out.println("  <tr>");
    out.println("    <th class=\"cell\" ></th>");
    out.println("    <th class=\"cell\" >" + WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "N_ritorno")) + "</th>");
    out.println(getHTMLTitoloDichInCorsoPers01()); //40458
    out.println("    <th class=\"cell\" >" + WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "N_ord_bolla")) + "</th>");
    out.println(getHTMLTitoloDichInCorsoPers02()); //40458
    out.println("    <th class=\"cell\" >" + WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "Articolo")) + "</th>");
    out.println(getHTMLTitoloDichInCorsoPers03()); //40458
    out.println("    <th class=\"cell\" >" + WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "Risorsa")) + "</th>");
    out.println(getHTMLTitoloDichInCorsoPers04()); //40458
    out.println("    <th class=\"cell\" >" + WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "Qta_UM")) + "</th>");
    out.println(getHTMLTitoloDichInCorsoPers05()); //40458
    out.println("  </tr>");
    //Fix 30298 fine
    int index = 0;
    Iterator iter = dichiarazioneInCorsoList.iterator();
    while (iter.hasNext()) {
      RilevazioneDatiProdTes testata = (RilevazioneDatiProdTes) iter.next();
      if(!testata.getRighe().isEmpty()){
        //Fix 13082 --inizio
        String srcImg = "it/thera/thip/produzione/raccoltaDati/images/Green.gif";
        /*if (testata.getStatoRilevazione() == RilevazioneDatiProdTes.IN_CORSO) {
          srcImg = "it/thera/thip/produzione/raccoltaDati/images/Green.gif";
        }
        else if (testata.getStatoRilevazione() == RilevazioneDatiProdTes.SOSPESA) {
          srcImg = "it/thera/thip/produzione/raccoltaDati/images/Yellow.gif";
        }*/
        //Fix 13082 --fine
        out.println("    <tr>");
        //out.println("    <td class=\"cell\"><img type=\"image\" name=\"\" src=\"" + srcImg + "\" alt=\"\" /></td>"); //Fix 14725
        //out.println("    <td class=\"cell\"><img type=\"image\" name=\"\" src=\"" + srcImg + "\" alt=\"\" /><br>&nbsp</td>"); //Fix 14725 //Fix 35044
        //String bollaLav = testata.getTipoDichiarazione() != PersDatiPrdCausaleRilev.NON_PRODUTTIVA ? testata.getNumeroRitorno() : "";//Fix 16741
        String bollaLav = testata.getTipoDichiarazione() != PersDatiPrdCausaleRilev.NON_PRODUTTIVA && testata.getTipoDichiarazione() != PersDatiPrdCausaleRilev.FERMO ? testata.getNumeroRitorno() : "";//Fix 16741
        //Fix 15064 inizio
        String descrRilev = "";
        String numOrdFmt = "";
        String annoOrd = "";
        String numOrd = "";
        String rigaAtv = "";
        String annoBolla = "";
        String numBolla = "";
        //Fix 14725 --inizio
        String idArticolo = "";
        String descrArticolo = "";
        String idMacchina = ""; //Fix 23542
        String descrMacchina = ""; //Fix 23542
        String idUmPrimaria = "";
        String idUmSecondaria = "";
        BigDecimal[] qtaDaProdurre = new BigDecimal[2];
        //Fix 14725 --fine
		String note = ""; //Fix 26907
		String commessa = ""; //Fix 30572
		String articolo_Config = ""; //Fix 33517
        String tipoBolla = String.valueOf(testata.getTipoBolla());
        if (testata.getTipoBolla() == RilevazioneDatiProdTes.BOLLA_CUCITA) {
          out.println("    <td class=\"cell\"><img type=\"image\" name=\"\" src=\"" + srcImg + "\" alt=\"\" /><br>&nbsp</td>"); //Fix 35044	
          if (testata.getBollaCucita().getAttivita() != null)
            descrRilev = testata.getBollaCucita(). getAttivita().getDescrizione().getDescrizione();
          numOrdFmt = testata.getBollaCucita().getNumeroBolFmt();
          annoBolla = testata.getBollaCucita().getIdAnnoBolla();
          numBolla = testata.getBollaCucita().getIdNumeroBolla();
		  note = testata.getNote();//Fix 26907
        }
       // else if (testata.getTipoDichiarazione() != PersDatiPrdCausaleRilev.NON_PRODUTTIVA) {//Fix 16741
       else if (testata.getTipoDichiarazione() != PersDatiPrdCausaleRilev.NON_PRODUTTIVA && testata.getTipoDichiarazione() != PersDatiPrdCausaleRilev.FERMO) {//Fix 16741
    	  if (testata.getAttivitaEsecutiva() == null) //Fix 35044
            continue; //Fix 35044
    	  out.println("    <td class=\"cell\"><img type=\"image\" name=\"\" src=\"" + srcImg + "\" alt=\"\" /><br>&nbsp</td>"); //Fix 35044
          descrRilev = testata.getAttivitaEsecutiva().getDescrizione().getDescrizione();
          numOrdFmt = testata.getOrdineEsecutivo().getNumeroOrdFmt();
          annoOrd = testata.getOrdineEsecutivo().getIdAnnoOrdine();
          numOrd = testata.getOrdineEsecutivo().getIdNumeroOrdine();
          rigaAtv = testata.getIdRigaAttivita().toString();
          //Fix 14725 --inizio
          idArticolo = testata.getOrdineEsecutivo().getIdArticolo();
          descrArticolo = testata.getOrdineEsecutivo().getArticolo().getDescrizioneArticoloNLS().getDescrizione();
          idMacchina = testata.getIdRisorsa(); //Fix 23542
          descrMacchina = testata.getRisorsa().getDescrizione().getDescrizione(); //Fix 23542
          qtaDaProdurre = RilevDatiPrdTS.calcolaQuantitaDaPorduire(testata.getAttivitaEsecutiva());
          idUmPrimaria = testata.getIdUMPrm();
          idUmSecondaria = testata.getIdUMSec();
		  note = testata.getNote();//Fix 26907
          //Fix 14725 --fine
		  //Fix 30572 -- inizio
		  if(testata.getOrdineEsecutivo().getIdCommessa() != null)
		     commessa = testata.getOrdineEsecutivo().getIdCommessa();
		  //Fix 30572 -- fine
		  //Fix 33517 -- Inizio
		  if(testata.getOrdineEsecutivo().getConfigurazione() != null) {
			  //articolo_Config = idArticolo + "/" + testata.getOrdineEsecutivo().getConfigurazione().getIdEsternoConfig() ; //Fix 33688
			  articolo_Config = testata.getOrdineEsecutivo().getConfigurazione().getIdEsternoConfig(); //Fix 33688
		  }
		  //Fix 33517 -- Fine
        }
        else {
          out.println("    <td class=\"cell\"><img type=\"image\" name=\"\" src=\"" + srcImg + "\" alt=\"\" /><br>&nbsp</td>"); //Fix 35044
          descrRilev = testata.getPersDatiPrdCausaleRilev().getDescrizione();
          note = testata.getNote();//Fix 29435
        }
        //Fix 15064 fine
        //Fix 14725 --inizio
        //out.println("    <td id=\"BollaLavTD" + index + "\" class=\"cell\" >" + WebElement.formatStringForHTML(bollaLav) + "</td>");
        //out.println("    <td class=\"cell\" >" + WebElement.formatStringForHTML(descrRilev) + "</td>");
       // if(testata.getTipoDichiarazione() != PersDatiPrdCausaleRilev.NON_PRODUTTIVA) {//Fix 16741
				out.println(getHTMLCellaDichInCorsoPers01(index, testata));		//Fix 22513
        if(testata.getTipoDichiarazione() != PersDatiPrdCausaleRilev.NON_PRODUTTIVA && testata.getTipoDichiarazione() != PersDatiPrdCausaleRilev.FERMO) {//Fix 16741
          out.println("    <td id=\"BollaLavTD" + index + "\" class=\"cell\" >" + WebElement.formatStringForHTML(bollaLav) + "<br>" + WebElement.formatStringForHTML(descrRilev) + "</td>");
        }
        else{
          out.println("    <td id=\"BollaLavTD" + index + "\" class=\"cell\" ></td>");
          out.println("    <td class=\"cell\" >" + WebElement.formatStringForHTML(descrRilev) + "</td>");
        }
        //Fix 14725 --fine
       // out.println("    <td class=\"cell\" >" + WebElement.formatStringForHTML(numOrdine) + "</td>");//Fix 13574

				out.println(getHTMLCellaDichInCorsoPers02(index, testata));		//Fix 22513
        out.println("    <td class=\"cell\" nowrap=\"true\" >" + WebElement.formatStringForHTML(numOrdFmt) + "<br>" + WebElement.formatStringForHTML(commessa) + "</td>"); //Fix 13574 //Fix 15064 //Fix 30572
        //Fix 14725 -- inizio
        //if(testata.getTipoDichiarazione() != PersDatiPrdCausaleRilev.NON_PRODUTTIVA) {//Fix 16741
				out.println(getHTMLCellaDichInCorsoPers03(index, testata));		//Fix 22513
        if(testata.getTipoDichiarazione() != PersDatiPrdCausaleRilev.NON_PRODUTTIVA && testata.getTipoDichiarazione() != PersDatiPrdCausaleRilev.FERMO) {//Fix 16741
          //Fix 30298 inizio	
          if (testata.getTipoBolla() == RilevazioneDatiProdTes.BOLLA_CUCITA) {
        	  out.println("    <td class=\"cell\" ><button onclick=\"setCurrentEvent(event);dettaglioAction(" + index + ")\" style="+ width +">" + butDettaglio + "</button></td>");
          }else {
          //Fix 30298 fine	
        	  //Fix 33517 -- Inizio
        	  if(articolo_Config.equals("")) {
        		  out.println("    <td class=\"cell\" nowrap=\"true\" >" + WebElement.formatStringForHTML(idArticolo) + "<br>" + WebElement.formatStringForHTML(descrArticolo) + "</td>");  
        	  }else {
        		  //out.println("    <td class=\"cell\" nowrap=\"true\" >" + WebElement.formatStringForHTML(articolo_Config) + "<br>" + WebElement.formatStringForHTML(descrArticolo) + "</td>");//Fix 33688  
        		  out.println("    <td class=\"cell\" nowrap=\"true\" >" + WebElement.formatStringForHTML(idArticolo) + "<br>" + WebElement.formatStringForHTML(articolo_Config) + "<br>" + WebElement.formatStringForHTML(descrArticolo) + "</td>");//Fix 33688
        	  }
        	  //Fix 33517 -- Fine
          }//Fix 30298 
          //Fix 23542 Inizio
          if(bo.getIdMacchina() == null)
            out.println("    <td class=\"cell\" nowrap=\"true\" >" + WebElement.formatStringForHTML(idMacchina) + "<br>" + WebElement.formatStringForHTML(descrMacchina) + "</td>");
          //Fix 23542 Fine
        }
				out.println(getHTMLCellaDichInCorsoPers04(index, testata));		//Fix 22513
        if(idUmSecondaria != null){
          out.println("    <td class=\"cell\" nowrap=\"true\" >" + getValue(qtaDaProdurre[0], 2) + " " + WebElement.formatStringForHTML(idUmPrimaria) + "<br>" + getValue(qtaDaProdurre[1], 2) + " " + WebElement.formatStringForHTML(idUmSecondaria) + "</td>");
        }
        else{
          out.println("    <td class=\"cell\" nowrap=\"true\" >" + getValue(qtaDaProdurre[0], 2) + " " + WebElement.formatStringForHTML(idUmPrimaria) + "<br>&nbsp</td>");
        }
        //Fix 14725 -- fine
				out.println(getHTMLCellaDichInCorsoPers05(index, testata));		//Fix 22513
        if (testata.getStatoRilevazione() == RilevazioneDatiProdTes.IN_CORSO) {
          //out.println("    <td class=\"cell\" ><button onclick=\"setCurrentEvent(event);sospensioneAction(" + index + ")\" style=\"width: 200px\">" + sospensioneBut + "</button></td>");//Fix 13574
          //Fix 24211 inizio
          //out.println("    <td class=\"cell\" ><button onclick=\"setCurrentEvent(event);sospensioneAction(" + index + ")\" >" + sospensioneBut + "</button></td>"); //Fix 13574
          out.println("    <td class=\"cell\" ><button onclick=\"setCurrentEvent(event);sospensioneAction(" + index + ")\" style="+ width +">" + sospensioneBut + "</button></td>"); //Fix 24211 //Fix 30236
          //Fix 24211 fine
          //out.println("    <td class=\"cell\" ><button onclick=\"setCurrentEvent(event);fineAction(" + index + ")\" style=\"width: 200px\">" + fineBut + "</button></td>");//Fix 13574
          //Fix 24211 inizio
          //out.println("    <td class=\"cell\" ><button onclick=\"setCurrentEvent(event);fineAction(" + index + ")\" >" + fineBut + "</button></td>"); //Fix 13574
          out.println("    <td class=\"cell\" ><button onclick=\"setCurrentEvent(event);fineAction(" + index + ")\" style=" + width + ">" + fineBut + "</button></td>"); //Fix 13574 //Fix 30236
          //Fix 24211 fine
          out.println(getHTMLPulsantiDichInCorsoRilevInCorsoPers(index, testata, width)); // Fix 42131
        }
        else if (testata.getStatoRilevazione() == RilevazioneDatiProdTes.SOSPESA) {
          //out.println("    <td class=\"cell\" ><button onclick=\"setCurrentEvent(event);ripresaAction(" + index + ")\" style=\"width: 200px\">" + ripresaBut + "</button></td>");//Fix 13574
          //Fix 24211 inizio
          //out.println("    <td class=\"cell\" ><button onclick=\"setCurrentEvent(event);ripresaAction(" + index + ")\" >" + ripresaBut + "</button></td>"); //Fix 13574
          out.println("    <td class=\"cell\" ><button onclick=\"setCurrentEvent(event);ripresaAction(" + index + ")\" style=" + width + ">" + ripresaBut + "</button></td>"); //Fix 13574 //Fix 30236
          //Fix 24211 fine
        }
        out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdCausaleRilevazione" + index + "\" value='" + WebElement.formatStringForHTML(testata.getIdCausaleRilevazione()) + "' /></td>");
        //if (testata.getTipoDichiarazione() != PersDatiPrdCausaleRilev.NON_PRODUTTIVA) {//Fix 16741
        //if (testata.getTipoDichiarazione() != PersDatiPrdCausaleRilev.NON_PRODUTTIVA && testata.getTipoDichiarazione() != PersDatiPrdCausaleRilev.FERMO) {//Fix 16741
          out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"BollaLav" + index + "\" value='" + WebElement.formatStringForHTML(bollaLav) + "' /></td>"); //Fix 14725
          out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdArticolo" + index + "\" value='" + WebElement.formatStringForHTML(testata.getIdArticolo()) + "' /></td>");
          out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdAnnoOrdine" + index + "\" value='" + WebElement.formatStringForHTML(annoOrd) + "' /></td>");
          out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdNumeroOrdine" + index + "\" value='" + WebElement.formatStringForHTML(numOrd) + "' /></td>");
          //Fix 15064 inizio
          out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdRigaAttivita" + index + "\" value='" + WebElement.formatStringForHTML(rigaAtv) + "' /></td>");
          out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdAnnoBolla" + index + "\" value='" + WebElement.formatStringForHTML(annoBolla) + "' /></td>");
          out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdNumeroBolla" + index + "\" value='" + WebElement.formatStringForHTML(numBolla) + "' /></td>");
          out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"TipoBolla" + index + "\" value='" + WebElement.formatStringForHTML(tipoBolla) + "' /></td>");
          //Fix 15064 fine
          out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"TipoRisorsa" + index + "\" value='" + testata.getTipoRisorsa() + "' /></td>");
          out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"LivelloRisorsa" + index + "\" value='" + testata.getLivelloRisorsa() + "' /></td>");
          out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdRisorsa" + index + "\" value='" + WebElement.formatStringForHTML(testata.getIdRisorsa()) + "' /></td>");
          out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdAmbiente" + index + "\" value='" + WebElement.formatStringForHTML(idAmbiente) + "' /></td>"); //Fix 12841
          //Fix 19104 inizio
          out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"Quantita" + index + "\" value='" + WebElement.formatStringForHTML(getValue(qtaDaProdurre[0], 2)) + "' /></td>");
          out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"QuantitaSec" + index + "\" value='" + WebElement.formatStringForHTML(getValue(qtaDaProdurre[1], 2)) + "' /></td>");
          //Fix 26907 Inizio
          out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"Note" + index + "\" value='" + WebElement.formatStringForHTML(note) + "' /></td>");
          //Fix 26907 Fine
          //Fix 19104 fine
        //}
        out.println("   </tr>");
        index++;
      }
    }
    //Fix 13264 --inizio
    if(bo.getIdAmbiente() != null){
      displayProssemeDichiarazione(out, bo, index); //Fix 12841      
    }
    
    out.println("  </table>");
    out.println("</td>");
    out.println("</tr>");
    //Fix 13434 inizio
    out.println("<tr>"); //Fix 13264
    out.println("<td colspan=\"5\" style=\"height:100%\"></td>"); //Fix 13264
    out.println("</tr>");
    //Fix 13082 --inizio
    out.println("<tr>");
    out.println("<td width=\"5px\">");//Fix 13175
    out.println("</td>");//Fix 13175
    out.println("<td colspan=\"5\">");
    //out.println("<table cellpadding=\"5\" cellspacing=\"5\">");//Fix 13574
    out.println("<table cellpadding=\"3\" cellspacing=\"3\">"); //Fix 13574
    out.println("<tr>");
    out.println("<td><label class=\"labelError\" id=\"ErroriList\"></label></td>");
    out.println("</tr>");
    out.println("</table>");
    out.println("</td>");
    out.println("</tr>");
    out.println("</table>");
    //Fix 13434 fine
    //Fix 13264 --fine
  }

  public void displaySospendiRilevazioneinCorso(JspWriter out, RilevDatiPrdTS bo) throws IOException {
    //Fix 13434 blocco commentato
    /*if (bo.getPersDatiPrdCausaleRilev().getTipoDichiarazione() == PersDatiPrdCausaleRilev.FERMO) {
      if (bo.cercaRilevazioneInCorso() != null) {
        out.println("document.getElementById(\"TerminaDichTR\").style.display = displayBlock;");
      }
    }*/
  }

  public void displayRiprendiRilevazioneSospesa(JspWriter out, RilevDatiPrdTS bo) throws IOException {
    //Fix 13434 blocco commentato
    /*if (bo.getPersDatiPrdCausaleRilev().getTipoDichiarazione() == PersDatiPrdCausaleRilev.FERMO) {
      if (bo.cercaRilevazioneInCorso() != null) {
        out.println("document.getElementById(\"RiprendiDichTR\").style.display = displayBlock;");
      }
    }*/
  }

  public void changeRisorsaLabel(JspWriter out, RilevDatiPrdTS bo) throws IOException {
    String risorsaUmanaLabel = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "RisorsaUmanaLabel"));
    String macchinaLabel = WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "MacchinaLabel"));
    if (bo.getTipoRisorsa() == Risorsa.RISORSE_UMANE) {
      out.println("if(document.getElementById('RisorsaLabel') != undefined)"); //Fix 15030
      out.println(" setInnerText(document.getElementById(\"RisorsaLabel\"), \"" + risorsaUmanaLabel + "\");");
    }
    else if (bo.getTipoRisorsa() == Risorsa.MACCHINE) {
      out.println("if(document.getElementById('RisorsaLabel') != undefined)"); //Fix 15030
      out.println(" setInnerText(document.getElementById(\"RisorsaLabel\"), \"" + macchinaLabel + "\");");
    }
  }

  public void valorizzaQuantita(JspWriter out, RilevDatiPrdTS bo) throws IOException {
    //Fix 13175 --inizio
    //Fix 16109 blocco commentato
     /*if(bo.getPersDatiPrdCausaleRilev().getCausaleDocumento().getRilevaQuantita() == CausaleDocProduzione.SI){
       out.println("if(eval('document.forms[0].' + idFromName['Quantita']) != undefined )"); //Fix 15030
       out.println(" eval('document.forms[0].' + idFromName['Quantita']).style.background = mCo;");
     }*/
    //Fix 13175 --fine
	//Fix 30572 inizio
	if(bo.getTipoTimbratura() == RilevazioneDatiProdRig.SOSPENSIONE) {
	   List errori = (List) getRequest().getAttribute("ErroriList");
	   if (errori == null || errori.isEmpty()) {
	   	out.println("document.getElementById('Quantita').value='0,00';");
	   	out.println("document.getElementById('QuantitaSec').value='0,00';");
	   }
	} 
	else { //Fix 30572 fine
     if(bo.getPersDatiPrdCausaleRilev().isQtaResInAvz()) {
      //Fix 12974 --inizio
      List errori = (List) getRequest().getAttribute("ErroriList"); //Fix 13434
      if (errori == null || errori.isEmpty()) { //Fix 13434
      	//Fix 22005 inizio
        //iQtaBuona = RilevDatiPrdTS.getQtaResiduaAtv(bo.getAttivitaEsecutiva()); //Fix 13264
      	//Fix 22198 inizio
      	//AttivitaEsecutiva atvPrec = bo.getAttivitaEsecutiva().getAtvEsecPrecedente();
        AttivitaEsecutiva atvPrec = null;
        if (bo.getAttivitaEsecutiva() != null) {
          //Fix 23916 inizio
          //atvPrec = bo.getAttivitaEsecutiva().getAtvEsecPrecedente();
        	//Fix 24042 inizio
          //atvPrec = getAtvPrecNoAuto(bo.getAttivitaEsecutiva());
        	atvPrec = bo.getAttivitaEsecutiva().getAtvEsecPrecNoAuto(bo.getAttivitaEsecutiva());
        	//Fix 24042 fine
          //Fix 23916 fine
        }
      	//Fix 22198  fine
      	CausaleDocProduzione cauDoc = bo.getPersDatiPrdCausaleRilev().getCausaleDocumento();
      	if (atvPrec != null && cauDoc != null && cauDoc.getTipoQtaResProposta() == CausaleDocProduzione.TIPO_QTA_RES_PROPOSTA_DA_ATV) {
          BigDecimal qtaPrdPrec = RilevDatiPrdTS.getQtaProdottaAtv(atvPrec);
          BigDecimal qtaPrd = RilevDatiPrdTS.getQtaProdottaAtv(bo.getAttivitaEsecutiva());
          BigDecimal qtaSca = RilevDatiPrdTS.getQtaScartataAtv(bo.getAttivitaEsecutiva());
          iQtaBuona = qtaPrdPrec.subtract(qtaPrd.add(qtaSca));
      	}
        else {
          //Fix 36358 -- Inizio
          if(cauDoc.getTipoQtaResProposta() == CausaleDocProduzione.TIPO_QTA_RES_PROPOSTA_STD && bo.getAttivitaEsecutiva() != null && bo.getAttivitaEsecutiva().isAttivitaFinale() && bo.getPersDatiPrdCausaleRilev().getTipoDichiarazione() == PersDatiPrdCausaleRilev.VERSAMENTO) {
            iQtaBuona = RilevDatiPrdTS.getQtaResiduaAtvPrd(bo.getAttivitaEsecutiva().getAtvEsecPrdPrimario());
          }
          else {
          //Fix 36358 -- Fine
            iQtaBuona = RilevDatiPrdTS.getQtaResiduaAtv(bo.getAttivitaEsecutiva());
          }//Fix 36358
        }
        //Fix 22005 fine
        //out.println(" eval('document.forms[0].' + idFromName['Quantita']).value = '" + getValue(bo.getAttivitaEsecutiva().getQtaResiduaUMPrm(), 2) + "';");
      	//Fix 38941 -- Inizio
      	//Fix 40289 -- Inizio
      	boolean isQtaDaLottoMov = isLottoMovimentazioneDaGestire(out, bo);
      	/*boolean isQtaDaLottoMov = false;
      	if(bo.getAttivitaEsecutiva() != null && bo.getAttivitaEsecutiva().getLottoMovimentazione() != null && bo.getAttivitaEsecutiva().getLottoMovimentazione().compareTo(ZERO) > 0) {
      	  if(iQtaBuona.compareTo(bo.getAttivitaEsecutiva().getLottoMovimentazione()) > 0) {
      		iQtaBuona = bo.getAttivitaEsecutiva().getLottoMovimentazione();
      		isQtaDaLottoMov = true;
      	  }
      	}*/
      	//Fix 40289 -- Fine
      	//Fix 38941 -- Fine
        out.println(" if (eval('document.forms[0].' + idFromName['Quantita']) != undefined )"); //Fix 16109
        out.println("   eval('document.forms[0].' + idFromName['Quantita']).value = '" + getValue(iQtaBuona, 2) + "';");
        //Fix 12974 --fine
        //Fix 27463 --inizio
        if (bo.getAttivitaEsecutiva() != null) {
         Articolo articolo = bo.getArticolo();
         //Integer idVersione = bo.getAttivitaEsecutiva().getOrdineEsecutivo().getIdVersione(); //Fix 27957 
         if (articolo.getUMSecMag() != null) { //Fix 27735
           //BigDecimal qtaBuonaSec = articolo.convertiUM(iQtaBuona, articolo.getUMPrmMag(), articolo.getUMSecMag(), getVersione(articolo, idVersione)); //Fix 27957 
        	 BigDecimal qtaBuonaSec = calcolaQtaBuonaSec(bo);//Fix 27957
           //Fix 38941 -- Inizio
           if(isQtaDaLottoMov) {
        	 Integer idVersione = bo.getAttivitaEsecutiva().getOrdineEsecutivo().getIdVersione();
        	 qtaBuonaSec = articolo.convertiUM(iQtaBuona, articolo.getUMPrmMag(), articolo.getUMSecMag(), getVersione(articolo, idVersione));
           }
           //Fix 38941 -- Fine
           out.println(" if (eval('document.forms[0].' + idFromName['QuantitaSec']) != undefined )"); //Fix 16109
           out.println("   eval('document.forms[0].' + idFromName['QuantitaSec']).value = '" + getValue(qtaBuonaSec, 2) + "';");
         }
        }
        //Fix 27463 --fine
      }
      else //Fix 13434
        iQtaBuona = null; //Fix 13434
    }
    else{ //Fix 24514 --inizio
    	List errori = (List) getRequest().getAttribute("ErroriList"); //Fix 25733
        if (errori == null || errori.isEmpty()) { //Fix 25733
      	  //Fix 38941 -- Inizio
          //Fix 40289 -- Inizio
          BigDecimal qtaMovi = recuperaQtaLottoMovimentazione(bo);
          /*BigDecimal qtaMovi = null;
      	  if(bo.getAttivitaEsecutiva() != null && bo.getAttivitaEsecutiva().getLottoMovimentazione() != null && bo.getAttivitaEsecutiva().getLottoMovimentazione().compareTo(ZERO) > 0) {
      		qtaMovi = bo.getAttivitaEsecutiva().getLottoMovimentazione();
      	  }*/
      	  //Fix 40289 -- Fine
      	  if(qtaMovi != null) {
            out.println(" if (eval('document.forms[0].' + idFromName['Quantita']) != undefined )");
            out.println("   eval('document.forms[0].' + idFromName['Quantita']).value = '" + getValue(qtaMovi, 2) + "';");
            Articolo articolo = bo.getArticolo();
            if (articolo.getUMSecMag() != null) {
              Integer idVersione = bo.getAttivitaEsecutiva().getOrdineEsecutivo().getIdVersione();
           	  BigDecimal qtaSec = articolo.convertiUM(qtaMovi, articolo.getUMPrmMag(), articolo.getUMSecMag(), getVersione(articolo, idVersione));
              out.println(" if (eval('document.forms[0].' + idFromName['QuantitaSec']) != undefined )");
              out.println("   eval('document.forms[0].' + idFromName['QuantitaSec']).value = '" + getValue(qtaSec, 2) + "';");	
            }
       	  }
      	  else {
      	  //Fix 38941 -- Fine
            out.println(" if (eval('document.forms[0].' + idFromName['Quantita']) != undefined )");
            out.println("   eval('document.forms[0].' + idFromName['Quantita']).value = '';");
            //Fix 27463 --inizio
            out.println(" if (eval('document.forms[0].' + idFromName['QuantitaSec']) != undefined )");
            out.println("   eval('document.forms[0].' + idFromName['QuantitaSec']).value = '';");
      	  }//Fix 38941
          //Fix 27463 --fine
        } //Fix 25733
     }//Fix 24514 --fine
   } //Fix 30572
  }

  protected String getValue(java.math.BigDecimal value, int scale) {
    if (value != null) {
      DecimalType dt = new DecimalType();
      //dt.setScale(scale); //Fix 34578
      dt.setScale(Q6Calc.get().scale(2)); //Fix 34578
      return dt.objectToString(value);
    }
    return "";
  }

  public void enableOraInizio(JspWriter out, RilevDatiPrdTS bo) throws IOException {
    if (bo.getPersDatiPrdCausaleRilev().isModificaOraTS()) {
      out.println(" eval('document.forms[0].' + idFromName['OraInizio']).readOnly = false;");
      out.println(" eval('document.forms[0].' + idFromName['OraInizio']).style.background = mCo;");
    }
    else {
      out.println(" eval('document.forms[0].' + idFromName['OraInizio']).readOnly = true;");
      out.println(" eval('document.forms[0].' + idFromName['OraInizio']).style.background = bCo;");
    }
  }

  public void enableOraFine(JspWriter out, RilevDatiPrdTS bo) throws IOException {
    if(bo.getPersDatiPrdCausaleRilev().isModificaOraTS()){
      out.println("if(eval('document.forms[0].' + idFromName['OraFine']) != undefined ){"); //Fix 15030
      out.println(" eval('document.forms[0].' + idFromName['OraFine']).readOnly = false;");
      out.println(" eval('document.forms[0].' + idFromName['OraFine']).style.background = mCo;");
      out.println("}"); //Fix 15030
    }
    else{
      out.println("if(eval('document.forms[0].' + idFromName['OraFine']) != undefined ){"); //Fix 15030
      out.println(" eval('document.forms[0].' + idFromName['OraFine']).readOnly = true;");
      out.println(" eval('document.forms[0].' + idFromName['OraFine']).style.background = bCo;");
      out.println("}"); //Fix 15030
    }
  }

  public void disableSospesaRilevInCorso(JspWriter out) throws IOException {
	 out.println(" if (eval('document.forms[0].' + idFromName['SospendiRilevInCorso']) != undefined) {"); //Fix 41628
     out.println("   eval('document.forms[0].' + idFromName['SospendiRilevInCorso']).disabled = true;");
     out.println(" }"); //Fix 41628
  }

  public void disableRiprendiRilevInCorso(JspWriter out) throws IOException {
	 out.println(" if (eval('document.forms[0].' + idFromName['RiprendiRilevSospesa']) != undefined) {"); //Fix 41628
     out.println("   eval('document.forms[0].' + idFromName['RiprendiRilevSospesa']).disabled = true;");
     out.println(" }"); //Fix 41628
  }

  //Fix 12841 --inizio
  public void hideDescrizioneMacchina(JspWriter out, RilevDatiPrdTS bo) throws IOException {
    if(bo.getDescrizioneMacchina() == null){
      out.println("document.getElementById(\"DescMacchinaTR\").style.display = displayNone;");
    }
  }

  public void displayProssemeDichiarazione(JspWriter out, RilevDatiPrdTS bo, int index) throws IOException {
    List prossemeDichiarazioneList = new ArrayList();
    if(bo.getIdMacchina() != null){
      prossemeDichiarazioneList = bo.getListAttivitaNonIniziata(bo.getIdMacchina(), Risorsa.MACCHINE);
    }
    else{
      prossemeDichiarazioneList = bo.getListAttivitaNonIniziata(bo.getIdOperatore(), Risorsa.RISORSE_UMANE);
    }
    //Fix 13082 --inizio
    //String inizioBut = ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "ButInizio");
    List attivitaBlu = new ArrayList(); //Fix 13264
    List attivitaGialle = new ArrayList();
    List attivitaRosse = new ArrayList();
    RilevDatiPrdTS.raggruppaListaAttivita(prossemeDichiarazioneList, attivitaBlu, attivitaGialle, attivitaRosse); //Fix 13434
    index = displayProssimeDichInternal(out, attivitaBlu, "it/thera/thip/produzione/raccoltaDati/images/Blue.gif", bo, index); //Fix 13264 //Fix 23541
    index = displayProssimeDichInternal(out, attivitaGialle, "it/thera/thip/produzione/raccoltaDati/images/Yellow.gif", bo, index); //Fix 13264 //Fix 23541
    index = displayProssimeDichInternal(out, attivitaRosse, "it/thera/thip/produzione/raccoltaDati/images/Red.gif", bo, index); //Fix 13264 //Fix 23541
   //Fix 13082 --fine
    if(!iEsistListaAttivitaBC) { //Fix 44763
      displayProssemeDichiarazioneBollaCucita(out, bo, index);//Fix 30572
    }
  }

  /* Fix 13175
   public String getIdAmbiente(RilevazioneDatiProdTes testata) {
    if(testata.getTipoDichiarazione() != PersDatiPrdCausaleRilev.NON_PRODUTTIVA){
      String where = ListaAttivitaTM.ID_AZIENDA + "='" + testata.getIdAzienda() + "' AND " +
                     ListaAttivitaTM.ID_ANNO_ORD + "='" + testata.getIdAnnoOrdine() + "' AND " +
                     ListaAttivitaTM.ID_NUMERO_ORD + "='" + testata.getIdNumeroOrdine() + "' AND " +
                     ListaAttivitaTM.ID_RIGA_ATTIVITA + "=" + testata.getIdRigaAttivita() + "";
      try {
        Vector listaAtv = ListaAttivita.retrieveList(where, "", true);
        if (!listaAtv.isEmpty()) {
          return ((ListaAttivita) listaAtv.get(0)).getIdAmbiente();
        }
      }
      catch (Exception ex) {
        ex.printStackTrace(Trace.excStream);
      }
    }
   return "";
 }*/

 /* Fix 13175
  public String getIdAmbiente(List dichiarazioneInCorsoList){
   String idAmbiente = "";
   if(!dichiarazioneInCorsoList.isEmpty()){
      int i = 0;
      while(idAmbiente.equals("") && i < dichiarazioneInCorsoList.size()){
        idAmbiente = getIdAmbiente((RilevazioneDatiProdTes) dichiarazioneInCorsoList.get(i++));
      }
   }
   if(idAmbiente.equals("")){
     if(getAmbienteForCurrentUser() != null){
       idAmbiente = getAmbienteForCurrentUser().getIdAmbiente();
     }
   }
   return idAmbiente;
 }*/

  //Fix 12841 --fine
  //Fix 12974 --inizio
  public void checkAttivazioneRilevDatiPrd(JspWriter out, RilevDatiPrdTS bo) throws IOException {
    ErrorMessage error = bo.checkAttivazioneRilevDatiPrd();
    if(error != null){
      out.println("alert('"+WebElement.formatStringForHTML(error.getLongText())+"');");
      out.println("top.window.close();");
    }
  }
  //Fix 12974 --fine
  //Fix 13082 --inizio
  public void hideMacchinaField(JspWriter out, RilevDatiPrdTS bo) throws IOException{
    //if(bo.getBollaLavorazione() != null && bo.getIdMacchina() == null){//Fix 16741
      if( (bo.getBollaLavorazione() != null && bo.getIdMacchina() == null) || (bo.getBollaLavorazione() == null && bo.getIdMacchina() == null) ){//Fix 16741
      out.println("document.getElementById('MacchinaTR').style.display=displayNone;");
    }
  }

  //public void displayProssimeDichInternal(JspWriter out, List prossimeDich, String srcImg, RilevDatiPrdTS bo, int index) throws IOException{ //Fix 23541 --Riga commentata
  public int displayProssimeDichInternal(JspWriter out, List prossimeDich, String srcImg, RilevDatiPrdTS bo, int index) throws IOException{ //Fix 23541
    //Fix 13264 inizio
    String but = ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "ButInizio"); //Fix 13434
    //Fix 13264 fine
    Iterator iter = prossimeDich.iterator();
    while (iter.hasNext()) {
      ListaAttivita listaAttivita = (ListaAttivita) iter.next();
      if(listaAttivita.getIdAnnoBolla() == null && listaAttivita.getIdNumeroBolla() == null) { //Fix 44763
	      out.println("    <tr>");
	      //out.println("    <td class=\"cell\"><img type=\"image\" name=\"\" src=\"" + srcImg + "\" alt=\"\" /></td>"); //Fix 14725
	      out.println("    <td class=\"cell\"><img type=\"image\" name=\"\" src=\"" + srcImg + "\" alt=\"\" /><br>&nbsp</td>"); //Fix 14725
	      String bollaLav = listaAttivita.getNumRitorno();
	      String descrRilev = listaAttivita.getDescrAttivita();
	      String numOrdine = listaAttivita.getNumeroOrdineFmt();
	      //Fix 14725 --inizio
	      String idArticolo = listaAttivita.getIdArticolo();
	      String descrArticolo = listaAttivita.getAttivitaEsecutiva().getOrdineEsecutivo().getArticolo().getDescrizioneArticoloNLS().getDescrizione();
	      //Fix 23542 Inizio
	      String idMacchina = listaAttivita.getIdMacchina();
	      String descMAcchina = null;
	      if(listaAttivita.getMacchine() != null)
	        descMAcchina = listaAttivita.getMacchine().getDescrizione().getDescrizione();
	      //Fix 23542 Fine
	      //Fix 33517 -- Inizio
	      String articolo_Config = "";
	      if(listaAttivita.getConfigurazione() != null) {
	    	  //articolo_Config = idArticolo + "/" + listaAttivita.getConfigurazione().getIdEsternoConfig();//Fix 33688
	    	  articolo_Config = listaAttivita.getConfigurazione().getIdEsternoConfig(); //Fix 33688 
	      }
	      //Fix 33517 -- Fine
	      //Fix 37456 -- Inizio
	      String commessa = "";
	      if(listaAttivita.getAttivitaEsecutiva().getOrdineEsecutivo().getIdCommessa() != null) {
	    	 commessa = listaAttivita.getAttivitaEsecutiva().getOrdineEsecutivo().getIdCommessa();
	      }
	      //Fix 37456 -- Fine
	      BigDecimal[] qtaDaProdurre = RilevDatiPrdTS.calcolaQuantitaDaPorduire(listaAttivita.getAttivitaEsecutiva());
	      String idUmPrimaria = listaAttivita.getAttivitaEsecutiva().getOrdineEsecutivo().getArticolo().getIdUMPrmMag();
	      String idUmSecondaria = listaAttivita.getAttivitaEsecutiva().getOrdineEsecutivo().getArticolo().getIdUMSecMag();
	      //out.println("    <td id=\"BollaLavTD" + index + "\" class=\"cell\" >" + WebElement.formatStringForHTML(bollaLav) + "</td>");
	      //out.println("    <td class=\"cell\" >" + WebElement.formatStringForHTML(descrRilev) + "</td>");
				out.println(getHTMLCellaProssimeDichPers01(index, listaAttivita));		//Fix 22513
	      out.println("    <td id=\"BollaLavTD" + index + "\" class=\"cell\" >" + WebElement.formatStringForHTML(bollaLav) + "<br>" + WebElement.formatStringForHTML(descrRilev) + "</td>");
	      //Fix 14725 --fine
				out.println(getHTMLCellaProssimeDichPers02(index, listaAttivita));		//Fix 22513
	      if(commessa.equals("")) { //Fix 37456
			  out.println("    <td class=\"cell\" >" + WebElement.formatStringForHTML(numOrdine) + "</td>");
		  }
		  else{ //Fix 37456 --inizio
		      out.println("    <td class=\"cell\" >" + WebElement.formatStringForHTML(numOrdine) + "<br>" + WebElement.formatStringForHTML(commessa) + "</td>");
		  }
		  //Fix 37456 --fine
	      //Fix 14725 -- inizio
				out.println(getHTMLCellaProssimeDichPers03(index, listaAttivita));		//Fix 22513
		  //Fix 33517 -- Inizio
		  if(articolo_Config.equals("")) {
			  out.println("    <td class=\"cell\" nowrap=\"true\" >" + WebElement.formatStringForHTML(idArticolo) + "<br>" + WebElement.formatStringForHTML(descrArticolo) + "</td>");
		  }else {
			  //out.println("    <td class=\"cell\" nowrap=\"true\" >" + WebElement.formatStringForHTML(articolo_Config) + "<br>" + WebElement.formatStringForHTML(descrArticolo) + "</td>"); //Fix 33688
			  out.println("    <td class=\"cell\" nowrap=\"true\" >" + WebElement.formatStringForHTML(idArticolo) + "<br>" + WebElement.formatStringForHTML(articolo_Config) + "<br>" + WebElement.formatStringForHTML(descrArticolo) + "</td>");
		  }
	      //Fix 33517 -- Fine
				out.println(getHTMLCellaProssimeDichPers04(index, listaAttivita));		//Fix 22513
		  //Fix 23542 Inizio
	      if(bo.getIdMacchina() == null)
	        out.println("    <td class=\"cell\" nowrap=\"true\" >" + WebElement.formatStringForHTML(idMacchina) + "<br>" + WebElement.formatStringForHTML(descMAcchina) + "</td>");
	      //Fix 23542 Fine
	      if(idUmSecondaria != null){
	        out.println("    <td class=\"cell\" nowrap=\"true\" >" + getValue(qtaDaProdurre[0], 2) + " " + WebElement.formatStringForHTML(idUmPrimaria) + "<br>" + getValue(qtaDaProdurre[1], 2) + " " + WebElement.formatStringForHTML(idUmSecondaria) + "</td>");
	      }
	      else{
	        out.println("    <td class=\"cell\" nowrap=\"true\" >" + getValue(qtaDaProdurre[0], 2) + " " + WebElement.formatStringForHTML(idUmPrimaria) + "<br>&nbsp</td>");
	      }
	      //Fix 14725 -- fine
	      //Fix 13264 inizio
				out.println(getHTMLCellaProssimeDichPers05(index, listaAttivita));		//Fix 22513
	      //Fix 24211 inizio
	      //out.println("    <td class=\"cell\" ><button onclick=\"setCurrentEvent(event);inizioAction(" + index + ")\" style=\"width: 200px\">" + but + "</button></td>"); //Fix 13434
	      out.println("    <td class=\"cell\" ><button onclick=\"setCurrentEvent(event);inizioAction(" + index + ")\" style=\"width: 117px\">" + but + "</button></td>");
	      //Fix 24211 fine
	      //Fix 13264 fine
	      out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"BollaLav" + index + "\" value='" + WebElement.formatStringForHTML(bollaLav) + "' /></td>"); //Fix 14725
	      out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdArticolo" + index + "\" value='" + WebElement.formatStringForHTML(listaAttivita.getIdArticolo()) + "' /></td>");
	      out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdAnnoOrdine" + index + "\" value='" + WebElement.formatStringForHTML(listaAttivita.getIdAnnoOrdine()) + "' /></td>");
	      out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdNumeroOrdine" + index + "\" value='" + WebElement.formatStringForHTML(listaAttivita.getIdNumeroOrdine()) + "' /></td>");
	      out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdRigaAttivita" + index + "\" value='" + WebElement.formatStringForHTML(listaAttivita.getIdRigaAttivita().toString()) + "' /></td>");
	      out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"TipoRisorsa" + index + "\" value='" + (bo.getIdMacchina() != null ? listaAttivita.getTipoMacchina() : listaAttivita.getTipoOperatore()) + "' /></td>");
	      out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"LivelloRisorsa" + index + "\" value='" + (bo.getIdMacchina() != null ? listaAttivita.getLivelloMacchina() : listaAttivita.getLivelloOperatore()) + "' /></td>");
	      out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdRisorsa" + index + "\" value='" + WebElement.formatStringForHTML((bo.getIdMacchina() != null ? listaAttivita.getIdMacchina() : listaAttivita.getIdOperatore())) + "' /></td>");
	      out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdAmbiente" + index + "\" value='" + WebElement.formatStringForHTML(listaAttivita.getIdAmbiente()) + "' /></td>");
	      out.println("   </tr>");
      }
      else { //Fix 44763 --inizio
    	  BollaCucitaTes bollaCucita = listaAttivita.getBollaCucita();
    	  if(bollaCucita != null) {
    		  iEsistListaAttivitaBC = true;
    		  String butDettaglio = ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "ButDettaglio");
    		  String width = "\"width:117px\"";
    		  if(bo.getPersDatiPrdUtenteRilev().getRisoluzioneVideo()== PersDatiPrdUtenteRilev.RISOL_800_600) {
    		    	width = "\"width:100px\"" ;
    		  }
	          out.println("    <tr>");
	          out.println("    <td class=\"cell\"><img type=\"image\" name=\"\" src=\"" + srcImg + "\" alt=\"\" /><br>&nbsp</td>"); 
	          String bollaLav = bollaCucita.getNumeroRitorno();
	          String descrRilev = "";
	          String numOrdine = bollaCucita.getNumeroBolFmt();
	          String idArticolo = "";
	          String descrArticolo = "";
	          String idMacchina = "";
	          String descMAcchina = null;
	
	          out.println("");
	          out.println("    <td id=\"BollaLavTD" + index + "\" class=\"cell\" >" + WebElement.formatStringForHTML(bollaLav) + "<br>" + WebElement.formatStringForHTML(descrRilev) + "</td>");
	          out.println("");
	          out.println("    <td class=\"cell\" >" + WebElement.formatStringForHTML(numOrdine) + "</td>");
	    	  out.println("");
	    	  out.println("    <td class=\"cell\" ><button onclick=\"setCurrentEvent(event);dettaglioAction(" + index + ")\" style="+ width +">" + butDettaglio + "</button></td>");
	    	  out.println("");
	    	  if(bo.getIdMacchina() == null)
	    		  out.println("    <td class=\"cell\" nowrap=\"true\" >" + WebElement.formatStringForHTML("") + "<br>" + WebElement.formatStringForHTML("") + "</td>");
	    	  out.println("    <td class=\"cell\" nowrap=\"true\" >" + "" + " " + WebElement.formatStringForHTML("") + "<br>&nbsp</td>");
	    	  out.println("");
	    	  out.println("    <td class=\"cell\" ><button onclick=\"setCurrentEvent(event);inizioAction(" + index + ")\" style=\"width: 117px\">" + but + "</button></td>");
	    	  
	    	  out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdCausaleRilevazione" + index + "\" value='" + WebElement.formatStringForHTML(bo.getIdCausaleRilevazione()) + "' /></td>");
	    	  out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"BollaLav" + index + "\" value='" + WebElement.formatStringForHTML(bollaLav) + "' /></td>");
	    	  out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdArticolo" + index + "\" value='" + WebElement.formatStringForHTML("") + "' /></td>");
	    	  out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdAnnoOrdine" + index + "\" value='" + WebElement.formatStringForHTML("") + "' /></td>");
	    	  out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdNumeroOrdine" + index + "\" value='" + WebElement.formatStringForHTML("") + "' /></td>");
	    	  out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdRigaAttivita" + index + "\" value='" + WebElement.formatStringForHTML("") + "' /></td>");
	    	  out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdAnnoBolla" + index + "\" value='" + WebElement.formatStringForHTML(bollaCucita.getIdAnnoBolla()) + "' /></td>");
	          out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdNumeroBolla" + index + "\" value='" + WebElement.formatStringForHTML(bollaCucita.getIdNumeroBolla()) + "' /></td>");
	          out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"TipoBolla" + index + "\" value='" + WebElement.formatStringForHTML(""+RilevazioneDatiProdTes.BOLLA_CUCITA) + "' /></td>");
	          if(bollaCucita.getLivelloRisorsa() == Risorsa.MATRICOLA) {
	    	   out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"TipoRisorsa" + index + "\" value='" + (bollaCucita.getTipoRisorsa()) + "' /></td>");
	    	   out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"LivelloRisorsa" + index + "\" value='" + (bollaCucita.getLivelloRisorsa()) + "' /></td>");
	    	   out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdRisorsa" + index + "\" value='" + WebElement.formatStringForHTML(bollaCucita.getIdRisorsa()) + "' /></td>");
	          }
	          else {
	       	   out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"TipoRisorsa" + index + "\" value='" + Risorsa.MACCHINE + "' /></td>");
	       	   out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"LivelloRisorsa" + index + "\" value='" + Risorsa.MATRICOLA + "' /></td>");
	       	   out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdRisorsa" + index + "\" value='' /></td>");  
	          }
	    	  out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdAmbiente" + index + "\" value='" + WebElement.formatStringForHTML(bo.getIdAmbiente()) + "' /></td>");
	    	  out.println("   </tr>");
         }
      } //Fix 44763 --fine
      index++;
    }
    return index; //Fix 23541
  }
  //Fix 13082 --fine
  //Fix 14721 --inizio
  public void hideTempoField(JspWriter out, RilevDatiPrdTS bo) throws IOException{
    if(bo.getPersDatiPrdCausaleRilev() != null){
      char tipoDichiarazione = bo.getPersDatiPrdCausaleRilev().getTipoDichiarazione();
      if (tipoDichiarazione == PersDatiPrdCausaleRilev.ATTREZZAGGIO || tipoDichiarazione == PersDatiPrdCausaleRilev.AVVIAMENTO ||
          tipoDichiarazione == PersDatiPrdCausaleRilev.PRODUTTIVA || tipoDichiarazione == PersDatiPrdCausaleRilev.FERMO ||
          tipoDichiarazione == PersDatiPrdCausaleRilev.NON_PRODUTTIVA || //Fix 32631
          tipoDichiarazione == PersDatiPrdCausaleRilev.AVANZAMENTO ) {//Fix 32631
    	  //Fix 31322 -- inizio
    	  //if(bo.getPersDatiPrdCausaleRilev().getCausaleDocumento()!= null && bo.getPersDatiPrdCausaleRilev().getCausaleDocumento().getRilevaTempo() != CausaleDocProduzione.NON_RILEV) {//Fix 30080  //Fix 30440
    	  if(bo.getPersDatiPrdCausaleRilev().getCausaleDocumento() != null && bo.getPersDatiPrdCausaleRilev().getCausaleDocumento().getRilevaTempo() == CausaleDocProduzione.NON_RILEV) {  
    		  out.println("document.getElementById('TempoTR').style.display=displayNone;");
    	  }else {	  
    	  //Fix 31322 -- fine
    	        out.println("document.getElementById('TempoTR').style.display=displayBlock;");
    	        //Fix 19758 inizio
    	        //out.println("document.getElementById('Tempo').style.background = mCo;");
    	        //out.println("document.getElementById('Tempo').typeNameJS.mandatory = true;");
    	        out.println("document.getElementById('OreRilevate').style.background = mCo;");
    	        out.println("document.getElementById('OreRilevate').typeNameJS.mandatory = true;");
    	        out.println("document.getElementById('MinutiRilevati').style.background = mCo;");
    	        out.println("document.getElementById('MinutiRilevati').typeNameJS.mandatory = true;");
    	        //Fix 19758 fine
    	  //Fix 30080 -- inizio
    	        Short arr = bo.getPersDatiPrdCausaleRilev().getIntervMinArrotond();
    	        if(arr != null && arr.shortValue() == 0) {
    	        	out.println("document.getElementById('labelTempoTR').innerText = '" + WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "Tempo_hh_mm_ss")) + "';");
    	        	out.println("document.getElementById('SecondiRilevati').style.display=displayBlock;");
    	            out.println("document.getElementById('SecondiRilevati').style.background = mCo;");
    	            out.println("document.getElementById('SecondiRilevati').typeNameJS.mandatory = true;");
    	            out.println(" isSecondiRilevati = true;");//Fix 32543
    	        }
    	        else {
    	        	out.println("if (document.getElementById('SecondiRilevati') != null)");//Fix 31322
    	        	out.println("document.getElementById('SecondiRilevati').style.display=displayNone;");
    	        	out.println(" isSecondiRilevati = false;");//Fix 32543
    	        } 
    	  //Fix 31322 --inizio  
    	  /*}else {
    		  out.println("document.getElementById('TempoTR').style.display=displayNone;");*/
    	  //Fix 31322 -- fine        
    	  }
    	  //Fix 30080 -- fine
      }
      //else if (tipoDichiarazione == PersDatiPrdCausaleRilev.AVANZAMENTO || tipoDichiarazione == PersDatiPrdCausaleRilev.VERSAMENTO || //Fix 32631
      else if(tipoDichiarazione == PersDatiPrdCausaleRilev.VERSAMENTO || //Fix 32631
               tipoDichiarazione == PersDatiPrdCausaleRilev.PRELIEVO) {
        out.println("document.getElementById('TempoTR').style.display=displayNone;");
      }
    }
  }

  public void hideQtaField(JspWriter out, RilevDatiPrdTS bo) throws IOException{
    if (bo.getPersDatiPrdCausaleRilev() != null) {
     CausaleDocProduzione causaleDocPrd = bo.getPersDatiPrdCausaleRilev().getCausaleDocumento(); //Fix 15064
     //Fix 42882 --inizio
     if(causaleDocPrd == null)
    	 return;
     //Fix 42882 --fine
     if(bo.getTipoBolla() == RilevazioneDatiProdTes.BOLLA_SINGOLA){ //Fix 14722
       char tipoDichiarazione = bo.getPersDatiPrdCausaleRilev().getTipoDichiarazione();
       //Fix 20491 inizio
       //if (tipoDichiarazione == PersDatiPrdCausaleRilev.PRELIEVO ||
       if (bo.verificaCondizionePrelievi() || 
       //Fix 20491 fine		
           (causaleDocPrd.getAbilitaVersamenti() != CausaleDocProduzione.NO && //Fix 17241
           (checkAttivitaProdotti(bo.getAttivitaEsecutiva().getProdotti()) || //Fix 36199
           bo.getPersDatiPrdCausaleRilev().getTipoDichiarazione() == PersDatiPrdCausaleRilev.VERSAMENTO))) { //Fix 36199
         //if (tipoDichiarazione == PersDatiPrdCausaleRilev.PRELIEVO /*|| bo.hasProdottoPrimario()*/) { //Fix 17241 //Fix 17550 // Fix 26494
		 if (causaleDocPrd.getRilevaQuantita() == CausaleDocProduzione.NO) { //Fix 26494
           out.println("if(document.getElementById('QuantitaTR') != undefined )"); //Fix 15030
           out.println("  document.getElementById('QuantitaTR').style.display=displayNone;");
           out.println("if(document.getElementById('QuantitaSecTR') != undefined )");//Fix 30900
           out.println("  document.getElementById('QuantitaSecTR').style.display=displayNone;");//Fix 30900
         }
         //Fix 17550 inizio
         else {
           out.println("if(eval('document.forms[0].' + idFromName['Quantita']) != undefined ) {"); //Fix 15030
           out.println(" eval('document.forms[0].' + idFromName['Quantita']).style.background = mCo;");
           out.println(" eval('document.forms[0].' + idFromName['Quantita']).typeNameJS.mandatory = true;");
           out.println("}");
           if (bo.getIdUMSec() == null || bo.getIdUMSec().equals("")) {
             out.println("if(document.getElementById('QuantitaSecTR') != undefined )"); //Fix 15030
             out.println("  document.getElementById('QuantitaSecTR').style.display=displayNone;");
           }
         }
         //Fix 17550 fine
         out.println("dispalyBtnSucc ="+ true +";"); //Fix 15030
		 //Fix 24177 inizio
         if(bo.verificaCondizionePrelievi() && !bo.getPersDatiPrdCausaleRilev().isAbilitaDichRidotta() && !bo.isPosizionePrelieviValido()
        		 && (causaleDocPrd.getAbilitaVersamenti() == CausaleDocProduzione.NO || !checkAttivitaProdotti(bo.getAttivitaEsecutiva().getProdotti())))
        	 out.println("dispalyBtnSucc ="+ false +";");
         //Fix 24177 fine
       }
       else {
         //Fix 15064 inizio
         if (causaleDocPrd.getRilevaQuantita() == CausaleDocProduzione.NO) {
           out.println("if(document.getElementById('QuantitaTR') != undefined )"); //Fix 15030
           out.println("  document.getElementById('QuantitaTR').style.display=displayNone;");
           out.println("if(document.getElementById('QuantitaSecTR') != undefined )");//Fix 30900
           out.println("  document.getElementById('QuantitaSecTR').style.display=displayNone;");//Fix 30900
         }
         //Fix 15064 fine
         else {
           out.println("if(document.getElementById('QuantitaTR') != undefined )"); //Fix 15030
           out.println("  document.getElementById('QuantitaTR').style.display=displayBlock;");
           //Fix 16109 inizio
           out.println("if(eval('document.forms[0].' + idFromName['Quantita']) != undefined ) {"); //Fix 15030
           out.println(" eval('document.forms[0].' + idFromName['Quantita']).style.background = mCo;");
           out.println(" eval('document.forms[0].' + idFromName['Quantita']).typeNameJS.mandatory = true;");
           out.println("}");
           if (bo.getIdUMSec() == null || bo.getIdUMSec().equals("")) {
             out.println("if(document.getElementById('QuantitaSecTR') != undefined )"); //Fix 15030
             out.println("  document.getElementById('QuantitaSecTR').style.display=displayNone;");
           }
           //Fix 16109 fine
         }
         out.println("dispalyBtnSucc ="+ false +";"); //Fix 15030
       }
     } //Fix 14722 inizio
     else if (bo.getTipoBolla() == RilevazioneDatiProdTes.BOLLA_CUCITA){
         out.println("if(document.getElementById('QuantitaTR') != undefined )"); //Fix 15030
         out.println("  document.getElementById('QuantitaTR').style.display=displayNone;");
         out.println("if(document.getElementById('QuantitaSecTR') != undefined )");//Fix 30900
         out.println("  document.getElementById('QuantitaSecTR').style.display=displayNone;");//Fix 30900
         //Fix 15064 inizio
         if (causaleDocPrd.getAvanzamento() || causaleDocPrd.getAbilitaVersamenti() != CausaleDocProduzione.NO ||
         (bo.verificaCondizionePrelievi() && (!bo.getPersDatiPrdCausaleRilev().isAbilitaDichRidotta() && //Fix 26120
          bo.isPosizionePrelieviValido() || bo.getPersDatiPrdCausaleRilev().isAbilitaDichRidotta()))) //Fix 26120
           out.println("dispalyBtnSucc ="+ true +";");
         else
           out.println("dispalyBtnSucc ="+ false +";");
          //Fix 15064 fine
       }
     //Fix 14722 fine
    }
  }

  public boolean checkAttivitaProdotti(List prodotti){
	//Fix 32846 -- Inizio
	List prodottiValido = new ArrayList();
    Iterator iterTmp = prodotti.iterator();
    while (iterTmp.hasNext()) {
      AttivitaEsecProdotto atvEsecPrd = (AttivitaEsecProdotto) iterTmp.next();
      if (atvEsecPrd.getStato() == DatiComuniEstesi.VALIDO)
       	prodottiValido.add(atvEsecPrd);
    }
    //Fix 32846 -- Fine
    //if(!prodotti.isEmpty()){ //Fix 32846 
    if(!prodottiValido.isEmpty()){ //Fix 32846
      //Iterator iterAtvPrd = prodotti.iterator(); //Fix 32846
      Iterator iterAtvPrd = prodottiValido.iterator();
      while(iterAtvPrd.hasNext()){
        AttivitaEsecProdotto atvEsecPrd = (AttivitaEsecProdotto)iterAtvPrd.next();
        if(atvEsecPrd.getTipoProdotto() == AttivitaEsecProdotto.PRODOTTO_PRIMARIO){
          //if(!atvEsecPrd.getLottiProdotti().isEmpty()) //Fix 14722
          if(atvEsecPrd.getArticolo().isArticLotto() /*&& !atvEsecPrd.getLottiProdotti().isEmpty()*/) //Fix 14722 //Fix 28595
            return true;
        }
        else if(atvEsecPrd.getTipoProdotto() == AttivitaEsecProdotto.SOTTOPRODOTTO ||
                atvEsecPrd.getTipoProdotto() == AttivitaEsecProdotto.SFRIDO_ROTTAME ||
                (atvEsecPrd.getTipoProdotto() == AttivitaEsecProdotto.ARTICOLO_WIP && atvEsecPrd.getArticolo().isArticLotto()) ||
                (atvEsecPrd.getTipoProdotto() == AttivitaEsecProdotto.SCARTO &&
                ((!atvEsecPrd.getIdArticolo().equals(atvEsecPrd.getAttivitaEsecutiva().getOrdineEsecutivo().getIdArticolo()) &&
                 atvEsecPrd.getArticolo().getTipoParte() != ArticoloDatiIdent.ARTICOLO_WIP) ||
                 atvEsecPrd.getArticolo().isArticLotto()))) //Fix 17241 //Fix 17550 //Fix 18532
          return true;
      }
    }
    return false;
  }

  public RilevDatiPrdTS initRigheProdotti(RilevDatiPrdTS bo, JspWriter out) {
    //Fix 15217 inizio
    List errori = (List) getRequest().getAttribute("ErroriList");
    //Fix 32789 -- Inizio
    String action = (String)getRequest().getAttribute("Action");
    if(action != null && action.equals(RilevDatiPrdTSFormActionAdapter.SUCCESSIVO))
      bo.setCurrentNumPag(1);
    //Fix 32789 -- Fine
    int currentNumPage = bo.getCurrentNumPag();
    if (errori != null && !errori.isEmpty()) {
      int i = 1;
      try {
        //Fix 18536 Inizio blocco commentato
        /*for (; i < 21; i++) {
          Class c = Factory.getClass(bo.getClass());
          String getterPrd = "getIdProdotto" + String.valueOf(i);
          String getterCfg = "getIdProdottoConfig" + String.valueOf(i);
          String getterVers = "getIdProdottoVersione" + String.valueOf(i);
          String getterLot = "getIdLottoProdotto" + String.valueOf(i);
          String getterQtaVrsPrm = "getQtaVrsUmPrm" + String.valueOf(i);
          String getterQtaVrsSec = "getQtaVrsUmSec" + String.valueOf(i);
          String getterQtaScartoPrm = "getQtaScartoUmPrm" + String.valueOf(i);
          String getterQtaScartoSec = "getQtaScartoUmSec" + String.valueOf(i);
          String getterIdAnnoOrd = "getIdAnnoOrdine" + String.valueOf(i);
          String getterIdNumeroOrd = "getIdNumeroOrdine" + String.valueOf(i);
          Method prdMethod = c.getMethod(getterPrd, null);
          Method cfgMethod = c.getMethod(getterCfg, null);
          Method versMethod = c.getMethod(getterVers, null);
          Method lotMethod = c.getMethod(getterLot, null);
          Method qtaVrsPrmMethod = c.getMethod(getterQtaVrsPrm, null);
          Method qtaVrsSecMethod = c.getMethod(getterQtaVrsSec, null);
          Method qtaScartoPrmMethod = c.getMethod(getterQtaScartoPrm, null);
          Method qtaScartoSecMethod = c.getMethod(getterQtaScartoSec, null);
          Method idAnnoOrdMethod = c.getMethod(getterIdAnnoOrd, null);
          Method idNumeroOrdMethod = c.getMethod(getterIdNumeroOrd, null);
          String idPrd = (String) prdMethod.invoke(bo, null);
          String idCfg = (String) cfgMethod.invoke(bo, null);
          Integer idVers = (Integer) versMethod.invoke(bo, null);
          String idLot = (String) lotMethod.invoke(bo, null);
          BigDecimal idQtaVrsPrm = (BigDecimal) qtaVrsPrmMethod.invoke(bo, null);
          BigDecimal idQtaVrsSec = (BigDecimal) qtaVrsSecMethod.invoke(bo, null);
          BigDecimal idQtaScartoPrm = (BigDecimal) qtaScartoPrmMethod.invoke(bo, null);
          BigDecimal idQtaScartoSec = (BigDecimal) qtaScartoSecMethod.invoke(bo, null);
          String idAnnoOrd = (String) idAnnoOrdMethod.invoke(bo, null);
          String idNumeroOrd = (String) idNumeroOrdMethod.invoke(bo, null);
          if (idPrd == null && idCfg == null && idVers == null && idLot == null &&
              idQtaVrsPrm == null && idQtaVrsSec == null && idQtaScartoPrm == null && idQtaScartoSec == null &&
              idAnnoOrd == null && idNumeroOrd == null)
            break;
        }*/
        for (int j=0; j < bo.getProdotti().size(); j++) {
          Articolo prodotto = (Articolo)bo.getProdotti().get(new Integer(j));
          if(prodotto != null && prodotto.getIdArticolo() != null){
            i++;
          }
        }
        //Fix 18536 Fine
        out.println("<script language='JavaScript1.2'>");
        //out.println("var inizialeTotRighe = "+ i +";"); //Fix 14722
        //Fix 18536 Inizio
        //out.println("inizialeTotRighe = "+ i +";"); //Fix 14722
        out.println("inizialeTotRighe = "+ (i-1) +";");
        out.println("lastCurrentLine = "+ (i-1) +";");
				out.println("lastCurrentLine = (lastCurrentLine < 1) ? 1 : lastCurrentLine;");		//Fix 25976
        out.println("totRighe = "+ (i-1) +";");
        //Fix 18536 Fine
        riempiProdottiConUMSec(bo, out); //Fix 39680
        out.println("</script>");
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    //Fix 15217 fine
    else if (bo != null && bo.getAttivitaEsecutiva() != null && abilitaInitRigheProdotti(bo)) { //Fix 18536
      int i = 1;
      //Fix 32846 -- Inizio
      //List prodotti = bo.getAttivitaEsecutiva().getProdotti();
      List prodotti = new ArrayList(); 
      List tmp = bo.getAttivitaEsecutiva().getProdotti();
      Iterator iterTmp = tmp.iterator();
      while (iterTmp.hasNext()) {
        AttivitaEsecProdotto atvEsecPrd = (AttivitaEsecProdotto) iterTmp.next();
        if (atvEsecPrd.getStato() == DatiComuniEstesi.VALIDO)
          prodotti.add(atvEsecPrd);
      }
      //Fix 32846 -- Fine
      if (!prodotti.isEmpty()) {
        Iterator iterAtvPrd = prodotti.iterator();
        try {
          while (iterAtvPrd.hasNext() && i < 21) {
            AttivitaEsecProdotto atvEsecPrd = (AttivitaEsecProdotto) iterAtvPrd.next();
            Object[] result = initRigaDaOrdineEsec(bo, atvEsecPrd, i); //Fix 16734 inizio
            bo = (RilevDatiPrdTS) result[0];
            i = ((Integer) result[1]).intValue();//Fix 16734 fine
          }
          out.println("<script language='JavaScript1.2'>");
          //out.println("var inizialeTotRighe = "+ i +";"); //Fix 14722
          //Fix 18536 Inizio
          //out.println("inizialeTotRighe = "+ i +";"); //Fix 14722
          out.println("inizialeTotRighe = "+ (i-1) +";");
          if(bo.getCurrentNumPag() > 1){
            iTotaliRigheInitial = ((bo.getCurrentNumPag()-1) * 20)+(i-1);
            out.println("totRighe = "+ iTotaliRigheInitial +";");
          }
          //Fix 18536 Fine
          riempiProdottiConUMSec(bo, out); //Fix 39680
          out.println("</script>");
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }
    //Fix 14722 inizio
    else if (bo != null && bo.getBollaCucita() != null && abilitaInitRigheProdotti(bo)) { //Fix 18536
      int i = 1;
      List listaAttivita = bo.getBollaCucita().getRighe();
      if (!listaAttivita.isEmpty()) {
        Iterator iterAtv = listaAttivita.iterator();
        while (iterAtv.hasNext()) {
          try { //Fix 15217
            BollaCucitaRig riga = (BollaCucitaRig) iterAtv.next();
            //Fix 15030 inizio
            //Fix 16734 inizio
            AttivitaEsecutiva atvFinale = riga.getAttivita();
            boolean isAtvFinale = atvFinale.isAttivitaFinale();
            //if (isAtvFinale)// Fix 17726
            if (!isAtvFinale) // Fix 17726
               atvFinale = atvFinale.getOrdineEsecutivo().getAttivitaEsecutivaConPrdPrm();
            AttivitaEsecProdotto atvEsecPrdPrm = getAtvEsecPrdPrimario(atvFinale);
            Object[] result = initRigaDaOrdineEsec(bo, riga, atvEsecPrdPrm, i);
            //Fix 16734 finale
            bo = (RilevDatiPrdTS)result[0];
            i = ((Integer)result[1]).intValue();
            //Fix 15030 fine
            //Fix 32846 -- Inizio
            //List prodotti = riga.getAttivita().getProdotti();
            List prodotti = new ArrayList();
            List tmp = riga.getAttivita().getProdotti();
            Iterator iterTmp = tmp.iterator();
            while (iterTmp.hasNext()) {
              AttivitaEsecProdotto atvEsecPrd = (AttivitaEsecProdotto) iterTmp.next();
              if (atvEsecPrd.getStato() == DatiComuniEstesi.VALIDO)
                prodotti.add(atvEsecPrd);
            }
            //Fix 32846 -- Fine
            if (!prodotti.isEmpty()) {
              Iterator iterAtvPrd = prodotti.iterator();
              while (iterAtvPrd.hasNext() && i < 21) {
                AttivitaEsecProdotto atvEsecPrd = (AttivitaEsecProdotto) iterAtvPrd.next();
                char tipoProdotto = atvEsecPrd.getTipoProdotto();
                if (/*(tipoProdotto == AttivitaEsecProdotto.PRODOTTO_PRIMARIO && !isAtvFinale) ||*/ //Fix 15030 //Fix 16734 //Fix 17726
                    tipoProdotto == AttivitaEsecProdotto.SFRIDO_ROTTAME ||
                    tipoProdotto == AttivitaEsecProdotto.SOTTOPRODOTTO ||
                    (tipoProdotto == AttivitaEsecProdotto.ARTICOLO_WIP && atvEsecPrd.getArticolo().isArticLotto()) ||
                    (atvEsecPrd.getTipoProdotto() == AttivitaEsecProdotto.SCARTO &&
                     ((!atvEsecPrd.getIdArticolo().equals(atvEsecPrd.getAttivitaEsecutiva().getOrdineEsecutivo().getIdArticolo()) &&
                     atvEsecPrd.getArticolo().getTipoParte() != ArticoloDatiIdent.ARTICOLO_WIP) ||
                     atvEsecPrd.getArticolo().isArticLotto()))) { //Fix 17241 //Fix 17550
                  //Fix 16734 inizio
                  result = initRigaDaOrdineEsec(bo, riga, atvEsecPrd, i);
                  bo = (RilevDatiPrdTS)result[0];
                  i = ((Integer)result[1]).intValue();
                  //Fix 16734 fine
                }
              }
            }
            /*Fix 19148 inizio
            out.println("<script language='JavaScript1.2'>");
            //Fix 18536 Inizio
            //out.println("var inizialeTotRighe = "+ i +";");
            out.println("var inizialeTotRighe = "+ (i-1) +";");
            out.println("lastCurrentLine = " + (i-1) + ";");
            if(bo.getCurrentNumPag() > 1)
              iTotaliRigheInitial = ((bo.getCurrentNumPag()-1) * 20)+(i-1);
              out.println("totRighe = "+ iTotaliRigheInitial +";");
            //Fix 18536 Fine
            out.println("</script>");
            Fix 19148 fine*/
          }
          catch (Exception ex) {
            ex.printStackTrace();
          }
        }
        //Fix 19148 inizio
        try {
        	out.println("<script language='JavaScript1.2'>");
        	//Fix 18536 Inizio
        	//out.println("var inizialeTotRighe = "+ i +";");
        	out.println("var inizialeTotRighe = "+ (i-1) +";");
        	out.println("lastCurrentLine = " + (i-1) + ";");
					out.println("lastCurrentLine = (lastCurrentLine < 1) ? 1 : lastCurrentLine;");		//Fix 25976
        	if(bo.getCurrentNumPag() > 1)
        		iTotaliRigheInitial = ((bo.getCurrentNumPag()-1) * 20)+(i-1);
          	out.println("totRighe = "+ iTotaliRigheInitial +";");
          	//Fix 18536 Fine
          	riempiProdottiConUMSec(bo, out); //Fix 39680
          	out.println("</script>");
        	}
        	catch (Exception ex) {
        		ex.printStackTrace();
        	}
        //Fix 19148 fine
      }
    }
    //Fix 14722 fine
    //Fix 32789 -- Blocco commentato Inizio
    //Fix 18536 Inizio
    /*String action = (String)getRequest().getAttribute("Action");
    if(action != null && action.equals(RilevDatiPrdTSFormActionAdapter.SUCCESSIVO))
      bo.setCurrentNumPag(1);*/
    //Fix 18536 Fine
    //Fix 32789 -- Blocco commentato Fine
    
    //Fix 33683 inizio
    if(action != null && action.equals(RilevDatiPrdTSFormActionAdapter.SUCCESSIVO))
      bo.setCurrentNumPag(1);
    //Fix 33683 fine
    return bo;
  }

  public void displayLottiFields (JspWriter out) {
    RilevDatiPrdTS bo = (RilevDatiPrdTS) getBODataCollector().getBo();
    if (bo != null  && bo.getIdProdotto1() != null) { //Fix 19599
      try {
        for (int i = 1; i < 21; i++) {
          String getterPrd = "getProdotto" + String.valueOf(i);
          //String getterVers = "getProdottoVersione" + String.valueOf(i); //Fix 15030
          //String getterCfg = "getProdottoConfig" + String.valueOf(i); //Fix 15030
          Class c = Factory.getClass(bo.getClass());
          Method prdMethod = c.getMethod(getterPrd, null);
          //Method methodVers = c.getMethod(getterVers, null); //Fix 15030
          //Method methodCfg = c.getMethod(getterCfg, null); //Fix 15030
          Articolo prodotto = (Articolo) prdMethod.invoke(bo, null);
          //ArticoloVersione versione = (ArticoloVersione)methodVers.invoke(bo, null); //Fix 15030
          //Configurazione config = (Configurazione)methodCfg.invoke(bo, null); //Fix 15030
          //if (prodotto != null) {//Fix 33267 
          if(prodotto != null && prodotto.getIdArticolo() != null) {//Fix 33267
            //Fix 15217 inizio
            boolean enableLotto = false;
            if (!prodotto.isArticLotto() ||
                /*prodotto.getArticoloDatiMagaz().getGenAutLotProd() == ArticoloDatiMagaz.CREA_DA_DOCUMENTO)*/ //Fix 30104
            	RilevDatiPrdTS.isCreazioneAutomaticaLottiDaDoc(prodotto)) //Fix 30104
              enableLotto = false;
            else {
            AttivitaEsecutiva atv = null;
            //Fix 18295 inizio
            //Fix 16109 inizio
            if (bo.getTipoBolla() ==  RilevazioneDatiProdTes.BOLLA_CUCITA){
              atv = getAttivitaEsecutiva(i);
              if (!atv.isAttivitaFinale() && prodotto.getIdArticolo().equals(atv.getOrdineEsecutivo().getIdArticolo())) //Fix 16734 disabilita i lotti solo se atv non finale e prd primario
                enableLotto = false;
              else
                enableLotto = true;
            }
            else
              enableLotto = true;
            /*else
              atv = bo.getAttivitaEsecutiva();
            //Fix 16109 fine
              if (!atv.isAttivitaFinale() && prodotto.getIdArticolo().equals(atv.getOrdineEsecutivo().getIdArticolo())) //Fix 16734 disabilita i lotti solo se atv non finale e prd primario
                enableLotto = false;
              else
                enableLotto = true;*/
            //Fix 18295 fine
            }
            if (!enableLotto) {
              out.println("document.getElementById('IdProdottoLotto"+i+"').style.background = bCo;");
              out.println("document.getElementById('IdProdottoLotto"+i+"').typeNameJS.mandatory = false;");
              out.println("document.getElementById('IdProdottoLotto"+i+"').readOnly = true;");
              out.println("document.getElementById('CreaLottoBut"+i+"').style.display=displayNone;");
              //Fix 33267 -- Inizio
              if (bo.getTipoBolla() ==  RilevazioneDatiProdTes.BOLLA_CUCITA && !getAttivitaEsecutiva(i).isAttivitaFinale() &&
            	  prodotto.getIdArticolo().equals(getAttivitaEsecutiva(i).getOrdineEsecutivo().getIdArticolo()))
                out.println("document.getElementById('IdProdottoLotto"+i+"').value = \"\";");
              //Fix 33267 -- Fine
            }
            else{//Fix 15866 inizio
              out.println("document.getElementById('IdProdottoLotto"+i+"').style.background = mCo;");
              out.println("document.getElementById('IdProdottoLotto"+i+"').typeNameJS.mandatory = true;");
              //Fix 16734 inizio
              out.println("document.getElementById('IdProdottoLotto"+i+"').style.backgroundImage = \"url('thermweb/css/angoloIS.gif')\";");
              out.println("document.getElementById('IdProdottoLotto"+i+"').style.backgroundPosition = \"bottom right\";");
              out.println("document.getElementById('IdProdottoLotto"+i+"').style.backgroundRepeat = \"no-repeat\";");
              //Fix 16734 fine
            }//Fix 15866 fine
            //Fix 18295 inizio
            if (readOnlyQta.contains(new Integer(i))) {
              out.println("document.getElementById('QtaVrsUMPrm"+i+"').style.background = bCo;");
              out.println("document.getElementById('QtaVrsUMPrm"+i+"').typeNameJS.mandatory = false;");
              out.println("document.getElementById('QtaVrsUMPrm"+i+"').readOnly = true;");
              out.println("document.getElementById('QtaVrsUMSec"+i+"').style.background = bCo;");
              out.println("document.getElementById('QtaVrsUMSec"+i+"').typeNameJS.mandatory = false;");
              out.println("document.getElementById('QtaVrsUMSec"+i+"').readOnly = true;");
            }
            else if (readOnlyQtaSca.contains(new Integer(i))) {
              out.println("document.getElementById('QtaScartoUMPrm"+i+"').style.background = bCo;");
              out.println("document.getElementById('QtaScartoUMPrm"+i+"').typeNameJS.mandatory = false;");
              out.println("document.getElementById('QtaScartoUMPrm"+i+"').readOnly = true;");
              out.println("document.getElementById('QtaScartoUMSec"+i+"').style.background = bCo;");
              out.println("document.getElementById('QtaScartoUMSec"+i+"').typeNameJS.mandatory = false;");
              out.println("document.getElementById('QtaScartoUMSec"+i+"').readOnly = true;");
            }
            //Fix 18295 fine
            //Fix 15217 fine
            //Fix 15030 inizio
            //Fix 15217 blocco commentato
            /*if(bo.getTipoBolla() == RilevazioneDatiProdTes.BOLLA_CUCITA){
              if(bo.getBollaCucita() != null && !bo.getBollaCucita().getRighe().isEmpty()){
                List righeBolla = bo.getBollaCucita().getRighe();
                boolean atvFinale = false;
                for(int j=0; j<righeBolla.size(); j++){
                  BollaCucitaRig rigaBolla = (BollaCucitaRig)righeBolla.get(j);
                  if(rigaBolla != null && rigaBolla.getAttivita() != null && rigaBolla.getAttivita().getOrdineEsecutivo() != null){
                    OrdineEsecutivo ordEsec = rigaBolla.getAttivita().getOrdineEsecutivo();
                    atvFinale = rigaBolla.getAttivita().isAttivitaFinale();
                    List prodotti = rigaBolla.getAttivita().getProdotti();
                    Iterator iterPrd = prodotti.iterator();
                    while(iterPrd.hasNext()){
                      AttivitaEsecProdotto atvEsecPrd = (AttivitaEsecProdotto) iterPrd.next();
                      char tipoProdotto = (atvEsecPrd != null ? atvEsecPrd.getTipoProdotto() : '-');
                      Integer idVersione = (versione != null ? versione.getIdVersione() : null);
                      Integer idConfig = (config != null ? config.getIdConfigurazione() : null);
                      if (atvFinale) {
                        if (RilevDatiPrdTS.verificaAttivitaPrd(atvEsecPrd, prodotto.getIdArticolo(), idConfig, idVersione)) {
                          if (tipoProdotto == AttivitaEsecProdotto.SFRIDO_ROTTAME ||
                              tipoProdotto == AttivitaEsecProdotto.SOTTOPRODOTTO) {
                            out.println("document.getElementById('IdProdottoLotto" + i + "').value = '';");
                            out.println("document.getElementById('IdProdottoLotto" + i + "').style.background = bCo;");
                            out.println("document.getElementById('IdProdottoLotto" + i + "').readOnly = true;");
                            out.println("document.getElementById('CreaLottoBut" + i + "').disabled = true;");
                          }
                        }
                      }
                      else {
                        if (RilevDatiPrdTS.verificaAttivitaPrd(atvEsecPrd, prodotto.getIdArticolo(), idConfig, idVersione)) {
                          if (tipoProdotto == AttivitaEsecProdotto.SFRIDO_ROTTAME ||
                              tipoProdotto == AttivitaEsecProdotto.SOTTOPRODOTTO) {
                            out.println("document.getElementById('IdProdottoLotto" + i + "').value = '';");
                            out.println("document.getElementById('IdProdottoLotto" + i + "').style.background = bCo;");
                            out.println("document.getElementById('IdProdottoLotto" + i + "').readOnly = true;");
                            out.println("document.getElementById('CreaLottoBut" + i + "').disabled = true;");
                          }
                        }
                        if (RilevDatiPrdTS.verificaOrdEsecArt(ordEsec, prodotto.getIdArticolo(), idConfig, idVersione)) {
                          if (tipoProdotto == AttivitaEsecProdotto.PRODOTTO_PRIMARIO){
                            out.println("document.getElementById('IdProdottoLotto" + i + "').value = '';");
                            out.println("document.getElementById('IdProdottoLotto" + i + "').style.background = bCo;");
                            out.println("document.getElementById('IdProdottoLotto" + i + "').readOnly = true;");
                            out.println("document.getElementById('CreaLottoBut" + i + "').disabled = true;");
                          }
                        }
                      }
                    }
                  }
                }
              }
            }*/
            //Fix 15030 fine
          }
        }
      }
      catch (Exception ex) {
       ex.printStackTrace(Trace.excStream);
     }
    }
  }
  //Fix 14721 --fine

  //Fix 14722 --inizio
  public void displayDettaglioBollaCucita(JspWriter out, RilevDatiPrdTS bo) throws IOException {
    if (bo.getBollaCucita() != null) {
      List bollaAggregateList = bo.getBollaCucita().getRighe();

      out.println("<table cellpadding=\"0\" cellspacing=\"0\" style=\"width:100%\">");
      out.println("<tr valign=\"top\">");
      out.println("<td style=\"height:0px\"></td>");
      out.println("<tr>");
      out.println("<td width=\"15px\"></td>");
      out.println("<td>");
      
      //out.println("  <table id=\"extraTable\" cellpadding=\"3\" cellspacing=\"3\" class=\"monitorListTable\" style=\"height: 100%; width: 100%\">");//Fix 30236
      out.println("  <table id=\"extraTable\" cellpadding=\"3\" cellspacing=\"3\" class=\"dettaglioListTable\">"); //Fix 30236 //Fix 30298
      //Fix 30298 inizio
      out.println("  <tr>");
      out.println("    <th class=\"cell\" >" + WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "N_ritorno")) + "</th>");
      out.println("    <th class=\"cell\" >" + WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "Attività")) + "</th>");
      out.println("    <th class=\"cell\" >" + WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "Ordine")) + "</th>");
      out.println("    <th class=\"cell\" >" + WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "Articolo")) + "</th>");
      out.println("    <th class=\"cell\" >" + WebElement.formatStringForHTML(ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "Descr_articolo")) + "</th>");
      out.println("  </tr>");
      //Fix 30298 fine
      int index = 0;
      Iterator iter = bollaAggregateList.iterator();
      while (iter.hasNext()) {
        BollaCucitaRig riga = (BollaCucitaRig) iter.next();
        if (riga != null) {
          String bollaLav = riga.getAttivita() != null ? riga.getAttivita().getNumeroRitorno() : "";
          String descrRilev = riga.getAttivita() != null ? riga.getAttivita().getDescrizione().getDescrizione() : "";
          String numOrdine = riga.getAttivita() != null ? riga.getAttivita().getOrdineEsecutivo().getNumeroOrdFmt() : "";
          //Fix 30298 inizio
          String articolo = riga.getArticolo() != null ? riga.getArticolo().getIdArticolo() : "";
          String descrizioneArticolo = riga.getArticolo() != null ? riga.getArticolo().getDescrizioneArticoloNLS().getDescrizione() : "";
          //Fix 30298 fine
          out.println("    <td id=\"BollaLavTD" + index + "\" class=\"cell\" >" + WebElement.formatStringForHTML(bollaLav) + "</td>");
          out.println("    <td class=\"cell\" >" + WebElement.formatStringForHTML(descrRilev) + "</td>");
          out.println("    <td class=\"cell\" nowrap=\"true\" >" + WebElement.formatStringForHTML(numOrdine) + "</td>");
          //Fix 30298 inizio
          out.println("    <td class=\"cell\" >" + WebElement.formatStringForHTML(articolo) + "</td>");
          out.println("    <td class=\"cell\" >" + WebElement.formatStringForHTML(descrizioneArticolo) + "</td>");
          //Fix 30298 fine
          out.println("   </tr>");
          index++;
        }
      }
      out.println("  </table>");
      out.println("</td>");
      out.println("</tr>");

      out.println("<tr>");
      out.println("<td colspan=\"5\" style=\"height:100%\"></td>");
      out.println("</tr>");
      out.println("<tr>");
      out.println("<td width=\"5px\">");
      out.println("</td>");
      out.println("<td colspan=\"5\">");
      out.println("<table cellpadding=\"3\" cellspacing=\"3\">");
      out.println("<tr>");
      out.println("<td><label class=\"labelError\" id=\"ErroriList\"></label></td>");
      out.println("</tr>");
      out.println("</table>");
      out.println("</td>");
      out.println("</tr>");
      out.println("</table>");
    }
  }

  public RilevDatiPrdTS initRigheMateriali(RilevDatiPrdTS bo, JspWriter out) throws IOException {//Fix 15250 throws IOException {
    //Fix 24177 inizio
  	String azione = (String) getRequest().getAttribute("Azione");
  	if(azione != null && azione.equals(PRELIEVI) && hasErrore()) {
  	  visualizzaMateriali(bo, out);
  	  return bo;
  	}
  	if (bo != null && bo.getAttivitaEsecutiva() != null && abilitaInitRigheMateriali(bo)) {
  	  caricaRigheMateriali(bo, out);
  	}
  	//Fix 24177 fine
    //if (bo != null && bo.getBollaCucita() != null) {//Fix 24177
    //else if (bo != null && bo.getBollaCucita() != null) {//Fix 24177 //Fix 33683
  	else if (bo != null && bo.getBollaCucita() != null && abilitaInitRigheMateriali(bo)) {//Fix 33683
      if (bo.getBollaCucita().getMateriale() != null) {
        bo.setIdMateriale1(bo.getBollaCucita().getIdMateriale());
        bo.setIdConfigurazione1(bo.getBollaCucita().getIdConfigurazione());
        bo.setIdEsternoConfig1(bo.getBollaCucita().getIdEsternoConfig());//Fix 30079
        bo.setIdVersione1(bo.getBollaCucita().getIdVersione());
        if(!bo.getBollaCucita().getRighe().isEmpty()){
          BollaCucitaRig riga = (BollaCucitaRig)bo.getBollaCucita().getRighe().get(0);
          //Fix 32846 -- Inizio
          //Fix 33801 inizio
          //List materiali = riga.getAttivita().getMateriali();
          //Iterator iterator = materiali.iterator();
          List materiali = new ArrayList();
          Iterator iterator = riga.getAttivita().getMateriali().iterator();
          //Fix 33801 fine
          while(iterator.hasNext()) {
        	  AttivitaEsecMateriale atvEsecMat = (AttivitaEsecMateriale) iterator.next();
        	  if(atvEsecMat.getStato() == DatiComuniEstesi.VALIDO) {
        		  materiali.add(atvEsecMat);
        	  }
          }
          //Iterator iterMat = riga.getAttivita().getMateriali().iterator();
          Iterator iterMat = materiali.iterator();
          //Fix 32846 -- Fine
          //Fix 29844 inizio
		  out.println("<script language='JavaScript1.2'>");
		  out.println("var initRigaMat = true;");
		  out.println("</script>");
		  //Fix 29844 fine    
          while(iterMat.hasNext()){
            AttivitaEsecMateriale atvEsecMat = (AttivitaEsecMateriale)iterMat.next();
            if(RilevDatiPrdTS.verificaAttivitaMat(atvEsecMat, bo.getIdMateriale1(), bo.getIdConfigurazione1(), bo.getIdVersione1()))
              bo.setIdMagazzinoPrl1(atvEsecMat.getIdMagazzinoPrl());
            //Fix 15250 inizio
            out.println("<script language='JavaScript1.2'>");
            if(atvEsecMat.getArticolo().isArticLotto())
            {
              out.println(" isArtGestLot= true;");
            }
            else
            {
             out.println(" isArtGestLot= false;");
            }
            out.println("</script>");
            //Fix 15250 fine

          }
        }
      }
	  // Fix 29844 inizio
	  else {
		List materiali = new ArrayList();
		Iterator iterRiga = bo.getBollaCucita().getRighe().iterator();
		while (iterRiga.hasNext()) {
			BollaCucitaRig riga = (BollaCucitaRig) iterRiga.next();
			Iterator iterMat = riga.getAttivita().getMateriali().iterator();
			while (iterMat.hasNext()) {
				AttivitaEsecMateriale atvEsecMat = (AttivitaEsecMateriale) iterMat.next();
				if(atvEsecMat.getStato() == DatiComuniEstesi.VALIDO) { //Fix 32846
					materiali.add(atvEsecMat);
				} //Fix 32846
			}
		}
		// Raggruppare materiali
		List materialiRaggruppare = raggruppaMateriali(materiali);
		int i = 1;
		materialiRaggruppare = verificaMateriali(materialiRaggruppare, bo.getPersDatiPrdCausaleRilev());
		if (!materialiRaggruppare.isEmpty()) {
			Iterator iterAtvMat = materialiRaggruppare.iterator();
			try {
				while (iterAtvMat.hasNext()) {
					AttivitaEsecMateriale atvEsecMat = (AttivitaEsecMateriale) iterAtvMat.next();
					Object[] result = initRigaDaOrdineEsec(bo, atvEsecMat, i);
					bo = (RilevDatiPrdTS) result[0];
					i = ((Integer) result[1]).intValue();
				}
				out.println("<script language='JavaScript1.2'>");
				out.println("inizialeTotRighe = " + (i - 1) + ";");
				out.println("lastCurrentLine = " + (i - 1) + ";");
				out.println("lastCurrentLine = (lastCurrentLine < 1) ? 1 : lastCurrentLine;");
				out.println("totRighe = " + (i - 1) + ";");
				if (bo.getCurrentNumPag() > 1) {
					iTotaliRigheInitial = ((bo.getCurrentNumPag() - 1) * 20) + (i - 1);
					out.println("totRighe = " + iTotaliRigheInitial + ";");
					out.println("inizialeTotRighe = 20;");
					out.println("lastCurrentLine = 20;");
					out.println("totRighe = 20;");
				}
				out.println("</script>");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			String action = (String) getRequest().getAttribute("Action");
			if (action != null && action.equals(RilevDatiPrdTSFormActionAdapter.SUCCESSIVO))
				bo.setCurrentNumPag(1);
		}
	  }
	  // Fix 29844 fine
    }

    return bo;
  }
  //Fix 14722 --fine
  //Fix 15030 --inizio
  public Object[] initRigaDaOrdineEsec(RilevDatiPrdTS bo, BollaCucitaRig riga, AttivitaEsecProdotto atvEsecPrd, int i) { //Fix 16734
    Object[] result = new Object[2];
    List listaOrdine = new ArrayList();
    try {
      AttivitaEsecutiva atv = riga.getAttivita(); //Fix 15087
      if (atv.getOrdineEsecutivo() != null) {
        OrdineEsecutivo ordEsec = atv.getOrdineEsecutivo();
        if (isNuovoOrdine(listaOrdine, ordEsec)) {
          listaOrdine.add(ordEsec);
          //Fix 16734 inizio
          char tipoProdotto = atvEsecPrd.getTipoProdotto();
          //Fix 33097 -- Inizio
          //BigDecimal qtaTot = RilevDatiPrdTS.getQtaBuona(riga);
          BigDecimal qtaTot = null ;
          if(bo.getTipoTimbratura() != RilevazioneDatiProdRig.SOSPENSIONE) {
        	  qtaTot = RilevDatiPrdTS.getQtaBuona(riga);
          }
          if(qtaTot == null) {
        	qtaTot = new BigDecimal("0");  
          }
          //Fix 33097 -- Fine
          //Fix 16528 inizio
          if (qtaTot != null && (tipoProdotto == AttivitaEsecProdotto.SFRIDO_ROTTAME ||
            tipoProdotto == AttivitaEsecProdotto.SOTTOPRODOTTO ||
            tipoProdotto == AttivitaEsecProdotto.ARTICOLO_WIP ||
            tipoProdotto == AttivitaEsecProdotto.SCARTO)) { //Fix 17241 //Fix 17550
            //Fix 16700 --inizio
            //qta = atvEsecPrd.getCoeffProduzione().multiply(qta).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal qtaBaseUMPrm = atvEsecPrd.getAttivitaEsecutiva().getOrdineEsecutivo().getQtaBaseUMPrm();
            if(qtaBaseUMPrm != null){
              //qtaTot = qtaTot.multiply(atvEsecPrd.getCoeffProduzioneUMPrm()).divide(qtaBaseUMPrm, 12, 0);//Fix 30965
              qtaTot = Q6Calc.get().divide(qtaTot.multiply(atvEsecPrd.getCoeffProduzioneUMPrm()), qtaBaseUMPrm, 12, 0);//Fix 30965
            }
            //Fix 16700 --fine
          }
          //Fix 16528 fine
          Iterator iter = recuperaLotti(atvEsecPrd, qtaTot).iterator();
          while (iter.hasNext()) {
            Object[] obj = (Object[]) iter.next();
            String idLotto = (String) obj[0];
            BigDecimal qta = (BigDecimal) obj[1];
            //Fix 16734 fine
            String setterPrd = "setProdotto" + String.valueOf(i);
            String setterDescPrd = "setDescrizioneProdotto" + String.valueOf(i); //Fix 31182
            //Fix 18536 Inizio
            //String setterVers = "setProdottoVersione" + String.valueOf(i);
            //String setterCfg = "setProdottoConfig" + String.valueOf(i);
            //String setterMag = "setMagazzinoVrs" + String.valueOf(i);
            String setterVers = "setIdProdottoVersione" + String.valueOf(i);
            String setterCfg = "setIdProdottoConfig" + String.valueOf(i);
            String setterEsternoCfg = "setPrdIdEsternoConfig" + String.valueOf(i);
            String setterMag = "setIdMagazzinoVrs" + String.valueOf(i);
            //Fix 18536 Fine
            String setterQtaBuo = "setQtaVrsUmPrm" + String.valueOf(i);
            String setterIdLot = "setIdLottoProdotto" + String.valueOf(i); //Fix 16734
            String setterAnnoOrdine = "setIdAnnoOrdine" + String.valueOf(i);
            String setterNumeroOrdine = "setIdNumeroOrdine" + String.valueOf(i);
            String setterIdRigaAtv = "setIdRigaAttivita" + String.valueOf(i);

            Class c = Factory.getClass(bo.getClass());
            Method prdMethod = c.getMethod(setterPrd, new Class[] {Articolo.class});
            Method descPrdMethod = c.getMethod(setterDescPrd, new Class[] {String.class}); //Fix 31182
            //Fix 18536 Inizio
            //Method versMethod = c.getMethod(setterVers, new Class[] {ArticoloVersione.class});
            //Method cfgMethod = c.getMethod(setterCfg, new Class[] {Configurazione.class});
            //Method magMethod = c.getMethod(setterMag, new Class[] {Magazzino.class});
            Method versMethod = c.getMethod(setterVers, new Class[] {Integer.class});
            Method cfgMethod = c.getMethod(setterCfg, new Class[] {Integer.class});
            Method esternoCfgMethod = c.getMethod(setterEsternoCfg, new Class[] {String.class});
            Method magMethod = c.getMethod(setterMag, new Class[] {String.class});
            //Fix 18536 Fine
            Method qtaMethod = c.getMethod(setterQtaBuo, new Class[] {BigDecimal.class});
            Method idLotMethod = c.getMethod(setterIdLot, new Class[] {String.class}); //Fix 16734
            Method annoOrdineMethod = c.getMethod(setterAnnoOrdine, new Class[] {String.class});
            Method numOrdineMethod = c.getMethod(setterNumeroOrdine, new Class[] {String.class});
            Method idRigaAtvMethod = c.getMethod(setterIdRigaAtv, new Class[] {Integer.class});
            prdMethod.invoke(bo, new Object[] {atvEsecPrd.getArticolo()});
            String descrizionePrd = riga != null ? riga.getOrdineEsecutivo().getDescrizione().getDescrizione() : null; //Fix 31182
            descPrdMethod.invoke(bo, new Object[] {descrizionePrd}); //Fix 31182
            //Fix 18536 Inizio
            //versMethod.invoke(bo, new Object[] {atvEsecPrd.getVersione()});
            //cfgMethod.invoke(bo, new Object[] {atvEsecPrd.getConfigurazione()});
            //magMethod.invoke(bo, new Object[] {atvEsecPrd.getMagazzinoVersamento()});
            versMethod.invoke(bo, new Object[] {atvEsecPrd.getIdVersione()});
            cfgMethod.invoke(bo, new Object[] {atvEsecPrd.getIdConfigurazione()});
            esternoCfgMethod.invoke(bo, new Object[] {atvEsecPrd.getIdEsternoConfig()});
            magMethod.invoke(bo, new Object[] {atvEsecPrd.getIdMagazzinoVrs()});
            //Fix 18536 Fine
            qtaMethod.invoke(bo, new Object[] {qta});
            //Fix 16734 inizio
            if (idLotto != null && !idLotto.equals(Lotto.LOTTO_DUMMY))
              idLotMethod.invoke(bo, new Object[] {idLotto});
            String annoOrd = riga != null ? riga.getIdAnnoOrdine() : null;
            String numOrd = riga != null ? riga.getIdNumeroOrdine() : null;
            Integer idRigAtv = (riga != null && riga.getAttivita() != null) ? riga.getAttivita().getIdRigaAttivita() : null;
            annoOrdineMethod.invoke(bo, new Object[] {annoOrd});
            numOrdineMethod.invoke(bo, new Object[] {numOrd});
            idRigaAtvMethod.invoke(bo, new Object[] {idRigAtv});
            //Fix 16734 fine
            i++;
          }
        }
      }
      //Fix 18536 Inizio
      if(i > 20){
        //bo.setCurrentNumPag((i/20)+1); //Fix 19148
      	bo.setCurrentNumPag((i/20)+bo.getCurrentNumPag()); //Fix 19148
        i=1;
      }
      //Fix 18536 Fine
      result[0] = bo;
      result[1] = new Integer(i);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    return result;
  }

  //Fix 16734 inizio
  public Object[] initRigaDaOrdineEsec(RilevDatiPrdTS bo, AttivitaEsecProdotto atvEsecPrd, int i) {
    Object[] result = new Object[2];
    try {
      char tipoProdotto = atvEsecPrd.getTipoProdotto();
      //Fix 39680 -- Inizio
      if(tipoProdotto == AttivitaEsecProdotto.PRODOTTO_PRIMARIO) {
        String ricalcoloQuantitaVers = "setRicalcoloQuantitaVers" + String.valueOf(i);
        Class c = Factory.getClass(bo.getClass());
        Method ricalcoloQuantitaVersMethod = c.getMethod(ricalcoloQuantitaVers, new Class[] {boolean.class});
        ricalcoloQuantitaVersMethod.invoke(bo, new Object[] {bo.isRicalcoloQuantita()});
      }
      //Fix 39680 -- Fine
      boolean addReadOnlyQta = false; //Fix 33200
      boolean addReadOnlyQtaSca = false; //Fix 33200
      if (tipoProdotto == AttivitaEsecProdotto.PRODOTTO_PRIMARIO ||
          tipoProdotto == AttivitaEsecProdotto.SFRIDO_ROTTAME ||
          tipoProdotto == AttivitaEsecProdotto.SOTTOPRODOTTO ||
          tipoProdotto == AttivitaEsecProdotto.ARTICOLO_WIP ||
          (tipoProdotto == AttivitaEsecProdotto.SCARTO &&
           ((/*!atvEsecPrd.getIdArticolo().equals(atvEsecPrd.getAttivitaEsecutiva().getOrdineEsecutivo().getIdArticolo()) &&*/ //Fix 26319
           atvEsecPrd.getArticolo().getTipoParte() != ArticoloDatiIdent.ARTICOLO_WIP) ||
           atvEsecPrd.getArticolo().isArticLotto()))) { //Fix 17241 //Fix 17550
        //Fix 17550 inizio
        BigDecimal qtaTot = bo.getQuantita();// RilevDatiPrdTS.getQtaResiduaAtv(bo.getAttivitaEsecutiva());
        if (qtaTot == null)
          qtaTot = new BigDecimal("0");
        BigDecimal qtaSca = null;
        //Fix 17550 fine
        //Fix 16528 inizio
        if (tipoProdotto == AttivitaEsecProdotto.SFRIDO_ROTTAME ||
            tipoProdotto == AttivitaEsecProdotto.SOTTOPRODOTTO ||
            tipoProdotto == AttivitaEsecProdotto.SCARTO) { //Fix 17241 //Fix 17250
          //Fix 16700 --inizio
          //qta = atvEsecPrd.getCoeffProduzione().multiply(qta).setScale(2, BigDecimal.ROUND_HALF_UP);
        //Fix 17550 inizio
        ///if (bo.getQuantita() != null) //Fix 17241
          //qtaTot = bo.getQuantita(); //Fix 17241

          if (tipoProdotto == AttivitaEsecProdotto.SCARTO) {
        	//Fix 26319 - inizio
        	  /*if (!atvEsecPrd.getIdArticolo().equals(atvEsecPrd.getAttivitaEsecutiva().getOrdineEsecutivo().getIdArticolo()) &&
             atvEsecPrd.getArticolo().getTipoParte() != ArticoloDatiIdent.ARTICOLO_WIP) {
              qtaTot = bo.getQtaScarto();
              if (qtaTot == null)
                qtaTot = new BigDecimal("0");
              readOnlyQtaSca.add(new Integer(i)); //Fix 18295
            }
            else {*/
        	//Fix 26319 - fine
              qtaTot = new BigDecimal("0");
              qtaSca = bo.getQtaScarto();
              qtaSca = modificaQtaScarto(qtaSca, bo, atvEsecPrd); //Fix 27513
              if (qtaSca == null)
                qtaSca = new BigDecimal("0");
              //readOnlyQta.add(new Integer(i)); //Fix 18295 //Fix 33200
              addReadOnlyQta = true; //Fix 33200
              //}// Fix 26319
          }
          //Fix 17550 fine
		  //Fix 26319 - inizio
		  //Fix 26730 Inizio
          /*else if (tipoProdotto == AttivitaEsecProdotto.SOTTOPRODOTTO){        	  
              Iterator iter = atvEsecPrd.getAttivitaEsecutiva().getProdotti().iterator();
              while (iter.hasNext()) {
                AttivitaEsecProdotto tmp = (AttivitaEsecProdotto) iter.next();
                if (tmp.getTipoProdotto() == AttivitaEsecProdotto.SCARTO &&
                    (tmp.getIdArticolo().equals(atvEsecPrd.getIdArticolo())))
                	readOnlyQtaSca.add(new Integer(i));                  
              }
          }*/ //Fix 26730 FINE
          //Fix 26319 - fine
          else {
          	//Fix 26730 Inizio
          	if (tipoProdotto == AttivitaEsecProdotto.SOTTOPRODOTTO){
          		Iterator iter = atvEsecPrd.getAttivitaEsecutiva().getProdotti().iterator();
          		while (iter.hasNext()) {
          			AttivitaEsecProdotto tmp = (AttivitaEsecProdotto) iter.next();
          			if (tmp.getTipoProdotto() == AttivitaEsecProdotto.SCARTO &&(tmp.getIdArticolo().equals(atvEsecPrd.getIdArticolo())))
          				//readOnlyQtaSca.add(new Integer(i)); //Fix 33200
          				addReadOnlyQtaSca = true; //Fix 33200

          		}
          	}
          	//Fix 26730 Fine
            BigDecimal qtaBaseUMPrm = atvEsecPrd.getAttivitaEsecutiva().getOrdineEsecutivo().getQtaBaseUMPrm();
            if(qtaBaseUMPrm != null) {
              //qtaTot = qtaTot.multiply(atvEsecPrd.getCoeffProduzioneUMPrm()).divide(qtaBaseUMPrm, 12, 0);//Fix 30965
              qtaTot = Q6Calc.get().divide(qtaTot.multiply(atvEsecPrd.getCoeffProduzioneUMPrm()), qtaBaseUMPrm, 12, 0);//Fix 30965
            }
          }
        }
        //Fix 16700 --fine
        //Fix 17550 inizio
        else if (tipoProdotto == AttivitaEsecProdotto.PRODOTTO_PRIMARIO || tipoProdotto == AttivitaEsecProdotto.ARTICOLO_WIP) {
          boolean found = false;
          Iterator iter = atvEsecPrd.getAttivitaEsecutiva().getProdotti().iterator();
          while (iter.hasNext()) {
            AttivitaEsecProdotto tmp = (AttivitaEsecProdotto) iter.next();
            if (tmp.getTipoProdotto() == AttivitaEsecProdotto.SCARTO &&
                (tmp.getIdArticolo().equals(atvEsecPrd.getAttivitaEsecutiva().getOrdineEsecutivo().getIdArticolo()) ||
                tmp.getArticolo().getTipoParte() == ArticoloDatiIdent.ARTICOLO_WIP))
				{// Fix 26319
					found = true;
					//readOnlyQtaSca.add(new Integer(i)); // Fix 26319 //Fix 33200
					addReadOnlyQtaSca = true; //Fix 33200
				}// Fix 26319
          }
          if (!found)
            qtaSca = bo.getQtaScarto();
          //Fix 18295 inizio
          // Fix 26319 - inizio
          /*else       	  
            readOnlyQtaSca.add(new Integer(i));*/
          // Fix 26319 - fine
          //Fix 18295 fine
        }
        //Fix 17550 fine
        //Fix 16528 fine
        boolean scaSetted = false; //Fix 17550
        Iterator iter = recuperaLotti(atvEsecPrd, qtaTot).iterator();
        while (iter.hasNext()) {
          //Fix 33200 inizio
          if (addReadOnlyQta)
      		readOnlyQta.add(new Integer(i));
          if (addReadOnlyQtaSca)
  			readOnlyQtaSca.add(new Integer(i));
          //Fix 33200 fine
          Object[] obj = (Object[]) iter.next();
          String idLotto = (String) obj[0];
          BigDecimal qta = (BigDecimal) obj[1];
          String setterPrd = "setProdotto" + String.valueOf(i);
          String setterDescPrd = "setDescrizioneProdotto" + String.valueOf(i); //Fix 17815
          //Fix 18536 Inizio
          //String setterVers = "setProdottoVersione" + String.valueOf(i);
          //String setterCfg = "setProdottoConfig" + String.valueOf(i);
          //String setterMag = "setMagazzinoVrs" + String.valueOf(i);
          String setterVers = "setIdProdottoVersione" + String.valueOf(i);
          String setterCfg = "setIdProdottoConfig" + String.valueOf(i);
          String setterEsternoCfg = "setPrdIdEsternoConfig" + String.valueOf(i);
          String setterMag = "setIdMagazzinoVrs" + String.valueOf(i);
          //Fix 18536 Fine
          String setterQtaBuo = "setQtaVrsUmPrm" + String.valueOf(i);
          String setterQtaVrsUMSec = "setQtaVrsUmSec" + String.valueOf(i);//Fix 25921
          String setterQtaSca = "setQtaScartoUmPrm" + String.valueOf(i); //Fix 17550
          String setterQtaScartoUMSec = "setQtaScartoUmSec" + String.valueOf(i);//Fix 25921
          String setterIdLot = "setIdLottoProdotto" + String.valueOf(i);
          //Fix 17815 Inizio
          String descrizionePrd = (atvEsecPrd.getDescrizione() != null && atvEsecPrd.getDescrizione().getDescrizione() != null) ?
              atvEsecPrd.getDescrizione().getDescrizione() :
              (atvEsecPrd.getArticolo() != null && atvEsecPrd.getArticolo().getDescrizioneArticoloNLS() != null) ? atvEsecPrd.getArticolo().getDescrizioneArticoloNLS().getDescrizione() :
              "";
          //Fix 17815 Fine

          Class c = Factory.getClass(bo.getClass());
          Method prdMethod = c.getMethod(setterPrd, new Class[] {Articolo.class});
          Method descPrdMethod = c.getMethod(setterDescPrd, new Class[] {String.class}); //Fix 17815
          //Fix 18536 Inizio
          //Method versMethod = c.getMethod(setterVers, new Class[] {ArticoloVersione.class});
          //Method cfgMethod = c.getMethod(setterCfg, new Class[] {Configurazione.class});
          //Method magMethod = c.getMethod(setterMag, new Class[] {Magazzino.class});
          Method versMethod = c.getMethod(setterVers, new Class[] {Integer.class});
          Method cfgMethod = c.getMethod(setterCfg, new Class[] {Integer.class});
          Method EsternoCfgMethod = c.getMethod(setterEsternoCfg, new Class[] {String.class});
          Method magMethod = c.getMethod(setterMag, new Class[] {String.class});
          //Fix 18536 Fine
          Method qtaMethod = c.getMethod(setterQtaBuo, new Class[] {BigDecimal.class});
          Method qtaVrsSecMethod = c.getMethod(setterQtaVrsUMSec, new Class[] {BigDecimal.class});//Fix 25921
          Method qtaScaMethod = c.getMethod(setterQtaSca, new Class[] {BigDecimal.class}); //Fix 17550
          Method qtaScaSecMethod = c.getMethod(setterQtaScartoUMSec, new Class[] {BigDecimal.class});//Fix 25921
          Method idLotMethod = c.getMethod(setterIdLot, new Class[] {String.class});
          prdMethod.invoke(bo, new Object[] {atvEsecPrd.getArticolo()});
          descPrdMethod.invoke(bo, new Object[] {descrizionePrd}); //Fix 17815
          //Fix 18536 Inizio
          //versMethod.invoke(bo, new Object[] {atvEsecPrd.getVersione()});
          //cfgMethod.invoke(bo, new Object[] {atvEsecPrd.getConfigurazione()});
          //magMethod.invoke(bo, new Object[] {atvEsecPrd.getMagazzinoVersamento()});
          versMethod.invoke(bo, new Object[] {atvEsecPrd.getIdVersione()});
          cfgMethod.invoke(bo, new Object[] {atvEsecPrd.getIdConfigurazione()});
          EsternoCfgMethod.invoke(bo, new Object[] {atvEsecPrd.getIdEsternoConfig()});
          magMethod.invoke(bo, new Object[] {atvEsecPrd.getIdMagazzinoVrs()});
          //Fix 18536 Fine
          qtaMethod.invoke(bo, new Object[] {qta});
          //Fix 25921 inizio          
  				Articolo articolo = atvEsecPrd.getArticolo();
  				if (articolo != null && articolo.getIdUMSecMag() != null) {
  					//Fix 32135 -- Inizio
  					//BigDecimal qtaSec = articolo.convertiUM(qta, articolo.getUMPrmMag(), articolo.getUMSecMag(),
  					//		atvEsecPrd.getVersione());
  				    //Fix 37890 inizio
  					//BigDecimal qtaSec = bo.getQuantitaSec();
  					BigDecimal qtaSec = null;
  				  	if (atvEsecPrd.getTipoProdotto() != AttivitaEsecProdotto.PRODOTTO_PRIMARIO)
  				  	  qtaSec = articolo.convertiUM(qta, articolo.getUMPrmMag(), articolo.getUMSecMag(), atvEsecPrd.getVersione());
  				  	else {
  				  	   qtaSec = bo.getQuantitaSec();
  				  	 //Fix 37890 fine  	
  		               if (qtaSec != null && qtaTot != null && qtaTot.compareTo(ZERO) != 0) //Fix 33053
  		                  qtaSec =  Q6Calc.get().divide(qta.multiply(qtaSec), qtaTot, 2, BigDecimal.ROUND_HALF_UP);
  		               else if (qta != null && qta.compareTo(ZERO) == 0) //Fix 33200
 	                      qtaSec = ZERO; //Fix 33200
  		            //Fix 32135 -- Fine
  				    } //Fix 37890
  				    if (qtaSec != null) //Fix 39755
 					   qtaSec = Q6Calc.get().setScale(qtaSec, 2, BigDecimal.ROUND_HALF_UP); //Fix 39755
  					qtaVrsSecMethod.invoke(bo, new Object[] {qtaSec});
  				}
          //Fix 25921 fine
          //Fix 17550 inizio
          //if (!scaSetted && qtaSca != null) { //Fix 33200
  		  //if (!scaSetted && qtaSca != null && qtaSca.compareTo(ZERO) > 0) { //Fix 33200 //Fix 45518
  		  if (!scaSetted && qtaSca != null && qtaSca.compareTo(ZERO) != 0) { //Fix 45518
  			 //Fix 33200 inizio
             if (articolo.getArticoloDatiMagaz().isLottoUnitario()) {
                qtaScaMethod.invoke(bo, new Object[] {UNO});
                qtaSca = qtaSca.subtract(UNO);
                if (qtaSca.compareTo(ZERO) <= 0)
              	  scaSetted = true;
             }
             else {
              //Fix 33200 fine
                qtaScaMethod.invoke(bo, new Object[] {qtaSca});
                scaSetted = true;
             } //Fix 33200
             //Fix 25921 inizio
    		 if (articolo != null && articolo.getIdUMSecMag() != null) {
    					   //Fix 32135 -- Inizio
    					   //BigDecimal qtaScaSec = articolo.convertiUM(qtaSca, articolo.getUMPrmMag(), articolo.getUMSecMag(),
    					   //		  atvEsecPrd.getVersione());
    					   BigDecimal qtaScaSec = bo.getQtaScartoSec();
    					   //Fix 37418 -- Bloc commentatto inizio
    		               /*  if (qtaScaSec != null && qtaSca != null && qtaSca.compareTo(ZERO) != 0) //Fix 33053
    		                   qtaScaSec =  Q6Calc.get().divide(qta.multiply(qtaScaSec), qtaSca, 2, BigDecimal.ROUND_HALF_UP);
    		                 else if (qtaSca != null && qtaSca.compareTo(ZERO) == 0) //Fix 33200
    		    		       qtaScaSec = ZERO; //Fix 33200 */
    		               //Fix 37418 -- Bloc commentatto fine
    					   //Fix 32135 -- Fine
    					   qtaScaSecMethod.invoke(bo, new Object[] {qtaScaSec});
     				}
            //Fix 25921 fine
          }
          //Fix 17550 fine
          if (idLotto != null && !idLotto.equals(Lotto.LOTTO_DUMMY))
            idLotMethod.invoke(bo, new Object[] {idLotto});
          //Fix 16528 fine
          i++;
          //28226 inizio
          if (i > 20) {
            bo.setCurrentNumPag((i / 20) + bo.getCurrentNumPag());
            bo.setIdAzienda(bo.getIdAzienda());
            i = 1;
          }
          //28226 fine          
        }
      }

      //Fix 18536 Inizio
      //28226 inizio
      /*      
      if(i > 20){
        //bo.setCurrentNumPag((i/20)+1); //Fix 19148
      	bo.setCurrentNumPag((i/20)+bo.getCurrentNumPag()); //Fix 19148
        i=1;
      }
            */
      //28226 fine
      //Fix 18536 Fine
      result[0] = bo;
      result[1] = new Integer(i);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    return result;
  }

  public List recuperaLotti(AttivitaEsecProdotto prodotto, BigDecimal quantita) {
    List ret = new ArrayList();
    try {
      Iterator lottiVrs = prodotto.getLottiProdotti().iterator();
      while (lottiVrs.hasNext()) {
        AttivitaEsecLottiPrd lottoVrs = (AttivitaEsecLottiPrd) lottiVrs.next();
        if (!lottoVrs.getIdLotto().equals(Lotto.LOTTO_DUMMY) &&
            (prodotto.getAttivitaEsecutiva().isAttivitaFinale() ||
             prodotto.getTipoProdotto() != AttivitaEsecProdotto.PRODOTTO_PRIMARIO) && //Fix 32012
        	(!prodotto.getArticolo().getArticoloDatiMagaz().isLottoUnitario() || lottoVrs.getStatoLotto() == AttivitaEsecLottiPrd.INVESO) ) { //Fix 32012
          BigDecimal qtaResidua = controllaBD(lottoVrs.getQtaResiduaUMPrm());
          //...Se la quantità residua del lotto dell'ordine è < di zero allora non considero il lotto
          //Fix 17550 riga commentata
          //if (quantita.compareTo(new BigDecimal("0")) > 0 && qtaResidua.compareTo(new BigDecimal("0")) > 0) {
            if (qtaResidua.compareTo(quantita) >= 0 || !lottiVrs.hasNext()) { //Fix 17550
              Object[] obj = new Object[2];
              //Fix 24696 inizio
              //obj[0] = lottoVrs.getIdLotto();
              //obj[0] = prodotto.getArticolo().getArticoloDatiMagaz().getGenAutLotProd() == ArticoloDatiMagaz.CREA_DA_DOCUMENTO ? Lotto.LOTTO_DUMMY : lottoVrs.getIdLotto();//Fix 30104
              obj[0] = RilevDatiPrdTS.isCreazioneAutomaticaLottiDaDoc(prodotto.getArticolo()) ? Lotto.LOTTO_DUMMY : lottoVrs.getIdLotto(); //Fix 30104
              //Fix 24696 fine
              obj[1] = quantita;
              ret.add(obj);
              quantita = new BigDecimal("0");
              //...Se sono a lotto unitario e ho esaurito la quantità richiesta allora esco dal metodo
              //if (prodotto.getArticolo().getArticoloDatiMagaz().isLottoUnitario()) {
               //break;
              //}
            }
            else if (qtaResidua.compareTo(new BigDecimal("0")) > 0) { //Fix 17550
              Object[] obj = new Object[2];
              //Fix 24696 inizio
              //obj[0] = lottoVrs.getIdLotto();
              //obj[0] = prodotto.getArticolo().getArticoloDatiMagaz().getGenAutLotProd() == ArticoloDatiMagaz.CREA_DA_DOCUMENTO ? Lotto.LOTTO_DUMMY : lottoVrs.getIdLotto();//Fix 30104
              obj[0] = RilevDatiPrdTS.isCreazioneAutomaticaLottiDaDoc(prodotto.getArticolo()) ? Lotto.LOTTO_DUMMY : lottoVrs.getIdLotto(); //Fix 30104
              //Fix 24696 fine
              obj[1] = qtaResidua;
              ret.add(obj);
              quantita = quantita.subtract(qtaResidua);
            }
          //}
        }
      }
      if (ret.isEmpty()) { //Fix 17550
      	//Fix 45683 -- Inizio
      	if(prodotto.getArticolo().getArticoloDatiMagaz().isLottoUnitario()) {
      	  BigDecimal UNO = new BigDecimal("1");
      	  while(quantita.compareTo(ZERO) > 0) {
      		Object[] obj = new Object[2];
              obj[0] = Lotto.LOTTO_DUMMY;
              obj[1] = UNO;
              ret.add(obj);
      		quantita = quantita.subtract(UNO);	
      	  }
      	}
      	else {
      	//Fix 45683 --Fine    	  
          Object[] obj = new Object[2];
          obj[0] = Lotto.LOTTO_DUMMY;
          obj[1] = quantita;
          ret.add(obj);
      	} //Fix 45683
      }
    }
    catch (Exception e) {
      e.printStackTrace(Trace.excStream);
    }
    return ret;
  }

  public BigDecimal controllaBD(BigDecimal param) {
    return param != null ? param : new BigDecimal("0");
  }
  //Fix 16734 fine

  public boolean isNuovoOrdine(List ordiniList, OrdineEsecutivo currentOrdEsec){
    if(ordiniList != null && !ordiniList.isEmpty() && currentOrdEsec != null){
      Iterator iterOrdini = ordiniList.iterator();
      while(iterOrdini.hasNext()){
        OrdineEsecutivo ordEsec = (OrdineEsecutivo)iterOrdini.next();
        if(ordEsec.equals(currentOrdEsec))
          return false;
      }
    }
    return true;
  }

  public AttivitaEsecProdotto getAtvEsecPrdPrimario(AttivitaEsecutiva atvEsec) {
    List attivitaEsecProdList = atvEsec.getProdotti();
    int size = attivitaEsecProdList.size();
    for (int i = 0; i < size; i++) {
      AttivitaEsecProdotto attivitaEsecProd = (AttivitaEsecProdotto) attivitaEsecProdList.get(i);
      if (attivitaEsecProd.getDatiComuniEstesi().getStato() == DatiComuniEstesi.VALIDO)
        if (attivitaEsecProd.getTipoProdotto() == AttivitaEsecProdotto.PRODOTTO_PRIMARIO)
          return attivitaEsecProd;
    }
    return null;
  }
  //Fix 15030 --fine

  //Fix 15217 inizio
  protected AttivitaEsecutiva getAttivitaEsecutiva(int i) {
    AttivitaEsecutiva atv = null;
    RilevDatiPrdTS bo = (RilevDatiPrdTS) getBODataCollector().getBo();
    Class c = Factory.getClass(bo.getClass());
    String getterAnnoOrd = "getIdAnnoOrdine" + String.valueOf(i);
    String getterNumOrd = "getIdNumeroOrdine" + String.valueOf(i);
    String getterRigAtv = "getIdRigaAttivita" + String.valueOf(i);
    try {
      Method methodAnnoOrd = c.getMethod(getterAnnoOrd, null);
      Method methodNumOrd = c.getMethod(getterNumOrd, null);
      Method methodRigAtv = c.getMethod(getterRigAtv, null);
      Object[] keys = new Object[4];
      keys[0] = bo.getIdAzienda();
      keys[1] = (String) methodAnnoOrd.invoke(bo, null);
      keys[2] = (String) methodNumOrd.invoke(bo, null);
      keys[3] = (Integer) methodRigAtv.invoke(bo, null);
      String key = KeyHelper.buildObjectKey(keys);
      atv = AttivitaEsecutiva.elementWithKey(key, PersistentObject.NO_LOCK);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return atv;
  }
  //Fix 15217 fine
  //Fix 15250 inizio
  public String getQuotedString(String str) {
    if (str == null)
      return "null";
    return "\"" + str + "\"";
  }

  public void azioneIndietro(JspWriter out) throws IOException {
    displayRowsMatInIndietro(out);
    out.println("<script language='JavaScript1.2'>");
    try {
      for (int i = 1; i < 21; i++) {
        String getIdMateriale = "getIdMateriale" + String.valueOf(i);
        String getMateriale = "getMateriale" + String.valueOf(i);
        String getIdLotto = "getIdLotto" + String.valueOf(i);
        String getIdIdEsternoConfig = "getIdEsternoConfig" + String.valueOf(i);
        String getIdVersione = "getIdVersione" + String.valueOf(i);
        String getQtaPrlUmPrm = "getQtaPrlUmPrm" + String.valueOf(i);
        String getQtaPrlUmSec = "getQtaPrlUmSec" + String.valueOf(i);
        Class c = Factory.getClass(this.getBODataCollector().getBo().getClass());
        Method idMatMethod = c.getMethod(getIdMateriale, null);
        Method matMethod = c.getMethod(getMateriale, null);
        Method idLotMethod = c.getMethod(getIdLotto, null);
        Method idConfigMethod = c.getMethod(getIdIdEsternoConfig, null);
        Method idVersMethod = c.getMethod(getIdVersione, null);
        Method qtaPrelUmPrmMethod = c.getMethod(getQtaPrlUmPrm, null);
        Method qtaPrelUmSecMethod = c.getMethod(getQtaPrlUmSec, null);

        Object objIdMat = idMatMethod.invoke(this.getBODataCollector().getBo(), null);
        Object objMat = matMethod.invoke(this.getBODataCollector().getBo(), null);
        Object objIdLot = idLotMethod.invoke(this.getBODataCollector().getBo(), null);
        Object objIdConfig = idConfigMethod.invoke(this.getBODataCollector().getBo(), null);
        Object objIdVers = idVersMethod.invoke(this.getBODataCollector().getBo(), null);
        Object objQtaPrlUmPrm = qtaPrelUmPrmMethod.invoke(this.getBODataCollector().getBo(), null);
        Object objQtaPrlUmSec = qtaPrelUmSecMethod.invoke(this.getBODataCollector().getBo(), null);
        if (objIdMat != null) {
          String idMat = (String) objIdMat;
          out.println("document.getElementById('IdMateriale" + i + "').value = '" + idMat + "';");
          if (objQtaPrlUmPrm != null) {
        	BigDecimal qtaPrelUmPrm = (BigDecimal) objQtaPrlUmPrm; //Fix 20491
            String qtaPrelUmPrmStr = Utils.replace(qtaPrelUmPrm.toString(), ".", ","); //Fix 20491
            out.println("document.getElementById('QtaPrelevataUMPrm" + i + "').value = '" + WebElement.formatStringForHTML(qtaPrelUmPrmStr) + "';"); //Fix 20491
            out.println("document.getElementById('QtaPrelevataUMPrm" + i + "').style.background = mCo;");
            out.println("document.getElementById('QtaPrelevataUMPrm" + i + "').typeNameJS.mandatory=true;");

          }
          if (objQtaPrlUmSec != null) {
        	BigDecimal qtaPrelUmSec = (BigDecimal) objQtaPrlUmSec;//Fix 20491
            String qtaPrelUmSecStr = Utils.replace(qtaPrelUmSec.toString(), ".", ",");//Fix 20491
            out.println("document.getElementById('QtaPrelevataUMSec" + i + "').value = '" + WebElement.formatStringForHTML(qtaPrelUmSecStr) + "';");//Fix 20491
          }
          Articolo art = (Articolo) objMat;
          if (art.isArticLotto()) {
        	  //Fix 36033 - inizio
//        	  out.println("document.getElementById('IdLotto" + i + "').style.background =mCo;");
//        	  out.println("document.getElementById('IdLotto" + i + "').readOnly = false;");
//        	  out.println("document.getElementById('IdLotto" + i + "').typeNameJS.mandatory = true;");
        	  visualizzaComponenteLottoArtGestLotti(out, i);
        	  //Fix 36033 - fine
          }
          else {
        	  //Fix 36033 - inizio
//        	  out.println("document.getElementById('IdLotto" + i + "').style.background =bCo;");
//        	  out.println("document.getElementById('IdLotto" + i + "').typeNameJS.mandatory = false;");
//        	  out.println("document.getElementById('IdLotto" + i + "').readOnly = true;");
        	  visualizzaComponenteLottoArtNonGestLotti(out, i);
        	  //Fix 36033 - fine
          }
        }
        if (objIdVers != null) {
          Integer idVers = (Integer) objIdVers;
          out.println("document.getElementById('IdVersione" + i + "').value = '" + idVers + "';");
        }

        if (objIdConfig != null) {
          String idConf = (String) objIdConfig;
          out.println("document.getElementById('IdEsternoConfig" + i + "').value = '" + idConf + "';");
        }
        if (objIdLot != null) {
          String idLot = (String) objIdLot;
          out.println("document.getElementById('IdLotto" + i + "').value = '" + idLot + "';");
        }
        if(((RilevDatiPrdTS)this.getBODataCollector().getBo()).getTipoBolla() == RilevazioneDatiProdTes.BOLLA_CUCITA){
         out.println("enableSearchComponent('Materiale"+i +"', false, false);");
         out.println("enableSearchComponent('MaterialeVersione"+i +"', false, false);");
         out.println("enableSearchComponent('MaterialeConfig"+i +"', false, false);");
       }

      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    out.println("</script>");
  }

  public void displayRowsMatInIndietro(JspWriter out) throws IOException {
    out.println("<script language='JavaScript1.2'>");
    setTitle(out, prelieviTitle);
    out.println("action = 'INDIETRO';");

    try {
      for (int i = 2; i < 21; i++) {
        String getIdMateriale = "getIdMateriale" + String.valueOf(i);
        Class c = Factory.getClass(this.getBODataCollector().getBo().getClass());
        Method idMatMethod = c.getMethod(getIdMateriale, null);
        Object objIdMat = idMatMethod.invoke(this.getBODataCollector().getBo(), null);
        if (objIdMat != null) {
          out.println("document.getElementById('TR" + i + "_1').style.display=displayBlock;");
          out.println("document.getElementById('TR" + i + "_2').style.display=displayBlock;");
          out.println("lastCurrentLine++;");
        }
        else {
          out.println("document.getElementById('TR" + i + "_1').style.display=displayNone;");
          out.println("document.getElementById('TR" + i + "_2').style.display=displayNone;");
        }
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    out.println("</script>");

  }
  //Fix 15250 fine
  //Fix 16741 inizio
  public List getErroriInCaricamentoFermo(RilevDatiPrdTS bo) {
    List errorList = new ArrayList();
    if (bo.getTipoTimbratura() == RilevazioneDatiProdRig.INIZIO) {
      RilevazioneDatiProdTes testata = bo.cercaRilevazioneInCorsoFermo();
      if (testata != null) {
        errorList.add(new ErrorMessage("THIP40T245"));
      }
    }
    else if ((bo.getTipoTimbratura() == RilevazioneDatiProdRig.SOSPENSIONE ||
              bo.getTipoTimbratura() == RilevazioneDatiProdRig.FINE) &&
             !bo.getPersDatiPrdCausaleRilev().isAbilitaDichRidotta()) {
      RilevazioneDatiProdTes testata = bo.cercaRilevazioneInCorsoFermo();
      if (testata == null) {
        errorList.add(new ErrorMessage("THIP40T246"));
      }
    }
    else if (bo.getTipoTimbratura() == RilevazioneDatiProdRig.RIPRESA) {
      RilevazioneDatiProdTes testata = bo.cercaRilevazioneSospesaFermo();
      if (testata == null) {
        errorList.add(new ErrorMessage("THIP40T247"));
      }
    }
    return errorList;
 }//Fix 16741 fine

  //Fix 18536 Inizio
  public String salvaInSessione(ServletEnvironment se) {
    if(se == null)
      return null;

    String iChiaveInSessione = (String)se.getRequest().getAttribute(AzionePaginaTS.CHIAVE_DATI_SESSIONE);
    if(iChiaveInSessione == null)
    	iChiaveInSessione = BaseServlet.getStringParameter(se.getRequest(),AzionePaginaTS.CHIAVE_DATI_SESSIONE);

    if(iChiaveInSessione == null)
      iChiaveInSessione = se.getUser().getId() + "-" + "RilevazioneTS" + "-" + new Date().getTime();

    se.getRequest().setAttribute(AzionePaginaTS.CHIAVE_DATI_SESSIONE, iChiaveInSessione);
    return iChiaveInSessione;
  }

  public boolean abilitaInitRigheProdotti(RilevDatiPrdTS bo){
    String actionPage = (String) getRequest().getAttribute("ActionPage");
    if(actionPage != null && !actionPage.equals("")){
      if(actionPage.equals(RilevDatiPrdTSFormActionAdapter.PAG_SUCC) && iTotaliRigheInitial < 20)
        return false;
      else if(actionPage.equals(RilevDatiPrdTSFormActionAdapter.PAG_PREC) && bo != null){
        if(bo.getCurrentNumPag() > 1 || (!bo.getProdotti().isEmpty() && bo.getProdotti().size() >= 20))
          return false;
      }
    }
    return true;
  }

  public void caricaIndex(JspWriter out, RilevDatiPrdTS rilevDatiPrdTS){
    try{
      int index = 1;
      String action = (String)getRequest().getAttribute("Action");
      String azione = (String)getRequest().getAttribute("Azione");
      if(action != null &&
         (action.equals(RilevDatiPrdTSFormActionAdapter.PAG_PREC) ||
          action.equals(RilevDatiPrdTSFormActionAdapter.PAG_SUCC)))
      {
        if(action.equals(RilevDatiPrdTSFormActionAdapter.PAG_SUCC)){
          if(azione != null && azione.equals(VERSAMENTI)){
            for(int i=1; i<21; i++){
              Class c=Factory.getClass(rilevDatiPrdTS.getClass());
              String getterPrd="getIdProdotto"+String.valueOf(i);
              Method prdMethod=c.getMethod(getterPrd,null);
              String idPrd=(String)prdMethod.invoke(rilevDatiPrdTS,null);
              if(idPrd!=null && i != 1)
                index++;
            }
          }
          else if(azione != null && azione.equals(PRELIEVI)){
            for(int i=1; i<21; i++){
              Class c=Factory.getClass(rilevDatiPrdTS.getClass());
              String getterMat="getIdMateriale"+String.valueOf(i);
              Method matMethod=c.getMethod(getterMat,null);
              String idMat=(String)matMethod.invoke(rilevDatiPrdTS,null);
              if(idMat!=null && i != 1)
                index++;
            }
          }
        }
        else if(action.equals(RilevDatiPrdTSFormActionAdapter.PAG_PREC)){
          index = 20;
        }
        out.println("<script language='JavaScript1.2'>");
        out.println("inizialeTotRighe = " + (index >= 20 ? 21 : index) + ";");
        out.println("firstCurrentLine = " + (index >= 20 ? 21 : index) + ";");
        out.println("lastCurrentLine = " + (index >= 20 ? 21 : index) + ";");
        out.println("action = '" + action + "';");
        out.println("</script>");
      }
    }
    catch(Exception e){e.printStackTrace(Trace.excStream);}
  }
  //Fix 18536 Fine
	
	
	//Fix 22513 - inizio
	protected String getHTMLCellaDichInCorsoPers01(int index, RilevazioneDatiProdTes testata) {
		return "";
	}
	
	
	protected String getHTMLCellaDichInCorsoPers02(int index, RilevazioneDatiProdTes testata) {
		return "";
	}
	
	
	protected String getHTMLCellaDichInCorsoPers03(int index, RilevazioneDatiProdTes testata) {
		return "";
	}
	
	
	protected String getHTMLCellaDichInCorsoPers04(int index, RilevazioneDatiProdTes testata) {
		return "";
	}
	
	
	protected String getHTMLCellaDichInCorsoPers05(int index, RilevazioneDatiProdTes testata) {
		return "";
	}
	
	
	protected String getHTMLCellaProssimeDichPers01(int index, ListaAttivita listaAttivita) {
		return "";
	}

	
	protected String getHTMLCellaProssimeDichPers02(int index, ListaAttivita listaAttivita) {
		return "";
	}

	
	protected String getHTMLCellaProssimeDichPers03(int index, ListaAttivita listaAttivita) {
		return "";
	}

	
	protected String getHTMLCellaProssimeDichPers04(int index, ListaAttivita listaAttivita) {
		return "";
	}

	
	protected String getHTMLCellaProssimeDichPers05(int index, ListaAttivita listaAttivita) {
		return "";
	}
	//Fix 22513 - fine
	
	// Fix 42131 - inizio
	protected String getHTMLPulsantiDichInCorsoRilevInCorsoPers(int index, RilevazioneDatiProdTes testata, String style) {
		return "";
	}
	// Fix 42131 - fine

	//Fix 23916 inizio
	//Fix 24042 inizio
	/*
	public AttivitaEsecutiva getAtvPrecNoAuto(AttivitaEsecutiva atvEsec) {
		AttivitaEsecutiva attivita = atvEsec.getAtvEsecPrecedente();
		if (attivita != null) {
			if (attivita.getPoliticaConsAttivita() != AttivitaEsecutiva.AUTOMATICA)
				return attivita;
			else
				attivita = getAtvPrecNoAuto(attivita);
		}
		return attivita;
	}
	*/
	//Fix 24042 fine
    //Fix 23916 fine
	
  //Fix 24177 inizio
  public void dispalyBtnSucc(RilevDatiPrdTS bo, JspWriter out) throws IOException {
    PersDatiPrdCausaleRilev causale = bo.getPersDatiPrdCausaleRilev();
	if (bo.verificaCondizionePrelievi()
		&& causale.getPosizionePrelievo() == PersDatiPrdCausaleRilev.INIZIO /*&& isSuccessivo(bo)*/) {
		out.println("var dispalyBtnSucc = "+ true +";");
	}
  }
  
  public boolean isSuccessivo(RilevDatiPrdTS bo) {
 	 String action = (String) getRequest().getAttribute("Action");
 	 return (!bo.getAttivitaEsecutiva().getMateriali().isEmpty()
 			 		 || !bo.getAttivitaEsecutiva().getProdotti().isEmpty())
 			 		 && action != null && action.equals(RilevDatiPrdTSFormActionAdapter.PRODUZIONE);
  }
  
  public boolean abilitaInitRigheMateriali(RilevDatiPrdTS bo){
    String actionPage = (String) getRequest().getAttribute("ActionPage");
    if(actionPage != null && !actionPage.equals("")){
      if(actionPage.equals(RilevDatiPrdTSFormActionAdapter.PAG_SUCC) && iTotaliRigheInitial < 20)
        return false;
      else if(actionPage.equals(RilevDatiPrdTSFormActionAdapter.PAG_PREC) && bo != null){
        if(bo.getCurrentNumPag() > 1 || (!bo.getMateriali().isEmpty() && bo.getMateriali().size() >= 20))
          return false;
      }
    }
    return true;
  }
  
	public void caricaRigheMateriali(RilevDatiPrdTS bo, JspWriter out) {
		String action = (String) getRequest().getAttribute("Action");
		int i = 1;
		//Fix 32846 -- Inizio
		List listMaterialiValido = new ArrayList() ;
		List tmp = bo.getAttivitaEsecutiva().getMateriali();
		Iterator it = tmp.iterator();
		while(it.hasNext()) {
			AttivitaEsecMateriale atvEsecMat = (AttivitaEsecMateriale) it.next();
			if(atvEsecMat.getStato() == DatiComuniEstesi.VALIDO) {
				listMaterialiValido.add(atvEsecMat);
			}
		}
		//Fix 32846 -- Fine
		//List materiali = verificaMateriali(bo.getAttivitaEsecutiva().getMateriali(), bo.getPersDatiPrdCausaleRilev()); //Fix 32846
		List materiali = verificaMateriali(listMaterialiValido, bo.getPersDatiPrdCausaleRilev()); //Fix 32846
		if (!materiali.isEmpty()) {
			Iterator iterAtvMat = materiali.iterator();
			try {
				while (iterAtvMat.hasNext()) {
					AttivitaEsecMateriale atvEsecMat = (AttivitaEsecMateriale) iterAtvMat.next();
					Object[] result = initRigaDaOrdineEsec(bo, atvEsecMat, i);
					bo = (RilevDatiPrdTS) result[0];
					i = ((Integer) result[1]).intValue();
				}
				out.println("<script language='JavaScript1.2'>");
				out.println("inizialeTotRighe = " + (i - 1) + ";");
				out.println("lastCurrentLine = " + (i - 1) + ";");
				out.println("lastCurrentLine = (lastCurrentLine < 1) ? 1 : lastCurrentLine;");		//Fix 25976
				out.println("totRighe = " + (i - 1) + ";");
				if (bo.getCurrentNumPag() > 1) {
					iTotaliRigheInitial = ((bo.getCurrentNumPag() - 1) * 20) + (i - 1);
					out.println("totRighe = " + iTotaliRigheInitial + ";");
					out.println("inizialeTotRighe = 20;");
					out.println("lastCurrentLine = 20;");
					out.println("totRighe = 20;");
				}
				out.println("</script>");
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			if (action != null && action.equals(RilevDatiPrdTSFormActionAdapter.SUCCESSIVO))
				bo.setCurrentNumPag(1);
		}
		else { //Fix 42153 --inizio
		  try {
			out.println("<script language='JavaScript1.2'>");
			out.println("inizialeTotRighe = " + (i - 1) + ";");
			out.println("lastCurrentLine = " + (i - 1) + ";");
			out.println("lastCurrentLine = (lastCurrentLine < 1) ? 1 : lastCurrentLine;");
			out.println("totRighe = " + (i - 1) + ";");
			if (bo.getCurrentNumPag() > 1) {
				iTotaliRigheInitial = ((bo.getCurrentNumPag() - 1) * 20) + (i - 1);
				out.println("totRighe = " + iTotaliRigheInitial + ";");
				out.println("inizialeTotRighe = 20;");
				out.println("lastCurrentLine = 20;");
				out.println("totRighe = 20;");
			}
			out.println("</script>");
		  }
		  catch (Exception ex) {
			 ex.printStackTrace(Trace.excStream);
		  }
		} //Fix 42153 --fine
	}
	
	public Object[] initRigaDaOrdineEsec(RilevDatiPrdTS bo, AttivitaEsecMateriale atvEsecMat, int i) {
		Object[] result = new Object[2];
		try {
			List lotti = recuperaLottiMat(bo, atvEsecMat);
			Iterator iter = lotti.iterator();
			while (iter.hasNext()) {
				Object[] obj = (Object[]) iter.next();
				String idLotto = (String) obj[0];
				BigDecimal qta = (BigDecimal) obj[1];
				if (qta.compareTo(new BigDecimal(0)) == 0) {
					//qta = null; //Fix 25930 commento
					idLotto = null;
				}
				String setterPrd = "setIdMateriale" + String.valueOf(i);
				String setterVers = "setIdVersione" + String.valueOf(i);
				String setterCfg = "setIdConfigurazione" + String.valueOf(i);
				String setterMag = "setIdMagazzinoPrl" + String.valueOf(i);
				String setterQta = "setQtaPrlUmPrm" + String.valueOf(i);
				String setterQtaSec = "setQtaPrlUmSec" + String.valueOf(i);
				String setterIdLot = "setIdLotto" + String.valueOf(i);
				String setterDescMat = "setDescrizioneMateriale" + String.valueOf(i);
				
				//Fix 26027 inizio
				String setterEster = "setIdEsternoConfig"+String.valueOf(i);
				//Fix 26027 fine
				
				//72267 Softre
				String setterYQtaOrigPrm = "setYQtaPrlUmPrmOrig"+String.valueOf(i);
				//72267 Softre

				Class c = Factory.getClass(bo.getClass());
				Method matMethod = c.getMethod(setterPrd, new Class[] { String.class });
				Method versMethod = c.getMethod(setterVers, new Class[] { Integer.class });
				Method cfgMethod = c.getMethod(setterCfg, new Class[] { Integer.class });
				Method magMethod = c.getMethod(setterMag, new Class[] { String.class });
				Method qtaMethod = c.getMethod(setterQta, new Class[] { BigDecimal.class });
				Method qtaSecMethod = c.getMethod(setterQtaSec, new Class[] { BigDecimal.class });
				Method idLotMethod = c.getMethod(setterIdLot, new Class[] { String.class });
				Method descMatMethod = c.getMethod(setterDescMat, new Class[] { String.class });
				
				//Fix 26027 inizio
				Method EsternMethod = c.getMethod(setterEster, new Class[] { String.class });
				EsternMethod.invoke(bo, new Object[] { atvEsecMat.getIdEsternoConfig()});
				//Fix 26027 fine
				
				//72267 Softre
				Method YQtaOrigPrmMethod = c.getMethod(setterYQtaOrigPrm, new Class[] { BigDecimal.class });
				YQtaOrigPrmMethod.invoke(bo, new Object[] { qta });
				//72267 Softre
				
				matMethod.invoke(bo, new Object[] { atvEsecMat.getIdArticolo() });
				versMethod.invoke(bo, new Object[] { atvEsecMat.getIdVersione() });
				cfgMethod.invoke(bo, new Object[] { atvEsecMat.getIdConfigurazione() });
				magMethod.invoke(bo, new Object[] { atvEsecMat.getIdMagazzinoPrl() });
				qtaMethod.invoke(bo, new Object[] { qta });
				Articolo articolo = atvEsecMat.getArticolo();
				if (articolo != null && articolo.getIdUMSecMag() != null) {
					BigDecimal qtaSec = articolo.convertiUM(qta, articolo.getUMPrmMag(), articolo.getUMSecMag(),
							atvEsecMat.getVersione());
					if (qtaSec != null) //Fix 39755
	  		            qtaSec = Q6Calc.get().setScale(qtaSec, 2, BigDecimal.ROUND_HALF_UP); //Fix 39755
					qtaSecMethod.invoke(bo, new Object[] { qtaSec });
				}
				if (idLotto != null)
					idLotMethod.invoke(bo, new Object[] { idLotto });

				if (atvEsecMat.getDescrizione() != null) {
					descMatMethod.invoke(bo, new Object[] { atvEsecMat.getDescrizione().getDescrizione() });
				}
				i++;
				if (i > 20) {
					bo.setCurrentNumPag((i / 20) + bo.getCurrentNumPag());
					bo.setIdAzienda(bo.getIdAzienda());
					i = 1;
				}
			}
			result[0] = bo;
			result[1] = new Integer(i);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public List recuperaLottiMat(RilevDatiPrdTS bo, AttivitaEsecMateriale materiale) {
		List ret = new ArrayList();
		List lottiOrig = new ArrayList();
		List lottiOrdine = new ArrayList();

		if (materiale == null)
			return new ArrayList();

		List lottiRig = materiale.getLottiMateriali();
		for (int i = 0; i < lottiRig.size(); i++) {
			AttivitaEsecLottiMat lt = (AttivitaEsecLottiMat) lottiRig.get(i);
			if (!lt.getIdLotto().equals(Lotto.LOTTO_DUMMY)) {
				lottiOrig.add(lt.getLotto());
				lottiOrdine.add(lt);
			}
		}
		BigDecimal controlloQta = ZERO;
		if (bo.getQuantita() == null) {
			controlloQta = getQtaMat(materiale);
		}
		else {
			//Fix 25207 --blocco commentato
			/*BigDecimal quantita = bo.getQuantita();
			if (bo.getQtaScarto() != null)
				quantita = quantita.add(bo.getQtaScarto());
			controlloQta = quantita.multiply(materiale.getCoeffImpiegoUMPrm());*/
			//BigDecimal[] qta = DocumentoProduzione.calcolaQuantitaMat(materiale, bo.getQuantita(), bo.getQtaScarto(), bo.getQuantitaSec(), bo.getQtaScartoSec(), false); //Fix 25207 //Fix 39336
			BigDecimal[] qta = DocumentoProduzione.calcolaQuantitaMat(materiale, bo.getQuantita(), bo.getQtaScarto(), bo.getQuantitaSec(), bo.getQtaScartoSec(), true); //Fix 39336
			controlloQta = qta[0]; //Fix 25207
		}
		
		if(bo.getPersDatiPrdCausaleRilev() != null && bo.getPersDatiPrdCausaleRilev().isQtaResiduaInPrelievo()) {
			controlloQta = RilevDatiPrdTS.getQtaResiduaAtvMat(materiale);
		}
		
		//if (controlloQta.equals(ZERO)) {//40311
		if (Utils.areEqual(controlloQta, ZERO)) {//40311
			//Fix 25930 inizio
			Object[] objTmp = new Object[] { "", controlloQta };
			ret.add(objTmp);
			//Fix 25930 fine
			return ret;
		}

		List lotti = proponiLottiAutomatici(materiale, lottiOrig, lottiOrdine, controlloQta);

		if (lotti != null && !lotti.isEmpty()) {
			//for (int j = 0; j < lotti.size() && !controlloQta.equals(ZERO); j++) {//40311
			for (int j = 0; j < lotti.size() && !Utils.areEqual(controlloQta, ZERO); j++) {//40311
				LottiSaldi lt = (LottiSaldi) lotti.get(j);
				BigDecimal qtaLotto = calcolaQtaDisponibileLotto(lt, !lottiOrig.isEmpty(), lottiOrdine);
				if (controlloQta.compareTo(qtaLotto) >= 0) {
					controlloQta = controlloQta.subtract(qtaLotto);
					Object[] obj = new Object[2];
					obj[0] = lt.getIdLotto();
					obj[1] = qtaLotto;
					ret.add(obj);
				}
				else {
					Object[] obj = new Object[2];
					obj[0] = lt.getIdLotto();
					obj[1] = controlloQta;
					controlloQta = ZERO;
					ret.add(obj);
				}
			}
			if (controlloQta.compareTo(ZERO) > 0) {
				Object[] objTmp = new Object[] { Lotto.LOTTO_DUMMY, controlloQta };
				ret.add(objTmp);
			}
		}
		if (lotti != null && lotti.isEmpty()) {
			//Fix 45683 -- Inizio
			if(materiale.getArticolo().getArticoloDatiMagaz().isLottoUnitario()) {
	    	  BigDecimal UNO = new BigDecimal("1");
	    	  while(controlloQta.compareTo(ZERO) > 0) {
	    		Object[] objTmp = new Object[] { "", UNO };
	            ret.add(objTmp);
	            controlloQta = controlloQta.subtract(UNO);	
	    	  }
	    	}
	    	else {
			//Fix 45683 -- Fine
			  Object[] objTmp = new Object[] { "", controlloQta };
			  ret.add(objTmp);
	    	}//Fix 45683
		}
		return ret;
	}
	
	public BigDecimal getQtaMat(AttivitaEsecMateriale materiale) {
		BigDecimal ret = ZERO;
		BigDecimal qtaVrs = null;

		if (materiale == null)
			return ZERO;

		if (materiale.getAttivitaEsecutiva() != null) {
			qtaVrs = ZERO;
			BigDecimal[] qta = getQtaVersata(materiale.getAttivitaEsecutiva());
			if (qta[0] != null && qta[0].compareTo(new BigDecimal("0")) > 0) {
				qtaVrs = qta[0];
			}
			else {
				qtaVrs = ZERO;
			}
		}
		else
			qtaVrs = ZERO;

		//if (qtaVrs != null && !qtaVrs.equals(ZERO)) {//40311
		if (qtaVrs != null && !Utils.areEqual(qtaVrs, ZERO)) {//40311
			ret = qtaVrs.multiply(materiale.getCoeffImpiegoUMPrm());
		}
		return ret;
	}
	
	public BigDecimal[] getQtaVersata(AttivitaEsecutiva atv) {
		String where = "";
		String orderBy = "";
		BigDecimal[] Qta = new BigDecimal[] {ZERO, ZERO};
		try {
			where = getWhere(atv);
			orderBy = getOrderBy();
			Vector vect = DocumentoProduzione.retrieveList(where, orderBy, false);
			if (!vect.isEmpty()) {
				DocumentoProduzione docVrs = (DocumentoProduzione) vect.get(0);
				Qta[0] = docVrs.getQuantita();
				Qta[1] = docVrs.getQtaSec();
				if (Qta[0] != null && docVrs.getQtaScarto() != null)
					Qta[0] = Qta[0].add(docVrs.getQtaScarto());
				if (Qta[1] != null && docVrs.getQtaScartoSec() != null)
					Qta[1] = Qta[1].add(docVrs.getQtaScartoSec());
			}
		}
		catch (Exception ex) {
			ex.printStackTrace(Trace.excStream);
		}
		return Qta;
	}

	public String getWhere(AttivitaEsecutiva atv) {
		Database db = ConnectionManager.getCurrentDatabase();
		String where = DocumentoProduzioneTM.ID_AZIENDA + " = '" + atv.getIdAzienda() + "' AND "
				+ DocumentoProduzioneTM.R_ANNO_ORDINE + " = '" + atv.getOrdineEsecutivo().getIdAnnoOrdine() + "' AND "
				+ DocumentoProduzioneTM.R_NUMERO_ORD + " = '" + atv.getOrdineEsecutivo().getIdNumeroOrdine() + "' AND "
				+ DocumentoProduzioneTM.DATA_REG + " = " + db.getLiteral(TimeUtils.getCurrentDate());
		return where;
	}

	public String getOrderBy() {
		String orderBy = DocumentoProduzioneTM.TIMESTAMP_CRZ + DESCENDING;
		return orderBy;
	}
	
	public List proponiLottiAutomatici(AttivitaEsecMateriale materiale, List lottiOrig, List lottiOrdine, BigDecimal qtaMat) {
    List lotti = new ArrayList();

    char modPrlLotto = materiale.getArticolo().getArticoloDatiMagaz().getModPrelievoLotto();
    char propostaLotto = materiale.getArticolo().getArticoloDatiMagaz().getPropostaLotto();

    if(modPrlLotto == ArticoloDatiMagaz.NON_SIGNIFICATIVO || modPrlLotto == ArticoloDatiMagaz.MANUALE)
      //return lotti; //Fix 26027
    	return controllaLottiOrdine(materiale, modPrlLotto, propostaLotto, lottiOrig, lottiOrdine, qtaMat); //Fix 26027

    if(propostaLotto == ArticoloDatiMagaz.NON_SIGNIFICATIVO)
      //return lotti; //Fix 26027
    	return controllaLottiOrdine(materiale, modPrlLotto, propostaLotto, lottiOrig, lottiOrdine, qtaMat); //Fix 26027

    if(lottiOrig.isEmpty()) {
      lotti = scorriLottiEsistenti(materiale, modPrlLotto, propostaLotto, "", lottiOrig, lottiOrdine, qtaMat);
    }
    else {
      lotti = controllaLottiOrdine(materiale, modPrlLotto, propostaLotto, lottiOrig, lottiOrdine, qtaMat);
    }

    return lotti;
  }
	
	public List scorriLottiEsistenti(AttivitaEsecMateriale materiale, char modPrlLotto, char propostaLotto, String finalWhere, List lottiOrig, List lottiOrdine, BigDecimal qtaMat) {
    List lotti = new ArrayList();
    if(materiale == null)
      return lotti;

    try {
      String orderBy = costruisciOrderBy(propostaLotto);
      String where = costruisciWhere(materiale.getIdAzienda(), materiale.getIdMagazzinoPrl(), materiale.getIdArticolo(), materiale.getIdVersione(), calcolaCodConfig(materiale.getIdEsternoConfig())) + finalWhere;

      //Vector lottiDisponibili = new Vector();//Fix 34100
      PersistentObjectCursor cursor = null; //Fix 34100

      char tipoSaldo = trovaTipoSaldo(materiale);

      switch (tipoSaldo) {
        case ProposizioneAutLotto.SALDO_COMMESSA:
          //lottiDisponibili = leggiLottiSaldiCommessa(where, orderBy, materiale);//Fix 34100
          cursor = leggiLottiSaldiCommessaCursor(where, orderBy, materiale);//Fix 34100
          break;
        case ProposizioneAutLotto.SALDO_CLIENTE:
          //lottiDisponibili = leggiLottiSaldiCliente(where, orderBy, materiale);//Fix 34100
          cursor = leggiLottiSaldiClienteCursor(where, orderBy, materiale); //Fix 34100
          break;
        case ProposizioneAutLotto.SALDO_LOTTO:
          //lottiDisponibili = leggiLottiSaldi(where, orderBy);//Fix 34100
          cursor = leggiLottiSaldiCursor(where, orderBy); //Fix 34100	
          break;
      }

      //lotti = identificaLotti(lottiDisponibili, modPrlLotto, lottiOrig, lottiOrdine, qtaMat);//Fix 34100
      //Fix 34100 -- Inizio
      if (cursor != null)
          lotti = identificaLottiFromCursor(cursor, modPrlLotto, lottiOrig, lottiOrdine, qtaMat);
      //Fix 34100 -- Fine
      return lotti;
    }
    catch(Exception ex) {
      ex.printStackTrace(Trace.excStream);
      return lotti;
    }
  }
	
	public static String costruisciOrderBy(char propostaLotto) {
		switch (propostaLotto) {
		case ArticoloDatiMagaz.NUMERO_LOTTO:
			return LottiSaldiTM.ID_LOTTO + ASCENDING;
		case ArticoloDatiMagaz.LIFO:
			return LottiSaldiTM.DTA_APR_LOTTO + DESCENDING;
		case ArticoloDatiMagaz.FIFO:
			return LottiSaldiTM.DTA_APR_LOTTO + ASCENDING;
		case ArticoloDatiMagaz.DATA_SCADENZA:
			return LottiSaldiTM.DTA_SCADEN + ASCENDING;
		default:
			return "";
		}
	}

	public List ordinaLottiOrig(List lottiOrig, char propostaLotto) {
		switch (propostaLotto) {
		case ArticoloDatiMagaz.NUMERO_LOTTO:
			Collections.sort(lottiOrig, new IdLottoComparator());
			break;
		case ArticoloDatiMagaz.LIFO:
			Collections.sort(lottiOrig, new DataApDescLottoComparator());
			break;
		case ArticoloDatiMagaz.FIFO:
			Collections.sort(lottiOrig, new DataApAscLottoComparator());
			break;
		case ArticoloDatiMagaz.DATA_SCADENZA:
			Collections.sort(lottiOrig, new DataScAscLottoComparator());
			break;
		}
		return lottiOrig;
	}

	public static String calcolaCodConfig(String idEsternoConfig) {
		if (idEsternoConfig != null && !idEsternoConfig.equals(""))
			return idEsternoConfig;
		return SaldoMag.OPERAZIONE_DUMMY;
	}

	public static String costruisciWhere(String idAz, String idMag, String idArt, Integer idVrs, String idConfig) {
	  return LottiSaldiTM.ID_AZIENDA + " = '" + idAz + "' AND " +
	  			 LottiSaldiTM.ID_MAGAZZINO + " = '" + idMag + "' AND " +
	  			 LottiSaldiTM.ID_ARTICOLO + " = '" + idArt + "' AND " +
	  			 LottiSaldiTM.ID_VERSIONE + " = " + idVrs + " AND " +
	  			 LottiSaldiTM.ID_OPERAZIONE + " = '" + SaldoMag.OPERAZIONE_DUMMY + "' AND " +
	  			 LottiSaldiTM.ID_LOTTO + " <> '" + Lotto.LOTTO_DUMMY + "' AND " +
	  			 LottiSaldiTM.COD_CONFIG + " = '" + idConfig + "'";
	}
	
	public class IdLottoComparator implements Comparator{
	   public int compare(Object o1, Object o2){
	     Lotto lt1 = (Lotto)o1;
	     Lotto lt2 = (Lotto)o2;
	     String idLotto1 = lt1.getCodiceLotto();
	     String idLotto2 = lt2.getCodiceLotto();
	     int result = Utils.compare(idLotto1, idLotto2);
	     return result;
	   }
	 }

	 public class DataApAscLottoComparator implements Comparator{
	   public int compare(Object o1, Object o2){
	     Lotto lt1 = (Lotto)o1;
	     Lotto lt2 = (Lotto)o2;
	     java.sql.Date dataApr1 = lt1.getDataApertura();
	     java.sql.Date dataApr2 = lt2.getDataApertura();
	     int result = Utils.compare(dataApr1, dataApr2);
	     return result;
	   }
	 }

	 public class DataApDescLottoComparator implements Comparator{
	   public int compare(Object o1, Object o2){
	     Lotto lt1 = (Lotto)o1;
	     Lotto lt2 = (Lotto)o2;
	     java.sql.Date dataApr1 = lt1.getDataApertura();
	     java.sql.Date dataApr2 = lt2.getDataApertura();
	     int result = Utils.compare(dataApr1, dataApr2);
	     if(result == +1)
	       result = -1;
	     else if(result == -1)
	       result = +1;
	     return result;
	   }
	 }

	 public class DataScAscLottoComparator implements Comparator{
	   public int compare(Object o1, Object o2){
	     Lotto lt1 = (Lotto)o1;
	     Lotto lt2 = (Lotto)o2;
	     java.sql.Date dataScad1 = lt1.getDataScadenza();
	     java.sql.Date dataScad2 = lt2.getDataScadenza();
	     int result = Utils.compare(dataScad1, dataScad2);
	     return result;
	   }
	 }
	
	 public BigDecimal calcolaQtaDisponibileLotto(LottiSaldi lotto, boolean esisteLottoOrdine,  List lottiOrdine) {
	    BigDecimal qtaDisp = ZERO;
	    if(lotto == null)
	      return qtaDisp;

	    BigDecimal qtaLtOrdine = ZERO;

	    if(esisteLottoOrdine) {
	      for(int i = 0; i < lottiOrdine.size(); i++) {
	        AttivitaEsecLottiMat lt = (AttivitaEsecLottiMat)lottiOrdine.get(i);
	        if(lt.getIdLotto().equals(lotto.getIdLotto())) {
	          qtaLtOrdine = controllaBD(lt.getQtaResiduaUMPrm());
	          break;
	        }
	      }
	    }

	    BigDecimal giacenza = controllaBD(lotto.getQtaGiacPrm());
	    BigDecimal entrate = controllaBD(lotto.getQtaEntPrm());
	    BigDecimal uscite = controllaBD(lotto.getQtaUscPrm());
	    BigDecimal accantonata = controllaBD(lotto.getQtaAccantManPrm());
	    BigDecimal prenotata = controllaBD(lotto.getQtaPrenotManPrm());
	    BigDecimal impegni = controllaBD(lotto.getQtaImnPrm());

	    BigDecimal EEntrate = entrate;
	    BigDecimal EUscite = uscite.add(accantonata).add(prenotata);
	    BigDecimal EImpegni = impegni.subtract(qtaLtOrdine);

	    qtaDisp = giacenza.add(EEntrate).subtract(EUscite).subtract(EImpegni);

	    if(qtaDisp.compareTo(UNO) > 0) {
	      try {
	        String idArt = lotto.getIdArticolo();
	        String idAzienda = lotto.getIdAzienda();
	        String artKey = KeyHelper.buildObjectKey(new String[] {idAzienda, idArt});
	        Articolo art = Articolo.elementWithKey(artKey, PersistentObject.NO_LOCK);
	        if(art != null && art.getArticoloDatiMagaz().isLottoUnitario())
	          return UNO;
	      }
	      catch(SQLException ex) {
	        ex.printStackTrace(Trace.excStream);
	        return ZERO;
	      }
	    }
	    return qtaDisp;
	  }
	 
	 public List controllaLottiOrdine(AttivitaEsecMateriale materiale, char modPrlLotto, char propostaLotto, List lottiOrig, List lottiOrdine, BigDecimal qtaDaPrelevare) {
	    List lottiGiusti = new ArrayList();
	    boolean find = false;
	    String completeWhere = "";

	    try {
	      String where = costruisciWhere(materiale.getIdAzienda(), materiale.getIdMagazzinoPrl(), materiale.getIdArticolo(), materiale.getIdVersione(), calcolaCodConfig(materiale.getIdEsternoConfig()));
	      List lottiOrigOrdinati = ordinaLottiOrig(lottiOrig, propostaLotto);

	      for (int i = 0; i < lottiOrigOrdinati.size(); i++) {
	        Lotto lt = (Lotto)lottiOrigOrdinati.get(i);
	        String endWhere = " AND " + LottiSaldiTM.ID_LOTTO + " = '" + lt.getCodiceLotto() + "'";
	        completeWhere += " AND " + LottiSaldiTM.ID_LOTTO + " <> '" + lt.getCodiceLotto() + "'";;
	        //List lottisaldi = new ArrayList(); //Fix 34100
	        PersistentObjectCursor cursor = null; //Fix 34100

	        char tipoSaldo = trovaTipoSaldo(materiale);
	        switch (tipoSaldo) {
	          case ProposizioneAutLotto.SALDO_COMMESSA:
	            //lottisaldi = leggiLottiSaldiCommessa(where + endWhere, "", materiale); //Fix 34100
	            cursor = leggiLottiSaldiCommessaCursor(where + endWhere, "", materiale); //Fix 34100
	            break;
	          case ProposizioneAutLotto.SALDO_CLIENTE:
	            //lottisaldi = leggiLottiSaldiCliente(where + endWhere, "", materiale); //Fix 34100
	            cursor = leggiLottiSaldiClienteCursor(where + endWhere, "", materiale); //Fix 34100
	            break;
	          case ProposizioneAutLotto.SALDO_LOTTO:
	            //lottisaldi = leggiLottiSaldi(where + endWhere, ""); //Fix 34100
	            cursor = leggiLottiSaldiCursor(where + endWhere, ""); //Fix 34100
	            break;
	        }

	        LottiSaldi lotto = null;
	        //Fix 34100 -- Inizio
	        /*if(!lottisaldi.isEmpty()) {
	          lotto = (LottiSaldi)lottisaldi.get(0);
	        }
	        else
	          continue;*/
	        
	        try {
	          if (cursor != null && cursor.hasNext())
	            lotto = (LottiSaldi) cursor.next();          
	          else
	            continue;
	        }
	        catch (SQLException sqle) {
	          throw sqle;
	        }
	        finally {
	          if (cursor != null)
	            cursor.free();
	        }
	        //Fix 34100 -- Fine

	        BigDecimal qtaDispLotto = calcolaQtaDisponibileLotto(lotto, !lottiOrig.isEmpty(), lottiOrdine);
  	        //Fix 27778 inizio
	        /*if(qtaDispLotto.compareTo(ZERO) > 0) {
	          lottiGiusti.add(lotto);
	          qtaDaPrelevare = qtaDaPrelevare.subtract(qtaDispLotto);
	          if(qtaDaPrelevare.compareTo(ZERO) <= 0) {
	            find = true;
	            break;
	          }
	        }*/
	        if(modPrlLotto == ArticoloDatiMagaz.SINGOLA) {
		        if(qtaDispLotto.compareTo(qtaDaPrelevare) >= 0){
		          lottiGiusti.add(lotto);
		          qtaDaPrelevare = qtaDaPrelevare.subtract(qtaDispLotto);
		          find = true;
		          break;
		        }
		     }
		     else /*if (modPrlLotto == ArticoloDatiMagaz.MULTIPLA)*/ { //Fix 30029
		        if(qtaDispLotto.compareTo(ZERO) > 0) {
		          lottiGiusti.add(lotto);
		          qtaDaPrelevare = qtaDaPrelevare.subtract(qtaDispLotto);
		          if(qtaDaPrelevare.compareTo(ZERO) <= 0) {
		            find = true;
		            break;
		          }
		       }
		     }
		     //Fix 27778 fine
	      }

	      if (!find) {
	        materiale.setQtaPrelevataUMPrm(qtaDaPrelevare);
	        lottiGiusti.addAll(scorriLottiEsistenti(materiale, modPrlLotto, propostaLotto, completeWhere, lottiOrig, lottiOrdine, qtaDaPrelevare));
	      }
	    }
	    catch(Exception ex) {
	      ex.printStackTrace(Trace.excStream);
	    }
	    return lottiGiusti;
	  }
	 
	 public char trovaTipoSaldo(AttivitaEsecMateriale materiale) throws Exception {
	    if (materiale.getIdCommessa() != null && !materiale.getIdCommessa().equals("")) {
	      String comKey = materiale.getIdAzienda() + PersistentObject.KEY_SEPARATOR + materiale.getIdCommessa();
	      Commessa commessa = Commessa.elementWithKey(comKey,PersistentObject.NO_LOCK);
	      boolean aggSaldi = commessa.getAggiornamentoSaldi();
	      boolean gesSaldiComm = materiale.getArticolo().getArticoloDatiMagaz().isGesSaldiCommessa();
	      if (aggSaldi && gesSaldiComm) {
	        return ProposizioneAutLotto.SALDO_COMMESSA;
	      }
	    }
	    else if(materiale.getAttivitaEsecutiva() != null && materiale.getAttivitaEsecutiva().getOrdineEsecutivo() != null) {
	      OrdineEsecutivo ordPrd = materiale.getAttivitaEsecutiva().getOrdineEsecutivo();
	      if(ordPrd != null) {
	        String idCliente = ordPrd.getIdCliente();
	        String magKey = materiale.getIdAzienda() + PersistentObject.KEY_SEPARATOR + materiale.getIdMagazzinoPrl();
	        Magazzino magazzino = (Magazzino)PersistentObject.readOnlyElementWithKey(Magazzino.class, magKey);

	        char tipoMag = magazzino.getTipoMagazzino();
	        if(idCliente != null && !idCliente.equals("") && tipoMag == Magazzino.LAVORO_INTERNO)
	          return ProposizioneAutLotto.SALDO_CLIENTE;
	      }
	    }
	    return ProposizioneAutLotto.SALDO_LOTTO;
	  }
	 
	  //Fix 34100
	  /**
	   * @deprecated
	  */
	  public Vector leggiLottiSaldiCommessa(String where, String orderBy, AttivitaEsecMateriale materiale) {
	    String idCommessa = materiale.getIdCommessa();
	    where += " AND " + LottiSaldiComTM.ID_COMMESSA + " = '" + idCommessa + "'";
	    Vector vect = new Vector();
	    if(idCommessa == null)
	      return vect;

	    try {
	      vect = LottiSaldiCom.retrieveList(where, orderBy, false);
	    }
	    catch(Exception ex) {
	      ex.printStackTrace(Trace.excStream);
	    }
	    return vect;
	  }

	  //Fix 34100
	  /**
	   * @deprecated
	  */
	  public Vector leggiLottiSaldiCliente(String where, String orderBy, AttivitaEsecMateriale materiale) {
	    String idCliente = null;
	    if(materiale.getAttivitaEsecutiva() != null && materiale.getAttivitaEsecutiva().getOrdineEsecutivo() != null)
	      idCliente = materiale.getAttivitaEsecutiva().getOrdineEsecutivo().getIdCliente();

	    where += " AND " + LottiSaldiCliTM.ID_CLIENTE + " = '" + idCliente + "'";
	    Vector vect = new Vector();
	    if(idCliente == null)
	      return vect;

	    try {
	      vect = LottiSaldiCli.retrieveList(where, orderBy, false);
	    }
	    catch(Exception ex) {
	      ex.printStackTrace(Trace.excStream);
	    }
	    return vect;
	  }

	  //Fix 34100
	  /**
	   * @deprecated
	  */
	  public Vector leggiLottiSaldi(String where, String orderBy) {
	    Vector vect = new Vector();
	    try {
	      vect = LottiSaldi.retrieveList(where, orderBy, false);
	    }
	    catch(Exception ex) {
	      ex.printStackTrace(Trace.excStream);
	    }
	    return vect;
	  }
	  
	  //Fix 34100
	  /**
	   * @deprecated
	  */
	  public List identificaLotti(Vector lottiDisponibili, char modPrlLotto, List lottiOrig, List lottiOrdine, BigDecimal qtaDaPrelevare) {
	    List lottiGiusti = new ArrayList();

	    for(int i = 0; i < lottiDisponibili.size(); i++) {
	      LottiSaldi lotto = (LottiSaldi)lottiDisponibili.elementAt(i);
	      BigDecimal qtaDispLotto = calcolaQtaGiacenzaNetta(lotto, !lottiOrig.isEmpty(), lottiOrdine);

	      if(modPrlLotto == ArticoloDatiMagaz.SINGOLA) {
	        if(qtaDispLotto.compareTo(qtaDaPrelevare) >= 0) {
	          lottiGiusti.add(lotto);
	          break;
	        }
	      }
	      else if (modPrlLotto == ArticoloDatiMagaz.MULTIPLA) {
	        if(qtaDispLotto.compareTo(ZERO) > 0) {
	          lottiGiusti.add(lotto);
	          qtaDaPrelevare = qtaDaPrelevare.subtract(qtaDispLotto);
	          if(qtaDaPrelevare.compareTo(ZERO) <= 0)
	            break;
	        }
	      }
	    }
	    return lottiGiusti;
	  }
	  
	  public  BigDecimal calcolaQtaGiacenzaNetta(LottiSaldi lotto, boolean esisteLottoOrdine,  List lottiOrdine) {
	    BigDecimal qtaDisp = ZERO;
	    if(lotto == null)
	      return qtaDisp;

	    BigDecimal qtaLtOrdine = ZERO;
	    if(esisteLottoOrdine) {
	      for(int i = 0; i < lottiOrdine.size(); i++) {
	        AttivitaEsecLottiMat lt = (AttivitaEsecLottiMat)lottiOrdine.get(i);
	        if(lt.getIdLotto().equals(lotto.getIdLotto())) {
	          qtaLtOrdine = controllaBD(lt.getQtaResiduaUMPrm());
	          break;
	        }
	      }
	    }

	    qtaDisp = lotto.calcolaGiacenzaNetta().getQuantitaInUMPrm();
	    if(qtaDisp.compareTo(UNO) > 0) {
	      try {
	        String idArt = lotto.getIdArticolo();
	        String idAzienda = lotto.getIdAzienda();
	        String artKey = KeyHelper.buildObjectKey(new String[] {idAzienda, idArt});
	        Articolo art = Articolo.elementWithKey(artKey, PersistentObject.NO_LOCK);
	        if(art != null && art.getArticoloDatiMagaz().isLottoUnitario())
	          return UNO;
	      }
	      catch(SQLException ex) {
	        ex.printStackTrace(Trace.excStream);
	        return ZERO;
	      }
	    }
	    return qtaDisp;
	  }
	  
	public boolean isOldJspNameDichiarazioneVersamenti() {
		String jspName = getRequest().getParameter("thOldJspName");
		return jspName != null && jspName.indexOf("DichiarazioneVersamenti.jsp") >= 0;
	}
	
	public List verificaMateriali(List materiali, PersDatiPrdCausaleRilev persDatiPrdCausaleRilev) {
  	if(persDatiPrdCausaleRilev != null && persDatiPrdCausaleRilev.isAbilitaPrelievo()) {
  		switch (persDatiPrdCausaleRilev.getMaterialiDaProporre()) {
  			case PersDatiPrdCausaleRilev.SOLO_MANUALE :
  				return getSoloManuale(materiali);
  			case PersDatiPrdCausaleRilev.SOLO_AUTOMATICI :
  				return getSoloAutomatici(materiali);
  			case PersDatiPrdCausaleRilev.TUTTI :
  			  return getTutti(materiali);
  			case PersDatiPrdCausaleRilev.NESSUNO : //Fix 42153
	              return new ArrayList(); //Fix 42153
  			default :
  				return materiali;
  		}
  	}
  	return materiali;
  }
	
	public List getSoloManuale(List materiali) {
		List mat = new ArrayList();
		Iterator matIter = materiali.iterator();
		while (matIter.hasNext()) {
			AttivitaEsecMateriale materiale = (AttivitaEsecMateriale)matIter.next();
			if(materiale.getModalitaPrelievo() == AttivitaEsecMateriale.NORMALE)
				mat.add(materiale);
		}
		return mat;
	}
	
	public List getSoloAutomatici(List materiali) {
		List mat = new ArrayList();
		Iterator matIter = materiali.iterator();
		while (matIter.hasNext()) {
			AttivitaEsecMateriale materiale = (AttivitaEsecMateriale)matIter.next();
			if(materiale.getModalitaPrelievo() == AttivitaEsecMateriale.AUTOMATICO_VERSAMENTO
					|| materiale.getModalitaPrelievo() == AttivitaEsecMateriale.AUTOMATICO_AVANZAMENTO)
				mat.add(materiale);
		}
		return mat;
	}
	
	public List getTutti(List materiali) {
		List mat = new ArrayList();
		Iterator matIter = materiali.iterator();
		while (matIter.hasNext()) {
			AttivitaEsecMateriale materiale = (AttivitaEsecMateriale)matIter.next();
			if( materiale.getModalitaPrelievo() == AttivitaEsecMateriale.NORMALE
					||materiale.getModalitaPrelievo() == AttivitaEsecMateriale.AUTOMATICO_VERSAMENTO
					|| materiale.getModalitaPrelievo() == AttivitaEsecMateriale.AUTOMATICO_AVANZAMENTO)
				mat.add(materiale);
		}
		return mat;
	}
	
	public boolean hasErrore() {
		String jspName = (String) getRequest().getParameter("thOldJspName");
		List errori = (List) getRequest().getAttribute("ErroriList");
		return jspName != null && jspName.indexOf("DichiarazioneRidotta.jsp") < 0 && errori != null && !errori.isEmpty();
	}

	public void visualizzaMateriali(RilevDatiPrdTS bo, JspWriter out) {
		int i = 1;
		try {
			for (int j = 0; j < bo.getMateriali().size(); j++) {
				Articolo materiale = (Articolo) bo.getMateriali().get(new Integer(j));
				if (materiale != null && materiale.getIdArticolo() != null) {
					i++;
				}
			}
			out.println("<script language='JavaScript1.2'>");
			out.println("inizialeTotRighe = " + (i - 1) + ";");
			out.println("lastCurrentLine = " + (i - 1) + ";");
			out.println("lastCurrentLine = (lastCurrentLine < 1) ? 1 : lastCurrentLine;");		//Fix 25976
			out.println("totRighe = " + (i - 1) + ";");
			out.println("</script>");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void riempiMatGestLottiArray(RilevDatiPrdTS bo, JspWriter out) {
		try {
			out.println("var matGestLotti = [];");
			for (int i = 0; i < bo.getMateriali().size(); i++) {
				Articolo materiale = (Articolo) bo.getMateriali().get(new Integer(i));
				if (materiale != null && materiale.getIdArticolo() != null && materiale.isArticLotto()) {
					out.println("matGestLotti.push('" + materiale.getIdArticolo() + "');");
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
  //Fix 24177 fine
	
  //Fix 27463 inizio
   public ArticoloVersione getVersione(Articolo articolo, Integer idVersione) {
     List versioni = articolo.getArticoloDatiVersioni().getVersioni();
     ListIterator versioniIt = versioni.listIterator();
     while (versioniIt.hasNext()) {
       ArticoloVersione artVers = (ArticoloVersione) versioniIt.next();
       if (artVers.getIdVersione().equals(idVersione)) {
         return artVers;
       }
     }
     return null;
   }
   //Fix 27463 fine

	// Fix 27513 ini
	// Metodo per permettere la personalizzazione
	public BigDecimal modificaQtaScarto(BigDecimal qtaSca, RilevDatiPrdTS bo, AttivitaEsecProdotto atvEsecPrd) {
		return qtaSca;
	}
	// Fix 27513 fin
	
  //Fix 27957 inizio
  protected BigDecimal calcolaQtaBuonaSec(RilevDatiPrdTS bo) {
    BigDecimal result = null;
    CausaleDocProduzione causale = bo.getPersDatiPrdCausaleRilev().getCausaleDocumento();
    AttivitaEsecutiva attivita = bo.getAttivitaEsecutiva();
    AttivitaEsecutiva attivitaprecedente = attivita.getAtvEsecPrecedente();
    while (attivitaprecedente != null && attivitaprecedente.getPoliticaConsAttivita() == AttivitaEsecutiva.AUTOMATICA)
      attivitaprecedente = attivitaprecedente.getAtvEsecPrecedente();
    if (causale != null) {
      char tipoqtaresproposta = causale.getTipoQtaResProposta();
      if (CausaleDocProduzione.TIPO_QTA_RES_PROPOSTA_STD == tipoqtaresproposta) {
        result = attivita.getQtaResiduaUMSec();
      }
      else if (CausaleDocProduzione.TIPO_QTA_RES_PROPOSTA_DA_ATV == tipoqtaresproposta) {
        if (attivitaprecedente == null)
          result = attivita.getQtaResiduaUMSec();
        else {
          BigDecimal qtaPrdPrecSec = controllaBD(attivitaprecedente.getQtaProdottaUMSec());
          BigDecimal qtaPrdSec = controllaBD(attivita.getQtaProdottaUMSec());
          BigDecimal qtaScaSec = controllaBD(attivita.getQtaScartataUMSec());
          BigDecimal qtaSec = qtaPrdPrecSec.subtract(qtaPrdSec.add(qtaScaSec));
          if (qtaSec.compareTo(ZERO) > 0)
            result = qtaSec;
        }
      }
    }
    return result;
  }
  //Fix 27957 fine
  
  // Fix 29844 inizio
  public List raggruppaMateriali(List materiali) {
	List materialiRaggruppare = new ArrayList();
	int j = materiali.size();
	while (j - 1 >= 0) {
		AttivitaEsecMateriale atvEsecMat1 = (AttivitaEsecMateriale) materiali.get(j - 1);
		boolean exist = false;
		int k = materialiRaggruppare.size();
		if (k == 0) {
			//Fix 33801 inizio
			RilevDatiPrdTS rilevDatiPrdTS = (RilevDatiPrdTS) getRequest().getAttribute("myObject");
			if (rilevDatiPrdTS.getBollaCucita() != null)
			    atvEsecMat1.getAttivitaEsecutiva().setNumeroRitorno(rilevDatiPrdTS.getBollaCucita().getNumeroRitorno());
			//Fix 33801 fine
			materialiRaggruppare.add(atvEsecMat1);
		} else {
			while (k - 1 >= 0) {
				AttivitaEsecMateriale atvEsecMat2 = (AttivitaEsecMateriale) materialiRaggruppare.get(k - 1);
				if (verificaAttivitaMat(atvEsecMat1, atvEsecMat2)) {
					atvEsecMat2.setQtaRichiestaUMPrm(atvEsecMat2.getQtaRichiestaUMPrm().add(atvEsecMat1.getQtaRichiestaUMPrm()));
					atvEsecMat2.setQtaPrelevataUMPrm(controllaBD(atvEsecMat2.getQtaPrelevataUMPrm()).add(controllaBD(atvEsecMat1.getQtaPrelevataUMPrm()))); //Fix 33801
					if (atvEsecMat2.getArticolo().getIdUMSecMag() != null){
						atvEsecMat2.setQtaRichiestaUMSec(atvEsecMat2.getQtaRichiestaUMSec().add(atvEsecMat1.getQtaRichiestaUMSec()));
						atvEsecMat2.setQtaPrelevataUMSec(controllaBD(atvEsecMat2.getQtaPrelevataUMSec()).add(controllaBD(atvEsecMat1.getQtaPrelevataUMSec()))); //Fix 33801
					}
					if (atvEsecMat2.getStatoPrlMateriale() != AttivitaEsecMateriale.INIZIATO && atvEsecMat1.getStatoPrlMateriale() == AttivitaEsecMateriale.INIZIATO) //Fix 33801
                        atvEsecMat2.setStatoPrlMateriale(AttivitaEsecMateriale.INIZIATO); //Fix 33801
					exist = true;
				}
				k--;
			}
			if (!exist) {
				materialiRaggruppare.add(atvEsecMat1);
			}
		}
		j--;
	}
	return materialiRaggruppare;
  }

  public boolean verificaAttivitaMat(AttivitaEsecMateriale atvEsecMat1, AttivitaEsecMateriale atvEsecMat2) {
	if (atvEsecMat1 != null && atvEsecMat2 != null) {
		if (atvEsecMat2.getIdArticolo() != null && atvEsecMat2.getIdConfigurazione() != null
				&& atvEsecMat2.getIdVersione() != null) {
			if (atvEsecMat2.getIdArticolo().equals(atvEsecMat1.getIdArticolo())
					&& atvEsecMat2.getIdConfigurazione().equals(atvEsecMat1.getIdConfigurazione())
					&& atvEsecMat2.getIdVersione().equals(atvEsecMat1.getIdVersione()))
				return true;
			else
				return false;
		} else if (atvEsecMat2.getIdArticolo() != null && atvEsecMat2.getIdConfigurazione() != null) {
			if (atvEsecMat2.getIdArticolo().equals(atvEsecMat1.getIdArticolo())
					&& atvEsecMat2.getIdConfigurazione().equals(atvEsecMat1.getIdConfigurazione()))
				return true;
			else
				return false;
		} else if (atvEsecMat2.getIdArticolo() != null && atvEsecMat2.getIdVersione() != null) {
			if (atvEsecMat2.getIdArticolo().equals(atvEsecMat1.getIdArticolo())
					&& atvEsecMat2.getIdVersione().equals(atvEsecMat1.getIdVersione()))
				return true;
			else
				return false;
		} else if (atvEsecMat2.getIdArticolo() != null) {
			if (atvEsecMat2.getIdArticolo().equals(atvEsecMat1.getIdArticolo()))
				return true;
			else
				return false;
		}
	}
	return false;
  }
  // Fix 29844 fine 
  
  //Fix 30440 -- inizio
  public void enableDataFine(JspWriter out, RilevDatiPrdTS bo) throws IOException {
	if(bo.getPersDatiPrdCausaleRilev().isModificaOraTS()){
	   out.println("if(eval('document.forms[0].' + idFromName['DataFine']) != undefined ){");
	   out.println(" eval('document.forms[0].' + idFromName['DataFine']).readOnly = false;");
	   out.println(" eval('document.forms[0].' + idFromName['DataFine']).style.background = mCo;");
	   out.println("}"); 
	}
	else{
	   out.println("if(eval('document.forms[0].' + idFromName['DataFine']) != undefined ){");
	   out.println(" eval('document.forms[0].' + idFromName['DataFine']).readOnly = true;");
	   out.println(" eval('document.forms[0].' + idFromName['DataFine']).style.background = bCo;");
	   out.println("}"); 
	}
  }
  //Fix 30440 -- fine
  
  //Fix 30572 -- inizio 
  public void displayProssemeDichiarazioneBollaCucita(JspWriter out, RilevDatiPrdTS bo, int index) throws IOException {
	List prossemeDichiarazioneList = new ArrayList();
	prossemeDichiarazioneList = bo.getListBollaCucitaNonIniziata(bo.getIdOperatore());
	index = displayProssimeDichInternalBollaCucita(out, prossemeDichiarazioneList, "it/thera/thip/produzione/raccoltaDati/images/Blue.gif", bo, index); 	
  }
  
  public int displayProssimeDichInternalBollaCucita(JspWriter out, List prossimeDich, String srcImg, RilevDatiPrdTS bo, int index) throws IOException{ 
    String but = ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "ButInizio"); 
    String butDettaglio = ResourceLoader.getString(RilevDatiPrdTS.RES_FILE, "ButDettaglio");
    String width = "\"width:117px\"";
    if(bo.getPersDatiPrdUtenteRilev().getRisoluzioneVideo()== PersDatiPrdUtenteRilev.RISOL_800_600) {
    	width = "\"width:100px\"" ;
    }
    Iterator iter = prossimeDich.iterator();
    while (iter.hasNext()) {
      BollaCucitaTes bollaCucita = (BollaCucitaTes) iter.next();
      out.println("    <tr>");
      out.println("    <td class=\"cell\"><img type=\"image\" name=\"\" src=\"" + srcImg + "\" alt=\"\" /><br>&nbsp</td>"); 
      String bollaLav = bollaCucita.getNumeroRitorno();
      String descrRilev = "";
      String numOrdine = bollaCucita.getNumeroBolFmt();
      String idArticolo = "";
      String descrArticolo = "";
      String idMacchina = "";
      String descMAcchina = null;

      out.println("");
      out.println("    <td id=\"BollaLavTD" + index + "\" class=\"cell\" >" + WebElement.formatStringForHTML(bollaLav) + "<br>" + WebElement.formatStringForHTML(descrRilev) + "</td>");
      out.println("");
      out.println("    <td class=\"cell\" >" + WebElement.formatStringForHTML(numOrdine) + "</td>");
	  out.println("");
	  out.println("    <td class=\"cell\" ><button onclick=\"setCurrentEvent(event);dettaglioAction(" + index + ")\" style="+ width +">" + butDettaglio + "</button></td>");
	  out.println("");
	  if(bo.getIdMacchina() == null)
		  out.println("    <td class=\"cell\" nowrap=\"true\" >" + WebElement.formatStringForHTML("") + "<br>" + WebElement.formatStringForHTML("") + "</td>");
	  out.println("    <td class=\"cell\" nowrap=\"true\" >" + "" + " " + WebElement.formatStringForHTML("") + "<br>&nbsp</td>");
	  out.println("");
	  out.println("    <td class=\"cell\" ><button onclick=\"setCurrentEvent(event);inizioAction(" + index + ")\" style=\"width: 117px\">" + but + "</button></td>");
	  
	  out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdCausaleRilevazione" + index + "\" value='" + WebElement.formatStringForHTML(bo.getIdCausaleRilevazione()) + "' /></td>");
	  out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"BollaLav" + index + "\" value='" + WebElement.formatStringForHTML(bollaLav) + "' /></td>");
	  out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdArticolo" + index + "\" value='" + WebElement.formatStringForHTML("") + "' /></td>");
	  out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdAnnoOrdine" + index + "\" value='" + WebElement.formatStringForHTML("") + "' /></td>");
	  out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdNumeroOrdine" + index + "\" value='" + WebElement.formatStringForHTML("") + "' /></td>");
	  out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdRigaAttivita" + index + "\" value='" + WebElement.formatStringForHTML("") + "' /></td>");
	  out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdAnnoBolla" + index + "\" value='" + WebElement.formatStringForHTML(bollaCucita.getIdAnnoBolla()) + "' /></td>");
      out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdNumeroBolla" + index + "\" value='" + WebElement.formatStringForHTML(bollaCucita.getIdNumeroBolla()) + "' /></td>");
      out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"TipoBolla" + index + "\" value='" + WebElement.formatStringForHTML(""+RilevazioneDatiProdTes.BOLLA_CUCITA) + "' /></td>");
      if(bollaCucita.getLivelloRisorsa() == Risorsa.MATRICOLA) {
	   out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"TipoRisorsa" + index + "\" value='" + (bollaCucita.getTipoRisorsa()) + "' /></td>");
	   out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"LivelloRisorsa" + index + "\" value='" + (bollaCucita.getLivelloRisorsa()) + "' /></td>");
	   out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdRisorsa" + index + "\" value='" + WebElement.formatStringForHTML(bollaCucita.getIdRisorsa()) + "' /></td>");
      }
      else {
   	   out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"TipoRisorsa" + index + "\" value='" + Risorsa.MACCHINE + "' /></td>");
   	   out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"LivelloRisorsa" + index + "\" value='" + Risorsa.MATRICOLA + "' /></td>");
   	   out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdRisorsa" + index + "\" value='' /></td>");  
      }
	  out.println("    <td class=\"cell\" style=\"display:none\"><input type =\"text\" id=\"IdAmbiente" + index + "\" value='" + WebElement.formatStringForHTML(bo.getIdAmbiente()) + "' /></td>");
	  out.println("   </tr>");
	  index++;
	}
	return index; 
  }
  //Fix 30572 -- fine
  
  //Fix 34100 -- Inizio
  public PersistentObjectCursor leggiLottiSaldiCommessaCursor(String where, String orderBy, AttivitaEsecMateriale materiale) {
    String idCommessa = materiale.getIdCommessa();
    if(idCommessa == null)
      return null;

    where += " AND " + LottiSaldiComTM.ID_COMMESSA + " = '" + idCommessa + "'";
    PersistentObjectCursor poCursor = new PersistentObjectCursor(LottiSaldiCom.class.getName(), where, orderBy, PersistentObject.NO_LOCK);
      return poCursor;
  }

  public PersistentObjectCursor leggiLottiSaldiClienteCursor(String where, String orderBy, AttivitaEsecMateriale materiale) {
    String idCliente = null;
    if(materiale.getAttivitaEsecutiva() != null && materiale.getAttivitaEsecutiva().getOrdineEsecutivo() != null)
      idCliente = materiale.getAttivitaEsecutiva().getOrdineEsecutivo().getIdCliente();
    if(idCliente == null)
      return null;

    where += " AND " + LottiSaldiCliTM.ID_CLIENTE + " = '" + idCliente + "'";
    PersistentObjectCursor poCursor = new PersistentObjectCursor(LottiSaldiCli.class.getName(), where, orderBy, PersistentObject.NO_LOCK);
      return poCursor;
  }

  public PersistentObjectCursor leggiLottiSaldiCursor(String where, String orderBy) {
    PersistentObjectCursor poCursor = new PersistentObjectCursor(LottiSaldi.class.getName(), where, orderBy, PersistentObject.NO_LOCK);
    return poCursor;
  }

  public List identificaLottiFromCursor(PersistentObjectCursor cursor, char modPrlLotto, List lottiOrig, List lottiOrdine, BigDecimal qtaDaPrelevare) throws SQLException {
    List lottiGiusti = new ArrayList();

    try {
      while (cursor.hasNext()) {
        LottiSaldi lotto = (LottiSaldi) cursor.next();
        BigDecimal qtaDispLotto = calcolaQtaGiacenzaNetta(lotto, !lottiOrig.isEmpty(), lottiOrdine);

        if(modPrlLotto == ArticoloDatiMagaz.SINGOLA) {
          if(qtaDispLotto.compareTo(qtaDaPrelevare) >= 0) {
            lottiGiusti.add(lotto);
            break;
          }
        }
        else if (modPrlLotto == ArticoloDatiMagaz.MULTIPLA) {
          if(qtaDispLotto.compareTo(ZERO) > 0) {
            lottiGiusti.add(lotto);
            qtaDaPrelevare = qtaDaPrelevare.subtract(qtaDispLotto);
            if(qtaDaPrelevare.compareTo(ZERO) <= 0)
              break;
          }
        }
      }
    }
    catch (SQLException sqle) {
       throw sqle;
    }
    finally {
       if (cursor != null) 
            cursor.free();
    }

    return lottiGiusti;
  }
  //Fix 34100 -- Fine
  
  //Fix 36033 - inizio
  protected void visualizzaComponenteLottoArtGestLotti(JspWriter out, int posizione) throws IOException {
	  out.println("document.getElementById('IdLotto" + posizione + "').style.background =mCo;");
	  out.println("document.getElementById('IdLotto" + posizione + "').readOnly = false;");
	  out.println("document.getElementById('IdLotto" + posizione + "').typeNameJS.mandatory = true;");
  }

  
  protected void visualizzaComponenteLottoArtNonGestLotti(JspWriter out, int posizione) throws IOException {
	  out.println("document.getElementById('IdLotto" + posizione + "').style.background =bCo;");
	  out.println("document.getElementById('IdLotto" + posizione + "').typeNameJS.mandatory = false;");
	  out.println("document.getElementById('IdLotto" + posizione + "').readOnly = true;");
  }
  
  
  protected void pagPrecSuccPers(RilevDatiPrdTS bo, JspWriter out) throws java.io.IOException {
	
  }
  //Fix 36033 - fine
  //Fix 39680 -- Inizio
  public void riempiMaterialeConUMSec(RilevDatiPrdTS bo, JspWriter out) {
	try {
		out.println("var materialeConUMSec = [];");
		for (int i = 0; i < bo.getMateriali().size(); i++) {
			Articolo materiale = (Articolo) bo.getMateriali().get(new Integer(i));
			if(materiale != null && materiale.getIdUMSecMag() != null) {
		    	out.println("materialeConUMSec.push(true);");
		    }
		    else {
		    	out.println("materialeConUMSec.push(false);");
		    }
		}
	}
	catch (Exception e) {
		e.printStackTrace();
	}
  }
  public void riempiProdottiConUMSec(RilevDatiPrdTS bo, JspWriter out) {
	try {
		out.println("var prodottoConUMSec = [];");
		for (int i = 0; i < bo.getProdotti().size(); i++) {
			Articolo prodotto = (Articolo) bo.getProdotti().get(new Integer(i));
			if(prodotto != null && prodotto.getIdUMSecMag() != null) {
		    	out.println("prodottoConUMSec.push(true);");
		    }
		    else {
		    	out.println("prodottoConUMSec.push(false);");
		    }
		}
	}
	catch (Exception e) {
		e.printStackTrace();
	}
  }
  //Fix 39680 -- Fine
  
//Fix 40289 -- Inizio
  public boolean isLottoMovimentazioneDaGestire(JspWriter out, RilevDatiPrdTS bo) {
	boolean isQtaDaLottoMov = false;
	if(bo.getAttivitaEsecutiva() != null && bo.getAttivitaEsecutiva().getLottoMovimentazione() != null && bo.getAttivitaEsecutiva().getLottoMovimentazione().compareTo(ZERO) > 0) {
	  if(iQtaBuona.compareTo(bo.getAttivitaEsecutiva().getLottoMovimentazione()) > 0) {
		iQtaBuona = bo.getAttivitaEsecutiva().getLottoMovimentazione();
		isQtaDaLottoMov = true;
	  }
	}
	return isQtaDaLottoMov;
  }

  public BigDecimal recuperaQtaLottoMovimentazione(RilevDatiPrdTS bo) {
    BigDecimal qtaMovi = null;
	if(bo.getAttivitaEsecutiva() != null && bo.getAttivitaEsecutiva().getLottoMovimentazione() != null && bo.getAttivitaEsecutiva().getLottoMovimentazione().compareTo(ZERO) > 0) {
	  qtaMovi = bo.getAttivitaEsecutiva().getLottoMovimentazione();
	}
	return qtaMovi;
  }
  //Fix 40289 -- Fine
  //40458 inizio
  protected String getHTMLTitoloDichInCorsoPers01() {
	  return "";
  }
  
  protected String getHTMLTitoloDichInCorsoPers02() {
	  return "";
  }
  
  protected String getHTMLTitoloDichInCorsoPers03() {
	  return "";
  }
  
  protected String getHTMLTitoloDichInCorsoPers04() {
	  return "";
  }
  
  protected String getHTMLTitoloDichInCorsoPers05() {
	  return "";
  }
  //40458 fine


  //Fix 42131 - inizio
  protected void impostaAttributiBOPers(RilevDatiPrdTS bo) {
	
  }
  //Fix 42131 - fine

}
