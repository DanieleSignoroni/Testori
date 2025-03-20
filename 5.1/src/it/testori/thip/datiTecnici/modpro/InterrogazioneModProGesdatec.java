package it.testori.thip.datiTecnici.modpro;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.common.BusinessObjectAdapter;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.gui.cnr.OpenType;
import com.thera.thermfw.persist.ErrorCodes;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.Proxy;
import com.thera.thermfw.security.Authorizable;

import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.articolo.PoliticaRiordino;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.generale.ParametroPsn;
import it.thera.thip.base.generale.PersDatiGen;
import it.thera.thip.cs.ThipException;
import it.thera.thip.datiTecnici.modpro.Attivita;
import it.thera.thip.datiTecnici.modpro.AttivitaProdMateriale;
import it.thera.thip.datiTecnici.modpro.AttivitaProdProdotto;
import it.thera.thip.datiTecnici.modpro.AttivitaProduttiva;
import it.thera.thip.datiTecnici.modpro.ModelloProduttivo;
import it.thera.thip.datiTecnici.modpro.ModelloProduttivoProxy;
import it.thera.thip.datiTecnici.modpro.ModelloProduttivoTM;
import it.thera.thip.datiTecnici.modpro.web.ModelloProduttivoDCNoConflict;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 07/03/2025
 * <br><br>
 * <b>71XXX    DSSOF3    07/03/2025</b>
 * <p></p>
 */

public class InterrogazioneModProGesdatec extends BusinessObjectAdapter implements Authorizable {

	protected String iIdAzienda;

	protected String iPriorita = "N";

	protected Proxy iArticoloPadre = new Proxy(it.thera.thip.base.articolo.Articolo.class);

	protected ModelloProduttivoProxy iModelloProduttivoOrig = new ModelloProduttivoProxy(it.thera.thip.datiTecnici.modpro.ModelloProduttivo.class);

	protected Proxy iAttivitaPrincipale = new Proxy(it.thera.thip.datiTecnici.modpro.Attivita.class);

	protected Proxy iMaterialePrincipale = new Proxy(it.thera.thip.base.articolo.Articolo.class);

	protected Proxy iPoliticaRiordino = new Proxy(it.thera.thip.base.articolo.PoliticaRiordino.class);

	protected List<InterrogazioneModProGesdatecAttivita> iRigheAttivita = new ArrayList<InterrogazioneModProGesdatecAttivita>();

	protected List<InterrogazioneModProGesdatecMateriale> iRigheMateriale = new ArrayList<InterrogazioneModProGesdatecMateriale>();

	protected BigDecimal iAltezzaEffettiva;
	protected BigDecimal iAltezzaManufatto;
	protected BigDecimal iLunghezzaManufatto;

	protected BigDecimal iTempoUnitarioAtv;

	public InterrogazioneModProGesdatec() {
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public String getIdAzienda() {
		return iIdAzienda;
	}

	public void setIdAzienda(String idAzienda) {
		this.iIdAzienda = idAzienda;
		setIdAziendaInternal(idAzienda);
	}

	public void setArticoloPadre(Articolo articolo){
		this.iArticoloPadre.setObject(articolo);
	}

	public Articolo getArticoloPadre() {
		return (Articolo)iArticoloPadre.getObject();
	}

	public void setArticoloPadreKey(String key) {
		iArticoloPadre.setKey(key);
	}

	public String getArticoloPadreKey() {
		return iArticoloPadre.getKey();
	}

	public void setIdArticoloLancio(String idArticolo) {
		String key = iArticoloPadre.getKey();
		iArticoloPadre.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idArticolo));
	}

	public String getIdArticoloLancio() {
		String key = iArticoloPadre.getKey();
		String objIdArticolo = KeyHelper.getTokenObjectKey(key,2);
		return objIdArticolo;
	}

	public void setModelloProduttivoOrig(ModelloProduttivo modelloProduttivo) {
		String oldObjectKey = getKey();
		String idAzienda = null;
		if (modelloProduttivo != null) {
			idAzienda = KeyHelper.getTokenObjectKey(modelloProduttivo.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iModelloProduttivoOrig.setObject(modelloProduttivo);
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public ModelloProduttivo getModelloProduttivoOrig() {
		return (ModelloProduttivo)iModelloProduttivoOrig.getObject();
	}

	public void setModelloProduttivoOrigKey(String key) {
		String oldObjectKey = getKey();
		iModelloProduttivoOrig.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getModelloProduttivoOrigKey() {
		return iModelloProduttivoOrig.getKey();
	}

	public void setIdModelloProdOriginale(Integer idModello) {
		String key = iModelloProduttivoOrig.getKey();
		iModelloProduttivoOrig.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idModello));
	}

	public Integer getIdModelloProdOriginale() {
		String key = iModelloProduttivoOrig.getKey();
		String objIdModello = KeyHelper.getTokenObjectKey(key,2);
		return KeyHelper.stringToIntegerObj(objIdModello);
	}

	public void setAttivitaPrincipale(Attivita attivita) {
		String oldObjectKey = getKey();
		String idAzienda = null;
		if (attivita != null) {
			idAzienda = KeyHelper.getTokenObjectKey(attivita.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iAttivitaPrincipale.setObject(attivita);
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public Attivita getAttivitaPrincipale() {
		return (Attivita) iAttivitaPrincipale.getObject();
	}

	public void setAttivitaPrincipaleKey(String key) {
		String oldObjectKey = getKey();
		iAttivitaPrincipale.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getAttivitaPrincipaleKey() {
		return iAttivitaPrincipale.getKey();
	}

	public void setIdAttivitaPrincipale(String idAttivita) {
		String key = iAttivitaPrincipale.getKey();
		iAttivitaPrincipale.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idAttivita));
	}

	public String getIdAttivitaPrincipale() {
		String key = iAttivitaPrincipale.getKey();
		String objIdAttivita = KeyHelper.getTokenObjectKey(key, 2);
		return objIdAttivita;
	}

	public void setMaterialePrincipale(Articolo articolo){
		this.iMaterialePrincipale.setObject(articolo);
	}

	public Articolo getMaterialePrincipale() {
		return (Articolo)iMaterialePrincipale.getObject();
	}

	public void setMaterialePrincipaleKey(String key) {
		iMaterialePrincipale.setKey(key);
	}

	public String getMaterialePrincipaleKey() {
		return iMaterialePrincipale.getKey();
	}

	public void setIdMaterialePrincipale(String idArticolo) {
		String key = iMaterialePrincipale.getKey();
		iMaterialePrincipale.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idArticolo));
	}

	public String getIdMaterialePrincipale() {
		String key = iMaterialePrincipale.getKey();
		String objIdArticolo = KeyHelper.getTokenObjectKey(key,2);
		return objIdArticolo;
	}

	public void setPoliticaRiordino(PoliticaRiordino politicaRiordino) {
		String oldObjectKey = getKey();
		String idAzienda = null;
		if (politicaRiordino != null) {
			idAzienda = KeyHelper.getTokenObjectKey(politicaRiordino.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iPoliticaRiordino.setObject(politicaRiordino);
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public PoliticaRiordino getPoliticaRiordino() {
		return (PoliticaRiordino)iPoliticaRiordino.getObject();
	}

	public void setPoliticaRiordinoKey(String key) {
		String oldObjectKey = getKey();
		iPoliticaRiordino.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getPoliticaRiordinoKey() {
		return iPoliticaRiordino.getKey();
	}

	public void setIdPoliticaRiordino(String idPoliticaRiordino) {
		String key = iPoliticaRiordino.getKey();
		iPoliticaRiordino.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idPoliticaRiordino));
	}

	public String getIdPoliticaRiordino() {
		String key = iPoliticaRiordino.getKey();
		String objIdPoliticaRiordino = KeyHelper.getTokenObjectKey(key,2);
		return objIdPoliticaRiordino;
	}

	public void setPriorita(String priorita) {
		this.iPriorita = priorita;
	}

	public String getPriorita() {
		return iPriorita;
	}

	public BigDecimal getAltezzaEffettiva() {
		return iAltezzaEffettiva;
	}

	public void setAltezzaEffettiva(BigDecimal iAltezzaEffettiva) {
		this.iAltezzaEffettiva = iAltezzaEffettiva;
	}

	public BigDecimal getAltezzaManufatto() {
		return iAltezzaManufatto;
	}

	public void setAltezzaManufatto(BigDecimal iAltezzaManufatto) {
		this.iAltezzaManufatto = iAltezzaManufatto;
	}

	public BigDecimal getLunghezzaManufatto() {
		return iLunghezzaManufatto;
	}

	public void setLunghezzaManufatto(BigDecimal iLunghezzaManufatto) {
		this.iLunghezzaManufatto = iLunghezzaManufatto;
	}

	public BigDecimal getTempoUnitarioAtv() {
		return iTempoUnitarioAtv;
	}

	public void setTempoUnitarioAtv(BigDecimal iTempoUnitarioAtv) {
		this.iTempoUnitarioAtv = iTempoUnitarioAtv;
	}

	public List<InterrogazioneModProGesdatecAttivita> getRigheAttivita() {
		return iRigheAttivita;
	}

	public List<InterrogazioneModProGesdatecMateriale> getRigheMateriale() {
		return iRigheMateriale;
	}

	protected void setIdAziendaInternal(String idAzienda) {
		String key1 = iArticoloPadre.getKey();
		iArticoloPadre.setKey(KeyHelper.replaceTokenObjectKey(key1, 1, idAzienda));
		String key2 = iAttivitaPrincipale.getKey();
		iAttivitaPrincipale.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
		String key3 = iModelloProduttivoOrig.getKey();
		iModelloProduttivoOrig.setKey(KeyHelper.replaceTokenObjectKey(key3, 1, idAzienda));
		String key4 = iMaterialePrincipale.getKey();
		iMaterialePrincipale.setKey(KeyHelper.replaceTokenObjectKey(key4, 1, idAzienda));
		String key5 = iPoliticaRiordino.getKey();
		iPoliticaRiordino.setKey(KeyHelper.replaceTokenObjectKey(key5, 1, idAzienda));
	}

	@Override
	public int save(boolean force) throws SQLException {
		int rc = super.save(force);
		if(getModelloProduttivoOrig() != null) {
			//.Vado in aggiornamento
		}else {
			rc = generaNuovoModelloProduttivo();
		}
		return rc;
	}

	/**
	 * Generazione di un nuovo modello produttivo.<br>
	 * 
	 * Tramite {@link #iArticoloPadre} creo un nuovo {@link ModelloProduttivo}.<br></br>
	 * L'attivita' principale e' la {@link #iAttivitaPrincipale}.<br>
	 * A questa si aggiungono tutti i materiali contenuti nella collezzione {@link #iRigheMateriale}.<br>
	 * Si aggiunge poi anche un prodotto primario preso da {@link #iArticoloPadre}.<br></br>
	 * 
	 * Se ho poi delle {@link #iRigheAttivita} allora genero delle altre attivita' vuote.<br>
	 * @return
	 * @throws ThipException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected int generaNuovoModelloProduttivo() throws ThipException {
		int rc = 0;
		ModelloProduttivo modPro = creaModelloProduttivo(getArticoloPadre());
		if(modPro != null) {
			if(getAttivitaPrincipale() != null) {
				AttivitaProduttiva atvPrincipale = creaAttivitaProduttiva(modPro, getAttivitaPrincipale());
				atvPrincipale.setAttivitaIniziale(true);

				Iterator iterMats = getRigheMateriale().iterator();
				while(iterMats.hasNext()) {
					InterrogazioneModProGesdatecMateriale rigaMat = (InterrogazioneModProGesdatecMateriale) iterMats.next();

					AttivitaProdMateriale atvMateriale = creaAttivitaProduttivaMateriale(modPro, atvPrincipale);
					atvMateriale.setArticolo(rigaMat.getMateriale());
					atvMateriale.setCoeffImpiego(rigaMat.getCoeffImpiego());
					atvMateriale.getDescrizione().setDescrizione(rigaMat.getMateriale().getDescrizioneArticoloNLS().getDescrizione());
					atvMateriale.getDescrizione().setDescrizioneRidotta(rigaMat.getMateriale().getDescrizioneArticoloNLS().getDescrizioneRidotta());

					atvPrincipale.getMateriali().add(atvMateriale);
				}

				if(getMaterialePrincipale() != null) {
					AttivitaProdMateriale atvMateriale = creaAttivitaProduttivaMateriale(modPro, atvPrincipale);
					atvMateriale.setArticolo(getMaterialePrincipale());
					atvMateriale.setCoeffImpiego(calcolaCoefficienteImpiego()); //.Calcolo tramite un metodo il coeff di impiego
					atvMateriale.getDescrizione().setDescrizione(getMaterialePrincipale().getDescrizioneArticoloNLS().getDescrizione());
					atvMateriale.getDescrizione().setDescrizioneRidotta(getMaterialePrincipale().getDescrizioneArticoloNLS().getDescrizioneRidotta());

					atvPrincipale.getMateriali().add(atvMateriale);
				}

				modPro.getAttivita().add(atvPrincipale);

				if(getArticoloPadre() != null) {
					AttivitaProdProdotto attivitaProdProdotto = creaAttivitaProduttivaProdotto(modPro, atvPrincipale);
					attivitaProdProdotto.setTipoProdotto(AttivitaProdProdotto.PRODOTTO_PRIMARIO);
					attivitaProdProdotto.setArticolo(getArticoloPadre());
					attivitaProdProdotto.getDescrizione().setDescrizione(getArticoloPadre().getDescrizioneArticoloNLS().getDescrizione());
					attivitaProdProdotto.getDescrizione().setDescrizioneRidotta(getArticoloPadre().getDescrizioneArticoloNLS().getDescrizioneRidotta());
				}
			}
			Iterator iterAtvs = getRigheAttivita().iterator();
			while(iterAtvs.hasNext()) {
				InterrogazioneModProGesdatecAttivita rigaAtv = (InterrogazioneModProGesdatecAttivita) iterAtvs.next();

				AttivitaProduttiva atvProduttiva = creaAttivitaProduttiva(modPro, rigaAtv.getAttivita());

				modPro.getAttivita().add(atvProduttiva);
			}
			ModelloProduttivoDCNoConflict boDC = (ModelloProduttivoDCNoConflict) Factory.createObject(ModelloProduttivoDCNoConflict.class);
			boDC.initialize("ModelloProduttivo");
			boDC.setAutoCommit(false);
			rc = boDC.initSecurityServices(OpenType.NEW, true, true, false);
			if(rc == BODataCollector.OK) {
				boDC.setBo(modPro);
				rc = boDC.save();
				if(rc != BODataCollector.OK) {
					throw new ThipException(boDC.getErrorList().getErrors());
				}
			}
		}else {
			rc = ErrorCodes.GENERIC_ERROR;
		}
		return rc;
	}

	protected ModelloProduttivo creaModelloProduttivo(Articolo articolo) {
		ModelloProduttivo modProd = (ModelloProduttivo) Factory.createObject(ModelloProduttivo.class);
		modProd.setIdModello(0); //.Valorizzato a 0, sara' poi la save che !isOnDB mi setta il progressivo
		modProd.setArticolo(articolo);
		modProd.getDescrizione().setDescrizione(articolo.getDescrizioneArticoloNLS().getDescrizione());
		modProd.getDescrizione().setDescrizioneRidotta(articolo.getDescrizioneArticoloNLS().getDescrizioneRidotta());
		modProd.setDominio(ModelloProduttivo.GENERICO);
		modProd.setTipoModello(ModelloProduttivo.PRODUZIONE);
		modProd.setIdStabilimento(PersDatiGen.getCurrentPersDatiGen().getIdStabilimento());
		modProd.setPriorita(getPriorita());
		modProd.setQuantitaBase(((short) 1));
		modProd.setIdMagazzinoVersamento(PersDatiGen.getCurrentPersDatiGen().getIdMagazzino());
		modProd.setIdMagazzinoPrelievo(PersDatiGen.getCurrentPersDatiGen().getIdMagazzino());
		return modProd;
	}

	protected AttivitaProduttiva creaAttivitaProduttiva(ModelloProduttivo modelloProduttivo, Attivita attivita) {
		AttivitaProduttiva attivitaProd = (AttivitaProduttiva) Factory.createObject(AttivitaProduttiva.class);
		attivitaProd.setIdAzienda(modelloProduttivo.getIdAzienda());
		attivitaProd.setModello(modelloProduttivo);
		attivitaProd.setAttivita(attivita);
		attivitaProd.getDescrizione().setDescrizione(attivita.getDescrizione().getDescrizione());
		attivitaProd.getDescrizione().setDescrizioneRidotta(attivita.getDescrizione().getDescrizioneRidotta());
		//attivitaProd.setIdOperazione("");
		attivitaProd.setIdCentroLavoro("");
		attivitaProd.setIdCentroCosto("");
		return attivitaProd;
	}

	protected AttivitaProdMateriale creaAttivitaProduttivaMateriale(ModelloProduttivo modelloProduttivo,AttivitaProduttiva attivitaProd) {
		AttivitaProdMateriale attivitaProdMat = (AttivitaProdMateriale) Factory.createObject(AttivitaProdMateriale.class);
		attivitaProdMat.setIdAzienda(modelloProduttivo.getIdAzienda());
		attivitaProdMat.setFather(attivitaProd);
		return attivitaProdMat;
	}

	protected AttivitaProdProdotto creaAttivitaProduttivaProdotto(ModelloProduttivo modelloProduttivo,AttivitaProduttiva attivitaProd) {
		AttivitaProdProdotto attivitaProdProdotto = (AttivitaProdProdotto) Factory.createObject(AttivitaProdProdotto.class);
		attivitaProdProdotto.setIdAzienda(modelloProduttivo.getIdAzienda());
		attivitaProdProdotto.setFather(attivitaProd);
		return attivitaProdProdotto;
	}

	protected BigDecimal calcolaCoefficienteImpiego() throws ThipException {
		BigDecimal result = BigDecimal.ZERO;

		BigDecimal L = getLunghezzaManufatto() != null ? getLunghezzaManufatto() : BigDecimal.ZERO; //.Lunghezza di taglio
		BigDecimal A = getAltezzaManufatto() != null ? getAltezzaManufatto() : BigDecimal.ZERO; //.Altezza di taglio
		BigDecimal AP = getAltezzaEffettiva() != null ? getAltezzaEffettiva() : BigDecimal.ZERO; //.Altezza pezza da tagliare

		BigDecimal SF = sfridoPezzaTestori(); //.Sfrido pezza

		YPercentualeSfrido percentualeSfridoTabellato = YPercentualeSfrido.percentualeSfridoAltezzaTaglio(A);

		BigDecimal PF = percentualeSfridoTabellato != null ? percentualeSfridoTabellato.getPercentuale() : BigDecimal.ZERO; //.Percentuale sfrido

		BigDecimal L1 = L.add(L.multiply(PF).divide(BigDecimal.valueOf(100), MathContext.DECIMAL128));; //.L incrementata di una percentuale di sfrido

		BigDecimal AP_su_A = new BigDecimal((AP.divide(A,MathContext.DECIMAL128)).toBigInteger());

		if(AP_su_A.compareTo(BigDecimal.ZERO) == 0) {
			throw new ThipException(new ErrorMessage("BAS0000078","Divisione tra altezza effettiva e altezza manufatto uguale a 0"));
		}

		BigDecimal A1 = (AP.subtract(SF)).divide(AP_su_A,MathContext.DECIMAL128); //. (AP-SF) / (AP/A)	Il risultato di AP/A deve essere un numero intero, senza decimali

		result = ((A1.multiply(L1)).divide(AP,MathContext.DECIMAL128)).divide(new BigDecimal(1000, MathContext.DECIMAL128)); //.((A1 x L1)/AP)/1000

		return result;
	}

	/**
	 * Recupera lo sfrido delle pezze, un valore assoluto per tutte le lavorazioni
	 * @return un {@link BigDecimal} contentente il valore del parametro personalizzato
	 * @throws NumberFormatException nel caso in cui il valore contenuto nel parametro personalizzato non sia formattato correttamente
	 */
	public static BigDecimal sfridoPezzaTestori() throws NumberFormatException {
		BigDecimal sfrido = null;
		String value = ParametroPsn.getValoreParametroPsn("pers.datiTecnici.modpro.ModelloProduttivoGesdatec", "SfridoPezza");
		if(value != null && !value.isEmpty()) {
			sfrido = new BigDecimal(value);
		}
		return sfrido != null ? sfrido : BigDecimal.ZERO;
	}

	@SuppressWarnings("rawtypes")
	public List recuperaModelliProduttiviTemplate(String idArticolo) {
		try {
			String where = " "+ModelloProduttivoTM.ID_AZIENDA+" = '"+getIdAzienda()+"' AND "+ModelloProduttivoTM.R_ARTICOLO+" = '"+idArticolo+"' ";
			return ModelloProduttivo.retrieveList(ModelloProduttivo.class, where, "", false);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return null;
	}

}
