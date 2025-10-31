package it.testori.thip.vendite.documentoVE;

import java.sql.SQLException;

import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.persist.*;
import it.thera.thip.vendite.documentoVE.*;
import it.testori.thip.base.cliente.YClienteFinale;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.cs.ThipException;
import it.thera.thip.produzione.rifornLinea.PianoRifornimento;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 13/10/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72166    13/10/2025  DSSOF3   Prima stesura
 */

public class YDocumentoVendita extends DocumentoVendita {

	protected Proxy iClienteFinale = new Proxy(YClienteFinale.class);

	protected Proxy iPianoRifornimento = new Proxy(it.thera.thip.produzione.rifornLinea.PianoRifornimento.class);

	public YDocumentoVendita() {
		super();
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setClienteFinale(YClienteFinale Cliente){
		this.iClienteFinale.setObject(Cliente);
		setDirty();
	}

	public YClienteFinale getClienteFinale(){
		return (YClienteFinale) iClienteFinale.getObject();
	}

	public void setClienteFinaleKey(String key){
		iClienteFinale.setKey(key);
		setDirty();
	}

	public String getClienteFinaleKey(){
		return iClienteFinale.getKey();
	}

	public void setIdCodiceClienteFinale(Integer rCliente){
		String key = iClienteFinale.getKey();
		iClienteFinale.setKey(KeyHelper.replaceTokenObjectKey(key, 3, rCliente));
		setDirty();
	}

	public Integer getIdCodiceClienteFinale(){
		String key = iClienteFinale.getKey();
		Integer objRCliente = KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 3));
		return objRCliente;
	}

	public void setPianoRifornimento(PianoRifornimento Cliente){
		this.iPianoRifornimento.setObject(Cliente);
		setDirty();
	}

	public PianoRifornimento getPianoRifornimento(){
		return (PianoRifornimento) iPianoRifornimento.getObject();
	}

	public void setPianoRifornimentoKey(String key){
		iPianoRifornimento.setKey(key);
		setDirty();
	}

	public String getPianoRifornimentoKey(){
		return iPianoRifornimento.getKey();
	}

	public void setAnnoPianoRfr(String anno){
		String key = iPianoRifornimento.getKey();
		iPianoRifornimento.setKey(KeyHelper.replaceTokenObjectKey(key, 2, anno));
		setDirty();
	}

	public String getAnnoPianoRfr(){
		String key = iPianoRifornimento.getKey();
		String objAnno = KeyHelper.getTokenObjectKey(key, 2);
		return objAnno;
	}

	public void setIdNumeroPianoRfr(String IdNumeroPianoRfr){
		String key = iPianoRifornimento.getKey();
		iPianoRifornimento.setKey(KeyHelper.replaceTokenObjectKey(key, 3, IdNumeroPianoRfr));
		setDirty();
	}

	public String getIdNumeroPianoRfr(){
		String key = iPianoRifornimento.getKey();
		String objIdNumeroPianoRfr = KeyHelper.getTokenObjectKey(key, 3);
		return objIdNumeroPianoRfr;
	}

	@Override
	public void setIdCliente(String rCliente) {
		super.setIdCliente(rCliente);
		String key = iClienteFinale.getKey();
		iClienteFinale.setKey(KeyHelper.replaceTokenObjectKey(key, 2, rCliente));
	}

	@Override
	public void setIdAzienda(String idAzienda) {
		super.setIdAzienda(idAzienda);
		if(iClienteFinale != null) {
			iClienteFinale.setKey(KeyHelper.replaceTokenObjectKey(iClienteFinale.getKey(), 1, idAzienda));
			iPianoRifornimento.setKey(KeyHelper.replaceTokenObjectKey(iPianoRifornimento.getKey(), 1, idAzienda));
		}
	}

	@Override
	public int delete() throws SQLException {
		if(getPianoRifornimento() != null) {
			cancellaPianoRifornimento();
		}
		int rcDel = super.delete();
		return rcDel;
	}

	protected int cancellaPianoRifornimento() throws ThipException {
		BODataCollector boDC = (BODataCollector) Factory.createObject(BODataCollector.class);
		boDC.initialize("PianoRifornimento");
		boDC.setBo(getPianoRifornimento());
		boDC.setAutoCommit(false);
		int rc = boDC.delete(getPianoRifornimentoKey());
		if(rc != BODataCollector.OK) {
			throw new ThipException(boDC.getErrorList().getErrors());
		}
		return rc;
	}

	@Override
	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		YDocumentoVendita ordineVendita = (YDocumentoVendita) obj;
		iClienteFinale.setEqual(ordineVendita.iClienteFinale);
	}

}