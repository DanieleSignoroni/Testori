function YAllegatiOrdVenRigPrmOL() {
	var mode = document.forms[0].thMode.value;
	if (mode === "NEW") {
		document.forms[0].AnnoDocumento.value = parent.opener.document.forms[0].AnnoOrdine.value;
		document.forms[0].NumeroDocumento.value = parent.opener.document.forms[0].NumeroOrdine.value;
		document.forms[0].NumeroRigaDocumento.value = parent.opener.document.forms[0].RigaOrdine.value;
	}
}
