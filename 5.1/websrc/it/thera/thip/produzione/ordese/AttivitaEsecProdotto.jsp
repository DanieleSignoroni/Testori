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
  BODataCollector AttivitaEsecProdottoBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebFormForIndipendentRowForm AttivitaEsecProdottoForm =  
     new com.thera.thermfw.web.WebFormForIndipendentRowForm(request, response, "AttivitaEsecProdottoForm", "AttivitaEsecProdotto", "Arial,10", "it.thera.thip.produzione.ordese.web.AttivitaEsecProdottoFormActionAdapter", false, false, false, true, true, true, "it.thera.thip.produzione.ordese.web.AttivitaEsecProdottoDC", 1, true, null); 
  AttivitaEsecProdottoForm.setServletEnvironment(se); 
  AttivitaEsecProdottoForm.setJSTypeList(jsList); 
  AttivitaEsecProdottoForm.setHeader(null); 
  AttivitaEsecProdottoForm.setFooter(null); 
  AttivitaEsecProdottoForm.setWebFormModifierClass("it.thera.thip.produzione.ordese.web.AttivitaEsecProdottoWebFormModifier"); 
  AttivitaEsecProdottoForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = AttivitaEsecProdottoForm.getMode(); 
  String key = AttivitaEsecProdottoForm.getKey(); 
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
        AttivitaEsecProdottoForm.outTraceInfo(getClass().getName()); 
        String collectorName = AttivitaEsecProdottoForm.findBODataCollectorName(); 
	     AttivitaEsecProdottoBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (AttivitaEsecProdottoBODC instanceof WebDataCollector) 
            ((WebDataCollector)AttivitaEsecProdottoBODC).setServletEnvironment(se); 
        AttivitaEsecProdottoBODC.initialize("AttivitaEsecProdotto", true, 1); 
        AttivitaEsecProdottoForm.setBODataCollector(AttivitaEsecProdottoBODC); 
        int rcBODC = AttivitaEsecProdottoForm.initSecurityServices(); 
        mode = AttivitaEsecProdottoForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           AttivitaEsecProdottoForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = AttivitaEsecProdottoBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              AttivitaEsecProdottoForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 

	<title>AttivitaEsecProdotto</title>
<% 
  WebToolBar myToolBarTB = new com.thera.thermfw.web.WebToolBar("myToolBar", "24", "24", "16", "16", "#f7fbfd","#C8D6E1"); 
  myToolBarTB.setParent(AttivitaEsecProdottoForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/produzione/ordese/defobjMenuOrdineEsec.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>

<body bottommargin="0" leftmargin="0" onbeforeunload="<%=AttivitaEsecProdottoForm.getBodyOnBeforeUnload()%>" onload="<%=AttivitaEsecProdottoForm.getBodyOnLoad()%>" onunload="<%=AttivitaEsecProdottoForm.getBodyOnUnload()%>" rightmargin="0" topmargin="0"><%
   AttivitaEsecProdottoForm.writeBodyStartElements(out); 
%> 


<table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = AttivitaEsecProdottoForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", AttivitaEsecProdottoBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=AttivitaEsecProdottoForm.getServlet()%>" method="post" name="form" style="height:100%"><%
  AttivitaEsecProdottoForm.writeFormStartElements(out); 
%>

	<table cellpadding="0" cellspacing="0" height="100%" id="emptyborder" width="100%">
		<tr>
			<td style="height:0"><% myToolBarTB.writeChildren(out); %> 
</td>
		</tr>
		<tr>
			<td height="100%">
				<!--<span class="tabbed" id="mytabbed">-->
<table width="100%" height="100%" cellpadding="0" cellspacing="0" style="padding-right:1px">
   <tr valign="top">
     <td><% 
  WebTabbed mytabbed = new com.thera.thermfw.web.WebTabbed("mytabbed", "100%", "100%"); 
  mytabbed.setParent(AttivitaEsecProdottoForm); 
 mytabbed.addTab("Tab1", "it.thera.thip.cs.resources.Cs", "DatiGenerali", "AttivitaEsecProdotto", null, null, null, null); 
 mytabbed.addTab("Tab2", "it.thera.thip.produzione.ordese.resources.AttivitaEsecProdotto", "AltriDatiTab", "AttivitaEsecProdotto", null, null, null, null); 
 mytabbed.addTab("Tab3", "it.thera.thip.produzione.ordese.resources.AttivitaEsecProdotto", "QuantitaEDateTab", "AttivitaEsecProdotto", null, null, null, null); 
 mytabbed.addTab("tab4", "it.thera.thip.produzione.ordese.resources.AttivitaEsecProdotto", "LottiTab", "AttivitaEsecProdotto", null, null, null, null); 
 mytabbed.addTab("Tab5", "it.thera.thip.cs.resources.Cs", "DescrizioniNLS", "AttivitaEsecProdotto", null, null, null, null); 
  mytabbed.write(out); 
%>

     </td>
   </tr>
   <tr>
     <td height="100%"><div class="tabbed_pagine" id="tabbedPagine" style="position: relative; width: 100%; height: 100%;">
					<div class="tabbed_page" id="<%=mytabbed.getTabPageId("Tab1")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("Tab1"); %>
						<table style="margin: 0 7 7 7;" width="97%">
							<% 
  WebTextInput AttivitaEsecProdottoIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "IdAzienda"); 
  AttivitaEsecProdottoIdAzienda.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoIdAzienda.getClassType()%>" id="<%=AttivitaEsecProdottoIdAzienda.getId()%>" maxlength="<%=AttivitaEsecProdottoIdAzienda.getMaxLength()%>" name="<%=AttivitaEsecProdottoIdAzienda.getName()%>" size="<%=AttivitaEsecProdottoIdAzienda.getSize()%>" type="hidden"><% 
  AttivitaEsecProdottoIdAzienda.write(out); 
%>

							<% 
  WebTextInput AttivitaEsecProdottoIdAnnoOrdine =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "IdAnnoOrdine"); 
  AttivitaEsecProdottoIdAnnoOrdine.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoIdAnnoOrdine.getClassType()%>" id="<%=AttivitaEsecProdottoIdAnnoOrdine.getId()%>" maxlength="<%=AttivitaEsecProdottoIdAnnoOrdine.getMaxLength()%>" name="<%=AttivitaEsecProdottoIdAnnoOrdine.getName()%>" size="<%=AttivitaEsecProdottoIdAnnoOrdine.getSize()%>" type="hidden"><% 
  AttivitaEsecProdottoIdAnnoOrdine.write(out); 
%>

							<% 
  WebTextInput AttivitaEsecProdottoIdNumeroOrdine =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "IdNumeroOrdine"); 
  AttivitaEsecProdottoIdNumeroOrdine.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoIdNumeroOrdine.getClassType()%>" id="<%=AttivitaEsecProdottoIdNumeroOrdine.getId()%>" maxlength="<%=AttivitaEsecProdottoIdNumeroOrdine.getMaxLength()%>" name="<%=AttivitaEsecProdottoIdNumeroOrdine.getName()%>" size="<%=AttivitaEsecProdottoIdNumeroOrdine.getSize()%>" type="hidden"><% 
  AttivitaEsecProdottoIdNumeroOrdine.write(out); 
%>

							<% 
  WebTextInput AttivitaEsecProdottoIdRigaAttivita =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "IdRigaAttivita"); 
  AttivitaEsecProdottoIdRigaAttivita.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoIdRigaAttivita.getClassType()%>" id="<%=AttivitaEsecProdottoIdRigaAttivita.getId()%>" maxlength="<%=AttivitaEsecProdottoIdRigaAttivita.getMaxLength()%>" name="<%=AttivitaEsecProdottoIdRigaAttivita.getName()%>" size="<%=AttivitaEsecProdottoIdRigaAttivita.getSize()%>" type="hidden"><% 
  AttivitaEsecProdottoIdRigaAttivita.write(out); 
%>

							<% 
  WebTextInput AttivitaEsecProdottoIdRigaProdotto =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "IdRigaProdotto"); 
  AttivitaEsecProdottoIdRigaProdotto.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoIdRigaProdotto.getClassType()%>" id="<%=AttivitaEsecProdottoIdRigaProdotto.getId()%>" maxlength="<%=AttivitaEsecProdottoIdRigaProdotto.getMaxLength()%>" name="<%=AttivitaEsecProdottoIdRigaProdotto.getName()%>" size="<%=AttivitaEsecProdottoIdRigaProdotto.getSize()%>" type="hidden"><% 
  AttivitaEsecProdottoIdRigaProdotto.write(out); 
%>

							<% 
  WebTextInput AttivitaEsecProdottoNodoDesc =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "NodoDesc"); 
  AttivitaEsecProdottoNodoDesc.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoNodoDesc.getClassType()%>" id="<%=AttivitaEsecProdottoNodoDesc.getId()%>" maxlength="<%=AttivitaEsecProdottoNodoDesc.getMaxLength()%>" name="<%=AttivitaEsecProdottoNodoDesc.getName()%>" size="<%=AttivitaEsecProdottoNodoDesc.getSize()%>" type="hidden"><% 
  AttivitaEsecProdottoNodoDesc.write(out); 
%>

							<input id="thCreaArticoloWIP" name="thCreaArticoloWIP" type="hidden" value="N">
							<iframe frameborder="0" height="0" id="DescrizioneFrame" name="DescrizioneFrame" style="visibility:hidden" width="0"></iframe> <!--Fix 12141 -->
							<tr>
								<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaEsecProdotto", "TipoProdotto", null); 
   label.setParent(AttivitaEsecProdottoForm); 
%><label class="<%=label.getClassType()%>" for="TipoProdotto"><%label.write(out);%></label><%}%></td>
								<td><% 
  WebComboBox AttivitaEsecProdottoTipoProdotto =  
     new com.thera.thermfw.web.WebComboBox("AttivitaEsecProdotto", "TipoProdotto", null); 
  AttivitaEsecProdottoTipoProdotto.setParent(AttivitaEsecProdottoForm); 
  AttivitaEsecProdottoTipoProdotto.setOnChange("checkTipoProdotto()"); 
%>
<select id="<%=AttivitaEsecProdottoTipoProdotto.getId()%>" name="<%=AttivitaEsecProdottoTipoProdotto.getName()%>"><% 
  AttivitaEsecProdottoTipoProdotto.write(out); 
%> 
</select></td>
							</tr>
							<tr>
								<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaEsecProdotto", "IdArticolo", null); 
   label.setParent(AttivitaEsecProdottoForm); 
%><label class="<%=label.getClassType()%>" for="Articolo"><%label.write(out);%></label><%}%></td>
								<td nowrap>
									<table cellpadding="0" cellspacing="0">
										<tr id="divArticolo"><td><% 
  WebMultiSearchForm AttivitaEsecProdottoArticolo =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("AttivitaEsecProdotto", "Articolo", false, false, true, 1, null, null); 
  AttivitaEsecProdottoArticolo.setParent(AttivitaEsecProdottoForm); 
  AttivitaEsecProdottoArticolo.setOnKeyChange("fillDescrizioneFields()"); 
  AttivitaEsecProdottoArticolo.write(out); 
%>
<!--<span class="articolomultisearchform" id="Articolo"></span>--></td></tr>
										<tr id="divArtScelte"><td><% 
  WebMultiSearchForm AttivitaEsecProdottoArtScelte =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("AttivitaEsecProdotto", "ArtScelte", false, false, true, 1, null, null); 
  AttivitaEsecProdottoArtScelte.setParent(AttivitaEsecProdottoForm); 
  AttivitaEsecProdottoArtScelte.setOnKeyChange("fillAttivitaProdProdFieldsScelte()"); 
  AttivitaEsecProdottoArtScelte.write(out); 
%>
<!--<span class="articolomultisearchform" id="ArtScelte"></span>--></td></tr>
									</table>
								</td>
							</tr>
							<div id="a"></div>
							<tr>
								<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaEsecProdotto", "IdVersione", null); 
   label.setParent(AttivitaEsecProdottoForm); 
%><label class="<%=label.getClassType()%>" for="Versione"><%label.write(out);%></label><%}%></td>
									<td><% 
  WebMultiSearchForm AttivitaEsecProdottoVersione =  
     new com.thera.thermfw.web.WebMultiSearchForm("AttivitaEsecProdotto", "Versione", false, false, true, 1, null, null); 
  AttivitaEsecProdottoVersione.setParent(AttivitaEsecProdottoForm); 
  AttivitaEsecProdottoVersione.write(out); 
%>
<!--<span class="multisearchform" id="Versione"></span>--></td>
								</tr>
								<tr>
									<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaEsecProdotto", "IdEsternoConfig", null); 
   label.setParent(AttivitaEsecProdottoForm); 
%><label class="<%=label.getClassType()%>" for="Configurazione"><%label.write(out);%></label><%}%></td>
									<td><% 
  WebMultiSearchForm AttivitaEsecProdottoConfigurazione =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("AttivitaEsecProdotto", "Configurazione", false, false, true, 1, null, null); 
  AttivitaEsecProdottoConfigurazione.setExtraRelatedClassAD("IdAzienda, IdConfigurazione"); 
  AttivitaEsecProdottoConfigurazione.setParent(AttivitaEsecProdottoForm); 
  AttivitaEsecProdottoConfigurazione.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="Configurazione"></span>--></td>
								</tr>
								<tr>
									<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaEsecProdotto", "IdCommessa", null); 
   label.setParent(AttivitaEsecProdottoForm); 
%><label class="<%=label.getClassType()%>" for="Commessa"><%label.write(out);%></label><%}%></td>
									<td><% 
  WebMultiSearchForm AttivitaEsecProdottoCommessa =  
     new com.thera.thermfw.web.WebMultiSearchForm("AttivitaEsecProdotto", "Commessa", false, false, true, 1, null, null); 
  AttivitaEsecProdottoCommessa.setParent(AttivitaEsecProdottoForm); 
  AttivitaEsecProdottoCommessa.setAdditionalRestrictConditions("IdCommessaArticolo,IdCommessaAppartenenza"); 
  AttivitaEsecProdottoCommessa.setSpecificDOList("it.thera.thip.base.commessa.web.RicercaCommessaDOList"); 
  AttivitaEsecProdottoCommessa.write(out); 
%>
<!--<span class="multisearchform" id="Commessa"></span>--></td>
								</tr>
								<div>
									<% 
   request.setAttribute("parentForm", AttivitaEsecProdottoForm); 
   String CDForDescrizione$it$thera$thip$cs$Descrizione$jsp = "Descrizione"; 
%>
<jsp:include page="/it/thera/thip/cs/Descrizione.jsp" flush="true"> 
<jsp:param name="CDName" value="<%=CDForDescrizione$it$thera$thip$cs$Descrizione$jsp%>"/> 
</jsp:include> 
<!--<span class="subform" id="Descrizione"></span>-->
								</div>
								<tr>
									<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaEsecProdotto", "CoeffProduzione", null); 
   label.setParent(AttivitaEsecProdottoForm); 
%><label class="<%=label.getClassType()%>" for="CoeffProduzione"><%label.write(out);%></label><%}%></td>
									<td>
										<% 
  WebTextInput AttivitaEsecProdottoCoeffProduzione =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "CoeffProduzione"); 
  AttivitaEsecProdottoCoeffProduzione.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoCoeffProduzione.getClassType()%>" id="<%=AttivitaEsecProdottoCoeffProduzione.getId()%>" maxlength="<%=AttivitaEsecProdottoCoeffProduzione.getMaxLength()%>" name="<%=AttivitaEsecProdottoCoeffProduzione.getName()%>" size="<%=AttivitaEsecProdottoCoeffProduzione.getSize()%>"><% 
  AttivitaEsecProdottoCoeffProduzione.write(out); 
%>

										<% 
  WebTextInput AttivitaEsecProdottoIdUMTecnica =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "IdUMTecnica"); 
  AttivitaEsecProdottoIdUMTecnica.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoIdUMTecnica.getClassType()%>" id="<%=AttivitaEsecProdottoIdUMTecnica.getId()%>" maxlength="<%=AttivitaEsecProdottoIdUMTecnica.getMaxLength()%>" name="<%=AttivitaEsecProdottoIdUMTecnica.getName()%>" readonly size="<%=AttivitaEsecProdottoIdUMTecnica.getSize()%>"><% 
  AttivitaEsecProdottoIdUMTecnica.write(out); 
%>

									</td>
								</tr>
								<tr>
									<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaEsecProdotto", "IdMagazzinoVrs", null); 
   label.setParent(AttivitaEsecProdottoForm); 
%><label class="<%=label.getClassType()%>" for="Magazzino"><%label.write(out);%></label><%}%></td>
									<td><% 
  WebMultiSearchForm AttivitaEsecProdottoMagazzino =  
     new com.thera.thermfw.web.WebMultiSearchForm("AttivitaEsecProdotto", "Magazzino", false, false, true, 1, null, null); 
  AttivitaEsecProdottoMagazzino.setParent(AttivitaEsecProdottoForm); 
  AttivitaEsecProdottoMagazzino.setAdditionalRestrictConditions("ClientePresenteInTestata,Foo"); 
  AttivitaEsecProdottoMagazzino.setSpecificDOList("it.thera.thip.produzione.ordese.web.AttivitaEsecProdottoDOList"); 
  AttivitaEsecProdottoMagazzino.write(out); 
%>
<!--<span class="multisearchform" id="Magazzino"></span>--></td>
								</tr>
								<div>
									<% 
   request.setAttribute("parentForm", AttivitaEsecProdottoForm); 
   String CDForDatiComuniEstesi$it$thera$thip$cs$DatiComuniEstesi$jsp = "DatiComuniEstesi"; 
%>
<jsp:include page="/it/thera/thip/cs/DatiComuniEstesi.jsp" flush="true"> 
<jsp:param name="CDName" value="<%=CDForDatiComuniEstesi$it$thera$thip$cs$DatiComuniEstesi$jsp%>"/> 
</jsp:include> 
<!--<span class="subform" id="Stato" name="Stato"></span>-->
								</div>
								<tr>
									<td></td>
									<td><% 
  WebTextInput AttivitaEsecProdottoIdArticOrdine =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "IdArticOrdine"); 
  AttivitaEsecProdottoIdArticOrdine.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoIdArticOrdine.getClassType()%>" id="<%=AttivitaEsecProdottoIdArticOrdine.getId()%>" maxlength="<%=AttivitaEsecProdottoIdArticOrdine.getMaxLength()%>" name="<%=AttivitaEsecProdottoIdArticOrdine.getName()%>" size="<%=AttivitaEsecProdottoIdArticOrdine.getSize()%>" type="hidden"><% 
  AttivitaEsecProdottoIdArticOrdine.write(out); 
%>
</td>
									<td><% 
  WebTextInput AttivitaEsecProdottoDescrizioneOrdine =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "DescrizioneOrdine"); 
  AttivitaEsecProdottoDescrizioneOrdine.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoDescrizioneOrdine.getClassType()%>" id="<%=AttivitaEsecProdottoDescrizioneOrdine.getId()%>" maxlength="<%=AttivitaEsecProdottoDescrizioneOrdine.getMaxLength()%>" name="<%=AttivitaEsecProdottoDescrizioneOrdine.getName()%>" size="<%=AttivitaEsecProdottoDescrizioneOrdine.getSize()%>" type="hidden"><% 
  AttivitaEsecProdottoDescrizioneOrdine.write(out); 
%>
</td>
									<td><% 
  WebTextInput AttivitaEsecProdottoDescrizioneRidOrdine =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "DescrizioneRidOrdine"); 
  AttivitaEsecProdottoDescrizioneRidOrdine.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoDescrizioneRidOrdine.getClassType()%>" id="<%=AttivitaEsecProdottoDescrizioneRidOrdine.getId()%>" maxlength="<%=AttivitaEsecProdottoDescrizioneRidOrdine.getMaxLength()%>" name="<%=AttivitaEsecProdottoDescrizioneRidOrdine.getName()%>" size="<%=AttivitaEsecProdottoDescrizioneRidOrdine.getSize()%>" type="hidden"><% 
  AttivitaEsecProdottoDescrizioneRidOrdine.write(out); 
%>
</td>
								</tr>
								<tr>
									<td><% 
  WebTextInput AttivitaEsecProdottoIdUMSecMagArt =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "IdUMSecMagArt"); 
  AttivitaEsecProdottoIdUMSecMagArt.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoIdUMSecMagArt.getClassType()%>" id="<%=AttivitaEsecProdottoIdUMSecMagArt.getId()%>" maxlength="<%=AttivitaEsecProdottoIdUMSecMagArt.getMaxLength()%>" name="<%=AttivitaEsecProdottoIdUMSecMagArt.getName()%>" size="<%=AttivitaEsecProdottoIdUMSecMagArt.getSize()%>" type="hidden"><% 
  AttivitaEsecProdottoIdUMSecMagArt.write(out); 
%>
</td>
									<td><% 
  WebTextInput AttivitaEsecProdottoQtaBase =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "QtaBase"); 
  AttivitaEsecProdottoQtaBase.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoQtaBase.getClassType()%>" id="<%=AttivitaEsecProdottoQtaBase.getId()%>" maxlength="<%=AttivitaEsecProdottoQtaBase.getMaxLength()%>" name="<%=AttivitaEsecProdottoQtaBase.getName()%>" size="<%=AttivitaEsecProdottoQtaBase.getSize()%>" type="hidden"><% 
  AttivitaEsecProdottoQtaBase.write(out); 
%>
</td>
									<td><% 
  WebTextInput AttivitaEsecProdottoIdCommessaArticolo =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "IdCommessaArticolo"); 
  AttivitaEsecProdottoIdCommessaArticolo.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoIdCommessaArticolo.getClassType()%>" id="<%=AttivitaEsecProdottoIdCommessaArticolo.getId()%>" maxlength="<%=AttivitaEsecProdottoIdCommessaArticolo.getMaxLength()%>" name="<%=AttivitaEsecProdottoIdCommessaArticolo.getName()%>" size="<%=AttivitaEsecProdottoIdCommessaArticolo.getSize()%>" type="hidden"><% 
  AttivitaEsecProdottoIdCommessaArticolo.write(out); 
%>
</td>
									<td><% 
  WebTextInput AttivitaEsecProdottoIdCommessaDesc =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "IdCommessaDesc"); 
  AttivitaEsecProdottoIdCommessaDesc.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoIdCommessaDesc.getClassType()%>" id="<%=AttivitaEsecProdottoIdCommessaDesc.getId()%>" maxlength="<%=AttivitaEsecProdottoIdCommessaDesc.getMaxLength()%>" name="<%=AttivitaEsecProdottoIdCommessaDesc.getName()%>" size="<%=AttivitaEsecProdottoIdCommessaDesc.getSize()%>" type="hidden"><% 
  AttivitaEsecProdottoIdCommessaDesc.write(out); 
%>
</td>
								</tr>
								<tr>
									<td><% 
  WebTextInput AttivitaEsecProdottoIdVersOrdine =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "IdVersOrdine"); 
  AttivitaEsecProdottoIdVersOrdine.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoIdVersOrdine.getClassType()%>" id="<%=AttivitaEsecProdottoIdVersOrdine.getId()%>" maxlength="<%=AttivitaEsecProdottoIdVersOrdine.getMaxLength()%>" name="<%=AttivitaEsecProdottoIdVersOrdine.getName()%>" size="<%=AttivitaEsecProdottoIdVersOrdine.getSize()%>" type="hidden"><% 
  AttivitaEsecProdottoIdVersOrdine.write(out); 
%>
</td>
									<td><% 
  WebTextInput AttivitaEsecProdottoIdVersOrdineDesc =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "IdVersOrdineDesc"); 
  AttivitaEsecProdottoIdVersOrdineDesc.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoIdVersOrdineDesc.getClassType()%>" id="<%=AttivitaEsecProdottoIdVersOrdineDesc.getId()%>" maxlength="<%=AttivitaEsecProdottoIdVersOrdineDesc.getMaxLength()%>" name="<%=AttivitaEsecProdottoIdVersOrdineDesc.getName()%>" size="<%=AttivitaEsecProdottoIdVersOrdineDesc.getSize()%>" type="hidden"><% 
  AttivitaEsecProdottoIdVersOrdineDesc.write(out); 
%>
</td>
									<td><% 
  WebTextInput AttivitaEsecProdottoIdConfigOrdine =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "IdConfigOrdine"); 
  AttivitaEsecProdottoIdConfigOrdine.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoIdConfigOrdine.getClassType()%>" id="<%=AttivitaEsecProdottoIdConfigOrdine.getId()%>" maxlength="<%=AttivitaEsecProdottoIdConfigOrdine.getMaxLength()%>" name="<%=AttivitaEsecProdottoIdConfigOrdine.getName()%>" size="<%=AttivitaEsecProdottoIdConfigOrdine.getSize()%>" type="hidden"><% 
  AttivitaEsecProdottoIdConfigOrdine.write(out); 
%>
</td>
									<td><% 
  WebTextInput AttivitaEsecProdottoIdConfigOrdineDesc =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "IdConfigOrdineDesc"); 
  AttivitaEsecProdottoIdConfigOrdineDesc.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoIdConfigOrdineDesc.getClassType()%>" id="<%=AttivitaEsecProdottoIdConfigOrdineDesc.getId()%>" maxlength="<%=AttivitaEsecProdottoIdConfigOrdineDesc.getMaxLength()%>" name="<%=AttivitaEsecProdottoIdConfigOrdineDesc.getName()%>" size="<%=AttivitaEsecProdottoIdConfigOrdineDesc.getSize()%>" type="hidden"><% 
  AttivitaEsecProdottoIdConfigOrdineDesc.write(out); 
%>
</td>
								</tr>
							</table>
						<% mytabbed.endTab(); %> 
</div>
						<div class="tabbed_page" id="<%=mytabbed.getTabPageId("Tab2")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("Tab2"); %>
							<table style="margin: 7 7 7 7;">
                                                                <!--Fix 21875 Inizio-->
								<tr>
									<td></td>
									<td><% 
  WebCheckBox AttivitaEsecProdottoEsponilnBollaVersam =  
     new com.thera.thermfw.web.WebCheckBox("AttivitaEsecProdotto", "EsponilnBollaVersam"); 
  AttivitaEsecProdottoEsponilnBollaVersam.setParent(AttivitaEsecProdottoForm); 
%>
<input id="<%=AttivitaEsecProdottoEsponilnBollaVersam.getId()%>" name="<%=AttivitaEsecProdottoEsponilnBollaVersam.getName()%>" type="checkbox" value="Y"><%
  AttivitaEsecProdottoEsponilnBollaVersam.write(out); 
%>
</td>
								</tr>
								<tr>
									<td></td>
									<td><% 
  WebCheckBox AttivitaEsecProdottoEsponilnTagliandoldent =  
     new com.thera.thermfw.web.WebCheckBox("AttivitaEsecProdotto", "EsponilnTagliandoldent"); 
  AttivitaEsecProdottoEsponilnTagliandoldent.setParent(AttivitaEsecProdottoForm); 
%>
<input id="<%=AttivitaEsecProdottoEsponilnTagliandoldent.getId()%>" name="<%=AttivitaEsecProdottoEsponilnTagliandoldent.getName()%>" type="checkbox" value="Y"><%
  AttivitaEsecProdottoEsponilnTagliandoldent.write(out); 
%>
</td>
								</tr>
                                                                <!--Fix 21875 Fine-->
								<tr>
									<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaEsecProdotto", "AzioneSuDispon", null); 
   label.setParent(AttivitaEsecProdottoForm); 
%><label class="<%=label.getClassType()%>" for="AzioneSuDispon"><%label.write(out);%></label><%}%></td>
									<td><% 
  WebComboBox AttivitaEsecProdottoAzioneSuDispon =  
     new com.thera.thermfw.web.WebComboBox("AttivitaEsecProdotto", "AzioneSuDispon", null); 
  AttivitaEsecProdottoAzioneSuDispon.setParent(AttivitaEsecProdottoForm); 
%>
<select id="<%=AttivitaEsecProdottoAzioneSuDispon.getId()%>" name="<%=AttivitaEsecProdottoAzioneSuDispon.getName()%>"><% 
  AttivitaEsecProdottoAzioneSuDispon.write(out); 
%> 
</select></td>
								</tr>
								<tr>
									<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaEsecProdotto", "PoliticaConsVersam", null); 
   label.setParent(AttivitaEsecProdottoForm); 
%><label class="<%=label.getClassType()%>" for="PoliticaConsVersam"><%label.write(out);%></label><%}%></td>
									<td><% 
  WebComboBox AttivitaEsecProdottoPoliticaConsVersam =  
     new com.thera.thermfw.web.WebComboBox("AttivitaEsecProdotto", "PoliticaConsVersam", null); 
  AttivitaEsecProdottoPoliticaConsVersam.setParent(AttivitaEsecProdottoForm); 
%>
<select id="<%=AttivitaEsecProdottoPoliticaConsVersam.getId()%>" name="<%=AttivitaEsecProdottoPoliticaConsVersam.getName()%>"><% 
  AttivitaEsecProdottoPoliticaConsVersam.write(out); 
%> 
</select></td>
								</tr>
                                                                <!--Fix 21875 Blocco commentato Inizio-->
								<!--tr>          <td></td>          <td><input type="checkbox" id="EsponiInBollaVersam" name="EsponiInBollaVersam"/></td>         </tr>         <tr>          <td></td>          <td><input type="checkbox" id="EsponilnTagliandoldent" name="EsponilnTagliandoldent"/></td>         </tr-->
                                                                <!--Fix 21875 Fine-->
								<tr>
									<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaEsecProdotto", "CostoRiferimento", null); 
   label.setParent(AttivitaEsecProdottoForm); 
%><label class="<%=label.getClassType()%>" for="CostoRiferimento"><%label.write(out);%></label><%}%></td>
									<td><% 
  WebTextInput AttivitaEsecProdottoCostoRiferimento =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "CostoRiferimento"); 
  AttivitaEsecProdottoCostoRiferimento.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoCostoRiferimento.getClassType()%>" id="<%=AttivitaEsecProdottoCostoRiferimento.getId()%>" maxlength="<%=AttivitaEsecProdottoCostoRiferimento.getMaxLength()%>" name="<%=AttivitaEsecProdottoCostoRiferimento.getName()%>" size="<%=AttivitaEsecProdottoCostoRiferimento.getSize()%>"><% 
  AttivitaEsecProdottoCostoRiferimento.write(out); 
%>
</td>
								</tr>
							</table>
                                                        <!--Fix 21875 Inizio-->
                                                        <table>
                                                          <tr>
                                                            <td colspan="2">
                                                              <FIELDSET name="TipiCicloAmmessiFieldSet">
                                                                <table>
                                                                  <tr>
                                                                    <td width="135"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaEsecProdotto", "TipiCicloAmmessi", null); 
   label.setParent(AttivitaEsecProdottoForm); 
%><label class="<%=label.getClassType()%>" for="TipiCicloAmmessi"><%label.write(out);%></label><%}%></td><!-- Fix 39405 modefica il width -->
                                                                    <td><% 
  WebComboBox AttivitaEsecProdottoTipiCicloAmmessi =  
     new com.thera.thermfw.web.WebComboBox("AttivitaEsecProdotto", "TipiCicloAmmessi", null); 
  AttivitaEsecProdottoTipiCicloAmmessi.setParent(AttivitaEsecProdottoForm); 
  AttivitaEsecProdottoTipiCicloAmmessi.setOnChange("checkTipoCiclo();"); 
%>
<select id="<%=AttivitaEsecProdottoTipiCicloAmmessi.getId()%>" name="<%=AttivitaEsecProdottoTipiCicloAmmessi.getName()%>"><% 
  AttivitaEsecProdottoTipiCicloAmmessi.write(out); 
%> 
</select></td>
                                                                  </tr>
                                                                  <tr>
                                                                    <td></td>
                                                                    <td> <% 
  WebDoubleList AttivitaEsecProdottoTipiCicloList =  
     new com.thera.thermfw.web.WebDoubleList("AttivitaEsecProdotto", "TipiCicloList", "15", "3", "it.thera.thip.datiTecnici.modpro.resources.AttivitaProduttiva", "Disponibili", "Scelti", "IdTipoCicAzn", true); 
 AttivitaEsecProdottoTipiCicloList.setParent(AttivitaEsecProdottoForm); 
 AttivitaEsecProdottoTipiCicloList.setGetAvailableElements("getTipoCicloAzElements"); 
  AttivitaEsecProdottoTipiCicloList.write(out); 
%>
<!--<span class="doublelist" id="TipiCicloList"></span>--></td>
                                                                  </tr>
                                                                </table>
                                                              </FIELDSET>
                                                             </td>
                                                          </tr>
                                                        </table>
                                                        <!--Fix 21875 Fine-->
						<% mytabbed.endTab(); %> 
</div>
						<div class="tabbed_page" id="<%=mytabbed.getTabPageId("Tab3")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("Tab3"); %>
							<table style="margin: 7 7 0 7;" width="97%">
								<tr>
									<td>
										<fieldset id="QuantitaFieldset"><legend align="top"><label class="thLabel" id="QuantitaLabel">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.produzione.ordese.resources.AttivitaEsecProdotto", "Quantita", null, null, null, null); 
 label.setParent(AttivitaEsecProdottoForm); 
label.write(out); }%> 
</label></legend>
											<table style="margin: 0 0 7 0;">
												<tr>
													<td width="95"></td>
													<td><% 
  WebTextInput AttivitaEsecProdottoDescrizioneUMPrmMag =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "DescrizioneUMPrmMag"); 
  AttivitaEsecProdottoDescrizioneUMPrmMag.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoDescrizioneUMPrmMag.getClassType()%>" id="<%=AttivitaEsecProdottoDescrizioneUMPrmMag.getId()%>" maxlength="<%=AttivitaEsecProdottoDescrizioneUMPrmMag.getMaxLength()%>" name="<%=AttivitaEsecProdottoDescrizioneUMPrmMag.getName()%>" size="35"><% 
  AttivitaEsecProdottoDescrizioneUMPrmMag.write(out); 
%>
</td>
													<td><% 
  WebTextInput AttivitaEsecProdottoDescrizioneUMSecMag =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "DescrizioneUMSecMag"); 
  AttivitaEsecProdottoDescrizioneUMSecMag.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoDescrizioneUMSecMag.getClassType()%>" id="<%=AttivitaEsecProdottoDescrizioneUMSecMag.getId()%>" maxlength="<%=AttivitaEsecProdottoDescrizioneUMSecMag.getMaxLength()%>" name="<%=AttivitaEsecProdottoDescrizioneUMSecMag.getName()%>" size="35"><% 
  AttivitaEsecProdottoDescrizioneUMSecMag.write(out); 
%>
</td>
												</tr>
												<tr>
													<td width="95"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaEsecProdotto", "QtaRichiestaUMPrm", null); 
   label.setParent(AttivitaEsecProdottoForm); 
%><label class="<%=label.getClassType()%>" for="QtaRichiestaUMPrm"><%label.write(out);%></label><%}%></td>
													<td><% 
  WebTextInput AttivitaEsecProdottoQtaRichiestaUMPrm =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "QtaRichiestaUMPrm"); 
  AttivitaEsecProdottoQtaRichiestaUMPrm.setOnChange("fillQtaSec(false)"); 
  AttivitaEsecProdottoQtaRichiestaUMPrm.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoQtaRichiestaUMPrm.getClassType()%>" id="<%=AttivitaEsecProdottoQtaRichiestaUMPrm.getId()%>" maxlength="<%=AttivitaEsecProdottoQtaRichiestaUMPrm.getMaxLength()%>" name="<%=AttivitaEsecProdottoQtaRichiestaUMPrm.getName()%>" size="<%=AttivitaEsecProdottoQtaRichiestaUMPrm.getSize()%>"><% 
  AttivitaEsecProdottoQtaRichiestaUMPrm.write(out); 
%>
</td>
						<td>
              <table cellpadding="0" cellspacing="0">
                <tr>
													<td><% 
  WebTextInput AttivitaEsecProdottoQtaRichiestaUMSec =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "QtaRichiestaUMSec"); 
  AttivitaEsecProdottoQtaRichiestaUMSec.setOnChange("fillQtaPrm(false)"); 
  AttivitaEsecProdottoQtaRichiestaUMSec.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoQtaRichiestaUMSec.getClassType()%>" id="<%=AttivitaEsecProdottoQtaRichiestaUMSec.getId()%>" maxlength="<%=AttivitaEsecProdottoQtaRichiestaUMSec.getMaxLength()%>" name="<%=AttivitaEsecProdottoQtaRichiestaUMSec.getName()%>" size="<%=AttivitaEsecProdottoQtaRichiestaUMSec.getSize()%>"><% 
  AttivitaEsecProdottoQtaRichiestaUMSec.write(out); 
%>
</td>
                  <td width="5"></td>
         			    <td><% 
  WebCheckBox AttivitaEsecProdottoRicalcoloQtaFattoreConv =  
     new com.thera.thermfw.web.WebCheckBox("AttivitaEsecProdotto", "RicalcoloQtaFattoreConv"); 
  AttivitaEsecProdottoRicalcoloQtaFattoreConv.setParent(AttivitaEsecProdottoForm); 
%>
<input id="<%=AttivitaEsecProdottoRicalcoloQtaFattoreConv.getId()%>" name="<%=AttivitaEsecProdottoRicalcoloQtaFattoreConv.getName()%>" type="checkbox" value="Y"><%
  AttivitaEsecProdottoRicalcoloQtaFattoreConv.write(out); 
%>
</td>
                </tr>
              </table>
            </td>
												</tr>
												<tr>
													<td width="95"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaEsecProdotto", "QtaVersataUMPrm", null); 
   label.setParent(AttivitaEsecProdottoForm); 
%><label class="<%=label.getClassType()%>" for="QtaVersataUMPrm"><%label.write(out);%></label><%}%></td>
													<td><% 
  WebTextInput AttivitaEsecProdottoQtaVersataUMPrm =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "QtaVersataUMPrm"); 
  AttivitaEsecProdottoQtaVersataUMPrm.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoQtaVersataUMPrm.getClassType()%>" id="<%=AttivitaEsecProdottoQtaVersataUMPrm.getId()%>" maxlength="<%=AttivitaEsecProdottoQtaVersataUMPrm.getMaxLength()%>" name="<%=AttivitaEsecProdottoQtaVersataUMPrm.getName()%>" size="<%=AttivitaEsecProdottoQtaVersataUMPrm.getSize()%>"><% 
  AttivitaEsecProdottoQtaVersataUMPrm.write(out); 
%>
</td>
													<td><% 
  WebTextInput AttivitaEsecProdottoQtaVersataUMSec =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "QtaVersataUMSec"); 
  AttivitaEsecProdottoQtaVersataUMSec.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoQtaVersataUMSec.getClassType()%>" id="<%=AttivitaEsecProdottoQtaVersataUMSec.getId()%>" maxlength="<%=AttivitaEsecProdottoQtaVersataUMSec.getMaxLength()%>" name="<%=AttivitaEsecProdottoQtaVersataUMSec.getName()%>" size="<%=AttivitaEsecProdottoQtaVersataUMSec.getSize()%>"><% 
  AttivitaEsecProdottoQtaVersataUMSec.write(out); 
%>
</td>
												</tr>
												<tr>
													<td width="95"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaEsecProdotto", "QtaResiduaUMPrm", null); 
   label.setParent(AttivitaEsecProdottoForm); 
%><label class="<%=label.getClassType()%>" for="QtaResiduaUMPrm"><%label.write(out);%></label><%}%></td>
													<td><% 
  WebTextInput AttivitaEsecProdottoQtaResiduaUMPrm =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "QtaResiduaUMPrm"); 
  AttivitaEsecProdottoQtaResiduaUMPrm.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoQtaResiduaUMPrm.getClassType()%>" id="<%=AttivitaEsecProdottoQtaResiduaUMPrm.getId()%>" maxlength="<%=AttivitaEsecProdottoQtaResiduaUMPrm.getMaxLength()%>" name="<%=AttivitaEsecProdottoQtaResiduaUMPrm.getName()%>" size="<%=AttivitaEsecProdottoQtaResiduaUMPrm.getSize()%>"><% 
  AttivitaEsecProdottoQtaResiduaUMPrm.write(out); 
%>
</td>
													<td><% 
  WebTextInput AttivitaEsecProdottoQtaResiduaUMSec =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "QtaResiduaUMSec"); 
  AttivitaEsecProdottoQtaResiduaUMSec.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoQtaResiduaUMSec.getClassType()%>" id="<%=AttivitaEsecProdottoQtaResiduaUMSec.getId()%>" maxlength="<%=AttivitaEsecProdottoQtaResiduaUMSec.getMaxLength()%>" name="<%=AttivitaEsecProdottoQtaResiduaUMSec.getName()%>" size="<%=AttivitaEsecProdottoQtaResiduaUMSec.getSize()%>"><% 
  AttivitaEsecProdottoQtaResiduaUMSec.write(out); 
%>
</td>
												</tr>
											</table>
										</fieldset>
									</td>
								</tr>
							</table>
							<table style="margin: 0 7 7 7;" width="97%">
							<tr>
								<td>
									<fieldset id="DateFieldset"><legend align="top"><label class="thLabel" id="DateLabel">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.produzione.ordese.resources.AttivitaEsecProdotto", "Date", null, null, null, null); 
 label.setParent(AttivitaEsecProdottoForm); 
label.write(out); }%> 
</label></legend>
										<table style="margin: 0 0 7 0;">
											<tr>
												<td width="95"></td>
												<td></td>
												<td><label class="thLabel" id="SettimanaLabel">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.produzione.ordese.resources.AttivitaEsecProdotto", "Settimana", null, null, null, null); 
 label.setParent(AttivitaEsecProdottoForm); 
label.write(out); }%> 
</label></td>
											</tr>
											<tr>
												<td width="95"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaEsecProdotto", "DataPrevistaDispon", null); 
   label.setParent(AttivitaEsecProdottoForm); 
%><label class="<%=label.getClassType()%>" for="DataPrevistaDispon"><%label.write(out);%></label><%}%></td>
												<!-- <td><input id="DataPrevistaDispon" name="DataPrevistaDispon" size="8"/></td> --> <!-- Fix 37228 -->
												<td><% 
  WebTextInput AttivitaEsecProdottoDataPrevistaDispon =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "DataPrevistaDispon"); 
  AttivitaEsecProdottoDataPrevistaDispon.setOnChange("weekNumeroPrevistaDisponDate()"); 
  AttivitaEsecProdottoDataPrevistaDispon.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoDataPrevistaDispon.getClassType()%>" id="<%=AttivitaEsecProdottoDataPrevistaDispon.getId()%>" maxlength="<%=AttivitaEsecProdottoDataPrevistaDispon.getMaxLength()%>" name="<%=AttivitaEsecProdottoDataPrevistaDispon.getName()%>" size="10"><% 
  AttivitaEsecProdottoDataPrevistaDispon.write(out); 
%>
</td> <!-- Fix 37228 -->
												<!-- <td><input id="SettPrevistaDispon" name="SettPrevistaDispon" size="1"/></td> --><!--Fix 32615 -->
												<td><input id="SettPrevistaDispon" name="SettPrevistaDispon" size="2"></td><!--Fix 32615 -->
											</tr>
											<% 
   request.setAttribute("parentForm", AttivitaEsecProdottoForm); 
   String CDForDateVersamenti$it$thera$thip$produzione$ordese$DateRangeSettVersamenti$jsp = "DateVersamenti"; 
%>
<jsp:include page="/it/thera/thip/produzione/ordese/DateRangeSettVersamenti.jsp" flush="true"> 
<jsp:param name="CDName" value="<%=CDForDateVersamenti$it$thera$thip$produzione$ordese$DateRangeSettVersamenti$jsp%>"/> 
</jsp:include> 
<!--<span class="subform" id="DateVersamenti"></span>-->
										</table>
									</fieldset>
								</td>
							</tr>
						</table>
						<table style="margin: 0 7 7 7;">
							<tr>
								<td width="100"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AttivitaEsecProdotto", "StatoVersamento", null); 
   label.setParent(AttivitaEsecProdottoForm); 
%><label class="<%=label.getClassType()%>" for="StatoVersamento"><%label.write(out);%></label><%}%></td>
								<td><% 
  WebComboBox AttivitaEsecProdottoStatoVersamento =  
     new com.thera.thermfw.web.WebComboBox("AttivitaEsecProdotto", "StatoVersamento", null); 
  AttivitaEsecProdottoStatoVersamento.setParent(AttivitaEsecProdottoForm); 
  AttivitaEsecProdottoStatoVersamento.setOnChange("enableBattoneSaldaRiapriProdotto();"); 
%>
<select id="<%=AttivitaEsecProdottoStatoVersamento.getId()%>" name="<%=AttivitaEsecProdottoStatoVersamento.getName()%>"><% 
  AttivitaEsecProdottoStatoVersamento.write(out); 
%> 
</select></td>
								<td><% 
  WebCheckBox AttivitaEsecProdottoSaldoManuale =  
     new com.thera.thermfw.web.WebCheckBox("AttivitaEsecProdotto", "SaldoManuale"); 
  AttivitaEsecProdottoSaldoManuale.setParent(AttivitaEsecProdottoForm); 
  AttivitaEsecProdottoSaldoManuale.setOnClick("enableBattoneSaldaRiapriProdotto();"); 
%>
<input id="<%=AttivitaEsecProdottoSaldoManuale.getId()%>" name="<%=AttivitaEsecProdottoSaldoManuale.getName()%>" type="checkbox" value="Y"><%
  AttivitaEsecProdottoSaldoManuale.write(out); 
%>
</td>
								<td><button id="SaldaVersamento" name="SaldaVersamento" onclick="ConfermaSaldaVersamento()" type="button"><%= ResourceLoader.getString("it.thera.thip.produzione.ordese.resources.AttivitaEsecProdotto", "SaldaVersamento")%></button></td> <!--Fix 12141 -->
								<td><button id="RiapriVersamento" name="RiapriVersamento" onclick="ConfermaRiapriVersamento()" type="button"><%= ResourceLoader.getString("it.thera.thip.produzione.ordese.resources.AttivitaEsecProdotto", "RiapriVersamento")%></button></td> <!--Fix 12141 -->
							</tr>
						</table>
					<% mytabbed.endTab(); %> 
</div>
					<div class="tabbed_page" id="<%=mytabbed.getTabPageId("tab4")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("tab4"); %>
					<!-- 71XXX Softre Inizio -->
						<table cellpadding="2" cellspacing="2" style="padding: 3px; width: 100%">
								<tr>
									<td nowrap="nowrap" colspan ="1" id="SezCodificaLottiTestori">
										<button style="width: 75px" name="CodificaLottiTestori" id="CodificaLottiTestori" 
										type="button" onClick="codificaLottiTestori()"><%= ResourceLoader.getString("it.testori.thip.magazzino.generalemag.resources.LottiTestori", "btnCreaLotti")%></button>
									</td>
									</table>
						<!-- 71XXX Softre Fine -->
						<table style="margin: 7 7 7 7;" width="97%">
							<tr>
								<td valign="top"><!--<span class="editgrid" id="LottiProdotti">--><% 
  WebEditGrid AttivitaEsecProdottoLottiProdotti =  
     new com.thera.thermfw.web.WebEditGrid("AttivitaEsecProdotto", "LottiProdotti", 3, new String[]{"IdLotto", "Lotto.IdLottoProdotto", "Lotto.IdLottoPrdFormattato", "QtaRichiestaUMPrm", "QtaVersataUMPrm", "StatoLotto"}, 1, null, null,false,"com.thera.thermfw.web.servlet.GridActionAdapterForIndependentRow"); 
 AttivitaEsecProdottoLottiProdotti.setParent(AttivitaEsecProdottoForm); 
 AttivitaEsecProdottoLottiProdotti.setNoControlRowKeys(false); 
 AttivitaEsecProdottoLottiProdotti.addHideAsDefault("IdLotto"); 
 AttivitaEsecProdottoLottiProdotti.addHideAsDefault("Lotto.IdLottoProdotto"); 
 AttivitaEsecProdottoLottiProdotti.includeAction("DeleteRow",new WebMenuItem("GestioneMatricole", "action_submit", "new", "no", "it.thera.thip.magazzino.matricole.resources.LottoMatricola", "GestioneMatricoleBtn", "gestioneMatricole()", "none", true)); 
 AttivitaEsecProdottoLottiProdotti.write(out); 
%>
<BR><% 
   request.setAttribute("parentForm", AttivitaEsecProdottoForm); 
   String CDForLottiProdotti = "LottiProdotti"; 
%>
<jsp:include page="/it/thera/thip/produzione/ordese/AttivitaEsecLottiPrd.jsp" flush="true"> 
<jsp:param name="EditGridCDName" value="<%=CDForLottiProdotti%>"/> 
<jsp:param name="Mode" value="NEW"/> 
</jsp:include> 
<!--</span>--></td>
							</tr>
						</table>
					<% mytabbed.endTab(); %> 
</div>
					<div class="tabbed_page" id="<%=mytabbed.getTabPageId("Tab5")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("Tab5"); %>
						<table style="margin: 7 7 7 7;" width="97%">
							<tr>
								<td valign="top"><% 
   request.setAttribute("parentForm", AttivitaEsecProdottoForm); 
   String CDForDescrizione$it$thera$thip$cs$DescrizioneInLingua$jsp = "Descrizione"; 
%>
<jsp:include page="/it/thera/thip/cs/DescrizioneInLingua.jsp" flush="true"> 
<jsp:param name="CDName" value="<%=CDForDescrizione$it$thera$thip$cs$DescrizioneInLingua$jsp%>"/> 
</jsp:include> 
<!--<span class="subform" id="DescNLS"></span>--></td>
							</tr>
						</table>
					<% mytabbed.endTab(); %> 
</div>
				</div><% mytabbed.endTabbed();%> 

     </td>
   </tr>
</table><!--</span>-->
			</td>
		</tr>
		<tr>
			<td style="height:0"><% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(AttivitaEsecProdottoForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>--></td>
		</tr>
	</table>
	<!-- LR - CLI - inizio -->
	<% 
  WebTextInput AttivitaEsecProdottoClientePresenteInTestata =  
     new com.thera.thermfw.web.WebTextInput("AttivitaEsecProdotto", "ClientePresenteInTestata"); 
  AttivitaEsecProdottoClientePresenteInTestata.setParent(AttivitaEsecProdottoForm); 
%>
<input class="<%=AttivitaEsecProdottoClientePresenteInTestata.getClassType()%>" id="<%=AttivitaEsecProdottoClientePresenteInTestata.getId()%>" maxlength="<%=AttivitaEsecProdottoClientePresenteInTestata.getMaxLength()%>" name="<%=AttivitaEsecProdottoClientePresenteInTestata.getName()%>" size="<%=AttivitaEsecProdottoClientePresenteInTestata.getSize()%>" type="hidden" value><% 
  AttivitaEsecProdottoClientePresenteInTestata.write(out); 
%>

	<!-- LR - CLI - fine -->


<%
  AttivitaEsecProdottoForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = AttivitaEsecProdottoForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", AttivitaEsecProdottoBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>



<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              AttivitaEsecProdottoForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, AttivitaEsecProdottoBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, AttivitaEsecProdottoBODC.getErrorList().getErrors()); 
           if(AttivitaEsecProdottoBODC.getConflict() != null) 
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
     if(AttivitaEsecProdottoBODC != null && !AttivitaEsecProdottoBODC.close(false)) 
        errors.addAll(0, AttivitaEsecProdottoBODC.getErrorList().getErrors()); 
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
     String errorPage = AttivitaEsecProdottoForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", AttivitaEsecProdottoBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = AttivitaEsecProdottoForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>

</html>
