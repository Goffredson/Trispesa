<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Trispesa</title>

<!-- Bootstrap  -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- css -->
<link href="css/main.css" rel="stylesheet">

</head>
<body>

	<!-- Navbar principale  -->
	<nav id="nav"
		class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<ul style="list-style: none;">
				<li class="nav-item py-0 title"><a
					class="navbar-brand title-trispesa" href="home">Trispesa</a></li>
			</ul>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto" id="ulNavBar">
					<li class="nav-item py-0">
						<button type="button" class="btn btn-primary cart-button"
							data-toggle="modal" data-target="#modalCart">Carrello</button>
					</li>
					<li class="nav-item py-0">
						<!-- Div di login -->
						<div class="dropdown" id="loginDropdown">
							<a class="btn btn-secondary dropdown-toggle login-button" href=""
								role="button" id="loginButton" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">Login</a>
							<div class="dropdown-menu login-dropdown">
								<form class="px-4 py-3">
									<div class="form-group">
										<label for="inputUsername">Nome utente</label> <input
											type="text" class="form-control" id="inputUsername"
											placeholder="Inserisci nome utente">
									</div>
									<div class="form-group">
										<label for="inputPassword">Password</label> <input
											type="password" class="form-control" id="inputPassword"
											placeholder="Password">
									</div>
									<input type="button" class="btn btn-primary color-scheme"
										value="Autenticati" onclick="ajaxLog('login', 500)">

								</form>
								<div class="dropdown-item" id="credenzialiErrate"
									style="color: red; display: none;">Username o password
									errati.</div>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="" data-toggle="modal"
									data-target="#modalLogin">Effettua registrazione</a> <a
									class="dropdown-item" href="">Password dimenticata?</a>
							</div>
						</div> <!-- Animazione slide per il form --> <script
							type="text/javascript">
							$('#loginDropdown').on(
									'show.bs.dropdown',
									function() {
										$(this).find('.dropdown-menu').first()
												.stop(true, true).slideDown();
									});

							$('#loginDropdown').on(
									'hide.bs.dropdown',
									function() {
										$(this).find('.dropdown-menu').first()
												.stop(true, true).slideUp();
									});
						</script>
					</li>
					<li class="nav-item py-0 login-dependent" id="ordini"><a
						class="nav-link" href="#"><button type="button"
								class="btn btn-primary order-button" data-toggle="modal">Ordini</button></a></li>
					<li class="nav-item py-0 login-dependent" id="profilo"><a
						class="nav-link" href="../user?page=profile"><button
								type="button" class="btn btn-primary profile-button"
								data-toggle="modal">Profilo</button></a></li>
					<li class="nav-item py-0 login-dependent" id="dieta"><a
						href="manageDiet" class="nav-link"><button type="button"
								class="btn btn-primary diet-button" data-toggle="modal">Dieta</button></a></li>
					<li class="nav-item py-0"><input type="button"
						id="logoutButton"
						class="btn btn-primary login-dependent logout-button"
						value="Logout" onclick="ajaxLog('logout', 500)"></li>
				</ul>
			</div>
		</div>
		<!-- Aggiorno la navbar se c'è un cliente in sessione -->
		<c:if test="${customer != null}">
			<script type="text/javascript">
				updateNavbarDOM('login', 0);
			</script>
		</c:if>
	</nav>
	<!-- Chiusura navbar principale -->

	<div class="container">
		<div class="row">
			<div id="colonna" class="col-sm-8 col-md-5 col-lg-3">
				<div>
					<h3>Dati personali</h3>
				</div>
				<div>
					<h4>
						Username <img src="images/pencil.png" height="16px"
							style="cursor: pointer;"
							onclick="$('#mod-username-form').slideToggle('slow'); $('#username-view').slideToggle('slow'); $('#username').val('${sessionScope.customer.username}');" />
					</h4>
					<p id="username-view">${sessionScope.customer.username}</p>
					<form id="mod-username-form" style="display: none;"
						class="needs-validation" novalidate autocomplete="on">
						<div class="form-group">
							<div id="input">
								<input type="text" class="form-control" id="username"
									placeholder="Username" name="username" required
									autocomplete="off">
							</div>
						</div>
						<button id="mod-username-button" type="button"
							onclick="modUsername()" class="btn btn-success" disabled>Modifica</button>
					</form>
				</div>
				<div>
					<h4>
						Password <img src="images/pencil.png" height="16px"
							style="cursor: pointer;"
							onclick="$('#mod-password-form').slideToggle('slow'); $('#password-view').slideToggle('slow');" />
					</h4>
					<p id="password-view">${sessionScope.customer.hiddenPassword}</p>
					<form id="mod-password-form" style="display: none;"
						class="needs-validation" novalidate autocomplete="on">
						<div class="form-group">
							<div id="input">
								<input type="password" class="form-control" id="password-old"
									placeholder="Vecchia password" name="passwordOld" required
									autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<div id="input">
								<input type="password" class="form-control" id="password-new"
									placeholder="Nuova password" name="passwordNew" required
									autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<div id="input">
								<input type="password" class="form-control" id="password-twice"
									placeholder="Conferma password" name="passwordTwice" required
									autocomplete="off">
							</div>
						</div>
						<button id="mod-password-button" type="button"
							onclick="modPassword()" class="btn btn-success">Modifica</button>
					</form>
				</div>
				<div>
					<h4>
						Nome <img src="images/pencil.png" height="16px"
							style="cursor: pointer;"
							onclick="$('#mod-name-form').slideToggle('slow'); $('#name-view').slideToggle('slow'); $('#name').val('${sessionScope.customer.name}');" />
					</h4>
					<p id="name-view">${sessionScope.customer.name}</p>
					<form id="mod-name-form" style="display: none;"
						class="needs-validation" novalidate autocomplete="on">
						<div class="form-group">
							<div id="input">
								<input type="text" class="form-control" id="name"
									placeholder="Nome" name="name" required autocomplete="off">
							</div>
						</div>
						<button id="mod-name-button" type="button" onclick="modName()"
							class="btn btn-success">Modifica</button>
					</form>
				</div>
				<div>
					<h4>
						Cognome <img src="images/pencil.png" height="16px"
							style="cursor: pointer;"
							onclick="$('#mod-surname-form').slideToggle('slow'); $('#surname-view').slideToggle('slow'); $('#surname').val('${sessionScope.customer.surname}');" />
					</h4>
					<p id="surname-view">${sessionScope.customer.surname}</p>
					<form id="mod-surname-form" style="display: none;"
						class="needs-validation" novalidate autocomplete="on">
						<div class="form-group">
							<div id="input">
								<input type="text" class="form-control" id="surname"
									placeholder="Cognome" name="surname" required
									autocomplete="off">
							</div>
						</div>
						<button id="mod-surname-button" type="button"
							onclick="modSurname()" class="btn btn-success">Modifica</button>
					</form>
				</div>
				<div>
					<h4>
						Email <img src="images/pencil.png" height="16px"
							style="cursor: pointer;"
							onclick="$('#mod-email-form').slideToggle('slow'); $('#email-view').slideToggle('slow'); $('#email').val('${sessionScope.customer.email}');" />
					</h4>
					<p id="email-view">${sessionScope.customer.email}</p>
					<form id="mod-email-form" style="display: none;"
						class="needs-validation" novalidate autocomplete="on">
						<div class="form-group">
							<div id="input">
								<input type="email" class="form-control" id="email"
									placeholder="Email" name="email" required autocomplete="off">
							</div>
						</div>
						<button id="mod-email-button" type="button" onclick="modEmail()"
							class="btn btn-success">Modifica</button>
					</form>
				</div>
				<div>
					<h4>
						Data di nascita <img src="images/pencil.png" height="16px"
							style="cursor: pointer;"
							onclick="$('#mod-birth-date-form').slideToggle('slow'); $('#birth-date-view').slideToggle('slow'); $('#birth-date').val('${sessionScope.customer.birthDate}');" />
					</h4>
					<p id="birth-date-view">${sessionScope.customer.birthDate}</p>
					<form id="mod-birth-date-form" style="display: none;"
						class="needs-validation" novalidate autocomplete="on">
						<div class="form-group">
							<div id="input">
								<input type="date" class="form-control" id="birth-date"
									placeholder="Data di nascita" name="birthDate" required
									autocomplete="off">
							</div>
						</div>
						<button id="mod-birth-date-button" type="button"
							onclick="modBirthDate()" class="btn btn-success">Modifica</button>
					</form>
				</div>
			</div>

			<div id="colonna" class="col-sm-8 col-md-5 col-lg-8">
				<div class="row">
					<h4>Indirizzi di consegna</h4>
					<table class="table table-hover table-responsive">
						<tr>
							<th>Nazione</th>
							<th>Provincia</th>
							<th>Città</th>
							<th>CAP</th>
							<th>Indirizzo</th>
							<th colspan="2"><button id="add-deivery-address"
									onclick="prepareAddDeliveryAddress()" class="btn btn-success"
									role="button">+ Aggiungi indirizzo di consegna</button></th>
						</tr>
						<c:forEach items="${sessionScope.customer.deliveryAddresses}"
							var="deliveryAddress">
							<tr>
								<td>${deliveryAddress.country}</td>
								<td>${deliveryAddress.province}</td>
								<td>${deliveryAddress.city}</td>
								<td>${deliveryAddress.zipcode}</td>
								<td>${deliveryAddress.address}</td>
								<td width="10%"><button
										id="modify-delivery-address-${deliveryAddress.id}"
										onclick="prepareModDeliveryAddress(${deliveryAddress.id})"
										class="btn btn-info" role="button">Modifica indirizzo
										di consegna</button></td>
								<td width="10%"><button
										id="delete-delivery-address-${deliveryAddress.id}"
										onclick="deleteDeliveryAddress(${deliveryAddress.id})"
										class="btn btn-danger" role="button">Elimina
										indirizzo di consegna</button></td>
							</tr>
						</c:forEach>
					</table>
				</div>

				<div class="row">
					<h4>Metodi di pagamento</h4>
					<table class="table table-hover table-responsive">
						<tr>
							<th>Numero carta</th>
							<th>Proprietario</th>
							<th>Data scadenza</th>
							<th colspan="2"><button id="add-payment-method"
									onclick="prepareAddPaymentMethod()" class="btn btn-success"
									role="button">+ Aggiungi metodo di pagamento</button></th>
						</tr>
						<c:forEach items="${sessionScope.customer.paymentMethods}"
							var="paymentMethod">
							<tr>
								<td>${paymentMethod.cardNumber}</td>
								<td>${paymentMethod.owner}</td>
								<td>${paymentMethod.expirationDate}</td>
								<td width="10%"><button
										id="modify-payment-method-${paymentMethod.id}"
										onclick="prepareModPaymentMethod(${paymentMethod.id})"
										class="btn btn-info" role="button">Modifica metodo di
										pagamento</button></td>
								<td width="10%">
									<button id="delete-payment-method-${paymentMethod.id}"
										onclick="deletePaymentMethod(${paymentMethod.id})"
										class="btn btn-danger" role="button">Elimina metodo
										di pagamento</button>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- MODALE -->
	<div class="modal" id="result-modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 id="result-modal-title" class="modal-title"></h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<!-- Modal body -->
				<div class="modal-body">
					<div id="result-modal-body" class="jumbotron">
						<p>
							<span id="result-modal-object"></span>
						</p>
					</div>
				</div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="close" data-dismiss="modal">Chiudi</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal" id="manage-payment-method-modal">
		<div class="modal-dialog" style="max-width: 80%;">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 id="manage-payment-method-modal-title" class="modal-title"></h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<!-- form -->
					<form id="manage-payment-method-form" class="needs-validation"
						novalidate autocomplete="on">
						<div class="form-group">
							<label for="company">Circuito di pagamento:</label>
							<div id="input">
								<input type="text" class="form-control" id="company"
									placeholder="Visa, Mastercard, Paypal, ..." name="company"
									required autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label for="cardNumber">Numero carta:</label>
							<div id="input">
								<input type="text" class="form-control" id="card-number"
									placeholder="XXXX-XXXX-XXXX-XXXX" name="cardNumber" required
									autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label for="owner">Titiolare della carta:</label>
							<div id="input">
								<input type="text" class="form-control" id="owner"
									placeholder="Titolare" name="owner" required autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label for="expirationDate">Data di scadenza:</label>
							<div id="input">
								<input type="month" class="form-control" id="expiration-date"
									name="expirationDate" required autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label for="securityCode">Codice di sicurezza:</label>
							<div id="input">
								<input type="number" class="form-control" id="security-code"
									placeholder="XXX" name="securityCode" required
									autocomplete="off">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button id="manage-payment-method-button" type="button"
						class="btn btn-success"></button>
					<button type="button" class="btn btn-secondary"
						onclick="clearForm()">Reset</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Chiudi</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal" id="manage-delivery-address-modal">
		<div class="modal-dialog" style="max-width: 80%;">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 id="manage-delivery-address-modal-title" class="modal-title"></h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<!-- form -->
					<form id="manage-delivery-address-form" class="needs-validation"
						novalidate autocomplete="on">
						<div class="form-group">
							<label for="country">Nazione:</label>
							<div id="input">
								<input type="text" class="form-control" id="country"
									placeholder="Nazione" name="country" required
									autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label for="province">Provincia:</label>
							<div id="input">
								<input type="text" class="form-control" id="province"
									placeholder="Provincia" name="province" required
									autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label for="city">Città:</label>
							<div id="input">
								<input type="text" class="form-control" id="city"
									placeholder="Città" name="city" required autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label for="zipcode">CAP:</label>
							<div id="input">
								<input type="text" class="form-control" id="zipcode"
									placeholder="CAP" name="zipcode" required autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label for="address">Indirizzo:</label>
							<div id="input">
								<input type="text" class="form-control" id="address"
									placeholder="Indirizzo" name="address" required
									autocomplete="off">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button id="manage-delivery-address-button" type="button"
						class="btn btn-success"></button>
					<button type="button" class="btn btn-secondary"
						onclick="clearForm()">Reset</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Chiudi</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Trispesa
				2020</p>
		</div>
		<!-- /.container -->
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="js/manageUser.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>

</body>

</html>