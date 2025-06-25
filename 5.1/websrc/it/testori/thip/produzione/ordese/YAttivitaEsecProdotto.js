var oldcodificaLottiTestori = codificaLottiTestori;

codificaLottiTestori = function() {
	if (creaLottiTestoriAutomatico == null || creaLottiTestoriAutomatico == undefined || !creaLottiTestoriAutomatico)
		oldcodificaLottiTestori();
	else {
		var className = document.forms[0].thClassName.value;
		var key = document.forms[0].thKey.value;
		document.forms[0].GeneraLottiTsAuto.checked = true;
		runActionDirect('SAVE','action_submit',className,key,'errorsFrame','no')
	}
}