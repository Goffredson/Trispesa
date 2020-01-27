<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Trispesa - Dieta</title>

<!-- Inclusioni (Bootstrap, JQuery) -->
<script src="../vendor/jquery/jquery.min.js"></script>
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Script -->
<script src="../js/order.js"></script>
<script src="../js/diet.js"></script>

<!-- CSS -->
<link href="../css/order-form.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body class="bg-light">

	<c:forEach items="${leafCategoriesList}" var="leafCategory">
		<script type="text/javascript">
			storeLeafCategory('${leafCategory.id}', '${leafCategory.name}');
		</script>
	</c:forEach>


	<button type="button" class="btn btn-info" onclick="addField()">Aggiungi
		alimento</button>
	<form id="dietForm" method="POST" action="ahaha">
		<input type="submit" class="btn btn-primary btn-lg btn-block"
			value="Calcola Spesa">
	</form>

	<div class="col-md-4 order-md-2 mb-4" id="dietCart"
		style="display: none;">

		<h4 class="d-flex justify-content-between align-items-center mb-3">
			<span class="text-muted">Il tuo carrello</span> <span
				class="badge badge-secondary badge-pill"></span>
		</h4>
		<ul class="list-group mb-3">

			<li class="list-group-item d-flex justify-content-between"><span>Totale
			</span> <strong id="totalPrice"></strong></li>
		</ul>
		<button type="button" class="btn btn-success"
			onclick="$('#orderConfirmed').modal('show');">Conferma spesa</button>
		<button type="button" class="btn btn-danger"
			onclick="$('#dietCart').empty(); $('#dietCart').hide(); for (var i in spesa) {removeProduct(spesa[i]);} $('#dietCanceled').modal('show');">Rifiuta spesa
			spesa</button>
	</div>

	<!-- Modal HTML -->
	<div id="orderConfirmed" class="modal fade">
		<div class="modal-dialog modal-confirm">
			<div class="modal-content">
				<div class="modal-header">
					<div class="icon-box">
						<i class="material-icons">&#xE876;</i>
					</div>
					<h4 class="modal-title">Ordine confermato</h4>
				</div>
				<div class="modal-body">
					<p class="text-center">La conferma dell'ordine è stata inviata
						via mail. Il riepilogo è disponibile nella sezione ordini</p>
				</div>
				<div class="modal-footer">
					<a href="home" class="btn btn-success btn-block">Torna alla
						home</a>
				</div>
			</div>
		</div>
	</div>
	<div id="dietCanceled" class="modal fade">
		<div class="modal-dialog modal-confirm">
			<div class="modal-content">
				<div class="modal-header">
					<div class="icon-box">
						<i class="material-icons">&#xE876;</i>
					</div>
					<h4 class="modal-title">Dieta cancellata</h4>
				</div>
				<div class="modal-body">
					<p class="text-center">Torna alla home oppure rifai la dieta</p>
				</div>
			</div>
		</div>
	</div>

	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Trispesa
				2020</p>
		</div>
	</footer>

	<!-- Check validità form (da mettere) -->
</body>
</html>