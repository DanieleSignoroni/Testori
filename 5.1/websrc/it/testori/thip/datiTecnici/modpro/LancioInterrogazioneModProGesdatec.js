function YLancioIntModProGesdatecOL() {

}

function conferma() {
	var className = eval("document.forms[0].thClassName.value");
	runActionDirect('CONFERMA_INTERROGAZIONE', 'action_submit', className, '', 'errorsFrame', 'no');
}

function cambiaJSP(azioneRichiesta, className, key, additional) {
	var errorsView = eval(errorsViewName);
	errorsView.setMessage(loadMsg);
	conflictClosed = true;
	 var curr = new Date();
	var loc = "com.thera.thermfw.web.servlet.Execute?JSP=it/testori/thip/datiTecnici/modpro/InterrogazioneModProGesdatec.jsp?Mode=NEW" +
		'&thClassName=' + className +
		'&thTarget=NEW' +
		'&thAction=' + azioneRichiesta +
		'&thChiaveDatiSessione=' + chiaveDatiSessione +
		'&thErroriPresenti=' + erroriPresenti +
		additional +
		'&pagetime=' + curr.getTime();
	loc += "&EntityId=YIntModProGesda";

	if (caricaDaSessione)
		loc += '&thCaricaDaSessione=true';
	setLocationOnWindow(window, loc);
}