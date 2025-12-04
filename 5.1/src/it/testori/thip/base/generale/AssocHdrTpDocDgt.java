package it.testori.thip.base.generale;

import java.sql.SQLException;
import java.util.Vector;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.Column;

import it.thera.thip.base.azienda.Azienda;

public class AssocHdrTpDocDgt extends AssocHdrTpDocDgtPO {

	public ErrorMessage checkDelete() {
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static Vector recuperaAssociazioniDefault(String className, boolean def) {
		String where = " "+AssocHdrTpDocDgtTM.ID_AZIENDA+" = '"+Azienda.getAziendaCorrente()+"' AND "+AssocHdrTpDocDgtTM.R_CLASS_HDR+" = '"+className+"' ";
		where += " AND "+AssocHdrTpDocDgtTM.DEF_COMPILATION+" = '"+(def ? Column.TRUE_CHAR : Column.FALSE_CHAR)+"' ";
		try {
			return retrieveList(where, "", false);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return new Vector();
	}

}
