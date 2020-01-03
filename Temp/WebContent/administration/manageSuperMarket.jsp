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

<!-- Maps -->
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"
	integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
	crossorigin="" />
<script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js"
	integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew=="
	crossorigin=""></script>

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

		<!-- maps -->
		<!-- Box esterno -->
		<div id="main-box" class="container">
			<!--Box ricerca + risultati-->
			<div id="query-box" class="container">
				<div class="input-group">
					<input id="super-market-name" type="text" class="form-control"
						placeholder="Cerca il supermercato">
					<div class="input-group-append">
						<a id="super-market-button" href="#" onclick="querySuperMarkets()"><span
							class="input-group-text"><img
								src="../../images/search.png" width="25px" /></span></a>
					</div>
				</div>
				<div id="query-result" class="overflow-auto">
					<!-- I risultati della query su Nominatim vengono inseriti qui -->
				</div>
			</div>
			<!--Box mappa-->
			<div id="map" class="container">
				<div id="mapid"></div>
			</div>
		</div>

		<!-- form -->
		<form id="add-supermarket-form" method="post"
			action="../supermarket/manage?action=${action}&old=(${superMarket.name},${superMarket.city},${superMarket.address})"
			class="needs-validation" novalidate autocomplete="on">
			<div class="form-group">
				<label for="name">Nome:</label>
				<c:if test="${action == 'add'}">
					<input type="text" class="form-control" id="name"
						placeholder="Nome" name="name" required autocomplete="off">
				</c:if>
				<c:if test="${action == 'mod'}">
					<input type="text" value="${superMarket.name}" class="form-control"
						id="name" placeholder="Nome" name="name" required
						autocomplete="off">
				</c:if>
				<div class="valid-feedback">Valido.</div>
				<div class="invalid-feedback">Perfavore, riempi questo campo.</div>
			</div>
			<div class="form-group">
				<label for="city">Città :</label>
				<c:if test="${action == 'add'}">
					<input type="text" class="form-control" id="city"
						placeholder="Città " name="city" required autocomplete="off">
				</c:if>
				<c:if test="${action == 'mod'}">
					<input type="text" value="${superMarket.city}" class="form-control"
						id="city" placeholder="Città " name="city" required
						autocomplete="off">
				</c:if>
				<div class="valid-feedback">Valido.</div>
				<div class="invalid-feedback">Perfavore, riempi questo campo.</div>
			</div>
			<div class="form-group">
				<label for="address">Indirizzo:</label>
				<c:if test="${action == 'add'}">
					<input type="text" class="form-control" id="address"
						placeholder="Indirizzo" name="address" required autocomplete="off">
				</c:if>
				<c:if test="${action == 'mod'}">
					<input type="text" value="${superMarket.address}"
						class="form-control" id="address" placeholder="Indirizzo"
						name="address" required autocomplete="off">
				</c:if>
				<div class="valid-feedback">Valido.</div>
				<div class="invalid-feedback">Perfavore, riempi questo campo.</div>
			</div>
			<c:if test="${action == 'add'}">
			Affiliato:
			<div class="form-check">
					<label class="form-check-label"> <input type="radio"
						class="form-check-input" name="affiliate" value="yes">SI
					</label>
				</div>
				<div class="form-check">
					<label class="form-check-label"> <input type="radio"
						class="form-check-input" name="affiliate" value="no" checked>NO
					</label>
				</div>
			</c:if>
			<c:if test="${action == 'add'}">
				<button type="submit" class="btn btn-primary">Aggiungi
					supermercato</button>
			</c:if>
			<c:if test="${action == 'mod'}">
				<button type="submit" class="btn btn-primary">Modifica
					supermercato</button>
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

	<!-- form script -->
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

	<!-- Maps scripts -->
	<script>
            var mymap = L.map('mapid').setView([41.458, 12.706], 6);
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(mymap);
            var marker = null;

            $('#super-market-name').keyup(event => {
                if(event.keyCode == 13)
                    $('#super-market-button').click();
            })

            $('#main-box-button').on('click', function() {
                $('#main-box').slideToggle("slow");
            });

            //$('#main-box').hide();

            function querySuperMarkets () {
                var superMarketName = $('#super-market-name').val();
                var queryResults = $('#query-result');
                if(superMarketName == "") {
                    queryResults.empty();
                    mymap.setView([41.458, 12.706], 6);
                }
                else {
                    superMarketName = encodeURI(superMarketName);
                    var url = 'https://nominatim.openstreetmap.org/search?q=?' + superMarketName + '&format=json&polygon=1&addressdetails=1';
                    queryResults.empty();
                    fetch(url).then(response => {return response.json()}).then(data => {
                        for(i in data) {
                            if(data[i].class == 'shop' || data[i].type == 'retail' || data[i].type == 'commercial')
                                queryResults.append('<div id="query-result-element" class="p-2 bd-highlight" onClick="addMarkerOnSuperMarket(' + data[i].lat + ',' + data[i].lon +')">'+ data[i].display_name +'</div>');
                        }
                    }).catch(err => {console.log(err)});
                }
            }

            function addMarkerOnSuperMarket(lat, lon) {
                if(marker != null)
                    marker.removeFrom(mymap);
                marker = L.marker([parseFloat(lat), parseFloat(lon)]).addTo(mymap)
                        .bindPopup('<button type="button" class="btn btn-primary" onClick="location.href=\'http://www.example.com\'">Seleziona il supermercato</button>')
                        .openPopup();
                mymap.setView([parseFloat(lat), parseFloat(lon)], 18);
            }
        </script>

</body>

</html>