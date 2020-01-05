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
					<li class="nav-item"><a class="nav-link" href="#">Dieta</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Area
							personale</a></li>
					<li class="nav-item"><a class="nav-link" href="#"><img
							src="images/cart.png" width="30" /></a></li>
					<!-- Menu form login -->
					<div class="dropdown">
						<a class="btn btn-secondary dropdown-toggle login" href="#"
							role="button" id="buttonLogin" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">Login</a>

						<div class="dropdown-menu">
							<form class="px-4 py-3">
								<div class="form-group">
									<label for="exampleDropdownFormEmail1">Indirizzo Email</label>
									<input type="email" class="form-control"
										id="exampleDropdownFormEmail1" placeholder="email@example.com">
								</div>
								<div class="form-group">
									<label for="exampleDropdownFormPassword1">Password</label> <input
										type="password" class="form-control"
										id="exampleDropdownFormPassword1" placeholder="Password">
								</div>
								<button type="submit" class="btn btn-primary">Autenticati</button>
							</form>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="#">Effettua registrazione</a> <a
								class="dropdown-item" href="#">Password dimenticata?</a>
						</div>
					</div>
					<!--Chiusura Menu form login -->
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
			<form id="searchProduct" method="post" action="user/showProducts">
				<div class="input-group">
					<input id="nomeProdotto" name="nomeProdotto" type="text" class="form-control"
						placeholder="Prodotto">
				<script type="text/javascript">
					document.getElementById("searchProduct").onsubmit = function(
							e) {


						var nomeProdotto = document
								.getElementById("nomeProdotto").value;
						if (nomeProdotto == "") {
							window.alert("Inserisci un prodotto");
							e.preventDefault();
						}
					}
				</script>

					<div class="container my-4">
						<hr>
						<ul class="list-group list-group-flush">

							<c:forEach items="${listaCategorieFoglia}" var="categoria">
								<li class="list-group-item">
									<!-- Default checked -->
									<div class="custom-control custom-checkbox">
										<input name="${categoria.name}" type="checkbox"
											class="custom-control-input" id="${categoria.name}">
										<label class="custom-control-label" for="${categoria.name}">${categoria.name}</label>
									</div>
								</li>
							</c:forEach>
						</ul>

					</div>
					<input class="btn btn-success" value="Cerca" type="submit" />
					<div class="input-group-append">
						<!-- 
						<span class="input-group-text"><img src="images/search.png"
							width="25px" /></span>
					 -->
					</div>
				</div>
			</form>



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

			<!-- left col -->
			<div class="col-lg-3">

				<h1>Categorie (filtri)</h1>
				<div class="list-group">
					<c:forEach items="${listaMacroCategorie}" var="categoria">
						<a href="user/showProducts?categoria=${categoria.name}"
							class="list-group-item">${categoria.name}</a>
					</c:forEach>
				</div>

			</div>
			<!-- /left col -->

			<div class="col-lg-9">



				<%-- 				<c:forEach items="${listaProdotti}" var="prodotto"> --%>
				<!-- 					<div class="row"> -->
				<!-- 						<div class="col-lg-4 col-md-6 mb-4"> -->
				<!-- 							<div class="card h-100"> -->
				<!-- 								<a href="#"><img class="card-img-top" -->
				<!-- 									src="http://placehold.it/700x400" alt=""></a> -->
				<!-- 								<div class="card-body"> -->
				<!-- 									<h4 class="card-title"> -->
				<%-- 										<a href="#">${prodotto.name}</a> --%>
				<!-- 									</h4> -->
				<%-- 									<h5>${prodotto.price}</h5> --%>
				<!-- 								</div> -->
				<!-- 								<div class="card-footer"> -->
				<!-- 									Le stelline -->
				<!-- 								</div> -->
				<!-- 							</div> -->
				<!-- 						</div> -->
				<%-- 				</c:forEach> --%>

			</div>
			<!-- /.row -->

		</div>
		<!-- /.col-lg-9 -->

	</div>
	<!-- /.row -->

	</div>
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
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>