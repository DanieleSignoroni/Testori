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
  BODataCollector YAllegatiOrdVenRigPrmBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YAllegatiOrdVenRigPrmForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YAllegatiOrdVenRigPrmForm", "YAllegatiOrdVenRigPrm", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/testori/thip/vendite/ordineVE/YAllegatiOrdVenRigPrm.js"); 
  YAllegatiOrdVenRigPrmForm.setServletEnvironment(se); 
  YAllegatiOrdVenRigPrmForm.setJSTypeList(jsList); 
  YAllegatiOrdVenRigPrmForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YAllegatiOrdVenRigPrmForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YAllegatiOrdVenRigPrmForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YAllegatiOrdVenRigPrmForm.getMode(); 
  String key = YAllegatiOrdVenRigPrmForm.getKey(); 
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
        YAllegatiOrdVenRigPrmForm.outTraceInfo(getClass().getName()); 
        String collectorName = YAllegatiOrdVenRigPrmForm.findBODataCollectorName(); 
                YAllegatiOrdVenRigPrmBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YAllegatiOrdVenRigPrmBODC instanceof WebDataCollector) 
            ((WebDataCollector)YAllegatiOrdVenRigPrmBODC).setServletEnvironment(se); 
        YAllegatiOrdVenRigPrmBODC.initialize("YAllegatiOrdVenRigPrm", true, 0); 
        YAllegatiOrdVenRigPrmForm.setBODataCollector(YAllegatiOrdVenRigPrmBODC); 
        int rcBODC = YAllegatiOrdVenRigPrmForm.initSecurityServices(); 
        mode = YAllegatiOrdVenRigPrmForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YAllegatiOrdVenRigPrmForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YAllegatiOrdVenRigPrmBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YAllegatiOrdVenRigPrmForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YAllegatiOrdVenRigPrmForm); 
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
  myToolBarTB.setParent(YAllegatiOrdVenRigPrmForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YAllegatiOrdVenRigPrmForm.getBodyOnBeforeUnload()%>" onload="<%=YAllegatiOrdVenRigPrmForm.getBodyOnLoad()%>" onunload="<%=YAllegatiOrdVenRigPrmForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YAllegatiOrdVenRigPrmForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YAllegatiOrdVenRigPrmForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YAllegatiOrdVenRigPrmBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YAllegatiOrdVenRigPrmForm.getServlet()%>" method="post" name="YAllegatiOrdVenRigPrmForm" style="height:100%"><%
  YAllegatiOrdVenRigPrmForm.writeFormStartElements(out); 
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
  WebTextInput YAllegatiOrdVenRigPrmAnnoDocumento =  
     new com.thera.thermfw.web.WebTextInput("YAllegatiOrdVenRigPrm", "AnnoDocumento"); 
  YAllegatiOrdVenRigPrmAnnoDocumento.setParent(YAllegatiOrdVenRigPrmForm); 
%>
<input class="<%=YAllegatiOrdVenRigPrmAnnoDocumento.getClassType()%>" id="<%=YAllegatiOrdVenRigPrmAnnoDocumento.getId()%>" maxlength="<%=YAllegatiOrdVenRigPrmAnnoDocumento.getMaxLength()%>" name="<%=YAllegatiOrdVenRigPrmAnnoDocumento.getName()%>" size="<%=YAllegatiOrdVenRigPrmAnnoDocumento.getSize()%>" type="hidden"><% 
  YAllegatiOrdVenRigPrmAnnoDocumento.write(out); 
%>

          </td>
        </tr>
        <tr>
          <td>
            <% 
  WebTextInput YAllegatiOrdVenRigPrmNumeroDocumento =  
     new com.thera.thermfw.web.WebTextInput("YAllegatiOrdVenRigPrm", "NumeroDocumento"); 
  YAllegatiOrdVenRigPrmNumeroDocumento.setParent(YAllegatiOrdVenRigPrmForm); 
%>
<input class="<%=YAllegatiOrdVenRigPrmNumeroDocumento.getClassType()%>" id="<%=YAllegatiOrdVenRigPrmNumeroDocumento.getId()%>" maxlength="<%=YAllegatiOrdVenRigPrmNumeroDocumento.getMaxLength()%>" name="<%=YAllegatiOrdVenRigPrmNumeroDocumento.getName()%>" size="<%=YAllegatiOrdVenRigPrmNumeroDocumento.getSize()%>" type="hidden"><% 
  YAllegatiOrdVenRigPrmNumeroDocumento.write(out); 
%>

          </td>
        </tr>
        <tr>
          <td>
            <% 
  WebTextInput YAllegatiOrdVenRigPrmNumeroRigaDocumento =  
     new com.thera.thermfw.web.WebTextInput("YAllegatiOrdVenRigPrm", "NumeroRigaDocumento"); 
  YAllegatiOrdVenRigPrmNumeroRigaDocumento.setParent(YAllegatiOrdVenRigPrmForm); 
%>
<input class="<%=YAllegatiOrdVenRigPrmNumeroRigaDocumento.getClassType()%>" id="<%=YAllegatiOrdVenRigPrmNumeroRigaDocumento.getId()%>" maxlength="<%=YAllegatiOrdVenRigPrmNumeroRigaDocumento.getMaxLength()%>" name="<%=YAllegatiOrdVenRigPrmNumeroRigaDocumento.getName()%>" size="<%=YAllegatiOrdVenRigPrmNumeroRigaDocumento.getSize()%>" type="hidden"><% 
  YAllegatiOrdVenRigPrmNumeroRigaDocumento.write(out); 
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
  mytabbed.setParent(YAllegatiOrdVenRigPrmForm); 
 mytabbed.addTab("tab1", "it.testori.thip.vendite.ordineVE.resources.YAllegatiOrdVenRigPrm", "tab1", "YAllegatiOrdVenRigPrm", null, null, null, null); 
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
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YAllegatiOrdVenRigPrm", "IdTipoDocDgt", null); 
   label.setParent(YAllegatiOrdVenRigPrmForm); 
%><label class="<%=label.getClassType()%>" for="AssociazioneDocumento"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YAllegatiOrdVenRigPrmAssociazioneDocumento =  
     new com.thera.thermfw.web.WebMultiSearchForm("YAllegatiOrdVenRigPrm", "AssociazioneDocumento", false, false, true, 2, null, null); 
  YAllegatiOrdVenRigPrmAssociazioneDocumento.setParent(YAllegatiOrdVenRigPrmForm); 
  YAllegatiOrdVenRigPrmAssociazioneDocumento.write(out); 
%>
<!--<span class="multisearchform" id="AssociazioneDocumento"></span>-->
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YAllegatiOrdVenRigPrm", "Vincolo", null); 
   label.setParent(YAllegatiOrdVenRigPrmForm); 
%><label class="<%=label.getClassType()%>" for="Vincolo"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebComboBox YAllegatiOrdVenRigPrmVincolo =  
     new com.thera.thermfw.web.WebComboBox("YAllegatiOrdVenRigPrm", "Vincolo", null); 
  YAllegatiOrdVenRigPrmVincolo.setParent(YAllegatiOrdVenRigPrmForm); 
%>
<select id="<%=YAllegatiOrdVenRigPrmVincolo.getId()%>" name="<%=YAllegatiOrdVenRigPrmVincolo.getName()%>"><% 
  YAllegatiOrdVenRigPrmVincolo.write(out); 
%> 
</select>
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                    </td>
                    <td valign="top">
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <% 
   request.setAttribute("parentForm", YAllegatiOrdVenRigPrmForm); 
   String CDForDatiComuniEstesi$it$thera$thip$cs$DatiComuniEstesi$jsp = "DatiComuniEstesi"; 
%>
<jsp:include page="/it/thera/thip/cs/DatiComuniEstesi.jsp" flush="true"> 
<jsp:param name="CDName" value="<%=CDForDatiComuniEstesi$it$thera$thip$cs$DatiComuniEstesi$jsp%>"/> 
</jsp:include> 
<!--<span class="subform" id="DatiComuniEstesi"></span>-->
                    </td>
                    <td valign="top">
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
  errorList.setParent(YAllegatiOrdVenRigPrmForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YAllegatiOrdVenRigPrmForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YAllegatiOrdVenRigPrmForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YAllegatiOrdVenRigPrmBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YAllegatiOrdVenRigPrmForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YAllegatiOrdVenRigPrmBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YAllegatiOrdVenRigPrmBODC.getErrorList().getErrors()); 
           if(YAllegatiOrdVenRigPrmBODC.getConflict() != null) 
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
     if(YAllegatiOrdVenRigPrmBODC != null && !YAllegatiOrdVenRigPrmBODC.close(false)) 
        errors.addAll(0, YAllegatiOrdVenRigPrmBODC.getErrorList().getErrors()); 
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
     String errorPage = YAllegatiOrdVenRigPrmForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YAllegatiOrdVenRigPrmBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YAllegatiOrdVenRigPrmForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
