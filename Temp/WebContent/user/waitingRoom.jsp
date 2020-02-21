<html>
<head>
<!-- Inclusioni (bootstrap, JQuery, assets esterni)  -->
<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<script src="../vendor/owl.carousel.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
	integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- CSS -->
<link href="../css/owl.carousel.css" rel="stylesheet" />
<link href="../css/footer.css" rel="stylesheet" />
<link href="../css/owl.theme.default.css" rel="stylesheet" />
<link href="../css/main.css" rel="stylesheet">
<link href="../css/faq.css" rel="stylesheet">
<script src="../js/cart.js"></script>
<script src="../js/login.js"></script>
<script src="../js/animations.js"></script>
<script src="../js/order.js"></script>
<script>
	$(function() {
		$("#footerDiv").load("footer.html");
	});
</script>
<title>Chat</title>
</head>
<body>
	<script>
		$(window).on("unload", (function() {
			$.ajax({
				type : "POST",
				url : "waitingRoom",
				data : {
					operation : "remove"
				},
				async : false,
				success : function() {
				}
			});
		}));
		$(document).ready(function() {
			intervalId = setInterval(function() {
				$.ajax({
					type : "POST",
					url : "waitingRoom",
					data : {
						operation : "add"
					},
					async : false,
					success : function(response) {
						if (response.nQueued === 0) {
							clearInterval(intervalId);
							window.location.href = "chat";
						} else {
							$("#nQueued").html(response.nQueued);
						}
					},
				});
			}, 3000);
		});
	</script>

	<!-- Navbar principale  -->
	<jsp:include page="navbar.jsp"></jsp:include>
	<!-- Chiusura navbar principale -->
	<!-- Div form dieta -->
	<div class="container">
		<div class="py-5 text-center">
			<img class="d-block mx-auto mb-4" src="../images/waitingRoom.png"
				alt="" width="128" height="128">
			<h2>Attendi qui</h2>
			<p style="font-size: medium;">Trasferiremo la tua richiesta
				d'aiuto il prima possibile al primo assistente disponibile.</p>
			<p style="font-size: small;">
				Clienti in coda prima di te: <strong id="nQueued"></strong>
			</p>
			<p class="lead"></p>
		</div>

	</div>
	<div id="footerDiv"></div>

</body>

<script src="../js/websocket.js"></script>

</html>