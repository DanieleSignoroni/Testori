package it.testori.thip.base.codificatore.web;

import java.util.Enumeration;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.ad.ClassCD;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.collector.CollectionDataCollector;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.web.WebNodeProperties;
import com.thera.thermfw.web.WebTreeNodeObject;

import it.testori.thip.base.codificatore.YVariabileSchemaCodifica;
import it.thera.thip.base.codificatore.ValoreVariabileCodifica;
import it.thera.thip.base.codificatore.web.CodificatoreWebTree;
import it.thera.thip.base.generale.ParametroPsn;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 14/04/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 71921    14/04/2025  DSSOF3   Prima stesura
 */

public class YCodificatoreWebTree extends CodificatoreWebTree {

	public YCodificatoreWebTree() {
		DEFAULT_LEFT_WIDTH = defaultLeftWidthParametroPers();
	}

	public String defaultLeftWidthParametroPers() {
		String def = "23%";
		String val = ParametroPsn.getValoreParametroPsn("pers.codificatore.tree", "LeftTreeWidth");
		if(val != null && !val.isEmpty()) {
			def = val;
		}
		return def;
	}

	@Override
	protected void loadFromBODC(BODataCollector nodeBODC, ClassADCollection nodeHdr, DefaultMutableTreeNode parentNode)
			throws Exception {
		if(nodeHdr.getClassName().contains("YVariabileSchemaCodifica") && ((isReloading && reloadTree) || !isReloading)) {
			persLoadLoadFromBODC(nodeBODC, nodeHdr, parentNode);
		}else {
			super.loadFromBODC(nodeBODC, nodeHdr, parentNode);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void persLoadLoadFromBODC(BODataCollector nodeBODC, ClassADCollection nodeHdr,
			DefaultMutableTreeNode parentNode) throws Exception {
		ClassCD nodeClassCD = null;
		CollectionDataCollector parentCollectionDC = nodeBODC.getOwnerCollectionDC();
		if (parentCollectionDC != null)
			nodeClassCD = parentCollectionDC.getClassCD();
		WebTreeNodeObject nodeObj = getTreeNodeObject(nodeBODC, nodeHdr, nodeClassCD, parentNode);
		DefaultMutableTreeNode node = root;
		if (parentNode == null)
			setRootWebTreeNodeObject(nodeObj);
		else
			node = addWebTreeNodeObject(parentNode, nodeObj);

		//WebNodeProperties np = (WebNodeProperties) nodePropertyList.get(nodeHdr.getClassName()); //5908 CB
		WebNodeProperties np = (WebNodeProperties) nodePropertyList.get(nodeHdr.getOriginalClassAdCollectionName()); //5908 CB
		for (int i = 0; i < np.numOfSon; i++) {
			ClassCD sonClassCD = nodeHdr.getComponent(np.getSonCDStrings()[i]);
			DefaultMutableTreeNode sonParentNode = addGroupTreeNodeObject(nodeObj, node, np, sonClassCD, false);
			CollectionDataCollector sonCollBODC = (CollectionDataCollector) nodeBODC.getSecondaryDataCollector(sonClassCD.getComponentName());
			///PTF01403 inizio righe aggiunte
			if (sonCollBODC == null) {
				nodeBODC.addCollectionDataCollector(sonClassCD.getComponentName());
				sonCollBODC = (CollectionDataCollector) nodeBODC.getSecondaryDataCollector(sonClassCD.getComponentName());
			}
			//fine righe aggiunte
			Enumeration rowEnum = sonCollBODC.getRowsDataCollectors(false); //PTF01403 riga modificata
			Vector newRows = new Vector();
			
			if(nodeBODC.getBo() instanceof YVariabileSchemaCodifica
					&& ((YVariabileSchemaCodifica)nodeBODC.getBo()).getRicercaConRange()) {
				//.Creo una riga con fake BODC
				BODataCollector bodc = (BODataCollector) Factory.createObject(BODataCollector.class);
				bodc.initialize("ValoreVariabileCodifica", true);
				ValoreVariabileCodifica bo = (ValoreVariabileCodifica) Factory.createObject(ValoreVariabileCodifica.class);
				bo.setFather(((YVariabileSchemaCodifica)nodeBODC.getBo()));
				bo.setValoreVariabile("Range");
				bo.getDescrizioneValVarCodifNLS().setDescrizione("Range");
				bodc.setBo(bo);
				bodc.setCheckKey(false);
				bodc.setOwnerCollectionDC(null);
				bodc.setPositionInOwnerCollectionDC(0);
				newRows.add(bodc);
			}

			Enumeration oldEnum = sonCollBODC.getRowsDataCollectors(false);
			while (oldEnum.hasMoreElements()) {
				newRows.add((BODataCollector) oldEnum.nextElement());
			}

			rowEnum = newRows.elements();

			if (rowEnum.hasMoreElements()) {
				int nodeLevel = getLoadNodeLevel(sonParentNode);
				//Fix 04969 MM inizio
				//if (nodeLevel <= preloadLevel) {
				//String nodeIdentifier = ((WebTreeNodeObject)sonParentNode.getUserObject()).getNodeIdentifier();
				String nodeIdentifier = getCompleteNodeIdentifier(sonParentNode);
				if (nodeLevel <= preloadLevel || openNodes.contains(nodeIdentifier)) {
					//Fix 04969 MM fine
					while (rowEnum.hasMoreElements()) {
						BODataCollector sonBODC = (BODataCollector) rowEnum.nextElement();
						loadFromBODC(sonBODC, sonClassCD.getComponentHDR(), sonParentNode);
					}
				}
				else {
					addLoadingNode(nodeHdr, sonParentNode);
				}
			}
		}
	}
}
