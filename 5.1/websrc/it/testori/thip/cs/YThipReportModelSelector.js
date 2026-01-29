var oldabilitaCheckGesDocumentale = abilitaCheckGesDocumentale;
var oldonlySsd = onlySsd;

abilitaCheckGesDocumentale = function(reportModel) {
	oldabilitaCheckGesDocumentale(reportModel);
	var ClassHdrName = document.forms[0].ClassHdrName.value;
	if (reportModel != "" && ClassHdrName.includes('ReportDdtBollaBatch')) {
		if (hasSSDAttivo[reportModel] == 'Y') {
			document.forms[0].AllegatiDigitaliCom.checked = true;
			document.getElementById('AllegatiDigitaliComLbl').style.display = displayBlock;
			document.forms[0].AllegatiDigitaliCom.style.display = displayBlock;
		} else {
			document.forms[0].AllegatiDigitaliCom.disabled = true;
			document.getElementById('AllegatiDigitaliComLbl').style.display = displayNone;
			document.forms[0].AllegatiDigitaliCom.style.display = displayNone;
		}
		if (hasSSDUtente[reportModel] == 'Y') {
			document.forms[0].AllegatiDigitaliCom.disabled = false;
			document.getElementById('AllegatiDigitaliComLbl').style.display = displayBlock;
			document.forms[0].AllegatiDigitaliCom.style.display = displayBlock;
		} else {
			document.forms[0].AllegatiDigitaliCom.disabled = true;
			document.getElementById('AllegatiDigitaliComLbl').style.display = displayNone;
			document.forms[0].AllegatiDigitaliCom.style.display = displayNone;
		}
	}
}

onlySsd = function(curRow, selected) {
	var curSSDFlag = eval('document.forms[0].enableSSD');
	var parentSSDFlag = eval('window.opener.document.forms[0].SSDEnabled');
	var ClassHdrName = document.forms[0].ClassHdrName.value;
	if (typeof (curSSDFlag) != 'undefined' && typeof (parentSSDFlag) != 'undefined') {
		if (ClassHdrName.includes('ReportDdtBollaBatch')) {
			var curAllegatiDigitaliComFlag = eval('document.forms[0].AllegatiDigitaliCom');
			var parentAllegatiDigitaliComFlag = eval('window.opener.document.forms[0].AllegatiDigitaliCom');
			if (typeof (curAllegatiDigitaliComFlag) != 'undefined' && typeof (parentAllegatiDigitaliComFlag) != 'undefined') {
				parentAllegatiDigitaliComFlag.checked = curAllegatiDigitaliComFlag.checked;
				if (curAllegatiDigitaliComFlag.checked)
					parentAllegatiDigitaliComFlag.value = 'Y';
				else
					parentAllegatiDigitaliComFlag.value = 'N';
			}
		}
	}
	oldonlySsd(curRow, selected);
}