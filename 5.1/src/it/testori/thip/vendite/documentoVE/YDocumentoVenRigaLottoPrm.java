package it.testori.thip.vendite.documentoVE;

import java.util.Vector;

import com.thera.thermfw.common.BaseComponentsCollection;
import com.thera.thermfw.common.ErrorMessage;

import it.testori.thip.base.cliente.YClienteVendita;
import it.testori.thip.logis.bas.YCostantiTestori;
import it.testori.thip.logis.lgb.YRigaLista;
import it.testori.thip.magazzino.generalemag.YLotto;
import it.thera.thip.base.generale.UnitaMisura;
import it.thera.thip.magazzino.generalemag.Lotto;
import it.thera.thip.vendite.documentoVE.DocumentoVenRigaLottoPrm;
import it.thera.thip.vendite.documentoVE.DocumentoVenRigaPrm;
import it.thera.thip.vendite.documentoVE.DocumentoVendita;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 20/10/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72178    20/10/2025  DSSOF3   Prima stesura
 * 72273	08/01/2025	DSSOF3	 In fase di chisura lista ometto l'errore della difettosita
 */

public class YDocumentoVenRigaLottoPrm extends DocumentoVenRigaLottoPrm {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = super.checkAll(components);
		ErrorMessage emGesPezze = controlloDifettositaLotto();
		if(emGesPezze != null) {
			errors.add(emGesPezze);
		}
		return errors;
	}

	protected ErrorMessage controlloDifettositaLotto() {
		//72273
		YDocumentoVendita dv = (YDocumentoVendita) getDocumentoRiga().getTestata();
		if(dv.isSalvataggioDaTtlLogis())
			return null;
		//72273
		ErrorMessage em = null;
		DocumentoVenRigaPrm dvr = (DocumentoVenRigaPrm) getDocumentoRiga();
		UnitaMisura umGesPezze = YCostantiTestori.getUnitaMisuraGestionePezze();
		if(!isOnDB() 
				&& getIdArticolo() != null 
				&& (getIdArticolo().startsWith("5") || getIdArticolo().startsWith("6"))
				&& (umGesPezze != null && umGesPezze.getIdUnitaMisura().equals(dvr.getIdUMRif()))
				&& (getIdLotto() != null && !getIdLotto().equals(Lotto.LOTTO_DUMMY))) {
			YClienteVendita cliente = (YClienteVendita) ((DocumentoVendita)(dvr.getTestata())).getCliente();
			String idMaxDif = null;
			if (getIdArticolo().startsWith("5"))
				idMaxDif = cliente != null ? cliente.getIdMaxDifAccTessuti() : null;
			else if(getIdArticolo().startsWith("6"))
				idMaxDif = cliente != null ? cliente.getIdMaxDifAccFeltri() : null;

			Integer defaultMax = 2;
			Integer maxAccettato = YRigaLista.parseIntOrNull(idMaxDif) != null
					? YRigaLista.parseIntOrNull(idMaxDif)
							: defaultMax;

			String difettositaLotto = ((YLotto) getLotto()).getIdDifettosita();

			if (!YRigaLista.isDifettositaAccettata(difettositaLotto, maxAccettato)) {
				em = new ErrorMessage("YT_0000005",new String[] {getIdLotto()});
			}
		}
		return em;
	}
}
