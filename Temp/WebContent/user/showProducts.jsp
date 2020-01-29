<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Trispesa</title>

<!-- Bootstrap  -->
<script src="../vendor/jquery/jquery.min.js"></script>
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- css -->
<link href="../css/owl.carousel.css" rel="stylesheet" />
<link href="../css/footer.css" rel="stylesheet" />
<link href="../css/owl.theme.default.css" rel="stylesheet" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
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
<link href="../css/main.css" rel="stylesheet">


<!-- Bootstrap core JavaScript -->


<script src="../js/cart.js"></script>
<script src="../js/login.js"></script>


</head>

<body>

	<!-- Navigation  -->
	<nav id="nav"
		class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="home">Trispesa</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto" id="ulNavBar">
					<li class="nav-item active"><a class="nav-link" href="#">Home</a></li>

					<li class="nav-item"><a class="nav-link" href="#"><img
							src="images/cart.png" width="30" /></a></li>
					<!-- Menu form login -->
					<li>
						<button type="button" class="btn btn-primary" data-toggle="modal"
							data-target="#modalCart">Carrello</button>
					</li>
					<li>
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
					<li class="nav-item" style="display: none;" id="ordini"><a
						class="nav-link" href="#">Ordini</a></li>
					<li class="nav-item" style="display: none;" id="profilo"><a
						class="nav-link" href="../user?page=profile">Profilo</a></li>
					<li class="nav-item" style="display: none;" id="dieta"><a
						href="manageDiet" class="nav-link" href="#">Dieta</a></li>
					<li><input type="button" id="logoutButton"
						class="btn btn-primary login-dependent" value="Logout"
						onclick="ajaxLog('logout', 500)"></li>

					<!--Chiusura Menu form login -->
					<li class="nav-item"><a class="nav-link" href="administration">Parte
							admin</a></li>
				</ul>
			</div>
		</div>
		<c:if test="${customer != null}">
			<script type="text/javascript">
			updateNavbarDOM('login', 0);
		</script>
		</c:if>
	</nav>

	<!-- Toast di notifica -->
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
	<div id="productToast" class="toast notification-toast" role="alert"
		aria-live="assertive" aria-atomic="true" data-delay="5000">
		<div class="toast-header error-color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>
			<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="toast-body" id="productToastMessage"></div>
	</div>
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
			tempo a disposizione,il tuo carrello verrà svuotato</div>
	</div>
	<!-- Chiusura toast di notifica -->

	<!-- Content -->
	<div class="container">
		<!-- Navbar categorie -->
		<form id="searchProduct" action="showProducts" method="post">
			<div class="container h-100 ">
				<div class="d-flex justify-content-center h-100">
					<div class="searchbar">
						<input class="search_input" id="nomeProdotto" name="nomeProdotto"
							type="text" placeholder="Cerca prodotto.."> <a
							class="search_icon"><i
							onclick='$("#searchProduct").submit();' class="fas fa-search"></i></a>
					</div>
				</div>
			</div>
		</form>
		<!-- Button trigger modal-->
		<!-- Modal: modalCart -->
		<div class="modal fade" id="modalCart" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<!--Header-->
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">Il tuo carrello</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true"></span>
						</button>
					</div>
					<!--Body-->
					<div class="modal-body">
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

						<h2 id="totalCartPrice" class="hidden-xs text-center">
							${totalCartPrice} &euro;</h2>

					</div>
					<!--Footer-->
					<div class="modal-footer">
						<button type="button" class="btn btn-outline-primary"
							data-dismiss="modal">Chiudi</button>
						<c:if test="${customer != null}">
							<a id="orderButton" href="manageOrder"><button
									class="btn btn-primary">Conferma ordine</button></a>
						</c:if>
						<c:if test="${customer == null}">
							<a id="orderAnchor" href=""><button id="orderButton"
									onclick="$('#modalCart').modal('hide'); $('.modal-backdrop').hide(); $('#loginToast').toast('show');"
									class="btn btn-primary">Conferma ordine</button></a>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal: modalCart -->

		<div class="col-lg-9">
			<c:forEach items="${listaProdotti}" var="prodotto">
				<div class="row">
					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card h-100">
							<img class="card-img-top" src="../${prodotto.imagePath}"
								height="250" alt="" />
							<div class="card-body">
								<h4 class="card-title">
									<a href="#">${prodotto.name}</a>
								</h4>
								<input type="button"
									onclick="updateCart(${prodotto.id}, '${prodotto.name}', ${prodotto.roundedDiscountedPrice}, '${prodotto.superMarket.name}', 'add')"
									value="Aggiungi Al Carrello">
								<h5>${prodotto.roundedPrice}</h5>
								&euro;
								<h5>${prodotto.superMarket.name}</h5>
							</div>
							<div class="card-footer">Le stelline</div>
						</div>
					</div>
				</div>
			</c:forEach>

		</div>
		<!-- /.row -->

	</div>
	<!-- /.col-lg-9 -->
	<!-- /.row -->

	<!-- /.container -->

	<footer class="footer-distributed">
		<div class="footer-left">
			<h3>
				Tri<span>Spesa</span>
			</h3>
			<p class="footer-company-name">Trispesa © 2020</p>
		</div>
		<div class="footer-center">
			<div>
				<i class="fa fa-map-marker"></i>
				<p>
					<span>Via Pietro Bucci</span>Rende,Cosenza
				</p>
			</div>
			<div>
				<i class="fa fa-phone"></i>
				<p>348-3218976</p>
			</div>
			<div>
				<i class="fa fa-envelope"></i>
				<p>
					<a href="mailto:trispesaStaff@gmail.com">trispesaStaff@gmail.com</a>
				</p>
			</div>
		</div>
		<div class="footer-right">
			<p class="footer-company-about">
				<span>Informazioni sito:</span> Questo progetto è stato creato da un
				gruppo di studenti dell'università della Calabria,dipartimento di
				matematica e informatica, per l'esame di ingegneria del software!
			</p>
			<div class="footer-icons">
				<a href="https://www.mat.unical.it/demacs"><img
					src="../images/logo_unical.png" width="24" height="24"></img></a> <a
					href="https://github.com/Goffredson/Trispesa"><i
					class="fa fa-github"></i></a>
			</div>
		</div>
	</footer>




</body>

</html>