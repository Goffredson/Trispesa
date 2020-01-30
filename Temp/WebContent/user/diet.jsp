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

	<!-- Memorizzazione di categorie nell'oggetto JS -->
	<c:forEach items="${leafCategoriesList}" var="leafCategory">
		<script type="text/javascript">
			storeLeafCategory('${leafCategory.id}', '${leafCategory.name}');
		</script>
	</c:forEach>

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
								onclick="$('#modalCart').modal('hide'); $('.modal-backdrop').hide(); $('#loginToast').toast('show');"
								class="btn color-scheme">Conferma ordine</button></a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<!-- Chiusura carrello -->


	<!-- Div form dieta -->
	<div class="container">
		<div class="py-5 text-center">
			<img class="d-block mx-auto mb-4" src="../images/diet.png" alt=""
				width="128" height="128">
			<h2>Inserisci una dieta</h2>
			<p style="font-size: medium;">Clicca sul segno '+' per aggiungere
				un alimento</p>
			<p class="lead"></p>
			<div class="float-md-right">
				<button type="button" class="btn color-scheme" onclick="addField()">
					<b>+</b>
				</button>
			</div>
		</div>

		<form id="dietForm" method="POST">
			<div class="float-md-right">
				<input type="submit" class="btn color-scheme" value="Elabora Spesa">
			</div>
		</form>
	</div>
	<!-- Chiusura div form dieta -->


	<!-- Div risultato calcolo spesa -->
	<div class="container" style="margin-top: 120px;">
		<div class="py-5 text-center" id="dietCart" style="display: none;">
			<h4 class="d-flex justify-content-between align-items-center mb-3">
				<span class="text-muted">Ecco cosa ho trovato</span> <span
					class="badge badge-secondary badge-pill"></span>
			</h4>
			<ul class="list-group mb-3" id="totalUl">
				<li class="list-group-item d-flex justify-content-between"><span>Totale
				</span> <strong id="totalPrice"></strong></li>
			</ul>
			<button type="button" class="btn btn-success"
				onclick="$('#dietConfirmed').modal('show');">Conferma spesa</button>
			<button type="button" class="btn btn-danger"
				onclick="$('#totalUl').empty(); $('#dietCart').hide(); for (var i in spesa) {removeProduct(spesa[i]);} $('#dietCanceled').modal('show');">Rifiuta
				spesa</button>
		</div>
	</div>
	<!-- Chiusura div risultato calcolo spesa -->

	<!-- Sezione modali -->
	<div id="dietConfirmed" class="modal fade">
		<div class="modal-dialog modal-confirm">
			<div class="modal-content">
				<div class="modal-header">
					<div class="icon-box">
						<i class="material-icons">&#xE876;</i>
					</div>
					<h4 class="modal-title">Dieta confermata</h4>
				</div>
				<div class="modal-body">
					<p class="text-center">I prodotti trovati dal sistema sono
						stati aggiunti al tuo carrello. Torna alla home per proseguire con
						il tuo ordine.</p>
				</div>
				<div class="modal-footer">
					<a href="home" class="btn color-scheme btn-block">Torna alla
						home</a>
				</div>
			</div>
		</div>
	</div>
	<div id="dietError" class="modal fade">
		<div class="modal-dialog modal-confirm">
			<div class="modal-content">
				<div class="modal-header">
					<div class="icon-box" style="background: red;">
						<i class="material-icons">&#xE000;</i>
					</div>
					<h4 class="modal-title">Dieta non soddisfacibile</h4>
				</div>
				<div class="modal-body">
					<p class="text-center">Siamo spiacenti, ma il sistema non è
						riuscito a riempire il carrello seguendo i criteri della dieta.
						Puoi riprovare scegliendo diverse categorie.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn color-scheme" data-dismiss="modal">Torna
						indietro</button>
				</div>
			</div>
		</div>
	</div>
	<div id="dietCanceled" class="modal fade">
		<div class="modal-dialog modal-confirm">
			<div class="modal-content">
				<div class="modal-header">
					<div class="icon-box" style="background: #c4a000;">
						<i class="material-icons">&#xE002;</i>
					</div>
					<h4 class="modal-title">Dieta annullata</h4>
				</div>
				<div class="modal-body">
					<p class="text-center">Puoi tornare indietro per modificare
						dettagli della dieta e riprovare, o tornare alla home.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn" data-dismiss="modal">Torna
						indietro</button>
					<a href="home" class="btn color-scheme btn-block">Torna alla
						home</a>
				</div>
			</div>
		</div>
	</div>
	<!-- Fine sezione modali -->
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