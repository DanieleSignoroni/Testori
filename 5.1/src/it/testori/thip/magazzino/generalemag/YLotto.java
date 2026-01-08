package it.testori.thip.magazzino.generalemag;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Vector;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.CopyException;
import com.thera.thermfw.persist.Copyable;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.Proxy;

import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.risorse.Risorsa;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.magazzino.generalemag.LottoTM;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 22/07/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72060    22/07/2025  DSSOF3   Prima stesura
 * 72178	21/10/2025	DSSOF3   Aggiunta campi
 * 72242	09/12/2025	DSSOF3   Check codice lotto come chiave assoluta e non per articolo
 * 72273	08/01/2026	DSSOF3	 Aggiunta campo
 */

public class YLotto extends Lotto {

	protected String iNumeroRocche;

	protected BigDecimal iQuantitaOriginale;

	protected BigDecimal iAltezzaMinima;

	protected BigDecimal iAltezzaMassima;

	protected BigDecimal iPesoKg;

	protected String iPezzaGreggia;

	protected BigDecimal iPesoCalcolato;

	protected BigDecimal iPesoAnagrafica;

	protected Proxy iTelaio = new Proxy(it.thera.thip.base.risorse.Risorsa.class);

	protected Proxy iDifettosita = new Proxy(it.testori.thip.magazzino.generalemag.YDifettosita.class);

	protected Proxy iDifettositaAltezza = new Proxy(it.testori.thip.magazzino.generalemag.YDifettositaMisure.class);

	protected boolean iCompilazioneRaccoltaDati = false;

	protected BigDecimal iQuantitaBonifico; //72178
	
	protected String iNotePianQlt; //72273

	public YLotto() {
		super();
		setIdTipoRisorsaTelaio(Risorsa.MACCHINE);
		setIdLivelloRisorsaTelaio(Risorsa.MATRICOLA);
		setCodiceAzienda(Azienda.getAziendaCorrente());
	}

	public boolean isCompilazioneRaccoltaDati() {
		return iCompilazioneRaccoltaDati;
	}

	public void setCompilazioneRaccoltaDati(boolean iCompilazioneRaccoltaDati) {
		this.iCompilazioneRaccoltaDati = iCompilazioneRaccoltaDati;
	}

	public void setNumeroRocche(String numeroRocche) {
		this.iNumeroRocche = numeroRocche;
		setDirty();
	}

	public String getNumeroRocche() {
		return iNumeroRocche;
	}

	public void setQuantitaOriginale(BigDecimal quantitaOriginale) {
		this.iQuantitaOriginale = quantitaOriginale;
		setDirty();
	}

	public BigDecimal getQuantitaOriginale() {
		return iQuantitaOriginale;
	}

	public void setAltezzaMinima(BigDecimal altezzaMinima) {
		this.iAltezzaMinima = altezzaMinima;
		setDirty();
	}

	public BigDecimal getAltezzaMinima() {
		return iAltezzaMinima;
	}

	public void setAltezzaMassima(BigDecimal altezzaMassima) {
		this.iAltezzaMassima = altezzaMassima;
		setDirty();
	}

	public BigDecimal getAltezzaMassima() {
		return iAltezzaMassima;
	}

	public void setPesoKg(BigDecimal pesoKg) {
		this.iPesoKg = pesoKg;
		setDirty();
	}

	public BigDecimal getPesoKg() {
		return iPesoKg;
	}

	public void setPezzaGreggia(String pezzaGreggia) {
		this.iPezzaGreggia = pezzaGreggia;
		setDirty();
	}

	public String getPezzaGreggia() {
		return iPezzaGreggia;
	}

	public void setPesoCalcolato(BigDecimal pesoCalcolato) {
		this.iPesoCalcolato = pesoCalcolato;
		setDirty();
	}

	public BigDecimal getPesoCalcolato() {
		return iPesoCalcolato;
	}

	public void setPesoAnagrafica(BigDecimal pesoAnagrafica) {
		this.iPesoAnagrafica = pesoAnagrafica;
		setDirty();
	}

	public BigDecimal getPesoAnagrafica() {
		return iPesoAnagrafica;
	}

	public void setTelaio(Risorsa telaio) {
		String oldObjectKey = getKey();
		String codiceAzienda = getCodiceAzienda();
		if (telaio != null) {
			codiceAzienda = KeyHelper.getTokenObjectKey(telaio.getKey(), 1);
		}
		setCodiceAziendaInternal(codiceAzienda);
		this.iTelaio.setObject(telaio);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public Risorsa getTelaio() {
		return (Risorsa) iTelaio.getObject();
	}

	public void setTelaioKey(String key) {
		String oldObjectKey = getKey();
		iTelaio.setKey(key);
		String codiceAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setCodiceAziendaInternal(codiceAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getTelaioKey() {
		return iTelaio.getKey();
	}

	public void setIdTipoRisorsaTelaio(char idTipoRisorsaTelaio) {
		String key = iTelaio.getKey();
		Character idTipoRisorsaTelaioTmp = new Character(idTipoRisorsaTelaio);
		iTelaio.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idTipoRisorsaTelaioTmp));
		setDirty();
	}

	public char getIdTipoRisorsaTelaio() {
		String key = iTelaio.getKey();
		String objIdTipoRisorsaTelaio = KeyHelper.getTokenObjectKey(key, 2);
		return KeyHelper.stringToChar(objIdTipoRisorsaTelaio);

	}

	public void setIdLivelloRisorsaTelaio(char idLivelloRisorsaTelaio) {
		String key = iTelaio.getKey();
		Character idLivelloRisorsaTelaioTmp = new Character(idLivelloRisorsaTelaio);
		iTelaio.setKey(KeyHelper.replaceTokenObjectKey(key, 3, idLivelloRisorsaTelaioTmp));
		setDirty();
	}

	public char getIdLivelloRisorsaTelaio() {
		String key = iTelaio.getKey();
		String objIdLivelloRisorsaTelaio = KeyHelper.getTokenObjectKey(key, 3);
		return KeyHelper.stringToChar(objIdLivelloRisorsaTelaio);

	}

	public void setIdRisorsaTelaio(String idRisorsaTelaio) {
		String key = iTelaio.getKey();
		iTelaio.setKey(KeyHelper.replaceTokenObjectKey(key, 4, idRisorsaTelaio));
		setDirty();
	}

	public String getIdRisorsaTelaio() {
		String key = iTelaio.getKey();
		String objIdRisorsaTelaio = KeyHelper.getTokenObjectKey(key, 4);
		return objIdRisorsaTelaio;
	}

	public void setDifettosita(YDifettosita difettosita) {
		String oldObjectKey = getKey();
		String codiceAzienda = getCodiceAzienda();
		if (difettosita != null) {
			codiceAzienda = KeyHelper.getTokenObjectKey(difettosita.getKey(), 1);
		}
		setCodiceAziendaInternal(codiceAzienda);
		this.iDifettosita.setObject(difettosita);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public YDifettosita getDifettosita() {
		return (YDifettosita) iDifettosita.getObject();
	}

	public void setDifettositaKey(String key) {
		String oldObjectKey = getKey();
		iDifettosita.setKey(key);
		String codiceAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setCodiceAziendaInternal(codiceAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getDifettositaKey() {
		return iDifettosita.getKey();
	}

	public void setIdDifettosita(String idDifettosita) {
		String key = iDifettosita.getKey();
		iDifettosita.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idDifettosita));
		setDirty();
	}

	public String getIdDifettosita() {
		String key = iDifettosita.getKey();
		String objIdDifettosita = KeyHelper.getTokenObjectKey(key, 2);
		return objIdDifettosita;
	}

	public void setDifettositaAltezza(YDifettositaMisure DifettositaAltezza) {
		String oldObjectKey = getKey();
		String codiceAzienda = getCodiceAzienda();
		if (DifettositaAltezza != null) {
			codiceAzienda = KeyHelper.getTokenObjectKey(DifettositaAltezza.getKey(), 1);
		}
		setCodiceAziendaInternal(codiceAzienda);
		this.iDifettositaAltezza.setObject(DifettositaAltezza);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public YDifettositaMisure getDifettositaAltezza() {
		return (YDifettositaMisure) iDifettositaAltezza.getObject();
	}

	public void setDifettositaAltezzaKey(String key) {
		String oldObjectKey = getKey();
		iDifettositaAltezza.setKey(key);
		String codiceAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setCodiceAziendaInternal(codiceAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getDifettositaAltezzaKey() {
		return iDifettositaAltezza.getKey();
	}

	public void setCodiceAzienda(String codiceAzienda) {
		super.setCodiceAzienda(codiceAzienda);

	}

	public void setIdDifettositaAltezza(String idDifettositaAltezza) {
		String key = iDifettositaAltezza.getKey();
		iDifettositaAltezza.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idDifettositaAltezza));
		setDirty();
	}

	public String getIdDifettositaAltezza() {
		String key = iDifettositaAltezza.getKey();
		String objIdDifettositaAltezza = KeyHelper.getTokenObjectKey(key, 2);
		return objIdDifettositaAltezza;
	}

	//72178 Inizio
	public BigDecimal getQuantitaBonifico() {
		return iQuantitaBonifico;
	}

	public void setQuantitaBonifico(BigDecimal iQuantitaBonifico) {
		this.iQuantitaBonifico = iQuantitaBonifico;
		setDirty();
	}
	//72178 Fine
	
	//72273 Inizio
	public String getNotePianQlt() {
		return iNotePianQlt;
	}

	public void setNotePianQlt(String iNotePianQlt) {
		this.iNotePianQlt = iNotePianQlt;
		setDirty();
	}
	//72273 Fine
	
	@SuppressWarnings("rawtypes")
	public ErrorMessage checkCodiceLotto() {
		ErrorMessage em = null;
		if(!isOnDB() && 
				(getCodiceLotto() != null && !getCodiceLotto().equals(LOTTO_DUMMY))) {
			String w = " "+LottoTM.ID_AZIENDA+" = '"+getCodiceAzienda()+"' AND "+LottoTM.ID_LOTTO+" = '"+getCodiceLotto()+"' ";
			try {
				Vector list = retrieveList(w, "", false);
				if(list.size() > 0) {
					em = new ErrorMessage("BAS0000078","Lotto con codice "+getCodiceLotto()+" gia' esistente");
				}
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return em;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		YLotto yLotto = (YLotto) obj;
		iTelaio.setEqual(yLotto.iTelaio);
		iDifettosita.setEqual(yLotto.iDifettosita);
		iDifettositaAltezza.setEqual(yLotto.iDifettositaAltezza);
	}

	protected void setCodiceAziendaInternal(String codiceAzienda) {
		super.setCodiceAziendaInternal(codiceAzienda);
		if (iTelaio != null) {
			String key1 = iTelaio.getKey();
			iTelaio.setKey(KeyHelper.replaceTokenObjectKey(key1, 1, codiceAzienda));
		}
		if (iDifettosita != null) {
			String key2 = iDifettosita.getKey();
			iDifettosita.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, codiceAzienda));
		}
		if (iDifettositaAltezza != null) {
			String key3 = iDifettositaAltezza.getKey();
			iDifettositaAltezza.setKey(KeyHelper.replaceTokenObjectKey(key3, 1, codiceAzienda));
		}
	}

}