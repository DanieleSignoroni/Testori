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
  BODataCollector AssocHdrTpDocDgtBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm AssocHdrTpDocDgtForm =  
     new com.thera.thermfw.web.WebForm(request, response, "AssocHdrTpDocDgtForm", "AssocHdrTpDocDgt", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/testori/thip/base/generale/AssocHdrTpDocDgt.js"); 
  AssocHdrTpDocDgtForm.setServletEnvironment(se); 
  AssocHdrTpDocDgtForm.setJSTypeList(jsList); 
  AssocHdrTpDocDgtForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  AssocHdrTpDocDgtForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  AssocHdrTpDocDgtForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = AssocHdrTpDocDgtForm.getMode(); 
  String key = AssocHdrTpDocDgtForm.getKey(); 
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
        AssocHdrTpDocDgtForm.outTraceInfo(getClass().getName()); 
        String collectorName = AssocHdrTpDocDgtForm.findBODataCollectorName(); 
                AssocHdrTpDocDgtBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (AssocHdrTpDocDgtBODC instanceof WebDataCollector) 
            ((WebDataCollector)AssocHdrTpDocDgtBODC).setServletEnvironment(se); 
        AssocHdrTpDocDgtBODC.initialize("AssocHdrTpDocDgt", true, 0); 
        AssocHdrTpDocDgtForm.setBODataCollector(AssocHdrTpDocDgtBODC); 
        int rcBODC = AssocHdrTpDocDgtForm.initSecurityServices(); 
        mode = AssocHdrTpDocDgtForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           AssocHdrTpDocDgtForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = AssocHdrTpDocDgtBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              AssocHdrTpDocDgtForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(AssocHdrTpDocDgtForm); 
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
  myToolBarTB.setParent(AssocHdrTpDocDgtForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=AssocHdrTpDocDgtForm.getBodyOnBeforeUnload()%>" onload="<%=AssocHdrTpDocDgtForm.getBodyOnLoad()%>" onunload="<%=AssocHdrTpDocDgtForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   AssocHdrTpDocDgtForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = AssocHdrTpDocDgtForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", AssocHdrTpDocDgtBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=AssocHdrTpDocDgtForm.getServlet()%>" method="post" name="AssocHdrTpDocDgtForm" style="height:100%"><%
  AssocHdrTpDocDgtForm.writeFormStartElements(out); 
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
  mytabbed.setParent(AssocHdrTpDocDgtForm); 
 mytabbed.addTab("tab1", "it.testori.thip.base.generale.resources.AssocHdrTpDocDgt", "tab1", "AssocHdrTpDocDgt", null, null, null, null); 
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
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AssocHdrTpDocDgt", "IdClassHdr", null); 
   label.setParent(AssocHdrTpDocDgtForm); 
%><label class="<%=label.getClassType()%>" for="Classe"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm AssocHdrTpDocDgtClasse =  
     new com.thera.thermfw.web.WebMultiSearchForm("AssocHdrTpDocDgt", "Classe", false, false, true, 1, null, null); 
  AssocHdrTpDocDgtClasse.setParent(AssocHdrTpDocDgtForm); 
  AssocHdrTpDocDgtClasse.write(out); 
%>
<!--<span class="multisearchform" id="Classe"></span>-->
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "AssocHdrTpDocDgt", "IdTipoDocumentoDigitale", null); 
   label.setParent(AssocHdrTpDocDgtForm); 
%><label class="<%=label.getClassType()%>" for="TipoDocDgt"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm AssocHdrTpDocDgtTipoDocDgt =  
     new com.thera.thermfw.web.WebMultiSearchForm("AssocHdrTpDocDgt", "TipoDocDgt", false, false, true, 1, null, null); 
  AssocHdrTpDocDgtTipoDocDgt.setParent(AssocHdrTpDocDgtForm); 
  AssocHdrTpDocDgtTipoDocDgt.write(out); 
%>
<!--<span class="multisearchform" id="TipoDocDgt"></span>-->
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <% 
  WebCheckBox AssocHdrTpDocDgtDefCompilation =  
     new com.thera.thermfw.web.WebCheckBox("AssocHdrTpDocDgt", "DefCompilation"); 
  AssocHdrTpDocDgtDefCompilation.setParent(AssocHdrTpDocDgtForm); 
%>
<input id="<%=AssocHdrTpDocDgtDefCompilation.getId()%>" name="<%=AssocHdrTpDocDgtDefCompilation.getName()%>" type="checkbox" value="Y"><%
  AssocHdrTpDocDgtDefCompilation.write(out); 
%>

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
  errorList.setParent(AssocHdrTpDocDgtForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  AssocHdrTpDocDgtForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = AssocHdrTpDocDgtForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", AssocHdrTpDocDgtBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              AssocHdrTpDocDgtForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, AssocHdrTpDocDgtBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, AssocHdrTpDocDgtBODC.getErrorList().getErrors()); 
           if(AssocHdrTpDocDgtBODC.getConflict() != null) 
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
     if(AssocHdrTpDocDgtBODC != null && !AssocHdrTpDocDgtBODC.close(false)) 
        errors.addAll(0, AssocHdrTpDocDgtBODC.getErrorList().getErrors()); 
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
     String errorPage = AssocHdrTpDocDgtForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", AssocHdrTpDocDgtBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = AssocHdrTpDocDgtForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
