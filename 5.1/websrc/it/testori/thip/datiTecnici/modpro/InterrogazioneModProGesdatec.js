function YIntModProGesdatecOL() {
	addBackButton();
	addSaveButton();
}

function recuperaAltezzaPezza() {
	var url = "/" + webAppPath + "/" + servletPath + "/" + servletAltezzaPezza +
		"?IdArticolo="+document.forms[0].IdMaterialePrincipale.value;
	url +="&thClassName="+document.forms[0].thClassName.value;
	setLocationOnWindow(document.getElementById("AltezzaPezza").contentWindow, url);
}

function addBackButton() {
	const targetDiv = document.getElementById("header-obj-key");

	const button = document.createElement("button");
	button.innerText = "TORNA INDIETRO";
	button.type = "button";
	button.classList.add("pth-btn");

	button.addEventListener("click", function(e) {
		e.preventDefault();
		var conf = window.confirm('Sei sicuro di voler tornare alla schermata di lancio?')
		if (conf) {
			window.location = 'it/testori/thip/datiTecnici/modpro/LancioInterrogazioneModProGesdatec.jsp';
		}
	});
	targetDiv.appendChild(button);
}

function addSaveButton() {
	const targetDiv = document.getElementById("header-obj-key");

	const button = document.createElement("button");
	button.innerText = "SALVA";
	button.type = "button";
	button.classList.add("pth-btn");
	button.classList.add("btn-salva");

	button.addEventListener("click", function(e) {
		e.preventDefault();
		var conf = window.confirm('Sei sicuro di voler salvare le modifiche?')
		if (conf) {
			runActionDirect('SAVE_AND_CLOSE', 'action_submit', document.forms[0].thClassName.value, '', 'errorsFrame', 'no');
		}
	});
	targetDiv.appendChild(button);
}