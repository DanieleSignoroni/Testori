<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///C:/pthsvil/deps/T_Thip_40/Thip/4.0/websrcsvil/dtd/xhtml1-transitional.dtd">
<!-- 34124 -->
<html>
<!-- WIZGEN Therm 2.0.0 as Form - multiBrowserGen = true -->
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
  BODataCollector OrdineAcquistoRigaPrmBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm OrdineAcquistoRigaPrmForm =  
     new com.thera.thermfw.web.WebForm(request, response, "OrdineAcquistoRigaPrmForm", "OrdineAcquistoRigaPrm", null, "it.thera.thip.acquisti.ordineAC.web.OrdineAcquistoRigaPrmFormActionAdapter", false, false, true, true, true, true, "it.thera.thip.acquisti.ordineAC.web.OrdineAcquistoRigaPrmDataCollector", 1, true, "it/thera/thip/acquisti/ordineAC/OrdineAcquistoRiga.js"); 
  OrdineAcquistoRigaPrmForm.setServletEnvironment(se); 
  OrdineAcquistoRigaPrmForm.setJSTypeList(jsList); 
  OrdineAcquistoRigaPrmForm.setHeader("it.thera.thip.cs.Header.jsp"); 
  OrdineAcquistoRigaPrmForm.setFooter("it.thera.thip.cs.Footer.jsp"); 
  OrdineAcquistoRigaPrmForm.setWebFormModifierClass("it.thera.thip.acquisti.ordineAC.web.OrdineAcquistoRigaPrmCompletaFormModifier"); 
  OrdineAcquistoRigaPrmForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = OrdineAcquistoRigaPrmForm.getMode(); 
  String key = OrdineAcquistoRigaPrmForm.getKey(); 
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
        OrdineAcquistoRigaPrmForm.outTraceInfo(getClass().getName()); 
        String collectorName = OrdineAcquistoRigaPrmForm.findBODataCollectorName(); 
                OrdineAcquistoRigaPrmBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (OrdineAcquistoRigaPrmBODC instanceof WebDataCollector) 
            ((WebDataCollector)OrdineAcquistoRigaPrmBODC).setServletEnvironment(se); 
        OrdineAcquistoRigaPrmBODC.initialize("OrdineAcquistoRigaPrm", true, 1); 
        OrdineAcquistoRigaPrmForm.setBODataCollector(OrdineAcquistoRigaPrmBODC); 
        int rcBODC = OrdineAcquistoRigaPrmForm.initSecurityServices(); 
        mode = OrdineAcquistoRigaPrmForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           OrdineAcquistoRigaPrmForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = OrdineAcquistoRigaPrmBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              OrdineAcquistoRigaPrmForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 

	<title>Ordine - Riga</title>
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(OrdineAcquistoRigaPrmForm); 
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
  myToolBarTB.setParent(OrdineAcquistoRigaPrmForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>

<body bottommargin="0" leftmargin="0" onbeforeunload="<%=OrdineAcquistoRigaPrmForm.getBodyOnBeforeUnload()%>" onload="<%=OrdineAcquistoRigaPrmForm.getBodyOnLoad()%>" onunload="<%=OrdineAcquistoRigaPrmForm.getBodyOnUnload()%>" rightmargin="0" topmargin="0"><%
   OrdineAcquistoRigaPrmForm.writeBodyStartElements(out); 
%> 


	<table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = OrdineAcquistoRigaPrmForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", OrdineAcquistoRigaPrmBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=OrdineAcquistoRigaPrmForm.getServlet()%>" method="post" name="OrdineAcquistoRigaForm" style="height:100%"><%
  OrdineAcquistoRigaPrmForm.writeFormStartElements(out); 
%>

		<table cellpadding="1" cellspacing="1" height="100%" width="100%">

			<!-- **************************************************************************************************** -->
			<!-- Menubar -->
			<tr>
				<td style="height:0">
					<% menuBar.writeElements(out); %> 

				</td>
			</tr>

			<!-- **************************************************************************************************** -->
			<!-- Toolbar -->
			<tr>
				<td style="height:0">
					<% myToolBarTB.writeChildren(out); %> 

				</td>
			</tr>

			<!-- **************************************************************************************************** -->
			<!-- Pannello tabulare principale -->
			<tr>
				<td height="100%">
					<!--<span class="tabbed" id="MainTabbed">-->
<table width="100%" height="100%" cellpadding="0" cellspacing="0" style="padding-right:1px">
   <tr valign="top">
     <td><% 
  WebTabbed MainTabbed = new com.thera.thermfw.web.WebTabbed("MainTabbed", "100%", "100%"); 
  MainTabbed.setParent(OrdineAcquistoRigaPrmForm); 
 MainTabbed.addTab("GeneraleTab", "it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "GeneraleTab", "OrdineAcquistoRigaPrm", null, null, null, null); 
 MainTabbed.addTab("PrezziScontiTab", "it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "PrezziScontiTab", "OrdineAcquistoRigaPrm", null, null, null, "verificaCondizioniRecuperoDatiAcquisto()"); 
 MainTabbed.addTab("CondEvasioneTab", "it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "CondEvasioneTab", "OrdineAcquistoRigaPrm", null, null, null, null); 
 MainTabbed.addTab("ContabilTab", "it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "ContabilTab", "OrdineAcquistoRigaPrm", null, null, null, null); 
 MainTabbed.addTab("CommentiMultimediaTab", "it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "CommentiMultimediaTab", "OrdineAcquistoRigaPrm", null, null, null, null); 
 MainTabbed.addTab("LottiTab", "it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "LottiTab", "OrdineAcquistoRigaPrm", null, null, null, null); 
 MainTabbed.addTab("SpeditoTab", "it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "SpeditoTab", "OrdineAcquistoRigaPrm", null, null, null, null); 
 MainTabbed.addTab("RiepilogoTab", "it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "RiepilogoTab", "OrdineAcquistoRigaPrm", null, null, null, null); 
  MainTabbed.write(out); 
%>

     </td>
   </tr>
   <tr>
     <td height="100%"><div class="tabbed_pagine" id="tabbedPagine" style="position: relative; width: 100%; height: 100%;">
						<!-- **************************************************************************************** -->
						<!-- Cartella Generale -->
						<div class="tabbed_page" id="<%=MainTabbed.getTabPageId("GeneraleTab")%>" style="width:100%;height:100%;overflow:auto;"><% MainTabbed.startTab("GeneraleTab"); %>
							<!-- INIZIO TABELLA PRINCIPALE -->
							<!-- Fix 39398 - inizio modifiche grafiche -->
							<!-- <table> -->
							<table cellpadding="2" cellspacing="2" style="padding: 3px; width: 100%;">
								<!-- INIZIO RIGA 1 TABELLA PRINCIPALE -->
								<tr>
									<td>
										<!-- <table cellspacing="1" cellpadding="1"> -->
										<table>
											<tr>
												<!-- Campo Sequenza Riga -->
												<td colspan="2" nowrap style="width: 100px">
													<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "SequenzaRiga", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="SequenzaRigaField"><%label.write(out);%></label><%}%>
												</td>
												<!-- Fix 3147 inizio -->
												<!-- <td></td> -->
												<!-- Fix 3147 fine -->
												<td colspan="2">
													<% 
  WebTextInput OrdineAcquistoRigaPrmSequenzaRiga =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "SequenzaRiga"); 
  OrdineAcquistoRigaPrmSequenzaRiga.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmSequenzaRiga.getClassType()%>" id="<%=OrdineAcquistoRigaPrmSequenzaRiga.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmSequenzaRiga.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmSequenzaRiga.getName()%>" size="5" type="text"><% 
  OrdineAcquistoRigaPrmSequenzaRiga.write(out); 
%>

												</td>
												<td>
													<!--               Questo campo hidden non serve a niente, anzi serve a molto:               serve per non far baccare il generatore (se si ha un certo numero               di campi in una pagina, forse 56, la JSP non viene generata)               QUINDI NON BISOGNA LEVARLO PER NESSUN MOTIVO!!!!!              -->
													<input id="FittizioField" name="FittizioField" type="hidden">
												</td>
												<!-- Combo Stato Avanzamento -->
												<td colspan="8">
													<table>
													<tr>
												<td>
													<input id="IdNumeratoreArtIntestField" name="IdNumeratoreArtIntestField" type="hidden">
												</td>
												</tr>
														<tr>
															<td nowrap style="width: 150px">
																<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "StatoAvanzamento", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="StatoAvanzamentoCombo"><%label.write(out);%></label><%}%>
															</td>
															<td>
																<% 
  WebComboBox OrdineAcquistoRigaPrmStatoAvanzamento =  
     new com.thera.thermfw.web.WebComboBox("OrdineAcquistoRigaPrm", "StatoAvanzamento", null); 
  OrdineAcquistoRigaPrmStatoAvanzamento.setParent(OrdineAcquistoRigaPrmForm); 
%>
<select id="<%=OrdineAcquistoRigaPrmStatoAvanzamento.getId()%>" name="<%=OrdineAcquistoRigaPrmStatoAvanzamento.getName()%>"><% 
  OrdineAcquistoRigaPrmStatoAvanzamento.write(out); 
%> 

																	
																</select>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<!-- Proxy Causale -->
												<td colspan="2" nowrap>
													<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdCausaleRigaAcquisto", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="CausaleProxy"><%label.write(out);%></label><%}%>
												</td>
												<!-- Fix 3147 inizio -->
												<!-- <td></td> -->
												<!-- Fix 3147 fine -->
												<td colspan="3" nowrap>
													<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRCausaleRigaAcquisto =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "RCausaleRigaAcquisto", false, false, true, 1, null, null); 
  OrdineAcquistoRigaPrmRCausaleRigaAcquisto.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRCausaleRigaAcquisto.write(out); 
%>
<!--<span class="multisearchform" id="CausaleProxy"></span>-->
												</td>
												
												<!-- Check Non Fatturare -->
												<td colspan="3">
													<% 
  WebCheckBox OrdineAcquistoRigaPrmNonFatturare =  
     new com.thera.thermfw.web.WebCheckBox("OrdineAcquistoRigaPrm", "NonFatturare"); 
  OrdineAcquistoRigaPrmNonFatturare.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input id="<%=OrdineAcquistoRigaPrmNonFatturare.getId()%>" name="<%=OrdineAcquistoRigaPrmNonFatturare.getName()%>" type="checkbox" value="Y"><%
  OrdineAcquistoRigaPrmNonFatturare.write(out); 
%>

												</td>
											</tr>
											<tr>
												<!-- Proxy Magazzino -->
												<td colspan="2" nowrap>
													<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdMagazzino", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="MagazzinoProxy"><%label.write(out);%></label><%}%>
												</td>
												<!-- Fix 3147 inizio -->
												<!-- <td></td> -->
												<!-- Fix 3147 fine -->
												<td colspan="6" nowrap>
													<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRMagazzino =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "RMagazzino", false, false, true, 1, null, null); 
  OrdineAcquistoRigaPrmRMagazzino.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRMagazzino.setOnKeyChange("gestioneOrdineCliente()"); 
  OrdineAcquistoRigaPrmRMagazzino.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoProxy"></span>-->
												</td>
											</tr>
											<tr id="SezMagazzinoLavEsterna" name="SezMagazzinoLavEsterna" style="display:none">
												<!-- Proxy Magazzino Lavorazione Esterna -->
												<td colspan="2" nowrap>
													<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdMagazzinoLavEsterna", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="MagazzinoLavEsternaProxy"><%label.write(out);%></label><%}%>
												</td>
												<td colspan="6" nowrap>
													<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRMagazzinoLavEsterna =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "RMagazzinoLavEsterna", false, false, true, 1, null, null); 
  OrdineAcquistoRigaPrmRMagazzinoLavEsterna.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRMagazzinoLavEsterna.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoLavEsternaProxy"></span>-->
												</td>
											</tr>
											<!-- Fix 1918 inizio -->
											<tr id="SezCatalogoEsterno" name="SezCatalogoEsterno" style="display:none">
												<!-- Proxy Catalogo Esterno -->
												<td colspan="2" nowrap>
													<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdArticoloFornitoreCatal", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="CatalogoEsternoProxy"><%label.write(out);%></label><%}%>
												</td>
												<!-- Fix 3147 inizio -->
												<!-- <td></td> -->
												<!-- Fix 3147 fine -->
												<td colspan="6" nowrap>
													<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRCatalogoEsterno =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "RCatalogoEsterno", false, false, true, 2, "15", null); 
  OrdineAcquistoRigaPrmRCatalogoEsterno.setExtraRelatedClassAD("IdCatalogo"); 
  OrdineAcquistoRigaPrmRCatalogoEsterno.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRCatalogoEsterno.setOnKeyFocus("memorizzaCodiciCatalogoEsterno()"); 
  OrdineAcquistoRigaPrmRCatalogoEsterno.setOnKeyBlur("confrontaCodiciCatalogoEsterno()"); 
  OrdineAcquistoRigaPrmRCatalogoEsterno.setOnSearchBack("recuperaDatiCatalogoEsterno()"); 
  OrdineAcquistoRigaPrmRCatalogoEsterno.write(out); 
%>
<!--<span class="multisearchform" id="CatalogoEsternoProxy"></span>-->
												</td>
											</tr>
											<!-- Fix 1918 fine -->
											<tr>
												<!-- Fix 3147 inizio -->
												<!-- Proxy Articolo Intestatario Label -->
												<td id="SezArtIntLabel" nowrap style="display:none">
													<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdArticoloIntestatario", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="ArticoloIntestatarioProxy"><%label.write(out);%></label><%}%>
												</td>
												<!-- Proxy Articolo Label -->
												<td id="SezArticoloLabel" nowrap><!-- style="display:block">12094-->
													<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdArticolo", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="ArticoloProxy"><%label.write(out);%></label><%}%>
												</td>
												<!-- Check Visualizza Articolo Intestatario -->
												<td id="SezCheckVisulArtInt" style="display:none; text-align: end; vertical-align: middle;">
													<% 
  WebCheckBox OrdineAcquistoRigaPrmVisualizzaArtInt =  
     new com.thera.thermfw.web.WebCheckBox("OrdineAcquistoRigaPrm", "VisualizzaArtInt"); 
  OrdineAcquistoRigaPrmVisualizzaArtInt.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input id="<%=OrdineAcquistoRigaPrmVisualizzaArtInt.getId()%>" name="<%=OrdineAcquistoRigaPrmVisualizzaArtInt.getName()%>" type="checkbox" value="Y"><%
  OrdineAcquistoRigaPrmVisualizzaArtInt.write(out); 
%>

												</td>
												<td style="text-align: end; vertical-align: middle;">
													<input id="VisualizzaArtInt2" name="VisualizzaArtInt2" onclick="visualizzaArticoloIntestatarioOnClick()" type="checkbox">
												</td>
												<!-- Proxy Articolo Intestatario Multisearch -->
												<td colspan="4" id="SezArtIntProxy" nowrap><!-- style="display:block">12094-->
													<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRArticoloIntestatario =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "RArticoloIntestatario", false, false, true, 1, "15", null); 
  OrdineAcquistoRigaPrmRArticoloIntestatario.setExtraRelatedClassAD("IdArticolo,IdConfigurazione,IdArticoloFornitore"); 
  OrdineAcquistoRigaPrmRArticoloIntestatario.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRArticoloIntestatario.setOnKeyFocus("memorizzaCodiceArticoloIntestatario()"); 
  OrdineAcquistoRigaPrmRArticoloIntestatario.setOnKeyBlur("confrontaCodiceArticoloIntestatario()"); 
  OrdineAcquistoRigaPrmRArticoloIntestatario.setOnKeyChange("ripulisciNumeratoreArticoloIntestatario()"); 
  OrdineAcquistoRigaPrmRArticoloIntestatario.setOnSearchBack("recuperaDatiArticoloIntestatario()"); 
  OrdineAcquistoRigaPrmRArticoloIntestatario.setAdditionalRestrictConditions("LavorazioneEsterna,LavEsterna"); 
  OrdineAcquistoRigaPrmRArticoloIntestatario.write(out); 
%>
<!--<span class="multisearchform" id="ArticoloIntestatarioProxy"></span>-->
												</td>
												<!-- Proxy Articolo Multisearch -->
												<td colspan="4" id="SezArticoloProxy" nowrap><!-- style="display:block">12094-->
													<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRArticolo =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("OrdineAcquistoRigaPrm", "RArticolo", false, false, true, 1, "15", null); 
  OrdineAcquistoRigaPrmRArticolo.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRArticolo.setOnKeyFocus("memorizzaCodiceArticolo()"); 
  OrdineAcquistoRigaPrmRArticolo.setOnKeyBlur("confrontaCodiceArticolo()"); 
  OrdineAcquistoRigaPrmRArticolo.setOnKeyChange("variazioneArticolo()"); 
  OrdineAcquistoRigaPrmRArticolo.setOnSearchClick("ricercaArticolo()"); 
  OrdineAcquistoRigaPrmRArticolo.setOnSearchBack("recuperaDatiArticolo()"); 
  OrdineAcquistoRigaPrmRArticolo.setFixedRestrictConditions("DatiComuniEstesi.Stato,V"); 
  ((it.thera.thip.base.articolo.web.ArticoloMultiSearchForm)OrdineAcquistoRigaPrmRArticolo).setArticoliAlternButton(true); 
  OrdineAcquistoRigaPrmRArticolo.write(out); 
%>
<!--<span class="articolomultisearchform" id="ArticoloProxy"></span>-->
												</td>
												<!-- Fix 3147 fine -->
												<!-- Campo Descrizione Articolo -->
												<td colspan="2" id="SezArticoloDescr" nowrap><!-- style="display:block">12094-->
													<% 
  WebTextInput OrdineAcquistoRigaPrmDescrizioneArticolo =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "DescrizioneArticolo"); 
  OrdineAcquistoRigaPrmDescrizioneArticolo.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmDescrizioneArticolo.getClassType()%>" id="<%=OrdineAcquistoRigaPrmDescrizioneArticolo.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmDescrizioneArticolo.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmDescrizioneArticolo.getName()%>" size="40" style="width: 290px" type="text"><% 
  OrdineAcquistoRigaPrmDescrizioneArticolo.write(out); 
%>

												</td>
												
											</tr>
											<tr style="height: 22px;">
												<!-- Proxy Versione Richiesta -->
												<td colspan="2" nowrap>
													<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdVersioneRichiesta", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="VersioneRichiestaProxy"><%label.write(out);%></label><%}%>
												</td>
												<!-- Fix 3147 inizio -->
												<!-- <td></td> -->
												<!-- Fix 3147 fine -->
												<td colspan="4" nowrap>
													<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRVersioneRichiesta =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "RVersioneRichiesta", false, false, true, 1, "3", null); 
  OrdineAcquistoRigaPrmRVersioneRichiesta.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRVersioneRichiesta.setOnKeyFocus("memorizzaCodiceVersione()"); 
  OrdineAcquistoRigaPrmRVersioneRichiesta.setOnKeyBlur("confrontaCodiceVersione()"); 
  OrdineAcquistoRigaPrmRVersioneRichiesta.setOnSearchBack("recuperaCostoUnitario()"); 
  OrdineAcquistoRigaPrmRVersioneRichiesta.write(out); 
%>
<!--<span class="multisearchform" id="VersioneRichiestaProxy"></span>-->
												</td>
												<!-- Operazione -->
												<td style="display:none">
													<table>
														<tr id="SezOperazione" name="SezOperazione" style="display:none">
															<td nowrap>
																<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "Operazione", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="OperazioneField"><%label.write(out);%></label><%}%>
															</td>
												<!-- Fix 3147 inizio -->
															<td></td>
												<!-- Fix 3147 fine -->
															<td>
																<% 
  WebTextInput OrdineAcquistoRigaPrmOperazione =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "Operazione"); 
  OrdineAcquistoRigaPrmOperazione.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmOperazione.getClassType()%>" id="<%=OrdineAcquistoRigaPrmOperazione.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmOperazione.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmOperazione.getName()%>" size="10" type="text"><% 
  OrdineAcquistoRigaPrmOperazione.write(out); 
%>

															</td>
														</tr>
													</table>
												</td>
												<!-- Fix14727 Inizio RA -->
												<td colspan="2" id="SezDescrExtArticolo" rowspan="3">
													<% 
  WebTextInput OrdineAcquistoRigaPrmDescrizioneExtArticolo =  
     new com.thera.thermfw.web.WebTextArea("OrdineAcquistoRigaPrm", "DescrizioneExtArticolo"); 
  OrdineAcquistoRigaPrmDescrizioneExtArticolo.setParent(OrdineAcquistoRigaPrmForm); 
%>
<textarea class="<%=OrdineAcquistoRigaPrmDescrizioneExtArticolo.getClassType()%>" id="<%=OrdineAcquistoRigaPrmDescrizioneExtArticolo.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmDescrizioneExtArticolo.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmDescrizioneExtArticolo.getName()%>" rows="3" size="<%=OrdineAcquistoRigaPrmDescrizioneExtArticolo.getSize()%>" style="width: 290px"></textarea><% 
  OrdineAcquistoRigaPrmDescrizioneExtArticolo.write(out); 
%>

												</td>
												<!-- Fix14727 Fine RA -->
											</tr>
											<tr style="height: 22px;">
												<!-- Proxy Configurazione -->
												<td colspan="2" nowrap>
													<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdEsternoConfig", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="ConfigurazioneProxy"><%label.write(out);%></label><%}%>
												</td>
												<!-- Fix 3147 inizio -->
												<!-- <td></td> -->
												<!-- Fix 3147 fine -->
												<td colspan="4" nowrap>
													<div id="confdiv">
														<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRConfigurazione =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("OrdineAcquistoRigaPrm", "RConfigurazione", false, false, true, 1, "20", null); 
  OrdineAcquistoRigaPrmRConfigurazione.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  OrdineAcquistoRigaPrmRConfigurazione.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRConfigurazione.setOnKeyFocus("memorizzaCodiceConfigurazione()"); 
  OrdineAcquistoRigaPrmRConfigurazione.setOnKeyBlur("confrontaCodiceConfigurazione()"); 
  OrdineAcquistoRigaPrmRConfigurazione.setOnSearchBack("recuperaCostoUnitario()"); 
  OrdineAcquistoRigaPrmRConfigurazione.setAdditionalRestrictConditions("IdArticolo,IdArticolo"); 
  OrdineAcquistoRigaPrmRConfigurazione.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ConfigurazioneProxy"></span>-->
													</div>
												</td>
												<!-- Numero Ordine Produzione -->
												<td>
												&nbsp;
												<!-- fix 5757 modificati rif a produzione              <table>               <tr id="SezNumOrdProd" name="SezNumOrdProd" style="display:none">                <td nowrap="true">                 <label for="NumeroOrdProduzioneField">                  Numero Ordine Produzione                 </label>                </td>                <td>                 <input type ="text" id="NumeroOrdProduzioneField" name="NumeroOrdProduzioneField" size="10"/>                </td>               </tr>              </table>              -->
												</td>
											</tr>
											<tr><td></td></tr>

                                                                                        <!-- Fix 13681 Inizio -->
                                                                                              <tr id="trCliente" style="display:none">
                                                                                                <!-- Proxy Cliente -->
                                                                                                <td colspan="2" nowrap><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdCliente", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="Cliente"><%label.write(out);%></label><%}%></td>
                                                                                                <td colspan="6" nowrap><% 
  WebMultiSearchForm OrdineAcquistoRigaPrmCliente =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "Cliente", false, false, true, 1, null, null); 
  OrdineAcquistoRigaPrmCliente.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmCliente.write(out); 
%>
<!--<span class="multisearchform" id="Cliente"></span>--></td>
                                                                                              </tr>
                                                                                              <tr id="trOrdineCliente" style="display:none">
                                                                                                <!-- Proxy OrdineVendita -->
                                                                                                <td colspan="2" nowrap><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "NumeroOrdineCliente", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="OrdineVendita"><%label.write(out);%></label><%}%></td>
                                                                                                <td colspan="6" nowrap><% 
  WebMultiSearchForm OrdineAcquistoRigaPrmOrdineVendita =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "OrdineVendita", true, false, true, 2, null, null); 
  OrdineAcquistoRigaPrmOrdineVendita.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmOrdineVendita.setAdditionalRestrictConditions("IdCliente,Cliente"); 
  OrdineAcquistoRigaPrmOrdineVendita.write(out); 
%>
<!--<span class="multisearchform" id="OrdineVendita"></span>-->
                                                                                                <!-- Proxy OrdineVenditaRiga -->
                                                                                                  <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "RigaOrdineCliente", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="OrdineVenditaRiga"><%label.write(out);%></label><%}%>
                                                                                                  <% 
  WebMultiSearchForm OrdineAcquistoRigaPrmOrdineVenditaRiga =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "OrdineVenditaRiga", false, false, true, 1, null, null); 
  OrdineAcquistoRigaPrmOrdineVenditaRiga.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmOrdineVenditaRiga.setAdditionalRestrictConditions("AnnoOrdineCliente,AnnoOrdine; NumeroOrdineCliente,NumeroOrdine"); 
  OrdineAcquistoRigaPrmOrdineVenditaRiga.write(out); 
%>
<!--<span class="multisearchform" id="OrdineVenditaRiga"></span>-->
                                                                                                </td>
                                                                                              </tr>
                                                                                        <!-- Fix 13681 Fine -->

											<tr id="SezNumOrdProd" name="SezNumOrdProd" style="display:none">
												<!-- Proxy a attivitaEsecutiva -->
												<td colspan="2" nowrap>
													<label class="thLabel" id="AttEsecProxyLbl">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "RifOrdPrdLbl", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label>
												</td>
												<!-- <td></td> -->
												<td colspan="4" nowrap>
														<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmAttivitaEsecutivaRel =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "AttivitaEsecutivaRel", false, false, true, 3, null, null); 
  OrdineAcquistoRigaPrmAttivitaEsecutivaRel.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmAttivitaEsecutivaRel.write(out); 
%>
<!--<span class="multisearchform" id="AttEsecProxy"></span>-->
												</td>
												<td style="display: none;">
													<% 
  WebTextInput OrdineAcquistoRigaPrmDettRigaOrdProduzione =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "DettRigaOrdProduzione"); 
  OrdineAcquistoRigaPrmDettRigaOrdProduzione.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmDettRigaOrdProduzione.getClassType()%>" id="<%=OrdineAcquistoRigaPrmDettRigaOrdProduzione.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmDettRigaOrdProduzione.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmDettRigaOrdProduzione.getName()%>" size="1" type="text"><% 
  OrdineAcquistoRigaPrmDettRigaOrdProduzione.write(out); 
%>

												</td>
											</tr>


										</table>
									</td>
								</tr>
								<!-- FINE RIGA 1 TABELLA PRINCIPALE -->
								<!-- INIZIO RIGA 2 TABELLA PRINCIPALE -->
								<tr>
									<td>
										<table cellpadding="1" cellspacing="1">
											<td align="left" style="width: 450px" valign="top">
												<fieldset>
													<legend><label class="thLabel" id="LabelGroupBoxQuantita">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "QuantitaGroupBox", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></legend>
													<table cellpadding="1" cellspacing="1" style="margin-left: 2px;">
														<tr style="height: 22px;">
															<!-- Campo Qta Acquisto -->
															<td nowrap style="width: 97px;">
																<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "QtaInUMAcq", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="QtaAcquistoField"><%label.write(out);%></label><%}%>
															</td>
															<td style="width: 80px">
																<% 
  WebTextInput OrdineAcquistoRigaPrmQtaInUMAcq =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "QtaInUMAcq"); 
  OrdineAcquistoRigaPrmQtaInUMAcq.setOnChange("variazioneQuantAcquistoRiga(false)"); 
  OrdineAcquistoRigaPrmQtaInUMAcq.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmQtaInUMAcq.getClassType()%>" id="<%=OrdineAcquistoRigaPrmQtaInUMAcq.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmQtaInUMAcq.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmQtaInUMAcq.getName()%>" size="<%=OrdineAcquistoRigaPrmQtaInUMAcq.getSize()%>" type="text"><% 
  OrdineAcquistoRigaPrmQtaInUMAcq.write(out); 
%>

															</td>
															<!-- Combo UM Acquisto -->
															<td colspan="2">
																<% 
  WebSearchComboBox OrdineAcquistoRigaPrmRUMRif =  
     new com.thera.thermfw.web.WebSearchComboBox("OrdineAcquistoRigaPrm", "RUMRif", null, 2, "20", false, "getListaUMRiferimento"); 
  OrdineAcquistoRigaPrmRUMRif.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRUMRif.setOnChange("variazioneUnitaMisura()"); 
  OrdineAcquistoRigaPrmRUMRif.write(out); 
%>
<!--<span class="searchcombobox" id="UMAcqCombo" name="UMAcqCombo"></span>-->
															</td>
														</tr>
														<tr style="height: 26.6px;">
															<!-- Campo Qta Primaria -->
															<td nowrap>
																<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "QtaInUMPrmMag", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="QtaPrimariaField"><%label.write(out);%></label><%}%>
															</td>
															<td>
																<% 
  WebTextInput OrdineAcquistoRigaPrmQtaInUMPrmMag =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "QtaInUMPrmMag"); 
  OrdineAcquistoRigaPrmQtaInUMPrmMag.setOnChange("variazioneQuantPrimariaMagazzinoRiga(false)"); 
  OrdineAcquistoRigaPrmQtaInUMPrmMag.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmQtaInUMPrmMag.getClassType()%>" id="<%=OrdineAcquistoRigaPrmQtaInUMPrmMag.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmQtaInUMPrmMag.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmQtaInUMPrmMag.getName()%>" size="<%=OrdineAcquistoRigaPrmQtaInUMPrmMag.getSize()%>" type="text"><% 
  OrdineAcquistoRigaPrmQtaInUMPrmMag.write(out); 
%>

															</td>
															<!-- Campo Servizio UM Primaria -->
															<td colspan="2" nowrap>
																<% 
  WebTextInput OrdineAcquistoRigaPrmServizioUMPrmMag =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ServizioUMPrmMag"); 
  OrdineAcquistoRigaPrmServizioUMPrmMag.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmServizioUMPrmMag.getClassType()%>" id="<%=OrdineAcquistoRigaPrmServizioUMPrmMag.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmServizioUMPrmMag.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmServizioUMPrmMag.getName()%>" size="20" type="text"><% 
  OrdineAcquistoRigaPrmServizioUMPrmMag.write(out); 
%>

															</td>
															<td>&nbsp;</td>
														</tr>
														<tr id="SezUMSecondaria" name="SezUMSecondaria" style="display:none; height: 26.6px;">
															<!-- Campo Qta Secondaria -->
															<td nowrap>
																<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "QtaInUMSecMag", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="QtaSecondariaField"><%label.write(out);%></label><%}%>
															</td>
															<td>
																<% 
  WebTextInput OrdineAcquistoRigaPrmQtaInUMSecMag =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "QtaInUMSecMag"); 
  OrdineAcquistoRigaPrmQtaInUMSecMag.setOnChange("variazioneQuantSecondariaMagazzinoRiga(false)"); 
  OrdineAcquistoRigaPrmQtaInUMSecMag.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmQtaInUMSecMag.getClassType()%>" id="<%=OrdineAcquistoRigaPrmQtaInUMSecMag.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmQtaInUMSecMag.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmQtaInUMSecMag.getName()%>" size="<%=OrdineAcquistoRigaPrmQtaInUMSecMag.getSize()%>" type="text"><% 
  OrdineAcquistoRigaPrmQtaInUMSecMag.write(out); 
%>

															</td>
															<!-- Campo Servizio UM Secondaria -->
															<td colspan="2" nowrap>
																<% 
  WebTextInput OrdineAcquistoRigaPrmServizioUMSecMag =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ServizioUMSecMag"); 
  OrdineAcquistoRigaPrmServizioUMSecMag.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmServizioUMSecMag.getClassType()%>" id="<%=OrdineAcquistoRigaPrmServizioUMSecMag.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmServizioUMSecMag.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmServizioUMSecMag.getName()%>" size="20" type="text"><% 
  OrdineAcquistoRigaPrmServizioUMSecMag.write(out); 
%>

															</td>
															<td>&nbsp;</td>
														</tr>
														<!-- fix 12572 > -->
														<tr id="SezNumeroImballo" name="SezNumeroImballo" style="display:none; height: 26.6px;">
															<td nowrap><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "NumeroImballo", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="NumeroImballoField"><%label.write(out);%></label><%}%></td>
															<td><% 
  WebTextInput OrdineAcquistoRigaPrmNumeroImballo =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "NumeroImballo"); 
  OrdineAcquistoRigaPrmNumeroImballo.setOnChange("numeroImballoManuale()"); 
  OrdineAcquistoRigaPrmNumeroImballo.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmNumeroImballo.getClassType()%>" id="<%=OrdineAcquistoRigaPrmNumeroImballo.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmNumeroImballo.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmNumeroImballo.getName()%>" size="<%=OrdineAcquistoRigaPrmNumeroImballo.getSize()%>" type="text"><% 
  OrdineAcquistoRigaPrmNumeroImballo.write(out); 
%>
</td>
															<td><input id="UMRifFattoreConvNI" name="UMRifFattoreConvNI" type="hidden"></td>
														</tr>
														<!-- fix 12572 < -->
														<!-- Fix 3246 inizio -->
														<!-- Check Ricalcolo Quantita Fattore Conversione -->
														<tr style="height: 26.6px;">
															<td>&nbsp;</td>
															<td colspan="2" nowrap>
																<% 
  WebCheckBox OrdineAcquistoRigaPrmRicalcoloQtaFattoreConv =  
     new com.thera.thermfw.web.WebCheckBox("OrdineAcquistoRigaPrm", "RicalcoloQtaFattoreConv"); 
  OrdineAcquistoRigaPrmRicalcoloQtaFattoreConv.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input id="<%=OrdineAcquistoRigaPrmRicalcoloQtaFattoreConv.getId()%>" name="<%=OrdineAcquistoRigaPrmRicalcoloQtaFattoreConv.getName()%>" type="checkbox" value="Y"><%
  OrdineAcquistoRigaPrmRicalcoloQtaFattoreConv.write(out); 
%>

															</td>
															
															<td hidden="true" id="SezBottoneRicalcolaSec" nowrap>
																
<% 
  WebButton RicalcolaCoefficientePulsanteWebButton = new WebButton(); 
  RicalcolaCoefficientePulsanteWebButton.setImage(null); 
  RicalcolaCoefficientePulsanteWebButton.setResourceFile("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga"); 
  RicalcolaCoefficientePulsanteWebButton.setResourceId("RicalcolaSecondarie"); 
  RicalcolaCoefficientePulsanteWebButton.setResourceTooltipId(null); 
%>
<button class=" <%=RicalcolaCoefficientePulsanteWebButton.getButtonCSSClass()%>" id="RicalcolaCoefficientePulsante" name="RicalcolaCoefficientePulsante" onclick="ricalcolaCoefficiente()" title="<%=RicalcolaCoefficientePulsanteWebButton.getTitle()%>" type="button"><%RicalcolaCoefficientePulsanteWebButton.getBtnContent(out);%></button>
															</td>
														</tr>
														<!-- Fix 3246 fine -->
													</table>
												</fieldset>
											</td>
											<td style="width: 10px;"></td>
											<td align="left" style="width: 400px" valign="top">
												<fieldset>
													<legend><label class="thLabel" id="LabelGroupBoxDateConsegna">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "DateConsegnaGroupBox", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></legend>
													<table cellpadding="1" cellspacing="1" style="margin-left: 2px;">
														<tr>
															<!-- Campo Data Consegna Richiesta -->
															<td nowrap>
																<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "DataConsegnaRichiesta", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="DataConsegnaRichiestaField"><%label.write(out);%></label><%}%>
															</td>
															<td style="width: 15px;"></td>
															<td nowrap>
																<% 
  WebTextInput OrdineAcquistoRigaPrmDataConsegnaRichiesta =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "DataConsegnaRichiesta"); 
  OrdineAcquistoRigaPrmDataConsegnaRichiesta.setOnChange("gestDataConsegnaRichiesta()"); 
  OrdineAcquistoRigaPrmDataConsegnaRichiesta.setShowCalendarBtn(true); 
  OrdineAcquistoRigaPrmDataConsegnaRichiesta.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmDataConsegnaRichiesta.getClassType()%>" id="<%=OrdineAcquistoRigaPrmDataConsegnaRichiesta.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmDataConsegnaRichiesta.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmDataConsegnaRichiesta.getName()%>" size="12" type="text"><% 
  OrdineAcquistoRigaPrmDataConsegnaRichiesta.write(out); 
%>

															</td>
															<td style="width: 30px;"></td>
															<!-- Campo Settimana Consegna Richiesta -->
															<td nowrap>
																<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "SettConsegnaRichiesta", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="SettConsegnaRichiestaField"><%label.write(out);%></label><%}%>
															</td>
															<td style="width: 15px;"></td>
															<td>
																<% 
  WebTextInput OrdineAcquistoRigaPrmSettConsegnaRichiesta =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "SettConsegnaRichiesta"); 
  OrdineAcquistoRigaPrmSettConsegnaRichiesta.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmSettConsegnaRichiesta.getClassType()%>" id="<%=OrdineAcquistoRigaPrmSettConsegnaRichiesta.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmSettConsegnaRichiesta.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmSettConsegnaRichiesta.getName()%>" size="8" type="text"><% 
  OrdineAcquistoRigaPrmSettConsegnaRichiesta.write(out); 
%>

															</td>
														</tr>
														<tr>
															<!-- Campo Data Consegna Confermata -->
															<td nowrap>
																<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "DataConsegnaConfermata", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="DataConsegnaConfermataField"><%label.write(out);%></label><%}%>
															</td>
															<td style="width: 15px;"></td>
															<td>
																<% 
  WebTextInput OrdineAcquistoRigaPrmDataConsegnaConfermata =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "DataConsegnaConfermata"); 
  OrdineAcquistoRigaPrmDataConsegnaConfermata.setOnChange("gestDataConsegnaConfermata()"); 
  OrdineAcquistoRigaPrmDataConsegnaConfermata.setShowCalendarBtn(true); 
  OrdineAcquistoRigaPrmDataConsegnaConfermata.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmDataConsegnaConfermata.getClassType()%>" id="<%=OrdineAcquistoRigaPrmDataConsegnaConfermata.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmDataConsegnaConfermata.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmDataConsegnaConfermata.getName()%>" size="12" type="text"><% 
  OrdineAcquistoRigaPrmDataConsegnaConfermata.write(out); 
%>

															</td>
															<td style="width: 30px;"></td>
															<!-- Campo Settimana Consegna Confermata -->
															<td>
																<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "SettConsegnaConfermata", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="SettConsegnaConfermataField"><%label.write(out);%></label><%}%>
															</td>
															<td style="width: 15px;"></td>
															<td>
																<% 
  WebTextInput OrdineAcquistoRigaPrmSettConsegnaConfermata =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "SettConsegnaConfermata"); 
  OrdineAcquistoRigaPrmSettConsegnaConfermata.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmSettConsegnaConfermata.getClassType()%>" id="<%=OrdineAcquistoRigaPrmSettConsegnaConfermata.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmSettConsegnaConfermata.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmSettConsegnaConfermata.getName()%>" size="8" type="text"><% 
  OrdineAcquistoRigaPrmSettConsegnaConfermata.write(out); 
%>

															</td>
														</tr>
														<tr>
															<!-- Campo Data Consegna Produzione -->
															<td nowrap>
																<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "DataConsegnaProduzione", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="DataConsegnaProduzioneField"><%label.write(out);%></label><%}%>
															</td>
															<td style="width: 15px;"></td>
															<td>
																<% 
  WebTextInput OrdineAcquistoRigaPrmDataConsegnaProduzione =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "DataConsegnaProduzione"); 
  OrdineAcquistoRigaPrmDataConsegnaProduzione.setOnChange("gestDataConsegnaProduzione()"); 
  OrdineAcquistoRigaPrmDataConsegnaProduzione.setShowCalendarBtn(true); 
  OrdineAcquistoRigaPrmDataConsegnaProduzione.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmDataConsegnaProduzione.getClassType()%>" id="<%=OrdineAcquistoRigaPrmDataConsegnaProduzione.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmDataConsegnaProduzione.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmDataConsegnaProduzione.getName()%>" size="12" type="text"><% 
  OrdineAcquistoRigaPrmDataConsegnaProduzione.write(out); 
%>

															</td>
															<td style="width: 30px;"></td>
															<!-- Campo Settimana Consegna Produzione -->
															<td>
																<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "SettConsegnaProduzione", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="SettConsegnaProduzioneField"><%label.write(out);%></label><%}%>
															</td>
															<td style="width: 15px;"></td>
															<td>
																<% 
  WebTextInput OrdineAcquistoRigaPrmSettConsegnaProduzione =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "SettConsegnaProduzione"); 
  OrdineAcquistoRigaPrmSettConsegnaProduzione.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmSettConsegnaProduzione.getClassType()%>" id="<%=OrdineAcquistoRigaPrmSettConsegnaProduzione.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmSettConsegnaProduzione.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmSettConsegnaProduzione.getName()%>" size="8" type="text"><% 
  OrdineAcquistoRigaPrmSettConsegnaProduzione.write(out); 
%>

															</td>
														</tr>
													</table>
												</fieldset>
											</td>
										</table>
									</td>
								</tr>
								<!-- FINE RIGA 2 TABELLA PRINCIPALE -->
								<!-- INIZIO RIGA 3 TABELLA PRINCIPALE -->
								<tr>
									<td>
										<table>
											<tr>
												<!-- Dati Comuni Estesi - Stato -->
												<td nowrap>
													<table>
														<tr>
															<td style="width: 99px"><!-- Fix 39398 - fine -->
																<% 
   request.setAttribute("parentForm", OrdineAcquistoRigaPrmForm); 
   String CDForDatiComuniEstesi$it$thera$thip$cs$DatiComuniEstesi$jsp = "DatiComuniEstesi"; 
%>
<jsp:include page="/it/thera/thip/cs/DatiComuniEstesi.jsp" flush="true"> 
<jsp:param name="CDName" value="<%=CDForDatiComuniEstesi$it$thera$thip$cs$DatiComuniEstesi$jsp%>"/> 
</jsp:include> 
<!--<span class="subform" id="StatoDCECombo" name="StatoDCECombo"></span>-->
															</td>
														</tr>
													</table>
												</td>
               									<!-- fix 4033 ini -->
               									<td style="width: 276px"></td>	<!-- Fix 39398 -->
               									<td id="SezPulsanteAnalisiChimica" nowrap>
            									    
<% 
  WebButton AnalisiChimicaPulsanteWebButton = new WebButton(); 
  AnalisiChimicaPulsanteWebButton.setImage(null); 
  AnalisiChimicaPulsanteWebButton.setResourceFile(null); 
  AnalisiChimicaPulsanteWebButton.setResourceId(null); 
  AnalisiChimicaPulsanteWebButton.setResourceTooltipId(null); 
%>
<button class=" <%=AnalisiChimicaPulsanteWebButton.getButtonCSSClass()%>" id="AnalisiChimicaPulsante" name="AnalisiChimicaPulsante" onclick="onClickAnalisiChimica()" style="display:none; width: 90px" title="<%=AnalisiChimicaPulsanteWebButton.getTitle()%>" type="button"><%AnalisiChimicaPulsanteWebButton.getBtnContent(out);%></button>
               									</td>
               									<td><iframe name="analisiChimicaFrame" style="display:none"></iframe></td>
               									<!-- fix 4033 fine -->
											</tr>
										</table>
									</td>
								</tr>
								<!-- FINE RIGA 3 TABELLA PRINCIPALE -->
							</table>
							<!-- FINE TABELLA PRINCIPALE -->
						<% MainTabbed.endTab(); %> 
</div>

						<!-- **************************************************************************************** -->
						<!-- Cartella Prezzi/Sconti -->
						<div class="tabbed_page" id="<%=MainTabbed.getTabPageId("PrezziScontiTab")%>" style="width:100%;height:100%;overflow:auto;"><% MainTabbed.startTab("PrezziScontiTab"); %>
							<!-- Fix 39398 - inizio modifiche grafiche -->
							<!-- <table cellspacing="1" cellpadding="1"> -->
							<table cellpadding="2" cellspacing="2" style="padding: 3px; width: 100%;">
								<tr>
									<!-- Proxy Listino -->
									<td style="width: 150px">
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdListinoPrezzi", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="ListinoProxy"><%label.write(out);%></label><%}%>
									</td>
									<td colspan="3" nowrap>
										<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRListino =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "RListino", false, false, true, 1, null, null); 
  OrdineAcquistoRigaPrmRListino.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRListino.setOnKeyChange("variazioneListino()"); 
  OrdineAcquistoRigaPrmRListino.setAdditionalRestrictConditions("ServizioValutaTestata,RValuta;ServizioLavEstListino,LavorazioneEsterna"); 
  OrdineAcquistoRigaPrmRListino.write(out); 
%>
<!--<span class="multisearchform" id="ListinoProxy"></span>-->
									</td>
									<!-- Pulsante Ricalcola Dati Acquisto -->
		                        	<!-- Fix 2333 aggiunto l'ID al bottone-->
									<td colspan="2" id="SezBottoneRicalcola" nowrap>
									<!-- Fine Fix 2333 -->

										
<% 
  WebButton RicalcolaDatiAcquistoPulsanteWebButton = new WebButton(); 
  RicalcolaDatiAcquistoPulsanteWebButton.setImage(null); 
  RicalcolaDatiAcquistoPulsanteWebButton.setResourceFile("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga"); 
  RicalcolaDatiAcquistoPulsanteWebButton.setResourceId("RicalcoloDatiAcqPulsanteLabel"); 
  RicalcolaDatiAcquistoPulsanteWebButton.setResourceTooltipId(null); 
%>
<button class=" <%=RicalcolaDatiAcquistoPulsanteWebButton.getButtonCSSClass()%>" id="RicalcolaDatiAcquistoPulsante" name="RicalcolaDatiAcquistoPulsante" onclick="ricalcolaDatiAcquisto()" style="width: 130px;" title="<%=RicalcolaDatiAcquistoPulsanteWebButton.getTitle()%>" type="button"><%RicalcolaDatiAcquistoPulsanteWebButton.getBtnContent(out);%></button>
									</td>

									<td>&nbsp;</td>
								</tr>
								<tr>
									<!-- Campo Prezzo -->
									<td>
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "Prezzo", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="PrezzoField"><%label.write(out);%></label><%}%>
									</td>
									<td style="width: 250px">
										<% 
  WebTextInput OrdineAcquistoRigaPrmPrezzo =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "Prezzo"); 
  OrdineAcquistoRigaPrmPrezzo.setOnFocus("memorizzaPrezzo()"); 
  OrdineAcquistoRigaPrmPrezzo.setOnChange("settaggioManualeProvenienzaPrezzo()"); 
  OrdineAcquistoRigaPrmPrezzo.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmPrezzo.getClassType()%>" id="<%=OrdineAcquistoRigaPrmPrezzo.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmPrezzo.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmPrezzo.getName()%>" size="12" type="text"><% 
  OrdineAcquistoRigaPrmPrezzo.write(out); 
%>

										<label id="LabelValuta"></label>
									</td>
									<!-- Fix 05398 SL inizio -->
									<% { 
   WebFormCustomization fc = new com.thera.thermfw.web.WebFormCustomization("FORM", "FormCustomizeLivelloInterno1"); 
  fc.setParent(OrdineAcquistoRigaPrmForm); 
  fc.customize(); } 
%>
<!--<span class="formCustomization" id="campiFormLivelloInterno1"></span>-->
									<!-- fine Fix 05398 SL -->
									<!--          <td>&nbsp;</td>          <td>&nbsp;</td>          -->
									<!-- Campo Prezzo Extra -->
									<td style="width: 110px">
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "PrezzoExtra", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="PrezzoExtraField"><%label.write(out);%></label><%}%>
									</td>
									<td style="width: 170px">
										<% 
  WebTextInput OrdineAcquistoRigaPrmPrezzoExtra =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "PrezzoExtra"); 
  OrdineAcquistoRigaPrmPrezzoExtra.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmPrezzoExtra.getClassType()%>" id="<%=OrdineAcquistoRigaPrmPrezzoExtra.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmPrezzoExtra.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmPrezzoExtra.getName()%>" size="12" type="text"><% 
  OrdineAcquistoRigaPrmPrezzoExtra.write(out); 
%>

									</td>
									<!-- Fix 2333 -->
									<td id="SezDettaglioPrezzi" style="display:none">
										<button id="DettaglioPrezzi" name="DettaglioPrezzi" onclick="apriDettaglioPrezzi('RecuperaPrezziExtraOrdAcq','true', 'Y')" style="width: 90px" type="button">
											<label class="thLabel" id="LabelDettaglioPrezzi">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.base.prezziExtra.resources.DocOrdRiga", "DettaglioPrezzi", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label>
										</button>
									</td>
									<!-- Fine Fix 2333 -->
									<!-- Fix 1495 -->
									<td id="SezDataInizioValiditaLstLbl" style="display:none; width: 75px;">
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "DataInizioValiditaLst", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="DataInizioValiditaLstField"><%label.write(out);%></label><%}%>
									</td>
									<td id="SezDataInizioValiditaLst" style="display:none">
										<% 
  WebTextInput OrdineAcquistoRigaPrmDataInizioValiditaLst =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "DataInizioValiditaLst"); 
  OrdineAcquistoRigaPrmDataInizioValiditaLst.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmDataInizioValiditaLst.getClassType()%>" id="<%=OrdineAcquistoRigaPrmDataInizioValiditaLst.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmDataInizioValiditaLst.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmDataInizioValiditaLst.getName()%>" size="12" type="text"><% 
  OrdineAcquistoRigaPrmDataInizioValiditaLst.write(out); 
%>

									</td>
									<!-- Fine fix 1495 -->

									<td>&nbsp;</td>
								</tr>
								<tr>
									<!-- Campo Sconto 1 Articolo -->
									<td>
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "ScontoArticolo1", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="ScontoArticolo1Field"><%label.write(out);%></label><%}%>
									</td>
									<td>
										<% 
  WebTextInput OrdineAcquistoRigaPrmScontoArticolo1 =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ScontoArticolo1"); 
  OrdineAcquistoRigaPrmScontoArticolo1.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmScontoArticolo1.getClassType()%>" id="<%=OrdineAcquistoRigaPrmScontoArticolo1.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmScontoArticolo1.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmScontoArticolo1.getName()%>" size="7" type="text"><% 
  OrdineAcquistoRigaPrmScontoArticolo1.write(out); 
%>

									</td>
									<!--<td>&nbsp;</td>-->
									<!-- Campo Sconto 2 Articolo -->
									<td>
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "ScontoArticolo2", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="ScontoArticolo2Field"><%label.write(out);%></label><%}%>
									</td>
									<td>
										<% 
  WebTextInput OrdineAcquistoRigaPrmScontoArticolo2 =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ScontoArticolo2"); 
  OrdineAcquistoRigaPrmScontoArticolo2.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmScontoArticolo2.getClassType()%>" id="<%=OrdineAcquistoRigaPrmScontoArticolo2.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmScontoArticolo2.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmScontoArticolo2.getName()%>" size="7" type="text"><% 
  OrdineAcquistoRigaPrmScontoArticolo2.write(out); 
%>

									</td>
									<!--<td>&nbsp;</td>-->
									<!-- Campo Maggiorazione -->
									<td style="width: 110px"><!-- Fix 39398 - fine -->
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "Maggiorazione", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="MaggiorazioneField"><%label.write(out);%></label><%}%>
									</td>
									<td>
										<% 
  WebTextInput OrdineAcquistoRigaPrmMaggiorazione =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "Maggiorazione"); 
  OrdineAcquistoRigaPrmMaggiorazione.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmMaggiorazione.getClassType()%>" id="<%=OrdineAcquistoRigaPrmMaggiorazione.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmMaggiorazione.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmMaggiorazione.getName()%>" size="7" type="text"><% 
  OrdineAcquistoRigaPrmMaggiorazione.write(out); 
%>

									</td>
								</tr>
								<tr>
									<!-- Proxy Sconto -->
									<td nowrap>
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdSconto", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="ScontoProxy"><%label.write(out);%></label><%}%>
									</td>
									<td colspan="3" nowrap>
										<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRSconto =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "RSconto", false, false, true, 1, null, null); 
  OrdineAcquistoRigaPrmRSconto.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRSconto.write(out); 
%>
<!--<span class="multisearchform" id="ScontoProxy"></span>-->
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<!-- Campo Sconto Fornitore -->
									<td>
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "PrcScontoIntestatario", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="ScontoFornitoreField"><%label.write(out);%></label><%}%>
									</td>
									<td>
										<% 
  WebTextInput OrdineAcquistoRigaPrmPrcScontoIntestatario =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "PrcScontoIntestatario"); 
  OrdineAcquistoRigaPrmPrcScontoIntestatario.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmPrcScontoIntestatario.getClassType()%>" id="<%=OrdineAcquistoRigaPrmPrcScontoIntestatario.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmPrcScontoIntestatario.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmPrcScontoIntestatario.getName()%>" size="7" type="text"><% 
  OrdineAcquistoRigaPrmPrcScontoIntestatario.write(out); 
%>

									</td>
									<!--<td>&nbsp;</td>-->
									<!-- Campo Sconto Modal. -->
									<td>
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "PrcScontoModalita", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="ScontoModalitaField"><%label.write(out);%></label><%}%>
									</td>
									<td>
										<% 
  WebTextInput OrdineAcquistoRigaPrmPrcScontoModalita =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "PrcScontoModalita"); 
  OrdineAcquistoRigaPrmPrcScontoModalita.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmPrcScontoModalita.getClassType()%>" id="<%=OrdineAcquistoRigaPrmPrcScontoModalita.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmPrcScontoModalita.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmPrcScontoModalita.getName()%>" size="7" type="text"><% 
  OrdineAcquistoRigaPrmPrcScontoModalita.write(out); 
%>

									</td>
								</tr>
								<tr>
									<!-- Proxy Sconto Modal -->
									<td>
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdScontoModalita", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="ScontoModalitaProxy"><%label.write(out);%></label><%}%>
									</td>
									<td colspan="3" nowrap>
										<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRScontoModalita =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "RScontoModalita", false, false, true, 1, null, null); 
  OrdineAcquistoRigaPrmRScontoModalita.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRScontoModalita.write(out); 
%>
<!--<span class="multisearchform" id="ScontoModalitaProxy"></span>-->
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr rowspan="2">
									<!-- Proxy Assoggettamento IVA -->
									<td>
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdAssoggettamentoIVA", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="AssoggettamentoIVAProxy"><%label.write(out);%></label><%}%>
									</td>
									<td colspan="3" nowrap>
										<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRAssoggettamentoIVA =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "RAssoggettamentoIVA", false, false, true, 1, null, null); 
  OrdineAcquistoRigaPrmRAssoggettamentoIVA.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRAssoggettamentoIVA.write(out); 
%>
<!--<span class="multisearchform" id="AssoggettamentoIVAProxy"></span>-->
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr style="display: none;">
									<td>&nbsp;</td>
								</tr>
								<tr>
									<!-- Combo Riferimento Prezzo -->
									<td>
 										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "RiferimentoUMPrezzo", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="RiferimentoPrezzoCombo"><%label.write(out);%></label><%}%>
									</td>
									<td>
										<% 
  WebComboBox OrdineAcquistoRigaPrmRiferimentoUMPrezzo =  
     new com.thera.thermfw.web.WebComboBox("OrdineAcquistoRigaPrm", "RiferimentoUMPrezzo", null); 
  OrdineAcquistoRigaPrmRiferimentoUMPrezzo.setParent(OrdineAcquistoRigaPrmForm); 
%>
<select id="<%=OrdineAcquistoRigaPrmRiferimentoUMPrezzo.getId()%>" name="<%=OrdineAcquistoRigaPrmRiferimentoUMPrezzo.getName()%>"><% 
  OrdineAcquistoRigaPrmRiferimentoUMPrezzo.write(out); 
%> 

											
										</select>
									</td>
								</tr>
								<tr>
									<!-- Combo Tipo Prezzo -->
									<td>
 										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "TipoPrezzo", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="TipoPrezzoCombo"><%label.write(out);%></label><%}%>
									</td>
									<td colspan="2">
										<% 
  WebComboBox OrdineAcquistoRigaPrmTipoPrezzo =  
     new com.thera.thermfw.web.WebComboBox("OrdineAcquistoRigaPrm", "TipoPrezzo", null); 
  OrdineAcquistoRigaPrmTipoPrezzo.setParent(OrdineAcquistoRigaPrmForm); 
%>
<select id="<%=OrdineAcquistoRigaPrmTipoPrezzo.getId()%>" name="<%=OrdineAcquistoRigaPrmTipoPrezzo.getName()%>"><% 
  OrdineAcquistoRigaPrmTipoPrezzo.write(out); 
%> 

											
										</select>
									</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<!-- Combo Provenienza Prezzo -->
									<td>
 										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "ProvenienzaPrezzo", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="ProvenienzaPrezzoCombo"><%label.write(out);%></label><%}%>
									</td>
									<td>
										<% 
  WebComboBox OrdineAcquistoRigaPrmProvenienzaPrezzo =  
     new com.thera.thermfw.web.WebComboBox("OrdineAcquistoRigaPrm", "ProvenienzaPrezzo", null); 
  OrdineAcquistoRigaPrmProvenienzaPrezzo.setParent(OrdineAcquistoRigaPrmForm); 
%>
<select id="<%=OrdineAcquistoRigaPrmProvenienzaPrezzo.getId()%>" name="<%=OrdineAcquistoRigaPrmProvenienzaPrezzo.getName()%>"><% 
  OrdineAcquistoRigaPrmProvenienzaPrezzo.write(out); 
%> 

											
										</select>
									</td>
								</tr>
							</table>
						<% MainTabbed.endTab(); %> 
</div>


						<!-- **************************************************************************************** -->
						<!-- Cartella Cond. evasione -->
						<div class="tabbed_page" id="<%=MainTabbed.getTabPageId("CondEvasioneTab")%>" style="width:100%;height:100%;overflow:auto;"><% MainTabbed.startTab("CondEvasioneTab"); %>
							<!-- Fix 39398 - inizio modifiche grafiche -->
							<!-- <table cellspacing="1" cellpadding="1"> -->
							<table cellpadding="2" cellspacing="2" style="padding: 3px; width: 100%;">
							<!-- Fix 21505 ini-->
							<tr id="OffAcq" style="display: none">
							<!--<tr>-->
							<!-- Fix 21505 fni-->
									<td>
										<table cellpadding="1" cellspacing="1" style="width: 100%;">
											<tr>
												<td align="left" valign="top">
													<fieldset>
													<legend><label class="thLabel" id="LabelGroupBoxOffertaFornitore">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "LabelGroupBoxOffertaFornitore", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></legend>
														<table cellpadding="1" cellspacing="1">
															<tr>
																<td nowrap style="width: 148px"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "NumOffertaAcqFormattato", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="NumOffertaAcqFormattato"><%label.write(out);%></label><%}%></td>
																<td style="width: 220px"><% 
  WebTextInput OrdineAcquistoRigaPrmNumOffertaAcqFormattato =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "NumOffertaAcqFormattato"); 
  OrdineAcquistoRigaPrmNumOffertaAcqFormattato.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmNumOffertaAcqFormattato.getClassType()%>" id="<%=OrdineAcquistoRigaPrmNumOffertaAcqFormattato.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmNumOffertaAcqFormattato.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmNumOffertaAcqFormattato.getName()%>" size="20" type="text"><% 
  OrdineAcquistoRigaPrmNumOffertaAcqFormattato.write(out); 
%>
</td>

																<td nowrap style="width: 100px"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "DataOffertaFornitore", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="DataOffertaFornitore"><%label.write(out);%></label><%}%></td>
																<td><% 
  WebTextInput OrdineAcquistoRigaPrmDataOffertaFornitore =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "DataOffertaFornitore"); 
  OrdineAcquistoRigaPrmDataOffertaFornitore.setShowCalendarBtn(true); 
  OrdineAcquistoRigaPrmDataOffertaFornitore.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmDataOffertaFornitore.getClassType()%>" id="<%=OrdineAcquistoRigaPrmDataOffertaFornitore.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmDataOffertaFornitore.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmDataOffertaFornitore.getName()%>" size="20" type="text"><% 
  OrdineAcquistoRigaPrmDataOffertaFornitore.write(out); 
%>
</td>
																<!--                  <td nowrap="true"><label for="RigaOfferta">RigaOfferta</label></td>                 <td><input type ="text" id="RigaOfferta" name="RigaOfferta" size="20"/></td> -->
															</tr>
														</table>
													</fieldset>
												</td>
											</tr>
										</table>
								   </td>
								</tr>
								<!-- Fix 21505 ini-->
								<tr id="RichAcq" style="display: none">
									<td>
										<table cellpadding="1" cellspacing="1" style="width: 100%;">
											<tr>
												<td align="left" valign="top">
													<fieldset>
													<legend><label class="thLabel" id="LabelGroupBoxRichiestaAcquisto">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "LabelGroupBoxRichiestaAcquisto", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></legend>
														<table cellpadding="1" cellspacing="1">
															<tr>
																<td nowrap style="width: 148px"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "NumRichiestaAcqFormattato", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="NumRichiestaAcqFormattato"><%label.write(out);%></label><%}%></td>
																<td style="width: 220px"><% 
  WebTextInput OrdineAcquistoRigaPrmNumRichiestaAcqFormattato =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "NumRichiestaAcqFormattato"); 
  OrdineAcquistoRigaPrmNumRichiestaAcqFormattato.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmNumRichiestaAcqFormattato.getClassType()%>" id="<%=OrdineAcquistoRigaPrmNumRichiestaAcqFormattato.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmNumRichiestaAcqFormattato.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmNumRichiestaAcqFormattato.getName()%>" size="20" type="text"><% 
  OrdineAcquistoRigaPrmNumRichiestaAcqFormattato.write(out); 
%>
</td>

																<td nowrap style="width: 100px"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "DataRichiestaAcquisto", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="DataRichiestaAcquisto"><%label.write(out);%></label><%}%></td>
																<td><% 
  WebTextInput OrdineAcquistoRigaPrmDataRichiestaAcquisto =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "DataRichiestaAcquisto"); 
  OrdineAcquistoRigaPrmDataRichiestaAcquisto.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmDataRichiestaAcquisto.getClassType()%>" id="<%=OrdineAcquistoRigaPrmDataRichiestaAcquisto.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmDataRichiestaAcquisto.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmDataRichiestaAcquisto.getName()%>" size="20" type="text"><% 
  OrdineAcquistoRigaPrmDataRichiestaAcquisto.write(out); 
%>
</td>
																<!--                  <td nowrap="true"><label for="RigaRda">RigaRda</label></td>                 <td><input type ="text" id="RigaRda" name="RigaRda" size="20"/></td> -->
															</tr>
														</table>
													</fieldset>
												</td>
											</tr>
										</table>
								   </td>
								</tr>
								<!-- Fix 21505 fni-->
								<tr><td><table>
										<!-- Fix 24021 inizio -->
										<tr>           								
              								<td nowrap style="width: 150px"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "RigaRda", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="RichiestaAcquistoRiga"><%label.write(out);%></label><%}%></td>
              								<td colspan="3" nowrap><% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRichiestaAcquistoRiga =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "RichiestaAcquistoRiga", false, false, true, 3, null, null); 
  OrdineAcquistoRigaPrmRichiestaAcquistoRiga.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRichiestaAcquistoRiga.write(out); 
%>
<!--<span class="multisearchform" id="RichiestaAcquistoRiga"></span>--></td>              									
            							</tr>
            							<tr>              								
              								<td nowrap><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "RigaOfferta", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="RigaOffertaFornitore"><%label.write(out);%></label><%}%></td>
              								<td colspan="3" nowrap><% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRigaOffertaFornitore =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "RigaOffertaFornitore", false, false, true, 3, null, null); 
  OrdineAcquistoRigaPrmRigaOffertaFornitore.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRigaOffertaFornitore.write(out); 
%>
<!--<span class="multisearchform" id="RigaOffertaFornitore"></span>--></td>              									
            							</tr>
										<!-- Fix 24021 fine -->
										<tr>
											<td>
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "Priorita", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="PrioritaField"><%label.write(out);%></label><%}%>
									</td>
									<td>
										<% 
  WebTextInput OrdineAcquistoRigaPrmPriorita =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "Priorita"); 
  OrdineAcquistoRigaPrmPriorita.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmPriorita.getClassType()%>" id="<%=OrdineAcquistoRigaPrmPriorita.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmPriorita.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmPriorita.getName()%>" size="5" type="text"><% 
  OrdineAcquistoRigaPrmPriorita.write(out); 
%>

									</td>
								</tr>
								<tr>
									<!-- Campo Perdita Residuo -->
									<td>
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "PrcPerditaResiduo", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="PerditaResiduoField"><%label.write(out);%></label><%}%>
									</td>
									<td style="width: 80px">
										<% 
  WebTextInput OrdineAcquistoRigaPrmPrcPerditaResiduo =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "PrcPerditaResiduo"); 
  OrdineAcquistoRigaPrmPrcPerditaResiduo.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmPrcPerditaResiduo.getClassType()%>" id="<%=OrdineAcquistoRigaPrmPrcPerditaResiduo.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmPrcPerditaResiduo.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmPrcPerditaResiduo.getName()%>" size="5" type="text"><% 
  OrdineAcquistoRigaPrmPrcPerditaResiduo.write(out); 
%>

									</td>
									<!-- <td>&nbsp;</td> -->
									<td>
										<% 
  WebCheckBox OrdineAcquistoRigaPrmRigaNonFrazionabile =  
     new com.thera.thermfw.web.WebCheckBox("OrdineAcquistoRigaPrm", "RigaNonFrazionabile"); 
  OrdineAcquistoRigaPrmRigaNonFrazionabile.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input id="<%=OrdineAcquistoRigaPrmRigaNonFrazionabile.getId()%>" name="<%=OrdineAcquistoRigaPrmRigaNonFrazionabile.getName()%>" type="checkbox" value="Y"><%
  OrdineAcquistoRigaPrmRigaNonFrazionabile.write(out); 
%>

									</td>
								</tr>
								<tr>
									<!-- Campo Gruppo Consegna -->
									<td>
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "GruppoConsegna", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="GruppoConsegnaField"><%label.write(out);%></label><%}%>
									</td>
									<td>
										<% 
  WebTextInput OrdineAcquistoRigaPrmGruppoConsegna =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "GruppoConsegna"); 
  OrdineAcquistoRigaPrmGruppoConsegna.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmGruppoConsegna.getClassType()%>" id="<%=OrdineAcquistoRigaPrmGruppoConsegna.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmGruppoConsegna.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmGruppoConsegna.getName()%>" size="5" type="text"><% 
  OrdineAcquistoRigaPrmGruppoConsegna.write(out); 
%>

									</td>
								</tr>
								<tr>
									<!-- Proxy Blocco Spedizione -->
									<td nowrap>
										<div id="lblMBEO" style="display:block">
											<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdMotivoBloccoSpedizione", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="BloccoSpedizioneProxy"><%label.write(out);%></label><%}%>
										</div>
									</td>
									<td colspan="3" nowrap>
										<div id="fldMBEO" style="display:block">
											<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRBloccoSpedizione =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "RBloccoSpedizione", false, false, true, 1, null, null); 
  OrdineAcquistoRigaPrmRBloccoSpedizione.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRBloccoSpedizione.write(out); 
%>
<!--<span class="multisearchform" id="BloccoSpedizioneProxy"></span>-->
										</div>
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<!-- Proxy Blocco Ricezione -->
									<td nowrap>
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdMotivoBloccoRicezione", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="BloccoRicezioneProxy"><%label.write(out);%></label><%}%>
									</td>
									<td colspan="3" nowrap>
										<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRBloccoRicezione =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "RBloccoRicezione", false, false, true, 1, null, null); 
  OrdineAcquistoRigaPrmRBloccoRicezione.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRBloccoRicezione.write(out); 
%>
<!--<span class="multisearchform" id="BloccoRicezioneProxy"></span>-->
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr rowspan="2">
									<!-- Proxy Blocco Pianif. -->
									<td nowrap>
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdMotivoBloccoPianif", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="BloccoPianifProxy"><%label.write(out);%></label><%}%>
									</td>
									<td colspan="3" nowrap>
										<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRBloccoPianif =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "RBloccoPianif", false, false, true, 1, null, null); 
  OrdineAcquistoRigaPrmRBloccoPianif.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRBloccoPianif.write(out); 
%>
<!--<span class="multisearchform" id="BloccoPianifProxy"></span>-->
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<!-- Proxy Responsabile Acquisti -->
									<td nowrap>
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdResponsabileAcquisti", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="ResponsabileAcquistiProxy"><%label.write(out);%></label><%}%>
									</td>
									<td colspan="3" nowrap>
										<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRResponsabileAcquisti =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "RResponsabileAcquisti", false, false, true, 1, null, null); 
  OrdineAcquistoRigaPrmRResponsabileAcquisti.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRResponsabileAcquisti.write(out); 
%>
<!--<span class="multisearchform" id="ResponsabileAcquistiProxy"></span>-->
									</td>
								</tr>
								</table></td></tr>
								<!-- Fix 1659 -->
								<tr>
								<td colspan="3">
									<table cellpadding="1" cellspacing="1" style="width: 100%">
									    <tr>
										<td align="left" valign="top" width="50%">
											<fieldset>
												<legend><label class="thLabel" id="DatiBolla">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.base.comuniVenAcq.resources.ComuniVenAcq", "DatiBolla", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></legend>
												<table cellpadding="1" cellspacing="1">
													<tr>
														<td nowrap style="width: 148px">
															<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "DataBolla", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="DataBolla"><%label.write(out);%></label><%}%>
														</td>
														<td style="width: 130px">
															<% 
  WebTextInput OrdineAcquistoRigaPrmDataBolla =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "DataBolla"); 
  OrdineAcquistoRigaPrmDataBolla.setShowCalendarBtn(true); 
  OrdineAcquistoRigaPrmDataBolla.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmDataBolla.getClassType()%>" id="<%=OrdineAcquistoRigaPrmDataBolla.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmDataBolla.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmDataBolla.getName()%>" size="<%=OrdineAcquistoRigaPrmDataBolla.getSize()%>" type="text"><% 
  OrdineAcquistoRigaPrmDataBolla.write(out); 
%>

														</td>
														<td nowrap style="width: 110px">
															<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "NumeroBolla", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="NumeroBolla"><%label.write(out);%></label><%}%>
														</td>
														<td>
															<% 
  WebTextInput OrdineAcquistoRigaPrmNumeroBolla =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "NumeroBolla"); 
  OrdineAcquistoRigaPrmNumeroBolla.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmNumeroBolla.getClassType()%>" id="<%=OrdineAcquistoRigaPrmNumeroBolla.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmNumeroBolla.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmNumeroBolla.getName()%>" size="<%=OrdineAcquistoRigaPrmNumeroBolla.getSize()%>" type="text"><% 
  OrdineAcquistoRigaPrmNumeroBolla.write(out); 
%>

														</td>
													</tr>
												</table>
											</fieldset>
										</td>
										</tr>
									</table>
								</td>
								</tr>
								<!-- Fine fix 1659 -->
								<tr>
									<td colspan="3">
										<table cellpadding="1" cellspacing="1" width="100%">
											<tr>
												<td align="left" valign="top" width="100%">
													<fieldset>
													<legend><label class="thLabel" id="DatiDiTrasporto">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "DatiDiTrasporto", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></legend>
													<table cellpadding="1" cellspacing="1">
														<tr>
															<td nowrap style="width: 148px">
																<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "PesoLordo", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="PesoLordo"><%label.write(out);%></label><%}%>
															</td>
															<td style="width: 130px">
																<% 
  WebTextInput OrdineAcquistoRigaPrmPesoLordo =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "PesoLordo"); 
  OrdineAcquistoRigaPrmPesoLordo.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmPesoLordo.getClassType()%>" id="<%=OrdineAcquistoRigaPrmPesoLordo.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmPesoLordo.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmPesoLordo.getName()%>" size="<%=OrdineAcquistoRigaPrmPesoLordo.getSize()%>" type="text"><% 
  OrdineAcquistoRigaPrmPesoLordo.write(out); 
%>

															</td>
															<td nowrap style="width: 110px">
																<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "PesoNetto", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="PesoNetto"><%label.write(out);%></label><%}%>
															</td>
															<td style="width: 140px">
																<% 
  WebTextInput OrdineAcquistoRigaPrmPesoNetto =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "PesoNetto"); 
  OrdineAcquistoRigaPrmPesoNetto.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmPesoNetto.getClassType()%>" id="<%=OrdineAcquistoRigaPrmPesoNetto.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmPesoNetto.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmPesoNetto.getName()%>" size="<%=OrdineAcquistoRigaPrmPesoNetto.getSize()%>" type="text"><% 
  OrdineAcquistoRigaPrmPesoNetto.write(out); 
%>

															</td>
															<td nowrap style="width: 80px">
																<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "Volume", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="Volume"><%label.write(out);%></label><%}%>
															</td>
															<td style="width: 120px"><!-- Fix 39398 - fine -->
																<% 
  WebTextInput OrdineAcquistoRigaPrmVolume =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "Volume"); 
  OrdineAcquistoRigaPrmVolume.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmVolume.getClassType()%>" id="<%=OrdineAcquistoRigaPrmVolume.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmVolume.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmVolume.getName()%>" size="<%=OrdineAcquistoRigaPrmVolume.getSize()%>" type="text"><% 
  OrdineAcquistoRigaPrmVolume.write(out); 
%>

															</td>
															<td>
																<% 
  WebCheckBox OrdineAcquistoRigaPrmRicalcolaPesiEVolume =  
     new com.thera.thermfw.web.WebCheckBox("OrdineAcquistoRigaPrm", "RicalcolaPesiEVolume"); 
  OrdineAcquistoRigaPrmRicalcolaPesiEVolume.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input id="<%=OrdineAcquistoRigaPrmRicalcolaPesiEVolume.getId()%>" name="<%=OrdineAcquistoRigaPrmRicalcolaPesiEVolume.getName()%>" type="checkbox" value="Y"><%
  OrdineAcquistoRigaPrmRicalcolaPesiEVolume.write(out); 
%>

															</td>
														</tr>
													</table>
													</fieldset>
												</td>
											</tr>
										</table>
									</td>
								</tr>

							</table>
						<% MainTabbed.endTab(); %> 
</div>


						<!-- **************************************************************************************** -->
						<!-- Cartella Contabil. -->
						<div class="tabbed_page" id="<%=MainTabbed.getTabPageId("ContabilTab")%>" style="width:100%;height:100%;overflow:auto;"><% MainTabbed.startTab("ContabilTab"); %>
							<!-- Fix 39398 - inizio modifiche grafiche -->
							<!-- <table cellspacing="1" cellpadding="1"> -->
							<table cellpadding="2" cellspacing="2" style="padding: 3px; width: 100%;">
								<tr>
									<!-- Proxy Commessa -->
									<td nowrap style="width: 149px">
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdCommessa", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="CommessaProxy"><%label.write(out);%></label><%}%>
									</td>
									<td colspan="2" nowrap>
										<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRCommessa =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "RCommessa", false, false, true, 1, null, null); 
  OrdineAcquistoRigaPrmRCommessa.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRCommessa.write(out); 
%>
<!--<span class="multisearchform" id="CommessaProxy"></span>-->
									</td>
								</tr>
								<tr>
									<!-- Proxy Centro Ricavo -->
									<td nowrap>
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdCentroRicavo", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="CentroRicavoProxy"><%label.write(out);%></label><%}%>
									</td>
									<td colspan="2" nowrap>
										<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRCentroCosto =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "RCentroCosto", false, false, true, 1, null, null); 
  OrdineAcquistoRigaPrmRCentroCosto.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRCentroCosto.write(out); 
%>
<!--<span class="multisearchform" id="CentroRicavoProxy"></span>-->
									</td>
								</tr>
								<tr>
									<!-- Proxy Gruppo Conti Analitica -->
									<td nowrap>
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdGrpCntCa", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="GruppoContiAnaliticaProxy"><%label.write(out);%></label><%}%>
									</td>
									<td nowrap style="width: 550px"><!-- Fix 39398 - fine -->
										<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRGruppoContiCA =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "RGruppoContiCA", false, false, true, 1, null, null); 
  OrdineAcquistoRigaPrmRGruppoContiCA.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRGruppoContiCA.write(out); 
%>
<!--<span class="multisearchform" id="GruppoContiAnaliticaProxy"></span>-->
									</td>

									<!-- 8286 - Inizio -->
              		<td nowrap>
									<button id="bottoneRicalcolaDatiCA" name="bottoneRicalcolaDatiCA" onclick="recuperaDatiCA()" type="button">
									<label class="thLabel" id="RicalcolaDatiCA">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.documentoAC.resources.DocumentoAcquisto", "RicalcolaDatiCA", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label>
									</button>
              		</td>

						  		<!-- 8286 - Fine -->
								</tr>

								<% 
   request.setAttribute("parentForm", OrdineAcquistoRigaPrmForm); 
   String CDForDatiCA$it$thera$thip$acquisti$generaleAC$DatiCA$jsp = "DatiCA"; 
%>
<jsp:include page="/it/thera/thip/acquisti/generaleAC/DatiCA.jsp" flush="true"> 
<jsp:param name="CDName" value="<%=CDForDatiCA$it$thera$thip$acquisti$generaleAC$DatiCA$jsp%>"/> 
</jsp:include> 
<!--<span class="subform" id="DatiCA"></span>-->

							</table>
						<% MainTabbed.endTab(); %> 
</div>

						<!-- **************************************************************************************** -->
						<!-- Cartella Commenti/Multimedia -->
						<div class="tabbed_page" id="<%=MainTabbed.getTabPageId("CommentiMultimediaTab")%>" style="width:100%;height:100%;overflow:auto;"><% MainTabbed.startTab("CommentiMultimediaTab"); %>
							<table height="20%" width="100%">
								<tr>
									<!-- Area Commenti -->
									<td nowrap>
										<table cellpadding="1" cellspacing="1" width="100%">
											<tr>
												<td>
													<% 
   request.setAttribute("parentForm", OrdineAcquistoRigaPrmForm); 
   String CDForCommentHandler$com$thera$thermfw$cbs$CommentHandler$jsp = "CommentHandler"; 
%>
<jsp:include page="/com/thera/thermfw/cbs/CommentHandler.jsp" flush="true"> 
<jsp:param name="CDName" value="<%=CDForCommentHandler$com$thera$thermfw$cbs$CommentHandler$jsp%>"/> 
</jsp:include> 
<!--<span class="subform" id="CommentiArea"></span>-->
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<!-- Fix 39398 - inizio modifiche grafiche -->
							<!-- <table cellspacing="1" cellpadding="1"> -->
							<table cellpadding="2" cellspacing="2" style="padding: 3px; width: 100%;">
								<tr>
									<!-- Proxy Documento MM -->
									<td nowrap style="width: 150px"><!-- Fix 39398 - fine -->
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "IdDocumentoMM", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="DocumentoMMProxy"><%label.write(out);%></label><%}%>
									</td>
									<td nowrap>
										<% 
  WebMultiSearchForm OrdineAcquistoRigaPrmRDocumentoMM =  
     new com.thera.thermfw.web.WebMultiSearchForm("OrdineAcquistoRigaPrm", "RDocumentoMM", false, false, true, 1, null, null); 
  OrdineAcquistoRigaPrmRDocumentoMM.setParent(OrdineAcquistoRigaPrmForm); 
  OrdineAcquistoRigaPrmRDocumentoMM.write(out); 
%>
<!--<span class="multisearchform" id="DocumentoMMProxy"></span>-->
									</td>
								</tr>
								<tr>
									<!-- Area Note -->
									<td nowrap>
										<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "Nota", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="NoteArea"><%label.write(out);%></label><%}%>
									</td>
									<td colspan="3">
										<% 
  WebTextInput OrdineAcquistoRigaPrmNota =  
     new com.thera.thermfw.web.WebTextArea("OrdineAcquistoRigaPrm", "Nota"); 
  OrdineAcquistoRigaPrmNota.setParent(OrdineAcquistoRigaPrmForm); 
%>
<textarea class="<%=OrdineAcquistoRigaPrmNota.getClassType()%>" cols="50" id="<%=OrdineAcquistoRigaPrmNota.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmNota.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmNota.getName()%>" rows="2" size="<%=OrdineAcquistoRigaPrmNota.getSize()%>"></textarea><% 
  OrdineAcquistoRigaPrmNota.write(out); 
%>

									</td>
								</tr>
							</table>
						<% MainTabbed.endTab(); %> 
</div>

						<!-- **************************************************************************************** -->
						<!-- Cartella Dettaglio lotti -->
						<div class="tabbed_page" id="<%=MainTabbed.getTabPageId("LottiTab")%>" style="width:100%;height:100%;overflow:auto;"><% MainTabbed.startTab("LottiTab"); %>
						<!-- 71XXX Softre Inizio -->
						<table cellpadding="2" cellspacing="2" style="padding: 3px; width: 100%">
							    <tr>
								<td>&nbsp;</td>
								</tr>
								<tr>
									<td nowrap="nowrap" colspan ="1" id="SezCodificaLottiTestori">
										<button style="width: 75px" name="CodificaLottiTestori" id="CodificaLottiTestori" 
										type="button" onClick="codificaLottiTestori()"><%= ResourceLoader.getString("it.testori.thip.magazzino.generalemag.resources.LottiTestori", "btnCreaLotti")%></button>
									</td>
									</table>
						<!-- 71XXX Softre Fine -->
							<table cellpadding="1" cellspacing="1" style="width: 100%">
								<tr>
									<!-- Griglia Lotti -->
									<td>
										<!--<span class="editgrid" id="LottiGrid">--><% 
  WebEditGrid OrdineAcquistoRigaPrmLotti =  
     new com.thera.thermfw.web.WebEditGrid("OrdineAcquistoRigaPrm", "Lotti", 6, new String[]{"IdAzienda", "IdArticolo", "IdLotto", "RLotto.IdLottoProdotto", "RLotto.IdLottoPrdFormattato", "QtaInUMAcq", "QtaInUMPrmMag", "QtaInUMSecMag", "QtaPropostaEvasione.QuantitaInUMRif", "QtaPropostaEvasione.QuantitaInUMPrm", "QtaPropostaEvasione.QuantitaInUMSec", "QtaAttesaEvasione.QuantitaInUMRif", "QtaAttesaEvasione.QuantitaInUMPrm", "QtaAttesaEvasione.QuantitaInUMSec", "StatoEvasione"}, 1, null, null,false,"com.thera.thermfw.web.servlet.GridActionAdapterForIndependentRow"); 
 OrdineAcquistoRigaPrmLotti.setParent(OrdineAcquistoRigaPrmForm); 
 OrdineAcquistoRigaPrmLotti.setNoControlRowKeys(false); 
 OrdineAcquistoRigaPrmLotti.addHideAsDefault("QtaPropostaEvasione.QuantitaInUMSec"); 
 OrdineAcquistoRigaPrmLotti.addHideAsDefault("QtaPropostaEvasione.QuantitaInUMRif"); 
 OrdineAcquistoRigaPrmLotti.addHideAsDefault("QtaAttesaEvasione.QuantitaInUMPrm"); 
 OrdineAcquistoRigaPrmLotti.addHideAsDefault("QtaPropostaEvasione.QuantitaInUMPrm"); 
 OrdineAcquistoRigaPrmLotti.addHideAsDefault("QtaAttesaEvasione.QuantitaInUMSec"); 
 OrdineAcquistoRigaPrmLotti.addHideAsDefault("RLotto.IdLottoProdotto"); 
 OrdineAcquistoRigaPrmLotti.addHideAsDefault("QtaAttesaEvasione.QuantitaInUMRif"); 
 OrdineAcquistoRigaPrmLotti.includeAction("DeleteRow",new WebMenuItem("GestioneMatricole", "action_submit", "new", "no", "it.thera.thip.magazzino.matricole.resources.LottoMatricola", "GestioneMatricoleBtn", "gestioneMatricole()", "none", true, null, "")); 
 OrdineAcquistoRigaPrmLotti.write(out); 
%>
<BR><% 
   request.setAttribute("parentForm", OrdineAcquistoRigaPrmForm); 
   String CDForLotti = "Lotti"; 
%>
<jsp:include page="/it/thera/thip/acquisti/ordineAC/OrdAcqRigaPrmLotto.jsp" flush="true"> 
<jsp:param name="EditGridCDName" value="<%=CDForLotti%>"/> 
<jsp:param name="Mode" value="NEW"/> 
</jsp:include> 
<!--</span>-->
									</td>
								</tr>
							</table>
						<% MainTabbed.endTab(); %> 
</div>

						<!-- **************************************************************************************** -->
						<!-- Cartella Spedito  -->
						<div class="tabbed_page" id="<%=MainTabbed.getTabPageId("SpeditoTab")%>" style="width:100%;height:100%;overflow:auto;"><% MainTabbed.startTab("SpeditoTab"); %>
							<!-- Fix 39398 - inizio modifiche grafiche -->
							<!-- <table cellspacing="3" cellpadding="3" width="100%"> -->
							<table cellpadding="2" cellspacing="2" style="padding: 3px; width: 100%;">
								<tr>
									<td style="width: 150px">&nbsp;</td>
									<td>
										<table height="100%" width="100%">
											<tr>
												<td style="width: 150px"><label class="thLabel" id="UMAcqLabel">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "UMAcquistoLabel", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></td>
												<td style="width: 150px"><label class="thLabel" id="UMPrmLabel">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "UMPrmLabel", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></td>
												<td><label class="thLabel" id="UMSecLabel">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "UMSecLabel", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></td><!-- Fix 39398 - fine -->
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td nowrap><label class="thLabel" id="QtaOrdinataLabel">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "QtaOrdinataLabel", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></td>
									<td><% 
   request.setAttribute("parentForm", OrdineAcquistoRigaPrmForm); 
   String CDForQuantitaOrdinata$it$thera$thip$base$comuniVenAcq$QuantitaInUMRif$jsp = "QuantitaOrdinata"; 
%>
<jsp:include page="/it/thera/thip/base/comuniVenAcq/QuantitaInUMRif.jsp" flush="true"> 
<jsp:param name="CDName" value="<%=CDForQuantitaOrdinata$it$thera$thip$base$comuniVenAcq$QuantitaInUMRif$jsp%>"/> 
</jsp:include> 
<!--<span class="subform" id="QuantitaOrdinataField" name="QuantitaOrdinataField"></span>--></td>
								</tr>
								<tr>
									<td nowrap><label class="thLabel" id="QtaPropostaEvasioneLabel">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "QtaPropostaEvasioneLabel", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></td>
									<td><% 
   request.setAttribute("parentForm", OrdineAcquistoRigaPrmForm); 
   String CDForQtaPropostaEvasione$it$thera$thip$base$comuniVenAcq$QuantitaInUMRif$jsp = "QtaPropostaEvasione"; 
%>
<jsp:include page="/it/thera/thip/base/comuniVenAcq/QuantitaInUMRif.jsp" flush="true"> 
<jsp:param name="CDName" value="<%=CDForQtaPropostaEvasione$it$thera$thip$base$comuniVenAcq$QuantitaInUMRif$jsp%>"/> 
</jsp:include> 
<!--<span class="subform" id="QtaPropostaEvasioneField" name="QtaPropostaEvasioneField"></span>--></td>
								</tr>
								<tr>
									<td nowrap><label class="thLabel" id="QtaAttesaEvasioneLabel">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "QtaAttesaEvasioneLabel", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></td>
									<td><% 
   request.setAttribute("parentForm", OrdineAcquistoRigaPrmForm); 
   String CDForQtaAttesaEvasione$it$thera$thip$base$comuniVenAcq$QuantitaInUMRif$jsp = "QtaAttesaEvasione"; 
%>
<jsp:include page="/it/thera/thip/base/comuniVenAcq/QuantitaInUMRif.jsp" flush="true"> 
<jsp:param name="CDName" value="<%=CDForQtaAttesaEvasione$it$thera$thip$base$comuniVenAcq$QuantitaInUMRif$jsp%>"/> 
</jsp:include> 
<!--<span class="subform" id="QtaAttesaEvasioneField" name="QtaAttesaEvasioneField"></span>--></td>
								</tr>
								<tr>
									<td nowrap><label class="thLabel" id="QtaRicevutaLabel">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "QtaRicevutaLabel", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></td>
									<td><% 
   request.setAttribute("parentForm", OrdineAcquistoRigaPrmForm); 
   String CDForQuantitaRicevuta$it$thera$thip$base$comuniVenAcq$QuantitaInUMRif$jsp = "QuantitaRicevuta"; 
%>
<jsp:include page="/it/thera/thip/base/comuniVenAcq/QuantitaInUMRif.jsp" flush="true"> 
<jsp:param name="CDName" value="<%=CDForQuantitaRicevuta$it$thera$thip$base$comuniVenAcq$QuantitaInUMRif$jsp%>"/> 
</jsp:include> 
<!--<span class="subform" id="QuantitaRicevutaField" name="QuantitaRicevutaField"></span>--></td>
								</tr>
<!--         <tr>          <td nowrap="true"><label id="QtaPrevistaLavEsternaLabel">Qta Prevista Lavorazione Esterna</label></td>          <span class="subform" name="QuantitaPrevistaLavEsternaField" id="QuantitaPrevistaLavEsternaField"></span>         </tr>         <tr>          <td nowrap="true"><label id="QtaSpeditaLavEsternaLabel">Qta Spedita Lavorazione Esterna</label></td>          <span class="subform" name="QuantitaSpeditaLavEsternaField" id="QuantitaSpeditaLavEsternaField"></span>         </tr>         <tr>          <td nowrap="true"><label id="QtaImpiegataLavEsternaLabel">Qta Impiegata Lavorazione Esterna</label></td>          <span class="subform" name="QuantitaImpiegLavEsternaField" id="QuantitaImpiegLavEsternaField"></span>         </tr> -->

							</table>
							<!-- Fix 39398 - inizio modifiche grafiche -->
							<!-- <table cellspacing="4" cellpadding="4"> -->
							<table cellpadding="2" cellspacing="2" style="padding: 3px; width: 100%;">
								<tr>
									<td>
										<table cellpadding="1" cellspacing="1">
											<td style="width: 300px">
												<fieldset>
													<legend><label class="thLabel" id="LabelGroupBoxOrdine">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "OrdineGroupBox", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></legend>
													<table cellpadding="1" cellspacing="1">
														<tr>
															<!-- Combo Stato Evasione -->
															<td nowrap style="width: 150px">
																<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "StatoEvasione", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="StatoEvasioneCombo"><%label.write(out);%></label><%}%>
															</td>
															<td>
																<% 
  WebComboBox OrdineAcquistoRigaPrmStatoEvasione =  
     new com.thera.thermfw.web.WebComboBox("OrdineAcquistoRigaPrm", "StatoEvasione", null); 
  OrdineAcquistoRigaPrmStatoEvasione.setParent(OrdineAcquistoRigaPrmForm); 
%>
<select id="<%=OrdineAcquistoRigaPrmStatoEvasione.getId()%>" name="<%=OrdineAcquistoRigaPrmStatoEvasione.getName()%>"><% 
  OrdineAcquistoRigaPrmStatoEvasione.write(out); 
%> 

																	
																</select>
															</td>
														</tr>
														<tr>
															<!-- Check Saldo Manuale -->
															<td nowrap>
																<% 
  WebCheckBox OrdineAcquistoRigaPrmSaldoManuale =  
     new com.thera.thermfw.web.WebCheckBox("OrdineAcquistoRigaPrm", "SaldoManuale"); 
  OrdineAcquistoRigaPrmSaldoManuale.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input id="<%=OrdineAcquistoRigaPrmSaldoManuale.getId()%>" name="<%=OrdineAcquistoRigaPrmSaldoManuale.getName()%>" type="checkbox" value="Y"><%
  OrdineAcquistoRigaPrmSaldoManuale.write(out); 
%>

															</td>
														</tr>
														<tr>
															<!-- Pulsante Salda/Riapri -->
															<td>
																
<% 
  WebButton SaldaRiapriPulsanteWebButton = new WebButton(); 
  SaldaRiapriPulsanteWebButton.setImage(null); 
  SaldaRiapriPulsanteWebButton.setResourceFile(null); 
  SaldaRiapriPulsanteWebButton.setResourceId(null); 
  SaldaRiapriPulsanteWebButton.setResourceTooltipId(null); 
%>
<button class=" <%=SaldaRiapriPulsanteWebButton.getButtonCSSClass()%>" id="SaldaRiapriPulsante" name="SaldaRiapriPulsante" onclick="gestioneSaldaRiapri()" style="width: 50px" title="<%=SaldaRiapriPulsanteWebButton.getTitle()%>" type="button"><%SaldaRiapriPulsanteWebButton.getBtnContent(out);%></button>
															</td>
														</tr>
													</table>
												</fieldset>
											</td>
											<td align="left" id="groupEvasioneComponenti" style="display:none; width: 300px" valign="top">
												<fieldset>
													<legend><label class="thLabel" id="LabelGroupBoxComponenti">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "ComponentiGroupBox", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></legend>
													<table cellpadding="1" cellspacing="1">
														<tr>
															<!-- Combo Stato Spedizione Componenti -->
															<td nowrap style="width: 120px">
																<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "StatoSpedizioneComp", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="StatoSpedizioneCompCombo"><%label.write(out);%></label><%}%>
															</td>
															<td>
																<% 
  WebComboBox OrdineAcquistoRigaPrmStatoSpedizioneComp =  
     new com.thera.thermfw.web.WebComboBox("OrdineAcquistoRigaPrm", "StatoSpedizioneComp", null); 
  OrdineAcquistoRigaPrmStatoSpedizioneComp.setParent(OrdineAcquistoRigaPrmForm); 
%>
<select id="<%=OrdineAcquistoRigaPrmStatoSpedizioneComp.getId()%>" name="<%=OrdineAcquistoRigaPrmStatoSpedizioneComp.getName()%>"><% 
  OrdineAcquistoRigaPrmStatoSpedizioneComp.write(out); 
%> 

																	
																</select>
															</td>
														</tr>
														<tr>
															<!-- Pulsante Salda/Riapri -->
															<td>
																
<% 
  WebButton SaldaRiapriComponentePulsanteWebButton = new WebButton(); 
  SaldaRiapriComponentePulsanteWebButton.setImage(null); 
  SaldaRiapriComponentePulsanteWebButton.setResourceFile(null); 
  SaldaRiapriComponentePulsanteWebButton.setResourceId(null); 
  SaldaRiapriComponentePulsanteWebButton.setResourceTooltipId(null); 
%>
<button class=" <%=SaldaRiapriComponentePulsanteWebButton.getButtonCSSClass()%>" id="SaldaRiapriComponentePulsante" name="SaldaRiapriComponentePulsante" onclick="gestioneSaldaRiapriComponente()" style="width: 50px" title="<%=SaldaRiapriComponentePulsanteWebButton.getTitle()%>" type="button"><%SaldaRiapriComponentePulsanteWebButton.getBtnContent(out);%></button>
																<!--12094-->
															</td>
														</tr>
													</table>
												</fieldset>
											</td>
										</table>
									</td>
								</tr>
								<!-- Fix 05101 inizio -->
								<tr><td><table>
								<tr>
								   <td nowrap style="width: 150px"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "DataUltimoSollecito", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="DataUltimoSollecito"><%label.write(out);%></label><%}%></td>
									<td><!-- Fix 39398 - fine -->
										<% 
  WebTextInput OrdineAcquistoRigaPrmDataUltimoSollecito =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "DataUltimoSollecito"); 
  OrdineAcquistoRigaPrmDataUltimoSollecito.setShowCalendarBtn(true); 
  OrdineAcquistoRigaPrmDataUltimoSollecito.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmDataUltimoSollecito.getClassType()%>" id="<%=OrdineAcquistoRigaPrmDataUltimoSollecito.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmDataUltimoSollecito.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmDataUltimoSollecito.getName()%>" size="<%=OrdineAcquistoRigaPrmDataUltimoSollecito.getSize()%>" type="text"><% 
  OrdineAcquistoRigaPrmDataUltimoSollecito.write(out); 
%>

									</td>
							   </tr>
							   <tr>
								   <td nowrap><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "NumSollecito", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="NumSollecito"><%label.write(out);%></label><%}%></td>
									<td>
										<% 
  WebTextInput OrdineAcquistoRigaPrmNumSollecito =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "NumSollecito"); 
  OrdineAcquistoRigaPrmNumSollecito.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmNumSollecito.getClassType()%>" id="<%=OrdineAcquistoRigaPrmNumSollecito.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmNumSollecito.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmNumSollecito.getName()%>" size="<%=OrdineAcquistoRigaPrmNumSollecito.getSize()%>" type="text"><% 
  OrdineAcquistoRigaPrmNumSollecito.write(out); 
%>

									</td>
							   </tr>
							  </table></td></tr>
							   <!-- Fix 05101 fine -->

							</table>
						<% MainTabbed.endTab(); %> 
</div>

						<!-- ************************************************************************************************ -->
						<!-- Cartella Riepilogo  -->
						<div class="tabbed_page" id="<%=MainTabbed.getTabPageId("RiepilogoTab")%>" style="width:100%;height:100%;overflow:auto;"><% MainTabbed.startTab("RiepilogoTab"); %>
							<!-- Fix 39398 - inizio modifiche grafiche -->
							<!-- <table cellspacing="1" cellpadding="1"> -->
							<table cellpadding="2" cellspacing="2" style="padding: 3px; width: 100%;">
								<tr id="SezValoreValutaAzienda" name="SezValoreValutaAzienda" style="display:none">
									<td>
										<fieldset>
											<legend><label class="thLabel" id="LabelValutaOrdineVO">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "LabelValutaOrdine", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></legend>
											<table>
												<!-- Intestazioni -->
												<tr>
													<td style="width: 165px">&nbsp;</td>
													<td colspan="2" style="width: 150px">
														<label class="thLabel" id="LabelValoreOrdinato">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "LabelValoreOrdinato", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label>
													</td>
													<td style="width: 10px">&nbsp;</td>
													<td colspan="2" style="width: 150px">
														<label class="thLabel" id="LabelValoreInEntrata">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "LabelValoreInEntrata", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label>
													</td>
													<td style="width: 10px">&nbsp;</td>
													<td colspan="2" style="width: 150px">
														<label class="thLabel" id="LabelValoreRicevuto">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "LabelValoreRicevuto", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label>
													</td>
													<td style="width: 10px">&nbsp;</td>
												</tr>
												<tr>
													<td nowrap><label class="thLabel" id="LabelGroupBoxValore">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "ValoreGroupBox", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></td>
													<!-- Campo Valore Ordine Ordinato in Valuta Ordine -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmValoreOrdinato =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ValoreOrdinato"); 
  OrdineAcquistoRigaPrmValoreOrdinato.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmValoreOrdinato.getClassType()%>" id="<%=OrdineAcquistoRigaPrmValoreOrdinato.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmValoreOrdinato.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmValoreOrdinato.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmValoreOrdinato.write(out); 
%>

													</td>
													<td style="width: 10px">&nbsp;</td>
													<!-- Campo Valore Ordine In Entrata in Valuta Ordine -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmValoreInEntrata =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ValoreInEntrata"); 
  OrdineAcquistoRigaPrmValoreInEntrata.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmValoreInEntrata.getClassType()%>" id="<%=OrdineAcquistoRigaPrmValoreInEntrata.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmValoreInEntrata.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmValoreInEntrata.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmValoreInEntrata.write(out); 
%>

													</td>
													<td style="width: 10px">&nbsp;</td>
													<!-- Campo Valore Ordine ricevuto in Valuta Ordine -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmValoreRicevuto =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ValoreRicevuto"); 
  OrdineAcquistoRigaPrmValoreRicevuto.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmValoreRicevuto.getClassType()%>" id="<%=OrdineAcquistoRigaPrmValoreRicevuto.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmValoreRicevuto.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmValoreRicevuto.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmValoreRicevuto.write(out); 
%>

													</td>
													<td style="width: 10px">&nbsp;</td>
												</tr>
												<tr>
													<td nowrap><label class="thLabel" id="LabelGroupBoxCosto">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "CostoGroupBox", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></td>
													<!-- Campo Costo Ordine Ordinato in Valuta Ordine -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmCostoOrdinato =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "CostoOrdinato"); 
  OrdineAcquistoRigaPrmCostoOrdinato.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmCostoOrdinato.getClassType()%>" id="<%=OrdineAcquistoRigaPrmCostoOrdinato.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmCostoOrdinato.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmCostoOrdinato.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmCostoOrdinato.write(out); 
%>

													</td>
													<td style="width: 10px">&nbsp;</td>
													<!-- Campo Costo Ordine In Entrata in Valuta Ordine -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmCostoInEntrata =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "CostoInEntrata"); 
  OrdineAcquistoRigaPrmCostoInEntrata.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmCostoInEntrata.getClassType()%>" id="<%=OrdineAcquistoRigaPrmCostoInEntrata.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmCostoInEntrata.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmCostoInEntrata.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmCostoInEntrata.write(out); 
%>

													</td>
													<td style="width: 10px">&nbsp;</td>
													<!-- Campo Costo Ordine ricevuto in Valuta Ordine -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmCostoRicevuto =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "CostoRicevuto"); 
  OrdineAcquistoRigaPrmCostoRicevuto.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmCostoRicevuto.getClassType()%>" id="<%=OrdineAcquistoRigaPrmCostoRicevuto.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmCostoRicevuto.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmCostoRicevuto.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmCostoRicevuto.write(out); 
%>

													</td>
													<td style="width: 10px">&nbsp;</td>
												</tr>
												<tr>
													<td nowrap><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "ValoreImpostaOrd", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="ValoreImpostaOrdField"><%label.write(out);%></label><%}%></td>
													<!-- Campo Valore Ordine Ordinato in Valuta Ordine -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmValoreImpostaOrd =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ValoreImpostaOrd"); 
  OrdineAcquistoRigaPrmValoreImpostaOrd.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmValoreImpostaOrd.getClassType()%>" id="<%=OrdineAcquistoRigaPrmValoreImpostaOrd.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmValoreImpostaOrd.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmValoreImpostaOrd.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmValoreImpostaOrd.write(out); 
%>

													</td>
													<td style="width: 10px">&nbsp;</td>
													<!-- Campo Valore Ordine In Entrata in Valuta Ordine -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmValoreImpostaInEn =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ValoreImpostaInEn"); 
  OrdineAcquistoRigaPrmValoreImpostaInEn.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmValoreImpostaInEn.getClassType()%>" id="<%=OrdineAcquistoRigaPrmValoreImpostaInEn.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmValoreImpostaInEn.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmValoreImpostaInEn.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmValoreImpostaInEn.write(out); 
%>

													</td>
													<td style="width: 10px">&nbsp;</td>
													<!-- Campo Valore Ordine ricevuto in Valuta Ordine -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmValoreImpostaRic =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ValoreImpostaRic"); 
  OrdineAcquistoRigaPrmValoreImpostaRic.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmValoreImpostaRic.getClassType()%>" id="<%=OrdineAcquistoRigaPrmValoreImpostaRic.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmValoreImpostaRic.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmValoreImpostaRic.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmValoreImpostaRic.write(out); 
%>

													</td>
													<td style="width: 10px">&nbsp;</td>
												</tr>

											</table>
										</fieldset>
									</td>
								</tr>
								<tr>
									<td>
										<fieldset>
											<legend><label class="thLabel" id="LabelValutaAziendaVO">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "LabelValutaAzienda", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></legend>
											<table>
												<!-- Intestazioni -->
												<tr>
													<td style="width: 165px">&nbsp;</td>
													<td colspan="2" style="width: 150px">
														<label class="thLabel" id="LabelCostoOrdinato">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "LabelCostoOrdinato", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label>
													</td>
													<td style="width: 10px">&nbsp;</td>
													<td colspan="2" style="width: 150px">
														<label class="thLabel" id="LabelCostoInEntrata">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "LabelCostoInEntrata", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label>
													</td>
													<td style="width: 10px">&nbsp;</td>
													<td colspan="2" style="width: 150px">
														<label class="thLabel" id="LabelCostoRicevuto">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "LabelCostoRicevuto", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label>
													</td>
													<td style="width: 10px">&nbsp;</td>
												</tr>
												<tr>
													<td nowrap><label class="thLabel" id="LabelGroupBoxValoreVA">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "ValoreGroupBox", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></td>
													<!-- Campo Valore Ordine Ordinato in Valuta Azienda -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmValoreOrdinatoVA =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ValoreOrdinatoVA"); 
  OrdineAcquistoRigaPrmValoreOrdinatoVA.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmValoreOrdinatoVA.getClassType()%>" id="<%=OrdineAcquistoRigaPrmValoreOrdinatoVA.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmValoreOrdinatoVA.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmValoreOrdinatoVA.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmValoreOrdinatoVA.write(out); 
%>

													</td>
  													<td style="width: 10px">&nbsp;</td>
													<!-- Campo Valore Ordine In Entrata in Valuta Azienda -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmValoreInEntrataVA =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ValoreInEntrataVA"); 
  OrdineAcquistoRigaPrmValoreInEntrataVA.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmValoreInEntrataVA.getClassType()%>" id="<%=OrdineAcquistoRigaPrmValoreInEntrataVA.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmValoreInEntrataVA.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmValoreInEntrataVA.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmValoreInEntrataVA.write(out); 
%>

													</td>
  													<td style="width: 10px">&nbsp;</td>
													<!-- Campo Valore Ordine ricevuto in Valuta Azienda -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmValoreRicevutoVA =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ValoreRicevutoVA"); 
  OrdineAcquistoRigaPrmValoreRicevutoVA.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmValoreRicevutoVA.getClassType()%>" id="<%=OrdineAcquistoRigaPrmValoreRicevutoVA.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmValoreRicevutoVA.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmValoreRicevutoVA.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmValoreRicevutoVA.write(out); 
%>

													</td>
  													<td style="width: 10px">&nbsp;</td>
												</tr>
												<tr>
													<td nowrap><label class="thLabel" id="LabelGroupBoxCostoVA">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "CostoGroupBox", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></td>
													<!-- Campo Costo Ordine Ordinato in Valuta Azienda -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmCostoOrdinatoVA =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "CostoOrdinatoVA"); 
  OrdineAcquistoRigaPrmCostoOrdinatoVA.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmCostoOrdinatoVA.getClassType()%>" id="<%=OrdineAcquistoRigaPrmCostoOrdinatoVA.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmCostoOrdinatoVA.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmCostoOrdinatoVA.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmCostoOrdinatoVA.write(out); 
%>

													</td>
 													<td style="width: 10px">&nbsp;</td>
													<!-- Campo Costo Ordine In Entrata in Valuta Azienda -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmCostoInEntrataVA =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "CostoInEntrataVA"); 
  OrdineAcquistoRigaPrmCostoInEntrataVA.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmCostoInEntrataVA.getClassType()%>" id="<%=OrdineAcquistoRigaPrmCostoInEntrataVA.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmCostoInEntrataVA.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmCostoInEntrataVA.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmCostoInEntrataVA.write(out); 
%>

													</td>
 													<td style="width: 10px">&nbsp;</td>
													<!-- Campo Costo Ordine ricevuto in Valuta Azienda -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmCostoRicevutoVA =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "CostoRicevutoVA"); 
  OrdineAcquistoRigaPrmCostoRicevutoVA.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmCostoRicevutoVA.getClassType()%>" id="<%=OrdineAcquistoRigaPrmCostoRicevutoVA.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmCostoRicevutoVA.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmCostoRicevutoVA.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmCostoRicevutoVA.write(out); 
%>

													</td>
 													<td style="width: 10px">&nbsp;</td>
												</tr>
												<tr>
													<td nowrap><label class="thLabel" id="LabelGroupBoxScostamento">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "ScostamentoGroupBox", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label></td>
													<!-- Campo Costo Ordine Ordinato in Valuta Azienda -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmScostamentoOrdinatoVA =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ScostamentoOrdinatoVA"); 
  OrdineAcquistoRigaPrmScostamentoOrdinatoVA.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmScostamentoOrdinatoVA.getClassType()%>" id="<%=OrdineAcquistoRigaPrmScostamentoOrdinatoVA.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmScostamentoOrdinatoVA.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmScostamentoOrdinatoVA.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmScostamentoOrdinatoVA.write(out); 
%>

													</td>
 													<td style="width: 10px">&nbsp;</td>
													<!-- Campo Costo Ordine In Entrata in Valuta Azienda -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmScostamentoInEntrataVA =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ScostamentoInEntrataVA"); 
  OrdineAcquistoRigaPrmScostamentoInEntrataVA.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmScostamentoInEntrataVA.getClassType()%>" id="<%=OrdineAcquistoRigaPrmScostamentoInEntrataVA.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmScostamentoInEntrataVA.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmScostamentoInEntrataVA.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmScostamentoInEntrataVA.write(out); 
%>

													</td>
 													<td style="width: 10px">&nbsp;</td>
													<!-- Campo Costo Ordine ricevuto in Valuta Azienda -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmScostamentoRicevutoVA =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ScostamentoRicevutoVA"); 
  OrdineAcquistoRigaPrmScostamentoRicevutoVA.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmScostamentoRicevutoVA.getClassType()%>" id="<%=OrdineAcquistoRigaPrmScostamentoRicevutoVA.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmScostamentoRicevutoVA.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmScostamentoRicevutoVA.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmScostamentoRicevutoVA.write(out); 
%>

													</td>
 													<td style="width: 10px">&nbsp;</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td nowrap><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "ValoreImpostaOrdVA", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="ValoreImpostaOrdVAField"><%label.write(out);%></label><%}%></td>
													<!-- Campo Valore Ordine Ordinato in Valuta Ordine -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmValoreImpostaOrdVA =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ValoreImpostaOrdVA"); 
  OrdineAcquistoRigaPrmValoreImpostaOrdVA.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmValoreImpostaOrdVA.getClassType()%>" id="<%=OrdineAcquistoRigaPrmValoreImpostaOrdVA.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmValoreImpostaOrdVA.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmValoreImpostaOrdVA.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmValoreImpostaOrdVA.write(out); 
%>

													</td>
													<td style="width: 10px">&nbsp;</td>
													<!-- Campo Valore Ordine In Entrata in Valuta Ordine -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmValoreImpostaInEnVA =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ValoreImpostaInEnVA"); 
  OrdineAcquistoRigaPrmValoreImpostaInEnVA.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmValoreImpostaInEnVA.getClassType()%>" id="<%=OrdineAcquistoRigaPrmValoreImpostaInEnVA.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmValoreImpostaInEnVA.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmValoreImpostaInEnVA.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmValoreImpostaInEnVA.write(out); 
%>

													</td>
													<td style="width: 10px">&nbsp;</td>
													<!-- Campo Valore Ordine ricevuto in Valuta Ordine -->
													<td colspan="2">
														<% 
  WebTextInput OrdineAcquistoRigaPrmValoreImpostaRicVA =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ValoreImpostaRicVA"); 
  OrdineAcquistoRigaPrmValoreImpostaRicVA.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmValoreImpostaRicVA.getClassType()%>" id="<%=OrdineAcquistoRigaPrmValoreImpostaRicVA.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmValoreImpostaRicVA.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmValoreImpostaRicVA.getName()%>" size="20" style="width: 147px" type="text"><% 
  OrdineAcquistoRigaPrmValoreImpostaRicVA.write(out); 
%>

													</td>
													<td style="width: 10px">&nbsp;</td>
												</tr>

											</table>
										</fieldset>
									</td>
								</tr>
								<tr>
									<td>
									<table>
									<tr>
									<td nowrap style="width: 167px"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "CostoUnitario", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="CostoUnitarioField"><%label.write(out);%></label><%}%></td>
									<!-- Campo Costo Unitario -->
									<td colspan="2">
										<% 
  WebTextInput OrdineAcquistoRigaPrmCostoUnitario =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "CostoUnitario"); 
  OrdineAcquistoRigaPrmCostoUnitario.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmCostoUnitario.getClassType()%>" id="<%=OrdineAcquistoRigaPrmCostoUnitario.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmCostoUnitario.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmCostoUnitario.getName()%>" size="20" type="text"><% 
  OrdineAcquistoRigaPrmCostoUnitario.write(out); 
%>

									</td>
									</tr>
									</table>
									</td>
								</tr>
<!-- Fix 2183 -->
								<tr>
								<td>
<!-- Fine fix 2183 -->

								<table cellpadding="1" cellspacing="1">
									<tr>
										<!-- Combo Stato Invio EDI -->
										<td nowrap style="width: 168px"><!-- Fix 39398 - fine -->
											<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "StatoInvioEDI", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="StatoInvioEDICombo"><%label.write(out);%></label><%}%>
										</td>
										<td>
											<% 
  WebComboBox OrdineAcquistoRigaPrmStatoInvioEDI =  
     new com.thera.thermfw.web.WebComboBox("OrdineAcquistoRigaPrm", "StatoInvioEDI", null); 
  OrdineAcquistoRigaPrmStatoInvioEDI.setParent(OrdineAcquistoRigaPrmForm); 
%>
<select id="<%=OrdineAcquistoRigaPrmStatoInvioEDI.getId()%>" name="<%=OrdineAcquistoRigaPrmStatoInvioEDI.getName()%>"><% 
  OrdineAcquistoRigaPrmStatoInvioEDI.write(out); 
%> 

												
											</select>
										</td>
									</tr>
									<tr>
										<!-- Combo Stato Invio Data Warehouse -->
										<td nowrap>
											<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "StatoInvioDatawarehouse", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="StatoInvioDatawarehouseCombo"><%label.write(out);%></label><%}%>
										</td>
										<td>
											<% 
  WebComboBox OrdineAcquistoRigaPrmStatoInvioDatawarehouse =  
     new com.thera.thermfw.web.WebComboBox("OrdineAcquistoRigaPrm", "StatoInvioDatawarehouse", null); 
  OrdineAcquistoRigaPrmStatoInvioDatawarehouse.setParent(OrdineAcquistoRigaPrmForm); 
%>
<select id="<%=OrdineAcquistoRigaPrmStatoInvioDatawarehouse.getId()%>" name="<%=OrdineAcquistoRigaPrmStatoInvioDatawarehouse.getName()%>"><% 
  OrdineAcquistoRigaPrmStatoInvioDatawarehouse.write(out); 
%> 

												
											</select>
										</td>
									</tr>
									<tr>
										<!-- Combo Stato Invio Logistica -->
										<td nowrap>
											<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "StatoInvioLogistica", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="StatoInvioLogisticaCombo"><%label.write(out);%></label><%}%>
										</td>
										<td>
											<% 
  WebComboBox OrdineAcquistoRigaPrmStatoInvioLogistica =  
     new com.thera.thermfw.web.WebComboBox("OrdineAcquistoRigaPrm", "StatoInvioLogistica", null); 
  OrdineAcquistoRigaPrmStatoInvioLogistica.setParent(OrdineAcquistoRigaPrmForm); 
%>
<select id="<%=OrdineAcquistoRigaPrmStatoInvioLogistica.getId()%>" name="<%=OrdineAcquistoRigaPrmStatoInvioLogistica.getName()%>"><% 
  OrdineAcquistoRigaPrmStatoInvioLogistica.write(out); 
%> 

												
											</select>
										</td>
									</tr>
									<tr>
										<!-- Combo Stato Invio Contabil. Analitica -->
										<td nowrap>
											<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "OrdineAcquistoRigaPrm", "StatoInvioContAnalitica", null); 
   label.setParent(OrdineAcquistoRigaPrmForm); 
%><label class="<%=label.getClassType()%>" for="StatoInvioContabAnaliticaCombo"><%label.write(out);%></label><%}%>
										</td>
										<td>
											<% 
  WebComboBox OrdineAcquistoRigaPrmStatoInvioContAnalitica =  
     new com.thera.thermfw.web.WebComboBox("OrdineAcquistoRigaPrm", "StatoInvioContAnalitica", null); 
  OrdineAcquistoRigaPrmStatoInvioContAnalitica.setParent(OrdineAcquistoRigaPrmForm); 
%>
<select id="<%=OrdineAcquistoRigaPrmStatoInvioContAnalitica.getId()%>" name="<%=OrdineAcquistoRigaPrmStatoInvioContAnalitica.getName()%>"><% 
  OrdineAcquistoRigaPrmStatoInvioContAnalitica.write(out); 
%> 

												
											</select>
										</td>
									</tr>
								</table>
<!-- Fix 2183 -->
								</td>
								</tr>
<!-- Fine fix 2183 -->
							<!--12094 begin-->


								<!-- **************************************************************************************************** -->
								<!-- Campi nascosti -->
								<tr style="display:none">
								<td height="0">
								<table cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "IdAzienda"); 
  OrdineAcquistoRigaPrmIdAzienda.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmIdAzienda.getClassType()%>" id="<%=OrdineAcquistoRigaPrmIdAzienda.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmIdAzienda.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmIdAzienda.getName()%>" size="<%=OrdineAcquistoRigaPrmIdAzienda.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmIdAzienda.write(out); 
%>

										</td>
									</tr>
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmAnnoOrdine =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "AnnoOrdine"); 
  OrdineAcquistoRigaPrmAnnoOrdine.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmAnnoOrdine.getClassType()%>" id="<%=OrdineAcquistoRigaPrmAnnoOrdine.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmAnnoOrdine.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmAnnoOrdine.getName()%>" size="<%=OrdineAcquistoRigaPrmAnnoOrdine.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmAnnoOrdine.write(out); 
%>

										</td>
									</tr>
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmNumeroOrdine =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "NumeroOrdine"); 
  OrdineAcquistoRigaPrmNumeroOrdine.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmNumeroOrdine.getClassType()%>" id="<%=OrdineAcquistoRigaPrmNumeroOrdine.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmNumeroOrdine.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmNumeroOrdine.getName()%>" size="<%=OrdineAcquistoRigaPrmNumeroOrdine.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmNumeroOrdine.write(out); 
%>

										</td>
									</tr>
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmRigaOrdine =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "RigaOrdine"); 
  OrdineAcquistoRigaPrmRigaOrdine.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmRigaOrdine.getClassType()%>" id="<%=OrdineAcquistoRigaPrmRigaOrdine.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmRigaOrdine.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmRigaOrdine.getName()%>" size="<%=OrdineAcquistoRigaPrmRigaOrdine.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmRigaOrdine.write(out); 
%>

										</td>
									</tr>
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmDettaglioRigaOrdine =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "DettaglioRigaOrdine"); 
  OrdineAcquistoRigaPrmDettaglioRigaOrdine.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmDettaglioRigaOrdine.getClassType()%>" id="<%=OrdineAcquistoRigaPrmDettaglioRigaOrdine.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmDettaglioRigaOrdine.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmDettaglioRigaOrdine.getName()%>" size="<%=OrdineAcquistoRigaPrmDettaglioRigaOrdine.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmDettaglioRigaOrdine.write(out); 
%>

										</td>
									</tr>
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmTipoRiga =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "TipoRiga"); 
  OrdineAcquistoRigaPrmTipoRiga.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmTipoRiga.getClassType()%>" id="<%=OrdineAcquistoRigaPrmTipoRiga.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmTipoRiga.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmTipoRiga.getName()%>" size="<%=OrdineAcquistoRigaPrmTipoRiga.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmTipoRiga.write(out); 
%>

										</td>
									</tr>
									<!-- Fix 8286 Inizio -->
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmIdSpesa =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "IdSpesa"); 
  OrdineAcquistoRigaPrmIdSpesa.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmIdSpesa.getClassType()%>" id="<%=OrdineAcquistoRigaPrmIdSpesa.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmIdSpesa.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmIdSpesa.getName()%>" size="<%=OrdineAcquistoRigaPrmIdSpesa.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmIdSpesa.write(out); 
%>

										</td>
									</tr>
									<!-- Fix 8286 Fine -->
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmIdUMPrm =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "IdUMPrm"); 
  OrdineAcquistoRigaPrmIdUMPrm.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmIdUMPrm.getClassType()%>" id="<%=OrdineAcquistoRigaPrmIdUMPrm.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmIdUMPrm.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmIdUMPrm.getName()%>" size="<%=OrdineAcquistoRigaPrmIdUMPrm.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmIdUMPrm.write(out); 
%>

										</td>
									</tr>
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmIdUMSec =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "IdUMSec"); 
  OrdineAcquistoRigaPrmIdUMSec.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmIdUMSec.getClassType()%>" id="<%=OrdineAcquistoRigaPrmIdUMSec.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmIdUMSec.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmIdUMSec.getName()%>" size="<%=OrdineAcquistoRigaPrmIdUMSec.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmIdUMSec.write(out); 
%>

										</td>
									</tr>
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmIdVersioneSaldi =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "IdVersioneSaldi"); 
  OrdineAcquistoRigaPrmIdVersioneSaldi.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmIdVersioneSaldi.getClassType()%>" id="<%=OrdineAcquistoRigaPrmIdVersioneSaldi.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmIdVersioneSaldi.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmIdVersioneSaldi.getName()%>" size="<%=OrdineAcquistoRigaPrmIdVersioneSaldi.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmIdVersioneSaldi.write(out); 
%>

										</td>
									</tr>
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmServizioValutaTestata =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ServizioValutaTestata"); 
  OrdineAcquistoRigaPrmServizioValutaTestata.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmServizioValutaTestata.getClassType()%>" id="<%=OrdineAcquistoRigaPrmServizioValutaTestata.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmServizioValutaTestata.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmServizioValutaTestata.getName()%>" size="<%=OrdineAcquistoRigaPrmServizioValutaTestata.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmServizioValutaTestata.write(out); 
%>

										</td>
									</tr>
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmServizioValutaAzienda =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ServizioValutaAzienda"); 
  OrdineAcquistoRigaPrmServizioValutaAzienda.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmServizioValutaAzienda.getClassType()%>" id="<%=OrdineAcquistoRigaPrmServizioValutaAzienda.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmServizioValutaAzienda.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmServizioValutaAzienda.getName()%>" size="<%=OrdineAcquistoRigaPrmServizioValutaAzienda.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmServizioValutaAzienda.write(out); 
%>

										</td>
									</tr>
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmServizioCalcDatiAcquisto =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ServizioCalcDatiAcquisto"); 
  OrdineAcquistoRigaPrmServizioCalcDatiAcquisto.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmServizioCalcDatiAcquisto.getClassType()%>" id="<%=OrdineAcquistoRigaPrmServizioCalcDatiAcquisto.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmServizioCalcDatiAcquisto.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmServizioCalcDatiAcquisto.getName()%>" size="<%=OrdineAcquistoRigaPrmServizioCalcDatiAcquisto.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmServizioCalcDatiAcquisto.write(out); 
%>

										</td>
									</tr>
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmServizioListAcqScaglione =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ServizioListAcqScaglione"); 
  OrdineAcquistoRigaPrmServizioListAcqScaglione.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmServizioListAcqScaglione.getClassType()%>" id="<%=OrdineAcquistoRigaPrmServizioListAcqScaglione.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmServizioListAcqScaglione.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmServizioListAcqScaglione.getName()%>" size="<%=OrdineAcquistoRigaPrmServizioListAcqScaglione.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmServizioListAcqScaglione.write(out); 
%>

										</td>
									</tr>
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmServizioArticoloConfig =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ServizioArticoloConfig"); 
  OrdineAcquistoRigaPrmServizioArticoloConfig.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmServizioArticoloConfig.getClassType()%>" id="<%=OrdineAcquistoRigaPrmServizioArticoloConfig.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmServizioArticoloConfig.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmServizioArticoloConfig.getName()%>" size="<%=OrdineAcquistoRigaPrmServizioArticoloConfig.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmServizioArticoloConfig.write(out); 
%>

										</td>
									</tr>
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmPrezzoListino =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "PrezzoListino"); 
  OrdineAcquistoRigaPrmPrezzoListino.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmPrezzoListino.getClassType()%>" id="<%=OrdineAcquistoRigaPrmPrezzoListino.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmPrezzoListino.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmPrezzoListino.getName()%>" size="<%=OrdineAcquistoRigaPrmPrezzoListino.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmPrezzoListino.write(out); 
%>

										</td>
									</tr>
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmPrezzoExtraListino =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "PrezzoExtraListino"); 
  OrdineAcquistoRigaPrmPrezzoExtraListino.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmPrezzoExtraListino.getClassType()%>" id="<%=OrdineAcquistoRigaPrmPrezzoExtraListino.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmPrezzoExtraListino.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmPrezzoExtraListino.getName()%>" size="<%=OrdineAcquistoRigaPrmPrezzoExtraListino.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmPrezzoExtraListino.write(out); 
%>

										</td>
									</tr>
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmLavorazioneEsterna =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "LavorazioneEsterna"); 
  OrdineAcquistoRigaPrmLavorazioneEsterna.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmLavorazioneEsterna.getClassType()%>" id="<%=OrdineAcquistoRigaPrmLavorazioneEsterna.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmLavorazioneEsterna.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmLavorazioneEsterna.getName()%>" size="<%=OrdineAcquistoRigaPrmLavorazioneEsterna.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmLavorazioneEsterna.write(out); 
%>

										</td>
									</tr>
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmTipoLavorazioneEsterna =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "TipoLavorazioneEsterna"); 
  OrdineAcquistoRigaPrmTipoLavorazioneEsterna.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmTipoLavorazioneEsterna.getClassType()%>" id="<%=OrdineAcquistoRigaPrmTipoLavorazioneEsterna.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmTipoLavorazioneEsterna.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmTipoLavorazioneEsterna.getName()%>" size="<%=OrdineAcquistoRigaPrmTipoLavorazioneEsterna.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmTipoLavorazioneEsterna.write(out); 
%>

										</td>
									</tr>
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmTipoCostoRiferimento =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "TipoCostoRiferimento"); 
  OrdineAcquistoRigaPrmTipoCostoRiferimento.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmTipoCostoRiferimento.getClassType()%>" id="<%=OrdineAcquistoRigaPrmTipoCostoRiferimento.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmTipoCostoRiferimento.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmTipoCostoRiferimento.getName()%>" size="<%=OrdineAcquistoRigaPrmTipoCostoRiferimento.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmTipoCostoRiferimento.write(out); 
%>

										</td>
									</tr>
									<tr>
										<td>
											<label class="thLabel" id="LabelPulsanteSalda" style="display:none">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "SaldaPulsanteLabel", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label>
										</td>
									</tr>
									<tr>
										<td>
											<label class="thLabel" id="LabelPulsanteRiapri" style="display:none">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "RiapriPulsanteLabel", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label>
										</td>
									</tr>
									<tr>
										<td>
											<label class="thLabel" id="LabelPulsanteSaldaComp" style="display:none">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "SaldaCompPulsanteLabel", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label>
										</td>
									</tr>
									<tr>
										<td>
											<label class="thLabel" id="LabelPulsanteRiapriComp" style="display:none">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.acquisti.ordineAC.resources.OrdineAcquistoRiga", "RiapriCompPulsanteLabel", null, null, null, null); 
 label.setParent(OrdineAcquistoRigaPrmForm); 
label.write(out); }%> 
</label>
										</td>
									</tr>
									<!-- Fix 1918 inizio -->
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmServizioCatalEstConfig =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ServizioCatalEstConfig"); 
  OrdineAcquistoRigaPrmServizioCatalEstConfig.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmServizioCatalEstConfig.getClassType()%>" id="<%=OrdineAcquistoRigaPrmServizioCatalEstConfig.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmServizioCatalEstConfig.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmServizioCatalEstConfig.getName()%>" size="<%=OrdineAcquistoRigaPrmServizioCatalEstConfig.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmServizioCatalEstConfig.write(out); 
%>

										</td>
									</tr>
									<!-- Fix 1918 fine -->
									<!-- Fix 2384 inizio -->
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmServizioProvenienzaPrezzo =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ServizioProvenienzaPrezzo"); 
  OrdineAcquistoRigaPrmServizioProvenienzaPrezzo.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmServizioProvenienzaPrezzo.getClassType()%>" id="<%=OrdineAcquistoRigaPrmServizioProvenienzaPrezzo.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmServizioProvenienzaPrezzo.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmServizioProvenienzaPrezzo.getName()%>" size="<%=OrdineAcquistoRigaPrmServizioProvenienzaPrezzo.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmServizioProvenienzaPrezzo.write(out); 
%>

										</td>
									</tr>
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmServizioRiferimUMPrezzo =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ServizioRiferimUMPrezzo"); 
  OrdineAcquistoRigaPrmServizioRiferimUMPrezzo.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmServizioRiferimUMPrezzo.getClassType()%>" id="<%=OrdineAcquistoRigaPrmServizioRiferimUMPrezzo.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmServizioRiferimUMPrezzo.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmServizioRiferimUMPrezzo.getName()%>" size="<%=OrdineAcquistoRigaPrmServizioRiferimUMPrezzo.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmServizioRiferimUMPrezzo.write(out); 
%>

										</td>
									</tr>
									<!-- Fix 2384 fine -->
									<!-- Fix 2333 -->
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmAPrezziExtra =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "APrezziExtra"); 
  OrdineAcquistoRigaPrmAPrezziExtra.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmAPrezziExtra.getClassType()%>" id="<%=OrdineAcquistoRigaPrmAPrezziExtra.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmAPrezziExtra.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmAPrezziExtra.getName()%>" size="<%=OrdineAcquistoRigaPrmAPrezziExtra.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmAPrezziExtra.write(out); 
%>

										</td>
									</tr>
									<!-- Fine Fix 2333 -->
									<!-- Fix 2844 inizio -->
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmIdConfigurazione =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "IdConfigurazione"); 
  OrdineAcquistoRigaPrmIdConfigurazione.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmIdConfigurazione.getClassType()%>" id="<%=OrdineAcquistoRigaPrmIdConfigurazione.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmIdConfigurazione.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmIdConfigurazione.getName()%>" size="<%=OrdineAcquistoRigaPrmIdConfigurazione.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmIdConfigurazione.write(out); 
%>

										</td>
									</tr>
									<!-- Fix 2844 fine -->
									<!-- Fix 3770 inizio -->
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmIdCatalogoEsterno =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "IdCatalogoEsterno"); 
  OrdineAcquistoRigaPrmIdCatalogoEsterno.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmIdCatalogoEsterno.getClassType()%>" id="<%=OrdineAcquistoRigaPrmIdCatalogoEsterno.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmIdCatalogoEsterno.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmIdCatalogoEsterno.getName()%>" size="<%=OrdineAcquistoRigaPrmIdCatalogoEsterno.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmIdCatalogoEsterno.write(out); 
%>

										</td>
									</tr>
									<!-- Fix 3770 fine -->
									<!-- Fix 3910 inizio -->
									<tr>
										<td>
											<% 
  WebTextInput OrdineAcquistoRigaPrmQtaMinListino =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "QtaMinListino"); 
  OrdineAcquistoRigaPrmQtaMinListino.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmQtaMinListino.getClassType()%>" id="<%=OrdineAcquistoRigaPrmQtaMinListino.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmQtaMinListino.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmQtaMinListino.getName()%>" size="<%=OrdineAcquistoRigaPrmQtaMinListino.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmQtaMinListino.write(out); 
%>

										</td>
									</tr>
									<!-- Fix 3910 fine -->
						            <!-- Inizio mmm -->
									<tr>
										<td>
											<input id="AzioneManuale" name="AzioneManuale" type="hidden">
										</td>
									</tr>
									<tr>
										<td>
											<input id="AzioneManualeComp" name="AzioneManualeComp" type="hidden">
										</td>
									</tr>
									<!-- Fine mmm -->
									<!-- Fix 10173 inizio -->
									<tr>
										<td>
											<input id="ForzaCambioCausale" name="ForzaCambioCausale" type="hidden">
										</td>
									</tr>
									<!-- Fix 10173 fine -->
                  <!--Fix 23345 inizio-->
                  <tr>
                    <td>
                      <% 
  WebTextInput OrdineAcquistoRigaPrmControlloRicalCondiz =  
     new com.thera.thermfw.web.WebTextInput("OrdineAcquistoRigaPrm", "ControlloRicalCondiz"); 
  OrdineAcquistoRigaPrmControlloRicalCondiz.setParent(OrdineAcquistoRigaPrmForm); 
%>
<input class="<%=OrdineAcquistoRigaPrmControlloRicalCondiz.getClassType()%>" id="<%=OrdineAcquistoRigaPrmControlloRicalCondiz.getId()%>" maxlength="<%=OrdineAcquistoRigaPrmControlloRicalCondiz.getMaxLength()%>" name="<%=OrdineAcquistoRigaPrmControlloRicalCondiz.getName()%>" size="<%=OrdineAcquistoRigaPrmControlloRicalCondiz.getSize()%>" type="hidden"><% 
  OrdineAcquistoRigaPrmControlloRicalCondiz.write(out); 
%>

                    </td>
                  </tr>
                  <!--Fix 23345 fine-->
								</table>
								</td></tr>
							<!--12094 end-->
							</table>
						<% MainTabbed.endTab(); %> 
</div>

					</div><% MainTabbed.endTabbed();%> 

     </td>
   </tr>
</table><!--</span>-->
				 </td>
			</tr>

			<!-- **************************************************************************************************** -->
			<!-- ErrorList -->
			<tr>
    			<td style="height:0">
		    	  <% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(OrdineAcquistoRigaPrmForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
			    </td>
			</tr>
		</table>

	<%
  OrdineAcquistoRigaPrmForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = OrdineAcquistoRigaPrmForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", OrdineAcquistoRigaPrmBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>



<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              OrdineAcquistoRigaPrmForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, OrdineAcquistoRigaPrmBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, OrdineAcquistoRigaPrmBODC.getErrorList().getErrors()); 
           if(OrdineAcquistoRigaPrmBODC.getConflict() != null) 
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
     if(OrdineAcquistoRigaPrmBODC != null && !OrdineAcquistoRigaPrmBODC.close(false)) 
        errors.addAll(0, OrdineAcquistoRigaPrmBODC.getErrorList().getErrors()); 
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
     String errorPage = OrdineAcquistoRigaPrmForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", OrdineAcquistoRigaPrmBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = OrdineAcquistoRigaPrmForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
