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
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- css -->
<link href="../css/main.css" rel="stylesheet">
<!-- Bootstrap core JavaScript -->
<script src="../vendor/jquery/jquery.min.js"></script>

<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<script src="../js/cart.js"></script>
<script src="../js/login.js"></script>
<!-- Bootstrap core JavaScript -->
<script src="../vendor/jquery/jquery.min.js"></script>

<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</head>

<body>

	<!-- Navigation  -->
	<nav id="nav"
		class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">Trispesa</a>
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
						<div class="dropdown" id="iduno">
							<a class="btn btn-secondary dropdown-toggle login" href="#"
								role="button" id="buttonLogin" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">Login</a>

							<div class="dropdown-menu" id="iddue">
								<form class="px-4 py-3">
									<div class="form-group">
										<label for="exampleDropdownFormEmail1">Nome utente</label> <input
											type="text" class="form-control" id="inputUsername"
											placeholder="Inserisci nome utente">
									</div>
									<div class="form-group">
										<label for="inputPassword">Password</label> <input
											type="password" class="form-control" id="inputPassword"
											placeholder="Password">
									</div>
									<!-- 
								<button type="submit" id="submitButton"
									class="btn btn-primary">Autenticati</button>
								 -->
									<input type="button" class="btn btn-primary"
										value="Autenticati" onclick="ajaxLog('login', 500)">

								</form>
								<div class="dropdown-item" id="credenzialiErrate"
									style="color: red; display: none;">Username o password
									errati.</div>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#modalLogin">Effettua registrazione</a> <a
									class="dropdown-item" href="#">Password dimenticata?</a>
							</div>
						</div>
					<li class="nav-item" style="display: none;" id="ordini"><a
						class="nav-link" href="#">Ordini</a></li>
					<li class="nav-item" style="display: none;" id="profilo"><a
						class="nav-link" href="user?page=profile">Profilo</a></li>
					<li class="nav-item" style="display: none;" id="dieta"><a
						class="nav-link" href="#">Dieta</a></li>
					<li><input type="button" id="buttonLogout"
						class="btn btn-primary" style="display: none;" value="Logout"
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
	<!-- Content -->
	<div class="container">

		<div class="row">

			<!-- barra di ricerca -->
			<div class="input-group">
				<input type="text" class="form-control" placeholder="Prodotto">
				<div class="input-group-append">
					<a href="#"><span class="input-group-text"><img
							src="images/search.png" width="25px" /></span></a>
				</div>
			</div>

			<!-- carosello -->
			<div id="carouselExampleIndicators"
				class="carousel slide my-4  mx-auto" data-ride="carousel">
				<ol class="carousel-indicators">
					<li data-target="#carouselExampleIndicators" data-slide-to="0"
						class="active"></li>
					<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
					<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<div class="carousel-item active">
						<img class="d-block img-fluid" src="http://placehold.it/900x350"
							alt="First slide">
					</div>
					<div class="carousel-item">
						<img class="d-block img-fluid" src="http://placehold.it/900x350"
							alt="Second slide">
					</div>
					<div class="carousel-item">
						<img class="d-block img-fluid" src="http://placehold.it/900x350"
							alt="Third slide">
					</div>
				</div>
				<a class="carousel-control-prev" href="#carouselExampleIndicators"
					role="button" data-slide="prev"> <span
					class="carousel-control-prev-icon" aria-hidden="true"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
					role="button" data-slide="next"> <span
					class="carousel-control-next-icon" aria-hidden="true"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>
			<!-- /carosello -->
			<!-- /left col -->

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
								<span aria-hidden="true">×</span>
							</button>
						</div>
						<!--Body-->
						<div class="modal-body">

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

								</tbody>
							</table>

						</div>
						<!--Footer-->
						<div class="modal-footer">
							<button type="button" class="btn btn-outline-primary"
								data-dismiss="modal">Chiudi</button>
							<button class="btn btn-primary">Conferma ordine</button>
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
										onclick="c.addProduct(${prodotto.id}, '${prodotto.name}', '${prodotto.superMarket.name}', ${prodotto.price})"
										value="Aggiungi Al Carrello">
									<h5>${prodotto.price}</h5>
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

	</div>
	<!-- /.row -->

	<!-- /.container -->

	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Trispesa
				2020</p>
		</div>
		<!-- /.container -->
	</footer>




</body>

</html>