<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Trispesa - Dieta</title>
<!-- Inclusioni (bootstrap, JQuery, assets esterni)  -->
<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../vendor/owl.carousel.js"></script>
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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Script -->
<script src="../js/cart.js"></script>
<script src="../js/login.js"></script>
<script src="../js/diet.js"></script>
<script src="../js/animations.js"></script>
<script src="../js/order.js"></script>
<script> 
    $(function(){
      $("#footerDiv").load("footer.html"); 
    });
</script>
<!-- CSS -->
<link href="../css/footer.css" rel="stylesheet" />
<link href="../css/order-form.css" rel="stylesheet" />
<link href="../css/main.css" rel="stylesheet">

</head>

<body class="bg-light">

	<!-- Memorizzazione di categorie nell'oggetto JS -->
	<c:forEach items="${leafCategoriesList}" var="leafCategory">
		<script type="text/javascript">
			storeLeafCategory('${leafCategory.id}', '${leafCategory.name}');
		</script>
	</c:forEach>

	<!-- Navbar principale  -->
	<jsp:include page="navbar.jsp"></jsp:include>
	<!-- Chiusura navbar principale -->

	<!-- Carrello -->
	<div class="modal fade" id="modalCart" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">Il tuo carrello</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true"></span>
					</button>
				</div>
				<div class="modal-body" id="modalTemp">
					<div class="count">
						<h3>
							<small>Tempo rimanente</small>
						</h3>
						<div id="timer"></div>
					</div>
					<table class="table">
						<thead>
							<tr>
								<th>N.</th>
								<th>Nome prodotto</th>
								<th>Prezzo</th>
								<th></th>

							</tr>
						</thead>
						<tbody id="listaProdottiCarrello">
							<c:set var="totalCartPrice" scope="request" value="${0}" />
							<c:forEach items="${customer.cart}" var="product">
								<c:set var="totalCartPrice" scope="request"
									value="${totalCartPrice + product.key.roundedDiscountedPrice*product.value}" />

								<tr id="product_${product.key.id}">
									<th scope="row" id="productQuantity">${product.value}</th>
									<td id="productName">${product.key.name}</td>
									<td id="productPrice">${product.key.roundedDiscountedPrice*product.value}&euro;</td>
									<td><a><i class="fas fa-times"></i></a></td>
									<td><button type="button"
											onclick="updateCart(${product.key.id}, '${product.key.name}', ${product.key.roundedDiscountedPrice}, '${product.key.superMarket.name}', 'remove');"
											class="btn btn-danger">Rimuovi</button></td>
								</tr>
							</c:forEach>
							<c:forEach items="${anonymousCart}" var="product">
								<c:set var="totalCartPrice" scope="request"
									value="${totalCartPrice + product.key.roundedDiscountedPrice*product.value}" />

								<tr id="product_${product.key.id}">
									<th scope="row" id="productQuantity">${product.value}</th>
									<td id="productName">${product.key.name}</td>
									<td id="productPrice">${product.key.roundedDiscountedPrice*product.value}&euro;</td>
									<td><a><i class="fas fa-times"></i></a></td>
									<td><button type="button"
											onclick="updateCart(${product.key.id}, '${product.key.name}', ${product.key.roundedDiscountedPrice}, '${product.key.superMarket.name}', 'remove');"
											class="btn btn-danger">Rimuovi</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<h2 id="totalCartPrice" class="hidden-xs text-center">${totalCartPrice}&euro;</h2>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn color-scheme" data-dismiss="modal">Chiudi</button>
					<c:if test="${customer != null}">
						<a id="orderButton" href="manageOrder"><button
								class="btn color-scheme">Conferma ordine</button></a>
					</c:if>
					<c:if test="${customer == null}">
						<a id="orderAnchor" href="#"><button id="orderButton"
								onclick="$('#modalCart').modal('hide'); $('.modal-backdrop').hide(); $('#loginToast').toast('show');"
								class="btn color-scheme">Conferma ordine</button></a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<!-- Chiusura carrello -->


	<!-- Div form dieta -->
	<div class="container">
		<div class="py-5 text-center">
			<img class="d-block mx-auto mb-4" src="../images/diet.png" alt=""
				width="128" height="128">
			<h2>Inserisci una dieta</h2>
			<p style="font-size: medium;">Clicca sul segno '+' per aggiungere
				un alimento</p>
			<p class="lead"></p>
			<div class="float-md-right">
				<button type="button" id="addButton" class="btn color-scheme"
					onclick="addField()">
					<b>+</b>
				</button>
			</div>
		</div>

		<form id="dietForm" method="POST">
			<div class="float-md-right">
				<input type="submit" class="btn color-scheme" value="Elabora Spesa">
			</div>
		</form>
	</div>
	<!-- Chiusura div form dieta -->


	<!-- Div risultato calcolo spesa -->
	<div class="container" style="margin-top: 120px;">
		<div class="py-5 text-center" id="dietCart" style="display: none;">
			<h4 class="d-flex justify-content-between align-items-center mb-3">
				<span class="text-muted">Ecco cosa ho trovato</span> <span
					class="badge badge-secondary badge-pill"></span>
			</h4>
			<ul class="list-group mb-3" id="totalUl">
				<li class="list-group-item d-flex justify-content-between"><span>Totale
				</span> <strong id="totalPrice"></strong></li>
			</ul>
			<button type="button" class="btn btn-success"
				onclick="$('#dietConfirmed').modal('show');">Conferma spesa</button>
			<button type="button" class="btn btn-danger"
				onclick="$('#totalUl').empty(); $('#dietCart').hide(); for (var i in spesa) {removeProduct(spesa[i]);} $('#dietCanceled').modal('show'); $('#addButton').prop('disabled', false); $('.minus-button').each(function() {$(this).prop('disabled', false);});">Rifiuta
				spesa</button>
		</div>
	</div>
	<!-- Chiusura div risultato calcolo spesa -->

	<!-- Sezione modali -->
	<div id="dietConfirmed" class="modal fade">
		<div class="modal-dialog modal-confirm">
			<div class="modal-content">
				<div class="modal-header">
					<div class="icon-box">
						<i class="material-icons">&#xE876;</i>
					</div>
					<h4 class="modal-title">Dieta confermata</h4>
				</div>
				<div class="modal-body">
					<p class="text-center">I prodotti trovati dal sistema sono
						stati aggiunti al tuo carrello. Torna alla home per proseguire con
						il tuo ordine.</p>
				</div>
				<div class="modal-footer">
					<a href="home" class="btn color-scheme btn-block">Torna alla
						home</a>
				</div>
			</div>
		</div>
	</div>
	<div id="dietError" class="modal fade">
		<div class="modal-dialog modal-confirm">
			<div class="modal-content">
				<div class="modal-header">
					<div class="icon-box" style="background: red;">
						<i class="material-icons">&#xE000;</i>
					</div>
					<h4 class="modal-title">Dieta non soddisfacibile</h4>
				</div>
				<div class="modal-body">
					<p class="text-center">Siamo spiacenti, ma il sistema non è
						riuscito a riempire il carrello seguendo i criteri della dieta.
						Puoi riprovare scegliendo diverse categorie.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn color-scheme" data-dismiss="modal">Torna
						indietro</button>
				</div>
			</div>
		</div>
	</div>
	<div id="dietCanceled" class="modal fade">
		<div class="modal-dialog modal-confirm">
			<div class="modal-content">
				<div class="modal-header">
					<div class="icon-box" style="background: #c4a000;">
						<i class="material-icons">&#xE002;</i>
					</div>
					<h4 class="modal-title">Dieta annullata</h4>
				</div>
				<div class="modal-body">
					<p class="text-center">Puoi tornare indietro per modificare
						dettagli della dieta e riprovare, o tornare alla home.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn" data-dismiss="modal">Torna
						indietro</button>
					<a href="home" class="btn color-scheme btn-block">Torna alla
						home</a>
				</div>
			</div>
		</div>
	</div>
	<!-- Modale spinner -->
		<div id="waitingModal" class="modal fade">
			<div class="modal-dialog modal-confirm">
				<div class="modal-content">
					<div class="modal-header">
						<div class="icon-box">
							<i class="material-icons">&#xE5D3;</i>
						</div>
						<h4 class="modal-title">Processamento dieta in corso</h4>
					</div>
					<div class="modal-body">
						<p class="text-center">Questa finestrella scomparirà non
							appena la dieta sarà calcolata.</p>
						<div class="d-flex justify-content-center">
							<div class="spinner-border text-success" role="status">
								<span class="sr-only">Loading...</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	<!-- Fine sezione modali -->
	<!-- Footer della pagina -->
	<div id="footerDiv"></div>
	<!-- Chiusura footer -->
</body>
</html>