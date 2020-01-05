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
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- css -->
<link href="../css/main.css" rel="stylesheet">

<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"
	integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
	crossorigin="" />
<!-- Make sure you put this AFTER Leaflet's CSS -->
<script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js"
	integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew=="
	crossorigin=""></script>

</head>
<body>

	<!-- Navigation NON TOCCARE!!! -->
	<nav id="nav"
		class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">Trispesa</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="#">Home</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">Gestione</a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="administration/supermarket">Gestione
								supermercati</a> <a class="dropdown-item"
								href="administration/product">Gestione prodotti</a>
						</div></li>
					<li class="nav-item"><a class="nav-link" href="#">Statistiche</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Mappe</a></li>
					<li class="nav-item"><a href="home" id="logoutButton"
						class="btn btn-danger" role="button">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<!--Box esterno-->
		<div id="main-box" class="container">
			<!--Box ricerca + risultati-->
			<div id="query-box" class="container">
				<div class="input-group">
					<input id="super-market-name" type="text" class="form-control"
						placeholder="Cerca il supermercato">
					<div class="input-group-append">
						<a id="super-market-button" href="#" onclick="querySuperMarkets()"><span
							class="input-group-text"><img src="../images//search.png"
								width="25px" /></span></a>
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

	<script>
            var mymap = L.map('mapid').setView([41.458, 12.706], 6);
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(mymap);
            var marker = null;

            $('#super-market-name').keyup(event => {
                if(event.keyCode == 13)
                    $('#super-market-button').click();
            });

            /*$('#main-box-button').on('click', function() {
                $('#main-box').slideToggle("slow");
            });*/

            //$('#main-box').hide();

            $('#lat').change(event => {
                if($('#lat').val() != '' && $('#lon').val() != '')
                    addMarkerOnMap($('#lat').val(), $('#lon').val());
                else 
                    mymap.setView([41.458, 12.706], 6);
            });

            $('#lon').change(event => {
                if($('#lat').val() != '' && $('#lon').val() != '')
                    addMarkerOnMap($('#lat').val(), $('#lon').val());
                else 
                    mymap.setView([41.458, 12.706], 6);
            });

            function querySuperMarkets () {
                var superMarketName = $('#super-market-name').val();
                var queryResults = $('#query-result');
                if(superMarketName == "") {
                    queryResults.empty();
                    clearMapForm();
                    mymap.setView([41.458, 12.706], 6);
                }
                else {
                    superMarketName = encodeURI(superMarketName);
                    var url = 'https://nominatim.openstreetmap.org/search?q=?' + superMarketName + '&format=json&polygon=1&addressdetails=1';
                    queryResults.empty();
                    fetch(url).then(response => {return response.json()}).then(data => {
                        for(i in data) {
                            var parameterString = data[i].lat + ', ' + data[i].lon + ', \'' + data[i].address[data[i].type] + '\', \'' + data[i].address.town + '\', \'' + data[i].display_name + '\'';
                            if(data[i].class == 'shop' || data[i].type == 'retail' || data[i].type == 'commercial')
                                queryResults.append('<div id="query-result-element" class="p-2 bd-highlight" onClick="addMarkerOnSuperMarket(' + parameterString + ')">'+ data[i].display_name +'</div>');
                        }
                    }).catch(err => {console.log(err)});
                }
            }

            function addMarkerOnMap(lat, lon) {
                if(marker != null)
                    marker.removeFrom(mymap);
                marker = L.marker([lat, lon]).addTo(mymap);
                mymap.setView([lat, lon], 18);
            }

            function addMarkerOnSuperMarket(lat, lon, name, town, address) {
                var parameterString = lat + ', ' + lon + ', \'' + name + '\', \'' + town + '\', \'' + address + '\'';
                addMarkerOnMap(lat, lon);
                marker.bindPopup('<button type="button" class="btn btn-primary" onClick="fillMapForm(' + parameterString + ')">Seleziona il supermercato</button>')
                      .openPopup();
            }

            function fillMapForm(lat, lon, name, town, address) {
                $('#name').val(name);
                $('#town').val(town);
                $('#address').val(address);
                $('#lat').val(lat);
                $('#lon').val(lon);
            }

            function clearMapForm() {
                $('#name').val('');
                $('#town').val('');
                $('#address').val('');
                $('#lat').val('');
                $('#lon').val('');
            }
        </script>

</body>

</html>