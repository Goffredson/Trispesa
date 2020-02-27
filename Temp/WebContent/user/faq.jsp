
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Trispesa - FAQ</title>
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
	$(function() {
		$("#footerDiv").load("footer.html");
	});
</script>
<script>
	$(function() {
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
<link href="../css/faq.css" rel="stylesheet">
</head>
<body>

	<!-- Navbar principale  -->
	<jsp:include page="navbar.jsp"></jsp:include>
	<!-- Chiusura navbar principale -->

	<!-- Div form dieta -->
	<div class="container">
		<div class="py-5 text-center">
			<img class="d-block mx-auto mb-4" src="../images/faq.png" alt=""
				width="128" height="128">
			<h2>Domande frequenti</h2>
			<p style="font-size: medium;">Clicca sulla sinistra per scegliere
				un argomento, e sulla destra per ottenere la risposta ad una
				domanda.</p>
			<p class="lead"></p>
		</div>

	</div>
	<!-- Chiusura div form dieta -->

	<div class="container" style="overflow: auto;">
		<div class="col-md-4" style="float: left;">
			<ul class="list-group help-group">
				<div class="faq-list list-group nav nav-tabs">
					<a href="#tab1" class="list-group-item active" role="tab"
						data-toggle="tab">Generali</a> <a href="#tab2"
						class="list-group-item" role="tab" data-toggle="tab"><i
						class="mdi mdi-account"></i> Carrello e Pagamento</a> <a href="#tab4"
						class="list-group-item" role="tab" data-toggle="tab"><i
						class="mdi mdi-star"></i> Contatta Servizio Clienti</a>
				</div>
			</ul>
		</div>
		<div class="col-md-8" style="float: right;">
			<div class="tab-content panels-faq">
				<div class="tab-pane active" id="tab1">
					<div class="panel-group" id="help-accordion-1">
						<div class="panel panel-default panel-help">
							<a href="#opret-produkt" data-toggle="collapse"
								data-parent="#help-accordion-1">
								<div class="panel-heading">
									<h2>Come funziona il servizio?</h2>
								</div>
							</a>
							<div id="opret-produkt" class="collapse in">
								<div class="panel-body">
									<p>E' semplice! Hai a disposizione un carrello virtuale,
										all'interno del quale puoi mettere qualsiasi articolo ti
										aggradi nella quantità che preferisci: proprio come al
										supermercato. Una volta che hai finito di scegliere i
										prodotti, puoi "andare alla cassa" e pagare.</p>

								</div>
							</div>
						</div>
						<div class="panel panel-default panel-help">
							<a href="#rediger-produkt" data-toggle="collapse"
								data-parent="#help-accordion-1">
								<div class="panel-heading">
									<h2>Da quali supermercati posso scegliere di acquistare?</h2>
								</div>
							</a>
							<div id="rediger-produkt" class="collapse">
								<div class="panel-body">
									<p>L'insieme di supermercati affiliati a TriSpesa è
										soggetto a cambiamenti, ma per il momento abbiamo più di 5
										catene di supermercati convenzionate, tra cui Crai, Conad e
										Despar.</p>

								</div>
							</div>
						</div>
						<div class="panel panel-default panel-help">
							<a href="#ret-pris" data-toggle="collapse"
								data-parent="#help-accordion-1">
								<div class="panel-heading">
									<h2>Come faccio a registrarmi?</h2>
								</div>
							</a>
							<div id="ret-pris" class="collapse">
								<div class="panel-body">
									<p>Guarda in alto a destra: vedi il pulsante "Login"?
										Cliccandoci sopra, si aprirà un menu a tendina con diverse
										voci, tra cui "Effettua Registrazione". Clicca proprio li per
										avviare la procedura di registrazione.</p>

								</div>
							</div>
						</div>
						<div class="panel panel-default panel-help">
							<a href="#slet-produkt" data-toggle="collapse"
								data-parent="#help-accordion-1">
								<div class="panel-heading">
									<h2>Quali dati sono necessari per registrarsi al sito?</h2>
								</div>
							</a>
							<div id="slet-produkt" class="collapse">
								<div class="panel-body">
									<p>Sono necessari: nome, cognome, e-mail (oltre ad username
										e password).</p>

								</div>
							</div>
						</div>

					</div>
				</div>
				<div class="tab-pane" id="tab2">
					<div class="panel-group" id="help-accordion-2">
						<div class="panel panel-default panel-help">
							<a href="#help-three" data-toggle="collapse"
								data-parent="#help-accordion-2">
								<div class="panel-heading">
									<h2>Come funziona il carrello?</h2>
								</div>
							</a>
							<div id="help-three" class="collapse in">
								<div class="panel-body">
									<p>Nel momento in cui metti un prodotto nel carrello, quel
										prodotto è già tuo: devi solo pagarlo. Il carrello è dotato di
										un timer, con una durata di default di 30 minuti. Se passano
										30 minuti senza che il carrello venga toccato (ovvero, senza
										che vengano da esso aggiunti o rimossi prodotti), allora il
										carrello viene svuotato. Ciò serve per evitare che un cliente
										possa reclamare un prodotto come suo per un tempo
										indeterminato.</p>

								</div>
							</div>
						</div>
					</div>
					<div class="panel-group" id="help-accordion-3">
						<div class="panel panel-default panel-help">
							<a href="#help-four" data-toggle="collapse"
								data-parent="#help-accordion-3">
								<div class="panel-heading">
									<h2>Come funziona il pagamento?</h2>
								</div>
							</a>
							<div id="help-four" class="collapse in">
								<div class="panel-body">
									<p>Puoi pagare utilizzando un account PayPal oppure una carta di credito .</p>

								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="tab-pane" id="tab4">
					<c:if test="${customer != null}">
						<a href="waitingRoom" id="waitingRoomAnchor" role="button"
							class="btn color-scheme btn-lg btn-block">Contattaci via Live
							Chat</a>

					</c:if>
					<c:if test="${customer == null}">
						<a href="waitingRoom" id="waitingRoomAnchor" role="button"
							class="btn color-scheme btn-lg btn-block disabled-live-chat">Contattaci
							via Live Chat</a>

					</c:if>

				</div>
			</div>
		</div>
	</div>
	<script src="../js/faq.js"></script>
	<div id="footerDiv"></div>
</body>
</html>