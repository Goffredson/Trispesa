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
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">Gestione</a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="administration/supermarket">Gestione
								supermercati</a> <a class="dropdown-item"
								href="administration/product">Gestione prodotti</a>
						</div></li>
					<li class="nav-item"><a class="nav-link" href="#">Statistiche</a></li>
					<li class="nav-item"><a class="nav-link"
						href="administration/map">Mappe</a></li>
					<li class="nav-item"><a href="user/home" id="logoutButton"
						class="btn btn-danger" role="button">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="row mx-auto">
			<h1 class="mx-auto">Benvenuto ${sessionScope.administrator.username}</h1>
		</div>

		<div class="row mx-auto">
			<div class="col-lg-6 col-md-6 mb-4">
				<a href="administration/supermarket">
					<div class="card d-flex">
						<img class="card-img-top"
							src="images/administration/superMarket.png" height="250" alt="" />
						<div class="card-body mx-auto">
							<h4 class="card-title">Gestione supermercati</h4>
						</div>
					</div>
				</a>
			</div>

			<div class="col-lg-6 col-md-6 mb-4">
				<a href="administration/product">
					<div class="card d-flex">
						<img class="card-img-top" src="images/administration/products.png"
							height="250" alt="" />
						<div class="card-body mx-auto">
							<h4 class="card-title">Gestione prodotti</h4>
						</div>
					</div>
				</a>
			</div>
		</div>

		<div class="row mx-auto">
			<div class="col-lg-6 col-md-6 mb-4">
				<a href="#">
					<div class="card d-flex">
						<img class="card-img-top"
							src="images/administration/statistics.jpg" height="250" alt="" />
						<div class="card-body mx-auto">
							<h4 class="card-title">Statistiche</h4>
						</div>
					</div>
				</a>
			</div>

			<div class="col-lg-6 col-md-6 mb-4">
				<a href="#">
					<div class="card d-flex">
						<img class="card-img-top" src="images/administration/maps.jpg"
							height="250" alt="" />
						<div class="card-body mx-auto">
							<h4 class="card-title">Mappe</h4>
						</div>
					</div>
				</a>
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

</body>

</html>