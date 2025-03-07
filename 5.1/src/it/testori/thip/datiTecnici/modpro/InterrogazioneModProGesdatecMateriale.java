package it.testori.thip.datiTecnici.modpro;

import com.thera.thermfw.common.BusinessObjectAdapter;
import com.thera.thermfw.persist.Child;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.persist.Proxy;

import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.azienda.Azienda;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 07/03/2025
 * <br><br>
 * <b>71XXX    DSSOF3    07/03/2025</b>
 * <p></p>
 */

public class InterrogazioneModProGesdatecMateriale extends BusinessObjectAdapter implements Child {

	protected String iIdAzienda;

	protected Proxy iMateriale = new Proxy(it.thera.thip.base.articolo.Articolo.class);

	protected Proxy iTestata = new Proxy(InterrogazioneModProGesdatec.class);

	public InterrogazioneModProGesdatecMateriale() {
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public String getIdAzienda() {
		return iIdAzienda;
	}

	public void setIdAzienda(String idAzienda) {
		this.iIdAzienda = idAzienda;
		setIdAziendaInternal(idAzienda);
	}

	public void setMateriale(Articolo articolo){
		this.iMateriale.setObject(articolo);
	}

	public Articolo getMateriale() {
		return (Articolo)iMateriale.getObject();
	}

	public void setMaterialeKey(String key) {
		iMateriale.setKey(key);
	}

	public String getMaterialeKey() {
		return iMateriale.getKey();
	}

	public void setIdMateriale(String idArticolo) {
		String key = iMateriale.getKey();
		iMateriale.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idArticolo));
	}

	public String getIdMateriale() {
		String key = iMateriale.getKey();
		String objIdArticolo = KeyHelper.getTokenObjectKey(key,2);
		return objIdArticolo;
	}

	protected void setIdAziendaInternal(String idAzienda) {
		String key1 = iMateriale.getKey();
		iMateriale.setKey(KeyHelper.replaceTokenObjectKey(key1, 1, idAzienda));
		String key2 = iTestata.getKey();
		iTestata.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
	}

	@Override
	public String getKey() {
		return getIdMateriale();
	}

	@Override
	public void setKey(String key) {
		setIdMateriale(key);
	}

	@Override
	public String getFatherKey() {
		return iTestata.getKey();
	}

	@Override
	public void setFatherKey(String key) {
		iTestata.setKey(key);

	}

	@Override
	public void setFather(PersistentObject father) {
		iTestata.setObject(father);
	}

	@Override
	public String getOrderByClause() {
		return "";
	}

}
