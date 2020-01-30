<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Trispesa</title>
<!-- Inclusioni (bootstrap, JQuery)  -->
<script src="vendor/jquery/jquery.min.js"></script>


<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="vendor/owl.carousel.js"></script>
<!-- Script -->
<script src="js/cart.js"></script>
<script src="js/login.js"></script>

<link href="css/owl.carousel.css" rel="stylesheet" />
<link href="css/footer.css" rel="stylesheet" />
<link href="css/owl.theme.default.css" rel="stylesheet" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
	integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="css/main.css" rel="stylesheet">

</head>

<body>
	<!-- METTETE IL NAV QUI PLS -->

	<!-- Toast di notifica -->
	<div id="welcomeToast" class="toast notification-toast" role="alert"
		aria-live="assertive" aria-atomic="true" data-delay="5000">
		<div class="toast-header color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>
			<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="toast-body" id="toastMessage"></div>
	</div>
	<!-- Chiusura toast di notifica -->

	<!-- Navbar categorie -->
	<!-- 	<div id="navCategories" class="w3-bar w3-mobile"> -->
	<!-- 		<div class="col-lg-12"> -->
	<!-- 			<div class="scrollmenu rounded"> -->
	<%-- 				<c:forEach items="${listaMacroCategorie}" var="categoria"> --%>
	<%-- 					<c:if test="${not fn:startsWith(categoria.name, 'Altro')}"> --%>
	<!-- 						<a -->
	<!-- 							class="w3-bar-item w3-mobile w3-button w3-hover-none w3-border-white w3-bottombar w3-hover-border-green" -->
	<%-- 							href="showProducts?categoria=${categoria.id}">${categoria.name}</a> --%>
	<%-- 					</c:if> --%>
	<%-- 				</c:forEach> --%>
	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 	</div> -->

	<div class="container">
		<h2>Riepilogo ordini</h2>
		<table class="table table-hover table-responsive">
			<tr>
				<th>Stato</th>
				<th>Indirizzo di consegna</th>
				<th>Metodo di pagamento</th>
				<th>Prezzo</th>
			</tr>
			<c:forEach items="${orders}" var="order">
				<tr>
					<td>${order.currentState}</td>
					<td>${order.deliveryAddress}</td>
					<td>${order.paymentMethod}</td>
					<td>${order.totalPrice}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<footer class="footer-distributed">
		<div class="footer-left">
			<h3>
				Tri<span class="span-title">Spesa</span>
			</h3>
			<p class="footer-company-name">Trispesa © 2020</p>
		</div>
		<div class="footer-center">
			<div>
				<i class="fa fa-map-marker"></i>
				<p>
					<span>Via Pietro Bucci</span>Rende, Cosenza
				</p>
			</div>
			<div>
				<i class="fa fa-phone"></i>
				<p>348-3218976</p>
			</div>
			<div>
				<i class="fa fa-envelope"></i>
				<p>
					<a href="mailto:trispesaStaff@gmail.com">trispesastaff@gmail.com</a>
				</p>
			</div>
		</div>
		<div class="footer-right">
			<p class="footer-company-about">
				<span>Informazioni sito:</span> Questo progetto è stato sviluppato
				da un gruppo di studenti dell'Università della Calabria,
				dipartimento di Matematica e Informatica, per l'esame di Ingegneria
				del Software.
			</p>
			<div class="footer-icons">
				<a href="https://www.mat.unical.it/demacs"><img
					src="images/logo_unical.png" width="24" height="20"></img></a> <a
					href="https://github.com/Goffredson/Trispesa"><i
					class="fa fa-github"></i></a>
			</div>
		</div>
	</footer>
</body>
</html>