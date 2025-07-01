package it.testori.thip.base.codificatore.web;

import java.util.Iterator;
import java.util.List;

import com.thera.thermfw.persist.KeyHelper;

import it.testori.thip.base.codificatore.YVariabileSchemaCodifica;
import it.thera.thip.base.codificatore.Codificatore;
import it.thera.thip.base.codificatore.VariabileSchemaCodifica;
import it.thera.thip.base.codificatore.web.CodificatoreModifier;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 01/07/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71XXX    01/07/2025  DSSOF3   Prima stesura
 */

public class YCodificatoreModifier extends CodificatoreModifier {

	public YCodificatoreModifier() {
		super();
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected String getVariabiliResources() {
		String str = super.getVariabiliResources();
		Codificatore codif = (Codificatore)getBODataCollector().getBo();
		List variabiliList = codif.getVariabiliCodifica();
		Iterator iterVariabili = variabiliList.iterator();
		while(iterVariabili.hasNext()){
			VariabileSchemaCodifica variabileNode = (VariabileSchemaCodifica)iterVariabili.next();
			String variabileNodeKey = variabileNode.getKey();
			String variabileNodeDesc = variabileNode.getDescrizioneUtente();  //...FIX03161 - DZ
			char variabileStato = variabileNode.getDatiComuniEstesi().getStato(); //...FIX02937 - DZ
			if(variabileNode instanceof YVariabileSchemaCodifica && ((YVariabileSchemaCodifica)variabileNode).getRicercaConRange()) {
				StringBuilder sb = new StringBuilder();
				String valoreNodeKey = variabileNode.getKey() + KeyHelper.KEY_SEPARATOR + "Range";
				String valoreNodeDesc = "Range";
				String descrizioneVariabile = "Range";
				String descrizioneValore = valoreNodeDesc;
				String valore = "";
				String limiteInfriore = null;
				String limiteSuperiore = null;
				String idVariabile = variabileNode.getIdVariabileCodif();
				String idValore = "Range";
				String dimensione = (variabileNode.getDimVariabileTipizzata() == null) ? "0" : variabileNode.getDimVariabileTipizzata().toString();
				String numDecimali = (variabileNode.getNumDecimaliTipizzata() == null) ? "0" : variabileNode.getNumDecimaliTipizzata().toString();
				String tipo = String.valueOf(variabileNode.getTipoVariabileTipizzata());
				boolean rangeTipizzata = variabileNode.isRangeTipizzata();
				if (rangeTipizzata)
					valore = "";
				sb.append("  var elementArray=new Array();\n")
				.append("  elementArray[0] = ").append(getQuotedString(variabileNodeKey)).append(";\n")
				.append("  elementArray[1] = ").append(getQuotedString(variabileNodeDesc)).append(";\n")
				.append("  elementArray[2] = ").append(getQuotedString(valoreNodeKey)).append(";\n")
				.append("  elementArray[3] = ").append(getQuotedString(valoreNodeDesc)).append(";\n")
				.append("  elementArray[4] = ").append(getQuotedString("" + variabileStato)).append(";\n")  //...FIX02937 - DZ
				//Fix 05099 Mz inizio
				.append("  elementArray[5] = ").append(getQuotedString(descrizioneVariabile)).append(";\n")
				.append("  elementArray[6] = ").append(getQuotedString(descrizioneValore)).append(";\n")
				.append("  elementArray[7] = ").append(getQuotedString(valore)).append(";\n")
				.append("  elementArray[8] = ").append(getQuotedString(limiteInfriore)).append(";\n")
				.append("  elementArray[9] = ").append(getQuotedString(limiteSuperiore)).append(";\n")
				.append("  elementArray[10] = ").append(getQuotedString(idVariabile)).append(";\n")
				.append("  elementArray[11] = ").append(getQuotedString(idValore)).append(";\n")
				.append("  elementArray[12] = ").append(getQuotedString("20")).append(";\n")
				.append("  elementArray[13] = ").append(getQuotedString(numDecimali)).append(";\n")
				.append("  elementArray[14] = ").append(getQuotedString(tipo)).append(";\n")
				.append("  elementArray[15] = ").append(rangeTipizzata).append(";\n")
				//Fix 05099 Mz fine
				.append("  leafAttribute[\"").append(valoreNodeKey).append("\"] = elementArray;\n");

				String lastPart = sb.toString();
				str += lastPart;
			}
		}
		return str;
	}

}
