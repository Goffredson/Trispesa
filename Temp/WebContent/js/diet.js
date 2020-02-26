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

$(document).ready(function() {
	addField();
});

$(document).ready(
		function() {
			$("#dietForm").submit(
					function(event) {
						$("#waitingModal").modal("show");

						var formData = $("#dietForm").serialize().replace(/%20/g, " ").split("&");

						var arrayTriple = [];
						var contTripla = 0;
						var nome, offbrand, quantity;

						for (var i = 0; i < formData.length; i++) {

							if (contTripla == 0) {
								nome = formData[i].split("=")[1];
								contTripla++;
							} else if (contTripla == 1) {
								offbrand = formData[i].split("=")[1];
								contTripla++;
							} else if (contTripla == 2) {
								quantity = formData[i].split("=")[1];
								var tripla = [];
								tripla.push(nome);
								tripla.push(offbrand);
								tripla.push(quantity);
								arrayTriple.push(tripla);
								contTripla = 0;
							}
						}

						event.preventDefault();
						$.ajax({
							type : "POST",
							url : "manageDiet",
							datatype : "JSON",
							data : JSON.stringify(arrayTriple),
							success : function(response) {
								spesa = response;
								var totalPrice = 0;
								for (var i = 0; i < response.length; i++) {
									totalPrice += response[i].price;
									var productName = response[i].name;
									var productPrice = response[i].price;
									$.ajax({
										type : "GET",
										url : "manageCart",
										async : false,
										data : {
											productId : response[i].id,
											operation : "add"
										},
										success : function() {
											$("#addButton").prop("disabled", true);
											$(".minus-button").each(function() {
												$(this).prop("disabled", true);
											});
											$("#totalUl").prepend(
													'<li class="list-group-item d-flex justify-content-between lh-condensed">'
															+ '<div> <h6 class="my-0">' + productName
															+ '</h6> <small class="text-muted">Quantita: 1</small></div> <span class="text-muted">'
															+ productPrice + '&euro;</span></li>');

										},
										error : function() {
											$("#dietError").modal("show");
										}
									});
								}
								$("#waitingModal").modal("hide");
								$("#dietCart").show("slow");
								$("#totalPrice").html(totalPrice + "&euro;");
							},
							error : function(response) {
								setTimeout(function() {
									$("#waitingModal").modal("hide");
								}, 500);
								// $("#waitingModal").modal("hide");
								$("#dietError").modal("show");
							}
						});
					});
		});

var leafCategories = {};
var selectCount = 0;

function storeLeafCategory(id, name) {
	leafCategories[id] = name;
}

function addField() {
	selectCount++;

	formGroup = $('<div class="form-group" style="display:none;" id="field_' + selectCount + '">').prependTo($("#dietForm"));
	row = $('<div class="row">');
	formGroup.append(row);

	row.append('<div class="col-md-5 mb-3"><label>Categoria:</label><select class="form-control" name="dietSelect_' + selectCount
			+ '" id="dietSelect_' + selectCount + '">');
	for ( let key in leafCategories) {
		$(String("#dietSelect_" + selectCount)).append('<option>' + leafCategories[key] + '</option>')
	}
	row.append('</select></div>' + '<div class="col-md-2 mb-3"> <label>Grammi</label> <input class="form-control" name="dietQuantity_' + selectCount
			+ '" type="number" required> </div>' + '<div class="col-md-2 mb-3"><label>Di marca</label>'
			+ '<div style="margin-top: 10;"> <label>No<input type="radio" name="offBrand_' + selectCount
			+ '" value="true" checked style="margin-left:5; margin-right: 8;"></label> <label>Si<input type="radio" name="offBrand_' + selectCount
			+ '" value="false" style="margin-left: 5;"></label></div></div>');
	row.append('<div style="margin-left: 15px;"><button type="button" class="btn btn-danger minus-button" onclick="$(\'#field_' + selectCount
			+ '\').remove()" ><b>-</b></button></div></div>');
	formGroup.show("slow");
}