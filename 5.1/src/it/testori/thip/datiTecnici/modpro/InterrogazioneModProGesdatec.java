package it.testori.thip.datiTecnici.modpro;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thera.thermfw.common.BusinessObjectAdapter;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.Proxy;
import com.thera.thermfw.security.Authorizable;

import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.articolo.PoliticaRiordino;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.datiTecnici.modpro.Attivita;
import it.thera.thip.datiTecnici.modpro.ModelloProduttivo;
import it.thera.thip.datiTecnici.modpro.ModelloProduttivoProxy;

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
		return super.save(force);
	}

}
