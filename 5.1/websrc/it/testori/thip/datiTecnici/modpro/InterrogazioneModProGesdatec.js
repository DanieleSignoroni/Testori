function YIntModProGesdatecOL() {
	addBackButton();
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
		if(conf){
			window.location = 'it/testori/thip/datiTecnici/modpro/LancioInterrogazioneModProGesdatec.jsp';
		}
	});
	targetDiv.appendChild(button);

}