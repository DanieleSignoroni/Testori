var oldinit = init;

init = function() {
	oldinit();
	addIdFromName("Codificatore.RangeInizio", "Codificatore$RangeInizio");
	addIdFromName("Codificatore.RangeFine", "Codificatore$RangeFine");
	var oldenableAllCodificatori = enableAll;

	enableAll = function(gridClassCD) {
		oldenableAllCodificatori(gridClassCD);
		var curGrid = eval(editGrid[gridClassCD]);
		var curRow = curGrid.rows[currentRow];
		var descr = curRow.data[0];
		var fieldRI = document.forms[0].Codificatore$RangeInizio;
		var fieldRF = document.forms[0].Codificatore$RangeFine;
		if (descr === "Range") {
			fieldRI.removeAttribute('readonly');
			fieldRF.removeAttribute('readonly');
			setupNF(fieldRI, new StringType(fieldRI, true ,20), "", NLSFld[fieldRI.name], true, false, true, null, null);
			setupNF(fieldRF, new StringType(fieldRF, true ,20), "", NLSFld[fieldRF.name], true, false, true, null, null);
			fieldRI.parentNode.style.display = displayBlock;
			fieldRI.parentNode.previousElementSibling.style.display = displayBlock;
			fieldRF.parentNode.style.display = displayBlock;
			fieldRF.parentNode.previousElementSibling.style.display = displayBlock;
			eval("document.forms[0].OkRow_" + gridClassCD).disabled = false;
			eval("document.forms[0].CancelRow_" + gridClassCD).disabled = false;
		} else {
			fieldRI.parentNode.style.display = displayNone;
			fieldRI.parentNode.previousElementSibling.style.display = displayNone;
			fieldRF.parentNode.style.display = displayNone;
			fieldRF.parentNode.previousElementSibling.style.display = displayNone;
			setupNF(fieldRI, new StringType(fieldRI, false,20), "", NLSFld[fieldRI.name], true, false, false, null, null);
			setupNF(fieldRF, new StringType(fieldRF, false,20), "", NLSFld[fieldRF.name], true, false, false, null, null);
		}
	}
}

getValoriNonValorizzati = function getValoriNonValorizzati() {
	var valoriNonValorizzati = "";
	var valori = editGrid["Codificatore"].rows;
	for (var idx = 0; idx < valori.length; idx++) {
		if (isEm(valori[idx].data[2]) && valori[idx].data[0] != "Range") {
			valoriNonValorizzati += "\n        " + valori[idx].data[0];
		}
	}
	return valoriNonValorizzati;
}

var oldgetSintesi = getSintesi;

getSintesi = function getSintesi(valore) {
	var id = valore.data[0];
	if (id === "Range") {
		//var valoreUtente = isEm(valore.data[2]) ? NULL_VALUE : valore.data[2];
		var idVariabile = valore.data[5];
		var idValore = valore.data[6];
		var rangeInizio = valore.data[11];
		var reangeFine = valore.data[12];

		return getSchemaKey() + fsep + idVariabile + fsep + idValore + "?" + rangeInizio + fsep + reangeFine + "|";
	} else {
		return oldgetSintesi(valore);
	}
}