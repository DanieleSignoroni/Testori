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
  BODataCollector YCreaLottiTestoriTestataBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YCreaLottiTestoriTestataForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YCreaLottiTestoriTestataForm", "YCreaLottiTestoriTestata", null, "it.testori.thip.magazzino.generalemag.web.CreaLottiTestoriTestataFormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/testori/thip/magazzino/generalemag/CreaLottiTestoriTestata.js"); 
  YCreaLottiTestoriTestataForm.setServletEnvironment(se); 
  YCreaLottiTestoriTestataForm.setJSTypeList(jsList); 
  YCreaLottiTestoriTestataForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YCreaLottiTestoriTestataForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YCreaLottiTestoriTestataForm.setWebFormModifierClass("it.testori.thip.magazzino.generalemag.web.CreaLottiTestoriTestataFormModifier"); 
  YCreaLottiTestoriTestataForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YCreaLottiTestoriTestataForm.getMode(); 
  String key = YCreaLottiTestoriTestataForm.getKey(); 
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
        YCreaLottiTestoriTestataForm.outTraceInfo(getClass().getName()); 
        String collectorName = YCreaLottiTestoriTestataForm.findBODataCollectorName(); 
                YCreaLottiTestoriTestataBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YCreaLottiTestoriTestataBODC instanceof WebDataCollector) 
            ((WebDataCollector)YCreaLottiTestoriTestataBODC).setServletEnvironment(se); 
        YCreaLottiTestoriTestataBODC.initialize("YCreaLottiTestoriTestata", true, 0); 
        YCreaLottiTestoriTestataForm.setBODataCollector(YCreaLottiTestoriTestataBODC); 
        int rcBODC = YCreaLottiTestoriTestataForm.initSecurityServices(); 
        mode = YCreaLottiTestoriTestataForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YCreaLottiTestoriTestataForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YCreaLottiTestoriTestataBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YCreaLottiTestoriTestataForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 

</head>
<body onbeforeunload="<%=YCreaLottiTestoriTestataForm.getBodyOnBeforeUnload()%>" onload="<%=YCreaLottiTestoriTestataForm.getBodyOnLoad()%>" onunload="<%=YCreaLottiTestoriTestataForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YCreaLottiTestoriTestataForm.writeBodyStartElements(out); 
%> 

	<table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YCreaLottiTestoriTestataForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YCreaLottiTestoriTestataBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YCreaLottiTestoriTestataForm.getServlet()%>" method="post" name="YCreaLottiTestoriTestataForm" style="height:100%"><%
  YCreaLottiTestoriTestataForm.writeFormStartElements(out); 
%>

		<table cellpadding="0" cellspacing="0" id="emptyborder" width="100%">
			<tr>
				<td>
					<table>
						<tr>
							<td>
								<table>
									<thead>
										<tr>
											<th><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YCreaLottiTestoriTestata", "Documento", null); 
   label.setParent(YCreaLottiTestoriTestataForm); 
%><label class="<%=label.getClassType()%>" for="Documento"><%label.write(out);%></label><%}%></th>
											<th><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YCreaLottiTestoriTestata", "Soggetto", null); 
   label.setParent(YCreaLottiTestoriTestataForm); 
%><label class="<%=label.getClassType()%>" for="Soggetto"><%label.write(out);%></label><%}%></th>
											<th><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YCreaLottiTestoriTestata", "Quantita", null); 
   label.setParent(YCreaLottiTestoriTestataForm); 
%><label class="<%=label.getClassType()%>" for="Quantita"><%label.write(out);%></label><%}%></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><% 
  WebTextInput YCreaLottiTestoriTestataDocumento =  
     new com.thera.thermfw.web.WebTextInput("YCreaLottiTestoriTestata", "Documento"); 
  YCreaLottiTestoriTestataDocumento.setParent(YCreaLottiTestoriTestataForm); 
%>
<input class="<%=YCreaLottiTestoriTestataDocumento.getClassType()%>" id="<%=YCreaLottiTestoriTestataDocumento.getId()%>" maxlength="<%=YCreaLottiTestoriTestataDocumento.getMaxLength()%>" name="<%=YCreaLottiTestoriTestataDocumento.getName()%>" size="<%=YCreaLottiTestoriTestataDocumento.getSize()%>" type="text"><% 
  YCreaLottiTestoriTestataDocumento.write(out); 
%>
</td>
											<td><% 
  WebTextInput YCreaLottiTestoriTestataSoggetto =  
     new com.thera.thermfw.web.WebTextInput("YCreaLottiTestoriTestata", "Soggetto"); 
  YCreaLottiTestoriTestataSoggetto.setParent(YCreaLottiTestoriTestataForm); 
%>
<input class="<%=YCreaLottiTestoriTestataSoggetto.getClassType()%>" id="<%=YCreaLottiTestoriTestataSoggetto.getId()%>" maxlength="<%=YCreaLottiTestoriTestataSoggetto.getMaxLength()%>" name="<%=YCreaLottiTestoriTestataSoggetto.getName()%>" size="<%=YCreaLottiTestoriTestataSoggetto.getSize()%>" type="text"><% 
  YCreaLottiTestoriTestataSoggetto.write(out); 
%>
</td>
											<td><% 
  WebTextInput YCreaLottiTestoriTestataQuantita =  
     new com.thera.thermfw.web.WebTextInput("YCreaLottiTestoriTestata", "Quantita"); 
  YCreaLottiTestoriTestataQuantita.setParent(YCreaLottiTestoriTestataForm); 
%>
<input class="<%=YCreaLottiTestoriTestataQuantita.getClassType()%>" id="<%=YCreaLottiTestoriTestataQuantita.getId()%>" maxlength="<%=YCreaLottiTestoriTestataQuantita.getMaxLength()%>" name="<%=YCreaLottiTestoriTestataQuantita.getName()%>" size="<%=YCreaLottiTestoriTestataQuantita.getSize()%>" type="text"><% 
  YCreaLottiTestoriTestataQuantita.write(out); 
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
										<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YCreaLottiTestoriTestata", "IdArticolo", null); 
   label.setParent(YCreaLottiTestoriTestataForm); 
%><label class="<%=label.getClassType()%>" for="Articolo"><%label.write(out);%></label><%}%></td>
									</tr>
									<tr>
										<td><% 
  WebMultiSearchForm YCreaLottiTestoriTestataArticolo =  
     new com.thera.thermfw.web.WebMultiSearchForm("YCreaLottiTestoriTestata", "Articolo", false, false, true, 1, null, null); 
  YCreaLottiTestoriTestataArticolo.setParent(YCreaLottiTestoriTestataForm); 
  YCreaLottiTestoriTestataArticolo.write(out); 
%>
<!--<span class="multisearchform" id="Articolo"></span>--></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table border="0" class="resTableSmartGridRighe" style="margin: 0 0 0 2;" width="99%">
									<tr>
										<td><!--<span class="ajaxgrid" id="LottiDettaglio">--><% 
  WebAjaxGrid YCreaLottiTestoriTestataLottiDettaglio =  
     new com.thera.thermfw.web.WebAjaxGrid("YCreaLottiTestoriTestata", "LottiDettaglio", 25, new String[]{"IdAzienda", "IdLotto", "NumeroRocche", "Quantita"}, "dependent", null, "it.testori.thip.magazzino.generalemag.web.CreaLottiTestoriDettaglioWebAjaxGridController", true, null,null,"A"); 
 YCreaLottiTestoriTestataLottiDettaglio.setParent(YCreaLottiTestoriTestataForm); 
 YCreaLottiTestoriTestataLottiDettaglio.setNoControlRowKeys(true); 
 YCreaLottiTestoriTestataLottiDettaglio.setOrderByClassAD(null); 
 YCreaLottiTestoriTestataLottiDettaglio.setOrderByWay("A"); 
 YCreaLottiTestoriTestataLottiDettaglio.addHidden("IdAzienda"); 
 YCreaLottiTestoriTestataLottiDettaglio.write(out); 
%>
<!--</span>--></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td><% 
  WebTextInput YCreaLottiTestoriTestataIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YCreaLottiTestoriTestata", "IdAzienda"); 
  YCreaLottiTestoriTestataIdAzienda.setParent(YCreaLottiTestoriTestataForm); 
%>
<input class="<%=YCreaLottiTestoriTestataIdAzienda.getClassType()%>" id="<%=YCreaLottiTestoriTestataIdAzienda.getId()%>" maxlength="<%=YCreaLottiTestoriTestataIdAzienda.getMaxLength()%>" name="<%=YCreaLottiTestoriTestataIdAzienda.getName()%>" size="<%=YCreaLottiTestoriTestataIdAzienda.getSize()%>" type="hidden"><% 
  YCreaLottiTestoriTestataIdAzienda.write(out); 
%>
</td>
			</tr>
			<tr>
				<td><% 
  WebTextInput YCreaLottiTestoriTestataChiaveSelezionato =  
     new com.thera.thermfw.web.WebTextInput("YCreaLottiTestoriTestata", "ChiaveSelezionato"); 
  YCreaLottiTestoriTestataChiaveSelezionato.setParent(YCreaLottiTestoriTestataForm); 
%>
<input class="<%=YCreaLottiTestoriTestataChiaveSelezionato.getClassType()%>" id="<%=YCreaLottiTestoriTestataChiaveSelezionato.getId()%>" maxlength="<%=YCreaLottiTestoriTestataChiaveSelezionato.getMaxLength()%>" name="<%=YCreaLottiTestoriTestataChiaveSelezionato.getName()%>" size="<%=YCreaLottiTestoriTestataChiaveSelezionato.getSize()%>" type="hidden"><% 
  YCreaLottiTestoriTestataChiaveSelezionato.write(out); 
%>
</td>
			</tr>
			<tr>
				<td><% 
  WebTextInput YCreaLottiTestoriTestataIdLotto =  
     new com.thera.thermfw.web.WebTextInput("YCreaLottiTestoriTestata", "IdLotto"); 
  YCreaLottiTestoriTestataIdLotto.setParent(YCreaLottiTestoriTestataForm); 
%>
<input class="<%=YCreaLottiTestoriTestataIdLotto.getClassType()%>" id="<%=YCreaLottiTestoriTestataIdLotto.getId()%>" maxlength="<%=YCreaLottiTestoriTestataIdLotto.getMaxLength()%>" name="<%=YCreaLottiTestoriTestataIdLotto.getName()%>" size="<%=YCreaLottiTestoriTestataIdLotto.getSize()%>" type="hidden"><% 
  YCreaLottiTestoriTestataIdLotto.write(out); 
%>
</td>
			</tr>
			<tr>
				<td><% 
  WebTextInput YCreaLottiTestoriTestataNumeroConfezioni =  
     new com.thera.thermfw.web.WebTextInput("YCreaLottiTestoriTestata", "NumeroConfezioni"); 
  YCreaLottiTestoriTestataNumeroConfezioni.setParent(YCreaLottiTestoriTestataForm); 
%>
<input class="<%=YCreaLottiTestoriTestataNumeroConfezioni.getClassType()%>" id="<%=YCreaLottiTestoriTestataNumeroConfezioni.getId()%>" maxlength="<%=YCreaLottiTestoriTestataNumeroConfezioni.getMaxLength()%>" name="<%=YCreaLottiTestoriTestataNumeroConfezioni.getName()%>" size="<%=YCreaLottiTestoriTestataNumeroConfezioni.getSize()%>" type="hidden"><% 
  YCreaLottiTestoriTestataNumeroConfezioni.write(out); 
%>
</td>
			</tr>
			<tr>
				<td><% 
  WebTextInput YCreaLottiTestoriTestataLottoAcquisto =  
     new com.thera.thermfw.web.WebTextInput("YCreaLottiTestoriTestata", "LottoAcquisto"); 
  YCreaLottiTestoriTestataLottoAcquisto.setParent(YCreaLottiTestoriTestataForm); 
%>
<input class="<%=YCreaLottiTestoriTestataLottoAcquisto.getClassType()%>" id="<%=YCreaLottiTestoriTestataLottoAcquisto.getId()%>" maxlength="<%=YCreaLottiTestoriTestataLottoAcquisto.getMaxLength()%>" name="<%=YCreaLottiTestoriTestataLottoAcquisto.getName()%>" size="<%=YCreaLottiTestoriTestataLottoAcquisto.getSize()%>" type="hidden"><% 
  YCreaLottiTestoriTestataLottoAcquisto.write(out); 
%>
</td>
			</tr>
			<tr style="display: none;">
				<td><% 
  WebTextInput YCreaLottiTestoriTestataNote =  
     new com.thera.thermfw.web.WebTextArea("YCreaLottiTestoriTestata", "Note"); 
  YCreaLottiTestoriTestataNote.setParent(YCreaLottiTestoriTestataForm); 
%>
<textarea class="<%=YCreaLottiTestoriTestataNote.getClassType()%>" cols="75" id="<%=YCreaLottiTestoriTestataNote.getId()%>" maxlength="<%=YCreaLottiTestoriTestataNote.getMaxLength()%>" name="<%=YCreaLottiTestoriTestataNote.getName()%>" rows="3" size="<%=YCreaLottiTestoriTestataNote.getSize()%>"></textarea><% 
  YCreaLottiTestoriTestataNote.write(out); 
%>
</td>
			</tr>
			<tr>
				<td style="height: 0"><% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(YCreaLottiTestoriTestataForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>--></td>
			</tr>
		</table>
	<%
  YCreaLottiTestoriTestataForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YCreaLottiTestoriTestataForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YCreaLottiTestoriTestataBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YCreaLottiTestoriTestataForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YCreaLottiTestoriTestataBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YCreaLottiTestoriTestataBODC.getErrorList().getErrors()); 
           if(YCreaLottiTestoriTestataBODC.getConflict() != null) 
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
     if(YCreaLottiTestoriTestataBODC != null && !YCreaLottiTestoriTestataBODC.close(false)) 
        errors.addAll(0, YCreaLottiTestoriTestataBODC.getErrorList().getErrors()); 
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
     String errorPage = YCreaLottiTestoriTestataForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YCreaLottiTestoriTestataBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YCreaLottiTestoriTestataForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
