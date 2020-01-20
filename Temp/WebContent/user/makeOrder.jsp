<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">

<title>Checkout example for Bootstrap</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/4.0/examples/checkout/">

<!-- Bootstrap core CSS -->
<link href="../../dist/css/bootstrap.min.css" rel="stylesheet">
<script src="../vendor/jquery/jquery.min.js"></script>
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

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
						class="badge badge-secondary badge-pill">3</span>
				</h4>
				<ul class="list-group mb-3">
					<li
						class="list-group-item d-flex justify-content-between lh-condensed">
						<div>
							<h6 class="my-0">Nome prodotto</h6>
							<small class="text-muted">Breve descrizione</small>
						</div> <span class="text-muted">$12</span>
					</li>
					<li
						class="list-group-item d-flex justify-content-between lh-condensed">
						<div>
							<h6 class="my-0">Second product</h6>
							<small class="text-muted">Brief description</small>
						</div> <span class="text-muted">$8</span>
					</li>
					<li
						class="list-group-item d-flex justify-content-between lh-condensed">
						<div>
							<h6 class="my-0">Third item</h6>
							<small class="text-muted">Brief description</small>
						</div> <span class="text-muted">$5</span>
					</li>
					<li class="list-group-item d-flex justify-content-between"><span>Totale
							(EUR)</span> <strong>$20</strong></li>
				</ul>
			</div>
			<div class="col-md-8 order-md-1">
				<h4 class="mb-3">Inserisci i tuoi dati per confermare l'ordine</h4>
				<form class="needs-validation" novalidate="">
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="firstName">Nome</label> <input type="text"
								class="form-control" id="firstName" placeholder="" value=""
								required="">
							<div class="invalid-feedback">Attenzione,inserire il nome.
							</div>
						</div>
						<div class="col-md-6 mb-3">
							<label for="lastName">Cognome</label> <input type="text"
								class="form-control" id="lastName" placeholder="" value=""
								required="">
							<div class="invalid-feedback">Attenzione,inserire il cognome.
							</div>
						</div>
					</div>

					<div class="mb-3">
						<label for="username">Username</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">@</span>
							</div>
							<input type="text" class="form-control" id="username"
								placeholder="Username" required="">
							<div class="invalid-feedback" style="width: 100%;">Attenzione,inserire username.</div>
						</div>
					</div>

					<div class="mb-3">
						<label for="email">Email <span class="text-muted"></span></label>
						<input type="email" class="form-control" id="email"
							placeholder="you@example.com">
						<div class="invalid-feedback">Attenzione,inserire un indirizzo mail valido.</div>
					</div>

					<div class="mb-3">
						<label for="address">Indirizzo</label> <input type="text"
							class="form-control" id="address" placeholder="1234 Main St"
							required="">
						<div class="invalid-feedback">Per favore,inserisci l'indirizzo di consegna.</div>
					</div>

<!-- 					<div class="mb-3"> -->
<!-- 						<label for="address2">Address 2 <span class="text-muted">(Optional)</span></label> -->
<!-- 						<input type="text" class="form-control" id="address2" -->
<!-- 							placeholder="Apartment or suite"> -->
<!-- 					</div> -->

					<div class="row">
						<div class="col-md-5 mb-3">
							<label for="country">Provincia</label> <select
								class="custom-select d-block w-100" id="country" required="">
								<option value="">Scegli</option>
								<option>Italia</option>
							</select>
							<div class="invalid-feedback">Per favore,selezione una provincia valida.</div>
						</div>
						<div class="col-md-4 mb-3">
							<label for="state">Comune</label> <select
								class="custom-select d-block w-100" id="state" required="">
								<option value="">Scegli</option>
								<option>Rende</option>
							</select>
							<div class="invalid-feedback">Per favore,inserisci un comune valido.
							</div>
						</div>
						<div class="col-md-3 mb-3">
							<label for="zip">Cap</label> <input type="text"
								class="form-control" id="zip" placeholder="" required="">
							<div class="invalid-feedback">Attenzione,inserire il cap.</div>
						</div>
					</div>
					<hr class="mb-4">
					<hr class="mb-4">

					<h4 class="mb-3">Pagamento</h4>

					<div class="d-block my-3">
						<div class="custom-control custom-radio">
							<input id="credit" name="paymentMethod" type="radio"
								class="custom-control-input" checked="" required=""> <label
								class="custom-control-label" for="credit">Carta di credito</label>
						</div>
						<div class="custom-control custom-radio">
							<input id="debit" name="paymentMethod" type="radio"
								class="custom-control-input" required=""> <label
								class="custom-control-label" for="debit">Contrassegno</label>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="cc-name">Nome intestatario</label> <input type="text"
								class="form-control" id="cc-name" placeholder="" required="">
							<div class="invalid-feedback">Attenzione,inserire il nome intestatario dela carta</div>
						</div>
						<div class="col-md-6 mb-3">
							<label for="cc-number">Numero carta di credito</label> <input
								type="text" class="form-control" id="cc-number" placeholder="0000-0000-0000-0000"
								required="">
							<div class="invalid-feedback">Attenzione,inserire il numero della carta di credito</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3 mb-3">
							<label for="cc-expiration">Scadenza</label> <input type="date"
								class="form-control" id="cc-expiration" placeholder=""
								required="">
							<div class="invalid-feedback">Attenzione,inserire la data si scadenza</div>
						</div>
						<div class="col-md-3 mb-3">
							<label for="cc-expiration">CVV</label> <input type="text"
								class="form-control" id="cc-cvv" placeholder="" required="">
							<div class="invalid-feedback">Attenzione,inserire il codice di sicurezza</div>
						</div>
					</div>
					<hr class="mb-4">
					<button class="btn btn-primary btn-lg btn-block" type="submit">Conferma ordine</button>
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
																										.checkValidity() === false) {
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