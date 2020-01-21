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

<!-- Maps -->
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"
	integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
	crossorigin="" />
<script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js"
	integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew=="
	crossorigin=""></script>

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
				<a href="" data-toggle="modal" data-target="#add-supermarket-modal"
					class="btn btn-success" role="button"> + Aggiungi supermercato</a>
			</div>
		</div>

		<table class="table table-hover table-responsive">
			<tr>
				<th>Nome</th>
				<th>Nazione</th>
				<th>Città</th>
				<th>Indirizzo</th>
				<th>Affiliato</th>
				<th></th>
				<th></th>
			</tr>
			<c:set var="cont" value="1"></c:set>
			<c:forEach items="${superMarkets}" var="superMarket">
				<tr>
					<td>${superMarket.name}</td>
					<td>${superMarket.country}</td>
					<td>${superMarket.city}</td>
					<td>${superMarket.address}</td>
					<c:if test="${superMarket.affiliate == true}">
						<td id="supermarket-affiliate">SI</td>
					</c:if>
					<c:if test="${superMarket.affiliate == false}">
						<td id="supermarket-not-affiliate">NO</td>
					</c:if>
					<td width="10%"><a
						href="supermarket/manageSuperMarketForm?action=mod&id=${superMarket.id}"
						class="btn btn-info" role="button">modifica</a></td>
					<td width="10%"><c:if test="${superMarket.affiliate == true}">
							<a href="" data-toggle="modal"
								data-target="#delete-modal-${cont}" class="btn btn-danger"
								role="button">rimuovi</a>
						</c:if> <c:if test="${superMarket.affiliate == false}">
							<a href="supermarket/manage?action=aff&id=${superMarket.id}"
								class="btn btn-success" role="button">affilia</a>
						</c:if> <!-- The Modal -->
						<div class="modal" id="delete-modal-${cont}">
							<div class="modal-dialog">
								<div class="modal-content">
									<!-- Modal Header -->
									<div class="modal-header">
										<h4 class="modal-title">ATTENZIONE!</h4>
										<button type="button" class="close" data-dismiss="modal">&times;</button>
									</div>
									<!-- Modal body -->
									<div class="modal-body">
										Rimuovendo l'affiliazione del supermercato verrano rimossi
										tutti i prodotti venduti dal supermercato.<br>Sei sicuro
										di voler continuare?
									</div>
									<!-- Modal footer -->
									<div class="modal-footer">
										<a href="supermarket/manage?action=del&id=${superMarket.id}"
											type="button" class="btn btn-danger">Rimuovi affilizione</a>
										<a href="" type="button" class="btn btn-secondary"
											data-dismiss="modal">Annulla</a>
									</div>

								</div>
							</div>
						</div></td>
				</tr>
				<c:set var="cont" value="${cont + 1}"></c:set>
			</c:forEach>
		</table>
	</div>

	<!-- MODALE -->
	<div class="modal" id="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<c:if test="${sessionScope.result == true}">
						<h4 class="modal-title">Operazione eseguita con successo</h4>
					</c:if>
					<c:if test="${sessionScope.result == false}">
						<h4 class="modal-title">Operazione annullata</h4>
					</c:if>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<!-- Modal body -->
				<div class="modal-body">
					<c:if test="${sessionScope.result == true}">
						<div id="success-message" class="jumbotron">
							<p>
								<b>Tipo: </b>${sessionScope.op}
							</p>
							<p>
								<b>Oggetto: </b>${sessionScope.object}
							</p>
							<p>
								<b>Stato: </b>COMPLETATO
							</p>
						</div>
					</c:if>

					<c:if test="${sessionScope.result == false}">
						<div id="error-message" class="jumbotron">
							<p>
								<b>Tipo: </b>${sessionScope.op}
							</p>
							<p>
								<b>Oggetto: </b>${sessionScope.exception.object}
							</p>
							<p>
								<b>Stato: </b>${sessionScope.exception.message}
							</p>
						</div>
					</c:if>
				</div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="close" data-dismiss="modal">Chiudi</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal" id="add-supermarket-modal">
		<div class="modal-dialog" style="max-width: 80%;">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Aggiungi supermercato</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<!-- maps -->
					<!-- Box esterno -->
					<div id="main-box" class="container">
						<!--Box ricerca + risultati-->
						<div id="query-box" class="container">
							<div class="input-group">
								<input id="super-market-name" type="text" class="form-control"
									placeholder="Cerca il supermercato">
								<div class="input-group-append">
									<a id="super-market-button" href="#"
										onclick="querySuperMarkets()"><span
										class="input-group-text"><img
											src="../images/search.png" width="25px" /></span></a>
								</div>
							</div>
							<div id="query-result" class="overflow-auto">
								<!-- I risultati della query su Nominatim vengono inseriti qui -->
							</div>
						</div>
						<!--Box mappa-->
						<div id="map" class="container">
							<div id="mapid"></div>
						</div>
					</div>
					<!-- form -->
					<form id="add-supermarket-form" class="needs-validation" novalidate autocomplete="on">
						<div class="form-group">
							<label for="name">Nome:</label>
							<div id="input">
								<input type="text" class="form-control" id="name"
									placeholder="Nome" name="name" required autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label for="city">Nazione:</label>
							<div id="input">
								<input type="text" class="form-control" id="country"
									placeholder="Nazione" name="country" required
									autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label for="city">Città:</label>
							<div id="input">
								<input type="text" class="form-control" id="town"
									placeholder="Città " name="city" required autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label for="address">Indirizzo:</label>
							<div id="input">
								<input type="text" class="form-control" id="address"
									placeholder="Indirizzo" name="address" required
									autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label for="address">Latitudine:</label>
							<div id="input">
								<input type="number" step="0.0000000000000001"
									class="form-control" id="lat" placeholder="Latitudine"
									name="latitude" required autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label for="address">Longitudine:</label>
							<div id="input">
								<input type="number" step="0.0000000000000001"
									class="form-control" id="lon" placeholder="Longitudine"
									name="longitude" required autocomplete="off">
							</div>
						</div>
						Affiliato:
						<div class="form-check">
							<label class="form-check-label"> <input type="radio"
								class="form-check-input" name="affiliate" value="yes">SI
							</label>
						</div>
						<div class="form-check">
							<label class="form-check-label"> <input type="radio"
								class="form-check-input" name="affiliate" value="no" checked>NO
							</label>
						</div>
						<div class="form-check">
							<c:if test="${superMarket.affiliate == true}">
								<label class="form-check-label"> <input type="radio"
									class="form-check-input" name="affiliate" value="no">NO
								</label>
							</c:if>
							<c:if test="${superMarket.affiliate == false}">
								<label class="form-check-label"> <input type="radio"
									class="form-check-input" name="affiliate" value="no" checked>NO
								</label>
							</c:if>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<a href="#" onclick="addSupermarket()" type="button"
						class="btn btn-success">Aggiungi supermercato</a> <a href="#"
						type="button" class="btn btn-secondary" onclick="clearMapForm()">Reset</a><a
						href="" type="button" class="btn btn-secondary"
						data-dismiss="modal">Chiudi</a>
				</div>
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
	<script src="../vendor/jquery/jquery.min.js"></script>
	<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="../js/manageSuperMarket.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>

	<script>
		$(document).ready( e => {
			if (${sessionScope.result != null}) {
				$('#modal').modal('show');
			}
		});
	</script>

	<%
		if (session.getAttribute("result") != null && (boolean) session.getAttribute("result")) {
			session.removeAttribute("object");
		} else {
			session.removeAttribute("exception");
		}
		session.removeAttribute("result");
		session.removeAttribute("op");
	%>

</body>
</html>