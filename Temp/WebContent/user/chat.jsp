
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>TriSpesa - Chat</title>
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
<!-- Script -->
<script src="../js/cart.js"></script>
<script src="../js/login.js"></script>
<script src="../js/animations.js"></script>
<script src="../js/order.js"></script>
<script src="../js/websocket.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
	type="text/css" rel="stylesheet">
<link href="../css/chat.css" rel="stylesheet">
<link href="../css/owl.carousel.css" rel="stylesheet" />
<link href="../css/footer.css" rel="stylesheet" />
<link href="../css/owl.theme.default.css" rel="stylesheet" />
<link href="../css/main.css" rel="stylesheet">
<link href="../css/faq.css" rel="stylesheet">
<link href="../css/order-form.css" rel="stylesheet">

<script>
	$(function() {
		$("#footerDiv").load("footer.html");
	});
	function logoutAdmin() {
		ajaxLog('logout', 500);
		window.location.href = "home";
	}
</script>

</head>
<body>
	<input readonly type="text" id="username" style="display: none;"
		value="${customer.username}" />
	<script>
		$(document).ready(function() {
			connect($("#username").val());
			if ($("#username").val().includes("admin")) {
				$("#centerIcon").attr("src", "../images/admin.png");
				$("#firstTitle").html("Aiuta un cliente");
				$("#secondTitle").html("Aiuta sto cliente bla bla loren ipsum sit ecc.");
				$('#adminOnlyModal').modal('show');
			} else {
				$("#connectedToast").toast("show");
			}

			$("#sendBox").on('keypress', function(e) {
				if (e.which == 13) {
					send($('#username').val());
				}
			});
		});
	</script>
	<!-- Navbar principale  -->
	<jsp:include page="navbar.jsp"></jsp:include>
	<!-- Chiusura navbar principale -->
	<div class="container">
		<div class="py-5 text-center">
			<img id="centerIcon" class="d-block mx-auto mb-4"
				src="../images/chat.png" alt="" width="128" height="128">
			<h2 id="firstTitle">Qual è il problema?</h2>
			<p id="secondTitle" style="font-size: medium;">Parlaci del tuo
				problema problema qui sotto, e lo risolveremo il prima possibile.</p>
			<p class="lead"></p>
		</div>

	</div>
	<div class="container">
		<div class="messaging row justify-content-center">
			<div class="mesgs">
				<div class="msg_history" id="msgHistory"></div>
				<div class="type_msg">
					<div class="input_msg_write">
						<input type="text" class="write_msg" style="outline-width: 0;"
							id="sendBox" placeholder="Scrivi un messaggio" />
						<button class="msg_send_btn" onclick="send($('#username').val());"
							type="button">
							<i class="fa fa-paper-plane-o" aria-hidden="true"></i>
						</button>
					</div>
				</div>
			</div>
		</div>

	</div>

	<div id="adminOnlyModal" class="modal fade">
		<div class="modal-dialog modal-confirm">
			<div class="modal-content">
				<div class="modal-header">
					<div class="icon-box">
						<i class="material-icons">&#xE0B1;</i>
					</div>
					<h4 class="modal-title">In attesa di richieste d'aiuto</h4>
				</div>
				<div class="modal-body">
					<p class="text-center">Questa finestrella scomparirà non appena
						arriverà una richiesta d'aiuto da parte di un cliente.</p>
					<div class="d-flex justify-content-center">
						<div class="spinner-border text-success" role="status">
							<span class="sr-only">Loading...</span>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn color-scheme btn-block" onclick="logoutAdmin();">Logout</button>
				</div>
			</div>
		</div>
	</div>

	<div id="connectedToast" class="toast notification-toast" role="alert"
		aria-live="assertive" aria-atomic="true" data-delay="5000">
		<div class="toast-header color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>
		</div>
		<div class="toast-body" id="toastMessage">Ora sei in contatto
			con uno specialista.</div>
	</div>

	<div id="footerDiv"></div>

</body>
</html>