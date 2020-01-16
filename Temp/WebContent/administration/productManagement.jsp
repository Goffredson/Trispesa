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
							<a class="dropdown-item" href="supermarket">Gestione
								supermercati</a> <a class="dropdown-item" href="">Gestione
								prodotti</a>
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
				<input type="text" class="form-control" placeholder="Prodotto">
				<div class="input-group-append">
					<a href="#"><span class="input-group-text"><img
							src="../images/search.png" width="25px" /></span></a>
				</div>
			</div>
			<!-- Aggiungi supermercato -->
			<div id="addSuperMarket" class="">
				<a href="product/manageProductForm?action=add"
					class="btn btn-success" role="button"> + Aggiungi prodotto</a>
			</div>
		</div>

		<table class="table table-hover table-responsive">
			<tr>
				<th>Codice a barre</th>
				<th>Nome</th>
				<th>Marca</th>
				<th>Peso</th>
				<th>Supermercato</th>
				<th>Categoria</th>
				<th>Di marca</th>
				<th>Prezzo</th>
				<th>Quantita</th>
				<th>Sconto</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items="${products}" var="product">
				<tr>
					<td>${product.barcode}</td>
					<td>${product.name}</td>
					<td>${product.brand}</td>
					<td>${product.weight}g</td>
					<td>${product.superMarket}</td>
					<td>${product.category.name}</td>
					<c:if test="${product.offBrand == true}">
						<td id="product-offbrand">NO</td>
					</c:if>
					<c:if test="${product.offBrand == false}">
						<td id="product-not-offbrand">SI</td>
					</c:if>
					<td>&euro; ${product.price}</td>
					<td>${product.quantity}pz.</td>
					<td>&euro; ${product.discount}</td>
					<td width="10%"><a
						href="product/manageProductForm?action=mod&id=${product.id}"
						class="btn btn-info" role="button">modifica</a></td>
					<td width="10%"><a
						href="product/manage?action=del&id=${product.id}"
						class="btn btn-danger" role="button">elimina</a></td>
				</tr>
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