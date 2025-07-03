function closeWindow() {
	debugger;
	if (typeof parent.enableFormActions != "undefined")
		parent.enableFormActions();
	var destination;
	var isTargetNew = true;
	var origin = window.parent.opener;
	var parentClassName = origin.document.forms[0].thClassName.value;
	origin.document.forms[0].LottiFilatiManufatti.value = params;
	var parentKey = window.parent.opener.document.forms[0].thKey.value;
	origin.runActionDirect('SUCCESSIVO','action_submit', parentClassName ,null,'same','no');
	parent.window.close();
}