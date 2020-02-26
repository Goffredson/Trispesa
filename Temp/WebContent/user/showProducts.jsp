<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>TriSpesa - Risultato ricerca</title>
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
<script src="../js/animations.js"></script>
<script src="../js/order.js"></script>
<script> 
    $(function(){
      $("#footerDiv").load("footer.html"); 
    });
</script>
<!-- CSS -->
<link href="../css/footer.css" rel="stylesheet" />
<link href="../css/main.css" rel="stylesheet">
</head>

<body class="bg-light">

	<!-- Navbar principale  -->
	<jsp:include page="navbar.jsp"></jsp:include>
	<!-- Chiusura navbar principale -->

	<!-- Sezione toast -->
	<!-- Toast di notifica login -->
	<div id="welcomeToast" class="toast notification-toast" role="alert"
		aria-live="assertive" aria-atomic="true" data-delay="5000">
		<div class="toast-header color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>

		</div>
		<div class="toast-body" id="toastMessage"></div>
	</div>
	<!-- Toast di aggiunta prodotto -->
	<div id="addToCartToast" class="toast notification-toast" role="alert"
		aria-live="assertive" aria-atomic="true" data-delay="5000">
		<div class="toast-header color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>

		</div>
		<div class="toast-body" id="toastMessage">Prodotto aggiunto al
			carrello.</div>
	</div>
	<!-- Toast di warning no login -->
	<div id="loginToast" class="toast notification-toast" role="alert"
		aria-live="assertive" aria-atomic="true" data-delay="5000">
		<div class="toast-header error-color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>

		</div>
		<div class="toast-body" id="loginToastMessage">Devi fare il
			login prima di poter completare l'ordine.</div>
	</div>
	<!-- Toast prodotto terminato -->
	<div id="unavailableProductToast" class="toast notification-toast"
		role="alert" aria-live="assertive" aria-atomic="true"
		data-delay="5000">
		<div class="toast-header error-color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>

		</div>
		<div class="toast-body" id="unavailableProductToastMessage"></div>
	</div>
	<!-- Toast carrello svuotato -->
	<div id="cartToast" class="toast notification-toast" role="alert"
		aria-live="assertive" aria-atomic="true" data-delay="5000">
		<div class="toast-header error-color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>

		</div>
		<div class="toast-body" id="cartToastMessage">Hai esaurito il
			tempo a disposizione, il tuo carrello è stato svuotato.</div>
	</div>
	<div id="noProductsInCart" class="toast notification-toast"
		role="alert" aria-live="assertive" aria-atomic="true"
		data-delay="5000">
		<div class="toast-header error-color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>

		</div>
		<div class="toast-body" id="noProductsInCartMessage">Non puoi
			effettuare un ordine senza prodotti nel carrello.</div>
	</div>
	<div id="noOrderData" class="toast notification-toast" role="alert"
		aria-live="assertive" aria-atomic="true" data-delay="5000">
		<div class="toast-header error-color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>

		</div>
		<div class="toast-body" id="noOrderDataMessage">Prima di
			effettuare un ordine, devi registrare almeno un metodo di pagamento
			ed un indirizzo di consegna.</div>
	</div>
	<div id="passwordRecovered" class="toast notification-toast"
		role="alert" aria-live="assertive" aria-atomic="true"
		data-delay="5000">
		<div class="toast-header color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>

		</div>
		<div class="toast-body" id="passwordRecoveredMessage">La tua
			nuova password è stata mandata all'indirizzo: .</div>
	</div>
	<div id="passwordNotRecovered" class="toast notification-toast"
		role="alert" aria-live="assertive" aria-atomic="true"
		data-delay="5000">
		<div class="toast-header error-color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>

		</div>
		<div class="toast-body" id="passwordNotRecoveredMessage">Questo
			username non esiste.</div>
	</div>
	<!-- Chiusura sezione toast -->

	<div class="container col-md-10 h-100">
		<!-- Form ricerca -->
		<form id="searchProduct" action="showProducts" method="post">
			<div style="margin-top: 30px; margin-bottom: 30px;"
				class="d-flex justify-content-center h-100">
				<div class="searchbar">
					<input class="search_input" id="nomeProdotto" name="nomeProdotto"
						type="text" placeholder="Cerca prodotto.."> <a
						class="search_icon"><i onclick='$("#searchProduct").submit();'
						class="fas fa-search"></i></a>
				</div>
			</div>
		</form>
		<div class="py-2 text-center">
			<h2 class="title-secondary">PRODOTTI TROVATI</h2>
		</div>
		<!-- Div risultati ricerca -->
		<div class="col-lg-9 mx-auto">
			<div class="row">
				<c:if test="${fn:length(listaProdotti) == 0}">
					<div class="container">
						<!-- Div logo -->
						<div class="py-5 text-center">
							<img class="d-block mx-auto mb-4" src="../images/noResults.png"
								alt="" width="128" height="128">
							<h2>Nessun prodotto trovato</h2>
							<p style="font-size: medium;">Prova a cercare usando parole diverse.</p>
							<p class="lead"></p>
						</div>
					</div>
				</c:if>
				<c:if test="${fn:length(listaProdotti) != 0}">
					<c:forEach items="${listaProdotti}" var="prodotto">
						<div class="col-lg-4 col-md-6 mb-4">
							<div class="card">
								<img width="200" height="300" class="card-img-top"
									src="${prodotto.imagePath}">
								<div class="card-body h-20">
									<h5>${prodotto.name}${prodotto.brand}</h5>
									<c:if test="${prodotto.discount != 0}">
										<div>
											<del style="color: red;">
												${prodotto.roundedPrice}&euro; </del>
										</div>
									</c:if>

									<b>${prodotto.roundedDiscountedPrice}&euro;</b>
									<button style="float: right; color: #e9b96e;"
										class="btn fa fa-shopping-cart item-icon-cart"
										onclick="$('#addToCartToast').toast('show');										
						updateCart(${prodotto.id}, '${prodotto.name}', ${prodotto.roundedDiscountedPrice}, '${prodotto.superMarket.name}', 'add')"
										id="addToCartProductDiscounted">+</button>
								</div>
								<div class="card-footer">Venduto da:
									${prodotto.superMarket.name}</div>
							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</div>

	<div id="footerDiv"></div>

</body>

</html>