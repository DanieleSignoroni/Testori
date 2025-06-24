var oldcodificaLottiTestori = codificaLottiTestori;

codificaLottiTestori = function() {
	if (creaLottiTestoriAutomatico == null || creaLottiTestoriAutomatico == undefined)
		oldcodificaLottiTestori();
	else {
		var className = document.forms[0].thClassName.value;
		var key = document.forms[0].thKey.value;
		runActionDirect('CREA_LOTTI_TESTORI_AUTO', 'action_submit', className, key, 'same', 'no');
	}
}
