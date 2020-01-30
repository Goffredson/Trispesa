<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Trispesa - ricerca prodotti</title>
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
<script src="../js/animations.js"></script>
<script src="../js/order.js"></script>
<!-- CSS -->
<link href="../css/footer.css" rel="stylesheet" />
<link href="../css/main.css" rel="stylesheet">
</head>

<body class="bg-light">

	<!-- Navbar principale  -->
	<nav id="nav"
		class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<!-- Logo -->
			<ul style="list-style: none;">
				<li class="nav-item py-0 title-trispesa"><a
					class="navbar-brand" href="home"><h2>
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
									<input type="button" class="btn color-scheme"
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
						</div>
					</li>
					<!-- Pulsanti login-dependent -->
					<li class="nav-item py-0 login-dependent" id="ordini"><a
						class="nav-link" href="../user?page=orders"><button
								type="button" class="btn btn-primary order-button"
								data-toggle="modal">Ordini</button></a></li>
					<li class="nav-item py-0 login-dependent" id="profilo"><a
						class="nav-link" href="../user?page=profile"><button
								type="button" class="btn btn-primary profile-button"
								data-toggle="modal">Profilo</button></a></li>
					<li class="nav-item py-0 login-dependent" id="dieta"><a
						href="manageDiet" class="nav-link"><button type="button"
								class="btn btn-primary diet-button" data-toggle="modal">Dieta</button></a></li>
					<li class="nav-item py-0"><input type="button"
						id="logoutButton" class="btn login-dependent logout-button"
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
					<form action="user/signup"
						class="text-center border border-light p-5" method="post"
						name="registrationForm">
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
						<input type="email" name="email" class="form-control mb-4"
							placeholder="E-mail"> <input type="text" name="username"
							class="form-control mb-4" placeholder="Username"> <input
							type="password" name="password" class="form-control"
							placeholder="Password"> <input type="password"
							name="passwordConfirmation" class="form-control"
							placeholder="Conferma password" aria-describedby="passwordHelp">
						<small id="passwordHelp" class="form-text text-muted mb-4">
							Almeno 8 caratteri e un numero</small> <input type="text"
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
	<!-- Chiusura modale registrazione -->

	<!-- Sezione toast -->
	<!-- Toast di notifica login -->
	<div id="welcomeToast" class="toast notification-toast" role="alert"
		aria-live="assertive" aria-atomic="true" data-delay="5000">
		<div class="toast-header color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>
			<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="toast-body" id="toastMessage"></div>
	</div>
	<!-- Toast di aggiunta prodotto -->
	<div id="addToCartToast" class="toast notification-toast" role="alert"
		aria-live="assertive" aria-atomic="true" data-delay="5000">
		<div class="toast-header color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>
			<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="toast-body" id="toastMessage">Prodotto aggiunto al
			carrello.</div>
	</div>
	<!-- Toast di warning no login -->
	<div id="loginToast" class="toast notification-toast" role="alert"
		aria-live="assertive" aria-atomic="true" data-delay="5000">
		<div class="toast-header error-color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>
			<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="toast-body" id="loginToastMessage">Devi fare il
			login prima di poter completare l'ordine.</div>
	</div>
	<!-- Toast prodotto terminato -->
	<div id="unavailableProductToast" class="toast notification-toast"
		role="alert" aria-live="assertive" aria-atomic="true"
		data-delay="5000">
		<div class="toast-header error-color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>
			<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="toast-body" id="unavailableProductToastMessage"></div>
	</div>
	<!-- Toast carrello svuotato -->
	<div id="cartToast" class="toast notification-toast" role="alert"
		aria-live="assertive" aria-atomic="true" data-delay="5000">
		<div class="toast-header error-color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>
			<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="toast-body" id="cartToastMessage">Hai esaurito il
			tempo a disposizione, il tuo carrello è stato svuotato.</div>
	</div>
	<!-- Chiusura sezione toast -->

	<div class="container col-md-10 h-100">
		<!-- Form ricerca -->
		<form id="searchProduct" action="showProducts" method="post">
			<div style="margin-top: 30px; margin-bottom: 30px;"
				class="d-flex justify-content-center h-100">
				<div class="searchbar">
					<input class="search_input" id="nomeProdotto" name="nomeProdotto"
						type="text" placeholder="Cerca prodotto.."> <a
						class="search_icon"><i onclick='$("#searchProduct").submit();'
						class="fas fa-search"></i></a>
				</div>
			</div>
		</form>
		<div class="py-2 text-center">
			<h2 class="title-secondary">PRODOTTI TROVATI</h2>
		</div>
		<!-- Div risultati ricerca -->
		<div class="col-lg-9 mx-auto">
			<div class="row">
				<c:forEach items="${listaProdotti}" var="prodotto">
					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card">
							<img width="200" height="300" class="card-img-top"
								src="${prodotto.imagePath}">
							<div class="card-body h-20">
								<h5>${prodotto.name} ${prodotto.brand}</h5>
								<c:if test="${prodotto.discount != 0}">
									<div>
										<del style="color: red;"> ${prodotto.roundedPrice}&euro;
										</del>
									</div>
								</c:if>

								<b>${prodotto.roundedDiscountedPrice}&euro;</b>
								<button style="float: right; color: #e9b96e;"
									class="btn fa fa-shopping-cart item-icon-cart"
									onclick="$('#addToCartToast').toast('show');										
						updateCart(${prodotto.id}, '${prodotto.name}', ${prodotto.roundedDiscountedPrice}, '${prodotto.superMarket.name}', 'add')"
									id="addToCartProductDiscounted">+</button>
							</div>
							<div class="card-footer">Venduto da:
								${prodotto.superMarket.name}</div>
						</div>
					</div>
				</c:forEach>
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
					<button type="button" class="btn color-scheme"
						data-dismiss="modal">Chiudi</button>
					<c:if test="${customer != null}">
						<a id="orderButton" href="manageOrder"><button
								class="btn color-scheme">Conferma ordine</button></a>
					</c:if>
					<c:if test="${customer == null}">
						<a id="orderAnchor" href="#"><button id="orderButton"
								onclick="$('#modalCart').modal('hide'); $('.modal-backdrop').hide(); $('#loginToast').toast('show');"
								class="btn color-scheme">Conferma ordine</button></a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<!-- Chiusura carrello -->

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
	</footer>
	<!-- Chiusura footer -->
</body>

</html>