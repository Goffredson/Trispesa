var spesa = null;

function removeProduct(product) {
	$.ajax({
		type : "GET",
		url : "manageCart",
		data : {
			productId : product.id,
			operation : "remove"
		},
		success : function() {
		},
	});
}

$(document)
		.ready(
				function() {
					$("#dietForm")
							.submit(
									function(event) {

										var formData = $("#dietForm")
												.serialize().replace(/%20/g,
														" ").split("&");

										var arrayTriple = [];
										var contTripla = 0;
										var nome, offbrand, quantity;

										for (var i = 0; i < formData.length; i++) {

											if (contTripla == 0) {
												nome = formData[i].split("=")[1];
												contTripla++;
											} else if (contTripla == 1) {
												offbrand = formData[i]
														.split("=")[1];
												contTripla++;
											} else if (contTripla == 2) {
												quantity = formData[i]
														.split("=")[1];
												var tripla = [];
												tripla.push(nome);
												tripla.push(offbrand);
												tripla.push(quantity);
												arrayTriple.push(tripla);
												contTripla = 0;
											}
										}

										event.preventDefault();
										$
												.ajax({
													type : "POST",
													url : "manageDiet",
													datatype : "JSON",
													data : JSON
															.stringify(arrayTriple),
													success : function(response) {
														spesa = response;
														var totalPrice = 0;
														for (var i = 0; i < response.length; i++) {
															totalPrice += response[i].price;
															var productName = response[i].name;
															var productPrice = response[i].price;
															$
																	.ajax({
																		type : "GET",
																		url : "manageCart",
																		data : {
																			productId : response[i].id,
																			operation : "add"
																		},
																		success : function() {
																			$(
																					"#dietCart")
																					.show(
																							"slow");
																			$(
																					"#dietCart")
																					.prepend(
																							'<li class="list-group-item d-flex justify-content-between lh-condensed">'
																									+ '<div> <h6 class="my-0">'
																									+ productName
																									+ '</h6> <small class="text-muted">Quantita: 1</small></div> <span class="text-muted">'
																									+ productPrice
																									+ '&euro;</span></li>');

																		},
																		error : function() {
																			alert("Prodotto finito");
																		}
																	});
														}
														$("#totalPrice").html(
																totalPrice);
													},
													error : function(response) {
														alert("Niente dieta");
													}
												});
									});
				});

var leafCategories = {};
var selectCount = 0;

function storeLeafCategory(id, name) {
	leafCategories[id] = name;
}

function removeField() {

}

function addField() {
	selectCount++;

	formGroup = $('<div class="form-group" id="field_' + selectCount + '">')
			.appendTo($("#dietForm"));

	formGroup.append(

	'<label for="sel1">Categoria:</label>'
			+ '<select class="form-control" name="dietSelect_' + selectCount
			+ '" id="dietSelect_' + selectCount + '">');
	for ( let key in leafCategories) {
		$(String("#dietSelect_" + selectCount)).append(
				'<option>' + leafCategories[key] + '</option>')
	}
	formGroup
			.append('</select>'
					+ '<div class="checkbox">'
					+ '<div class="form-check"> <input class="form-check-input" type="radio" name="offBrand_'
					+ selectCount
					+ '" id="exampleRadios1" value="true" checked> <label class="form-check-label" for="exampleRadios1"> Non di marca </label>'
					+ '</div>'
					+ '<div class="form-check">'
					+ '<input class="form-check-input" type="radio" name="offBrand_'
					+ selectCount
					+ '" id="exampleRadios2" value="false">'
					+ '<label class="form-check-label" for="exampleRadios2">'
					+ 'Di marca'
					+ '</label>'
					+ '</div>'
					+ '</div>'
					+ '<div class="col-xs-2"> <label for="ex1">Grammi</label> <input class="col-xs-1" name="dietQuantity_'
					+ selectCount + '" type="text">' + '</div>');
	formGroup
			.append('<button type="button" class="btn btn-warning" onclick="$(\'#field_'
					+ selectCount + '\').remove()" >Rimuovi alimento</button>');
}