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
<link href="../../vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- css -->
<link href="../../css/main.css" rel="stylesheet">

</head>
<body>

	<!-- Navigation NON TOCCARE!!! -->
	<nav id="nav"
		class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="../../administration">Trispesa</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link"
						href="../../administration">Home</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">Gestione</a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="../../administration/supermarket">Gestione
								supermercati</a> <a class="dropdown-item"
								href="../../administration/product">Gestione prodotti</a>
						</div></li>
					<li class="nav-item"><a class="nav-link" href="#">Statistiche</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Mappe</a></li>
					<li class="nav-item"><a href="../../home" id="logoutButton"
						class="btn btn-danger" role="button">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<form id="add-product-form" method="post"
			action="../product/manage?action=${action}" class="needs-validation"
			novalidate autocomplete="on">
			<div class="form-group">
				<label for="barcode">Codice a barre:</label>
				<c:if test="${action == 'add'}">
					<input type="number" class="form-control" id="barcode"
						placeholder="Codice a barre" name="barcode" required
						autocomplete="off">
				</c:if>
				<c:if test="${action == 'mod'}">
					<input type="number" value="${product.barcode}" readonly
						class="form-control" id="barcode" placeholder="Codice a barre"
						name="barcode" required autocomplete="off">
				</c:if>
				<div class="valid-feedback">Valido.</div>
				<div class="invalid-feedback">Perfavore, riempi questo campo.</div>
			</div>
			<div class="form-group">
				<label for="name">Nome:</label>
				<c:if test="${action == 'add'}">
					<input type="text" class="form-control" id="name"
						placeholder="Nome" name="name" required autocomplete="off">
				</c:if>
				<c:if test="${action == 'mod'}">
					<input type="text" value="${product.name}" readonly
						class="form-control" id="name" placeholder="Nome" name="name"
						required autocomplete="off">
				</c:if>
				<div class="valid-feedback">Valido.</div>
				<div class="invalid-feedback">Perfavore, riempi questo campo.</div>
			</div>
			<div class="form-group">
				<label for="weight">Peso in grammi:</label>
				<c:if test="${action == 'add'}">
					<input type="number" min="1" class="form-control" id="weight"
						placeholder="Peso" name="weight" required autocomplete="off">
				</c:if>
				<c:if test="${action == 'mod'}">
					<input type="number" value="${product.weight}" readonly min="1"
						class="form-control" id="weight" placeholder="Peso" name="weight"
						required autocomplete="off">
				</c:if>
				<div class="valid-feedback">Valido.</div>
				<div class="invalid-feedback">Perfavore, riempi questo campo.</div>
			</div>

			<div class="form-group">
				<label for="superMarket">Supermercato:</label>
				<c:if test="${action == 'add'}">
					<select name="superMarket" required class="form-control"
						id="superMarket">
						<c:forEach items="${superMarkets}" var="supermarket">
							<option
								value="(${supermarket.name},${supermarket.city},${supermarket.address})">${supermarket.name}-${supermarket.city}-
								${supermarket.address}</option>
						</c:forEach>
					</select>
				</c:if>
				<c:if test="${action == 'mod'}">
					<select name="superMarket-select" disabled required class="form-control"
						id="superMarket">
						<c:forEach items="${superMarkets}" var="supermarket">
							<c:set var="s1"
								value="(${supermarket.name},${supermarket.city},${supermarket.address})"></c:set>
							<c:set var="s2"
								value="(${product.superMarket.name},${product.superMarket.city},${product.superMarket.address})"></c:set>
							<c:if test="${s1 eq s2}">
								<option selected
									value="(${supermarket.name},${supermarket.city},${supermarket.address})">${supermarket.name}-${supermarket.city}-
									${supermarket.address}</option>
							</c:if>
							<c:if test="${s1 ne s2}">
								<option
									value="(${supermarket.name},${supermarket.city},${supermarket.address})">${supermarket.name}-${supermarket.city}-
									${supermarket.address}</option>
							</c:if>
						</c:forEach>
					</select>
					<input type="hidden" name="superMarket"
						value="(${product.superMarket.name},${product.superMarket.city},${product.superMarket.address})">
				</c:if>
				<div class="valid-feedback">Valido.</div>
				<div class="invalid-feedback">Perfavore, riempi questo campo.</div>
			</div>
			<div class="form-group">
				<label for="category">Categoria:</label>
				<c:if test="${action == 'add'}">
					<select name="category" class="form-control" id="category" required>
						<c:forEach items="${categories}" var="category">
							<option value="${category.familyName}">${category.familyName}</option>
						</c:forEach>
					</select>
				</c:if>
				<c:if test="${action == 'mod'}">
					<select disabled required name="category-select" class="form-control"
						id="category">
						<c:forEach items="${categories}" var="category">
							<c:if
								test="${product.category.familyName eq category.familyName}">
								<option selected value="${category.familyName}">${category.familyName}</option>
							</c:if>
							<c:if
								test="${product.category.familyName ne category.familyName}">
								<option value="${category.familyName}">${category.familyName}</option>
							</c:if>
						</c:forEach>
					</select>
					<input type="hidden" name="category"
						value="${product.category.familyName}">
				</c:if>
				<div class="valid-feedback">Valido.</div>
				<div class="invalid-feedback">Perfavore, riempi questo campo.</div>
			</div>
			<div class="form-group">
				<label for="price">Prezzo in euro:</label>
				<c:if test="${action == 'add'}">
					<input type="number" step="0.01" min="0.01" class="form-control"
						id="price" placeholder="Prezzo" name="price" required
						autocomplete="off">
				</c:if>
				<c:if test="${action == 'mod'}">
					<input type="number" value="${product.price}" step="0.01"
						min="0.01" class="form-control" id="price" placeholder="Prezzo"
						name="price" required autocomplete="off">
				</c:if>
				<div class="valid-feedback">Valido.</div>
				<div class="invalid-feedback">Perfavore, riempi questo campo.</div>
			</div>
			<div class="form-group">
				<label for="quantity">Quantità:</label>
				<c:if test="${action == 'add'}">
					<input type="number" min="1" class="form-control" id="quantity"
						placeholder="Quantità" name="quantity" required autocomplete="off">
				</c:if>
				<c:if test="${action == 'mod'}">
					<input type="number" value="${product.quantity}" min="1"
						class="form-control" id="quantity" placeholder="Quantità"
						name="quantity" required autocomplete="off">
				</c:if>
				<div class="valid-feedback">Valido.</div>
				<div class="invalid-feedback">Perfavore, riempi questo campo.</div>
			</div>
			Di marca:
			<c:if test="${action == 'add'}">
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
			</c:if>
			<c:if test="${action == 'mod'}">
				<div class="form-check">
					<label class="form-check-label"> <c:if
							test="${product.offBrand == false}">
							<input type="radio" checked class="form-check-input"
								name="offbrand" value="no">
						</c:if> <c:if test="${product.offBrand == true}">
							<input type="radio" class="form-check-input" name="offbrand"
								value="no">
						</c:if>SI
					</label>
				</div>
				<div class="form-check">
					<label class="form-check-label"> <c:if
							test="${product.offBrand == false}">
							<input type="radio" class="form-check-input" name="offbrand"
								value="yes">
						</c:if> <c:if test="${product.offBrand == true}">
							<input type="radio" checked class="form-check-input"
								name="offbrand" value="yes">
						</c:if>NO
					</label>
				</div>
			</c:if>
			<c:if test="${action=='add'}">
				<button type="submit" class="btn btn-primary">Aggiungi
					prodotto</button>
			</c:if>
			<c:if test="${action=='mod'}">
				<button type="submit" class="btn btn-primary">Modifica
					prodotto</button>
			</c:if>
		</form>

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
	<script src="../../vendor/jquery/jquery.min.js"></script>
	<script src="../../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<script>
		// Disable form submissions if there are invalid fields
		(function() {
			'use strict';
			window.addEventListener('load',
					function() {
						// Get the forms we want to add validation styles to
						var forms = document
								.getElementsByClassName('needs-validation');
						// Loop over them and prevent submission
						var validation = Array.prototype.filter.call(forms,
								function(form) {
									form.addEventListener('submit', function(
											event) {
										if (form.checkValidity() === false) {
											event.preventDefault();
											event.stopPropagation();
										}
										form.classList.add('was-validated');
									}, false);
								});
					}, false);
		})();
	</script>

</body>

</html>