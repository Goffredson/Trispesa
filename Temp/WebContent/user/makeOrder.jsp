<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">

<title>Trispesa - Conferma ordine</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/4.0/examples/checkout/">

<!-- Bootstrap core CSS -->
<link href="../../dist/css/bootstrap.min.css" rel="stylesheet">
<script src="../vendor/jquery/jquery.min.js"></script>
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="../js/order.js"></script>


<!-- Custom styles for this template -->
<link href="../css/order-form.css" rel="stylesheet">
</head>

<body class="bg-light">

	<div class="container">
		<div class="py-5 text-center">
			<img class="d-block mx-auto mb-4"
				src="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-solid.svg"
				alt="" width="72" height="72">
			<h2>Conferma Ordine</h2>
			<p class="lead"></p>
		</div>

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
							</div> <span class="text-muted">${product.key.price*product.value}</span>
						</li>
					</c:forEach>
					<li class="list-group-item d-flex justify-content-between"><span>Totale
							(EUR)</span> <strong>${totalCartPrice}</strong></li>
				</ul>
			</div>
			<div class="col-md-8 order-md-1">
				<h4 class="mb-3">Inserisci i tuoi dati per confermare l'ordine</h4>
				<form class="needs-validation" novalidate="">
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="firstName">Nome</label> <input type="text"
								class="form-control" id="firstName" value="${customer.name}"
								readonly required="">
							<div class="invalid-feedback">Attenzione,inserire il nome.
							</div>
						</div>
						<div class="col-md-6 mb-3">
							<label for="lastName">Cognome</label> <input type="text"
								class="form-control" id="lastName" value="${customer.surname}"
								readonly required="">
							<div class="invalid-feedback">Attenzione,inserire il
								cognome.</div>
						</div>
					</div>

					<div class="mb-3">
						<label for="username">Username</label>
						<div class="input-group">
							<input type="text" class="form-control" id="username"
								value="${customer.username}" readonly>
							<div class="invalid-feedback" style="width: 100%;">Attenzione,inserire
								username.</div>
						</div>
					</div>

					<div class="mb-3">
						<label for="email">Email <span class="text-muted"></span></label>
						<input type="email" class="form-control" id="email"
							value="${customer.email}" readonly>
						<div class="invalid-feedback">Attenzione,inserire un
							indirizzo mail valido.</div>
					</div>

					<div class="mb-3">
						<div class="bootstrap-select-wrapper">
							<label>Indirizzo di consegna</label>
							<c:forEach items="${customer.deliveryAddresses}"
								var="deliveryAddress">
								<select id="address" title="Scegli un indirizzo"
									onchange="fillDeliveryAddressDOM('${deliveryAddress.address}','${deliveryAddress.city}','${deliveryAddress.province}','${deliveryAddress.zipcode}')">
									<option>Scegli il tuo indirizzo</option>
									<option>${deliveryAddress}</option>
								</select>
							</c:forEach>
						</div>
						<div class="invalid-feedback">Per favore,inserisci
							l'indirizzo di consegna.</div>
					</div>

					<!-- 					<div class="mb-3"> -->
					<!-- 						<label for="address2">Address 2 <span class="text-muted">(Optional)</span></label> -->
					<!-- 						<input type="text" class="form-control" id="address2" -->
					<!-- 							placeholder="Apartment or suite"> -->
					<!-- 					</div> -->

					<div class="row">
						<div class="col-md-5 mb-3">
							<label for="country">Provincia</label> <input type="text"
								class="form-control" id="provincia" value="" readonly>
							<div class="invalid-feedback">Per favore,selezione una
								provincia valida.</div>
						</div>
						<div class="col-md-4 mb-3">
							<label for="state">Comune</label> <input type="text"
								class="form-control" id="comune" value="" readonly>
							<div class="invalid-feedback">Per favore,inserisci un
								comune valido.</div>
						</div>
						<div class="col-md-3 mb-3">
							<label for="zip">Cap</label> <input type="text"
								class="form-control" id="zipcode" value="" readonly>
							<div class="invalid-feedback">Attenzione,inserire il CAP.</div>
						</div>
					</div>
					<hr class="mb-4">
					<hr class="mb-4">

					<h4 class="mb-3">Pagamento</h4>

					<div class="d-block my-3">
						<div class="custom-control custom-radio">
							<input id="myMethod" name="paymentMethod" type="radio"
								class="custom-control-input" checked="" required=""> <label
								class="custom-control-label" for="myMethod">Esistente</label>
						</div>
						<div class="custom-control custom-radio">
							<input id="newMethod" name="paymentMethod" type="radio"
								class="custom-control-input" required=""> <label
								class="custom-control-label" for="newMethod">Altro</label>
						</div>

					</div>
					
					<div class="mb-3">
						<div class="bootstrap-select-wrapper">
							<label>Metodo di pagamento</label>
							<c:forEach items="${customer.paymentMethods}"
								var="paymentMethod">
								<select id="method" title="Scegli un metodo di pagamento">
									<option>Scegli il tuo metodo di pagamento</option>
									<option>${paymentMethod}</option>
								</select>
							</c:forEach>
						</div>
						<div class="invalid-feedback">Per favore,inserisci
							l'indirizzo di consegna.</div>
					</div>
					
<!-- 					<div class="row"> -->
<!-- 						<div class="col-md-6 mb-3"> -->
<!-- 							<label for="cc-name">Nome intestatario</label> <input type="text" -->
<!-- 								class="form-control" id="cc-name" placeholder="" required=""> -->
<!-- 							<div class="invalid-feedback">Attenzione,inserire il nome -->
<!-- 								intestatario dela carta</div> -->
<!-- 						</div> -->
<!-- 						<div class="col-md-6 mb-3"> -->
<!-- 							<label for="cc-number">Numero carta di credito</label> <input -->
<!-- 								type="text" class="form-control" id="cc-number" -->
<!-- 								placeholder="0000-0000-0000-0000" required=""> -->
<!-- 							<div class="invalid-feedback">Attenzione,inserire il numero -->
<!-- 								della carta di credito</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="row"> -->
<!-- 						<div class="col-md-3 mb-3"> -->
<!-- 							<label for="cc-expiration">Scadenza</label> <input type="date" -->
<!-- 								class="form-control" id="cc-expiration" placeholder="" -->
<!-- 								required=""> -->
<!-- 							<div class="invalid-feedback">Attenzione,inserire la data -->
<!-- 								si scadenza</div> -->
<!-- 						</div> -->
<!-- 						<div class="col-md-3 mb-3"> -->
<!-- 							<label for="cc-expiration">CVV</label> <input type="text" -->
<!-- 								class="form-control" id="cc-cvv" placeholder="" required=""> -->
<!-- 							<div class="invalid-feedback">Attenzione,inserire il codice -->
<!-- 								di sicurezza</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<hr class="mb-4">
					<button class="btn btn-primary btn-lg btn-block" type="submit">Conferma
						ordine</button>
				</form>
			</div>
		</div>

		<footer class="py-5 bg-dark">
			<div class="container">
				<p class="m-0 text-center text-white">Copyright &copy; Trispesa
					2020</p>
			</div>
			<!-- /.container -->
		</footer>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script>
		window.jQuery
				|| document
						.write(
								'<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')
	</script>
	<script src="../../assets/js/vendor/popper.min.js"></script>
	<script src="../../dist/js/bootstrap.min.js"></script>
	<script src="../../assets/js/vendor/holder.min.js"></script>
	<script>
		// Example starter JavaScript for disabling form submissions if there are invalid fields
						(

								function() {
									'use strict';

									window
											.addEventListener(
													'load',
													function() {
														// Fetch all the forms we want to apply custom Bootstrap validation styles to
														var forms = document
																.getElementsByClassName('needs-validation');

														// Loop over them and prevent submission
														var validation = Array.prototype.filter
																.call(
																		forms,
																		function(
																				form) {
																			form
																					.addEventListener(
																							'submit',
																							function(
																									event) {
																								if (form
																										.checkValidity() === false
																										|| document
																												.getElementById("address").html === "Scegli il tuo indirizzo") {
																									event
																											.preventDefault();
																									event
																											.stopPropagation();
																								}
																								form.classList
																										.add('was-validated');
																							},
																							false);
																		});
													}, false);
								})();
	</script>


</body>
</html>