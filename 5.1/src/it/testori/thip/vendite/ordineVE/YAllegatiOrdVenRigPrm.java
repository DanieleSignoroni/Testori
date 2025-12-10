package it.testori.thip.vendite.ordineVE;

import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.PersistentObject;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 10/12/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72247    10/12/2025  DSSOF3   Prima stesura
 */

public class YAllegatiOrdVenRigPrm extends YAllegatiOrdVenRigPrmPO {

	public ErrorMessage checkDelete() {

		return null;
	}

	public String getFatherKey() {
		return getOrdinevenditarigaprmKey();
	}

	public void setFatherKey(String key) {
		setOrdinevenditarigaprmKey(key);
	}

	public void setFather(PersistentObject father) {
		iOrdinevenditarigaprm.setObject(father);
	}

	public String getOrderByClause() {
		return "";
	}

}
