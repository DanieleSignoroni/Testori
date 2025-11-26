package it.testori.thip.logis.lgb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.collector.SecondaryDataCollector;
import com.thera.thermfw.common.BaseComponentsCollection;
import com.thera.thermfw.common.BusinessObjectAdapter;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.log.LogTask;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.OneToMany;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.persist.Proxy;
import com.thera.thermfw.security.Authorizable;

import it.testori.thip.logis.bas.YCostantiTestori;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.comuniVenAcq.DocumentoOrdineTestata;
import it.thera.thip.base.comuniVenAcq.SpecificheCopiaDocumento;
import it.thera.thip.base.comuniVenAcq.SpecificheCopiaDocumentoVen;
import it.thera.thip.base.documenti.StatoAvanzamento;
import it.thera.thip.base.documenti.web.DocumentoDataCollector;
import it.thera.thip.cs.ThipException;
import it.thera.thip.logis.fis.RigaUds;
import it.thera.thip.logis.fis.TestataUds;
import it.thera.thip.logis.lgb.RigaLista;
import it.thera.thip.logis.lgb.TestataLista;
import it.thera.thip.vendite.documentoVE.DocumentoVendita;
import it.thera.thip.vendite.documentoVE.web.DocumentoVenditaDataCollector;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 26/11/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    26/11/2025  DSSOF3   Prima stesura
 */

public class SceltaTestataListaSpostaUds extends BusinessObjectAdapter implements Authorizable {

	protected String iCodiceSocieta;

	protected Proxy testataLista = new Proxy(TestataLista.class);

	protected boolean iSposta;
	protected String iChiaviSelezionati;

	public SceltaTestataListaSpostaUds() {
		setCodiceSocieta(Azienda.getAziendaCorrente());
	}

	public String getCodiceSocieta() {
		return iCodiceSocieta;
	}

	public void setCodiceSocieta(String iCodiceSocieta) {
		this.iCodiceSocieta = iCodiceSocieta;
		testataLista.setKey(KeyHelper.replaceTokenObjectKey(testataLista.getKey(), 1, iCodiceSocieta));
	}

	public boolean isSposta() {
		return iSposta;
	}

	public void setSposta(boolean iSposta) {
		this.iSposta = iSposta;
	}

	public String getChiaviSelezionati() {
		return iChiaviSelezionati;
	}

	public void setChiaviSelezionati(String iChiaviSelezionati) {
		this.iChiaviSelezionati = iChiaviSelezionati;
	}

	public String getCodiceTestataLista() {
		return KeyHelper.getTokenObjectKey(testataLista.getKey(), 2);
	}

	public void setCodiceTestataLista(String idCodiceLista) {
		testataLista.setKey(KeyHelper.replaceTokenObjectKey(testataLista.getKey(), 2, idCodiceLista));
	}

	public TestataLista getTestataLista(){
		return (TestataLista) testataLista.getObject();
	}

	public void setTestataLista(TestataLista t){
		testataLista.setObject(t);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = super.checkAll(components);
		if(getTestataLista() != null) {
			Vector elencoUds = TestataLista.getElencoUds(getTestataLista());
			if(elencoUds == null || elencoUds.size() == 0) {
				errors.add(new ErrorMessage("BAS0000078","La lsita selezionata non contiene UDS"));
			}
		}
		return errors;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int save(boolean force) throws SQLException {
		int rc = 1;
		if(isSposta() && getChiaviSelezionati() != null) {
			String[] elencoUds = getChiaviSelezionati().split(";");
			if(elencoUds != null && elencoUds.length > 0) {
				List uds = recuperaControllaTestateUds(elencoUds);
				if(uds.size() > 0) {
					DocumentoVendita docOrigine = YCostantiTestori.documentoVenditaTestataLista(getTestataLista());
					if(docOrigine != null) {
						try {
							BODataCollector boDC = YCostantiTestori.createDataCollector("SpecifCopiaDocumentoVen");
							SpecificheCopiaDocumentoVen spec = (SpecificheCopiaDocumentoVen) boDC.getBo();
							spec.setAnnoDocOrig(docOrigine.getAnnoDocumento());
							spec.setNumeroDocOrig(docOrigine.getNumeroDocumento());
							spec.setDivisione(docOrigine.getDivisione());
							if (docOrigine.getDivisione() != null) {
								spec.setDescrDivisione(docOrigine.getDivisione().getIdDescrizione());
							}
							spec.getNumeratore().setIdSerie(recuperaSerieDocOrigine(docOrigine));
							spec.setIdCausale(docOrigine.getIdCau());
							spec.setIdCausaleDocOrig(docOrigine.getIdCau());
							spec.setIdIntestatario(docOrigine.getIdCliente());
							spec.setIdIntestatarioDocOrig(docOrigine.getIdCliente());
							spec.setIdDivisione(docOrigine.getIdDivisione());
							spec.setStatoAvanzamento(StatoAvanzamento.PROVVISORIO);
							spec.setLotti(SpecificheCopiaDocumento.LTT_DUMMY);
							DocumentoOrdineTestata docDest = null;
							docDest = docOrigine.copiaDocumento(spec);
							DocumentoDataCollector docDC = getDocumentoDC(DocumentoVenditaDataCollector.class.getName(), "DocumentoVendita");
							docDC.setBo(docDest);
							docDC.loadAttValue();
							docDC.impostaSecondoCausale();
							docDest.setInCopia(true);		
							long numLog;
							numLog = LogTask.startLogTask(docOrigine, "COPY", false, null);
							docDC.setNumLog(numLog);
							docDC.initSecurityServices(boDC.getMode(), true, true, true);  
							((DocumentoVendita)docDC.getBo()).setSalvaRighe(false);
							SecondaryDataCollector righePrm = docDC.getSecondaryDataCollector("RighePrimarie");

							//qui devo rimuovere le righe che ho scelto a video

							//dalla riga uds prendo la riga lista e prendo la num riga host --> se non ce allora l'utente
							//non l'ha messa quindi tolgo dalla oneToMany

							Collection<Integer> righeDocScelte = Collections.emptyList();
							Iterator iterUds = uds.iterator();
							while(iterUds.hasNext()) {
								TestataUds tu = (TestataUds) iterUds.next();
								Iterator iterRigheUds = tu.getRigheUds().iterator();
								while(iterRigheUds.hasNext()) {
									RigaUds ru = (RigaUds) iterRigheUds.next();
									RigaLista rl = ru.getRigaLista();
									righeDocScelte.add(rl.getNumeroRigaHost());
								}
							}

							((OneToMany)righePrm.getBo()).clear();
							docDC.setAutoCommit(false);
							int rcSave = docDC.save();
							if (rcSave == BODataCollector.OK ) {
								rc = 1;
							}else {
								throw new ThipException(docDC.getErrorList().getErrors());
							}
						}catch (Exception e) {
							e.printStackTrace(Trace.excStream);
							rc = -100;
							throw new ThipException(e.getMessage());
						}
					}else {
						rc = -100;
					}
				}
			}
		}
		return rc;
	}

	protected String recuperaSerieDocOrigine(DocumentoOrdineTestata docOrigine) {
		String idSerie = docOrigine.getNumeroDocumento().substring(0, 2).trim();
		return idSerie;
	}

	protected DocumentoDataCollector getDocumentoDC(String classeDC, String nomeHdr) {
		DocumentoDataCollector boDC = (DocumentoDataCollector)Factory.createObject(classeDC);
		boDC.initialize(nomeHdr);
		return boDC;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List recuperaControllaTestateUds(String[] elencoUds) throws SQLException{
		List uds = new ArrayList();
		for (int i = 0; i < elencoUds.length; i++) {
			TestataUds tu = (TestataUds) TestataUds.elementWithKey(TestataUds.class, elencoUds[i], PersistentObject.NO_LOCK);
			if(tu != null) {
				if(!tu.getCodiceTestataLista().equals(getCodiceTestataLista()))
					throw new ThipException(new ErrorMessage("BAS0000078","L'UDS selezionata appartiene alla lista: "+tu.getCodiceTestataLista()+" e non alla lista: "+getCodiceTestataLista()));
				else
					uds.add(tu);
			}else {
				throw new ThipException(new ErrorMessage("BAS0000004",elencoUds[i]));
			}
		}
		return uds;
	}
}