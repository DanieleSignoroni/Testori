<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///K:/Thip/5.1.0/websrcsvil/dtd/xhtml1-transitional.dtd">
<html>
<!-- WIZGEN Therm 2.0.0 as Batch form - multiBrowserGen = true -->
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
  BODataCollector YImpDatiAnagrWyhtexBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YImpDatiAnagrWyhtexForm =  
     new com.thera.thermfw.web.WebFormForBatchForm(request, response, "YImpDatiAnagrWyhtexForm", "YImpDatiAnagrWyhtex", "Arial,10", "com.thera.thermfw.batch.web.BatchFormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/testori/thip/whytex/ImportDatiAnagrafica.js"); 
  YImpDatiAnagrWyhtexForm.setServletEnvironment(se); 
  YImpDatiAnagrWyhtexForm.setJSTypeList(jsList); 
  YImpDatiAnagrWyhtexForm.setHeader("it.thera.thip.cs.Header.jsp"); 
  YImpDatiAnagrWyhtexForm.setFooter("it.thera.thip.cs.Footer.jsp"); 
  ((WebFormForBatchForm)  YImpDatiAnagrWyhtexForm).setGenerateThReportId(true); 
  ((WebFormForBatchForm)  YImpDatiAnagrWyhtexForm).setGenerateSSDEnabled(true); 
  YImpDatiAnagrWyhtexForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YImpDatiAnagrWyhtexForm.getMode(); 
  String key = YImpDatiAnagrWyhtexForm.getKey(); 
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
        YImpDatiAnagrWyhtexForm.outTraceInfo(getClass().getName()); 
        String collectorName = YImpDatiAnagrWyhtexForm.findBODataCollectorName(); 
				 YImpDatiAnagrWyhtexBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YImpDatiAnagrWyhtexBODC instanceof WebDataCollector) 
            ((WebDataCollector)YImpDatiAnagrWyhtexBODC).setServletEnvironment(se); 
        YImpDatiAnagrWyhtexBODC.initialize("YImpDatiAnagrWyhtex", true, 0); 
        int rcBODC; 
        if (YImpDatiAnagrWyhtexBODC.getBo() instanceof BatchRunnable) 
          rcBODC = YImpDatiAnagrWyhtexBODC.initSecurityServices("RUN", mode, true, false, true); 
        else 
          rcBODC = YImpDatiAnagrWyhtexBODC.initSecurityServices(mode, true, true, true); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YImpDatiAnagrWyhtexForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YImpDatiAnagrWyhtexBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YImpDatiAnagrWyhtexForm.setBODataCollector(YImpDatiAnagrWyhtexBODC); 
              YImpDatiAnagrWyhtexForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 

<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YImpDatiAnagrWyhtexForm); 
   request.setAttribute("menuBar", menuBar); 
%> 
<jsp:include page="/com/thera/thermfw/batch/BatchRunnableMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="menuBar"/> 
</jsp:include> 
<% 
  menuBar.write(out); 
  menuBar.writeChildren(out); 
%> 
<% 
  WebToolBar myToolBarTB = new com.thera.thermfw.web.WebToolBar("myToolBar", "24", "24", "16", "16", "#f7fbfd","#C8D6E1"); 
  myToolBarTB.setParent(YImpDatiAnagrWyhtexForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/com/thera/thermfw/batch/BatchRunnableMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>

<body bottommargin="0" leftmargin="0" onbeforeunload="<%=YImpDatiAnagrWyhtexForm.getBodyOnBeforeUnload()%>" onload="<%=YImpDatiAnagrWyhtexForm.getBodyOnLoad()%>" onunload="<%=YImpDatiAnagrWyhtexForm.getBodyOnUnload()%>" rightmargin="0" topmargin="0"><%
   YImpDatiAnagrWyhtexForm.writeBodyStartElements(out); 
%> 


	<% 
  WebScript script_0 =  
   new com.thera.thermfw.web.WebScript(); 
 script_0.setRequest(request); 
 script_0.setSrcAttribute("it/thera/thip/cs/util.js"); 
 script_0.setLanguageAttribute("JavaScript1.2"); 
  script_0.write(out); 
%>
<!--<script language="JavaScript1.2" src="it/thera/thip/cs/util.js" type="text/javascript"></script>-->
	<table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YImpDatiAnagrWyhtexForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YImpDatiAnagrWyhtexBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YImpDatiAnagrWyhtexForm.getServlet()%>" method="post" name="myForm" style="height:100%"><%
  YImpDatiAnagrWyhtexForm.writeFormStartElements(out); 
%>

		<!-- Fix 18758 -->
		<table cellpadding="2" cellspacing="0" id="emptyborder" width="100%">
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
  WebTextInput YImpDatiAnagrWyhtexIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YImpDatiAnagrWyhtex", "IdAzienda"); 
  YImpDatiAnagrWyhtexIdAzienda.setParent(YImpDatiAnagrWyhtexForm); 
%>
<input class="<%=YImpDatiAnagrWyhtexIdAzienda.getClassType()%>" id="<%=YImpDatiAnagrWyhtexIdAzienda.getId()%>" maxlength="<%=YImpDatiAnagrWyhtexIdAzienda.getMaxLength()%>" name="<%=YImpDatiAnagrWyhtexIdAzienda.getName()%>" size="<%=YImpDatiAnagrWyhtexIdAzienda.getSize()%>" type="hidden"><% 
  YImpDatiAnagrWyhtexIdAzienda.write(out); 
%>
</td>
			</tr>
			<tr>
				<td>
					<table border="0" style="margin: 7 7 7 7;">
						<tr>
							<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YImpDatiAnagrWyhtex", "InboundPath", null); 
   label.setParent(YImpDatiAnagrWyhtexForm); 
%><label class="<%=label.getClassType()%>" for="InboundPath"><%label.write(out);%></label><%}%></td>
							<td><% 
  WebTextInput YImpDatiAnagrWyhtexInboundPath =  
     new com.thera.thermfw.web.WebTextInput("YImpDatiAnagrWyhtex", "InboundPath"); 
  YImpDatiAnagrWyhtexInboundPath.setParent(YImpDatiAnagrWyhtexForm); 
%>
<input class="<%=YImpDatiAnagrWyhtexInboundPath.getClassType()%>" id="<%=YImpDatiAnagrWyhtexInboundPath.getId()%>" maxlength="<%=YImpDatiAnagrWyhtexInboundPath.getMaxLength()%>" name="<%=YImpDatiAnagrWyhtexInboundPath.getName()%>" size="<%=YImpDatiAnagrWyhtexInboundPath.getSize()%>" type="text"><% 
  YImpDatiAnagrWyhtexInboundPath.write(out); 
%>
</td>
						</tr>
						<tr>
							<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YImpDatiAnagrWyhtex", "OutboundPath", null); 
   label.setParent(YImpDatiAnagrWyhtexForm); 
%><label class="<%=label.getClassType()%>" for="OutboundPath"><%label.write(out);%></label><%}%></td>
							<td><% 
  WebTextInput YImpDatiAnagrWyhtexOutboundPath =  
     new com.thera.thermfw.web.WebTextInput("YImpDatiAnagrWyhtex", "OutboundPath"); 
  YImpDatiAnagrWyhtexOutboundPath.setParent(YImpDatiAnagrWyhtexForm); 
%>
<input class="<%=YImpDatiAnagrWyhtexOutboundPath.getClassType()%>" id="<%=YImpDatiAnagrWyhtexOutboundPath.getId()%>" maxlength="<%=YImpDatiAnagrWyhtexOutboundPath.getMaxLength()%>" name="<%=YImpDatiAnagrWyhtexOutboundPath.getName()%>" size="<%=YImpDatiAnagrWyhtexOutboundPath.getSize()%>" type="text"><% 
  YImpDatiAnagrWyhtexOutboundPath.write(out); 
%>
</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="height: 0"><% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(YImpDatiAnagrWyhtexForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>--></td>
			</tr>
		</table>
	<%
  YImpDatiAnagrWyhtexForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YImpDatiAnagrWyhtexForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YImpDatiAnagrWyhtexBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YImpDatiAnagrWyhtexForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YImpDatiAnagrWyhtexBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YImpDatiAnagrWyhtexBODC.getErrorList().getErrors()); 
           if(YImpDatiAnagrWyhtexBODC.getConflict() != null) 
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
     if(YImpDatiAnagrWyhtexBODC != null && !YImpDatiAnagrWyhtexBODC.close(false)) 
        errors.addAll(0, YImpDatiAnagrWyhtexBODC.getErrorList().getErrors()); 
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
     String errorPage = YImpDatiAnagrWyhtexForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YImpDatiAnagrWyhtexBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YImpDatiAnagrWyhtexForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
