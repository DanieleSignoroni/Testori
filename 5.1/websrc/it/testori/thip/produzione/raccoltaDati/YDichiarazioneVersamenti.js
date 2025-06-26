var oldCreaLotto = creaLotto;

creaLotto = function(index) {
	let varName = 'creaLottiTestoriAutomatico' + index;
	if (typeof window[varName] !== 'undefined' && window[varName] === true) {
		creaLottoTestori(index);
	} else {
		oldCreaLotto(index);
	}
}

function creaLottoTestori(i) {
	var url = "/" + webAppPath + "/" + servletPath + "/it.testori.thip.produzione.raccoltaDati.web.CreaLottoTestoriRilevDatiPrdTS";
	var params = "?thAction=CREA_LOTTO&thClassName=Lotto";
	var idAzienda = eval("document.forms[0]." + idFromName["IdAzienda"]).value;
	var idArticolo = eval("document.forms[0]." + idFromName["IdProdotto" + i]).value;
	var idMagazzino = eval("document.forms[0]." + idFromName["IdMagazzinoVrs" + i]).value;
	var idLotto = eval("document.forms[0]." + idFromName["IdProdottoLotto" + i]).value;
	fillParametriLotto();
	params += "&Tipo=" + tipo;
	params += "&NumeroOrdDoc=" + numeroOrdDoc;
	params += "&AnnoOrdDoc=" + annoOrdDoc;
	params += "&DataOrdDoc=" + dataOrdDoc;
	params += "&NumRiga=" + numRiga;
	params += "&NumRigaPadre=" + numRigaPadre;
	params += "&Provenienza=" + provenienza;
	params += "&IdAzienda=" + idAzienda;
	params += "&IdArticolo=" + URLEncode(idArticolo);
	params += "&IdVersione=" + versione;
	params += "&IdConfigurazione=" + configurazione;
	params += "&IdLotto=" + URLEncode(idLotto);
	params += "&IdMagazzino=" + URLEncode(idMagazzino);
	params += "&LottoField=IdProdottoLotto" + i;
	params += "&AziendaField=IdAzienda";
	params += "&ArticoloField=IdProdotto" + i;
	params += "&NoErrorList=true";
	setLocationOnWindow(document.getElementById("thNavFrame").contentWindow, url + params);
}