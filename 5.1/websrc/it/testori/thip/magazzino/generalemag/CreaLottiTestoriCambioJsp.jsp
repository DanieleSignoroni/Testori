<%
String jspName = request.getParameter("jspName");
String className = request.getParameter("thClassName");
String url = jspName + "?thClassName="+className;
%>
<script>
	parent.window.location = '/' + parent.webAppPath + '/<%=url%>';
	
</script>