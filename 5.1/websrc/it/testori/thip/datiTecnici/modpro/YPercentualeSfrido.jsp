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
  BODataCollector YPercentualeSfridoBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YPercentualeSfridoForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YPercentualeSfridoForm", "YPercentualeSfrido", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/testori/thip/datiTecnici/modpro/YPercentualeSfrido.js"); 
  YPercentualeSfridoForm.setServletEnvironment(se); 
  YPercentualeSfridoForm.setJSTypeList(jsList); 
  YPercentualeSfridoForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YPercentualeSfridoForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YPercentualeSfridoForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YPercentualeSfridoForm.getMode(); 
  String key = YPercentualeSfridoForm.getKey(); 
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
        YPercentualeSfridoForm.outTraceInfo(getClass().getName()); 
        String collectorName = YPercentualeSfridoForm.findBODataCollectorName(); 
                YPercentualeSfridoBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YPercentualeSfridoBODC instanceof WebDataCollector) 
            ((WebDataCollector)YPercentualeSfridoBODC).setServletEnvironment(se); 
        YPercentualeSfridoBODC.initialize("YPercentualeSfrido", true, 0); 
        YPercentualeSfridoForm.setBODataCollector(YPercentualeSfridoBODC); 
        int rcBODC = YPercentualeSfridoForm.initSecurityServices(); 
        mode = YPercentualeSfridoForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YPercentualeSfridoForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YPercentualeSfridoBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YPercentualeSfridoForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YPercentualeSfridoForm); 
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
  myToolBarTB.setParent(YPercentualeSfridoForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YPercentualeSfridoForm.getBodyOnBeforeUnload()%>" onload="<%=YPercentualeSfridoForm.getBodyOnLoad()%>" onunload="<%=YPercentualeSfridoForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YPercentualeSfridoForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YPercentualeSfridoForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YPercentualeSfridoBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YPercentualeSfridoForm.getServlet()%>" method="post" name="YPercentualeSfridoForm" style="height:100%"><%
  YPercentualeSfridoForm.writeFormStartElements(out); 
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
  WebTextInput YPercentualeSfridoIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YPercentualeSfrido", "IdAzienda"); 
  YPercentualeSfridoIdAzienda.setParent(YPercentualeSfridoForm); 
%>
<input class="<%=YPercentualeSfridoIdAzienda.getClassType()%>" id="<%=YPercentualeSfridoIdAzienda.getId()%>" maxlength="<%=YPercentualeSfridoIdAzienda.getMaxLength()%>" name="<%=YPercentualeSfridoIdAzienda.getName()%>" size="<%=YPercentualeSfridoIdAzienda.getSize()%>" type="hidden"><% 
  YPercentualeSfridoIdAzienda.write(out); 
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
  mytabbed.setParent(YPercentualeSfridoForm); 
 mytabbed.addTab("tab1", "it.testori.thip.datiTecnici.modpro.resources.YPercentualeSfrido", "tab1", "YPercentualeSfrido", null, null, null, null); 
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
                    </td>
                    <td valign="top">
                    </td>
                    <td valign="top">
                    </td>
                    <td valign="top">
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPercentualeSfrido", "Lunghezza", null); 
   label.setParent(YPercentualeSfridoForm); 
%><label class="<%=label.getClassType()%>" for="Lunghezza"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YPercentualeSfridoLunghezza =  
     new com.thera.thermfw.web.WebTextInput("YPercentualeSfrido", "Lunghezza"); 
  YPercentualeSfridoLunghezza.setParent(YPercentualeSfridoForm); 
%>
<input class="<%=YPercentualeSfridoLunghezza.getClassType()%>" id="<%=YPercentualeSfridoLunghezza.getId()%>" maxlength="<%=YPercentualeSfridoLunghezza.getMaxLength()%>" name="<%=YPercentualeSfridoLunghezza.getName()%>" size="<%=YPercentualeSfridoLunghezza.getSize()%>"><% 
  YPercentualeSfridoLunghezza.write(out); 
%>

                    </td>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPercentualeSfrido", "Percentuale", null); 
   label.setParent(YPercentualeSfridoForm); 
%><label class="<%=label.getClassType()%>" for="Percentuale"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YPercentualeSfridoPercentuale =  
     new com.thera.thermfw.web.WebTextInput("YPercentualeSfrido", "Percentuale"); 
  YPercentualeSfridoPercentuale.setParent(YPercentualeSfridoForm); 
%>
<input class="<%=YPercentualeSfridoPercentuale.getClassType()%>" id="<%=YPercentualeSfridoPercentuale.getId()%>" maxlength="<%=YPercentualeSfridoPercentuale.getMaxLength()%>" name="<%=YPercentualeSfridoPercentuale.getName()%>" size="<%=YPercentualeSfridoPercentuale.getSize()%>"><% 
  YPercentualeSfridoPercentuale.write(out); 
%>

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
  errorList.setParent(YPercentualeSfridoForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YPercentualeSfridoForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YPercentualeSfridoForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YPercentualeSfridoBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YPercentualeSfridoForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YPercentualeSfridoBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YPercentualeSfridoBODC.getErrorList().getErrors()); 
           if(YPercentualeSfridoBODC.getConflict() != null) 
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
     if(YPercentualeSfridoBODC != null && !YPercentualeSfridoBODC.close(false)) 
        errors.addAll(0, YPercentualeSfridoBODC.getErrorList().getErrors()); 
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
     String errorPage = YPercentualeSfridoForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YPercentualeSfridoBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YPercentualeSfridoForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
