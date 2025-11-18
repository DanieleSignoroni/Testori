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
  BODataCollector YImpModProWyhtexBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YImpModProWyhtexForm =  
     new com.thera.thermfw.web.WebFormForBatchForm(request, response, "YImpModProWyhtexForm", "YImpModProWyhtex", "Arial,10", "com.thera.thermfw.batch.web.BatchFormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/testori/thip/whytex/ImportModProWyhtex.js"); 
  YImpModProWyhtexForm.setServletEnvironment(se); 
  YImpModProWyhtexForm.setJSTypeList(jsList); 
  YImpModProWyhtexForm.setHeader("it.thera.thip.cs.Header.jsp"); 
  YImpModProWyhtexForm.setFooter("it.thera.thip.cs.Footer.jsp"); 
  ((WebFormForBatchForm)  YImpModProWyhtexForm).setGenerateThReportId(true); 
  ((WebFormForBatchForm)  YImpModProWyhtexForm).setGenerateSSDEnabled(true); 
  YImpModProWyhtexForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YImpModProWyhtexForm.getMode(); 
  String key = YImpModProWyhtexForm.getKey(); 
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
        YImpModProWyhtexForm.outTraceInfo(getClass().getName()); 
        String collectorName = YImpModProWyhtexForm.findBODataCollectorName(); 
				 YImpModProWyhtexBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YImpModProWyhtexBODC instanceof WebDataCollector) 
            ((WebDataCollector)YImpModProWyhtexBODC).setServletEnvironment(se); 
        YImpModProWyhtexBODC.initialize("YImpModProWyhtex", true, 0); 
        int rcBODC; 
        if (YImpModProWyhtexBODC.getBo() instanceof BatchRunnable) 
          rcBODC = YImpModProWyhtexBODC.initSecurityServices("RUN", mode, true, false, true); 
        else 
          rcBODC = YImpModProWyhtexBODC.initSecurityServices(mode, true, true, true); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YImpModProWyhtexForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YImpModProWyhtexBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YImpModProWyhtexForm.setBODataCollector(YImpModProWyhtexBODC); 
              YImpModProWyhtexForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 

<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YImpModProWyhtexForm); 
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
  myToolBarTB.setParent(YImpModProWyhtexForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/com/thera/thermfw/batch/BatchRunnableMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>

<body bottommargin="0" leftmargin="0" onbeforeunload="<%=YImpModProWyhtexForm.getBodyOnBeforeUnload()%>" onload="<%=YImpModProWyhtexForm.getBodyOnLoad()%>" onunload="<%=YImpModProWyhtexForm.getBodyOnUnload()%>" rightmargin="0" topmargin="0"><%
   YImpModProWyhtexForm.writeBodyStartElements(out); 
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
<% String hdr = YImpModProWyhtexForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YImpModProWyhtexBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YImpModProWyhtexForm.getServlet()%>" method="post" name="myForm" style="height:100%"><%
  YImpModProWyhtexForm.writeFormStartElements(out); 
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
  WebTextInput YImpModProWyhtexIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YImpModProWyhtex", "IdAzienda"); 
  YImpModProWyhtexIdAzienda.setParent(YImpModProWyhtexForm); 
%>
<input class="<%=YImpModProWyhtexIdAzienda.getClassType()%>" id="<%=YImpModProWyhtexIdAzienda.getId()%>" maxlength="<%=YImpModProWyhtexIdAzienda.getMaxLength()%>" name="<%=YImpModProWyhtexIdAzienda.getName()%>" size="<%=YImpModProWyhtexIdAzienda.getSize()%>" type="hidden"><% 
  YImpModProWyhtexIdAzienda.write(out); 
%>
</td>
			</tr>
			<tr>
				<td><input id="CMEntityId" name="CMEntityId" type="hidden"></td>
			</tr>
			<tr>
				<td height="100%">
					<table style="margin: 7 7 7 7;">
						<tr>
							<td colspan="2"><% 
   request.setAttribute("parentForm", YImpModProWyhtexForm); 
   String CDForRunParameter$com$thera$thermfw$batchLoad$RunParamBatchLoader$jsp = "RunParameter"; 
%>
<jsp:include page="/com/thera/thermfw/batchLoad/RunParamBatchLoader.jsp" flush="true"> 
<jsp:param name="CDName" value="<%=CDForRunParameter$com$thera$thermfw$batchLoad$RunParamBatchLoader$jsp%>"/> 
</jsp:include> 
<!--<span class="subform" id="runParameter"></span>-->
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table border="0" style="margin: 7 7 7 7;">
						<tr>
							<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YImpModProWyhtex", "InboundPath", null); 
   label.setParent(YImpModProWyhtexForm); 
%><label class="<%=label.getClassType()%>" for="InboundPath"><%label.write(out);%></label><%}%></td>
							<td><% 
  WebTextInput YImpModProWyhtexInboundPath =  
     new com.thera.thermfw.web.WebTextInput("YImpModProWyhtex", "InboundPath"); 
  YImpModProWyhtexInboundPath.setParent(YImpModProWyhtexForm); 
%>
<input class="<%=YImpModProWyhtexInboundPath.getClassType()%>" id="<%=YImpModProWyhtexInboundPath.getId()%>" maxlength="<%=YImpModProWyhtexInboundPath.getMaxLength()%>" name="<%=YImpModProWyhtexInboundPath.getName()%>" size="<%=YImpModProWyhtexInboundPath.getSize()%>" type="text"><% 
  YImpModProWyhtexInboundPath.write(out); 
%>
</td>
						</tr>
						<tr>
							<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YImpModProWyhtex", "OutboundPath", null); 
   label.setParent(YImpModProWyhtexForm); 
%><label class="<%=label.getClassType()%>" for="OutboundPath"><%label.write(out);%></label><%}%></td>
							<td><% 
  WebTextInput YImpModProWyhtexOutboundPath =  
     new com.thera.thermfw.web.WebTextInput("YImpModProWyhtex", "OutboundPath"); 
  YImpModProWyhtexOutboundPath.setParent(YImpModProWyhtexForm); 
%>
<input class="<%=YImpModProWyhtexOutboundPath.getClassType()%>" id="<%=YImpModProWyhtexOutboundPath.getId()%>" maxlength="<%=YImpModProWyhtexOutboundPath.getMaxLength()%>" name="<%=YImpModProWyhtexOutboundPath.getName()%>" size="<%=YImpModProWyhtexOutboundPath.getSize()%>" type="text"><% 
  YImpModProWyhtexOutboundPath.write(out); 
%>
</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="height: 0"><% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(YImpModProWyhtexForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>--></td>
			</tr>
		</table>
	<%
  YImpModProWyhtexForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YImpModProWyhtexForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YImpModProWyhtexBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YImpModProWyhtexForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YImpModProWyhtexBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YImpModProWyhtexBODC.getErrorList().getErrors()); 
           if(YImpModProWyhtexBODC.getConflict() != null) 
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
     if(YImpModProWyhtexBODC != null && !YImpModProWyhtexBODC.close(false)) 
        errors.addAll(0, YImpModProWyhtexBODC.getErrorList().getErrors()); 
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
     String errorPage = YImpModProWyhtexForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YImpModProWyhtexBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YImpModProWyhtexForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
