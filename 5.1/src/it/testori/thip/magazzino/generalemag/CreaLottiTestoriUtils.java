package it.testori.thip.magazzino.generalemag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.ConnectionManager;

import it.testori.thip.base.articolo.YArticoloDatiMagaz;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.magazzino.generalemag.LottoTM;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 04/06/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71XXX    04/06/2025  DSSOF3   Prima stesura
 */

public class CreaLottiTestoriUtils {

	public static final String CREA_LOTTI_NUOVO = "CREA_LOTTI_TESTORI_NUOVO";

	//Tipo di Lotto
	public static final char MANUFATTI = 'M';
	public static final char PEZZE = 'P';

	//Provenienza
	public static final char PRODUZIONE = 'P';
	public static final char CONTO_LAVORO = 'T';
	public static final char ACQUISTO = 'F';

	public static final String STMT_MAX_ID_LOTTO = "SELECT MAX(SUBSTRING(ID_LOTTO, 5, 15)) "
			+ "FROM "+LottoTM.TABLE_NAME+" "
			+ "WHERE "+LottoTM.ID_AZIENDA+" = ? "
			+ "  AND SUBSTRING("+LottoTM.ID_LOTTO+", 1, 4) = ? "
			+ "";

	public static String buildNextIdProgressivoLotto(char tipoCodificaLotto, char Provenienza) {
		String idProgressivo = null;
		try {
			switch (tipoCodificaLotto) {
			case YArticoloDatiMagaz.FELTRI_MANUFATTI:
				idProgressivo = getYearSuffix();
				idProgressivo += MANUFATTI;
				idProgressivo += Provenienza;
				idProgressivo += getMaxLottoId(Azienda.getAziendaCorrente(), idProgressivo, 5);
				break;
			case YArticoloDatiMagaz.PEZZE:
				break;
			case YArticoloDatiMagaz.SUBBI_FELTRI:
				idProgressivo = getYearSuffix();
				idProgressivo += PEZZE;
				idProgressivo += Provenienza;
				idProgressivo += getMaxLottoId(Azienda.getAziendaCorrente(), idProgressivo, 5);
				break;

			default:
				break;
			}
		}catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return idProgressivo;
	}

	public static String getMaxLottoId(String idAzienda, String prefisso, int lunghezzaSuffisso) throws SQLException {
		try (PreparedStatement stmt = ConnectionManager.getCurrentConnection().prepareStatement(STMT_MAX_ID_LOTTO)) {
			stmt.setString(1, idAzienda);
			stmt.setString(2, prefisso);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					String maxIdLotto = rs.getString(1).trim();
					if(maxIdLotto == null) {
						String suffissoFormattato = String.format("%0" + lunghezzaSuffisso + "d", 1);
						return suffissoFormattato;
					}else {
						int numero = 0;

						try {
							numero = Integer.parseInt(maxIdLotto)+1;
						} catch (NumberFormatException e) {
							numero = 0;
						}

						String suffissoFormattato = String.format("%0" + lunghezzaSuffisso + "d", numero);
						return suffissoFormattato;
					}
				}
			}
		}
		return null;
	}

	/**
	 * @return le due cifre significative dell'anno corrente
	 */
	public static String getYearSuffix() {
		int year = Year.now().getValue();
		return String.format("%02d", year % 100);
	}

	public static boolean isArticoloGestioneLottiTestori(Articolo articolo, char provenienza) {
		if(articolo == null)
			return false;
		switch (provenienza) {
		case ACQUISTO:
			if(articolo.getArticoloDatiMagaz().getCodAutLotAcq() == YArticoloDatiMagaz.FELTRI_MANUFATTI ||
			articolo.getArticoloDatiMagaz().getCodAutLotAcq() == YArticoloDatiMagaz.PEZZE || 
			articolo.getArticoloDatiMagaz().getCodAutLotAcq() == YArticoloDatiMagaz.SUBBI_FELTRI)
				return true;
			break;
		case CONTO_LAVORO:
			if(articolo.getArticoloDatiMagaz().getCodAutLotCl() == YArticoloDatiMagaz.FELTRI_MANUFATTI ||
			articolo.getArticoloDatiMagaz().getCodAutLotCl() == YArticoloDatiMagaz.PEZZE || 
			articolo.getArticoloDatiMagaz().getCodAutLotCl() == YArticoloDatiMagaz.SUBBI_FELTRI)
				return true;
			break;
		case PRODUZIONE:
			if(articolo.getArticoloDatiMagaz().getCodAutLotProd() == YArticoloDatiMagaz.FELTRI_MANUFATTI ||
			articolo.getArticoloDatiMagaz().getCodAutLotProd() == YArticoloDatiMagaz.PEZZE || 
			articolo.getArticoloDatiMagaz().getCodAutLotProd() == YArticoloDatiMagaz.SUBBI_FELTRI)
				return true;
			break;
		default:
			break;
		}
		return false;
	}
}
