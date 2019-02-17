<html>

<head>
	<!-- Web page title -->
	<title>Top Trumps</title>

	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

	<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

	<!-- Had to make a following extra import -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">



</head>

<body onload="initalize()" class="mainPage"> <!-- Call the initalize method when the page loads -->

<div class="container">

	<!-- HTML Start-->

	<div class="container-fluid">
		<div id="id1" style="text-align: center; color: ghostwhite">Top Trumps</div>
	</div>
	<br><br><br><br><br>
	<div class="container">
		<div class="row text-center" style="margin-right: 30%;margin-left: 30%;">
			<button class="btn-default btn-xl" id="modalbtn">Play</button>
			<button class="btn-default btn-xl" onclick="location.href = 'http://localhost:7777/toptrumps/stats'">View Statistics</button>
		</div>
	</div>

	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id='text'>Select AI players</h4>
				</div>
				<div class="modal-body">
					<button type="button" class=" btn btn-warning btn-lg startgame" onclick="take2GamePage(1)">1</button>
					<button type="button" class=" btn btn-warning btn-lg startgame" onclick="take2GamePage(2)">2</button>
					<button type="button" class=" btn btn-warning btn-lg startgame" onclick="take2GamePage(3)">3</button>
					<button type="button" class=" btn btn-warning btn-lg startgame" onclick="take2GamePage(4)">4</button>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn" data-dismiss="modal" >Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- HTML End-->


	<script type="text/javascript">

		// Method that is called on page load
		function initalize() {


			// --------------------------------------------------------------------------
			// You can call other methods you want to run when the page first loads here
			// --------------------------------------------------------------------------

			// For example, lets call our sample methods


		}

		var x = document.getElementById('modalbtn');
		x.addEventListener('click',runmodal);

		function runmodal() {
			$("#myModal").modal();
		}

		function take2GamePage(numOfPlayers) {
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/startGame?numberOfPlayers="+numOfPlayers); // Request type and URL+parameters
			if (!xhr) {
				alert("CORS not supported");
			}
			xhr.send();
			location.href = "http://localhost:7777/toptrumps/game";
		}

		// -----------------------------------------
		// Add your other Javascript methods Here
		// -----------------------------------------

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

	</script>

	<style>
		.mainPage{
			background-image: url("http://localhost:7777/assets/bg.jpg");
			background-size: 100%;
		}

		.btn-xl{
			font-size: 30px;
			border-radius: 10px;
			opacity: 0.8;
			background-color: rgb(170, 167, 167);
			padding: 15px;
			margin: 20px;
		}

		#id1{
			color: rgb(170, 167, 167);
			font-size: 70px;
			opacity: 0.7;
		}

	</style>

	<!-- Here are examples of how to call REST API Methods -->
	<script type="text/javascript">

		// This calls the helloJSONList REST method from TopTrumpsRESTAPI
		function helloJSONList() {

			// First create a CORS request, this is the message we are going to send (a get request in this case)
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL

			// Message is not sent yet, but we can check that the browser supports CORS
			if (!xhr) {
				alert("CORS not supported");
			}

			// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
			// to do when the response arrives
			xhr.onload = function(e) {
				var responseText = xhr.response; // the text of the response
				alert(responseText); // lets produce an alert
			};

			// We have done everything we need to prepare the CORS request, so send it
			xhr.send();
		}

		// This calls the helloJSONList REST method from TopTrumpsRESTAPI
		function helloWord(word) {

			// First create a CORS request, this is the message we are going to send (a get request in this case)
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters

			// Message is not sent yet, but we can check that the browser supports CORS
			if (!xhr) {
				alert("CORS not supported");
			}

			// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
			// to do when the response arrives
			xhr.onload = function(e) {
				var responseText = xhr.response; // the text of the response
				alert(responseText); // lets produce an alert
			};

			// We have done everything we need to prepare the CORS request, so send it
			xhr.send();
		}

	</script>

</body>
</html>