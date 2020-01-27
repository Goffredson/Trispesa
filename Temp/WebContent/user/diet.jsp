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
	<form id="dietForm" method="POST" action="">
		<input type="submit" class="btn btn-primary btn-lg btn-block"
			value="Conferma Ordine">
	</form>



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
					<a href="../home" class="btn btn-success btn-block">Torna alla
						home</a>
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