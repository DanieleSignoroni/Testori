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
  BODataCollector YIntModProGesdatecBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YIntModProGesdatecForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YIntModProGesdatecForm", "YIntModProGesdatec", null, "it.testori.thip.datiTecnici.modpro.web.InterrogazioneModProGesdatecFormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/testori/thip/datiTecnici/modpro/InterrogazioneModProGesdatec.js"); 
  YIntModProGesdatecForm.setServletEnvironment(se); 
  YIntModProGesdatecForm.setJSTypeList(jsList); 
  YIntModProGesdatecForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YIntModProGesdatecForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YIntModProGesdatecForm.setWebFormModifierClass("it.testori.thip.datiTecnici.modpro.web.InterrogazioneModProGesdatecFormModifier"); 
  YIntModProGesdatecForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YIntModProGesdatecForm.getMode(); 
  String key = YIntModProGesdatecForm.getKey(); 
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
        YIntModProGesdatecForm.outTraceInfo(getClass().getName()); 
        String collectorName = YIntModProGesdatecForm.findBODataCollectorName(); 
                YIntModProGesdatecBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YIntModProGesdatecBODC instanceof WebDataCollector) 
            ((WebDataCollector)YIntModProGesdatecBODC).setServletEnvironment(se); 
        YIntModProGesdatecBODC.initialize("YIntModProGesdatec", true, 0); 
        YIntModProGesdatecForm.setBODataCollector(YIntModProGesdatecBODC); 
        int rcBODC = YIntModProGesdatecForm.initSecurityServices(); 
        mode = YIntModProGesdatecForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YIntModProGesdatecForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YIntModProGesdatecBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YIntModProGesdatecForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
</head>
<body onbeforeunload="<%=YIntModProGesdatecForm.getBodyOnBeforeUnload()%>" onload="<%=YIntModProGesdatecForm.getBodyOnLoad()%>" onunload="<%=YIntModProGesdatecForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YIntModProGesdatecForm.writeBodyStartElements(out); 
%> 

	<table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YIntModProGesdatecForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YIntModProGesdatecBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YIntModProGesdatecForm.getServlet()%>" method="post" name="YIntModProGesdatecForm" style="height:100%"><%
  YIntModProGesdatecForm.writeFormStartElements(out); 
%>

		<table cellpadding="0" cellspacing="0" id="emptyborder" width="100%">
			<!--    <tr> -->
			<!--     <td style="height: 0"><span class="toolbar" id="myToolBar" /></td> -->
			<!--    </tr> -->
			<tr>
				<td><% 
  WebTextInput YIntModProGesdatecIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YIntModProGesdatec", "IdAzienda"); 
  YIntModProGesdatecIdAzienda.setParent(YIntModProGesdatecForm); 
%>
<input class="<%=YIntModProGesdatecIdAzienda.getClassType()%>" id="<%=YIntModProGesdatecIdAzienda.getId()%>" maxlength="<%=YIntModProGesdatecIdAzienda.getMaxLength()%>" name="<%=YIntModProGesdatecIdAzienda.getName()%>" size="<%=YIntModProGesdatecIdAzienda.getSize()%>" type="hidden"><% 
  YIntModProGesdatecIdAzienda.write(out); 
%>
</td>
			</tr>
			<tr style="display: none;">
				<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YIntModProGesdatec", "IdModelloProdOriginale", null); 
   label.setParent(YIntModProGesdatecForm); 
%><label class="<%=label.getClassType()%>" for="ModelloProduttivoOrig"><%label.write(out);%></label><%}%></td>
				<td><% 
  WebMultiSearchForm YIntModProGesdatecModelloProduttivoOrig =  
     new com.thera.thermfw.web.WebMultiSearchForm("YIntModProGesdatec", "ModelloProduttivoOrig", false, false, true, 1, null, null); 
  YIntModProGesdatecModelloProduttivoOrig.setParent(YIntModProGesdatecForm); 
  YIntModProGesdatecModelloProduttivoOrig.write(out); 
%>
<!--<span class="multisearchform" id="ModelloProduttivoOrig"></span>--></td>
			</tr>
			<tr>
				<td><iframe frameborder="0" height="0px" id="AltezzaPezza" name="AltezzaPezza" style="visibility:hidden" width="0px"></iframe></td>
			</tr>
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td>
								<fieldset style="width: fit-content; display: inline;">
									<legend>Dati - Padre</legend>
									<table>
										<tr>
											<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YIntModProGesdatec", "IdArticoloLancio", null); 
   label.setParent(YIntModProGesdatecForm); 
%><label class="<%=label.getClassType()%>" for="ArticoloPadre"><%label.write(out);%></label><%}%></td>
											<td><% 
  WebMultiSearchForm YIntModProGesdatecArticoloPadre =  
     new it.thera.thip.base.articolo.web.ArticoloMultiSearchForm("YIntModProGesdatec", "ArticoloPadre", false, false, true, 1, null, null); 
  YIntModProGesdatecArticoloPadre.setParent(YIntModProGesdatecForm); 
  YIntModProGesdatecArticoloPadre.write(out); 
%>
<!--<span class="articolomultisearchform" id="ArticoloPadre"></span>--></td>
										</tr>
										<tr>
											<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YIntModProGesdatec", "IdMaterialePrincipale", null); 
   label.setParent(YIntModProGesdatecForm); 
%><label class="<%=label.getClassType()%>" for="MaterialePrincipale"><%label.write(out);%></label><%}%></td>
											<td><% 
  WebMultiSearchForm YIntModProGesdatecMaterialePrincipale =  
     new com.thera.thermfw.web.WebMultiSearchForm("YIntModProGesdatec", "MaterialePrincipale", false, false, true, 1, null, null); 
  YIntModProGesdatecMaterialePrincipale.setParent(YIntModProGesdatecForm); 
  YIntModProGesdatecMaterialePrincipale.setOnKeyChange("recuperaAltezzaPezza()"); 
  YIntModProGesdatecMaterialePrincipale.write(out); 
%>
<!--<span class="multisearchform" id="MaterialePrincipale"></span>--></td>
										</tr>
										<tr>
											<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YIntModProGesdatec", "IdPoliticaRiordino", null); 
   label.setParent(YIntModProGesdatecForm); 
%><label class="<%=label.getClassType()%>" for="PoliticaRiordino"><%label.write(out);%></label><%}%></td>
											<td><% 
  WebMultiSearchForm YIntModProGesdatecPoliticaRiordino =  
     new com.thera.thermfw.web.WebMultiSearchForm("YIntModProGesdatec", "PoliticaRiordino", false, false, true, 1, null, null); 
  YIntModProGesdatecPoliticaRiordino.setParent(YIntModProGesdatecForm); 
  YIntModProGesdatecPoliticaRiordino.write(out); 
%>
<!--<span class="multisearchform" id="PoliticaRiordino"></span>--></td>
										</tr>
										<tr>
											<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YIntModProGesdatec", "IdAttivitaPrincipale", null); 
   label.setParent(YIntModProGesdatecForm); 
%><label class="<%=label.getClassType()%>" for="AttivitaPrincipale"><%label.write(out);%></label><%}%></td>
											<td><% 
  WebMultiSearchForm YIntModProGesdatecAttivitaPrincipale =  
     new com.thera.thermfw.web.WebMultiSearchForm("YIntModProGesdatec", "AttivitaPrincipale", false, false, true, 1, null, null); 
  YIntModProGesdatecAttivitaPrincipale.setParent(YIntModProGesdatecForm); 
  YIntModProGesdatecAttivitaPrincipale.write(out); 
%>
<!--<span class="multisearchform" id="AttivitaPrincipale"></span>--></td>
										</tr>
										<tr>
											<td colspan="2">
												<table>
													<tr>
														<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YIntModProGesdatec", "Priorita", null); 
   label.setParent(YIntModProGesdatecForm); 
%><label class="<%=label.getClassType()%>" for="Priorita"><%label.write(out);%></label><%}%></td>
														<td><% 
  WebTextInput YIntModProGesdatecPriorita =  
     new com.thera.thermfw.web.WebTextInput("YIntModProGesdatec", "Priorita"); 
  YIntModProGesdatecPriorita.setParent(YIntModProGesdatecForm); 
%>
<input class="<%=YIntModProGesdatecPriorita.getClassType()%>" id="<%=YIntModProGesdatecPriorita.getId()%>" maxlength="<%=YIntModProGesdatecPriorita.getMaxLength()%>" name="<%=YIntModProGesdatecPriorita.getName()%>" size="<%=YIntModProGesdatecPriorita.getSize()%>" type="text"><% 
  YIntModProGesdatecPriorita.write(out); 
%>
</td>
														<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YIntModProGesdatec", "AltezzaEffettiva", null); 
   label.setParent(YIntModProGesdatecForm); 
%><label class="<%=label.getClassType()%>" for="AltezzaEffettiva"><%label.write(out);%></label><%}%></td>
														<td><% 
  WebTextInput YIntModProGesdatecAltezzaEffettiva =  
     new com.thera.thermfw.web.WebTextInput("YIntModProGesdatec", "AltezzaEffettiva"); 
  YIntModProGesdatecAltezzaEffettiva.setParent(YIntModProGesdatecForm); 
%>
<input class="<%=YIntModProGesdatecAltezzaEffettiva.getClassType()%>" id="<%=YIntModProGesdatecAltezzaEffettiva.getId()%>" maxlength="<%=YIntModProGesdatecAltezzaEffettiva.getMaxLength()%>" name="<%=YIntModProGesdatecAltezzaEffettiva.getName()%>" size="<%=YIntModProGesdatecAltezzaEffettiva.getSize()%>" type="text"><% 
  YIntModProGesdatecAltezzaEffettiva.write(out); 
%>
</td>
													</tr>
													<tr>
														<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YIntModProGesdatec", "AltezzaManufatto", null); 
   label.setParent(YIntModProGesdatecForm); 
%><label class="<%=label.getClassType()%>" for="AltezzaManufatto"><%label.write(out);%></label><%}%></td>
														<td><% 
  WebTextInput YIntModProGesdatecAltezzaManufatto =  
     new com.thera.thermfw.web.WebTextInput("YIntModProGesdatec", "AltezzaManufatto"); 
  YIntModProGesdatecAltezzaManufatto.setParent(YIntModProGesdatecForm); 
%>
<input class="<%=YIntModProGesdatecAltezzaManufatto.getClassType()%>" id="<%=YIntModProGesdatecAltezzaManufatto.getId()%>" maxlength="<%=YIntModProGesdatecAltezzaManufatto.getMaxLength()%>" name="<%=YIntModProGesdatecAltezzaManufatto.getName()%>" size="<%=YIntModProGesdatecAltezzaManufatto.getSize()%>" type="text"><% 
  YIntModProGesdatecAltezzaManufatto.write(out); 
%>
</td>
														<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YIntModProGesdatec", "LunghezzaManufatto", null); 
   label.setParent(YIntModProGesdatecForm); 
%><label class="<%=label.getClassType()%>" for="LunghezzaManufatto"><%label.write(out);%></label><%}%></td>
														<td><% 
  WebTextInput YIntModProGesdatecLunghezzaManufatto =  
     new com.thera.thermfw.web.WebTextInput("YIntModProGesdatec", "LunghezzaManufatto"); 
  YIntModProGesdatecLunghezzaManufatto.setParent(YIntModProGesdatecForm); 
%>
<input class="<%=YIntModProGesdatecLunghezzaManufatto.getClassType()%>" id="<%=YIntModProGesdatecLunghezzaManufatto.getId()%>" maxlength="<%=YIntModProGesdatecLunghezzaManufatto.getMaxLength()%>" name="<%=YIntModProGesdatecLunghezzaManufatto.getName()%>" size="<%=YIntModProGesdatecLunghezzaManufatto.getSize()%>" type="text"><% 
  YIntModProGesdatecLunghezzaManufatto.write(out); 
%>
</td>
													</tr>
													<tr>
														<td><%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YIntModProGesdatec", "TempoUnitarioAtv", null); 
   label.setParent(YIntModProGesdatecForm); 
%><label class="<%=label.getClassType()%>" for="TempoUnitarioAtv"><%label.write(out);%></label><%}%></td>
														<td><% 
  WebTextInput YIntModProGesdatecTempoUnitarioAtv =  
     new com.thera.thermfw.web.WebTextInput("YIntModProGesdatec", "TempoUnitarioAtv"); 
  YIntModProGesdatecTempoUnitarioAtv.setParent(YIntModProGesdatecForm); 
%>
<input class="<%=YIntModProGesdatecTempoUnitarioAtv.getClassType()%>" id="<%=YIntModProGesdatecTempoUnitarioAtv.getId()%>" maxlength="<%=YIntModProGesdatecTempoUnitarioAtv.getMaxLength()%>" name="<%=YIntModProGesdatecTempoUnitarioAtv.getName()%>" size="<%=YIntModProGesdatecTempoUnitarioAtv.getSize()%>" type="text"><% 
  YIntModProGesdatecTempoUnitarioAtv.write(out); 
%>
</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
						<tr>
							<td>
								<table border="0" height="300px" style="margin: 0 0 0 2;" width="99%">
									<tr>
										<td><!--<span class="ajaxgrid" id="RigheMateriale">--><% 
  WebAjaxGrid YIntModProGesdatecRigheMateriale =  
     new com.thera.thermfw.web.WebAjaxGrid("YIntModProGesdatec", "RigheMateriale", 5, new String[]{"IdAzienda", "IdMateriale", "Materiale.DescrizioneArticolo.Descrizione", "IdUnitaMisura", "UnitaMisura.Descrizione.Descrizione", "CoeffImpiego"}, "dependent", null, "it.testori.thip.datiTecnici.modpro.web.InterrogazioneModProMatWebAjaxGridController", true, null,null,"A"); 
 YIntModProGesdatecRigheMateriale.setParent(YIntModProGesdatecForm); 
 YIntModProGesdatecRigheMateriale.setNoControlRowKeys(true); 
 YIntModProGesdatecRigheMateriale.setOrderByClassAD(null); 
 YIntModProGesdatecRigheMateriale.setOrderByWay("A"); 
 YIntModProGesdatecRigheMateriale.addOnChange("IdMateriale"); 
 YIntModProGesdatecRigheMateriale.addOnChange("IdUnitaMisura"); 
 YIntModProGesdatecRigheMateriale.addHidden("IdAzienda"); 
 YIntModProGesdatecRigheMateriale.addWidth("IdMateriale", "180"); 
 YIntModProGesdatecRigheMateriale.addWidth("UnitaMisura.Descrizione.Descrizione", "220"); 
 YIntModProGesdatecRigheMateriale.addWidth("IdUnitaMisura", "180"); 
 YIntModProGesdatecRigheMateriale.addWidth("Materiale.DescrizioneArticolo.Descrizione", "220"); 
 YIntModProGesdatecRigheMateriale.write(out); 
%>
<!--</span>--></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table border="0" height="300px" style="margin: 0 0 0 2;" width="99%">
									<tr>
										<td><!--<span class="ajaxgrid" id="RigheAttivita">--><% 
  WebAjaxGrid YIntModProGesdatecRigheAttivita =  
     new com.thera.thermfw.web.WebAjaxGrid("YIntModProGesdatec", "RigheAttivita", 5, new String[]{"IdAzienda", "IdAttivita", "Attivita.Descrizione.Descrizione", "Quantita"}, "dependent", null, "it.testori.thip.datiTecnici.modpro.web.InterrogazioneModProAtvWebAjaxGridController", true, null,null,"A"); 
 YIntModProGesdatecRigheAttivita.setParent(YIntModProGesdatecForm); 
 YIntModProGesdatecRigheAttivita.setNoControlRowKeys(true); 
 YIntModProGesdatecRigheAttivita.setOrderByClassAD(null); 
 YIntModProGesdatecRigheAttivita.setOrderByWay("A"); 
 YIntModProGesdatecRigheAttivita.addOnChange("IdAttivita"); 
 YIntModProGesdatecRigheAttivita.addHidden("IdAzienda"); 
 YIntModProGesdatecRigheAttivita.addWidth("Attivita.Descrizione.Descrizione", "220"); 
 YIntModProGesdatecRigheAttivita.addWidth("IdAttivita", "180"); 
 YIntModProGesdatecRigheAttivita.write(out); 
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
				<td style="height: 0"><% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(YIntModProGesdatecForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>--></td>
			</tr>
		</table>
	<%
  YIntModProGesdatecForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YIntModProGesdatecForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YIntModProGesdatecBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YIntModProGesdatecForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YIntModProGesdatecBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YIntModProGesdatecBODC.getErrorList().getErrors()); 
           if(YIntModProGesdatecBODC.getConflict() != null) 
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
     if(YIntModProGesdatecBODC != null && !YIntModProGesdatecBODC.close(false)) 
        errors.addAll(0, YIntModProGesdatecBODC.getErrorList().getErrors()); 
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
     String errorPage = YIntModProGesdatecForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YIntModProGesdatecBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YIntModProGesdatecForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
