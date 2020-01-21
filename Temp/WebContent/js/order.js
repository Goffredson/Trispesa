$(document).ready(function() {
	$('#selectAddress').on('change', function(e) {
		var optionSelected = $("#selectAddress").find("option:selected");
		var address = optionSelected.attr("data-address");
		var province = optionSelected.attr("data-address-province");
		var city = optionSelected.attr("data-address-city");
		var zipcode = optionSelected.attr("data-address-zipcode");
		$("#provinceField").val(province);
		$("#cityField").val(city);
		$("#zipcodeField").val(zipcode);
	});
});

$(document).ready(function() {
	$('#selectPayment').on('change', function(e) {
		var optionSelected = $("#selectPayment").find("option:selected");
		alert(optionSelected);
		var id = optionSelected.val();
		
		var cvv = prompt("Inserisci cvv");
		var expiration = prompt("Inserisci scadenza coi trattini")
	
		$.ajax({
			type : "GET",
			url : "manageOrder",
			data : {
				paymentId : id,
				securityCode : cvv,
				expirationDate : expiration,
				operation : "check" // Forse da togliere, non serve?
			},
			success : function() {
				alert("Dati corretti");
			},
			error : function() {
				alert("Dati sbagliati");
			}
		});
	});
});