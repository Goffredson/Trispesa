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

</head>
<body>

	<!-- Navigation NON TOCCARE!!! -->
	<nav id="nav"
		class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="../administration">Trispesa</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link"
						href="../administration">Home</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">Gestione</a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="">Gestione supermercati</a> <a
								class="dropdown-item" href="product">Gestione prodotti</a>
						</div></li>
					<li class="nav-item"><a class="nav-link" href="#">Statistiche</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Mappe</a></li>
					<li class="nav-item"><a href="../home" id="logoutButton"
						class="btn btn-danger" role="button">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="d-flex justify-content-between">
			<!-- barra di ricerca -->
			<div id="search-bar" class="input-group">
				<input type="text" class="form-control" placeholder="Supermercato">
				<div class="input-group-append">
					<a href="#"><span class="input-group-text"><img
							src="../images/search.png" width="25px" /></span></a>
				</div>
			</div>
			<!-- Aggiungi supermercato -->
			<div id="addSuperMarket" class="">
				<a href="supermarket/manageSuperMarketForm?action=add"
					class="btn btn-success" role="button"> + Aggiungi supermercato</a>
			</div>
		</div>

		<table class="table table-hover table-responsive">
			<tr>
				<th>Nome</th>
				<th>Città</th>
				<th>Indirizzo</th>
				<th>Affiliato</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items="${superMarkets}" var="superMarket">
				<tr>
					<td>${superMarket.name}</td>
					<td>${superMarket.city}</td>
					<td>${superMarket.address}</td>
					<c:if test="${superMarket.affiliate == true}">
						<td id="supermarket-affiliate">SI</td>
					</c:if>
					<c:if test="${superMarket.affiliate == false}">
						<td id="supermarket-not-affiliate">NO</td>
					</c:if>
					<td width="10%"><a
						href="supermarket/manageSuperMarketForm?action=mod&superMarket=(${superMarket.name},${superMarket.city},${superMarket.address})"
						class="btn btn-info" role="button">modifica</a></td>
					<td width="10%"><c:if test="${superMarket.affiliate == true}">
							<a
								href="supermarket/manage?action=del&superMarket=(${superMarket.name},${superMarket.city},${superMarket.address})"
								class="btn btn-danger" role="button">rimuovi</a>
						</c:if><c:if test="${superMarket.affiliate == false}">
							<a
								href="supermarket/manage?action=aff&superMarket=(${superMarket.name},${superMarket.city},${superMarket.address})"
								class="btn btn-success" role="button">affillia</a>
						</c:if></td>
				</tr>
			</c:forEach>
		</table>

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
	<script src="../vendor/jquery/jquery.min.js"></script>
	<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>