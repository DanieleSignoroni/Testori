var oldabilitaCheckGesDocumentale = abilitaCheckGesDocumentale;

abilitaCheckGesDocumentale = function(reportModel) {
	oldabilitaCheckGesDocumentale(reportModel);
	var ClassHdrName = document.forms[0].ClassHdrName.value;
	if (reportModel != "" && ClassHdrName.includes('ReportDdtBollaBatch')) {
		if (hasSSDAttivo[reportModel] == 'Y') {
			document.forms[0].AllegatiDigitaliCom.checked = true;
			document.getElementById('AllegatiDigitaliComLbl').style.display = displayBlock;
			document.forms[0].AllegatiDigitaliCom.style.display = displayBlock;
		} else {
			document.forms[0].AllegatiDigitaliCom.checked = false;
			document.getElementById('AllegatiDigitaliComLbl').style.display = displayNone;
			document.forms[0].AllegatiDigitaliCom.style.display = displayNone;
		}
		if (hasSSDUtente[reportModel] == 'Y') {
			document.forms[0].AllegatiDigitaliCom.checked = true;
			document.getElementById('AllegatiDigitaliComLbl').style.display = displayBlock;
			document.forms[0].AllegatiDigitaliCom.style.display = displayBlock;
		} else {
			document.forms[0].AllegatiDigitaliCom.checked = false;
			document.getElementById('AllegatiDigitaliComLbl').style.display = displayNone;
			document.forms[0].AllegatiDigitaliCom.style.display = displayNone;
		}
	}
}