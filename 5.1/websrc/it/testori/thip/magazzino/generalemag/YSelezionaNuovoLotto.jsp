<%@page import="com.thera.thermfw.base.IniFile"%>
<%@page import="com.thera.thermfw.persist.Column"%>
<jsp:include
	page="/it/thera/thip/magazzino/generalemag/SelezionaNuovoLotto.jsp"></jsp:include>
<%
Character mostraLotto = (Character) request.getAttribute("YMostraLotto");
if(mostraLotto != null && mostraLotto == Column.TRUE_CHAR){
	String url = "/" + IniFile.getValue("thermfw.ini", "Web", "WebApplicationPath") + "/" + IniFile.getValue("thermfw.ini", "Web", "ServletPath");
	url += "/it.thera.thip.magazzino.generalemag.web.LottoGridActionAdapter?thClassName=Lotto&thTarget=UPDATE";
	url += "&HightLightSubbioPezzaNoMandatory=true";
	%>	
		<script>
			var oldselezionaLotto = selezionaLotto;
			selezionaLotto = function(){
				oldselezionaLotto();
				window.parent.myDoEditDim('Lotti.RLotto.DescrizioneLotto','<%=url%>',800,600);
			}
		</script>
	<%
}
%>