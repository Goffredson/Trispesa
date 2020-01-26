var leafCategories = {};
var selectCount = 0;

function storeLeafCategory(id, name) {
	leafCategories[id] = name;
}

function addField() {
	selectCount++;
	$("#dietForm")
			.append(
					'<div class="form-group">'
							+ '<label for="sel1">Categoria:</label> <select class="form-control" id="dietSelect_'
							+ selectCount + '">');
	for ( let key in leafCategories) {
		$(String("#dietSelect_" + selectCount)).append(
				'<option>' + leafCategories[key] + '</option>')
	}
	$("#dietForm").append(
			'</select>' + '<div class="checkbox">'
					+ '<label><input type="checkbox" value="">Di marca</label>'
					+ '</div>' + '</div>');
}