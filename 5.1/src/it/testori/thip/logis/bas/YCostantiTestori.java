package it.testori.thip.logis.bas;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.ad.ClassADCollectionManager;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;

import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.documenti.web.DocumentoDataCollector;
import it.thera.thip.base.generale.ParametroPsn;
import it.thera.thip.base.generale.UnitaMisura;
import it.thera.thip.cs.ThipException;
import it.thera.thip.logis.lgb.TestataLista;
import it.thera.thip.vendite.documentoVE.DocumentoVendita;

/**
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
 */

public class YCostantiTestori {

	public static String XML = "it/testori/thip/logis/rf/gui/xml/Testori.xml";

	/**
	 * Ritorna l'xml dove sono presenti le form personalizzate.<br>
	 * Se la user.dir e' sviluppo allora do precedenza al file in sviluppo, altrimenti prendo quello del progetto o di D:\.<br>
	 * @return
	 */
	public static String searchXml(){
		String userDir = System.getProperty("user.dir");
		if(userDir != null && userDir.startsWith("W:\\")) {
			File xmlSvil = new File(userDir + "\\lib\\"+XML.replace("//", "\\"));
			if(xmlSvil.exists()) {
				return xmlSvil.toURI().toString();
			}
		}
		String defaultXml = XML;
		URL url = YCostantiTestori.class.getClassLoader().getResource(defaultXml);
		if(url != null){
			return url.toString();
		}
		return XML;
	}

	public static DocumentoVendita documentoVenditaTestataLista(TestataLista testataLista) {
		DocumentoVendita dv = (DocumentoVendita) Factory.createObject(DocumentoVendita.class);
		if (testataLista.getCodice().length() > 5) {
			dv.setAnnoDocumento(testataLista.getCodice().substring(1, 5));
			dv.setNumeroDocumento(testataLista.getCodice().substring(5));
		}
		else
			dv.setAnnoDocumento(testataLista.getCodice());
		boolean res = false;
		try {
			res = dv.retrieve(PersistentObject.NO_LOCK);
		}
		catch (SQLException ex) {
			ex.printStackTrace(Trace.excStream);
		}
		if(!res) {
			dv = null;
		}
		return dv;
	}

	public static Articolo getArticoloThip(it.thera.thip.logis.prd.Articolo artLogis) {
		if(artLogis == null)
			return null;
		else {
			try {
				return (Articolo) Articolo.elementWithKey(Articolo.class,KeyHelper.buildObjectKey(new String[] {
						artLogis.getCodiceGruppo(),artLogis.getCodice()
				}), PersistentObject.NO_LOCK);
			} catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return null;
	}

	public static boolean unitaMisuraVenditaRientraGestionePezze(Articolo articolo) {
		if(articolo == null)
			return false;
		else {
			UnitaMisura um = getUnitaMisuraGestionePezze();
			if(um != null && um.getIdUnitaMisura().equals(articolo.getArticoloDatiVendita().getUMDefaultVen().getIdUnitaMisura())) {
				return true;
			}
		}
		return false;
	}

	public static UnitaMisura getUnitaMisuraGestionePezze() {
		String idUnitaMisura = getIdUnitaMisuraGestionePezze();
		if(idUnitaMisura != null) {
			try {
				return (UnitaMisura) UnitaMisura.elementWithKey(UnitaMisura.class, KeyHelper.buildObjectKey(new String[] {
						Azienda.getAziendaCorrente(),
						idUnitaMisura
				}), PersistentObject.NO_LOCK);
			} catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return null;
	}

	private static String getIdUnitaMisuraGestionePezze() {
		return ParametroPsn.getValoreParametroPsn("GestionePezzeTestori", "UnitaMisuraVendita");
	}

	public static BODataCollector createDataCollector(String str) throws SQLException{
		try {
			BODataCollector bodc = null;
			ClassADCollection classDesc = ClassADCollectionManager.collectionWithName(str);
			String collectorName = classDesc.getBODataCollector();
			if (collectorName != null) {
				bodc = (BODataCollector)Factory.createObject(collectorName);
				if (bodc instanceof DocumentoDataCollector){
					((DocumentoDataCollector)bodc).setDisabilitaControlliRelazione(true);
				}
				bodc.initialize(classDesc.getClassName(), true);
			}
			else
				bodc = new BODataCollector(classDesc.getClassName(), true);

			return bodc;
		}
		catch (Exception e){
			e.printStackTrace(Trace.excStream);
			throw new ThipException(e.getMessage());
		}
	}
}