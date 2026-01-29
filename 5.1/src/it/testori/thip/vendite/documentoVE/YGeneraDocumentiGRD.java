package it.testori.thip.vendite.documentoVE;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.batch.BatchJob;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.persist.Factory;

import it.testori.thip.vendite.ordineVE.YAllegatiOrdVenRigPrm;
import it.testori.thip.vendite.ordineVE.YOrdineVenditaRigaPrm;
import it.thera.thip.base.documentoDgt.AllegatoSSD;
import it.thera.thip.base.documentoDgt.DocumentoDgtOggetto;
import it.thera.thip.base.documentoDgt.DocumentoDigitale;
import it.thera.thip.base.documentoDgt.DocumentoSSD;
import it.thera.thip.base.generale.PersDatiGen;
import it.thera.thip.vendite.documentoVE.DocumentoVenRigaPrm;
import it.thera.thip.vendite.documentoVE.DocumentoVendita;
import it.thera.thip.vendite.documentoVE.DocumentoVenditaTM;
import it.thera.thip.vendite.documentoVE.GeneraDocumentiGRD;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 29/01/2026
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    29/01/2026  DSSOF3   Prima stesura
 */

public class YGeneraDocumentiGRD extends GeneraDocumentiGRD {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public HashMap aggiungiAllegati(DocumentoSSD dSSD) {
		HashMap mapOggetti = super.aggiungiAllegati(dSSD);
		if(mapOggetti == null)
			mapOggetti = new HashMap();
		if(getAvailableReport() != null && getAvailableReport().getBatchJob() != null) {
			BatchJob job = getAvailableReport().getBatchJob();
			if(job.getRunnerClassName().equals("it.testori.thip.vendite.documentoVE.YReportDdtBollaBatch")) {
				BODataCollector boDC = (BODataCollector) Factory.createObject(BODataCollector.class);
				boDC.initialize("YReportDdtBollaBatch");
				boDC.streamToObject(boDC.getBo(), job.getParameter());
				YReportDdtBollaBatch bo = (YReportDdtBollaBatch) boDC.getBo();
				if(bo.isAllegatiDigitaliCom()) {
					List documentiVenList = new ArrayList();
					List righe = new ArrayList();
					String idAzienda = dSSD.getIdAzienda();
					String annoBolla = dSSD.getIdAnnoDocumento();
					String numeroBolla = dSSD.getIdNumeroDocumento();
					if (idAzienda != null && annoBolla != null && numeroBolla != null) {
						try {
							String where = DocumentoVenditaTM.ID_AZIENDA + " = '" + idAzienda + "' AND "
									+ DocumentoVenditaTM.ANNO_BOLLA + " = '" + annoBolla + "' AND "
									+ DocumentoVenditaTM.NUMERO_BOLLA + " = '" + numeroBolla + "'";
							documentiVenList = DocumentoVendita.retrieveList(where, "", true);
							if (!documentiVenList.isEmpty()) {
								Iterator documentiVendita = documentiVenList.iterator();
								while (documentiVendita.hasNext()) {
									DocumentoVendita docVen = (DocumentoVendita) documentiVendita.next();
									Iterator it = docVen.getRighe().iterator();
									while (it.hasNext()) {
										DocumentoVenRigaPrm docVenRig = (DocumentoVenRigaPrm) it.next();
										if(docVenRig.getRigaOrdine() != null) {
											righe.add(docVenRig);
										}
									}
								}
								mapOggetti = recuperaAllegatiCommessa(righe, dSSD);
							}
						} catch (Exception e) {
							e.printStackTrace(Trace.excStream);
						}
					}
				}
			}
		}
		return mapOggetti;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected HashMap recuperaAllegatiCommessa(List righe, DocumentoSSD dSSD) {
		HashMap mapAllegati = new HashMap();
		int sequenza = 1;
		for (Iterator iterator = righe.iterator(); iterator.hasNext();) {
			DocumentoVenRigaPrm docVenRig = (DocumentoVenRigaPrm) iterator.next();
			if(docVenRig.getRigaOrdine() != null) {
				YOrdineVenditaRigaPrm riga = (YOrdineVenditaRigaPrm) docVenRig.getRigaOrdine();

				Iterator iterAllegati = riga.getYAllegati().iterator();
				while(iterAllegati.hasNext()) {
					YAllegatiOrdVenRigPrm allegatoOvr = (YAllegatiOrdVenRigPrm) iterAllegati.next();
					if(allegatoOvr.getIdTipoDocDgt().startsWith("COM")) {
						Vector docDgts = allegatoOvr.getDocumentiDigitaliCollegato();
						if(docDgts != null && docDgts.size() > 0) {
							Iterator itD = docDgts.iterator();
							while (itD.hasNext()) {
								DocumentoDigitale docDgt = (DocumentoDigitale) itD.next();
								if (!docDgt.getOggetti().isEmpty()) {
									Iterator itg = docDgt.getOggetti().iterator();
									while (itg.hasNext()) {
										DocumentoDgtOggetto docDgtOggetto = (DocumentoDgtOggetto) itg.next();
										if(!isEsisteallegatiAgg(dSSD.getAttachmentColl(),docDgtOggetto.getFilename())) {//Fix 32917 ini
											AllegatoSSD allegatoSSD = createAllegatoSSD(docDgtOggetto, dSSD);
											allegatoSSD.setSequence((short) sequenza);
											sequenza++;
											try {
												int rc = allegatoSSD.save();
												if ((PersDatiGen.getCurrentPersDatiGen().getMemorizzazioneDocDgt() == PersDatiGen.MEM_DOC_DGT_SU_FILESYSTEM)) {
													File fileOgg = new File(docDgtOggetto.percorsoSalvataggioFile() + File.separator + docDgtOggetto.creaNomeFile());
													if(fileOgg.exists()){
														allegatoSSD.percorsoSalvataggioFile(dSSD);
														salvaSuFileSystem(fileOgg, allegatoSSD);
													}
												} 
												else {
													allegatoSSD.doAttach();
												}

												if (rc > 0) {
													dSSD.getAttachmentColl().add(allegatoSSD);
													allegatoSSD.setOnDB(true);
													mapAllegati.put(allegatoSSD.getKey(), allegatoSSD);
												}
											} catch (Exception e) {
												e.printStackTrace(Trace.excStream);
											}
										}
									}
								}
							}
						}
					}

				}
			}

		}
		return mapAllegati;
	}
}