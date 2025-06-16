<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///D:\3rdParty\Panthera4.6.0\websrc\dtd/xhtml1-transitional.dtd">
<!-- Fix 6285: eliminato campo nascosto IdConfigurazione introdotto con la fix 6002 -->
<html>
<!-- WIZGEN Therm 2.0.0 as Form riga indipendente - multiBrowserGen = true -->
<%=WebGenerator.writeRuntimeInfo()%>
<head>
<%@ page contentType="text/html; charset=Cp1252"%>
<%@ page import= " 
  java.sql.*, 
  java.util.*, 
  java.lang.reflect.*, 
  javax.naming.*, 
  com.thera.thermfw.common.*, 
  com.thera.thermfw.type.*, 
  com.thera.thermfw.web.*, 
  com.thera.thermfw.security.*, 
  com.thera.thermfw.base.*, 
  com.thera.thermfw.ad.*, 
  com.thera.thermfw.persist.*, 
  com.thera.thermfw.gui.cnr.*, 
  com.thera.thermfw.setting.*, 
  com.thera.thermfw.collector.*, 
  com.thera.thermfw.batch.web.*, 
  com.thera.thermfw.batch.*, 
  com.thera.thermfw.pref.* 
"%> 
<%
  ServletEnvironment se = (ServletEnvironment)Factory.createObject("com.thera.thermfw.web.ServletEnvironment"); 
  BODataCollector DocumentoPrdRigaVersamBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebFormForIndipendentRowForm DocumentoPrdRigaVersamForm =  
     new com.thera.thermfw.web.WebFormForIndipendentRowForm(request, response, "DocumentoPrdRigaVersamForm", "DocumentoPrdRigaVersam", "Arial,10", "it.thera.thip.produzione.documento.web.DocumentoPrdRigaVersamentoFAA", false, false, true, true, true, true, "it.thera.thip.produzione.documento.web.DocumentoPrdRigaVersamentoDataCollector", 1, true, "it/thera/thip/produzione/documento/DocumentoPrdRigaVersamento.js"); 
  DocumentoPrdRigaVersamForm.setServletEnvironment(se); 
  DocumentoPrdRigaVersamForm.setJSTypeList(jsList); 
  DocumentoPrdRigaVersamForm.setHeader(null); 
  DocumentoPrdRigaVersamForm.setFooter(null); 
  DocumentoPrdRigaVersamForm.setWebFormModifierClass("it.thera.thip.produzione.documento.web.DocumentoPrdRigaVersamentoModifier"); 
  DocumentoPrdRigaVersamForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = DocumentoPrdRigaVersamForm.getMode(); 
  String key = DocumentoPrdRigaVersamForm.getKey(); 
  String errorMessage; 
  boolean requestIsValid = false; 
  boolean leftIsKey = false; 
  boolean conflitPresent = false; 
  String leftClass = ""; 
  try 
  {
     se.initialize(request, response); 
     if(se.begin()) 
     { 
        DocumentoPrdRigaVersamForm.outTraceInfo(getClass().getName()); 
        String collectorName = DocumentoPrdRigaVersamForm.findBODataCollectorName(); 
	     DocumentoPrdRigaVersamBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (DocumentoPrdRigaVersamBODC instanceof WebDataCollector) 
            ((WebDataCollector)DocumentoPrdRigaVersamBODC).setServletEnvironment(se); 
        DocumentoPrdRigaVersamBODC.initialize("DocumentoPrdRigaVersam", true, 1); 
        DocumentoPrdRigaVersamForm.setBODataCollector(DocumentoPrdRigaVersamBODC); 
        int rcBODC = DocumentoPrdRigaVersamForm.initSecurityServices(); 
        mode = DocumentoPrdRigaVersamForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           DocumentoPrdRigaVersamForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = DocumentoPrdRigaVersamBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              DocumentoPrdRigaVersamForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(DocumentoPrdRigaVersamForm); 
   request.setAttribute("menuBar", menuBar); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="menuBar"/> 
</jsp:include> 
<% 
  menuBar.write(out); 
  menuBar.writeChildren(out); 
%> 
<% 
  WebToolBar myToolBarTB = new com.thera.thermfw.web.WebToolBar("myToolBar", "24", "24", "16", "16", "#f7fbfd","#C8D6E1"); 
  myToolBarTB.setParent(DocumentoPrdRigaVersamForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
<body bottommargin="0" leftmargin="0" onbeforeunload="<%=DocumentoPrdRigaVersamForm.getBodyOnBeforeUnload()%>" onload="<%=DocumentoPrdRigaVersamForm.getBodyOnLoad()%>" onunload="<%=DocumentoPrdRigaVersamForm.getBodyOnUnload()%>" rightmargin="0" topmargin="0"><%
   DocumentoPrdRigaVersamForm.writeBodyStartElements(out); 
%> 


<table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = DocumentoPrdRigaVersamForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", DocumentoPrdRigaVersamBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=DocumentoPrdRigaVersamForm.getServlet()%>" method="post" name="DocumentoPrdRigaVersamForm" style="height:100%"><%
  DocumentoPrdRigaVersamForm.writeFormStartElements(out); 
%>

<table cellpadding="2" cellspacing="0" height="100%" id="emptyborder" width="100%">
	<tr>
		<td style="height:0"><% menuBar.writeElements(out); %> 
</td>
	</tr>
	<tr>
		<td style="height:0"><% myToolBarTB.writeChildren(out); %> 
</td>
	</tr>
	<tr>
		<td height="100%">
			<!--<span class="tabbed" id="myTabbed">-->
<table width="100%" height="100%" cellpadding="0" cellspacing="0" style="padding-right:1px">
   <tr valign="top">
     <td><% 
  WebTabbed myTabbed = new com.thera.thermfw.web.WebTabbed("myTabbed", "100%", "100%"); 
  myTabbed.setParent(DocumentoPrdRigaVersamForm); 
 myTabbed.addTab("tabDatiGen", "it/thera/thip/produzione/documento/resources/DocumentoProduzione", "TabDatiGenerali", "DocumentoPrdRigaVersam", null, null, null, null); 
 myTabbed.addTab("tabLotti", "it/thera/thip/produzione/documento/resources/DocumentoProduzione", "TabLotti", "DocumentoPrdRigaVersam", null, null, null, null); 
  myTabbed.write(out); 
%>

     </td>
   </tr>
   <tr>
     <td height="100%"><div class="tabbed_pagine" id="tabbedPagine" style="position: relative; width: 100%; height: 100%;">
				<div class="tabbed_page" id="<%=myTabbed.getTabPageId("tabDatiGen")%>" style="width:100%;height:100%;overflow:auto;"><% myTabbed.startTab("tabDatiGen"); %>
					<table border="0" cellpadding="2" cellspacing="2" style="margin: 7 7 7 7;">
						<tr>
							<td><% 
  WebTextInput DocumentoPrdRigaVersamIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("DocumentoPrdRigaVersam", "IdAzienda"); 
  DocumentoPrdRigaVersamIdAzienda.setParent(DocumentoPrdRigaVersamForm); 
%>
<input class="<%=DocumentoPrdRigaVersamIdAzienda.getClassType()%>" id="<%=DocumentoPrdRigaVersamIdAzienda.getId()%>" maxlength="<%=DocumentoPrdRigaVersamIdAzienda.getMaxLength()%>" name="<%=DocumentoPrdRigaVersamIdAzienda.getName()%>" size="<%=DocumentoPrdRigaVersamIdAzienda.getSize()%>" type="hidden"><% 
  DocumentoPrdRigaVersamIdAzienda.write(out); 
%>
</td>
							<td><% 
  WebTextInput DocumentoPrdRigaVersamIdAnnoDoc =  
     new com.thera.thermfw.web.WebTextInput("DocumentoPrdRigaVersam", "IdAnnoDoc"); 
  DocumentoPrdRigaVersamIdAnnoDoc.setParent(DocumentoPrdRigaVersamForm); 
%>
<input class="<%=DocumentoPrdRigaVersamIdAnnoDoc.getClassType()%>" id="<%=DocumentoPrdRigaVersamIdAnnoDoc.getId()%>" maxlength="<%=DocumentoPrdRigaVersamIdAnnoDoc.getMaxLength()%>" name="<%=DocumentoPrdRigaVersamIdAnnoDoc.getName()%>" size="<%=DocumentoPrdRigaVersamIdAnnoDoc.getSize()%>" type="hidden"><% 
  DocumentoPrdRigaVersamIdAnnoDoc.write(out); 
%>
</td>
							<td><% 
  WebTextInput DocumentoPrdRigaVersamIdNumeroDoc =  
     new com.thera.thermfw.web.WebTextInput("DocumentoPrdRigaVersam", "IdNumeroDoc"); 
  DocumentoPrdRigaVersamIdNumeroDoc.setParent(DocumentoPrdRigaVersamForm); 
%>
<input class="<%=DocumentoPrdRigaVersamIdNumeroDoc.getClassType()%>" id="<%=DocumentoPrdRigaVersamIdNumeroDoc.getId()%>" maxlength="<%=DocumentoPrdRigaVersamIdNumeroDoc.getMaxLength()%>" name="<%=DocumentoPrdRigaVersamIdNumeroDoc.getName()%>" size="<%=DocumentoPrdRigaVersamIdNumeroDoc.getSize()%>" type="hidden"><% 
  DocumentoPrdRigaVersamIdNumeroDoc.write(out); 
%>
</td>
							<td><% 
  WebTextInput DocumentoPrdRigaVersamIdRigaDoc =  
     new com.thera.thermfw.web.WebTextInput("DocumentoPrdRigaVersam", "IdRigaDoc"); 
  DocumentoPrdRigaVersamIdRigaDoc.setParent(DocumentoPrdRigaVersamForm); 
%>
<input class="<%=DocumentoPrdRigaVersamIdRigaDoc.getClassType()%>" id="<%=DocumentoPrdRigaVersamIdRigaDoc.getId()%>" maxlength="<%=DocumentoPrdRigaVersamIdRigaDoc.getMaxLength()%>" name="<%=DocumentoPrdRigaVersamIdRigaDoc.getName()%>" size="<%=DocumentoPrdRigaVersamIdRigaDoc.getSize()%>" type="hidden"><% 
  DocumentoPrdRigaVersamIdRigaDoc.write(out); 
%>
</td>
							<td><input id="GesArticoloLotti" name="GesArticoloLotti" type="hidden"></td>
						</tr>
						<tr>
							<td width="130"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "DocumentoPrdRigaVersam", "IdCausaleMagVrs", null); 
   label.setParent(DocumentoPrdRigaVersamForm); 
%><label class="<%=label.getClassType()%>" for="CausaleMagVrs"><%label.write(out);%></label><%}%></td>
							<td colspan="2" nowrap><% 
  WebMultiSearchForm DocumentoPrdRigaVersamCausaleVrsRel =  
     new com.thera.thermfw.web.WebMultiSearchForm("DocumentoPrdRigaVersam", "CausaleVrsRel", false, false, false, 1, "20", "45"); 
  DocumentoPrdRigaVersamCausaleVrsRel.setParent(DocumentoPrdRigaVersamForm); 
  DocumentoPrdRigaVersamCausaleVrsRel.write(out); 
%>
<!--<span class="multisearchform" id="CausaleMagVrs"></span>--></td>
						</tr>
						<tr id="vrsDiv">
							<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "DocumentoPrdRigaVersam", "IdRigaProdotto", null); 
   label.setParent(DocumentoPrdRigaVersamForm); 
%><label class="<%=label.getClassType()%>" for="RigaVersamento"><%label.write(out);%></label><%}%></td>
							<td colspan="2" nowrap><% 
  WebMultiSearchForm DocumentoPrdRigaVersamVersamentoRel =  
     new com.thera.thermfw.web.WebMultiSearchForm("DocumentoPrdRigaVersam", "VersamentoRel", false, false, false, 1, "20", "45"); 
  DocumentoPrdRigaVersamVersamentoRel.setParent(DocumentoPrdRigaVersamForm); 
  DocumentoPrdRigaVersamVersamentoRel.setOnKeyChange("fillCampiArticolo()"); 
  DocumentoPrdRigaVersamVersamentoRel.setOnSearchBack("fillCampiArticolo()"); 
  DocumentoPrdRigaVersamVersamentoRel.write(out); 
%>
<!--<span class="multisearchform" id="RigaVersamento"></span>--></td>
						</tr>
						<tr>
							<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "DocumentoPrdRigaVersam", "TipoProdotto", null); 
   label.setParent(DocumentoPrdRigaVersamForm); 
%><label class="<%=label.getClassType()%>" for="TipoProdotto"><%label.write(out);%></label><%}%></td>
							<td><% 
  WebComboBox DocumentoPrdRigaVersamTipoProdotto =  
     new com.thera.thermfw.web.WebComboBox("DocumentoPrdRigaVersam", "TipoProdotto", null); 
  DocumentoPrdRigaVersamTipoProdotto.setParent(DocumentoPrdRigaVersamForm); 
  DocumentoPrdRigaVersamTipoProdotto.setOnChange("abilitaAzioneSuDisp()"); 
%>
<select id="<%=DocumentoPrdRigaVersamTipoProdotto.getId()%>" name="<%=DocumentoPrdRigaVersamTipoProdotto.getName()%>" style="width: 210px;"><% 
  DocumentoPrdRigaVersamTipoProdotto.write(out); 
%> 
</select></td>
						</tr>
						<tr id="azDispDiv">
							<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "DocumentoPrdRigaVersam", "AzioneSuDisponibilita", null); 
   label.setParent(DocumentoPrdRigaVersamForm); 
%><label class="<%=label.getClassType()%>" for="AzioneSuDisp"><%label.write(out);%></label><%}%></td>
							<td><% 
  WebComboBox DocumentoPrdRigaVersamAzioneSuDisponibilita =  
     new com.thera.thermfw.web.WebComboBox("DocumentoPrdRigaVersam", "AzioneSuDisponibilita", null); 
  DocumentoPrdRigaVersamAzioneSuDisponibilita.setParent(DocumentoPrdRigaVersamForm); 
%>
<select id="<%=DocumentoPrdRigaVersamAzioneSuDisponibilita.getId()%>" name="<%=DocumentoPrdRigaVersamAzioneSuDisponibilita.getName()%>" style="width: 210px;"><% 
  DocumentoPrdRigaVersamAzioneSuDisponibilita.write(out); 
%> 
</select></td>
						</tr>
						<tr>
							<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "DocumentoPrdRigaVersam", "IdArticolo", null); 
   label.setParent(DocumentoPrdRigaVersamForm); 
%><label class="<%=label.getClassType()%>" for="Articolo"><%label.write(out);%></label><%}%></td>
							<td colspan="2" nowrap><% 
  WebMultiSearchForm DocumentoPrdRigaVersamArticoloRel =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("DocumentoPrdRigaVersam", "ArticoloRel", false, false, true, 1, "20", "40"); 
  DocumentoPrdRigaVersamArticoloRel.setParent(DocumentoPrdRigaVersamForm); 
  DocumentoPrdRigaVersamArticoloRel.setOnKeyChange("gestioneLottieUM()"); 
  DocumentoPrdRigaVersamArticoloRel.setOnSearchBack("gestioneLottieUM()"); 
  DocumentoPrdRigaVersamArticoloRel.setAdditionalRestrictConditions("TipoProdotto,TipoProdotto;IdAnnoOrdine,IdAnnoOrdine;IdNumeroOrd,IdNumeroOrd"); 
  DocumentoPrdRigaVersamArticoloRel.setSpecificDOList("it.thera.thip.produzione.documento.web.RicercaArticoloVrsDOList"); 
  DocumentoPrdRigaVersamArticoloRel.write(out); 
%>
<!--<span class="articolomultisearchform" id="Articolo"></span>--></td>
						</tr>
						<tr>
							<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "DocumentoPrdRigaVersam", "IdVersione", null); 
   label.setParent(DocumentoPrdRigaVersamForm); 
%><label class="<%=label.getClassType()%>" for="Versione"><%label.write(out);%></label><%}%></td>
							<td colspan="2" nowrap><% 
  WebMultiSearchForm DocumentoPrdRigaVersamVersioneRel =  
     new com.thera.thermfw.web.WebMultiSearchForm("DocumentoPrdRigaVersam", "VersioneRel", false, false, true, 1, "20", "40"); 
  DocumentoPrdRigaVersamVersioneRel.setParent(DocumentoPrdRigaVersamForm); 
  DocumentoPrdRigaVersamVersioneRel.write(out); 
%>
<!--<span class="multisearchform" id="Versione"></span>--></td>
						</tr>
						<tr>
							<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "DocumentoPrdRigaVersam", "IdEsternoConfig", null); 
   label.setParent(DocumentoPrdRigaVersamForm); 
%><label class="<%=label.getClassType()%>" for="Configurazione"><%label.write(out);%></label><%}%></td>
							<td colspan="2" nowrap><% 
  WebMultiSearchForm DocumentoPrdRigaVersamConfigurazioneRel =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("DocumentoPrdRigaVersam", "ConfigurazioneRel", false, false, true, 1, "20", "35"); 
  DocumentoPrdRigaVersamConfigurazioneRel.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  DocumentoPrdRigaVersamConfigurazioneRel.setParent(DocumentoPrdRigaVersamForm); 
  DocumentoPrdRigaVersamConfigurazioneRel.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="Configurazione"></span>--></td>
						</tr>
						<tr>
							<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "DocumentoPrdRigaVersam", "IdCommessa", null); 
   label.setParent(DocumentoPrdRigaVersamForm); 
%><label class="<%=label.getClassType()%>" for="Commessa"><%label.write(out);%></label><%}%></td>
							<td colspan="2" nowrap><% 
  WebMultiSearchForm DocumentoPrdRigaVersamCommessaRel =  
     new com.thera.thermfw.web.WebMultiSearchForm("DocumentoPrdRigaVersam", "CommessaRel", false, false, true, 1, "20", "40"); 
  DocumentoPrdRigaVersamCommessaRel.setParent(DocumentoPrdRigaVersamForm); 
  DocumentoPrdRigaVersamCommessaRel.write(out); 
%>
<!--<span class="multisearchform" id="Commessa"></span>--></td>
						</tr>
						<tr>
							<td colspan="3" width="50%">
								<fieldset id="Quantita" name="Quantita">
									<legend align="top">
										<label class="thLabel" id="QuantitaLabel" name="QuantitaLabel">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it/thera/thip/produzione/documento/resources/DocumentoProduzione", "LegQuantita", null, null, null, null); 
 label.setParent(DocumentoPrdRigaVersamForm); 
label.write(out); }%> 
</label>
									</legend>
									<table cellpadding="0" cellspacing="5">
										<tr>
											<td nowrap width="98"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "DocumentoPrdRigaVersam", "QuantitaUMPrm", null); 
   label.setParent(DocumentoPrdRigaVersamForm); 
%><label class="<%=label.getClassType()%>" for="QuantitaPrm"><%label.write(out);%></label><%}%></td><!-- Fix 39405 modefica il width -->
											<td><% 
  WebTextInput DocumentoPrdRigaVersamQuantitaUMPrm =  
     new com.thera.thermfw.web.WebTextInput("DocumentoPrdRigaVersam", "QuantitaUMPrm"); 
  DocumentoPrdRigaVersamQuantitaUMPrm.setOnChange("fillQtaSec(false)"); 
  DocumentoPrdRigaVersamQuantitaUMPrm.setParent(DocumentoPrdRigaVersamForm); 
%>
<input class="<%=DocumentoPrdRigaVersamQuantitaUMPrm.getClassType()%>" id="<%=DocumentoPrdRigaVersamQuantitaUMPrm.getId()%>" maxlength="<%=DocumentoPrdRigaVersamQuantitaUMPrm.getMaxLength()%>" name="<%=DocumentoPrdRigaVersamQuantitaUMPrm.getName()%>" size="<%=DocumentoPrdRigaVersamQuantitaUMPrm.getSize()%>" type="text"><% 
  DocumentoPrdRigaVersamQuantitaUMPrm.write(out); 
%>
</td>
											<td nowrap><% 
  WebMultiSearchForm DocumentoPrdRigaVersamUmPrimariaMagRel =  
     new com.thera.thermfw.web.WebMultiSearchForm("DocumentoPrdRigaVersam", "UmPrimariaMagRel", false, false, false, 1, null, "20"); 
  DocumentoPrdRigaVersamUmPrimariaMagRel.setParent(DocumentoPrdRigaVersamForm); 
  DocumentoPrdRigaVersamUmPrimariaMagRel.write(out); 
%>
<!--<span class="multisearchform" id="UMQuantitaPrm"></span>--></td>
										</tr>
										<tr>
											<td nowrap><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "DocumentoPrdRigaVersam", "QuantitaUMSec", null); 
   label.setParent(DocumentoPrdRigaVersamForm); 
%><label class="<%=label.getClassType()%>" for="QuantitaSec"><%label.write(out);%></label><%}%></td>
											<td><% 
  WebTextInput DocumentoPrdRigaVersamQuantitaUMSec =  
     new com.thera.thermfw.web.WebTextInput("DocumentoPrdRigaVersam", "QuantitaUMSec"); 
  DocumentoPrdRigaVersamQuantitaUMSec.setOnChange("fillQtaPrm(false)"); 
  DocumentoPrdRigaVersamQuantitaUMSec.setParent(DocumentoPrdRigaVersamForm); 
%>
<input class="<%=DocumentoPrdRigaVersamQuantitaUMSec.getClassType()%>" id="<%=DocumentoPrdRigaVersamQuantitaUMSec.getId()%>" maxlength="<%=DocumentoPrdRigaVersamQuantitaUMSec.getMaxLength()%>" name="<%=DocumentoPrdRigaVersamQuantitaUMSec.getName()%>" size="<%=DocumentoPrdRigaVersamQuantitaUMSec.getSize()%>" type="text"><% 
  DocumentoPrdRigaVersamQuantitaUMSec.write(out); 
%>
</td>
											<td nowrap><% 
  WebMultiSearchForm DocumentoPrdRigaVersamUmSecondariaMagRel =  
     new com.thera.thermfw.web.WebMultiSearchForm("DocumentoPrdRigaVersam", "UmSecondariaMagRel", false, false, false, 1, null, "20"); 
  DocumentoPrdRigaVersamUmSecondariaMagRel.setParent(DocumentoPrdRigaVersamForm); 
  DocumentoPrdRigaVersamUmSecondariaMagRel.write(out); 
%>
<!--<span class="multisearchform" id="UMQuantitaSec"></span>--></td>
										</tr>
										<tr>
											<td></td>
											<td colspan="2"><% 
  WebCheckBox DocumentoPrdRigaVersamRicalcoloQtaFattoreConv =  
     new com.thera.thermfw.web.WebCheckBox("DocumentoPrdRigaVersam", "RicalcoloQtaFattoreConv"); 
  DocumentoPrdRigaVersamRicalcoloQtaFattoreConv.setParent(DocumentoPrdRigaVersamForm); 
%>
<input id="<%=DocumentoPrdRigaVersamRicalcoloQtaFattoreConv.getId()%>" name="<%=DocumentoPrdRigaVersamRicalcoloQtaFattoreConv.getName()%>" type="checkbox" value="Y"><%
  DocumentoPrdRigaVersamRicalcoloQtaFattoreConv.write(out); 
%>
</td>
											<td></td>
										</tr>
									</table>
								</fieldset>
							</td>
							<!-- Inizio 5142 -->
							<td width="50%">
							<fieldset name="QuantitaScarto">
							<legend align="top">
								<label class="thLabel" id="QuantitaScartoLabel" name="QuantitaScartoLabel">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it/thera/thip/produzione/documento/resources/DocumentoProduzione", "LegQtaScarto", null, null, null, null); 
 label.setParent(DocumentoPrdRigaVersamForm); 
label.write(out); }%> 
</label>
							</legend>
							<table border="0" cellpadding="0" cellspacing="5">
								<tr>
									<td nowrap><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "DocumentoPrdRigaVersam", "QtaScarto", null); 
   label.setParent(DocumentoPrdRigaVersamForm); 
%><label class="<%=label.getClassType()%>" for="QtaScartoPrm"><%label.write(out);%></label><%}%></td>
									<td><% 
  WebTextInput DocumentoPrdRigaVersamQtaScarto =  
     new com.thera.thermfw.web.WebTextInput("DocumentoPrdRigaVersam", "QtaScarto"); 
  DocumentoPrdRigaVersamQtaScarto.setOnChange("fillQtaScartoSecVers()"); 
  DocumentoPrdRigaVersamQtaScarto.setParent(DocumentoPrdRigaVersamForm); 
%>
<input class="<%=DocumentoPrdRigaVersamQtaScarto.getClassType()%>" id="<%=DocumentoPrdRigaVersamQtaScarto.getId()%>" maxlength="<%=DocumentoPrdRigaVersamQtaScarto.getMaxLength()%>" name="<%=DocumentoPrdRigaVersamQtaScarto.getName()%>" size="<%=DocumentoPrdRigaVersamQtaScarto.getSize()%>" type="text"><% 
  DocumentoPrdRigaVersamQtaScarto.write(out); 
%>
</td>
								</tr>
								<tr>
									<td nowrap><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "DocumentoPrdRigaVersam", "QtaScartoSec", null); 
   label.setParent(DocumentoPrdRigaVersamForm); 
%><label class="<%=label.getClassType()%>" for="QtaScartoSec"><%label.write(out);%></label><%}%></td>
									<td><% 
  WebTextInput DocumentoPrdRigaVersamQtaScartoSec =  
     new com.thera.thermfw.web.WebTextInput("DocumentoPrdRigaVersam", "QtaScartoSec"); 
  DocumentoPrdRigaVersamQtaScartoSec.setOnChange("fillQtaScartoPrmVers()"); 
  DocumentoPrdRigaVersamQtaScartoSec.setParent(DocumentoPrdRigaVersamForm); 
%>
<input class="<%=DocumentoPrdRigaVersamQtaScartoSec.getClassType()%>" id="<%=DocumentoPrdRigaVersamQtaScartoSec.getId()%>" maxlength="<%=DocumentoPrdRigaVersamQtaScartoSec.getMaxLength()%>" name="<%=DocumentoPrdRigaVersamQtaScartoSec.getName()%>" size="<%=DocumentoPrdRigaVersamQtaScartoSec.getSize()%>" type="text"><% 
  DocumentoPrdRigaVersamQtaScartoSec.write(out); 
%>
</td>
								</tr>
								<tr><td>&nbsp;</td></tr>
							</table>
							</fieldset>
							</td>
            				<!-- Fine 5142 -->
						</tr>
						<tr>
							<td nowrap><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "DocumentoPrdRigaVersam", "IdMagazzino", null); 
   label.setParent(DocumentoPrdRigaVersamForm); 
%><label class="<%=label.getClassType()%>" for="Magazzino"><%label.write(out);%></label><%}%></td>
							<td colspan="2" nowrap><% 
  WebMultiSearchForm DocumentoPrdRigaVersamMagazzinoRel =  
     new com.thera.thermfw.web.WebMultiSearchForm("DocumentoPrdRigaVersam", "MagazzinoRel", false, false, true, 1, "20", "40"); 
  DocumentoPrdRigaVersamMagazzinoRel.setParent(DocumentoPrdRigaVersamForm); 
  DocumentoPrdRigaVersamMagazzinoRel.write(out); 
%>
<!--<span class="multisearchform" id="Magazzino"></span>--></td>
						</tr>
						<tr>
							<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "DocumentoPrdRigaVersam", "CostoRiferimento", null); 
   label.setParent(DocumentoPrdRigaVersamForm); 
%><label class="<%=label.getClassType()%>" for="CostoRiferimento"><%label.write(out);%></label><%}%></td>
							<td><% 
  WebTextInput DocumentoPrdRigaVersamCostoRiferimento =  
     new com.thera.thermfw.web.WebTextInput("DocumentoPrdRigaVersam", "CostoRiferimento"); 
  DocumentoPrdRigaVersamCostoRiferimento.setParent(DocumentoPrdRigaVersamForm); 
%>
<input class="<%=DocumentoPrdRigaVersamCostoRiferimento.getClassType()%>" id="<%=DocumentoPrdRigaVersamCostoRiferimento.getId()%>" maxlength="<%=DocumentoPrdRigaVersamCostoRiferimento.getMaxLength()%>" name="<%=DocumentoPrdRigaVersamCostoRiferimento.getName()%>" size="<%=DocumentoPrdRigaVersamCostoRiferimento.getSize()%>" type="text"><% 
  DocumentoPrdRigaVersamCostoRiferimento.write(out); 
%>
</td>
						</tr>
						<tr id="SaldoVrs">
							<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "DocumentoPrdRigaVersam", "Saldo", null); 
   label.setParent(DocumentoPrdRigaVersamForm); 
%><label class="<%=label.getClassType()%>" for="Saldo"><%label.write(out);%></label><%}%></td>
							<td><% 
  WebComboBox DocumentoPrdRigaVersamSaldo =  
     new com.thera.thermfw.web.WebComboBox("DocumentoPrdRigaVersam", "Saldo", null); 
  DocumentoPrdRigaVersamSaldo.setParent(DocumentoPrdRigaVersamForm); 
%>
<select id="<%=DocumentoPrdRigaVersamSaldo.getId()%>" name="<%=DocumentoPrdRigaVersamSaldo.getName()%>" style="width: 210px;"><% 
  DocumentoPrdRigaVersamSaldo.write(out); 
%> 
</select></td>
						</tr>
					</table>
				<% myTabbed.endTab(); %> 
</div>
				<div class="tabbed_page" id="<%=myTabbed.getTabPageId("tabLotti")%>" style="width:100%;height:100%;overflow:auto;"><% myTabbed.startTab("tabLotti"); %>
				<!-- 71XXX Softre Inizio -->
						<table cellpadding="2" cellspacing="2" style="padding: 3px; width: 100%">
								<tr>
									<td nowrap="nowrap" colspan ="1" id="SezCodificaLottiTestori">
										<button style="width: 75px" name="CodificaLottiTestori" id="CodificaLottiTestori" 
										type="button" onClick="codificaLottiTestori()"><%= ResourceLoader.getString("it.testori.thip.magazzino.generalemag.resources.LottiTestori", "btnCreaLotti")%></button>
									</td>
									</table>
						<!-- 71XXX Softre Fine -->
					<table border="0" style="margin: 7 7 7 7;" width="90%">
						<tr>
							<td valign="top"><!--<span class="editgrid" id="LottiGrid">--><% 
  WebEditGrid DocumentoPrdRigaVersamLottiColl =  
     new com.thera.thermfw.web.WebEditGrid("DocumentoPrdRigaVersam", "LottiColl", 8, new String[]{"IdLotto", "Lotto.IdLottoPrdFormattato", "QuantitaUMPrm", "IdUmPrmMag", "QuantitaUMSec", "IdUmSecMag", "QtaScartoPrm", "QtaScartoSec"}, 1, null, null,false,"com.thera.thermfw.web.servlet.GridActionAdapterForIndependentRow"); 
 DocumentoPrdRigaVersamLottiColl.setParent(DocumentoPrdRigaVersamForm); 
 DocumentoPrdRigaVersamLottiColl.setNoControlRowKeys(false); 
 DocumentoPrdRigaVersamLottiColl.addHideAsDefault("IdLotto"); 
 DocumentoPrdRigaVersamLottiColl.includeAction("DeleteRow",new WebMenuItem("GestioneMatricole", "action_submit", "new", "no", "it.thera.thip.magazzino.matricole.resources.LottoMatricola", "GestioneMatricoleBtn", "gestioneMatricole()", "none", true)); 
 DocumentoPrdRigaVersamLottiColl.write(out); 
%>
<BR><% 
   request.setAttribute("parentForm", DocumentoPrdRigaVersamForm); 
   String CDForLottiColl = "LottiColl"; 
%>
<jsp:include page="/it/thera/thip/produzione/documento/DocumentoPrdRigaLottoVrs.jsp" flush="true"> 
<jsp:param name="EditGridCDName" value="<%=CDForLottiColl%>"/> 
<jsp:param name="Mode" value="NEW"/> 
</jsp:include> 
<!--</span>--></td>
						</tr>
					</table>
				<% myTabbed.endTab(); %> 
</div>
			</div><% myTabbed.endTabbed();%> 

     </td>
   </tr>
</table><!--</span>-->
		</td>
	</tr>
	<tr>
		<td style="height:0"><% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(DocumentoPrdRigaVersamForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>--></td>
	</tr>
</table>

<%
  DocumentoPrdRigaVersamForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = DocumentoPrdRigaVersamForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", DocumentoPrdRigaVersamBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


<% 
  WebScript script_0 =  
   new com.thera.thermfw.web.WebScript(); 
 script_0.setRequest(request); 
 script_0.setSrcAttribute("it/thera/thip/produzione/documento/DocumentoPrdLotti.js"); 
 script_0.setLanguageAttribute("JavaScript1.2"); 
  script_0.write(out); 
%>
<!--<script language="JavaScript1.2" src="it/thera/thip/produzione/documento/DocumentoPrdLotti.js" type="text/javascript"></script>-->
<% 
  WebScript script_1 =  
   new com.thera.thermfw.web.WebScript(); 
 script_1.setRequest(request); 
 script_1.setSrcAttribute("it/thera/thip/produzione/documento/UtilityDocumenti.js"); 
 script_1.setLanguageAttribute("JavaScript1.2"); 
  script_1.write(out); 
%>
<!--<script language="JavaScript1.2" src="it/thera/thip/produzione/documento/UtilityDocumenti.js" type="text/javascript"></script>-->
<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              DocumentoPrdRigaVersamForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, DocumentoPrdRigaVersamBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, DocumentoPrdRigaVersamBODC.getErrorList().getErrors()); 
           if(DocumentoPrdRigaVersamBODC.getConflict() != null) 
                conflitPresent = true; 
     } 
     else 
        errors.add(new ErrorMessage("BAS0000010")); 
  } 
  catch(NamingException e) { 
     errorMessage = e.getMessage(); 
     errors.add(new ErrorMessage("CBS000025", errorMessage));  } 
  catch(SQLException e) {
     errorMessage = e.getMessage(); 
     errors.add(new ErrorMessage("BAS0000071", errorMessage));  } 
  catch(Throwable e) {
     e.printStackTrace(Trace.excStream);
  }
  finally 
  {
     if(DocumentoPrdRigaVersamBODC != null && !DocumentoPrdRigaVersamBODC.close(false)) 
        errors.addAll(0, DocumentoPrdRigaVersamBODC.getErrorList().getErrors()); 
     try 
     { 
        se.end(); 
     }
     catch(IllegalArgumentException e) { 
        e.printStackTrace(Trace.excStream); 
     } 
     catch(SQLException e) { 
        e.printStackTrace(Trace.excStream); 
     } 
  } 
  if(!errors.isEmpty())
  { 
      if(!conflitPresent)
  { 
     request.setAttribute("ErrorMessages", errors); 
     String errorPage = DocumentoPrdRigaVersamForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", DocumentoPrdRigaVersamBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = DocumentoPrdRigaVersamForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
