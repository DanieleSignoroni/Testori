function codificaLottiTestori() {
	if (creaLottiTestoriAutomatico == null || creaLottiTestoriAutomatico == undefined || !creaLottiTestoriAutomatico)
		runActionDirect('CREA_LOTTI_TESTORI_NUOVO', 'action_submit', document.forms[0].thClassName.value, document.forms[0].thKey.value, 'new', 'no');
	else {
		var className = document.forms[0].thClassName.value;
		var key = document.forms[0].thKey.value;
		if (document.forms[0].GeneraLottiTsAuto != undefined && document.forms[0].GeneraLottiTsAuto != null) {
			document.forms[0].GeneraLottiTsAuto.checked = true;
			runActionDirect('SAVE', 'action_submit', className, key, 'errorsFrame', 'no');
		}
	}
}