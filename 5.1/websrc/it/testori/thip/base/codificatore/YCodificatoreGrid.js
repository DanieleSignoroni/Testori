var oldinit = init;

init = function() {
	oldinit();
	var oldenableAllCodificatori = enableAll;

	enableAll = function(gridClassCD) {
		oldenableAllCodificatori(gridClassCD);
		var curGrid = eval(editGrid[gridClassCD]);
		var curRow = curGrid.rows[currentRow];
		var descr = curRow.data[0];
		if (descr === "Range") {
			document.getElementById(gridClassCD + "$RangeInizio").parentNode.style.display = displayBlock;
			document.getElementById(gridClassCD + "$RangeFine").parentNode.style.display = displayBlock;
			document.getElementById(gridClassCD + "$RangeInizio").parentNode.previousElementSibling.style.display = displayBlock;
			document.getElementById(gridClassCD + "$RangeFine").parentNode.previousElementSibling.style.display = displayBlock;
			eval("document.forms[0].OkRow_" + gridClassCD).disabled = false;
			eval("document.forms[0].CancelRow_" + gridClassCD).disabled = false;
		} else {
			document.getElementById(gridClassCD + "$RangeInizio'").parentNode.previousElementSibling.style.display = displayNone;
			document.getElementById(gridClassCD + "$RangeFine").parentNode.previousElementSibling.style.display = displayNone;
			document.getElementById(gridClassCD + "$RangeInizio").parentNode.style.display = displayNone;
			document.getElementById(gridClassCD + "$RangeFine").parentNode.style.display = displayNone;
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
		var valoreUtente = isEm(valore.data[2]) ? NULL_VALUE : valore.data[2];
		var idVariabile = valore.data[5];
		var idValore = valore.data[6];

		return getSchemaKey() + fsep + idVariabile + fsep + idValore + "?" + valoreUtente + "|";
	} else {
		oldgetSintesi(valore);
	}
}