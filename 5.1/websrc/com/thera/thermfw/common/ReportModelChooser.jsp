<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///D:\3rdParty\Panthera4.6.0\websrc\dtd/xhtml1-transitional.dtd">
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
  BODataCollector ReportModelSelectorBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm ReportModelSelectorForm =  
     new com.thera.thermfw.web.WebForm(request, response, "ReportModelSelectorForm", "ReportModelSelector", "Arial,10", "com.thera.thermfw.web.ReportModelSelectorFormActionAdapter", false, false, false, true, true, true, null, 1, false, "com/thera/thermfw/common/AllReportModelChooser.js"); 
  ReportModelSelectorForm.setServletEnvironment(se); 
  ReportModelSelectorForm.setJSTypeList(jsList); 
  ReportModelSelectorForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  ReportModelSelectorForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  ReportModelSelectorForm.setWebFormModifierClass("com.thera.thermfw.web.ReportModelSelectorFormModifier"); 
  ReportModelSelectorForm.setDeniedAttributeModeStr("hideAll"); 
  int mode = ReportModelSelectorForm.getMode(); 
  String key = ReportModelSelectorForm.getKey(); 
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
        ReportModelSelectorForm.outTraceInfo(getClass().getName()); 
        String collectorName = ReportModelSelectorForm.findBODataCollectorName(); 
                ReportModelSelectorBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (ReportModelSelectorBODC instanceof WebDataCollector) 
            ((WebDataCollector)ReportModelSelectorBODC).setServletEnvironment(se); 
        ReportModelSelectorBODC.initialize("ReportModelSelector", true, 1); 
        ReportModelSelectorForm.setBODataCollector(ReportModelSelectorBODC); 
        int rcBODC = ReportModelSelectorForm.initSecurityServices(); 
        mode = ReportModelSelectorForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           ReportModelSelectorForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = ReportModelSelectorBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              ReportModelSelectorForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 

	<!-- 47114 diverse modifiche -->
	<% 
  WebToolBar myToolBarTB = new com.thera.thermfw.web.WebToolBar("myToolBar", "24", "24", "16", "16", "#f7fbfd","#C8D6E1"); 
  myToolBarTB.setParent(ReportModelSelectorForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/com/thera/thermfw/common/ReportModelChooserMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
	<body bottommargin="0" leftmargin="0" onload="resize()" onbeforeunload="<%=ReportModelSelectorForm.getBodyOnBeforeUnload()%>" onload="<%=ReportModelSelectorForm.getBodyOnLoad()%>" onunload="<%=ReportModelSelectorForm.getBodyOnUnload()%>" rightmargin="0" topmargin="0"><%
   ReportModelSelectorForm.writeBodyStartElements(out); 
%> 

		<table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = ReportModelSelectorForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", ReportModelSelectorBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=ReportModelSelectorForm.getServlet()%>" method="post" name="ReportModelChooserForm" style="height:100%"><%
  ReportModelSelectorForm.writeFormStartElements(out); 
%>

		<table cellpadding="0" cellspacing="0" id="emptyborder" width="100%">
		<tr>
		<td>
		<div id="Wait" style="display:none">
				<table cellpadding="2" cellspacing="0" height="100%" id="emptyborder" width="100%">
					<tr>
						<td>
							<table cellpadding="10">
								<tr>
									<td>
				Attendere elaborazione in corso...
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</td>
		</tr>
		<tr valign="top">
		<td align="top" width="100%">
		 <div id="AllForm">
				<table border="0" cellpadding="0" cellspacing="0" id="emptyborder" width="100%">
					<tr style="height:20px">
						<td style="height:0"><% myToolBarTB.writeChildren(out); %> 
</td>
					</tr>
					<tr style="vertical-align: top;height: 2px;">
						<td>
							<table cellpadding="0" style="margin-left:7px">
								<tr>
									<td style="height:30px">
										<label id="lab1" name="lab1" style="font-weight:bold;font-size:12pt"></label>
									</td>
								</tr>
								<tr>
									<td>
										<label id="lab2" name="lab2"></label>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="100%"> <!-- height="100%"-->
						<div style="max-height: 70vh; overflow: auto; padding-right: 10px;">
							<table border="0" cellpadding="0" cellspacing="5" id="header" name="header" width="98%">
								<tr>
									<td colspan="3" height="30" width="100%"><div id="ReportModelGridDiv"><% 
  WebSelectionTable ReportModelSelectorReportModelCD =  
     new com.thera.thermfw.web.WebSelectionTable("ReportModelSelector", "ReportModelCD", 8, new String[]{"ReportModelId", "Description", "DefaultPrinter", "Group", "CopyNumber", "PrintFileName"}, false); 
 ReportModelSelectorReportModelCD.setParent(ReportModelSelectorForm); 
 ReportModelSelectorReportModelCD.setOnSelectAction("singleCheck()"); 
 ReportModelSelectorReportModelCD.setGetAvailableElements("getAvailableReports"); 
  ReportModelSelectorReportModelCD.write(out); 
%>
<!--<span class="selectiontable" id="ReportModelGrid"></span>--></div></td>
								</tr>
							</table>
						</div>
						</td>
					</tr>
					
					
				</table>
			</div>
		</td>
		</tr>
		<tr valign="top">
		 <td>
							<div id="OtherInfoDivWithButton">
								<table border="0" cellpadding="0" cellspacing="0" id="header" name="header" width="100%">
									<!--Fix 13114 inizio-->
									<tr id="thStoreChoiceTR" style="display:none">
										<td>
											<input id="thStoreChoice" name="thStoreChoice" style="margin-left:145px" type="checkbox">
											<label class="thLabel" id="thStoreChoiceLabel">
 <% { WebLabelSimple label = new com.thera.thermfw.web.WebLabelSimple("com.thera.thermfw.common.resources.Common", "StoreChoiceLabel", null, null, null, null); 
 label.setParent(ReportModelSelectorForm); 
label.write(out); }%> 
</label>
										</td>
									</tr>
									<!--Fix 13114 fine-->
									<tr>
										<td>
											<div id="OtherInfoDiv">
												<table cellpadding="0" cellspacing="5" style="height:0%">
													<tr id="ExportTypeDiv">
														<td height="0" width="115px"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ReportModelSelector", "ExportType", null); 
   label.setParent(ReportModelSelectorForm); 
%><label class="<%=label.getClassType()%>" for="ExportType"><%label.write(out);%></label><%}%></td>
														<td height="20"><% 
  WebComboBox ReportModelSelectorExportType =  
     new com.thera.thermfw.web.WebComboBox("ReportModelSelector", "ExportType", null); 
  ReportModelSelectorExportType.setParent(ReportModelSelectorForm); 
  ReportModelSelectorExportType.setOnChange("exportChanged()"); 
%>
<select id="<%=ReportModelSelectorExportType.getId()%>" name="<%=ReportModelSelectorExportType.getName()%>" size="1"><% 
  ReportModelSelectorExportType.write(out); 
%> 
</select></td>
													</tr>
													<tr>
														<td height="0" width="115px"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ReportModelSelector", "PrinterId", null); 
   label.setParent(ReportModelSelectorForm); 
%><label class="<%=label.getClassType()%>" for="Printer"><%label.write(out);%></label><%}%></td>
														<td height="0" width="10"><% 
  WebSearchComboBox ReportModelSelectorPrinter =  
     new com.thera.thermfw.web.WebSearchComboBox("ReportModelSelector", "Printer", null, 2, null, false, null); 
  ReportModelSelectorPrinter.setParent(ReportModelSelectorForm); 
  ReportModelSelectorPrinter.write(out); 
%>
<!--<span class="searchcombobox" id="Printer"></span>--></td>
														<td width="20"></td>
														<td height="0" style="white-space:nowrap"><% 
  WebCheckBox ReportModelSelectorOnlyGroupLeader =  
     new com.thera.thermfw.web.WebCheckBox("ReportModelSelector", "OnlyGroupLeader"); 
  ReportModelSelectorOnlyGroupLeader.setParent(ReportModelSelectorForm); 
%>
<input id="<%=ReportModelSelectorOnlyGroupLeader.getId()%>" name="<%=ReportModelSelectorOnlyGroupLeader.getName()%>" size="15" type="checkbox" value="Y"><%
  ReportModelSelectorOnlyGroupLeader.write(out); 
%>
</td>
													</tr>
													<tr id="CopyNumberDiv">
														<td height="0" width="115px"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ReportModelSelector", "CopyNumber", null); 
   label.setParent(ReportModelSelectorForm); 
%><label class="<%=label.getClassType()%>" for="CopyNumber"><%label.write(out);%></label><%}%></td>
														<td height="0"><% 
  WebTextInput ReportModelSelectorCopyNumber =  
     new com.thera.thermfw.web.WebTextInput("ReportModelSelector", "CopyNumber"); 
  ReportModelSelectorCopyNumber.setParent(ReportModelSelectorForm); 
%>
<input class="<%=ReportModelSelectorCopyNumber.getClassType()%>" id="<%=ReportModelSelectorCopyNumber.getId()%>" maxlength="<%=ReportModelSelectorCopyNumber.getMaxLength()%>" name="<%=ReportModelSelectorCopyNumber.getName()%>" size="5" type="text"><% 
  ReportModelSelectorCopyNumber.write(out); 
%>
</td>
													</tr>
													<tr id="PageRange">
														<td colspan="4">
														<table cellpadding="0" cellspacing="0" style="height:0%">
															<tr>
																<td height="0" width="120px"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ReportModelSelector", "PageFrom", null); 
   label.setParent(ReportModelSelectorForm); 
%><label class="<%=label.getClassType()%>" for="PageFrom"><%label.write(out);%></label><%}%></td>
																<td height="0"><% 
  WebTextInput ReportModelSelectorPageFrom =  
     new com.thera.thermfw.web.WebTextInput("ReportModelSelector", "PageFrom"); 
  ReportModelSelectorPageFrom.setParent(ReportModelSelectorForm); 
%>
<input class="<%=ReportModelSelectorPageFrom.getClassType()%>" id="<%=ReportModelSelectorPageFrom.getId()%>" maxlength="<%=ReportModelSelectorPageFrom.getMaxLength()%>" name="<%=ReportModelSelectorPageFrom.getName()%>" size="5" type="text"><% 
  ReportModelSelectorPageFrom.write(out); 
%>
</td>
																<td width="20"></td>
																<td height="0"><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "ReportModelSelector", "PageTo", null); 
   label.setParent(ReportModelSelectorForm); 
%><label class="<%=label.getClassType()%>" for="PageTo"><%label.write(out);%></label><%}%></td>
																<td width="20"></td>
																<td height="0"><% 
  WebTextInput ReportModelSelectorPageTo =  
     new com.thera.thermfw.web.WebTextInput("ReportModelSelector", "PageTo"); 
  ReportModelSelectorPageTo.setParent(ReportModelSelectorForm); 
%>
<input class="<%=ReportModelSelectorPageTo.getClassType()%>" id="<%=ReportModelSelectorPageTo.getId()%>" maxlength="<%=ReportModelSelectorPageTo.getMaxLength()%>" name="<%=ReportModelSelectorPageTo.getName()%>" size="5" type="text"><% 
  ReportModelSelectorPageTo.write(out); 
%>
</td>
															</tr>
														</table>
													</td>
													</tr>
													<tr>
														<td width="115"></td>
														<td colspan="3">
														<table cellpadding="0" cellspacing="0" style="width:100%">
															<tr>
																<td>
																	<% { 
   WebFormCustomization fc = new com.thera.thermfw.web.WebFormCustomization("FORM", "SSDCustomization"); 
  fc.setParent(ReportModelSelectorForm); 
  fc.customize(); } 
%>
<!--<span class="formcustomization" id="SSDCustomization"></span>-->
																</td>
																<td style="white-space:nowrap"><input id="enableSSD" name="enableSSD" style="display:none;" type="checkbox">
																<label id="enableSSDLbl" name="enableSSDLbl" style="display:none;"></label></td>
																<!-- 72327 Softre Inizio -->
																<td>
																			<% { 
   WebFormCustomization fc = new com.thera.thermfw.web.WebFormCustomization("FORM", "YAllegatiDigitaliCom"); 
  fc.setParent(ReportModelSelectorForm); 
  fc.customize(); } 
%>
<!--<span class="formcustomization" id="SSDCustomization"></span>-->
																</td>
																<!-- 72327 Softre Fine -->
															</tr>
														</table>
														</td>
													</tr>
													
												</table>
											</div>
										</td>
									</tr>
									<tr style="height:0%">
										<td>
											
<% 
  WebButton OkWebButton = new WebButton(); 
  OkWebButton.setImage(null); 
  OkWebButton.setResourceFile("com.thera.thermfw.common.resources.Common"); 
  OkWebButton.setResourceId("Print"); 
  OkWebButton.setResourceTooltipId(null); 
%>
<button class=" <%=OkWebButton.getButtonCSSClass()%>" id="Ok" name="Ok" onclick="okBut()" style="width:60; margin-left:5px" title="<%=OkWebButton.getTitle()%>" type="button"><%OkWebButton.getBtnContent(out);%></button>
										</td>
									</tr>
									<tr style="height:7px">
										<td></td>
									</tr>
								</table>
							</div>
						</td>
			</tr>
			<tr>
			<td>
			<% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(ReportModelSelectorForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
			</td>
		 </tr>
		</table>
		<%
  ReportModelSelectorForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = ReportModelSelectorForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", ReportModelSelectorBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


	<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              ReportModelSelectorForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, ReportModelSelectorBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, ReportModelSelectorBODC.getErrorList().getErrors()); 
           if(ReportModelSelectorBODC.getConflict() != null) 
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
     if(ReportModelSelectorBODC != null && !ReportModelSelectorBODC.close(false)) 
        errors.addAll(0, ReportModelSelectorBODC.getErrorList().getErrors()); 
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
     String errorPage = ReportModelSelectorForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", ReportModelSelectorBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = ReportModelSelectorForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
	<script language="JavaScript1.2">
	if (document.forms[0].PrinterId != null)
		document.forms[0].PrinterId.style.width="200px";
	/*
	if (typeof(printSingleCopy) == 'undefined') {
		if (document.getElementById('CopyNumberDiv') != null)
			document.getElementById('CopyNumberDiv').style.display = 'none';
	}
	*/
	</script>
</html>
