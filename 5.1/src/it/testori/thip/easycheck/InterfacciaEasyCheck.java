package it.testori.thip.easycheck;

import java.sql.SQLException;
import java.util.Hashtable;

import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.Cacheable;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.persist.PersistentObjectCache;

import it.thera.thip.base.azienda.Azienda;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 01/12/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72236    01/12/2025  DSSOF3   Prima stesura
 */

public class InterfacciaEasyCheck extends InterfacciaEasyCheckPO implements Cacheable {

	protected static Hashtable<String, InterfacciaEasyCheck> iHistory_InterfacciaEasyCheck = new Hashtable<String, InterfacciaEasyCheck>();

	public static InterfacciaEasyCheck getCurrentInterfacciaEasyCheck(){
		return getInterfacciaEasyCheck(Azienda.getAziendaCorrente());
	}

	public static InterfacciaEasyCheck getInterfacciaEasyCheck(String iIdAzienda){
		if (iIdAzienda == null)
			return null;

		InterfacciaEasyCheck iInterfacciaEasyCheck = null;

		try
		{
			if(PersistentObjectCache.isEnabled())
			{
				return (InterfacciaEasyCheck)PersistentObject.readOnlyElementWithKey(InterfacciaEasyCheck.class, iIdAzienda);
			}
			else
			{
				if(iHistory_InterfacciaEasyCheck.containsKey(iIdAzienda))
					return (InterfacciaEasyCheck)iHistory_InterfacciaEasyCheck.get(iIdAzienda);
				else
				{
					iInterfacciaEasyCheck=InterfacciaEasyCheck.elementWithKey(iIdAzienda, PersistentObject.OPTIMISTIC_LOCK);
					if(iInterfacciaEasyCheck != null)
						iHistory_InterfacciaEasyCheck.put(iIdAzienda,iInterfacciaEasyCheck);
				}
			}
		}catch (SQLException ex){
			ex.printStackTrace();
		}
		return iInterfacciaEasyCheck;
	}

	public int saveOwnedObjects(int rc) throws SQLException{
		rc += super.saveOwnedObjects(rc);

		if(rc >= 0)
			iHistory_InterfacciaEasyCheck.put(this.getIdAzienda(),this);

		return rc;
	}

	public ErrorMessage checkDelete() {
		return null;
	}

}
