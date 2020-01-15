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
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- css -->
<link href="css/main.css" rel="stylesheet">

<script src="js/login.js"></script>
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

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
								<div class="dropdown-item" id="credenzialiErrate" style="color:red; display:none; ">Username o password errati.</div>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#modalLogin">Effettua registrazione</a> <a
									class="dropdown-item" href="#">Password dimenticata?</a>
							</div>
						</div> <li class="nav-item" style="display: none;" id="ordini">
					<a class="nav-link" href="#">Ordini</a>
					</li><li class="nav-item" style="display: none;" id="profilo"><a
						class="nav-link" href="user?page=profile">Profilo</a></li>
					<li class="nav-item" style="display: none;" id="dieta"><a
						class="nav-link" href="#">Dieta</a></li>
					<li><input type="button" id="buttonLogout"
						class="btn btn-primary" style="display: none;" value="Logout"
						onclick="ajaxLog('logout', 500)"></li>

					<!--Chiusura Menu form login -->
					<li class="nav-item"><a class="nav-link" href="administration">Parte
							admin (NON TOCCARE!)</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- 	form registrazione -->
	<div class="modal fade" id="modalLogin" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<!--Header-->
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">Registrazione</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<!--Body-->
				<div class="modal-body">
					<form action="user/signup"
						class="text-center border border-light p-5" method="post"
						name="registrationForm">
						<div class="form-row mb-4">
							<div class="col">
								<!-- First name -->
								<input type="text" name="firstName" class="form-control"
									placeholder="Nome">
							</div>
							<div class="col">
								<!-- Last name -->
								<input type="text" name="lastName" class="form-control"
									placeholder="Cognome">
							</div>
						</div>

						<!-- E-mail -->
						<input type="email" name="email" class="form-control mb-4"
							placeholder="E-mail">

						<div class="col">
							<!-- Username -->
							<input type="text" name="username" class="form-control"
								placeholder="Username">
						</div>

						<!-- Password -->
						<input type="password" name="password" class="form-control"
							placeholder="Password"
							aria-describedby="defaultRegisterFormPasswordHelpBlock">
						<small id="defaultRegisterFormPasswordHelpBlock"
							class="form-text text-muted mb-4"> Almeno 8 caratteri e
							un numero</small>



						<!-- Date birth -->
						<input type="date" name="birthDate" class="form-control"
							placeholder="Data di nascita"
							aria-describedby="defaultRegisterFormPhoneHelpBlock">


						<!-- Sign up button -->
						<div class="modal-footer">
							<input type="submit"
								class="btn btn-info my-4 btn-block waves-effect waves-light"
								value="Registrati">
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- chiusura	form registrazione -->



	<div id="welcomeToast" class="toast" role="alert" aria-live="assertive"
		aria-atomic="true" data-delay="5000">
		<div class="toast-header">
			<img src="" class="rounded mr-2" alt=""> <strong
				class="mr-auto">Trispesa staff</strong> <small>ora</small>
			<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="toast-body" id="toastMessage">Bentornato su Trispesa!</div>
	</div>

	<!-- Content -->
	<div class="container">

		<div class="row">

			<!-- barra di ricerca -->
			<form id="searchProduct" method="post" action="user/showProducts">
				<div class="input-group">
					<input id="nomeProdotto" name="nomeProdotto" type="text"
						class="form-control" placeholder="Prodotto">
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

					<!-- 					<div class="container my-4"> -->
					<!-- 						<hr> -->
					<!-- 						<ul class="list-group list-group-flush"> -->

					<!-- 							TODO: Deve diventare un menù a tendina, checkbox non hanno senso -->
					<%-- 							<c:forEach items="${listaCategorieFoglia}" var="categoria"> --%>
					<!-- 								<li class="list-group-item"> -->
					<!-- 									Default checked -->
					<!-- 									<div class="custom-control custom-checkbox"> -->
					<%-- 										<input name="${categoria.name}" type="checkbox" --%>
					<%-- 											class="custom-control-input" id="${categoria.name}"> --%>
					<%-- 										<label class="custom-control-label" for="${categoria.name}">${categoria.name}</label> --%>
					<!-- 									</div> -->
					<!-- 								</li> -->
					<%-- 							</c:forEach> --%>
					<!-- 						</ul> -->

					<!-- 					</div> -->
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

	<!-- /.container -->

	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Trispesa
				2020</p>
		</div>
	</footer>


	<c:if test="${customer != null}">
		<script type="text/javascript">
			updateNavbarDOM('login', 0);
		</script>
	</c:if>

</body>

</html>