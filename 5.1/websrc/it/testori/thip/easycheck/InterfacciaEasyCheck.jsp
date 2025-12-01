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
  BODataCollector InterfacciaEasyCheckBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm InterfacciaEasyCheckForm =  
     new com.thera.thermfw.web.WebForm(request, response, "InterfacciaEasyCheckForm", "InterfacciaEasyCheck", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/testori/thip/easycheck/InterfacciaEasyCheck.js"); 
  InterfacciaEasyCheckForm.setServletEnvironment(se); 
  InterfacciaEasyCheckForm.setJSTypeList(jsList); 
  InterfacciaEasyCheckForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  InterfacciaEasyCheckForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  InterfacciaEasyCheckForm.setWebFormModifierClass("it.testori.thip.easycheck.web.InterfacciaEasyCheckFormModifier"); 
  InterfacciaEasyCheckForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = InterfacciaEasyCheckForm.getMode(); 
  String key = InterfacciaEasyCheckForm.getKey(); 
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
        InterfacciaEasyCheckForm.outTraceInfo(getClass().getName()); 
        String collectorName = InterfacciaEasyCheckForm.findBODataCollectorName(); 
                InterfacciaEasyCheckBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (InterfacciaEasyCheckBODC instanceof WebDataCollector) 
            ((WebDataCollector)InterfacciaEasyCheckBODC).setServletEnvironment(se); 
        InterfacciaEasyCheckBODC.initialize("InterfacciaEasyCheck", true, 0); 
        InterfacciaEasyCheckForm.setBODataCollector(InterfacciaEasyCheckBODC); 
        int rcBODC = InterfacciaEasyCheckForm.initSecurityServices(); 
        mode = InterfacciaEasyCheckForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           InterfacciaEasyCheckForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = InterfacciaEasyCheckBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              InterfacciaEasyCheckForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(InterfacciaEasyCheckForm); 
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
  myToolBarTB.setParent(InterfacciaEasyCheckForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
<body onbeforeunload="<%=InterfacciaEasyCheckForm.getBodyOnBeforeUnload()%>" onload="<%=InterfacciaEasyCheckForm.getBodyOnLoad()%>" onunload="<%=InterfacciaEasyCheckForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   InterfacciaEasyCheckForm.writeBodyStartElements(out); 
%> 

	<table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = InterfacciaEasyCheckForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", InterfacciaEasyCheckBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=InterfacciaEasyCheckForm.getServlet()%>" method="post" name="InterfacciaEasyCheckForm" style="height:100%"><%
  InterfacciaEasyCheckForm.writeFormStartElements(out); 
%>

		<table cellpadding="0" cellspacing="0" height="100%" id="emptyborder" width="100%">
			<tr>
				<td style="height: 0"><% menuBar.writeElements(out); %> 
</td>
			</tr>
			<tr>
				<td style="height: 0"><% myToolBarTB.writeChildren(out); %> 
</td>
			</tr>
			<tr>
				<td><% 
  WebTextInput InterfacciaEasyCheckIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("InterfacciaEasyCheck", "IdAzienda"); 
  InterfacciaEasyCheckIdAzienda.setParent(InterfacciaEasyCheckForm); 
%>
<input class="<%=InterfacciaEasyCheckIdAzienda.getClassType()%>" id="<%=InterfacciaEasyCheckIdAzienda.getId()%>" maxlength="<%=InterfacciaEasyCheckIdAzienda.getMaxLength()%>" name="<%=InterfacciaEasyCheckIdAzienda.getName()%>" size="<%=InterfacciaEasyCheckIdAzienda.getSize()%>" type="hidden"><% 
  InterfacciaEasyCheckIdAzienda.write(out); 
%>
</td>
			</tr>
			<tr>
				<td height="100%"><!--<span class="tabbed" id="mytabbed">-->
<table width="100%" height="100%" cellpadding="0" cellspacing="0" style="padding-right:1px">
   <tr valign="top">
     <td><% 
  WebTabbed mytabbed = new com.thera.thermfw.web.WebTabbed("mytabbed", "100%", "100%"); 
  mytabbed.setParent(InterfacciaEasyCheckForm); 
 mytabbed.addTab("tab1", "it.testori.thip.easycheck.resources.InterfacciaEasyCheck", "tab1", "InterfacciaEasyCheck", null, null, null, null); 
  mytabbed.write(out); 
%>

     </td>
   </tr>
   <tr>
     <td height="100%"><div class="tabbed_pagine" id="tabbedPagine" style="position: relative; width: 100%; height: 100%;"> <div class="tabbed_page" id="<%=mytabbed.getTabPageId("tab1")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("tab1"); %>
							<table style="width: 100%;">
								<tr>
									<td valign="top"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "InterfacciaEasyCheck", "IdNumeratoreDocPrd", null); 
   label.setParent(InterfacciaEasyCheckForm); 
%><label class="<%=label.getClassType()%>" for="NumeratoreDocProd"><%label.write(out);%></label><%}%></td>
									<td valign="top"><% 
  WebMultiSearchForm InterfacciaEasyCheckNumeratoreDocProd =  
     new com.thera.thermfw.web.WebMultiSearchForm("InterfacciaEasyCheck", "NumeratoreDocProd", false, false, true, 1, null, null); 
  InterfacciaEasyCheckNumeratoreDocProd.setParent(InterfacciaEasyCheckForm); 
  InterfacciaEasyCheckNumeratoreDocProd.write(out); 
%>
<!--<span class="multisearchform" id="NumeratoreDocProd"></span>--></td>
								</tr>
								<tr>
									<td valign="top"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "InterfacciaEasyCheck", "IdSerieDocPrd", null); 
   label.setParent(InterfacciaEasyCheckForm); 
%><label class="<%=label.getClassType()%>" for="SerieDocProd"><%label.write(out);%></label><%}%></td>
									<td valign="top"><% 
  WebMultiSearchForm InterfacciaEasyCheckSerieDocProd =  
     new com.thera.thermfw.web.WebMultiSearchForm("InterfacciaEasyCheck", "SerieDocProd", false, false, true, 1, null, null); 
  InterfacciaEasyCheckSerieDocProd.setParent(InterfacciaEasyCheckForm); 
  InterfacciaEasyCheckSerieDocProd.write(out); 
%>
<!--<span class="multisearchform" id="SerieDocProd"></span>--></td>
								</tr>
								<tr>
									<td valign="top"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "InterfacciaEasyCheck", "IdCausaleDocProd", null); 
   label.setParent(InterfacciaEasyCheckForm); 
%><label class="<%=label.getClassType()%>" for="CausaleDocProd"><%label.write(out);%></label><%}%></td>
									<td valign="top"><% 
  WebMultiSearchForm InterfacciaEasyCheckCausaleDocProd =  
     new com.thera.thermfw.web.WebMultiSearchForm("InterfacciaEasyCheck", "CausaleDocProd", false, false, true, 1, null, null); 
  InterfacciaEasyCheckCausaleDocProd.setParent(InterfacciaEasyCheckForm); 
  InterfacciaEasyCheckCausaleDocProd.write(out); 
%>
<!--<span class="multisearchform" id="CausaleDocProd"></span>--></td>
								</tr>
								<tr>
									<td valign="top"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "InterfacciaEasyCheck", "IdDipendenteRilev", null); 
   label.setParent(InterfacciaEasyCheckForm); 
%><label class="<%=label.getClassType()%>" for="DipendenteRilevazione"><%label.write(out);%></label><%}%>
									</td>
									<td valign="top"><% 
  WebMultiSearchForm InterfacciaEasyCheckDipendenteRilevazione =  
     new com.thera.thermfw.web.WebMultiSearchForm("InterfacciaEasyCheck", "DipendenteRilevazione", false, false, true, 1, null, null); 
  InterfacciaEasyCheckDipendenteRilevazione.setParent(InterfacciaEasyCheckForm); 
  InterfacciaEasyCheckDipendenteRilevazione.write(out); 
%>
<!--<span class="multisearchform" id="DipendenteRilevazione"></span>--></td>
								</tr>
								<tr>
									<td valign="top"></td>
									<td valign="top"></td>
								</tr>
								<tr>
									<td valign="top"><% 
   request.setAttribute("parentForm", InterfacciaEasyCheckForm); 
   String CDForDatiComuniEstesi$it$thera$thip$cs$DatiComuniEstesi$jsp = "DatiComuniEstesi"; 
%>
<jsp:include page="/it/thera/thip/cs/DatiComuniEstesi.jsp" flush="true"> 
<jsp:param name="CDName" value="<%=CDForDatiComuniEstesi$it$thera$thip$cs$DatiComuniEstesi$jsp%>"/> 
</jsp:include> 
<!--<span class="subform" id="DatiComuniEstesi"></span>--></td>
									<td valign="top"></td>
								</tr>
							</table>
					<% mytabbed.endTab(); %> 
</div>
				</div><% mytabbed.endTabbed();%> 

     </td>
   </tr>
</table><!--</span>--></td>
			</tr>
			<tr>
				<td style="height: 0"><% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(InterfacciaEasyCheckForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>--></td>
			</tr>
		</table>
	<%
  InterfacciaEasyCheckForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = InterfacciaEasyCheckForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", InterfacciaEasyCheckBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              InterfacciaEasyCheckForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, InterfacciaEasyCheckBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, InterfacciaEasyCheckBODC.getErrorList().getErrors()); 
           if(InterfacciaEasyCheckBODC.getConflict() != null) 
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
     if(InterfacciaEasyCheckBODC != null && !InterfacciaEasyCheckBODC.close(false)) 
        errors.addAll(0, InterfacciaEasyCheckBODC.getErrorList().getErrors()); 
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
     String errorPage = InterfacciaEasyCheckForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", InterfacciaEasyCheckBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = InterfacciaEasyCheckForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
