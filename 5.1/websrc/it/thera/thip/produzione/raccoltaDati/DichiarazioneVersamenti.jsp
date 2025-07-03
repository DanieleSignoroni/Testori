<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///D:\3rdParty\Panthera4.6.0\websrc\dtd/xhtml1-transitional.dtd">
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
  BODataCollector RilevDatiPrdTSBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm RilevDatiPrdTSForm =  
     new com.thera.thermfw.web.WebForm(request, response, "RilevDatiPrdTSForm", "RilevDatiPrdTS", null, "it.thera.thip.produzione.raccoltaDati.web.RilevDatiPrdTSFormActionAdapter", false, false, false, false, true, true, null, 1, true, "it/thera/thip/produzione/raccoltaDati/DichiarazioneVersamenti.js"); 
  RilevDatiPrdTSForm.setServletEnvironment(se); 
  RilevDatiPrdTSForm.setJSTypeList(jsList); 
  RilevDatiPrdTSForm.setHeader(null); 
  RilevDatiPrdTSForm.setFooter(null); 
  RilevDatiPrdTSForm.setWebFormModifierClass("it.thera.thip.produzione.raccoltaDati.web.RilevDatiPrdTSWebFormModifier"); 
  RilevDatiPrdTSForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = RilevDatiPrdTSForm.getMode(); 
  String key = RilevDatiPrdTSForm.getKey(); 
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
        RilevDatiPrdTSForm.outTraceInfo(getClass().getName()); 
        String collectorName = RilevDatiPrdTSForm.findBODataCollectorName(); 
                RilevDatiPrdTSBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (RilevDatiPrdTSBODC instanceof WebDataCollector) 
            ((WebDataCollector)RilevDatiPrdTSBODC).setServletEnvironment(se); 
        RilevDatiPrdTSBODC.initialize("RilevDatiPrdTS", true, 1); 
        RilevDatiPrdTSForm.setBODataCollector(RilevDatiPrdTSBODC); 
        int rcBODC = RilevDatiPrdTSForm.initSecurityServices(); 
        mode = RilevDatiPrdTSForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           RilevDatiPrdTSForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = RilevDatiPrdTSBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              RilevDatiPrdTSForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 

<title></title>
</head>
<body bottommargin="0" leftmargin="0" onfocus="manageKeyPad();" onbeforeunload="<%=RilevDatiPrdTSForm.getBodyOnBeforeUnload()%>" onclick="manageKeyPad();" onload="<%=RilevDatiPrdTSForm.getBodyOnLoad()%>" onunload="<%=RilevDatiPrdTSForm.getBodyOnUnload()%>" rightmargin="0" topmargin="0"><%
   RilevDatiPrdTSForm.writeBodyStartElements(out); 
%> 
<!--Fix 15250--><!-- Fix 31082 -->

<table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = RilevDatiPrdTSForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", RilevDatiPrdTSBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=RilevDatiPrdTSForm.getServlet()%>" method="post" name="RilevDatiPrdTSForm" style="height:100%"><%
  RilevDatiPrdTSForm.writeFormStartElements(out); 
%>


<table class="maintable" style="height:100%;">
  <tr valign="top">
    <td colspan="2" style="height:15px"></td>
  </tr>
  <tr valign="top">
    <td width="15px"></td>
    <td width="1">
      <table cellpadding="3" cellspacing="3">
        <tr valign="top">
          <td nowrap><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "RilevDatiPrdTS", "DescrizioneOperatore", null); 
   label.setParent(RilevDatiPrdTSForm); 
%><label class="<%=label.getClassType()%>" for="DescrizioneOperatore"><%label.write(out);%></label><%}%></td>
          <td colspan="2"><% 
  WebTextInput RilevDatiPrdTSDescrizioneOperatore =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneOperatore"); 
  RilevDatiPrdTSDescrizioneOperatore.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneOperatore.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneOperatore.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneOperatore.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneOperatore.getName()%>" readonly size="40" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneOperatore.write(out); 
%>
</td>
        </tr>
        <tr id="BollaTR">
          <td id="LabelBollaLavorazione" nowrap><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "RilevDatiPrdTS", "BollaLavorazione", null); 
   label.setParent(RilevDatiPrdTSForm); 
%><label class="<%=label.getClassType()%>" for="BollaLavorazione"><%label.write(out);%></label><%}%></td> <!-- Fix 14722 Aggiunto Id-->
          <td id="LabelBollaCucita" nowrap style="display:none;"><label class="thLabel" id="BollaCucita" name="BollaCucita">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("it.thera.thip.produzione.raccoltaDati.resources.RilevDatiPrdTS", "LabelBollaCucita", null, null, null, null); 
 label.setParent(RilevDatiPrdTSForm); 
label.write(out); }%> 
</label></td> <!-- Fix 14722 -->
          <td><% 
  WebTextInput RilevDatiPrdTSBollaLavorazione =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "BollaLavorazione"); 
  RilevDatiPrdTSBollaLavorazione.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSBollaLavorazione.getClassType()%>" id="<%=RilevDatiPrdTSBollaLavorazione.getId()%>" maxlength="<%=RilevDatiPrdTSBollaLavorazione.getMaxLength()%>" name="<%=RilevDatiPrdTSBollaLavorazione.getName()%>" readonly size="12" tabindex="-1"><% 
  RilevDatiPrdTSBollaLavorazione.write(out); 
%>
</td>
          <td><% 
  WebTextInput RilevDatiPrdTSDescrizioneBolla =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneBolla"); 
  RilevDatiPrdTSDescrizioneBolla.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneBolla.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneBolla.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneBolla.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneBolla.getName()%>" readonly size="40" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneBolla.write(out); 
%>
</td>
        </tr>
        <tr id="TrOrdine" valign="top"> <!-- Fix 14722 Aggiunto Id-->
          <td nowrap><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "RilevDatiPrdTS", "IdNumeroOrdine", null); 
   label.setParent(RilevDatiPrdTSForm); 
%><label class="<%=label.getClassType()%>" for="IdNumeroOrdine"><%label.write(out);%></label><%}%></td>
          <td colspan="2">
            <% 
  WebTextInput RilevDatiPrdTSIdAnnoOrdine =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdAnnoOrdine"); 
  RilevDatiPrdTSIdAnnoOrdine.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdAnnoOrdine.getClassType()%>" id="<%=RilevDatiPrdTSIdAnnoOrdine.getId()%>" maxlength="<%=RilevDatiPrdTSIdAnnoOrdine.getMaxLength()%>" name="<%=RilevDatiPrdTSIdAnnoOrdine.getName()%>" readonly size="4" tabindex="-1"><% 
  RilevDatiPrdTSIdAnnoOrdine.write(out); 
%>

            <% 
  WebTextInput RilevDatiPrdTSIdNumeroOrdine =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdNumeroOrdine"); 
  RilevDatiPrdTSIdNumeroOrdine.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdNumeroOrdine.getClassType()%>" id="<%=RilevDatiPrdTSIdNumeroOrdine.getId()%>" maxlength="<%=RilevDatiPrdTSIdNumeroOrdine.getMaxLength()%>" name="<%=RilevDatiPrdTSIdNumeroOrdine.getName()%>" readonly size="<%=RilevDatiPrdTSIdNumeroOrdine.getSize()%>" style="width:228px" tabindex="-1"><% 
  RilevDatiPrdTSIdNumeroOrdine.write(out); 
%>

          </td>
        </tr>
        <!-- Fix 14722 inizio-->
        <tr id="TrBollaCucita" style="display:none;" valign="top">
          <td nowrap><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "RilevDatiPrdTS", "IdNumeroBolla", null); 
   label.setParent(RilevDatiPrdTSForm); 
%><label class="<%=label.getClassType()%>" for="IdNumeroBolla"><%label.write(out);%></label><%}%></td>
          <td colspan="2">
            <% 
  WebTextInput RilevDatiPrdTSIdAnnoBolla =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdAnnoBolla"); 
  RilevDatiPrdTSIdAnnoBolla.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdAnnoBolla.getClassType()%>" id="<%=RilevDatiPrdTSIdAnnoBolla.getId()%>" maxlength="<%=RilevDatiPrdTSIdAnnoBolla.getMaxLength()%>" name="<%=RilevDatiPrdTSIdAnnoBolla.getName()%>" readonly size="4" tabindex="-1"><% 
  RilevDatiPrdTSIdAnnoBolla.write(out); 
%>

            <% 
  WebTextInput RilevDatiPrdTSIdNumeroBolla =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdNumeroBolla"); 
  RilevDatiPrdTSIdNumeroBolla.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdNumeroBolla.getClassType()%>" id="<%=RilevDatiPrdTSIdNumeroBolla.getId()%>" maxlength="<%=RilevDatiPrdTSIdNumeroBolla.getMaxLength()%>" name="<%=RilevDatiPrdTSIdNumeroBolla.getName()%>" readonly size="10" style="width:228px" tabindex="-1"><% 
  RilevDatiPrdTSIdNumeroBolla.write(out); 
%>

          </td>
        </tr>
        <!-- Fix 14722 fine-->
        <tr style="display:none">
          <td colspan="2">
            <% 
  WebTextInput RilevDatiPrdTSIdArticolo =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdArticolo"); 
  RilevDatiPrdTSIdArticolo.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdArticolo.getClassType()%>" id="<%=RilevDatiPrdTSIdArticolo.getId()%>" maxlength="<%=RilevDatiPrdTSIdArticolo.getMaxLength()%>" name="<%=RilevDatiPrdTSIdArticolo.getName()%>" size="<%=RilevDatiPrdTSIdArticolo.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdArticolo.write(out); 
%>

            <% 
  WebTextInput RilevDatiPrdTSIdOperatore =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdOperatore"); 
  RilevDatiPrdTSIdOperatore.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdOperatore.getClassType()%>" id="<%=RilevDatiPrdTSIdOperatore.getId()%>" maxlength="<%=RilevDatiPrdTSIdOperatore.getMaxLength()%>" name="<%=RilevDatiPrdTSIdOperatore.getName()%>" size="<%=RilevDatiPrdTSIdOperatore.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdOperatore.write(out); 
%>

            <% 
  WebTextInput RilevDatiPrdTSTipoTimbratura =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "TipoTimbratura"); 
  RilevDatiPrdTSTipoTimbratura.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSTipoTimbratura.getClassType()%>" id="<%=RilevDatiPrdTSTipoTimbratura.getId()%>" maxlength="<%=RilevDatiPrdTSTipoTimbratura.getMaxLength()%>" name="<%=RilevDatiPrdTSTipoTimbratura.getName()%>" size="<%=RilevDatiPrdTSTipoTimbratura.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSTipoTimbratura.write(out); 
%>

            <% 
  WebTextInput RilevDatiPrdTSIdCausaleRilevazione =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdCausaleRilevazione"); 
  RilevDatiPrdTSIdCausaleRilevazione.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdCausaleRilevazione.getClassType()%>" id="<%=RilevDatiPrdTSIdCausaleRilevazione.getId()%>" maxlength="<%=RilevDatiPrdTSIdCausaleRilevazione.getMaxLength()%>" name="<%=RilevDatiPrdTSIdCausaleRilevazione.getName()%>" size="<%=RilevDatiPrdTSIdCausaleRilevazione.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdCausaleRilevazione.write(out); 
%>

            <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita"); 
  RilevDatiPrdTSIdRigaAttivita.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita.write(out); 
%>

            <% 
  WebTextInput RilevDatiPrdTSTipoRisorsa =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "TipoRisorsa"); 
  RilevDatiPrdTSTipoRisorsa.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSTipoRisorsa.getClassType()%>" id="<%=RilevDatiPrdTSTipoRisorsa.getId()%>" maxlength="<%=RilevDatiPrdTSTipoRisorsa.getMaxLength()%>" name="<%=RilevDatiPrdTSTipoRisorsa.getName()%>" size="<%=RilevDatiPrdTSTipoRisorsa.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSTipoRisorsa.write(out); 
%>

            <% 
  WebTextInput RilevDatiPrdTSLivelloRisorsa =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "LivelloRisorsa"); 
  RilevDatiPrdTSLivelloRisorsa.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSLivelloRisorsa.getClassType()%>" id="<%=RilevDatiPrdTSLivelloRisorsa.getId()%>" maxlength="<%=RilevDatiPrdTSLivelloRisorsa.getMaxLength()%>" name="<%=RilevDatiPrdTSLivelloRisorsa.getName()%>" size="<%=RilevDatiPrdTSLivelloRisorsa.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSLivelloRisorsa.write(out); 
%>

            <% 
  WebTextInput RilevDatiPrdTSIdRisorsa =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRisorsa"); 
  RilevDatiPrdTSIdRisorsa.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRisorsa.getClassType()%>" id="<%=RilevDatiPrdTSIdRisorsa.getId()%>" maxlength="<%=RilevDatiPrdTSIdRisorsa.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRisorsa.getName()%>" size="<%=RilevDatiPrdTSIdRisorsa.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRisorsa.write(out); 
%>

            <% 
  WebTextInput RilevDatiPrdTSIdAmbiente =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdAmbiente"); 
  RilevDatiPrdTSIdAmbiente.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdAmbiente.getClassType()%>" id="<%=RilevDatiPrdTSIdAmbiente.getId()%>" maxlength="<%=RilevDatiPrdTSIdAmbiente.getMaxLength()%>" name="<%=RilevDatiPrdTSIdAmbiente.getName()%>" size="<%=RilevDatiPrdTSIdAmbiente.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdAmbiente.write(out); 
%>

            <% 
  WebTextInput RilevDatiPrdTSTipoBolla =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "TipoBolla"); 
  RilevDatiPrdTSTipoBolla.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSTipoBolla.getClassType()%>" id="<%=RilevDatiPrdTSTipoBolla.getId()%>" maxlength="<%=RilevDatiPrdTSTipoBolla.getMaxLength()%>" name="<%=RilevDatiPrdTSTipoBolla.getName()%>" size="<%=RilevDatiPrdTSTipoBolla.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSTipoBolla.write(out); 
%>

            <% 
  WebTextInput RilevDatiPrdTSTempo =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "Tempo"); 
  RilevDatiPrdTSTempo.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSTempo.getClassType()%>" id="<%=RilevDatiPrdTSTempo.getId()%>" maxlength="<%=RilevDatiPrdTSTempo.getMaxLength()%>" name="<%=RilevDatiPrdTSTempo.getName()%>" size="<%=RilevDatiPrdTSTempo.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSTempo.write(out); 
%>

            <% 
  WebTextInput RilevDatiPrdTSNote =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "Note"); 
  RilevDatiPrdTSNote.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSNote.getClassType()%>" id="<%=RilevDatiPrdTSNote.getId()%>" maxlength="<%=RilevDatiPrdTSNote.getMaxLength()%>" name="<%=RilevDatiPrdTSNote.getName()%>" size="<%=RilevDatiPrdTSNote.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSNote.write(out); 
%>

            <!--Fix 17241 inizio-->
            <% 
  WebTextInput RilevDatiPrdTSQuantita =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "Quantita"); 
  RilevDatiPrdTSQuantita.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQuantita.getClassType()%>" id="<%=RilevDatiPrdTSQuantita.getId()%>" maxlength="<%=RilevDatiPrdTSQuantita.getMaxLength()%>" name="<%=RilevDatiPrdTSQuantita.getName()%>" size="<%=RilevDatiPrdTSQuantita.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSQuantita.write(out); 
%>

            <% 
  WebTextInput RilevDatiPrdTSQuantitaSec =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QuantitaSec"); 
  RilevDatiPrdTSQuantitaSec.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQuantitaSec.getClassType()%>" id="<%=RilevDatiPrdTSQuantitaSec.getId()%>" maxlength="<%=RilevDatiPrdTSQuantitaSec.getMaxLength()%>" name="<%=RilevDatiPrdTSQuantitaSec.getName()%>" size="<%=RilevDatiPrdTSQuantitaSec.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSQuantitaSec.write(out); 
%>

            <!--Fix 17241 fine-->
            <!--Fix 17550 inizio-->
            <% 
  WebTextInput RilevDatiPrdTSQtaScarto =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScarto"); 
  RilevDatiPrdTSQtaScarto.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScarto.getClassType()%>" id="<%=RilevDatiPrdTSQtaScarto.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScarto.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScarto.getName()%>" size="<%=RilevDatiPrdTSQtaScarto.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSQtaScarto.write(out); 
%>

            <% 
  WebTextInput RilevDatiPrdTSQtaScartoSec =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoSec"); 
  RilevDatiPrdTSQtaScartoSec.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoSec.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoSec.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoSec.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoSec.getName()%>" size="<%=RilevDatiPrdTSQtaScartoSec.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSQtaScartoSec.write(out); 
%>

            <!--Fix 17550 fine-->
            <% 
  WebTextInput RilevDatiPrdTSCurrentNumPag =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "CurrentNumPag"); 
  RilevDatiPrdTSCurrentNumPag.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSCurrentNumPag.getClassType()%>" id="<%=RilevDatiPrdTSCurrentNumPag.getId()%>" maxlength="<%=RilevDatiPrdTSCurrentNumPag.getMaxLength()%>" name="<%=RilevDatiPrdTSCurrentNumPag.getName()%>" size="<%=RilevDatiPrdTSCurrentNumPag.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSCurrentNumPag.write(out); 
%>
<!-- Fix 18536 -->
            <% 
  WebTextInput RilevDatiPrdTSOraFine =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "OraFine"); 
  RilevDatiPrdTSOraFine.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSOraFine.getClassType()%>" id="<%=RilevDatiPrdTSOraFine.getId()%>" maxlength="<%=RilevDatiPrdTSOraFine.getMaxLength()%>" name="<%=RilevDatiPrdTSOraFine.getName()%>" size="<%=RilevDatiPrdTSOraFine.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSOraFine.write(out); 
%>
<!-- Fix 19148 -->
            <% 
  WebTextInput RilevDatiPrdTSDataFine =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DataFine"); 
  RilevDatiPrdTSDataFine.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDataFine.getClassType()%>" id="<%=RilevDatiPrdTSDataFine.getId()%>" maxlength="<%=RilevDatiPrdTSDataFine.getMaxLength()%>" name="<%=RilevDatiPrdTSDataFine.getName()%>" size="<%=RilevDatiPrdTSDataFine.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSDataFine.write(out); 
%>
<!-- Fix 30440 -->
            <% 
  WebTextInput RilevDatiPrdTSStampaBolla =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "StampaBolla"); 
  RilevDatiPrdTSStampaBolla.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSStampaBolla.getClassType()%>" id="<%=RilevDatiPrdTSStampaBolla.getId()%>" maxlength="<%=RilevDatiPrdTSStampaBolla.getMaxLength()%>" name="<%=RilevDatiPrdTSStampaBolla.getName()%>" size="<%=RilevDatiPrdTSStampaBolla.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSStampaBolla.write(out); 
%>
<!-- Fix 30900 -->
          </td>
          <!-- Fix 39680 Inizio -->
          <td style="display:none">
              <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantita =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantita"); 
  RilevDatiPrdTSRicalcoloQuantita.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantita.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantita.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantita.write(out); 
%>

			  <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaPrl1 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaPrl1"); 
  RilevDatiPrdTSRicalcoloQuantitaPrl1.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaPrl1.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaPrl1.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaPrl1.write(out); 
%>

			  <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaPrl2 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaPrl2"); 
  RilevDatiPrdTSRicalcoloQuantitaPrl2.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaPrl2.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaPrl2.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaPrl2.write(out); 
%>

			  <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaPrl3 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaPrl3"); 
  RilevDatiPrdTSRicalcoloQuantitaPrl3.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaPrl3.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaPrl3.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaPrl3.write(out); 
%>

			  <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaPrl4 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaPrl4"); 
  RilevDatiPrdTSRicalcoloQuantitaPrl4.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaPrl4.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaPrl4.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaPrl4.write(out); 
%>

			  <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaPrl5 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaPrl5"); 
  RilevDatiPrdTSRicalcoloQuantitaPrl5.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaPrl5.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaPrl5.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaPrl5.write(out); 
%>

			  <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaPrl6 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaPrl6"); 
  RilevDatiPrdTSRicalcoloQuantitaPrl6.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaPrl6.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaPrl6.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaPrl6.write(out); 
%>

			  <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaPrl7 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaPrl7"); 
  RilevDatiPrdTSRicalcoloQuantitaPrl7.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaPrl7.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaPrl7.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaPrl7.write(out); 
%>

			  <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaPrl8 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaPrl8"); 
  RilevDatiPrdTSRicalcoloQuantitaPrl8.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaPrl8.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaPrl8.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaPrl8.write(out); 
%>

			  <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaPrl9 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaPrl9"); 
  RilevDatiPrdTSRicalcoloQuantitaPrl9.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaPrl9.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaPrl9.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaPrl9.write(out); 
%>

			  <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaPrl10 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaPrl10"); 
  RilevDatiPrdTSRicalcoloQuantitaPrl10.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaPrl10.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaPrl10.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaPrl10.write(out); 
%>

			  <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaPrl11 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaPrl11"); 
  RilevDatiPrdTSRicalcoloQuantitaPrl11.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaPrl11.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaPrl11.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaPrl11.write(out); 
%>

			  <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaPrl12 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaPrl12"); 
  RilevDatiPrdTSRicalcoloQuantitaPrl12.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaPrl12.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaPrl12.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaPrl12.write(out); 
%>

			  <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaPrl13 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaPrl13"); 
  RilevDatiPrdTSRicalcoloQuantitaPrl13.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaPrl13.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaPrl13.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaPrl13.write(out); 
%>

			  <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaPrl14 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaPrl14"); 
  RilevDatiPrdTSRicalcoloQuantitaPrl14.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaPrl14.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaPrl14.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaPrl14.write(out); 
%>

			  <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaPrl15 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaPrl15"); 
  RilevDatiPrdTSRicalcoloQuantitaPrl15.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaPrl15.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaPrl15.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaPrl15.write(out); 
%>

			  <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaPrl16 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaPrl16"); 
  RilevDatiPrdTSRicalcoloQuantitaPrl16.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaPrl16.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaPrl16.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaPrl16.write(out); 
%>

			  <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaPrl17 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaPrl17"); 
  RilevDatiPrdTSRicalcoloQuantitaPrl17.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaPrl17.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaPrl17.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaPrl17.write(out); 
%>

			  <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaPrl18 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaPrl18"); 
  RilevDatiPrdTSRicalcoloQuantitaPrl18.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaPrl18.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaPrl18.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaPrl18.write(out); 
%>

			  <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaPrl19 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaPrl19"); 
  RilevDatiPrdTSRicalcoloQuantitaPrl19.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaPrl19.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaPrl19.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaPrl19.write(out); 
%>

			  <% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaPrl20 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaPrl20"); 
  RilevDatiPrdTSRicalcoloQuantitaPrl20.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaPrl20.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaPrl20.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaPrl20.write(out); 
%>

		  </td>
          <!-- Fix 39680 Fine -->
          
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td width="15px"></td>
    <td height="100%">
      <div style="height:100%;overflow:auto;">
      <table cellpadding="1" cellspacing="1">
        <tr>
          <td nowrap><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "RilevDatiPrdTS", "IdProdotto1", null); 
   label.setParent(RilevDatiPrdTSForm); 
%><label class="<%=label.getClassType()%>" for="Prodotto1"><%label.write(out);%></label><%}%></td>
          <td id="LabelNumeroOrdine" nowrap style="display:none"><label class="labelro" for="IdNumeroOrdine1" style="margin: 0 50 0 0;">NumOrdine</label></td> <!-- Fix 14722 -->
          <td nowrap><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "RilevDatiPrdTS", "QtaVrsUMPrm1", null); 
   label.setParent(RilevDatiPrdTSForm); 
%><label class="<%=label.getClassType()%>" for="QtaVrsUMPrm1"><%label.write(out);%></label><%}%></td>
          <td nowrap><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "RilevDatiPrdTS", "QtaScartoUMPrm1", null); 
   label.setParent(RilevDatiPrdTSForm); 
%><label class="<%=label.getClassType()%>" for="QtaScartoUMPrm1"><%label.write(out);%></label><%}%></td>
          <td nowrap><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "RilevDatiPrdTS", "IdUMPrmPrd1", null); 
   label.setParent(RilevDatiPrdTSForm); 
%><label class="<%=label.getClassType()%>" for="IdUMPrmPrd1"><%label.write(out);%></label><%}%></td>
          <td nowrap><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "RilevDatiPrdTS", "IdProdottoLotto1", null); 
   label.setParent(RilevDatiPrdTSForm); 
%><label class="<%=label.getClassType()%>" for="LottoProdotto1"><%label.write(out);%></label><%}%></td>
        </tr>
        <!-- Fix 14722 blocco commentato -->
      <!--/table>     </td>   </tr>   <tr>     <td width="15px"></td>     <td>       <table cellpadding="3" cellspacing="3"-->
        <tr id="TR1_1">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdotto1 =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("RilevDatiPrdTS", "Prodotto1", false, false, false, 1, "25", null); 
  RilevDatiPrdTSProdotto1.setExtraRelatedClassAD("DescrizioneArticolo.Descrizione,UMPrmMag,UMSecMag"); 
  RilevDatiPrdTSProdotto1.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdotto1.setOnKeyBlur("onKeyBlurArticolo(1)"); 
  RilevDatiPrdTSProdotto1.setOnKeyChange("resetDatiProdotto(1)"); 
  RilevDatiPrdTSProdotto1.setOnSearchBack("recuperaDatiProdotto()"); 
  RilevDatiPrdTSProdotto1.setAdditionalRestrictConditions("IdAnnoOrdine,IdClasseMerclg;IdNumeroOrdine,IdClasseMerclg;IdRigaAttivita,IdClasseMerclg;   TipoBolla,TipoArticolo;IdAnnoBolla,IdClasseMerclg;IdNumeroBolla,IdClasseMerclg"); 
  RilevDatiPrdTSProdotto1.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdotto1.write(out); 
%>
<!--<span class="articolomultisearchform" id="Prodotto1"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoVersione1 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoVersione1", false, false, false, 1, "1", null); 
  RilevDatiPrdTSProdottoVersione1.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoVersione1.setOnSearchBack("verificaDatiArticoli(1)"); 
  RilevDatiPrdTSProdottoVersione1.setAdditionalRestrictConditions("IdAnnoOrdine,IdDisegnoTecnico;IdNumeroOrdine,IdDisegnoTecnico;IdRigaAttivita,IdDisegnoTecnico;   TipoBolla,StatoTecnico;IdAnnoBolla,IdDisegnoTecnico;IdNumeroBolla,IdDisegnoTecnico"); 
  RilevDatiPrdTSProdottoVersione1.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoVersione1.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoVersione1"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoConfig1 =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("RilevDatiPrdTS", "ProdottoConfig1", false, false, false, 1, "15", null); 
  RilevDatiPrdTSProdottoConfig1.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  RilevDatiPrdTSProdottoConfig1.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoConfig1.setAdditionalRestrictConditions("IdAnnoOrdine,SintesiConfig;IdNumeroOrdine,SintesiConfig;IdRigaAttivita,SintesiConfig;   TipoBolla,TipoConfig;IdAnnoBolla,SintesiConfig;IdNumeroBolla,SintesiConfig"); 
  RilevDatiPrdTSProdottoConfig1.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoConfig1.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ProdottoConfig1"></span>--></td>
            <!-- Fix 14722 Inizio-->
            <!--td nowrap="true" id="TDNumeroOrdine1" style="display:none">               <input name="IdAnnoOrdine1" class="inputro" tabindex="-1" size="4"/>               <input name="IdNumeroOrdine1" class="inputro" tabindex="-1" size="9"/>               <input name="IdRigaAttivita1" type="Hidden"/>             </td-->
            <!-- Fix 14722 Fine-->
            <!-- Fix 15030 Inizio modifica size-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMPrm1 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMPrm1"); 
  RilevDatiPrdTSQtaVrsUMPrm1.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMPrm1.setOnChange("recuperaQtaSec(1);"); 
  RilevDatiPrdTSQtaVrsUMPrm1.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMPrm1.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMPrm1.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMPrm1.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMPrm1.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMPrm1.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMPrm1 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMPrm1"); 
  RilevDatiPrdTSQtaScartoUMPrm1.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMPrm1.setOnChange("recuperaQtaScartoSec(1);"); 
  RilevDatiPrdTSQtaScartoUMPrm1.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMPrm1.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMPrm1.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMPrm1.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMPrm1.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMPrm1.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMPrmPrd1 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMPrmPrd1"); 
  RilevDatiPrdTSIdUMPrmPrd1.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMPrmPrd1.getClassType()%>" id="<%=RilevDatiPrdTSIdUMPrmPrd1.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMPrmPrd1.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMPrmPrd1.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMPrmPrd1.write(out); 
%>
</td>
            <!-- Fix 15030 Fine-->
            <td><% 
  WebMultiSearchForm RilevDatiPrdTSLottoProdotto1 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "LottoProdotto1", false, false, false, 1, "12", null); 
  RilevDatiPrdTSLottoProdotto1.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSLottoProdotto1.setAdditionalRestrictConditions("IdAnnoOrdine,DescrizioneLotto;IdNumeroOrdine,DescrizioneLotto;   IdRigaAttivita,DescrizioneLotto;IdAnnoOrdine1,DescrizioneLotto;IdNumeroOrdine1,DescrizioneLotto;IdRigaAttivita1,DescrizioneLotto;   IdVersionePrd1,DescrizioneLotto;IdEsternoConfigPrd1,DescrizioneLotto;"); 
  RilevDatiPrdTSLottoProdotto1.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.LottiVersamentiDoList"); 
  RilevDatiPrdTSLottoProdotto1.write(out); 
%>
<!--<span class="multisearchform" id="LottoProdotto1"></span>--></td>
        </tr>
        <tr id="TR1_2">
          <!-- Fix 15030 Inizio-->
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdottoOrdine1 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoOrdine1", false, false, false, 2, null, null); 
  RilevDatiPrdTSProdottoOrdine1.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoOrdine1.setOnSearchBack("fillVrsCfg()"); 
  RilevDatiPrdTSProdottoOrdine1.setAdditionalRestrictConditions("IdNumeroBolla,NumeroOrdFmt;IdAnnoBolla,NumeroOrdFmt;IdProdotto1,IdArticolo"); 
  RilevDatiPrdTSProdottoOrdine1.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.OrdiniEsecutiveDoList"); 
  RilevDatiPrdTSProdottoOrdine1.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoOrdine1"></span>-->
          <% 
  WebTextInput RilevDatiPrdTSDescrizioneProdotto1 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneProdotto1"); 
  RilevDatiPrdTSDescrizioneProdotto1.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneProdotto1.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneProdotto1.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneProdotto1.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneProdotto1.getName()%>" readonly size="29" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneProdotto1.write(out); 
%>
 <!-- Fix 15030 modifica size da 46-->
          <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita1 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita1"); 
  RilevDatiPrdTSIdRigaAttivita1.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita1.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita1.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita1.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita1.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita1.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita1.write(out); 
%>

          <!-- Fix 15030 Fine-->
          </td>
          <td id="TDDisposizione1" style="display:none"></td> <!-- Fix 14722 -->
          <!-- Fix 15030 Inizio modifica size-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMSec1 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMSec1"); 
  RilevDatiPrdTSQtaVrsUMSec1.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMSec1.setOnChange("recuperaQtaPrm(1);recuperaQtaArrotondateSec(1);"); 
  RilevDatiPrdTSQtaVrsUMSec1.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMSec1.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMSec1.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMSec1.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMSec1.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMSec1.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMSec1 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMSec1"); 
  RilevDatiPrdTSQtaScartoUMSec1.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMSec1.setOnChange("recuperaQtaScartoPrm(1);recuperaQtaScartoArrotondateSec(1);"); 
  RilevDatiPrdTSQtaScartoUMSec1.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMSec1.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMSec1.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMSec1.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMSec1.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMSec1.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMSecPrd1 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMSecPrd1"); 
  RilevDatiPrdTSIdUMSecPrd1.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMSecPrd1.getClassType()%>" id="<%=RilevDatiPrdTSIdUMSecPrd1.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMSecPrd1.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMSecPrd1.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMSecPrd1.write(out); 
%>
</td>
          <td id="RicalcoloQuantitaVers1TD" nowrap style="display:none;"><% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaVers1 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaVers1"); 
  RilevDatiPrdTSRicalcoloQuantitaVers1.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaVers1.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaVers1.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaVers1.write(out); 
%>
</td> <!-- Fix 39680 -->
          <!-- Fix 15030 Fine-->
          <td align="right"><button class="littleButton" id="CreaLottoBut1" name="CreaLottoBut1" onclick="creaLotto(1)" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButCreaLotto")%></button></td>
        </tr>
        <tr style="display:none">
        <td><% 
  WebMultiSearchForm RilevDatiPrdTSMagazzinoVrs1 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "MagazzinoVrs1", false, false, false, 1, null, null); 
  RilevDatiPrdTSMagazzinoVrs1.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSMagazzinoVrs1.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoVrs1"></span>--></td>
        </tr>
        <tr id="TR1_3" style="display:none">
          <td style="height:10px">
          </td>
        </tr>
        <tr id="TR2_1" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdotto2 =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("RilevDatiPrdTS", "Prodotto2", false, false, false, 1, "25", null); 
  RilevDatiPrdTSProdotto2.setExtraRelatedClassAD("DescrizioneArticolo.Descrizione,UMPrmMag,UMSecMag"); 
  RilevDatiPrdTSProdotto2.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdotto2.setOnKeyBlur("onKeyBlurArticolo(2)"); 
  RilevDatiPrdTSProdotto2.setOnKeyChange("resetDatiProdotto(2)"); 
  RilevDatiPrdTSProdotto2.setOnSearchBack("recuperaDatiProdotto()"); 
  RilevDatiPrdTSProdotto2.setAdditionalRestrictConditions("IdAnnoOrdine,IdClasseMerclg;IdNumeroOrdine,IdClasseMerclg;IdRigaAttivita,IdClasseMerclg;   TipoBolla,TipoArticolo;IdAnnoBolla,IdClasseMerclg;IdNumeroBolla,IdClasseMerclg"); 
  RilevDatiPrdTSProdotto2.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdotto2.write(out); 
%>
<!--<span class="articolomultisearchform" id="Prodotto2"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoVersione2 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoVersione2", false, false, false, 1, "1", null); 
  RilevDatiPrdTSProdottoVersione2.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoVersione2.setAdditionalRestrictConditions("IdAnnoOrdine,IdDisegnoTecnico;IdNumeroOrdine,IdDisegnoTecnico;IdRigaAttivita,IdDisegnoTecnico;   TipoBolla,StatoTecnico;IdAnnoBolla,IdDisegnoTecnico;IdNumeroBolla,IdDisegnoTecnico"); 
  RilevDatiPrdTSProdottoVersione2.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoVersione2.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoVersione2"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoConfig2 =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("RilevDatiPrdTS", "ProdottoConfig2", false, false, false, 1, "15", null); 
  RilevDatiPrdTSProdottoConfig2.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  RilevDatiPrdTSProdottoConfig2.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoConfig2.setAdditionalRestrictConditions("IdAnnoOrdine,SintesiConfig;IdNumeroOrdine,SintesiConfig;IdRigaAttivita,SintesiConfig;   TipoBolla,TipoConfig;IdAnnoBolla,SintesiConfig;IdNumeroBolla,SintesiConfig"); 
  RilevDatiPrdTSProdottoConfig2.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoConfig2.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ProdottoConfig2"></span>--></td>
            <!-- Fix 14722 Inizio-->
            <!-- Fix 15030 blocco commentato-->
            <!--td nowrap="true" id="TDNumeroOrdine2" style="display:none">               <input name="IdAnnoOrdine2" class="inputro" tabindex="-1" size="4"/>               <input name="IdNumeroOrdine2" class="inputro" tabindex="-1" size="9"/>               <input name="IdRigaAttivita2" type="Hidden"/>             </td-->
            <!-- Fix 14722 Fine-->
            <!-- Fix 15030 Inizio modifica size-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMPrm2 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMPrm2"); 
  RilevDatiPrdTSQtaVrsUMPrm2.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMPrm2.setOnChange("recuperaQtaSec(2);"); 
  RilevDatiPrdTSQtaVrsUMPrm2.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMPrm2.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMPrm2.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMPrm2.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMPrm2.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMPrm2.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMPrm2 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMPrm2"); 
  RilevDatiPrdTSQtaScartoUMPrm2.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMPrm2.setOnChange("recuperaQtaScartoSec(2);"); 
  RilevDatiPrdTSQtaScartoUMPrm2.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMPrm2.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMPrm2.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMPrm2.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMPrm2.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMPrm2.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMPrmPrd2 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMPrmPrd2"); 
  RilevDatiPrdTSIdUMPrmPrd2.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMPrmPrd2.getClassType()%>" id="<%=RilevDatiPrdTSIdUMPrmPrd2.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMPrmPrd2.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMPrmPrd2.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMPrmPrd2.write(out); 
%>
</td>
            <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSLottoProdotto2 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "LottoProdotto2", false, false, false, 1, "12", null); 
  RilevDatiPrdTSLottoProdotto2.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSLottoProdotto2.setAdditionalRestrictConditions("IdAnnoOrdine,DescrizioneLotto;IdNumeroOrdine,DescrizioneLotto;   IdRigaAttivita,DescrizioneLotto;IdAnnoOrdine2,DescrizioneLotto;IdNumeroOrdine2,DescrizioneLotto;IdRigaAttivita2,DescrizioneLotto;   IdVersionePrd2,DescrizioneLotto;IdEsternoConfigPrd2,DescrizioneLotto;"); 
  RilevDatiPrdTSLottoProdotto2.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.LottiVersamentiDoList"); 
  RilevDatiPrdTSLottoProdotto2.write(out); 
%>
<!--<span class="multisearchform" id="LottoProdotto2"></span>--></td>
            <!-- Fix 15030 Fine-->
        </tr>
        <tr id="TR2_2" style="display:none">
          <!-- Fix 15030 inizio-->
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdottoOrdine2 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoOrdine2", false, false, false, 2, null, null); 
  RilevDatiPrdTSProdottoOrdine2.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoOrdine2.setOnSearchBack("fillVrsCfg()"); 
  RilevDatiPrdTSProdottoOrdine2.setAdditionalRestrictConditions("IdNumeroBolla,NumeroOrdFmt;IdAnnoBolla,NumeroOrdFmt;IdProdotto2,IdArticolo"); 
  RilevDatiPrdTSProdottoOrdine2.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.OrdiniEsecutiveDoList"); 
  RilevDatiPrdTSProdottoOrdine2.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoOrdine2"></span>-->
          <% 
  WebTextInput RilevDatiPrdTSDescrizioneProdotto2 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneProdotto2"); 
  RilevDatiPrdTSDescrizioneProdotto2.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneProdotto2.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneProdotto2.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneProdotto2.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneProdotto2.getName()%>" readonly size="29" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneProdotto2.write(out); 
%>
 <!-- Fix 15030 modifica size da 46-->
          <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita2 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita2"); 
  RilevDatiPrdTSIdRigaAttivita2.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita2.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita2.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita2.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita2.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita2.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita2.write(out); 
%>

          <!-- Fix 15030 fine-->
          </td>
          <td id="TDDisposizione2" style="display:none"></td> <!-- Fix 14722 -->
          <!-- Fix 15030 Inizio modifica size-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMSec2 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMSec2"); 
  RilevDatiPrdTSQtaVrsUMSec2.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMSec2.setOnChange("recuperaQtaPrm(2);recuperaQtaArrotondateSec(2);"); 
  RilevDatiPrdTSQtaVrsUMSec2.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMSec2.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMSec2.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMSec2.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMSec2.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMSec2.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMSec2 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMSec2"); 
  RilevDatiPrdTSQtaScartoUMSec2.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMSec2.setOnChange("recuperaQtaScartoPrm(2);recuperaQtaScartoArrotondateSec(2);"); 
  RilevDatiPrdTSQtaScartoUMSec2.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMSec2.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMSec2.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMSec2.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMSec2.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMSec2.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMSecPrd2 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMSecPrd2"); 
  RilevDatiPrdTSIdUMSecPrd2.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMSecPrd2.getClassType()%>" id="<%=RilevDatiPrdTSIdUMSecPrd2.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMSecPrd2.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMSecPrd2.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMSecPrd2.write(out); 
%>
</td>
          <td id="RicalcoloQuantitaVers2TD" nowrap style="display:none;"><% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaVers2 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaVers2"); 
  RilevDatiPrdTSRicalcoloQuantitaVers2.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaVers2.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaVers2.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaVers2.write(out); 
%>
</td> <!-- Fix 39680 -->
          <!-- Fix 15030 Fine-->
          <td align="right"><button class="littleButton" id="CreaLottoBut2" name="CreaLottoBut2" onclick="creaLotto(2)" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButCreaLotto")%></button></td>
        </tr>
        <tr style="display:none">
        <td><% 
  WebMultiSearchForm RilevDatiPrdTSMagazzinoVrs2 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "MagazzinoVrs2", false, false, false, 1, null, null); 
  RilevDatiPrdTSMagazzinoVrs2.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSMagazzinoVrs2.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoVrs2"></span>--></td>
        </tr>
        <tr id="TR2_3" style="display:none">
          <td style="height:10px">
          </td>
        </tr>
        <tr id="TR3_1" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdotto3 =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("RilevDatiPrdTS", "Prodotto3", false, false, false, 1, "25", null); 
  RilevDatiPrdTSProdotto3.setExtraRelatedClassAD("DescrizioneArticolo.Descrizione,UMPrmMag,UMSecMag"); 
  RilevDatiPrdTSProdotto3.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdotto3.setOnKeyBlur("onKeyBlurArticolo(3)"); 
  RilevDatiPrdTSProdotto3.setOnKeyChange("resetDatiProdotto(3)"); 
  RilevDatiPrdTSProdotto3.setOnSearchBack("recuperaDatiProdotto()"); 
  RilevDatiPrdTSProdotto3.setAdditionalRestrictConditions("IdAnnoOrdine,IdClasseMerclg;IdNumeroOrdine,IdClasseMerclg;IdRigaAttivita,IdClasseMerclg;   TipoBolla,TipoArticolo;IdAnnoBolla,IdClasseMerclg;IdNumeroBolla,IdClasseMerclg"); 
  RilevDatiPrdTSProdotto3.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdotto3.write(out); 
%>
<!--<span class="articolomultisearchform" id="Prodotto3"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoVersione3 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoVersione3", false, false, false, 1, "1", null); 
  RilevDatiPrdTSProdottoVersione3.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoVersione3.setAdditionalRestrictConditions("IdAnnoOrdine,IdDisegnoTecnico;IdNumeroOrdine,IdDisegnoTecnico;IdRigaAttivita,IdDisegnoTecnico;   TipoBolla,StatoTecnico;IdAnnoBolla,IdDisegnoTecnico;IdNumeroBolla,IdDisegnoTecnico"); 
  RilevDatiPrdTSProdottoVersione3.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoVersione3.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoVersione3"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoConfig3 =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("RilevDatiPrdTS", "ProdottoConfig3", false, false, false, 1, "15", null); 
  RilevDatiPrdTSProdottoConfig3.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  RilevDatiPrdTSProdottoConfig3.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoConfig3.setAdditionalRestrictConditions("IdAnnoOrdine,SintesiConfig;IdNumeroOrdine,SintesiConfig;IdRigaAttivita,SintesiConfig;   TipoBolla,TipoConfig;IdAnnoBolla,SintesiConfig;IdNumeroBolla,SintesiConfig"); 
  RilevDatiPrdTSProdottoConfig3.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoConfig3.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ProdottoConfig3"></span>--></td>
            <!-- Fix 14722 Inizio-->
            <!-- Fix 15030 blocco commentato-->
            <!--td nowrap="true" id="TDNumeroOrdine3" style="display:none">               <input name="IdAnnoOrdine3" class="inputro" tabindex="-1" size="4"/>               <input name="IdNumeroOrdine3" class="inputro" tabindex="-1" size="9"/>               <input name="IdRigaAttivita3" type="Hidden"/>             </td-->
            <!-- Fix 14722 Fine-->
            <!-- Fix 15030 Inizio modifica size-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMPrm3 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMPrm3"); 
  RilevDatiPrdTSQtaVrsUMPrm3.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMPrm3.setOnChange("recuperaQtaSec(3);"); 
  RilevDatiPrdTSQtaVrsUMPrm3.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMPrm3.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMPrm3.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMPrm3.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMPrm3.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMPrm3.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMPrm3 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMPrm3"); 
  RilevDatiPrdTSQtaScartoUMPrm3.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMPrm3.setOnChange("recuperaQtaScartoSec(3);"); 
  RilevDatiPrdTSQtaScartoUMPrm3.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMPrm3.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMPrm3.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMPrm3.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMPrm3.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMPrm3.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMPrmPrd3 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMPrmPrd3"); 
  RilevDatiPrdTSIdUMPrmPrd3.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMPrmPrd3.getClassType()%>" id="<%=RilevDatiPrdTSIdUMPrmPrd3.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMPrmPrd3.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMPrmPrd3.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMPrmPrd3.write(out); 
%>
</td>
            <!-- Fix 15030 Fine-->
            <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSLottoProdotto3 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "LottoProdotto3", false, false, false, 1, "12", null); 
  RilevDatiPrdTSLottoProdotto3.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSLottoProdotto3.setAdditionalRestrictConditions("IdAnnoOrdine,DescrizioneLotto;IdNumeroOrdine,DescrizioneLotto;   IdRigaAttivita,DescrizioneLotto;IdAnnoOrdine3,DescrizioneLotto;IdNumeroOrdine3,DescrizioneLotto;IdRigaAttivita3,DescrizioneLotto;   IdVersionePrd3,DescrizioneLotto;IdEsternoConfigPrd3,DescrizioneLotto;"); 
  RilevDatiPrdTSLottoProdotto3.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.LottiVersamentiDoList"); 
  RilevDatiPrdTSLottoProdotto3.write(out); 
%>
<!--<span class="multisearchform" id="LottoProdotto3"></span>--></td>
        </tr>
        <tr id="TR3_2" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdottoOrdine3 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoOrdine3", false, false, false, 2, null, null); 
  RilevDatiPrdTSProdottoOrdine3.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoOrdine3.setOnSearchBack("fillVrsCfg()"); 
  RilevDatiPrdTSProdottoOrdine3.setAdditionalRestrictConditions("IdNumeroBolla,NumeroOrdFmt;IdAnnoBolla,NumeroOrdFmt;IdProdotto3,IdArticolo"); 
  RilevDatiPrdTSProdottoOrdine3.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.OrdiniEsecutiveDoList"); 
  RilevDatiPrdTSProdottoOrdine3.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoOrdine3"></span>-->
          <% 
  WebTextInput RilevDatiPrdTSDescrizioneProdotto3 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneProdotto3"); 
  RilevDatiPrdTSDescrizioneProdotto3.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneProdotto3.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneProdotto3.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneProdotto3.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneProdotto3.getName()%>" readonly size="29" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneProdotto3.write(out); 
%>
 <!-- Fix 15030 modifica size da 46-->
          <!-- Fix 15030 inizio-->
          <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita3 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita3"); 
  RilevDatiPrdTSIdRigaAttivita3.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita3.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita3.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita3.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita3.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita3.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita3.write(out); 
%>

          <!-- Fix 15030 fine-->
          </td>
          <td id="TDDisposizione3" style="display:none"></td> <!-- Fix 14722 -->
          <!-- Fix 15030 Inizio modifica size-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMSec3 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMSec3"); 
  RilevDatiPrdTSQtaVrsUMSec3.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMSec3.setOnChange("recuperaQtaPrm(3);recuperaQtaArrotondateSec(3);"); 
  RilevDatiPrdTSQtaVrsUMSec3.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMSec3.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMSec3.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMSec3.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMSec3.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMSec3.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMSec3 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMSec3"); 
  RilevDatiPrdTSQtaScartoUMSec3.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMSec3.setOnChange("recuperaQtaScartoPrm(3);recuperaQtaScartoArrotondateSec(3);"); 
  RilevDatiPrdTSQtaScartoUMSec3.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMSec3.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMSec3.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMSec3.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMSec3.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMSec3.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMSecPrd3 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMSecPrd3"); 
  RilevDatiPrdTSIdUMSecPrd3.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMSecPrd3.getClassType()%>" id="<%=RilevDatiPrdTSIdUMSecPrd3.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMSecPrd3.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMSecPrd3.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMSecPrd3.write(out); 
%>
</td>
          <td id="RicalcoloQuantitaVers3TD" nowrap style="display:none;"><% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaVers3 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaVers3"); 
  RilevDatiPrdTSRicalcoloQuantitaVers3.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaVers3.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaVers3.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaVers3.write(out); 
%>
</td> <!-- Fix 39680 -->
          <!-- Fix 15030 Fine-->
          <td align="right"><button class="littleButton" id="CreaLottoBut3" name="CreaLottoBut3" onclick="creaLotto(3)" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButCreaLotto")%></button></td>
        </tr>
        <tr style="display:none">
        <td><% 
  WebMultiSearchForm RilevDatiPrdTSMagazzinoVrs3 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "MagazzinoVrs3", false, false, false, 1, null, null); 
  RilevDatiPrdTSMagazzinoVrs3.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSMagazzinoVrs3.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoVrs3"></span>--></td>
        </tr>
        <tr id="TR3_3" style="display:none">
          <td style="height:10px">
          </td>
        </tr>
        <tr id="TR4_1" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdotto4 =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("RilevDatiPrdTS", "Prodotto4", false, false, false, 1, "25", null); 
  RilevDatiPrdTSProdotto4.setExtraRelatedClassAD("DescrizioneArticolo.Descrizione,UMPrmMag,UMSecMag"); 
  RilevDatiPrdTSProdotto4.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdotto4.setOnKeyBlur("onKeyBlurArticolo(4)"); 
  RilevDatiPrdTSProdotto4.setOnKeyChange("resetDatiProdotto(4)"); 
  RilevDatiPrdTSProdotto4.setOnSearchBack("recuperaDatiProdotto()"); 
  RilevDatiPrdTSProdotto4.setAdditionalRestrictConditions("IdAnnoOrdine,IdClasseMerclg;IdNumeroOrdine,IdClasseMerclg;IdRigaAttivita,IdClasseMerclg;   TipoBolla,TipoArticolo;IdAnnoBolla,IdClasseMerclg;IdNumeroBolla,IdClasseMerclg"); 
  RilevDatiPrdTSProdotto4.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdotto4.write(out); 
%>
<!--<span class="articolomultisearchform" id="Prodotto4"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoVersione4 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoVersione4", false, false, false, 1, "1", null); 
  RilevDatiPrdTSProdottoVersione4.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoVersione4.setAdditionalRestrictConditions("IdAnnoOrdine,IdDisegnoTecnico;IdNumeroOrdine,IdDisegnoTecnico;IdRigaAttivita,IdDisegnoTecnico;   TipoBolla,StatoTecnico;IdAnnoBolla,IdDisegnoTecnico;IdNumeroBolla,IdDisegnoTecnico"); 
  RilevDatiPrdTSProdottoVersione4.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoVersione4.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoVersione4"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoConfig4 =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("RilevDatiPrdTS", "ProdottoConfig4", false, false, false, 1, "15", null); 
  RilevDatiPrdTSProdottoConfig4.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  RilevDatiPrdTSProdottoConfig4.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoConfig4.setAdditionalRestrictConditions("IdAnnoOrdine,SintesiConfig;IdNumeroOrdine,SintesiConfig;IdRigaAttivita,SintesiConfig;   TipoBolla,TipoConfig;IdAnnoBolla,SintesiConfig;IdNumeroBolla,SintesiConfig"); 
  RilevDatiPrdTSProdottoConfig4.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoConfig4.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ProdottoConfig4"></span>--></td>
            <!-- Fix 14722 Inizio-->
            <!-- Fix 15030 blocco commentato-->
            <!--td nowrap="true" id="TDNumeroOrdine4" style="display:none">               <input name="IdAnnoOrdine4" class="inputro" tabindex="-1" size="4"/>               <input name="IdNumeroOrdine4" class="inputro" tabindex="-1" size="9"/>               <input name="IdRigaAttivita4" type="Hidden"/>             </td-->
            <!-- Fix 14722 Fine-->
            <!-- Fix 15030 Inizio modifica size-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMPrm4 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMPrm4"); 
  RilevDatiPrdTSQtaVrsUMPrm4.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMPrm4.setOnChange("recuperaQtaSec(4);"); 
  RilevDatiPrdTSQtaVrsUMPrm4.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMPrm4.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMPrm4.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMPrm4.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMPrm4.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMPrm4.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMPrm4 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMPrm4"); 
  RilevDatiPrdTSQtaScartoUMPrm4.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMPrm4.setOnChange("recuperaQtaScartoSec(4);"); 
  RilevDatiPrdTSQtaScartoUMPrm4.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMPrm4.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMPrm4.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMPrm4.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMPrm4.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMPrm4.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMPrmPrd4 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMPrmPrd4"); 
  RilevDatiPrdTSIdUMPrmPrd4.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMPrmPrd4.getClassType()%>" id="<%=RilevDatiPrdTSIdUMPrmPrd4.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMPrmPrd4.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMPrmPrd4.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMPrmPrd4.write(out); 
%>
</td>
            <!-- Fix 15030 Fine-->
            <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSLottoProdotto4 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "LottoProdotto4", false, false, false, 1, "12", null); 
  RilevDatiPrdTSLottoProdotto4.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSLottoProdotto4.setAdditionalRestrictConditions("IdAnnoOrdine,DescrizioneLotto;IdNumeroOrdine,DescrizioneLotto;   IdRigaAttivita,DescrizioneLotto;IdAnnoOrdine4,DescrizioneLotto;IdNumeroOrdine4,DescrizioneLotto;IdRigaAttivita4,DescrizioneLotto;   IdVersionePrd4,DescrizioneLotto;IdEsternoConfigPrd4,DescrizioneLotto;"); 
  RilevDatiPrdTSLottoProdotto4.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.LottiVersamentiDoList"); 
  RilevDatiPrdTSLottoProdotto4.write(out); 
%>
<!--<span class="multisearchform" id="LottoProdotto4"></span>--></td>
        </tr>
        <tr id="TR4_2" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdottoOrdine4 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoOrdine4", false, false, false, 2, null, null); 
  RilevDatiPrdTSProdottoOrdine4.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoOrdine4.setOnSearchBack("fillVrsCfg()"); 
  RilevDatiPrdTSProdottoOrdine4.setAdditionalRestrictConditions("IdNumeroBolla,NumeroOrdFmt;IdAnnoBolla,NumeroOrdFmt;IdProdotto4,IdArticolo"); 
  RilevDatiPrdTSProdottoOrdine4.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.OrdiniEsecutiveDoList"); 
  RilevDatiPrdTSProdottoOrdine4.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoOrdine4"></span>-->
          <% 
  WebTextInput RilevDatiPrdTSDescrizioneProdotto4 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneProdotto4"); 
  RilevDatiPrdTSDescrizioneProdotto4.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneProdotto4.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneProdotto4.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneProdotto4.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneProdotto4.getName()%>" readonly size="29" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneProdotto4.write(out); 
%>
 <!-- Fix 15030 modifica size da 46-->
          <!-- Fix 15030 inizio-->
          <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita4 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita4"); 
  RilevDatiPrdTSIdRigaAttivita4.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita4.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita4.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita4.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita4.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita4.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita4.write(out); 
%>

          <!-- Fix 15030 fine-->
          </td>
          <td id="TDDisposizione4" style="display:none"></td> <!-- Fix 14722 -->
          <!-- Fix 15030 Inizio modifica size-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMSec4 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMSec4"); 
  RilevDatiPrdTSQtaVrsUMSec4.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMSec4.setOnChange("recuperaQtaPrm(4);recuperaQtaArrotondateSec(4);"); 
  RilevDatiPrdTSQtaVrsUMSec4.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMSec4.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMSec4.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMSec4.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMSec4.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMSec4.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMSec4 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMSec4"); 
  RilevDatiPrdTSQtaScartoUMSec4.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMSec4.setOnChange("recuperaQtaScartoPrm(4);recuperaQtaScartoArrotondateSec(4);"); 
  RilevDatiPrdTSQtaScartoUMSec4.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMSec4.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMSec4.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMSec4.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMSec4.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMSec4.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMSecPrd4 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMSecPrd4"); 
  RilevDatiPrdTSIdUMSecPrd4.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMSecPrd4.getClassType()%>" id="<%=RilevDatiPrdTSIdUMSecPrd4.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMSecPrd4.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMSecPrd4.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMSecPrd4.write(out); 
%>
</td>
          <td id="RicalcoloQuantitaVers4TD" nowrap style="display:none;"><% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaVers4 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaVers4"); 
  RilevDatiPrdTSRicalcoloQuantitaVers4.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaVers4.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaVers4.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaVers4.write(out); 
%>
</td> <!-- Fix 39680 -->
          <!-- Fix 15030 Fine-->
          <td align="right"><button class="littleButton" id="CreaLottoBut4" name="CreaLottoBut4" onclick="creaLotto(4)" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButCreaLotto")%></button></td>
        </tr>
        <tr style="display:none">
        <td><% 
  WebMultiSearchForm RilevDatiPrdTSMagazzinoVrs4 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "MagazzinoVrs4", false, false, false, 1, null, null); 
  RilevDatiPrdTSMagazzinoVrs4.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSMagazzinoVrs4.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoVrs4"></span>--></td>
        </tr>
        <tr id="TR4_3" style="display:none">
          <td style="height:10px">
          </td>
        </tr>
        <tr id="TR5_1" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdotto5 =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("RilevDatiPrdTS", "Prodotto5", false, false, false, 1, "25", null); 
  RilevDatiPrdTSProdotto5.setExtraRelatedClassAD("DescrizioneArticolo.Descrizione,UMPrmMag,UMSecMag"); 
  RilevDatiPrdTSProdotto5.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdotto5.setOnKeyBlur("onKeyBlurArticolo(5)"); 
  RilevDatiPrdTSProdotto5.setOnKeyChange("resetDatiProdotto(5)"); 
  RilevDatiPrdTSProdotto5.setOnSearchBack("recuperaDatiProdotto()"); 
  RilevDatiPrdTSProdotto5.setAdditionalRestrictConditions("IdAnnoOrdine,IdClasseMerclg;IdNumeroOrdine,IdClasseMerclg;IdRigaAttivita,IdClasseMerclg;   TipoBolla,TipoArticolo;IdAnnoBolla,IdClasseMerclg;IdNumeroBolla,IdClasseMerclg"); 
  RilevDatiPrdTSProdotto5.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdotto5.write(out); 
%>
<!--<span class="articolomultisearchform" id="Prodotto5"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoVersione5 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoVersione5", false, false, false, 1, "1", null); 
  RilevDatiPrdTSProdottoVersione5.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoVersione5.setAdditionalRestrictConditions("IdAnnoOrdine,IdDisegnoTecnico;IdNumeroOrdine,IdDisegnoTecnico;IdRigaAttivita,IdDisegnoTecnico;   TipoBolla,StatoTecnico;IdAnnoBolla,IdDisegnoTecnico;IdNumeroBolla,IdDisegnoTecnico"); 
  RilevDatiPrdTSProdottoVersione5.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoVersione5.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoVersione5"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoConfig5 =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("RilevDatiPrdTS", "ProdottoConfig5", false, false, false, 1, "15", null); 
  RilevDatiPrdTSProdottoConfig5.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  RilevDatiPrdTSProdottoConfig5.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoConfig5.setAdditionalRestrictConditions("IdAnnoOrdine,SintesiConfig;IdNumeroOrdine,SintesiConfig;IdRigaAttivita,SintesiConfig;   TipoBolla,TipoConfig;IdAnnoBolla,SintesiConfig;IdNumeroBolla,SintesiConfig"); 
  RilevDatiPrdTSProdottoConfig5.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoConfig5.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ProdottoConfig5"></span>--></td>
            <!-- Fix 14722 Inizio-->
            <!-- Fix 15030 blocco commentato-->
            <!--td nowrap="true" id="TDNumeroOrdine5" style="display:none">               <input name="IdAnnoOrdine5" class="inputro" tabindex="-1" size="4"/>               <input name="IdNumeroOrdine5" class="inputro" tabindex="-1" size="9"/>               <input name="IdRigaAttivita5" type="Hidden"/>             </td-->
            <!-- Fix 14722 Fine-->
            <!-- Fix 15030 Inizio modifica size-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMPrm5 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMPrm5"); 
  RilevDatiPrdTSQtaVrsUMPrm5.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMPrm5.setOnChange("recuperaQtaSec(5);"); 
  RilevDatiPrdTSQtaVrsUMPrm5.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMPrm5.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMPrm5.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMPrm5.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMPrm5.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMPrm5.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMPrm5 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMPrm5"); 
  RilevDatiPrdTSQtaScartoUMPrm5.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMPrm5.setOnChange("recuperaQtaScartoSec(5);"); 
  RilevDatiPrdTSQtaScartoUMPrm5.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMPrm5.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMPrm5.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMPrm5.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMPrm5.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMPrm5.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMPrmPrd5 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMPrmPrd5"); 
  RilevDatiPrdTSIdUMPrmPrd5.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMPrmPrd5.getClassType()%>" id="<%=RilevDatiPrdTSIdUMPrmPrd5.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMPrmPrd5.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMPrmPrd5.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMPrmPrd5.write(out); 
%>
</td>
            <!-- Fix 15030 Fine-->
            <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSLottoProdotto5 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "LottoProdotto5", false, false, false, 1, "12", null); 
  RilevDatiPrdTSLottoProdotto5.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSLottoProdotto5.setAdditionalRestrictConditions("IdAnnoOrdine,DescrizioneLotto;IdNumeroOrdine,DescrizioneLotto;   IdRigaAttivita,DescrizioneLotto;IdAnnoOrdine5,DescrizioneLotto;IdNumeroOrdine5,DescrizioneLotto;IdRigaAttivita5,DescrizioneLotto;   IdVersionePrd5,DescrizioneLotto;IdEsternoConfigPrd5,DescrizioneLotto;"); 
  RilevDatiPrdTSLottoProdotto5.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.LottiVersamentiDoList"); 
  RilevDatiPrdTSLottoProdotto5.write(out); 
%>
<!--<span class="multisearchform" id="LottoProdotto5"></span>--></td>
        </tr>
        <tr id="TR5_2" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdottoOrdine5 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoOrdine5", false, false, false, 2, null, null); 
  RilevDatiPrdTSProdottoOrdine5.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoOrdine5.setOnSearchBack("fillVrsCfg()"); 
  RilevDatiPrdTSProdottoOrdine5.setAdditionalRestrictConditions("IdNumeroBolla,NumeroOrdFmt;IdAnnoBolla,NumeroOrdFmt;IdProdotto5,IdArticolo"); 
  RilevDatiPrdTSProdottoOrdine5.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.OrdiniEsecutiveDoList"); 
  RilevDatiPrdTSProdottoOrdine5.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoOrdine5"></span>-->
          <% 
  WebTextInput RilevDatiPrdTSDescrizioneProdotto5 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneProdotto5"); 
  RilevDatiPrdTSDescrizioneProdotto5.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneProdotto5.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneProdotto5.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneProdotto5.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneProdotto5.getName()%>" readonly size="29" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneProdotto5.write(out); 
%>
 <!-- Fix 15030 modifica size da 46-->
          <!-- Fix 15030 inizio-->
          <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita5 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita5"); 
  RilevDatiPrdTSIdRigaAttivita5.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita5.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita5.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita5.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita5.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita5.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita5.write(out); 
%>

          <!-- Fix 15030 fine-->
          </td>
          <td id="TDDisposizione5" style="display:none"></td> <!-- Fix 14722 -->
          <!-- Fix 15030 Inizio modifica size-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMSec5 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMSec5"); 
  RilevDatiPrdTSQtaVrsUMSec5.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMSec5.setOnChange("recuperaQtaPrm(5);recuperaQtaArrotondateSec(5);"); 
  RilevDatiPrdTSQtaVrsUMSec5.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMSec5.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMSec5.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMSec5.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMSec5.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMSec5.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMSec5 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMSec5"); 
  RilevDatiPrdTSQtaScartoUMSec5.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMSec5.setOnChange("recuperaQtaScartoPrm(5);recuperaQtaScartoArrotondateSec(5);"); 
  RilevDatiPrdTSQtaScartoUMSec5.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMSec5.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMSec5.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMSec5.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMSec5.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMSec5.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMSecPrd5 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMSecPrd5"); 
  RilevDatiPrdTSIdUMSecPrd5.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMSecPrd5.getClassType()%>" id="<%=RilevDatiPrdTSIdUMSecPrd5.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMSecPrd5.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMSecPrd5.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMSecPrd5.write(out); 
%>
</td>
          <td id="RicalcoloQuantitaVers5TD" nowrap style="display:none;"><% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaVers5 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaVers5"); 
  RilevDatiPrdTSRicalcoloQuantitaVers5.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaVers5.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaVers5.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaVers5.write(out); 
%>
</td> <!-- Fix 39680 -->
          <!-- Fix 15030 Fine-->
          <td align="right"><button class="littleButton" id="CreaLottoBut5" name="CreaLottoBut5" onclick="creaLotto(5)" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButCreaLotto")%></button></td>
        </tr>
        <tr style="display:none">
          <td><% 
  WebMultiSearchForm RilevDatiPrdTSMagazzinoVrs5 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "MagazzinoVrs5", false, false, false, 1, null, null); 
  RilevDatiPrdTSMagazzinoVrs5.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSMagazzinoVrs5.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoVrs5"></span>--></td>
        </tr>
        <!--Fix 19000 AA Inizio-->
        <!--<tr id="TR2_3" style="display:none">-->
        <tr id="TR5_3" style="display:none">
        <!--Fix 19000 AA Fine-->
          <td style="height:10px">
          </td>
        </tr>
        <tr id="TR6_1" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdotto6 =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("RilevDatiPrdTS", "Prodotto6", false, false, false, 1, "25", null); 
  RilevDatiPrdTSProdotto6.setExtraRelatedClassAD("DescrizioneArticolo.Descrizione,UMPrmMag,UMSecMag"); 
  RilevDatiPrdTSProdotto6.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdotto6.setOnKeyBlur("onKeyBlurArticolo(6)"); 
  RilevDatiPrdTSProdotto6.setOnKeyChange("resetDatiProdotto(6)"); 
  RilevDatiPrdTSProdotto6.setOnSearchBack("recuperaDatiProdotto()"); 
  RilevDatiPrdTSProdotto6.setAdditionalRestrictConditions("IdAnnoOrdine,IdClasseMerclg;IdNumeroOrdine,IdClasseMerclg;IdRigaAttivita,IdClasseMerclg;   TipoBolla,TipoArticolo;IdAnnoBolla,IdClasseMerclg;IdNumeroBolla,IdClasseMerclg"); 
  RilevDatiPrdTSProdotto6.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdotto6.write(out); 
%>
<!--<span class="articolomultisearchform" id="Prodotto6"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoVersione6 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoVersione6", false, false, false, 1, "1", null); 
  RilevDatiPrdTSProdottoVersione6.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoVersione6.setAdditionalRestrictConditions("IdAnnoOrdine,IdDisegnoTecnico;IdNumeroOrdine,IdDisegnoTecnico;IdRigaAttivita,IdDisegnoTecnico;   TipoBolla,StatoTecnico;IdAnnoBolla,IdDisegnoTecnico;IdNumeroBolla,IdDisegnoTecnico"); 
  RilevDatiPrdTSProdottoVersione6.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoVersione6.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoVersione6"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoConfig6 =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("RilevDatiPrdTS", "ProdottoConfig6", false, false, false, 1, "15", null); 
  RilevDatiPrdTSProdottoConfig6.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  RilevDatiPrdTSProdottoConfig6.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoConfig6.setAdditionalRestrictConditions("IdAnnoOrdine,SintesiConfig;IdNumeroOrdine,SintesiConfig;IdRigaAttivita,SintesiConfig;   TipoBolla,TipoConfig;IdAnnoBolla,SintesiConfig;IdNumeroBolla,SintesiConfig"); 
  RilevDatiPrdTSProdottoConfig6.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoConfig6.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ProdottoConfig6"></span>--></td>
            <!-- Fix 14722 Inizio-->
            <!-- Fix 15030 blocco commentato-->
            <!--td nowrap="true" id="TDNumeroOrdine6" style="display:none">               <input name="IdAnnoOrdine6" class="inputro" tabindex="-1" size="4"/>               <input name="IdNumeroOrdine6" class="inputro" tabindex="-1" size="9"/>               <input name="IdRigaAttivita6" type="Hidden"/>             </td-->
            <!-- Fix 14722 Fine-->
            <!-- Fix 15030 Inizio modifica size-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMPrm6 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMPrm6"); 
  RilevDatiPrdTSQtaVrsUMPrm6.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMPrm6.setOnChange("recuperaQtaSec(6);"); 
  RilevDatiPrdTSQtaVrsUMPrm6.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMPrm6.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMPrm6.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMPrm6.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMPrm6.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMPrm6.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMPrm6 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMPrm6"); 
  RilevDatiPrdTSQtaScartoUMPrm6.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMPrm6.setOnChange("recuperaQtaScartoSec(6);"); 
  RilevDatiPrdTSQtaScartoUMPrm6.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMPrm6.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMPrm6.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMPrm6.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMPrm6.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMPrm6.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMPrmPrd6 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMPrmPrd6"); 
  RilevDatiPrdTSIdUMPrmPrd6.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMPrmPrd6.getClassType()%>" id="<%=RilevDatiPrdTSIdUMPrmPrd6.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMPrmPrd6.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMPrmPrd6.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMPrmPrd6.write(out); 
%>
</td>
            <!-- Fix 15030 Fine-->
            <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSLottoProdotto6 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "LottoProdotto6", false, false, false, 1, "12", null); 
  RilevDatiPrdTSLottoProdotto6.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSLottoProdotto6.setAdditionalRestrictConditions("IdAnnoOrdine,DescrizioneLotto;IdNumeroOrdine,DescrizioneLotto;   IdRigaAttivita,DescrizioneLotto;IdAnnoOrdine6,DescrizioneLotto;IdNumeroOrdine6,DescrizioneLotto;IdRigaAttivita6,DescrizioneLotto;   IdVersionePrd6,DescrizioneLotto;IdEsternoConfigPrd6,DescrizioneLotto;"); 
  RilevDatiPrdTSLottoProdotto6.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.LottiVersamentiDoList"); 
  RilevDatiPrdTSLottoProdotto6.write(out); 
%>
<!--<span class="multisearchform" id="LottoProdotto6"></span>--></td>
        </tr>
        <tr id="TR6_2" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdottoOrdine6 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoOrdine6", false, false, false, 2, null, null); 
  RilevDatiPrdTSProdottoOrdine6.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoOrdine6.setOnSearchBack("fillVrsCfg()"); 
  RilevDatiPrdTSProdottoOrdine6.setAdditionalRestrictConditions("IdNumeroBolla,NumeroOrdFmt;IdAnnoBolla,NumeroOrdFmt;IdProdotto6,IdArticolo"); 
  RilevDatiPrdTSProdottoOrdine6.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.OrdiniEsecutiveDoList"); 
  RilevDatiPrdTSProdottoOrdine6.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoOrdine6"></span>-->
          <% 
  WebTextInput RilevDatiPrdTSDescrizioneProdotto6 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneProdotto6"); 
  RilevDatiPrdTSDescrizioneProdotto6.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneProdotto6.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneProdotto6.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneProdotto6.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneProdotto6.getName()%>" readonly size="29" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneProdotto6.write(out); 
%>
 <!-- Fix 15030 modifica size da 46-->
          <!-- Fix 15030 inizio-->
          <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita6 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita6"); 
  RilevDatiPrdTSIdRigaAttivita6.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita6.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita6.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita6.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita6.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita6.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita6.write(out); 
%>

          <!-- Fix 15030 fine-->
          </td>
          <td id="TDDisposizione6" style="display:none"></td> <!-- Fix 14722 -->
          <!-- Fix 15030 Inizio modifica size-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMSec6 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMSec6"); 
  RilevDatiPrdTSQtaVrsUMSec6.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMSec6.setOnChange("recuperaQtaPrm(6);recuperaQtaArrotondateSec(6);"); 
  RilevDatiPrdTSQtaVrsUMSec6.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMSec6.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMSec6.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMSec6.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMSec6.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMSec6.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMSec6 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMSec6"); 
  RilevDatiPrdTSQtaScartoUMSec6.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMSec6.setOnChange("recuperaQtaScartoPrm(6);recuperaQtaScartoArrotondateSec(6);"); 
  RilevDatiPrdTSQtaScartoUMSec6.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMSec6.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMSec6.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMSec6.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMSec6.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMSec6.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMSecPrd6 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMSecPrd6"); 
  RilevDatiPrdTSIdUMSecPrd6.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMSecPrd6.getClassType()%>" id="<%=RilevDatiPrdTSIdUMSecPrd6.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMSecPrd6.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMSecPrd6.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMSecPrd6.write(out); 
%>
</td>
          <td id="RicalcoloQuantitaVers6TD" nowrap style="display:none;"><% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaVers6 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaVers6"); 
  RilevDatiPrdTSRicalcoloQuantitaVers6.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaVers6.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaVers6.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaVers6.write(out); 
%>
</td> <!-- Fix 39680 -->
          <!-- Fix 15030 Fine-->
          <td align="right"><button class="littleButton" id="CreaLottoBut6" name="CreaLottoBut6" onclick="creaLotto(6)" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButCreaLotto")%></button></td>
        </tr>
        <tr style="display:none">
          <td><% 
  WebMultiSearchForm RilevDatiPrdTSMagazzinoVrs6 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "MagazzinoVrs6", false, false, false, 1, null, null); 
  RilevDatiPrdTSMagazzinoVrs6.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSMagazzinoVrs6.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoVrs6"></span>--></td>
        </tr>
        <tr id="TR6_3" style="display:none">
          <td style="height:10px">
          </td>
        </tr>
        <tr id="TR7_1" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdotto7 =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("RilevDatiPrdTS", "Prodotto7", false, false, false, 1, "25", null); 
  RilevDatiPrdTSProdotto7.setExtraRelatedClassAD("DescrizioneArticolo.Descrizione,UMPrmMag,UMSecMag"); 
  RilevDatiPrdTSProdotto7.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdotto7.setOnKeyBlur("onKeyBlurArticolo(7)"); 
  RilevDatiPrdTSProdotto7.setOnKeyChange("resetDatiProdotto(7)"); 
  RilevDatiPrdTSProdotto7.setOnSearchBack("recuperaDatiProdotto()"); 
  RilevDatiPrdTSProdotto7.setAdditionalRestrictConditions("IdAnnoOrdine,IdClasseMerclg;IdNumeroOrdine,IdClasseMerclg;IdRigaAttivita,IdClasseMerclg;   TipoBolla,TipoArticolo;IdAnnoBolla,IdClasseMerclg;IdNumeroBolla,IdClasseMerclg"); 
  RilevDatiPrdTSProdotto7.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdotto7.write(out); 
%>
<!--<span class="articolomultisearchform" id="Prodotto7"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoVersione7 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoVersione7", false, false, false, 1, "1", null); 
  RilevDatiPrdTSProdottoVersione7.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoVersione7.setAdditionalRestrictConditions("IdAnnoOrdine,IdDisegnoTecnico;IdNumeroOrdine,IdDisegnoTecnico;IdRigaAttivita,IdDisegnoTecnico;   TipoBolla,StatoTecnico;IdAnnoBolla,IdDisegnoTecnico;IdNumeroBolla,IdDisegnoTecnico"); 
  RilevDatiPrdTSProdottoVersione7.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoVersione7.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoVersione7"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoConfig7 =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("RilevDatiPrdTS", "ProdottoConfig7", false, false, false, 1, "15", null); 
  RilevDatiPrdTSProdottoConfig7.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  RilevDatiPrdTSProdottoConfig7.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoConfig7.setAdditionalRestrictConditions("IdAnnoOrdine,SintesiConfig;IdNumeroOrdine,SintesiConfig;IdRigaAttivita,SintesiConfig;   TipoBolla,TipoConfig;IdAnnoBolla,SintesiConfig;IdNumeroBolla,SintesiConfig"); 
  RilevDatiPrdTSProdottoConfig7.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoConfig7.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ProdottoConfig7"></span>--></td>
            <!-- Fix 14722 Inizio-->
            <!-- Fix 15030 blocco commentato-->
            <!--td nowrap="true" id="TDNumeroOrdine7" style="display:none">               <input name="IdAnnoOrdine7" class="inputro" tabindex="-1" size="4"/>               <input name="IdNumeroOrdine7" class="inputro" tabindex="-1" size="9"/>               <input name="IdRigaAttivita7" type="Hidden"/>             </td-->
            <!-- Fix 14722 Fine-->
            <!-- Fix 15030 Inizio modifica size-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMPrm7 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMPrm7"); 
  RilevDatiPrdTSQtaVrsUMPrm7.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMPrm7.setOnChange("recuperaQtaSec(7);"); 
  RilevDatiPrdTSQtaVrsUMPrm7.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMPrm7.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMPrm7.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMPrm7.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMPrm7.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMPrm7.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMPrm7 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMPrm7"); 
  RilevDatiPrdTSQtaScartoUMPrm7.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMPrm7.setOnChange("recuperaQtaScartoSec(7);"); 
  RilevDatiPrdTSQtaScartoUMPrm7.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMPrm7.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMPrm7.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMPrm7.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMPrm7.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMPrm7.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMPrmPrd7 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMPrmPrd7"); 
  RilevDatiPrdTSIdUMPrmPrd7.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMPrmPrd7.getClassType()%>" id="<%=RilevDatiPrdTSIdUMPrmPrd7.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMPrmPrd7.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMPrmPrd7.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMPrmPrd7.write(out); 
%>
</td>
            <!-- Fix 15030 Fine-->
            <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSLottoProdotto7 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "LottoProdotto7", false, false, false, 1, "12", null); 
  RilevDatiPrdTSLottoProdotto7.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSLottoProdotto7.setAdditionalRestrictConditions("IdAnnoOrdine,DescrizioneLotto;IdNumeroOrdine,DescrizioneLotto;   IdRigaAttivita,DescrizioneLotto;IdAnnoOrdine7,DescrizioneLotto;IdNumeroOrdine7,DescrizioneLotto;IdRigaAttivita7,DescrizioneLotto;   IdVersionePrd7,DescrizioneLotto;IdEsternoConfigPrd7,DescrizioneLotto;"); 
  RilevDatiPrdTSLottoProdotto7.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.LottiVersamentiDoList"); 
  RilevDatiPrdTSLottoProdotto7.write(out); 
%>
<!--<span class="multisearchform" id="LottoProdotto7"></span>--></td>
        </tr>
        <tr id="TR7_2" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdottoOrdine7 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoOrdine7", false, false, false, 2, null, null); 
  RilevDatiPrdTSProdottoOrdine7.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoOrdine7.setOnSearchBack("fillVrsCfg()"); 
  RilevDatiPrdTSProdottoOrdine7.setAdditionalRestrictConditions("IdNumeroBolla,NumeroOrdFmt;IdAnnoBolla,NumeroOrdFmt;IdProdotto7,IdArticolo"); 
  RilevDatiPrdTSProdottoOrdine7.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.OrdiniEsecutiveDoList"); 
  RilevDatiPrdTSProdottoOrdine7.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoOrdine7"></span>-->
          <% 
  WebTextInput RilevDatiPrdTSDescrizioneProdotto7 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneProdotto7"); 
  RilevDatiPrdTSDescrizioneProdotto7.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneProdotto7.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneProdotto7.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneProdotto7.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneProdotto7.getName()%>" readonly size="29" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneProdotto7.write(out); 
%>
 <!-- Fix 15030 modifica size da 46-->
          <!-- Fix 15030 inizio-->
          <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita7 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita7"); 
  RilevDatiPrdTSIdRigaAttivita7.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita7.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita7.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita7.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita7.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita7.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita7.write(out); 
%>

          <!-- Fix 15030 fine-->
          </td>
          <td id="TDDisposizione7" style="display:none"></td> <!-- Fix 14722 -->
          <!-- Fix 15030 Inizio modifica size-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMSec7 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMSec7"); 
  RilevDatiPrdTSQtaVrsUMSec7.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMSec7.setOnChange("recuperaQtaPrm(7);recuperaQtaArrotondateSec(7);"); 
  RilevDatiPrdTSQtaVrsUMSec7.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMSec7.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMSec7.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMSec7.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMSec7.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMSec7.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMSec7 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMSec7"); 
  RilevDatiPrdTSQtaScartoUMSec7.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMSec7.setOnChange("recuperaQtaScartoPrm(7);recuperaQtaScartoArrotondateSec(7);"); 
  RilevDatiPrdTSQtaScartoUMSec7.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMSec7.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMSec7.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMSec7.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMSec7.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMSec7.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMSecPrd7 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMSecPrd7"); 
  RilevDatiPrdTSIdUMSecPrd7.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMSecPrd7.getClassType()%>" id="<%=RilevDatiPrdTSIdUMSecPrd7.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMSecPrd7.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMSecPrd7.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMSecPrd7.write(out); 
%>
</td>
          <td id="RicalcoloQuantitaVers7TD" nowrap style="display:none;"><% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaVers7 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaVers7"); 
  RilevDatiPrdTSRicalcoloQuantitaVers7.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaVers7.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaVers7.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaVers7.write(out); 
%>
</td> <!-- Fix 39680 -->
          <!-- Fix 15030 Fine-->
          <td align="right"><button class="littleButton" id="CreaLottoBut7" name="CreaLottoBut7" onclick="creaLotto(7)" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButCreaLotto")%></button></td>
        </tr>
        <tr style="display:none">
          <td><% 
  WebMultiSearchForm RilevDatiPrdTSMagazzinoVrs7 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "MagazzinoVrs7", false, false, false, 1, null, null); 
  RilevDatiPrdTSMagazzinoVrs7.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSMagazzinoVrs7.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoVrs7"></span>--></td>
        </tr>
        <tr id="TR7_3" style="display:none">
          <td style="height:10px">
          </td>
        </tr>
        <tr id="TR8_1" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdotto8 =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("RilevDatiPrdTS", "Prodotto8", false, false, false, 1, "25", null); 
  RilevDatiPrdTSProdotto8.setExtraRelatedClassAD("DescrizioneArticolo.Descrizione,UMPrmMag,UMSecMag"); 
  RilevDatiPrdTSProdotto8.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdotto8.setOnKeyBlur("onKeyBlurArticolo(8)"); 
  RilevDatiPrdTSProdotto8.setOnKeyChange("resetDatiProdotto(8)"); 
  RilevDatiPrdTSProdotto8.setOnSearchBack("recuperaDatiProdotto()"); 
  RilevDatiPrdTSProdotto8.setAdditionalRestrictConditions("IdAnnoOrdine,IdClasseMerclg;IdNumeroOrdine,IdClasseMerclg;IdRigaAttivita,IdClasseMerclg;   TipoBolla,TipoArticolo;IdAnnoBolla,IdClasseMerclg;IdNumeroBolla,IdClasseMerclg"); 
  RilevDatiPrdTSProdotto8.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdotto8.write(out); 
%>
<!--<span class="articolomultisearchform" id="Prodotto8"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoVersione8 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoVersione8", false, false, false, 1, "1", null); 
  RilevDatiPrdTSProdottoVersione8.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoVersione8.setAdditionalRestrictConditions("IdAnnoOrdine,IdDisegnoTecnico;IdNumeroOrdine,IdDisegnoTecnico;IdRigaAttivita,IdDisegnoTecnico;   TipoBolla,StatoTecnico;IdAnnoBolla,IdDisegnoTecnico;IdNumeroBolla,IdDisegnoTecnico"); 
  RilevDatiPrdTSProdottoVersione8.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoVersione8.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoVersione8"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoConfig8 =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("RilevDatiPrdTS", "ProdottoConfig8", false, false, false, 1, "15", null); 
  RilevDatiPrdTSProdottoConfig8.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  RilevDatiPrdTSProdottoConfig8.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoConfig8.setAdditionalRestrictConditions("IdAnnoOrdine,SintesiConfig;IdNumeroOrdine,SintesiConfig;IdRigaAttivita,SintesiConfig;   TipoBolla,TipoConfig;IdAnnoBolla,SintesiConfig;IdNumeroBolla,SintesiConfig"); 
  RilevDatiPrdTSProdottoConfig8.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoConfig8.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ProdottoConfig8"></span>--></td>
            <!-- Fix 14722 Inizio-->
            <!-- Fix 15030 blocco commentato-->
            <!--td nowrap="true" id="TDNumeroOrdine8" style="display:none">               <input name="IdAnnoOrdine8" class="inputro" tabindex="-1" size="4"/>               <input name="IdNumeroOrdine8" class="inputro" tabindex="-1" size="9"/>               <input name="IdRigaAttivita8" type="Hidden"/>             </td-->
            <!-- Fix 14722 Fine-->
            <!-- Fix 15030 Inizio modifica size-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMPrm8 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMPrm8"); 
  RilevDatiPrdTSQtaVrsUMPrm8.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMPrm8.setOnChange("recuperaQtaSec(8);"); 
  RilevDatiPrdTSQtaVrsUMPrm8.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMPrm8.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMPrm8.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMPrm8.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMPrm8.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMPrm8.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMPrm8 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMPrm8"); 
  RilevDatiPrdTSQtaScartoUMPrm8.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMPrm8.setOnChange("recuperaQtaScartoSec(8);"); 
  RilevDatiPrdTSQtaScartoUMPrm8.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMPrm8.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMPrm8.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMPrm8.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMPrm8.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMPrm8.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMPrmPrd8 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMPrmPrd8"); 
  RilevDatiPrdTSIdUMPrmPrd8.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMPrmPrd8.getClassType()%>" id="<%=RilevDatiPrdTSIdUMPrmPrd8.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMPrmPrd8.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMPrmPrd8.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMPrmPrd8.write(out); 
%>
</td>
            <!-- Fix 15030 Fine-->
            <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSLottoProdotto8 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "LottoProdotto8", false, false, false, 1, "12", null); 
  RilevDatiPrdTSLottoProdotto8.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSLottoProdotto8.setAdditionalRestrictConditions("IdAnnoOrdine,DescrizioneLotto;IdNumeroOrdine,DescrizioneLotto;   IdRigaAttivita,DescrizioneLotto;IdAnnoOrdine8,DescrizioneLotto;IdNumeroOrdine8,DescrizioneLotto;IdRigaAttivita8,DescrizioneLotto;   IdVersionePrd8,DescrizioneLotto;IdEsternoConfigPrd8,DescrizioneLotto;"); 
  RilevDatiPrdTSLottoProdotto8.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.LottiVersamentiDoList"); 
  RilevDatiPrdTSLottoProdotto8.write(out); 
%>
<!--<span class="multisearchform" id="LottoProdotto8"></span>--></td>
        </tr>
        <tr id="TR8_2" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdottoOrdine8 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoOrdine8", false, false, false, 2, null, null); 
  RilevDatiPrdTSProdottoOrdine8.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoOrdine8.setOnSearchBack("fillVrsCfg()"); 
  RilevDatiPrdTSProdottoOrdine8.setAdditionalRestrictConditions("IdNumeroBolla,NumeroOrdFmt;IdAnnoBolla,NumeroOrdFmt;IdProdotto8,IdArticolo"); 
  RilevDatiPrdTSProdottoOrdine8.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.OrdiniEsecutiveDoList"); 
  RilevDatiPrdTSProdottoOrdine8.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoOrdine8"></span>-->
          <% 
  WebTextInput RilevDatiPrdTSDescrizioneProdotto8 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneProdotto8"); 
  RilevDatiPrdTSDescrizioneProdotto8.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneProdotto8.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneProdotto8.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneProdotto8.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneProdotto8.getName()%>" readonly size="29" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneProdotto8.write(out); 
%>
 <!-- Fix 15030 modifica size da 46-->
          <!-- Fix 15030 inizio-->
          <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita8 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita8"); 
  RilevDatiPrdTSIdRigaAttivita8.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita8.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita8.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita8.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita8.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita8.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita8.write(out); 
%>

          <!-- Fix 15030 fine-->
          </td>
          <td id="TDDisposizione8" style="display:none"></td> <!-- Fix 14722 -->
          <!-- Fix 15030 Inizio modifica size-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMSec8 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMSec8"); 
  RilevDatiPrdTSQtaVrsUMSec8.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMSec8.setOnChange("recuperaQtaPrm(8);recuperaQtaArrotondateSec(8);"); 
  RilevDatiPrdTSQtaVrsUMSec8.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMSec8.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMSec8.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMSec8.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMSec8.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMSec8.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMSec8 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMSec8"); 
  RilevDatiPrdTSQtaScartoUMSec8.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMSec8.setOnChange("recuperaQtaScartoPrm(8);recuperaQtaScartoArrotondateSec(8);"); 
  RilevDatiPrdTSQtaScartoUMSec8.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMSec8.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMSec8.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMSec8.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMSec8.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMSec8.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMSecPrd8 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMSecPrd8"); 
  RilevDatiPrdTSIdUMSecPrd8.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMSecPrd8.getClassType()%>" id="<%=RilevDatiPrdTSIdUMSecPrd8.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMSecPrd8.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMSecPrd8.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMSecPrd8.write(out); 
%>
</td>
          <td id="RicalcoloQuantitaVers8TD" nowrap style="display:none;"><% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaVers8 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaVers8"); 
  RilevDatiPrdTSRicalcoloQuantitaVers8.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaVers8.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaVers8.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaVers8.write(out); 
%>
</td> <!-- Fix 39680 -->
          <!-- Fix 15030 Fine-->
          <td align="right"><button class="littleButton" id="CreaLottoBut8" name="CreaLottoBut8" onclick="creaLotto(8)" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButCreaLotto")%></button></td>
        </tr>
        <tr style="display:none">
          <td><% 
  WebMultiSearchForm RilevDatiPrdTSMagazzinoVrs8 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "MagazzinoVrs8", false, false, false, 1, null, null); 
  RilevDatiPrdTSMagazzinoVrs8.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSMagazzinoVrs8.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoVrs8"></span>--></td>
        </tr>
        <tr id="TR8_3" style="display:none">
          <td style="height:10px">
          </td>
        </tr>
        <tr id="TR9_1" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdotto9 =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("RilevDatiPrdTS", "Prodotto9", false, false, false, 1, "25", null); 
  RilevDatiPrdTSProdotto9.setExtraRelatedClassAD("DescrizioneArticolo.Descrizione,UMPrmMag,UMSecMag"); 
  RilevDatiPrdTSProdotto9.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdotto9.setOnKeyBlur("onKeyBlurArticolo(9)"); 
  RilevDatiPrdTSProdotto9.setOnKeyChange("resetDatiProdotto(9)"); 
  RilevDatiPrdTSProdotto9.setOnSearchBack("recuperaDatiProdotto()"); 
  RilevDatiPrdTSProdotto9.setAdditionalRestrictConditions("IdAnnoOrdine,IdClasseMerclg;IdNumeroOrdine,IdClasseMerclg;IdRigaAttivita,IdClasseMerclg;   TipoBolla,TipoArticolo;IdAnnoBolla,IdClasseMerclg;IdNumeroBolla,IdClasseMerclg"); 
  RilevDatiPrdTSProdotto9.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdotto9.write(out); 
%>
<!--<span class="articolomultisearchform" id="Prodotto9"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoVersione9 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoVersione9", false, false, false, 1, "1", null); 
  RilevDatiPrdTSProdottoVersione9.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoVersione9.setAdditionalRestrictConditions("IdAnnoOrdine,IdDisegnoTecnico;IdNumeroOrdine,IdDisegnoTecnico;IdRigaAttivita,IdDisegnoTecnico;   TipoBolla,StatoTecnico;IdAnnoBolla,IdDisegnoTecnico;IdNumeroBolla,IdDisegnoTecnico"); 
  RilevDatiPrdTSProdottoVersione9.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoVersione9.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoVersione9"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoConfig9 =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("RilevDatiPrdTS", "ProdottoConfig9", false, false, false, 1, "15", null); 
  RilevDatiPrdTSProdottoConfig9.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  RilevDatiPrdTSProdottoConfig9.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoConfig9.setAdditionalRestrictConditions("IdAnnoOrdine,SintesiConfig;IdNumeroOrdine,SintesiConfig;IdRigaAttivita,SintesiConfig;   TipoBolla,TipoConfig;IdAnnoBolla,SintesiConfig;IdNumeroBolla,SintesiConfig"); 
  RilevDatiPrdTSProdottoConfig9.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoConfig9.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ProdottoConfig9"></span>--></td>
            <!-- Fix 14722 Inizio-->
            <!-- Fix 15030 blocco commentato-->
            <!--td nowrap="true" id="TDNumeroOrdine9" style="display:none">               <input name="IdAnnoOrdine9" class="inputro" tabindex="-1" size="4"/>               <input name="IdNumeroOrdine9" class="inputro" tabindex="-1" size="9"/>               <input name="IdRigaAttivita9" type="Hidden"/>             </td-->
            <!-- Fix 14722 Fine-->
            <!-- Fix 15030 Inizio modifica size-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMPrm9 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMPrm9"); 
  RilevDatiPrdTSQtaVrsUMPrm9.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMPrm9.setOnChange("recuperaQtaSec(9);"); 
  RilevDatiPrdTSQtaVrsUMPrm9.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMPrm9.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMPrm9.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMPrm9.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMPrm9.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMPrm9.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMPrm9 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMPrm9"); 
  RilevDatiPrdTSQtaScartoUMPrm9.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMPrm9.setOnChange("recuperaQtaScartoSec(9);"); 
  RilevDatiPrdTSQtaScartoUMPrm9.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMPrm9.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMPrm9.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMPrm9.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMPrm9.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMPrm9.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMPrmPrd9 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMPrmPrd9"); 
  RilevDatiPrdTSIdUMPrmPrd9.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMPrmPrd9.getClassType()%>" id="<%=RilevDatiPrdTSIdUMPrmPrd9.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMPrmPrd9.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMPrmPrd9.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMPrmPrd9.write(out); 
%>
</td>
            <!-- Fix 15030 Fine-->
            <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSLottoProdotto9 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "LottoProdotto9", false, false, false, 1, "12", null); 
  RilevDatiPrdTSLottoProdotto9.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSLottoProdotto9.setAdditionalRestrictConditions("IdAnnoOrdine,DescrizioneLotto;IdNumeroOrdine,DescrizioneLotto;   IdRigaAttivita,DescrizioneLotto;IdAnnoOrdine9,DescrizioneLotto;IdNumeroOrdine9,DescrizioneLotto;IdRigaAttivita9,DescrizioneLotto;   IdVersionePrd9,DescrizioneLotto;IdEsternoConfigPrd9,DescrizioneLotto;"); 
  RilevDatiPrdTSLottoProdotto9.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.LottiVersamentiDoList"); 
  RilevDatiPrdTSLottoProdotto9.write(out); 
%>
<!--<span class="multisearchform" id="LottoProdotto9"></span>--></td>
        </tr>
        <tr id="TR9_2" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdottoOrdine9 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoOrdine9", false, false, false, 2, null, null); 
  RilevDatiPrdTSProdottoOrdine9.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoOrdine9.setOnSearchBack("fillVrsCfg()"); 
  RilevDatiPrdTSProdottoOrdine9.setAdditionalRestrictConditions("IdNumeroBolla,NumeroOrdFmt;IdAnnoBolla,NumeroOrdFmt;IdProdotto9,IdArticolo"); 
  RilevDatiPrdTSProdottoOrdine9.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.OrdiniEsecutiveDoList"); 
  RilevDatiPrdTSProdottoOrdine9.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoOrdine9"></span>-->
          <% 
  WebTextInput RilevDatiPrdTSDescrizioneProdotto9 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneProdotto9"); 
  RilevDatiPrdTSDescrizioneProdotto9.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneProdotto9.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneProdotto9.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneProdotto9.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneProdotto9.getName()%>" readonly size="29" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneProdotto9.write(out); 
%>
 <!-- Fix 15030 modifica size da 46-->
          <!-- Fix 15030 inizio-->
          <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita9 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita9"); 
  RilevDatiPrdTSIdRigaAttivita9.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita9.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita9.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita9.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita9.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita9.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita9.write(out); 
%>

          <!-- Fix 15030 fine-->
          </td>
          <td id="TDDisposizione9" style="display:none"></td> <!-- Fix 14722 -->
          <!-- Fix 15030 Inizio modifica size-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMSec9 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMSec9"); 
  RilevDatiPrdTSQtaVrsUMSec9.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMSec9.setOnChange("recuperaQtaPrm(9);recuperaQtaArrotondateSec(9);"); 
  RilevDatiPrdTSQtaVrsUMSec9.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMSec9.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMSec9.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMSec9.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMSec9.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMSec9.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMSec9 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMSec9"); 
  RilevDatiPrdTSQtaScartoUMSec9.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMSec9.setOnChange("recuperaQtaScartoPrm(9);recuperaQtaScartoArrotondateSec(9);"); 
  RilevDatiPrdTSQtaScartoUMSec9.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMSec9.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMSec9.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMSec9.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMSec9.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMSec9.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMSecPrd9 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMSecPrd9"); 
  RilevDatiPrdTSIdUMSecPrd9.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMSecPrd9.getClassType()%>" id="<%=RilevDatiPrdTSIdUMSecPrd9.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMSecPrd9.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMSecPrd9.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMSecPrd9.write(out); 
%>
</td>
          <td id="RicalcoloQuantitaVers9TD" nowrap style="display:none;"><% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaVers9 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaVers9"); 
  RilevDatiPrdTSRicalcoloQuantitaVers9.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaVers9.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaVers9.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaVers9.write(out); 
%>
</td> <!-- Fix 39680 -->
          <!-- Fix 15030 Fine-->
          <td align="right"><button class="littleButton" id="CreaLottoBut9" name="CreaLottoBut9" onclick="creaLotto(9)" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButCreaLotto")%></button></td>
        </tr>
        <tr style="display:none">
          <td><% 
  WebMultiSearchForm RilevDatiPrdTSMagazzinoVrs9 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "MagazzinoVrs9", false, false, false, 1, null, null); 
  RilevDatiPrdTSMagazzinoVrs9.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSMagazzinoVrs9.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoVrs9"></span>--></td>
        </tr>
        <tr id="TR9_3" style="display:none">
          <td style="height:10px">
          </td>
        </tr>
        <tr id="TR10_1" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdotto10 =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("RilevDatiPrdTS", "Prodotto10", false, false, false, 1, "25", null); 
  RilevDatiPrdTSProdotto10.setExtraRelatedClassAD("DescrizioneArticolo.Descrizione,UMPrmMag,UMSecMag"); 
  RilevDatiPrdTSProdotto10.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdotto10.setOnKeyBlur("onKeyBlurArticolo(10)"); 
  RilevDatiPrdTSProdotto10.setOnKeyChange("resetDatiProdotto(10)"); 
  RilevDatiPrdTSProdotto10.setOnSearchBack("recuperaDatiProdotto()"); 
  RilevDatiPrdTSProdotto10.setAdditionalRestrictConditions("IdAnnoOrdine,IdClasseMerclg;IdNumeroOrdine,IdClasseMerclg;IdRigaAttivita,IdClasseMerclg;   TipoBolla,TipoArticolo;IdAnnoBolla,IdClasseMerclg;IdNumeroBolla,IdClasseMerclg"); 
  RilevDatiPrdTSProdotto10.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdotto10.write(out); 
%>
<!--<span class="articolomultisearchform" id="Prodotto10"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoVersione10 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoVersione10", false, false, false, 1, "1", null); 
  RilevDatiPrdTSProdottoVersione10.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoVersione10.setAdditionalRestrictConditions("IdAnnoOrdine,IdDisegnoTecnico;IdNumeroOrdine,IdDisegnoTecnico;IdRigaAttivita,IdDisegnoTecnico;   TipoBolla,StatoTecnico;IdAnnoBolla,IdDisegnoTecnico;IdNumeroBolla,IdDisegnoTecnico"); 
  RilevDatiPrdTSProdottoVersione10.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoVersione10.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoVersione10"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoConfig10 =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("RilevDatiPrdTS", "ProdottoConfig10", false, false, false, 1, "15", null); 
  RilevDatiPrdTSProdottoConfig10.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  RilevDatiPrdTSProdottoConfig10.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoConfig10.setAdditionalRestrictConditions("IdAnnoOrdine,SintesiConfig;IdNumeroOrdine,SintesiConfig;IdRigaAttivita,SintesiConfig;   TipoBolla,TipoConfig;IdAnnoBolla,SintesiConfig;IdNumeroBolla,SintesiConfig"); 
  RilevDatiPrdTSProdottoConfig10.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoConfig10.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ProdottoConfig10"></span>--></td>
            <!-- Fix 14722 Inizio-->
            <!-- Fix 15030 blocco commentato-->
            <!--td nowrap="true" id="TDNumeroOrdine10" style="display:none">               <input name="IdAnnoOrdine10" class="inputro" tabindex="-1" size="4"/>               <input name="IdNumeroOrdine10" class="inputro" tabindex="-1" size="9"/>               <input name="IdRigaAttivita10" type="Hidden"/>             </td-->
            <!-- Fix 14722 Fine-->
            <!-- Fix 15030 Inizio modifica size-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMPrm10 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMPrm10"); 
  RilevDatiPrdTSQtaVrsUMPrm10.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMPrm10.setOnChange("recuperaQtaSec(10);"); 
  RilevDatiPrdTSQtaVrsUMPrm10.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMPrm10.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMPrm10.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMPrm10.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMPrm10.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMPrm10.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMPrm10 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMPrm10"); 
  RilevDatiPrdTSQtaScartoUMPrm10.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMPrm10.setOnChange("recuperaQtaScartoSec(10);"); 
  RilevDatiPrdTSQtaScartoUMPrm10.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMPrm10.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMPrm10.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMPrm10.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMPrm10.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMPrm10.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMPrmPrd10 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMPrmPrd10"); 
  RilevDatiPrdTSIdUMPrmPrd10.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMPrmPrd10.getClassType()%>" id="<%=RilevDatiPrdTSIdUMPrmPrd10.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMPrmPrd10.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMPrmPrd10.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMPrmPrd10.write(out); 
%>
</td>
            <!-- Fix 15030 Fine-->
            <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSLottoProdotto10 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "LottoProdotto10", false, false, false, 1, "12", null); 
  RilevDatiPrdTSLottoProdotto10.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSLottoProdotto10.setAdditionalRestrictConditions("IdAnnoOrdine,DescrizioneLotto;IdNumeroOrdine,DescrizioneLotto;   IdRigaAttivita,DescrizioneLotto;IdAnnoOrdine10,DescrizioneLotto;IdNumeroOrdine10,DescrizioneLotto;IdRigaAttivita10,DescrizioneLotto;   IdVersionePrd10,DescrizioneLotto;IdEsternoConfigPrd10,DescrizioneLotto;"); 
  RilevDatiPrdTSLottoProdotto10.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.LottiVersamentiDoList"); 
  RilevDatiPrdTSLottoProdotto10.write(out); 
%>
<!--<span class="multisearchform" id="LottoProdotto10"></span>--></td>
        </tr>
        <tr id="TR10_2" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdottoOrdine10 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoOrdine10", false, false, false, 2, null, null); 
  RilevDatiPrdTSProdottoOrdine10.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoOrdine10.setOnSearchBack("fillVrsCfg()"); 
  RilevDatiPrdTSProdottoOrdine10.setAdditionalRestrictConditions("IdNumeroBolla,NumeroOrdFmt;IdAnnoBolla,NumeroOrdFmt;IdProdotto10,IdArticolo"); 
  RilevDatiPrdTSProdottoOrdine10.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.OrdiniEsecutiveDoList"); 
  RilevDatiPrdTSProdottoOrdine10.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoOrdine10"></span>-->
          <% 
  WebTextInput RilevDatiPrdTSDescrizioneProdotto10 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneProdotto10"); 
  RilevDatiPrdTSDescrizioneProdotto10.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneProdotto10.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneProdotto10.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneProdotto10.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneProdotto10.getName()%>" readonly size="29" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneProdotto10.write(out); 
%>
 <!-- Fix 15030 modifica size da 46-->
          <!-- Fix 15030 inizio-->
          <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita10 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita10"); 
  RilevDatiPrdTSIdRigaAttivita10.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita10.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita10.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita10.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita10.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita10.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita10.write(out); 
%>

          <!-- Fix 15030 fine-->
          </td>
          <td id="TDDisposizione10" style="display:none"></td> <!-- Fix 14722 -->
          <!-- Fix 15030 Inizio modifica size-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMSec10 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMSec10"); 
  RilevDatiPrdTSQtaVrsUMSec10.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMSec10.setOnChange("recuperaQtaPrm(10);recuperaQtaArrotondateSec(10);"); 
  RilevDatiPrdTSQtaVrsUMSec10.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMSec10.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMSec10.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMSec10.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMSec10.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMSec10.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMSec10 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMSec10"); 
  RilevDatiPrdTSQtaScartoUMSec10.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMSec10.setOnChange("recuperaQtaScartoPrm(10);recuperaQtaScartoArrotondateSec(10);"); 
  RilevDatiPrdTSQtaScartoUMSec10.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMSec10.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMSec10.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMSec10.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMSec10.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMSec10.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMSecPrd10 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMSecPrd10"); 
  RilevDatiPrdTSIdUMSecPrd10.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMSecPrd10.getClassType()%>" id="<%=RilevDatiPrdTSIdUMSecPrd10.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMSecPrd10.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMSecPrd10.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMSecPrd10.write(out); 
%>
</td>
          <td id="RicalcoloQuantitaVers10TD" nowrap style="display:none;"><% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaVers10 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaVers10"); 
  RilevDatiPrdTSRicalcoloQuantitaVers10.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaVers10.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaVers10.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaVers10.write(out); 
%>
</td> <!-- Fix 39680 -->
          <!-- Fix 15030 Fine-->
          <td align="right"><button class="littleButton" id="CreaLottoBut10" name="CreaLottoBut10" onclick="creaLotto(10)" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButCreaLotto")%></button></td>
        </tr>
        <tr style="display:none">
          <td><% 
  WebMultiSearchForm RilevDatiPrdTSMagazzinoVrs10 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "MagazzinoVrs10", false, false, false, 1, null, null); 
  RilevDatiPrdTSMagazzinoVrs10.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSMagazzinoVrs10.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoVrs10"></span>--></td>
        </tr>
        <tr id="TR10_3" style="display:none">
          <td style="height:10px">
          </td>
        </tr>
        <tr id="TR11_1" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdotto11 =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("RilevDatiPrdTS", "Prodotto11", false, false, false, 1, "25", null); 
  RilevDatiPrdTSProdotto11.setExtraRelatedClassAD("DescrizioneArticolo.Descrizione,UMPrmMag,UMSecMag"); 
  RilevDatiPrdTSProdotto11.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdotto11.setOnKeyBlur("onKeyBlurArticolo(11)"); 
  RilevDatiPrdTSProdotto11.setOnKeyChange("resetDatiProdotto(11)"); 
  RilevDatiPrdTSProdotto11.setOnSearchBack("recuperaDatiProdotto()"); 
  RilevDatiPrdTSProdotto11.setAdditionalRestrictConditions("IdAnnoOrdine,IdClasseMerclg;IdNumeroOrdine,IdClasseMerclg;IdRigaAttivita,IdClasseMerclg;   TipoBolla,TipoArticolo;IdAnnoBolla,IdClasseMerclg;IdNumeroBolla,IdClasseMerclg"); 
  RilevDatiPrdTSProdotto11.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdotto11.write(out); 
%>
<!--<span class="articolomultisearchform" id="Prodotto11"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoVersione11 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoVersione11", false, false, false, 1, "1", null); 
  RilevDatiPrdTSProdottoVersione11.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoVersione11.setAdditionalRestrictConditions("IdAnnoOrdine,IdDisegnoTecnico;IdNumeroOrdine,IdDisegnoTecnico;IdRigaAttivita,IdDisegnoTecnico;   TipoBolla,StatoTecnico;IdAnnoBolla,IdDisegnoTecnico;IdNumeroBolla,IdDisegnoTecnico"); 
  RilevDatiPrdTSProdottoVersione11.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoVersione11.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoVersione11"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoConfig11 =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("RilevDatiPrdTS", "ProdottoConfig11", false, false, false, 1, "15", null); 
  RilevDatiPrdTSProdottoConfig11.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  RilevDatiPrdTSProdottoConfig11.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoConfig11.setAdditionalRestrictConditions("IdAnnoOrdine,SintesiConfig;IdNumeroOrdine,SintesiConfig;IdRigaAttivita,SintesiConfig;   TipoBolla,TipoConfig;IdAnnoBolla,SintesiConfig;IdNumeroBolla,SintesiConfig"); 
  RilevDatiPrdTSProdottoConfig11.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoConfig11.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ProdottoConfig11"></span>--></td>
            <!-- Fix 14722 Inizio-->
            <!-- Fix 15030 blocco commentato-->
            <!--td nowrap="true" id="TDNumeroOrdine11" style="display:none">               <input name="IdAnnoOrdine11" class="inputro" tabindex="-1" size="4"/>               <input name="IdNumeroOrdine11" class="inputro" tabindex="-1" size="9"/>               <input name="IdRigaAttivita11" type="Hidden"/>             </td-->
            <!-- Fix 14722 Fine-->
            <!-- Fix 15030 Inizio modifica size-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMPrm11 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMPrm11"); 
  RilevDatiPrdTSQtaVrsUMPrm11.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMPrm11.setOnChange("recuperaQtaSec(11);"); 
  RilevDatiPrdTSQtaVrsUMPrm11.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMPrm11.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMPrm11.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMPrm11.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMPrm11.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMPrm11.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMPrm11 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMPrm11"); 
  RilevDatiPrdTSQtaScartoUMPrm11.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMPrm11.setOnChange("recuperaQtaScartoSec(11);"); 
  RilevDatiPrdTSQtaScartoUMPrm11.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMPrm11.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMPrm11.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMPrm11.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMPrm11.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMPrm11.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMPrmPrd11 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMPrmPrd11"); 
  RilevDatiPrdTSIdUMPrmPrd11.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMPrmPrd11.getClassType()%>" id="<%=RilevDatiPrdTSIdUMPrmPrd11.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMPrmPrd11.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMPrmPrd11.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMPrmPrd11.write(out); 
%>
</td>
            <!-- Fix 15030 Fine-->
            <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSLottoProdotto11 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "LottoProdotto11", false, false, false, 1, "12", null); 
  RilevDatiPrdTSLottoProdotto11.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSLottoProdotto11.setAdditionalRestrictConditions("IdAnnoOrdine,DescrizioneLotto;IdNumeroOrdine,DescrizioneLotto;   IdRigaAttivita,DescrizioneLotto;IdAnnoOrdine11,DescrizioneLotto;IdNumeroOrdine11,DescrizioneLotto;IdRigaAttivita11,DescrizioneLotto;   IdVersionePrd11,DescrizioneLotto;IdEsternoConfigPrd11,DescrizioneLotto;"); 
  RilevDatiPrdTSLottoProdotto11.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.LottiVersamentiDoList"); 
  RilevDatiPrdTSLottoProdotto11.write(out); 
%>
<!--<span class="multisearchform" id="LottoProdotto11"></span>--></td>
        </tr>
        <tr id="TR11_2" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdottoOrdine11 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoOrdine11", false, false, false, 2, null, null); 
  RilevDatiPrdTSProdottoOrdine11.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoOrdine11.setOnSearchBack("fillVrsCfg()"); 
  RilevDatiPrdTSProdottoOrdine11.setAdditionalRestrictConditions("IdNumeroBolla,NumeroOrdFmt;IdAnnoBolla,NumeroOrdFmt;IdProdotto11,IdArticolo"); 
  RilevDatiPrdTSProdottoOrdine11.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.OrdiniEsecutiveDoList"); 
  RilevDatiPrdTSProdottoOrdine11.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoOrdine11"></span>-->
          <% 
  WebTextInput RilevDatiPrdTSDescrizioneProdotto11 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneProdotto11"); 
  RilevDatiPrdTSDescrizioneProdotto11.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneProdotto11.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneProdotto11.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneProdotto11.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneProdotto11.getName()%>" readonly size="29" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneProdotto11.write(out); 
%>
 <!-- Fix 15030 modifica size da 46-->
          <!-- Fix 15030 inizio-->
          <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita11 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita11"); 
  RilevDatiPrdTSIdRigaAttivita11.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita11.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita11.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita11.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita11.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita11.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita11.write(out); 
%>

          <!-- Fix 15030 fine-->
          </td>
          <td id="TDDisposizione11" style="display:none"></td> <!-- Fix 14722 -->
          <!-- Fix 15030 Inizio modifica size-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMSec11 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMSec11"); 
  RilevDatiPrdTSQtaVrsUMSec11.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMSec11.setOnChange("recuperaQtaPrm(11);recuperaQtaArrotondateSec(11);"); 
  RilevDatiPrdTSQtaVrsUMSec11.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMSec11.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMSec11.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMSec11.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMSec11.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMSec11.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMSec11 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMSec11"); 
  RilevDatiPrdTSQtaScartoUMSec11.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMSec11.setOnChange("recuperaQtaScartoPrm(11);recuperaQtaScartoArrotondateSec(11);"); 
  RilevDatiPrdTSQtaScartoUMSec11.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMSec11.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMSec11.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMSec11.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMSec11.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMSec11.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMSecPrd11 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMSecPrd11"); 
  RilevDatiPrdTSIdUMSecPrd11.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMSecPrd11.getClassType()%>" id="<%=RilevDatiPrdTSIdUMSecPrd11.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMSecPrd11.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMSecPrd11.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMSecPrd11.write(out); 
%>
</td>
          <td id="RicalcoloQuantitaVers11TD" nowrap style="display:none;"><% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaVers11 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaVers11"); 
  RilevDatiPrdTSRicalcoloQuantitaVers11.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaVers11.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaVers11.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaVers11.write(out); 
%>
</td> <!-- Fix 39680 -->
          <!-- Fix 15030 Fine-->
          <td align="right"><button class="littleButton" id="CreaLottoBut11" name="CreaLottoBut11" onclick="creaLotto(11)" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButCreaLotto")%></button></td>
        </tr>
        <tr style="display:none">
          <td><% 
  WebMultiSearchForm RilevDatiPrdTSMagazzinoVrs11 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "MagazzinoVrs11", false, false, false, 1, null, null); 
  RilevDatiPrdTSMagazzinoVrs11.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSMagazzinoVrs11.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoVrs11"></span>--></td>
        </tr>
        <tr id="TR11_3" style="display:none">
          <td style="height:10px">
          </td>
        </tr>
        <tr id="TR12_1" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdotto12 =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("RilevDatiPrdTS", "Prodotto12", false, false, false, 1, "25", null); 
  RilevDatiPrdTSProdotto12.setExtraRelatedClassAD("DescrizioneArticolo.Descrizione,UMPrmMag,UMSecMag"); 
  RilevDatiPrdTSProdotto12.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdotto12.setOnKeyBlur("onKeyBlurArticolo(12)"); 
  RilevDatiPrdTSProdotto12.setOnKeyChange("resetDatiProdotto(12)"); 
  RilevDatiPrdTSProdotto12.setOnSearchBack("recuperaDatiProdotto()"); 
  RilevDatiPrdTSProdotto12.setAdditionalRestrictConditions("IdAnnoOrdine,IdClasseMerclg;IdNumeroOrdine,IdClasseMerclg;IdRigaAttivita,IdClasseMerclg;   TipoBolla,TipoArticolo;IdAnnoBolla,IdClasseMerclg;IdNumeroBolla,IdClasseMerclg"); 
  RilevDatiPrdTSProdotto12.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdotto12.write(out); 
%>
<!--<span class="articolomultisearchform" id="Prodotto12"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoVersione12 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoVersione12", false, false, false, 1, "1", null); 
  RilevDatiPrdTSProdottoVersione12.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoVersione12.setAdditionalRestrictConditions("IdAnnoOrdine,IdDisegnoTecnico;IdNumeroOrdine,IdDisegnoTecnico;IdRigaAttivita,IdDisegnoTecnico;   TipoBolla,StatoTecnico;IdAnnoBolla,IdDisegnoTecnico;IdNumeroBolla,IdDisegnoTecnico"); 
  RilevDatiPrdTSProdottoVersione12.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoVersione12.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoVersione12"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoConfig12 =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("RilevDatiPrdTS", "ProdottoConfig12", false, false, false, 1, "15", null); 
  RilevDatiPrdTSProdottoConfig12.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  RilevDatiPrdTSProdottoConfig12.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoConfig12.setAdditionalRestrictConditions("IdAnnoOrdine,SintesiConfig;IdNumeroOrdine,SintesiConfig;IdRigaAttivita,SintesiConfig;   TipoBolla,TipoConfig;IdAnnoBolla,SintesiConfig;IdNumeroBolla,SintesiConfig"); 
  RilevDatiPrdTSProdottoConfig12.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoConfig12.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ProdottoConfig12"></span>--></td>
             <!-- Fix 14722 Inizio-->
             <!-- Fix 15030 blocco commentato-->
            <!--td nowrap="true" id="TDNumeroOrdine12" style="display:none">               <input name="IdAnnoOrdine12" class="inputro" tabindex="-1" size="4"/>               <input name="IdNumeroOrdine12" class="inputro" tabindex="-1" size="9"/>               <input name="IdRigaAttivita12" type="Hidden"/>             </td-->
            <!-- Fix 14722 Fine-->
            <!-- Fix 15030 Inizio modifica size-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMPrm12 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMPrm12"); 
  RilevDatiPrdTSQtaVrsUMPrm12.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMPrm12.setOnChange("recuperaQtaSec(12);"); 
  RilevDatiPrdTSQtaVrsUMPrm12.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMPrm12.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMPrm12.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMPrm12.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMPrm12.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMPrm12.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMPrm12 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMPrm12"); 
  RilevDatiPrdTSQtaScartoUMPrm12.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMPrm12.setOnChange("recuperaQtaScartoSec(12);"); 
  RilevDatiPrdTSQtaScartoUMPrm12.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMPrm12.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMPrm12.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMPrm12.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMPrm12.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMPrm12.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMPrmPrd12 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMPrmPrd12"); 
  RilevDatiPrdTSIdUMPrmPrd12.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMPrmPrd12.getClassType()%>" id="<%=RilevDatiPrdTSIdUMPrmPrd12.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMPrmPrd12.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMPrmPrd12.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMPrmPrd12.write(out); 
%>
</td>
            <!-- Fix 15030 Fine-->
            <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSLottoProdotto12 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "LottoProdotto12", false, false, false, 1, "12", null); 
  RilevDatiPrdTSLottoProdotto12.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSLottoProdotto12.setAdditionalRestrictConditions("IdAnnoOrdine,DescrizioneLotto;IdNumeroOrdine,DescrizioneLotto;   IdRigaAttivita,DescrizioneLotto;IdAnnoOrdine12,DescrizioneLotto;IdNumeroOrdine12,DescrizioneLotto;IdRigaAttivita12,DescrizioneLotto;   IdVersionePrd12,DescrizioneLotto;IdEsternoConfigPrd12,DescrizioneLotto;"); 
  RilevDatiPrdTSLottoProdotto12.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.LottiVersamentiDoList"); 
  RilevDatiPrdTSLottoProdotto12.write(out); 
%>
<!--<span class="multisearchform" id="LottoProdotto12"></span>--></td>
        </tr>
        <tr id="TR12_2" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdottoOrdine12 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoOrdine12", false, false, false, 2, null, null); 
  RilevDatiPrdTSProdottoOrdine12.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoOrdine12.setOnSearchBack("fillVrsCfg()"); 
  RilevDatiPrdTSProdottoOrdine12.setAdditionalRestrictConditions("IdNumeroBolla,NumeroOrdFmt;IdAnnoBolla,NumeroOrdFmt;IdProdotto12,IdArticolo"); 
  RilevDatiPrdTSProdottoOrdine12.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.OrdiniEsecutiveDoList"); 
  RilevDatiPrdTSProdottoOrdine12.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoOrdine12"></span>-->
          <% 
  WebTextInput RilevDatiPrdTSDescrizioneProdotto12 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneProdotto12"); 
  RilevDatiPrdTSDescrizioneProdotto12.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneProdotto12.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneProdotto12.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneProdotto12.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneProdotto12.getName()%>" readonly size="29" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneProdotto12.write(out); 
%>
 <!-- Fix 15030 modifica size da 46-->
          <!-- Fix 15030 inizio-->
          <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita12 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita12"); 
  RilevDatiPrdTSIdRigaAttivita12.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita12.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita12.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita12.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita12.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita12.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita12.write(out); 
%>

          <!-- Fix 15030 fine-->
          </td>
          <td id="TDDisposizione12" style="display:none"></td> <!-- Fix 14722 -->
          <!-- Fix 15030 Inizio modifica size-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMSec12 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMSec12"); 
  RilevDatiPrdTSQtaVrsUMSec12.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMSec12.setOnChange("recuperaQtaPrm(12);recuperaQtaArrotondateSec(12);"); 
  RilevDatiPrdTSQtaVrsUMSec12.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMSec12.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMSec12.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMSec12.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMSec12.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMSec12.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMSec12 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMSec12"); 
  RilevDatiPrdTSQtaScartoUMSec12.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMSec12.setOnChange("recuperaQtaScartoPrm(12);recuperaQtaScartoArrotondateSec(12);"); 
  RilevDatiPrdTSQtaScartoUMSec12.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMSec12.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMSec12.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMSec12.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMSec12.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMSec12.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMSecPrd12 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMSecPrd12"); 
  RilevDatiPrdTSIdUMSecPrd12.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMSecPrd12.getClassType()%>" id="<%=RilevDatiPrdTSIdUMSecPrd12.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMSecPrd12.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMSecPrd12.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMSecPrd12.write(out); 
%>
</td>
          <td id="RicalcoloQuantitaVers12TD" nowrap style="display:none;"><% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaVers12 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaVers12"); 
  RilevDatiPrdTSRicalcoloQuantitaVers12.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaVers12.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaVers12.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaVers12.write(out); 
%>
</td> <!-- Fix 39680 -->
          <!-- Fix 15030 Fine-->
          <td align="right"><button class="littleButton" id="CreaLottoBut12" name="CreaLottoBut12" onclick="creaLotto(12)" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButCreaLotto")%></button></td>
        </tr>
        <tr style="display:none">
          <td><% 
  WebMultiSearchForm RilevDatiPrdTSMagazzinoVrs12 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "MagazzinoVrs12", false, false, false, 1, null, null); 
  RilevDatiPrdTSMagazzinoVrs12.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSMagazzinoVrs12.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoVrs12"></span>--></td>
        </tr>
        <tr id="TR12_3" style="display:none">
          <td style="height:10px">
          </td>
        </tr>
        <tr id="TR13_1" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdotto13 =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("RilevDatiPrdTS", "Prodotto13", false, false, false, 1, "25", null); 
  RilevDatiPrdTSProdotto13.setExtraRelatedClassAD("DescrizioneArticolo.Descrizione,UMPrmMag,UMSecMag"); 
  RilevDatiPrdTSProdotto13.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdotto13.setOnKeyBlur("onKeyBlurArticolo(13)"); 
  RilevDatiPrdTSProdotto13.setOnKeyChange("resetDatiProdotto(13)"); 
  RilevDatiPrdTSProdotto13.setOnSearchBack("recuperaDatiProdotto()"); 
  RilevDatiPrdTSProdotto13.setAdditionalRestrictConditions("IdAnnoOrdine,IdClasseMerclg;IdNumeroOrdine,IdClasseMerclg;IdRigaAttivita,IdClasseMerclg;   TipoBolla,TipoArticolo;IdAnnoBolla,IdClasseMerclg;IdNumeroBolla,IdClasseMerclg"); 
  RilevDatiPrdTSProdotto13.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdotto13.write(out); 
%>
<!--<span class="articolomultisearchform" id="Prodotto13"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoVersione13 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoVersione13", false, false, false, 1, "1", null); 
  RilevDatiPrdTSProdottoVersione13.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoVersione13.setAdditionalRestrictConditions("IdAnnoOrdine,IdDisegnoTecnico;IdNumeroOrdine,IdDisegnoTecnico;IdRigaAttivita,IdDisegnoTecnico;   TipoBolla,StatoTecnico;IdAnnoBolla,IdDisegnoTecnico;IdNumeroBolla,IdDisegnoTecnico"); 
  RilevDatiPrdTSProdottoVersione13.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoVersione13.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoVersione13"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoConfig13 =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("RilevDatiPrdTS", "ProdottoConfig13", false, false, false, 1, "15", null); 
  RilevDatiPrdTSProdottoConfig13.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  RilevDatiPrdTSProdottoConfig13.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoConfig13.setAdditionalRestrictConditions("IdAnnoOrdine,SintesiConfig;IdNumeroOrdine,SintesiConfig;IdRigaAttivita,SintesiConfig;   TipoBolla,TipoConfig;IdAnnoBolla,SintesiConfig;IdNumeroBolla,SintesiConfig"); 
  RilevDatiPrdTSProdottoConfig13.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoConfig13.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ProdottoConfig13"></span>--></td>
             <!-- Fix 14722 Inizio-->
             <!-- Fix 15030 blocco commentato-->
            <!--td nowrap="true" id="TDNumeroOrdine13" style="display:none">               <input name="IdAnnoOrdine13" class="inputro" tabindex="-1" size="4"/>               <input name="IdNumeroOrdine13" class="inputro" tabindex="-1" size="9"/>               <input name="IdRigaAttivita13" type="Hidden"/>             </td-->
            <!-- Fix 14722 Fine-->
            <!-- Fix 15030 Inizio modifica size-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMPrm13 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMPrm13"); 
  RilevDatiPrdTSQtaVrsUMPrm13.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMPrm13.setOnChange("recuperaQtaSec(13);"); 
  RilevDatiPrdTSQtaVrsUMPrm13.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMPrm13.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMPrm13.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMPrm13.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMPrm13.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMPrm13.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMPrm13 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMPrm13"); 
  RilevDatiPrdTSQtaScartoUMPrm13.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMPrm13.setOnChange("recuperaQtaScartoSec(13);"); 
  RilevDatiPrdTSQtaScartoUMPrm13.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMPrm13.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMPrm13.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMPrm13.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMPrm13.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMPrm13.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMPrmPrd13 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMPrmPrd13"); 
  RilevDatiPrdTSIdUMPrmPrd13.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMPrmPrd13.getClassType()%>" id="<%=RilevDatiPrdTSIdUMPrmPrd13.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMPrmPrd13.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMPrmPrd13.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMPrmPrd13.write(out); 
%>
</td>
            <!-- Fix 15030 Fine-->
            <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSLottoProdotto13 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "LottoProdotto13", false, false, false, 1, "12", null); 
  RilevDatiPrdTSLottoProdotto13.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSLottoProdotto13.setAdditionalRestrictConditions("IdAnnoOrdine,DescrizioneLotto;IdNumeroOrdine,DescrizioneLotto;   IdRigaAttivita,DescrizioneLotto;IdAnnoOrdine13,DescrizioneLotto;IdNumeroOrdine13,DescrizioneLotto;IdRigaAttivita13,DescrizioneLotto;   IdVersionePrd13,DescrizioneLotto;IdEsternoConfigPrd13,DescrizioneLotto;"); 
  RilevDatiPrdTSLottoProdotto13.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.LottiVersamentiDoList"); 
  RilevDatiPrdTSLottoProdotto13.write(out); 
%>
<!--<span class="multisearchform" id="LottoProdotto13"></span>--></td>
        </tr>
        <tr id="TR13_2" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdottoOrdine13 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoOrdine13", false, false, false, 2, null, null); 
  RilevDatiPrdTSProdottoOrdine13.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoOrdine13.setOnSearchBack("fillVrsCfg()"); 
  RilevDatiPrdTSProdottoOrdine13.setAdditionalRestrictConditions("IdNumeroBolla,NumeroOrdFmt;IdAnnoBolla,NumeroOrdFmt;IdProdotto13,IdArticolo"); 
  RilevDatiPrdTSProdottoOrdine13.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.OrdiniEsecutiveDoList"); 
  RilevDatiPrdTSProdottoOrdine13.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoOrdine13"></span>-->
          <% 
  WebTextInput RilevDatiPrdTSDescrizioneProdotto13 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneProdotto13"); 
  RilevDatiPrdTSDescrizioneProdotto13.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneProdotto13.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneProdotto13.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneProdotto13.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneProdotto13.getName()%>" readonly size="29" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneProdotto13.write(out); 
%>
 <!-- Fix 15030 modifica size da 46-->
          <!-- Fix 15030 inizio-->
          <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita13 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita13"); 
  RilevDatiPrdTSIdRigaAttivita13.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita13.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita13.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita13.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita13.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita13.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita13.write(out); 
%>

          <!-- Fix 15030 fine-->
          </td>
          <td id="TDDisposizione13" style="display:none"></td> <!-- Fix 14722 -->
          <!-- Fix 15030 Inizio modifica size-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMSec13 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMSec13"); 
  RilevDatiPrdTSQtaVrsUMSec13.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMSec13.setOnChange("recuperaQtaPrm(13);recuperaQtaArrotondateSec(13);"); 
  RilevDatiPrdTSQtaVrsUMSec13.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMSec13.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMSec13.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMSec13.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMSec13.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMSec13.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMSec13 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMSec13"); 
  RilevDatiPrdTSQtaScartoUMSec13.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMSec13.setOnChange("recuperaQtaScartoPrm(13);recuperaQtaScartoArrotondateSec(13);"); 
  RilevDatiPrdTSQtaScartoUMSec13.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMSec13.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMSec13.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMSec13.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMSec13.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMSec13.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMSecPrd13 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMSecPrd13"); 
  RilevDatiPrdTSIdUMSecPrd13.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMSecPrd13.getClassType()%>" id="<%=RilevDatiPrdTSIdUMSecPrd13.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMSecPrd13.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMSecPrd13.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMSecPrd13.write(out); 
%>
</td>
          <td id="RicalcoloQuantitaVers13TD" nowrap style="display:none;"><% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaVers13 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaVers13"); 
  RilevDatiPrdTSRicalcoloQuantitaVers13.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaVers13.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaVers13.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaVers13.write(out); 
%>
</td> <!-- Fix 39680 -->
          <!-- Fix 15030 Fine-->
          <td align="right"><button class="littleButton" id="CreaLottoBut13" name="CreaLottoBut13" onclick="creaLotto(13)" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButCreaLotto")%></button></td>
        </tr>
        <tr style="display:none">
          <td><% 
  WebMultiSearchForm RilevDatiPrdTSMagazzinoVrs13 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "MagazzinoVrs13", false, false, false, 1, null, null); 
  RilevDatiPrdTSMagazzinoVrs13.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSMagazzinoVrs13.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoVrs13"></span>--></td>
        </tr>
        <tr id="TR13_3" style="display:none">
          <td style="height:10px">
          </td>
        </tr>
        <tr id="TR14_1" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdotto14 =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("RilevDatiPrdTS", "Prodotto14", false, false, false, 1, "25", null); 
  RilevDatiPrdTSProdotto14.setExtraRelatedClassAD("DescrizioneArticolo.Descrizione,UMPrmMag,UMSecMag"); 
  RilevDatiPrdTSProdotto14.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdotto14.setOnKeyBlur("onKeyBlurArticolo(14)"); 
  RilevDatiPrdTSProdotto14.setOnKeyChange("resetDatiProdotto(14)"); 
  RilevDatiPrdTSProdotto14.setOnSearchBack("recuperaDatiProdotto()"); 
  RilevDatiPrdTSProdotto14.setAdditionalRestrictConditions("IdAnnoOrdine,IdClasseMerclg;IdNumeroOrdine,IdClasseMerclg;IdRigaAttivita,IdClasseMerclg;   TipoBolla,TipoArticolo;IdAnnoBolla,IdClasseMerclg;IdNumeroBolla,IdClasseMerclg"); 
  RilevDatiPrdTSProdotto14.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdotto14.write(out); 
%>
<!--<span class="articolomultisearchform" id="Prodotto14"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoVersione14 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoVersione14", false, false, false, 1, "1", null); 
  RilevDatiPrdTSProdottoVersione14.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoVersione14.setAdditionalRestrictConditions("IdAnnoOrdine,IdDisegnoTecnico;IdNumeroOrdine,IdDisegnoTecnico;IdRigaAttivita,IdDisegnoTecnico;   TipoBolla,StatoTecnico;IdAnnoBolla,IdDisegnoTecnico;IdNumeroBolla,IdDisegnoTecnico"); 
  RilevDatiPrdTSProdottoVersione14.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoVersione14.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoVersione14"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoConfig14 =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("RilevDatiPrdTS", "ProdottoConfig14", false, false, false, 1, "15", null); 
  RilevDatiPrdTSProdottoConfig14.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  RilevDatiPrdTSProdottoConfig14.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoConfig14.setAdditionalRestrictConditions("IdAnnoOrdine,SintesiConfig;IdNumeroOrdine,SintesiConfig;IdRigaAttivita,SintesiConfig;   TipoBolla,TipoConfig;IdAnnoBolla,SintesiConfig;IdNumeroBolla,SintesiConfig"); 
  RilevDatiPrdTSProdottoConfig14.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoConfig14.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ProdottoConfig14"></span>--></td>
             <!-- Fix 14722 Inizio-->
             <!-- Fix 15030 blocco commentato-->
            <!--td nowrap="true" id="TDNumeroOrdine14" style="display:none">               <input name="IdAnnoOrdine14" class="inputro" tabindex="-1" size="4"/>               <input name="IdNumeroOrdine14" class="inputro" tabindex="-1" size="9"/>               <input name="IdRigaAttivita14" type="Hidden"/>             </td-->
            <!-- Fix 14722 Fine-->
            <!-- Fix 15030 Inizio modifica size-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMPrm14 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMPrm14"); 
  RilevDatiPrdTSQtaVrsUMPrm14.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMPrm14.setOnChange("recuperaQtaSec(14);"); 
  RilevDatiPrdTSQtaVrsUMPrm14.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMPrm14.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMPrm14.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMPrm14.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMPrm14.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMPrm14.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMPrm14 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMPrm14"); 
  RilevDatiPrdTSQtaScartoUMPrm14.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMPrm14.setOnChange("recuperaQtaScartoSec(14);"); 
  RilevDatiPrdTSQtaScartoUMPrm14.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMPrm14.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMPrm14.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMPrm14.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMPrm14.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMPrm14.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMPrmPrd14 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMPrmPrd14"); 
  RilevDatiPrdTSIdUMPrmPrd14.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMPrmPrd14.getClassType()%>" id="<%=RilevDatiPrdTSIdUMPrmPrd14.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMPrmPrd14.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMPrmPrd14.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMPrmPrd14.write(out); 
%>
</td>
            <!-- Fix 15030 Fine-->
            <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSLottoProdotto14 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "LottoProdotto14", false, false, false, 1, "12", null); 
  RilevDatiPrdTSLottoProdotto14.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSLottoProdotto14.setAdditionalRestrictConditions("IdAnnoOrdine,DescrizioneLotto;IdNumeroOrdine,DescrizioneLotto;   IdRigaAttivita,DescrizioneLotto;IdAnnoOrdine14,DescrizioneLotto;IdNumeroOrdine14,DescrizioneLotto;IdRigaAttivita14,DescrizioneLotto;   IdVersionePrd14,DescrizioneLotto;IdEsternoConfigPrd14,DescrizioneLotto;"); 
  RilevDatiPrdTSLottoProdotto14.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.LottiVersamentiDoList"); 
  RilevDatiPrdTSLottoProdotto14.write(out); 
%>
<!--<span class="multisearchform" id="LottoProdotto14"></span>--></td>
        </tr>
        <tr id="TR14_2" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdottoOrdine14 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoOrdine14", false, false, false, 2, null, null); 
  RilevDatiPrdTSProdottoOrdine14.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoOrdine14.setOnSearchBack("fillVrsCfg()"); 
  RilevDatiPrdTSProdottoOrdine14.setAdditionalRestrictConditions("IdNumeroBolla,NumeroOrdFmt;IdAnnoBolla,NumeroOrdFmt;IdProdotto14,IdArticolo"); 
  RilevDatiPrdTSProdottoOrdine14.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.OrdiniEsecutiveDoList"); 
  RilevDatiPrdTSProdottoOrdine14.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoOrdine14"></span>-->
          <% 
  WebTextInput RilevDatiPrdTSDescrizioneProdotto14 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneProdotto14"); 
  RilevDatiPrdTSDescrizioneProdotto14.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneProdotto14.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneProdotto14.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneProdotto14.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneProdotto14.getName()%>" readonly size="29" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneProdotto14.write(out); 
%>
 <!-- Fix 15030 modifica size da 46-->
          <!-- Fix 15030 inizio-->
          <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita14 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita14"); 
  RilevDatiPrdTSIdRigaAttivita14.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita14.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita14.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita14.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita14.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita14.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita14.write(out); 
%>

          <!-- Fix 15030 fine-->
          </td>
          <td id="TDDisposizione14" style="display:none"></td> <!-- Fix 14722 -->
          <!-- Fix 15030 Inizio modifica size-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMSec14 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMSec14"); 
  RilevDatiPrdTSQtaVrsUMSec14.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMSec14.setOnChange("recuperaQtaPrm(14);recuperaQtaArrotondateSec(14);"); 
  RilevDatiPrdTSQtaVrsUMSec14.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMSec14.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMSec14.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMSec14.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMSec14.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMSec14.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMSec14 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMSec14"); 
  RilevDatiPrdTSQtaScartoUMSec14.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMSec14.setOnChange("recuperaQtaScartoPrm(14);recuperaQtaScartoArrotondateSec(14);"); 
  RilevDatiPrdTSQtaScartoUMSec14.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMSec14.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMSec14.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMSec14.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMSec14.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMSec14.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMSecPrd14 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMSecPrd14"); 
  RilevDatiPrdTSIdUMSecPrd14.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMSecPrd14.getClassType()%>" id="<%=RilevDatiPrdTSIdUMSecPrd14.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMSecPrd14.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMSecPrd14.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMSecPrd14.write(out); 
%>
</td>
          <td id="RicalcoloQuantitaVers14TD" nowrap style="display:none;"><% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaVers14 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaVers14"); 
  RilevDatiPrdTSRicalcoloQuantitaVers14.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaVers14.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaVers14.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaVers14.write(out); 
%>
</td> <!-- Fix 39680 -->
          <!-- Fix 15030 Fine-->
          <td align="right"><button class="littleButton" id="CreaLottoBut14" name="CreaLottoBut14" onclick="creaLotto(14)" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButCreaLotto")%></button></td>
        </tr>
        <tr style="display:none">
          <td><% 
  WebMultiSearchForm RilevDatiPrdTSMagazzinoVrs14 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "MagazzinoVrs14", false, false, false, 1, null, null); 
  RilevDatiPrdTSMagazzinoVrs14.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSMagazzinoVrs14.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoVrs14"></span>--></td>
        </tr>
        <tr id="TR14_3" style="display:none">
          <td style="height:10px">
          </td>
        </tr>
        <tr id="TR15_1" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdotto15 =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("RilevDatiPrdTS", "Prodotto15", false, false, false, 1, "25", null); 
  RilevDatiPrdTSProdotto15.setExtraRelatedClassAD("DescrizioneArticolo.Descrizione,UMPrmMag,UMSecMag"); 
  RilevDatiPrdTSProdotto15.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdotto15.setOnKeyBlur("onKeyBlurArticolo(15)"); 
  RilevDatiPrdTSProdotto15.setOnKeyChange("resetDatiProdotto(15)"); 
  RilevDatiPrdTSProdotto15.setOnSearchBack("recuperaDatiProdotto()"); 
  RilevDatiPrdTSProdotto15.setAdditionalRestrictConditions("IdAnnoOrdine,IdClasseMerclg;IdNumeroOrdine,IdClasseMerclg;IdRigaAttivita,IdClasseMerclg;   TipoBolla,TipoArticolo;IdAnnoBolla,IdClasseMerclg;IdNumeroBolla,IdClasseMerclg"); 
  RilevDatiPrdTSProdotto15.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdotto15.write(out); 
%>
<!--<span class="articolomultisearchform" id="Prodotto15"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoVersione15 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoVersione15", false, false, false, 1, "1", null); 
  RilevDatiPrdTSProdottoVersione15.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoVersione15.setAdditionalRestrictConditions("IdAnnoOrdine,IdDisegnoTecnico;IdNumeroOrdine,IdDisegnoTecnico;IdRigaAttivita,IdDisegnoTecnico;   TipoBolla,StatoTecnico;IdAnnoBolla,IdDisegnoTecnico;IdNumeroBolla,IdDisegnoTecnico"); 
  RilevDatiPrdTSProdottoVersione15.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoVersione15.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoVersione15"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoConfig15 =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("RilevDatiPrdTS", "ProdottoConfig15", false, false, false, 1, "15", null); 
  RilevDatiPrdTSProdottoConfig15.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  RilevDatiPrdTSProdottoConfig15.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoConfig15.setAdditionalRestrictConditions("IdAnnoOrdine,SintesiConfig;IdNumeroOrdine,SintesiConfig;IdRigaAttivita,SintesiConfig;   TipoBolla,TipoConfig;IdAnnoBolla,SintesiConfig;IdNumeroBolla,SintesiConfig"); 
  RilevDatiPrdTSProdottoConfig15.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoConfig15.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ProdottoConfig15"></span>--></td>
             <!-- Fix 14722 Inizio-->
             <!-- Fix 15030 blocco commentato-->
            <!--td nowrap="true" id="TDNumeroOrdine15" style="display:none">               <input name="IdAnnoOrdine15" class="inputro" tabindex="-1" size="4"/>               <input name="IdNumeroOrdine15" class="inputro" tabindex="-1" size="9"/>               <input name="IdRigaAttivita15" type="Hidden"/>             </td-->
            <!-- Fix 14722 Fine-->
            <!-- Fix 15030 Inizio modifica size-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMPrm15 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMPrm15"); 
  RilevDatiPrdTSQtaVrsUMPrm15.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMPrm15.setOnChange("recuperaQtaSec(15);"); 
  RilevDatiPrdTSQtaVrsUMPrm15.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMPrm15.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMPrm15.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMPrm15.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMPrm15.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMPrm15.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMPrm15 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMPrm15"); 
  RilevDatiPrdTSQtaScartoUMPrm15.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMPrm15.setOnChange("recuperaQtaScartoSec(15);"); 
  RilevDatiPrdTSQtaScartoUMPrm15.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMPrm15.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMPrm15.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMPrm15.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMPrm15.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMPrm15.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMPrmPrd15 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMPrmPrd15"); 
  RilevDatiPrdTSIdUMPrmPrd15.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMPrmPrd15.getClassType()%>" id="<%=RilevDatiPrdTSIdUMPrmPrd15.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMPrmPrd15.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMPrmPrd15.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMPrmPrd15.write(out); 
%>
</td>
            <!-- Fix 15030 Fine-->
            <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSLottoProdotto15 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "LottoProdotto15", false, false, false, 1, "12", null); 
  RilevDatiPrdTSLottoProdotto15.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSLottoProdotto15.setAdditionalRestrictConditions("IdAnnoOrdine,DescrizioneLotto;IdNumeroOrdine,DescrizioneLotto;   IdRigaAttivita,DescrizioneLotto;IdAnnoOrdine15,DescrizioneLotto;IdNumeroOrdine15,DescrizioneLotto;IdRigaAttivita15,DescrizioneLotto;   IdVersionePrd15,DescrizioneLotto;IdEsternoConfigPrd15,DescrizioneLotto;"); 
  RilevDatiPrdTSLottoProdotto15.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.LottiVersamentiDoList"); 
  RilevDatiPrdTSLottoProdotto15.write(out); 
%>
<!--<span class="multisearchform" id="LottoProdotto15"></span>--></td>
        </tr>
        <tr id="TR15_2" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdottoOrdine15 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoOrdine15", false, false, false, 2, null, null); 
  RilevDatiPrdTSProdottoOrdine15.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoOrdine15.setOnSearchBack("fillVrsCfg()"); 
  RilevDatiPrdTSProdottoOrdine15.setAdditionalRestrictConditions("IdNumeroBolla,NumeroOrdFmt;IdAnnoBolla,NumeroOrdFmt;IdProdotto15,IdArticolo"); 
  RilevDatiPrdTSProdottoOrdine15.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.OrdiniEsecutiveDoList"); 
  RilevDatiPrdTSProdottoOrdine15.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoOrdine15"></span>-->
          <% 
  WebTextInput RilevDatiPrdTSDescrizioneProdotto15 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneProdotto15"); 
  RilevDatiPrdTSDescrizioneProdotto15.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneProdotto15.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneProdotto15.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneProdotto15.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneProdotto15.getName()%>" readonly size="29" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneProdotto15.write(out); 
%>
 <!-- Fix 15030 modifica size da 46-->
          <!-- Fix 15030 inizio-->
          <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita15 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita15"); 
  RilevDatiPrdTSIdRigaAttivita15.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita15.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita15.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita15.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita15.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita15.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita15.write(out); 
%>

          <!-- Fix 15030 fine-->
          </td>
          <td id="TDDisposizione15" style="display:none"></td> <!-- Fix 14722 -->
          <!-- Fix 15030 Inizio modifica size-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMSec15 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMSec15"); 
  RilevDatiPrdTSQtaVrsUMSec15.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMSec15.setOnChange("recuperaQtaPrm(15);recuperaQtaArrotondateSec(15);"); 
  RilevDatiPrdTSQtaVrsUMSec15.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMSec15.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMSec15.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMSec15.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMSec15.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMSec15.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMSec15 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMSec15"); 
  RilevDatiPrdTSQtaScartoUMSec15.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMSec15.setOnChange("recuperaQtaScartoPrm(15);recuperaQtaScartoArrotondateSec(15);"); 
  RilevDatiPrdTSQtaScartoUMSec15.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMSec15.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMSec15.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMSec15.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMSec15.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMSec15.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMSecPrd15 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMSecPrd15"); 
  RilevDatiPrdTSIdUMSecPrd15.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMSecPrd15.getClassType()%>" id="<%=RilevDatiPrdTSIdUMSecPrd15.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMSecPrd15.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMSecPrd15.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMSecPrd15.write(out); 
%>
</td>
          <td id="RicalcoloQuantitaVers15TD" nowrap style="display:none;"><% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaVers15 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaVers15"); 
  RilevDatiPrdTSRicalcoloQuantitaVers15.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaVers15.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaVers15.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaVers15.write(out); 
%>
</td> <!-- Fix 39680 -->
          <!-- Fix 15030 Fine-->
          <td align="right"><button class="littleButton" id="CreaLottoBut15" name="CreaLottoBut15" onclick="creaLotto(15)" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButCreaLotto")%></button></td>
        </tr>
        <tr style="display:none">
          <td><% 
  WebMultiSearchForm RilevDatiPrdTSMagazzinoVrs15 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "MagazzinoVrs15", false, false, false, 1, null, null); 
  RilevDatiPrdTSMagazzinoVrs15.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSMagazzinoVrs15.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoVrs15"></span>--></td>
        </tr>
        <tr id="TR15_3" style="display:none">
          <td style="height:10px">
          </td>
        </tr>
        <tr id="TR16_1" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdotto16 =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("RilevDatiPrdTS", "Prodotto16", false, false, false, 1, "25", null); 
  RilevDatiPrdTSProdotto16.setExtraRelatedClassAD("DescrizioneArticolo.Descrizione,UMPrmMag,UMSecMag"); 
  RilevDatiPrdTSProdotto16.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdotto16.setOnKeyBlur("onKeyBlurArticolo(16)"); 
  RilevDatiPrdTSProdotto16.setOnKeyChange("resetDatiProdotto(16)"); 
  RilevDatiPrdTSProdotto16.setOnSearchBack("recuperaDatiProdotto()"); 
  RilevDatiPrdTSProdotto16.setAdditionalRestrictConditions("IdAnnoOrdine,IdClasseMerclg;IdNumeroOrdine,IdClasseMerclg;IdRigaAttivita,IdClasseMerclg;   TipoBolla,TipoArticolo;IdAnnoBolla,IdClasseMerclg;IdNumeroBolla,IdClasseMerclg"); 
  RilevDatiPrdTSProdotto16.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdotto16.write(out); 
%>
<!--<span class="articolomultisearchform" id="Prodotto16"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoVersione16 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoVersione16", false, false, false, 1, "1", null); 
  RilevDatiPrdTSProdottoVersione16.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoVersione16.setAdditionalRestrictConditions("IdAnnoOrdine,IdDisegnoTecnico;IdNumeroOrdine,IdDisegnoTecnico;IdRigaAttivita,IdDisegnoTecnico;   TipoBolla,StatoTecnico;IdAnnoBolla,IdDisegnoTecnico;IdNumeroBolla,IdDisegnoTecnico"); 
  RilevDatiPrdTSProdottoVersione16.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoVersione16.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoVersione16"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoConfig16 =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("RilevDatiPrdTS", "ProdottoConfig16", false, false, false, 1, "15", null); 
  RilevDatiPrdTSProdottoConfig16.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  RilevDatiPrdTSProdottoConfig16.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoConfig16.setAdditionalRestrictConditions("IdAnnoOrdine,SintesiConfig;IdNumeroOrdine,SintesiConfig;IdRigaAttivita,SintesiConfig;   TipoBolla,TipoConfig;IdAnnoBolla,SintesiConfig;IdNumeroBolla,SintesiConfig"); 
  RilevDatiPrdTSProdottoConfig16.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoConfig16.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ProdottoConfig16"></span>--></td>
             <!-- Fix 14722 Inizio-->
             <!-- Fix 15030 blocco commentato-->
            <!--td nowrap="true" id="TDNumeroOrdine16" style="display:none">               <input name="IdAnnoOrdine16" class="inputro" tabindex="-1" size="4"/>               <input name="IdNumeroOrdine16" class="inputro" tabindex="-1" size="9"/>               <input name="IdRigaAttivita16" type="Hidden"/>             </td-->
            <!-- Fix 14722 Fine-->
            <!-- Fix 15030 Inizio modifica size-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMPrm16 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMPrm16"); 
  RilevDatiPrdTSQtaVrsUMPrm16.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMPrm16.setOnChange("recuperaQtaSec(16);"); 
  RilevDatiPrdTSQtaVrsUMPrm16.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMPrm16.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMPrm16.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMPrm16.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMPrm16.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMPrm16.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMPrm16 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMPrm16"); 
  RilevDatiPrdTSQtaScartoUMPrm16.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMPrm16.setOnChange("recuperaQtaScartoSec(16);"); 
  RilevDatiPrdTSQtaScartoUMPrm16.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMPrm16.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMPrm16.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMPrm16.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMPrm16.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMPrm16.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMPrmPrd16 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMPrmPrd16"); 
  RilevDatiPrdTSIdUMPrmPrd16.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMPrmPrd16.getClassType()%>" id="<%=RilevDatiPrdTSIdUMPrmPrd16.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMPrmPrd16.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMPrmPrd16.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMPrmPrd16.write(out); 
%>
</td>
            <!-- Fix 15030 Fine-->
            <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSLottoProdotto16 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "LottoProdotto16", false, false, false, 1, "12", null); 
  RilevDatiPrdTSLottoProdotto16.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSLottoProdotto16.setAdditionalRestrictConditions("IdAnnoOrdine,DescrizioneLotto;IdNumeroOrdine,DescrizioneLotto;   IdRigaAttivita,DescrizioneLotto;IdAnnoOrdine16,DescrizioneLotto;IdNumeroOrdine16,DescrizioneLotto;IdRigaAttivita16,DescrizioneLotto;   IdVersionePrd16,DescrizioneLotto;IdEsternoConfigPrd16,DescrizioneLotto;"); 
  RilevDatiPrdTSLottoProdotto16.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.LottiVersamentiDoList"); 
  RilevDatiPrdTSLottoProdotto16.write(out); 
%>
<!--<span class="multisearchform" id="LottoProdotto16"></span>--></td>
        </tr>
        <tr id="TR16_2" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdottoOrdine16 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoOrdine16", false, false, false, 2, null, null); 
  RilevDatiPrdTSProdottoOrdine16.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoOrdine16.setOnSearchBack("fillVrsCfg()"); 
  RilevDatiPrdTSProdottoOrdine16.setAdditionalRestrictConditions("IdNumeroBolla,NumeroOrdFmt;IdAnnoBolla,NumeroOrdFmt;IdProdotto16,IdArticolo"); 
  RilevDatiPrdTSProdottoOrdine16.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.OrdiniEsecutiveDoList"); 
  RilevDatiPrdTSProdottoOrdine16.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoOrdine16"></span>-->
          <% 
  WebTextInput RilevDatiPrdTSDescrizioneProdotto16 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneProdotto16"); 
  RilevDatiPrdTSDescrizioneProdotto16.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneProdotto16.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneProdotto16.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneProdotto16.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneProdotto16.getName()%>" readonly size="29" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneProdotto16.write(out); 
%>
 <!-- Fix 15030 modifica size da 46-->
          <!-- Fix 15030 inizio-->
          <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita16 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita16"); 
  RilevDatiPrdTSIdRigaAttivita16.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita16.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita16.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita16.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita16.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita16.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita16.write(out); 
%>

          <!-- Fix 15030 fine-->
          </td>
          <td id="TDDisposizione16" style="display:none"></td> <!-- Fix 14722 -->
          <!-- Fix 15030 Inizio modifica size-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMSec16 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMSec16"); 
  RilevDatiPrdTSQtaVrsUMSec16.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMSec16.setOnChange("recuperaQtaPrm(16);recuperaQtaArrotondateSec(16);"); 
  RilevDatiPrdTSQtaVrsUMSec16.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMSec16.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMSec16.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMSec16.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMSec16.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMSec16.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMSec16 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMSec16"); 
  RilevDatiPrdTSQtaScartoUMSec16.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMSec16.setOnChange("recuperaQtaScartoPrm(16);recuperaQtaScartoArrotondateSec(16);"); 
  RilevDatiPrdTSQtaScartoUMSec16.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMSec16.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMSec16.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMSec16.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMSec16.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMSec16.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMSecPrd16 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMSecPrd16"); 
  RilevDatiPrdTSIdUMSecPrd16.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMSecPrd16.getClassType()%>" id="<%=RilevDatiPrdTSIdUMSecPrd16.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMSecPrd16.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMSecPrd16.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMSecPrd16.write(out); 
%>
</td>
          <td id="RicalcoloQuantitaVers16TD" nowrap style="display:none;"><% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaVers16 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaVers16"); 
  RilevDatiPrdTSRicalcoloQuantitaVers16.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaVers16.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaVers16.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaVers16.write(out); 
%>
</td> <!-- Fix 39680 -->
          <!-- Fix 15030 Fine-->
          <td align="right"><button class="littleButton" id="CreaLottoBut16" name="CreaLottoBut16" onclick="creaLotto(16)" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButCreaLotto")%></button></td>
        </tr>
        <tr style="display:none">
          <td><% 
  WebMultiSearchForm RilevDatiPrdTSMagazzinoVrs16 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "MagazzinoVrs16", false, false, false, 1, null, null); 
  RilevDatiPrdTSMagazzinoVrs16.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSMagazzinoVrs16.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoVrs16"></span>--></td>
        </tr>
        <tr id="TR16_3" style="display:none">
          <td style="height:10px">
          </td>
        </tr>
        <tr id="TR17_1" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdotto17 =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("RilevDatiPrdTS", "Prodotto17", false, false, false, 1, "25", null); 
  RilevDatiPrdTSProdotto17.setExtraRelatedClassAD("DescrizioneArticolo.Descrizione,UMPrmMag,UMSecMag"); 
  RilevDatiPrdTSProdotto17.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdotto17.setOnKeyBlur("onKeyBlurArticolo(17)"); 
  RilevDatiPrdTSProdotto17.setOnKeyChange("resetDatiProdotto(17)"); 
  RilevDatiPrdTSProdotto17.setOnSearchBack("recuperaDatiProdotto()"); 
  RilevDatiPrdTSProdotto17.setAdditionalRestrictConditions("IdAnnoOrdine,IdClasseMerclg;IdNumeroOrdine,IdClasseMerclg;IdRigaAttivita,IdClasseMerclg;   TipoBolla,TipoArticolo;IdAnnoBolla,IdClasseMerclg;IdNumeroBolla,IdClasseMerclg"); 
  RilevDatiPrdTSProdotto17.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdotto17.write(out); 
%>
<!--<span class="articolomultisearchform" id="Prodotto17"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoVersione17 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoVersione17", false, false, false, 1, "1", null); 
  RilevDatiPrdTSProdottoVersione17.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoVersione17.setAdditionalRestrictConditions("IdAnnoOrdine,IdDisegnoTecnico;IdNumeroOrdine,IdDisegnoTecnico;IdRigaAttivita,IdDisegnoTecnico;   TipoBolla,StatoTecnico;IdAnnoBolla,IdDisegnoTecnico;IdNumeroBolla,IdDisegnoTecnico"); 
  RilevDatiPrdTSProdottoVersione17.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoVersione17.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoVersione17"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoConfig17 =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("RilevDatiPrdTS", "ProdottoConfig17", false, false, false, 1, "15", null); 
  RilevDatiPrdTSProdottoConfig17.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  RilevDatiPrdTSProdottoConfig17.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoConfig17.setAdditionalRestrictConditions("IdAnnoOrdine,SintesiConfig;IdNumeroOrdine,SintesiConfig;IdRigaAttivita,SintesiConfig;   TipoBolla,TipoConfig;IdAnnoBolla,SintesiConfig;IdNumeroBolla,SintesiConfig"); 
  RilevDatiPrdTSProdottoConfig17.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoConfig17.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ProdottoConfig17"></span>--></td>
             <!-- Fix 14722 Inizio-->
             <!-- Fix 15030 blocco commentato-->
            <!--td nowrap="true" id="TDNumeroOrdine17" style="display:none">               <input name="IdAnnoOrdine17" class="inputro" tabindex="-1" size="4"/>               <input name="IdNumeroOrdine17" class="inputro" tabindex="-1" size="9"/>               <input name="IdRigaAttivita17" type="Hidden"/>             </td-->
            <!-- Fix 14722 Fine-->
            <!-- Fix 15030 Inizio modifica size-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMPrm17 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMPrm17"); 
  RilevDatiPrdTSQtaVrsUMPrm17.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMPrm17.setOnChange("recuperaQtaSec(17);"); 
  RilevDatiPrdTSQtaVrsUMPrm17.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMPrm17.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMPrm17.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMPrm17.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMPrm17.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMPrm17.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMPrm17 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMPrm17"); 
  RilevDatiPrdTSQtaScartoUMPrm17.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMPrm17.setOnChange("recuperaQtaScartoSec(17);"); 
  RilevDatiPrdTSQtaScartoUMPrm17.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMPrm17.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMPrm17.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMPrm17.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMPrm17.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMPrm17.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMPrmPrd17 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMPrmPrd17"); 
  RilevDatiPrdTSIdUMPrmPrd17.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMPrmPrd17.getClassType()%>" id="<%=RilevDatiPrdTSIdUMPrmPrd17.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMPrmPrd17.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMPrmPrd17.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMPrmPrd17.write(out); 
%>
</td>
            <!-- Fix 15030 Fine-->
            <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSLottoProdotto17 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "LottoProdotto17", false, false, false, 1, "12", null); 
  RilevDatiPrdTSLottoProdotto17.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSLottoProdotto17.setAdditionalRestrictConditions("IdAnnoOrdine,DescrizioneLotto;IdNumeroOrdine,DescrizioneLotto;   IdRigaAttivita,DescrizioneLotto;IdAnnoOrdine17,DescrizioneLotto;IdNumeroOrdine17,DescrizioneLotto;IdRigaAttivita17,DescrizioneLotto;   IdVersionePrd17,DescrizioneLotto;IdEsternoConfigPrd17,DescrizioneLotto;"); 
  RilevDatiPrdTSLottoProdotto17.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.LottiVersamentiDoList"); 
  RilevDatiPrdTSLottoProdotto17.write(out); 
%>
<!--<span class="multisearchform" id="LottoProdotto17"></span>--></td>
        </tr>
        <tr id="TR17_2" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdottoOrdine17 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoOrdine17", false, false, false, 2, null, null); 
  RilevDatiPrdTSProdottoOrdine17.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoOrdine17.setOnSearchBack("fillVrsCfg()"); 
  RilevDatiPrdTSProdottoOrdine17.setAdditionalRestrictConditions("IdNumeroBolla,NumeroOrdFmt;IdAnnoBolla,NumeroOrdFmt;IdProdotto17,IdArticolo"); 
  RilevDatiPrdTSProdottoOrdine17.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.OrdiniEsecutiveDoList"); 
  RilevDatiPrdTSProdottoOrdine17.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoOrdine17"></span>-->
          <% 
  WebTextInput RilevDatiPrdTSDescrizioneProdotto17 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneProdotto17"); 
  RilevDatiPrdTSDescrizioneProdotto17.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneProdotto17.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneProdotto17.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneProdotto17.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneProdotto17.getName()%>" readonly size="29" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneProdotto17.write(out); 
%>
 <!-- Fix 15030 modifica size da 46-->
          <!-- Fix 15030 inizio-->
          <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita17 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita17"); 
  RilevDatiPrdTSIdRigaAttivita17.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita17.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita17.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita17.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita17.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita17.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita17.write(out); 
%>

          <!-- Fix 15030 fine-->
          </td>
          <td id="TDDisposizione17" style="display:none"></td> <!-- Fix 14722 -->
          <!-- Fix 15030 Inizio modifica size-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMSec17 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMSec17"); 
  RilevDatiPrdTSQtaVrsUMSec17.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMSec17.setOnChange("recuperaQtaPrm(17);recuperaQtaArrotondateSec(17);"); 
  RilevDatiPrdTSQtaVrsUMSec17.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMSec17.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMSec17.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMSec17.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMSec17.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMSec17.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMSec17 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMSec17"); 
  RilevDatiPrdTSQtaScartoUMSec17.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMSec17.setOnChange("recuperaQtaScartoPrm(17);recuperaQtaScartoArrotondateSec(17);"); 
  RilevDatiPrdTSQtaScartoUMSec17.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMSec17.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMSec17.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMSec17.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMSec17.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMSec17.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMSecPrd17 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMSecPrd17"); 
  RilevDatiPrdTSIdUMSecPrd17.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMSecPrd17.getClassType()%>" id="<%=RilevDatiPrdTSIdUMSecPrd17.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMSecPrd17.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMSecPrd17.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMSecPrd17.write(out); 
%>
</td>
          <td id="RicalcoloQuantitaVers17TD" nowrap style="display:none;"><% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaVers17 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaVers17"); 
  RilevDatiPrdTSRicalcoloQuantitaVers17.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaVers17.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaVers17.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaVers17.write(out); 
%>
</td> <!-- Fix 39680 -->
          <!-- Fix 15030 Fine-->
          <td align="right"><button class="littleButton" id="CreaLottoBut17" name="CreaLottoBut17" onclick="creaLotto(17)" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButCreaLotto")%></button></td>
        </tr>
        <tr style="display:none">
          <td><% 
  WebMultiSearchForm RilevDatiPrdTSMagazzinoVrs17 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "MagazzinoVrs17", false, false, false, 1, null, null); 
  RilevDatiPrdTSMagazzinoVrs17.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSMagazzinoVrs17.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoVrs17"></span>--></td>
        </tr>
        <tr id="TR17_3" style="display:none">
          <td style="height:10px">
          </td>
        </tr>
        <tr id="TR18_1" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdotto18 =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("RilevDatiPrdTS", "Prodotto18", false, false, false, 1, "25", null); 
  RilevDatiPrdTSProdotto18.setExtraRelatedClassAD("DescrizioneArticolo.Descrizione,UMPrmMag,UMSecMag"); 
  RilevDatiPrdTSProdotto18.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdotto18.setOnKeyBlur("onKeyBlurArticolo(18)"); 
  RilevDatiPrdTSProdotto18.setOnKeyChange("resetDatiProdotto(18)"); 
  RilevDatiPrdTSProdotto18.setOnSearchBack("recuperaDatiProdotto()"); 
  RilevDatiPrdTSProdotto18.setAdditionalRestrictConditions("IdAnnoOrdine,IdClasseMerclg;IdNumeroOrdine,IdClasseMerclg;IdRigaAttivita,IdClasseMerclg;   TipoBolla,TipoArticolo;IdAnnoBolla,IdClasseMerclg;IdNumeroBolla,IdClasseMerclg"); 
  RilevDatiPrdTSProdotto18.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdotto18.write(out); 
%>
<!--<span class="articolomultisearchform" id="Prodotto18"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoVersione18 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoVersione18", false, false, false, 1, "1", null); 
  RilevDatiPrdTSProdottoVersione18.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoVersione18.setAdditionalRestrictConditions("IdAnnoOrdine,IdDisegnoTecnico;IdNumeroOrdine,IdDisegnoTecnico;IdRigaAttivita,IdDisegnoTecnico;   TipoBolla,StatoTecnico;IdAnnoBolla,IdDisegnoTecnico;IdNumeroBolla,IdDisegnoTecnico"); 
  RilevDatiPrdTSProdottoVersione18.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoVersione18.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoVersione18"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoConfig18 =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("RilevDatiPrdTS", "ProdottoConfig18", false, false, false, 1, "15", null); 
  RilevDatiPrdTSProdottoConfig18.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  RilevDatiPrdTSProdottoConfig18.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoConfig18.setAdditionalRestrictConditions("IdAnnoOrdine,SintesiConfig;IdNumeroOrdine,SintesiConfig;IdRigaAttivita,SintesiConfig;   TipoBolla,TipoConfig;IdAnnoBolla,SintesiConfig;IdNumeroBolla,SintesiConfig"); 
  RilevDatiPrdTSProdottoConfig18.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoConfig18.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ProdottoConfig18"></span>--></td>
             <!-- Fix 14722 Inizio-->
             <!-- Fix 15030 blocco commentato-->
            <!--td nowrap="true" id="TDNumeroOrdine18" style="display:none">               <input name="IdAnnoOrdine18" class="inputro" tabindex="-1" size="4"/>               <input name="IdNumeroOrdine18" class="inputro" tabindex="-1" size="9"/>               <input name="IdRigaAttivita18" type="Hidden"/>             </td-->
            <!-- Fix 14722 Fine-->
            <!-- Fix 15030 Inizio modifica size-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMPrm18 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMPrm18"); 
  RilevDatiPrdTSQtaVrsUMPrm18.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMPrm18.setOnChange("recuperaQtaSec(18);"); 
  RilevDatiPrdTSQtaVrsUMPrm18.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMPrm18.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMPrm18.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMPrm18.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMPrm18.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMPrm18.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMPrm18 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMPrm18"); 
  RilevDatiPrdTSQtaScartoUMPrm18.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMPrm18.setOnChange("recuperaQtaScartoSec(18);"); 
  RilevDatiPrdTSQtaScartoUMPrm18.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMPrm18.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMPrm18.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMPrm18.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMPrm18.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMPrm18.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMPrmPrd18 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMPrmPrd18"); 
  RilevDatiPrdTSIdUMPrmPrd18.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMPrmPrd18.getClassType()%>" id="<%=RilevDatiPrdTSIdUMPrmPrd18.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMPrmPrd18.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMPrmPrd18.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMPrmPrd18.write(out); 
%>
</td>
            <!-- Fix 15030 Fine-->
            <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSLottoProdotto18 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "LottoProdotto18", false, false, false, 1, "12", null); 
  RilevDatiPrdTSLottoProdotto18.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSLottoProdotto18.setAdditionalRestrictConditions("IdAnnoOrdine,DescrizioneLotto;IdNumeroOrdine,DescrizioneLotto;   IdRigaAttivita,DescrizioneLotto;IdAnnoOrdine18,DescrizioneLotto;IdNumeroOrdine18,DescrizioneLotto;IdRigaAttivita18,DescrizioneLotto;   IdVersionePrd18,DescrizioneLotto;IdEsternoConfigPrd18,DescrizioneLotto;"); 
  RilevDatiPrdTSLottoProdotto18.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.LottiVersamentiDoList"); 
  RilevDatiPrdTSLottoProdotto18.write(out); 
%>
<!--<span class="multisearchform" id="LottoProdotto18"></span>--></td>
        </tr>
        <tr id="TR18_2" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdottoOrdine18 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoOrdine18", false, false, false, 2, null, null); 
  RilevDatiPrdTSProdottoOrdine18.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoOrdine18.setOnSearchBack("fillVrsCfg()"); 
  RilevDatiPrdTSProdottoOrdine18.setAdditionalRestrictConditions("IdNumeroBolla,NumeroOrdFmt;IdAnnoBolla,NumeroOrdFmt;IdProdotto18,IdArticolo"); 
  RilevDatiPrdTSProdottoOrdine18.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.OrdiniEsecutiveDoList"); 
  RilevDatiPrdTSProdottoOrdine18.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoOrdine18"></span>-->
          <% 
  WebTextInput RilevDatiPrdTSDescrizioneProdotto18 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneProdotto18"); 
  RilevDatiPrdTSDescrizioneProdotto18.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneProdotto18.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneProdotto18.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneProdotto18.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneProdotto18.getName()%>" readonly size="29" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneProdotto18.write(out); 
%>
 <!-- Fix 15030 modifica size da 46-->
          <!-- Fix 15030 inizio-->
          <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita18 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita18"); 
  RilevDatiPrdTSIdRigaAttivita18.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita18.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita18.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita18.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita18.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita18.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita18.write(out); 
%>

          <!-- Fix 15030 fine-->
          </td>
          <td id="TDDisposizione18" style="display:none"></td> <!-- Fix 14722 -->
          <!-- Fix 15030 Inizio modifica size-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMSec18 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMSec18"); 
  RilevDatiPrdTSQtaVrsUMSec18.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMSec18.setOnChange("recuperaQtaPrm(18);recuperaQtaArrotondateSec(18);"); 
  RilevDatiPrdTSQtaVrsUMSec18.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMSec18.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMSec18.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMSec18.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMSec18.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMSec18.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMSec18 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMSec18"); 
  RilevDatiPrdTSQtaScartoUMSec18.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMSec18.setOnChange("recuperaQtaScartoPrm(18);recuperaQtaScartoArrotondateSec(18);"); 
  RilevDatiPrdTSQtaScartoUMSec18.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMSec18.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMSec18.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMSec18.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMSec18.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMSec18.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMSecPrd18 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMSecPrd18"); 
  RilevDatiPrdTSIdUMSecPrd18.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMSecPrd18.getClassType()%>" id="<%=RilevDatiPrdTSIdUMSecPrd18.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMSecPrd18.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMSecPrd18.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMSecPrd18.write(out); 
%>
</td>
          <td id="RicalcoloQuantitaVers18TD" nowrap style="display:none;"><% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaVers18 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaVers18"); 
  RilevDatiPrdTSRicalcoloQuantitaVers18.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaVers18.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaVers18.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaVers18.write(out); 
%>
</td> <!-- Fix 39680 -->
          <!-- Fix 15030 Fine-->
          <td align="right"><button class="littleButton" id="CreaLottoBut18" name="CreaLottoBut18" onclick="creaLotto(18)" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButCreaLotto")%></button></td>
        </tr>
        <tr style="display:none">
          <td><% 
  WebMultiSearchForm RilevDatiPrdTSMagazzinoVrs18 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "MagazzinoVrs18", false, false, false, 1, null, null); 
  RilevDatiPrdTSMagazzinoVrs18.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSMagazzinoVrs18.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoVrs18"></span>--></td>
        </tr>
        <tr id="TR18_3" style="display:none">
          <td style="height:10px">
          </td>
        </tr>
        <tr id="TR19_1" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdotto19 =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("RilevDatiPrdTS", "Prodotto19", false, false, false, 1, "25", null); 
  RilevDatiPrdTSProdotto19.setExtraRelatedClassAD("DescrizioneArticolo.Descrizione,UMPrmMag,UMSecMag"); 
  RilevDatiPrdTSProdotto19.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdotto19.setOnKeyBlur("onKeyBlurArticolo(19)"); 
  RilevDatiPrdTSProdotto19.setOnKeyChange("resetDatiProdotto(19)"); 
  RilevDatiPrdTSProdotto19.setOnSearchBack("recuperaDatiProdotto()"); 
  RilevDatiPrdTSProdotto19.setAdditionalRestrictConditions("IdAnnoOrdine,IdClasseMerclg;IdNumeroOrdine,IdClasseMerclg;IdRigaAttivita,IdClasseMerclg;   TipoBolla,TipoArticolo;IdAnnoBolla,IdClasseMerclg;IdNumeroBolla,IdClasseMerclg"); 
  RilevDatiPrdTSProdotto19.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdotto19.write(out); 
%>
<!--<span class="articolomultisearchform" id="Prodotto19"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoVersione19 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoVersione19", false, false, false, 1, "1", null); 
  RilevDatiPrdTSProdottoVersione19.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoVersione19.setAdditionalRestrictConditions("IdAnnoOrdine,IdDisegnoTecnico;IdNumeroOrdine,IdDisegnoTecnico;IdRigaAttivita,IdDisegnoTecnico;   TipoBolla,StatoTecnico;IdAnnoBolla,IdDisegnoTecnico;IdNumeroBolla,IdDisegnoTecnico"); 
  RilevDatiPrdTSProdottoVersione19.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoVersione19.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoVersione19"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoConfig19 =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("RilevDatiPrdTS", "ProdottoConfig19", false, false, false, 1, "15", null); 
  RilevDatiPrdTSProdottoConfig19.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  RilevDatiPrdTSProdottoConfig19.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoConfig19.setAdditionalRestrictConditions("IdAnnoOrdine,SintesiConfig;IdNumeroOrdine,SintesiConfig;IdRigaAttivita,SintesiConfig;   TipoBolla,TipoConfig;IdAnnoBolla,SintesiConfig;IdNumeroBolla,SintesiConfig"); 
  RilevDatiPrdTSProdottoConfig19.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoConfig19.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ProdottoConfig19"></span>--></td>
             <!-- Fix 14722 Inizio-->
             <!-- Fix 15030 blocco commentato-->
            <!--td nowrap="true" id="TDNumeroOrdine19" style="display:none">               <input name="IdAnnoOrdine19" class="inputro" tabindex="-1" size="4"/>               <input name="IdNumeroOrdine19" class="inputro" tabindex="-1" size="9"/>               <input name="IdRigaAttivita19" type="Hidden"/>             </td-->
            <!-- Fix 14722 Fine-->
            <!-- Fix 15030 Inizio modifica size-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMPrm19 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMPrm19"); 
  RilevDatiPrdTSQtaVrsUMPrm19.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMPrm19.setOnChange("recuperaQtaSec(19);"); 
  RilevDatiPrdTSQtaVrsUMPrm19.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMPrm19.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMPrm19.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMPrm19.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMPrm19.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMPrm19.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMPrm19 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMPrm19"); 
  RilevDatiPrdTSQtaScartoUMPrm19.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMPrm19.setOnChange("recuperaQtaScartoSec(19);"); 
  RilevDatiPrdTSQtaScartoUMPrm19.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMPrm19.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMPrm19.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMPrm19.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMPrm19.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMPrm19.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMPrmPrd19 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMPrmPrd19"); 
  RilevDatiPrdTSIdUMPrmPrd19.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMPrmPrd19.getClassType()%>" id="<%=RilevDatiPrdTSIdUMPrmPrd19.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMPrmPrd19.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMPrmPrd19.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMPrmPrd19.write(out); 
%>
</td>
            <!-- Fix 15030 Fine-->
            <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSLottoProdotto19 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "LottoProdotto19", false, false, false, 1, "12", null); 
  RilevDatiPrdTSLottoProdotto19.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSLottoProdotto19.setAdditionalRestrictConditions("IdAnnoOrdine,DescrizioneLotto;IdNumeroOrdine,DescrizioneLotto;   IdRigaAttivita,DescrizioneLotto;IdAnnoOrdine19,DescrizioneLotto;IdNumeroOrdine19,DescrizioneLotto;IdRigaAttivita19,DescrizioneLotto;   IdVersionePrd19,DescrizioneLotto;IdEsternoConfigPrd19,DescrizioneLotto;"); 
  RilevDatiPrdTSLottoProdotto19.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.LottiVersamentiDoList"); 
  RilevDatiPrdTSLottoProdotto19.write(out); 
%>
<!--<span class="multisearchform" id="LottoProdotto19"></span>--></td>
        </tr>
        <tr id="TR19_2" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdottoOrdine19 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoOrdine19", false, false, false, 2, null, null); 
  RilevDatiPrdTSProdottoOrdine19.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoOrdine19.setOnSearchBack("fillVrsCfg()"); 
  RilevDatiPrdTSProdottoOrdine19.setAdditionalRestrictConditions("IdNumeroBolla,NumeroOrdFmt;IdAnnoBolla,NumeroOrdFmt;IdProdotto19,IdArticolo"); 
  RilevDatiPrdTSProdottoOrdine19.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.OrdiniEsecutiveDoList"); 
  RilevDatiPrdTSProdottoOrdine19.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoOrdine19"></span>-->
          <% 
  WebTextInput RilevDatiPrdTSDescrizioneProdotto19 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneProdotto19"); 
  RilevDatiPrdTSDescrizioneProdotto19.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneProdotto19.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneProdotto19.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneProdotto19.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneProdotto19.getName()%>" readonly size="29" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneProdotto19.write(out); 
%>
 <!-- Fix 15030 modifica size da 46-->
          <!-- Fix 15030 inizio-->
          <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita19 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita19"); 
  RilevDatiPrdTSIdRigaAttivita19.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita19.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita19.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita19.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita19.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita19.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita19.write(out); 
%>

          <!-- Fix 15030 fine-->
          </td>
          <td id="TDDisposizione19" style="display:none"></td> <!-- Fix 14722 -->
          <!-- Fix 15030 Inizio modifica size-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMSec19 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMSec19"); 
  RilevDatiPrdTSQtaVrsUMSec19.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMSec19.setOnChange("recuperaQtaPrm(19);recuperaQtaArrotondateSec(19);"); 
  RilevDatiPrdTSQtaVrsUMSec19.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMSec19.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMSec19.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMSec19.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMSec19.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMSec19.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMSec19 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMSec19"); 
  RilevDatiPrdTSQtaScartoUMSec19.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMSec19.setOnChange("recuperaQtaScartoPrm(19);recuperaQtaScartoArrotondateSec(19);"); 
  RilevDatiPrdTSQtaScartoUMSec19.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMSec19.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMSec19.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMSec19.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMSec19.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMSec19.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMSecPrd19 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMSecPrd19"); 
  RilevDatiPrdTSIdUMSecPrd19.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMSecPrd19.getClassType()%>" id="<%=RilevDatiPrdTSIdUMSecPrd19.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMSecPrd19.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMSecPrd19.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMSecPrd19.write(out); 
%>
</td>
          <td id="RicalcoloQuantitaVers19TD" nowrap style="display:none;"><% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaVers19 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaVers19"); 
  RilevDatiPrdTSRicalcoloQuantitaVers19.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaVers19.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaVers19.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaVers19.write(out); 
%>
</td> <!-- Fix 39680 -->
          <!-- Fix 15030 Fine-->
          <td align="right"><button class="littleButton" id="CreaLottoBut19" name="CreaLottoBut19" onclick="creaLotto(19)" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButCreaLotto")%></button></td>
        </tr>
        <tr style="display:none">
          <td><% 
  WebMultiSearchForm RilevDatiPrdTSMagazzinoVrs19 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "MagazzinoVrs19", false, false, false, 1, null, null); 
  RilevDatiPrdTSMagazzinoVrs19.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSMagazzinoVrs19.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoVrs19"></span>--></td>
        </tr>
        <tr id="TR19_3" style="display:none">
          <td style="height:10px">
          </td>
        </tr>
        <tr id="TR20_1" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdotto20 =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("RilevDatiPrdTS", "Prodotto20", false, false, false, 1, "25", null); 
  RilevDatiPrdTSProdotto20.setExtraRelatedClassAD("DescrizioneArticolo.Descrizione,UMPrmMag,UMSecMag"); 
  RilevDatiPrdTSProdotto20.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdotto20.setOnKeyBlur("onKeyBlurArticolo(20)"); 
  RilevDatiPrdTSProdotto20.setOnKeyChange("resetDatiProdotto(20)"); 
  RilevDatiPrdTSProdotto20.setOnSearchBack("recuperaDatiProdotto()"); 
  RilevDatiPrdTSProdotto20.setAdditionalRestrictConditions("IdAnnoOrdine,IdClasseMerclg;IdNumeroOrdine,IdClasseMerclg;IdRigaAttivita,IdClasseMerclg;   TipoBolla,TipoArticolo;IdAnnoBolla,IdClasseMerclg;IdNumeroBolla,IdClasseMerclg"); 
  RilevDatiPrdTSProdotto20.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdotto20.write(out); 
%>
<!--<span class="articolomultisearchform" id="Prodotto20"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoVersione20 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoVersione20", false, false, false, 1, "1", null); 
  RilevDatiPrdTSProdottoVersione20.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoVersione20.setAdditionalRestrictConditions("IdAnnoOrdine,IdDisegnoTecnico;IdNumeroOrdine,IdDisegnoTecnico;IdRigaAttivita,IdDisegnoTecnico;   TipoBolla,StatoTecnico;IdAnnoBolla,IdDisegnoTecnico;IdNumeroBolla,IdDisegnoTecnico"); 
  RilevDatiPrdTSProdottoVersione20.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoVersione20.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoVersione20"></span>-->
            <% 
  WebMultiSearchForm RilevDatiPrdTSProdottoConfig20 =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("RilevDatiPrdTS", "ProdottoConfig20", false, false, false, 1, "15", null); 
  RilevDatiPrdTSProdottoConfig20.setExtraRelatedClassAD("IdAzienda,IdConfigurazione"); 
  RilevDatiPrdTSProdottoConfig20.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoConfig20.setAdditionalRestrictConditions("IdAnnoOrdine,SintesiConfig;IdNumeroOrdine,SintesiConfig;IdRigaAttivita,SintesiConfig;   TipoBolla,TipoConfig;IdAnnoBolla,SintesiConfig;IdNumeroBolla,SintesiConfig"); 
  RilevDatiPrdTSProdottoConfig20.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.ProdottiDoList"); 
  RilevDatiPrdTSProdottoConfig20.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="ProdottoConfig20"></span>--></td>
             <!-- Fix 14722 Inizio-->
             <!-- Fix 15030 blocco commentato-->
            <!--td nowrap="true" id="TDNumeroOrdine20" style="display:none">               <input name="IdAnnoOrdine20" class="inputro" tabindex="-1" size="4"/>               <input name="IdNumeroOrdine20" class="inputro" tabindex="-1" size="9"/>               <input name="IdRigaAttivita20" type="Hidden"/>             </td-->
            <!-- Fix 14722 Fine-->
            <!-- Fix 15030 Inizio modifica size-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMPrm20 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMPrm20"); 
  RilevDatiPrdTSQtaVrsUMPrm20.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMPrm20.setOnChange("recuperaQtaSec(20);"); 
  RilevDatiPrdTSQtaVrsUMPrm20.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMPrm20.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMPrm20.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMPrm20.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMPrm20.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMPrm20.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMPrm20 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMPrm20"); 
  RilevDatiPrdTSQtaScartoUMPrm20.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMPrm20.setOnChange("recuperaQtaScartoSec(20);"); 
  RilevDatiPrdTSQtaScartoUMPrm20.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMPrm20.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMPrm20.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMPrm20.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMPrm20.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMPrm20.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
            <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMPrmPrd20 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMPrmPrd20"); 
  RilevDatiPrdTSIdUMPrmPrd20.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMPrmPrd20.getClassType()%>" id="<%=RilevDatiPrdTSIdUMPrmPrd20.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMPrmPrd20.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMPrmPrd20.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMPrmPrd20.write(out); 
%>
</td>
            <!-- Fix 15030 Fine-->
            <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSLottoProdotto20 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "LottoProdotto20", false, false, false, 1, "12", null); 
  RilevDatiPrdTSLottoProdotto20.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSLottoProdotto20.setAdditionalRestrictConditions("IdAnnoOrdine,DescrizioneLotto;IdNumeroOrdine,DescrizioneLotto;   IdRigaAttivita,DescrizioneLotto;IdAnnoOrdine20,DescrizioneLotto;IdNumeroOrdine20,DescrizioneLotto;IdRigaAttivita20,DescrizioneLotto;   IdVersionePrd20,DescrizioneLotto;IdEsternoConfigPrd20,DescrizioneLotto;"); 
  RilevDatiPrdTSLottoProdotto20.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.LottiVersamentiDoList"); 
  RilevDatiPrdTSLottoProdotto20.write(out); 
%>
<!--<span class="multisearchform" id="LottoProdotto20"></span>--></td>
        </tr>
        <tr id="TR20_2" style="display:none">
          <td nowrap><% 
  WebMultiSearchForm RilevDatiPrdTSProdottoOrdine20 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "ProdottoOrdine20", false, false, false, 2, null, null); 
  RilevDatiPrdTSProdottoOrdine20.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSProdottoOrdine20.setOnSearchBack("fillVrsCfg()"); 
  RilevDatiPrdTSProdottoOrdine20.setAdditionalRestrictConditions("IdNumeroBolla,NumeroOrdFmt;IdAnnoBolla,NumeroOrdFmt;IdProdotto20,IdArticolo"); 
  RilevDatiPrdTSProdottoOrdine20.setSpecificDOList("it.thera.thip.produzione.raccoltaDati.web.OrdiniEsecutiveDoList"); 
  RilevDatiPrdTSProdottoOrdine20.write(out); 
%>
<!--<span class="multisearchform" id="ProdottoOrdine20"></span>-->
          <% 
  WebTextInput RilevDatiPrdTSDescrizioneProdotto20 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "DescrizioneProdotto20"); 
  RilevDatiPrdTSDescrizioneProdotto20.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSDescrizioneProdotto20.getClassType()%>" id="<%=RilevDatiPrdTSDescrizioneProdotto20.getId()%>" maxlength="<%=RilevDatiPrdTSDescrizioneProdotto20.getMaxLength()%>" name="<%=RilevDatiPrdTSDescrizioneProdotto20.getName()%>" readonly size="29" tabindex="-1"><% 
  RilevDatiPrdTSDescrizioneProdotto20.write(out); 
%>
 <!-- Fix 15030 modifica size da 46-->
          <!-- Fix 15030 inizio-->
          <% 
  WebTextInput RilevDatiPrdTSIdRigaAttivita20 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdRigaAttivita20"); 
  RilevDatiPrdTSIdRigaAttivita20.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdRigaAttivita20.getClassType()%>" id="<%=RilevDatiPrdTSIdRigaAttivita20.getId()%>" maxlength="<%=RilevDatiPrdTSIdRigaAttivita20.getMaxLength()%>" name="<%=RilevDatiPrdTSIdRigaAttivita20.getName()%>" size="<%=RilevDatiPrdTSIdRigaAttivita20.getSize()%>" type="Hidden"><% 
  RilevDatiPrdTSIdRigaAttivita20.write(out); 
%>

          <!-- Fix 15030 fine-->
          </td>
          <td id="TDDisposizione20" style="display:none"></td> <!-- Fix 14722 -->
          <!-- Fix 15030 Inizio modifica size-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaVrsUMSec20 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaVrsUMSec20"); 
  RilevDatiPrdTSQtaVrsUMSec20.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaVrsUMSec20.setOnChange("recuperaQtaPrm(20);recuperaQtaArrotondateSec(20);"); 
  RilevDatiPrdTSQtaVrsUMSec20.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaVrsUMSec20.getClassType()%>" id="<%=RilevDatiPrdTSQtaVrsUMSec20.getId()%>" maxlength="<%=RilevDatiPrdTSQtaVrsUMSec20.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaVrsUMSec20.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaVrsUMSec20.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSQtaScartoUMSec20 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "QtaScartoUMSec20"); 
  RilevDatiPrdTSQtaScartoUMSec20.setOnFocus("showKeyPad(this);"); 
  RilevDatiPrdTSQtaScartoUMSec20.setOnChange("recuperaQtaScartoPrm(20);recuperaQtaScartoArrotondateSec(20);"); 
  RilevDatiPrdTSQtaScartoUMSec20.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSQtaScartoUMSec20.getClassType()%>" id="<%=RilevDatiPrdTSQtaScartoUMSec20.getId()%>" maxlength="<%=RilevDatiPrdTSQtaScartoUMSec20.getMaxLength()%>" name="<%=RilevDatiPrdTSQtaScartoUMSec20.getName()%>" size="9"><% 
  RilevDatiPrdTSQtaScartoUMSec20.write(out); 
%>
</td> <!--Fix 22941 Tolto tabindex="-1"-->
          <td nowrap><% 
  WebTextInput RilevDatiPrdTSIdUMSecPrd20 =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "IdUMSecPrd20"); 
  RilevDatiPrdTSIdUMSecPrd20.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSIdUMSecPrd20.getClassType()%>" id="<%=RilevDatiPrdTSIdUMSecPrd20.getId()%>" maxlength="<%=RilevDatiPrdTSIdUMSecPrd20.getMaxLength()%>" name="<%=RilevDatiPrdTSIdUMSecPrd20.getName()%>" readonly size="1" tabindex="-1"><% 
  RilevDatiPrdTSIdUMSecPrd20.write(out); 
%>
</td>
          <td id="RicalcoloQuantitaVers20TD" nowrap style="display:none;"><% 
  WebCheckBox RilevDatiPrdTSRicalcoloQuantitaVers20 =  
     new com.thera.thermfw.web.WebCheckBox("RilevDatiPrdTS", "RicalcoloQuantitaVers20"); 
  RilevDatiPrdTSRicalcoloQuantitaVers20.setParent(RilevDatiPrdTSForm); 
%>
<input id="<%=RilevDatiPrdTSRicalcoloQuantitaVers20.getId()%>" name="<%=RilevDatiPrdTSRicalcoloQuantitaVers20.getName()%>" type="checkbox" value="Y"><%
  RilevDatiPrdTSRicalcoloQuantitaVers20.write(out); 
%>
</td> <!-- Fix 39680 -->
          <!-- Fix 15030 Fine-->
          <td align="right"><button class="littleButton" id="CreaLottoBut20" name="CreaLottoBut20" onclick="creaLotto(20)" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButCreaLotto")%></button></td>
        </tr>
        <tr style="display:none">
          <td><% 
  WebMultiSearchForm RilevDatiPrdTSMagazzinoVrs20 =  
     new com.thera.thermfw.web.WebMultiSearchForm("RilevDatiPrdTS", "MagazzinoVrs20", false, false, false, 1, null, null); 
  RilevDatiPrdTSMagazzinoVrs20.setParent(RilevDatiPrdTSForm); 
  RilevDatiPrdTSMagazzinoVrs20.write(out); 
%>
<!--<span class="multisearchform" id="MagazzinoVrs20"></span>--></td>
        </tr>
        <tr id="TR20_3" style="display:none">
          <td style="height:10px">
          </td>
        </tr>
      </table>
      </div>
   </td>
  </tr>
  <tr>
  <td width="15px"></td>
  <!-- Fix 18536 inizio -->
  <td>
  	<table style="height:100%; width:100%">
  		<tr>
			  <td align="left"><button id="NuovaRigaBut" name="NuovaRigaBut" onclick="NuovaRiga()" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "ButNuovaRiga")%></button></td>
			  <!-- Fix 72XXX Softre inizio -->
			  <td align="left"><button id="CreaLottiFilatiManufatti" name="CreaLottiFilatiManufatti" onclick="creaLottiTestoriFilatiManufatti()" type="button"><%= ResourceLoader.getString("it/testori/thip/produzione/raccoltaDati/resources/YRilevDatiPrdTS", "ButCreaLottiFilatiManufatti")%></button></td>
			              <% 
  WebTextInput RilevDatiPrdTSLottiFilatiManufatti =  
     new com.thera.thermfw.web.WebTextInput("RilevDatiPrdTS", "LottiFilatiManufatti"); 
			              RilevDatiPrdTSLottiFilatiManufatti.setParent(RilevDatiPrdTSForm); 
%>
<input class="<%=RilevDatiPrdTSLottiFilatiManufatti.getClassType()%>" id="<%=RilevDatiPrdTSLottiFilatiManufatti.getId()%>" maxlength="<%=RilevDatiPrdTSLottiFilatiManufatti.getMaxLength()%>" name="<%=RilevDatiPrdTSLottiFilatiManufatti.getName()%>" size="<%=RilevDatiPrdTSLottiFilatiManufatti.getSize()%>" type="Hidden"><% 
RilevDatiPrdTSLottiFilatiManufatti.write(out); 
%>
			  <!-- Fix 72XXX Softre fine -->
        <td align="center" width="40%"><label id="numPag" name="numPag"></label></td><!-- Fix 19148 -->
  			<td align="right">
  				<button id="PaginaPrecBut" name="PaginaPrecBut" onclick="PaginaPrec()" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "PaginaPrec")%></button>
  				<button id="PaginaSuccBut" name="PaginaSuccBut" onclick="PaginaSucc()" type="button"><%= ResourceLoader.getString("it/thera/thip/produzione/raccoltaDati/resources/RilevDatiPrdTS", "PaginaSucc")%></button>
  			</td>
  		</tr>
  	</table>
  </td>
  <!-- Fix 18536 fine -->
  </tr>
    <!--Fix 15250 inizio-->
   <tr><td> 
   <!-- Fix 32648 Inizio -->
   <!-- <iframe  id="KeyPad" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"       style="display:none; position: absolute; left: 120; top:300; width:107; height:155;" src="it/thera/thip/produzione/raccoltaDati/KeyPad.jsp"></iframe> -->
   <iframe frameborder="0" id="KeyPad" marginheight="0" marginwidth="0" scrolling="no" src="it/thera/thip/produzione/raccoltaDati/KeyPad.jsp" style="display:none; position: absolute; left: 120; top:300; width:152; height:160;"></iframe>
   <!-- Fix 32648 Fine -->
</td>
  </tr><!--Fix 15250 fine-->
  <tr>
    <td colspan="3"><label class="labelError" id="ErroriList"></label></td>
  </tr>
</table>
<%
  RilevDatiPrdTSForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = RilevDatiPrdTSForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", RilevDatiPrdTSBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              RilevDatiPrdTSForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, RilevDatiPrdTSBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, RilevDatiPrdTSBODC.getErrorList().getErrors()); 
           if(RilevDatiPrdTSBODC.getConflict() != null) 
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
     if(RilevDatiPrdTSBODC != null && !RilevDatiPrdTSBODC.close(false)) 
        errors.addAll(0, RilevDatiPrdTSBODC.getErrorList().getErrors()); 
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
     String errorPage = RilevDatiPrdTSForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", RilevDatiPrdTSBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = RilevDatiPrdTSForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
