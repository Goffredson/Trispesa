


<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Trispesa</title>
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
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
	type="text/css" rel="stylesheet">
<link href="../css/chat2.css" rel="stylesheet">
<link href="../css/owl.carousel.css" rel="stylesheet" />
<link href="../css/footer.css" rel="stylesheet" />
<link href="../css/owl.theme.default.css" rel="stylesheet" />
<link href="../css/main.css" rel="stylesheet">
<link href="../css/faq.css" rel="stylesheet">
<script>
	$(function() {
		$("#footerDiv").load("footer.html");
	});
</script>
</head>
<body>
	<!-- Navbar principale  -->
	<jsp:include page="navbar.jsp"></jsp:include>
	<!-- Chiusura navbar principale -->
	<div class="container">
		<div class="py-5 text-center">
			<img class="d-block mx-auto mb-4" src="../images/chat.png" alt=""
				width="128" height="128">
			<h2>Qual � il problema?</h2>
			<p style="font-size: medium;">Esponi il problema qui sotto, e lo risolveremo il prima possibile.</p>
			<p class="lead"></p>
		</div>

	</div>
	<div class="container">
		<div class="messaging row justify-content-center">
			<div class="mesgs">
				<div class="msg_history">
					<div class="incoming_msg">
						<div class="incoming_msg_img">
							<img src="https://ptetutorials.com/images/user-profile.png"
								alt="sunil">
						</div>
						<div class="received_msg">
							<div class="received_withd_msg">
								<p>Test which is a new approach to have all solutions</p>
								<span class="time_date"> 11:01 AM | June 9</span>
							</div>
						</div>
					</div>
					<div class="outgoing_msg">
						<div class="sent_msg">
							<p>Test which is a new approach to have all solutions</p>
							<span class="time_date"> 11:01 AM | June 9</span>
						</div>
					</div>
					<div class="incoming_msg">
						<div class="incoming_msg_img">
							<img src="https://ptetutorials.com/images/user-profile.png"
								alt="sunil">
						</div>
						<div class="received_msg">
							<div class="received_withd_msg">
								<p>Test, which is a new approach to have</p>
								<span class="time_date"> 11:01 AM | Yesterday</span>
							</div>
						</div>
					</div>
					<div class="outgoing_msg">
						<div class="sent_msg">
							<p>Apollo University, Delhi, India Test</p>
							<span class="time_date"> 11:01 AM | Today</span>
						</div>
					</div>
					<div class="incoming_msg">
						<div class="incoming_msg_img">
							<img src="https://ptetutorials.com/images/user-profile.png"
								alt="sunil">
						</div>
						<div class="received_msg">
							<div class="received_withd_msg">
								<p>We work directly with our designers and suppliers, and
									sell direct to you, which means quality, exclusive products, at
									a price anyone can afford.</p>
								<span class="time_date"> 11:01 AM | Today</span>
							</div>
						</div>
					</div>
				</div>
				<div class="type_msg">
					<div class="input_msg_write">
						<input type="text" class="write_msg" placeholder="Type a message" />
						<button class="msg_send_btn" type="button">
							<i class="fa fa-paper-plane-o" aria-hidden="true"></i>
						</button>
					</div>
				</div>
			</div>
		</div>

	</div>

	<div id="footerDiv"></div>

</body>
</html>