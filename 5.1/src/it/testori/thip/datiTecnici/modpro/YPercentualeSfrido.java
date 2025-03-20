package it.testori.thip.datiTecnici.modpro;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.*;
import com.thera.thermfw.persist.PersistentObject;

/**
 *
 * <p>Eventuale descrizione della classe</p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 13/03/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71868    07/03/2025  AGSOF3   Aggiunto campo solo ordini programmati
 */

public class YPercentualeSfrido extends YPercentualeSfridoPO {

	public ErrorMessage checkDelete() {
		return null;
	}

	public static YPercentualeSfrido percentualeSfridoAltezzaTaglio(BigDecimal altezzaTaglio) {
		if(altezzaTaglio != null) {
			try {
				return elementWithKey(altezzaTaglio.toString(), PersistentObject.NO_LOCK);
			} catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return null;
	}

}

