package it.testori.thip.vendite.documentoVE;

import java.util.Iterator;
import java.util.Vector;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.BaseComponentsCollection;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.PersistentObject;

import it.testori.thip.base.cliente.YModalitaConsegna;
import it.thera.thip.base.comuniVenAcq.ReportDdtBollaTestata;
import it.thera.thip.base.comuniVenAcq.TipoRiga;
import it.thera.thip.base.generale.ParametroPsn;
import it.thera.thip.vendite.documentoVE.DocumentoVenRigaPrm;
import it.thera.thip.vendite.documentoVE.DocumentoVendita;
import it.thera.thip.vendite.documentoVE.ReportDdtBollaBatch;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 11/11/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72214    11/11/2025  DSSOF3   Prima stesura
 */

public class YReportDdtBollaBatch extends ReportDdtBollaBatch {

	protected boolean iAllegatiDigitaliCom;

	public boolean isAllegatiDigitaliCom() {
		return iAllegatiDigitaliCom;
	}

	public void setAllegatiDigitaliCom(boolean iAllegatiDigitaliCom) {
		this.iAllegatiDigitaliCom = iAllegatiDigitaliCom;
	}

	public YReportDdtBollaBatch() {
		super();
		setAllegatiDigitaliCom(false);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Vector checkAll(BaseComponentsCollection bcc) {
		Vector errors = super.checkAll(bcc);
		ErrorMessage error = null;
		error = checkAddebitoSpese();
		if (error != null)
			errors.add(error);
		return errors;
	}

	@SuppressWarnings("rawtypes")
	protected ErrorMessage checkAddebitoSpese() {
		if(getAzione() != ReportDdtBollaTestata.STAMPA) return null;
		if(getCausaleAddebitoSpeseMandatory() == null) return null;
		if (getNumeroDocumento() != null && !getNumeroDocumento().equals("") && !getNumeroDocumento().equals("null")) {
			try {
				DocumentoVendita docVen = DocumentoVendita.elementWithKey(getNumeroDocumento(), PersistentObject.NO_LOCK);
				if(docVen.getModalitaConsegna() == null) return null;
				if(!((YModalitaConsegna)docVen.getModalitaConsegna()).getAddebitoSpese()) return null;
				Iterator iterRighe = docVen.getRighe().iterator();
				boolean presenteAddebito = false;
				while(iterRighe.hasNext()) {
					DocumentoVenRigaPrm riga = (DocumentoVenRigaPrm) iterRighe.next();
					if(riga.getTipoRiga() != TipoRiga.MERCE && riga.getIdCauRig().equals(getCausaleAddebitoSpeseMandatory())) {
						presenteAddebito = true;
					}
				}
				if(!presenteAddebito) {
					return new ErrorMessage("BAS0000089","La modalita' di consegna richiede delle spese non presenti nel documento");
				}
			}
			catch (Exception ex) {
				ex.printStackTrace(Trace.excStream);
			}
		}
		return null;
	}

	public static String getCausaleAddebitoSpeseMandatory() {
		return ParametroPsn.getValoreParametroPsn("pers.vendite.documentoVE.ReportDdtBollaBatch", "CausaleAddebitoSpese");
	}
}