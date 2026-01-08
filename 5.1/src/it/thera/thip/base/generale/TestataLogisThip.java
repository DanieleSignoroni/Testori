
/**
 * Title:        <b>TestataLogisThip</b><p>
 * Description:  Sviluppo del progetto LOGIS 2
 * Copyright:    Copyright (c) 2002
 * Company:      Infolog 2002
 * @author Adriano Romano 06/06/03
 * @version 2.0
 *
 * 05501 30.05.06 GM - modalità di consegna e modalità di spedizione erano invertiti
 *       08.06.06 GM - se qtà evasa = 0 forzo lo stato avanzamento della riga a definitivo (il metodo std non lo farebbe in quanto la riga è annullata)
 *       05.07.06 GM - eliminazione righe lotti con qtà = 0: corretto errori dell'if e nella gestione dell'indice
 *       06.07.06 GM - in caso di errore durante rendiDefinitivo() il messaggio andava perso
 *       10.07.06 GM - non passava il campo Note e i vettori
 *       12.07.06 GM - aggiunto System.out degli errori restituiti da rendiDefinitivo()
 *       17.07.06 GM - sbagliava la creazione delle righe lotto delle righe sec.
 *       20.07.06 GM - se le righe lotto non erano evase veniva azzerata la qtà originale e veniva spostata sul loto dummy
 *       25.07.06 GM - in caso di chiusura lista senza prelievi la qtà originale va settata sulla qtàAttesaEvasione
 *                   - le righe lotto non evase vanno cancellate solo se ho prelevato qualcosa della riga padre
 *       26.07.05 GM - riscritta la save del documento perchè i saldi venivano aggiornati 2 volte
 *       27.07.06 GM - se tutte le righe sec. sono annullate devo annullare anche la riga primaria
 * 05994 09.10.06 GM - in salvaSuThip numeroRigaHost e dettaglioRigaHost sostituiscono gli attributi4 e 5
 *       11.10.06 GM - la testata del documento di vendita rimaneva in stato provvisorio
 *       13.10.06 GM - aggiunto Trace.excStream in tutti i printStackTrace()
 * 06167 02.11.06 GM - se UM di vendita e UM di mag. sono diverse sbagliava a settare la qtà evasa
 * 06332 29.11.06 AR - modificata la valorizzazione di qtaStr: anzichè toString si usa il cast
 * 06379 04.12.06 GM - aggiunti metodi get/set mancanti per Rx Liste
 * 06408 10.01.07 GM - rinominato salvaSuThip in salvaSuThipVen
 *                   - aggiunto salvaSuThipAcq per gestire l'integrazione Logis/Panthera dei doc. d'acquisto
 *                   - testTrasmissioneDoc() "disattivato" in quanto inutile
 *       16.01.07 GM - modificato valorizzazione del nr. colli e del peso lordo
 *                   - aggiunto peso netto
 *       17.01.07 GM - acquisti: se è un reso non devo gestire le righe sec.
 *                     vendite: se è un kit gestito a magazzino non devo gestire le righe sec.
 *       22.01.07 GM - aggiunto metodi per personalizzare la trascodifica
 * 07074 30.04.07 GM - l'aliquota IVA non deve essere gestita
 *                     (inoltre, dopo l'attivazione del parametro enabled della sezione cache nel thermfw.ini, la chiusura della lista andava in errore)
 * 07425 20.07.07 GM - modificata gestione delle qta1 e qta2, aggiunta gestione della qta3
 *       23.07.07 GM - sulle righe lotto non era valorizzata l'azienda
 *       24.07.07 GM - modificata gestione delle UM
 * 08204 07.11.07 GM - a seguito di una fix pth, per salvare le righe del documento insieme alla testata
 *                     è necessario settare iSalvataggioRigheForzato=true
 * 08464 20/12/07 PJ - hook per annullamento righe documento vendita da parte di logistica
 * 08886 19.03.08 GM - prima di rimuovere la riga lotto reimposto le qtà originali
 *                     altrimenti pth sbaglia l'aggiornamento dell'ordine
 *       21.03.08 PM - aggiunto metodo raggruppaRigheStessoOrdine()
 * 08711 26.03.08 GM - migliorato descrizione degli errori
 * 09095 28/04/08 MM - migliorato descrizione degli errori
 * 09438 24/06/08 MM - Commentata trasmissione dei dati di destinazione al documento di vendita/acquisto
 * 09698 27.08.08 GM - migliorato descrizione degli errori
 * 10004 09.10.08 GM - nel caso di numeratori senza serie non veniva trovato il documento PTH
 *       20.10.08 GM - aggiunto attributo salvaPerRiga per salvare il documento con commit ad ogni riga
 * 10396 03.02.09 GM - introdotto metodo isRigaTrasmessa() per evitare che la chiusura della lista
 *                     annulli le righe del documento non trasmesse alla logistica
 * 10524 03.03.09 GM - i prezzi non devono essere modificati
 * 10760 27.04.09 GM - gestione del flag aggiunto nei filtri doc.ven. che indica se le righe a valore devono essere avanzate di stato
 * 10955   17/06/2009  Gscarta   modificate chiamate a convertiUM dell'articolo per passare la versione
 * 11618 16.11.09 GM - Se non è selezionato il flag "ricalcolo quantità" sul doc.pth,
 *                     per il calcolo della qtà in UMRif, non va utilizzato il metodo convertiUM dell'articolo,
 *                     ma è necessario calcolare il fattore di conversione reale tra UMRif e UMPrm.
 * 11374 07.01.10 GM - il documento va messo in stato definitivo solamente se lo sono tutte le righe
 * 12258 06.04.10 GM - tipoCollegamentoLogistica deve restare invariato (prima non era utilizzato)
 *       16.04.10 GM - le righe del doc.acq. non devono essere ritrasmesse alla logistica (capitava per le righe non prelevate)
 * 12507 20.04.10 DBot Modificato passaggio peso netto /lordo per introduzione gestione pesi in Panthera. Se
 *                     Logis ha valori questi sono imposti a Panthera. Se Logis non ha valori rimangono quelli di Panthera
 * 12807 22.06.10 DBot Aggiunta la gestione trasporti
 * 13435 26.10.10 GM   per cercare il filtro dei doc.ven. si deve usare il magazzino della testata (non della riga)
 * 14041 22.02.11 GM   La chiusura della lista non deve modificare la modalità di pagamento.
 * 14155 15.03.11 GM   tolto aggiornamento di alcuni attributi sulla testata del documento
 *       13.04.11 GM   tolto aggiornamento di alcuni attributi sulle righe del documento
 * 15940 15.03.12 MA   Suporto NLS
 * 15947 12.04.12 MA - Utilizzo oggetti in Cache
 * 16470 24.10.12 MA - Ottimizzazione accesso database
 * 18079 13.06.13 GM   risoluzione del problema "L'oggetto è stato modificato da un altro utente optimistic lock fallito" che si verificava utilizzando la funzione "accoda righe" durante l'evasione di un ordine.
 * 22600 07.12.15 RA - cambiato la chiamata al metodo di TrascodificaThipLogis
 * 23695 30.05.16 GM - correzione NullPointer  
 * 28352 03.12.18 GM - corretto errore: la versione della riga sec veniva settata per sbaglio sulla riga prm
 * 29278 02.05.19 GM - valorizzazione del volume 
 * 29450 03.06.19 GM - valorizzazione del volume: aggiunto condizione != 0
 * 29451 28.06.19 GM - gestione matricole  
 * 29627 12.07.19 GM - aggiornamento stato matricola
 * 31470 25.06.20 DB - Quando si chiude la lista con commit riga per riga non deve 
 *                   - salvare le righe una seconda volta 
 * 32219 10.11.20 GM - corretto errore nell'aggiornamento LottoMatricola (aggiornava solo il primo articolo)    
 * 34657 15.11.21 LTB- Impostare il valore del peso sulle testate quando la logistica ha chiuso le operazioni   
 * 34792 02.12.21 GM - le righe merce a valore non devono passare in definitivo (estensione modifica del 27.04.09)
 * 35962 20.06.22 MBH- sistemare il calcolo del peso della testata in caso di riga gestito lotti trasmessa ma non prelevata
  					   Ho confirmato il problema e la soluzione con GM   
 * 36691 05.10.22 YBA  Sostituire DocumentoVenRigaPrm con DocumentoAcqRigaPrm          
 * 37183 05/12/22 SZ   Recalcola il pesi e volum si non e calcolato nel logis.	 
 * 40798 21.12.23 GM   Aggiunto gancio per personalizzazioni        
 * 47101 24.09.25 GM   Aggiornamento matricole doc.acquisto: corretto errore, aggiornava solo le matricole della prima riga lista        
 */

package it.thera.thip.base.generale;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.ad.ClassADCollectionManager;
import com.thera.thermfw.base.ResourceLoader;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.common.BusinessObject;
import com.thera.thermfw.common.ErrorList;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.CachedStatement;
import com.thera.thermfw.persist.ConnectionDescriptor;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.CopyException;
import com.thera.thermfw.persist.Database;
import com.thera.thermfw.persist.ErrorCodes;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.persist.Proxy;

import it.testori.thip.vendite.documentoVE.YDocumentoVendita; //72273
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaLottoPrm;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaLottoSec;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaPrm;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaSec;
import it.thera.thip.acquisti.documentoAC.DocumentoAcquisto;
import it.thera.thip.acquisti.documentoAC.DocumentoAcquistoRiga;
import it.thera.thip.acquisti.generaleAC.CausaleDocumentoRigaAcq;
import it.thera.thip.base.articolo.ArticoloDatiIdent;
import it.thera.thip.base.articolo.ArticoloVersione;
import it.thera.thip.base.comuniVenAcq.DocumentoDocRiga;
import it.thera.thip.base.comuniVenAcq.DocumentoOrdineRiga;
import it.thera.thip.base.comuniVenAcq.DocumentoOrdineTestata;
import it.thera.thip.base.comuniVenAcq.DocumentoRigaLotto;
import it.thera.thip.base.comuniVenAcq.DocumentoTestata;
import it.thera.thip.base.comuniVenAcq.RigaPrimaria;
import it.thera.thip.base.comuniVenAcq.TipoRiga;
import it.thera.thip.base.documenti.web.DocumentoDataCollector;
import it.thera.thip.cs.DatiComuniEstesi;
import it.thera.thip.cs.ThipException;
import it.thera.thip.datiTecnici.configuratore.Configurazione;
import it.thera.thip.logis.bas.CampoTrascodifica;
import it.thera.thip.logis.bas.CampoVariabile;
import it.thera.thip.logis.bas.Indirizzo;
import it.thera.thip.logis.bas.ParametriLogis;
import it.thera.thip.logis.bas.Tabella;
import it.thera.thip.logis.fis.Matricola;
import it.thera.thip.logis.fis.MatricolaTM;
import it.thera.thip.logis.lgb.RigaLista;
import it.thera.thip.logis.lgb.TestataLista;
import it.thera.thip.logis.prd.Gruppo;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.magazzino.matricole.LottoMatricola;
import it.thera.thip.magazzino.matricole.StoricoMatricola;
import it.thera.thip.vendite.documentoVE.DocumentoVenRigaLotto;
import it.thera.thip.vendite.documentoVE.DocumentoVenRigaLottoPrm;
import it.thera.thip.vendite.documentoVE.DocumentoVenRigaLottoSec;
import it.thera.thip.vendite.documentoVE.DocumentoVenRigaPrm;
import it.thera.thip.vendite.documentoVE.DocumentoVenRigaSec;
import it.thera.thip.vendite.documentoVE.DocumentoVendita;
import it.thera.thip.vendite.documentoVE.DocumentoVenditaRiga;
import it.thera.thip.vendite.generaleVE.CausaleRigaDocVen;

/**
 * Testata documenti packing list prodotti da Logis per Thip
 */
public class TestataLogisThip {

	public static final String RES_FILE = "it.thera.thip.base.generale.resources.TestataLogisThip"; // Fix 15940

  public static final char LISTA = 'L';
  public static final char ORDINE = 'O';
  public static final char ARTICOLI = 'A';
  public static final char SERVIZIO = 'S';

  protected Proxy societa = new Proxy (it.thera.thip.logis.lgb.Societa.class);

  protected String codice;

  protected Date dataTestata;

  protected Integer allestimentoTestata;

  public void setAllestimento(Integer s) {
    allestimentoTestata = s;
  }

  public Integer getAllestimento() {
    return allestimentoTestata;
  }

/**
 * Differenzia tra ordini e liste.
 */
  protected char tipoTestata = LISTA;

  protected char stato = it.thera.thip.logis.lgb.TestataLista.VALIDO;

  protected char statoTestata = it.thera.thip.logis.lgb.TestataLista.NUOVO;

  protected boolean flagBlocco = false;

  protected String origine;

  protected String zonaGenerica;

  protected boolean flagControvaloreEuro = false;

  protected String descCondizioniTrasporto;

  protected String descModalitaTrasporto;

  protected String descTipoImballo;

  protected String riferimentoPartner;

  protected Date dataRiferimentoPartner;

  protected String[] statisticaPartner= new String[5];

  protected char giornoChiusura;

  protected char giornoConsegna;

  protected String priorita;

  protected Date dataConsegnaRichiesta;

  protected Date dataConsegnaConfermata;

  protected String[] attributo=new String[5];

  protected Date[] data= new Date[3];

  protected BigDecimal[] valori=new BigDecimal[3];

  protected BigDecimal importoDivisa=new BigDecimal(0);

  protected BigDecimal importoControvalore=new BigDecimal(0);

  protected BigDecimal pesoTotale=new BigDecimal(0);

  protected BigDecimal pesoNetto=new BigDecimal(0);//16.01.07

  protected BigDecimal volumeTotale=new BigDecimal(0);
  protected Integer numUdm = new Integer(0);


  protected boolean flagGestionePianificazione = false;

  protected boolean flagAbilitaAccorpamento = true;

  protected boolean flagControlloCompatibilitaRiga = true;

//  protected Integer allestimentoTestata;

  protected Integer prioritaSpedizione;

  protected boolean allarmeCompatibilita = false;

  protected boolean flagUds = true;

  protected boolean flagSpedizione = true;

//  protected Boolean mancante = new Boolean(false);

//  protected Timestamp timestampAperto;

//  protected Timestamp timestampChiuso;

  protected Proxy tipoLista = new Proxy(it.thera.thip.logis.lgb.TipoLista.class);
  protected Proxy tipoOrdine = new Proxy(it.thera.thip.logis.lgb.TipoOrdine.class);

  protected Proxy destinatario=new Proxy(it.thera.thip.logis.lgb.Partner.class);
  protected Proxy partnerGruppo=new Proxy(it.thera.thip.logis.lgb.Partner.class);
  protected Proxy partnerFatturazione=new Proxy(it.thera.thip.logis.lgb.Partner.class);
  protected Proxy partnerDestinazione=new Proxy(it.thera.thip.logis.lgb.Partner.class);
  protected Proxy destinazione=new Proxy(it.thera.thip.logis.lgb.Destinazione.class);

  protected Proxy magLogicoTrasferimento = new Proxy (it.thera.thip.logis.lgb.MagLogico.class);

  protected Proxy lingua=new Proxy(com.thera.thermfw.util.PersistentLanguage.class);

  protected String divisa;

  protected Proxy modalitaTrasporto=new Proxy(it.thera.thip.logis.lgb.ModalitaTrasporto.class);
  protected Proxy condizioneTrasporto=new Proxy(it.thera.thip.logis.lgb.CondizioneTrasporto.class);
  protected Proxy tipoImballo=new Proxy (it.thera.thip.logis.lgb.TipoImballo.class);

  protected Proxy vettore1=new Proxy(it.thera.thip.logis.lgb.Vettore.class);
  protected Proxy vettore2=new Proxy(it.thera.thip.logis.lgb.Vettore.class);

  protected Proxy agente1 = new Proxy(it.thera.thip.logis.lgb.Agente.class);
  protected Proxy agente2 = new Proxy(it.thera.thip.logis.lgb.Agente.class);

  protected Proxy magLogicoImpegno=new Proxy(it.thera.thip.logis.lgb.MagLogico.class);

  protected Proxy gruppo=new Proxy(it.thera.thip.logis.prd.Gruppo.class);

  protected Proxy magFisicoImpegno=new Proxy (it.thera.thip.logis.fis.MagFisico.class);
  protected Proxy magFisicoTrasferimento=new Proxy (it.thera.thip.logis.fis.MagFisico.class);

  protected Proxy tipoPackingList=new Proxy (it.thera.thip.logis.lgb.TipoPackingList.class);

  protected String note;

  protected Integer sequenzaDistribuzione;

  protected Proxy proxyGiroDistributivo = new Proxy(it.thera.thip.logis.lgb.GiroDistributivo.class);

//  protected Proxy proxyPianificazioneLista = new Proxy(it.thera.thip.logis.fis.PianificazioneLista.class);

//  protected Proxy proxyPianoSpedizione = new Proxy(it.thera.thip.logis.fis.PianoSpedizione.class);

  protected Indirizzo destinazioneAlternativa = (Indirizzo) Factory.createObject(Indirizzo.class);
  protected Indirizzo vettore1Alternativo = (Indirizzo) Factory.createObject(Indirizzo.class);
  protected Indirizzo vettore2Alternativo = (Indirizzo) Factory.createObject(Indirizzo.class);

  protected boolean flagMissioni = true;

  protected Proxy postazione = new Proxy(it.thera.thip.logis.fis.Postazione.class);
  protected Proxy operatore = new Proxy(it.thera.thip.logis.fis.Operatore.class);

  protected boolean flagMissionato=false;
  protected boolean flagMancante=false;
  protected String flagUtente1="";
  protected String flagUtente2="";
  protected String flagUtente3="";
  protected String flagUtente4="";
  protected String flagUtente5="";

  protected boolean flagPickPack = false;

  protected Proxy azienda = new Proxy(it.thera.thip.base.azienda.Azienda.class);

  //04.12.06 - inizio
  protected Integer unita;

  protected BigDecimal volume;

  protected char statoSpedizione;

  protected String puntoCarico;

  protected String prioritaLista;

  protected boolean flagGestioneSpedizione;

  protected Integer codicePianoSpedizione;

  protected Integer codicePianificazioneLista;
  //04.12.06 - fine

  public void setAzienda(it.thera.thip.base.azienda.Azienda a) {
    azienda.setObject(a);
  }

  public it.thera.thip.base.azienda.Azienda getAzienda() {
    return (it.thera.thip.base.azienda.Azienda) azienda.getObject();
  }

  public void setAziendaKey(String aziendaKey) {
    azienda.setKey(aziendaKey);
  }

  public String getAziendaKey() {
    return azienda.getKey();
  }

  public void setIdAzienda(String idAzienda) {
    setAziendaKey(idAzienda);
  }

  public String getIdAzienda() {
    return getAziendaKey();
  }

  public boolean getFlagPickPack() {
    return flagPickPack;
  }

  public void setFlagPickPack(boolean m) {
    flagPickPack = m;
  }

  public boolean getFlagMissioni() {
    return flagMissioni;
  }

  public void setFlagMissioni(boolean m) {
    flagMissioni = m;
  }

/**  public Boolean isMancante() {
    return mancante;
  }**/

  public it.thera.thip.logis.lgb.GiroDistributivo getGiroDistributivo() {
    return (it.thera.thip.logis.lgb.GiroDistributivo) proxyGiroDistributivo.getObject();
  }

  public void setGiroDistributivo(it.thera.thip.logis.lgb.GiroDistributivo gd) {
    proxyGiroDistributivo.setObject(gd);
  }

  public String getChiaveGiroDistributivo() {
    return proxyGiroDistributivo.getKey();
  }

  public void setChiaveGiroDistributivo(String key) {
    proxyGiroDistributivo.setKey(key);
  }

  public String getSocietaGiroDistributivo() {
    return KeyHelper.getTokenObjectKey(getChiaveGiroDistributivo(), 1);
  }

  public void setSocietaGiroDistributivo(String soc) {
    setChiaveGiroDistributivo(KeyHelper.replaceTokenObjectKey(proxyGiroDistributivo.getKey(), 1, soc));
  }

  public String getCodiceGiroDistributivo() {
    return KeyHelper.getTokenObjectKey(proxyGiroDistributivo.getKey(), 2);
  }

  public void setCodiceGiroDistributivo(String cod) {
    setChiaveGiroDistributivo(KeyHelper.replaceTokenObjectKey(proxyGiroDistributivo.getKey(), 2, cod));
  }

  public Integer getSequenzaDistribuzione() {
    return sequenzaDistribuzione;
  }

  public void setSequenzaDistribuzione(Integer sd) {
    sequenzaDistribuzione = sd;
  }

  public Integer getPrioritaSpedizione() {
    return prioritaSpedizione;
  }

  public void setPrioritaSpedizione(Integer ps) {
    prioritaSpedizione = ps;
  }

/*  public it.thera.thip.logis.fis.PianificazioneLista getPianificazioneLista() {
    return (it.thera.thip.logis.fis.PianificazioneLista) proxyPianificazioneLista.getObject();
  }

  public void setPianificazioneLista(it.thera.thip.logis.fis.PianificazioneLista pl) {
    proxyPianificazioneLista.setObject(pl);
  }

  public String getChiavePianificazioneLista() {
    return proxyPianificazioneLista.getKey();
  }

  public void setChiavePianificazioneLista(String key) {
    proxyPianificazioneLista.setKey(key);
  }

  public Integer getCodicePianificazioneLista() {
    String cod = KeyHelper.getTokenObjectKey(getChiavePianificazioneLista(), 2);
    if (cod == null)
      return null;
    return Integer.valueOf(cod);
  }

  public void setCodicePianificazioneLista(Integer cod) {
    if (cod != null) {
      proxyPianificazioneLista.setKey(KeyHelper.replaceTokenObjectKey(getChiavePianificazioneLista(), 2, cod.toString()));
    }
  }

  public it.thera.thip.logis.fis.PianoSpedizione getPianoSpedizione() {
    return (it.thera.thip.logis.fis.PianoSpedizione) proxyPianoSpedizione.getObject();
  }

  public void setPianoSpedizione(it.thera.thip.logis.fis.PianoSpedizione pl) {
    proxyPianoSpedizione.setObject(pl);
  }

  public String getChiavePianoSpedizione() {
    return proxyPianoSpedizione.getKey();
  }

  public void setChiavePianoSpedizione(String key) {
    proxyPianoSpedizione.setKey(key);
  }

  public Integer getCodicePianoSpedizione() {
    String cod = KeyHelper.getTokenObjectKey(getChiavePianoSpedizione(), 2);
    if (cod == null) return null;
    return Integer.valueOf(cod);
  }

  public void setCodicePianoSpedizione(Integer cod) {
    if (cod != null) {
      proxyPianoSpedizione.setKey(KeyHelper.replaceTokenObjectKey(getChiavePianoSpedizione(), 2, cod.toString()));
    }
  }*/

  public boolean getFlagGestionePianific() {
    return flagGestionePianificazione;
  }

  public void setFlagGestionePianific(boolean f) {
    flagGestionePianificazione = f;
  }

  public boolean getFlagAbilitaAccorp() {
    return flagAbilitaAccorpamento;
  }

  public void setFlagAbilitaAccorp(boolean f) {
    flagAbilitaAccorpamento = f;
  }

  public void setFlagUds(boolean flag) {
    flagUds = flag;
  }

  public boolean getFlagUds() {
    return flagUds;
  }

  public void setFlagSpedizione(boolean flag) {
    flagSpedizione = flag;
  }

  public boolean getFlagSpedizione() {
    return flagSpedizione;
  }

  public boolean getFlagContCompatRiga() {
    return flagControlloCompatibilitaRiga;
  }

  public void setFlagContCompatRiga(boolean f) {
    flagControlloCompatibilitaRiga = f;
  }

/* public Integer getAllestimentoTestate() {
    return allestimentoTestata;
  }

  public void setAllestimentoTestate(Integer f) {
    allestimentoTestata = f;
  }*/

  public boolean getAllarmeCompatibilita() {
    return allarmeCompatibilita;
  }

  public void setAllarmeCompatibilita(boolean f) {
    allarmeCompatibilita = f;
  }

  public void setSocieta(it.thera.thip.logis.lgb.Societa s){
    societa.setObject(s);
    if(s!=null){
      String cod=s.getKey();
      setChiaveTipoLista(KeyHelper.replaceTokenObjectKey(getChiaveTipoLista(), 1, cod));
      setChiaveTipoOrdine(KeyHelper.replaceTokenObjectKey(getChiaveTipoOrdine(), 1, cod));
      setChiaveDestinatario(KeyHelper.replaceTokenObjectKey(getChiaveDestinatario(), 1, cod));
      setChiavePartnerGruppo(KeyHelper.replaceTokenObjectKey(getChiavePartnerGruppo(), 1, cod));
      setChiavePartnerFatturazione(KeyHelper.replaceTokenObjectKey(getChiavePartnerFatturazione(), 1, cod));
      setChiaveTipoImballo(KeyHelper.replaceTokenObjectKey(getChiaveTipoImballo(), 1, cod));
      setChiaveCondizioneTrasporto(KeyHelper.replaceTokenObjectKey(getChiaveCondizioneTrasporto(), 1, cod));
      setChiaveModalitaTrasporto(KeyHelper.replaceTokenObjectKey(getChiaveModalitaTrasporto(), 1, cod));
      setChiaveMagLogicoTrasferimento(KeyHelper.replaceTokenObjectKey(getChiaveMagLogicoTrasferimento(), 1, cod));
      setChiaveMagLogicoImpegno(KeyHelper.replaceTokenObjectKey(getChiaveMagLogicoImpegno(), 1, cod));
      setChiaveAgente1(KeyHelper.replaceTokenObjectKey(getChiaveAgente1(), 1, cod));
      setChiaveAgente2(KeyHelper.replaceTokenObjectKey(getChiaveAgente2(), 1, cod));
      setChiaveVettore1(KeyHelper.replaceTokenObjectKey(getChiaveVettore1(), 1, cod));
      setChiaveVettore2(KeyHelper.replaceTokenObjectKey(getChiaveVettore2(), 1, cod));
      setChiavePartnerDestinazione(KeyHelper.replaceTokenObjectKey(getChiavePartnerDestinazione(), 1, cod));
      setChiaveDestinazione(KeyHelper.replaceTokenObjectKey(getChiaveDestinazione(), 1, cod));
      setChiaveTipoPackingList(KeyHelper.replaceTokenObjectKey(getChiaveTipoPackingList(), 1, cod));
      setSocietaGiroDistributivo(KeyHelper.replaceTokenObjectKey(getChiaveGiroDistributivo(), 1, cod));
    }
  }

  public it.thera.thip.logis.lgb.Societa getSocieta(){
    return (it.thera.thip.logis.lgb.Societa) societa.getObject();
  }

  public void setCodiceSocieta(String cod){
    societa.setKey(cod);
    setChiaveTipoLista(KeyHelper.replaceTokenObjectKey(getChiaveTipoLista(), 1, cod));
    setChiaveTipoOrdine(KeyHelper.replaceTokenObjectKey(getChiaveTipoOrdine(), 1, cod));
    setChiaveDestinatario(KeyHelper.replaceTokenObjectKey(getChiaveDestinatario(), 1, cod));
    setChiavePartnerGruppo(KeyHelper.replaceTokenObjectKey(getChiavePartnerGruppo(), 1, cod));
    setChiavePartnerFatturazione(KeyHelper.replaceTokenObjectKey(getChiavePartnerFatturazione(), 1, cod));
    setChiaveTipoImballo(KeyHelper.replaceTokenObjectKey(getChiaveTipoImballo(), 1, cod));
    setChiaveCondizioneTrasporto(KeyHelper.replaceTokenObjectKey(getChiaveCondizioneTrasporto(), 1, cod));
    setChiaveModalitaTrasporto(KeyHelper.replaceTokenObjectKey(getChiaveModalitaTrasporto(), 1, cod));
    setChiaveMagLogicoTrasferimento(KeyHelper.replaceTokenObjectKey(getChiaveMagLogicoTrasferimento(), 1, cod));
    setChiaveMagLogicoImpegno(KeyHelper.replaceTokenObjectKey(getChiaveMagLogicoImpegno(), 1, cod));
    setChiaveAgente1(KeyHelper.replaceTokenObjectKey(getChiaveAgente1(), 1, cod));
    setChiaveAgente2(KeyHelper.replaceTokenObjectKey(getChiaveAgente2(), 1, cod));
    setChiaveVettore1(KeyHelper.replaceTokenObjectKey(getChiaveVettore1(), 1, cod));
    setChiaveVettore2(KeyHelper.replaceTokenObjectKey(getChiaveVettore2(), 1, cod));
    setChiavePartnerDestinazione(KeyHelper.replaceTokenObjectKey(getChiavePartnerDestinazione(), 1, cod));
    setChiaveDestinazione(KeyHelper.replaceTokenObjectKey(getChiaveDestinazione(), 1, cod));
    setChiaveTipoPackingList(KeyHelper.replaceTokenObjectKey(getChiaveTipoPackingList(), 1, cod));
    setSocietaGiroDistributivo(KeyHelper.replaceTokenObjectKey(getChiaveGiroDistributivo(), 1, cod));
  }

  public String getCodiceSocieta(){
    return societa.getKey();
  }

  public void setCodice(String cod){
    codice=cod;
  }

  public String getCodice(){
    return codice;
  }

  public void setDataTestata(Date d){
    dataTestata=d;
  }

  public Date getDataTestata(){
    return dataTestata;
  }

  public void setStato(char s){
    stato=s;
  }

  public char getStato(){
    return stato;
  }

  public void setStatoTestata(char s){
    statoTestata=s;
  }

  public char getStatoTestata(){
    return statoTestata;
  }

  public void setFlagBlocco(boolean flag){
    flagBlocco=flag;
  }

  public boolean getFlagBlocco(){
    return flagBlocco;
  }

  public void setOrigine(String o){
    origine=o;
  }

  public String getOrigine(){
    return origine;
  }

  public void setZonaGenerica(String zona){
    zonaGenerica=zona;
  }

  public String getZonaGenerica(){
    return zonaGenerica;
  }

  public void setFlagControvaloreEuro(boolean flag){
    flagControvaloreEuro=flag;
  }

  public boolean getFlagControvaloreEuro(){
    return flagControvaloreEuro;
  }

  public void setDescCondizioniTrasporto(String desc){
    descCondizioniTrasporto=desc;
  }

  public String getDescCondizioniTrasporto(){
    return descCondizioniTrasporto;
  }

  public void setDescModalitaTrasporto(String desc){
    descModalitaTrasporto=desc;
  }

  public String getDescModalitaTrasporto(){
    return descModalitaTrasporto;
  }

  public void setDescTipoImballo(String desc){
    descTipoImballo=desc;
  }

  public String getDescTipoImballo(){
    return descTipoImballo;
  }

  public void setRiferimentoPartner(String rif){
    riferimentoPartner=rif;
  }

  public String getRiferimentoPartner(){
    return riferimentoPartner;
  }

  public void setDataRiferimentoPartner(Date data){
    dataRiferimentoPartner=data;
  }

  public Date getDataRiferimentoPartner(){
    return dataRiferimentoPartner;
  }

  public void setGiornoChiusura(char g){
    giornoChiusura=g;
  }

  public char getGiornoChiusura(){
    return giornoChiusura;
  }

  public void setGiornoConsegna(char g){
    giornoConsegna=g;
  }

  public char getGiornoConsegna(){
    return giornoConsegna;
  }

  public void setPriorita(String p){
    priorita = p;
  }

  public String getPriorita(){
    return priorita;
  }

  public void setDataConsegnaRichiesta(Date d){
    dataConsegnaRichiesta=d;
  }

  public Date getDataConsegnaRichiesta(){
    return dataConsegnaRichiesta;
  }

  public void setDataConsegnaConfermata(Date d){
    dataConsegnaConfermata=d;
  }

  public Date getDataConsegnaConfermata(){
    return dataConsegnaConfermata;
  }

  public void setImportoDivisa(BigDecimal i){
    importoDivisa=i;
  }

  public BigDecimal getImportoDivisa(){
    return importoDivisa;
  }

  public void setImportoControvalore(BigDecimal i){
    importoControvalore=i;
  }

  public BigDecimal getImportoControvalore(){
    return importoControvalore;
  }

  public void setPesoTotale(BigDecimal i){
    pesoTotale=i;
  }

  public BigDecimal getPesoTotale(){
    return pesoTotale;
  }

  //16.01.07 - inizio
  public void setPesoNetto(BigDecimal i){
    pesoNetto=i;
  }

  public BigDecimal getPesoNetto(){
    return pesoNetto;
  }
  //16.01.07 - fine

  public void setVolumeTotale(BigDecimal i){
    volumeTotale=i;
  }

  public BigDecimal getVolumeTotale(){
    return volumeTotale;
  }

  public Integer getNumUdm() {
    return numUdm;
  }

  public void setNumUdm(Integer num) {
    numUdm = num;
  }

  public String[] getStatisticaPartner() {
    return statisticaPartner;
  }

  public void setStatisticaPartner(String[] s) {
    statisticaPartner = s;
  }

  public String getStatisticaPartner(int pos) {
    if ((pos>=0) && (pos<statisticaPartner.length)) {
      return statisticaPartner[pos];
    }
    else {
      return "";
    }
  }

  public void setStatisticaPartner(String s, int pos) {
    if ((pos>=0) && (pos<statisticaPartner.length)) {
      statisticaPartner[pos] = s;
    }
  }

  public CampoVariabile getCampoVariabileStatisticaPartner(int pos){
    if (getSocieta() == null)
      return null;
    if (pos >= 0 && pos < statisticaPartner.length) {
      List pc = getSocieta().getStatistiche();
      CampoVariabile cv = (CampoVariabile) pc.get(pos);
      return cv;
    }
    else
      return null;
  }

  public CampoVariabile getCampoVariabileAttributo(int pos){
    if (pos >= 0 && pos < attributo.length) {
      List pc = null;
      if (tipoTestata == LISTA)
        pc = getTipoLista().getAttributi();
      else
        if (tipoTestata == ORDINE)
          pc = getTipoOrdine().getAttributi();
      CampoVariabile ct = (CampoVariabile) pc.get(pos);
      return ct;
    } else
      return null;
  }


  public void setAttributo(String[] a){
    attributo=a;
  }

  public String[] getAttributo(){
    return attributo;
  }

  public void setAttributo(String a, int pos){
   if ((pos>=0) && (pos<attributo.length)) {
      attributo[pos] = a;
    }
  }

  public String getAttributo(int pos){
    if ((pos>=0) && (pos<attributo.length)) {
      return attributo[pos];
    } else {
      return "";
    }
  }

  public void setAttributo1(String a){
    setAttributo(a,0);
  }

  public String getAttributo1(){
    return attributo[0];
  }

  public void setAttributo2(String a){
    setAttributo(a,1);
  }

  public String getAttributo2(){
    return attributo[1];
  }

  public void setAttributo3(String a){
    setAttributo(a,2);
  }

  public String getAttributo3(){
    return attributo[2];
  }

  public void setAttributo4(String a){
    setAttributo(a,3);
  }

  public String getAttributo4(){
    return attributo[3];
  }

  public void setAttributo5(String a){
    setAttributo(a,4);
  }

  public String getAttributo5(){
    return attributo[4];
  }

  public void setData1(Date d){
    setData(d,0);
  }

  public Date getData1(){
    return data[0];
  }

  public void setData2(Date d){
    setData(d,1);
  }

  public Date getData2(){
    return data[1];
  }

  public void setData3(Date d){
    setData(d,2);
  }

  public Date getData3(){
    return data[2];
  }

  public void setValori1(BigDecimal v){
    setValori(v,0);
  }

  public BigDecimal getValori1(){
    return valori[0];
  }

  public void setValori2(BigDecimal v){
    setValori(v,1);
  }

  public BigDecimal getValori2(){
    return valori[1];
  }

  public void setValori3(BigDecimal v){
    setValori(v,2);
  }

  public BigDecimal getValori3(){
    return valori[2];
  }

  public void setStatisticaPartner1(String s){
    setStatisticaPartner(s,0);
  }

  public String getStatisticaPartner1(){
    return statisticaPartner[0];
  }

  public String getStatisticaPartner2(){
    return statisticaPartner[1];
  }

  public void setStatisticaPartner2(String s){
    setStatisticaPartner(s,1);
  }

  public String getStatisticaPartner3(String s){
    return statisticaPartner[2];
  }

  public String getStatisticaPartner3(){
    return statisticaPartner[2];
  }

  public void setStatisticaPartner4(String s){
    setStatisticaPartner(s,3);
  }

  public String getStatisticaPartner4(){
    return statisticaPartner[3];
  }

  public void setStatisticaPartner5(String s){
    setStatisticaPartner(s,4);
  }

  public String getStatisticaPartner5(){
    return statisticaPartner[4];
  }

  public String getStatistica1Table(){
    return getStatisticaPartner(0);
  }

  public String getStatistica2Table(){
    return getStatisticaPartner(1);
  }

  public String getStatistica3Table(){
    return getStatisticaPartner(2);
  }

  public String getStatistica4Table(){
    return getStatisticaPartner(3);
  }

  public String getStatistica5Table(){
    return getStatisticaPartner(4);
  }

  public CampoVariabile getCampoVariabileData(int pos) {
    if (pos >= 0 && pos < data.length) {
      List pc = null;
      if (tipoTestata == LISTA)
        pc = getTipoLista().getDateLista();
      else
        if (tipoTestata == ORDINE)
          pc = getTipoOrdine().getDateOrdine();
      CampoVariabile ct = (CampoVariabile) pc.get(pos);
      return ct;
    }
    else
      return null;
  }

  public Date[] getData(){
    return data;
  }

  public void setData(Date a, int pos){
   if ((pos>=0) && (pos<data.length)) {
      data[pos] = a;
    }
  }

  public Date getData(int pos){
    if ((pos>=0) && (pos<data.length) ) {
      return data[pos];
    } else {
      return null;
    }
  }

/**
 * Restituisce il campo variable relativo all'elemento nella posizione <code> pos</code>
 * all'interno della persistent collection <code> numeriOrdine </code> di TipoOrdine.
 */
  public CampoVariabile getCampoVariabileValore(int pos) {
    if (pos >= 0 && pos < valori.length) {
      List pc = null;
      if (tipoTestata == LISTA)
        pc = getTipoLista().getCampiNumero();
      else
        if (tipoTestata == ORDINE)
          pc = getTipoOrdine().getCampiNumeroOrdine();
      CampoVariabile ct = (CampoVariabile) pc.get(pos);
      return ct;
    }
    else
      return null;
  }

  public void setValori(BigDecimal[] val){
    valori=val;
  }

  public BigDecimal[] getValori(){
    return valori;
  }

  public void setValori(BigDecimal val,int pos){
  if ((pos>=0) && (pos<valori.length)) {
      valori[pos] = val;
    }
  }

  public BigDecimal getValori(int pos){
    if ((pos>=0) && (pos<valori.length)) {
      return valori[pos];
    } else {
      return null;
    }
  }

  public Indirizzo getDestinazioneAlternativa(){
    return destinazioneAlternativa;
  }

  public Indirizzo getVettore1Alternativo(){
    return vettore1Alternativo;
  }

  public Indirizzo getVettore2Alternativo(){
    return vettore2Alternativo;
  }

  public void setTipoLista(it.thera.thip.logis.lgb.TipoLista t){
    tipoLista.setObject(t);
  }

  public it.thera.thip.logis.lgb.TipoLista getTipoLista(){
    return (it.thera.thip.logis.lgb.TipoLista) tipoLista.getObject();
  }

  public void setChiaveTipoLista(String key){
    tipoLista.setKey(key);
  }

  public String getChiaveTipoLista(){
    return tipoLista.getKey();
  }

  public void setCodiceTipoLista(String cod){
    setChiaveTipoLista(KeyHelper.replaceTokenObjectKey(getChiaveTipoLista(), 2, cod));
  }

  public String getCodiceTipoLista(){
    return KeyHelper.getTokenObjectKey(getChiaveTipoLista(), 2);
  }

  public void setTipoOrdine(it.thera.thip.logis.lgb.TipoOrdine t){
    tipoLista.setObject(t);
  }

  public it.thera.thip.logis.lgb.TipoOrdine getTipoOrdine(){
    return (it.thera.thip.logis.lgb.TipoOrdine) tipoOrdine.getObject();
  }

  public void setChiaveTipoOrdine(String key){
    tipoOrdine.setKey(key);
  }

  public String getChiaveTipoOrdine(){
    return tipoOrdine.getKey();
  }

  public void setCodiceTipoOrdine(String cod){
    setChiaveTipoOrdine(KeyHelper.replaceTokenObjectKey(getChiaveTipoOrdine(), 2, cod));
  }

  public String getCodiceTipoOrdine(){
    return KeyHelper.getTokenObjectKey(getChiaveTipoOrdine(), 2);
  }

  public void setDestinatario(it.thera.thip.logis.lgb.Partner p){
    destinatario.setObject(p);
  }

  public it.thera.thip.logis.lgb.Partner getDestinatario(){
    return (it.thera.thip.logis.lgb.Partner) destinatario.getObject();
  }

  public void setChiaveDestinatario(String key){
    destinatario.setKey(key);
  }

  public String getChiaveDestinatario(){
    return destinatario.getKey();
  }

  public void setCodiceDestinatario(String cod){
    setChiaveDestinatario(KeyHelper.replaceTokenObjectKey(getChiaveDestinatario(), 2, cod));
  }

  public String getCodiceDestinatario(){
    return KeyHelper.getTokenObjectKey(getChiaveDestinatario(), 2);
  }

  public String getDescBrDestinatario() {
    if(getDestinatario()!=null)
      return getDestinatario().getDescrizioneBr();
    else
      return "";
  }

  public void setPartnerGruppo(it.thera.thip.logis.lgb.Partner p){
    partnerGruppo.setObject(p);
  }

  public it.thera.thip.logis.lgb.Partner getPartnerGruppo(){
    return (it.thera.thip.logis.lgb.Partner) partnerGruppo.getObject();
  }

  public void setChiavePartnerGruppo(String key){
    partnerGruppo.setKey(key);
  }

  public String getChiavePartnerGruppo(){
    return partnerGruppo.getKey();
  }

  public void setCodicePartnerGruppo(String cod){
    setChiavePartnerGruppo(KeyHelper.replaceTokenObjectKey(getChiavePartnerGruppo(), 2, cod));
  }

  public String getCodicePartnerGruppo(){
    return KeyHelper.getTokenObjectKey(getChiavePartnerGruppo(), 2);
  }

  public void setPartnerFatturazione(it.thera.thip.logis.lgb.Partner p){
    partnerFatturazione.setObject(p);
  }

  public it.thera.thip.logis.lgb.Partner getPartnerFatturazione(){
    return (it.thera.thip.logis.lgb.Partner) partnerFatturazione.getObject();
  }

  public void setChiavePartnerFatturazione(String key){
    partnerFatturazione.setKey(key);
  }

  public String getChiavePartnerFatturazione(){
    return partnerFatturazione.getKey();
  }

  public void setCodicePartnerFatturazione(String cod){
    setChiavePartnerFatturazione(KeyHelper.replaceTokenObjectKey(getChiavePartnerFatturazione(), 2, cod));
  }

  public String getCodicePartnerFatturazione(){
    return KeyHelper.getTokenObjectKey(getChiavePartnerFatturazione(), 2);
  }

  public void setPartnerDestinazione(it.thera.thip.logis.lgb.Partner p){
    partnerDestinazione.setObject(p);
  }

  public it.thera.thip.logis.lgb.Partner getPartnerDestinazione(){
    return (it.thera.thip.logis.lgb.Partner) partnerDestinazione.getObject();
  }

  public void setChiavePartnerDestinazione(String key){
    partnerDestinazione.setKey(key);
  }

  public String getChiavePartnerDestinazione(){
    return partnerDestinazione.getKey();
  }

  public void setCodicePartnerDestinazione(String cod){
    setChiavePartnerDestinazione(KeyHelper.replaceTokenObjectKey(getChiavePartnerDestinazione(), 2, cod));
    setChiaveDestinazione(KeyHelper.replaceTokenObjectKey(getChiaveDestinazione(), 2, cod));
  }

  public String getCodicePartnerDestinazione(){
    return KeyHelper.getTokenObjectKey(getChiavePartnerDestinazione(), 2);
  }

  public void setCodiceDivisa(String cod){
    divisa=cod;
  }

  public String getCodiceDivisa(){
    return divisa;
  }

  public void setLingua(com.thera.thermfw.util.PersistentLanguage l){
    lingua.setObject(l);
  }

  public com.thera.thermfw.util.PersistentLanguage getLingua(){
    return (com.thera.thermfw.util.PersistentLanguage) lingua.getObject();
  }

  public void setCodiceLingua(String lin){
    lingua.setKey(lin);
  }

  public String getCodiceLingua(){
    return lingua.getKey();
  }

  public void setGruppo(it.thera.thip.logis.prd.Gruppo g){
    gruppo.setObject(g);
  }

  public it.thera.thip.logis.prd.Gruppo getGruppo(){
    return (it.thera.thip.logis.prd.Gruppo) gruppo.getObject();
  }

  public void setCodiceGruppo(String cod){
    gruppo.setKey(cod);
  }

  public String getCodiceGruppo(){
    return gruppo.getKey();
  }

  public void setTipoImballo(it.thera.thip.logis.lgb.TipoImballo t){
    tipoImballo.setObject(t);
  }

  public it.thera.thip.logis.lgb.TipoImballo getTipoImballo(){
    return (it.thera.thip.logis.lgb.TipoImballo) tipoImballo.getObject();
  }

  public void setChiaveTipoImballo(String key){
    tipoImballo.setKey(key);
  }

  public String getChiaveTipoImballo(){
    return tipoImballo.getKey();
  }

  public void setCodiceTipoImballo(String cod){
    setChiaveTipoImballo(KeyHelper.replaceTokenObjectKey(getChiaveTipoImballo(), 2, cod));
  }

  public String getCodiceTipoImballo(){
    return KeyHelper.getTokenObjectKey(getChiaveTipoImballo(), 2);
  }

  public void setCondizioneTrasporto(it.thera.thip.logis.lgb.CondizioneTrasporto t){
    condizioneTrasporto.setObject(t);
  }

  public it.thera.thip.logis.lgb.CondizioneTrasporto getCondizioneTrasporto(){
    return (it.thera.thip.logis.lgb.CondizioneTrasporto) condizioneTrasporto.getObject();
  }

  public void setChiaveCondizioneTrasporto(String key){
    condizioneTrasporto.setKey(key);
  }

  public String getChiaveCondizioneTrasporto(){
    return condizioneTrasporto.getKey();
  }

  public void setCodiceCondizioneTrasporto(String cod){
    setChiaveCondizioneTrasporto(KeyHelper.replaceTokenObjectKey(getChiaveCondizioneTrasporto(), 2, cod));
  }

  public String getCodiceCondizioneTrasporto(){
    return KeyHelper.getTokenObjectKey(getChiaveCondizioneTrasporto(), 2);
  }

  public void setModalitaTrasporto(it.thera.thip.logis.lgb.ModalitaTrasporto t){
    modalitaTrasporto.setObject(t);
  }

  public it.thera.thip.logis.lgb.ModalitaTrasporto getModalitaTrasporto(){
    return (it.thera.thip.logis.lgb.ModalitaTrasporto) modalitaTrasporto.getObject();
  }

  public void setChiaveModalitaTrasporto(String key){
    modalitaTrasporto.setKey(key);
  }

  public String getChiaveModalitaTrasporto(){
    return modalitaTrasporto.getKey();
  }

  public void setCodiceModalitaTrasporto(String cod){
    setChiaveModalitaTrasporto(KeyHelper.replaceTokenObjectKey(getChiaveModalitaTrasporto(), 2, cod));
  }

  public String getCodiceModalitaTrasporto(){
    return KeyHelper.getTokenObjectKey(getChiaveModalitaTrasporto(), 2);
  }

  public void setMagLogicoTrasferimento(it.thera.thip.logis.lgb.MagLogico m){
    magLogicoTrasferimento.setObject(m);
  }

  public it.thera.thip.logis.lgb.MagLogico getMagLogicoTrasferimento(){
    return (it.thera.thip.logis.lgb.MagLogico) magLogicoTrasferimento.getObject();
  }

  public void setChiaveMagLogicoTrasferimento(String key){
    magLogicoTrasferimento.setKey(key);
  }

  public String getChiaveMagLogicoTrasferimento(){
    return magLogicoTrasferimento.getKey();
  }

  public void setCodiceMagLogicoTrasfer(String cod){
    setChiaveMagLogicoTrasferimento(KeyHelper.replaceTokenObjectKey(getChiaveMagLogicoTrasferimento(), 2, cod));
  }

  public String getCodiceMagLogicoTrasfer(){
    return KeyHelper.getTokenObjectKey(getChiaveMagLogicoTrasferimento(), 2);
  }

  public void setMagLogicoImpegno(it.thera.thip.logis.lgb.MagLogico m){
    magLogicoImpegno.setObject(m);
  }

  public it.thera.thip.logis.lgb.MagLogico getMagLogicoImpegno(){
    return (it.thera.thip.logis.lgb.MagLogico) magLogicoImpegno.getObject();
  }

  public void setChiaveMagLogicoImpegno(String key){
    magLogicoImpegno.setKey(key);
  }

  public String getChiaveMagLogicoImpegno(){
    return magLogicoImpegno.getKey();
  }

  public void setCodiceMagLogicoImpegno(String cod){
    setChiaveMagLogicoImpegno(KeyHelper.replaceTokenObjectKey(getChiaveMagLogicoImpegno(), 2, cod));
  }

  public String getCodiceMagLogicoImpegno(){
    return KeyHelper.getTokenObjectKey(getChiaveMagLogicoImpegno(), 2);
  }

  public void setAgente1(it.thera.thip.logis.lgb.Agente a){
    agente1.setObject(a);
  }

  public it.thera.thip.logis.lgb.Agente getAgente1(){
    return (it.thera.thip.logis.lgb.Agente) agente1.getObject();
  }

  public void setChiaveAgente1(String key){
    agente1.setKey(key);
  }

  public String getChiaveAgente1(){
    return agente1.getKey();
  }

  public void setCodiceAgente1(String cod){
    setChiaveAgente1(KeyHelper.replaceTokenObjectKey(getChiaveAgente1(), 2, cod));
  }

  public String getCodiceAgente1(){
    return KeyHelper.getTokenObjectKey(getChiaveAgente1(), 2);
  }

  public void setAgente2(it.thera.thip.logis.lgb.Agente a){
    agente2.setObject(a);
  }

  public it.thera.thip.logis.lgb.Agente getAgente2(){
    return (it.thera.thip.logis.lgb.Agente) agente2.getObject();
  }

  public void setChiaveAgente2(String key){
    agente2.setKey(key);
  }

  public String getChiaveAgente2(){
    return agente2.getKey();
  }

  public void setCodiceAgente2(String cod){
    setChiaveAgente2(KeyHelper.replaceTokenObjectKey(getChiaveAgente2(), 2, cod));
  }

  public String getCodiceAgente2(){
    return KeyHelper.getTokenObjectKey(getChiaveAgente2(), 2);
  }

  public void setVettore1(it.thera.thip.logis.lgb.Vettore a){
    vettore1.setObject(a);
  }

  public it.thera.thip.logis.lgb.Vettore getVettore1(){
    return (it.thera.thip.logis.lgb.Vettore) vettore1.getObject();
  }

  public void setChiaveVettore1(String key){
    vettore1.setKey(key);
  }

  public String getChiaveVettore1(){
    return vettore1.getKey();
  }

  public void setCodiceVettore1(String cod){
    setChiaveVettore1(KeyHelper.replaceTokenObjectKey(getChiaveVettore1(), 2, cod));
  }

  public String getCodiceVettore1(){
    return KeyHelper.getTokenObjectKey(getChiaveVettore1(), 2);
  }

  public void setVettore2(it.thera.thip.logis.lgb.Vettore a){
    vettore2.setObject(a);
  }

  public it.thera.thip.logis.lgb.Vettore getVettore2(){
    return (it.thera.thip.logis.lgb.Vettore) vettore2.getObject();
  }

  public void setChiaveVettore2(String key){
    vettore2.setKey(key);
  }

  public String getChiaveVettore2(){
    return vettore2.getKey();
  }

  public void setCodiceVettore2(String cod){
    setChiaveVettore2(KeyHelper.replaceTokenObjectKey(getChiaveVettore2(), 2, cod));
  }

  public String getCodiceVettore2(){
    return KeyHelper.getTokenObjectKey(getChiaveVettore2(), 2);
  }

  public void setDestinazione(it.thera.thip.logis.lgb.Destinazione d){
    destinazione.setObject(d);
  }

  public it.thera.thip.logis.lgb.Destinazione getDestinazione(){
    return (it.thera.thip.logis.lgb.Destinazione) destinazione.getObject();
  }

  public void setChiaveDestinazione(String key){
    destinazione.setKey(key);
  }

  public String getChiaveDestinazione(){
    return destinazione.getKey();
  }

  public void setCodiceDestinazione(String cod){
    setChiaveDestinazione(KeyHelper.replaceTokenObjectKey(getChiaveDestinazione(),3, cod));
  }

  public String getCodiceDestinazione(){
    return KeyHelper.getTokenObjectKey(getChiaveDestinazione(), 3);
  }

  public void setMagFisicoImpegno(it.thera.thip.logis.fis.MagFisico m){
    magFisicoImpegno.setObject(m);
    if(magFisicoImpegno!= null){
      setChiavePostazione(KeyHelper.replaceTokenObjectKey(getChiavePostazione(),1,m.getKey()));
      setChiaveOperatore(KeyHelper.replaceTokenObjectKey(getChiaveOperatore(),1,m.getKey()));
    }
  }

  public it.thera.thip.logis.fis.MagFisico getMagFisicoImpegno(){
    return (it.thera.thip.logis.fis.MagFisico) magFisicoImpegno.getObject();
  }

  public void setCodiceMagFisicoImpegno(String key){
    magFisicoImpegno.setKey(key);
    setChiavePostazione(KeyHelper.replaceTokenObjectKey(getChiavePostazione(),1,key));
    setChiaveOperatore(KeyHelper.replaceTokenObjectKey(getChiaveOperatore(),1,key));
  }

  public String getCodiceMagFisicoImpegno(){
    return magFisicoImpegno.getKey();
  }

  public void setMagFisicoTrasferimento(it.thera.thip.logis.fis.MagFisico m){
    magFisicoTrasferimento.setObject(m);
  }

  public it.thera.thip.logis.fis.MagFisico getMagFisicoTrasferimento(){
    return (it.thera.thip.logis.fis.MagFisico) magFisicoTrasferimento.getObject();
  }

  public void setCodiceMagFisicoTrasfer(String key){
    magFisicoTrasferimento.setKey(key);
  }

  public String getCodiceMagFisicoTrasfer(){
    return magFisicoTrasferimento.getKey();
  }

/*  public void setTimestampAperto(Timestamp t){
    timestampAperto=t;
  }

  public Timestamp getTimestampAperto(){
    return timestampAperto;
  }

  public void setTimestampChiuso(Timestamp t){
    timestampChiuso=t;
  }

  public Timestamp getTimestampChiuso(){
    return timestampChiuso;
  }*/

  public void setOperatore(it.thera.thip.logis.fis.Operatore o){
    operatore.setObject(o);
  }

  public it.thera.thip.logis.fis.Operatore getOperatore(){
    return (it.thera.thip.logis.fis.Operatore)operatore.getObject();
  }

  public void setChiaveOperatore(String key){
    operatore.setKey(key);
  }

  public String getChiaveOperatore(){
    return operatore.getKey();
  }

  public void setCodiceOperatore(String cod){
    setChiaveOperatore(KeyHelper.replaceTokenObjectKey(getChiaveOperatore(),2,cod));
  }

  public String getCodiceOperatore(){
    return KeyHelper.getTokenObjectKey(getChiaveOperatore(),2);
  }

  public void setPostazione(it.thera.thip.logis.fis.Postazione o){
    postazione.setObject(o);
  }

  public it.thera.thip.logis.fis.Postazione getPostazione(){
    return (it.thera.thip.logis.fis.Postazione)postazione.getObject();
  }

  public void setChiavePostazione(String key){
    postazione.setKey(key);
  }

  public String getChiavePostazione(){
    return postazione.getKey();
  }

  public void setCodicePostazione(String cod){
    setChiavePostazione(KeyHelper.replaceTokenObjectKey(getChiavePostazione(),2,cod));
  }

  public String getCodicePostazione(){
    return KeyHelper.getTokenObjectKey(getChiavePostazione(),2);
  }

  public void setFlagMissionato(boolean flag){
    flagMissionato=flag;
  }

  public boolean getFlagMissionato(){
    return flagMissionato;
  }

  public void setFlagMancante(boolean flag){
    flagMancante=flag;
  }

  public boolean getFlagMancante(){
    return flagMancante;
  }

  public void setFlagUtente1(String f){
    flagUtente1=f;
  }

  public String getFlagUtente1(){
    return flagUtente1;
  }

  public void setFlagUtente2(String f){
    flagUtente2=f;
  }

  public String getFlagUtente2(){
    return flagUtente2;
  }

  public void setFlagUtente3(String f){
    flagUtente3=f;
  }

  public String getFlagUtente3(){
    return flagUtente3;
  }

  public void setFlagUtente4(String f){
    flagUtente4=f;
  }

  public String getFlagUtente4(){
    return flagUtente4;
  }

  public void setFlagUtente5(String f){
    flagUtente5=f;
  }

  public String getFlagUtente5(){
    return flagUtente5;
  }

  public void setTipoPackingList(it.thera.thip.logis.lgb.TipoPackingList t){
    tipoPackingList.setObject(t);
  }

  public it.thera.thip.logis.lgb.TipoPackingList getTipoPackingList(){
    return (it.thera.thip.logis.lgb.TipoPackingList) tipoPackingList.getObject();
  }

  public void setChiaveTipoPackingList(String key){
    tipoPackingList.setKey(key);
  }

  public String getChiaveTipoPackingList(){
    return tipoPackingList.getKey();
  }

  public void setCodTipoPackingList(String cod){
    setChiaveTipoPackingList(KeyHelper.replaceTokenObjectKey(getChiaveTipoPackingList(), 2, cod));
  }

  public String getCodTipoPackingList(){
    return KeyHelper.getTokenObjectKey(getChiaveTipoPackingList(), 2);
  }

  public void setNote(String no){
    note=no;
  }

  public String getNote(){
    return note;
  }

  //04.12.06 - inizio
  public Integer getUnita() {
    return unita;
  }

  public void setUnita(Integer unit) {
    unita = unit;
  }

  public BigDecimal getVolume() {
    return volume;
  }

  public void setVolume(BigDecimal v) {
    volume = v;
  }

  public char getStatoSpedizione() {
    return statoSpedizione;
  }

  public void setStatoSpedizione(char s) {
    statoSpedizione = s;
  }

  public char getStatoLista() {
    return getStatoTestata();
  }

  public void setStatoLista(char s) {
    setStatoTestata(s);
  }

  public String getPuntoCarico() {
    return puntoCarico;
  }

  public void setPuntoCarico(String pc) {
    puntoCarico = pc;
  }

  public String getPrioritaLista() {
    return prioritaLista;
  }

  public void setPrioritaLista(String pl) {
    prioritaLista = pl;
  }

  public BigDecimal getPeso() {
    return getPesoTotale();
  }

  public void setPeso(BigDecimal p) {
    setPesoTotale(p);
  }

  public boolean getFlagGestioneSped() {
    return flagGestioneSpedizione;
  }

  public void setFlagGestioneSped(boolean m) {
    flagGestioneSpedizione = m;
  }

  public void setDataLista(Date d){
    setDataTestata(d);
  }

  public Date getDataLista(){
    return getDataTestata();
  }

  public Integer getCodicePianoSpedizione() {
    return codicePianoSpedizione;
  }

  public void setCodicePianoSpedizione(Integer cod) {
    codicePianoSpedizione = cod;
  }

  public Integer getCodicePianificazioneLista() {
    return codicePianificazioneLista;
  }

  public void setCodicePianificazioneLista(Integer cod) {
    codicePianificazioneLista = cod;
  }

  public Integer getAllestimentoTestate() {
    return getAllestimento();
  }

  public void setAllestimentoTestate(Integer a) {
    setAllestimento(a);
  }
  //04.12.06 - fine

  //20.10.08 - inizio
  protected boolean salvaPerRiga = false;

  public boolean getSalvaPerRiga() {
    return salvaPerRiga;
  }

  public void setSalvaPerRiga(boolean b) {
    salvaPerRiga = b;
  }
  //20.10.08 - fine
/**
 * Controlla il valore dei campiVariabili attributo rispetto l'obligatorietà e l'eventuale segmento
 * definito in modellazione sul tipoLista.
 */
  public Vector checkListaAttributi() {
    Vector errori = checkListaAttributo1();
    errori.addAll(checkListaAttributo2());
    errori.addAll(checkListaAttributo3());
    errori.addAll(checkListaAttributo4());
    errori.addAll(checkListaAttributo5());
    return errori;
  }

  protected Vector checkListaAttributo1() {
    if (getTipoLista() == null)
      return new Vector();
    Vector errori = new Vector();
    it.thera.thip.logis.lgb.CampoTrascodificaTipoLista ct1 = (it.thera.thip.logis.lgb.CampoTrascodificaTipoLista) getTipoLista().getAttributi().get(0);
    if (ct1.getAbilitazione() &&
        ct1.getFlagTestata()) {
      if (ct1.getObbligatorio() &&
          (getAttributo1() == null ||
           getAttributo1().equals(""))) {
        if (ct1.getValoreDef() != null &&
            !ct1.getValoreDef().equals("")) {
          setAttributo1(ct1.getValoreDef());
          return new Vector();
        } else
//          errori.addElement(new ErrorMessage("LOGIS01054", ct1.getCodiceCampo() + " obligatorio.")); Fix 15940
				errori.addElement(new ErrorMessage("LOGIST0290", new String[]{ct1.getCodiceCampo()}));// Fix 15940
      }
      if (ct1.getSegmento() != null &&
          getAttributo1() != null &&
          !getAttributo1().equals("")) {
        String key = KeyHelper.buildObjectKey(new String[] {ct1.getChiaveSegmento(), getAttributo1()});
        Tabella elemTab = null;
        try {
//          elemTab = Tabella.elementWithKey(key, PersistentObject.OPTIMISTIC_LOCK); Fix 15947
			elemTab = (Tabella)Tabella.readOnlyElementWithKey(Tabella.class, key); // Fix 15947
        } catch (SQLException ex) {
          ex.printStackTrace(Trace.excStream);
        }
        if (elemTab == null) {
          elemTab = (Tabella) Factory.createObject(Tabella.class);
          elemTab.setCodiceSegmento(ct1.getChiaveSegmento());
          elemTab.setCodiceElemento(getAttributo1());
//          elemTab.setDescrizioneBr("Inserimento automatico."); Fix 15940
					elemTab.setDescrizioneBr(com.thera.thermfw.base.ResourceLoader.getString(MovimentoLogisThip.MLT_RES, "gen0001"));// Fix 15940
         try {
            elemTab.save();
          } catch (SQLException ex) {
            ex.printStackTrace(Trace.excStream);
          }
        }
      }
    }
    return errori;
  }

  protected Vector checkListaAttributo2() {
    if (getTipoLista() == null)
      return new Vector();
    Vector errori = new Vector();
    it.thera.thip.logis.lgb.CampoTrascodificaTipoLista ct2 = (it.thera.thip.logis.lgb.CampoTrascodificaTipoLista) getTipoLista().getAttributi().get(1);
    if (ct2.getAbilitazione() &&
        ct2.getFlagTestata()) {
      if (ct2.getObbligatorio() &&
          (getAttributo2() == null ||
           getAttributo2().equals(""))) {
        if (ct2.getValoreDef() != null &&
            !ct2.getValoreDef().equals("")) {
          setAttributo2(ct2.getValoreDef());
          return new Vector();
        } else
//          errori.addElement(new ErrorMessage("LOGIS01054", ct2.getCodiceCampo() + " obligatorio.")); Fix 15940
				errori.addElement(new ErrorMessage("LOGIST0290", new String[]{ct2.getCodiceCampo()}));// Fix 15940
      }
      if (ct2.getSegmento() != null &&
          getAttributo2() != null &&
          !getAttributo2().equals("")) {
        String key = KeyHelper.buildObjectKey(new String[] {ct2.getChiaveSegmento(), getAttributo2()});
        Tabella elemTab = null;
        try {
//          elemTab = Tabella.elementWithKey(key, PersistentObject.OPTIMISTIC_LOCK); Fix 15947
			elemTab = (Tabella)Tabella.readOnlyElementWithKey(Tabella.class, key); // Fix 15947
        } catch (SQLException ex) {
          ex.printStackTrace(Trace.excStream);
        }
        if (elemTab == null) {
          elemTab = (Tabella) Factory.createObject(Tabella.class);
          elemTab.setCodiceSegmento(ct2.getChiaveSegmento());
          elemTab.setCodiceElemento(getAttributo2());
//          elemTab.setDescrizioneBr("Inserimento automatico."); Fix 15940
					elemTab.setDescrizioneBr(com.thera.thermfw.base.ResourceLoader.getString(MovimentoLogisThip.MLT_RES, "gen0001"));// Fix 15940
         try {
            elemTab.save();
          } catch (SQLException ex) {
            ex.printStackTrace(Trace.excStream);
          }
        }
      }
    }
    return errori;
  }

  protected Vector checkListaAttributo3() {
    if (getTipoLista() == null)
      return new Vector();
    Vector errori = new Vector();
    it.thera.thip.logis.lgb.CampoTrascodificaTipoLista ct3 = (it.thera.thip.logis.lgb.CampoTrascodificaTipoLista) getTipoLista().getAttributi().get(2);
    if (ct3.getAbilitazione() &&
        ct3.getFlagTestata()) {
      if (ct3.getObbligatorio() &&
          (getAttributo3() == null ||
           getAttributo3().equals(""))) {
        if (ct3.getValoreDef() != null &&
            !ct3.getValoreDef().equals("")) {
          setAttributo3(ct3.getValoreDef());
          return new Vector();
        } else
//          errori.addElement(new ErrorMessage("LOGIS01054", ct3.getCodiceCampo() + " obligatorio.")); Fix 15940
					errori.addElement(new ErrorMessage("LOGIST0290", new String[]{ct3.getCodiceCampo()}));// Fix 15940
      }
      if (ct3.getSegmento() != null &&
          getAttributo3() != null &&
          !getAttributo3().equals("")) {
        String key = KeyHelper.buildObjectKey(new String[] {ct3.getChiaveSegmento(), getAttributo3()});
        Tabella elemTab = null;
        try {
//          elemTab = Tabella.elementWithKey(key, PersistentObject.OPTIMISTIC_LOCK); Fix 15947
			elemTab = (Tabella)Tabella.readOnlyElementWithKey(Tabella.class, key); // Fix 15947
        } catch (SQLException ex) {
          ex.printStackTrace(Trace.excStream);
        }
        if (elemTab == null) {
          elemTab = (Tabella) Factory.createObject(Tabella.class);
          elemTab.setCodiceSegmento(ct3.getChiaveSegmento());
          elemTab.setCodiceElemento(getAttributo3());
//          elemTab.setDescrizioneBr("Inserimento automatico."); Fix 15940
					elemTab.setDescrizioneBr(com.thera.thermfw.base.ResourceLoader.getString(MovimentoLogisThip.MLT_RES, "gen0001"));// Fix 15940
          try {
            elemTab.save();
          } catch (SQLException ex) {
            ex.printStackTrace(Trace.excStream);
          }
        }
      }
    }
    return errori;
  }

  protected Vector checkListaAttributo4() {
    if (getTipoLista() == null)
      return new Vector();
    Vector errori = new Vector();
    it.thera.thip.logis.lgb.CampoTrascodificaTipoLista ct4 = (it.thera.thip.logis.lgb.CampoTrascodificaTipoLista) getTipoLista().getAttributi().get(3);
    if (ct4.getAbilitazione() &&
        ct4.getFlagTestata()) {
      if (ct4.getObbligatorio() &&
          (getAttributo4() == null ||
           getAttributo4().equals(""))) {
        if (ct4.getValoreDef() != null &&
            !ct4.getValoreDef().equals("")) {
          setAttributo4(ct4.getValoreDef());
          return new Vector();
        } else
//          errori.addElement(new ErrorMessage("LOGIS01054", ct4.getCodiceCampo() + " obligatorio.")); Fix 15940
				errori.addElement(new ErrorMessage("LOGIST0290", new String[]{ct4.getCodiceCampo()}));// Fix 15940
     }
      if (ct4.getSegmento() != null &&
          getAttributo4() != null &&
          !getAttributo4().equals("")) {
        String key = KeyHelper.buildObjectKey(new String[] {ct4.getChiaveSegmento(), getAttributo4()});
        Tabella elemTab = null;
        try {
//          elemTab = Tabella.elementWithKey(key, PersistentObject.OPTIMISTIC_LOCK); Fix 15947
			elemTab = (Tabella)Tabella.readOnlyElementWithKey(Tabella.class, key); // Fix 15947
        } catch (SQLException ex) {
          ex.printStackTrace(Trace.excStream);
        }
        if (elemTab == null) {
          elemTab = (Tabella) Factory.createObject(Tabella.class);
          elemTab.setCodiceSegmento(ct4.getChiaveSegmento());
          elemTab.setCodiceElemento(getAttributo4());
//          elemTab.setDescrizioneBr("Inserimento automatico."); Fix 15940
					elemTab.setDescrizioneBr(com.thera.thermfw.base.ResourceLoader.getString(MovimentoLogisThip.MLT_RES, "gen0001"));// Fix 15940
          try {
            elemTab.save();
          } catch (SQLException ex) {
            ex.printStackTrace(Trace.excStream);
          }
        }
      }
    }
    return errori;
  }

  protected Vector checkListaAttributo5() {
    if (getTipoLista() == null)
      return new Vector();
    Vector errori = new Vector();
    it.thera.thip.logis.lgb.CampoTrascodificaTipoLista ct5 = (it.thera.thip.logis.lgb.CampoTrascodificaTipoLista) getTipoLista().getAttributi().get(4);
    if (ct5.getAbilitazione() &&
        ct5.getFlagTestata()) {
      if (ct5.getObbligatorio() &&
          (getAttributo5() == null ||
           getAttributo5().equals(""))) {
        if (ct5.getValoreDef() != null &&
            !ct5.getValoreDef().equals("")) {
          setAttributo5(ct5.getValoreDef());
          return new Vector();
        } else
//          errori.addElement(new ErrorMessage("LOGIS01054", ct5.getCodiceCampo() + " obligatorio.")); Fix 15940
					errori.addElement(new ErrorMessage("LOGIST0290", new String[]{ct5.getCodiceCampo()}));// Fix 15940
     }
      if (ct5.getSegmento() != null &&
          getAttributo5() != null &&
          !getAttributo5().equals("")) {
        String key = KeyHelper.buildObjectKey(new String[] {ct5.getChiaveSegmento(), getAttributo5()});
        Tabella elemTab = null;
        try {
//          elemTab = Tabella.elementWithKey(key, PersistentObject.OPTIMISTIC_LOCK); Fix 15947
			elemTab = (Tabella)Tabella.readOnlyElementWithKey(Tabella.class, key); // Fix 15947
        } catch (SQLException ex) {
          ex.printStackTrace(Trace.excStream);
        }
        if (elemTab == null) {
          elemTab = (Tabella) Factory.createObject(Tabella.class);
          elemTab.setCodiceSegmento(ct5.getChiaveSegmento());
          elemTab.setCodiceElemento(getAttributo5());
//          elemTab.setDescrizioneBr("Inserimento automatico."); Fix 15940
					elemTab.setDescrizioneBr(com.thera.thermfw.base.ResourceLoader.getString(MovimentoLogisThip.MLT_RES, "gen0001"));// Fix 15940
          try {
            elemTab.save();
          } catch (SQLException ex) {
            ex.printStackTrace(Trace.excStream);
          }
        }
      }
    }
    return errori;
  }

/**
 * Controlla il valore dei campiVariabili attributo rispetto l'obligatorietà e l'eventuale segmento
 * definito in modellazione sul tipoLista.
 */
  protected Vector checkStatistiche() {
    Vector errori = checkStatistica1();
    errori.addAll(checkStatistica2());
    errori.addAll(checkStatistica3());
    errori.addAll(checkStatistica4());
    errori.addAll(checkStatistica5());
    return errori;
  }

  protected Vector checkStatistica1() {
    if (getSocieta() == null)
      return new Vector();
    Vector errori = new Vector();
    CampoTrascodifica ct1 = (CampoTrascodifica) getSocieta().getStatistiche().get(0);
    if (ct1.getAbilitazione()) {
      if (ct1.getObbligatorio() &&
          (getStatisticaPartner1() == null ||
           getStatisticaPartner1().equals(""))) {
        if (getDestinatario() != null &&
            getDestinatario().getStatistica1() != null &&
            !getDestinatario().getStatistica1().equals("")) {
          setStatisticaPartner1(getDestinatario().getStatistica1());
        } else {
          if (ct1.getValoreDef() != null &&
              !ct1.getValoreDef().equals("")) {
            setStatisticaPartner1(ct1.getValoreDef());
            return new Vector();
          } else
//            errori.addElement(new ErrorMessage("LOGIS01054", ct1.getCodiceCampo() + " obligatorio.")); Fix 15940
					errori.addElement(new ErrorMessage("LOGIST0290", new String[]{ct1.getCodiceCampo()}));// Fix 15940
       }
      }
      if (ct1.getSegmento() != null &&
          getStatisticaPartner1() != null &&
          !getStatisticaPartner1().equals("")) {
        String key = KeyHelper.buildObjectKey(new String[] {ct1.getChiaveSegmento(), getStatisticaPartner1()});
        Tabella elemTab = null;
        try {
//          elemTab = Tabella.elementWithKey(key, PersistentObject.OPTIMISTIC_LOCK); Fix 15947
			elemTab = (Tabella)Tabella.readOnlyElementWithKey(Tabella.class, key); // Fix 15947
        } catch (SQLException ex) {
          ex.printStackTrace(Trace.excStream);
        }
        if (elemTab == null) {
          elemTab = (Tabella) Factory.createObject(Tabella.class);
          elemTab.setCodiceSegmento(ct1.getChiaveSegmento());
          elemTab.setCodiceElemento(getStatisticaPartner1());
//          elemTab.setDescrizioneBr("Inserimento automatico."); Fix 15940
					elemTab.setDescrizioneBr(com.thera.thermfw.base.ResourceLoader.getString(MovimentoLogisThip.MLT_RES, "gen0001"));// Fix 15940
          try {
            elemTab.save();
          } catch (SQLException ex) {
            ex.printStackTrace(Trace.excStream);
          }
        }
      }
    }
    return errori;
  }

  protected Vector checkStatistica2() {
    if (getSocieta() == null)
      return new Vector();
    Vector errori = new Vector();
    CampoTrascodifica ct1 = (CampoTrascodifica) getSocieta().getStatistiche().get(1);
    if (ct1.getAbilitazione()) {
      if (ct1.getObbligatorio() &&
          (getStatisticaPartner2() == null ||
           getStatisticaPartner2().equals(""))) {
        if (getDestinatario() != null &&
            getDestinatario().getStatistica2() != null &&
            !getDestinatario().getStatistica2().equals("")) {
          setStatisticaPartner2(getDestinatario().getStatistica2());
        } else {
          if (ct1.getValoreDef() != null &&
              !ct1.getValoreDef().equals("")) {
            setStatisticaPartner2(ct1.getValoreDef());
            return new Vector();
          } else
//            errori.addElement(new ErrorMessage("LOGIS01054", ct1.getCodiceCampo() + " obligatorio.")); Fix 15940
						errori.addElement(new ErrorMessage("LOGIST0290", new String[]{ct1.getCodiceCampo()}));// Fix 15940
        }
      }
      if (ct1.getSegmento() != null &&
          getStatisticaPartner2() != null &&
          !getStatisticaPartner2().equals("")) {
        String key = KeyHelper.buildObjectKey(new String[] {ct1.getChiaveSegmento(), getStatisticaPartner2()});
        Tabella elemTab = null;
        try {
//          elemTab = Tabella.elementWithKey(key, PersistentObject.OPTIMISTIC_LOCK); Fix 15947
			elemTab = (Tabella)Tabella.readOnlyElementWithKey(Tabella.class, key); // Fix 15947
        } catch (SQLException ex) {
          ex.printStackTrace(Trace.excStream);
        }
        if (elemTab == null) {
          elemTab = (Tabella) Factory.createObject(Tabella.class);
          elemTab.setCodiceSegmento(ct1.getChiaveSegmento());
          elemTab.setCodiceElemento(getStatisticaPartner2());
//          elemTab.setDescrizioneBr("Inserimento automatico."); Fix 15940
					elemTab.setDescrizioneBr(com.thera.thermfw.base.ResourceLoader.getString(MovimentoLogisThip.MLT_RES, "gen0001"));// Fix 15940
          try {
            elemTab.save();
          } catch (SQLException ex) {
            ex.printStackTrace(Trace.excStream);
          }
        }
      }
    }
    return errori;
  }

  protected Vector checkStatistica3() {
    if (getSocieta() == null)
      return new Vector();
    Vector errori = new Vector();
    CampoTrascodifica ct1 = (CampoTrascodifica) getSocieta().getStatistiche().get(2);
    if (ct1.getAbilitazione()) {
      if (ct1.getObbligatorio() &&
          (getStatisticaPartner3() == null ||
           getStatisticaPartner3().equals(""))) {
        if (getDestinatario() != null &&
            getDestinatario().getStatistica3() != null &&
            !getDestinatario().getStatistica3().equals("")) {
          setStatisticaPartner(getDestinatario().getStatistica3(), 2);
        } else {
          if (ct1.getValoreDef() != null &&
              !ct1.getValoreDef().equals("")) {
            setStatisticaPartner(ct1.getValoreDef(), 2);
            return new Vector();
          } else
//            errori.addElement(new ErrorMessage("LOGIS01054", ct1.getCodiceCampo() + " obligatorio.")); Fix 15940
					errori.addElement(new ErrorMessage("LOGIST0290", new String[]{ct1.getCodiceCampo()}));// Fix 15940
        }
      }
      if (ct1.getSegmento() != null &&
          getStatisticaPartner3() != null &&
          !getStatisticaPartner3().equals("")) {
        String key = KeyHelper.buildObjectKey(new String[] {ct1.getChiaveSegmento(), getStatisticaPartner3()});
        Tabella elemTab = null;
        try {
//					elemTab = Tabella.elementWithKey(key, PersistentObject.OPTIMISTIC_LOCK); Fix 15947
         elemTab = (Tabella)Tabella.readOnlyElementWithKey(Tabella.class, key); // Fix 15947
        } catch (SQLException ex) {
          ex.printStackTrace(Trace.excStream);
        }
        if (elemTab == null) {
          elemTab = (Tabella) Factory.createObject(Tabella.class);
          elemTab.setCodiceSegmento(ct1.getChiaveSegmento());
          elemTab.setCodiceElemento(getStatisticaPartner3());
//          elemTab.setDescrizioneBr("Inserimento automatico."); Fix 15940
					elemTab.setDescrizioneBr(com.thera.thermfw.base.ResourceLoader.getString(MovimentoLogisThip.MLT_RES, "gen0001"));// Fix 15940
          try {
            elemTab.save();
          } catch (SQLException ex) {
            ex.printStackTrace(Trace.excStream);
          }
        }
      }
    }
    return errori;
  }

  protected Vector checkStatistica4() {
    if (getSocieta() == null)
      return new Vector();
    Vector errori = new Vector();
    CampoTrascodifica ct1 = (CampoTrascodifica) getSocieta().getStatistiche().get(3);
    if (ct1.getAbilitazione()) {
      if (ct1.getObbligatorio() &&
          (getStatisticaPartner4() == null ||
           getStatisticaPartner4().equals(""))) {
        if (getDestinatario() != null &&
            getDestinatario().getStatistica4() != null &&
            !getDestinatario().getStatistica4().equals("")) {
          setStatisticaPartner4(getDestinatario().getStatistica4());
        } else {
          if (ct1.getValoreDef() != null &&
              !ct1.getValoreDef().equals("")) {
            setStatisticaPartner4(ct1.getValoreDef());
            return new Vector();
          } else
//            errori.addElement(new ErrorMessage("LOGIS01054", ct1.getCodiceCampo() + " obligatorio.")); Fix 15940
						errori.addElement(new ErrorMessage("LOGIST0290", new String[]{ct1.getCodiceCampo()}));// Fix 15940
        }
      }
      if (ct1.getSegmento() != null &&
          getStatisticaPartner4() != null &&
          !getStatisticaPartner4().equals("")) {
        String key = KeyHelper.buildObjectKey(new String[] {ct1.getChiaveSegmento(), getStatisticaPartner4()});
        Tabella elemTab = null;
        try {
//          elemTab = Tabella.elementWithKey(key, PersistentObject.OPTIMISTIC_LOCK); Fix 15947
			elemTab = (Tabella)Tabella.readOnlyElementWithKey(Tabella.class, key); // Fix 15947
        } catch (SQLException ex) {
          ex.printStackTrace(Trace.excStream);
        }
        if (elemTab == null) {
          elemTab = (Tabella) Factory.createObject(Tabella.class);
          elemTab.setCodiceSegmento(ct1.getChiaveSegmento());
          elemTab.setCodiceElemento(getStatisticaPartner4());
//          elemTab.setDescrizioneBr("Inserimento automatico."); Fix 15940
					elemTab.setDescrizioneBr(com.thera.thermfw.base.ResourceLoader.getString(MovimentoLogisThip.MLT_RES, "gen0001"));// Fix 15940
          try {
            elemTab.save();
          } catch (SQLException ex) {
            ex.printStackTrace(Trace.excStream);
          }
        }
      }
    }
    return errori;
  }

  protected Vector checkStatistica5() {
    if (getSocieta() == null)
      return new Vector();
    Vector errori = new Vector();
    CampoTrascodifica ct1 = (CampoTrascodifica) getSocieta().getStatistiche().get(4);
    if (ct1.getAbilitazione()) {
      if (ct1.getObbligatorio() &&
          (getStatisticaPartner5() == null ||
           getStatisticaPartner5().equals(""))) {
        if (getDestinatario() != null &&
            getDestinatario().getStatistica5() != null &&
            !getDestinatario().getStatistica5().equals("")) {
          setStatisticaPartner5(getDestinatario().getStatistica5());
        } else {
          if (ct1.getValoreDef() != null &&
              !ct1.getValoreDef().equals("")) {
            setStatisticaPartner5(ct1.getValoreDef());
            return new Vector();
          } else
//            errori.addElement(new ErrorMessage("LOGIS01054", ct1.getCodiceCampo() + " obligatorio.")); Fix 15940
						errori.addElement(new ErrorMessage("LOGIST0290", new String[]{ct1.getCodiceCampo()}));// Fix 15940
        }
      }
      if (ct1.getSegmento() != null &&
          getStatisticaPartner5() != null &&
          !getStatisticaPartner5().equals("")) {
        String key = KeyHelper.buildObjectKey(new String[] {ct1.getChiaveSegmento(), getStatisticaPartner5()});
        Tabella elemTab = null;
        try {
//          elemTab = Tabella.elementWithKey(key, PersistentObject.OPTIMISTIC_LOCK); Fix 15947
			elemTab = (Tabella)Tabella.readOnlyElementWithKey(Tabella.class, key); // Fix 15947
        } catch (SQLException ex) {
          ex.printStackTrace(Trace.excStream);
        }
        if (elemTab == null) {
          elemTab = (Tabella) Factory.createObject(Tabella.class);
          elemTab.setCodiceSegmento(ct1.getChiaveSegmento());
          elemTab.setCodiceElemento(getStatisticaPartner5());
//          elemTab.setDescrizioneBr("Inserimento automatico."); Fix 15940
					elemTab.setDescrizioneBr(com.thera.thermfw.base.ResourceLoader.getString(MovimentoLogisThip.MLT_RES, "gen0001"));// Fix 15940
          try {
            elemTab.save();
          } catch (SQLException ex) {
            ex.printStackTrace(Trace.excStream);
          }
        }
      }
    }
    return errori;
  }

  public Vector checkListaValori() {
    Vector errori = checkListaValore1();
    errori.addAll(checkListaValore2());
    errori.addAll(checkListaValore3());
    return errori;
  }

  protected Vector checkListaValore1() {
    if (getTipoLista() == null)
      return new Vector();
    Vector errori = new Vector();
    it.thera.thip.logis.lgb.CampoNumeroTipoLista ct1 = (it.thera.thip.logis.lgb.CampoNumeroTipoLista) getTipoLista().getCampiNumero().get(0);
    if (ct1.getAbilitazione() &&
        ct1.getFlagTestata()) {
      if (ct1.getObbligatorio() &&
          getValori1() == null) {
        if (ct1.getValoreDef() != null)
          setValori1(ct1.getValoreDef());
        else
//          errori.addElement(new ErrorMessage("LOGIS01054", ct1.getCodiceCampo() + " obligatorio.")); Fix 15940
					errori.addElement(new ErrorMessage("LOGIST0290", new String[]{ct1.getCodiceCampo()}));// Fix 15940
      }
    }
    return errori;
  }

  protected Vector checkListaValore2() {
    if (getTipoLista() == null)
      return new Vector();
    Vector errori = new Vector();
    it.thera.thip.logis.lgb.CampoNumeroTipoLista ct1 = (it.thera.thip.logis.lgb.CampoNumeroTipoLista) getTipoLista().getCampiNumero().get(1);
    if (ct1.getAbilitazione() &&
        ct1.getFlagTestata()) {
      if (ct1.getObbligatorio() &&
          getValori2() == null) {
        if (ct1.getValoreDef() != null)
          setValori2(ct1.getValoreDef());
        else
//          errori.addElement(new ErrorMessage("LOGIS01054", ct1.getCodiceCampo() + " obligatorio.")); Fix 15940
					errori.addElement(new ErrorMessage("LOGIST0290", new String[]{ct1.getCodiceCampo()}));// Fix 15940
      }
    }
    return errori;
  }

  protected Vector checkListaValore3() {
    if (getTipoLista() == null)
      return new Vector();
    Vector errori = new Vector();
    it.thera.thip.logis.lgb.CampoNumeroTipoLista ct1 = (it.thera.thip.logis.lgb.CampoNumeroTipoLista) getTipoLista().getCampiNumero().get(2);
    if (ct1.getAbilitazione() &&
        ct1.getFlagTestata()) {
      if (ct1.getObbligatorio() &&
          getValori3() == null) {
        if (ct1.getValoreDef() != null)
          setValori3(ct1.getValoreDef());
        else
//          errori.addElement(new ErrorMessage("LOGIS01054", ct1.getCodiceCampo() + " obligatorio.")); Fix 15940
					errori.addElement(new ErrorMessage("LOGIST0290", new String[]{ct1.getCodiceCampo()}));// Fix 15940
      }
    }
    return errori;
  }

  public Vector checkListaDate() {
    Vector errori = checkListaData1();
    errori.addAll(checkListaData2());
    errori.addAll(checkListaData3());
    return errori;
  }

  protected Vector checkListaData1() {
    if (getTipoLista() == null)
      return new Vector();
    Vector errori = new Vector();
    it.thera.thip.logis.lgb.CampoDataTipoLista ct1 = (it.thera.thip.logis.lgb.CampoDataTipoLista) getTipoLista().getDateLista().get(0);
    if (ct1.getAbilitazione() &&
        ct1.getFlagTestata()) {
      if (ct1.getObbligatorio() &&
          getData1() == null) {
        if (ct1.getValoreDef() != null)
          setData1(ct1.getValoreDef());
        else
//          errori.addElement(new ErrorMessage("LOGIS01054", ct1.getCodiceCampo() + " obligatorio.")); Fix 15940
					errori.addElement(new ErrorMessage("LOGIST0290", new String[]{ct1.getCodiceCampo()}));// Fix 15940
      }
    }
    return errori;
  }

  protected Vector checkListaData2() {
    if (getTipoLista() == null)
      return new Vector();
    Vector errori = new Vector();
    it.thera.thip.logis.lgb.CampoDataTipoLista ct1 = (it.thera.thip.logis.lgb.CampoDataTipoLista) getTipoLista().getDateLista().get(1);
    if (ct1.getAbilitazione() &&
        ct1.getFlagTestata()) {
      if (ct1.getObbligatorio() &&
          getData2() == null) {
        if (ct1.getValoreDef() != null)
          setData2(ct1.getValoreDef());
        else
//          errori.addElement(new ErrorMessage("LOGIS01054", ct1.getCodiceCampo() + " obligatorio.")); Fix 15940
					errori.addElement(new ErrorMessage("LOGIST0290", new String[]{ct1.getCodiceCampo()}));// Fix 15940
      }
    }
    return errori;
  }

  protected Vector checkListaData3() {
    if (getTipoLista() == null)
      return new Vector();
    Vector errori = new Vector();
    it.thera.thip.logis.lgb.CampoDataTipoLista ct1 = (it.thera.thip.logis.lgb.CampoDataTipoLista) getTipoLista().getDateLista().get(2);
    if (ct1.getAbilitazione() &&
        ct1.getFlagTestata()) {
      if (ct1.getObbligatorio() &&
          getData3() == null) {
        if (ct1.getValoreDef() != null)
          setData3(ct1.getValoreDef());
        else
//          errori.addElement(new ErrorMessage("LOGIS01054", ct1.getCodiceCampo() + " obligatorio.")); Fix 15940
					errori.addElement(new ErrorMessage("LOGIST0290", new String[]{ct1.getCodiceCampo()}));// Fix 15940
      }
    }
    return errori;
  }

  protected boolean testTrasmissioneDoc(DocumentoVenditaRiga dvr) {
    //10.01.07 - inizio
    /*if (dvr.getTipoRiga() == it.thera.thip.base.comuniVenAcq.TipoRiga.SERVIZIO)
      return false;
        // Verifica che la riga sia destinata a produrre movimenti di magazzino e quindi
        //  implicitamente è gestita nella logistica operativa.
        //  (testare la valorizzazione di almeno una causaleMagazzino associata alla causaleRiga)
    if (dvr.getCausaleRiga() == null ||
        !dvr.isAbilitatoMovimentiMagazzino())
      return false;
//    if (dvr.getStatoAvanzamento() != it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO)
//      return false;
        // Non gestisco le righe secondarie di KIT gestiti a magazzino.
    if (dvr instanceof DocumentoVenRigaSec)
      if (((DocumentoVenRigaSec)dvr).getRigaPrimaria().getArticolo().getTipoParte() == ArticoloDatiIdent.KIT_GEST)
        return false;
    if (CfgLogTxArtic.compatibile(dvr.getArticolo()))
      return true;
    return false;*/
    return true;
  }

  protected boolean testTrasmissioneDoc(DocumentoAcquistoRiga dar) {
    return true;
  }
  //10.01.07 - fine

  protected static String UM_RIF = "UMRif";
  protected static String UM_PRM = "UMPrm";
  protected static String UM_SEC = "UMSec";

/**
 * Chiama il metodo di thip per la save dell'oggetto.
 * E' compito del chiamante settare a proprio uso gli attributi di interesse.
 *
 * @param elencoRighe elenco delle righe associate alla testata corrente
 *
 * @return ErrorMessage eventuale errore generato da host che produce rollback su Logis.
 */
  public ErrorMessage salvaSuThipVen(Vector elencoRighe) {//10.01.07
    int rc = ErrorCodes.NO_ROWS_UPDATED;
    DocumentoVendita dv = (DocumentoVendita) Factory.createObject(DocumentoVendita.class);
    DocumentoVendita dvEsistente = (DocumentoVendita) Factory.createObject(DocumentoVendita.class);//Fix 22600
    dv.setIdAzienda(getIdAzienda());
    if (getCodice().length() > 5) {
      dv.setAnnoDocumento(getCodice().substring(1,5));//09.10.08
      dv.setNumeroDocumento(getCodice().substring(5));//09.10.08
    } else
      dv.setAnnoDocumento(getCodice());
    boolean res = false;
    try {
      res = dv.retrieve(PersistentObject.OPTIMISTIC_LOCK);
      DocumentoTestata.raggruppaRigheStessaRigaOrdine(dv);//13.06.13
    } catch (SQLException ex) {
      ex.printStackTrace(Trace.excStream);
    }
    if (!res)
      dv = creoNuovoDocumento(dv);
    //Fix 22600 inizio
    else{
      try {
      	dvEsistente.setEqual(dv);
       }
       catch (CopyException ex2) {
         ex2.printStackTrace(Trace.excStream);
       }
      if (ParametriLogis.SHOW_TRACE)
//        System.out.println("Recuperato documento vendita " + dv.getNumeroDocumento()); Fix 15940
				System.out.println(ResourceLoader.getString(RES_FILE,"gen0001", new String[]{dv.getNumeroDocumento()})); // Fix 15940
    }
    //Fix 22600 fine 
    if (dv.isCollegataAMagazzino()) {
// Fix 15940 inizio
//      System.out.println("Documento " + dv.getNumeroDocumento()+" collegato a magazzino");
//      return new ErrorMessage("LOGIS01054", "Il documento " + dv.getKey().replace(KeyHelper.KEY_SEPARATOR.charAt(0), '/') + " è già collegato a magazzino.");
			System.out.println(ResourceLoader.getString(RES_FILE,"gen0002", new String[]{dv.getNumeroDocumento()}));
			return new ErrorMessage("LOGIST0362", new String[]{KeyHelper.formatKeyString(dv.getKey())});
// Fix 15940 fine
    }
    if (dv == null)
//			return new ErrorMessage("LOGIS01054", "Errore nella creazione del nuovo documento vendita su ERP"); Fix 15940
			return new ErrorMessage("LOGIST0363"); // Fix 15940
    if (dv.getNumeratoreHandler() != null)
      dv.getNumeratoreHandler().setDataDocumento(getDataTestata());
    //15.03.11 - inizio
    // le righe seguenti sono state commentate perchè non è corretto che la chiusura
    // della lista modifichi alcuni attributi sulla testata del documento
    // le righe commentate sono state trasferite nel metodo creoNuovoDocumento(dv)
    /*dv.setNumeroRifIntestatario(getRiferimentoPartner());
    dv.setDataRifIntestatario(getDataRiferimentoPartner());
//09438 24/06/2008 MM inizio
//    dv.setIdDenAbt(getCodicePartnerDestinazione().substring(1));
//    if (getDestinazioneAlternativa() != null) {
//      dv.setRagioneSocaleDest(getDestinazioneAlternativa().getRagioneSociale1());
//      dv.setIndirizzoDestinatario(getDestinazioneAlternativa().getVia());
//      dv.setLocalitaDestinatario(getDestinazioneAlternativa().getLocalita());
//      dv.setCAPDestinatario(getDestinazioneAlternativa().getCap());
//      dv.setIdNazioneDen(getDestinazioneAlternativa().getNazione());
//    }
//09438 24/06/2008 MM fine
    dv.setIdClienteFat(getCodicePartnerFatturazione().substring(1));
    dv.setDataConsegnaRichiesta(getDataConsegnaRichiesta());
    dv.setDataConsegnaConfermata(getDataConsegnaConfermata());
    dv.setIdMagazzino(getCodiceMagLogicoImpegno());
    dv.setIdMagazzinoTra(getCodiceMagLogicoTrasfer());
    dv.setIdCommessa(getAttributo3());
    dv.setIdLingua(getCodiceLingua());
    dv.setIdZona(getZonaGenerica());
    if (getPriorita() !=null || !getPriorita().equals(""))
      dv.setPriorita((new Short(getPriorita())).shortValue());
    dv.setIdValuta(getCodiceDivisa());
    //dv.setIdModPagamento(getAttributo2());22.02.11
    dv.setDataInizioPagamento(getData1());
    dv.setIdAgente(getCodiceAgente1());
    dv.setIdAgenteSub(getCodiceAgente2());*/
    //15.03.11 - fine
    dv.setIdModSpedizione(getCodiceModalitaTrasporto());//30.05.06
    dv.setDescrModalitaSpedizione(getDescModalitaTrasporto());
    dv.setIdModConsegna(getCodiceCondizioneTrasporto());//30.05.06
    dv.setDescrModalitaConsegna(getDescCondizioniTrasporto());
    dv.setIdAspettoEsn(getCodiceTipoImballo());
    dv.setDescrAspettoEsteriore(getDescTipoImballo());
    //10.07.06 - inizio
    /*if (getVettore1Alternativo() != null)
      dv.setDescrVettore1(getVettore1Alternativo().getRagioneSociale1());*/
    String keyVett = "";
    // se il vettore è cambiato modifico la text area (concordato con PM)
    if ((getCodiceVettore1() != null && !getCodiceVettore1().equals(dv.getIdVettore1())) ||
        (getCodiceVettore1() == null && dv.getIdVettore1() != null))
      dv.setDescrVettore1(descIndirizzoFormattata(getVettore1Alternativo()));
    keyVett = KeyHelper.buildObjectKey(new String[] {getIdAzienda(), getCodiceVettore1()});
    dv.setVettore1Key(keyVett);
    if ((getCodiceVettore2() != null && !getCodiceVettore2().equals(dv.getIdVettore2())) ||
        (getCodiceVettore2() == null && dv.getIdVettore2() != null))
      dv.setDescrVettore2(descIndirizzoFormattata(getVettore2Alternativo()));
    keyVett = KeyHelper.buildObjectKey(new String[] {getIdAzienda(), getCodiceVettore2()});
    dv.setVettore2Key(keyVett);
    //10.07.06 - fine
    //16.01.07 - inizio
    /*if (getValori(0) != null)
      dv.setNumeroColli(getValori(0).intValue());
    dv.setPesoLordo(getValori(1));
    dv.setPesoNetto(getValori(2));*/
    if (getUnita() != null)
      dv.setNumeroColli(getUnita().intValue());
    //Fix 12507 20.04.10 inizio
    if(getPeso() != null && getPesoNetto() != null)
    {
       dv.setPesoLordo(getPeso());
       dv.setPesoNetto(getPesoNetto());
       dv.setRicalcolaPesiEVolume(false);
       dv.setRicalcolaTrasporti(true); //Fix 12807
    }
    //dv.setPesoLordo(getPeso());
    //dv.setPesoNetto(getPesoNetto());
    //Fix 12507 20.04.10 fine    
    //02.05.19 - inizio
    if (getVolume() != null && 
    		getVolume().compareTo(new BigDecimal(0)) != 0)//03.06.19
    	dv.setVolume(getVolume());
    //02.05.19 - fine
    //16.01.07 - fine
    //34657 inizio
    List riferimentiRigheLogistica = new ArrayList();
    /* Fix 35962 inizio Commentato
    Iterator itRigheLogis = elencoRighe.iterator();
    while (itRigheLogis.hasNext()) {
      RigaLogisThip rlt = (RigaLogisThip) itRigheLogis.next();
      String idAzienda = dv.getIdAzienda();
      String annoDocumento = dv.getAnnoDocumento();
      String numeroDocumento = dv.getNumeroDocumento();
      Integer numeroRigaDocumento = rlt.getNumeroRigaHost();
      Integer dettailRigaDocumento = rlt.getDettaglioRigaHost();
      Object[] keyParts = {idAzienda,
                           annoDocumento,
                           numeroDocumento,
                           numeroRigaDocumento,
                           dettailRigaDocumento};
      String key = KeyHelper.buildObjectKey(keyParts);
      riferimentiRigheLogistica.add(key);
    }
    //dv.impostaPesiVolumiDaLogistica(getPesoNetto(), getPeso(), getVolume(), riferimentiRigheLogistica);
    *///Fix 35962 Fine

    //34657 fine
    dv.setIdGiroConsegne(getCodiceGiroDistributivo());
    dv.setNota(getNote());//10.07.06
    dv = trascodificaDatiPersonalizzati(dv);//22.01.07
    //dv = (DocumentoVendita)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST, this, dv);//22600
    dv = (DocumentoVendita)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST, this, dv, dvEsistente);//22600
    //03.02.09 - inizio
    String keyTL = KeyHelper.buildObjectKey(new String[] {getCodiceSocieta(), getCodice()});
    TestataLista tes = null;
    try {
      tes = TestataLista.elementWithKey(keyTL, PersistentObject.NO_LOCK);
    } catch (SQLException ex) {
      ex.printStackTrace(Trace.excStream);
    }
    if (tes == null) {
// Fix 15940 inizio
//      System.out.println("Lista " + keyTL.replace(KeyHelper.KEY_SEPARATOR.charAt(0), '/') +" non trovata.");
//      return new ErrorMessage("LOGIS01054", "Lista " + keyTL.replace(KeyHelper.KEY_SEPARATOR.charAt(0), '/') + " non trovata.");
			System.out.println(ResourceLoader.getString(RES_FILE,"gen0003",new String[]{KeyHelper.formatKeyString(keyTL)}));
			return new ErrorMessage("LOGIST0364", new String[]{KeyHelper.formatKeyString(keyTL)});
// Fix 15940 fine
    }
    List righeLista = tes.getRigheLista();
    //03.02.09 - fine
    //35962 inizio
    riferimentiRigheLogistica = getListaRigheDocTrasmessa(dv,righeLista);
    dv.impostaPesiVolumiDaLogistica(getPesoNetto(), getPeso(), getVolume(), riferimentiRigheLogistica);
    //35962 Fine
    Vector elencoFiltri = recuperaFiltriDocVen(dv.getIdCau());//27.04.09
    Hashtable qtaOriginali = new Hashtable();
    Hashtable statoOriginale = new Hashtable();
    // Azzero qta proposte di ERP per settare i consuntivi di PLE.
    for (int j = 0; j < dv.getRighe().size(); j++) {
      DocumentoVenRigaPrm dvr = (DocumentoVenRigaPrm) dv.getRighe().get(j);
      //03.02.09 - inizio
      //se la riga doc. non è stata trasmessa alla logistica non viene modificata
      if (!isRigaTrasmessa(dvr.getNumeroRigaDocumento(), dvr.getDettaglioRigaDocumento(), righeLista))
        continue;
      //03.02.09 - fine
      if (!testTrasmissioneDoc(dvr))
        continue;
      statoOriginale.put(dvr.getKey(), dvr.getStatoAvanzamento() + "");
      if (dvr.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO &&
          dvr.getQtaPropostaEvasione() != null) {
        qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvr.getKey(), UM_RIF}), dvr.getQtaPropostaEvasione().getQuantitaInUMRif().toString());
        dvr.getQtaPropostaEvasione().setQuantitaInUMRif(new BigDecimal(0));
        qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvr.getKey(), UM_PRM}), dvr.getQtaPropostaEvasione().getQuantitaInUMPrm().toString());
        dvr.getQtaPropostaEvasione().setQuantitaInUMPrm(new BigDecimal(0));
        qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvr.getKey(), UM_SEC}), dvr.getQtaPropostaEvasione().getQuantitaInUMSec().toString());
        dvr.getQtaPropostaEvasione().setQuantitaInUMSec(new BigDecimal(0));
      }
      if (dvr.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO &&
          dvr.getQtaAttesaEvasione() != null) {
        qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvr.getKey(), UM_RIF}), dvr.getQtaAttesaEvasione().getQuantitaInUMRif().toString());
        dvr.getQtaAttesaEvasione().setQuantitaInUMRif(new BigDecimal(0));
        qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvr.getKey(), UM_PRM}), dvr.getQtaAttesaEvasione().getQuantitaInUMPrm().toString());
        dvr.getQtaAttesaEvasione().setQuantitaInUMPrm(new BigDecimal(0));
        qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvr.getKey(), UM_SEC}), dvr.getQtaAttesaEvasione().getQuantitaInUMSec().toString());
        dvr.getQtaAttesaEvasione().setQuantitaInUMSec(new BigDecimal(0));
      }
      for (int i = 0; i < dvr.getRigheLotto().size(); i++) {
        DocumentoVenRigaLottoPrm dvr1otto = (DocumentoVenRigaLottoPrm) dvr.getRigheLotto().get(i);
        statoOriginale.put(dvr1otto.getKey(), dvr.getStatoAvanzamento() + "");
        if (dvr.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO &&
            dvr1otto.getQtaPropostaEvasione() != null) {
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvr1otto.getKey(), UM_RIF}), dvr1otto.getQtaPropostaEvasione().getQuantitaInUMRif().toString());//20.07.06
          dvr1otto.getQtaPropostaEvasione().setQuantitaInUMRif(new BigDecimal(0));
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvr1otto.getKey(), UM_PRM}), dvr1otto.getQtaPropostaEvasione().getQuantitaInUMPrm().toString());//20.07.06
          dvr1otto.getQtaPropostaEvasione().setQuantitaInUMPrm(new BigDecimal(0));
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvr1otto.getKey(), UM_SEC}), dvr1otto.getQtaPropostaEvasione().getQuantitaInUMSec().toString());//20.07.06
          dvr1otto.getQtaPropostaEvasione().setQuantitaInUMSec(new BigDecimal(0));
        }
        if (dvr.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO &&
            dvr1otto.getQtaAttesaEvasione() != null) {
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvr1otto.getKey(), UM_RIF}), dvr1otto.getQtaAttesaEvasione().getQuantitaInUMRif().toString());//20.07.06
          dvr1otto.getQtaAttesaEvasione().setQuantitaInUMRif(new BigDecimal(0));
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvr1otto.getKey(), UM_PRM}), dvr1otto.getQtaAttesaEvasione().getQuantitaInUMPrm().toString());//20.07.06
          dvr1otto.getQtaAttesaEvasione().setQuantitaInUMPrm(new BigDecimal(0));
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvr1otto.getKey(), UM_SEC}), dvr1otto.getQtaAttesaEvasione().getQuantitaInUMSec().toString());//20.07.06
          dvr1otto.getQtaAttesaEvasione().setQuantitaInUMSec(new BigDecimal(0));
        }
      }
      for (int k = 0; k < dvr.getRigheSecondarie().size() && dvr.getArticolo().getTipoParte() != ArticoloDatiIdent.KIT_GEST; k++) {//17.01.07
        DocumentoVenRigaSec dvrS = (DocumentoVenRigaSec) dvr.getRigheSecondarie().get(k);
        //03.02.09 - inizio
        //se la riga doc. non è stata trasmessa alla logistica non viene modificata
        if (!isRigaTrasmessa(dvrS.getNumeroRigaDocumento(), dvrS.getDettaglioRigaDocumento(), righeLista))
          continue;
        //03.02.09 - fine
        if (!testTrasmissioneDoc(dvrS))
          continue;
        statoOriginale.put(dvrS.getKey(), dvrS.getStatoAvanzamento() + "");
        if (dvrS.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO &&
            dvrS.getQtaPropostaEvasione() != null) {
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvrS.getKey(), UM_RIF}), dvrS.getQtaPropostaEvasione().getQuantitaInUMRif().toString());
          dvrS.getQtaPropostaEvasione().setQuantitaInUMRif(new BigDecimal(0));
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvrS.getKey(), UM_PRM}), dvrS.getQtaPropostaEvasione().getQuantitaInUMPrm().toString());
          dvrS.getQtaPropostaEvasione().setQuantitaInUMPrm(new BigDecimal(0));
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvrS.getKey(), UM_SEC}), dvrS.getQtaPropostaEvasione().getQuantitaInUMSec().toString());
          dvrS.getQtaPropostaEvasione().setQuantitaInUMSec(new BigDecimal(0));
        }
        if (dvrS.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO &&
            dvrS.getQtaAttesaEvasione() != null) {
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvrS.getKey(), UM_RIF}), dvrS.getQtaAttesaEvasione().getQuantitaInUMRif().toString());
          dvrS.getQtaAttesaEvasione().setQuantitaInUMRif(new BigDecimal(0));
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvrS.getKey(), UM_PRM}), dvrS.getQtaAttesaEvasione().getQuantitaInUMPrm().toString());
          dvrS.getQtaAttesaEvasione().setQuantitaInUMPrm(new BigDecimal(0));
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvrS.getKey(), UM_SEC}), dvrS.getQtaAttesaEvasione().getQuantitaInUMSec().toString());
          dvrS.getQtaAttesaEvasione().setQuantitaInUMSec(new BigDecimal(0));
        }
        for (int i = 0; i < dvrS.getRigheLotto().size(); i++) {
          DocumentoVenRigaLottoSec dvrS1otto = (DocumentoVenRigaLottoSec) dvrS.getRigheLotto().get(i);
          statoOriginale.put(dvrS1otto.getKey(), dvrS.getStatoAvanzamento() + "");
          if (dvrS.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO &&
              dvrS1otto.getQtaPropostaEvasione() != null) {
            qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvrS1otto.getKey(), UM_RIF}), dvrS1otto.getQtaPropostaEvasione().getQuantitaInUMRif().toString());//20.07.06
            dvrS1otto.getQtaPropostaEvasione().setQuantitaInUMRif(new BigDecimal(0));
            qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvrS1otto.getKey(), UM_PRM}), dvrS1otto.getQtaPropostaEvasione().getQuantitaInUMPrm().toString());//20.07.06
            dvrS1otto.getQtaPropostaEvasione().setQuantitaInUMPrm(new BigDecimal(0));
            qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvrS1otto.getKey(), UM_SEC}), dvrS1otto.getQtaPropostaEvasione().getQuantitaInUMSec().toString());//20.07.06
            dvrS1otto.getQtaPropostaEvasione().setQuantitaInUMSec(new BigDecimal(0));
          }
          if (dvrS.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO &&
              dvrS1otto.getQtaAttesaEvasione() != null) {
            qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvrS1otto.getKey(), UM_RIF}), dvrS1otto.getQtaAttesaEvasione().getQuantitaInUMRif().toString());//20.07.06
            dvrS1otto.getQtaAttesaEvasione().setQuantitaInUMRif(new BigDecimal(0));
            qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvrS1otto.getKey(), UM_PRM}), dvrS1otto.getQtaAttesaEvasione().getQuantitaInUMPrm().toString());//20.07.06
            dvrS1otto.getQtaAttesaEvasione().setQuantitaInUMPrm(new BigDecimal(0));
            qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dvrS1otto.getKey(), UM_SEC}), dvrS1otto.getQtaAttesaEvasione().getQuantitaInUMSec().toString());//20.07.06
            dvrS1otto.getQtaAttesaEvasione().setQuantitaInUMSec(new BigDecimal(0));
          }
        }
      }
    }
    // Setta i consuntivi di PLE
    Iterator iterRigheLogis = elencoRighe.iterator();
    while (iterRigheLogis.hasNext()) {
      RigaLogisThip rlt = (RigaLogisThip) iterRigheLogis.next();
      boolean dvrNuova = false;
      boolean dvrSeconNuova = false;
      String idAzienda = dv.getIdAzienda();
      String annoDocumento = dv.getAnnoDocumento();
      String numeroDocumento = dv.getNumeroDocumento();
      Integer numeroRigaDocumento = rlt.getNumeroRigaHost();//rlt.getAttributo4();//09.10.06
      Integer dettailRigaDocumento = rlt.getDettaglioRigaHost();//rlt.getAttributo5();//09.10.06
      String stepLotto = IntegrazioneThipLogis.RIGA_LOTTO + "";
      String lotto = rlt.getCodice().toString().substring(rlt.getCodice().toString().length() - stepLotto.length() + 1);
      if (ParametriLogis.SHOW_TRACE) {
// Fix 15940 inizio
//        System.out.println("  Numero riga " + numeroRigaDocumento);
//        System.out.println("  Dettaglio riga " + dettailRigaDocumento);
//        System.out.println("  Lotto riga " + lotto);
				System.out.println("  " + ResourceLoader.getString(RES_FILE,"gen0004") + " " + numeroRigaDocumento);
				System.out.println("  " + ResourceLoader.getString(RES_FILE,"gen0005") + " " + dettailRigaDocumento);
				System.out.println("  " + ResourceLoader.getString(RES_FILE,"gen0006") + " " + lotto);
// Fix 15940 fine
      }
      DocumentoVenRigaPrm dvr = null;
      DocumentoVenRigaPrm dvrEsistente = null;//Fix 22600 
      Object[] keyParts = {idAzienda,
                           annoDocumento,
                           numeroDocumento,
                           numeroRigaDocumento};
      String key = KeyHelper.buildObjectKey(keyParts);
      Iterator iterRighePrimarie = dv.getRighe().iterator();
      if (dettailRigaDocumento != null && dettailRigaDocumento.compareTo(new Integer(0)) == 0) {   // Riga primaria/lotto di riga primaria - 09.10.06
        while (iterRighePrimarie.hasNext() && dvr == null) {
          DocumentoVenRigaPrm dvr1 = (DocumentoVenRigaPrm) iterRighePrimarie.next();
          if (dvr1.getKey().equals(key))
            dvr = dvr1;
        }
        if (dvr == null) {
          dvr = (DocumentoVenRigaPrm) Factory.createObject(DocumentoVenRigaPrm.class);
          dvr.setIdAzienda(rlt.getIdAzienda());
          dvr.setFather(dv);
          dvr.setIdCauRig(rlt.getAttributo3());
          dvr.completaBO();
          dvrNuova = true;
          if (ParametriLogis.SHOW_TRACE)
//            System.out.println("  Riga primaria nuova."); Fix 15940
						System.out.println("  " + ResourceLoader.getString(RES_FILE,"gen0007"));// Fix 15940
        } 
        else{
          //Fix 22600 inizio
        	dvrEsistente = (DocumentoVenRigaPrm) Factory.createObject(DocumentoVenRigaPrm.class);
        	try {
        		dvrEsistente.setEqual(dvr);
        	}
        	catch (CopyException ex2) {
        		ex2.printStackTrace(Trace.excStream);
        	}
          //Fix 22600 fine
          if (ParametriLogis.SHOW_TRACE)
//            System.out.println("  Riga primaria recuperata."); Fix 15940
						System.out.println("  " + ResourceLoader.getString(RES_FILE,"gen0008"));// Fix 15940
        }
        if (rlt.getTipoRiga() == ARTICOLI &&
            !dvr.onDB)
          dvr.setTipoRiga(TrasmissioneDocumentiLogis.MERCE);
        if (rlt.getTipoRiga() == SERVIZIO &&
            !dvr.onDB)
          dvr.setTipoRiga(TrasmissioneDocumentiLogis.SERVIZIO);
        dvr.setIdMagazzino(rlt.getCodiceMagLogico());
        dvr.setIdArticolo(rlt.getCodiceArticolo());
        if (rlt.getVersione().equals(Gruppo.VERSIONE_DEFAULT))
           dvr.setIdVersioneSal(DocumentoVenRigaLotto.VERSIONE_DUMMY);    // Verisone default di Panthera
        else
           dvr.setIdVersioneSal(new Integer(rlt.getVersione()));
        if (rlt.getLotto2()!= null)
           dvr.setIdConfigurazione(new Integer(rlt.getLotto2()));
        dvr.setDescrizioneArticolo(rlt.getDescManuale());
        dvr.setIdUMRif(rlt.getUmDocumento());
        dvr.setIdUMPrm(rlt.getUmBase1());//24.07.07
        dvr.setIdUMSec(rlt.getUmBase2());//24.07.07
        //13.04.11 - inizio
        /*dvr.setDataConsegnaRichiesta(rlt.getDataConsegnaRichiesta());
        dvr.setDataConsegnaConfermata(rlt.getDataConsegnaConfermata());*/
        //dvr.setPrezzo(rlt.getPrezzoDivisa()); 03.03.09
        // 30.04.07 - inizio
        /*if (rlt.getAliquotaIVA() != null) {
          if (dvr.getAssoggettamentoIVA() != null)
            dvr.getAssoggettamentoIVA().setAliquotaIVA(new BigDecimal(rlt.getAliquotaIVA()));
        }*/ //30.04.07 - fine
        /*dvr.setIdAgente(rlt.getCodiceAgente1());
        dvr.setIdAgenteSub(rlt.getCodiceAgente2());
        if (rlt.getPriorita() != null)
          dvr.setPriorita((new Integer(rlt.getPriorita())).intValue());*/
        //13.04.11 - fine
        dvr.setRicalcoloQtaFattoreConv(false);//02.11.06
        //20.07.07 - inizio
        //BigDecimal qtaEvUMRif = dvr.getArticolo().convertiUM(rlt.getQta1Evasa(), dvr.getUMPrm(), dvr.getUMRif());//02.11.06
        // fix 10955 >
        ArticoloVersione versione = dvr.getArticoloVersRichiesta() == null ? dvr.getArticoloVersSaldi() : dvr.getArticoloVersRichiesta();
        BigDecimal qtaEvUMRif = calcolaQtaDoc(dvr.getArticolo(),dvr.getUMRif(),rlt, versione, dvr.isRicalcoloQtaFattoreConv());//16.11.09
        // fx 10955 <
        //20.07.07 - fine
        if (dvr.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO &&
            dvr.getQtaPropostaEvasione() != null){
//           dvr.getQtaPropostaEvasione().setQuantitaInUMRif(dvr.getQtaPropostaEvasione().getQuantitaInUMRif().add(rlt.getQtaDocumento()));
// Solo per l'installazione IMT: appena definita la relazione con l'UMLogistica rimettere in ordine
//  queste qta.
           dvr.getQtaPropostaEvasione().setQuantitaInUMRif(dvr.getQtaPropostaEvasione().getQuantitaInUMRif().add(qtaEvUMRif));//02.11.06
           dvr.getQtaPropostaEvasione().setQuantitaInUMPrm(dvr.getQtaPropostaEvasione().getQuantitaInUMPrm().add(rlt.getQta2Evasa()));//20.07.07
           dvr.getQtaPropostaEvasione().setQuantitaInUMSec(dvr.getQtaPropostaEvasione().getQuantitaInUMSec().add(rlt.getQta3Evasa()));//20.07.07
        }
        if (dvr.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO &&
            dvr.getQtaAttesaEvasione() != null){
//           dvr.getQtaAttesaEvasione().setQuantitaInUMRif(dvr.getQtaPropostaEvasione().getQuantitaInUMRif().add(rlt.getQtaDocumento()));
// Solo per l'installazione IMT: appena definita la relazione con l'UMLogistica rimettere in ordine
//  queste qta.
           dvr.getQtaAttesaEvasione().setQuantitaInUMRif(dvr.getQtaAttesaEvasione().getQuantitaInUMRif().add(qtaEvUMRif));//02.11.06
           dvr.getQtaAttesaEvasione().setQuantitaInUMPrm(dvr.getQtaAttesaEvasione().getQuantitaInUMPrm().add(rlt.getQta2Evasa()));//20.07.07
           dvr.getQtaAttesaEvasione().setQuantitaInUMSec(dvr.getQtaAttesaEvasione().getQuantitaInUMSec().add(rlt.getQta3Evasa()));//20.07.07
        }
        dvr.setStatoInvioLogistica(it.thera.thip.base.documenti.StatoAttivita.NO);
        dvr = trascodificaDatiPersonalizzati(rlt,dvr);//22.01.07
        //dvr = (DocumentoVenRigaPrm)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST_RIGHE, rlt, dvr);//22600
        dvr = (DocumentoVenRigaPrm)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST_RIGHE, rlt, dvr,dvrEsistente);//22600
        String valLotto = rlt.getLotto1();
        if (valLotto == null ||
            valLotto.equals(""))
          valLotto = dvr.LOTTO_DUMMY;
        Object[] keyPartsLotto = {idAzienda,
                                  annoDocumento,
                                  numeroDocumento,
                                  numeroRigaDocumento,
                                  dvr.getIdArticolo(),
                                  valLotto};
        String keyLotto = KeyHelper.buildObjectKey(keyPartsLotto);
        DocumentoVenRigaLottoPrm dvr1otto = null;
        DocumentoVenRigaLottoPrm dvr1ottoEsistente = null;//22600
        Iterator iterRighePrimarieLotto = dvr.getRigheLotto().iterator();
        while (iterRighePrimarieLotto.hasNext() && dvr1otto == null) {
          DocumentoVenRigaLottoPrm dvr1 = (DocumentoVenRigaLottoPrm) iterRighePrimarieLotto.next();
          if (dvr1.getKey().equals(keyLotto))
            dvr1otto = dvr1;
        }
        if (dvr1otto == null) {
          dvr1otto = (DocumentoVenRigaLottoPrm) Factory.createObject(DocumentoVenRigaLottoPrm.class);
          dvr1otto.setFather(dvr);
          dvr1otto.setIdAzienda(dvr.getIdAzienda());//23.07.07
          dvr1otto.setIdArticolo(dvr.getArticolo().getIdArticolo());
          dvr1otto.setIdLotto(valLotto);
          if (ParametriLogis.SHOW_TRACE)
//            System.out.println("  Riga lotto primaria nuova."); Fix 15940
						System.out.println("  " + ResourceLoader.getString(RES_FILE,"gen0009"));// Fix 15940
        } 
        //Fix 22600 inizio
        else{
        	dvr1ottoEsistente = (DocumentoVenRigaLottoPrm) Factory.createObject(DocumentoVenRigaLottoPrm.class);
        	try {
        		dvr1ottoEsistente.setEqual(dvr1ottoEsistente);
        	}
        	catch (CopyException ex2) {
        		ex2.printStackTrace(Trace.excStream);
        	}        	
          if (ParametriLogis.SHOW_TRACE)
//            System.out.println("  Riga lotto primaria recuperata."); Fix 15940
						System.out.println("  " + ResourceLoader.getString(RES_FILE,"gen0010"));// Fix 15940
        }
        //Fix 22600 fine
//        dvr1otto.getQtaPropostaEvasione().setQuantitaInUMRif(rlt.getQtaDocumento());
// Solo per l'installazione IMT: appena definita la relazione con l'UMLogistica rimettere in ordine
//  queste qta.
        if (dvr1otto.getArticolo() != null)   // AR 28.11.06
          //20.07.07 - inizio
          //qtaEvUMRif = dvr1otto.getArticolo().convertiUM(rlt.getQta1Evasa(), dvr.getUMPrm(), dvr.getUMRif());//02.11.06
          qtaEvUMRif = calcolaQtaDoc(dvr1otto.getArticolo(),dvr.getUMRif(),rlt,versione,dvr.isRicalcoloQtaFattoreConv());//16.11.09
          //20.07.07 - fine
        if (dvr.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO) {
          dvr1otto.getQtaPropostaEvasione().setQuantitaInUMRif(qtaEvUMRif);//02.11.06
          dvr1otto.getQtaPropostaEvasione().setQuantitaInUMPrm(rlt.getQta2Evasa());//20.07.07
          dvr1otto.getQtaPropostaEvasione().setQuantitaInUMSec(rlt.getQta3Evasa());//20.07.07
        }
        if (dvr.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO) {
          dvr1otto.getQtaAttesaEvasione().setQuantitaInUMRif(qtaEvUMRif);//02.11.06
          dvr1otto.getQtaAttesaEvasione().setQuantitaInUMPrm(rlt.getQta2Evasa());//20.07.07
          dvr1otto.getQtaAttesaEvasione().setQuantitaInUMSec(rlt.getQta3Evasa());//20.07.07
        }
        dvr1otto = trascodificaDatiPersonalizzati(rlt, dvr1otto);//22.01.07
        //dvr1otto = (DocumentoVenRigaLottoPrm)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST_RIGHE_LOTTO, rlt, dvr1otto);//22600
        dvr1otto = (DocumentoVenRigaLottoPrm)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST_RIGHE_LOTTO, rlt, dvr1otto, dvr1ottoEsistente);//22600
        if (!dvr1otto.onDB)
          dvr.getRigheLotto().add(dvr1otto);
      } else {    // Riga secondaria/lotto di riga secondaria
        dvr = null;
        while (iterRighePrimarie.hasNext() && dvr == null) {
          DocumentoVenRigaPrm dvr1 = (DocumentoVenRigaPrm) iterRighePrimarie.next();
          if (dvr1.getKey().equals(key))
            dvr = dvr1;
        }
        if (dvr == null) {
          dvr = (DocumentoVenRigaPrm) Factory.createObject(DocumentoVenRigaPrm.class);
          dvr.setIdAzienda(rlt.getIdAzienda());
          dvr.setFather(dv);
          dvr.setIdCauRig(rlt.getAttributo3());
          dvr.completaBO();
          dvrNuova = true;
          if (ParametriLogis.SHOW_TRACE)
//            System.out.println("  Riga primaria nuova (1)."); Fix 15940
						System.out.println("  " + ResourceLoader.getString(RES_FILE,"gen0011"));// Fix 15940
       } 
        //Fix 22600 inizio
        else{
        	dvrEsistente = (DocumentoVenRigaPrm) Factory.createObject(DocumentoVenRigaPrm.class);//30.05.16
        	try{
        		dvrEsistente.setEqual(dvr);
          }
          catch (CopyException ex2) {
            ex2.printStackTrace(Trace.excStream);
          }
          if (ParametriLogis.SHOW_TRACE)
//            System.out.println("  Riga primaria recuperata (2)."); Fix 15940
						System.out.println("  " + ResourceLoader.getString(RES_FILE,"gen0012"));// Fix 15940
        }
        //Fix 22600 fine
        dvr = trascodificaDatiPersonalizzati(rlt, dvr);//22.01.07
        //dvr = (DocumentoVenRigaPrm)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST_RIGHE, rlt, dvr);//22600
        dvr = (DocumentoVenRigaPrm)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST_RIGHE, rlt, dvr, dvrEsistente);//22600
        Object[] keyPartsSecon = {idAzienda,
                                  annoDocumento,
                                  numeroDocumento,
                                  numeroRigaDocumento,
                                  dettailRigaDocumento};
        String keySecon = KeyHelper.buildObjectKey(keyPartsSecon);
        Iterator iterRigheSecondarie = dvr.getRigheSecondarie().iterator();
        DocumentoVenRigaSec dvrSecon = null;
        DocumentoVenRigaSec dvrSeconEsistente = null;//22600
        while (iterRigheSecondarie.hasNext() && dvrSecon == null) {
          DocumentoVenRigaSec dvr1 = (DocumentoVenRigaSec) iterRigheSecondarie.next();
          if (dvr1.getKey().equals(keySecon))
            dvrSecon = dvr1;
        }
        if (dvrSecon == null) {
          dvrSecon = (DocumentoVenRigaSec) Factory.createObject(DocumentoVenRigaSec.class);
          dvrSecon.setFather(dvr);
          dvrSecon.setIdCauRig(rlt.getAttributo3());
          dvrSecon.completaBO();
          dvrSeconNuova = true;
          if (ParametriLogis.SHOW_TRACE)
//            System.out.println("  Riga secondaria nuova."); Fix 15940
						System.out.println("  " + ResourceLoader.getString(RES_FILE,"gen0013"));// Fix 15940
        } 
        //Fix 22600 inizio
        else{
        	dvrSeconEsistente = (DocumentoVenRigaSec) Factory.createObject(DocumentoVenRigaSec.class);
        	try {
        		dvrSeconEsistente.setEqual(dvrSeconEsistente);
           }
           catch (CopyException ex2) {
             ex2.printStackTrace(Trace.excStream);
           }
          if (ParametriLogis.SHOW_TRACE)
//            System.out.println("  Riga secondaria recuperata."); Fix 15940
						System.out.println("  " + ResourceLoader.getString(RES_FILE,"gen0014"));// Fix 15940
        }
        //Fix 22600 fine
        if (rlt.getTipoRiga() == ARTICOLI &&
            !dvrSecon.onDB)
          dvrSecon.setTipoRiga(TrasmissioneDocumentiLogis.MERCE);
        if (rlt.getTipoRiga() == SERVIZIO &&
            !dvrSecon.onDB)
          dvrSecon.setTipoRiga(TrasmissioneDocumentiLogis.SERVIZIO);
        dvrSecon.setIdMagazzino(rlt.getCodiceMagLogico());
        dvrSecon.setIdArticolo(rlt.getCodiceArticolo());
        if (rlt.getVersione().equals(Gruppo.VERSIONE_DEFAULT))
           dvrSecon.setIdVersioneSal(DocumentoVenRigaLotto.VERSIONE_DUMMY);//03.12.18
        else
           dvrSecon.setIdVersioneSal(new Integer(rlt.getVersione()));//03.12.18
        if (rlt.getLotto2()!= null)
           dvrSecon.setIdConfigurazione(new Integer(rlt.getLotto2()));
        dvrSecon.setDescrizioneArticolo(rlt.getDescManuale());
        dvrSecon.setIdUMRif(rlt.getUmDocumento());
        dvrSecon.setIdUMPrm(rlt.getUmBase1());//24.07.07
        dvrSecon.setIdUMSec(rlt.getUmBase2());//24.07.07
        //13.04.11 - inizio
        /*dvrSecon.setDataConsegnaRichiesta(rlt.getDataConsegnaRichiesta());
        dvrSecon.setDataConsegnaConfermata(rlt.getDataConsegnaConfermata());*/
        //dvrSecon.setPrezzo(rlt.getPrezzoDivisa()); 03.03.09
        // 30.04.07 - inizio
        /*if (rlt.getAliquotaIVA() != null) {
          if (dvrSecon.getAssoggettamentoIVA() != null)
            dvrSecon.getAssoggettamentoIVA().setAliquotaIVA(new BigDecimal(rlt.getAliquotaIVA()));
        }*/ // 30.04.07 - fine
        /*dvrSecon.setIdAgente(rlt.getCodiceAgente1());
        dvrSecon.setIdAgenteSub(rlt.getCodiceAgente2());
        if (rlt.getPriorita() != null)
          dvrSecon.setPriorita((new Integer(rlt.getPriorita())).intValue());*/
        //13.04.11 - fine
        dvrSecon.setRicalcoloQtaFattoreConv(false);//02.11.06
        //20.07.07 - inizio
        //BigDecimal qtaEvUMRif = dvrSecon.getArticolo().convertiUM(rlt.getQta1Evasa(), dvrSecon.getUMPrm(), dvrSecon.getUMRif());//02.11.06
        //16.11.09 - inizio
        ArticoloVersione versione = dvrSecon.getArticoloVersRichiesta() == null ? dvrSecon.getArticoloVersSaldi() : dvrSecon.getArticoloVersRichiesta();
        BigDecimal qtaEvUMRif = calcolaQtaDoc(dvrSecon.getArticolo(),dvrSecon.getUMRif(),rlt,versione,dvrSecon.isRicalcoloQtaFattoreConv());
        //16.11.09 - fine
        //20.07.07 - fine
        if (dvrSecon.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO &&
            dvrSecon.getQtaPropostaEvasione() != null){
          dvrSecon.getQtaPropostaEvasione().setQuantitaInUMRif(dvrSecon.getQtaPropostaEvasione().getQuantitaInUMRif().add(qtaEvUMRif));//02.11.06
          dvrSecon.getQtaPropostaEvasione().setQuantitaInUMPrm(dvrSecon.getQtaPropostaEvasione().getQuantitaInUMPrm().add(rlt.getQta2Evasa()));//20.07.07
          dvrSecon.getQtaPropostaEvasione().setQuantitaInUMSec(dvrSecon.getQtaPropostaEvasione().getQuantitaInUMSec().add(rlt.getQta3Evasa()));//20.07.07
        }
        if (dvrSecon.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO &&
            dvrSecon.getQtaAttesaEvasione() != null){
          dvrSecon.getQtaAttesaEvasione().setQuantitaInUMRif(dvrSecon.getQtaAttesaEvasione().getQuantitaInUMRif().add(qtaEvUMRif));//02.11.06
          dvrSecon.getQtaAttesaEvasione().setQuantitaInUMPrm(dvrSecon.getQtaAttesaEvasione().getQuantitaInUMPrm().add(rlt.getQta2Evasa()));//20.07.07
          dvrSecon.getQtaAttesaEvasione().setQuantitaInUMSec(dvrSecon.getQtaAttesaEvasione().getQuantitaInUMSec().add(rlt.getQta3Evasa()));//20.07.07
        }
        dvrSecon.setStatoInvioLogistica(it.thera.thip.base.documenti.StatoAttivita.NO);
        dvrSecon = trascodificaDatiPersonalizzati(rlt, dvrSecon);//22.01.07
        //dvrSecon = (DocumentoVenRigaSec)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST_RIGHE, rlt, dvrSecon);//22600
        dvrSecon = (DocumentoVenRigaSec)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST_RIGHE, rlt, dvrSecon, dvrSeconEsistente);//22600
        String valLotto = rlt.getLotto1();
        if (valLotto == null ||
            valLotto.equals(""))
          valLotto = dvr.LOTTO_DUMMY;
        Object[] keyPartsLotto = {idAzienda,
                                  annoDocumento,
                                  numeroDocumento,
                                  numeroRigaDocumento,
                                  dettailRigaDocumento,
                                  dvrSecon.getIdArticolo(),
                                  valLotto};
        String keyLotto = KeyHelper.buildObjectKey(keyPartsLotto);
        DocumentoVenRigaLottoSec dvr1otto = null;
        DocumentoVenRigaLottoSec dvr1ottoEsistente = null;//22600
        Iterator iterRigheSecondarieLotto = dvrSecon.getRigheLotto().iterator();
        while (iterRigheSecondarieLotto.hasNext() && dvr1otto == null) {
          DocumentoVenRigaLottoSec dvr1 = (DocumentoVenRigaLottoSec) iterRigheSecondarieLotto.next();
          if (dvr1.getKey().equals(keyLotto))
            dvr1otto = dvr1;
        }
        if (dvr1otto == null) {
          dvr1otto = (DocumentoVenRigaLottoSec) Factory.createObject(DocumentoVenRigaLottoSec.class);
          dvr1otto.setFather(dvrSecon);//17.07.06
          dvr1otto.setIdAzienda(dvrSecon.getIdAzienda());//23.07.07
          dvr1otto.setIdArticolo(dvrSecon.getIdArticolo());//17.07.06 - 10.10.06
          dvr1otto.setIdLotto(valLotto);
          if (ParametriLogis.SHOW_TRACE)
//            System.out.println("  Riga lotto secondaria nuova."); Fix 15940
						System.out.println("  " + ResourceLoader.getString(RES_FILE,"gen0015"));// Fix 15940
        }
        //Fix 22600 iniziio
        else{
        	dvr1ottoEsistente = (DocumentoVenRigaLottoSec) Factory.createObject(DocumentoVenRigaLottoSec.class);
           try {
          	 dvr1ottoEsistente.setEqual(dvr1otto);
           }
           catch (CopyException ex2) {
             ex2.printStackTrace(Trace.excStream);
           }
          if (ParametriLogis.SHOW_TRACE)
//            System.out.println("  Riga lotto secondaria recuperata."); Fix 15940
						System.out.println("  " + ResourceLoader.getString(RES_FILE,"gen0016"));// Fix 15940
        }
        //Fix 22600 fine
          //20.07.07 - inizio
          //qtaEvUMRif = dvr1otto.getArticolo().convertiUM(rlt.getQta1Evasa(), dvrSecon.getUMPrm(), dvrSecon.getUMRif());//02.11.06
          qtaEvUMRif = calcolaQtaDoc(dvr1otto.getArticolo(), dvrSecon.getUMRif(), rlt, versione, dvrSecon.isRicalcoloQtaFattoreConv());//16.11.09
          //20.07.07 - fine
          if (dvrSecon.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO) {
          dvr1otto.getQtaPropostaEvasione().setQuantitaInUMRif(qtaEvUMRif);//02.11.06
          dvr1otto.getQtaPropostaEvasione().setQuantitaInUMPrm(rlt.getQta2Evasa());//20.07.07
          dvr1otto.getQtaPropostaEvasione().setQuantitaInUMSec(rlt.getQta3Evasa());//20.07.07
        }
        if (dvrSecon.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO) {
          dvr1otto.getQtaAttesaEvasione().setQuantitaInUMRif(qtaEvUMRif);//02.11.06
          dvr1otto.getQtaAttesaEvasione().setQuantitaInUMPrm(rlt.getQta2Evasa());//20.07.07
          dvr1otto.getQtaAttesaEvasione().setQuantitaInUMSec(rlt.getQta3Evasa());//20.07.07
        }
        dvr1otto = trascodificaDatiPersonalizzati(rlt, dvr1otto);//22.01.07
        //dvr1otto = (DocumentoVenRigaLottoSec)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST_RIGHE, rlt, dvr1otto);//22600
        dvr1otto = (DocumentoVenRigaLottoSec)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST_RIGHE, rlt, dvr1otto, dvr1ottoEsistente);//22600
        if (!dvr1otto.onDB)
          dvrSecon.getRigheLotto().add(dvr1otto);
        if (dvrSeconNuova)
          dvr.getRigheSecondarie().add(dvrSecon);
      }
      if (dvrNuova)
        dv.getRighe().add(dvr);
    }
    // Elimia le righe lotto con qta = 0;
    for (int j = 0; j < dv.getRighe().size(); j++) {
      DocumentoVenRigaPrm dvr = (DocumentoVenRigaPrm) dv.getRighe().get(j);
      //03.02.09 - inizio
      //se la riga doc. non è stata trasmessa alla logistica non viene modificata
      if (!isRigaTrasmessa(dvr.getNumeroRigaDocumento(), dvr.getDettaglioRigaDocumento(), righeLista))
        continue;
      //03.02.09 - fine
      if (!testTrasmissioneDoc(dvr))
        continue;
      char statoAvzDVR = it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO;
      String tmp = (String) statoOriginale.get(dvr.getKey());
      if (tmp != null)
        statoAvzDVR = tmp.charAt(0);
      for (int i = 0; i < dvr.getRigheLotto().size(); i++) {
        DocumentoVenRigaLottoPrm dvr1otto = (DocumentoVenRigaLottoPrm) dvr.getRigheLotto().get(i);
        if ((statoAvzDVR == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO && dvr1otto.getQtaPropostaEvasione() == null) ||
            (statoAvzDVR == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO && dvr1otto.getQtaAttesaEvasione() == null))
          dvr.getRigheLotto().remove(i);
        else {
          //le righe lotto non evase vanno cancellate solo se ho prelevato qualcosa della riga primaria
          if (dvr.getQtaPropostaEvasione() != null &&
              !dvr.getQtaPropostaEvasione().isZero()){//25.07.06
            if (statoAvzDVR == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO &&
                dvr1otto.getQtaPropostaEvasione().getQuantitaInUMPrm().compareTo(new BigDecimal(0)) == 0) {
              if (!dvr1otto.getLotto().getCodiceLotto().equals(dvr.LOTTO_DUMMY)){//fix 5501 05.07.06
                reimpostaQtaOriginali(dvr1otto,statoAvzDVR,qtaOriginali);//19.03.08
                dvr.getRigheLotto().remove(i);
                i--;//fix 5501 05.07.06
              }
              else
              if (dvr.getQtaPropostaEvasione().getQuantitaInUMPrm().compareTo(new BigDecimal(0)) > 0){
                reimpostaQtaOriginali(dvr1otto,statoAvzDVR,qtaOriginali);//19.03.08
                dvr.getRigheLotto().remove(i);
              }
            }
            if (statoAvzDVR == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO &&
                dvr1otto.getQtaAttesaEvasione().getQuantitaInUMPrm().compareTo(new BigDecimal(0)) == 0) {
              if (!dvr1otto.getLotto().equals(dvr.LOTTO_DUMMY)){
                reimpostaQtaOriginali(dvr1otto,statoAvzDVR,qtaOriginali);//19.03.08
                dvr.getRigheLotto().remove(i);
              }
              else
              if (dvr.getQtaAttesaEvasione().getQuantitaInUMPrm().compareTo(new BigDecimal(0)) > 0){
                reimpostaQtaOriginali(dvr1otto,statoAvzDVR,qtaOriginali);//19.03.08
                dvr.getRigheLotto().remove(i);
              }
            }
          }
        }
      }
      if (statoAvzDVR == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO &&
          dvr.getQtaPropostaEvasione().isZero()) {
        //8464 - inizio
        //dvr.getDatiComuniEstesi().setStato(DatiComuniEstesi.ANNULLATO);
        ErrorMessage erroreAnnullamento = annullamentoRiga(dvr);
        if (erroreAnnullamento != null)
            return erroreAnnullamento;
        //8464 - fine
        dvr.setStatoAvanzamento(it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO);//08.06.06
        BigDecimal qta = new BigDecimal(1);
        String qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvr.getKey(), UM_RIF}));
        if (qtaStr != null) {
          try {
            qta = new BigDecimal(qtaStr);
          } catch (Exception ex) {
            ex.printStackTrace(Trace.excStream);
          }
        }
        dvr.getQtaAttesaEvasione().setQuantitaInUMRif(qta);//25.07.06
        qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvr.getKey(), UM_PRM}));
        if (qtaStr != null) {
          try {
            qta = new BigDecimal(qtaStr);
          } catch (Exception ex) {
            ex.printStackTrace(Trace.excStream);
          }
        }
        dvr.getQtaAttesaEvasione().setQuantitaInUMPrm(qta);//25.07.06
        qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvr.getKey(), UM_SEC}));
        if (qtaStr != null) {
          try {
            qta = new BigDecimal(qtaStr);
          } catch (Exception ex) {
            ex.printStackTrace(Trace.excStream);
          }
        }
        dvr.getQtaAttesaEvasione().setQuantitaInUMSec(qta);//25.07.06
        //20.07.06 - inizio
        // ripristino la qtà originale sulle righe lotto primarie
        for (int i = 0; i < dvr.getRigheLotto().size(); i++) {
          DocumentoVenRigaLottoPrm dvrlotto = (DocumentoVenRigaLottoPrm) dvr.getRigheLotto().get(i);
          qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvrlotto.getKey(), UM_RIF}));
          if (qtaStr != null) {
            try {
              qta = new BigDecimal(qtaStr);
            }
            catch (Exception ex) {
              ex.printStackTrace(Trace.excStream);
            }
          }
          dvrlotto.getQtaAttesaEvasione().setQuantitaInUMRif(qta);//25.07.06
          qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvrlotto.getKey(), UM_PRM}));
          if (qtaStr != null) {
            try {
              qta = new BigDecimal(qtaStr);
            }
            catch (Exception ex) {
              ex.printStackTrace(Trace.excStream);
            }
          }
          dvrlotto.getQtaAttesaEvasione().setQuantitaInUMPrm(qta);//25.07.06
          qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvrlotto.getKey(), UM_SEC}));
          if (qtaStr != null) {
            try {
              qta = new BigDecimal(qtaStr);
            }
            catch (Exception ex) {
              ex.printStackTrace(Trace.excStream);
            }
          }
          dvrlotto.getQtaAttesaEvasione().setQuantitaInUMSec(qta);//25.07.06
        }
        //20.07.06 - fine
      }
      if (statoAvzDVR == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO &&
          dvr.getQtaAttesaEvasione().isZero()) {
        //8464 - inizio
        //dvr.getDatiComuniEstesi().setStato(DatiComuniEstesi.ANNULLATO);
        ErrorMessage erroreAnnullamento = annullamentoRiga(dvr);
        if (erroreAnnullamento != null)
            return erroreAnnullamento;
        //8464 - fine
        BigDecimal qta = new BigDecimal(1);
        String qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvr.getKey(), UM_RIF}));
        if (qtaStr != null) {
          try {
            qta = new BigDecimal(qtaStr);
          } catch (Exception ex) {
            ex.printStackTrace(Trace.excStream);
          }
        }
        dvr.getQtaAttesaEvasione().setQuantitaInUMRif(qta);
        qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvr.getKey(), UM_PRM}));
        if (qtaStr != null) {
          try {
            qta = new BigDecimal(qtaStr);
          } catch (Exception ex) {
            ex.printStackTrace(Trace.excStream);
          }
        }
        dvr.getQtaAttesaEvasione().setQuantitaInUMPrm(qta);
        qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvr.getKey(), UM_SEC}));
        if (qtaStr != null) {
          try {
            qta = new BigDecimal(qtaStr);
          } catch (Exception ex) {
            ex.printStackTrace(Trace.excStream);
          }
        }
        dvr.getQtaAttesaEvasione().setQuantitaInUMSec(qta);
        //20.07.06 - inizio
        // ripristino la qtà originale sulle righe lotto primarie
        for (int i = 0; i < dvr.getRigheLotto().size(); i++) {
          DocumentoVenRigaLottoPrm dvrlotto = (DocumentoVenRigaLottoPrm) dvr.getRigheLotto().get(i);
          qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvrlotto.getKey(), UM_RIF}));
          if (qtaStr != null) {
            try {
              qta = new BigDecimal(qtaStr);
            }
            catch (Exception ex) {
              ex.printStackTrace(Trace.excStream);
            }
          }
          dvrlotto.getQtaAttesaEvasione().setQuantitaInUMRif(qta);
          qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvrlotto.getKey(), UM_PRM}));
          if (qtaStr != null) {
            try {
              qta = new BigDecimal(qtaStr);
            }
            catch (Exception ex) {
              ex.printStackTrace(Trace.excStream);
            }
          }
          dvrlotto.getQtaAttesaEvasione().setQuantitaInUMPrm(qta);
          qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvrlotto.getKey(), UM_SEC}));
          if (qtaStr != null) {
            try {
              qta = new BigDecimal(qtaStr);
            }
            catch (Exception ex) {
              ex.printStackTrace(Trace.excStream);
            }
          }
          dvrlotto.getQtaAttesaEvasione().setQuantitaInUMSec(qta);
        }
        //20.07.06 - fine
      }
      for (int k = 0; k < dvr.getRigheSecondarie().size() && dvr.getArticolo().getTipoParte() != ArticoloDatiIdent.KIT_GEST; k++) {//17.01.07
        DocumentoVenRigaSec dvrS = (DocumentoVenRigaSec) dvr.getRigheSecondarie().get(k);
        //03.02.09 - inizio
        //se la riga doc. non è stata trasmessa alla logistica non viene modificata
        if (!isRigaTrasmessa(dvrS.getNumeroRigaDocumento(), dvrS.getDettaglioRigaDocumento(), righeLista))
          continue;
        //03.02.09 - fine
        if (!testTrasmissioneDoc(dvrS))
          continue;
        char statoAvzDVRS = it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO;
        tmp = (String) statoOriginale.get(dvrS.getKey());
        if (tmp != null)
          statoAvzDVRS = tmp.charAt(0);
        for (int i = 0; i < dvrS.getRigheLotto().size(); i++) {
          DocumentoVenRigaLottoSec dvr1otto = (DocumentoVenRigaLottoSec) dvrS.getRigheLotto().get(i);
          if ((statoAvzDVRS == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO && dvr1otto.getQtaPropostaEvasione() == null) ||
              (statoAvzDVRS == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO && dvr1otto.getQtaAttesaEvasione() == null))
            dvrS.getRigheLotto().remove(i);
          else
            //le righe lotto non evase vanno cancellate solo se ho prelevato qualcosa della riga sec.
            if (dvrS.getQtaPropostaEvasione() != null &&
                !dvrS.getQtaPropostaEvasione().isZero()){//25.07.06
              if (statoAvzDVRS == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO &&
                  dvr1otto.getQtaPropostaEvasione().getQuantitaInUMPrm().compareTo(new BigDecimal(0)) == 0) {
                if (!dvr1otto.getLotto().equals(dvr.LOTTO_DUMMY)){
                  reimpostaQtaOriginali(dvr1otto,statoAvzDVRS,qtaOriginali);//19.03.08
                  dvrS.getRigheLotto().remove(i);
                }
                else
                if (dvrS.getQtaPropostaEvasione().getQuantitaInUMPrm().compareTo(new BigDecimal(0)) > 0){
                  reimpostaQtaOriginali(dvr1otto,statoAvzDVRS,qtaOriginali);//19.03.08
                  dvrS.getRigheLotto().remove(i);
                }
              }
              if (statoAvzDVRS == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO &&
                  dvr1otto.getQtaAttesaEvasione().getQuantitaInUMPrm().compareTo(new BigDecimal(0)) == 0) {
                if (!dvr1otto.getLotto().equals(dvr.LOTTO_DUMMY)){
                  reimpostaQtaOriginali(dvr1otto,statoAvzDVRS,qtaOriginali);//19.03.08
                  dvrS.getRigheLotto().remove(i);
                }
                else
                if (dvrS.getQtaAttesaEvasione().getQuantitaInUMPrm().compareTo(new BigDecimal(0)) > 0){
                  reimpostaQtaOriginali(dvr1otto,statoAvzDVRS,qtaOriginali);//19.03.08
                  dvrS.getRigheLotto().remove(i);
                }
              }
            }
          }
        if (statoAvzDVRS == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO &&
            dvrS.getQtaPropostaEvasione().isZero()) {
          dvrS.getDatiComuniEstesi().setStato(DatiComuniEstesi.ANNULLATO);
          dvrS.setStatoAvanzamento(it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO);//08.06.06
          BigDecimal qta = new BigDecimal(1);
          String qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvrS.getKey(), UM_RIF}));
          if (qtaStr != null) {
            try {
              qta = new BigDecimal(qtaStr);
            } catch (Exception ex) {
              ex.printStackTrace(Trace.excStream);
            }
          }
          dvrS.getQtaAttesaEvasione().setQuantitaInUMRif(qta);//25.07.06
          qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvrS.getKey(), UM_PRM}));
          if (qtaStr != null) {
            try {
              qta = new BigDecimal(qtaStr);
            } catch (Exception ex) {
              ex.printStackTrace(Trace.excStream);
            }
          }
          dvrS.getQtaAttesaEvasione().setQuantitaInUMPrm(qta);//25.07.06
          qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvrS.getKey(), UM_SEC}));
          if (qtaStr != null) {
            try {
              qta = new BigDecimal(qtaStr);
            } catch (Exception ex) {
              ex.printStackTrace(Trace.excStream);
            }
          }
          dvrS.getQtaAttesaEvasione().setQuantitaInUMSec(qta);//25.07.06
          //20.07.06 - inizio
          // ripristino la qtà originale sulle righe lotto secondarie
          for (int i = 0; i < dvrS.getRigheLotto().size(); i++) {
            DocumentoVenRigaLottoSec dvrSlotto = (DocumentoVenRigaLottoSec) dvrS.getRigheLotto().get(i);
            qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvrSlotto.getKey(), UM_RIF}));
            if (qtaStr != null) {
              try {
                qta = new BigDecimal(qtaStr);
              }
              catch (Exception ex) {
                ex.printStackTrace(Trace.excStream);
              }
            }
            dvrSlotto.getQtaAttesaEvasione().setQuantitaInUMRif(qta);//25.07.06
            qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvrSlotto.getKey(), UM_PRM}));
            if (qtaStr != null) {
              try {
                qta = new BigDecimal(qtaStr);
              }
              catch (Exception ex) {
                ex.printStackTrace(Trace.excStream);
              }
            }
            dvrSlotto.getQtaAttesaEvasione().setQuantitaInUMPrm(qta);//25.07.06
            qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvrSlotto.getKey(), UM_SEC}));
            if (qtaStr != null) {
              try {
                qta = new BigDecimal(qtaStr);
              }
              catch (Exception ex) {
                ex.printStackTrace(Trace.excStream);
              }
            }
            dvrSlotto.getQtaAttesaEvasione().setQuantitaInUMSec(qta);//25.07.06
          }
          //20.07.06 - fine
        }
        if (statoAvzDVRS == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO &&
            dvrS.getQtaAttesaEvasione().isZero()) {
          dvrS.getDatiComuniEstesi().setStato(DatiComuniEstesi.ANNULLATO);
          BigDecimal qta = new BigDecimal(1);
          String qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvrS.getKey(), UM_RIF}));
          if (qtaStr != null) {
            try {
              qta = new BigDecimal(qtaStr);
            } catch (Exception ex) {
              ex.printStackTrace(Trace.excStream);
            }
          }
          dvrS.getQtaAttesaEvasione().setQuantitaInUMRif(qta);
          qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvrS.getKey(), UM_PRM}));
          if (qtaStr != null) {
            try {
              qta = new BigDecimal(qtaStr);
            } catch (Exception ex) {
              ex.printStackTrace(Trace.excStream);
            }
          }
          dvrS.getQtaAttesaEvasione().setQuantitaInUMPrm(qta);
          qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvrS.getKey(), UM_SEC}));
          if (qtaStr != null) {
            try {
              qta = new BigDecimal(qtaStr);
            } catch (Exception ex) {
              ex.printStackTrace(Trace.excStream);
            }
          }
          dvrS.getQtaAttesaEvasione().setQuantitaInUMSec(qta);
          //20.07.06 - inizio
          // ripristino la qtà originale sulle righe lotto secondarie
          for (int i = 0; i < dvrS.getRigheLotto().size(); i++) {
            DocumentoVenRigaLottoSec dvrSlotto = (DocumentoVenRigaLottoSec) dvrS.getRigheLotto().get(i);
            qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvrSlotto.getKey(), UM_RIF}));
            if (qtaStr != null) {
              try {
                qta = new BigDecimal(qtaStr);
              }
              catch (Exception ex) {
                ex.printStackTrace(Trace.excStream);
              }
            }
            dvrSlotto.getQtaAttesaEvasione().setQuantitaInUMRif(qta);
            qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvrSlotto.getKey(), UM_PRM}));
            if (qtaStr != null) {
              try {
                qta = new BigDecimal(qtaStr);
              }
              catch (Exception ex) {
                ex.printStackTrace(Trace.excStream);
              }
            }
            dvrSlotto.getQtaAttesaEvasione().setQuantitaInUMPrm(qta);
            qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dvrSlotto.getKey(), UM_SEC}));
            if (qtaStr != null) {
              try {
                qta = new BigDecimal(qtaStr);
              }
              catch (Exception ex) {
                ex.printStackTrace(Trace.excStream);
              }
            }
            dvrSlotto.getQtaAttesaEvasione().setQuantitaInUMSec(qta);
          }
          //20.07.06 - fine
        }
      }
    }
    //dv.setTipoCollegamentoLogistica(it.thera.thip.base.comuniVenAcq.TipoCollegamentoLogistica.NO_LOGIS);06.04.10
    //26.07.06 - inizio
    //Le righe venivano salvate 2 volte (in rendiDefinitivo() e dv.save()) quindi i saldi non risultavano corretti
    /*ErrorMessage err = null;
    try {
      List errori = GestoreDocumenti.getInstance().rendiDefinitivo(dv);
      if (errori.size() > 0){
        err = (ErrorMessage) errori.get(0);
        //12.07.06 - inizio
        if (ParametriLogis.SHOW_TRACE)
          for (int i = 0; i < errori.size(); i++)
            System.out.println("Errore durante 'rendiDefinitivo': " + ((ErrorMessage) errori.get(i)).getText());
        //12.07.06 - fine
      }
      if (err == null)
        rc = dv.save();
    } catch (SQLException ex) {
      if (ex instanceof ThipException)
        return ((ThipException)ex).getErrorMessage();
      else
        ex.printStackTrace(Trace.excStream);
    }
    if (err == null && rc <= ErrorCodes.NO_ROWS_UPDATED) {//06.07.06
      return getErrorFromCode(rc);
    }
    return err;*/
    for (int i=0; i<dv.getRighe().size(); i++){
      DocumentoVenRigaPrm riga = (DocumentoVenRigaPrm) dv.getRighe().get(i);
      //03.02.09 - inizio
      //se la riga doc. non è stata trasmessa alla logistica, viene avanzata di stato solo se
      //si tratta di una riga a valore ed è selezionato l'opportuno flag sui filtri doc.ven.
      if (!isRigaTrasmessa(riga.getNumeroRigaDocumento(), riga.getDettaglioRigaDocumento(), righeLista)){
        if (!aggiornaStatoRigheValore(elencoFiltri,dv.getIdMagazzino(),dv.getDataDocumento()) ||//27.04.09 - 26.10.10
            //(riga.getCausaleRiga().getTipoRiga() != TipoRiga.SPESE_MOV_VALORE))//27.04.09
            (riga.getCausaleRiga().getTipoRiga() != TipoRiga.SPESE_MOV_VALORE && (riga.getCausaleRiga().getTipoRiga() != TipoRiga.MERCE || !riga.getCausaleRiga().isRigaMerceAValore())))//02.12.21
          continue;
      }
      //03.02.09 - fine
      //27.07.06 - inizio
      // se tutte le righe sec. sono annullate devo annullare anche la riga primaria
      boolean annulla = true;
      for (int j=0; j<riga.getRigheSecondarie().size(); j++){
        DocumentoVenRigaSec rigaS = (DocumentoVenRigaSec) riga.getRigheSecondarie().get(j);
        if (rigaS.getDatiComuniEstesi().getStato() != DatiComuniEstesi.ANNULLATO){
          annulla = false;
          break;
        }
      }
      //8464 - inizio
      if (annulla && riga.getRigheSecondarie().size() > 0) {
          //riga.getDatiComuniEstesi().setStato(DatiComuniEstesi.ANNULLATO);
          ErrorMessage erroreAnnullamento = annullamentoRiga(riga);
          if (erroreAnnullamento != null)
              return erroreAnnullamento;
      }
      //8464 - fine
      //27.07.06 - fine
      try {
        riga.rendiDefinitivoRiga();
      }
      //11.10.06 - inizio
      catch (ThipException e) {
        e.printStackTrace(Trace.excStream);
//        System.out.println("Errore durante rendiDefinitivoRiga(): " + e.getMessage()); Fix 15940
				System.out.println(ResourceLoader.getString(RES_FILE, "gen0017", new String[]{e.getMessage()}));// Fix 15940
        //09095 MM
        //return new ErrorMessage("THIP_BS165", new String[] {riga.getIdArticolo(), riga.getKey()});
//        return new ErrorMessage("LOGIS01054", "Riga " + riga.getKey() + " non definitiva. " + e.getMessage()); Fix 15940
				return new ErrorMessage("LOGIST0369", new String[]{KeyHelper.formatKeyString(riga.getKey()), e.getMessage()});// Fix 15940
      }
      //11.10.06 - fine
      catch (SQLException e) {
        e.printStackTrace(Trace.excStream);
//        System.out.println("Errore durante rendiDefinitivoRiga(): " + e.getMessage()); Fix 15940
				System.out.println(ResourceLoader.getString(RES_FILE, "gen0017", new String[]{e.getMessage()}));// Fix 15940
        return new ErrorMessage("THIP_BS165", new String[] {riga.getIdArticolo(), riga.getKey()});//11.10.06
      }
    }
    
    //16.11.20 - inizio
    //blocco spostato prima di salvare il dv, altrimenti dava errore di quadratura delle matricole
  
    //se almeno un articolo è matricolato allora recupero tutte le matricole della lista
    try {
			for (int i=0; i<righeLista.size(); i++) {
				RigaLista rl = (RigaLista) righeLista.get(i);
				if (rl.getArticolo().getGestioneMatricola()){
					Vector elencoMatricole = trovaMatricoleLista(rl.getTestataLista());
			    for (int j=0; j<elencoMatricole.size(); j++) {
			    	Matricola mat = (Matricola) elencoMatricole.get(j);
			    	int ret = aggiornaStoricoMatricola(mat,dv,null);
			    	if (ret < ErrorCodes.NO_ROWS_UPDATED){
			    		return new ErrorMessage("LOGIS05107", new String[]{ret+""});
			    	}
			    	//aggiornamento stato matricola
			    	LottoMatricola lm = null;
			    	String chiaveMatricola = KeyHelper.buildObjectKey(new String[] {dv.getIdAzienda(), mat.getCodiceArticolo(), mat.getMatricola()});//10.11.20 
			    	lm = LottoMatricola.elementWithKey(chiaveMatricola, PersistentObject.OPTIMISTIC_LOCK);
			    	if (lm != null){
			    		lm.setStatoMatricola(LottoMatricola.STATO_MAT_VENDUTA);
			    		lm.setAnnoDocVendita(dv.getAnnoDocumento());
			    		lm.setNumeroDocVendita(dv.getNumeroDocumento());
			    		lm.setDataDocVendita(dv.getDataDocumento());
			    		ret = lm.save();
			    		if (ret <= ErrorCodes.NO_ROWS_UPDATED){
			    			return new ErrorMessage("LOGIS05109", new String[]{ret+""});
			    		}
			    	}
			    }
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
			System.out.println(ResourceLoader.getString(RES_FILE,"gen0019") + " " + e.getMessage());
			return new ErrorMessage("LOGIST0379", new String[]{e.getMessage()});
		}
    //16.11.20 - fine
     //Fix 37183 Inizio
    BigDecimal pesiLordoDoc = dv.getPesoLordo();
    BigDecimal pesiNettoDoc = dv.getPesoNetto();
    BigDecimal volumeDoc = dv.getVolume();
    if(!dv.isRicalcolaPesiEVolume())
	    {
		    BigDecimal[] pesiEVolumeRecalcolata = recalcolaPesiVolumDocVen(dv);
		    if(getPeso() != null)
		    	dv.setPesoLordo(pesiLordoDoc);
		    if(getPesoNetto() != null)
		    	dv.setPesoNetto(pesiNettoDoc);
		    if(getVolume() != null && (getVolume().compareTo(new BigDecimal(0)) > 0))
		    	dv.setVolume(volumeDoc);
	    }
    //Fix 37183 Fine      
    ArrayList arrS = new ArrayList();
    BODataCollector boDC = null;
    try {
      boDC = createDataCollector("DocumentoVendita");
    }
    catch (SQLException e) {
      e.printStackTrace(Trace.excStream);
// Fix 15940 inizio
//			System.out.println("Errore durante createDataCollector(): " + e.getMessage());
//      return new ErrorMessage("LOGIS01054", "Errore durante createDataCollector(): " + e.getMessage());
			System.out.println(ResourceLoader.getString(RES_FILE, "gen0018", new String[]{e.getMessage()}));
			return new ErrorMessage("LOGIST0373", new String[]{e.getMessage()});
// Fix 15940 fine
    }
    //DocumentoTestata.raggruppaRigheStessoOrdine(dv);
    raggruppaRigheStessoOrdine(dv);//21.03.08
    ConnectionDescriptor conn = null;
    boDC.setForceableErrorForced(forzaErroriForzabili());//21.12.23
    try {//20.10.08
      dv.setNonDeveTrasmettereALogis(true);//la save del doc.ven. non deve trasmettere le righe a Logis
      ((YDocumentoVendita)dv).setSalvataggioDaTtlLogis(true); //72273 sof3
      if (!salvaPerRiga){ //20.10.08
        // salvataggio del documento complessivo
        if (tutteLeRigheSonoDefinitive(dv))//07.01.10
          dv.setStatoAvanzamento(it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO);//11.10.06
        dv.setSalvataggioRigheForzato(true);//07.11.07
        boDC.setBo(dv);
        DocumentoDataCollector.initRighePerCheck(boDC);
        int rc1 = boDC.save();
        if (rc1 == BODataCollector.ERROR){
          ErrorList err = boDC.getErrorList();
//          scriviListaErrori(err,"Errore durante il salvataggio del documento: "); Fix 15940
					scriviListaErrori(err,ResourceLoader.getString(RES_FILE,"gen0019")+" ");// Fix 15940
          arrS.addAll(err.getErrors());
        }
      }
      //20.10.08 - inizio
      // salvataggio riga per riga con commit intermedie
      else {
        dv.setStatoAvanzamento(it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO);//
        boDC.setBo(dv);
        DocumentoDataCollector.initRighePerCheck(boDC);
        conn = ConnectionManager.pushConnection();
      }
      // se la save della testata non ha dato problemi salvo le righe una alla volta
      if (arrS.isEmpty() && salvaPerRiga){
        ErrorList errori = salvaRigheSingoleVen(boDC.getBo());
        // se la save delle righe non ha dato problemi salvo la testata
        if (errori.getErrors().size() == 0){
          if (tutteLeRigheSonoDefinitive(dv))//07.01.10
            dv.setStatoAvanzamento(it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO);
          dv.setSalvataggioRigheForzato(false);
          dv.setSalvaRighe(false);   // fix 31470
          dv.calcolaCostiValoriOrdine();
          boDC.setAutoCommit(true);
          rc = boDC.save();
          if (rc == BODataCollector.ERROR){
            ErrorList err = boDC.getErrorList();
//            scriviListaErrori(err,"Errore durante il salvataggio della testata del documento in stato definitivo: "); Fix 15940
						scriviListaErrori(err, ResourceLoader.getString(RES_FILE,"gen0020")+" ");// Fix 15940
            arrS.addAll(err.getErrors());
          }
        }
        else {
//          scriviListaErrori(errori,"Errore durante il salvataggio delle righe del documento: "); Fix 15940
					scriviListaErrori(errori,ResourceLoader.getString(RES_FILE,"gen0021")+" ");// Fix 15940
          arrS.addAll(errori.getErrors());
        }
      }
      //16.11.20 - inizio
      /* blocco spostato prima di salvare il dv, altrimenti dava errore di quadratura delle matricole
      //28.06.19 - inizio
      //se almeno un articolo è matricolato allora recupero tutte le matricole della lista
      for (int i=0; i<righeLista.size(); i++) {
				RigaLista rl = (RigaLista) righeLista.get(i);
				if (rl.getArticolo().getGestioneMatricola()){
					Vector elencoMatricole = trovaMatricoleLista(rl.getTestataLista());
		      for (int j=0; j<elencoMatricole.size(); j++) {
		      	Matricola mat = (Matricola) elencoMatricole.get(j);
		      	int ret = aggiornaStoricoMatricola(mat,dv,null);
		      	if (ret < ErrorCodes.NO_ROWS_UPDATED){
		      		return new ErrorMessage("LOGIS05107", new String[]{ret+""});
		      	}
		      	//12.07.19 - inizio
		      	//aggiornamento stato matricola
		      	LottoMatricola lm = null;
		      	String chiaveMatricola = KeyHelper.buildObjectKey(new String[] {dv.getIdAzienda(), /*rl.getCodiceArticolo()*/ /*mat.getCodiceArticolo(), mat.getMatricola()});//10.11.20 
		      	lm = LottoMatricola.elementWithKey(chiaveMatricola, PersistentObject.OPTIMISTIC_LOCK);
		      	if (lm != null){
		      		lm.setStatoMatricola(LottoMatricola.STATO_MAT_VENDUTA);
		      		lm.setAnnoDocVendita(dv.getAnnoDocumento());
		      		lm.setNumeroDocVendita(dv.getNumeroDocumento());
		      		lm.setDataDocVendita(dv.getDataDocumento());
		      		ret = lm.save();
		      		if (ret <= ErrorCodes.NO_ROWS_UPDATED){
		      			return new ErrorMessage("LOGIS05109", new String[]{ret+""});
		      		}
		      	}
		      	//12.07.19 - fine
		      }
					break;
				}
			}
      //28.06.19 - fine
			*/
      //16.11.20 - fine
    }
    catch(SQLException e) {
// Fix 15940 inizio
//      e.printStackTrace();
//      System.out.println("Errore durante salvataggio del documento: " + e.getMessage());
//      return new ErrorMessage("LOGIS01054", "Errore durante salvataggio del documento: " + e.getMessage());
			e.printStackTrace(Trace.excStream);
			System.out.println(ResourceLoader.getString(RES_FILE,"gen0019") + " " + e.getMessage());
			return new ErrorMessage("LOGIST0379", new String[]{e.getMessage()});
// Fix 15940 fine
    }
    finally {
      if (conn != null)
        ConnectionManager.popConnection();
    }
    //20.10.08 - fine
    if (arrS.isEmpty())
      return null;
    else {
      ErrorMessage e = (ErrorMessage)arrS.get(0);
      return e;
    }
    //26.07.06 - fine
  }

  //28.06.19 - inizio
	protected static final String STMT_MATRICOLE_LISTA =
      "SELECT " + MatricolaTM.ID + " FROM " + MatricolaTM.TABLE_NAME + 
      " WHERE " + MatricolaTM.COD_SOCIETA + " = ?" + " AND " + MatricolaTM.COD_LISTA + "= ?";  

	//ritorna le matricole della lista
	public Vector trovaMatricoleLista(TestataLista tl) throws SQLException {
		Vector elenco = new Vector();
		CachedStatement stmt = new CachedStatement();
		ResultSet rs = null;
		try {
			Database db = ConnectionManager.getCurrentDatabase();
			stmt.setStmtString(STMT_MATRICOLE_LISTA);
			PreparedStatement ps = stmt.getStatement();
			db.setString(ps, 1, tl.getCodiceSocieta());
			db.setString(ps, 2, tl.getCodice());
			String cod = null;
			rs = ps.executeQuery();
			while (rs.next()){
				cod = rs.getString(1).trim();
				Matricola matr = null;
				matr = Matricola.elementWithKey(cod, PersistentObject.NO_LOCK);
				if (matr != null){
					elenco.add(matr);
				}
			}
		}
		finally{
			try {
				if (rs != null) rs.close();
				stmt.free();
			} catch (SQLException sqle){
				sqle.printStackTrace(Trace.excStream);
			}
		}
		return elenco;
	}
  
  public int aggiornaStoricoMatricola(Matricola mat, DocumentoVendita dv, DocumentoAcquisto da) throws SQLException {
  	StoricoMatricola sm = (StoricoMatricola)Factory.createObject(StoricoMatricola.class);
  	sm.setIdRigaDoc(mat.getRigaLista().getNumeroRigaHost().intValue());
  	sm.setIdDetRigaDoc(mat.getRigaLista().getDettaglioRigaHost().intValue());
  	sm.setIdArticolo(mat.getCodiceArticolo());
  	if (mat.getVersione().equals(Gruppo.VERSIONE_DEFAULT))
  		sm.setIdVersione(DocumentoVenRigaLotto.VERSIONE_DUMMY.intValue());
  	else
  		sm.setIdVersione((new Integer(mat.getVersione())).intValue());
  	if (mat.getLotto02() == null)
  		sm.setIdConfig(Configurazione.CONFIGURAZIONE_DUMMY.intValue());
  	else
  		sm.setIdConfig((new Integer(mat.getLotto02())).intValue());
  	if (mat.getLotto01() != null)
  		sm.setIdLotto(mat.getLotto01());
  	else
  		sm.setIdLotto(Lotto.LOTTO_DUMMY);
  	sm.setIdMatricola(mat.getMatricola());
  	sm.setVersoMovim(StoricoMatricola.VERMOV_STORMAT_USCITA);
    sm.setDataReg(new java.sql.Date(System.currentTimeMillis()));
    sm.setIdMagazzino(mat.getCodiceMagLogico());
  	String[] key = new String[] {mat.getCodiceSocieta(), mat.getCodiceLista(), mat.getCodiceRigaLista()+""};
  	RigaLista rl = null;
  	try {
  		rl = RigaLista.elementWithKey(KeyHelper.buildObjectKey(key), PersistentObject.NO_LOCK);
  	}
  	catch (Exception ex) {
  		ex.printStackTrace(Trace.excStream);
  	}
  	sm.setIdCauDocRiga(rl.getAttributo1());

    if (dv != null){
    	sm.setIdAzienda(dv.getIdAzienda());
    	sm.setTipoDoc(StoricoMatricola.TPDOC_STORMAT_DOCVEN);
    	sm.setIdAnnoDoc(dv.getAnnoDocumento());
    	sm.setIdNumeroDoc(dv.getNumeroDocumento());
    	sm.setIdCauDoc(dv.getIdCau());
    	sm.setDescCauDoc(dv.getCausale().getDescrizione().getDescrizione());
    	key = new String[] {sm.getIdAzienda(), sm.getIdCauDocRiga()};
    	CausaleRigaDocVen crdv = null;
    	try {
    		crdv = CausaleRigaDocVen.elementWithKey(KeyHelper.buildObjectKey(key), PersistentObject.NO_LOCK);
    	}
    	catch (Exception ex) {
    		ex.printStackTrace(Trace.excStream);
    	}
    	sm.setDescCauDocRiga(crdv.getDescrizione().getDescrizione());
    	sm.setIdCliente(dv.getIdCliente());
    }

    if (da != null){
    	sm.setIdAzienda(da.getIdAzienda());
    	sm.setTipoDoc(StoricoMatricola.TPDOC_STORMAT_DOCACQ);
    	sm.setIdAnnoDoc(da.getAnnoDocumento());
    	sm.setIdNumeroDoc(da.getNumeroDocumento());
    	sm.setIdCauDoc(da.getIdCau());
    	sm.setDescCauDoc(da.getCausale().getDescrizione().getDescrizione());
    	key = new String[] {sm.getIdAzienda(), sm.getIdCauDocRiga()};
    	CausaleDocumentoRigaAcq crda = null;
    	try {
    		crda = CausaleDocumentoRigaAcq.elementWithKey(KeyHelper.buildObjectKey(key), PersistentObject.NO_LOCK);
    	}
    	catch (Exception ex) {
    		ex.printStackTrace(Trace.excStream);
    	}
    	sm.setDescCauDocRiga(crda.getDescrizione().getDescrizione());
    	sm.setIdFornitore(da.getIdFornitore());
    }
    
    int ret = sm.save();
  	return ret;
  }
  //28.06.19 - fine
  
  //20.10.08 - inizio
  // salvataggio delle righe con commit per ciascuna riga (viene salvata anche la testata)
  public ErrorList salvaRigheSingoleVen(BusinessObject bo){
    DocumentoVendita dv = (DocumentoVendita)bo;
    BODataCollector boDC = null;
    try {
      boDC = createDataCollector("DocumentoVenditaRigaPrm");
    }
    catch (SQLException e) {
      e.printStackTrace(Trace.excStream);
//      System.out.println("Errore durante createDataCollector delle righe: " + e.getMessage()); Fix 15940
			System.out.println(ResourceLoader.getString(RES_FILE, "gen0022") + " " + e.getMessage()); // Fix 15940
      ErrorList err = new ErrorList();
//      err.addError(new ErrorMessage("LOGIS01054", "Errore durante createDataCollector delle righe: " + e.getMessage())); Fix 15940
			err.addError(new ErrorMessage("LOGIST0381", new String[]{e.getMessage()})); // Fix 15940
      return err;
    }
    for (int i=0; i<dv.getRighe().size(); i++){
      DocumentoVenditaRiga dvr = (DocumentoVenditaRiga)dv.getRighe().get(i);
      dvr.setSalvaTestata(false);
      boDC.setBo(dvr);
      boDC.setAutoCommit(true);
      int ret = BODataCollector.OK;
      try {
        ret = boDC.save();
      }
      catch (Exception e) {
// Fix 15940 inizio
//        e.printStackTrace();
//        System.out.println("Errore durante salvataggio delle righe del documento: " + e.getMessage());
				e.printStackTrace(Trace.excStream);
				System.out.println(ResourceLoader.getString(RES_FILE,"gen0023") + " " + e.getMessage());
//Fix 15940 fine
        ErrorList err = new ErrorList();
//        err.addError(new ErrorMessage("LOGIS01054", "Errore durante salvataggio delle righe del documento: " + e.getMessage())); Fix 15940
				err.addError(new ErrorMessage("LOGIST0384", new String[]{e.getMessage()}));// Fix 15940
        return err;
      }
      if (ret == BODataCollector.ERROR) {
        return boDC.getErrorList(); //se una riga non è stata salvata interrompo il ciclo
      }
    }
    return new ErrorList();
  }

  // salvataggio delle righe con commit per ciascuna riga (viene salvata anche la testata)
  public ErrorList salvaRigheSingoleAcq(BusinessObject bo){
    DocumentoAcquisto da = (DocumentoAcquisto)bo;
    BODataCollector boDC = null;
    try {
      boDC = createDataCollector("DocumentoAcquistoRigaPrm");
    }
    catch (SQLException e) {
      e.printStackTrace(Trace.excStream);
//      System.out.println("Errore durante createDataCollector delle righe: " + e.getMessage()); Fix 15940
			System.out.println(ResourceLoader.getString(RES_FILE,"gen0022") + " " + e.getMessage());// Fix 15940
      ErrorList err = new ErrorList();
//      err.addError(new ErrorMessage("LOGIS01054", "Errore durante createDataCollector delle righe: " + e.getMessage())); Fix 15940
			err.addError(new ErrorMessage("LOGIST0381", new String[]{e.getMessage()})); // Fix 15940
      return err;
    }
    for (int i=0; i<da.getRighe().size(); i++){
      DocumentoAcquistoRiga dar = (DocumentoAcquistoRiga)da.getRighe().get(i);
      dar.setSalvaTestata(false);
      dar.setTrasmissioneALogisDaEffettuare(false);
      boDC.setBo(dar);
      boDC.setAutoCommit(true);
      int ret = BODataCollector.OK;
      try {
        ret = boDC.save();
      }
      catch (Exception e) {
// Fix 15940 inizio
//				e.printStackTrace();
//        System.out.println("Errore durante salvataggio delle righe del documento: " + e.getMessage());
				e.printStackTrace(Trace.excStream);
				System.out.println(ResourceLoader.getString(RES_FILE,"gen0023") + " " + e.getMessage());
// Fix 15940 fine
        ErrorList err = new ErrorList();
//        err.addError(new ErrorMessage("LOGIS01054", "Errore durante salvataggio delle righe del documento: " + e.getMessage())); Fix 15940
				err.addError(new ErrorMessage("LOGIST0384", new String[]{e.getMessage()}));// Fix 15940
        return err;
      }
      if (ret == BODataCollector.ERROR) {
        return boDC.getErrorList(); //se una riga non è stata salvata interrompo il ciclo
      }
    }
    return new ErrorList();
  }

  public void scriviListaErrori(ErrorList err, String testo){
    Iterator iter = err.getErrors().iterator();
    while (iter.hasNext()){
      ErrorMessage errM = (ErrorMessage)iter.next();
      if (errM !=null){
        String campo = errM.getAttOrGroupName();
        System.out.println(testo + errM.getText() + " (" + campo +")");
        System.out.println("-------------> " + errM.getLongText());
        System.out.println("-------------> BO: " + errM.getAttOrGroupLabel());//27.08.08
      }
    }
  }
  //20.10.08 - fine

  //8464 - inizio
  /**
   * Richiede l'annullamento della riga
   * @param riga riga documento da annullare
   * @return eventuale errore nell'annullamento della riga
   */
  protected ErrorMessage annullamentoRiga(DocumentoDocRiga riga) {
      ErrorMessage errore = null;
      try {
          annullaRigaDocumento(riga);
      }
      catch (ThipException e) {
          errore = e.getErrorMessage();
      }
      return errore;
  }

  /**
   * Annulla la riga documento solo se questa non è già annullata
   * @param riga riga documento da annullare
   * @return true se la riga è stata annullata, false se era già in stato annullato
   */
  protected boolean annullaRigaDocumento(DocumentoDocRiga riga) throws ThipException {
      if (riga == null || riga.getDatiComuniEstesi().getStato() == DatiComuniEstesi.ANNULLATO)
          return false;

      riga.getDatiComuniEstesi().setStato(DatiComuniEstesi.ANNULLATO);
      return true;
  }
  //8464 - fine

  //10.01.07 - inizio
  public ErrorMessage salvaSuThipAcq(Vector elencoRighe, boolean reso) {//17.01.07
    int rc = ErrorCodes.NO_ROWS_UPDATED;
    DocumentoAcquisto da = (DocumentoAcquisto) Factory.createObject(DocumentoAcquisto.class);
    DocumentoAcquisto daEsistente = (DocumentoAcquisto) Factory.createObject(DocumentoAcquisto.class);//22600
    da.setIdAzienda(getIdAzienda());
    if (getCodice().length() > 5) {
      da.setAnnoDocumento(getCodice().substring(1, 5));//09.10.08
      da.setNumeroDocumento(getCodice().substring(5));//09.10.08
    }
    else
      da.setAnnoDocumento(getCodice());
    boolean res = false;
    try {
      res = da.retrieve(PersistentObject.OPTIMISTIC_LOCK);
    }
    catch (SQLException ex) {
      ex.printStackTrace(Trace.excStream);
    }
    if (!res)
      da = creoNuovoDocumento(da);
    //Fix 22600 inizio 
    else{
    	daEsistente = (DocumentoAcquisto) Factory.createObject(DocumentoAcquisto.class);
      try {
      	daEsistente.setEqual(da);
      }
      catch (CopyException ex2) {
        ex2.printStackTrace(Trace.excStream);
      }
    if (ParametriLogis.SHOW_TRACE)
//      System.out.println("Recuperato documento acquisto " + da.getNumeroDocumento()); Fix 15940
		System.out.println(ResourceLoader.getString(RES_FILE, "gen0024") + " " + da.getNumeroDocumento()); // Fix 15940
    }
    //Fix 22600 fine 
    if (da.isCollegataAMagazzino()) {
// Fix 15940 inizio
//      System.out.println("Documento " + da.getNumeroDocumento()+" collegato a magazzino");
//      return new ErrorMessage("LOGIS01054", "Il documento " + da.getKey().replace(KeyHelper.KEY_SEPARATOR.charAt(0), '/') + " è già collegato a magazzino.");
			System.out.println(ResourceLoader.getString(RES_FILE,"gen0002", new String[]{da.getNumeroDocumento()}));
			return new ErrorMessage("LOGIST0362", new String[]{KeyHelper.formatKeyString(da.getKey())});
// Fix 15940 fine
    }
    if (da == null)
//      return new ErrorMessage("LOGIS01054", "Errore nella creazione del nuovo documento acquisto su ERP"); Fix 15940
			return new ErrorMessage("LOGIST0394");// Fix 15940
    if (da.getNumeratoreHandler() != null)
      da.getNumeratoreHandler().setDataDocumento(getDataTestata());
    //15.03.11 - inizio
    // le righe seguenti sono state commentate perchè non è corretto che la chiusura
    // della lista modifichi alcuni attributi sulla testata del documento
    // le righe commentate sono state trasferite nel metodo creoNuovoDocumento(da)
    /*da.setNumeroRifIntestatario(getRiferimentoPartner());
    da.setDataRifIntestatario(getDataRiferimentoPartner());
//09438 24/06/2008 MM inizio
//    if (getCodicePartnerDestinazione() != null)
//      da.setIdDenAbt(getCodicePartnerDestinazione().substring(1));
//    if (getDestinazioneAlternativa() != null) {
//      da.setRagioneSocaleDest(getDestinazioneAlternativa().getRagioneSociale1());
//      da.setIndirizzoDestinatario(getDestinazioneAlternativa().getVia());
//      da.setLocalitaDestinatario(getDestinazioneAlternativa().getLocalita());
//      da.setCAPDestinatario(getDestinazioneAlternativa().getCap());
//      da.setIdNazioneDen(getDestinazioneAlternativa().getNazione());
//    }
//09438 24/06/2008 MM fine
    //?da.setIdClienteFat(getCodicePartnerFatturazione().substring(1));
    da.setDataConsegnaRichiesta(getDataConsegnaRichiesta());
    da.setDataConsegnaConfermata(getDataConsegnaConfermata());
    da.setIdMagazzino(getCodiceMagLogicoImpegno());
    //da.setIdMagazzinoTra(getCodiceMagLogicoTrasfer());
    da.setIdCommessa(getAttributo3());
    da.setIdLingua(getCodiceLingua());
    da.setIdZona(getZonaGenerica());
    if (getPriorita() != null || !getPriorita().equals(""))
      da.setPriorita( (new Short(getPriorita())).shortValue());
    da.setIdValuta(getCodiceDivisa());
    //da.setIdModPagamento(getAttributo2());22.02.11
    da.setDataInizioPagamento(getData1());
    /*da.setIdAgente(getCodiceAgente1());
       da.setIdAgenteSub(getCodiceAgente2());*/
    //15.03.11 - fine
    da.setIdModSpedizione(getCodiceModalitaTrasporto());
    da.setDescrModalitaSpedizione(getDescModalitaTrasporto());
    da.setIdModConsegna(getCodiceCondizioneTrasporto());
    da.setDescrModalitaConsegna(getDescCondizioniTrasporto());
    da.setIdAspettoEsn(getCodiceTipoImballo());
    da.setDescrAspettoEsteriore(getDescTipoImballo());
    String keyVett = "";
    // se il vettore è cambiato modifico la text area (concordato con PM)
    if ((getCodiceVettore1() != null && !getCodiceVettore1().equals(da.getIdVettore1())) ||
        (getCodiceVettore1() == null && da.getIdVettore1() != null))
      da.setDescrVettore1(descIndirizzoFormattata(getVettore1Alternativo()));
    keyVett = KeyHelper.buildObjectKey(new String[] {getIdAzienda(),getCodiceVettore1()});
    da.setVettore1Key(keyVett);
    if ((getCodiceVettore2() != null && !getCodiceVettore2().equals(da.getIdVettore2())) ||
        (getCodiceVettore2() == null && da.getIdVettore2() != null))
      da.setDescrVettore2(descIndirizzoFormattata(getVettore2Alternativo()));
    keyVett = KeyHelper.buildObjectKey(new String[] {getIdAzienda(), getCodiceVettore2()});
    da.setVettore2Key(keyVett);
    //16.01.07 - inizio
    /*if (getValori(0) != null)
      da.setNumeroColli(getValori(0).intValue());
    da.setPesoLordo(getValori(1));
    da.setPesoNetto(getValori(2));*/
    if (getUnita() != null)
      da.setNumeroColli(getUnita().intValue());
    //Fix 12507 20.04.10 inizio
    if(getPeso() != null && getPesoNetto() != null)
    {
       da.setPesoLordo(getPeso());
       da.setPesoNetto(getPesoNetto());
       da.setRicalcolaPesiEVolume(false);
       da.setRicalcolaTrasporti(true); //Fix 12807
    }
    //da.setPesoLordo(getPeso());
    //Fix 12507 20.04.10 fine
    //16.01.07 - fine
    //02.05.19 - inizio
    if (getVolume() != null && 
    		!getVolume().equals(new BigDecimal(0)))//03.06.19
    	da.setVolume(getVolume());
    
    //34657 inizio
    List riferimentiRigheLogistica = new ArrayList();
    /* Fix 35962 inizio Commentato
    Iterator itRigheLogis = elencoRighe.iterator();
    while (itRigheLogis.hasNext()) {
      RigaLogisThip rlt = (RigaLogisThip) itRigheLogis.next();
      String idAzienda = da.getIdAzienda();
      String annoDocumento = da.getAnnoDocumento();
      String numeroDocumento = da.getNumeroDocumento();
      Integer numeroRigaDocumento = rlt.getNumeroRigaHost();
      Integer dettailRigaDocumento = rlt.getDettaglioRigaHost();
      Object[] keyParts = {idAzienda,
                           annoDocumento,
                           numeroDocumento,
                           numeroRigaDocumento,
                           dettailRigaDocumento};
      String key = KeyHelper.buildObjectKey(keyParts);
      riferimentiRigheLogistica.add(key);
    }
    
    da.impostaPesiVolumiDaLogistica(getPesoNetto(), getPeso(), getVolume(), riferimentiRigheLogistica);
    */ //Fix 35962 Fine 
    //34657 fine
    
    //02.05.19 - fine
    da.setIdGiroConsegne(getCodiceGiroDistributivo());
    da.setNota(getNote());
    da = trascodificaDatiPersonalizzati(da);//22.01.07
    //da = (DocumentoAcquisto)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST, this, da);//22600
    da = (DocumentoAcquisto)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST, this, da, daEsistente);//22600
    //03.02.09 - inizio
    String keyTL = KeyHelper.buildObjectKey(new String[] {getCodiceSocieta(), getCodice()});
    TestataLista tes = null;
    try {
      tes = TestataLista.elementWithKey(keyTL, PersistentObject.NO_LOCK);
    } catch (SQLException ex) {
      ex.printStackTrace(Trace.excStream);
    }
    if (tes == null) {
// Fix 15940 inizio
//      System.out.println("Lista " + keyTL.replace(KeyHelper.KEY_SEPARATOR.charAt(0), '/') +" non trovata.");
//      return new ErrorMessage("LOGIS01054", "Lista " + keyTL.replace(KeyHelper.KEY_SEPARATOR.charAt(0), '/') + " non trovata.");
			System.out.println(ResourceLoader.getString(RES_FILE,"gen0003", new String[]{KeyHelper.formatKeyString(keyTL)}));
			return new ErrorMessage("LOGIST0364", new String[]{KeyHelper.formatKeyString(keyTL)});
// Fix 15940 fine
    }
    List righeLista = tes.getRigheLista();
    //03.02.09 - fine
    //35962 inizio
    riferimentiRigheLogistica = getListaRigheDocTrasmessa(da,righeLista,reso);
    da.impostaPesiVolumiDaLogistica(getPesoNetto(), getPeso(), getVolume(), riferimentiRigheLogistica);
    //35962 Fine
    
    Hashtable qtaOriginali = new Hashtable();
    Hashtable statoOriginale = new Hashtable();
    // Azzero qta proposte di ERP per settare i consuntivi di PLE.
    for (int j = 0; j < da.getRighe().size(); j++) {
      DocumentoAcqRigaPrm dar = (DocumentoAcqRigaPrm) da.getRighe().get(j);
      //03.02.09 - inizio
      //se la riga doc. non è stata trasmessa alla logistica non viene modificata
      if (!isRigaTrasmessa(dar.getNumeroRigaDocumento(), dar.getDettaglioRigaDocumento(), righeLista))
        continue;
      //03.02.09 - fine
      if (!testTrasmissioneDoc(dar))
        continue;
      statoOriginale.put(dar.getKey(), dar.getStatoAvanzamento() + "");
      if (dar.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO &&
          dar.getQtaPropostaEvasione() != null) {
        qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dar.getKey(), UM_RIF}), dar.getQtaPropostaEvasione().getQuantitaInUMRif().toString());
        dar.getQtaPropostaEvasione().setQuantitaInUMRif(new BigDecimal(0));
        qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dar.getKey(), UM_PRM}), dar.getQtaPropostaEvasione().getQuantitaInUMPrm().toString());
        dar.getQtaPropostaEvasione().setQuantitaInUMPrm(new BigDecimal(0));
        qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dar.getKey(), UM_SEC}), dar.getQtaPropostaEvasione().getQuantitaInUMSec().toString());
        dar.getQtaPropostaEvasione().setQuantitaInUMSec(new BigDecimal(0));
      }
      if (dar.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO &&
          dar.getQtaAttesaEvasione() != null) {
        qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dar.getKey(), UM_RIF}), dar.getQtaAttesaEvasione().getQuantitaInUMRif().toString());
        dar.getQtaAttesaEvasione().setQuantitaInUMRif(new BigDecimal(0));
        qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dar.getKey(), UM_PRM}), dar.getQtaAttesaEvasione().getQuantitaInUMPrm().toString());
        dar.getQtaAttesaEvasione().setQuantitaInUMPrm(new BigDecimal(0));
        qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {dar.getKey(), UM_SEC}), dar.getQtaAttesaEvasione().getQuantitaInUMSec().toString());
        dar.getQtaAttesaEvasione().setQuantitaInUMSec(new BigDecimal(0));
      }
      for (int i = 0; i < dar.getRigheLotto().size(); i++) {
        DocumentoAcqRigaLottoPrm darLotto = (DocumentoAcqRigaLottoPrm) dar.getRigheLotto().get(i);
        statoOriginale.put(darLotto.getKey(), dar.getStatoAvanzamento() + "");
        if (dar.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO &&
            darLotto.getQtaPropostaEvasione() != null) {
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {darLotto.getKey(), UM_RIF}), darLotto.getQtaPropostaEvasione().getQuantitaInUMRif().toString());//20.07.06
          darLotto.getQtaPropostaEvasione().setQuantitaInUMRif(new BigDecimal(0));
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {darLotto.getKey(), UM_PRM}), darLotto.getQtaPropostaEvasione().getQuantitaInUMPrm().toString());//20.07.06
          darLotto.getQtaPropostaEvasione().setQuantitaInUMPrm(new BigDecimal(0));
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {darLotto.getKey(), UM_SEC}), darLotto.getQtaPropostaEvasione().getQuantitaInUMSec().toString());//20.07.06
          darLotto.getQtaPropostaEvasione().setQuantitaInUMSec(new BigDecimal(0));
        }
        if (dar.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO &&
            darLotto.getQtaAttesaEvasione() != null) {
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {darLotto.getKey(), UM_RIF}), darLotto.getQtaAttesaEvasione().getQuantitaInUMRif().toString());//20.07.06
          darLotto.getQtaAttesaEvasione().setQuantitaInUMRif(new BigDecimal(0));
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {darLotto.getKey(), UM_PRM}), darLotto.getQtaAttesaEvasione().getQuantitaInUMPrm().toString());//20.07.06
          darLotto.getQtaAttesaEvasione().setQuantitaInUMPrm(new BigDecimal(0));
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {darLotto.getKey(), UM_SEC}), darLotto.getQtaAttesaEvasione().getQuantitaInUMSec().toString());//20.07.06
          darLotto.getQtaAttesaEvasione().setQuantitaInUMSec(new BigDecimal(0));
        }
      }
      for (int k = 0; k < dar.getRigheSecondarie().size() && !reso; k++) {//15.01.07
        DocumentoAcqRigaSec darS = (DocumentoAcqRigaSec) dar.getRigheSecondarie().get(k);
        //03.02.09 - inizio
        //se la riga doc. non è stata trasmessa alla logistica non viene modificata
        if (!isRigaTrasmessa(darS.getNumeroRigaDocumento(), darS.getDettaglioRigaDocumento(), righeLista))
          continue;
        //03.02.09 - fine
        if (!testTrasmissioneDoc(darS))
          continue;
        statoOriginale.put(darS.getKey(), darS.getStatoAvanzamento() + "");
        if (darS.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO &&
            darS.getQtaPropostaEvasione() != null) {
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {darS.getKey(), UM_RIF}), darS.getQtaPropostaEvasione().getQuantitaInUMRif().toString());
          darS.getQtaPropostaEvasione().setQuantitaInUMRif(new BigDecimal(0));
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {darS.getKey(), UM_PRM}), darS.getQtaPropostaEvasione().getQuantitaInUMPrm().toString());
          darS.getQtaPropostaEvasione().setQuantitaInUMPrm(new BigDecimal(0));
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {darS.getKey(), UM_SEC}), darS.getQtaPropostaEvasione().getQuantitaInUMSec().toString());
          darS.getQtaPropostaEvasione().setQuantitaInUMSec(new BigDecimal(0));
        }
        if (darS.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO &&
            darS.getQtaAttesaEvasione() != null) {
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {darS.getKey(), UM_RIF}), darS.getQtaAttesaEvasione().getQuantitaInUMRif().toString());
          darS.getQtaAttesaEvasione().setQuantitaInUMRif(new BigDecimal(0));
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {darS.getKey(), UM_PRM}), darS.getQtaAttesaEvasione().getQuantitaInUMPrm().toString());
          darS.getQtaAttesaEvasione().setQuantitaInUMPrm(new BigDecimal(0));
          qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {darS.getKey(), UM_SEC}), darS.getQtaAttesaEvasione().getQuantitaInUMSec().toString());
          darS.getQtaAttesaEvasione().setQuantitaInUMSec(new BigDecimal(0));
        }
        for (int i = 0; i < darS.getRigheLotto().size(); i++) {
          DocumentoAcqRigaLottoSec darSlotto = (DocumentoAcqRigaLottoSec) darS.getRigheLotto().get(i);
          statoOriginale.put(darSlotto.getKey(), darS.getStatoAvanzamento() + "");
          if (darS.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO &&
              darSlotto.getQtaPropostaEvasione() != null) {
            qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {darSlotto.getKey(), UM_RIF}), darSlotto.getQtaPropostaEvasione().getQuantitaInUMRif().toString());//20.07.06
            darSlotto.getQtaPropostaEvasione().setQuantitaInUMRif(new BigDecimal(0));
            qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {darSlotto.getKey(), UM_PRM}), darSlotto.getQtaPropostaEvasione().getQuantitaInUMPrm().toString());//20.07.06
            darSlotto.getQtaPropostaEvasione().setQuantitaInUMPrm(new BigDecimal(0));
            qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {darSlotto.getKey(), UM_SEC}), darSlotto.getQtaPropostaEvasione().getQuantitaInUMSec().toString());//20.07.06
            darSlotto.getQtaPropostaEvasione().setQuantitaInUMSec(new BigDecimal(0));
          }
          if (darS.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO &&
              darSlotto.getQtaAttesaEvasione() != null) {
            qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {darSlotto.getKey(), UM_RIF}), darSlotto.getQtaAttesaEvasione().getQuantitaInUMRif().toString());//20.07.06
            darSlotto.getQtaAttesaEvasione().setQuantitaInUMRif(new BigDecimal(0));
            qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {darSlotto.getKey(), UM_PRM}), darSlotto.getQtaAttesaEvasione().getQuantitaInUMPrm().toString());//20.07.06
            darSlotto.getQtaAttesaEvasione().setQuantitaInUMPrm(new BigDecimal(0));
            qtaOriginali.put(KeyHelper.buildObjectKey(new String[] {darSlotto.getKey(), UM_SEC}), darSlotto.getQtaAttesaEvasione().getQuantitaInUMSec().toString());//20.07.06
            darSlotto.getQtaAttesaEvasione().setQuantitaInUMSec(new BigDecimal(0));
          }
        }
      }
    }
    // Setta i consuntivi di PLE
    Iterator iterRigheLogis = elencoRighe.iterator();
    while (iterRigheLogis.hasNext()) {
      RigaLogisThip rlt = (RigaLogisThip) iterRigheLogis.next();
      boolean darNuova = false;
      boolean darSeconNuova = false;
      String idAzienda = da.getIdAzienda();
      String annoDocumento = da.getAnnoDocumento();
      String numeroDocumento = da.getNumeroDocumento();
      Integer numeroRigaDocumento = rlt.getNumeroRigaHost();
      Integer dettailRigaDocumento = rlt.getDettaglioRigaHost();
      String stepLotto = IntegrazioneThipLogis.RIGA_LOTTO + "";
      String lotto = rlt.getCodice().toString().substring(rlt.getCodice().toString().length() - stepLotto.length() + 1);
      if (ParametriLogis.SHOW_TRACE) {
// Fix 15940 inizio
//        System.out.println("  Numero riga " + numeroRigaDocumento);
//        System.out.println("  Dettaglio riga " + dettailRigaDocumento);
//        System.out.println("  Lotto riga " + lotto);
				System.out.println("  " + ResourceLoader.getString(RES_FILE,"gen0004") + " " + numeroRigaDocumento);
				System.out.println("  " + ResourceLoader.getString(RES_FILE,"gen0005") + " " + dettailRigaDocumento);
				System.out.println("  " + ResourceLoader.getString(RES_FILE,"gen0006") + " " + lotto);
// Fix 15940 fine
      }
      DocumentoAcqRigaPrm dar = null;
      DocumentoAcqRigaPrm darEsistente = null;//22600
      Object[] keyParts = {idAzienda,
                           annoDocumento,
                           numeroDocumento,
                           numeroRigaDocumento};
      String key = KeyHelper.buildObjectKey(keyParts);
      Iterator iterRighePrimarie = da.getRighe().iterator();
      if (dettailRigaDocumento != null && dettailRigaDocumento.compareTo(new Integer(0)) == 0) {
        while (iterRighePrimarie.hasNext() && dar == null) {
          DocumentoAcqRigaPrm dar1 = (DocumentoAcqRigaPrm) iterRighePrimarie.next();
          if (dar1.getKey().equals(key))
            dar = dar1;
        }
        if (dar == null) {
          dar = (DocumentoAcqRigaPrm) Factory.createObject(DocumentoAcqRigaPrm.class);
          dar.setIdAzienda(rlt.getIdAzienda());
          dar.setFather(da);
          dar.setIdCauRig(rlt.getAttributo3());
          dar.completaBO();
          darNuova = true;
          if (ParametriLogis.SHOW_TRACE)
//            System.out.println("  Riga primaria nuova."); Fix 15940
						System.out.println(" " + ResourceLoader.getString(RES_FILE,"gen0007")); // Fix 15940
        }
        //Fix 22600 inizio
        else{
        	darEsistente = (DocumentoAcqRigaPrm) Factory.createObject(DocumentoAcqRigaPrm.class);
        	try {
        		darEsistente.setEqual(dar);
        	}
        	catch (CopyException ex2) {
        		ex2.printStackTrace(Trace.excStream);
        	}
        if (ParametriLogis.SHOW_TRACE)
//          System.out.println("  Riga primaria recuperata."); Fix 15940
					System.out.println(" " + ResourceLoader.getString(RES_FILE,"gen0008")); // Fix 15940
        }
        //Fix 22600 fine
        if (rlt.getTipoRiga() == ARTICOLI &&
            !dar.onDB)
          dar.setTipoRiga(TrasmissioneDocumentiLogis.MERCE);
        if (rlt.getTipoRiga() == SERVIZIO &&
            !dar.onDB)
          dar.setTipoRiga(TrasmissioneDocumentiLogis.SERVIZIO);
        dar.setIdMagazzino(rlt.getCodiceMagLogico());
        dar.setIdArticolo(rlt.getCodiceArticolo());
        if (rlt.getVersione().equals(Gruppo.VERSIONE_DEFAULT))
          dar.setIdVersioneSal(DocumentoVenRigaLotto.VERSIONE_DUMMY); // Verisone default di Panthera
        else
          dar.setIdVersioneSal(new Integer(rlt.getVersione()));
        if (rlt.getLotto2() != null)
          dar.setIdConfigurazione(new Integer(rlt.getLotto2()));
        dar.setDescrizioneArticolo(rlt.getDescManuale());
        dar.setIdUMRif(rlt.getUmDocumento());
        dar.setIdUMPrm(rlt.getUmBase1());//25.07.07
        dar.setIdUMSec(rlt.getUmBase2());//25.07.07
        //13.04.11 - inizio
        /*dar.setDataConsegnaRichiesta(rlt.getDataConsegnaRichiesta());
        dar.setDataConsegnaConfermata(rlt.getDataConsegnaConfermata());*/
        //dar.setPrezzo(rlt.getPrezzoDivisa()); 03.03.09
        // 30.04.07 - inizio
        /*if (rlt.getAliquotaIVA() != null) {
          if (dar.getAssoggettamentoIVA() != null)
            dar.getAssoggettamentoIVA().setAliquotaIVA(new BigDecimal(rlt.getAliquotaIVA()));
        }*/ // 30.04.07 - fine
        /*dar.setIdAgente(rlt.getCodiceAgente1());
               dar.setIdAgenteSub(rlt.getCodiceAgente2());*/
        /*if (rlt.getPriorita() != null)
          dar.setPriorita( (new Integer(rlt.getPriorita())).intValue());*/
        //13.04.11 - fine
        dar.setRicalcoloQtaFattoreConv(false);
        //25.07.07 - inizio
        //BigDecimal qtaEvUMRif = dar.getArticolo().convertiUM(rlt.getQta1Evasa(), dar.getUMPrm(), dar.getUMRif());
        //16.11.09 - inizio
        ArticoloVersione versione = dar.getArticoloVersRichiesta() == null ? dar.getArticoloVersSaldi() : dar.getArticoloVersRichiesta();
        BigDecimal qtaEvUMRif = calcolaQtaDoc(dar.getArticolo(),dar.getUMRif(),rlt, versione, dar.isRicalcoloQtaFattoreConv());
        //16.11.09 - fine
        //25.07.07 - fine
        if (dar.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO &&
            dar.getQtaPropostaEvasione() != null){
            //           dar.getQtaPropostaEvasione().setQuantitaInUMRif(dar.getQtaPropostaEvasione().getQuantitaInUMRif().add(rlt.getQtaDocumento()));
            // Solo per l'installazione IMT: appena definita la relazione con l'UMLogistica rimettere in ordine
            //  queste qta.
            dar.getQtaPropostaEvasione().setQuantitaInUMRif(dar.getQtaPropostaEvasione().getQuantitaInUMRif().add(qtaEvUMRif));//02.11.06
            dar.getQtaPropostaEvasione().setQuantitaInUMPrm(dar.getQtaPropostaEvasione().getQuantitaInUMPrm().add(rlt.getQta2Evasa()));//25.07.07
            dar.getQtaPropostaEvasione().setQuantitaInUMSec(dar.getQtaPropostaEvasione().getQuantitaInUMSec().add(rlt.getQta3Evasa()));//25.07.07
        }
        if (dar.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO &&
            dar.getQtaAttesaEvasione() != null){
            //           dar.getQtaAttesaEvasione().setQuantitaInUMRif(dar.getQtaPropostaEvasione().getQuantitaInUMRif().add(rlt.getQtaDocumento()));
            // Solo per l'installazione IMT: appena definita la relazione con l'UMLogistica rimettere in ordine
            //  queste qta.
            dar.getQtaAttesaEvasione().setQuantitaInUMRif(dar.getQtaAttesaEvasione().getQuantitaInUMRif().add(qtaEvUMRif));//02.11.06
            dar.getQtaAttesaEvasione().setQuantitaInUMPrm(dar.getQtaAttesaEvasione().getQuantitaInUMPrm().add(rlt.getQta2Evasa()));//25.07.07
            dar.getQtaAttesaEvasione().setQuantitaInUMSec(dar.getQtaAttesaEvasione().getQuantitaInUMSec().add(rlt.getQta3Evasa()));//25.07.07
        }
        dar.setStatoInvioLogistica(it.thera.thip.base.documenti.StatoAttivita.NO);
        dar = trascodificaDatiPersonalizzati(rlt, dar);//22.01.07
        //dar = (DocumentoAcqRigaPrm)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST_RIGHE, rlt, dar);//22600
        dar = (DocumentoAcqRigaPrm)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST_RIGHE, rlt, dar, darEsistente);//22600
        String valLotto = rlt.getLotto1();
        if (valLotto == null ||
            valLotto.equals(""))
          valLotto = dar.LOTTO_DUMMY;
        Object[] keyPartsLotto = {idAzienda,
                                  annoDocumento,
                                  numeroDocumento,
                                  numeroRigaDocumento,
                                  dar.getIdArticolo(),
                                  valLotto};
        String keyLotto = KeyHelper.buildObjectKey(keyPartsLotto);
        DocumentoAcqRigaLottoPrm darLotto = null;
        DocumentoAcqRigaLottoPrm darLottoEsistente = null;//22600
        Iterator iterRighePrimarieLotto = dar.getRigheLotto().iterator();
        while (iterRighePrimarieLotto.hasNext() && darLotto == null) {
          DocumentoAcqRigaLottoPrm dar1 = (DocumentoAcqRigaLottoPrm) iterRighePrimarieLotto.next();
          if (dar1.getKey().equals(keyLotto))
            darLotto = dar1;
        }
        if (darLotto == null) {
          darLotto = (DocumentoAcqRigaLottoPrm) Factory.createObject(DocumentoAcqRigaLottoPrm.class);
          darLotto.setFather(dar);
          darLotto.setIdAzienda(dar.getIdAzienda());//25.07.07
          darLotto.setIdArticolo(dar.getArticolo().getIdArticolo());
          darLotto.setIdLotto(valLotto);
          if (ParametriLogis.SHOW_TRACE)
//            System.out.println("  Riga lotto primaria nuova."); Fix 15940
						System.out.println(" " + ResourceLoader.getString(RES_FILE,"gen0009")); // Fix 15940
        } 
        //Fix 22600 inizio
        else{
        	darLottoEsistente = (DocumentoAcqRigaLottoPrm) Factory.createObject(DocumentoAcqRigaLottoPrm.class);
        	try {
        		darLottoEsistente.setEqual(darLotto);
        	}
        	catch (CopyException ex2) {
        		ex2.printStackTrace(Trace.excStream);
        	}        	
        if (ParametriLogis.SHOW_TRACE)
//          System.out.println("  Riga lotto primaria recuperata."); Fix 15940
					System.out.println(" " + ResourceLoader.getString(RES_FILE,"gen0010")); // Fix 15940
        }
        //Fix 22600 fine
        //        darLotto.getQtaPropostaEvasione().setQuantitaInUMRif(rlt.getQtaDocumento());
        // Solo per l'installazione IMT: appena definita la relazione con l'UMLogistica rimettere in ordine
        //  queste qta.
        if (darLotto.getArticolo() != null)
          //25.07.07 - inizio
          //qtaEvUMRif = darLotto.getArticolo().convertiUM(rlt.getQta1Evasa(), dar.getUMPrm(), dar.getUMRif());
          qtaEvUMRif = calcolaQtaDoc(darLotto.getArticolo(),dar.getUMRif(),rlt, versione, dar.isRicalcoloQtaFattoreConv());//16.11.09
          //25.07.07 - fine
        if (dar.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO) {
          darLotto.getQtaPropostaEvasione().setQuantitaInUMRif(qtaEvUMRif);
          darLotto.getQtaPropostaEvasione().setQuantitaInUMPrm(rlt.getQta2Evasa());//25.07.07
          darLotto.getQtaPropostaEvasione().setQuantitaInUMSec(rlt.getQta3Evasa());//25.07.07
        }
        if (dar.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO) {
          darLotto.getQtaAttesaEvasione().setQuantitaInUMRif(qtaEvUMRif);
          darLotto.getQtaAttesaEvasione().setQuantitaInUMPrm(rlt.getQta2Evasa());//25.07.07
          darLotto.getQtaAttesaEvasione().setQuantitaInUMSec(rlt.getQta3Evasa());//25.07.07
        }
        darLotto = trascodificaDatiPersonalizzati(rlt, darLotto);//22.01.07
        //darLotto = (DocumentoAcqRigaLottoPrm)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST_RIGHE_LOTTO, rlt, darLotto);//22600
        darLotto = (DocumentoAcqRigaLottoPrm)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST_RIGHE_LOTTO, rlt, darLotto, darLottoEsistente);//22600
        if (!darLotto.onDB)
          dar.getRigheLotto().add(darLotto);
      } else {    // Riga secondaria/lotto di riga secondaria
        dar = null;
        while (iterRighePrimarie.hasNext() && dar == null) {
          DocumentoAcqRigaPrm dar1 = (DocumentoAcqRigaPrm) iterRighePrimarie.next();
          if (dar1.getKey().equals(key))
            dar = dar1;
        }
        if (dar == null) {
          dar = (DocumentoAcqRigaPrm) Factory.createObject(DocumentoAcqRigaPrm.class);
          dar.setIdAzienda(rlt.getIdAzienda());
          dar.setFather(da);
          dar.setIdCauRig(rlt.getAttributo3());
          dar.completaBO();
          darNuova = true;
          if (ParametriLogis.SHOW_TRACE)
//            System.out.println("  Riga primaria nuova (1)."); Fix 15940
						System.out.println(" " + ResourceLoader.getString(RES_FILE,"gen0011")); // Fix 15940
        } 
        //Fix 22600 inizio
        else{
        	darEsistente = (DocumentoAcqRigaPrm) Factory.createObject(DocumentoAcqRigaPrm.class);
        	try {
        		darEsistente.setEqual(dar);
        	}
        	catch (CopyException ex2) {
        		ex2.printStackTrace(Trace.excStream);
        	}
        if (ParametriLogis.SHOW_TRACE)
//          System.out.println("  Riga primaria recuperata (2)."); Fix 15940
					System.out.println(" " + ResourceLoader.getString(RES_FILE,"gen0012")); // Fix 15940
        }
        //Fix 22600 fine
        dar = trascodificaDatiPersonalizzati(rlt, dar);//22.01.07
        //dar = (DocumentoAcqRigaPrm)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST_RIGHE, rlt, dar);//22600
        dar = (DocumentoAcqRigaPrm)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST_RIGHE, rlt, dar, darEsistente);//22600
        Object[] keyPartsSecon = {idAzienda,
                                  annoDocumento,
                                  numeroDocumento,
                                  numeroRigaDocumento,
                                  dettailRigaDocumento};
        String keySecon = KeyHelper.buildObjectKey(keyPartsSecon);
        Iterator iterRigheSecondarie = dar.getRigheSecondarie().iterator();
        DocumentoAcqRigaSec darSecon = null;
        DocumentoAcqRigaSec darSeconEsistente = null;//22600
        while (iterRigheSecondarie.hasNext() && darSecon == null) {
          DocumentoAcqRigaSec dar1 = (DocumentoAcqRigaSec) iterRigheSecondarie.next();
          if (dar1.getKey().equals(keySecon))
            darSecon = dar1;
        }
        if (darSecon == null) {
          darSecon = (DocumentoAcqRigaSec) Factory.createObject(DocumentoAcqRigaSec.class);
          darSecon.setFather(dar);
          darSecon.setIdCauRig(rlt.getAttributo3());
          darSecon.completaBO();
          darSeconNuova = true;
          if (ParametriLogis.SHOW_TRACE)
//            System.out.println("  Riga secondaria nuova."); Fix 15940
						System.out.println(" " + ResourceLoader.getString(RES_FILE,"gen0013")); // Fix 15940
        } 
        //Fix 22600 inizio
        else{
        	darSeconEsistente = (DocumentoAcqRigaSec) Factory.createObject(DocumentoAcqRigaSec.class);
        	try {
        		darSeconEsistente.setEqual(darSecon);
        	}
        	catch (CopyException ex2) {
        		ex2.printStackTrace(Trace.excStream);
        	}
        if (ParametriLogis.SHOW_TRACE)
//          System.out.println("  Riga secondaria recuperata."); Fix 15940
					System.out.println(" " + ResourceLoader.getString(RES_FILE,"gen0014")); // Fix 15940
        }
        //Fix 22600 fine
        if (rlt.getTipoRiga() == ARTICOLI &&
            !darSecon.onDB)
          darSecon.setTipoRiga(TrasmissioneDocumentiLogis.MERCE);
        if (rlt.getTipoRiga() == SERVIZIO &&
            !darSecon.onDB)
          darSecon.setTipoRiga(TrasmissioneDocumentiLogis.SERVIZIO);
        darSecon.setIdMagazzino(rlt.getCodiceMagLogico());
        darSecon.setIdArticolo(rlt.getCodiceArticolo());
        if (rlt.getVersione().equals(Gruppo.VERSIONE_DEFAULT))
          darSecon.setIdVersioneSal(DocumentoVenRigaLotto.VERSIONE_DUMMY);//03.12.18
        else
          darSecon.setIdVersioneSal(new Integer(rlt.getVersione()));//03.12.18
        if (rlt.getLotto2()!= null)
          darSecon.setIdConfigurazione(new Integer(rlt.getLotto2()));
        darSecon.setDescrizioneArticolo(rlt.getDescManuale());
        darSecon.setIdUMRif(rlt.getUmDocumento());
        darSecon.setIdUMPrm(rlt.getUmBase1());//25.07.07
        darSecon.setIdUMSec(rlt.getUmBase2());//25.07.07
        //13.04.11 - inizio
        /*darSecon.setDataConsegnaRichiesta(rlt.getDataConsegnaRichiesta());
        darSecon.setDataConsegnaConfermata(rlt.getDataConsegnaConfermata());*/
        //darSecon.setPrezzo(rlt.getPrezzoDivisa()); 03.03.09
        // 30.04.07 - inizio
        /*if (rlt.getAliquotaIVA() != null) {
          if (darSecon.getAssoggettamentoIVA() != null)
            darSecon.getAssoggettamentoIVA().setAliquotaIVA(new BigDecimal(rlt.getAliquotaIVA()));
        }*/ // 30.04.07 - inizio
        /*darSecon.setIdAgente(rlt.getCodiceAgente1());
                   darSecon.setIdAgenteSub(rlt.getCodiceAgente2());*/
        /*if (rlt.getPriorita() != null)
          darSecon.setPriorita((new Integer(rlt.getPriorita())).intValue());*/
        //13.04.11 - fine
        darSecon.setRicalcoloQtaFattoreConv(false);
        //25.07.07 - inizio
        //BigDecimal qtaEvUMRif = darSecon.getArticolo().convertiUM(rlt.getQta1Evasa(), darSecon.getUMPrm(), darSecon.getUMRif());//02.11.06
        //16.11.09 - inizio
        ArticoloVersione versione = darSecon.getArticoloVersRichiesta() == null ? darSecon.getArticoloVersSaldi() : darSecon.getArticoloVersRichiesta();
        BigDecimal qtaEvUMRif = calcolaQtaDoc(darSecon.getArticolo(),darSecon.getUMRif(),rlt, versione, darSecon.isRicalcoloQtaFattoreConv());
        //16.11.09 - fine
        //25.07.07 - fine
        if (darSecon.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO &&
            darSecon.getQtaPropostaEvasione() != null){
          darSecon.getQtaPropostaEvasione().setQuantitaInUMRif(darSecon.getQtaPropostaEvasione().getQuantitaInUMRif().add(qtaEvUMRif));//02.11.06
          darSecon.getQtaPropostaEvasione().setQuantitaInUMPrm(darSecon.getQtaPropostaEvasione().getQuantitaInUMPrm().add(rlt.getQta2Evasa()));//25.07.07
          darSecon.getQtaPropostaEvasione().setQuantitaInUMSec(darSecon.getQtaPropostaEvasione().getQuantitaInUMSec().add(rlt.getQta3Evasa()));//25.07.07
        }
        if (darSecon.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO &&
            darSecon.getQtaAttesaEvasione() != null){
          darSecon.getQtaAttesaEvasione().setQuantitaInUMRif(darSecon.getQtaAttesaEvasione().getQuantitaInUMRif().add(qtaEvUMRif));//02.11.06
          darSecon.getQtaAttesaEvasione().setQuantitaInUMPrm(darSecon.getQtaAttesaEvasione().getQuantitaInUMPrm().add(rlt.getQta2Evasa()));//25.07.07
          darSecon.getQtaAttesaEvasione().setQuantitaInUMSec(darSecon.getQtaAttesaEvasione().getQuantitaInUMSec().add(rlt.getQta3Evasa()));//25.07.07
        }
        darSecon.setStatoInvioLogistica(it.thera.thip.base.documenti.StatoAttivita.NO);
        darSecon = trascodificaDatiPersonalizzati(rlt, darSecon);//22.01.07
        //darSecon = (DocumentoAcqRigaSec)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST_RIGHE, rlt, darSecon);//22600
        darSecon = (DocumentoAcqRigaSec)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST_RIGHE, rlt, darSecon, darSeconEsistente);//22600
        String valLotto = rlt.getLotto1();
        if (valLotto == null ||
            valLotto.equals(""))
          valLotto = dar.LOTTO_DUMMY;
        Object[] keyPartsLotto = {idAzienda,
                                  annoDocumento,
                                  numeroDocumento,
                                  numeroRigaDocumento,
                                  dettailRigaDocumento,
                                  darSecon.getIdArticolo(),
                                  valLotto};
        String keyLotto = KeyHelper.buildObjectKey(keyPartsLotto);
        DocumentoAcqRigaLottoSec darLotto = null;
        DocumentoAcqRigaLottoSec darLottoEsistente = null;//22600
        Iterator iterRigheSecondarieLotto = darSecon.getRigheLotto().iterator();
        while (iterRigheSecondarieLotto.hasNext() && darLotto == null) {
          DocumentoAcqRigaLottoSec dar1 = (DocumentoAcqRigaLottoSec) iterRigheSecondarieLotto.next();
          if (dar1.getKey().equals(keyLotto))
            darLotto = dar1;
        }
        if (darLotto == null) {
          darLotto = (DocumentoAcqRigaLottoSec) Factory.createObject(DocumentoAcqRigaLottoSec.class);
          darLotto.setFather(darSecon);
          darLotto.setIdAzienda(darSecon.getIdAzienda());//25.07.07
          darLotto.setIdArticolo(darSecon.getIdArticolo());
          darLotto.setIdLotto(valLotto);
          if (ParametriLogis.SHOW_TRACE)
//            System.out.println("  Riga lotto secondaria nuova."); Fix 15940
						System.out.println(" " + ResourceLoader.getString(RES_FILE,"gen0015")); // Fix 15940
        } 
        //Fix 22600 inizio
        else{
        	darLottoEsistente = (DocumentoAcqRigaLottoSec) Factory.createObject(DocumentoAcqRigaLottoSec.class);
        	try {
        		darLottoEsistente.setEqual(darLotto);
        	}
        	catch (CopyException ex2) {
        		ex2.printStackTrace(Trace.excStream);
        	}
        if (ParametriLogis.SHOW_TRACE)
//          System.out.println("  Riga lotto secondaria recuperata."); Fix 15940
					System.out.println(" " + ResourceLoader.getString(RES_FILE,"gen0016")); // Fix 15940
        }
        //Fix 22600 fine
        //25.07.07 - inizio
        //qtaEvUMRif = darLotto.getArticolo().convertiUM(rlt.getQta1Evasa(), darSecon.getUMPrm(), darSecon.getUMRif());//02.11.06
        qtaEvUMRif = calcolaQtaDoc(darLotto.getArticolo(), darSecon.getUMRif(), rlt, versione, darSecon.isRicalcoloQtaFattoreConv());//16.11.09
        //25.07.07 - inizio
        if (darSecon.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO) {
          darLotto.getQtaPropostaEvasione().setQuantitaInUMRif(qtaEvUMRif);
          darLotto.getQtaPropostaEvasione().setQuantitaInUMPrm(rlt.getQta2Evasa());//25.07.07
          darLotto.getQtaPropostaEvasione().setQuantitaInUMSec(rlt.getQta3Evasa());//25.07.07
        }
        if (darSecon.getStatoAvanzamento() == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO) {
          darLotto.getQtaAttesaEvasione().setQuantitaInUMRif(qtaEvUMRif);
          darLotto.getQtaAttesaEvasione().setQuantitaInUMPrm(rlt.getQta2Evasa());//25.07.07
          darLotto.getQtaAttesaEvasione().setQuantitaInUMSec(rlt.getQta3Evasa());//25.07.07
        }
        darLotto = trascodificaDatiPersonalizzati(rlt, darLotto);//22.01.07
        //darLotto = (DocumentoAcqRigaLottoSec)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST_RIGHE, rlt, darLotto);//22600
        darLotto = (DocumentoAcqRigaLottoSec)TrascodificaThipLogis.trascodificaDati(TrascodificaThipLogis.RX_PACKING_LIST_RIGHE, rlt, darLotto, darLottoEsistente);//22600
        if (!darLotto.onDB)
          darSecon.getRigheLotto().add(darLotto);
        if (darSeconNuova)
          dar.getRigheSecondarie().add(darSecon);
      }
      if (darNuova)
        da.getRighe().add(dar);
    }
    // Elimia le righe lotto con qta = 0;
    for (int j = 0; j < da.getRighe().size(); j++) {
      DocumentoAcqRigaPrm dar = (DocumentoAcqRigaPrm) da.getRighe().get(j);
      //03.02.09 - inizio
      //se la riga doc. non è stata trasmessa alla logistica non viene modificata
      if (!isRigaTrasmessa(dar.getNumeroRigaDocumento(), dar.getDettaglioRigaDocumento(), righeLista))
        continue;
      //03.02.09 - fine
      if (!testTrasmissioneDoc(dar))
        continue;
      char statoAvzDAR = it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO;
      String tmp = (String) statoOriginale.get(dar.getKey());
      if (tmp != null)
        statoAvzDAR = tmp.charAt(0);
      for (int i = 0; i < dar.getRigheLotto().size(); i++) {
        DocumentoAcqRigaLottoPrm darLotto = (DocumentoAcqRigaLottoPrm) dar.getRigheLotto().get(i);
        if ((statoAvzDAR == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO && darLotto.getQtaPropostaEvasione() == null) ||
            (statoAvzDAR == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO && darLotto.getQtaAttesaEvasione() == null))
          dar.getRigheLotto().remove(i);
        else {
         //le righe lotto non evase vanno cancellate solo se ho prelevato qualcosa della riga primaria
         if (dar.getQtaPropostaEvasione() != null &&
             !dar.getQtaPropostaEvasione().isZero()){
           if (statoAvzDAR == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO &&
               darLotto.getQtaPropostaEvasione().getQuantitaInUMPrm().compareTo(new BigDecimal(0)) == 0) {
             if (!darLotto.getLotto().getCodiceLotto().equals(dar.LOTTO_DUMMY)){
               reimpostaQtaOriginali(darLotto,statoAvzDAR,qtaOriginali);//19.03.08
               dar.getRigheLotto().remove(i);
               i--;
             }
             else
             if (dar.getQtaPropostaEvasione().getQuantitaInUMPrm().compareTo(new BigDecimal(0)) > 0){
               reimpostaQtaOriginali(darLotto,statoAvzDAR,qtaOriginali);//19.03.08
               dar.getRigheLotto().remove(i);
             }
           }
           if (statoAvzDAR == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO &&
               darLotto.getQtaAttesaEvasione().getQuantitaInUMPrm().compareTo(new BigDecimal(0)) == 0) {
             if (!darLotto.getLotto().equals(dar.LOTTO_DUMMY)){
               reimpostaQtaOriginali(darLotto,statoAvzDAR,qtaOriginali);//19.03.08
               dar.getRigheLotto().remove(i);
             }
             else
             if (dar.getQtaAttesaEvasione().getQuantitaInUMPrm().compareTo(new BigDecimal(0)) > 0){
               reimpostaQtaOriginali(darLotto,statoAvzDAR,qtaOriginali);//19.03.08
               dar.getRigheLotto().remove(i);
             }
           }
         }
       }
     }
     if (statoAvzDAR == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO &&
         dar.getQtaPropostaEvasione().isZero()) {
       dar.getDatiComuniEstesi().setStato(DatiComuniEstesi.ANNULLATO);
       dar.setStatoAvanzamento(it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO);//08.06.06
       BigDecimal qta = new BigDecimal(1);
       String qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dar.getKey(), UM_RIF}));
       if (qtaStr != null) {
         try {
           qta = new BigDecimal(qtaStr);
         } catch (Exception ex) {
           ex.printStackTrace(Trace.excStream);
         }
       }
       dar.getQtaAttesaEvasione().setQuantitaInUMRif(qta);
       qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dar.getKey(), UM_PRM}));
       if (qtaStr != null) {
         try {
           qta = new BigDecimal(qtaStr);
         } catch (Exception ex) {
           ex.printStackTrace(Trace.excStream);
         }
       }
       dar.getQtaAttesaEvasione().setQuantitaInUMPrm(qta);
       qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dar.getKey(), UM_SEC}));
       if (qtaStr != null) {
         try {
           qta = new BigDecimal(qtaStr);
         } catch (Exception ex) {
           ex.printStackTrace(Trace.excStream);
         }
       }
       dar.getQtaAttesaEvasione().setQuantitaInUMSec(qta);
       // ripristino la qtà originale sulle righe lotto primarie
       for (int i = 0; i < dar.getRigheLotto().size(); i++) {
         DocumentoAcqRigaLottoPrm darlotto = (DocumentoAcqRigaLottoPrm) dar.getRigheLotto().get(i);
         qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {darlotto.getKey(), UM_RIF}));
         if (qtaStr != null) {
           try {
             qta = new BigDecimal(qtaStr);
           }
           catch (Exception ex) {
             ex.printStackTrace(Trace.excStream);
           }
         }
         darlotto.getQtaAttesaEvasione().setQuantitaInUMRif(qta);
         qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {darlotto.getKey(), UM_PRM}));
         if (qtaStr != null) {
           try {
             qta = new BigDecimal(qtaStr);
           }
           catch (Exception ex) {
             ex.printStackTrace(Trace.excStream);
           }
         }
         darlotto.getQtaAttesaEvasione().setQuantitaInUMPrm(qta);
         qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {darlotto.getKey(), UM_SEC}));
         if (qtaStr != null) {
           try {
             qta = new BigDecimal(qtaStr);
           }
           catch (Exception ex) {
             ex.printStackTrace(Trace.excStream);
           }
         }
         darlotto.getQtaAttesaEvasione().setQuantitaInUMSec(qta);
       }
     }
     if (statoAvzDAR == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO &&
         dar.getQtaAttesaEvasione().isZero()) {
       dar.getDatiComuniEstesi().setStato(DatiComuniEstesi.ANNULLATO);
       BigDecimal qta = new BigDecimal(1);
       String qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dar.getKey(), UM_RIF}));
       if (qtaStr != null) {
         try {
           qta = new BigDecimal(qtaStr);
         } catch (Exception ex) {
           ex.printStackTrace(Trace.excStream);
         }
       }
       dar.getQtaAttesaEvasione().setQuantitaInUMRif(qta);
       qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dar.getKey(), UM_PRM}));
       if (qtaStr != null) {
         try {
           qta = new BigDecimal(qtaStr);
         } catch (Exception ex) {
           ex.printStackTrace(Trace.excStream);
         }
       }
       dar.getQtaAttesaEvasione().setQuantitaInUMPrm(qta);
       qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {dar.getKey(), UM_SEC}));
       if (qtaStr != null) {
         try {
           qta = new BigDecimal(qtaStr);
         } catch (Exception ex) {
           ex.printStackTrace(Trace.excStream);
         }
       }
       dar.getQtaAttesaEvasione().setQuantitaInUMSec(qta);
       // ripristino la qtà originale sulle righe lotto primarie
       for (int i = 0; i < dar.getRigheLotto().size(); i++) {
         DocumentoAcqRigaLottoPrm darlotto = (DocumentoAcqRigaLottoPrm) dar.getRigheLotto().get(i);
         qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {darlotto.getKey(), UM_RIF}));
         if (qtaStr != null) {
           try {
             qta = new BigDecimal(qtaStr);
           }
           catch (Exception ex) {
             ex.printStackTrace(Trace.excStream);
           }
         }
         darlotto.getQtaAttesaEvasione().setQuantitaInUMRif(qta);
         qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {darlotto.getKey(), UM_PRM}));
         if (qtaStr != null) {
           try {
             qta = new BigDecimal(qtaStr);
           }
           catch (Exception ex) {
             ex.printStackTrace(Trace.excStream);
           }
         }
         darlotto.getQtaAttesaEvasione().setQuantitaInUMPrm(qta);
         qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {darlotto.getKey(), UM_SEC}));
         if (qtaStr != null) {
           try {
             qta = new BigDecimal(qtaStr);
           }
           catch (Exception ex) {
             ex.printStackTrace(Trace.excStream);
           }
         }
         darlotto.getQtaAttesaEvasione().setQuantitaInUMSec(qta);
       }
     }
     for (int k = 0; k < dar.getRigheSecondarie().size() && !reso; k++) {//15.01.07
       DocumentoAcqRigaSec darS = (DocumentoAcqRigaSec) dar.getRigheSecondarie().get(k);
       //03.02.09 - inizio
       //se la riga doc. non è stata trasmessa alla logistica non viene modificata
       if (!isRigaTrasmessa(darS.getNumeroRigaDocumento(), darS.getDettaglioRigaDocumento(), righeLista))
         continue;
       //03.02.09 - fine
       if (!testTrasmissioneDoc(darS))
         continue;
       darS.setTrasmissioneALogisDaEffettuare(false);//16.04.10
       char statoAvzDARS = it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO;
       tmp = (String) statoOriginale.get(darS.getKey());
       if (tmp != null)
         statoAvzDARS = tmp.charAt(0);
       for (int i = 0; i < darS.getRigheLotto().size(); i++) {
         DocumentoAcqRigaLottoSec darLotto = (DocumentoAcqRigaLottoSec) darS.getRigheLotto().get(i);
         if ((statoAvzDARS == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO && darLotto.getQtaPropostaEvasione() == null) ||
             (statoAvzDARS == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO && darLotto.getQtaAttesaEvasione() == null))
           darS.getRigheLotto().remove(i);
         else
           //le righe lotto non evase vanno cancellate solo se ho prelevato qualcosa della riga sec.
           if (darS.getQtaPropostaEvasione() != null &&
               !darS.getQtaPropostaEvasione().isZero()){
             if (statoAvzDARS == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO &&
                 darLotto.getQtaPropostaEvasione().getQuantitaInUMPrm().compareTo(new BigDecimal(0)) == 0) {
               if (!darLotto.getLotto().equals(dar.LOTTO_DUMMY)){
                 reimpostaQtaOriginali(darLotto,statoAvzDARS,qtaOriginali);//19.03.08
                 darS.getRigheLotto().remove(i);
               }
               else
               if (darS.getQtaPropostaEvasione().getQuantitaInUMPrm().compareTo(new BigDecimal(0)) > 0){
                 reimpostaQtaOriginali(darLotto,statoAvzDARS,qtaOriginali);//19.03.08
                 darS.getRigheLotto().remove(i);
               }
             }
             if (statoAvzDARS == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO &&
                 darLotto.getQtaAttesaEvasione().getQuantitaInUMPrm().compareTo(new BigDecimal(0)) == 0) {
               if (!darLotto.getLotto().equals(dar.LOTTO_DUMMY)){
                 reimpostaQtaOriginali(darLotto,statoAvzDARS,qtaOriginali);//19.03.08
                 darS.getRigheLotto().remove(i);
               }
               else
               if (darS.getQtaAttesaEvasione().getQuantitaInUMPrm().compareTo(new BigDecimal(0)) > 0){
                 reimpostaQtaOriginali(darLotto,statoAvzDARS,qtaOriginali);//19.03.08
                 darS.getRigheLotto().remove(i);
               }
             }
           }
         }
         if (statoAvzDARS == it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO &&
             darS.getQtaPropostaEvasione().isZero()) {
           darS.getDatiComuniEstesi().setStato(DatiComuniEstesi.ANNULLATO);
           darS.setStatoAvanzamento(it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO);//08.06.06
           BigDecimal qta = new BigDecimal(1);
           String qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {darS.getKey(), UM_RIF}));
           if (qtaStr != null) {
             try {
               qta = new BigDecimal(qtaStr);
             } catch (Exception ex) {
               ex.printStackTrace(Trace.excStream);
             }
           }
           darS.getQtaAttesaEvasione().setQuantitaInUMRif(qta);
           qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {darS.getKey(), UM_PRM}));
           if (qtaStr != null) {
             try {
               qta = new BigDecimal(qtaStr);
             } catch (Exception ex) {
               ex.printStackTrace(Trace.excStream);
             }
           }
           darS.getQtaAttesaEvasione().setQuantitaInUMPrm(qta);
           qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {darS.getKey(), UM_SEC}));
           if (qtaStr != null) {
             try {
               qta = new BigDecimal(qtaStr);
             } catch (Exception ex) {
               ex.printStackTrace(Trace.excStream);
             }
           }
           darS.getQtaAttesaEvasione().setQuantitaInUMSec(qta);
           // ripristino la qtà originale sulle righe lotto secondarie
           for (int i = 0; i < darS.getRigheLotto().size(); i++) {
             DocumentoAcqRigaLottoSec darSlotto = (DocumentoAcqRigaLottoSec) darS.getRigheLotto().get(i);
             qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {darSlotto.getKey(), UM_RIF}));
             if (qtaStr != null) {
               try {
                 qta = new BigDecimal(qtaStr);
               }
               catch (Exception ex) {
                 ex.printStackTrace(Trace.excStream);
               }
             }
             darSlotto.getQtaAttesaEvasione().setQuantitaInUMRif(qta);
             qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {darSlotto.getKey(), UM_PRM}));
             if (qtaStr != null) {
               try {
                 qta = new BigDecimal(qtaStr);
               }
               catch (Exception ex) {
                 ex.printStackTrace(Trace.excStream);
               }
             }
             darSlotto.getQtaAttesaEvasione().setQuantitaInUMPrm(qta);
             qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {darSlotto.getKey(), UM_SEC}));
             if (qtaStr != null) {
               try {
                 qta = new BigDecimal(qtaStr);
               }
               catch (Exception ex) {
                 ex.printStackTrace(Trace.excStream);
               }
             }
             darSlotto.getQtaAttesaEvasione().setQuantitaInUMSec(qta);
           }
         }
         if (statoAvzDARS == it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO &&
             darS.getQtaAttesaEvasione().isZero()) {
           darS.getDatiComuniEstesi().setStato(DatiComuniEstesi.ANNULLATO);
           BigDecimal qta = new BigDecimal(1);
           String qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {darS.getKey(), UM_RIF}));
           if (qtaStr != null) {
             try {
               qta = new BigDecimal(qtaStr);
             } catch (Exception ex) {
               ex.printStackTrace(Trace.excStream);
             }
           }
           darS.getQtaAttesaEvasione().setQuantitaInUMRif(qta);
           qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {darS.getKey(), UM_PRM}));
           if (qtaStr != null) {
             try {
               qta = new BigDecimal(qtaStr);
             } catch (Exception ex) {
               ex.printStackTrace(Trace.excStream);
             }
           }
           darS.getQtaAttesaEvasione().setQuantitaInUMPrm(qta);
           qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {darS.getKey(), UM_SEC}));
           if (qtaStr != null) {
             try {
               qta = new BigDecimal(qtaStr);
             } catch (Exception ex) {
               ex.printStackTrace(Trace.excStream);
             }
           }
           darS.getQtaAttesaEvasione().setQuantitaInUMSec(qta);
           // ripristino la qtà originale sulle righe lotto secondarie
           for (int i = 0; i < darS.getRigheLotto().size(); i++) {
             DocumentoAcqRigaLottoSec darSlotto = (DocumentoAcqRigaLottoSec) darS.getRigheLotto().get(i);
             qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {darSlotto.getKey(), UM_RIF}));
             if (qtaStr != null) {
               try {
                 qta = new BigDecimal(qtaStr);
               }
               catch (Exception ex) {
                 ex.printStackTrace(Trace.excStream);
               }
             }
             darSlotto.getQtaAttesaEvasione().setQuantitaInUMRif(qta);
             qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {darSlotto.getKey(), UM_PRM}));
             if (qtaStr != null) {
               try {
                 qta = new BigDecimal(qtaStr);
               }
               catch (Exception ex) {
                 ex.printStackTrace(Trace.excStream);
               }
             }
             darSlotto.getQtaAttesaEvasione().setQuantitaInUMPrm(qta);
             qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {darSlotto.getKey(), UM_SEC}));
             if (qtaStr != null) {
               try {
                 qta = new BigDecimal(qtaStr);
               }
               catch (Exception ex) {
                 ex.printStackTrace(Trace.excStream);
               }
             }
             darSlotto.getQtaAttesaEvasione().setQuantitaInUMSec(qta);
           }
         }
       }
     }
     //da.setTipoCollegamentoLogistica(it.thera.thip.base.comuniVenAcq.TipoCollegamentoLogistica.NO_LOGIS);06.04.10
     for (int i=0; i<da.getRighe().size(); i++){
       DocumentoAcqRigaPrm riga = (DocumentoAcqRigaPrm) da.getRighe().get(i);
       //03.02.09 - inizio
       //se la riga doc. non è stata trasmessa alla logistica non viene modificata
       if (!isRigaTrasmessa(riga.getNumeroRigaDocumento(), riga.getDettaglioRigaDocumento(), righeLista))
         continue;
       //03.02.09 - fine
       // se tutte le righe sec. sono annullate devo annullare anche la riga primaria
       boolean annulla = true;
       for (int j=0; j<riga.getRigheSecondarie().size(); j++){
         DocumentoAcqRigaSec rigaS = (DocumentoAcqRigaSec) riga.getRigheSecondarie().get(j);
         if (rigaS.getDatiComuniEstesi().getStato() != DatiComuniEstesi.ANNULLATO){
           annulla = false;
           break;
         }
       }
       if (annulla && riga.getRigheSecondarie().size() > 0)
         riga.getDatiComuniEstesi().setStato(DatiComuniEstesi.ANNULLATO);
       try {
         riga.rendiDefinitivoRiga();
       }
       catch (ThipException e) {
         e.printStackTrace(Trace.excStream);
//         System.out.println("Errore durante rendiDefinitivoRiga(): " + e.getMessage()); Fix 15940
				 System.out.println(ResourceLoader.getString(RES_FILE, "gen0017", new String[]{e.getMessage()})); // Fix 15940
         //09095 MM
         //return new ErrorMessage("THIP_BS165", new String[] {riga.getIdArticolo(), riga.getKey()});
//         return new ErrorMessage("LOGIS01054", "Riga " + riga.getKey() + " non definitiva. " + e.getMessage()); Fix 15940
				 return new ErrorMessage("LOGIST0369", new String[]{KeyHelper.formatKeyString(riga.getKey()), e.getMessage()}); // Fix 15940
       }
       catch (SQLException e) {
         e.printStackTrace(Trace.excStream);
//         System.out.println("Errore durante rendiDefinitivoRiga(): " + e.getMessage()); Fix 15940
				 System.out.println(ResourceLoader.getString(RES_FILE, "gen0017", new String[]{e.getMessage()})); // Fix 15940
         return new ErrorMessage("THIP_BS165", new String[] {riga.getIdArticolo(), riga.getKey()});
       }
     }
     
     //16.11.20 - inizio
     //se almeno un articolo è matricolato allora recupero tutte le matricole della lista
     try {
    	 for (int i=0; i<righeLista.size(); i++) {
    		 RigaLista rl = (RigaLista) righeLista.get(i);
    		 if (rl.getArticolo().getGestioneMatricola()){
    			 Vector elencoMatricole = trovaMatricoleLista(rl.getTestataLista());
    			 for (int j=0; j<elencoMatricole.size(); j++) {
    				 Matricola mat = (Matricola) elencoMatricole.get(j);
    				 int ret = aggiornaStoricoMatricola(mat,null,da);
    				 if (ret < ErrorCodes.NO_ROWS_UPDATED){
    					 return new ErrorMessage("LOGIS05107", new String[]{ret+""});
    				 }
    				 //12.07.19 - inizio
    				 //aggiornamento stato matricola
    				 LottoMatricola lm = null;
    				 //String chiaveMatricola = KeyHelper.buildObjectKey(new String[] {da.getIdAzienda(), rl.getCodiceArticolo(), mat.getMatricola()});//24.09.25
    				 String chiaveMatricola = KeyHelper.buildObjectKey(new String[] {da.getIdAzienda(), mat.getCodiceArticolo(), mat.getMatricola()});
    				 lm = LottoMatricola.elementWithKey(chiaveMatricola, PersistentObject.OPTIMISTIC_LOCK);
    				 if (lm != null){
    					 lm.setStatoMatricola(LottoMatricola.STATO_MAT_VENDUTA);
    					 lm.setAnnoDocVendita(da.getAnnoDocumento());
    					 lm.setNumeroDocVendita(da.getNumeroDocumento());
    					 lm.setDataDocVendita(da.getDataDocumento());
    					 ret = lm.save();
    					 if (ret <= ErrorCodes.NO_ROWS_UPDATED){
    						 return new ErrorMessage("LOGIS05109", new String[]{ret+""});
    					 }
    				 }
    				 //12.07.19 - fine
    			 }
    			 break;
    		 }
    	 }
     } catch (SQLException e) {
    	 e.printStackTrace(Trace.excStream);
    	 System.out.println(ResourceLoader.getString(RES_FILE,"gen0019") + " " + e.getMessage());
    	 return new ErrorMessage("LOGIST0379", new String[]{e.getMessage()});
     }
     //16.11.20 - fine
     //Fix 37183 Inizio
     BigDecimal pesiLordoDaDoc = da.getPesoLordo();
     BigDecimal pesiNettoDaDoc = da.getPesoNetto();
     BigDecimal volumeDaDoc = da.getVolume();
     if(!da.isRicalcolaPesiEVolume())
 	    {
 		    BigDecimal[] pesiEVolumeRecalcolata = recalcolaPesiVolumDocAcq(da);
 		    if(getPeso() != null)
 		    	da.setPesoLordo(pesiLordoDaDoc);
 		    if(getPeso() != null)
 		    	da.setPesoNetto(pesiNettoDaDoc);
 		    if(getVolume() != null && (getVolume().compareTo(new BigDecimal(0)) > 0))
 		    	da.setVolume(volumeDaDoc);
 	    }
     //Fix 37183 Fine     
     ArrayList arrS = new ArrayList();
     BODataCollector boDC = null;
     try {
       boDC = createDataCollector("DocumentoAcquisto");
     }
     catch (SQLException e) {
       e.printStackTrace(Trace.excStream);
// Fix 15940 inizio
//       System.out.println("Errore durante createDataCollector(): " + e.getMessage());
//			 return new ErrorMessage("LOGIS01054","Errore durante createDataCollector(): " + e.getMessage());
			 System.out.println(ResourceLoader.getString(RES_FILE, "gen0018", new String[]{e.getMessage()}));
			 return new ErrorMessage("LOGIST0373", new String[]{e.getMessage()});
// Fix 15940 fine
     }
//     DocumentoTestata.raggruppaRigheStessoOrdine(da);
     raggruppaRigheStessoOrdine(da);//21.03.08
     if (tutteLeRigheSonoDefinitive(da))//07.01.10
       da.setStatoAvanzamento(it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO);
     ConnectionDescriptor conn = null; //20.10.08
     try {
       if (!salvaPerRiga){//20.10.08
         // salvataggio del documento complessivo
         if (tutteLeRigheSonoDefinitive(da))//07.01.10
           da.setStatoAvanzamento(it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO);//11.10.06
         da.setSalvataggioRigheForzato(true);//07.11.07
         boDC.setBo(da);
         DocumentoDataCollector.initRighePerCheck(boDC);
         int rc1 = boDC.save();
         if (rc1 == BODataCollector.ERROR){
           ErrorList err = boDC.getErrorList();
//           scriviListaErrori(err,"Errore durante il salvataggio del documento: "); Fix 15940
					 scriviListaErrori(err, ResourceLoader.getString(RES_FILE, "gen0019") + " "); // Fix 15940
           arrS.addAll(err.getErrors());
         }
       }
       //20.10.08 - inizio
       // salvataggio riga per riga con commit intermedie
       else {
         da.setStatoAvanzamento(it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO);//
         boDC.setBo(da);
         DocumentoDataCollector.initRighePerCheck(boDC);
         conn = ConnectionManager.pushConnection();
       }
       // se la save della testata non ha dato problemi salvo le righe una alla volta
       if (arrS.isEmpty() && salvaPerRiga){
         ErrorList errori = salvaRigheSingoleAcq(boDC.getBo());
         // se la save delle righe non ha dato problemi salvo la testata
         if (errori.getErrors().size() == 0){
           if (tutteLeRigheSonoDefinitive(da))//07.01.10
             da.setStatoAvanzamento(it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO);
           da.setSalvataggioRigheForzato(false);
           da.calcolaCostiValoriOrdine();
           boDC.setAutoCommit(true);
           rc = boDC.save();
           if (rc == BODataCollector.ERROR){
             ErrorList err = boDC.getErrorList();
//             scriviListaErrori(err,"Errore durante il salvataggio della testata del documento in stato definitivo: "); Fix 15940
						 scriviListaErrori(err, ResourceLoader.getString(RES_FILE,"gen0020") + " "); // Fix 15940
             arrS.addAll(err.getErrors());
           }
         }
         else {
//           scriviListaErrori(errori,"Errore durante il salvataggio delle righe del documento: "); Fix 15940
					 scriviListaErrori(errori, ResourceLoader.getString(RES_FILE,"gen0021") + " "); // Fix 15940
           arrS.addAll(errori.getErrors());
         }
       }
       //16.11.20 - inizio
       /* blocco spostato prima di salvare il documento, altrimenti dava errore di quadratura matricole
       //28.06.19 - inizio
       //se almeno un articolo è matricolato allora recupero tutte le matricole della lista
       for (int i=0; i<righeLista.size(); i++) {
      	 RigaLista rl = (RigaLista) righeLista.get(i);
      	 if (rl.getArticolo().getGestioneMatricola()){
      		 Vector elencoMatricole = trovaMatricoleLista(rl.getTestataLista());
      		 for (int j=0; j<elencoMatricole.size(); j++) {
      			 Matricola mat = (Matricola) elencoMatricole.get(j);
      			 int ret = aggiornaStoricoMatricola(mat,null,da);
      			 if (ret < ErrorCodes.NO_ROWS_UPDATED){
      				 return new ErrorMessage("LOGIS05107", new String[]{ret+""});
      			 }
 		      	//12.07.19 - inizio
 		      	//aggiornamento stato matricola
 		      	LottoMatricola lm = null;
 		      	String chiaveMatricola = KeyHelper.buildObjectKey(new String[] {da.getIdAzienda(), rl.getCodiceArticolo(), mat.getMatricola()});
 		      	lm = LottoMatricola.elementWithKey(chiaveMatricola, PersistentObject.OPTIMISTIC_LOCK);
 		      	if (lm != null){
 		      		lm.setStatoMatricola(LottoMatricola.STATO_MAT_VENDUTA);
 		      		lm.setAnnoDocVendita(da.getAnnoDocumento());
 		      		lm.setNumeroDocVendita(da.getNumeroDocumento());
 		      		lm.setDataDocVendita(da.getDataDocumento());
 		      		ret = lm.save();
 		      		if (ret <= ErrorCodes.NO_ROWS_UPDATED){
 		      			return new ErrorMessage("LOGIS05109", new String[]{ret+""});
 		      		}
 		      	}
 		      	//12.07.19 - fine
      		 }
      		 break;
      	 }
       }
       //28.06.19 - fine
        */
       //16.11.20 - fine
     }
     catch(SQLException e) {
// Fix 15940 inizio
//       e.printStackTrace();
//       System.out.println("Errore durante salvataggio del documento: " + e.getMessage());
//       return new ErrorMessage("LOGIS01054", "Errore durante salvataggio del documento: " + e.getMessage());
			 e.printStackTrace(Trace.excStream);
			 System.out.println(ResourceLoader.getString(RES_FILE,"gen0019") + " " + e.getMessage());
			 return new ErrorMessage("LOGIST0379", new String[]{e.getMessage()});
// Fix 15940 fine
     }
     finally {
       if (conn != null)
         ConnectionManager.popConnection();
     }
     //20.10.08 - fine
     if (arrS.isEmpty())
       return null;
     else {
       ErrorMessage e = (ErrorMessage)arrS.get(0);
       return e;
     }
   }

   protected DocumentoAcquisto creoNuovoDocumento(DocumentoAcquisto daIniziale) {
     daIniziale.setIdCliente(getCodiceDestinatario().substring(1));
     daIniziale.setIdCau(getAttributo1());
     daIniziale.completaBO(); // Setta i default clinte/causale
     //15.03.11 - inizio
     // le set seguenti prima venivano effettuate sempre, ora solo in creazione
     daIniziale.setNumeroRifIntestatario(getRiferimentoPartner());
     daIniziale.setDataRifIntestatario(getDataRiferimentoPartner());
     daIniziale.setDataConsegnaRichiesta(getDataConsegnaRichiesta());
     daIniziale.setDataConsegnaConfermata(getDataConsegnaConfermata());
     daIniziale.setIdMagazzino(getCodiceMagLogicoImpegno());
     daIniziale.setIdCommessa(getAttributo3());
     daIniziale.setIdLingua(getCodiceLingua());
     daIniziale.setIdZona(getZonaGenerica());
     if (getPriorita() != null || !getPriorita().equals(""))
       daIniziale.setPriorita( (new Short(getPriorita())).shortValue());
     daIniziale.setIdValuta(getCodiceDivisa());
     daIniziale.setDataInizioPagamento(getData1());
     //15.03.11 - fine
     if (ParametriLogis.SHOW_TRACE)
// Fix 15940 inizio
//       System.out.println("Creato nuovo documento acquisto " +
//                          daIniziale.getNumeroDocumento());
		 System.out.println(ResourceLoader.getString(RES_FILE,"gen0025") + " " + daIniziale.getNumeroDocumento());
// Fix15940 fine
     return daIniziale;
   }
   //10.01.07 - fine

   /**
    * Implementa la creazione di un nuovo documento
    */
   protected DocumentoVendita creoNuovoDocumento(DocumentoVendita dvIniziale) {
     dvIniziale.setIdCliente(getCodiceDestinatario().substring(1));
     dvIniziale.setIdCau(getAttributo1());
     dvIniziale.completaBO(); // Setta i default clinte/causale
     //15.03.11 - inizio
     // le set seguenti prima venivano effettuate sempre, ora solo in creazione
     dvIniziale.setNumeroRifIntestatario(getRiferimentoPartner());
     dvIniziale.setDataRifIntestatario(getDataRiferimentoPartner());
     dvIniziale.setIdClienteFat(getCodicePartnerFatturazione().substring(1));
     dvIniziale.setDataConsegnaRichiesta(getDataConsegnaRichiesta());
     dvIniziale.setDataConsegnaConfermata(getDataConsegnaConfermata());
     dvIniziale.setIdMagazzino(getCodiceMagLogicoImpegno());
     dvIniziale.setIdMagazzinoTra(getCodiceMagLogicoTrasfer());
     dvIniziale.setIdCommessa(getAttributo3());
     dvIniziale.setIdLingua(getCodiceLingua());
     dvIniziale.setIdZona(getZonaGenerica());
     if (getPriorita() !=null || !getPriorita().equals(""))
       dvIniziale.setPriorita((new Short(getPriorita())).shortValue());
     dvIniziale.setIdValuta(getCodiceDivisa());
     dvIniziale.setDataInizioPagamento(getData1());
     dvIniziale.setIdAgente(getCodiceAgente1());
     dvIniziale.setIdAgenteSub(getCodiceAgente2());
     //15.03.11 - fine
     if (ParametriLogis.SHOW_TRACE)
// Fix 15940 inizio
//       System.out.println("Creato nuovo documento vendita " +
//                          dvIniziale.getNumeroDocumento());
			 System.out.println(ResourceLoader.getString(RES_FILE,"gen0026") + " " + dvIniziale.getNumeroDocumento());
// Fix 15940 fine
     return dvIniziale;
   }

  protected static ErrorMessage getErrorFromCode(int rc)
  {
    ErrorMessage message = null;
    if (rc < ErrorCodes.OK)
    {
      if (rc == ErrorCodes.OPTIMISTIC_LOCK_FAILED)
          message = new ErrorMessage("BAS0000035");
      else if (rc == ErrorCodes.DUPLICATED_ROW)
            message = new ErrorMessage("BAS0000034");
      else if (rc == ErrorCodes.CONSTRAINT_VIOLATION)
            message = new ErrorMessage("BAS0000033");
      else if (rc == ErrorCodes.LOCKED_ROW || rc == ErrorCodes.OBJ_TIMEOUT)
            message = new ErrorMessage("BAS0000019");
      else if (rc == ErrorCodes.NO_ROWS_FOUND)
            message = new ErrorMessage("BAS0000045");
     }
     return message;
  }

  //10.07.06 - inizio
  // copiato da DocumentoOrdineTestata.getDescrVettFormattata()
  public String descIndirizzoFormattata(Indirizzo ind){
    String ret = new String();

    if (ind != null){
      String riga1 = ind.getRagioneSociale1();

      String riga2 = new String();
      String indirizzo = ind.getVia();
      if (indirizzo != null && indirizzo.length() > 0) {
        riga2 += indirizzo;
      }

      String riga3 = new String();
      String cap = ind.getCap();
      if (cap != null && cap.length() > 0) {
        riga3 += cap + " - ";
      }
      String localita = ind.getLocalita();
      if (localita != null && localita.length() > 0) {
        riga3 += localita;
      }
      String provincia = ind.getProvincia();
      if (provincia != null && provincia.length() > 0) {
        riga3 += " (" + provincia + ")";
      }

      String nazione = ind.getNazione();
      if (nazione != null && nazione.length() > 0) {
        riga3 += " - " + nazione;
      }

      String riga4 = new String();
      /*String partitaIVA = vettore.getPartitaIVA();
           if (partitaIVA != null && partitaIVA.length() > 0) {
       riga4 += partitaIVA;
           }*/

      ret = riga1;
      if (riga2.length() > 0) {
        ret += "\n" + riga2;
      }
      if (riga3.length() > 0) {
        ret += "\n" + riga3;
      }
      if (riga4.length() > 0) {
        ret += "\n" + riga4;
      }
    }
    return ret;
  }
  //10.07.06 - fine

  //26.07.06 - inizio
  //20.10.08 cambiato il parametro da Object a String
  protected BODataCollector createDataCollector(String str) throws SQLException{
    try {
      BODataCollector bodc = null;
      ClassADCollection classDesc = ClassADCollectionManager.collectionWithName(str);
      String collectorName = classDesc.getBODataCollector();
      if (collectorName != null) {
        bodc = (BODataCollector)Factory.createObject(collectorName);
        if (bodc instanceof DocumentoDataCollector){
          ((DocumentoDataCollector)bodc).setDisabilitaControlliRelazione(true);
        }
        bodc.initialize(classDesc.getClassName(), true);
      }
      else
        bodc = new BODataCollector(classDesc.getClassName(), true);

      return bodc;
    }
    catch (Exception e){
      e.printStackTrace(Trace.excStream);
      throw new ThipException(e.getMessage());
    }
  }
  //26.07.06 - fine

  //20.10.08 - inizio
  protected BODataCollector createDataCollector(Object obj) throws SQLException{
    if (obj instanceof DocumentoVendita)
      return createDataCollector("DocumentoVendita");
    else
      if (obj instanceof DocumentoAcquisto)
        return createDataCollector("DocumentoAcquisto");
      else
        if (obj instanceof DocumentoVenRigaPrm)
          return createDataCollector("DocumentoVenditaRigaPrm");
        else
          if (obj instanceof DocumentoAcqRigaPrm)
            return createDataCollector("DocumentoAcquistoRigaPrm");
    return null;
  }
  //20.10.08 - fine

  //22.01.07 - inizio
  public DocumentoVendita trascodificaDatiPersonalizzati(DocumentoVendita dv) {
    return dv;
  }

  public DocumentoVenRigaPrm trascodificaDatiPersonalizzati(RigaLogisThip rlt, DocumentoVenRigaPrm dvr) {
    return dvr;
  }

  public DocumentoVenRigaLottoPrm trascodificaDatiPersonalizzati(RigaLogisThip rlt, DocumentoVenRigaLottoPrm dvr1otto) {
    return dvr1otto;
  }

  public DocumentoVenRigaSec trascodificaDatiPersonalizzati(RigaLogisThip rlt, DocumentoVenRigaSec dvrSecon) {
    return dvrSecon;
  }

  public DocumentoVenRigaLottoSec trascodificaDatiPersonalizzati(RigaLogisThip rlt, DocumentoVenRigaLottoSec dvr1otto) {
    return dvr1otto;
  }

  public DocumentoAcquisto trascodificaDatiPersonalizzati(DocumentoAcquisto da) {
    return da;
  }

  public DocumentoAcqRigaPrm trascodificaDatiPersonalizzati(RigaLogisThip rlt, DocumentoAcqRigaPrm dar) {
    return dar;
  }

  public DocumentoAcqRigaLottoPrm trascodificaDatiPersonalizzati(RigaLogisThip rlt, DocumentoAcqRigaLottoPrm darLotto) {
    return darLotto;
  }

  public DocumentoAcqRigaSec trascodificaDatiPersonalizzati(RigaLogisThip rlt, DocumentoAcqRigaSec darSecon) {
    return darSecon;
  }

  public DocumentoAcqRigaLottoSec trascodificaDatiPersonalizzati(RigaLogisThip rlt, DocumentoAcqRigaLottoSec darLotto) {
    return darLotto;
  }
  //22.01.07 - fine

  // fix 10955 >
  /**
   * @deprecated
   */
  public BigDecimal calcolaQtaDoc(it.thera.thip.base.articolo.Articolo art, UnitaMisura umRif, RigaLogisThip rl){
    return calcolaQtaDoc(art, umRif, rl, null);
  }

  /**
   * @deprecated   //16.11.09
   */
  public BigDecimal calcolaQtaDoc(it.thera.thip.base.articolo.Articolo art, UnitaMisura umRif, RigaLogisThip rl, ArticoloVersione versione){
    return calcolaQtaDoc(art, umRif, rl, versione, true);
  }

  //20.07.07 - inizio
  // restituisce la qta evasa espressa nell'UM di vendita
  public BigDecimal calcolaQtaDoc(it.thera.thip.base.articolo.Articolo art, UnitaMisura umRif, RigaLogisThip rl, ArticoloVersione versione, boolean ricalcoloQtà){
    //UM doc = UM logistica
    if (umRif.getIdUnitaMisura().equals(rl.getUmBase()))
      return rl.getQta1Evasa();
    //UM doc = UM primaria
    if (umRif.getIdUnitaMisura().equals(rl.getUmBase1()))
      return rl.getQta2Evasa();
    //UM doc = UM secondaria
    if (umRif.getIdUnitaMisura().equals(rl.getUmBase2()))
      return rl.getQta3Evasa();
    //default
    //16.11.09 - inizio
    //BigDecimal qtaDoc = art.convertiUM(rl.getQta1Evasa(), art.getUMLogistica(), umRif, versione);
    BigDecimal qtaDoc = new BigDecimal(0);
    if (ricalcoloQtà)
      qtaDoc = art.convertiUM(rl.getQta1Evasa(), art.getUMLogistica(), umRif, versione);
    else
      // se non è selezionato il flag "ricalcolo qtà", per determinare qtàUMRif devo utilizzare il fattore di conversione effettivo (qtaDoc/qta1Ric)
      if (rl.getQta1Richiesta().compareTo(new BigDecimal(0)) != 0)
        qtaDoc = rl.getQta1Evasa().multiply(rl.getQtaDocumento()).divide(rl.getQta1Richiesta(),6,BigDecimal.ROUND_HALF_UP);
    //16.11.09 - fine
    return qtaDoc;
  }
  //20.07.07 - fine

  // fix 10955 <

  //19.03.08 - inizio
  // reimposta sulla riga lotto le qta originali
  // stato = PROVVISORIO --> qta proposta evasione
  // stato = DEFINITIVO  --> qta attesa evasione
  public void reimpostaQtaOriginali(DocumentoRigaLotto riga, char stato, Hashtable qtaOriginali){
    String qtaStr = "";
    BigDecimal qtaRif = new BigDecimal(0);
    qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {riga.getKey(), UM_RIF}));
    if (qtaStr != null) {
      try {
        qtaRif = new BigDecimal(qtaStr);
      }
      catch (Exception ex) {
        ex.printStackTrace(Trace.excStream);
      }
    }
    BigDecimal qtaPrm = new BigDecimal(0);
    qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {riga.getKey(), UM_PRM}));
    if (qtaStr != null) {
      try {
        qtaPrm = new BigDecimal(qtaStr);
      }
      catch (Exception ex) {
        ex.printStackTrace(Trace.excStream);
      }
    }
    BigDecimal qtaSec = new BigDecimal(0);
    qtaStr = (String) qtaOriginali.get(KeyHelper.buildObjectKey(new String[] {riga.getKey(), UM_SEC}));
    if (qtaStr != null) {
      try {
        qtaSec = new BigDecimal(qtaStr);
      }
      catch (Exception ex) {
        ex.printStackTrace(Trace.excStream);
      }
    }
    switch (stato) {
      case it.thera.thip.base.documenti.StatoAvanzamento.PROVVISORIO:
        riga.getQtaPropostaEvasione().setQuantitaInUMRif(qtaRif);
        riga.getQtaPropostaEvasione().setQuantitaInUMPrm(qtaPrm);
        riga.getQtaPropostaEvasione().setQuantitaInUMSec(qtaSec);
        break;
      case it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO:
        riga.getQtaAttesaEvasione().setQuantitaInUMRif(qtaRif);
        riga.getQtaAttesaEvasione().setQuantitaInUMPrm(qtaPrm);
        riga.getQtaAttesaEvasione().setQuantitaInUMSec(qtaSec);
        break;
    }
  }
  //19.03.08 - fine

  //21.03.08 - inizio
  //Metodo che assegna la stessa istanza della testata di un ordine a tutte le righe
  //dello stesso ordine. Questo serve per evitare che in fase di salvataggio di un docummento
  //che nasce dall'evasione degli ordini e che ha dentro più righe che puntano a righe
  //dello stesso ordine si abbiano problemi di optimistic_lock.
  //sulla testata dell'ordine. La riga docuemnto salva la riga ordine la quale salva la sua testata.
  //Senza questo metodo due righe dello stesso ordine instanzierebbero e salverebbero due istanze della stessa
  //testata con il problema che la save della seconda riga oredine si blocca per otmistic lock sulla save.
  //Il metodo lavora anche sulle righe secondarie in modo che ogni riga secondaria punti alla stessa istanza della riga primaria
  //Esite un metodo simile nella classe DocumentoTestata ma non opera sulle righe secondarie.

  public static DocumentoTestata raggruppaRigheStessoOrdine(DocumentoTestata documento)
  {
    Map testateOrdine = new HashMap();
    Map righePrmOrdine = new HashMap();
    Iterator i = documento.getRighe().iterator();
    while (i.hasNext())
    {
      DocumentoDocRiga rigaDoc = (DocumentoDocRiga) i.next();
      DocumentoOrdineRiga rigaOrdine = rigaDoc.getRigaOrdine();
      if (rigaOrdine != null)
      {
        String testateOrdinekey = rigaOrdine.getTestataKey().trim();
        if (testateOrdine.containsKey(testateOrdinekey))
          rigaOrdine.setFather( (DocumentoOrdineTestata) testateOrdine.get(testateOrdinekey));
        else
          testateOrdine.put(testateOrdinekey, rigaOrdine.getTestata());

        List righeOrdineSec = ((RigaPrimaria)rigaOrdine).getRigheSecondarie();
        Iterator iRSD = ((RigaPrimaria)rigaDoc).getRigheSecondarie().iterator();
        while (iRSD.hasNext())
        {
          DocumentoDocRiga rigaDocSec = (DocumentoDocRiga) iRSD.next();
          String rigaOrdineSecKey = rigaDocSec.getRigaOrdineKey();
          Iterator iRSO = righeOrdineSec.iterator();
          while (iRSO.hasNext())
          {
              DocumentoOrdineRiga rigaOrdSec = (DocumentoOrdineRiga) iRSO.next();
              if (rigaOrdSec.getKey().equals(rigaOrdineSecKey))
              {
                rigaDocSec.setRigaOrdine(rigaOrdSec);
                break;
              }
          }
        }
      }
    }
    return documento;
  }
  //21.03.08 - fine

  //03.02.09 - inizio
  public boolean isRigaTrasmessa(Integer riga, Integer dett, List righeLista){
    Iterator iterRigheLogis = righeLista.iterator();
    while (iterRigheLogis.hasNext()) {
      RigaLista rl = (RigaLista) iterRigheLogis.next();
      if (riga.equals(rl.getNumeroRigaHost()) &&
          dett.equals(rl.getDettaglioRigaHost()))
        return true;
    }
    return false;
  }
  //03.02.09 - fine

  //27.04.09 - inizio
  public Vector recuperaFiltriDocVen(String causale){
    Vector elencoFiltri = new Vector();
    try {
      String where = CfgLogTxDocVenTM.ID_AZIENDA + "='" + getIdAzienda() + "' AND " +
        CfgLogTxDocVenTM.ID_CAU_DOC_VEN + "='" + causale + "' AND " +
        CfgLogTxDocVenTM.ABILITATO + "='Y'";
// Fix 16470 inizio
//      elencoFiltri = CfgLogTxDocVen.retrieveList(where, "", false);
			elencoFiltri.addAll(PersistentObject.readOnlyRetrieveList(CfgLogTxDocVen.class));
			for (Iterator iterFiltri = elencoFiltri.iterator(); iterFiltri.hasNext(); ){
				CfgLogTxDocVen itemFiltri = (CfgLogTxDocVen)iterFiltri.next();
				if (!itemFiltri.getFlagAbilitato() || itemFiltri.getIdAzienda() == null || !itemFiltri.getIdAzienda().equals(getIdAzienda()) || itemFiltri.getIdCausale() == null || !itemFiltri.getIdCausale().equals(causale)) {
					iterFiltri.remove();
				}
			}
// Fix 16470 fine
    }
    catch (Exception ex) {
      ex.printStackTrace(Trace.excStream);
    }
    return elencoFiltri;
  }

  public boolean aggiornaStatoRigheValore(Vector elencoFiltri, String mag, Date data){
    CfgLogTxDocVen cfgDocVen = null;
    for (int i = 0; i < elencoFiltri.size(); i++) {
      CfgLogTxDocVen filtro = (CfgLogTxDocVen) elencoFiltri.get(i);
      // caso in cui esiste il record del mag.
      if (filtro.getIdMagazzino() != null &&
          filtro.getIdMagazzino().equals(mag)) {
        cfgDocVen = filtro;
        break;
      }
      // nel caso non esista il record del mag. vale quello senza mag.
      if (filtro.getIdMagazzino() == null)
        cfgDocVen = filtro;
    }
    if (cfgDocVen == null)
      return false;
    // se la data documento è precedente alla data inizio il documento non passa
    if (cfgDocVen.getDataInizio() != null &&
        data.compareTo(cfgDocVen.getDataInizio()) < 0)
      return false;
    return cfgDocVen.isAggiornaStatoRigheValore();
  }
  //27.04.09 - fine

  //07.01.10 - inizio
  // ritorna true se tutte le righe del doc sono in stato definitivo
  public boolean tutteLeRigheSonoDefinitive(DocumentoTestata doc){
    boolean ret = true;
    for (int i=0; i<doc.getRighe().size(); i++){
      DocumentoDocRiga riga = (DocumentoDocRiga)doc.getRighe().get(i);
      if (riga.getStatoAvanzamento() != it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO)
        return false;
    }
    return ret;
  }
  //07.01.10 - fine
  
  
  //Fix 35962 inizio
  public List getListaRigheDocTrasmessa(DocumentoVendita dv,List righeLista)
  {
	    List riferimentiRigheLogistica = new ArrayList();
	  	Iterator righe = dv.getRighe().listIterator();
	    while (righe.hasNext())
	    {
	        DocumentoVenRigaPrm dvr = (DocumentoVenRigaPrm) righe.next();
	        if (isRigaTrasmessa(dvr.getNumeroRigaDocumento(), dvr.getDettaglioRigaDocumento(), righeLista))
	        {
	            String idAzienda = dv.getIdAzienda();
	            String annoDocumento = dv.getAnnoDocumento();
	            String numeroDocumento = dv.getNumeroDocumento();
	            Integer numeroRigaDocumento = dvr.getNumeroRigaDocumento();
	            Integer dettailRigaDocumento = dvr.getDettaglioRigaDocumento();
	            Object[] keyParts = {idAzienda,
	                                 annoDocumento,
	                                 numeroDocumento,
	                                 numeroRigaDocumento,
	                                 dettailRigaDocumento};
	            String key = KeyHelper.buildObjectKey(keyParts);
	            riferimentiRigheLogistica.add(key);
	        	
	        }
	        
	        if (dvr.getArticolo().getTipoParte() != ArticoloDatiIdent.KIT_GEST)
	        {
	        	Iterator righeSec = dvr.getRigheSecondarie().listIterator();
	        	while (righeSec.hasNext())
	        	{
	        		DocumentoVenRigaSec dvrS = (DocumentoVenRigaSec) righeSec.next();	
	        		if (isRigaTrasmessa(dvrS.getNumeroRigaDocumento(), dvrS.getDettaglioRigaDocumento(), righeLista))//Fix 37183
	        			riferimentiRigheLogistica.add(dvrS.getKey());
	        	}
	        }
	        
	    }
	        
	 return riferimentiRigheLogistica;
  }
  public List getListaRigheDocTrasmessa(DocumentoAcquisto da,List righeLista,boolean reso)
  {
	    List riferimentiRigheLogistica = new ArrayList();
	  	Iterator righe = da.getRighe().listIterator();
	    while (righe.hasNext())
	    {
	        //DocumentoVenRigaPrm dar = (DocumentoVenRigaPrm) righe.next();//Fix 36691
	    	DocumentoAcqRigaPrm dar = (DocumentoAcqRigaPrm) righe.next();//Fix 36691
	        if (isRigaTrasmessa(dar.getNumeroRigaDocumento(), dar.getDettaglioRigaDocumento(), righeLista))
	        {
	            String idAzienda = da.getIdAzienda();
	            String annoDocumento = da.getAnnoDocumento();
	            String numeroDocumento = da.getNumeroDocumento();
	            Integer numeroRigaDocumento = dar.getNumeroRigaDocumento();
	            Integer dettailRigaDocumento = dar.getDettaglioRigaDocumento();
	            Object[] keyParts = {idAzienda,
	                                 annoDocumento,
	                                 numeroDocumento,
	                                 numeroRigaDocumento,
	                                 dettailRigaDocumento};
	            String key = KeyHelper.buildObjectKey(keyParts);
	            riferimentiRigheLogistica.add(key);
	        	
	        }
	        
	        if (!reso)
	        {
	        	Iterator righeSec = dar.getRigheSecondarie().listIterator();
	        	while (righeSec.hasNext())
	        	{
	        		//DocumentoVenRigaSec dvrS = (DocumentoVenRigaSec) righeSec.next();	//Fix 36691
	        		DocumentoAcqRigaSec dvrS = (DocumentoAcqRigaSec) righeSec.next();	//Fix 36691
	        		if (isRigaTrasmessa(dvrS.getNumeroRigaDocumento(), dvrS.getDettaglioRigaDocumento(), righeLista))//Fix 37183
	        			riferimentiRigheLogistica.add(dvrS.getKey());
	        	}
	        }
	        
	    }
	        
	 return riferimentiRigheLogistica;
  }
  //Fix 35962 Fine
  
  //Fix 37183 inizio
  private BigDecimal[] recalcolaPesiVolumDocVen(DocumentoVendita dv) {
	  BigDecimal[] nuovoPeseVolume = new BigDecimal[] {null,null,null};
		  dv.setRicalcolaPesiEVolume(true);
		  Iterator i = dv.getRighe().iterator();
		  while(i.hasNext()) {
			  DocumentoVenRigaPrm rigaPrm = (DocumentoVenRigaPrm) i.next();
			  rigaPrm.calcolaPesiEVolume(false);
			  Iterator it = rigaPrm.getRigheSecondarie().iterator();
			  while(it.hasNext()) {
				  DocumentoVenRigaSec rigaSec = (DocumentoVenRigaSec) it.next();
				  rigaSec.calcolaPesiEVolume();
			  }
		  }
		  
		  dv.calcolaPesiEVolume(false);
		  
		  dv.setRicalcolaPesiEVolume(false);
		  nuovoPeseVolume[0] = dv.getPesoNetto();
		  nuovoPeseVolume[1] = dv.getPesoLordo();
		  nuovoPeseVolume[2] = dv.getVolume();
	  return nuovoPeseVolume;
  }
  
  private BigDecimal[] recalcolaPesiVolumDocAcq(DocumentoAcquisto da) {
	  BigDecimal[] nuovoPeseVolume = new BigDecimal[] {null,null,null};
	   da.setRicalcolaPesiEVolume(true);
		  Iterator i = da.getRighe().iterator();
		  while(i.hasNext()) {
			  DocumentoAcqRigaPrm rigaPrm = (DocumentoAcqRigaPrm) i.next();
			  rigaPrm.calcolaPesiEVolume(false);
			  Iterator it = rigaPrm.getRigheSecondarie().iterator();
			  while(it.hasNext()) {
				  DocumentoAcqRigaSec rigaSec = (DocumentoAcqRigaSec) it.next();
				  rigaSec.calcolaPesiEVolume();
			  }
		  }
		  
		  da.calcolaPesiEVolume(false);
		  
		  da.setRicalcolaPesiEVolume(false);
		  nuovoPeseVolume[0] = da.getPesoNetto();
		  nuovoPeseVolume[1] = da.getPesoLordo();
		  nuovoPeseVolume[2] = da.getVolume();
	  return nuovoPeseVolume;
  }
  //Fix 37183 Fine
  
  
  //21.12.23 - inizio
  public boolean forzaErroriForzabili() {
  	return false;
  }
  //21.12.23 - fine
}

