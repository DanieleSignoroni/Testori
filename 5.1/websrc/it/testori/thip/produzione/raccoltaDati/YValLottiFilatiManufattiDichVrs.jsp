<%@page import="it.thera.thip.cs.ColonneFiltri"%>
<%@page import="com.thera.thermfw.web.WebElement"%>
<%@page import="com.thera.thermfw.base.IniFile"%>
<%@page import="com.thera.thermfw.persist.Factory"%>
<%@page import="com.thera.thermfw.web.ServletEnvironment"%>
<%@page import="java.sql.SQLException"%>
<%@page import="javax.naming.NamingException"%>
<%@page
	import="it.testori.thip.magazzino.generalemag.CreaLottiTestoriNuovo"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "..\..\..\..\DTD\tfml.dtd">

<html>
<!-- HANDGEN Therm 2.0.1  multiBrowserGen = true -->
<head>
<title>Error Handler</title>
<%=com.thera.thermfw.web.WebGenerator.getMetaTagEmulate()%>
<base
	href="http://<%=request.getServerName()%>:<%=request.getServerPort()%>/<%=IniFile.getValue("thermfw.ini", "Web", "WebApplicationPath")%>/">
</head>
<body onload="closeWindow()">
	<FORM NAME="newForm">
		<%=com.thera.thermfw.web.WebJSTypeList.getImportForJSLibrary("it/testori/thip/produzione/raccoltaDati/YValLottiFilatiManufattiDichVrs.js", request)%><!-- Fix 10979 -->
		<%
		ServletEnvironment se = (ServletEnvironment) Factory.createObject("com.thera.thermfw.web.ServletEnvironment");
		try {
			se.initialize(request, response);
			if (se.begin()) {
				CreaLottiTestoriNuovo bo = (CreaLottiTestoriNuovo) request.getAttribute("CreaLottiTestoriNuovo");
				String[] params = bo.getChiaveSelezionato().split(ColonneFiltri.LISTA_SEP);
				String className = params[0];
				String thKey = params[1];
		%>
		<SCRIPT language=JavaScript type=text/javascript>
			var params = "<%=bo.iLottiRilevDatiPrdTS%>";
			var thKey = "<%=thKey%>";
		</SCRIPT>
		<%
		}
		} catch (NamingException e) {
		e.printStackTrace();
		} catch (SQLException e) {
		e.printStackTrace();
		} finally {
		try {
		se.end();
		} catch (IllegalArgumentException e) {
		e.printStackTrace();
		} catch (SQLException e) {
		e.printStackTrace();
		}
		}
		%>
	</FORM>
</body>
</html>