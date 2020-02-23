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

function updateDOMForPayPal() {
	$("#paypal-button").show("slow");
	$("#paymentSelect").hide("slow");
}
function updateDOMForCreditCard() {
	$("#paypal-button").hide("slow");
	$("#paymentSelect").show("slow");
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
$(document).ready(
		function() {
			$("#orderForm").submit(
					function(event) {
						var paymentSelected = $("#selectPayment").find(
								"option:selected").val();
						var addressSelected = $("#selectAddress").find(
								"option:selected").val();
						event.preventDefault();
						$.ajax({
							type : "POST",
							url : "manageOrder",
							data : {
								paymentId : paymentSelected,
								deliveryAddressId : addressSelected,
							},
							success : function() {
								$("#orderConfirmed").modal("show");
							},
							error : function() {
								$("#errorToast").toast("show");
							}
						});
					});
		});
