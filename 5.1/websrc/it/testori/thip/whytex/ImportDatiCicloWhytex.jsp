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
  BODataCollector YImpDatiCicloWyhtexBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YImpDatiCicloWyhtexForm =  
     new com.thera.thermfw.web.WebFormForBatchForm(request, response, "YImpDatiCicloWyhtexForm", "YImpDatiCicloWyhtex", "Arial,10", "com.thera.thermfw.batch.web.BatchFormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/testori/thip/whytex/ImportDatiCicloWhytex.js"); 
  YImpDatiCicloWyhtexForm.setServletEnvironment(se); 
  YImpDatiCicloWyhtexForm.setJSTypeList(jsList); 
  YImpDatiCicloWyhtexForm.setHeader("it.thera.thip.cs.Header.jsp"); 
  YImpDatiCicloWyhtexForm.setFooter("it.thera.thip.cs.Footer.jsp"); 
  ((WebFormForBatchForm)  YImpDatiCicloWyhtexForm).setGenerateThReportId(true); 
  ((WebFormForBatchForm)  YImpDatiCicloWyhtexForm).setGenerateSSDEnabled(true); 
  YImpDatiCicloWyhtexForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YImpDatiCicloWyhtexForm.getMode(); 
  String key = YImpDatiCicloWyhtexForm.getKey(); 
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
        YImpDatiCicloWyhtexForm.outTraceInfo(getClass().getName()); 
        String collectorName = YImpDatiCicloWyhtexForm.findBODataCollectorName(); 
				 YImpDatiCicloWyhtexBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YImpDatiCicloWyhtexBODC instanceof WebDataCollector) 
            ((WebDataCollector)YImpDatiCicloWyhtexBODC).setServletEnvironment(se); 
        YImpDatiCicloWyhtexBODC.initialize("YImpDatiCicloWyhtex", true, 0); 
        int rcBODC; 
        if (YImpDatiCicloWyhtexBODC.getBo() instanceof BatchRunnable) 
          rcBODC = YImpDatiCicloWyhtexBODC.initSecurityServices("RUN", mode, true, false, true); 
        else 
          rcBODC = YImpDatiCicloWyhtexBODC.initSecurityServices(mode, true, true, true); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YImpDatiCicloWyhtexForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YImpDatiCicloWyhtexBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YImpDatiCicloWyhtexForm.setBODataCollector(YImpDatiCicloWyhtexBODC); 
              YImpDatiCicloWyhtexForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 

<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YImpDatiCicloWyhtexForm); 
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
  myToolBarTB.setParent(YImpDatiCicloWyhtexForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/com/thera/thermfw/batch/BatchRunnableMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>

<body bottommargin="0" leftmargin="0" onbeforeunload="<%=YImpDatiCicloWyhtexForm.getBodyOnBeforeUnload()%>" onload="<%=YImpDatiCicloWyhtexForm.getBodyOnLoad()%>" onunload="<%=YImpDatiCicloWyhtexForm.getBodyOnUnload()%>" rightmargin="0" topmargin="0"><%
   YImpDatiCicloWyhtexForm.writeBodyStartElements(out); 
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
<% String hdr = YImpDatiCicloWyhtexForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YImpDatiCicloWyhtexBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YImpDatiCicloWyhtexForm.getServlet()%>" method="post" name="myForm" style="height:100%"><%
  YImpDatiCicloWyhtexForm.writeFormStartElements(out); 
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
  WebTextInput YImpDatiCicloWyhtexIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YImpDatiCicloWyhtex", "IdAzienda"); 
  YImpDatiCicloWyhtexIdAzienda.setParent(YImpDatiCicloWyhtexForm); 
%>
<input class="<%=YImpDatiCicloWyhtexIdAzienda.getClassType()%>" id="<%=YImpDatiCicloWyhtexIdAzienda.getId()%>" maxlength="<%=YImpDatiCicloWyhtexIdAzienda.getMaxLength()%>" name="<%=YImpDatiCicloWyhtexIdAzienda.getName()%>" size="<%=YImpDatiCicloWyhtexIdAzienda.getSize()%>" type="hidden"><% 
  YImpDatiCicloWyhtexIdAzienda.write(out); 
%>
</td>
			</tr>
			<tr>
				<td>
					<table border="0" style="margin: 7 7 7 7;">
						<tr>
							<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YImpDatiCicloWyhtex", "InboundPath", null); 
   label.setParent(YImpDatiCicloWyhtexForm); 
%><label class="<%=label.getClassType()%>" for="InboundPath"><%label.write(out);%></label><%}%></td>
							<td><% 
  WebTextInput YImpDatiCicloWyhtexInboundPath =  
     new com.thera.thermfw.web.WebTextInput("YImpDatiCicloWyhtex", "InboundPath"); 
  YImpDatiCicloWyhtexInboundPath.setParent(YImpDatiCicloWyhtexForm); 
%>
<input class="<%=YImpDatiCicloWyhtexInboundPath.getClassType()%>" id="<%=YImpDatiCicloWyhtexInboundPath.getId()%>" maxlength="<%=YImpDatiCicloWyhtexInboundPath.getMaxLength()%>" name="<%=YImpDatiCicloWyhtexInboundPath.getName()%>" size="<%=YImpDatiCicloWyhtexInboundPath.getSize()%>" type="text"><% 
  YImpDatiCicloWyhtexInboundPath.write(out); 
%>
</td>
						</tr>
						<tr>
							<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YImpDatiCicloWyhtex", "OutboundPath", null); 
   label.setParent(YImpDatiCicloWyhtexForm); 
%><label class="<%=label.getClassType()%>" for="OutboundPath"><%label.write(out);%></label><%}%></td>
							<td><% 
  WebTextInput YImpDatiCicloWyhtexOutboundPath =  
     new com.thera.thermfw.web.WebTextInput("YImpDatiCicloWyhtex", "OutboundPath"); 
  YImpDatiCicloWyhtexOutboundPath.setParent(YImpDatiCicloWyhtexForm); 
%>
<input class="<%=YImpDatiCicloWyhtexOutboundPath.getClassType()%>" id="<%=YImpDatiCicloWyhtexOutboundPath.getId()%>" maxlength="<%=YImpDatiCicloWyhtexOutboundPath.getMaxLength()%>" name="<%=YImpDatiCicloWyhtexOutboundPath.getName()%>" size="<%=YImpDatiCicloWyhtexOutboundPath.getSize()%>" type="text"><% 
  YImpDatiCicloWyhtexOutboundPath.write(out); 
%>
</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="height: 0"><% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(YImpDatiCicloWyhtexForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>--></td>
			</tr>
		</table>
	<%
  YImpDatiCicloWyhtexForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YImpDatiCicloWyhtexForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YImpDatiCicloWyhtexBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YImpDatiCicloWyhtexForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YImpDatiCicloWyhtexBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YImpDatiCicloWyhtexBODC.getErrorList().getErrors()); 
           if(YImpDatiCicloWyhtexBODC.getConflict() != null) 
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
     if(YImpDatiCicloWyhtexBODC != null && !YImpDatiCicloWyhtexBODC.close(false)) 
        errors.addAll(0, YImpDatiCicloWyhtexBODC.getErrorList().getErrors()); 
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
     String errorPage = YImpDatiCicloWyhtexForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YImpDatiCicloWyhtexBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YImpDatiCicloWyhtexForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
