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
  BODataCollector YDifettositaBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YDifettositaForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YDifettositaForm", "YDifettosita", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/testori/thip/magazzino/generalemag/YDifettosita.js"); 
  YDifettositaForm.setServletEnvironment(se); 
  YDifettositaForm.setJSTypeList(jsList); 
  YDifettositaForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YDifettositaForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YDifettositaForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YDifettositaForm.getMode(); 
  String key = YDifettositaForm.getKey(); 
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
        YDifettositaForm.outTraceInfo(getClass().getName()); 
        String collectorName = YDifettositaForm.findBODataCollectorName(); 
                YDifettositaBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YDifettositaBODC instanceof WebDataCollector) 
            ((WebDataCollector)YDifettositaBODC).setServletEnvironment(se); 
        YDifettositaBODC.initialize("YDifettosita", true, 0); 
        YDifettositaForm.setBODataCollector(YDifettositaBODC); 
        int rcBODC = YDifettositaForm.initSecurityServices(); 
        mode = YDifettositaForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YDifettositaForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YDifettositaBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YDifettositaForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YDifettositaForm); 
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
  myToolBarTB.setParent(YDifettositaForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YDifettositaForm.getBodyOnBeforeUnload()%>" onload="<%=YDifettositaForm.getBodyOnLoad()%>" onunload="<%=YDifettositaForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YDifettositaForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YDifettositaForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YDifettositaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YDifettositaForm.getServlet()%>" method="post" name="YDifettositaForm" style="height:100%"><%
  YDifettositaForm.writeFormStartElements(out); 
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
  WebTextInput YDifettositaIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YDifettosita", "IdAzienda"); 
  YDifettositaIdAzienda.setParent(YDifettositaForm); 
%>
<input class="<%=YDifettositaIdAzienda.getClassType()%>" id="<%=YDifettositaIdAzienda.getId()%>" maxlength="<%=YDifettositaIdAzienda.getMaxLength()%>" name="<%=YDifettositaIdAzienda.getName()%>" size="<%=YDifettositaIdAzienda.getSize()%>" type="hidden"><% 
  YDifettositaIdAzienda.write(out); 
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
  mytabbed.setParent(YDifettositaForm); 
 mytabbed.addTab("tab1", "it.testori.thip.magazzino.generalemag.resources.YDifettosita", "tab1", "YDifettosita", null, null, null, null); 
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
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDifettosita", "Codice", null); 
   label.setParent(YDifettositaForm); 
%><label class="<%=label.getClassType()%>" for="Codice"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDifettositaCodice =  
     new com.thera.thermfw.web.WebTextInput("YDifettosita", "Codice"); 
  YDifettositaCodice.setParent(YDifettositaForm); 
%>
<input class="<%=YDifettositaCodice.getClassType()%>" id="<%=YDifettositaCodice.getId()%>" maxlength="<%=YDifettositaCodice.getMaxLength()%>" name="<%=YDifettositaCodice.getName()%>" size="<%=YDifettositaCodice.getSize()%>"><% 
  YDifettositaCodice.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDifettosita", "Descrizione", null); 
   label.setParent(YDifettositaForm); 
%><label class="<%=label.getClassType()%>" for="Descrizione"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDifettositaDescrizione =  
     new com.thera.thermfw.web.WebTextInput("YDifettosita", "Descrizione"); 
  YDifettositaDescrizione.setParent(YDifettositaForm); 
%>
<input class="<%=YDifettositaDescrizione.getClassType()%>" id="<%=YDifettositaDescrizione.getId()%>" maxlength="<%=YDifettositaDescrizione.getMaxLength()%>" name="<%=YDifettositaDescrizione.getName()%>" size="<%=YDifettositaDescrizione.getSize()%>"><% 
  YDifettositaDescrizione.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
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
  errorList.setParent(YDifettositaForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YDifettositaForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YDifettositaForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YDifettositaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YDifettositaForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YDifettositaBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YDifettositaBODC.getErrorList().getErrors()); 
           if(YDifettositaBODC.getConflict() != null) 
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
     if(YDifettositaBODC != null && !YDifettositaBODC.close(false)) 
        errors.addAll(0, YDifettositaBODC.getErrorList().getErrors()); 
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
     String errorPage = YDifettositaForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YDifettositaBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YDifettositaForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
