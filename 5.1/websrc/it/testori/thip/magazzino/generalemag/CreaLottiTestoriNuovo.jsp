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
  BODataCollector YRichDatiCreazLottiBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YRichDatiCreazLottiForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YRichDatiCreazLottiForm", "YRichDatiCreazLotti", null, "it.testori.thip.magazzino.generalemag.web.CreaLottiTestoriNuovoFormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/testori/thip/magazzino/generalemag/CreaLottiTestoriNuovo.js"); 
  YRichDatiCreazLottiForm.setServletEnvironment(se); 
  YRichDatiCreazLottiForm.setJSTypeList(jsList); 
  YRichDatiCreazLottiForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YRichDatiCreazLottiForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YRichDatiCreazLottiForm.setWebFormModifierClass("it.testori.thip.magazzino.generalemag.web.CreaLottiTestoriNuovoFormModifier"); 
  YRichDatiCreazLottiForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YRichDatiCreazLottiForm.getMode(); 
  String key = YRichDatiCreazLottiForm.getKey(); 
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
        YRichDatiCreazLottiForm.outTraceInfo(getClass().getName()); 
        String collectorName = YRichDatiCreazLottiForm.findBODataCollectorName(); 
                YRichDatiCreazLottiBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YRichDatiCreazLottiBODC instanceof WebDataCollector) 
            ((WebDataCollector)YRichDatiCreazLottiBODC).setServletEnvironment(se); 
        YRichDatiCreazLottiBODC.initialize("YRichDatiCreazLotti", true, 0); 
        YRichDatiCreazLottiForm.setBODataCollector(YRichDatiCreazLottiBODC); 
        int rcBODC = YRichDatiCreazLottiForm.initSecurityServices(); 
        mode = YRichDatiCreazLottiForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YRichDatiCreazLottiForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YRichDatiCreazLottiBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YRichDatiCreazLottiForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 

</head>
<body onbeforeunload="<%=YRichDatiCreazLottiForm.getBodyOnBeforeUnload()%>" onload="<%=YRichDatiCreazLottiForm.getBodyOnLoad()%>" onunload="<%=YRichDatiCreazLottiForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YRichDatiCreazLottiForm.writeBodyStartElements(out); 
%> 

	<table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YRichDatiCreazLottiForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YRichDatiCreazLottiBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YRichDatiCreazLottiForm.getServlet()%>" method="post" name="YRichDatiCreazLottiForm" style="height:100%"><%
  YRichDatiCreazLottiForm.writeFormStartElements(out); 
%>

		<table cellpadding="0" cellspacing="0" id="emptyborder" width="100%">
			<tr>
				<td><% 
  WebTextInput YRichDatiCreazLottiIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YRichDatiCreazLotti", "IdAzienda"); 
  YRichDatiCreazLottiIdAzienda.setParent(YRichDatiCreazLottiForm); 
%>
<input class="<%=YRichDatiCreazLottiIdAzienda.getClassType()%>" id="<%=YRichDatiCreazLottiIdAzienda.getId()%>" maxlength="<%=YRichDatiCreazLottiIdAzienda.getMaxLength()%>" name="<%=YRichDatiCreazLottiIdAzienda.getName()%>" size="<%=YRichDatiCreazLottiIdAzienda.getSize()%>" type="hidden"><% 
  YRichDatiCreazLottiIdAzienda.write(out); 
%>
</td>
			</tr>
			<tr>
				<td><% 
  WebTextInput YRichDatiCreazLottiChiaveSelezionato =  
     new com.thera.thermfw.web.WebTextInput("YRichDatiCreazLotti", "ChiaveSelezionato"); 
  YRichDatiCreazLottiChiaveSelezionato.setParent(YRichDatiCreazLottiForm); 
%>
<input class="<%=YRichDatiCreazLottiChiaveSelezionato.getClassType()%>" id="<%=YRichDatiCreazLottiChiaveSelezionato.getId()%>" maxlength="<%=YRichDatiCreazLottiChiaveSelezionato.getMaxLength()%>" name="<%=YRichDatiCreazLottiChiaveSelezionato.getName()%>" size="<%=YRichDatiCreazLottiChiaveSelezionato.getSize()%>" type="hidden"><% 
  YRichDatiCreazLottiChiaveSelezionato.write(out); 
%>
</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<td>
								<table>
									<thead>
										<tr>
											<th><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YRichDatiCreazLotti", "IdLotto", null); 
   label.setParent(YRichDatiCreazLottiForm); 
%><label class="<%=label.getClassType()%>" for="IdLotto"><%label.write(out);%></label><%}%></th>
											<th><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YRichDatiCreazLotti", "Quantita", null); 
   label.setParent(YRichDatiCreazLottiForm); 
%><label class="<%=label.getClassType()%>" for="Quantita"><%label.write(out);%></label><%}%></th>
											<th><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YRichDatiCreazLotti", "NumeroConfezioni", null); 
   label.setParent(YRichDatiCreazLottiForm); 
%><label class="<%=label.getClassType()%>" for="NumeroConfezioni"><%label.write(out);%></label><%}%></th>
											<th><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YRichDatiCreazLotti", "NumeroRocche", null); 
   label.setParent(YRichDatiCreazLottiForm); 
%><label class="<%=label.getClassType()%>" for="NumeroRocche"><%label.write(out);%></label><%}%></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><% 
  WebTextInput YRichDatiCreazLottiIdLotto =  
     new com.thera.thermfw.web.WebTextInput("YRichDatiCreazLotti", "IdLotto"); 
  YRichDatiCreazLottiIdLotto.setParent(YRichDatiCreazLottiForm); 
%>
<input class="<%=YRichDatiCreazLottiIdLotto.getClassType()%>" id="<%=YRichDatiCreazLottiIdLotto.getId()%>" maxlength="<%=YRichDatiCreazLottiIdLotto.getMaxLength()%>" name="<%=YRichDatiCreazLottiIdLotto.getName()%>" size="<%=YRichDatiCreazLottiIdLotto.getSize()%>" type="text"><% 
  YRichDatiCreazLottiIdLotto.write(out); 
%>
</td>
											<td><% 
  WebTextInput YRichDatiCreazLottiQuantita =  
     new com.thera.thermfw.web.WebTextInput("YRichDatiCreazLotti", "Quantita"); 
  YRichDatiCreazLottiQuantita.setParent(YRichDatiCreazLottiForm); 
%>
<input class="<%=YRichDatiCreazLottiQuantita.getClassType()%>" id="<%=YRichDatiCreazLottiQuantita.getId()%>" maxlength="<%=YRichDatiCreazLottiQuantita.getMaxLength()%>" name="<%=YRichDatiCreazLottiQuantita.getName()%>" size="<%=YRichDatiCreazLottiQuantita.getSize()%>" type="text"><% 
  YRichDatiCreazLottiQuantita.write(out); 
%>
</td>
											<td><% 
  WebTextInput YRichDatiCreazLottiNumeroConfezioni =  
     new com.thera.thermfw.web.WebTextInput("YRichDatiCreazLotti", "NumeroConfezioni"); 
  YRichDatiCreazLottiNumeroConfezioni.setParent(YRichDatiCreazLottiForm); 
%>
<input class="<%=YRichDatiCreazLottiNumeroConfezioni.getClassType()%>" id="<%=YRichDatiCreazLottiNumeroConfezioni.getId()%>" maxlength="<%=YRichDatiCreazLottiNumeroConfezioni.getMaxLength()%>" name="<%=YRichDatiCreazLottiNumeroConfezioni.getName()%>" size="<%=YRichDatiCreazLottiNumeroConfezioni.getSize()%>" type="text"><% 
  YRichDatiCreazLottiNumeroConfezioni.write(out); 
%>
</td>
											<td><% 
  WebTextInput YRichDatiCreazLottiNumeroRocche =  
     new com.thera.thermfw.web.WebTextInput("YRichDatiCreazLotti", "NumeroRocche"); 
  YRichDatiCreazLottiNumeroRocche.setParent(YRichDatiCreazLottiForm); 
%>
<input class="<%=YRichDatiCreazLottiNumeroRocche.getClassType()%>" id="<%=YRichDatiCreazLottiNumeroRocche.getId()%>" maxlength="<%=YRichDatiCreazLottiNumeroRocche.getMaxLength()%>" name="<%=YRichDatiCreazLottiNumeroRocche.getName()%>" size="<%=YRichDatiCreazLottiNumeroRocche.getSize()%>" type="text"><% 
  YRichDatiCreazLottiNumeroRocche.write(out); 
%>
</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table>
									<tr>
										<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YRichDatiCreazLotti", "LottoAcquisto", null); 
   label.setParent(YRichDatiCreazLottiForm); 
%><label class="<%=label.getClassType()%>" for="LottoAcquisto"><%label.write(out);%></label><%}%></td>
										<td><% 
  WebTextInput YRichDatiCreazLottiLottoAcquisto =  
     new com.thera.thermfw.web.WebTextInput("YRichDatiCreazLotti", "LottoAcquisto"); 
  YRichDatiCreazLottiLottoAcquisto.setParent(YRichDatiCreazLottiForm); 
%>
<input class="<%=YRichDatiCreazLottiLottoAcquisto.getClassType()%>" id="<%=YRichDatiCreazLottiLottoAcquisto.getId()%>" maxlength="<%=YRichDatiCreazLottiLottoAcquisto.getMaxLength()%>" name="<%=YRichDatiCreazLottiLottoAcquisto.getName()%>" size="<%=YRichDatiCreazLottiLottoAcquisto.getSize()%>" type="text"><% 
  YRichDatiCreazLottiLottoAcquisto.write(out); 
%>
</td>
									</tr>
									<tr>
										<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YRichDatiCreazLotti", "DataScadenza", null); 
   label.setParent(YRichDatiCreazLottiForm); 
%><label class="<%=label.getClassType()%>" for="DataScadenza"><%label.write(out);%></label><%}%></td>
										<td><% 
  WebTextInput YRichDatiCreazLottiDataScadenza =  
     new com.thera.thermfw.web.WebTextInput("YRichDatiCreazLotti", "DataScadenza"); 
  YRichDatiCreazLottiDataScadenza.setShowCalendarBtn(true); 
  YRichDatiCreazLottiDataScadenza.setParent(YRichDatiCreazLottiForm); 
%>
<input class="<%=YRichDatiCreazLottiDataScadenza.getClassType()%>" id="<%=YRichDatiCreazLottiDataScadenza.getId()%>" maxlength="<%=YRichDatiCreazLottiDataScadenza.getMaxLength()%>" name="<%=YRichDatiCreazLottiDataScadenza.getName()%>" size="<%=YRichDatiCreazLottiDataScadenza.getSize()%>" type="text"><% 
  YRichDatiCreazLottiDataScadenza.write(out); 
%>
</td>
									</tr>
									<tr>
										<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YRichDatiCreazLotti", "Note", null); 
   label.setParent(YRichDatiCreazLottiForm); 
%><label class="<%=label.getClassType()%>" for="Note"><%label.write(out);%></label><%}%></td>
										<td><% 
  WebTextInput YRichDatiCreazLottiNote =  
     new com.thera.thermfw.web.WebTextArea("YRichDatiCreazLotti", "Note"); 
  YRichDatiCreazLottiNote.setParent(YRichDatiCreazLottiForm); 
%>
<textarea class="<%=YRichDatiCreazLottiNote.getClassType()%>" cols="75" id="<%=YRichDatiCreazLottiNote.getId()%>" maxlength="<%=YRichDatiCreazLottiNote.getMaxLength()%>" name="<%=YRichDatiCreazLottiNote.getName()%>" rows="3" size="<%=YRichDatiCreazLottiNote.getSize()%>"></textarea><% 
  YRichDatiCreazLottiNote.write(out); 
%>
</td>
									</tr>
									<tr>
										<td>
											<button id="bottoneConferma" name="bottoneConferma" onclick="conferma()" style="width: 125px; display: inline; margin-top: 2.5rem; margin-left: 1rem" type="button">Conferma</button>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="height: 0"><% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(YRichDatiCreazLottiForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>--></td>
			</tr>
		</table>
	<%
  YRichDatiCreazLottiForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YRichDatiCreazLottiForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YRichDatiCreazLottiBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YRichDatiCreazLottiForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YRichDatiCreazLottiBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YRichDatiCreazLottiBODC.getErrorList().getErrors()); 
           if(YRichDatiCreazLottiBODC.getConflict() != null) 
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
     if(YRichDatiCreazLottiBODC != null && !YRichDatiCreazLottiBODC.close(false)) 
        errors.addAll(0, YRichDatiCreazLottiBODC.getErrorList().getErrors()); 
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
     String errorPage = YRichDatiCreazLottiForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YRichDatiCreazLottiBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YRichDatiCreazLottiForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
