var model;
var humanIndex;
// Method that is called on page load
function initalize() {
	// --------------------------------------------------------------------------
	// You can call other methods you want to run when the page first loads here
	// --------------------------------------------------------------------------

	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getModel"); // Request type and URL
	if (!xhr) {
		alert("CORS not supported");
	}
	xhr.onload = function() {
		var responseText = xhr.responseText; // the text of the response
		model = JSON.parse(responseText);
		for (var i = 0; i <model.players.length ; i++) {
			addElementPara("pos"+i, model.players[i].playerName, "names");
			addElementImg("pos"+i, "pos"+i+"image");
		}
		//...initialization here if modelJSON is needed
		showHumanFirstCard();			//draw top card and display it
		document.getElementById('info').innerText = "You drew " + model.players[humanIndex].playerCards[0].name+".";
		console.log(responseText);
		if(model.roundNumber!=0) {
			document.getElementById('roundinfo').innerText = "Round " + model.roundNumber;
		}else {
			document.getElementById('roundinfo').innerText = "Round 1";
		}
		if(getNameOfCurrentWinner()==="You"){
			//if human player is the current winner
			showElement("selection");
		}else {
			showElement("nextRound");
		}

	};
	// We have done everything we need to prepare the CORS request, so send it
	xhr.send();

}



// This is a reusable method for creating a CORS request. Do not edit this.
function createCORSRequest(method, url) {
	var xhr = new XMLHttpRequest();
	if ("withCredentials" in xhr) {

		// Check if the XMLHttpRequest object has a "withCredentials" property.
		// "withCredentials" only exists on XMLHTTPRequest2 objects.
		xhr.open(method, url, true);

	} else if (typeof XDomainRequest != "undefined") {

		// Otherwise, check if XDomainRequest.
		// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
		xhr = new XDomainRequest();
		xhr.open(method, url);

	} else {

		// Otherwise, CORS is not supported by the browser.
		xhr = null;

	}
	return xhr;
}

function getHumanTopCardIndex() {
	var indexOfHP = model.indexOfHumanPlayer;
	var humanPlayer = model.players[indexOfHP];
	var index = humanPlayer.playerCards[0].cardIndex + 1;
	return index;
}

function showHumanFirstCard() {

	var i;
	for(i=0;i<model.players.length;i++){
		if(model.players[i].playerName==="You"){
			break;
		}
	}
	humanIndex=i;
	showElement('pos'+i+'image');
	document.getElementById('pos'+i+'image').src = "/assets/" + model.players[i].playerCards[0].cardIndex + ".png";
}

function getNameOfCurrentWinner() {
	var name = model.players[model.indexOfRoundWinner].playerName;
	// alert(name);
	return name;
}

function hideElement(id) {
	var whichEl =document.getElementById(id);
	whichEl.className = 'hidden';
}

function showElement(id) {
	var whichEl =document.getElementById(id);
	whichEl.className = 'displayblock';
}

function hideElementVis(id) {
	var whichEl =document.getElementById(id);
	whichEl.className = 'vishidden';
}

function twinkleCardDiv(id) {
	var whichEl =document.getElementById(id);
	whichEl.className += ' twinkle';
}

function twinkleCardDivStop(id) {
	var whichEl =document.getElementById(id);
	whichEl.className = 'col-md-4 carddiv';
}

function nextRound() {
	playARound(getHighestAttrId(model.indexOfRoundWinner));
	hideElement('nextRound');
}

function playARound(index) {
	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playARound?index=" + index);
	var xhr1 = createCORSRequest('GET', "http://localhost:7777/toptrumps/getModel");
	if (!xhr||!xhr1) {
		alert("CORS not supported");
	}
	xhr.onload = function (){
		xhr1.onload = function () {
			var responseText = xhr1.responseText; // the text of the response
			console.log(xhr1.readyState);
			console.log(responseText);
			var nameOfFormalWinner = model.players[model.indexOfRoundWinner].playerName;
			// var attrIdThisRound = getHighestAttrId(model.indexOfRoundWinner);
			model = JSON.parse(responseText);
			showAllCardInThisRound();
			if (!model.draw) {
				twinkleCardDiv("pos" + model.indexOfRoundWinner);
				document.getElementById('info').innerText = "Player " + nameOfFormalWinner + " chose "+model.attributeNames[model.indexOfCurrentAttribute+1] + ". The round winner is " + model.players[model.indexOfRoundWinner].playerName  ;
			}else{
				document.getElementById('info').innerText = "This round was a Draw, common pile now has " + model.communalPile.length + " cards.";
			}
			showCardCount();
		}
		xhr1.send();
		hideElement('selection');
		showElement('drawNextCard');
	}
	xhr.send();

}

function getHighestAttrId(playerid) {
	var index = 0;
	var max = 0;
	var characteristics = model.players[playerid].playerCards[0].attributes;
	for(var i=0;i<characteristics.length;i++) {	//for each attribute
		if(characteristics[i]>max) {
			max = characteristics[i];
			index = i;
		}
	}
	return index;
}

function sleep(d){
	for(var t = Date.now();Date.now() - t <= d;);
}

function showAllCardInThisRound() {
	var i;
	var indexInCTR = 0;
	for (i = 0; i < model.players.length; i++) {
		if(!model.players[i].eliminated){
			document.getElementById('pos'+i+'image').src = "/assets/" + model.cardsThisRound[indexInCTR].cardIndex + ".png";
			showElement('pos'+i+'image');
			indexInCTR++;
		}
	}
}

function clearCards() {
	for (var i = 0; i < model.players.length; i++) {
		hideElementVis("pos"+i+"image");
		twinkleCardDivStop("pos"+i);
	}
}

function drawNextCard() {
	if(!model.end){				//if game hasn't ended
		if(model.players[humanIndex].playerCards.length!=0){ //if human player hasn't been eliminated
			clearCards();
			showHumanFirstCard();
			hideElement("drawNextCard");
			if(model.players[model.indexOfRoundWinner].playerName==="You"){
				showElement("selection");
			}else{
				showElement("nextRound");
			}
			document.getElementById("roundinfo").innerText = "Round " + (model.roundNumber+1);
			document.getElementById('info').innerText = "You drew " + model.players[humanIndex].playerCards[0].name+".";
		}else{					//if player has lost
			document.getElementById('lose').style.display='block';		//show lose message
			document.getElementById('fade').style.display='block';
		}
	}else{				//if game ends

	}
}

function showStatsInfo() {
	document.getElementById('lose').style.display='none';
	document.getElementById('win').style.display='none';
	document.getElementById('fade').style.display='none';
	var xhr =createCORSRequest('GET', 'http://localhost:7777/toptrumps/autoPlay');
	if (!xhr) {
		alert("CORS not supported");
	}
	xhr.onload = function () {
		var xhr1 = createCORSRequest('GET', "http://localhost:7777/toptrumps/getModel");
		xhr1.onload = function () {
			var responseText = xhr1.responseText; // the text of the response
			model = JSON.parse(responseText);
			addElementPara("statsdiv", "After "+ model.roundNumber +" rounds, " + "the final winner is " + model.players[model.indexOfFinalWinner].playerName, "names");
			addElementPara("statsdiv", "Scores:\t\t\t", "scoretext");
			for (var i = 0; i < model.players.length; i++) {
				addElementPara("statsdiv", model.players[i].playerName + ": " + model.players[i].gameWon, "scoretext");
			}
			document.getElementById("statsinfo").style.display='block';
			document.getElementById("fade").style.display='block';
		}
		xhr1.send();
	}
	xhr.send();
}


function showCardCount() {
	var s = "Card Count:\n\n";
	for (var i = 0; i < model.players.length; i++) {
		s += model.players[i].playerName + ": " + model.players[i].playerCards.length + "\n"
	}
	document.getElementById('cardcount').innerText = s;
}

function addElementImg(obj, id) {
	var parent = document.getElementById(obj);
	var img = document.createElement("img");
	img.setAttribute("id", id);
	parent.appendChild(img);
}

function addElementPara(obj, text, classname) {
	var parent = document.getElementById(obj);
	var p = document.createElement("p");
	p.className = classname;
	var textNode = document.createTextNode(text);
	p.appendChild(textNode);
	parent.appendChild(p);
}

