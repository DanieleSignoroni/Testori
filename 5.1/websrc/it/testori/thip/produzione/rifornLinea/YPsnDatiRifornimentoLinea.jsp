<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///K:/Thip/5.1.0/websrcsvil/dtd/xhtml1-transitional.dtd">
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
  BODataCollector YPsnDatiRifornimentoLineaBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YPsnDatiRifornimentoLineaForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YPsnDatiRifornimentoLineaForm", "YPsnDatiRifornimentoLinea", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/testori/thip/produzione/rifornLinea/YPsnDatiRifornimentoLinea.js"); 
  YPsnDatiRifornimentoLineaForm.setServletEnvironment(se); 
  YPsnDatiRifornimentoLineaForm.setJSTypeList(jsList); 
  YPsnDatiRifornimentoLineaForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YPsnDatiRifornimentoLineaForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YPsnDatiRifornimentoLineaForm.setWebFormModifierClass("it.testori.thip.produzione.rifornLinea.web.YPsnDatiRifornimentoLineaFormModifier"); 
  YPsnDatiRifornimentoLineaForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YPsnDatiRifornimentoLineaForm.getMode(); 
  String key = YPsnDatiRifornimentoLineaForm.getKey(); 
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
        YPsnDatiRifornimentoLineaForm.outTraceInfo(getClass().getName()); 
        String collectorName = YPsnDatiRifornimentoLineaForm.findBODataCollectorName(); 
                YPsnDatiRifornimentoLineaBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YPsnDatiRifornimentoLineaBODC instanceof WebDataCollector) 
            ((WebDataCollector)YPsnDatiRifornimentoLineaBODC).setServletEnvironment(se); 
        YPsnDatiRifornimentoLineaBODC.initialize("YPsnDatiRifornimentoLinea", true, 0); 
        YPsnDatiRifornimentoLineaForm.setBODataCollector(YPsnDatiRifornimentoLineaBODC); 
        int rcBODC = YPsnDatiRifornimentoLineaForm.initSecurityServices(); 
        mode = YPsnDatiRifornimentoLineaForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YPsnDatiRifornimentoLineaForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YPsnDatiRifornimentoLineaBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YPsnDatiRifornimentoLineaForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YPsnDatiRifornimentoLineaForm); 
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
  myToolBarTB.setParent(YPsnDatiRifornimentoLineaForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YPsnDatiRifornimentoLineaForm.getBodyOnBeforeUnload()%>" onload="<%=YPsnDatiRifornimentoLineaForm.getBodyOnLoad()%>" onunload="<%=YPsnDatiRifornimentoLineaForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YPsnDatiRifornimentoLineaForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YPsnDatiRifornimentoLineaForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YPsnDatiRifornimentoLineaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YPsnDatiRifornimentoLineaForm.getServlet()%>" method="post" name="YPsnDatiRifornimentoLineaForm" style="height:100%"><%
  YPsnDatiRifornimentoLineaForm.writeFormStartElements(out); 
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
          <td height="100%">
            <!--<span class="tabbed" id="mytabbed">-->
<table width="100%" height="100%" cellpadding="0" cellspacing="0" style="padding-right:1px">
   <tr valign="top">
     <td><% 
  WebTabbed mytabbed = new com.thera.thermfw.web.WebTabbed("mytabbed", "100%", "100%"); 
  mytabbed.setParent(YPsnDatiRifornimentoLineaForm); 
 mytabbed.addTab("tab1", "it.testori.thip.produzione.rifornLinea.resources.YPsnDatiRifornimentoLinea", "tab1", "YPsnDatiRifornimentoLinea", null, null, null, null); 
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
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPsnDatiRifornimentoLinea", "IdClienteIntestatario", null); 
   label.setParent(YPsnDatiRifornimentoLineaForm); 
%><label class="<%=label.getClassType()%>" for="ClienteIntestatario"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YPsnDatiRifornimentoLineaClienteIntestatario =  
     new com.thera.thermfw.web.WebMultiSearchForm("YPsnDatiRifornimentoLinea", "ClienteIntestatario", false, false, true, 1, null, null); 
  YPsnDatiRifornimentoLineaClienteIntestatario.setParent(YPsnDatiRifornimentoLineaForm); 
  YPsnDatiRifornimentoLineaClienteIntestatario.write(out); 
%>
<!--<span class="multisearchform" id="ClienteIntestatario"></span>-->
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPsnDatiRifornimentoLinea", "IdNumeratoreDocVen", null); 
   label.setParent(YPsnDatiRifornimentoLineaForm); 
%><label class="<%=label.getClassType()%>" for="NumeratoreDocVen"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YPsnDatiRifornimentoLineaNumeratoreDocVen =  
     new com.thera.thermfw.web.WebMultiSearchForm("YPsnDatiRifornimentoLinea", "NumeratoreDocVen", false, false, true, 1, null, null); 
  YPsnDatiRifornimentoLineaNumeratoreDocVen.setParent(YPsnDatiRifornimentoLineaForm); 
  YPsnDatiRifornimentoLineaNumeratoreDocVen.write(out); 
%>
<!--<span class="multisearchform" id="NumeratoreDocVen"></span>-->
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPsnDatiRifornimentoLinea", "IdSerieDocVen", null); 
   label.setParent(YPsnDatiRifornimentoLineaForm); 
%><label class="<%=label.getClassType()%>" for="SerieDocVen"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YPsnDatiRifornimentoLineaSerieDocVen =  
     new com.thera.thermfw.web.WebMultiSearchForm("YPsnDatiRifornimentoLinea", "SerieDocVen", false, false, true, 1, null, null); 
  YPsnDatiRifornimentoLineaSerieDocVen.setParent(YPsnDatiRifornimentoLineaForm); 
  YPsnDatiRifornimentoLineaSerieDocVen.write(out); 
%>
<!--<span class="multisearchform" id="SerieDocVen"></span>-->
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPsnDatiRifornimentoLinea", "IdCausaleDocVen", null); 
   label.setParent(YPsnDatiRifornimentoLineaForm); 
%><label class="<%=label.getClassType()%>" for="CausaleDocVen"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YPsnDatiRifornimentoLineaCausaleDocVen =  
     new com.thera.thermfw.web.WebMultiSearchForm("YPsnDatiRifornimentoLinea", "CausaleDocVen", false, false, true, 1, null, null); 
  YPsnDatiRifornimentoLineaCausaleDocVen.setParent(YPsnDatiRifornimentoLineaForm); 
  YPsnDatiRifornimentoLineaCausaleDocVen.write(out); 
%>
<!--<span class="multisearchform" id="CausaleDocVen"></span>-->
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <% 
   request.setAttribute("parentForm", YPsnDatiRifornimentoLineaForm); 
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
  errorList.setParent(YPsnDatiRifornimentoLineaForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YPsnDatiRifornimentoLineaForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YPsnDatiRifornimentoLineaForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YPsnDatiRifornimentoLineaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YPsnDatiRifornimentoLineaForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YPsnDatiRifornimentoLineaBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YPsnDatiRifornimentoLineaBODC.getErrorList().getErrors()); 
           if(YPsnDatiRifornimentoLineaBODC.getConflict() != null) 
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
     if(YPsnDatiRifornimentoLineaBODC != null && !YPsnDatiRifornimentoLineaBODC.close(false)) 
        errors.addAll(0, YPsnDatiRifornimentoLineaBODC.getErrorList().getErrors()); 
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
     String errorPage = YPsnDatiRifornimentoLineaForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YPsnDatiRifornimentoLineaBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YPsnDatiRifornimentoLineaForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
