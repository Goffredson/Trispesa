<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<!-- Navbar principale  -->
<nav id="nav"
	class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<!-- Logo -->
		<ul style="list-style: none;">
			<li class="nav-item py-0 title-trispesa"><a class="navbar-brand"
				href="home"><h2>
						Tri<span class="span-title">Spesa</span>
					</h2></a></li>
		</ul>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<!-- UL di carrello, login, etc. -->
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto" id="ulNavBar">
				<a href="#">
					<li class="nav-item py-0 item-icon-cart"><i
						class="fa fa-shopping-cart cart-icon" aria-hidden="true"
						data-toggle="modal" data-target="#modalCart"></i></li>
				</a>
				<li class="nav-item py-0">
					<!-- Div di login -->
					<div class="dropdown" id="loginDropdown">
						<a class="btn btn-secondary dropdown-toggle login-button" href=""
							role="button" id="loginButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">Login</a>
						<div class="dropdown-menu login-dropdown">
							<form class="px-4 py-3" id="loginForm">
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
								<div class="g-recaptcha"
									data-sitekey="6Lc1aNkUAAAAAPqQpuwuHaujwOzeV5Yda8EIeljO"
									data-callback="enableButton"></div>

								<button type="submit" id="authButton" disabled
									class="btn color-scheme" value="Autenticati">
									Autenticati</button>

							</form>
							<div class="dropdown-item" id="credenzialiErrate"
								style="color: red; display: none;">Username o password
								errati.</div>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="" data-toggle="modal"
								data-target="#modalLogin">Effettua registrazione</a> <a
								class="dropdown-item" data-toggle="modal"
								data-target="#recoveryModal" href="#">Password dimenticata?</a>
						</div>
					</div>
				</li>
				<!-- Pulsanti login-dependent -->
				<li class="nav-item py-0 login-dependent" id="dieta"><a
					href="manageDiet" class="nav-link"><button type="button"
							class="btn btn-primary diet-button" data-toggle="modal">Dieta</button></a></li>

				<li class="nav-item py-0" id="logoutListItem"><input
					type="button" id="logoutButton"
					class="btn login-dependent logout-button" value="Logout"
					onclick="ajaxLog('logout', 500)"></li>
				<a id="faqId" class="btn btn-secondary" href="faq" role="button">FAQ</a>
			</ul>
		</div>
		<script type="text/javascript">
					var currLocation = window.location.pathname.split("/").pop();
					if (currLocation === "manageDiet" || currLocation === "manageOrder")
						$("#logoutListItem").hide();
					</script>
	</div>
	<!-- Aggiorno la navbar se c'è un cliente in sessione -->
	<c:if test="${customer != null}">
		<script type="text/javascript">
			updateNavbarDOM('login', 0);
		</script>
	</c:if>
</nav>
<!-- Chiusura navbar principale -->
<!-- Modale form registrazione -->
<div class="modal fade" id="modalLogin" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Registrazione cliente</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true"></span>
				</button>
			</div>
			<div class="modal-body">
				<!-- Form di registrazione -->
				<form action="signup" class="text-center border border-light p-5"
					method="post" name="registrationForm">
					<div class="form-row mb-4">
						<div class="col">
							<input type="text" name="firstName" class="form-control"
								placeholder="Nome">
						</div>
						<div class="col">
							<input type="text" name="lastName" class="form-control"
								placeholder="Cognome">
						</div>
					</div>
					<input required type="email" name="email" class="form-control mb-4"
						placeholder="E-mail"> <input required type="text"
						name="username" class="form-control mb-4" placeholder="Username">
					<input pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required
						type="password" name="password" class="form-control"
						placeholder="Password"> <small id="passwordHelp"
						class="form-text text-muted mb-4"> Almeno un numero, una
						maiuscola e una minuscola, 8 caratteri</small> <input required type="text"
						placeholder="Data di nascita" name="birthDate"
						onfocus="(this.type='date')" onblur="(this.type='text')"
						class="form-control">
					<div class="modal-footer">
						<input type="submit"
							class="btn color-scheme my-4 btn-block waves-effect waves-light"
							value="Registrati">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- Modale recupero password -->
<div class="modal" id="recoveryModal" style="display: none"
	tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Password dimenticata</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="mb-3">
					<label for="usernameRecovery">Inserisci qui il tuo username</label>
					<input type="text" class="form-control" id="usernameRecovery"
						placeholder="Username">
				</div>
				<label><small>Ti verrà inviata per e-mail la nuova
						password.</small></label>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
				<button type="button" class="btn btn-primary"
					onclick="passwordRecovery($('#usernameRecovery').val());">Conferma</button>
			</div>
		</div>
	</div>
</div>

<!-- Carrello -->
<div class="modal fade" id="modalCart" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">Il tuo carrello</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true"></span>
				</button>
			</div>
			<div class="modal-body" id="modalTemp">
				<div class="count">
					<h3>
						<small>Tempo rimanente</small>
					</h3>
					<div id="timer"></div>
				</div>
				<table class="table">
					<thead>
						<tr>
							<th>N.</th>
							<th>Nome prodotto</th>
							<th>Prezzo</th>
							<th></th>

						</tr>
					</thead>
					<tbody id="listaProdottiCarrello">
						<c:set var="totalCartPrice" scope="request" value="${0}" />
						<c:forEach items="${customer.cart}" var="product">
							<c:set var="totalCartPrice" scope="request"
								value="${totalCartPrice + product.key.roundedDiscountedPrice*product.value}" />

							<tr id="product_${product.key.id}">
								<th scope="row" id="productQuantity">${product.value}</th>
								<td id="productName">${product.key.name}</td>
								<td id="productPrice">${product.key.roundedDiscountedPrice*product.value}&euro;</td>
								<td><a><i class="fas fa-times"></i></a></td>
								<td><button type="button"
										onclick="updateCart(${product.key.id}, '${product.key.name}', ${product.key.roundedDiscountedPrice}, '${product.key.superMarket.name}', 'remove');"
										class="btn btn-danger">Rimuovi</button></td>
							</tr>
						</c:forEach>
						<c:forEach items="${anonymousCart}" var="product">
							<c:set var="totalCartPrice" scope="request"
								value="${totalCartPrice + product.key.roundedDiscountedPrice*product.value}" />

							<tr id="product_${product.key.id}">
								<th scope="row" id="productQuantity">${product.value}</th>
								<td id="productName">${product.key.name}</td>
								<td id="productPrice">${product.key.roundedDiscountedPrice*product.value}&euro;</td>
								<td><a><i class="fas fa-times"></i></a></td>
								<td><button type="button"
										onclick="updateCart(${product.key.id}, '${product.key.name}', ${product.key.roundedDiscountedPrice}, '${product.key.superMarket.name}', 'remove');"
										class="btn btn-danger">Rimuovi</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<h2 id="totalCartPrice" class="hidden-xs text-center">${totalCartPrice}&euro;</h2>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn color-scheme" data-dismiss="modal">Chiudi</button>
				<c:if test="${customer != null}">
					<a id="orderButton" href="manageOrder"><button
							class="btn color-scheme">Conferma ordine</button></a>
				</c:if>
				<c:if test="${customer == null}">
					<a id="orderAnchor" href="#"><button id="orderButton"
							onclick="$('#modalCart').modal('hide'); $('.modal-backdrop').hide(); $('#loginToast').toast('show'); $('.dropdown-menu').show();"
							class="btn color-scheme">Conferma ordine</button></a>
				</c:if>
			</div>
		</div>
	</div>
</div>
<!-- Chiusura carrello -->

</html>