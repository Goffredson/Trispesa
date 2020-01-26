$(document).ready(function() {
	if (sessionStorage.getItem("remainingTime") != null) {
		durata = sessionStorage.getItem("remainingTime").split(":");
		var duration = parseInt(durata[0] * 60) + parseInt(durata[1]);
		startTimer(duration, $("#timer"));
	}
});

var intervalId = null;

function startTimer(duration, display) {

	var timer = duration, minutes, seconds;
	if (intervalId != null)
		clearInterval(intervalId);
	intervalId = setInterval(function() {
		minutes = parseInt(timer / 60, 10);
		seconds = parseInt(timer % 60, 10);

		minutes = minutes < 10 ? "0" + minutes : minutes;
		seconds = seconds < 10 ? "0" + seconds : seconds;

		display.text(minutes + ":" + seconds);
		sessionStorage.setItem("remainingTime", (minutes + ":" + seconds));

		if (--timer < 0) {
			sessionStorage.removeItem("remainingTime");
			clearInterval(intervalId);
			var productsInCart = {};
			$("#listaProdottiCarrello").children().each(function() {
				var productId = $(this).attr("id").split("_")[1];
				var amount = $(this).find("#productQuantity").html();
				productsInCart[productId] = amount;
			});
			$.ajax({
				type : "POST",
				url : "manageCart",
				datatype : "JSON",
				data : JSON.stringify(productsInCart),
				success : function() {
					$("#cartToast").toast("show");
					$("#listaProdottiCarrello").empty();
					$("#totalCartPrice").html("0");
				},
			});
		}
	}, 1000);
}

updateCart = function(id, name, supermarket, price, op) {
	// alert("Entro");

	$
			.ajax({
				type : "GET",
				url : "manageCart",
				data : {
					productId : id,
					operation : op
				},
				success : function() {
					startTimer(30*60, $("#timer"));
					var idTag = 'product_' + id;
					if (op === "add") {
						// Se il prodotto esiste già aggiorno la quantità
						if ($('#' + idTag).length) {
							var currentQuantity = parseInt($('#' + idTag).find(
									"#productQuantity").html());
							var currentPrice = parseFloat($('#' + idTag).find(
									"#productPrice").html());
							currentQuantity += 1;
							currentPrice += price;
							$('#' + idTag).find("#productQuantity").html(
									currentQuantity);
							$('#' + idTag).find("#productPrice").html(
									currentPrice);
						}
						// Altrimenti lo aggiungo
						else {
							$("#listaProdottiCarrello")
									.append(
											'<tr id="'
													+ idTag
													+ '">'
													+ '<th scope="row" id="productQuantity"> 1 </th>'
													+ '<td id="productName">'
													+ name
													+ '</td>'
													+ '<td id="productPrice">'
													+ price
													+ '</td>'
													+ '<td><a><i class="fas fa-times"></i></a></td>'
													+ '<td><button type="button"'
													+ 'onclick="updateCart('
													+ id
													+ ',\''
													+ name
													+ '\',\''
													+ supermarket
													+ '\','
													+ price
													+ ',\'remove\')"'
													+ 'class="btn btn-danger">Rimuovi</button></td>'
													+ '</tr>');
						}

						// Aggiorno il totale del carrello
						var currentTotalPrice = parseFloat($("#totalCartPrice")
								.html());
						currentTotalPrice += price;
						$("#totalCartPrice").html(currentTotalPrice);

					} else if (op === "remove") {
						var currentQuantity = parseInt($('#' + idTag).find(
								"#productQuantity").html());
						// Se ce n'è più di uno, decremento la quantity
						if (currentQuantity > 1) {
							currentQuantity -= 1;
							$('#' + idTag).find("#productQuantity").html(
									currentQuantity);

							var currentPrice = parseFloat($('#' + idTag).find(
									"#productPrice").html());
							currentPrice -= price;
							$('#' + idTag).find("#productPrice").html(
									currentPrice);
						}
						// Altrimenti cancello
						else {
							$('#' + idTag).remove();
						}
						// Aggiorno il totale del carrello
						var currentTotalPrice = parseFloat($("#totalCartPrice")
								.html());
						currentTotalPrice -= price;
						$("#totalCartPrice").html(currentTotalPrice);
					}
				},
				error : function() {
					alert("Prodotto finito");
				}
			});
}