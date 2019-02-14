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

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	<style type="text/css">
			.container
			{
				position: center;
			}
			.column
			{
				width: 33.33%;
				position: center;
			}
			.row
			{
				position: center;
			}
			.button
			{
				height: 90px;
				width: 320px;
				left: 30;
				position: relative;
				font-weight: bold;
			}
			div {
				outline: 1px transparent;
			}
			.container
			{
				position: absolute; top: 0; bottom: 0; left: 0; right: 0;
			}
		</style>

		<div class="container-fluid" >
			<p></p>
			<div class = "row">
				<div class = "column">
					<button class="button" onclick="window.location.href = 'http://localhost:7777/toptrumps/game';">NEXT ROUND</button></br>
					<button class="button" onclick="window.location.href = 'http://localhost:7777/toptrumps/game';">ATTACK</button></br>
					<button class="button" onclick="window.location.href = 'http://localhost:7777/toptrumps/game';">HEALTH</button></br>
					<button class="button" onclick="window.location.href = 'http://localhost:7777/toptrumps/game';">STRENGTH</button></br>
					<button class="button" onclick="window.location.href = 'http://localhost:7777/toptrumps/game';">AGILITY</button></br>
					<button class="button" onclick="window.location.href = 'http://localhost:7777/toptrumps/game';">INTELLIGENCE</button>
				</div>
				<div class = "column"><img src="/assets/40.png"/></div>
				<div class = "column"><img src="/assets/39.png"/></div>
			</div>
			<div class = "row">
				<div class = "column"><img src="/assets/38.png"/></div>
				<div class = "column"><img src="/assets/37.png"/></div>
				<div class = "column"><img src="/assets/36.png"/></div>
			</div>
		</div>
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
				helloJSONList();
				helloWord("Student");
				
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
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
		


		</script>
		
		</body>
</html>