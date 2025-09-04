package it.testori.thip.produzione.rifornLinea;

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
 * Date: 04/09/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    04/09/2025  DSSOF3   Prima stesura
 */

public class YPsnDatiRifornimentoLinea extends YPsnDatiRifornimentoLineaPO implements Cacheable{

	public ErrorMessage checkDelete() {
		return null;
	}

	@SuppressWarnings("rawtypes")
	protected static Hashtable iHistory_YPsnDatiRifornimentoLinea = new Hashtable();

	public static YPsnDatiRifornimentoLinea getCurrentYPsnDatiRifornimentoLinea()
	{
		return getYPsnDatiRifornimentoLinea(Azienda.getAziendaCorrente());
	}

	@SuppressWarnings("unchecked")
	public static YPsnDatiRifornimentoLinea getYPsnDatiRifornimentoLinea(String iIdAzienda)
	{
		if (iIdAzienda == null)
			return null;

		YPsnDatiRifornimentoLinea iYPsnDatiRifornimentoLinea = null;

		//fix 5298 inizio
		try
		{
			if(PersistentObjectCache.isEnabled())
			{
				return (YPsnDatiRifornimentoLinea)PersistentObject.readOnlyElementWithKey(YPsnDatiRifornimentoLinea.class, iIdAzienda);
			}
			else
			{
				if(iHistory_YPsnDatiRifornimentoLinea.containsKey(iIdAzienda))
					return (YPsnDatiRifornimentoLinea)iHistory_YPsnDatiRifornimentoLinea.get(iIdAzienda);
				else
				{
					iYPsnDatiRifornimentoLinea=YPsnDatiRifornimentoLinea.elementWithKey(iIdAzienda, PersistentObject.OPTIMISTIC_LOCK);
					if(iYPsnDatiRifornimentoLinea != null)
						iHistory_YPsnDatiRifornimentoLinea.put(iIdAzienda,iYPsnDatiRifornimentoLinea);
				}
			}
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		return iYPsnDatiRifornimentoLinea;
	}

	@SuppressWarnings("unchecked")
	public int saveOwnedObjects(int rc) throws SQLException
	{
		rc += super.saveOwnedObjects(rc);

		if(rc >= 0)
			iHistory_YPsnDatiRifornimentoLinea.put(this.getIdAzienda(),this);

		return rc;
	}


}
