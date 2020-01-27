$(document).ready(
		function() {
			$("#dietForm").submit(
					function(event) {

						var formData = $("#dietForm").serialize().replace(
								/%20/g, " ").split("&");

						var arrayTriple = [];
						var contTripla = 0;
						var nome, offbrand, quantity;

						alert(formData.length);
						for (var i = 0; i < formData.length; i++) {
							alert("Entro con " + contTripla);

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
							success : function() {

							},
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

	formGroup = $('<div class="form-group">').appendTo($("#dietForm"));

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
}