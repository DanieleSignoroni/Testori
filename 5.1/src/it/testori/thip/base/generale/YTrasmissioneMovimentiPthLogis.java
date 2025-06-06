package it.testori.thip.base.generale;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;

import it.testori.thip.magazzino.generalemag.CreaLottiTestoriUtils;
import it.thera.thip.acquisti.documentoAC.DocumentoAcquisto;
import it.thera.thip.base.generale.CfgLogTxMov;
import it.thera.thip.base.generale.MovimentoThipLogis;
import it.thera.thip.base.generale.TrasmissioneMovimentiPthLogis;
import it.thera.thip.logis.bas.ParametriLogis;
import it.thera.thip.logis.fis.AnagraficaUdc;
import it.thera.thip.magazzino.movimenti.MovimentoMagazzino;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 06/06/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71XXX    06/06/2025  DSSOF3   Prima stesura
 */

public class YTrasmissioneMovimentiPthLogis extends TrasmissioneMovimentiPthLogis {

	@Override
	protected MovimentoThipLogis setAttributiDaMovimento(MovimentoMagazzino movMagazzino, boolean delete,
			CfgLogTxMov cfgLogTxMov) {
		MovimentoThipLogis mtl = super.setAttributiDaMovimento(movMagazzino, delete, cfgLogTxMov);
		char tipoDoc = movMagazzino.getTipoDocumento().charAt(0);
		if(tipoDoc == MovimentoMagazzino.AREA_ACQUISTO) {
			String key = KeyHelper.buildObjectKey(new String[] {movMagazzino.getCodiceAzienda(), 
					movMagazzino.getCodiceAnnoFiscale(), 
					movMagazzino.getNumeroDocumento()});
			DocumentoAcquisto docAcq = null;
			try {
				docAcq = DocumentoAcquisto.elementWithKey(key,PersistentObject.NO_LOCK);
				if(docAcq != null) {
					//.Se l'articolo e' gestito a lotti della gestione di testori
					if(CreaLottiTestoriUtils.isArticoloGestioneLottiTestori(movMagazzino.getArticolo(),
							docAcq.getCausale().isLavEsterna() ? CreaLottiTestoriUtils.CONTO_LAVORO : CreaLottiTestoriUtils.ACQUISTO)) {
						AnagraficaUdc anagUdc = (AnagraficaUdc) Factory.createObject(AnagraficaUdc.class);
						anagUdc.setKey(movMagazzino.getCodiceLotto());
						anagUdc.retrieve();
						//.Se l'anagrafica UDC non e' presente a db la creo
						if(!anagUdc.isOnDB()) {
							anagUdc.setDescrizioneBr(movMagazzino.getCodiceLotto());
							String codTipoUdc = ParametriLogis.valoreDiSezioneChiave("YENT_LOTTI_TESTORI", "COD_TIPO_UDC");
							anagUdc.setCodiceTipoUdc(codTipoUdc);
							anagUdc.save();
						}
						mtl.setCodiceAnagraficaUdc(movMagazzino.getCodiceLotto());
					}
				}
			} catch (Exception e) {
				e.printStackTrace(Trace.excStream);
			}  
		}
		return mtl;
	}
}
