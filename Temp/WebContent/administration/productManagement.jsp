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
			<div id="addProduct" class="">
				<button id="addProductButton" onclick="prepareAddProduct()" class="btn btn-success"
					role="button">+ Aggiungi prodotto</button>
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
					<td width="10%"><button
							onclick="prepareModProduct(${product.id})" class="btn btn-info"
							role="button">Modifica prodotto</button></td>
					<td width="10%"><button onclick="deleteProduct(${product.id})"
							class="btn btn-danger" role="button">Elimina prodotto</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<!-- MODALE -->
	<div class="modal" id="result-modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 id="result-modal-title" class="modal-title"></h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<!-- Modal body -->
				<div class="modal-body">
					<div id="result-modal-body" class="jumbotron">
						<p>
							<b>Tipo: </b><span id="result-modal-type"></span>
						</p>
						<p>
							<b>Oggetto: </b><span id="result-modal-object"></span>
						</p>
						<p>
							<b>Stato: </b><span id="result-modal-state"></span>
						</p>
					</div>
				</div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="close" data-dismiss="modal">Chiudi</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal" id="manage-product-modal">
		<div class="modal-dialog" style="max-width: 80%;">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 id="manage-product-modal-title" class="modal-title"></h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<!-- form -->
					<form id="manage-product-form" class="needs-validation" novalidate
						autocomplete="on" enctype="multipart/form-data">
						<div class="form-group">
							<label for="barcode">Codice a barre:</label> <input type="number"
								class="form-control" id="barcode" placeholder="Codice a barre"
								name="barcode" required autocomplete="off">
						</div>
						<div class="form-group">
							<label for="name">Nome:</label> <input type="text"
								class="form-control" id="name" placeholder="Nome" name="name"
								required autocomplete="off">
						</div>
						<div class="form-group">
							<label for="name">Marca:</label> <input type="text"
								class="form-control" id="brand" placeholder="Marca" name="brand"
								required autocomplete="off">
						</div>
						<div class="form-group">
							<label for="weight">Peso in grammi:</label> <input type="number"
								min="1" class="form-control" id="weight" placeholder="Peso"
								name="weight" required autocomplete="off">
						</div>
						<div class="form-group">
							<label for="superMarket">Supermercato:</label> <select
								name="superMarket" required class="form-control"
								id="superMarket">
							</select>
						</div>
						<div class="form-group">
							<label for="category">Categoria:</label> <select name="category"
								class="form-control" id="category" required>
							</select>
						</div>
						<div class="form-group">
							<label for="price">Prezzo in euro:</label> <input type="number"
								min="0.01" class="form-control" id="price" placeholder="Prezzo"
								name="price" required autocomplete="off">
						</div>
						<div class="form-group">
							<label for="quantity">Quantità:</label> <input type="number"
								min="1" class="form-control" id="quantity"
								placeholder="Quantità" name="quantity" required
								autocomplete="off">
						</div>
						<div class="form-group">
							<label for="price">Sconto:</label> <input type="number"
								min="0.00" class="form-control" id="discount"
								placeholder="Sconto" name="discount" required autocomplete="off">
						</div>
						<div class="form-group">
							<label for="image">Immagine:</label><br /> <img id="image"
								name="image" height="200px" alt="immagine"
								src="https://drive.google.com/uc?export=view&id=1DbMKHR-mObaG56QAVDqGHoO4XoXStC2M" />
							<br /> <input type="file" name="image-chooser"
								id="image-chooser" class="file" accept="image/*">
						</div>
						Di marca:
						<div class="form-check">
							<label class="form-check-label"> <input type="radio"
								class="form-check-input" name="offbrand" value="no">SI
							</label>
						</div>
						<div class="form-check">
							<label class="form-check-label"> <input type="radio"
								class="form-check-input" name="offbrand" value="yes" checked>NO
							</label>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button id="manage-product-button" type="button"
						class="btn btn-success"></button>
					<button type="button" class="btn btn-secondary"
						onclick="clearForm()">Reset</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Chiudi</button>
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
	<script src="../js/manageProduct.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>

</body>
</html>