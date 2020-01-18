updateCart = function(id, name, supermarket, price, op) {
	$.ajax({
		type : "GET",
		url : "manageCart",
		data : {
			productId : id,
			operation : op
		},
		success : function() {
			var idTag = 'product' + id;
			
			if (op === "add") {
				// Se il prodotto esiste già aggiorno la quantità
				if ($('#'+idTag).length) {
					var currentQuantity = parseInt($('#'+idTag).find("#productQuantity").html());
					var currentPrice = parseFloat($('#'+idTag).find("#productPrice").html());
					currentQuantity += 1;
					currentPrice += price;
					$('#'+idTag).find("#productQuantity").html(currentQuantity);
					$('#'+idTag).find("#productPrice").html(currentPrice);
				}
				// Altrimenti lo aggiungo
				else {	
				$("#listaProdottiCarrello").append(
						'<tr id="' + idTag + '">'
						+ '<th scope="row" id="productQuantity"> 1 </th>'
						+ '<td id="productName">' + name + '</td>'
						+ '<td id="productPrice">' + price + '</td>'
						+ '<td><a><i class="fas fa-times"></i></a></td>'
						+ '<td><button type="button"' 
						+ 'onclick="updateCart('+ id + ',\'' + name + '\',\'' + supermarket + '\',' + price + ',\'remove\')"' 
						+ 'class="btn btn-danger">Rimuovi</button></td>'
						+ '</tr>');
				}
				
				// Aggiorno il totale del carrello
				var currentTotalPrice = parseFloat($("#totalCartPrice").html());
				currentTotalPrice += price;
				$("#totalCartPrice").html(currentTotalPrice);

			}
			else if (op === "remove") {
				var currentQuantity = parseInt($('#'+idTag).find("#productQuantity").html());
				// Se ce n'è più di uno, decremento la quantity
				if (currentQuantity > 1) {
					currentQuantity -= 1;
					$('#'+idTag).find("#productQuantity").html(currentQuantity);
					
					var currentPrice = parseFloat($('#'+idTag).find("#productPrice").html());
					currentPrice -= price;
					$('#'+idTag).find("#productPrice").html(currentPrice);
				}
				// Altrimenti cancello
				else {
					$('#'+idTag).remove();
				}
				// Aggiorno il totale del carrello
				var currentTotalPrice = parseFloat($("#totalCartPrice").html());
				currentTotalPrice -= price;
				$("#totalCartPrice").html(currentTotalPrice);
			}
		},
		error : function() {
			alert("Prodotto finito");
		}
	});
}