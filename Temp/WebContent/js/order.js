function verifyPayment() {

	var cvv = $("#securityCode").val();
	var expiration = $("#expirationDate").val();
	var id = $("#selectPayment").find("option:selected").val();

	$.ajax({
		type : "GET",
		url : "manageOrder",
		data : {
			paymentId : id,
			securityCode : cvv,
			expirationDate : expiration,
			operation : "check"
		},
		success : function() {
			$("#paymentModal").modal("hide");
			$('#paymentToast').toast("show");
		},
		error : function() {
			$("#securityCode").addClass("error");
			$("#expirationDate").addClass("error");
		}
	});
}

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
		var id = optionSelected.val();
		$("#paymentModal").modal("show");
	});
});
