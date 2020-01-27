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
					+ '<div class="form-check"> <input class="form-check-input" type="radio" name="offBrand" id="exampleRadios1" value="true" checked> <label class="form-check-label" for="exampleRadios1"> Non di marca </label>'
					+ '</div>'
					+ '<div class="form-check">'
					+ '<input class="form-check-input" type="radio" name="offBrand" id="exampleRadios2" value="false">'
					+ '<label class="form-check-label" for="exampleRadios2">'
					+ 'Di marca'
					+ '</label>'
					+ '</div>'
					+ '</div>'
					+ '<div class="col-xs-2"> <label for="ex1">Grammi</label> <input class="col-xs-1" name="dietQuantity_'
					+ selectCount + '" type="text">' + '</div>');
}