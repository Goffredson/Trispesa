<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<!-- Inclusioni (bootstrap, JQuery, assets esterni)  -->
<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
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
<script> 
    $(function(){
      $("#loginForm").submit(function(e) {
    		e.preventDefault();
			ajaxLog('login', 500);
      }); 
    });
</script>
<!-- CSS -->
<link href="../css/owl.carousel.css" rel="stylesheet" />
<link href="../css/footer.css" rel="stylesheet" />
<link href="../css/owl.theme.default.css" rel="stylesheet" />
<link href="../css/main.css" rel="stylesheet">
</head>

<body class="bg-light" onload="signOut();">
	<!-- Navbar principale  -->
	<jsp:include page="navbar.jsp"></jsp:include>
	<!-- Chiusura navbar principale -->

	<!-- Sezione toast -->
	<!-- Toast di notifica login -->
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
	<!-- Toast di aggiunta prodotto -->
	<div id="addToCartToast" class="toast notification-toast" role="alert"
		aria-live="assertive" aria-atomic="true" data-delay="5000">
		<div class="toast-header color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>
			<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="toast-body" id="toastMessage">Prodotto aggiunto al
			carrello.</div>
	</div>
	<!-- Toast di warning no login -->
	<div id="loginToast" class="toast notification-toast" role="alert"
		aria-live="assertive" aria-atomic="true" data-delay="5000">
		<div class="toast-header error-color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>
			<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
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
			<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="toast-body" id="unavailableProductToastMessage"></div>
	</div>
	<!-- Toast carrello svuotato -->
	<div id="cartToast" class="toast notification-toast" role="alert"
		aria-live="assertive" aria-atomic="true" data-delay="5000">
		<div class="toast-header error-color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>
			<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="toast-body" id="cartToastMessage">Hai esaurito il
			tempo a disposizione, il tuo carrello è stato svuotato.</div>
	</div>
	<div id="noProductsInCart" class="toast notification-toast"
		role="alert" aria-live="assertive" aria-atomic="true"
		data-delay="5000">
		<div class="toast-header error-color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>
			<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="toast-body" id="noProductsInCartMessage">Non puoi
			effettuare un ordine senza prodotti nel carrello.</div>
	</div>
	<div id="noOrderData" class="toast notification-toast" role="alert"
		aria-live="assertive" aria-atomic="true" data-delay="5000">
		<div class="toast-header error-color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>
			<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
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
			<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="toast-body" id="passwordRecoveredMessage">La tua
			nuova password è stata mandata all'indirizzo:</div>
	</div>
	<div id="completaRegistrazione" class="toast notification-toast"
		role="alert" aria-live="assertive" aria-atomic="true"
		data-delay="5000">
		<div class="toast-header color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>
			<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="toast-body" id="passwordRecoveredMessage">Gentile cliente,deve prima completare la registrazione.</div>
	</div>
	<div id="passwordNotRecovered" class="toast notification-toast"
		role="alert" aria-live="assertive" aria-atomic="true"
		data-delay="5000">
		<div class="toast-header error-color-scheme">
			<strong class="mr-auto">Trispesa staff</strong> <small>ora</small>
			<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="toast-body" id="passwordNotRecoveredMessage">Questo
			username non esiste.</div>
	</div>
	<!-- Chiusura sezione toast -->


	<!-- Carousel di categorie -->
	<div class="py-2 text-center">
		<h2 class="title-secondary">CATEGORIE</h2>
	</div>
	<div class="container col-md-10 h-100 ">
		<div id="categoryCarousel"
			style="margin-top: 30px; margin-bottom: 30px;"
			class="owl-carousel owl-theme">
			<c:forEach items="${listaMacroCategorie}" var="categoria">
				<c:if test="${not fn:startsWith(categoria.name, 'Altro')}">
					<a
						class="w3-bar-item w3-mobile w3-button w3-hover-none w3-border-white w3-bottombar w3-hover-border-green"
						href="showProducts?categoria=${categoria.id}">${categoria.name}</a>
				</c:if>
			</c:forEach>
		</div>
		<!-- Form di ricerca -->
		<form id="searchProduct" action="showProducts" method="post">
			<div class="d-flex justify-content-center h-100">
				<div class="searchbar">
					<input class="search_input" id="nomeProdotto" name="nomeProdotto"
						type="text" placeholder="Cerca prodotto.."> <a
						class="search_icon"><i onclick='$("#searchProduct").submit();'
						class="fas fa-search"></i></a>
				</div>
			</div>
		</form>
		<!-- Chiusura form di ricerca -->
		<div class="py-5 text-center">
			<h2 class="title-secondary">OGGI IN OFFERTA</h2>
		</div>
		<!-- Carousel prodotti -->
		<div id="productCarousel" class="owl-carousel owl-theme">
			<c:forEach items="${prodottiScontati}" var="prodottoScontato">
				<div class="card">
					<img width="200" height="250" class="card-img-top"
						src="${prodottoScontato.imagePath}">
					<div class="card-body h-20">
						<h5>${prodottoScontato.name}${prodottoScontato.brand}</h5>

						<div>
							<del style="color: red;">
								${prodottoScontato.roundedPrice}&euro; </del>
						</div>

						<b>${prodottoScontato.roundedDiscountedPrice}&euro;</b>
						<button style="float: right; color: #e9b96e;"
							class="btn fa fa-shopping-cart item-icon-cart"
							onclick="$('#addToCartToast').toast('show');										
						updateCart(${prodottoScontato.id}, '${prodottoScontato.name}', ${prodottoScontato.roundedDiscountedPrice}, '${prodottoScontato.superMarket.name}', 'add')"
							id="addToCartProductDiscounted">+</button>
					</div>
					<div class="card-footer">Venduto da:
						${prodottoScontato.superMarket.name}</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- Chiusura carousel di categorie -->



	<!-- Footer della pagina -->
	<div id="footerDiv"></div>
	<!-- Chiusura footer -->
</body>
</html>