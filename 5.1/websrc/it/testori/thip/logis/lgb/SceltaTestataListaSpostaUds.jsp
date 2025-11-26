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
  BODataCollector YSceltaTlSpUdsBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YSceltaTlSpUdsForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YSceltaTlSpUdsForm", "YSceltaTlSpUds", null, "it.testori.thip.logis.lgb.web.SceltaTestataListaSpostaUdsFAA", false, false, true, true, true, true, null, 0, true, "it/testori/thip/logis/lgb/SceltaTestataListaSpostaUds.js"); 
  YSceltaTlSpUdsForm.setServletEnvironment(se); 
  YSceltaTlSpUdsForm.setJSTypeList(jsList); 
  YSceltaTlSpUdsForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YSceltaTlSpUdsForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YSceltaTlSpUdsForm.setWebFormModifierClass("it.testori.thip.logis.lgb.web.SceltaTestataListaSpostaUdsFM"); 
  YSceltaTlSpUdsForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YSceltaTlSpUdsForm.getMode(); 
  String key = YSceltaTlSpUdsForm.getKey(); 
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
        YSceltaTlSpUdsForm.outTraceInfo(getClass().getName()); 
        String collectorName = YSceltaTlSpUdsForm.findBODataCollectorName(); 
                YSceltaTlSpUdsBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YSceltaTlSpUdsBODC instanceof WebDataCollector) 
            ((WebDataCollector)YSceltaTlSpUdsBODC).setServletEnvironment(se); 
        YSceltaTlSpUdsBODC.initialize("YSceltaTlSpUds", true, 0); 
        YSceltaTlSpUdsForm.setBODataCollector(YSceltaTlSpUdsBODC); 
        int rcBODC = YSceltaTlSpUdsForm.initSecurityServices(); 
        mode = YSceltaTlSpUdsForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YSceltaTlSpUdsForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YSceltaTlSpUdsBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YSceltaTlSpUdsForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
</head>

<body onbeforeunload="<%=YSceltaTlSpUdsForm.getBodyOnBeforeUnload()%>" onload="<%=YSceltaTlSpUdsForm.getBodyOnLoad()%>" onunload="<%=YSceltaTlSpUdsForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YSceltaTlSpUdsForm.writeBodyStartElements(out); 
%> 

	<table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YSceltaTlSpUdsForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YSceltaTlSpUdsBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YSceltaTlSpUdsForm.getServlet()%>" method="post" name="myForm" style="height:100%"><%
  YSceltaTlSpUdsForm.writeFormStartElements(out); 
%>

		<table cellpadding="0" cellspacing="0" id="emptyborder" width="100%">
			<tr>
				<td><% 
  WebTextInput YSceltaTlSpUdsCodiceSocieta =  
     new com.thera.thermfw.web.WebTextInput("YSceltaTlSpUds", "CodiceSocieta"); 
  YSceltaTlSpUdsCodiceSocieta.setParent(YSceltaTlSpUdsForm); 
%>
<input class="<%=YSceltaTlSpUdsCodiceSocieta.getClassType()%>" id="<%=YSceltaTlSpUdsCodiceSocieta.getId()%>" maxlength="<%=YSceltaTlSpUdsCodiceSocieta.getMaxLength()%>" name="<%=YSceltaTlSpUdsCodiceSocieta.getName()%>" size="<%=YSceltaTlSpUdsCodiceSocieta.getSize()%>" type="hidden"><% 
  YSceltaTlSpUdsCodiceSocieta.write(out); 
%>
</td>
					<td><% 
  WebTextInput YSceltaTlSpUdsChiaviSelezionati =  
     new com.thera.thermfw.web.WebTextInput("YSceltaTlSpUds", "ChiaviSelezionati"); 
  YSceltaTlSpUdsChiaviSelezionati.setParent(YSceltaTlSpUdsForm); 
%>
<input class="<%=YSceltaTlSpUdsChiaviSelezionati.getClassType()%>" id="<%=YSceltaTlSpUdsChiaviSelezionati.getId()%>" maxlength="<%=YSceltaTlSpUdsChiaviSelezionati.getMaxLength()%>" name="<%=YSceltaTlSpUdsChiaviSelezionati.getName()%>" size="<%=YSceltaTlSpUdsChiaviSelezionati.getSize()%>" type="hidden"><% 
  YSceltaTlSpUdsChiaviSelezionati.write(out); 
%>
</td>
				<td style="display:none;"><% 
  WebCheckBox YSceltaTlSpUdsSposta =  
     new com.thera.thermfw.web.WebCheckBox("YSceltaTlSpUds", "Sposta"); 
  YSceltaTlSpUdsSposta.setParent(YSceltaTlSpUdsForm); 
%>
<input id="<%=YSceltaTlSpUdsSposta.getId()%>" name="<%=YSceltaTlSpUdsSposta.getName()%>" type="checkbox" value="Y"><%
  YSceltaTlSpUdsSposta.write(out); 
%>
</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<td width="100"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YSceltaTlSpUds", "CodiceTestataLista", null); 
   label.setParent(YSceltaTlSpUdsForm); 
%><label class="<%=label.getClassType()%>" for="TestataLista"><%label.write(out);%></label><%}%></td>
							<td><% 
  WebMultiSearchForm YSceltaTlSpUdsTestataLista =  
     new com.thera.thermfw.web.WebMultiSearchForm("YSceltaTlSpUds", "TestataLista", false, false, true, 1, null, null); 
  YSceltaTlSpUdsTestataLista.setParent(YSceltaTlSpUdsForm); 
  YSceltaTlSpUdsTestataLista.setSpecificDOList("it.testori.thip.logis.lgb.web.SceltaTestataListaSpostaUdsDoList"); 
  YSceltaTlSpUdsTestataLista.write(out); 
%>
<!--<span class="multisearchform" id="TestataLista"></span>--></td>
							<td>
								<button id="bottoneConferma" name="bottoneConferma" onclick="conferma()" style="width: 125px; display: inline;" type="button">conferma lista</button>
							</td>
							<td style="display: none;">
								<button id="spostaUds" name="spostaUds" onclick="spostaUdsFn()" style="width: 125px; display: inline;" type="button">sposta
									uds</button>
							</td>
							<td style="display: none;">
								<button id="indietro" name="indietro" onclick="indietroFn()" style="width: 125px; display: inline;" type="button">indietro</button>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr style="margin-top: 2rem;">
				<td>
					<table>
						<iframe id="righe" name="righe" src style="display: none; width: 100%; height: 90vh;"></iframe>
					</table>
				</td>
			</tr>
			<tr>
				<td style="height: 0"><% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(YSceltaTlSpUdsForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>--></td>
			</tr>
		</table>
	<%
  YSceltaTlSpUdsForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YSceltaTlSpUdsForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YSceltaTlSpUdsBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YSceltaTlSpUdsForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YSceltaTlSpUdsBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YSceltaTlSpUdsBODC.getErrorList().getErrors()); 
           if(YSceltaTlSpUdsBODC.getConflict() != null) 
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
     if(YSceltaTlSpUdsBODC != null && !YSceltaTlSpUdsBODC.close(false)) 
        errors.addAll(0, YSceltaTlSpUdsBODC.getErrorList().getErrors()); 
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
     String errorPage = YSceltaTlSpUdsForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YSceltaTlSpUdsBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YSceltaTlSpUdsForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
