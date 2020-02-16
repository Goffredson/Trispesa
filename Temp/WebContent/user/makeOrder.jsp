<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Trispesa - Dieta</title>
<!-- Inclusioni (bootstrap, JQuery, assets esterni)  -->
<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
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
<script src="../js/diet.js"></script>
<script src="../js/animations.js"></script>
<script src="../js/order.js"></script>
<!-- CSS -->
<link href="../css/footer.css" rel="stylesheet" />
<link href="../css/order-form.css" rel="stylesheet" />
<link href="../css/main.css" rel="stylesheet">

</head>

<body class="bg-light">

	<!-- Container principale -->
	<div class="container">
		<!-- Div logo -->
		<div class="py-5 text-center">
			<img class="d-block mx-auto mb-4" src="../images/package.png" alt=""
				width="128" height="128">
			<h2>Conferma Ordine</h2>
			<p class="lead"></p>
		</div>

		<!-- Navbar principale  -->
		<jsp:include page="navbar.jsp"></jsp:include>
		<!-- Fine navbar principale -->

		<!-- Carrello sul margine destro -->
		<div class="row">
			<div class="col-md-4 order-md-2 mb-4">
				<h4 class="d-flex justify-content-between align-items-center mb-3">
					<span class="text-muted">Il tuo carrello</span> <span
						class="badge badge-secondary badge-pill">${customer.cart.size()}</span>
				</h4>
				<ul class="list-group mb-3">
					<c:set var="totalCartPrice" scope="request" value="${0}" />
					<c:forEach items="${customer.cart}" var="product">
						<c:set var="totalCartPrice" scope="request"
							value="${totalCartPrice + product.key.price*product.value}" />
						<li
							class="list-group-item d-flex justify-content-between lh-condensed">
							<div>
								<h6 class="my-0">${product.key.name}</h6>
								<small class="text-muted">Quantita: ${product.value}</small>
							</div> <span class="text-muted">${product.key.price*product.value}&euro;</span>
						</li>
					</c:forEach>
					<li class="list-group-item d-flex justify-content-between"><span>Totale
					</span> <strong>${totalCartPrice}&euro;</strong></li>
				</ul>
			</div>
			<!-- Div dati account -->
			<div class="col-md-8 order-md-1">
				<h4 class="mb-3">Consegna</h4>
				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="firstName">Nome</label> <input type="text"
							class="form-control" id="firstName" value="${customer.name}"
							readonly>
					</div>
					<div class="col-md-6 mb-3">
						<label for="lastName">Cognome</label> <input type="text"
							class="form-control" id="lastName" value="${customer.surname}"
							readonly>
					</div>
				</div>
				<div class="mb-3">
					<label for="email">Email <span class="text-muted"></span></label> <input
						type="email" class="form-control" id="email"
						value="${customer.email}" readonly>
				</div>

				<!-- Form indirizzo e metodo di pagamento -->
				<form method="POST" id="orderForm" action="manageOrder">
					<div class="mb-3">
						<div class="form-group">
							<label>Indirizzo di consegna</label> <select required
								name="deliveryAddressId" id="selectAddress" class="form-control"
								form="orderForm">
								<option value="">Seleziona un indirizzo</option>
								<c:forEach items="${customer.deliveryAddresses}"
									var="deliveryAddress">
									<option value="${deliveryAddress.id}"
										data-address="${deliveryAddress.address}"
										data-address-city="${deliveryAddress.city}"
										data-address-province="${deliveryAddress.province}"
										data-address-zipcode="${deliveryAddress.zipcode}">${deliveryAddress}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<!-- Caselle da riempire con jQuery -->
					<div class="row">
						<div class="col-md-5 mb-3">
							<label for="province">Provincia</label> <input type="text"
								class="form-control" id="provinceField" value="" readonly>
						</div>
						<div class="col-md-4 mb-3">
							<label for="city">Comune</label> <input type="text"
								class="form-control" id="cityField" value="" readonly>
						</div>
						<div class="col-md-3 mb-3">
							<label for="zipcode">CAP</label> <input type="text"
								class="form-control" id="zipcodeField" value="" readonly>
						</div>
					</div>
					<hr class="mb-4">

					<h4 class="mb-3">Pagamento</h4>
					<div class="mb-3">
						<div class="form-group">
							<label>Metodo di pagamento</label> <select required
								name="paymentId" id="selectPayment" class="form-control"
								form="orderForm">
								<option value="">Seleziona un metodo di pagamento</option>
								<c:forEach items="${customer.paymentMethods}"
									var="paymentMethod">
									<option value="${paymentMethod.id}">${paymentMethod}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<hr class="mb-4">
					<input type="submit" class="btn color-scheme btn-lg btn-block"
						value="Conferma Ordine">

				</form>
			</div>
		</div>

		<!-- Sezione modali e toast -->
		<div id="orderConfirmed" class="modal fade">
			<div class="modal-dialog modal-confirm">
				<div class="modal-content">
					<div class="modal-header">
						<div class="icon-box">
							<i class="material-icons">&#xE876;</i>
						</div>
						<h4 class="modal-title">Ordine confermato</h4>
					</div>
					<div class="modal-body">
						<p class="text-center">La conferma dell'ordine è stata inviata
							via mail. Il riepilogo è disponibile nella sezione ordini</p>
					</div>
					<div class="modal-footer">
						<a href="home" class="btn btn-success btn-block">Torna alla
							home</a>
					</div>
				</div>
			</div>
		</div>
		<div id="paymentToast" class="toast notification-toast" role="alert"
			aria-live="assertive" aria-atomic="true" data-delay="5000">
			<div class="toast-header color-scheme">
				<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>
				<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="toast-body" id="toastMessage">Dati corretti.</div>
		</div>
		<div id="errorToast" class="toast notification-toast" role="alert"
			aria-live="assertive" aria-atomic="true" data-delay="5000">
			<div class="toast-header error-color-scheme">
				<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>
				<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="toast-body" id="toastMessage">Qualcosa è andato
				storto. Riprova più tardi.</div>
		</div>
		<!-- Modale conferma CVC -->
		<div class="modal" id="paymentModal" style="display: none"
			tabindex="-1" role="dialog">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Conferma dati carta</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="col-md-3 mb-3" id="divSecurityCode">
							<label for="securityCode">CVV</label> <input type="text"
								class="form-control" id="securityCode" placeholder="CVV">
							<div class="invalid-feedback">CVV necessario</div>
						</div>
						<div class="col-md-3 mb-3" id="divExpirationDate">
							<label for="expirationDate">Data di scadenza</label> <input
								type="text" class="form-control" id="expirationDate"
								placeholder="MM-YY">
							<div class="invalid-feedback">Scadenza necessaria</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Chiudi</button>
						<button type="button" class="btn btn-primary"
							onclick="verifyPayment()">Conferma</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Fine sezione modali e toast -->

	</div>
	<!-- Footer della pagina -->
	<footer class="footer-distributed">
		<div class="footer-left">
			<h3>
				Tri<span class="span-title">Spesa</span>
			</h3>
			<p class="footer-company-name">Trispesa © 2020</p>
		</div>
		<div class="footer-center">
			<div>
				<i class="fa fa-map-marker"></i>
				<p>
					<span>Via Pietro Bucci</span>Rende, Cosenza
				</p>
			</div>
			<div>
				<i class="fa fa-phone"></i>
				<p>348-3218976</p>
			</div>
			<div>
				<i class="fa fa-envelope"></i>
				<p>
					<a href="mailto:trispesaStaff@gmail.com">trispesastaff@gmail.com</a>
				</p>
			</div>
		</div>
		<div class="footer-right">
			<p class="footer-company-about">
				<span>Informazioni sito:</span> Questo progetto è stato sviluppato
				da un gruppo di studenti dell'Università della Calabria,
				dipartimento di Matematica e Informatica, per l'esame di Ingegneria
				del Software.
			</p>
			<div class="footer-icons">
				<a href="https://www.mat.unical.it/demacs"><img
					src="../images/logo_unical.png" width="24" height="20"></img></a> <a
					href="https://github.com/Goffredson/Trispesa"><i
					class="fa fa-github"></i></a>
			</div>
		</div>
		<!-- Chiusura footer -->
	</footer>

</body>
</html>