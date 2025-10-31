package it.testori.thip.produzione.rifornLinea;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;

import com.thera.thermfw.base.TimeUtils;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.persist.ErrorCodes;
import com.thera.thermfw.persist.Factory;

import it.testori.thip.vendite.documentoVE.YDocumentoVenRigaPrm;
import it.testori.thip.vendite.documentoVE.YDocumentoVendita;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.cliente.ClienteVendita;
import it.thera.thip.base.generale.Numeratore;
import it.thera.thip.base.generale.Serie;
import it.thera.thip.produzione.rifornLinea.GenerazionePianoRiforn;
import it.thera.thip.produzione.rifornLinea.PianoRifornimento;
import it.thera.thip.produzione.rifornLinea.PianoRifornimentoRiga;
import it.thera.thip.vendite.documentoVE.DocumentoVenRigaPrm;
import it.thera.thip.vendite.documentoVE.DocumentoVendita;
import it.thera.thip.vendite.documentoVE.web.DocumentoVenditaDataCollector;
import it.thera.thip.vendite.generaleVE.CausaleDocumentoVendita;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 04/09/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    04/09/2025  DSSOF3   Prima stesura
 */

public class YGenerazionePianoRiforn extends GenerazionePianoRiforn {

	@Override
	protected int afterGenerazionePianoRifornimento(int result, PianoRifornimento pianoRifornimento) {
		int rc = super.afterGenerazionePianoRifornimento(result, pianoRifornimento);
		if(rc > 0 && pianoRifornimento.isOnDB()
				&& pianoRifornimento.getMagazzinoLinea() != null
				&& pianoRifornimento.getMagazzinoLinea().isMagazzinoLinea()) { //..Se il magazzino e' di linea

			//..Genero un documento di vendita di tipo trasferimento in modo da gestire il riapprovvigionamento tramite DDT
			rc = generaDocumentoVenditaTrasferimento(pianoRifornimento);
		}
		return rc;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected int generaDocumentoVenditaTrasferimento(PianoRifornimento pianoRifornimento) {
		YPsnDatiRifornimentoLinea persDati = YPsnDatiRifornimentoLinea.getCurrentYPsnDatiRifornimentoLinea();
		if(persDati != null
				&& persDati.getClienteIntestatario() != null
				&& persDati.getSerieDocVen() != null
				&& persDati.getCausaleDocVen() != null) {
			try {
				DocumentoVendita docVen = creaDocumentoVendita(pianoRifornimento, persDati.getClienteIntestatario(), persDati.getNumeratoreDocVen(), persDati.getSerieDocVen(), persDati.getCausaleDocVen());
				if(docVen != null) {
					Iterator iterRighe = pianoRifornimento.getPianoRifornRighe().iterator();
					while(iterRighe.hasNext()) {
						PianoRifornimentoRiga rigaPiano = (PianoRifornimentoRiga) iterRighe.next();
						DocumentoVenRigaPrm docVenRig = creaDocumentoVenditaRigaPrm(docVen, rigaPiano);
						if(docVenRig != null) {
							docVen.getRighe().add(docVenRig);
						}
					}
					DocumentoVenditaDataCollector boDC = (DocumentoVenditaDataCollector)Factory.createObject(DocumentoVenditaDataCollector.class);
					boDC.setAutoCommit(false);
					boDC.setAutoCheck(true);
					boDC.initialize("DocumentoVendita");
					boDC.setBo(docVen);
					boDC.impostaSecondoCausale();
					int rc = boDC.save();
					if (rc == BODataCollector.ERROR) {
						output.println("errore di generazione documento di trasferimento : "+boDC.messages().toString());
						return ErrorCodes.GENERIC_ERROR;
					}else {
						return 1;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return 0;
	}

	public DocumentoVendita creaDocumentoVendita(PianoRifornimento pianoRifornimento, ClienteVendita cliente, Numeratore numeratore, Serie serie, CausaleDocumentoVendita causale) throws SQLException {
		DocumentoVendita docVen = (DocumentoVendita) Factory.createObject(DocumentoVendita.class);
		java.sql.Date data = TimeUtils.getCurrentDate();
		docVen.setIdAzienda(Azienda.getAziendaCorrente());
		docVen.getNumeratoreHandler().setIdNumeratore(numeratore.getIdNumeratore());
		docVen.getNumeratoreHandler().setDataDocumento(data);
		docVen.getNumeratoreHandler().setIdSerie(serie.getIdSerie());
		docVen.setCausale(causale);
		docVen.setCliente(cliente);
		docVen.completaBO();
		docVen.setMagazzino(causale.getMagazzino());
		docVen.setIdMagazzinoTra(getIdMagazzinoLinea());
		if(docVen instanceof YDocumentoVendita) {
			((YDocumentoVendita) docVen).setPianoRifornimento(pianoRifornimento);
		}
		return docVen;
	}

	public DocumentoVenRigaPrm creaDocumentoVenditaRigaPrm(DocumentoVendita docVen, PianoRifornimentoRiga pianoRiga) {
		DocumentoVenRigaPrm docVenRig = (DocumentoVenRigaPrm) Factory.createObject(DocumentoVenRigaPrm.class);
		docVenRig.setIdAzienda(Azienda.getAziendaCorrente());
		docVenRig.setTestata(docVen);
		docVenRig.setIdCauRig(docVen.getCausale().getIdCausaleRigaDocumVen());
		docVenRig.setIdArticolo(pianoRiga.getIdArticolo());		
		docVenRig.setArticoloVersRichiesta(pianoRiga.getVersione());
		docVenRig.setConfigurazione(pianoRiga.getConfigurazione());
		docVenRig.setIdUMPrm(pianoRiga.getIdUmPrmMag());
		docVenRig.setIdUMRif(pianoRiga.getArticolo().getUMDefaultVendita().getIdUnitaMisura());
		docVenRig.completaBO();
		docVenRig.getQtaPropostaEvasione().setQuantitaInUMPrm(pianoRiga.getQtaPrelievoUMPrm());
		docVenRig.getQtaPropostaEvasione().setQuantitaInUMSec(pianoRiga.getQtaPrelievoUMSec());
		BigDecimal qtaInUMRif = BigDecimal.ZERO;
		qtaInUMRif = docVenRig.getArticolo().convertiUM(pianoRiga.getQtaPrelievoUMPrm(), docVenRig.getUMPrm(), docVenRig.getUMRif(), docVenRig.getArticoloVersRichiesta());
		docVenRig.getQtaPropostaEvasione().setQuantitaInUMRif(qtaInUMRif);
		docVenRig.setAssoggettamentoIVA(pianoRiga.getArticolo().getAssoggettamentoIVA());
		if(docVenRig instanceof YDocumentoVenRigaPrm) {
			((YDocumentoVenRigaPrm) docVenRig).setPianoRifornimentoRiga(pianoRiga);
		}
		return docVenRig;
	}
}
