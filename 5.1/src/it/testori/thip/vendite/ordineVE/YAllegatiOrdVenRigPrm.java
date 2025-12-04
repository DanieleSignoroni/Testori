package it.testori.thip.vendite.ordineVE;

import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.PersistentObject;

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
