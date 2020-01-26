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
					$('#manage-delivery-address-form')
							.validate(
									{
										rules : {
											barcode : {
												required : true,
												digits : true
											},
											name : "required",
											brand : "required",
											weight : {
												required : true,
												number : true
											},
											price : {
												required : true,
												number : true
											},
											quantity : {
												required : true,
												digits : true
											},
											discount : {
												required : true,
												number : true
											},
										},
										messages : {
											barcode : {
												required : "Inserisci codice a barre",
												digits : "Il codice a barre deve contenere solo numeri"
											},
											name : "Inserisci il nome",
											brand : "Inserisci la marca",
											weight : {
												required : "Inserisci il peso",
												number : "Il peso deve contenere solo numeri"
											},
											price : {
												required : "Inserisci il peso",
												number : "Il prezzo deve contenere solo numeri"
											},
											quantity : {
												required : "Inserisci il peso",
												number : "La quantità deve contenere solo numeri"
											},
											discount : {
												required : "Inserisci il peso",
												number : "Lo sconto deve contenere solo numeri"
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
				});

// va aggiustata
function clearForm() {
	$('#company').val('');
	$('#card-number').val('');
	$('#owner').val('');
	$('#expiration-date').val('');
	$('#security-code').val('');
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

function prepareModPaymentMethod() {

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

function modifyPaymentMethod(id) {

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

}

function prepareModDeliveryAddress() {

}

function addDeliveryAddress() {

}

function modifyDeliveryAddress(id) {

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