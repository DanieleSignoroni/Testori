function codificaLottiTestori() {
	runActionDirect('CREA_LOTTI_TESTORI_NUOVO', 'action_submit', document.forms[0].thClassName.value, document.forms[0].thKey.value, 'new', 'no');
}