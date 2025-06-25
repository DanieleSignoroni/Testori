package it.testori.thip.magazzino.generalemag;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import com.thera.thermfw.base.TimeUtils;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;

import it.testori.thip.base.articolo.YArticoloDatiMagaz;
import it.thera.thip.acquisti.documentoAC.DocumentoAcquisto;
import it.thera.thip.acquisti.offerteFornitore.OffertaFornitore;
import it.thera.thip.acquisti.ordineAC.OrdineAcquisto;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.comuniVenAcq.DocumentoOrdineTestata;
import it.thera.thip.datiTecnici.configuratore.Configurazione;
import it.thera.thip.datiTecnici.configuratore.ConfigurazioneRicEnh;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.magazzino.generalemag.LottoTM;
import it.thera.thip.magazzino.generalemag.PersDatiMagazzino;
import it.thera.thip.produzione.documento.DocumentoProduzione;
import it.thera.thip.produzione.ordese.OrdineEsecutivo;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 04/06/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71992    04/06/2025  DSSOF3   Prima stesura
 */

public class CreaLottiTestoriUtils {

	public static final String CREA_LOTTI_NUOVO = "CREA_LOTTI_TESTORI_NUOVO";

	//Tipo di Lotto
	public static final char MANUFATTI = 'M';
	public static final char PEZZE = 'P';

	//Provenienza
	public static final char PRODUZIONE = 'P';
	public static final char CONTO_LAVORO = 'T';
	public static final char ACQUISTO = 'F';

	/**
	 * Costanti simboliche per l'attributo iAmbito.
	 */
	public static final char CREA_DA_OFFERTA   = 'F';//Fix 22277
	public static final char CREA_DA_ORDINE    = 'O';
	public static final char CREA_DA_DOCUMENTO = 'D';

	public static final char CREA_DA_DOC_SPED  = 'S';
	public static final char CREA_DA_DOC_ENTR  = 'E';

	/**
	 * Costanti simboliche l'attributo iTipo.
	 */
	public static final char TIPO_ACQ = 'F';
	public static final char TIPO_PRD = 'P';
	public static final char TIPO_CL  = 'T';
	public static final char TIPO_VEN = 'S';
	public static final char TIPO_VEN_CTO_TRASF = 'U';  //MG FIX 4656

	public static final String STMT_MAX_ID_LOTTO = "SELECT MAX(CAST(SUBSTRING(ID_LOTTO, 5, 5) AS INT)) "
			+ "FROM "+LottoTM.TABLE_NAME+" "
			+ "WHERE "+LottoTM.ID_AZIENDA+" = ? "
			+ "  AND SUBSTRING("+LottoTM.ID_LOTTO+", 1, 4) = ? "
			+ "";

	public static final String STMT_MAX_ID_LOTTO_PEZZE = "SELECT MAX(CAST(SUBSTRING(ID_LOTTO, ?, ?) AS INT)) "
			+ "FROM "+LottoTM.TABLE_NAME+" "
			+ "WHERE "+LottoTM.ID_AZIENDA+" = ? "
			+ "  AND "+LottoTM.ID_LOTTO+" LIKE ? "
			+ "";

	protected String iNumeroDocOrd;

	protected String iAnnoDocOrd;

	protected java.sql.Date iDataDocOrd;

	protected Integer iNumRiga;

	protected Integer iNumRigaPadre;

	protected String iIdArticolo;

	protected Integer iIdVersione;

	protected String iIdEsternoConfig;

	protected String iIdMagazzino;

	protected String iIdCommessa;

	protected String iIdCliFor;

	protected String iIdAzienda;

	protected Date iDataApertura;

	protected char iTipo;

	protected char iAmbito;

	protected String iIdFornitore;

	protected BigDecimal iQtaUMPrm;

	protected String iIdLotto;

	protected boolean iGeneraCodiceLottoAutomatico = true;

	@SuppressWarnings("rawtypes")
	protected List iLottiOrig;

	@SuppressWarnings("rawtypes")
	protected List iLottiOrdine;

	public void setNumeroDocOrd(String numeroDocOrd) {
		iNumeroDocOrd = numeroDocOrd;
	}

	/**
	 * Restituisce l'attributo
	 * @return String
	 */
	public String getNumeroDocOrd() {
		return iNumeroDocOrd;
	}

	/**
	 * Valorizza l'attributo
	 * @param dataDocOrd java.sql.Date
	 */
	public void setDataDocOrd(java.sql.Date dataDocOrd) {
		iDataDocOrd = dataDocOrd;
	}

	/**
	 * Restituisce l'attributo
	 * @return java.sql.Date
	 */
	public java.sql.Date getDataDocOrd() {
		return iDataDocOrd;
	}

	/**
	 * Valorizza l'attributo
	 * @param NumRiga Integer
	 */
	public void setNumRiga(Integer NumRiga) {
		iNumRiga = NumRiga;
	}

	/**
	 * Restituisce l'attributo
	 * @return String
	 */
	public Integer getNumRiga() {
		return iNumRiga;
	}

	/**
	 * Valorizza l'attributo
	 * @param NumRigaPadre Integer
	 */
	public void setNumRigaPadre(Integer NumRigaPadre) {
		iNumRigaPadre = NumRigaPadre;
	}

	/**
	 * Restituisce l'attributo
	 * @return Integer
	 */
	public Integer getNumRigaPadre() {
		return iNumRigaPadre;
	}

	/**
	 * Valorizza l'attributo
	 * @param annoDocOrd String
	 */
	public void setAnnoDocOrd(String annoDocOrd) {
		iAnnoDocOrd = annoDocOrd;
	}

	/**
	 * Restituisce l'attributo
	 * @return String
	 */
	public String getAnnoDocOrd() {
		return iAnnoDocOrd;
	}

	/**
	 * Valorizza l'attributo
	 * @param idArticolo String
	 */
	public void setIdArticolo(String idArticolo) {
		iIdArticolo = idArticolo;
	}

	/**
	 * Restituisce l'attributo
	 * @return String
	 */
	public String getIdArticolo() {
		return iIdArticolo;
	}

	/**
	 * Valorizza l'attributo
	 * @param idVersione Integer
	 */
	public void setIdVersione(Integer idVersione) {
		iIdVersione = idVersione;
	}

	/**
	 * Restituisce l'attributo
	 * @return Integer
	 */
	public Integer getIdVersione() {
		return iIdVersione;
	}

	/**
	 * Valorizza l'attributo
	 * @param idEsternoConfig String
	 */
	public void setIdEsternoConfig(String idEsternoConfig) {
		iIdEsternoConfig = idEsternoConfig;
	}

	/**
	 * Restituisce l'attributo
	 * @return String
	 */
	public String getIdEsternoConfig() {
		return iIdEsternoConfig;
	}

	/**
	 * Valorizza l'attributo
	 * @param idMagazzino String
	 */
	public void setIdMagazzino(String idMagazzino) {
		iIdMagazzino = idMagazzino;
	}

	/**
	 * Restituisce l'attributo
	 * @return String
	 */
	public String getIdMagazzino() {
		return iIdMagazzino;
	}

	/**
	 * Valorizza l'attributo
	 * @param idCommessa String
	 */
	public void setIdCommessa(String idCommessa) {
		iIdCommessa = idCommessa;
	}

	/**
	 * Restituisce l'attributo
	 * @return String
	 */
	public String getIdCommessa() {
		return iIdCommessa;
	}

	/**
	 * Valorizza l'attributo
	 * @param idCliFor String
	 */
	public void setIdCliFor(String idCliFor) {
		iIdCliFor = idCliFor;
	}

	/**
	 * Restituisce l'attributo
	 * @return String
	 */
	public String getIdCliFor() {
		return iIdCliFor;
	}

	public void setIdAzienda(String idAzienda) {
		iIdAzienda = idAzienda;
	}

	/**
	 * Restituisce l'attributo
	 * @return String
	 */
	public String getIdAzienda() {
		return iIdAzienda;
	}

	public void setDataApertura(java.sql.Date dataApertura) {
		iDataApertura = dataApertura;
	}

	/**
	 * Restituisce l'attributo
	 * @return java.sql.Date
	 */
	public java.sql.Date getDataApertura() {
		return iDataApertura;
	}

	/**
	 * Valorizza l'attributo
	 * @param ambito char
	 */
	public void setAmbito(char ambito) {
		iAmbito = ambito;
	}

	/**
	 * Restituisce l'attributo
	 * @return char
	 */
	public char getAmbito() {
		return iAmbito;
	}

	/**
	 * Valorizza l'attributo
	 * @param tipo char
	 */
	public void setTipo(char tipo) {
		iTipo = tipo;
	}

	/**
	 * Restituisce l'attributo
	 * @return char
	 */
	public char getTipo() {
		return iTipo;
	}

	/**
	 * Valorizza l'attributo
	 * @param idFornitore String
	 */
	public void setIdFornitore(String idFornitore) {
		iIdFornitore = idFornitore;
	}

	/**
	 * Restituisce l'attributo
	 * @return String
	 */
	public String getIdFornitore() {
		return iIdFornitore;
	}

	/**
	 * Valorizza l'attributo
	 * @param qtaUMPrm BigDecimal
	 */
	public void setQtaUMPrm(BigDecimal qtaUMPrm) {
		iQtaUMPrm = qtaUMPrm;
	}

	/**
	 * Restituisce l'attributo
	 * @return BigDecimal
	 */
	public BigDecimal getQtaUMPrm() {
		return iQtaUMPrm;
	}

	/**
	 * Valorizza l'attributo
	 * @param lottiOrig List
	 */
	@SuppressWarnings("rawtypes")
	public void setLottiOrig(List lottiOrig) {
		iLottiOrig = lottiOrig;
	}

	/**
	 * Restituisce l'attributo
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	public List getLottiOrig() {
		return iLottiOrig;
	}

	/**
	 * Valorizza l'attributo
	 * @param lottiOrdine List
	 */
	@SuppressWarnings("rawtypes")
	public void setLottiOrdine(List lottiOrdine) {
		iLottiOrdine = lottiOrdine;
	}

	/**
	 * Restituisce l'attributo
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	public List getLottiOrdine() {
		return iLottiOrdine;
	}

	public String getIdLotto() {
		return iIdLotto;
	}

	public void setIdLotto(String iIdLotto) {
		this.iIdLotto = iIdLotto;
	}

	public boolean isGeneraCodiceLottoAutomatico() {
		return iGeneraCodiceLottoAutomatico;
	}

	public void setGeneraCodiceLottoAutomatico(boolean iGeneraCodiceLottoAutomatico) {
		this.iGeneraCodiceLottoAutomatico = iGeneraCodiceLottoAutomatico;
	}

	public static String buildNextIdProgressivoLotto(char tipoCodificaLotto, char Provenienza) {
		String idProgressivo = null;
		try {
			switch (tipoCodificaLotto) {
			case YArticoloDatiMagaz.FELTRI_MANUFATTI:
				idProgressivo = getYearSuffix();
				idProgressivo += MANUFATTI;
				idProgressivo += Provenienza;
				idProgressivo += getMaxLottoId(Azienda.getAziendaCorrente(), idProgressivo, 5);
				break;
			case YArticoloDatiMagaz.PEZZE:
				break;
			case YArticoloDatiMagaz.SUBBI_FELTRI:
				idProgressivo = getYearSuffix();
				idProgressivo += PEZZE;
				idProgressivo += Provenienza;
				idProgressivo += getMaxLottoId(Azienda.getAziendaCorrente(), idProgressivo, 5);
				break;

			default:
				break;
			}
		}catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return idProgressivo;
	}

	public static String getMaxLottoId(String idAzienda, String prefisso, int lunghezzaSuffisso) throws SQLException {
		try (PreparedStatement stmt = ConnectionManager.getCurrentConnection().prepareStatement(STMT_MAX_ID_LOTTO)) {
			stmt.setString(1, idAzienda);
			stmt.setString(2, prefisso);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					String maxIdLotto = rs.getString(1);
					if(maxIdLotto == null) {
						String suffissoFormattato = String.format("%0" + lunghezzaSuffisso + "d", 1);
						return suffissoFormattato;
					}else {
						int numero = 0;

						try {
							numero = Integer.parseInt(maxIdLotto.trim())+1;
						} catch (NumberFormatException e) {
							numero = 0;
						}

						String suffissoFormattato = String.format("%0" + lunghezzaSuffisso + "d", numero);
						return suffissoFormattato;
					}
				}
			}
		}
		return null;
	}

	public static String getMaxProgressivoPezzeFromLotto(String idAzienda,
			String idLotto,
			int lunghezzaSuffisso
			) throws SQLException {
		try (PreparedStatement stmt = ConnectionManager.getCurrentConnection().prepareStatement(STMT_MAX_ID_LOTTO_PEZZE)) {
			stmt.setString(1, idAzienda);
			stmt.setInt(2, idLotto.length());
			if(lunghezzaSuffisso > 0)
				stmt.setInt(2, idLotto.length()+lunghezzaSuffisso);
			else
				stmt.setInt(2, idLotto.length());
			stmt.setString(3, "%"+idLotto);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					String maxIdLotto = rs.getString(1);
					//					if(maxIdLotto == null) {
					//						String suffissoFormattato = String.format("%0" + lunghezzaSuffisso + "d", 1);
					//						return suffissoFormattato;
					//					}else {
					//						int numero = 0;
					//
					//						try {
					//							numero = Integer.parseInt(maxIdLotto.trim())+1;
					//						} catch (NumberFormatException e) {
					//							numero = 0;
					//						}
					//
					//						String suffissoFormattato = String.format("%0" + lunghezzaSuffisso + "d", numero);
					//						return suffissoFormattato;
					//					}
					return maxIdLotto;
				}
			}
		}
		return null;
	}

	/**
	 * @return le due cifre significative dell'anno corrente
	 */
	public static String getYearSuffix() {
		int year = Year.now().getValue();
		return String.format("%02d", year % 100);
	}

	public static boolean isArticoloGestioneLottiTestori(Articolo articolo, char provenienza) {
		if(articolo == null)
			return false;
		switch (provenienza) {
		case ACQUISTO:
			if(articolo.getArticoloDatiMagaz().getCodAutLotAcq() == YArticoloDatiMagaz.FELTRI_MANUFATTI ||
			articolo.getArticoloDatiMagaz().getCodAutLotAcq() == YArticoloDatiMagaz.PEZZE || 
			articolo.getArticoloDatiMagaz().getCodAutLotAcq() == YArticoloDatiMagaz.SUBBI_FELTRI)
				return true;
			break;
		case CONTO_LAVORO:
			if(articolo.getArticoloDatiMagaz().getCodAutLotCl() == YArticoloDatiMagaz.FELTRI_MANUFATTI ||
			articolo.getArticoloDatiMagaz().getCodAutLotCl() == YArticoloDatiMagaz.PEZZE || 
			articolo.getArticoloDatiMagaz().getCodAutLotCl() == YArticoloDatiMagaz.SUBBI_FELTRI)
				return true;
			break;
		case PRODUZIONE:
			if(articolo.getArticoloDatiMagaz().getCodAutLotProd() == YArticoloDatiMagaz.FELTRI_MANUFATTI ||
			articolo.getArticoloDatiMagaz().getCodAutLotProd() == YArticoloDatiMagaz.PEZZE || 
			articolo.getArticoloDatiMagaz().getCodAutLotProd() == YArticoloDatiMagaz.SUBBI_FELTRI)
				return true;
			break;
		default:
			break;
		}
		return false;
	}

	public static boolean isArticoloGestioneSubbiFeltri(Articolo articolo, char provenienza) {
		if(articolo == null)
			return false;
		switch (provenienza) {
		case ACQUISTO:
			if(articolo.getArticoloDatiMagaz().getCodAutLotAcq() == YArticoloDatiMagaz.SUBBI_FELTRI)
				return true;
			break;
		case CONTO_LAVORO:
			if(articolo.getArticoloDatiMagaz().getCodAutLotCl() == YArticoloDatiMagaz.SUBBI_FELTRI)
				return true;
			break;
		case PRODUZIONE:
			if(articolo.getArticoloDatiMagaz().getCodAutLotProd() == YArticoloDatiMagaz.SUBBI_FELTRI)
				return true;
			break;
		default:
			break;
		}
		return false;
	}

	public static boolean isArticoloGestioneFilatiManufatti(Articolo articolo, char provenienza) {
		if(articolo == null)
			return false;
		switch (provenienza) {
		case ACQUISTO:
			if(articolo.getArticoloDatiMagaz().getCodAutLotAcq() == YArticoloDatiMagaz.FELTRI_MANUFATTI)
				return true;
			break;
		case CONTO_LAVORO:
			if(articolo.getArticoloDatiMagaz().getCodAutLotCl() == YArticoloDatiMagaz.FELTRI_MANUFATTI)
				return true;
			break;
		case PRODUZIONE:
			if(articolo.getArticoloDatiMagaz().getCodAutLotProd() == YArticoloDatiMagaz.FELTRI_MANUFATTI)
				return true;
			break;
		default:
			break;
		}
		return false;
	}

	public static boolean isArticoloGestionePezze(Articolo articolo, char provenienza) {
		if(articolo == null)
			return false;
		switch (provenienza) {
		case ACQUISTO:
			if(articolo.getArticoloDatiMagaz().getCodAutLotAcq() == YArticoloDatiMagaz.PEZZE)
				return true;
			break;
		case CONTO_LAVORO:
			if(articolo.getArticoloDatiMagaz().getCodAutLotCl() == YArticoloDatiMagaz.PEZZE)
				return true;
			break;
		case PRODUZIONE:
			if(articolo.getArticoloDatiMagaz().getCodAutLotProd() == YArticoloDatiMagaz.PEZZE)
				return true;
			break;
		default:
			break;
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public List creaLottiAutomatici() {
		List lotti = new ArrayList();
		lotti = creaLotti();
		return lotti;
	} 

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List creaLotti() {
		List lotti = new ArrayList();
		Lotto lotto = null;

		try {
			Articolo articolo = (Articolo) Articolo.elementWithKey(Articolo.class, KeyHelper.buildObjectKey(new String[] {getIdAzienda(),getIdArticolo()}), PersistentObject.NO_LOCK);
			if(articolo != null) {

				char tipoCodifica = tipoCodificaTestoriFromTipoProvenienza(articolo);
				String idLotto = null;
				if(isGeneraCodiceLottoAutomatico()) {
					idLotto = buildNextIdProgressivoLotto(tipoCodifica, tipoProvenienzaFromPal());
				}else {
					idLotto = getIdLotto();
				}
				lotto = creaLotto(idLotto, iIdVersione, iIdEsternoConfig, iIdMagazzino, false);

				if(lotto != null) {
					lotto.save(); 
					lotti.add(lotto);
				}
			}
		}catch(SQLException ex) {
			ex.printStackTrace(Trace.excStream);
		}

		return lotti;
	}

	public char tipoProvenienzaFromPal() {
		char tipoCodifica = '0';
		switch (getTipo()) {
		case PersDatiMagazzino.TIPO_ACQ:
			tipoCodifica = ACQUISTO;
			break;
		case PersDatiMagazzino.TIPO_CL:
			tipoCodifica = CONTO_LAVORO;
			break;
		case PersDatiMagazzino.TIPO_PRD:
			tipoCodifica = PRODUZIONE;
			break;
		default:
			break;
		}
		return tipoCodifica;
	}

	public char tipoCodificaTestoriFromTipoProvenienza(Articolo articolo) {
		char tipoCodifica = '0';
		switch (getTipo()) {
		case PersDatiMagazzino.TIPO_ACQ:
			tipoCodifica = articolo.getArticoloDatiMagaz().getCodAutLotAcq();
			break;
		case PersDatiMagazzino.TIPO_CL:
			tipoCodifica = articolo.getArticoloDatiMagaz().getCodAutLotCl();
			break;
		case PersDatiMagazzino.TIPO_PRD:
			tipoCodifica = articolo.getArticoloDatiMagaz().getCodAutLotProd();
			break;
		default:
			break;
		}
		return tipoCodifica;
	}

	public Lotto creaLotto(String idLotto, Integer versione, String idEsternoConfig, String idMagazzino, boolean creaSaldi) {

		try {
			//...Creo un nuovo lotto con il nuovo codice
			Lotto lotto = (Lotto) Factory.createObject(Lotto.class);
			lotto.setCodiceAzienda(getIdAzienda());
			lotto.setCodiceArticolo(getIdArticolo());
			lotto.setCodiceLotto(idLotto);
			lotto.setDataApertura(getDataApertura());

			java.sql.Date dataScadenza = calcolaDataScadenzaLotto(lotto.getArticolo().getArticoloDatiMagaz().getGiorniValidita());
			lotto.setDataScadenza(dataScadenza);
			if(lotto.getArticolo().getArticoloDatiMagaz() != null)
				lotto.setGiorniScadenza(lotto.getArticolo().getArticoloDatiMagaz().getGiorniValidita());

			//...Se devo gestire la creazione automatica dei saldi
			lotto.setMagazzino(getIdMagazzino());
			lotto.setCheckCreaSaldi(creaSaldi);
			lotto.setCheckTuttiSaldi(false);
			lotto.setCheckTuttiMagazzini(false);
			String idRifDocFmt = getIdRifDocFormattato(getTipo(), getAmbito()); //Fix 22277
			if(creaSaldi) {
				if (versione != null)
					lotto.setVersione(versione);
				if (idEsternoConfig != null && !idEsternoConfig.equals("")) { //Fix 4322
					//Fix 4290 inizio
					Configurazione cfg = ConfigurazioneRicEnh.recuperaConfigurazione(getIdAzienda(), getIdArticolo(), idEsternoConfig);
					lotto.setConfigurazione(cfg);
					//Fix 4290 fine
				}
				else
					lotto.setIdConfigurazione(Configurazione.CONFIGURAZIONE_DUMMY);
			}
			//...fine

			//...Inserisco i riferimenti all'ordine o al documento che ha generato il lotto
			if(getTipo() == PersDatiMagazzino.TIPO_ACQ) {
				lotto.setRifDocAcq(getAnnoDocOrd() + "/" + getNumeroDocOrd());
				lotto.setRifRigaDocAcq("" + getNumRiga());
				lotto.setDataDocAcq(getDataDocOrd());
				//Fix 5151 - inizio
				lotto.setCodiceFornitore(getIdCliFor());
				//Fix 5151 - inizio
				lotto.setRifDocAcqFmt(idRifDocFmt);//Fix 22277

			}
			else if(getTipo() == PersDatiMagazzino.TIPO_PRD) {
				if(getAmbito() == CREA_DA_DOCUMENTO) {
					lotto.setRifDocProd(getAnnoDocOrd() + "/" + getNumeroDocOrd());
					lotto.setRifRigaDocProd("" + getNumRiga()); //...Codice riga prodotto
					lotto.setDataDocProd(getDataDocOrd());
				}
				else if(getAmbito() == CREA_DA_ORDINE) {
					lotto.setRifDocProd(getAnnoDocOrd() + "/" + getNumeroDocOrd());
					lotto.setRifRigaDocProd("" + getNumRigaPadre()); //...Codice riga attivita
					lotto.setRifRigaSecProd("" + getNumRiga()); //...Codice riga prodotto
					lotto.setDataDocProd(getDataDocOrd());
				}

				lotto.setRifDocProdFmt(idRifDocFmt);//Fix 22277
			}
			else if(getTipo() == PersDatiMagazzino.TIPO_CL) {
				lotto.setRifDocCLav(getAnnoDocOrd() + "/" + getNumeroDocOrd());
				lotto.setRifRigaDocCLav("" + getNumRiga());
				lotto.setDataDocCLav(getDataDocOrd());
				//Fix 5151 - inizio
				lotto.setCodiceFornitore(getIdCliFor());
				//Fix 5151 - fine
				lotto.setRifDocCLavFmt(idRifDocFmt);//Fix 22277
			}

			return lotto;
		}
		catch (Exception ex) {
			ex.printStackTrace(Trace.excStream);
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public static CreaLottiTestoriUtils creaProposizioneAutLotto(char tipo, String numeroDocOrd, String annoDocOrd, java.sql.Date dataDocOrd, Integer NumRiga, Integer NumRigaPadre, String idArticolo, Integer idVersione, String idEsternoCfg, String idMagazzino, String idCommessa, String idCliFor, char ambito, List lottiOrig, List lottiOrdine, java.sql.Date dataApertura, BigDecimal qtaUMPrm) {
		java.sql.Date data = dataApertura;
		if(data == null)
			data = TimeUtils.getCurrentDate();

		List lotti = lottiOrig;
		if(lotti == null)
			lotti = new ArrayList();
		CreaLottiTestoriUtils pal = (CreaLottiTestoriUtils)Factory.createObject(CreaLottiTestoriUtils.class);
		pal.inizializzaParametri(tipo, numeroDocOrd, annoDocOrd, dataDocOrd, NumRiga, NumRigaPadre, idArticolo, idVersione, idEsternoCfg, idMagazzino, idCommessa, idCliFor, ambito, lotti, lottiOrdine, data, qtaUMPrm);
		return pal;
	}

	@SuppressWarnings("rawtypes")
	public void inizializzaParametri(char tipo, String numeroDocOrd, String annoDocOrd, java.sql.Date dataDocOrd, Integer NumRiga, Integer NumRigaPadre, String idArticolo, Integer idVersione, String idEsternoCfg, String idMagazzino, String idCommessa, String idCliFor, char ambito, List lottiOrig, List lottiOrdine, java.sql.Date dataApertura, BigDecimal qtaUMPrm) {
		setTipo(tipo);
		setNumeroDocOrd(numeroDocOrd);
		setAnnoDocOrd(annoDocOrd);
		setDataDocOrd(dataDocOrd);
		setNumRiga(NumRiga);
		setNumRigaPadre(NumRigaPadre);
		setIdArticolo(idArticolo);
		setIdVersione(idVersione);
		setIdEsternoConfig(idEsternoCfg);
		setIdMagazzino(idMagazzino);
		setIdCommessa(idCommessa);
		setIdCliFor(idCliFor);
		setAmbito(ambito);
		setDataApertura(dataApertura);
		setQtaUMPrm(qtaUMPrm);
		setLottiOrig(lottiOrig);
		setLottiOrdine(lottiOrdine);
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public String getIdRifDocFormattato(char tipo, char ambito){
		DocumentoOrdineTestata testata =null;
		switch (tipo){

		case PersDatiMagazzino.TIPO_ACQ :
		case PersDatiMagazzino.TIPO_CL:
			testata = getTestataAcquisto(ambito);
			return testata.getNumeroDocumentoFormattato();

		case PersDatiMagazzino.TIPO_PRD :
			if(ambito ==CREA_DA_ORDINE){
				OrdineEsecutivo ordProd =getTestataOrdProd();
				return ordProd.getNumeroOrdFmt();
			}

			else if(ambito ==CREA_DA_DOCUMENTO){
				DocumentoProduzione docProd =getTestataDocProd();
				return docProd.getNumeroDocFormattato();

			}
			break;
		}
		return null;
	}


	public DocumentoOrdineTestata getTestataAcquisto(char ambito){
		DocumentoOrdineTestata testata =null;

		if(ambito ==CREA_DA_OFFERTA)
			testata = (DocumentoOrdineTestata)Factory.createObject(OffertaFornitore.class);
		else if(ambito ==CREA_DA_ORDINE)
			testata =(DocumentoOrdineTestata)Factory.createObject(OrdineAcquisto.class);
		else if(ambito ==CREA_DA_DOCUMENTO)
			testata =(DocumentoOrdineTestata)Factory.createObject(DocumentoAcquisto.class);

		if (testata ==null) return null;
		testata.setIdAzienda(Azienda.getAziendaCorrente());
		testata.setNumeroDocumento(getNumeroDocOrd());
		testata.setAnnoDocumento(getAnnoDocOrd());
		try{
			testata.retrieve();
		}
		catch(Exception ex){
			ex.printStackTrace(Trace.excStream);
		}

		return testata;
	}

	public DocumentoProduzione getTestataDocProd(){
		DocumentoProduzione docProd =(DocumentoProduzione)Factory.createObject(DocumentoProduzione.class);
		docProd.setIdAzienda(Azienda.getAziendaCorrente());
		docProd.setIdNumeroDoc(this.getNumeroDocOrd());
		docProd.setIdAnnoDoc(this.getAnnoDocOrd());
		try{
			docProd.retrieve();
		}
		catch(Exception ex){
			ex.printStackTrace(Trace.excStream);
		}
		return docProd;
	}

	public OrdineEsecutivo getTestataOrdProd(){
		OrdineEsecutivo ordProd = (OrdineEsecutivo)Factory.createObject(OrdineEsecutivo.class);
		ordProd.setIdAzienda(Azienda.getAziendaCorrente());
		ordProd.setIdNumeroOrdine(this.getNumeroDocOrd());
		ordProd.setIdAnnoOrdine(this.getAnnoDocOrd());
		try{
			ordProd.retrieve();
		}
		catch(Exception ex){
			ex.printStackTrace(Trace.excStream);
		}
		return ordProd;
	}

	protected java.sql.Date calcolaDataScadenzaLotto(Integer giorniVal) {
		java.sql.Date dataScadenza = null;
		if(giorniVal != null)
			dataScadenza = TimeUtils.addDays(getDataApertura(), giorniVal.intValue());
		return dataScadenza;
	}

}
