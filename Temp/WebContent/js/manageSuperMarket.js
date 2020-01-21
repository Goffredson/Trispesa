// Disable form submissions if there are invalid fields
(function() {
	'use strict';
	window.addEventListener('load', function() {
		// Get the forms we want to add validation styles to
		var forms = document.getElementsByClassName('needs-validation');
		// Loop over them and prevent submission
		var validation = Array.prototype.filter.call(forms, function(form) {
			form.addEventListener('submit', function(event) {
				if (form.checkValidity() === false) {
					event.preventDefault();
					event.stopPropagation();
				}
				form.classList.add('was-validated');
			}, false);
		});
	}, false);
})();

var mymap = L.map('mapid').setView([41.458, 12.706], 6);
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(mymap);
var marker = null;

$('#super-market-name').keyup(event => {
    if(event.keyCode == 13)
        $('#super-market-button').click();
});

/*
 * $('#main-box-button').on('click', function() {
 * $('#main-box').slideToggle("slow"); });
 */

// $('#main-box').hide();

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
        mymap.setView([41.458, 12.706], 6);
    }
    else {
        superMarketName = encodeURI(superMarketName);
        var url = 'https://nominatim.openstreetmap.org/search?q=?' + superMarketName + '&format=json&polygon=1&addressdetails=1';
        queryResults.empty();
        fetch(url).then(response => {return response.json()}).then(data => {
            for(i in data) {
            	if(data[i].class == 'shop' || data[i].type == 'retail' || data[i].type == 'commercial'){
                	var town;
                	var country = data[i].address.country;
                	if (data[i].address.town != undefined)
                		town = data[i].address.town;
                	else if(data[i].address.city != undefined)
                		town = data[i].address.city;
                	else
                		town = data[i].address.village;
                	var parameterString = data[i].lat + ', ' + data[i].lon + ', \'' + removeSingleQuotes(data[i].address[data[i].type]) + '\', \'' + removeSingleQuotes(country) + '\', \''+ removeSingleQuotes(town) + '\', \'' + removeSingleQuotes(data[i].display_name) + '\'';
                    queryResults.append('<div id="query-result-element" class="p-2 bd-highlight" onClick="addMarkerOnSuperMarket(' + parameterString + ')">'+ data[i].display_name +'</div>');
            	}
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

function addMarkerOnSuperMarket(lat, lon, name, country, town, address) {
    var parameterString = lat + ', ' + lon + ', \'' + name + '\', \'' + country + '\', \'' + town + '\', \'' + address + '\'';
    addMarkerOnMap(lat, lon);
    marker.bindPopup('<button type="button" class="btn btn-primary" onClick="fillMapForm(' + parameterString + ')">Seleziona il supermercato</button>')
          .openPopup();
}

function fillMapForm(lat, lon, name, country, town, address) {
    $('#name').val(name);
    $('#country').val(country);
    $('#town').val(town);
    $('#address').val(address);
    $('#lat').val(lat);
    $('#lon').val(lon);
    $('#add-supermarket-form').valid();
}

function removeSingleQuotes(string) {
    if(string != undefined)
        return string.replace(/'/g, "");
    return 'Da specificare';
}

function clearMapForm() {
    $('#name').val('');
    $('#country').val('');
    $('#town').val('');
    $('#address').val('');
    $('#lat').val('');
    $('#lon').val('');
    $('#query-result').empty();
    if (marker != null)
    	marker.removeFrom(mymap);
    mymap.setView([41.458, 12.706], 6);
}

	$(document).ready(event => {
		if ($('#lat').val() != '' && $('#lon').val() != '')
        addMarkerOnMap($('#lat').val(), $('#lon').val());
	});
	
function addSupermarket() {
	if ($('#add-supermarket-form').valid() === false) {
		$('#add-supermarket-form').addClass("was-validated").removeClass("needs-validation");
		return;
	}
	
	var supermarket = {
			id : 0,
			name : $('#name').val(),
			country : $('#country').val(),
			city : $('#town').val(),
			address : $('#address').val(),
			latitude : $('#latitude').val(),
			longitude : $('#longitude').val(),
			affiliate : $('#affiliate').val()
	};
	$.ajax({
		type : "POST",
		url : "supermarket/manage?action=add",
		data : supermarket,
		success : function(data) {
			// boh
		}
	});
}

$( document ).ready( function () {
	$('#add-supermarket-form').validate({
		rules : {
			name : "required",
			country : "required",
			city : "required",
			address : "required",
			latitude : {
				required : true,
				number : true
			},
			longitude : {
				required : true,
				number : true
			}
		},
		messages : {
			name : "Inserisci nome",
			country : "Inserisci nazione",
			city : "Inserisci citta'",
			address : "Inserisci indirizzo",
			latitude : {
				required : "Inserisci latitudine",
				number : "La latitudine deve essere un numero"
			},
			longitude : {
				required : "Inserisci longitudine",
				number : "La longitudine deve essere un numero"
			}
		},
		errorElement: "em",
		errorPlacement: function ( error, element ) {
			// Add the `help-block` class to the error element
			error.addClass( "help-block" );
			error.addClass("invalid-feedback");

			if ( element.prop( "type" ) === "checkbox" ) {
				error.insertAfter( element.parent( "label" ) );
			} else {
				error.insertAfter( element );
			}
		},
		highlight: function ( element, errorClass, validClass ) {
			$( element ).addClass( "is-invalid" ).removeClass( "is-valid" );
		},
		unhighlight: function (element, errorClass, validClass) {
			$( element ).addClass( "is-valid" ).removeClass( "is-invalid" );
		}
	});
})