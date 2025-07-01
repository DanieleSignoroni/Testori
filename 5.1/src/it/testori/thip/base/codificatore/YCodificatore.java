package it.testori.thip.base.codificatore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.CachedStatement;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;

import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.codificatore.Codificatore;
import it.thera.thip.base.codificatore.ValoreVariabileCodifica;
import it.thera.thip.base.codificatore.VariabileSchemaCodifica;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 16/04/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71934    16/04/2025  DSSOF3   Prima stesura
 */

public class YCodificatore extends Codificatore {

	public static final String STMT_SELECT_MAX_VALUES_IN_N_POSITION = "SELECT "
			+ "	ISNULL(MAX(CAST(SUBSTRING(ID_ARTICOLO, ?, ?) AS INT)), 0) + 1 AS NEXT_PROGR "
			+ "FROM "
			+ "	THIP.ARTICOLI "
			+ "WHERE "
			+ "	SUBSTRING(ID_ARTICOLO, 1, ?) = ? "
			+ "	AND SUBSTRING(ID_ARTICOLO, ?, ?) NOT LIKE '%[^0-9]%' "
			+ "	AND ID_AZIENDA = ? ";
	public static CachedStatement cSelezionaProgressivoTestori = new CachedStatement(STMT_SELECT_MAX_VALUES_IN_N_POSITION);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int save(boolean force) throws SQLException {
		int rc = super.save(force);

		//.Propongo il codice di Testori
		if(getSchemaCodifica() instanceof YSchemaCodifica && ((YSchemaCodifica)getSchemaCodifica()).getPosizionePartenza() != null) {
			Set variabili = iVariabili.keySet();
			List lstDesc=new ArrayList();
			lstDesc.addAll(variabili);
			Collections.sort(lstDesc, new ComparatorSeqDescr());
			String templateCodice = getSchemaCodifica().getTemplCodice();
			for(Iterator i = lstDesc.iterator(); i.hasNext(); ) {
				VariabileSchemaCodifica variabile = (VariabileSchemaCodifica)i.next();
				ValoreVariabileCodifica valore = (ValoreVariabileCodifica)iVariabili.get(variabile);
				if(valore != null) {
					templateCodice = getTemplateCodice(templateCodice, variabile, valore);
				}
			}
			if(templateCodice != null) {
				//				if(getSchemaCodifica().getNumCifreNumeratore() != null && templateCodice != null)
				//					templateCodice += getNumeratore(templateCodice);
				//				if(templateCodice == null)
				//					templateCodice = getSchemaCodifica().getTemplCodice();

				String nuovoCodice = null;

				//.Radice uguale al codice template tagliato fino al carattere prima della posizione di partenza del progressivo numerico
				String radice = templateCodice.substring(0,((YSchemaCodifica)getSchemaCodifica()).getPosizionePartenza()-1);

				//.Il progressivo sarebbe la parte numerica tra (posizione di partenza fino a (posizione di partenza + cifre numeratore)), della radice sopra estratta
				String progressivo = leggiProgressivoTestoriCodificatore(((YSchemaCodifica)getSchemaCodifica()).getPosizionePartenza(), getSchemaCodifica().getNumCifreNumeratore(), radice);

				//.Completo il progressivo con eventuali zeri fino a creare una stringa lunga N, dove N e' (cifre numeratore)
				progressivo = completaConZeri(progressivo, getSchemaCodifica().getNumCifreNumeratore());
				if(progressivo != null) {

					//.Il codice finale e' composto da radice + progressivo + parte restante calcolata da panthera
					nuovoCodice = radice + progressivo + (templateCodice.substring((radice+progressivo).length(),templateCodice.length()));
					setCodiceTeorico(nuovoCodice);
					iOriginalCodTeorico = nuovoCodice == null ? getIdArticolo() : nuovoCodice;
				}
			}
		}
		return rc;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void ricerca() {
		//.Se contiene la ricerca con range...
		if(iSintesiCodif.contains("Range")) {
			String[] blocchi = iSintesiCodif.split("\\|");
			StringBuilder cleaned = new StringBuilder();

			String bloccoRange = null;
			for (String blocco : blocchi) {
				if (!blocco.contains("Range")) {
					if (cleaned.length() > 0) {
						cleaned.append("|");
					}
					cleaned.append(blocco);
				}else {
					bloccoRange = blocco;
				}
			}
			cleaned.append("|");
			String nuovaSintesi = cleaned.toString();
			iSintesiCodif = nuovaSintesi;

			String chiaveSchemaVariabile = bloccoRange.substring(0,bloccoRange.indexOf("?"));
			String rangeStr = bloccoRange.substring(bloccoRange.indexOf("?")+1,bloccoRange.length());
			String[] rangeValori = KeyHelper.unpackObjectKey(rangeStr);

			if(!iSintesiCodif.isEmpty())
				super.ricerca();

			try {
				YVariabileSchemaCodifica varRange = (YVariabileSchemaCodifica) 
						YVariabileSchemaCodifica.elementWithKey(YVariabileSchemaCodifica.class, chiaveSchemaVariabile, PersistentObject.NO_LOCK);
				if(varRange != null) {
					Short posiz = varRange.getPosizInCod();
					Short lunghezza = varRange.getLunghezzaCod();

					String rangeDa = rangeValori[0];
					String rangeA = rangeValori[1];

					// Clona la lista per evitare ConcurrentModificationException
					Iterator iterator = iRisultatoRicerca.iterator();
					while (iterator.hasNext()) {
						Articolo articolo = (Articolo) iterator.next();
						String idArticolo = articolo.getIdArticolo();

						// Calcolo la sottostringa alla posizione indicata
						int pos = posiz.intValue() - 1;
						int len = lunghezza.intValue();

						// Sicurezza: controllo che la stringa sia abbastanza lunga
						if (idArticolo.length() >= (pos + len)) {
							String sottoStringa = idArticolo.substring(pos, pos + len);

							// Confronto (se sono numeri, puoi fare il parse)
							if (sottoStringa.compareTo(rangeDa) < 0 || sottoStringa.compareTo(rangeA) > 0) {
								// Fuori dal range => rimuovo
								iterator.remove();
							}
						} else {
							// id troppo corto => rimuovo
							iterator.remove();
						}
					}

				}
			} catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}

		}else {
			super.ricerca();
		}
	}

	public static synchronized String leggiProgressivoTestoriCodificatore(int posizionePartenza, int cifreNumeratore, String radice){
		ResultSet rs = null;
		try{
			PreparedStatement ps = cSelezionaProgressivoTestori.getStatement();
			ps.setInt(1, posizionePartenza);
			ps.setInt(2, cifreNumeratore);
			ps.setInt(3, posizionePartenza-1); //.Taglio fino a 
			ps.setString(4, radice);
			ps.setInt(5, posizionePartenza);
			ps.setInt(6, cifreNumeratore);
			ps.setString(7, Azienda.getAziendaCorrente());

			rs = ps.executeQuery();
			if (rs.next()){
				return rs.getString(1);
			}
		}
		catch (SQLException e){
			e.printStackTrace(Trace.excStream);
		}
		finally{
			try{
				if (rs != null)
					rs.close();
			}
			catch(SQLException e){
				e.printStackTrace(Trace.excStream);
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	class ComparatorSeqDescr implements Comparator {

		public int compare(Object first, Object second) {

			VariabileSchemaCodifica firstVar = (VariabileSchemaCodifica) first;
			VariabileSchemaCodifica secondVar= (VariabileSchemaCodifica) second;
			Short firstS = null;
			Short secondS = null;
			if(firstVar != null) firstS = firstVar.getSeqDescr();
			if(secondVar != null) secondS = secondVar.getSeqDescr();

			if(( firstS == null)&& (secondS == null))
				return 0;
			else if((firstS == null) && (secondS != null))
				return 1;
			else if((firstS!= null)&& (secondS == null))
				return -1;
			else
				return secondS.compareTo(firstS);

		}
	}
}
