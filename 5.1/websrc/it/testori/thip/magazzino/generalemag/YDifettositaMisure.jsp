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
  BODataCollector YDifettositaMisureBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YDifettositaMisureForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YDifettositaMisureForm", "YDifettositaMisure", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/testori/thip/magazzino/generalemag/YDifettositaMisure.js"); 
  YDifettositaMisureForm.setServletEnvironment(se); 
  YDifettositaMisureForm.setJSTypeList(jsList); 
  YDifettositaMisureForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YDifettositaMisureForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YDifettositaMisureForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YDifettositaMisureForm.getMode(); 
  String key = YDifettositaMisureForm.getKey(); 
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
        YDifettositaMisureForm.outTraceInfo(getClass().getName()); 
        String collectorName = YDifettositaMisureForm.findBODataCollectorName(); 
                YDifettositaMisureBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YDifettositaMisureBODC instanceof WebDataCollector) 
            ((WebDataCollector)YDifettositaMisureBODC).setServletEnvironment(se); 
        YDifettositaMisureBODC.initialize("YDifettositaMisure", true, 0); 
        YDifettositaMisureForm.setBODataCollector(YDifettositaMisureBODC); 
        int rcBODC = YDifettositaMisureForm.initSecurityServices(); 
        mode = YDifettositaMisureForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YDifettositaMisureForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YDifettositaMisureBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YDifettositaMisureForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YDifettositaMisureForm); 
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
  myToolBarTB.setParent(YDifettositaMisureForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YDifettositaMisureForm.getBodyOnBeforeUnload()%>" onload="<%=YDifettositaMisureForm.getBodyOnLoad()%>" onunload="<%=YDifettositaMisureForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YDifettositaMisureForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YDifettositaMisureForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YDifettositaMisureBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YDifettositaMisureForm.getServlet()%>" method="post" name="YDifettositaMisureForm" style="height:100%"><%
  YDifettositaMisureForm.writeFormStartElements(out); 
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
  WebTextInput YDifettositaMisureIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YDifettositaMisure", "IdAzienda"); 
  YDifettositaMisureIdAzienda.setParent(YDifettositaMisureForm); 
%>
<input class="<%=YDifettositaMisureIdAzienda.getClassType()%>" id="<%=YDifettositaMisureIdAzienda.getId()%>" maxlength="<%=YDifettositaMisureIdAzienda.getMaxLength()%>" name="<%=YDifettositaMisureIdAzienda.getName()%>" size="<%=YDifettositaMisureIdAzienda.getSize()%>" type="hidden"><% 
  YDifettositaMisureIdAzienda.write(out); 
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
  mytabbed.setParent(YDifettositaMisureForm); 
 mytabbed.addTab("tab1", "it.testori.thip.magazzino.generalemag.resources.YDifettositaMisure", "tab1", "YDifettositaMisure", null, null, null, null); 
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
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDifettositaMisure", "Codice", null); 
   label.setParent(YDifettositaMisureForm); 
%><label class="<%=label.getClassType()%>" for="Codice"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDifettositaMisureCodice =  
     new com.thera.thermfw.web.WebTextInput("YDifettositaMisure", "Codice"); 
  YDifettositaMisureCodice.setParent(YDifettositaMisureForm); 
%>
<input class="<%=YDifettositaMisureCodice.getClassType()%>" id="<%=YDifettositaMisureCodice.getId()%>" maxlength="<%=YDifettositaMisureCodice.getMaxLength()%>" name="<%=YDifettositaMisureCodice.getName()%>" size="<%=YDifettositaMisureCodice.getSize()%>"><% 
  YDifettositaMisureCodice.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDifettositaMisure", "Descrizione", null); 
   label.setParent(YDifettositaMisureForm); 
%><label class="<%=label.getClassType()%>" for="Descrizione"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDifettositaMisureDescrizione =  
     new com.thera.thermfw.web.WebTextInput("YDifettositaMisure", "Descrizione"); 
  YDifettositaMisureDescrizione.setParent(YDifettositaMisureForm); 
%>
<input class="<%=YDifettositaMisureDescrizione.getClassType()%>" id="<%=YDifettositaMisureDescrizione.getId()%>" maxlength="<%=YDifettositaMisureDescrizione.getMaxLength()%>" name="<%=YDifettositaMisureDescrizione.getName()%>" size="<%=YDifettositaMisureDescrizione.getSize()%>"><% 
  YDifettositaMisureDescrizione.write(out); 
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
  errorList.setParent(YDifettositaMisureForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YDifettositaMisureForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YDifettositaMisureForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YDifettositaMisureBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YDifettositaMisureForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YDifettositaMisureBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YDifettositaMisureBODC.getErrorList().getErrors()); 
           if(YDifettositaMisureBODC.getConflict() != null) 
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
     if(YDifettositaMisureBODC != null && !YDifettositaMisureBODC.close(false)) 
        errors.addAll(0, YDifettositaMisureBODC.getErrorList().getErrors()); 
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
     String errorPage = YDifettositaMisureForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YDifettositaMisureBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YDifettositaMisureForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
