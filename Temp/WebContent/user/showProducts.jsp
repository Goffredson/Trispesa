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
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- css -->
<link href="../css/main.css" rel="stylesheet">

<script src="../js/cart.js"></script>
</head>

<body>

	<!-- Navigation NON TOCCARE!!! -->
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
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="#">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Dieta</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Area
							personale</a></li>
					<li>
						<button type="button" class="btn btn-primary" data-toggle="modal"
							data-target="#modalCart">Carrello</button>
					</li>
					<li class="nav-item"><a href="#" id="loginButton"
						class="btn btn-success" role="button">Login</a></li>
					<li class="nav-item"><a class="nav-link" href="administration">Parte
							admin (NON TOCCARE!)</a></li>
				</ul>
			</div>
		</div>
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
										onclick="c.addProduct(${prodotto.barcode}, '${prodotto.superMarket.name}', ${prodotto.quantity}, ${prodotto.price})"
										value="Aggiungi Al Carrello">
									<h5>${prodotto.price}</h5>
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

	<!-- Bootstrap core JavaScript -->
	<script src="../vendor/jquery/jquery.min.js"></script>

	<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


</body>

</html>