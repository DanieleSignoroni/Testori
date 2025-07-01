<!-- HANDGEN Thip 2.0 - multiBrowserGen = true -->
<html>
<head>
	<title>Untitled</title>
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
  com.thera.thermfw.pref.* ,
  it.thera.thip.base.codificatore.*
"%>
<%=WebGenerator.getMetaTagEmulate()%>
</head>

<style type="text/css">
.BUTTON {
	font-family : Arial;
	font-size : 9pt;
	background-color : #C8D6E1;
	height: 30px;
	width: 30px;
	vertical-align : middle;
}
</style>
<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%>/<%=IniFile.getValue("thermfw.ini","Web","WebApplicationPath")%>/">
<script language="JavaScript1.2">
	sCo=window.parent.parent.sCo;
	mCo=window.parent.parent.mCo;
	eCo=window.parent.parent.eCo;
	datSep = window.parent.parent.datSep;
	dateMask = window.parent.parent.dateMask;
	timSep = window.parent.parent.timSep;
	timeMask = window.parent.parent.timeMask;
	decSep = window.parent.parent.decSep;
	thousandsSeparator = window.parent.parent.thousandsSeparator;
	notAllowedKeyChar = window.parent.parent.notAllowedKeyChar;
	fsep = window.parent.parent.fsep;
</script>

<!--<script language='JavaScript1.2' type='text/javascript' src='thermweb/factory/gui/URLEncoder.js'></script> Fix 11323-->
<%=com.thera.thermfw.web.WebJSTypeList.getImportForJSLibrary("thermweb/factory/gui/URLEncoder.js", request)%><!-- Fix 11323 -->
<!--<script language='JavaScript1.2' type='text/javascript' src='thermweb/factory/gui/globals.js'></script> -->
<%=com.thera.thermfw.web.WebJSTypeList.getImportForJSLibrary("thermweb/factory/gui/globals.js", request)%><!-- Fix 11323 -->
<!--<script language='JavaScript1.2' type='text/javascript' src='thermweb/factory/gui/fullCk.js'></script> Fix 11323-->
<%=com.thera.thermfw.web.WebJSTypeList.getImportForJSLibrary("thermweb/factory/gui/fullCk.js", request)%><!-- Fix 11323 -->
<!--<script language='JavaScript1.2' type='text/javascript' src='thermweb/factory/gui/misc.js'></script> -->
<%=com.thera.thermfw.web.WebJSTypeList.getImportForJSLibrary("thermweb/factory/gui/misc.js", request)%><!-- Fix 11323 -->
<!--<script language='JavaScript1.2' type='text/javascript' src='thermweb/factory/gui/ckErrHnd.js'></script> -->
<%=com.thera.thermfw.web.WebJSTypeList.getImportForJSLibrary("thermweb/factory/gui/ckErrHnd.js", request)%><!-- Fix 11323 -->
<!--<script language='JavaScript1.2' type='text/javascript' src='thermweb/factory/gui/errHnd.js'></script> Fix 11323-->
<%=com.thera.thermfw.web.WebJSTypeList.getImportForJSLibrary("thermweb/factory/gui/errHnd.js", request)%><!-- Fix 11323 -->
<!--<script language='JavaScript1.2' type='text/javascript' src='thermweb/factory/gui/ErrorHandler.js'></script> Fix 11323-->
<%=com.thera.thermfw.web.WebJSTypeList.getImportForJSLibrary("thermweb/factory/gui/ErrorHandler.js", request)%><!-- Fix 11323 -->
<!--<script language='JavaScript1.2' type='text/javascript' SRC='thermweb/factory/gui/ErrorViewHandler.js'></script> Fix 11323-->
<%=com.thera.thermfw.web.WebJSTypeList.getImportForJSLibrary("thermweb/factory/gui/ErrorViewHandler.js", request)%><!-- Fix 11323 -->
<!--<script language='JavaScript1.2' type='text/javascript' SRC='thermweb/factory/gui/msg/formMsg_it.js'></script> Fix 11323-->
<%=com.thera.thermfw.web.WebJSTypeList.getImportForJSLibrary("thermweb/factory/gui/msg/formMsg_it.js", request)%><!-- Fix 11323 -->
<!--<script language='JavaScript1.2' type='text/javascript' src='thermweb/factory/gui/components/search.js'></script> Fix 11323-->
<%=com.thera.thermfw.web.WebJSTypeList.getImportForJSLibrary("thermweb/factory/gui/components/search.js", request)%><!-- Fix 11323 -->
<!--<script language='JavaScript1.2' type='text/javascript' SRC='thermweb/factory/gui/components/editgrid.js'></script>  Fix 11323-->
<%=com.thera.thermfw.web.WebJSTypeList.getImportForJSLibrary("thermweb/factory/gui/components/editgrid.js", request)%><!-- Fix 11323 -->
<!--<script language='JavaScript1.2' type='text/javascript' SRC='thermweb/factory/gui/therm.js'></script> Fix 11323-->
<%=com.thera.thermfw.web.WebJSTypeList.getImportForJSLibrary("thermweb/factory/gui/therm.js", request)%><!-- Fix 11323 -->
<!--<script language='JavaScript1.2' type='text/javascript' SRC='thermweb/factory/type/Type.js'></script> Fix 11323-->
<%=com.thera.thermfw.web.WebJSTypeList.getImportForJSLibrary("thermweb/factory/type/Type.js", request)%><!-- Fix 11323 -->
<!--<script language='JavaScript1.2' type='text/javascript' SRC='it/thera/thip/base/codificatore/CodificatoreGrid.js'></script> Fix 11323-->
<%=com.thera.thermfw.web.WebJSTypeList.getImportForJSLibrary("it/thera/thip/base/codificatore/CodificatoreGrid.js", request)%><!-- Fix 11323 -->

<!--<link href="thermweb/css/therm.css" rel="STYLESHEET" type="text/css" /> Fix 11323-->
<%=com.thera.thermfw.web.WebJSTypeList.getImportForCSS("thermweb/css/therm.css", request)%><!-- Fix 11323 -->
<!-- Fix 43704 - inizio -->
<% if(LayoutUtils.getInstance().isResponsiveLook(request.getSession())) { %>
	<%=WebJSTypeList.getImportForCSS("thermweb/css/responsiveLookPth.css", request)%>
	<%=WebJSTypeList.getImportForCSS("thermweb/css/responsiveFormLook.css", request)%>
<% } %>
<!-- Fix 43704 - fine -->
<!--<link href="com/thera/thermfw/common/form.css" rel="STYLESHEET" type="text/css"> Fix 11323-->
<%//=com.thera.thermfw.web.WebJSTypeList.getImportForCSS("com/thera/thermfw/common/form.css", request)//Fix 15036%><!-- Fix 11323 -->
<body style="background: var(--form-background)" bottommargin="0" leftmargin="0" rightmargin="0" topmargin="0" onload="javascript:init();"><!-- 43704 - aggiunto style tolto bgcolor -->
<form>
<script language="JavaScript1.2">
setupBackgroundColor();
</script>
<input type="Hidden" id="thMode" name="thMode" value="UPDATE"/>
<table width="98%" cellspacing="2" cellpadding="0" style="margin: 5 5 5 5;">
	<tr>
		<td >
    		<table>
        		<tr>
            		<td>
			    		<button class="BUTTON" type="button" onclick="addRowToGrid();">&gt;</button>
					</td>
				</tr>
				<tr>
		    		<td>
			    		<button class="BUTTON" type="button" onclick="removeRowFromGrid();">&lt;</button>
            		</td>
        		</tr>
				<tr>
				  	<td>
  						<input type="hidden" id="thEditGrid_Codificatore" name="thEditGrid_Codificatore" value>
					</td>
				</tr>
    		</table>
		</td>
<%
	String treeName = com.thera.thermfw.web.servlet.BaseServlet.getStringParameter(request, "sessionTreeName");
	WebTree codificatoreTree = (WebTree)session.getAttribute(treeName);
	int rows = 10;
	String prevStr = ResourceLoader.getString(WebGenerator.WEB_RESOURCE, "PagePrecedente");
	String nextStr = ResourceLoader.getString(WebGenerator.WEB_RESOURCE, "PageSuccessiva");
	WebLabelSimple descrizioneVariabileColumn      = new WebLabelSimple("it.thera.thip.base.codificatore.resources.Codificatore", "DescrizioneVariabile", null, null);
	WebLabelSimple descrizioneValoreColumn         = new WebLabelSimple("it.thera.thip.base.codificatore.resources.Codificatore", "DescrizioneValore", null, null);
	WebLabelSimple valoreColumn                    = new WebLabelSimple("it.thera.thip.base.codificatore.resources.Codificatore", "Valore", null, null);
	WebLabelSimple valoreInferioreIntervalloColumn = new WebLabelSimple("it.thera.thip.base.codificatore.resources.Codificatore", "ValoreInferioreIntervallo", null, null);
	WebLabelSimple valoreSuperioreIntervalloColumn = new WebLabelSimple("it.thera.thip.base.codificatore.resources.Codificatore", "ValoreSuperioreIntervallo", null, null);
	WebLabelSimple[] columns = new WebLabelSimple[]{descrizioneVariabileColumn, descrizioneValoreColumn, valoreColumn, valoreInferioreIntervalloColumn, valoreSuperioreIntervalloColumn};
%>
		<td width="100%" height="100%">
			<table height="100%" width="100%">
				<tr>
					<td valign="top">
<script language="JavaScript1.2">
  	editGrid["Codificatore"] = new grid("Codificatore", <%=rows%>, <%=columns.length%>, 1);
  	editGrid["Codificatore"].ofValue="di";
  	editGrid["Codificatore"].rowUrl="";
	editGrid["Codificatore"].gridType == "INT";
  	editGrid["Codificatore"].rowKeys= new Array();
  	editGrid["Codificatore"].fatherKeys= new Array();
  	editGridName[editGridName.length] = "Codificatore";
	editGrid["Codificatore"].columns[0] = new gridColumn("DescVariabile", "STRING", 0, false, false, false);
	editGrid["Codificatore"].columns[1] = new gridColumn("DescValore", "STRING", 0, false, false, false);
	editGrid["Codificatore"].columns[2] = new gridColumn("Valore", "STRING", 0, false, false, false);
	editGrid["Codificatore"].columns[3] = new gridColumn("LimiteInf", "STRING", 0, false, false, false);
	editGrid["Codificatore"].columns[4] = new gridColumn("LimiteSup", "STRING", 0, false, false, false);
	editGrid["Codificatore"].columns[5] = new gridColumn("IdVariabile", "STRING", 0, false, false, false);
	editGrid["Codificatore"].columns[6] = new gridColumn("IdValore", "STRING", 0, false, false, false);
	editGrid["Codificatore"].columns[7] = new gridColumn("Dimensione", "STRING", 0, false, false, false);
	editGrid["Codificatore"].columns[8] = new gridColumn("NumDecimali", "STRING", 0, false, false, false);
	editGrid["Codificatore"].columns[9] = new gridColumn("Tipo", "STRING", 0, false, false, false);
	editGrid["Codificatore"].columns[10] = new gridColumn("Range", "STRING", 0, false, false, false);
	editGrid["Codificatore"].columns[11] = new gridColumn("RangeInizio", "STRING", 0, false, false, false);
	editGrid["Codificatore"].columns[12] = new gridColumn("RangeFine", "STRING", 0, false, false, false);
</script>
<div class="grid_container">
  <table width="100%" class="eg_header_table" id="EditGrid_Codificatore" cellSpacing ="0" cellpadding="0"><thead><tr><TD class="eg_header">&nbsp;</TD><%for (int col = 0; col < columns.length; col++){%><TD class="eg_header"><%columns[col].write(out);%></TD><%}%></tr></thead><tbody><%for (int row = 0; row < rows; row++){%><TR class="eg_row" onclick='editGridSelectRow("Codificatore", <%=row%>, 0);'><TD class="eg_cell" width="20">&nbsp;</TD><%for (int col = 0; col < columns.length; col++){%><TD class="eg_cell" width = "" align="center" ondblclick = 'removeRowFromGrid();' >	&nbsp;</TD><%}%></TR><%}%></tbody></table>
	<table width="100%" class="edit_grid_footer eg_footer_table" id="footer_Codificatore" cellSpacing ="0"><thead><tr><td class="eg_footer" id="prev" bgcolor="#88C4FF" width="80" align="center"><button class="eg_footer_bt" type="button" onclick='javascript:editGridPrevTable("Codificatore");'>&lt;&lt; <%=prevStr%></button></td><td class="eg_footer" id="status" bgcolor="#88C4FF" width="" align="center" class="footer">&nbsp;</td><td class="eg_footer"id="next" bgcolor="#88C4FF" width="80" align="center"><button class="eg_footer_bt" type="button" onclick='javascript:editGridNextTable("Codificatore");'><%=nextStr%> &gt;&gt;</button></td></tr></thead></table>
	<input type="hidden" id="CopyRow_Codificatore" disabled>
	<input type="hidden" id="DeleteRow_Codificatore" disabled>
</div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2" height="20"></td>
	</tr>
<%
 	WebLegend definizioneValoreLegend = new WebLegend("it.thera.thip.base.codificatore.resources.Codificatore", "DefinizioneValore");
 	WebLabelSimple variabileLabel     = new WebLabelSimple("it.thera.thip.base.codificatore.resources.Codificatore", "Variabile", null, null);
 	WebLabelSimple valoreLabel        = new WebLabelSimple("it.thera.thip.base.codificatore.resources.Codificatore", "Valore", null, null);
	WebLabelSimple OkLabel 			  = new WebLabelSimple("com.thera.thermfw.gui.resources.Gui", "OkButton", null, null);
    WebLabelSimple CancelLabel 		  = new WebLabelSimple("com.thera.thermfw.gui.resources.Gui", "CancelButton", null, null);
%>
	<tr>
		<td colspan="2">
			<FIELDSET name="fid1"><legend><%definizioneValoreLegend.write(out);%></legend>
			<table>
				<tr>
					<td><%variabileLabel.write(out);%></td>
					<td>
						<input id="Codificatore$IdVariabile" name="Codificatore.IdVariabile" type="text" maxlength = 15 size = "15" value/>
						<button class="thShowSBut" type="button" name="thVariabileSearchBut" id = "thVariabileSearchBut" disabled>
							<img border="0" width="16" height="16" src="<%=StyleDefinition.getInstance().getImgPath("thermweb/image/gui/Search.gif")%>">
						</button>
						<button class="thShowSBut" type="button" name="thVariabileEditBut" id = "thVariabileEditBut" disabled>
   							<img border="0" width="16" height="16" src="<%=StyleDefinition.getInstance().getImgPath("thermweb/image/gui/Edit.gif")%>">
						</button>
						<input id="Codificatore$DescVariabile" name="Codificatore.DescVariabile" type="text" maxlength = 35 size = "35" value/>
					</td>
				</tr>
				<tr>
					<td><%valoreLabel.write(out);%></td>
					<td>
						<input id="Codificatore$Valore" name="Codificatore.Valore" type="text" class = '' maxlength = 15 size = "15" value/>
					</td>
					<!-- sofre -->
					<td style="display:none">Range Inizio</td>
					<td style="display:none">
						<input id="Codificatore$RangeInizio" name="Codificatore.RangeInizio" type="text" class = '' maxlength = 15 size = "15" value/>
					</td>
					<td style="display:none">Range Fine</td>
					<td style="display:none">
						<input id="Codificatore$RangeFine" name="Codificatore.RangeFine" type="text" class = '' maxlength = 15 size = "15" value/>
					</td>
				</tr>
			</table>
			<table>
				<tr>
	  				<td>
      					<button id="OkRow_Codificatore" name="OkRow_Codificatore" type="button" class="ok_button pth-btn" style="width:65px;" onclick="runOkButton('Codificatore');"><%OkLabel.write(out);%></button>
	  					&nbsp;
      					<button id="CancelRow_Codificatore" name="CancelRow_Codificatore" type="button" class="cancel_button pth-btn" style="width:65px; margin-left:5px;" onclick="runCancelButton('Codificatore');"><%CancelLabel.write(out);%></button>
	  				</td>
				</tr>
			</table>
			</FIELDSET>
		</td>
	</tr>
</table>
</form>
<script language="JavaScript1.2">
NULL_VALUE = '<%=com.thera.thermfw.gui.cnr.DOList.NULL_VALUE%>';
valoreNLS = '<%=ResourceLoader.getString("it.thera.thip.base.codificatore.resources.Codificatore", "Valore")%>';
fuoriLimitiErrorMessage = '<%=WebElement.formatStringForHTML(new ErrorMessage("THIP20T177").getText())%>';
</script>

</body>
</html>
