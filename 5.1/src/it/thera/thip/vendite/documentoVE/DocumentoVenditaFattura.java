package it.thera.thip.vendite.documentoVE;

import java.io.*;
import java.math.*;
import java.sql.*;
import java.util.*;

import com.thera.thermfw.ad.*;
import com.thera.thermfw.base.*;
import com.thera.thermfw.cbs.*;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.security.*;
import com.thera.thermfw.type.*;
import com.thera.thermfw.common.*;

import it.testori.thip.vendite.documentoVE.YDocumentoVendita;
import it.thera.thip.base.articolo.*;
import it.thera.thip.base.azienda.*;
import it.thera.thip.base.catalogo.*;
import it.thera.thip.base.cliente.*;
import it.thera.thip.base.comuniVenAcq.*;
import it.thera.thip.base.controlloFido.*;
import it.thera.thip.base.documenti.*;
import it.thera.thip.base.documentoDgt.*;
import it.thera.thip.base.ecommerce.*;
import it.thera.thip.base.generale.*;
import it.thera.thip.base.partner.*;
import it.thera.thip.cs.*;
import it.thera.thip.magazzino.movimenti.*;
import it.thera.thip.vendite.generaleVE.*;
import it.thera.thip.vendite.ordineVE.*;
import it.thera.thip.magazzino.chiusure.*;
import it.thera.thip.datiTecnici.configuratore.Configurazione;
import it.thera.thip.base.documenti.DocumentoBaseRiga;
import it.thera.thip.servizi.anagraficiBase.*;

/**
 * Classe per il calcolo TotaliFattura di un DocumentoVendita <br>
 * </br><b>Copyright (C) : Infracom Italia s.p.a.</b>
 * 
 * @author MZ
 * @see
 * @see
 */
/*
 * Revision: 
 * Number	 Date 		Owner 	Description 
 * 16471	23/05/2012	MZ		Sviluppo
 * 23171  10/03/2016  RA    Rendi attributi entity e task non static per la gestione di commenti
 * 20948  01/08/2016  OCH   Ganci per gestione scorporo IVA  
 * 25139  13/03/2017  AYM   Gestione IVA dich intento
 * 28855  25/02/2019  LTB   Agguingere la funziona di rigenerazione di registrazioni contabili
 * 29332  20/05/2019  Jihene creare righe FatturaVenditaRigaRpt per le spese ritenuta acconto.
 * 29707  28/07/2019  PM    Correzione 29332 
 * 30467  16/01/2020  LTB    update il cast della classe recuperaPrimoDocumentoVendita
 * 30684  08/02/2020  PM    Gestione bollo fisso per fatture CEE in valuta diversa da euro
 * 38933  19/06/2023  SZ	Le righe annulato non deve essere calcolati nel totale documento.
 */

public class DocumentoVenditaFattura extends YDocumentoVendita {

	public static final String FILE_PROPERTIES = "it.thera.thip.vendite.documentoVE.resources.FatturaVendita";

	protected List listaCommenti = null;

	protected int numDocumentiDaProcessare = 0;
	protected int numDocumentiProcessati = 0;

	protected int numDocumentiConWarning = 0;

	protected static final String linguaDefault = SystemParam
			.getCompanyDefaultLocale();

	protected String linguaCorrente = null;

	//Fix 23171 inizio
	/*
	public static Task task = null;
	public static Entity entity = null;
	*/
	public Task task = null;
	public Entity entity = null;	
	//Fix 23171 fine

	protected int batchJobId = 0;
	protected int reportNr = 0;
	protected int rigaJobId = 0;
	protected int detRigaJobId = 0;

	/**
	 * Attributo iNumeroDocumento.
	 */
	protected String iNumeroDocumento;

	protected Integer CONTATORE_FATTURE_PRESTAMPA = null;

	protected DatiGeneraliFattura condizioniGenerali = null;

	/********************************/
	/*** parametri stampa fatture ***/
	/********************************/

	public static final char TIPO_DOC_PER_STAMPA_TUTTI = '0';
	public static final char TIPO_DOC_PER_STAMPA_FATTURA = '1';
	public static final char TIPO_DOC_PER_STAMPA_NOTA_ACCREDITO = '2';
	public static final char TIPO_DOC_PER_STAMPA_NOTA_ADDEBITO = '3';

	protected char iAzioneStampa = DatiGeneraliFattura.AZIONE_PRESTAMPA;

	protected java.sql.Date iDataStampa = TimeUtils.getCurrentDate();;
	protected char iFinePeriodo = FatturazionePeriodica.NON_PERIODICA;
	protected boolean iStampaInLingua = false;
	protected boolean iRistampaConRicalcolo = true;

	protected boolean iNoRigheDaStampare = true;

	protected boolean iStampaSingola = false;

	public boolean isStampaSingola() {
		return iStampaSingola;
	}

	protected char iTipoDocumento = TIPO_DOC_PER_STAMPA_TUTTI;

	protected String s = "";

	protected PrintWriter output;

	protected FatturaVendita fattura = null;
	protected FatturaVenditaRpt fatturaRpt = null;

	/**
	 * Inica le fatture da elaborare per la stampa.
	 */
	protected char iTipoStampa = GestorePostalizzazione.STAMPA_TUTTI;

	public CondizioniFiltri cdfr;

	public DocumentoVenditaFattura() {
		super();
		output = new PrintWriter(System.out);
		initCommenti();
		setFiltro();
	}

	protected String getNomeVista() {
		return VistaStampaFattureTM.TABLE_NAME;
	}

	public void initCommenti() {
		// inizializazioni necessarie per gestione commenti
		try {
			if (entity == null) {
				entity = Entity.elementWithKey("StpFatture", Entity.NO_LOCK);
			}
		} catch (Exception ex) {
			ex.printStackTrace(Trace.excStream);
		}
		if (entity != null) {
			Iterator i = entity.getTasks().iterator();
			while (i.hasNext()) {
				task = (Task) i.next();
				if (task.getId().equals("RUN"))
					break;
			}
		}
	}

	public void setFiltro() {
		ColonneFiltri cfrA = ColonneFiltri.creaColonnaFiltro(
				"ParametriStampaFatture", "Cliente", "ClienteVendita",
				"RagioneSociale", true);
		ColonneFiltri cfrB = ColonneFiltri.creaColonnaFiltro(
				"ParametriStampaFatture", "ClienteFatturazione",
				"ClienteVendita", "RagioneSociale", true);
		ColonneFiltri cfrC = ColonneFiltri.creaColonnaFiltro(
				"ParametriStampaFatture", "DataDocumento", false);

		// ColonneFiltri cfrD =
		// ColonneFiltri.creaColonnaFiltro("ParametriStampaFatture","NumeroDocumento","thisRicercaNrDocumento","NumeroDocumentoFormattato",
		// true);
		ColonneFiltri cfrD = ColonneFiltri.creaColonnaFiltro(
				"ParametriStampaFatture", "NumeroDocumento",
				"DocumentoVendita", "NumeroDocumentoFormattato", true);
		cfrD.setAdditionalClassAdName("AnnoDocumentoPerFatture");
		cfrD.setUsaDescrizioneClasseRelazionata(false);
		// Fix 05987 GMA Fine
		ColonneFiltri cfrE = ColonneFiltri.creaColonnaFiltro(
				"ParametriStampaFatture", "Causale", "CausaleDocumentoVendita",
				"Descrizione.Descrizione", true);
		ColonneFiltri cfrG = ColonneFiltri.creaColonnaFiltro(
				"ParametriStampaFatture", "DataBolla", false);

		// ColonneFiltri cfrH =
		// ColonneFiltri.creaColonnaFiltro("ParametriStampaFatture","NumeroBolla","thisRicercaNrBolla","NumeroDocumentoFormattato",
		// true);
		ColonneFiltri cfrH = ColonneFiltri.creaColonnaFiltro(
				"ParametriStampaFatture", "NumeroBolla",
				"DocumentoVenditaBolla", "NumeroDocumentoFormattato", true);
		cfrH.setAdditionalClassAdName("AnnoBollaPerFatture");
		cfrH.setUsaDescrizioneClasseRelazionata(false);

		ColonneFiltri cfrI = ColonneFiltri.creaColonnaFiltro(
				"ParametriStampaFatture", "DataFattura", false);

		// ColonneFiltri cfrL =
		// ColonneFiltri.creaColonnaFiltro("ParametriStampaFatture","NumeroFattura","thisRicercaNrFattura","NumeroDocumentoFormattato",
		// true);
		ColonneFiltri cfrL = ColonneFiltri.creaColonnaFiltro(
				"ParametriStampaFatture", "NumeroFattura",
				"DocumentoVenditaFattura", "NumeroDocumentoFormattato", true);
		cfrL.setAdditionalClassAdName("AnnoFatturaPerFatture");
		cfrL.setUsaDescrizioneClasseRelazionata(false);

		ColonneFiltri cfrM = ColonneFiltri.creaColonnaFiltro(
				"ParametriStampaFatture", "Agente", "Agente", true);

		ColonneFiltri cfrM1 = ColonneFiltri.creaColonnaFiltro(
				"ParametriStampaFatture", "Subagente", "Agente", true);

		ColonneFiltri cfrN = ColonneFiltri.creaColonnaFiltro(
				"ParametriStampaFatture", "Divisione", "Divisione", true);

		ColonneFiltri cfrO = ColonneFiltri.creaColonnaFiltro(
				"ParametriStampaFatture", "Commessa", "Commessa", true);

		ColonneFiltri cfrP = ColonneFiltri.creaColonnaFiltro(
				"ParametriStampaFatture", "ModalitaPagamento",
				"ModalitaPagamento", true);

		Vector where = new Vector();
		where.add(cfrA);
		where.add(cfrB);
		where.add(cfrC);
		where.add(cfrD);
		where.add(cfrE);
		where.add(cfrG);
		where.add(cfrH);
		where.add(cfrI);
		where.add(cfrL);

		where.add(cfrM);

		where.add(cfrM1);

		where.add(cfrN);

		where.add(cfrO);

		where.add(cfrP);

		cdfr = new CondizioniFiltri(where);
		cdfr.setNumeroRighe((short) 13);
	}

	public CondizioniFiltri getFiltro() {
		return cdfr;
	}

	/**
	 * riempiParametriDocumento.
	 * 
	 * @throws Exception
	 */
	public void riempiParametriDocumento() {
		impostaFiltroNumeroDataDoc();
	}
	
	//28855 inizio
	public void riempiParametriFattura(FatturaVendita fatVendita) {
		impostaFiltroNumeroDataFattura(fatVendita);
	}
	
	public void impostaFiltroNumeroDataFattura(FatturaVendita fatVendita) {
		
		java.sql.Date data = fatVendita.getDataFat();
		String numeroFat = fatVendita.getIdNumeroFat();
		String annoFat = fatVendita.getIdAnnoFat();
		Vector colonne = getFiltro().getColonneFiltro();
		for (int i = 0; i < colonne.size(); i++) {
			ColonneFiltri colFiltro = (ColonneFiltri) colonne.elementAt(i);
			if (colFiltro.getClassAdName().equals("NumeroFattura")) {
				try {
					int size = colFiltro.getAdditionalClassAd().getType()
							.getSize();
					while (annoFat.length() < size) {
							annoFat += " ";
					}
				} catch (Exception ex) {
					ex.printStackTrace(Trace.excStream);
				}
				String filtro = annoFat + ColonneFiltri.ANNO_SEP + numeroFat;
				CondizioniFiltri.svuotaColonnaFiltro(colFiltro);
				CondizioniFiltri.impostaFiltroFrom(colFiltro, filtro);
				CondizioniFiltri.impostaFiltroTo(colFiltro, filtro);
			} 
			else if (colFiltro.getClassAdName().equals("DataFattura")) {
				DateType dt = new DateType();
				String dataStr = dt.objectToString(data);
				CondizioniFiltri.svuotaColonnaFiltro(colFiltro);
				CondizioniFiltri.impostaFiltroFrom(colFiltro, dataStr);
				CondizioniFiltri.impostaFiltroTo(colFiltro, dataStr);
			}
		}
	}
	
	 public DocumentoVendita recuperaPrimoDocumentoVendita(List righe) {
		 if(righe.isEmpty())
			 return null;
		 
 		//DocumentoVenRigaPrm rigaPrm = (DocumentoVenRigaPrm)righe.get(0); //30467
		 DocumentoVenditaRiga rigaPrm = (DocumentoVenditaRiga)righe.get(0); //30467		 

 		if(rigaPrm != null)
 			return (DocumentoVendita)rigaPrm.getTestata(); 
		return null;
	 }
	//28855 fine

	/**
	 * Metodo aggiunto per gestire la stampa di un singolo documento.
	 * 
	 * @param numeroDoc
	 *            String
	 * @param data
	 *            java.sql.Date
	 */

	// public void impostaFiltroNumeroDataDoc(String numeroDoc, java.sql.Date
	// data){
	public void impostaFiltroNumeroDataDoc() {

		if (this.getStatoStampaFattura() == StatoAttivita.NO
				&& (this.getNumeroFattura() == null || this.getNumeroFattura()
						.equals(""))) {

			java.sql.Date data = this.getDataDocumento();
			String numeroDoc = this.getNumeroDocumento();

			String annoDoc = this.getAnnoDocumento();
			Vector colonne = getFiltro().getColonneFiltro();
			for (int i = 0; i < colonne.size(); i++) {
				ColonneFiltri colFiltro = (ColonneFiltri) colonne.elementAt(i);
				if (colFiltro.getClassAdName().equals("NumeroDocumento")) {
					try {
						int size = colFiltro.getAdditionalClassAd().getType()
								.getSize();
						while (annoDoc.length() < size) {
							annoDoc += " ";
						}
					} catch (Exception ex) {
						ex.printStackTrace(Trace.excStream);
					}
					String filtro = annoDoc + ColonneFiltri.ANNO_SEP
							+ numeroDoc;
					CondizioniFiltri.svuotaColonnaFiltro(colFiltro);
					CondizioniFiltri.impostaFiltroFrom(colFiltro, filtro);
					CondizioniFiltri.impostaFiltroTo(colFiltro, filtro);

				} else if (colFiltro.getClassAdName().equals("DataDocumento")) {
					DateType dt = new DateType();

					String dataStr = dt.objectToString(data);
					CondizioniFiltri.svuotaColonnaFiltro(colFiltro);
					CondizioniFiltri.impostaFiltroFrom(colFiltro, dataStr);
					CondizioniFiltri.impostaFiltroTo(colFiltro, dataStr);
				}
			}
		}

		if (this.getStatoStampaFattura() == StatoAttivita.ESEGUITO
				|| this.getStatoStampaFattura() == StatoAttivita.DA_RIESEGUIRE
				||
				// Per gestire la stampa da singolo
				// documento ma con numero di fattura già assegnato sul
				// documento
				(this.getStatoStampaFattura() == StatoAttivita.NO
						&& this.getNumeroFattura() != null && !this
						.getNumeroFattura().equals(""))) {

			java.sql.Date dataFat = this.getDataFattura();
			String numeroFat = this.getNumeroFattura();
			String annoFat = this.getAnnoFattura();
			Vector colonne = getFiltro().getColonneFiltro();
			for (int i = 0; i < colonne.size(); i++) {
				ColonneFiltri colFiltro = (ColonneFiltri) colonne.elementAt(i);
				if (colFiltro.getClassAdName().equals("NumeroFattura")) {
					/*
					 * colFiltro.setFrom(numeroFat); colFiltro.setTo(numeroFat);
					 */
					try {
						int size = colFiltro.getAdditionalClassAd().getType()
								.getSize();
						while (annoFat.length() < size) {
							annoFat += " ";
						}
					} catch (Exception ex) {
						ex.printStackTrace(Trace.excStream);
					}

					String filtro = annoFat + ColonneFiltri.ANNO_SEP
							+ numeroFat;
					CondizioniFiltri.svuotaColonnaFiltro(colFiltro);
					CondizioniFiltri.impostaFiltroFrom(colFiltro, filtro);
					CondizioniFiltri.impostaFiltroTo(colFiltro, filtro);

				} else if (colFiltro.getClassAdName().equals("DataFattura")) {
					DateType dt = new DateType();

					String dataStr = dt.objectToString(dataFat);
					CondizioniFiltri.svuotaColonnaFiltro(colFiltro);
					CondizioniFiltri.impostaFiltroFrom(colFiltro, dataStr);
					CondizioniFiltri.impostaFiltroTo(colFiltro, dataStr);
				}
			}
		}
	}

	/********************************/
	/*** parametri stampa fatture ***/
	/********************************/

	public char getAzioneStampa() {
		return iAzioneStampa;
	}

	public void setAzioneStampa(char azione) {
		iAzioneStampa = azione;
	}

	public java.sql.Date getDataStampa() {
		return iDataStampa;
	}

	public void setDataStampa(java.sql.Date data) {
		iDataStampa = data;
	}

	public char getFinePeriodo() {
		return iFinePeriodo;
	}

	public void setFinePeriodo(char tipo) {
		iFinePeriodo = tipo;
	}

	public boolean isStampaInLingua() {
		return iStampaInLingua;
	}

	public void setStampaInLingua(boolean flag) {
		iStampaInLingua = flag;
	}

	public boolean isRistampaConRicalcolo() {
		return iRistampaConRicalcolo;
	}

	public void setRistampaConRicalcolo(boolean flag) {
		iRistampaConRicalcolo = flag;
	}

	public char getTipoDocumento() {
		return iTipoDocumento;
	}

	public void setTipoDocumento(char iTipo) {
		iTipoDocumento = iTipo;
	}

	/**
	 * getNumeroDocumento.
	 * 
	 * @return String
	 */
	public String getNumeroDocumento() {
		return iNumeroDocumento;
	}

	/**
	 * setNumeroDocumento.
	 * 
	 * @param docKey
	 *            String
	 */
	public void setNumeroDocumento(String docKey) {
		iNumeroDocumento = docKey;
	}

	/**
	 * 
	 * @return char
	 */
	public char getTipoStampa() {
		return iTipoStampa;
	}

	/**
	 * 
	 * @param tipo
	 *            char
	 */
	public void setTipoStampa(char tipo) {
		iTipoStampa = tipo;
	}

	/***************************************************************************/

	/**
	 * Metodo che deve essere implementato quando si eredita dalla classe
	 * BatchRunnable. Restituisce il nome del classAd della classe erede
	 * concreta.
	 */
	protected String getClassAdCollectionName() {
		return "StampaFatture";
	}

	protected Vector documentiVenditaCollegati = new Vector();

	protected Vector elencoRigheSpesaPerc = new Vector();

	/**
	 * Calcola totali fattura
	 * 
	 * @return true se l'operazione è terminata correttamente.
	 */

	public boolean calcolaFattura() {

		boolean ret = false;

		// Documento non soggetto a fattura
		if (this.getNumeratoreFattura() == null
				|| this.getSerieNumFatture() == null
				|| this.getStatoStampaFattura() == StatoAttivita.NON_RICHIESTO) {
			return false;
		}

		//
		if (this.getCollegatoContabilita() == StatoAttivita.ESEGUITO) {
			// Carico la fattura x documento con Fattura già emessa e collegata
			// a contabilità. 
			// In questo caso i totali fattura sono inerenti a tutti i 
			// documenti eventualmente raggruppati in questa fattura.
			try {
				caricaFattura();
			} catch (Exception e) {
				e.printStackTrace(Trace.excStream);
				return false;
			}
		} else {
			// Calcolo la fattura di questo singolo documento
			ret = calcolaFatturaInternal();
		}

		return ret;
	}

	//28855 inizio
	public boolean rigeneraRegContabili(FatturaVendita fatturaVendita) {
		return calcolaFatturaInternal(fatturaVendita);
	}
	
	protected boolean calcolaFatturaInternal() {
		return calcolaFatturaInternal(null);
	}
	//28855 fine
	
	//protected boolean calcolaFatturaInternal() {  //28855
	protected boolean calcolaFatturaInternal(FatturaVendita fatturaVendita) { //28855		

		// batchJobId = getBatchJob().getBatchJobId();
		batchJobId = new java.util.Random().nextInt();
		reportNr = 0;
		rigaJobId = 0;
		detRigaJobId = 0;

		// IMPOSTAZIONE PARAMETRI PER STAMPA DA DOCUMENTO SINGOLO
		
		if(fatturaVendita == null) //28855
			riempiParametriDocumento();
		else 
			riempiParametriFattura(fatturaVendita); //28855

		java.sql.Date dataStampa = this.getDataDocumento();
		if(fatturaVendita == null) { // 28855
			boolean isDataBolla = false;
			if (this.getBollaStampata() == StatoAttivita.ESEGUITO) {
				if (this.getDataBolla() != null)
					dataStampa = this.getDataBolla();
				isDataBolla = true;
			}
			if (this.getDataFattura() != null)
				dataStampa = this.getDataFattura();
		}
		else 
			dataStampa = fatturaVendita.getDataFat();//28855
		
		this.setDataStampa(dataStampa);
		if(fatturaVendita == null)//28855
			this.setAzioneStampa(DatiGeneraliFattura.AZIONE_PRESTAMPA);
		else 
			this.setAzioneStampa(DatiGeneraliFattura.AZIONE_STAMPA); //28855

		java.sql.Date dataDoc = this.getDataDocumento();

		iStampaSingola = true; 
		setRistampaConRicalcolo(true);

		numDocumentiProcessati = 0;

		long numFatture = 0L;

		boolean fatalError = false;
		int err_save = 0;
		boolean withWarning = false;

		CONTATORE_FATTURE_PRESTAMPA = new Integer(0);

		// INIZIALIZZAZIONE
		documentiVenditaCollegati.clear();
		elencoRigheSpesaPerc.clear();

		char azioneStampa = getAzioneStampa();

		try {

			err_save = 0;
			int count_riga = 0;

			/* set condizioni generali */
			condizioniGenerali = new DatiGeneraliFattura();
			condizioniGenerali.init(this.getIdAzienda(), iAzioneStampa, null);

			// reportNr = availableReport.getReportNr();
			reportNr = new java.util.Random().nextInt();

			//String annoRgrFatCorr = null; //28855
			//String numRgrFatCorr = null;  //28855
			int count = 0;  //28855
			boolean no_save = false;  //28855
			boolean eseguiRicalcoloPrezziSconti = false;

			TotaliImportiFatturaVendita totaliImportiFattura = (TotaliImportiFatturaVendita) Factory
					.createObject(TotaliImportiFatturaVendita.class);

			DocumentoVendita documentoVendita = this; 

			/*
			 * test per la dich. di intento e aggiornamento dell'assoggettamento
			 * IVA in testata SOLO per i documenti da fatturare senza bolla
			 */
			if (fatturaVendita == null && documentoVendita.getDataBolla() == null) { //28855
				if (azioneStampa == DatiGeneraliFattura.AZIONE_PRESTAMPA
						|| azioneStampa == DatiGeneraliFattura.AZIONE_STAMPA
						|| (azioneStampa == DatiGeneraliFattura.AZIONE_RISTAMPA && isRistampaConRicalcolo())) {
					if (checkDichIntento(documentoVendita)) {
						// recupero assoggettamento IVA di esenzione
						// inizio AssoggettamentoIVA assogg
						// =
						// documentoVendita.getCliente().getAssoggettamentoIVA();
						ClienteVendita clienteDichIntento = getClienteFatturazione(documentoVendita);
						AssoggettamentoIVA assogg = clienteDichIntento
								.getAssoggettamentoIVA();
						if (assogg != null)
							documentoVendita.setAssoggettamentoIVA(assogg);
					}
				}
			}

			//28855 inizio
			List righe = new ArrayList(); 
			if(fatturaVendita != null) {
				righe = fatturaVendita.getRigheDocVenLegati();
			}
			//28855 fine			
			else 
				righe = documentoVendita.getRigheOrdinate();

			if(fatturaVendita != null) { //28855
				documentoVendita = recuperaPrimoDocumentoVendita(righe);//28855
				fatturaVendita.setDocumentoVendita(documentoVendita);
			}
			
			boolean conRicalcolo = isRistampaConRicalcolo();
			if (!conRicalcolo) {
				PersDatiVen pdv = PersDatiVen.getCurrentPersDatiVen();
				Provenienza provenienzaCliente = null;
				boolean isFatturaGiaStampata = documentoVendita.getCausale()
						.isFatturaGiaStampataAbilitato();
				if (documentoVendita.getCliente() != null
						&& documentoVendita.getCliente().getAnagrafico() != null)
					provenienzaCliente = documentoVendita.getCliente()
							.getAnagrafico().getProvenienza();
				// ...Se si tratta di un Documento proveniente da
				// “vendita al banco” (vale a dire VenditaBanco
				// ...di PersDatiVendita è diverso da ‘No’,
				// IdProvenienza di AnagraficoBase del Cliente =
				// ...omonimo attributo di PersDatiVendita) e il flag di
				// isFatturaGiaStampata è a true,
				// ...allora imposto a true il booleano conRicalcolo
				if (pdv != null
						//&& pdv.getSistemaVenditaBanco() != PersDatiVen.SIS_VEN_BANCO_ASSENTE // Fix 20948
						&& pdv.isSistemaVenditaBancoAbilitato() // Fix 20948
						&& (provenienzaCliente != null && provenienzaCliente
								.getIdProvenienza().equals(
										pdv.getIdProvenienza()))
						&& isFatturaGiaStampata) {
					conRicalcolo = true;
				}
			}
		
			// INIZIA L'ELABORAZIONE DEL DOCUMENTO
			//annoRgrFatCorr = this.getAnnoDocumento();  //28855
			//numRgrFatCorr = this.getNumeroDocumento();  //28855

			/*** INIZIALIZZO LA FATTURA ***/

			no_save = false;
			count_riga = 0;
			documentiVenditaCollegati.clear();
			elencoRigheSpesaPerc.clear();
			this.listaCommenti = new Vector();

			rigaJobId++;
			detRigaJobId = 0;

			// Imposto la lingua in cui deve avvenire la stampa
			linguaCorrente = this.getIdLingua();
			if (linguaCorrente == null)
				linguaCorrente = linguaDefault;

			try {
				if(fatturaVendita != null) //28855
					fattura = fatturaVendita;//28855
				else
				fattura = initFatturaVendita();

			} catch (Exception e) {
				output.println(UtilStampaFatture.getErrMsg("Calcola fattura ",
						documentoVendita.getKey(),
						" inizializzazione testata fattura", true));
				e.printStackTrace(Trace.excStream);
				no_save = true;
				err_save++;
				fatalError = true;
				return false;
			}

			try {
				fatturaRpt = initFatturaVenditaRpt(fattura);
			} catch (Exception e) {
				// inizializzazione fattura non riuscita
				no_save = true;
				output.println(UtilStampaFatture.getErrMsg("Calcola fattura ",
						documentoVendita.getKey(),
						" inizializzazione testata fatturaRPT", true));
				e.printStackTrace(Trace.excStream); // MG FIX 5034
				err_save++;
				fatalError = true;
				return fatalError;
			}

			/* compilazione della chiave */
			fatturaRpt.setBatchJobId(batchJobId);
			fatturaRpt.setReportNr(reportNr);
			fatturaRpt.setRigaJobId(rigaJobId);

			// imposta condizione di ricalcolo prezzi/sconti
			if (azioneStampa == DatiGeneraliFattura.AZIONE_PRESTAMPA
					|| azioneStampa == DatiGeneraliFattura.AZIONE_STAMPA) {
				if (condizioniGenerali.getPersDatiVen()
						.getRiassegnaPrezziSconti() == PersDatiVen.ALLA_FATTURAZIONE) {

					if (documentoVendita.getTpRicPrzScnFat() != RicalcoloPrzScn.NO)
						eseguiRicalcoloPrezziSconti = true;
				}
			}

			/***
			 * estraggo commenti singola bolla e li parcheggio nella list
			 * ListaCommenti
			 ***/
			try {
				preparaCommenti(documentoVendita);
			} catch (Exception e) {
				e.printStackTrace(Trace.excStream);
			}

			try {
				inserisciInfoInizioDocumento(documentoVendita, fattura,
						fatturaRpt);
			} catch (Exception e) {
				output.println(UtilStampaFatture.getErrMsg("Calcola fattura",
						documentoVendita.getKey(), " verificatasi eccezione ",
						true));
				e.printStackTrace(Trace.excStream);
			}

			documentiVenditaCollegati.add(documentoVendita);

			String numOrdCorr = null;
			String annoOrdCorr = null;

			/***
			 * processo le righe del documento ed alimento fatturaRpt
			 * 
			 ***/
			Iterator iter1 = righe.iterator();
			while (iter1.hasNext()) {
				DocumentoVenRigaPrm riga = (DocumentoVenRigaPrm) iter1.next();
				if (riga.getDatiComuniEstesi().getStato() == DatiComuniEstesi.VALIDO) {
					//Fix 29332 inizio
					if(riga.isRigaRitenutaAcconto()) {
						fatturaRpt.getRigheSpesaRitenuaAcconto().add(riga);
						//Fix 29707 PM >
						FatturaVenditaRigaRpt rSpesaRpt = initFatturaVenditaRigaRpt(null, null,fattura, null);
						rSpesaRpt.setSpesa(riga.getSpesa());
						rSpesaRpt.setImportoRiga(riga.getImportoPercentualeSpesa());
						rSpesaRpt.setImportoRigaNettoScFF(riga.getImportoPercentualeSpesa()); //Fix 29707						
						rSpesaRpt.setTipoRiga(TipoRiga.SPESE_MOV_VALORE);
						rSpesaRpt.setAssoggettamentoIVA(riga.getAssoggettamentoIVA());
						fatturaRpt.getRigheSpesaRptRitenutaAcconto().add(rSpesaRpt);
						//Fix 29707 PM <
					}
					//Fix 29332 fine
					// Riga da fatturare
					if ((isRigaDaFatturare(riga)
							|| (riga.getTipoRiga() == TipoRigaDocumentoVendita.SERVIZIO_ASSIST_MANUT
									|| riga.getTipoRiga() == TipoRigaDocumentoVendita.SERVIZIO_CANONE || riga
									.getTipoRiga() == TipoRigaDocumentoVendita.SERVIZIO_NOLEGGIO)) && 
							!riga.isRigaRitenutaAcconto()) {// Fix 29332

						// RicalcoloPrezziSconti
						if ((isRigaDaFatturare(riga) || (riga.getTipoRiga() == TipoRigaDocumentoVendita.SERVIZIO_ASSIST_MANUT
								|| riga.getTipoRiga() == TipoRigaDocumentoVendita.SERVIZIO_CANONE || riga
								.getTipoRiga() == TipoRigaDocumentoVendita.SERVIZIO_NOLEGGIO))
								&& eseguiRicalcoloPrezziSconti) {
							try {
								if (riga.ricalcoloDatiVenditaPerFatturazione(
										documentoVendita, getDataStampa())) {
									/*--- volutamente non salvo i dati ricalcolati in quanto 
									 * queso è compito del vero processo di Fatturazione 
									boolean oldValue = riga
											.isSalvaRigheSecondarie();
									riga.setSalvaRigheSecondarie(true);
									int errRic = riga.save();
									riga.setSalvaRigheSecondarie(oldValue);
									if (errRic < 0) {
									// " in seguito al ricalcolo prezzi-sconti");
										output.println(UtilStampaFatture
												.getErrMsg(
														"Calcolo fattura ",
														documentoVendita
																.getKey()
																+ " - riga "
																+ riga.getNumeroRigaDocumento()
																		.intValue(),
														" ricalcoloDatiVenditaPerFatturazione (err = "
																+ errRic
																+ ")",
														true));
										err_save++;
									}
									---*/
								}
							} catch (SQLException e) {
								output.println(UtilStampaFatture.getErrMsg(
										"Stampa fatture ",
										documentoVendita.getKey()
												+ " - riga "
												+ riga.getNumeroRigaDocumento()
														.intValue(),
										" ricalcoloDatiVenditaPerFatturazione (eccez)",
										true));
								e.printStackTrace(Trace.excStream);
								err_save++;
							}
						}
						// verifica cambio ordine
						String descrOrd = null;
						String annoOrdRiga = riga.getRAnnoOrd();
						String numOrdRiga = riga.getRNumeroOrd();
						if (annoOrdRiga != null
								&& !annoOrdRiga.trim().equals("")
								&& numOrdRiga != null
								&& !numOrdRiga.trim().equals("")) {
							if (!annoOrdRiga.equals(annoOrdCorr)
									|| !numOrdRiga.equals(numOrdCorr)) {
								// cambio ordine ...
								descrOrd = getStringaRiferimentoOrdine(riga);
							}
						}
						if (descrOrd != null) {
							// setRiferimentoOrdine();
							FatturaVenditaRigaRpt fatturaRigaRptRifOrd = initFatturaVenditaRigaRiferimento(
									descrOrd, fattura);
							// compilazione campi chiave
							fatturaRigaRptRifOrd.setBatchJobId(batchJobId);
							fatturaRigaRptRifOrd.setReportNr(reportNr);
							fatturaRigaRptRifOrd.setRigaJobId(rigaJobId);
							fatturaRigaRptRifOrd.setDetRigaJob(++detRigaJobId);
							fatturaRigaRptRifOrd.setIdRigaFat(detRigaJobId);
							setDatiUlterioriRigaCommento(fatturaRigaRptRifOrd,
									riga);
							fatturaRpt.getRigheRpt().add(fatturaRigaRptRifOrd);
							annoOrdCorr = riga.getRAnnoOrd();
							numOrdCorr = riga.getRNumeroOrd();
						}

						FatturaVenditaRigaRpt fatturaRigaRpt = initFatturaVenditaRigaRpt(
								documentoVendita, riga, fattura, fatturaRpt);
			
						if (riga.getTipoRiga() == TipoRiga.SPESE_MOV_VALORE
								&& riga.isSpesaPercentuale()) {
							// se la riga è di spesa percentuale, va
							// gestita in modo differente rispetto alle
							// altre: non origina una riga fattura rpt
							// corrispondente e la inserisco
							// in un vettore da utilizzare
							// successivamente; controllo che non
							// ci siano doppioni
							Iterator iterSp = fatturaRpt.getRigheSpesaPerc()
									.iterator();
							boolean trovato = false;
							while (iterSp.hasNext()) {
								FatturaVenditaRigaRpt el = (FatturaVenditaRigaRpt) iterSp
										.next();
								if (el.getIdSpesa().equals(riga.getIdSpesa())
										&& el.getImpPrcSpesa()
												.equals(riga
														.getImportoPercentualeSpesa())) {
									trovato = true;
								}
							}
							if (!trovato)
								fatturaRpt.getRigheSpesaPerc().add(
										fatturaRigaRpt);
						} else {
							// compilazione campi chiave
							fatturaRigaRpt.setBatchJobId(batchJobId);
							fatturaRigaRpt.setReportNr(reportNr);
							fatturaRigaRpt.setRigaJobId(rigaJobId);
							fatturaRigaRpt.setDetRigaJob(++detRigaJobId);
							fatturaRigaRpt
									.setTipoRigaReport(DatiGeneraliFattura.TIPO_RIGA_REPORT_ARTICOLO);
							fatturaRigaRpt.setIdRigaFat(detRigaJobId);
							fatturaRpt.getRigheRpt().add(fatturaRigaRpt);
							count_riga++;
							// aggiunta gestione righe secondarie
							if (riga.getSpecializzazioneRiga() == DocumentoVenditaRiga.RIGA_PRIMARIA)
								compilaRigheSecondarie(documentoVendita, riga,
										fattura, fatturaRpt);
						}
					}
				}
			}

			// FINE DOCUMENTO
			inserisciInfoFineDocumento(documentoVendita, fattura, fatturaRpt);

			count++;
			// -----
			/*** chiususura fattura ***/

			fatturaRpt = aggiungiRighePrimaDiTotaleImporti(fattura, fatturaRpt);

			if (totaliImportiFattura.valorizza(condizioniGenerali, fattura,
					fatturaRpt, output)) {

				if(fatturaVendita == null) //28855
					fineFattura(fattura, fatturaRpt);

			} else {
				err_save++;
				fatalError = true;
				return false;
			}

			// -----

		} catch (Throwable t) {
			t.printStackTrace(Trace.excStream);
			fatalError = true;
			try {
				ConnectionManager.rollback();
			} catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
				return false;
			}
		} finally {

			try {
				ConnectionManager.commit();

			} catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
				return false;
			}
		}

		if ((this.getAzioneStampa() == DatiGeneraliFattura.AZIONE_RISTAMPA || this
				.getAzioneStampa() == DatiGeneraliFattura.AZIONE_RISTP_COLLEGATI)
				&& !this.isRistampaConRicalcolo()) {

			return (err_save != 0) ? false : true;
		}

		// si sono verificati errori calcolo fattura
		if (fatalError
				|| err_save > 0
				|| this.numDocumentiDaProcessare > this.numDocumentiProcessati
						+ this.numDocumentiConWarning) {
			return false;
		}

		return true;
	}

	protected void aggiungiRighe(FatturaVendita fattura,
			FatturaVenditaRpt fatturaRpt) {
		// creo righe FatturaVenditaRigaRpt di tipo C per le spese percentuali
		Iterator iterSpesaPerc = fatturaRpt.getRigheSpesaPerc().iterator();
		while (iterSpesaPerc.hasNext()) {
			FatturaVenditaRigaRpt rSpesa = (FatturaVenditaRigaRpt) iterSpesaPerc
					.next();
			FatturaVenditaRigaRpt rSpesaRpt = this
					.initFatturaVenditaRigaSpesaPerc(rSpesa, fattura);
			// compilazione campi chiave
			rSpesaRpt.setBatchJobId(batchJobId);
			rSpesaRpt.setReportNr(reportNr);
			rSpesaRpt.setRigaJobId(rigaJobId);
			rSpesaRpt.setDetRigaJob(++detRigaJobId);
			rSpesaRpt.setIdRigaFat(detRigaJobId);
			fatturaRpt.getRigheRpt().add(rSpesaRpt);
		}
		if (fattura.getTotSpeseInc() != null
				&& fattura.getTotSpeseInc().compareTo(new BigDecimal(0)) != 0) {
			/*
			 * aggiungo una riga di stampa fattura che contiene informazioni
			 * tipiche delle spese di incasso
			 */
			FatturaVenditaRigaRpt fatturaRigaRpt = initFatturaVenditaRigaSpeseIncasso(fattura);
			// compilazione campi chiave
			fatturaRigaRpt.setBatchJobId(batchJobId);
			fatturaRigaRpt.setReportNr(reportNr);
			fatturaRigaRpt.setRigaJobId(rigaJobId);
			fatturaRigaRpt.setDetRigaJob(++detRigaJobId);
			fatturaRigaRpt.setIdRigaFat(detRigaJobId);
			fatturaRpt.getRigheRpt().add(fatturaRigaRpt);
		}
		if (fattura.isApplicatoBolloFisso()) {
			/*
			 * aggiungo una riga di stampa fattura che contiene informazioni
			 * tipiche del bollo fisso
			 */
			FatturaVenditaRigaRpt fatturaRigaRpt = initFatturaVenditaRigaBolloFisso(fattura);
			// compilazione campi chiave
			fatturaRigaRpt.setBatchJobId(batchJobId);
			fatturaRigaRpt.setReportNr(reportNr);
			fatturaRigaRpt.setRigaJobId(rigaJobId);
			fatturaRigaRpt.setDetRigaJob(++detRigaJobId);
			fatturaRigaRpt.setIdRigaFat(detRigaJobId);
			fatturaRpt.getRigheRpt().add(fatturaRigaRpt);
		}
		if (fatturaRpt.getScontoFineFatt() != null
				&& fatturaRpt.getScontoFineFatt().compareTo(new BigDecimal(0)) != 0) {
			/*
			 * aggiungo una riga di stampa fattura che contiene informazioni
			 * tipiche dello sconto fine fattura
			 */
			FatturaVenditaRigaRpt fatturaRigaRpt = initFatturaVenditaRigaScontoFineFattura(fattura);
			// compilazione campi chiave
			fatturaRigaRpt.setBatchJobId(batchJobId);
			fatturaRigaRpt.setReportNr(reportNr);
			fatturaRigaRpt.setRigaJobId(rigaJobId);
			fatturaRigaRpt.setDetRigaJob(++detRigaJobId);
			fatturaRigaRpt.setIdRigaFat(detRigaJobId);
			fatturaRpt.getRigheRpt().add(fatturaRigaRpt);
		}
		//Fix 29332 inizio
		// creo righe FatturaVenditaRigaRpt per le spese ritenuta acconto
				Iterator iterSpesaRitAcc = fatturaRpt.getRigheSpesaRitenuaAcconto().iterator();
				while (iterSpesaRitAcc.hasNext()) {
					DocumentoVenditaRiga rSpesa = (DocumentoVenditaRiga) iterSpesaRitAcc.next();
					FatturaVenditaRigaRpt rSpesaRpt = initFatturaVenditaRigaSpeseRitenutaAcconto(fattura, rSpesa);
					// compilazione campi chiave
					rSpesaRpt.setBatchJobId(batchJobId);
					rSpesaRpt.setReportNr(reportNr);
					rSpesaRpt.setRigaJobId(rigaJobId);
					rSpesaRpt.setDetRigaJob(++detRigaJobId);
					rSpesaRpt.setIdRigaFat(detRigaJobId);
					fatturaRpt.getRigheRpt().add(rSpesaRpt);
				}
		
		//Fix 29332 fine
		DocumentoVendita docven = fattura.getDocumentoVendita();

		if (checkDichIntento(docven)) {

			/*
			 * aggiungo una riga di stampa fattura che contiene informazioni
			 * tipiche della dichirazione di intento
			 */
			String str = getStringaRiferimentoDichIntento();
			if (str != null) {
				FatturaVenditaRigaRpt fatturaRigaRpt = initFatturaVenditaRigaRiferimento(
						str, fattura);
				// compilazione campi chiave
				fatturaRigaRpt.setBatchJobId(batchJobId);
				fatturaRigaRpt.setReportNr(reportNr);
				fatturaRigaRpt.setRigaJobId(rigaJobId);
				fatturaRigaRpt.setDetRigaJob(++detRigaJobId);
				fatturaRigaRpt.setIdRigaFat(detRigaJobId);

				setDatiUlterioriTestataCommento(fatturaRigaRpt, this);

				fatturaRpt.getRigheRpt().add(fatturaRigaRpt);
			}
		}
	}

	protected void caricaFattura() throws SQLException {
		// fattura = (FatturaVendita) Factory
		// .createObject(FatturaVendita.class);
		String[] objectKeys = new String[] { getIdAzienda(), getAnnoFattura(),
				getNumeroFattura() };
		// fattura.setKey(KeyHelper.buildObjectKey(objectKeys));
		fattura = FatturaVendita.elementWithKey(
				KeyHelper.buildObjectKey(objectKeys), PersistentObject.NO_LOCK);
	}

	protected void fineFattura(FatturaVendita fattura,
			FatturaVenditaRpt fatturaRpt) {
		// completo la fattura con righe spese percentuali, spese incasso, bollo
		// fisso, ...
		aggiungiRighe(fattura, fatturaRpt);
		inserisciCommenti(fatturaRpt);
	}

	/**
	 * assegna numero di fattura reperendolo dal numeratore
	 */
	protected int assegnaNumeroFattura(FatturaVendita fattura,
			FatturaVenditaRpt fatturaRpt) {
		int esito = UtilStampaFatture.SAVE_OK;
		// DocumentoVendita docven = fattura.getDocumentoVendita();
		fattura.getNumeratoreHandler().setIdAzienda(fattura.getIdAzienda());
		fattura.getNumeratoreHandler().setIdNumeratore(fattura.getRNumFat());
		fattura.getNumeratoreHandler().setIdSerie(fattura.getRSerieNumFat());
		fattura.getNumeratoreHandler().setDataDocumento(this.getDataStampa());

		/*** se sul documento c'è già un numero di fattura assegno questo ***/
		if ((this.getNumeroFattura() != null && !this.getNumeroFattura()
				.equals(""))) {
			String annoFat = null;
			String serieFat = this.getIdSerieNumFatture();
			String sottoserieFat = this.getIdSottoSerieFattura();
			String numeroFat = this.getNumeroFattura();
			java.sql.Date dataFat = this.getDataFattura();
			String numeroFatFmt = null;

			fattura.setDataFat(dataFat);
			fattura.setRSerieNumFat(serieFat);
			fattura.setIdNumeroFat(numeroFat);
			fattura.getNumeratoreHandler().setDataDocumento(dataFat);
			fattura.getNumeratoreHandler().setUsaAnnoSolare(true);

			/*** di conseguenza ... ***/
			try {
				annoFat = fattura.getNumeratoreHandler().getAnno();
				fattura.setIdAnnoFat(annoFat);
				String str_num = numeroFat.substring(4);
				numeroFatFmt = fattura.getNumeratoreHandler()
						.buildIdProgressivoFormattato(annoFat, serieFat,
								sottoserieFat, str_num);
				fattura.setNumeroFatFmt(numeroFatFmt);
			} catch (Exception e) {
				output.println(UtilStampaFatture.getErrMsg(
						"Stampa fatture - assegnaNumeroFattura ",
						this.getKey(),
						"errore in assegnamento anno(numero predefinito)", true));
				e.printStackTrace(Trace.excStream);
				return UtilStampaFatture.SAVE_ERROR;
			}

			try {
				if (!StampaFatture.controllaProgressivo(fattura,
						this.documentiVenditaCollegati)) {
					output.println("\n*** ERRORE IN STAMPA FATTURA DOCUMENTO "
							+ this.getNumeroDocumentoFormattato() + " ***");
					output.println("*** PROGRESSIVO FATTURA "
							+ fattura.getIdNumeroFat() + " GIA IN USO ***");
					output.println("*** FATTURA NON STAMPATA");
					return UtilStampaFatture.SAVE_ERROR;
				}
			} catch (SQLException e) {
				output.println(UtilStampaFatture.getErrMsg(
						"Stampa fatture - assegnaNumeroFattura ",
						this.getKey(), "errore in assegnamento numero fattura",
						true));
				e.printStackTrace(Trace.excStream);
				return UtilStampaFatture.SAVE_ERROR;
			}
			// MG FIX 4485

		} else {
			// MG FIX 2187
			try {
				// MG FIX 9442 inizio
				fattura.getNumeratoreHandler().setUsaDataDocumento(true);
				// MG FIX 9442 fine
				fattura.getNumeratoreHandler().setUsaAnnoSolare(true);// MG FIX
																		// 10455

				String progressivo = fattura.getNumeratoreHandler()
						.getIdProgressivo();
				// System.out.println("progressivo fattura: " + progressivo);
				fattura.setIdNumeroFat(progressivo);
				// fatturaRpt.setIdNumeroFat(progressivo);
			} catch (Exception e) {
				output.println(UtilStampaFatture.getErrMsg(
						"Stampa fatture - assegnaNumeroFattura ",
						this.getKey(), "errore in assegnamento numero fattura",
						true));
				e.printStackTrace(Trace.excStream);
				return UtilStampaFatture.SAVE_ERROR;
			}
			try {
				String anno = fattura.getNumeratoreHandler().getAnno();
				// System.out.println("anno fattura: " + anno);
				fattura.setIdAnnoFat(anno);
				// fatturaRpt.setIdAnnoFat(anno);
			} catch (Exception e) {
				output.println(UtilStampaFatture.getErrMsg(
						"Stampa fatture - assegnaNumeroFattura ",
						this.getKey(), "errore in assegnamento anno fattura",
						true));
				e.printStackTrace(Trace.excStream);
				return UtilStampaFatture.SAVE_ERROR;
			}
			try {
				String progrFmt = fattura.getNumeratoreHandler()
						.getIdProgressivoFormattato();
				// System.out.println("progressivo FMT fattura: " + progrFmt);
				fattura.setNumeroFatFmt(progrFmt);
				// fatturaRpt.setNumeroFatFmt(progrFmt);
			} catch (Exception ex) {
				output.println(UtilStampaFatture.getErrMsg(
						"Stampa fatture - assegnaNumeroFattura ",
						this.getKey(),
						"errore in assegnamento progressivo formattato fattura",
						true));
				ex.printStackTrace(Trace.excStream);
				return UtilStampaFatture.SAVE_ERROR;
			}
		}
		if (esito == UtilStampaFatture.SAVE_OK) {
			fatturaRpt.aggiornaChiaveFattura(fattura.getKey());
			fatturaRpt.setNumeroFatFmt(fattura.getNumeroFatFmt());
			// setta numero fattura formattato in righe di statistica
			Iterator iter = fattura.getListaStatistiche().iterator();
			while (iter.hasNext()) {
				FatturaVenditaSTAT stat = (FatturaVenditaSTAT) iter.next();
				stat.setNumeroFatFmt(fattura.getNumeroFatFmt());
			}
		}
		return esito;
	}

	/**
	 * compila testata fattura di vendita
	 */
	protected FatturaVendita initFatturaVendita() throws Exception {
		FatturaVendita fattura = (FatturaVendita) Factory
				.createObject(FatturaVendita.class);
		/*
		 * inizializzazione dati di fattura : la data di fattura è per default
		 * la data di stampa
		 */
		fattura.setDataFat(getDataStampa());
		fattura.init(this, getAzioneStampa(), linguaCorrente);

		/* compilazione numeri identificativi della fattura */
		if (getAzioneStampa() == DatiGeneraliFattura.AZIONE_PRESTAMPA) {
			CONTATORE_FATTURE_PRESTAMPA = new Integer(
					CONTATORE_FATTURE_PRESTAMPA.intValue() + 1);
			fattura.setIdNumeroFat(CONTATORE_FATTURE_PRESTAMPA.toString());
			fattura.setIdAnnoFat(String.valueOf(TimeUtils.getValues(fattura
					.getDataFat())[0]));
			fattura.setNumeroFatFmt(this.getNumeratoreHandler()
					.buildIdProgressivoFormattato(fattura.getIdAnnoFat(),
							fattura.getRSerieNumFat(), null,
							fattura.getIdNumeroFat()));
		}
		if (getAzioneStampa() == DatiGeneraliFattura.AZIONE_RISTAMPA
				|| getAzioneStampa() == DatiGeneraliFattura.AZIONE_RISTP_COLLEGATI) {
			// numero fattura già assegnato
			fattura.setDataFat(this.getDataFattura());
			fattura.setIdAnnoFat(this.getAnnoFattura());
			fattura.setIdNumeroFat(this.getNumeroFattura());
			fattura.setNumeroFatFmt(this.getNumeroFatturaFormattato());
		}
		return fattura;
	}

	/**
	 * compila testata report fattura di vendita
	 */
	protected FatturaVenditaRpt initFatturaVenditaRpt(FatturaVendita fatt)
			throws Exception {
		FatturaVenditaRpt fattura = (FatturaVenditaRpt) Factory
				.createObject(FatturaVenditaRpt.class);
		DocumentoVendita docven = fatt.getDocumentoVendita();
		fattura.init(fatt, getAzioneStampa(), linguaCorrente);

		// per riconoscere in fase di stampa Cristal report se PRESTAMPA oppure
		// no
		fattura.setStato((getAzioneStampa() == DatiGeneraliFattura.AZIONE_PRESTAMPA) ? DatiComuniEstesi.SOSPESO
				: DatiComuniEstesi.VALIDO);

		fattura.setTimestampCrz(new Timestamp(System.currentTimeMillis()));
		fattura.setTimestampAgg(new Timestamp(System.currentTimeMillis()));
		fattura.setIdUtenteCrz(getUtenteInternal());
		fattura.setIdUtenteAgg(getUtenteInternal());

		if (PersDatiVen.getCurrentPersDatiVen().getAblDenAbtFat()) {
			fillClienteDestinatario(docven, fattura);
			fillClienteDestinatarioRA(docven, fattura); // ...FIX 5518 LP
		}

		// compilazione dati di spedizione / consegna
		// dati modalità di spedizione
		if (docven.getIdModSpedizione() != null) {
			fattura.setRModSpedizione(docven.getIdModSpedizione());
			String desModSpediz = docven.getModalitaSpedizione()
					.getDescrizione().getDescrizione();
			// ...Se è stata indicata una descrizione apposita DIVERSA da quella
			// standard utilizzo quella
			// ...anziché quella in lingua. FIX02447 - DZ
			if (desModSpediz != null
					&& docven.getDescrModalitaSpedizione() != null
					&& !desModSpediz
							.equals(docven.getDescrModalitaSpedizione()))
				desModSpediz = docven.getDescrModalitaSpedizione();
			else
				desModSpediz = ImportiDocumentoOrdineUtil
						.getDescrizioneInLingua(docven.getModalitaSpedizione()
								.getDescrizione(), linguaCorrente);
			fattura.setDesModSpediz(desModSpediz);
		}

		// dati modalità di consegna
		if (docven.getIdModConsegna() != null) {
			fattura.setRModConsegna(docven.getIdModConsegna());
			String desModConsegna = docven.getModalitaConsegna()
					.getDescrizione().getDescrizione();
			// ...Se è stata indicata una descrizione apposita DIVERSA da quella
			// standard utilizzo quella
			// ...anziché quella in lingua.
			if (desModConsegna != null
					&& docven.getDescrModalitaConsegna() != null
					&& !desModConsegna
							.equals(docven.getDescrModalitaConsegna()))
				desModConsegna = docven.getDescrModalitaConsegna();
			else
				desModConsegna = ImportiDocumentoOrdineUtil
						.getDescrizioneInLingua(docven.getModalitaConsegna()
								.getDescrizione(), linguaCorrente);
			fattura.setDesModConsegna(desModConsegna);
		}

		// dati aspetto esteriore
		if (docven.getIdAspettoEsn() != null) {
			fattura.setRAspettoEsn(docven.getIdAspettoEsn());
			String desAspettoEsn = docven.getAspettoEsteriore()
					.getDescrizione().getDescrizione();
			// ...Se è stata indicata una descrizione apposita DIVERSA da quella
			// standard utilizzo quella
			// ...anziché quella in lingua.
			if (desAspettoEsn != null
					&& docven.getDescrAspettoEsteriore() != null
					&& !desAspettoEsn.equals(docven.getDescrAspettoEsteriore()))
				desAspettoEsn = docven.getDescrAspettoEsteriore();
			else
				desAspettoEsn = ImportiDocumentoOrdineUtil
						.getDescrizioneInLingua(docven.getAspettoEsteriore()
								.getDescrizione(), linguaCorrente);
			fattura.setDesAspettoEsn(desAspettoEsn);
		}

		// dati causale di trasporto
		if (docven.getIdCauTrasporto() != null) {
			fattura.setRCauTrasporto(docven.getIdCauTrasporto());
			String desCauTrasporto = docven.getCausaleTrasporto()
					.getDescrizione().getDescrizione();
			// ...Se è stata indicata una descrizione apposita DIVERSA da quella
			// standard utilizzo quella
			// ...anziché quella in lingua.
			if (desCauTrasporto != null
					&& docven.getDescrCausaleTrasporto() != null
					&& !desCauTrasporto.equals(docven
							.getDescrCausaleTrasporto()))
				desCauTrasporto = docven.getDescrCausaleTrasporto();
			else
				desCauTrasporto = ImportiDocumentoOrdineUtil
						.getDescrizioneInLingua(docven.getCausaleTrasporto()
								.getDescrizione(), linguaCorrente);
			fattura.setDesCauTrasporto(desCauTrasporto);
		}

		fattura.setVettore1(docven.getIdVettore1());
		if (docven.getDescrVettore1() != null
				&& !docven.getDescrVettore1().equals(""))
			fattura.setDesVettore1(docven.getDescrVettore1());
		else if (docven.getVettore1() != null)
			fattura.setDesVettore1(docven.getVettore1().getRagioneSociale());

		fattura.setVettore2(docven.getIdVettore2());
		if (docven.getDescrVettore2() != null
				&& !docven.getDescrVettore2().equals(""))
			fattura.setDesVettore2(docven.getDescrVettore2());
		else if (docven.getVettore2() != null)
			fattura.setDesVettore2(docven.getVettore2().getRagioneSociale());

		fattura.setVettore3(docven.getIdVettore3());
		if (docven.getDescrVettore3() != null
				&& !docven.getDescrVettore3().equals(""))
			fattura.setDesVettore3(docven.getDescrVettore3());
		else if (docven.getVettore3() != null)
			fattura.setDesVettore3(docven.getVettore3().getRagioneSociale());

		fattura.setDataInizioTrp(docven.getDataInizioTrasporto());
		fattura.setOraInizioTrp(docven.getOraInizioTrasporto());

		fattura.setNumeroColli(docven.getNumeroColli());
		fattura.setPesoLordo(docven.getPesoLordo());
		fattura.setPesoNetto(docven.getPesoNetto());

		return fattura;
	}

	public FatturaVenditaRigaRpt initFatturaVenditaRigaBolloFisso(
			FatturaVendita fattura) {
		FatturaVenditaRigaRpt rigaRpt = initFatturaVenditaRigaRpt(null, null,
				fattura, null);
		rigaRpt.setTipoRigaReport(DatiGeneraliFattura.TIPO_RIGA_REPORT_VARIE);
		rigaRpt.setDesArticoloFormattata(ResourceLoader.getString(
				FILE_PROPERTIES, "BolloFisso", linguaCorrente));
		/*rigaRpt.setImportoRiga(condizioniGenerali.getPersDatiVen()
				.getImportoBolloFisso());*/ //Fix 30684 PM
		
		rigaRpt.setImportoRiga(UtilStampaFatture.convertiInValutaEstero(fattura.getIdValuta(), fattura.getFattoreCambi(), condizioniGenerali.getPersDatiVen().getImportoBolloFisso(), fattura.getDataFat(), 2)); //Fix 30684 PM
		rigaRpt.setAssoggettamentoIVAKey(condizioniGenerali.getPersDatiVen()
				.getAssoggettamentoIVAKey());
		rigaRpt.setAliqivaStr(ImportiDocumentoOrdineUtil
				.formattaAliquotaIvaRiga(condizioniGenerali.getPersDatiVen()
						.getAssoggettamentoIVA()));

		rigaRpt.setNumDecUnitari(new BigDecimal(0));
		return rigaRpt;
	}

	public FatturaVenditaRigaRpt initFatturaVenditaRigaScontoFineFattura(
			FatturaVendita fattura) {
		FatturaVenditaRigaRpt rigaRpt = initFatturaVenditaRigaRpt(null, null,
				fattura, null);
		rigaRpt.setTipoRigaReport(DatiGeneraliFattura.TIPO_RIGA_REPORT_VARIE);
		String str = ResourceLoader.getString(FILE_PROPERTIES,
				"ScontoFineFattura", linguaCorrente)
				+ " "
				+ fattura.getDocumentoVendita().getPrcScontoFineFattura() + "%";
		// MG FIX 3804
		rigaRpt.setDesArticoloFormattata(str);
		// MG FIX 3804
		rigaRpt.setImportoRiga(fattura.getTotScoFf());
		rigaRpt.setNumDecUnitari(new BigDecimal(0));
		return rigaRpt;
	}
	//Fix 29332 inizio
	public FatturaVenditaRigaRpt initFatturaVenditaRigaSpeseRitenutaAcconto(FatturaVendita fattura, DocumentoVenditaRiga rSpesa) {
		FatturaVenditaRigaRpt rigaRpt = initFatturaVenditaRigaRpt(null, null,fattura, null);
		rigaRpt.setTipoRigaReport(DatiGeneraliFattura.TIPO_RIGA_REPORT_VARIE);
		String str = ResourceLoader.getString(FILE_PROPERTIES, "RitenutaAcconto",
				new String[]{String.valueOf(rSpesa.getAliquotaRitenAcconto())}, linguaCorrente) + " ";
		rigaRpt.setDesArticoloFormattata(str);
		rigaRpt.setImportoRiga(rSpesa.getImportoPercentualeSpesa());
		rigaRpt.setImportoRitenAcconto(rSpesa.getImportoPercentualeSpesa());
		rigaRpt.setImponibileRitenAccconto(rSpesa.getImponibileRitenAccconto());
		rigaRpt.setAliquotaRitenAcconto(rSpesa.getAliquotaRitenAcconto());
		Spesa spesa = rSpesa.getSpesa();
		if (spesa!=null)
		{
			rigaRpt.setCauPagamRitenAcconto(spesa.getCauPagamRitenAcconto());
			if (spesa.getCauPagRitenutaAcconto() != null)
				rigaRpt.setDescrCauPagamRitAcc(spesa.getCauPagRitenutaAcconto().getDescrizione());
			rigaRpt.setTipoRitenAcconto(spesa.getTipoRitenAcconto());
		}
		return rigaRpt;
	}
	//Fix 29332 fine

	public FatturaVenditaRigaRpt initFatturaVenditaRigaSpeseIncasso(
			FatturaVendita fattura) {
		FatturaVenditaRigaRpt rigaRpt = initFatturaVenditaRigaRpt(null, null,
				fattura, null);
		rigaRpt.setTipoRigaReport(DatiGeneraliFattura.TIPO_RIGA_REPORT_VARIE);
		String str = ResourceLoader.getString(FILE_PROPERTIES, "SpeseIncasso",
				linguaCorrente) + " ";
		// MG FIX 3804
		rigaRpt.setDesArticoloFormattata(str);
		// MG FIX 3804
		rigaRpt.setImportoRiga(fattura.getTotSpeseInc());

		// determino aliquota IVA spese incasso
		AssoggettamentoIVA aliquota = null;
		if (fattura.getModPagamento() != null) {
			List rate = fattura.getModPagamento().getRate();
			if (rate != null && rate.size() > 0) {
				ModalitaPagamentoRata rata = (ModalitaPagamentoRata) rate
						.get(0);
				if (rata != null && rata.getTipoPagamento() != null) {
					aliquota = rata.getTipoPagamento().getAssoggettamentoIVA();
				}
			}
		}
		
		AssoggettamentoIVA assogg = DatiAssoggIva.determinaAssoggIva(aliquota,
				fattura.getDocumentoVendita().getAssoggettamentoIVA());
		if (assogg != null) {
			rigaRpt.setIdAssogIVA(assogg.getIdAssoggettamentoIVA());
			// MG FIX 3170
			// rigaRpt.setAliqivaStr(ImportiDocumentoOrdineUtil.formattaAliquotaIva(assogg));
			rigaRpt.setAliqivaStr(ImportiDocumentoOrdineUtil
					.formattaAliquotaIvaRiga(assogg));
			// MG FIX 3170
		}
		return rigaRpt;
	}

	public FatturaVenditaRigaRpt initFatturaVenditaRigaRiferimento(String rif,
			FatturaVendita fattura) {
		FatturaVenditaRigaRpt rigaRpt = initFatturaVenditaRigaRpt(null, null,
				fattura, null);
		rigaRpt.setTipoRigaReport(DatiGeneraliFattura.TIPO_RIGA_REPORT_COMMENTO);
		rigaRpt.setNumDecUnitari(new BigDecimal(0));
		rigaRpt.setCommenti(rif);
		return rigaRpt;
	}

	public FatturaVenditaRigaRpt initFatturaVenditaRigaSpesaPerc(
			FatturaVenditaRigaRpt rigaSpesa, FatturaVendita fattura) {
		FatturaVenditaRigaRpt rigaRpt = initFatturaVenditaRigaRpt(null, null,
				fattura, null);
		rigaRpt.setTipoRigaReport(DatiGeneraliFattura.TIPO_RIGA_REPORT_VARIE);
		rigaRpt.setNumDecUnitari(new BigDecimal(0));
		String descrSpesa = "";
		// MG FIX 3804
		if (rigaSpesa.getSpesa() != null) {
			descrSpesa = getDescrizioneInLinguaCorrente(rigaSpesa.getSpesa()
					.getDescrizione());
			if (descrSpesa.length() > FatturaVenditaRigaRpt.MAX_LEN_DESC_ARTICOLO - 10)
				descrSpesa = descrSpesa.substring(0,
						FatturaVenditaRigaRpt.MAX_LEN_DESC_ARTICOLO - 10);
			descrSpesa = descrSpesa + " " + rigaSpesa.getImpPrcSpesa() + "% :";
		}
		rigaRpt.setDesArticoloFormattata(descrSpesa);
		// MG FIX 3804
		rigaRpt.setImportoRiga(rigaSpesa.getImportoRiga());
		rigaRpt.setAssoggettamentoIVA(rigaSpesa.getAssoggettamentoIVA());
		rigaRpt.setAliqivaStr(rigaSpesa.getAliqivaStr());
		return rigaRpt;
	}

	/**
	 * per impostare l'utente corrente su FatturaVenditaRigaRpt nel caso in cui
	 * DocumentoVenRigaPrm sia null.
	 * 
	 * @return String
	 */
	/*
	 * Revision: Number Date Owner Description 00958 03/11/2003 DZ Initial
	 * revision.
	 */
	protected String getUtenteInternal() {
		String utente;
		User ut = Security.getCurrentUser();
		if (ut != null)
			utente = ut.getId();
		else
			utente = ConnectionManager.getCurrentUser();
		if (utente == null)
			utente = "Unknown";
		return utente;
	}

	protected void compilaRigheSecondarie(DocumentoVendita docven,
			DocumentoVenditaRiga riga, FatturaVendita fattura,
			FatturaVenditaRpt fatturaRpt) {
		List righeSecondarie = ((DocumentoVenRigaPrm) riga)
				.getRigheSecondarie();
		if (righeSecondarie != null) {
			Iterator iterator = righeSecondarie.iterator();
			while (iterator.hasNext()) {
				DocumentoVenditaRiga rigaSec = (DocumentoVenditaRiga) iterator
						.next();
			if (rigaSec.getDatiComuniEstesi().getStato() == DatiComuniEstesi.VALIDO) { //Fix 38933
				FatturaVenditaRigaRpt fatturaRigaSecRpt = initFatturaVenditaRigaRpt(
						docven, rigaSec, fattura, fatturaRpt);
				if (fatturaRigaSecRpt != null) {
					// compilazione campi chiave
					fatturaRigaSecRpt.setBatchJobId(batchJobId);
					fatturaRigaSecRpt.setReportNr(reportNr);
					fatturaRigaSecRpt.setRigaJobId(rigaJobId);
					fatturaRigaSecRpt.setDetRigaJob(++detRigaJobId);
					fatturaRigaSecRpt
							.setTipoRigaReport(DatiGeneraliFattura.TIPO_RIGA_REPORT_ARTICOLO);
					fatturaRigaSecRpt.setIdRigaFat(detRigaJobId);
					fatturaRpt.getRigheRpt().add(fatturaRigaSecRpt);
					}
				}//Fix 38933
			}
		}
	}

	// private FatturaVenditaRigaRpt
	// initFatturaVenditaRigaRpt(DocumentoVendita this, DocumentoVenRigaPrm
	// riga, FatturaVendita fattura, FatturaVenditaRpt fatturaRpt) {
	protected FatturaVenditaRigaRpt initFatturaVenditaRigaRpt(
			DocumentoVendita docven, DocumentoVenditaRiga riga,
			FatturaVendita fattura, FatturaVenditaRpt fatturaRpt) {
		FatturaVenditaRigaRpt rigaRpt = (FatturaVenditaRigaRpt) Factory
				.createObject(FatturaVenditaRigaRpt.class);

		rigaRpt.setDocVenRiga(riga);

		// per riconoscere in fase di stampa Cristal report se PRESTAMPA oppure
		// no
		rigaRpt.setStato((getAzioneStampa() == DatiGeneraliFattura.AZIONE_PRESTAMPA) ? DatiComuniEstesi.SOSPESO
				: DatiComuniEstesi.VALIDO);

		rigaRpt.setRAzienda(fattura.getIdAzienda());
		rigaRpt.setIdAnnoFat(fattura.getIdAnnoFat());
		rigaRpt.setIdNumeroFat(fattura.getIdNumeroFat());

		if (riga == null) {
			/*
			 * riga particolare che non corrisponde ad alcuna riga articolo del
			 * documento di vendita ma che viene aggiunta per registrare le
			 * informazioni del bollo fisso o riferimenti ordine o bolla
			 */
			rigaRpt.setStatoAvanzamento(it.thera.thip.base.documenti.StatoAvanzamento.DEFINITIVO);
			rigaRpt.setIdDetRigaFat(new Integer(0));
			rigaRpt.setSplRiga('1');
			rigaRpt.setDesArticolo("");

			rigaRpt.setTimestampCrz(new Timestamp(System.currentTimeMillis()));
			rigaRpt.setTimestampAgg(new Timestamp(System.currentTimeMillis()));
			rigaRpt.setIdUtenteCrz(getUtenteInternal());
			rigaRpt.setIdUtenteAgg(getUtenteInternal());

			return rigaRpt;
		}

		Articolo articolo = riga.getArticolo();
		if (articolo != null) {
			// prima si recupera l' eventuale descrizione estesa
			// cliente/articolo
			ArticoloCliente artCli = (ArticoloCliente) riga
					.recuperaArticoloIntestatario();
			if (artCli != null) {
				rigaRpt.setArticoloCli(artCli.getArticoloPerCliente());
				// ...Il campo DescrizioneArtClifor va riempito SOLO con la
				// descrizione reperita
				// ...da ArticolCliente. Se su ArticolCliente la descrizione è
				// null, devo salvare null!
				if (artCli.getDescrizioneEst().getDescrizione() != null) {
					rigaRpt.setDescrizioneArtCli(ImportiDocumentoOrdineUtil
							.getDescrizioneInLingua(
									((DescrizioneInLingua) artCli
											.getDescrizioneEst()),
									linguaCorrente));
				}
			}

			String descrizioneArticoloEstesa = getDesExtArt(artCli, articolo);
			/*
			 * // in seconda battuta si recupera la descrizione estesa in lingua
			 * dell'articolo if (descrizioneArticoloEstesa == null)
			 * descrizioneArticoloEstesa =
			 * ImportiDocumentoOrdineUtil.getDescrizioneInLingua
			 * ((DescrizioneEstesa)articolo.getDescrizioneArticoloNLS(),
			 * linguaCorrente); // infine la descrizione semplice in lingua
			 * dell'articolo if (descrizioneArticoloEstesa == null)
			 * descrizioneArticoloEstesa =
			 * ImportiDocumentoOrdineUtil.getDescrizioneInLingua
			 * ((DescrizioneInLingua)articolo.getDescrizioneArticoloNLS(),
			 * linguaCorrente); if (descrizioneArticoloEstesa != null)
			 */

			String descrEstInLinguaArt = ImportiDocumentoOrdineUtil
					.getDesExtSoloInLingua(
							artCli != null ? artCli.getDescrizioneEst() : null,
							articolo.getDescrizioneArticoloNLS(),
							(linguaCorrente == null ? linguaDefault
									: linguaCorrente));
			String descInitRiga = (artCli != null
					&& artCli.getDescrizioneEst() != null && !isEm(artCli
					.getDescrizioneEst().getDescrizioneEstesa())) ? artCli
					.getDescrizioneEst().getDescrizioneEstesa() : articolo
					.getDescrizioneArticoloNLS().getDescrizioneEstesa();
			if (PersDatiVen.getCurrentPersDatiVen()
					.getGestioneDescExtArticolo() == PersDatiVen.NON_GESTITA)
				rigaRpt.setDesExtArt(descrEstInLinguaArt);
			else if (!isStampaInLingua()) {
				if (!isEm(riga.getDescrizioneExtArticolo()))
					rigaRpt.setDesExtArt(riga.getDescrizioneExtArticolo());
				else
					rigaRpt.setDesExtArt(descInitRiga);
			} else if (isEm(riga.getDescrizioneExtArticolo())
					|| riga.getDescrizioneExtArticolo().equals(descInitRiga))
				rigaRpt.setDesExtArt(descrEstInLinguaArt);
			else
				rigaRpt.setDesExtArt(riga.getDescrizioneExtArticolo());
		}

		rigaRpt.setTimestampCrz(riga.getDatiComuniEstesi().getTimestampCrz());
		rigaRpt.setTimestampAgg(riga.getDatiComuniEstesi().getTimestampAgg());
		rigaRpt.setIdUtenteCrz(riga.getDatiComuniEstesi().getIdUtenteCrz());
		rigaRpt.setIdUtenteAgg(riga.getDatiComuniEstesi().getIdUtenteAgg());

		rigaRpt.setStatoAvanzamento(riga.getStatoAvanzamento());
		rigaRpt.setIdDetRigaFat(riga.getDettaglioRigaDocumento());
		rigaRpt.setSplRiga(riga.getSpecializzazioneRiga());

		rigaRpt.setRAnnoDoc(riga.getAnnoDocumento());
		rigaRpt.setRNumDoc(riga.getNumeroDocumento());
		rigaRpt.setRRigaClg(riga.getIdRigaCollegata());
		rigaRpt.setRDetRigaClg(riga.getIdDettaglioRigaCollegata());

		rigaRpt.setRAnnoOrd(riga.getRAnnoOrd());
		rigaRpt.setRNumOrd(riga.getRNumeroOrd());
		rigaRpt.setRRigaOrd(riga.getRRigaOrd());
		rigaRpt.setRDetRigaOrd(riga.getRDetRigaOrd());

		rigaRpt.setRRigaDoc(riga.getNumeroRigaDocumento());
		rigaRpt.setRDetRigaDoc(riga.getDettaglioRigaDocumento());

		rigaRpt.setTipoBolla(docven.getTipoBolla());
		rigaRpt.setRNumBolle(fatturaRpt.getRNumBolle());
		rigaRpt.setRSerieNumBolle(fatturaRpt.getRSerieNumBolle());

		rigaRpt.setDataBolla(docven.getDataBolla());
		rigaRpt.setNumeroBolla(docven.getNumeroBolla());

		rigaRpt.setIdCauRigaDoc(riga.getIdCauRig());
		if (riga.getCausaleRiga() != null)
			rigaRpt.setDesCauRigDoc(getDescrizioneInLinguaCorrente(riga
					.getCausaleRiga().getDescrizione()));

		rigaRpt.setTipoRiga(riga.getTipoRiga());
		rigaRpt.setRMagazzino(riga.getIdMagazzino());
		rigaRpt.setRArticolo(riga.getIdArticolo());
		rigaRpt.setRVersioneSal(riga.getIdVersioneSal());
		rigaRpt.setRConfigurazione(riga.getIdConfigurazione());

		if (riga.getConfigurazione() != null) {
			rigaRpt.setCodConfig(riga.getConfigurazione().getIdEsternoConfig());
			rigaRpt.setDesConfigurazione(getDescrizioneInLinguaCorrente(riga
					.getConfigurazione().getDescrizione()));
			rigaRpt.setSintesiCfg(getSintesiConfigurazione(
					riga.getConfigurazione(),
					riga.getSpecializzazioneRiga() == DocumentoBaseRiga.RIGA_PRIMARIA));
		}
		rigaRpt.setROperazione(riga.getIdOperazione());
		rigaRpt.setRVersioneRcs(riga.getIdVersioneRcs());
		if (riga.getArticolo() != null) {
			if (riga.getTipoRiga() != TipoRiga.SPESE_MOV_VALORE) {
				String des1 = riga.getArticolo().getDescrizioneArticoloNLS()
						.getDescrizione();
				String des2 = riga.getDescrizioneArticolo();
				if ((des1 != null && des2 != null && des1.equals(des2))
						|| des2 == null || des2.equals(""))
					rigaRpt.setDesArticolo(getDescrizioneInLinguaCorrente(riga
							.getArticolo().getDescrizioneArticoloNLS()));
				else {
					rigaRpt.setDesArticolo(des2);
				}
			} else {
				// righe di tipo spesa: recupero la descrizione in lingua della
				// spesa
				Spesa spesa = riga.getSpesa();
				if (spesa != null) {
					String desSpesa = spesa.getDescrizione().getDescrizione(); // descr.default
																				// in
																				// lingua
																				// aziendale
					String desSpesaSuRiga = riga.getDescrizioneArticolo(); // descriz.ev.riscritta
																			// sulla
																			// riga
					if ((desSpesa != null && desSpesaSuRiga != null && desSpesa
							.equals(desSpesaSuRiga)) // la descriz.di riga non è
														// stata modificata
							|| desSpesaSuRiga == null
							|| desSpesaSuRiga.equals("")) {
						rigaRpt.setDesArticolo(getDescrizioneInLinguaCorrente(spesa
								.getDescrizione()));
					} else
						rigaRpt.setDesArticolo(desSpesaSuRiga);
				}
			}
		}

		if (riga.getDettaglioRigaDocumento().equals(new Integer(0))) {
			CatalEsternoRicerca catalEsn = ((DocumentoVenRigaPrm) riga)
					.getCatalogoEsterno();
			if (catalEsn != null) {
				rigaRpt.setArticoloForCatalEsn(catalEsn.getIdArticoloFor());
				rigaRpt.setFornitoreCatalEsn(catalEsn.getRFornitore());
			}
		}

		rigaRpt.setNota(riga.getNota());
		try {
			rigaRpt.caricaCommentiRiga(entity, task, linguaCorrente);
		} catch (Exception e) {
			e.printStackTrace(Trace.excStream);
		}

		rigaRpt.setIdSpesa(riga.getIdSpesa());
		rigaRpt.setImpPrcSpesa(riga.getImportoPercentualeSpesa());
		rigaRpt.setSpesaPrc(riga.isSpesaPercentuale());

		// U.M. e qta di vendita e primaria di magazzino
		// Imposto le Qta su rigaRpt in quato il  calcolo fattura può
		// essere richiesto anche quando il documento non è
		// ancora collegato a magazzino.
		String umRif = riga.getIdUMRif();
		String umPrm = riga.getIdUMPrm();
		rigaRpt.setRUm(umRif);
		if (umRif != null) {
			rigaRpt.setUmFsc(riga.getUMRif().getUnitaMisuraFsc());
			// rigaRpt.setQtaUm(riga.getQtaSpedita().getQuantitaInUMRif());
			rigaRpt.setQtaUm(riga.getServizioQta()
					.getQuantitaInUMRif());
			rigaRpt.setQtaLetUm(ImportiDocumentoOrdineUtil
					.trasformaQuantitaInLettere(rigaRpt.getQtaUm()));

			if (umPrm != null) {
				rigaRpt.setRUmPrm(umPrm);
				rigaRpt.setUmFscPrm(riga.getUMPrm().getUnitaMisuraFsc());
				// rigaRpt.setQtaUmPrm(riga.getQtaSpedita().getQuantitaInUMPrm());
				rigaRpt.setQtaUmPrm(riga.getServizioQta()
						.getQuantitaInUMPrm());
				rigaRpt.setQtaLetUmPrm(ImportiDocumentoOrdineUtil
						.trasformaQuantitaInLettere(rigaRpt.getQtaUmPrm()));
			}
		}

		// unita di misura qta secondaria di magazzino
		rigaRpt.setRUmSec(riga.getIdUMSec());
		if (riga.getUMSec() != null) {
			rigaRpt.setUmFscSec(riga.getUMSec().getUnitaMisuraFsc());
			// rigaRpt.setQtaUmSec(riga.getQtaSpedita().getQuantitaInUMSec());
			rigaRpt.setQtaUmSec(riga.getServizioQta()
					.getQuantitaInUMSec());
			rigaRpt.setQtaLetUmSec(ImportiDocumentoOrdineUtil
					.trasformaQuantitaInLettere(rigaRpt.getQtaUmSec()));
		}

		rigaRpt.setCoeffImpiegoCmp(riga.getCoefficienteImpiego());
		rigaRpt.setBloQtaCmp(riga.isBloccoRicalcoloQtaComp());
		rigaRpt.setDataConsegRcs(riga.getDataConsegnaRichiesta());
		rigaRpt.setDataConsegCfm(riga.getDataConsegnaConfermata());
		rigaRpt.setRListino(riga.getIdListino());

		if (riga.getListinoPrezzi() != null)
			rigaRpt.setDesListino(getDescrizioneInLinguaCorrente(riga
					.getListinoPrezzi().getDescrizione()));

		// dalla valuta ... della testata
		if (fattura.getValuta() != null)
			rigaRpt.setNumDecUnitari(fattura.getValuta().getNumDecUnitari());
		else
			rigaRpt.setNumDecUnitari(new BigDecimal(0));

		rigaRpt.setUmPrezzo(riga.getRiferimentoUMPrezzo());
		rigaRpt.setTpPrezzo(riga.getTipoPrezzo());
		//Fix 25139 inizio
		if(riga.getIdAssoggIVADichiarIntento()!=null)
			rigaRpt.setIdAssogIVA(riga.getIdAssoggIVADichiarIntento());
		else
		//Fix 25139 fine
		   rigaRpt.setIdAssogIVA(riga.getIdAssogIVA());

		BigDecimal prezzo = recuperaPrezzo(riga);
		prezzo = prezzo.setScale(rigaRpt.getNumDecUnitari().intValue(),
				BigDecimal.ROUND_HALF_UP);
		rigaRpt.setPrezzo(prezzo);

		BigDecimal prezzoExtra = (riga.getPrezzoExtra() != null) ? riga
				.getPrezzoExtra() : new BigDecimal(0);
		prezzoExtra = prezzoExtra.setScale(rigaRpt.getNumDecUnitari()
				.intValue(), BigDecimal.ROUND_HALF_UP);
		rigaRpt.setPrezzoExtra(prezzoExtra);

		rigaRpt.setAliqivaStr("");

		rigaRpt.setRResponVen(riga.getIdResponVendite());
		if (riga.getResponsabileVendite() != null)
			rigaRpt.setDesResponVen(getDescrizioneInLinguaCorrente(riga
					.getResponsabileVendite().getDescrizione()));

		rigaRpt.setScontoArt1(riga.getScontoArticolo1());
		rigaRpt.setScontoArt2(riga.getScontoArticolo2());
		rigaRpt.setMaggiorazione(riga.getMaggiorazione());
		rigaRpt.setIdSconto(riga.getIdSconto());
		if (riga.getSconto() != null)
			rigaRpt.setDesSconto(getDescrizioneInLinguaCorrente(riga
					.getSconto().getDescrizione()));
		rigaRpt.setScontoCli(riga.getPrcScontoIntestatario());
		rigaRpt.setPrcScontoModalita(riga.getPrcScontoModalita());
		rigaRpt.setIdScontoMod(riga.getIdScontoMod());
		if (riga.getScontoModalita() != null)
			rigaRpt.setDesScontoMod(getDescrizioneInLinguaCorrente(riga
					.getScontoModalita().getDescrizione()));
		rigaRpt.setScontiStr("");

		rigaRpt.setImportoRiga(new BigDecimal(0));
		rigaRpt.setRAgente(riga.getIdAgente());
		if (riga.getAgente() != null)
			rigaRpt.setDesAgente(riga.getAgente().getDescrizione()
					.getDescrizione());
		rigaRpt.setPvg1Agente(riga.getProvvigione1Agente());
		rigaRpt.setPvg2Agente(riga.getProvvigione2Agente());

		rigaRpt.setRAgenteSub(riga.getIdAgenteSub());
		if (riga.getSubagente() != null)
			rigaRpt.setDesAgenteSub(riga.getSubagente().getDescrizione()
					.getDescrizione());
		rigaRpt.setPvg1SubAgente(riga.getProvvigione1Subagente());
		rigaRpt.setPvg2SubAgente(riga.getProvvigione2Subagente());

		rigaRpt.setRCommessa(riga.getIdCommessa());
		if (riga.getCommessa() != null)
			rigaRpt.setDesCommessa(getDescrizioneInLinguaCorrente(riga
					.getCommessa().getDescrizione()));
		rigaRpt.setRCentroCosto(riga.getIdCentroCosto());
		if (riga.getCentroCosto() != null)
			rigaRpt.setDesCentroCosto(getDescrizioneInLinguaCorrente(riga
					.getCentroCosto().getDescrizione()));

		rigaRpt.setRFornitore(riga.getIdFornitore());
		if (riga.getFornitore() != null)
			rigaRpt.setDesFornitore(riga.getFornitore().getRagioneSociale());

		rigaRpt.setPriorita(riga.getPriorita());

		// tutto da fare ...
		rigaRpt.setRCauContabile(fatturaRpt.getIdCausaleContabile());
		rigaRpt.setRCatConCli(fatturaRpt.getIdCatContCli());
		try {
			rigaRpt.settaCatContiVen(riga, docven);
		} catch (Exception e) {
			e.printStackTrace(Trace.excStream);
		}

		rigaRpt.setTipoSchema(TipoSchema.RICAVI);
		rigaRpt.setRVoceCon("");

		rigaRpt.setFlagRisUte1(riga.getFlagRiservatoUtente1());
		rigaRpt.setFlagRisUte2(riga.getFlagRiservatoUtente2());
		rigaRpt.setFlagRisUte3(riga.getFlagRiservatoUtente3());
		rigaRpt.setFlagRisUte4(riga.getFlagRiservatoUtente4());
		rigaRpt.setFlagRisUte5(riga.getFlagRiservatoUtente5());
		rigaRpt.setStringaRisUte1(riga.getAlfanumRiservatoUtente1());
		rigaRpt.setStringaRisUte2(riga.getAlfanumRiservatoUtente2());
		rigaRpt.setNumRisUte1(riga.getNumeroRiservatoUtente1());
		rigaRpt.setNumRisUte2(riga.getNumeroRiservatoUtente2());

		rigaRpt.setValoreTotaleRiga(riga.getValoreTotaleRiga());

		if (riga.getTipoRiga() == TipoRigaDocumentoVendita.SERVIZIO_NOLEGGIO
				|| riga.getTipoRiga() == TipoRigaDocumentoVendita.SERVIZIO_ASSIST_MANUT
				|| riga.getTipoRiga() == TipoRigaDocumentoVendita.SERVIZIO_CANONE) { // Fix
																						// 7663
			AnagraficaBeni bene = riga.getBene();
			if (bene != null) {
				boolean ok = true;
				if (riga instanceof DocumentoVenRigaSec) {
					DocumentoVenRigaPrm rigaPrm = ((DocumentoVenRigaSec) riga)
							.getRigaPrimaria();
					if (rigaPrm.getIdBene() != null
							&& rigaPrm.getIdBene().equals(riga.getIdBene()))
						ok = false;
				}
				if (ok) {
					rigaRpt.setIdBene(riga.getIdBene());
					rigaRpt.setDescrBene(ImportiDocumentoOrdineUtil
							.getDescrizioneInLingua(((DescrizioneInLingua) bene
									.getDescrizione()), linguaCorrente));
					rigaRpt.setMarcaBene(bene.getDIMarca());
					rigaRpt.setModelloBene(bene.getDIModello());
					rigaRpt.setTelaioBene(bene.getDITelaio());
					rigaRpt.setTargaBene(bene.getDITarga());
					rigaRpt.setCostruttoreBene(bene.getDSCostruttore());
					rigaRpt.setAnnoFabbrBene(bene.getDSAnnoFabbric() != null ? bene
							.getDSAnnoFabbric().toString() : null);
				}
			}
			if (riga.getNumeroOrdineServizio() != null) {
				rigaRpt.setAttivazContratto(riga.isConfFineAttivContratto());
				rigaRpt.setAnnoOrdineServizio(riga.getAnnoOrdineServizio());
				rigaRpt.setNumeroOrdineServizio(riga.getNumeroOrdineServizio());
			}

			rigaRpt.setDataInizioAttivContratto(riga
					.getDataInizioAttivContratto());
			rigaRpt.setDataFineAttivContratto(riga.getDataFineAttivContratto());
			rigaRpt.setOraInizioAttivContratto(riga
					.getOraInizioAttivContratto());
			rigaRpt.setOraFineAttivContratto(riga.getOraFineAttivContratto());

			rigaRpt.setAnnoContrattoServizio(riga.getAnnoContrattoServizio());
			rigaRpt.setNumeroContrattoServizio(riga
					.getNumeroContrattoServizio());
			rigaRpt.setRigaContrattoServizio(riga.getNumRigaContrattoServizio());

		}

		rigaRpt.setRifCommessaCli(riga.getRifCommessaCli());
		rigaRpt.setNumBollaTrasf(riga.getNumBollaTrasf());
		rigaRpt.setDataBollaTrasf(riga.getDataBollaTrasf());

		rigaRpt.setNonFatturare(riga.isNonFatturare()); // MG FIX 7885

		if (riga.getTipoRiga() == TipoRigaDocumentoVendita.SPESE_MOV_VALORE) {
			rigaRpt.setAnnoOrdineServizio(riga.getAnnoOrdineServizio());
			rigaRpt.setNumeroOrdineServizio(riga.getNumeroOrdineServizio());
			rigaRpt.setAnnoContrattoServizio(riga.getAnnoContrattoServizio());
			rigaRpt.setNumeroContrattoServizio(riga
					.getNumeroContrattoServizio());
			rigaRpt.setRigaContrattoServizio(riga.getNumRigaContrattoServizio());
		}

		return rigaRpt;
	}

	protected String getStringaRiferimentoBolla(DocumentoVendita docven) {
		DateType type = new DateType();

		String data = (docven.getDataBolla() == null) ? "" : type
				.objectToString(docven.getDataBolla());
		String numero = (docven.getNumeroBolla() == null) ? "" : docven
				.getNumeroBolla();
		return ResourceLoader.getString(FILE_PROPERTIES, "RifBolla",
				linguaCorrente)
				+ " "
				+ docven.getNumeroBolla()
				+ " "
				+ ResourceLoader.getString(FILE_PROPERTIES, "RifData",
						linguaCorrente) + " " + data;
	}

	protected String getStringaRiferimentoOrdine(DocumentoVenditaRiga riga) {
		String strOrdine = null;
		String keyItems[] = { riga.getIdAzienda(), riga.getRAnnoOrd(),
				riga.getRNumeroOrd() };
		String key = KeyHelper.buildObjectKey(keyItems);
		try {
			OrdineVendita ordine = (OrdineVendita) OrdineVendita
					.elementWithKey(
							it.thera.thip.vendite.ordineVE.OrdineVendita.class,
							key, PersistentObject.NO_LOCK);
			if (ordine != null) {
				String vsRifDataOrdine;
				if (ordine.getDataDocumento() == null)
					vsRifDataOrdine = "";
				else {
					DateType type = new DateType();
					vsRifDataOrdine = type.objectToString(ordine
							.getDataDocumento());
				}
				strOrdine = ResourceLoader.getString(FILE_PROPERTIES, "Ordine",
						linguaCorrente)
						+ " "
						+ riga.getRAnnoOrd()
						+ " "
						+ riga.getRNumeroOrd()
						+ " "
						+ ResourceLoader.getString(FILE_PROPERTIES, "RifData",
								linguaCorrente) + " " + vsRifDataOrdine;

				String strOrdineIntestatario = null;
				String vsRif = ordine.getNumeroOrdineIntestatario();
				if (vsRif != null && !vsRif.equals("")) { // Fix 6130 PM
					String vsRifData;
					if (ordine.getDataOrdineIntestatario() == null)
						vsRifData = "";
					else {
						DateType type = new DateType();
						vsRifData = type.objectToString(ordine
								.getDataOrdineIntestatario());
					}
					strOrdineIntestatario = ResourceLoader.getString(
							FILE_PROPERTIES, "RifOrdine", linguaCorrente)
							+ " "
							+ vsRif
							+ " "
							+ ResourceLoader.getString(FILE_PROPERTIES,
									"RifData", linguaCorrente)
							+ " "
							+ vsRifData;
				}

				if (strOrdineIntestatario != null)
					strOrdine += "\n" + strOrdineIntestatario;
			}
		} catch (Exception e) {
			e.printStackTrace(Trace.excStream);
		}
		return strOrdine;
	}

	public String getStringaRiferimentoDichIntento() {
		Cliente cli = getClienteFatturazione().getCliente();
		DateType type = new DateType();
		String dataDichIntento = type.objectToString(cli
				.getDataDichiarazioneIntento());
		String numDichIntento = cli.getNumDichiarazioneIntento();
		return ResourceLoader.getString(FILE_PROPERTIES, "DichIntento",
				linguaCorrente)
				+ " "
				+ numDichIntento
				+ " "
				+ ResourceLoader.getString(FILE_PROPERTIES, "RifData",
						linguaCorrente) + " " + dataDichIntento;
	}

	public String getDescrizioneInLinguaCorrente(
			DescrizioneInLingua descrInLingua) {
		return ImportiDocumentoOrdineUtil.getDescrizioneInLingua(descrInLingua,
				linguaCorrente);
	}

	/**
	 * Riempie i campi del destinario cercando sul documento se sia settata una
	 * SequenzaIndirizzo, un destinatario di vendita o se i dati siano stati
	 * inseriti manualmente dell'utente
	 * 
	 * @param thisTes
	 *            DocumentoVendita
	 * @param testataBolla
	 *            FatturaVenditaRpt
	 */

	protected void fillClienteDestinatario(DocumentoVendita thisTes,
			FatturaVenditaRpt fatVenRpt) {
		if (thisTes.getIdDenAbt() != null) {
			ClienteVendita destinatario = thisTes.getClienteDestinatario();
			fatVenRpt.setRClenteDen(destinatario.getIdCliente());
			fatVenRpt.fillDatiAnagraficiDestinatario(destinatario
					.getDatiAnagraficiValidiAl(thisTes.getDataDocumento()),
					linguaCorrente);
		} else if (thisTes.getIdSequenzaInd() != null) {
			IndirizzoPrimRose indirizzo = thisTes.getIndirizzo()
					.getDatiIndirizzo();
			fatVenRpt.setRagioneSocDen(thisTes.getIndirizzo()
					.getRagioneSocialeCompleta()); // MG FIX 4809
			fatVenRpt.setIndirizzoDen(indirizzo.getIndirizzo());
			fatVenRpt.setLocalitaDen(indirizzo.getLocalita());
			fatVenRpt.setCapDen(indirizzo.getCAP());
			fatVenRpt.setRNazioneDen(indirizzo.getIdNazione());
			if (indirizzo.getNazione() != null)
				fatVenRpt
						.setDesNazioneDen(getDescrizioneInLinguaCorrente(indirizzo
								.getNazione().getDescrizione())); // ...LP 01509
			fatVenRpt.setRProvinciaDen(indirizzo.getIdProvincia());
		} else {
			// ... i dati sono stati inseriti manualmente
			if (thisTes.getRagioneSocaleDest() != null
					&& !thisTes.getRagioneSocaleDest().equals("")) {
				fatVenRpt.setRagioneSocDen(thisTes.getRagioneSocaleDest());
				if (thisTes.getIndirizzoDestinatario() != null) {
					fatVenRpt.setIndirizzoDen(thisTes
							.getIndirizzoDestinatario());
				}
				fatVenRpt.setLocalitaDen(thisTes.getLocalitaDestinatario());
				fatVenRpt.setCapDen(thisTes.getCAPDestinatario());
				fatVenRpt.setRNazioneDen(thisTes.getIdNazioneDen());
				if (thisTes.getNazione() != null) {
					fatVenRpt
							.setDesNazioneDen(getDescrizioneInLinguaCorrente(thisTes
									.getNazione().getDescrizione()));
				}
				fatVenRpt.setRProvinciaDen(thisTes.getIdProvinciaDen());
			}
		}
	}

	/**
	 * Riempie i campi del destinario cercando sul documento se sia settata una
	 * SequenzaIndirizzo, un destinatario di vendita o se i dati siano stati
	 * inseriti manualmente dell'utente
	 * 
	 * @param thisTes
	 *            DocumentoVendita
	 * @param testataBolla
	 *            FatturaVenditaRpt
	 */
	protected void fillClienteDestinatarioRA(DocumentoVendita thisTes,
			FatturaVenditaRpt fatVenRpt) {
		// ...Controllo che la provenienza sia da "Vendita al banco"
		AnagraficoDiBasePrimrose anaBas = (AnagraficoDiBasePrimrose) thisTes
				.getCliente().getAnagrafico();
		Provenienza prov = anaBas.getProvenienza();
		PersDatiVen pdv = PersDatiVen.getCurrentPersDatiVen();
		Provenienza pdvProv = pdv.getProvenienza();
		if (pdv.getSistemaVenditaBanco() == PersDatiVen.SIS_VEN_BANCO_RA
				&& prov != null && pdvProv != null
				&& prov.getIdProvenienza().equals(pdvProv.getIdProvenienza())) {
			// ...Se il cliente di fatturazione è uguale al cliente intestatario
			// del documento
			// ...allora imposto l'indirizzo del cliente
			if (Utils
					.compare(thisTes.getIdCliente(), thisTes.getIdClienteFat()) == 0) {
				ClienteVendita cliente = thisTes.getCliente();
				fatVenRpt.setRClenteDen(cliente.getIdCliente());
				fatVenRpt.fillDatiAnagraficiDestinatario(cliente
						.getDatiAnagraficiValidiAl(thisTes.getDataDocumento()),
						linguaCorrente);
			}
			// ...Se è stato indicato un indirizzo specificio allora uso quello
			// per la destinazione
			else if (!anaBas.getIndirizzi().isEmpty()) {
				Indirizzo ind = (Indirizzo) anaBas.getIndirizzi().get(0);
				IndirizzoPrimRose indirizzo = ind.getDatiIndirizzo();
				fatVenRpt.setRagioneSocDen(ind.getRagioneSocialeCompleta());
				fatVenRpt.setIndirizzoDen(indirizzo.getIndirizzo());
				fatVenRpt.setLocalitaDen(indirizzo.getLocalita());
				fatVenRpt.setCapDen(indirizzo.getCAP());
				fatVenRpt.setRNazioneDen(indirizzo.getIdNazione());
				if (indirizzo.getNazione() != null)
					fatVenRpt
							.setDesNazioneDen(getDescrizioneInLinguaCorrente(indirizzo
									.getNazione().getDescrizione()));
				fatVenRpt.setRProvinciaDen(indirizzo.getIdProvincia());
			}
		}
	}

	protected boolean isRigaDaFatturare(DocumentoVenditaRiga riga) {
		return !riga.isNonFatturare();
	}

	protected void inserisciCommenti(FatturaVenditaRpt fatturaRpt) {
		if (condizioniGenerali.getPersDatiVen().getTipoPosizioneCommentiFat() != PersDatiVen.TESTA_PIEDE_FATTURA)
			return;
		String retCommentiTesta = "";
		String retCommentiPiede = "";

		HashMap commOnlyOneTesta = new HashMap();
		HashMap commOnlyOnePiede = new HashMap();

		if (listaCommenti != null && !listaCommenti.isEmpty()) {
			Iterator iter = listaCommenti.iterator();
			while (iter.hasNext()) {
				CommentUseLink commentUseObj = (CommentUseLink) iter.next();
				if (commentUseObj.getCommentUse() != null) {
					char pos = commentUseObj.getCommentUse()
							.getCommentPosition();
					if (pos == CommentiDocumento.NORMALE
							|| pos == CommentiDocumento.PRIMA_DETTAGLI) {
						if (!commOnlyOneTesta.containsKey(commentUseObj
								.getCommentHandlerLink().getCommentId())) {
							commOnlyOneTesta.put(commentUseObj
									.getCommentHandlerLink().getCommentId(),
									commentUseObj);
							retCommentiTesta = retCommentiTesta
									+ "\n"
									+ commentUseObj.getCommentHandlerLink()
											.getCommentText(linguaCorrente);
						}
					}
					if (pos == CommentiDocumento.DOPO_DETTAGLI) {
						if (!commOnlyOnePiede.containsKey(commentUseObj
								.getCommentHandlerLink().getCommentId())) {
							commOnlyOnePiede.put(commentUseObj
									.getCommentHandlerLink().getCommentId(),
									commentUseObj);
							retCommentiPiede = retCommentiPiede
									+ "\n"
									+ commentUseObj.getCommentHandlerLink()
											.getCommentText(linguaCorrente);
						}
					}
				}
			}
			fatturaRpt.setCommenti(retCommentiTesta);
			fatturaRpt.setCommentiPiede(retCommentiPiede);
		}
	}

	protected BigDecimal recuperaPrezzo(DocumentoVenditaRiga riga) {
		BigDecimal prezzo = (riga.getPrezzo() != null) ? riga.getPrezzo()
				: new BigDecimal(0);
		if (PersDatiVen.getCurrentPersDatiVen().isGestionePrezzoExtra()) {
			BigDecimal prezzoExtra = riga.getPrezzoExtra();
			if (prezzoExtra != null && prezzo != null)
				prezzo = prezzo.add(prezzoExtra);
		}
		return prezzo;
	}

	protected void preparaCommenti(DocumentoVendita documentoVendita)
			throws Exception {
		if (condizioniGenerali.getPersDatiVen().getTipoPosizioneCommentiFat() != PersDatiVen.TESTA_PIEDE_FATTURA)
			return;
		List commentUseLink = CommentService.getFindCommentUses(
				documentoVendita.getCommentHandler(), entity, task);
		if (commentUseLink != null && !commentUseLink.isEmpty()) {
			Iterator iterCommentUseLink = commentUseLink.iterator();
			while (iterCommentUseLink.hasNext()) {
				CommentUseLink commentUseLinkObj = (CommentUseLink) iterCommentUseLink
						.next();
				listaCommenti.add(commentUseLinkObj);
			}
		}
	}

	protected void inserisciInfoInizioDocumento(DocumentoVendita docven,
			FatturaVendita fattura, FatturaVenditaRpt fatturaRpt)
			throws Exception {
		/*** formattazione riga titolo documento ***/
		inserisciRiferimentoBolla(docven, fattura, fatturaRpt);
		/*** formattazione riga commenti testata documento ***/
		if (condizioniGenerali.getPersDatiVen().getTipoPosizioneCommentiFat() == PersDatiVen.TESTA_PIEDE_BOLLA) {
			FatturaVenditaRigaRpt rigaCommento = getCommentiTestataInAlto(
					docven, fattura, fatturaRpt);
			if (rigaCommento != null) {
				// compilazione campi chiave
				rigaCommento.setBatchJobId(batchJobId);
				rigaCommento.setReportNr(reportNr);
				rigaCommento.setRigaJobId(rigaJobId);
				rigaCommento.setDetRigaJob(++detRigaJobId);
				rigaCommento.setIdRigaFat(detRigaJobId);
				fatturaRpt.getRigheRpt().add(rigaCommento);
			}
		}
	}

	protected void inserisciInfoFineDocumento(DocumentoVendita docven,
			FatturaVendita fattura, FatturaVenditaRpt fatturaRpt)
			throws Exception {
		if (condizioniGenerali.getPersDatiVen().getTipoPosizioneCommentiFat() == PersDatiVen.TESTA_PIEDE_BOLLA) {
			FatturaVenditaRigaRpt rigaCommento = getCommentiTestataInBasso(
					docven, fattura, fatturaRpt);
			if (rigaCommento != null) {
				// compilazione campi chiave
				rigaCommento.setBatchJobId(batchJobId);
				rigaCommento.setReportNr(reportNr);
				rigaCommento.setRigaJobId(rigaJobId);
				rigaCommento.setDetRigaJob(++detRigaJobId);
				rigaCommento.setIdRigaFat(detRigaJobId);
				fatturaRpt.getRigheRpt().add(rigaCommento);
			}
		}
	}

	protected void inserisciRiferimentoBolla(DocumentoVendita docven,
			FatturaVendita fattura, FatturaVenditaRpt fatturaRpt) {
		if (docven.getTipoBolla() != TipoBolla.DDT)
			return;
		String str = getStringaRiferimentoBolla(docven);
		if (str != null) {
			FatturaVenditaRigaRpt fatturaRigaRptRifBolla = initFatturaVenditaRigaRiferimento(
					str, fattura);
			// compilazione campi chiave
			fatturaRigaRptRifBolla.setBatchJobId(batchJobId);
			fatturaRigaRptRifBolla.setReportNr(reportNr);
			fatturaRigaRptRifBolla.setRigaJobId(rigaJobId);
			fatturaRigaRptRifBolla.setDetRigaJob(++detRigaJobId);
			fatturaRigaRptRifBolla.setIdRigaFat(detRigaJobId);

			fatturaRigaRptRifBolla.setRAnnoDoc(docven.getAnnoDocumento());
			fatturaRigaRptRifBolla.setRNumDoc(docven.getNumeroDocumento());
			fatturaRigaRptRifBolla.setRNumBolle(docven.getNumeratoreBolla()
					.getIdNumeratore());
			if (docven.getSerieNumBolle() != null)
				fatturaRigaRptRifBolla.setRSerieNumBolle(docven
						.getSerieNumBolle().getIdSerie());
			fatturaRigaRptRifBolla.setNota(docven.getNota());

			setDatiUlterioriTestataCommento(fatturaRigaRptRifBolla, docven);

			fatturaRpt.getRigheRpt().add(fatturaRigaRptRifBolla);
		}
	}

	// protected void inserisciCommentiTestataInAlto(DocumentoVendita docven,
	// FatturaVendita fattura, FatturaVenditaRpt fatturaRpt) throws Exception {
	protected FatturaVenditaRigaRpt getCommentiTestataInAlto(
			DocumentoVendita docven, FatturaVendita fattura,
			FatturaVenditaRpt fatturaRpt) throws Exception {

		FatturaVenditaRigaRpt rigaCommento = null;
		CommentiDocumento commPos = (CommentiDocumento) Factory
				.createObject(it.thera.thip.base.comuniVenAcq.CommentiDocumento.class);
		String commenti = commPos.formattaListaCommenti(commPos
				.getCommentiPerPosizione(docven.getCommentHandler(), entity,
						task, "" + CommentiDocumento.NORMALE
								+ CommentiDocumento.PRIMA_DETTAGLI,
						linguaCorrente));
		if (commenti != null && !commenti.equals("")) {
			rigaCommento = initFatturaVenditaRigaCommentiDoc(commenti, fattura);

			this.setDatiUlterioriTestataCommento(rigaCommento, docven);

		}
		return rigaCommento;
	}

	// protected void inserisciCommentiTestataInBasso(DocumentoVendita docven,
	// FatturaVendita fattura, FatturaVenditaRpt fatturaRpt) throws Exception{
	protected FatturaVenditaRigaRpt getCommentiTestataInBasso(
			DocumentoVendita docven, FatturaVendita fattura,
			FatturaVenditaRpt fatturaRpt) throws Exception {

		FatturaVenditaRigaRpt rigaCommento = null;
		CommentiDocumento commPos = (CommentiDocumento) Factory
				.createObject(it.thera.thip.base.comuniVenAcq.CommentiDocumento.class);
		String commenti = commPos.formattaListaCommenti(commPos
				.getCommentiPerPosizione(docven.getCommentHandler(), entity,
						task, "" + CommentiDocumento.DOPO_DETTAGLI,
						linguaCorrente));
		if (commenti != null && !commenti.equals("")) {
			rigaCommento = initFatturaVenditaRigaCommentiDoc(commenti, fattura);

			this.setDatiUlterioriTestataCommento(rigaCommento, docven);

		}
		return rigaCommento;
	}

	public FatturaVenditaRigaRpt initFatturaVenditaRigaCommentiDoc(String rif,
			FatturaVendita fattura) {
		FatturaVenditaRigaRpt rigaRpt = initFatturaVenditaRigaRpt(null, null,
				fattura, null);
		rigaRpt.setTipoRigaReport(DatiGeneraliFattura.TIPO_RIGA_REPORT_INFO_DOC);
		rigaRpt.setNumDecUnitari(new BigDecimal(0));
		rigaRpt.setCommenti(rif);
		return rigaRpt;
	}

	// attenzione questi metodi vengono ridefiniti in
	// personalizzazioni
	protected void setDatiUlterioriRigaCommento(FatturaVenditaRigaRpt fattura,
			DocumentoVenRigaPrm riga) {
		fattura.setDataBolla(riga.getDataBolla());
		fattura.setNumeroBolla(riga.getNumeroBolla());
		DocumentoOrdineRiga rigaOrdine = riga.getRigaOrdine();
		if (rigaOrdine != null) {
			fattura.setRAnnoOrd(rigaOrdine.getAnnoDocumento());
			fattura.setRNumOrd(rigaOrdine.getNumeroDocumento());
		}
		fattura.setRAnnoDoc(riga.getAnnoDocumento());
		fattura.setRNumDoc(riga.getNumeroDocumento());
	}

	protected void setDatiUlterioriTestataCommento(
			FatturaVenditaRigaRpt fattura, DocumentoVendita testata) {
		fattura.setDataBolla(testata.getDataBolla());
		fattura.setNumeroBolla(testata.getNumeroBolla());
		OrdineTestata ordine = testata.getOrdineTestata();
		if (ordine != null) {
			fattura.setRAnnoOrd(ordine.getAnnoDocumento());
			fattura.setRNumOrd(ordine.getNumeroDocumento());
		}
	}

	protected static final String selectDocStr = "SELECT "
			+ DocumentoVenditaTM.ID_AZIENDA + ","
			+ DocumentoVenditaTM.ID_ANNO_DOC + ","
			+ DocumentoVenditaTM.ID_NUMERO_DOC + " FROM "
			+ DocumentoVenditaTM.TABLE_NAME + " WHERE "
			+ DocumentoVenditaTM.ID_AZIENDA + " = ? " + " AND "
			+ DocumentoVenditaTM.ANNO_FATTURA + " = ? " + " AND "
			+ DocumentoVenditaTM.NUMERO_FATTURA + " = ? " + " AND ("
			+ DocumentoVenditaTM.ID_AZIENDA + " <> ? " + " OR "
			+ DocumentoVenditaTM.ID_ANNO_DOC + " <> ? " + " OR "
			+ DocumentoVenditaTM.ID_NUMERO_DOC + " <> ? )";

	protected static final CachedStatement selectDocStmt = new CachedStatement(
			selectDocStr);

	// Metodo da implementare nel caso in cui si debbano inserire delle righe
	// i cui importi devono essere calcolati nei totali di fattura
	protected FatturaVenditaRpt aggiungiRighePrimaDiTotaleImporti(
			FatturaVendita fattura, FatturaVenditaRpt fatturaRpt) {
		return fatturaRpt;
	}

	protected boolean checkDichIntento(DocumentoVendita docven) {
		boolean ret = false;

		ClienteVendita clienteDichIntento = getClienteFatturazione();
		if (clienteDichIntento == null)
			clienteDichIntento = getCliente();
		String numDich = clienteDichIntento.getCliente()
				.getNumDichiarazioneIntento();

		if (numDich != null && !numDich.equals("")) {
			// la data utilizzata per stabilire la validità della dichiarazione
			// di intento è : data bolla se esiste la bolla, data fattura in
			// caso contrario
			java.sql.Date dataRifer = null;
			if (docven.getDataBolla() != null)
				dataRifer = docven.getDataBolla();
			else {
				if (docven.getDataFattura() != null)
					dataRifer = docven.getDataFattura();
				else
					dataRifer = getDataStampa();
			}
			if (dataRifer != null) {
				if (clienteDichIntento.getCliente().getValDichIntento()
						.checkValidity(dataRifer))
					return true;
			}
		}
		return ret;
	}

	protected String getDesExtArt(ArticoloCliente artCli, Articolo articolo) {
		return ImportiDocumentoOrdineUtil.getDesExtSoloInLingua(
				artCli != null ? artCli.getDescrizioneEst() : null,
				articolo.getDescrizioneArticoloNLS(), linguaCorrente);
	}

	protected ClienteVendita getClienteFatturazione(DocumentoVendita docven) {
		if (docven.getCondizioneFatturazione() == ClienteVendita.CLIENTE_FATTURATO
				&& docven.getClienteFatturazione() != null)
			return docven.getClienteFatturazione();
		return docven.getCliente();
	}

	protected String getSintesiConfigurazione(Configurazione cfg,
			boolean rigaPrimaria) {
		String result = null;
		if (cfg != null && cfg.getIdConfigurazione().intValue() != 0)
			result = cfg.elaboraSintesiPerStampeDettConfig(entity.getId(),
					linguaCorrente, 8000,
					rigaPrimaria ? DocumentoBaseRiga.RIGA_PRIMARIA
							: DocumentoBaseRiga.RIGA_SECONDARIA_PER_COMPONENTE);
		return result;
	}

	public void impostaFiltroNumeroDataFat(FatturaVendita fatVen) {
		java.sql.Date dataFat = fatVen.getDataFat();
		String numeroFat = fatVen.getIdNumeroFat();
		String annoFat = fatVen.getIdAnnoFat();
		Vector colonne = getFiltro().getColonneFiltro();
		for (int i = 0; i < colonne.size(); i++) {
			ColonneFiltri colFiltro = (ColonneFiltri) colonne.elementAt(i);
			if (colFiltro.getClassAdName().equals("NumeroFattura")) {
				try {
					int size = colFiltro.getAdditionalClassAd().getType()
							.getSize();
					while (annoFat.length() < size) {
						annoFat += " ";
					}
				} catch (Exception ex) {
					ex.printStackTrace(Trace.excStream);
				}
				String filtro = annoFat + ColonneFiltri.ANNO_SEP + numeroFat;
				CondizioniFiltri.svuotaColonnaFiltro(colFiltro);
				CondizioniFiltri.impostaFiltroFrom(colFiltro, filtro);
				CondizioniFiltri.impostaFiltroTo(colFiltro, filtro);
			} else if (colFiltro.getClassAdName().equals("DataFattura")) {
				DateType dt = new DateType();
				String dataStr = dt.objectToString(dataFat);
				CondizioniFiltri.svuotaColonnaFiltro(colFiltro);
				CondizioniFiltri.impostaFiltroFrom(colFiltro, dataStr);
				CondizioniFiltri.impostaFiltroTo(colFiltro, dataStr);
			}
		}
	}

	public boolean isEm(String tmp) {
		return (tmp != null && !tmp.equals("")) ? false : true;
	}

	public String getListNumFact(String where) {
		where = VistaStampaFattureTM.ID_AZIENDA + " = '"
				+ Azienda.getAziendaCorrente() + "' AND " + where;
		String stmtFatthisStr = "SELECT DISTINCT ("
				+ concatKeyFattura(VistaStampaFattureTM.ANNO_FATTURA,
						VistaStampaFattureTM.NUMERO_FATTURA) + ")" + " FROM "
				+ VistaStampaFattureTM.TABLE_NAME + " WHERE " + where;
		return stmtFatthisStr;
	}

	public String concatKeyFattura(String annnoFat, String numeroFat) {
		Database db = ConnectionManager.getCurrentDatabase();
		String dataBaseConcat = db.getConcatOperator();
		return annnoFat + dataBaseConcat + "'" + KeyHelper.KEY_SEPARATOR + "'"
				+ dataBaseConcat + numeroFat;
	}

	public FatturaVendita getFattura() {
		return fattura;
	}

	public FatturaVenditaRpt getFatturaRpt() {
		return fatturaRpt;
	}
}
