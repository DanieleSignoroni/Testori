function YSceltaTlSpUdsOL() {

}

function conferma() {
	var className = eval("document.forms[0].thClassName.value");
	runActionDirect('SAVE', 'action_submit', className, '', 'errorsFrame', 'no');
}

function spostaUdsFn() {
	let selectedKeys = document.getElementById('righe').contentWindow.getSelectedKeys();
	if (selectedKeys != null && selectedKeys.length > 0) {
		let conf = window.confirm("Si conferma lo spostamento delle UDS  selezionate?");
		if (conf) {
			let chiavi = selectedKeys.join(';');
			document.forms[0].ChiaviSelezionati.value = chiavi;
			document.forms[0].Sposta.checked = true;
			var className = eval("document.forms[0].thClassName.value");
			runActionDirect('SAVE', 'action_submit', className, '', 'errorsFrame', 'no');
		}
	} else {
		window.alert('Selezionare delle UDS');
	}
}

function indietroFn() {
	window.location.reload();
}