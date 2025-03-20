<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///W:\PthDev\Projects\Panthera\Testori\WebContent\dtd/xhtml1-transitional.dtd">
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
  BODataCollector YTempiRisorseAttivitaBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YTempiRisorseAttivitaForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YTempiRisorseAttivitaForm", "YTempiRisorseAttivita", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/testori/thip/datiTecnici/modpro/YTempiRisorseAttivita.js"); 
  YTempiRisorseAttivitaForm.setServletEnvironment(se); 
  YTempiRisorseAttivitaForm.setJSTypeList(jsList); 
  YTempiRisorseAttivitaForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YTempiRisorseAttivitaForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YTempiRisorseAttivitaForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YTempiRisorseAttivitaForm.getMode(); 
  String key = YTempiRisorseAttivitaForm.getKey(); 
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
        YTempiRisorseAttivitaForm.outTraceInfo(getClass().getName()); 
        String collectorName = YTempiRisorseAttivitaForm.findBODataCollectorName(); 
                YTempiRisorseAttivitaBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YTempiRisorseAttivitaBODC instanceof WebDataCollector) 
            ((WebDataCollector)YTempiRisorseAttivitaBODC).setServletEnvironment(se); 
        YTempiRisorseAttivitaBODC.initialize("YTempiRisorseAttivita", true, 0); 
        YTempiRisorseAttivitaForm.setBODataCollector(YTempiRisorseAttivitaBODC); 
        int rcBODC = YTempiRisorseAttivitaForm.initSecurityServices(); 
        mode = YTempiRisorseAttivitaForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YTempiRisorseAttivitaForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YTempiRisorseAttivitaBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YTempiRisorseAttivitaForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YTempiRisorseAttivitaForm); 
   request.setAttribute("menuBar", menuBar); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="menuBar"/> 
</jsp:include> 
<% 
  menuBar.write(out); 
  menuBar.writeChildren(out); 
%> 
<% 
  WebToolBar myToolBarTB = new com.thera.thermfw.web.WebToolBar("myToolBar", "24", "24", "16", "16", "#f7fbfd","#C8D6E1"); 
  myToolBarTB.setParent(YTempiRisorseAttivitaForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YTempiRisorseAttivitaForm.getBodyOnBeforeUnload()%>" onload="<%=YTempiRisorseAttivitaForm.getBodyOnLoad()%>" onunload="<%=YTempiRisorseAttivitaForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YTempiRisorseAttivitaForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YTempiRisorseAttivitaForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YTempiRisorseAttivitaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YTempiRisorseAttivitaForm.getServlet()%>" method="post" name="YTempiRisorseAttivitaForm" style="height:100%"><%
  YTempiRisorseAttivitaForm.writeFormStartElements(out); 
%>

      <table cellpadding="0" cellspacing="0" height="100%" id="emptyborder" width="100%">
        <tr>
          <td style="height:0">
            <% menuBar.writeElements(out); %> 

          </td>
        </tr>
        <tr>
          <td style="height:0">
            <% myToolBarTB.writeChildren(out); %> 

          </td>
        </tr>
        <tr>
          <td height="100%">
            <!--<span class="tabbed" id="mytabbed">-->
<table width="100%" height="100%" cellpadding="0" cellspacing="0" style="padding-right:1px">
   <tr valign="top">
     <td><% 
  WebTabbed mytabbed = new com.thera.thermfw.web.WebTabbed("mytabbed", "100%", "100%"); 
  mytabbed.setParent(YTempiRisorseAttivitaForm); 
 mytabbed.addTab("tab1", "it.testori.thip.datiTecnici.modpro.resources.YTempiRisorseAttivita", "tab1", "YTempiRisorseAttivita", null, null, null, null); 
  mytabbed.write(out); 
%>

     </td>
   </tr>
   <tr>
     <td height="100%"><div class="tabbed_pagine" id="tabbedPagine" style="position: relative; width: 100%; height: 100%;">
              <div class="tabbed_page" id="<%=mytabbed.getTabPageId("tab1")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("tab1"); %>
                <table style="width: 100%;">
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YTempiRisorseAttivita", "IdAttivita", null); 
   label.setParent(YTempiRisorseAttivitaForm); 
%><label class="<%=label.getClassType()%>" for="Attivita"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YTempiRisorseAttivitaAttivita =  
     new com.thera.thermfw.web.WebMultiSearchForm("YTempiRisorseAttivita", "Attivita", false, false, true, 1, null, null); 
  YTempiRisorseAttivitaAttivita.setParent(YTempiRisorseAttivitaForm); 
  YTempiRisorseAttivitaAttivita.write(out); 
%>
<!--<span class="multisearchform" id="Attivita"></span>-->
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YTempiRisorseAttivita", "IdRisorsa", null); 
   label.setParent(YTempiRisorseAttivitaForm); 
%><label class="<%=label.getClassType()%>" for="Risorsa"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YTempiRisorseAttivitaRisorsa =  
     new com.thera.thermfw.web.WebMultiSearchForm("YTempiRisorseAttivita", "Risorsa", false, false, true, 3, null, null); 
  YTempiRisorseAttivitaRisorsa.setParent(YTempiRisorseAttivitaForm); 
  YTempiRisorseAttivitaRisorsa.write(out); 
%>
<!--<span class="multisearchform" id="Risorsa"></span>-->
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                    </td>
                    <td valign="top">
                    </td>
                  </tr>
                  <tr>
                    <td colspan="2" valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YTempiRisorseAttivita", null, null); 
   label.setParent(YTempiRisorseAttivitaForm); 
%><label class="<%=label.getClassType()%>" for="YTempiRisorseAtvMinuti"><%label.write(out);%></label><%}%>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="2" valign="top">
                      <!--<span class="ajaxgrid" id="YTempiRisorseAtvMinuti">--><% 
  WebAjaxGrid YTempiRisorseAttivitaYTempiRisorseAtvMinuti =  
     new com.thera.thermfw.web.WebAjaxGrid("YTempiRisorseAttivita", "YTempiRisorseAtvMinuti", 10, new String[]{"FinoA", "IdRisorsa", "LivelloRisorsa", "TipoRisorsa", "IdAttivita", "IdAzienda", "Tempo", "Azienda.Descrizione"}, "independent" , null, true, null,null,"A"); 
 YTempiRisorseAttivitaYTempiRisorseAtvMinuti.setParent(YTempiRisorseAttivitaForm); 
 YTempiRisorseAttivitaYTempiRisorseAtvMinuti.setNoControlRowKeys(false); 
 YTempiRisorseAttivitaYTempiRisorseAtvMinuti.setOrderByClassAD(null); 
 YTempiRisorseAttivitaYTempiRisorseAtvMinuti.setOrderByWay("A"); 
 YTempiRisorseAttivitaYTempiRisorseAtvMinuti.addHideAsDefault("Azienda.Descrizione"); 
 YTempiRisorseAttivitaYTempiRisorseAtvMinuti.addHidden("TipoRisorsa"); 
 YTempiRisorseAttivitaYTempiRisorseAtvMinuti.addHidden("LivelloRisorsa"); 
 YTempiRisorseAttivitaYTempiRisorseAtvMinuti.addHidden("IdRisorsa"); 
 YTempiRisorseAttivitaYTempiRisorseAtvMinuti.addHidden("IdAttivita"); 
 YTempiRisorseAttivitaYTempiRisorseAtvMinuti.addHidden("IdAzienda"); 
 YTempiRisorseAttivitaYTempiRisorseAtvMinuti.write(out); 
%>
<!--</span>-->
                    </td>
                  </tr>
                </table>
              <% mytabbed.endTab(); %> 
</div>
            </div><% mytabbed.endTabbed();%> 

     </td>
   </tr>
</table><!--</span>-->
          </td>
        </tr>
        <tr>
          <td style="height:0">
            <% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(YTempiRisorseAttivitaForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YTempiRisorseAttivitaForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YTempiRisorseAttivitaForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YTempiRisorseAttivitaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YTempiRisorseAttivitaForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YTempiRisorseAttivitaBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YTempiRisorseAttivitaBODC.getErrorList().getErrors()); 
           if(YTempiRisorseAttivitaBODC.getConflict() != null) 
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
     if(YTempiRisorseAttivitaBODC != null && !YTempiRisorseAttivitaBODC.close(false)) 
        errors.addAll(0, YTempiRisorseAttivitaBODC.getErrorList().getErrors()); 
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
     String errorPage = YTempiRisorseAttivitaForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YTempiRisorseAttivitaBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YTempiRisorseAttivitaForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
