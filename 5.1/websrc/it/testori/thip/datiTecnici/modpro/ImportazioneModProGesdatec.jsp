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
  BODataCollector YImpModProGSBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YImpModProGSForm =  
     new com.thera.thermfw.web.WebFormForBatchForm(request, response, "YImpModProGSForm", "YImpModProGS", "Arial,10", "it.testori.thip.datiTecnici.modpro.web.ImportazioneModProGesdatecFormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/testori/thip/datiTecnici/modpro/ImportazioneModProGesdatec.js"); 
  YImpModProGSForm.setServletEnvironment(se); 
  YImpModProGSForm.setJSTypeList(jsList); 
  YImpModProGSForm.setHeader("it.thera.thip.cs.Header.jsp"); 
  YImpModProGSForm.setFooter("it.thera.thip.cs.Footer.jsp"); 
  ((WebFormForBatchForm)  YImpModProGSForm).setGenerateThReportId(true); 
  ((WebFormForBatchForm)  YImpModProGSForm).setGenerateSSDEnabled(true); 
  YImpModProGSForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YImpModProGSForm.getMode(); 
  String key = YImpModProGSForm.getKey(); 
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
        YImpModProGSForm.outTraceInfo(getClass().getName()); 
        String collectorName = YImpModProGSForm.findBODataCollectorName(); 
				 YImpModProGSBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YImpModProGSBODC instanceof WebDataCollector) 
            ((WebDataCollector)YImpModProGSBODC).setServletEnvironment(se); 
        YImpModProGSBODC.initialize("YImpModProGS", true, 0); 
        int rcBODC; 
        if (YImpModProGSBODC.getBo() instanceof BatchRunnable) 
          rcBODC = YImpModProGSBODC.initSecurityServices("RUN", mode, true, false, true); 
        else 
          rcBODC = YImpModProGSBODC.initSecurityServices(mode, true, true, true); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YImpModProGSForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YImpModProGSBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YImpModProGSForm.setBODataCollector(YImpModProGSBODC); 
              YImpModProGSForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 

<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YImpModProGSForm); 
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
  myToolBarTB.setParent(YImpModProGSForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/com/thera/thermfw/batch/BatchRunnableMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>

<body bottommargin="0" leftmargin="0" onbeforeunload="<%=YImpModProGSForm.getBodyOnBeforeUnload()%>" onload="<%=YImpModProGSForm.getBodyOnLoad()%>" onunload="<%=YImpModProGSForm.getBodyOnUnload()%>" rightmargin="0" topmargin="0"><%
   YImpModProGSForm.writeBodyStartElements(out); 
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
<% String hdr = YImpModProGSForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YImpModProGSBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YImpModProGSForm.getServlet()%>" method="post" name="myForm" style="height:100%"><%
  YImpModProGSForm.writeFormStartElements(out); 
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
			<!--tr><td-->
			<!--table height="100%" width="100%" border="0" Fix 12105-->
			<tr>
				<td>
					<table border="0" style="margin: 7 7 7 7;" width="98%">
						<tr>
							<td><label id="SceltaFile">File</label></td>
							<td><input id="NomeFile" name="NomeFile" size="90" style="width: 300px; height: 100px;" type="file"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="height: 0"><% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(YImpModProGSForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>--></td>
			</tr>
		</table>
	<%
  YImpModProGSForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YImpModProGSForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YImpModProGSBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YImpModProGSForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YImpModProGSBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YImpModProGSBODC.getErrorList().getErrors()); 
           if(YImpModProGSBODC.getConflict() != null) 
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
     if(YImpModProGSBODC != null && !YImpModProGSBODC.close(false)) 
        errors.addAll(0, YImpModProGSBODC.getErrorList().getErrors()); 
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
     String errorPage = YImpModProGSForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YImpModProGSBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YImpModProGSForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
