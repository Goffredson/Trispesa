<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>TriSpesa - Conferma Ordine</title>
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
<script>
	$(function() {
		$("#footerDiv").load("footer.html");
	});
</script>
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
							value="${totalCartPrice + product.key.roundedDiscountedPrice*product.value}" />
						<li
							class="list-group-item d-flex justify-content-between lh-condensed">
							<div>
								<h6 class="my-0">${product.key.name}</h6>
								<small class="text-muted">Quantita: ${product.value}</small>
							</div> <span class="text-muted">${product.key.roundedDiscountedPrice*product.value}&euro;</span>
						</li>
					</c:forEach>
					<li class="list-group-item d-flex justify-content-between"><span>Totale
					</span> <strong id="cartPrice">${totalCartPrice}&euro;</strong></li>
				</ul>
			</div>

			<!-- Div dati account -->
			<div class="col-md-8 order-md-1">
				<h4 class="mb-3">Consegna</h4>
				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="orderFirstName">Nome</label> <input type="text"
							class="form-control" id="orderFirstName" value="${customer.name}"
							readonly>
					</div>
					<div class="col-md-6 mb-3">
						<label for="orderLastName">Cognome</label> <input type="text"
							class="form-control" id="orderLastName"
							value="${customer.surname}" readonly>
					</div>
				</div>
				<div class="mb-3">
					<label for="orderEmail">Email <span class="text-muted"></span></label>
					<input type="email" class="form-control" id="orderEmail"
						value="${customer.email}" readonly>
				</div>

				<!-- Form indirizzo e metodo di pagamento -->

				<form method="POST" id="orderForm" action="manageOrder">
					<div class="mb-3">
						<div class="form-group">
							<label>Indirizzo di consegna</label>
							<div class="row">
								<div class="col-md-10 mb-3">
									<select required name="deliveryAddressId" id="selectAddress"
										class="form-control" form="orderForm">
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
								<div class="col-md-1 mb-3">
									<button data-toggle="modal" data-target="#addAddressModal"
										type="button" id="addButton" class="btn color-scheme">
										<b>+</b>
									</button>
								</div>
							</div>
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
					<!-- Default unchecked -->
					<div>
						<label>Scegli una modalità di pagamento</label>
					</div>

					<div style="margin-bottom: 12px;">
						<div class="custom-control custom-radio"
							style="display: inline; margin-right: 20px;">
							<input type="radio" class="custom-control-input"
								id="defaultChecked" name="defaultExampleRadios"> <label
								class="custom-control-label" for="defaultChecked"
								onclick="updateDOMForPayPal()">PayPal</label>
						</div>
						<!-- Default checked -->
						<div class="custom-control custom-radio" style="display: inline;">
							<input type="radio" class="custom-control-input"
								id="defaultUnchecked" name="defaultExampleRadios"> <label
								class="custom-control-label" for="defaultUnchecked"
								onclick="updateDOMForCreditCard()">Carta di credito</label>
						</div>
					</div>
					<div id="paypal-button" style="display: none;"></div>
					<script src="https://www.paypalobjects.com/api/checkout.js"></script>
					<script>
						var itemsInCart = [];
						$("#listaProdottiCarrello").children().each(
								function() {
									var productPrice = $(this).find(
											"#productPrice").html();
									var qty = $(this).find("#productQuantity")
											.html();
									// Simbolo dell'euro
									productPrice = productPrice.replace(
											/\u20AC/g, "");
									productPrice /= qty;
									var product = {
										name : $(this).find("#productName")
												.html(),
										quantity : qty,
										description : "La marca",
										price : productPrice,
										currency : 'EUR'
									};
									itemsInCart.push(product);
								});
						paypal.Button
								.render(
										{
											// Configure environment
											env : 'sandbox',
											client : {
												sandbox : 'demo_sandbox_client_id',
												production : 'demo_production_client_id'
											},
											// Customize button (optional)
											locale : 'it_IT',
											style : {
												size : 'medium',
												color : 'gold',
												shape : 'pill',
											},

											// Enable Pay Now checkout flow (optional)
											commit : true,

											// Set up a payment
											payment : function(data, actions) {
												return actions.payment
														.create({
															transactions : [ {
																amount : {
																	total : $(
																			"#cartPrice")
																			.html()
																			.substring(
																					0,
																					$(
																							"#cartPrice")
																							.html().length - 1),
																	currency : 'EUR',
																	details : {
																		subtotal : $(
																				"#cartPrice")
																				.html()
																				.substring(
																						0,
																						$(
																								"#cartPrice")
																								.html().length - 1),
																		shipping : '0.00',
																	//	handling_fee : '1.00',
																	//	shipping_discount : '-1.00',
																	//	insurance : '0.01'
																	}
																},
																description : 'Ordine TriSpesa via PayPal',
																//custom : '90048630024435',

																//soft_descriptor : 'ECHI5786786',
																item_list : {
																	items : itemsInCart,
																	shipping_address : {
																		recipient_name : $(
																				"#orderFirstName")
																				.val()
																				+ " "
																				+ $(
																						"#orderLastName")
																						.val(),
																		line1 : $(
																				'#selectAddress')
																				.find(
																						":selected")
																				.text()
																				.split(
																						",")[0],
																		line2 : $(
																				'#selectAddress')
																				.find(
																						":selected")
																				.text()
																				.split(
																						",")[1],
																		city : $(
																				'#selectAddress')
																				.find(
																						":selected")
																				.text()
																				.split(
																						",")[3],
																		country_code : 'IT',
																		postal_code : $(
																				'#selectAddress')
																				.find(
																						":selected")
																				.attr(
																						"data-address-zipcode"),
																	//phone : '011862212345678',
																	//state : 'CA'
																	}
																}
															} ],
															application_context : {
																brand_name : 'TriSpesa Staff'
															},
															note_to_payer : 'Grazie per aver scelto TriSpesa.',
														});
											},
											// Execute the payment
											onAuthorize : function(data,
													actions) {
												return actions.payment
														.execute()
														.then(
																function() {
																	// Show a confirmation message to the buyer
																	window
																			.alert('deve partire il modale con la spunta verde');
																});
											}
										}, '#paypal-button');
					</script>
					<div class="mb-3">
						<div class="form-group">
							<select id="selectPayment" style="display: none;" required
								name="paymentId" id="selectPayment" class="form-control"
								form="orderForm">
								<option value="">Seleziona una carta di credito</option>
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
						<p class="text-center">La conferma dell'ordine &egrave; stata
							inviata via mail. Grazie per aver ordinato su TriSpesa.</p>
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

			</div>
			<div class="toast-body" id="toastMessage">Dati corretti.</div>
		</div>
		<div id="errorToast" class="toast notification-toast" role="alert"
			aria-live="assertive" aria-atomic="true" data-delay="5000">
			<div class="toast-header error-color-scheme">
				<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>

			</div>
			<div class="toast-body" id="toastMessage">Qualcosa &egrave;
				andato storto. Riprova pi&ugrave; tardi.</div>
		</div>
		<!-- Modale conferma CVC -->
		<div class="modal" id="paymentModal" style="display: none"
			tabindex="-1" role="dialog">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Conferma dati carta</h5>

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

		<!-- Modale conferma CVC -->
		<div class="modal fade" id="addAddressModal" style="display: none"
			tabindex="-1" role="dialog">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Inserisci un indirizzo</h5>

					</div>
					<div class="modal-body">
						<div class="mb-3">
							<label for="orderEmail">Via <span class="text-muted"></span></label>
							<input type="text" class="form-control" id="inputVia">
						</div>
						<div class="row">
							<div class="col-md-5 mb-3">
								<label for="province">Provincia</label> <input type="text"
									class="form-control" id="inputProvincia">
							</div>
							<div class="col-md-4 mb-3">
								<label for="city">Comune</label> <input type="text"
									class="form-control" id="inputCitta">
							</div>
							<div class="col-md-3 mb-3">
								<label for="zipcode">CAP</label> <input type="number"
									class="form-control" id="inputCAP">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Chiudi</button>
						<button type="button" data-dismiss="modal" class="btn btn-primary"
							onclick="addAddressToSelect()">Conferma</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Modale spinner -->
		<div id="waitingModal" class="modal fade">
			<div class="modal-dialog modal-confirm">
				<div class="modal-content">
					<div class="modal-header">
						<div class="icon-box">
							<i class="material-icons">&#xE5D3;</i>
						</div>
						<h4 class="modal-title">Processamento ordine in corso</h4>
					</div>
					<div class="modal-body">
						<p class="text-center">Questa finestrella scomparirà non
							appena l'ordine verrà confermato.</p>
						<div class="d-flex justify-content-center">
							<div class="spinner-border text-success" role="status">
								<span class="sr-only">Loading...</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Fine sezione modali e toast -->

	</div>
	<!-- Footer della pagina -->
	<div class="footerDiv"></div>

</body>
</html>