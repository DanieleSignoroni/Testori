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
  BODataCollector YLancioIntModProGesdatecBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YLancioIntModProGesdatecForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YLancioIntModProGesdatecForm", "YLancioIntModProGesdatec", null, "it.testori.thip.datiTecnici.modpro.web.LancioInterrogazioneModProGesdatecFormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/testori/thip/datiTecnici/modpro/LancioInterrogazioneModProGesdatec.js"); 
  YLancioIntModProGesdatecForm.setServletEnvironment(se); 
  YLancioIntModProGesdatecForm.setJSTypeList(jsList); 
  YLancioIntModProGesdatecForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YLancioIntModProGesdatecForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YLancioIntModProGesdatecForm.setWebFormModifierClass("it.testori.thip.datiTecnici.modpro.web.LancioInterrogazioneModProGesdatecFormModifier"); 
  YLancioIntModProGesdatecForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YLancioIntModProGesdatecForm.getMode(); 
  String key = YLancioIntModProGesdatecForm.getKey(); 
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
        YLancioIntModProGesdatecForm.outTraceInfo(getClass().getName()); 
        String collectorName = YLancioIntModProGesdatecForm.findBODataCollectorName(); 
                YLancioIntModProGesdatecBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YLancioIntModProGesdatecBODC instanceof WebDataCollector) 
            ((WebDataCollector)YLancioIntModProGesdatecBODC).setServletEnvironment(se); 
        YLancioIntModProGesdatecBODC.initialize("YLancioIntModProGesdatec", true, 0); 
        YLancioIntModProGesdatecForm.setBODataCollector(YLancioIntModProGesdatecBODC); 
        int rcBODC = YLancioIntModProGesdatecForm.initSecurityServices(); 
        mode = YLancioIntModProGesdatecForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YLancioIntModProGesdatecForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YLancioIntModProGesdatecBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YLancioIntModProGesdatecForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
</head>
<body onbeforeunload="<%=YLancioIntModProGesdatecForm.getBodyOnBeforeUnload()%>" onload="<%=YLancioIntModProGesdatecForm.getBodyOnLoad()%>" onunload="<%=YLancioIntModProGesdatecForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YLancioIntModProGesdatecForm.writeBodyStartElements(out); 
%> 

	<table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YLancioIntModProGesdatecForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YLancioIntModProGesdatecBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YLancioIntModProGesdatecForm.getServlet()%>" method="post" name="YLancioIntModProGesdatecForm" style="height:100%"><%
  YLancioIntModProGesdatecForm.writeFormStartElements(out); 
%>

		<table cellpadding="0" cellspacing="0" id="emptyborder" width="100%">
			<!--    <tr> -->
			<!--     <td style="height: 0"><span class="toolbar" id="myToolBar" /></td> -->
			<!--    </tr> -->
			<tr>
				<td><% 
  WebTextInput YLancioIntModProGesdatecIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YLancioIntModProGesdatec", "IdAzienda"); 
  YLancioIntModProGesdatecIdAzienda.setParent(YLancioIntModProGesdatecForm); 
%>
<input class="<%=YLancioIntModProGesdatecIdAzienda.getClassType()%>" id="<%=YLancioIntModProGesdatecIdAzienda.getId()%>" maxlength="<%=YLancioIntModProGesdatecIdAzienda.getMaxLength()%>" name="<%=YLancioIntModProGesdatecIdAzienda.getName()%>" size="<%=YLancioIntModProGesdatecIdAzienda.getSize()%>" type="hidden"><% 
  YLancioIntModProGesdatecIdAzienda.write(out); 
%>
</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<td>
								<fieldset style="width: fit-content; display: inline;">
									<legend>Dati - Interrogazione</legend>
									<table>
										<tr>
											<td width="100"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YLancioIntModProGesdatec", "IdArticolo", null); 
   label.setParent(YLancioIntModProGesdatecForm); 
%><label class="<%=label.getClassType()%>" for="Articolo"><%label.write(out);%></label><%}%></td>
											<td><% 
  WebMultiSearchForm YLancioIntModProGesdatecArticolo =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("YLancioIntModProGesdatec", "Articolo", false, false, true, 1, null, null); 
  YLancioIntModProGesdatecArticolo.setParent(YLancioIntModProGesdatecForm); 
  YLancioIntModProGesdatecArticolo.write(out); 
%>
<!--<span class="articolomultisearchform" id="Articolo"></span>--></td>
										</tr>
										<tr>
											<td width="100"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YLancioIntModProGesdatec", "IdVersione", null); 
   label.setParent(YLancioIntModProGesdatecForm); 
%><label class="<%=label.getClassType()%>" for="Versione"><%label.write(out);%></label><%}%></td>
											<td><% 
  WebMultiSearchForm YLancioIntModProGesdatecVersione =  
     new com.thera.thermfw.web.WebMultiSearchForm("YLancioIntModProGesdatec", "Versione", false, false, true, 1, null, null); 
  YLancioIntModProGesdatecVersione.setParent(YLancioIntModProGesdatecForm); 
  YLancioIntModProGesdatecVersione.write(out); 
%>
<!--<span class="multisearchform" id="Versione"></span>--></td>
										</tr>
										<tr>
											<td width="100"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YLancioIntModProGesdatec", "IdEsternoConfig", null); 
   label.setParent(YLancioIntModProGesdatecForm); 
%><label class="<%=label.getClassType()%>" for="Configurazione"><%label.write(out);%></label><%}%></td>
											<td><% 
  WebMultiSearchForm YLancioIntModProGesdatecConfigurazione =  
     new it.thera.thip.datiTecnici.configuratore.web.ConfigurazioneMultiSearchForm("YLancioIntModProGesdatec", "Configurazione", false, false, true, 1, null, null); 
  YLancioIntModProGesdatecConfigurazione.setExtraRelatedClassAD("IdAzienda, IdConfigurazione"); 
  YLancioIntModProGesdatecConfigurazione.setParent(YLancioIntModProGesdatecForm); 
  YLancioIntModProGesdatecConfigurazione.write(out); 
%>
<!--<span class="configurazionemultisearchform" id="Configurazione"></span>--></td>
										</tr>
										<tr>
											<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YLancioIntModProGesdatec", "Priorita", null); 
   label.setParent(YLancioIntModProGesdatecForm); 
%><label class="<%=label.getClassType()%>" for="Priorita"><%label.write(out);%></label><%}%></td>
											<td><% 
  WebTextInput YLancioIntModProGesdatecPriorita =  
     new com.thera.thermfw.web.WebTextInput("YLancioIntModProGesdatec", "Priorita"); 
  YLancioIntModProGesdatecPriorita.setParent(YLancioIntModProGesdatecForm); 
%>
<input class="<%=YLancioIntModProGesdatecPriorita.getClassType()%>" id="<%=YLancioIntModProGesdatecPriorita.getId()%>" maxlength="<%=YLancioIntModProGesdatecPriorita.getMaxLength()%>" name="<%=YLancioIntModProGesdatecPriorita.getName()%>" size="<%=YLancioIntModProGesdatecPriorita.getSize()%>" type="text"><% 
  YLancioIntModProGesdatecPriorita.write(out); 
%>
</td>
										</tr>
									</table>
								</fieldset>
								<button id="bottoneConferma" name="bottoneConferma" onclick="conferma()" style="width: 125px; display: inline; margin-top: 2.5rem; margin-left: 1rem" type="button">Conferma modifica</button>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="height: 0"><% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(YLancioIntModProGesdatecForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>--></td>
			</tr>
		</table>
	<%
  YLancioIntModProGesdatecForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YLancioIntModProGesdatecForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YLancioIntModProGesdatecBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YLancioIntModProGesdatecForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YLancioIntModProGesdatecBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YLancioIntModProGesdatecBODC.getErrorList().getErrors()); 
           if(YLancioIntModProGesdatecBODC.getConflict() != null) 
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
     if(YLancioIntModProGesdatecBODC != null && !YLancioIntModProGesdatecBODC.close(false)) 
        errors.addAll(0, YLancioIntModProGesdatecBODC.getErrorList().getErrors()); 
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
     String errorPage = YLancioIntModProGesdatecForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YLancioIntModProGesdatecBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YLancioIntModProGesdatecForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
