<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Trispesa - Dieta</title>

<!-- Inclusioni (Bootstrap, JQuery) -->
<script src="../vendor/jquery/jquery.min.js"></script>
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Script -->
<script src="../js/order.js"></script>
<script src="../js/diet.js"></script>
<script src="../js/login.js"></script>

<!-- CSS -->
<link href="../css/order-form.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link href="../css/main.css" rel="stylesheet">

</head>

<body class="bg-light">

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
							$('#loginDropdown').on('show.bs.dropdown', function() {
								$(this).find('.dropdown-menu').first().stop(true, true).slideDown();
							});

							$('#loginDropdown').on('hide.bs.dropdown', function() {
								$(this).find('.dropdown-menu').first().stop(true, true).slideUp();
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

	<c:forEach items="${leafCategoriesList}" var="leafCategory">
		<script type="text/javascript">
			storeLeafCategory('${leafCategory.id}', '${leafCategory.name}');
		</script>
	</c:forEach>

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
	<div class=container>
		<div class="py-5 text-center" id="dietCart"
			style="display: none;">

			<h4 class="d-flex justify-content-between align-items-center mb-3">
				<span class="text-muted">Ecco cosa ho trovato</span> <span
					class="badge badge-secondary badge-pill"></span>
			</h4>
			<ul class="list-group mb-3" id="totalUl">

				<li class="list-group-item d-flex justify-content-between"><span>Totale
				</span> <strong id="totalPrice"></strong></li>
			</ul>
			<button type="button" class="btn btn-success"
				onclick="$('#orderConfirmed').modal('show');">Conferma
				spesa</button>
			<button type="button" class="btn btn-danger"
				onclick="$('#totalUl').empty(); $('#dietCart').hide(); for (var i in spesa) {removeProduct(spesa[i]);} $('#dietCanceled').modal('show');">Rifiuta
				spesa</button>
		</div>
	</div>

	<!-- Modal HTML -->
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
	<!-- Modal HTML -->
	<div id="noDiet" class="modal fade">
		<div class="modal-dialog modal-confirm">
			<div class="modal-content">
				<div class="modal-header">
					<div class="icon-box" style="background:red;">
						<i class="material-icons">&#xE000;</i>
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
	<div id="dietCanceled" class="modal fade">
		<div class="modal-dialog modal-confirm">
			<div class="modal-content">
				<div class="modal-header">
					<div class="icon-box">
						<i class="material-icons">&#xE876;</i>
					</div>
					<h4 class="modal-title">Dieta cancellata</h4>
				</div>
				<div class="modal-body">
					<p class="text-center">Torna alla home oppure rifai la dieta</p>
				</div>
			</div>
		</div>
	</div>



	<!-- Check validità form (da mettere) -->
</body>
</html>