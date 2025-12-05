<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///W:\PthDev\Projects\Panthera\Testori\WebContent\dtd/xhtml1-transitional.dtd">
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
  BODataCollector YVariabiliApplicazioneBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YVariabiliApplicazioneForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YVariabiliApplicazioneForm", "YVariabiliApplicazione", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/testori/thip/base/generale/YVariabiliApplicazione.js"); 
  YVariabiliApplicazioneForm.setServletEnvironment(se); 
  YVariabiliApplicazioneForm.setJSTypeList(jsList); 
  YVariabiliApplicazioneForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YVariabiliApplicazioneForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YVariabiliApplicazioneForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YVariabiliApplicazioneForm.getMode(); 
  String key = YVariabiliApplicazioneForm.getKey(); 
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
        YVariabiliApplicazioneForm.outTraceInfo(getClass().getName()); 
        String collectorName = YVariabiliApplicazioneForm.findBODataCollectorName(); 
                YVariabiliApplicazioneBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YVariabiliApplicazioneBODC instanceof WebDataCollector) 
            ((WebDataCollector)YVariabiliApplicazioneBODC).setServletEnvironment(se); 
        YVariabiliApplicazioneBODC.initialize("YVariabiliApplicazione", true, 0); 
        YVariabiliApplicazioneForm.setBODataCollector(YVariabiliApplicazioneBODC); 
        int rcBODC = YVariabiliApplicazioneForm.initSecurityServices(); 
        mode = YVariabiliApplicazioneForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YVariabiliApplicazioneForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YVariabiliApplicazioneBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YVariabiliApplicazioneForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YVariabiliApplicazioneForm); 
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
  myToolBarTB.setParent(YVariabiliApplicazioneForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YVariabiliApplicazioneForm.getBodyOnBeforeUnload()%>" onload="<%=YVariabiliApplicazioneForm.getBodyOnLoad()%>" onunload="<%=YVariabiliApplicazioneForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YVariabiliApplicazioneForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YVariabiliApplicazioneForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YVariabiliApplicazioneBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YVariabiliApplicazioneForm.getServlet()%>" method="post" name="YVariabiliApplicazioneForm" style="height:100%"><%
  YVariabiliApplicazioneForm.writeFormStartElements(out); 
%>

      <table cellpadding="0" cellspacing="0" height="100%" id="emptyborder" width="100%">
        <tr>
          <td style="height:0">
            <% menuBar.writeElements(out); %> 

          </td>
        </tr>
        <tr>
          <td style="height:0">
            <% myToolBarTB.writeChildren(out); %> 

          </td>
        </tr>
        <tr>
          <td>
            <% 
  WebTextInput YVariabiliApplicazioneIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YVariabiliApplicazione", "IdAzienda"); 
  YVariabiliApplicazioneIdAzienda.setParent(YVariabiliApplicazioneForm); 
%>
<input class="<%=YVariabiliApplicazioneIdAzienda.getClassType()%>" id="<%=YVariabiliApplicazioneIdAzienda.getId()%>" maxlength="<%=YVariabiliApplicazioneIdAzienda.getMaxLength()%>" name="<%=YVariabiliApplicazioneIdAzienda.getName()%>" size="<%=YVariabiliApplicazioneIdAzienda.getSize()%>" type="hidden"><% 
  YVariabiliApplicazioneIdAzienda.write(out); 
%>

          </td>
        </tr>
        <tr>
          <td height="100%">
            <!--<span class="tabbed" id="mytabbed">-->
<table width="100%" height="100%" cellpadding="0" cellspacing="0" style="padding-right:1px">
   <tr valign="top">
     <td><% 
  WebTabbed mytabbed = new com.thera.thermfw.web.WebTabbed("mytabbed", "100%", "100%"); 
  mytabbed.setParent(YVariabiliApplicazioneForm); 
 mytabbed.addTab("tab1", "it.testori.thip.base.generale.resources.YVariabiliApplicazione", "tab1", "YVariabiliApplicazione", null, null, null, null); 
 mytabbed.addTab("tabNls", "it.testori.thip.base.generale.resources.YVariabiliApplicazione", "tabNls", "YVariabiliApplicazione", null, null, null, null); 
  mytabbed.write(out); 
%>

     </td>
   </tr>
   <tr>
     <td height="100%"><div class="tabbed_pagine" id="tabbedPagine" style="position: relative; width: 100%; height: 100%;">
              <div class="tabbed_page" id="<%=mytabbed.getTabPageId("tab1")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("tab1"); %>
                <table style="width: 100%;">
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YVariabiliApplicazione", "Codice", null); 
   label.setParent(YVariabiliApplicazioneForm); 
%><label class="<%=label.getClassType()%>" for="Codice"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YVariabiliApplicazioneCodice =  
     new com.thera.thermfw.web.WebTextInput("YVariabiliApplicazione", "Codice"); 
  YVariabiliApplicazioneCodice.setParent(YVariabiliApplicazioneForm); 
%>
<input class="<%=YVariabiliApplicazioneCodice.getClassType()%>" id="<%=YVariabiliApplicazioneCodice.getId()%>" maxlength="<%=YVariabiliApplicazioneCodice.getMaxLength()%>" name="<%=YVariabiliApplicazioneCodice.getName()%>" size="<%=YVariabiliApplicazioneCodice.getSize()%>"><% 
  YVariabiliApplicazioneCodice.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <% 
   request.setAttribute("parentForm", YVariabiliApplicazioneForm); 
   String CDForDescrizione$it$thera$thip$cs$Descrizione$jsp = "Descrizione"; 
%>
<jsp:include page="/it/thera/thip/cs/Descrizione.jsp" flush="true"> 
<jsp:param name="CDName" value="<%=CDForDescrizione$it$thera$thip$cs$Descrizione$jsp%>"/> 
</jsp:include> 
<!--<span class="subform" id="Descrizione"></span>-->
                    </td>
                    <td valign="top">
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YVariabiliApplicazione", "Valore", null); 
   label.setParent(YVariabiliApplicazioneForm); 
%><label class="<%=label.getClassType()%>" for="Valore"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YVariabiliApplicazioneValore =  
     new com.thera.thermfw.web.WebTextInput("YVariabiliApplicazione", "Valore"); 
  YVariabiliApplicazioneValore.setParent(YVariabiliApplicazioneForm); 
%>
<input class="<%=YVariabiliApplicazioneValore.getClassType()%>" id="<%=YVariabiliApplicazioneValore.getId()%>" maxlength="<%=YVariabiliApplicazioneValore.getMaxLength()%>" name="<%=YVariabiliApplicazioneValore.getName()%>" size="<%=YVariabiliApplicazioneValore.getSize()%>"><% 
  YVariabiliApplicazioneValore.write(out); 
%>

                    </td>
                  </tr>
                </table>
              <% mytabbed.endTab(); %> 
</div>
              <div class="tabbed_page" id="<%=mytabbed.getTabPageId("tabNls")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("tabNls"); %>
                <table style="width: 100%;">
                  <tr>
                    <td valign="top">
                      <% 
   request.setAttribute("parentForm", YVariabiliApplicazioneForm); 
   String CDForDescrizione$it$thera$thip$cs$DescrizioneInLingua$jsp = "Descrizione"; 
%>
<jsp:include page="/it/thera/thip/cs/DescrizioneInLingua.jsp" flush="true"> 
<jsp:param name="CDName" value="<%=CDForDescrizione$it$thera$thip$cs$DescrizioneInLingua$jsp%>"/> 
</jsp:include> 
<!--<span class="subform" id="DescrizioneNLS"></span>-->
                    </td>
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
          <td style="height:0">
            <% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(YVariabiliApplicazioneForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YVariabiliApplicazioneForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YVariabiliApplicazioneForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YVariabiliApplicazioneBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YVariabiliApplicazioneForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YVariabiliApplicazioneBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YVariabiliApplicazioneBODC.getErrorList().getErrors()); 
           if(YVariabiliApplicazioneBODC.getConflict() != null) 
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
     if(YVariabiliApplicazioneBODC != null && !YVariabiliApplicazioneBODC.close(false)) 
        errors.addAll(0, YVariabiliApplicazioneBODC.getErrorList().getErrors()); 
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
     String errorPage = YVariabiliApplicazioneForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YVariabiliApplicazioneBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YVariabiliApplicazioneForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
