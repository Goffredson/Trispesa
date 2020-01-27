$(document)
		.ready(
				function() {
					$.validator.addMethod("regex", function(value, element,
							regexp) {
						var re = new RegExp(regexp);
						return this.optional(element) || re.test(value);
					}, "Per favore, inserisci un numero di carta valido");

					$('#manage-payment-method-form')
							.validate(
									{
										rules : {
											company : "required",
											cardNumber : {
												required : true,
												regex : /\d\d\d\d-\d\d\d\d-\d\d\d\d-\d\d\d\d/
											},
											owner : "required",
											expirationDate : "required",
											securityCode : {
												required : true,
												digits : true,
												minlength : 3,
												maxlength : 3,
											},
										},
										messages : {
											company : "Per favore, inserisca il nome del circuito di pagamento",
											cardNumber : {
												required : "Per favore, inserisca il numero della carta",
												regex : "Per favore, inserisci un numero di carta valido",
											},
											owner : "Per favore, inserisca il titolare della carta",
											expirationDate : "Per favore, inserisca mese e anno di scadenza",
											securityCode : {
												required : "Per favore, inserisca il codice di sicurezza di 3 cifre",
												digits : "Per favore, inserisca il codice di sicurezza di 3 cifre",
												minlength : "Per favore, inserisca il codice di sicurezza di 3 cifre",
												maxlength : "Per favore, inserisca il codice di sicurezza di 3 cifre",
											},
										},
										errorElement : "em",
										errorPlacement : function(error,
												element) {
											// Add the `help-block` class to the
											// error element
											error.addClass("help-block");
											error.addClass("invalid-feedback");

											if (element.prop("type") === "checkbox") {
												error.insertAfter(element
														.parent("label"));
											} else {
												error.insertAfter(element);
											}
										},
										highlight : function(element,
												errorClass, validClass) {
											$(element).addClass("is-invalid")
													.removeClass("is-valid");
										},
										unhighlight : function(element,
												errorClass, validClass) {
											$(element).addClass("is-valid")
													.removeClass("is-invalid");
										}
									});

					// validazione form DA AGGIUSTARE
					$('#manage-delivery-address-form').validate(
							{
								rules : {
									country : "required",
									city : "required",
									address : "required",
									zipcode : "required",
									province : "required",
								},
								messages : {
									country : "Per favore, inserisca la nazione",
									city : "Per favore, inserisca la città",
									address : "Per favore, inserisca l'indirizzo",
									zipcode : "Per favore, inserisca il codice di avviamento postale",
									province : "Per favore, inserisca la provincia",
								},
								errorElement : "em",
								errorPlacement : function(error, element) {
									// Add the `help-block` class to the
									// error element
									error.addClass("help-block");
									error.addClass("invalid-feedback");

									if (element.prop("type") === "checkbox") {
										error.insertAfter(element
												.parent("label"));
									} else {
										error.insertAfter(element);
									}
								},
								highlight : function(element, errorClass,
										validClass) {
									$(element).addClass("is-invalid")
											.removeClass("is-valid");
								},
								unhighlight : function(element, errorClass,
										validClass) {
									$(element).addClass("is-valid")
											.removeClass("is-invalid");
								}
							});
				});

// va aggiustata
function clearForm() {
	$('#company').val('');
	$('#card-number').val('');
	$('#owner').val('');
	$('#expiration-date').val('');
	$('#security-code').val('');
	$('#country').val('');
	$('#city').val('');
	$('#address').val('');
	$('#zipcode').val('');
	$('#province').val('');
}

$('#result-modal').on('hide.bs.modal', function(event) {
	location.reload();
});

function prepareAddPaymentMethod() {
	clearForm();
	$('#manage-payment-method-modal-title')
			.html('Aggiungi metodo di pagamento');
	$('#manage-payment-method-button').html("Aggiungi metodo di pagamento");
	$('#manage-payment-method-button').click(function(e) {
		addPaymentMethod()
	});
	$('#manage-payment-method-modal').modal('show');
}

function prepareModPaymentMethod(id) {
	$('#modify-payment-method-' + id.toString())
			.html(
					'<span class="spinner-border spinner-border-sm"></span> Caricamento');
	$('.btn').prop('disabled', true);

	clearForm();
	manageModPaymentMethod(id);

	$('#manage-payment-method-modal-title')
			.html('Modifica metodo di pagamento');
	$('#manage-payment-method-button').html("Modifica metodo di pagamento");
	$('#manage-payment-method-button').click(function(e) {
		modPaymentMethod(id)
	});
}

function manageModPaymentMethod(id) {
	$.ajax({
		type : "POST",
		url : "user/manageUserForm?type=payment",
		dataType : "json",
		contentType : "application/json; charset=UTF-8",
		data : JSON.stringify({
			payment : id
		}),
		success : function(data) {
			if (data.result.result === false) {
				$('#result-modal-title').html('Operazione annullata');
				$('#result-modal-body').addClass('error-message');
				$('#result-modal-object').html(data.result.object);
				$('#result-modal').modal('show');
				success = false;
			} else {
				$('#company').val(data.payment.company);
				$('#card-number').val(data.payment.cardNumber);
				$('#owner').val(data.payment.owner);
				$('#expiration-date').val(data.payment.expirationDate);
				$('#security-code').val(data.payment.securityCode);
				success = true;
			}
		},
		complete : function() {
			if (success) {
				$('#manage-payment-method-modal').modal('show');
			}
			$('#modify-payment-method-' + id.toString()).html(
					'Modifica metodo di pagamento');
			$('.btn').prop('disabled', false);
		}
	});
}

function addPaymentMethod() {
	if ($('#manage-payment-method-form').valid() === false) {
		$('#manage-payment-method-form').addClass("was-validated").removeClass(
				"needs-validation");
		return;
	}

	$.ajax({
		type : "POST",
		url : "user/manage?type=paymentMethod&action=add",
		dataType : "json",
		contentType : "application/json; charset=UTF-8",
		data : JSON.stringify({
			id : 0,
			cardNumber : $('#card-number').val(),
			owner : $('#owner').val(),
			expirationDate : $('#expiration-date').val(),
			securityCode : $('#security-code').val(),
			company : $('#company').val(),
		}),
		success : function(data) {
			$('#manage-payment-method-modal').modal('hide');
			if (data.result == true) {
				$('#result-modal-title').html(
						'Operazione eseguita con successo');
				$('#result-modal-body').addClass('success-message');
			} else {
				$('#result-modal-title').html('Operazione annullata');
				$('#result-modal-body').addClass('error-message');
			}
			$('#result-modal-object').html(data.object);
			$('#result-modal').modal('show');
		}
	});
}

function modPaymentMethod(payment) {
	if ($('#manage-payment-method-form').valid() === false) {
		$('#manage-payment-method-form').addClass("was-validated").removeClass(
				"needs-validation");
		return;
	}

	$.ajax({
		type : "POST",
		url : "user/manage?type=paymentMethod&action=mod",
		dataType : "json",
		contentType : "application/json; charset=UTF-8",
		data : JSON.stringify({
			id : payment,
			cardNumber : $('#card-number').val(),
			owner : $('#owner').val(),
			expirationDate : $('#expiration-date').val(),
			securityCode : $('#security-code').val(),
			company : $('#company').val(),
		}),
		success : function(data) {
			$('#manage-payment-method-modal').modal('hide');
			if (data.result == true) {
				$('#result-modal-title').html(
						'Operazione eseguita con successo');
				$('#result-modal-body').addClass('success-message');
			} else {
				$('#result-modal-title').html('Operazione annullata');
				$('#result-modal-body').addClass('error-message');
			}
			$('#result-modal-object').html(data.object);
			$('#result-modal').modal('show');
		}
	});
}

function deletePaymentMethod(id) {
	$('#delete-payment-method-' + id.toString())
			.html(
					'<span class="spinner-border spinner-border-sm"></span> Caricamento');
	$('.btn').prop('disabled', true);

	$.ajax({
		type : "POST",
		url : "user/manage?type=paymentMethod&action=del",
		dataType : "json",
		contentType : "application/json; charset=UTF-8",
		data : JSON.stringify({
			paymentMethod : id
		}),
		success : function(data) {
			if (data.result === false) {
				$('#result-modal-title').html('Operazione annullata');
				$('#result-modal-body').addClass('error-message');
				$('#result-modal-type').html(data.type);
				$('#result-modal-object').html(data.object);
				$('#result-modal-state').html(data.state);
				$('#result-modal').modal('show');
				success = false;
			} else {
				$('#result-modal-title').html('Operazione completata');
				$('#result-modal-body').addClass('success-message');
				$('#result-modal-type').html(data.type);
				$('#result-modal-object').html(data.object);
				$('#result-modal-state').html(data.state);
				$('#result-modal').modal('show');
				success = true;
			}
		},
		complete : function() {
			$('#delete-payment-method-' + id.toString()).html(
					'Elimina metodo di pagamento');
			$('.btn').prop('disabled', false);
		}
	});
}

function prepareAddDeliveryAddress() {
	clearForm();
	$('#manage-delivery-address-modal-title').html(
			'Aggiungi indirizzo di consegna');
	$('#manage-delivery-address-button').html("Aggiungi indirizzo di consegna");
	$('#manage-delivery-address-button').click(function(e) {
		addDeliveryAddress()
	});
	$('#manage-delivery-address-modal').modal('show');
}

function prepareModDeliveryAddress(id) {
	$('#modify-delivery-address-' + id.toString())
			.html(
					'<span class="spinner-border spinner-border-sm"></span> Caricamento');
	$('.btn').prop('disabled', true);

	clearForm();
	manageModDeliveryAddress(id);

	$('#manage-delivery-address-modal-title').html(
			'Modifica indirizzo di consegna');
	$('#manage-delivery-address-button').html("Modifica indirizzo di consegna");
	$('#manage-delivery-address-button').click(function(e) {
		modDeliveryAddress(id)
	});
}

function manageModDeliveryAddress(id) {
	$.ajax({
		type : "POST",
		url : "user/manageUserForm?type=delivery",
		dataType : "json",
		contentType : "application/json; charset=UTF-8",
		data : JSON.stringify({
			delivery : id
		}),
		success : function(data) {
			if (data.result.result === false) {
				$('#result-modal-title').html('Operazione annullata');
				$('#result-modal-body').addClass('error-message');
				$('#result-modal-object').html(data.result.object);
				$('#result-modal').modal('show');
				success = false;
			} else {
				$('#country').val(data.delivery.country);
				$('#city').val(data.delivery.city);
				$('#address').val(data.delivery.address);
				$('#zipcode').val(data.delivery.zipcode);
				$('#province').val(data.delivery.province);
				success = true;
			}
		},
		complete : function() {
			if (success) {
				$('#manage-delivery-address-modal').modal('show');
			}
			$('#modify-delivery-address-' + id.toString()).html(
					'Modifica indirizzo di consegna');
			$('.btn').prop('disabled', false);
		}
	});
}

function addDeliveryAddress() {
	if ($('#manage-delivery-address-form').valid() === false) {
		$('#manage-delivery-address-form').addClass("was-validated")
				.removeClass("needs-validation");
		return;
	}

	$.ajax({
		type : "POST",
		url : "user/manage?type=deliveryAddress&action=add",
		dataType : "json",
		contentType : "application/json; charset=UTF-8",
		data : JSON.stringify({
			id : 0,
			country : $('#country').val(),
			city : $('#city').val(),
			address : $('#address').val(),
			zipcode : $('#zipcode').val(),
			province : $('#province').val(),
		}),
		success : function(data) {
			$('#manage-delivery-address-modal').modal('hide');
			if (data.result == true) {
				$('#result-modal-title').html(
						'Operazione eseguita con successo');
				$('#result-modal-body').addClass('success-message');
			} else {
				$('#result-modal-title').html('Operazione annullata');
				$('#result-modal-body').addClass('error-message');
			}
			$('#result-modal-object').html(data.object);
			$('#result-modal').modal('show');
		}
	});
}

function modDeliveryAddress(delivery) {
	if ($('#manage-delivery-address-form').valid() === false) {
		$('#manage-delivery-address-form').addClass("was-validated")
				.removeClass("needs-validation");
		return;
	}

	$.ajax({
		type : "POST",
		url : "user/manage?type=deliveryAddress&action=mod",
		dataType : "json",
		contentType : "application/json; charset=UTF-8",
		data : JSON.stringify({
			id : delivery,
			country : $('#country').val(),
			city : $('#city').val(),
			address : $('#address').val(),
			zipcode : $('#zipcode').val(),
			province : $('#province').val(),
		}),
		success : function(data) {
			$('#manage-delivery-address-modal').modal('hide');
			if (data.result == true) {
				$('#result-modal-title').html(
						'Operazione eseguita con successo');
				$('#result-modal-body').addClass('success-message');
			} else {
				$('#result-modal-title').html('Operazione annullata');
				$('#result-modal-body').addClass('error-message');
			}
			$('#result-modal-object').html(data.object);
			$('#result-modal').modal('show');
		}
	});
}

function deleteDeliveryAddress(id) {
	$('#delete-delivery-address-' + id.toString())
			.html(
					'<span class="spinner-border spinner-border-sm"></span> Caricamento');
	$('.btn').prop('disabled', true);

	$.ajax({
		type : "POST",
		url : "user/manage?type=deliveryAddress&action=del",
		dataType : "json",
		contentType : "application/json; charset=UTF-8",
		data : JSON.stringify({
			deliveryAddress : id
		}),
		success : function(data) {
			if (data.result === false) {
				$('#result-modal-title').html('Operazione annullata');
				$('#result-modal-body').addClass('error-message');
				$('#result-modal-type').html(data.type);
				$('#result-modal-object').html(data.object);
				$('#result-modal-state').html(data.state);
				$('#result-modal').modal('show');
				success = false;
			} else {
				$('#result-modal-title').html('Operazione completata');
				$('#result-modal-body').addClass('success-message');
				$('#result-modal-type').html(data.type);
				$('#result-modal-object').html(data.object);
				$('#result-modal-state').html(data.state);
				$('#result-modal').modal('show');
				success = true;
			}
		},
		complete : function() {
			$('#delete-delivery-address-' + id.toString()).html(
					'Elimina indirizzo di consegna');
			$('.btn').prop('disabled', false);
		}
	});
}