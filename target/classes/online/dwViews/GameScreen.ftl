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
	<#--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">-->
	<script src="/assets/gamescreen.js"></script>

</head>

<body onload="initalize()"> <!-- Call the initalize method when the page loads -->
</script>
<script>
</script>
<#--<div class="sidebar"></div>-->
<div class="container">
	<!-- Add your HTML Here -->

	<div class="container-fluid" style="background-image: inherit">
		<h2 style="color: ghostwhite" id="roundinfo"></h2>
		<hr style="border: 1px solid whitesmoke">
		<h3 style="color: ghostwhite" id="info"></h3>
		<div class="row">

			<div class="col-md-3" style="background-image: inherit;box-shadow:
         inset 1px -1px 1px #444, inset -1px 1px 1px #444;">
				<div class="hidden" id="selection">
					<button class="btn-default btn-xl" onclick="playARound(0)">Attack</button><br>
					<button class="btn-default btn-xl" onclick="playARound(1)">Health</button><br>
					<button class="btn-default btn-xl" onclick="playARound(2)">Strength</button><br>
					<button class="btn-default btn-xl" onclick="playARound(3)">Agility</button><br>
					<button class="btn-default btn-xl" onclick="playARound(4)">Intelligence</button>
				</div>
				<div class="hidden" id="nextRound">
					<#--AI auto selects attribute and show all cards and winner-->
					<button class="btn-default btn-xl" onclick="nextRound()">Next Round</button>
				</div>
				<div class="hidden" id="drawNextCard">
					<#--if user not eliminated yet, show next card; if eliminated, ...-->
					<button class="btn-default btn-xl" onclick="drawNextCard()">Next</button>
				</div>
			</div>

			<div class="col-md-9" style="background-image: inherit;box-shadow:
         inset 1px -1px 1px #444, inset -1px 1px 1px #444;">
				<div class="row">
					<div class="col-md-4 carddiv" id="pos0">

					</div>
					<div class="col-md-4 carddiv" id="pos1">

					</div>
					<div class="col-md-4 carddiv" id="pos2">

					</div>
				</div>

				<div class="row">
					<div class="col-md-4 carddiv" id="pos3">

					</div>
					<div class="col-md-4 carddiv" id="pos4">

					</div>
					<div class="col-md-4 carddiv">
						<p class="names" id="cardcount"></p>
					</div>
				</div>

			</div>

		</div>
		<div id="lose" class="white_content">
			<h1 style="text-align: center">Booo, You Lose!</h1>
			<button class="btn-xl" onclick = "showStatsInfo()">Close</button>
		</div>
		<div id="win" class="white_content">
			<h1 style="text-align: center">Congratulations! You Win!</h1>
			<button class="btn-xl" onclick = "showStatsInfo()">Close</button>
		</div>
		<div id="fade" class="black_overlay"></div>
		<div id="statsinfo" class="white_content">
			<h1>Game Over!</h1>
			<div id="statsdiv"></div>
			<button class="btn-xl" onclick = "location.href = 'http://localhost:7777/toptrumps/'">Home Page</button>
		</div>
	</div>


</div>

<#--CSS-->
<style>

	body{
		background-image: url("http://localhost:7777/assets/bg.jpg");
		background-size: 100%;
	}

	img{
		width: auto;
		height: auto;
		max-width: 100%;

	}
	@-webkit-keyframes twinkling{
		0%{
			opacity:0.5;
		}
		50%{
			opacity:1;
		}
		100%{
			opacity:0.5;
		}
	}
	.names
	{
		margin-bottom: 0px !important;
		text-align: center;
		font-size: 25;
		color: ghostwhite;
		font-weight: bold;
	}

	.twinkle{
		-webkit-animation: twinkling 1s infinite ease-in-out;
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
	div {
		border: 2px transparent black;
	}
	.scoretext{
		text-align: center;
		font-size: 20;
	}

	.carddiv{
		background-color: transparent;
		box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444;
		padding-bottom: 44%;
		height: 0;
	}

	.container-fluid
	{
		width: 100%!important;
	}

	.container
	{
		max-width: 85%!important;
		opacity: 0.9;
		background-image: inherit;
	}

	.btn-xl{
		height: 10%;
		width: 100%;
		position: relative;
		font-weight: bold;
		font-size: 18px;
		border-radius: 5px;
		opacity: 0.8;
		background-color: rgb(170, 167, 167);
		padding: 5px;
		margin:  0px;

	}

	.vishidden{
		visibility: hidden;
	}

	.displayblock{
		display: block;
	}

	.hidden{
		display: none;
	}
	.black_overlay{
		display: none;
		position: absolute;
		top: 0%;
		left: 0%;
		width: 100%;
		height: 100%;
		background-image: inherit;
		z-index:1001;
		-moz-opacity: 0.8;
		opacity:.80;
		filter: alpha(opacity=88);
	}
	.white_content {
		display: none;
		position: absolute;
		top: 25%;
		left: 25%;
		width: 55%;
		height: 55%;
		padding: 20px;
		border: 10px solid #bfb2ff;
		background-color: white;
		z-index:1002;
		overflow: auto;
	}

</style>
<#--javascript part-->

<script type="text/javascript">

</script>

</body>
</html>