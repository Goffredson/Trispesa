function Product(barcode, supermercato, quantita, prezzo) {
	this.barcode = barcode;
	this.supermercato = supermercato;
	this.quantita = quantita;
	this.prezzo = prezzo;
}
function updateCartDOM() {
	// var inner = document.getElementById("listaProdottiCarrello");
	// inner.innerHTML = "";

	$("#listaProdottiCarrello").empty();
	for (var i = 0; i < c.products.length; i += 1) {

		$("#listaProdottiCarrello")
				.append(
						// Math.round(c.products[i].prezzo*c.products[i].quantita);
						'<tr>\n <th scope="row" id="quantita">'
								+ c.products[i].quantita
								+ '</th>\n'
								+ '<td id="'
								+ c.products[i].barcode
								+ '">'
								+ c.products[i].barcode
								+ '</td>\n'
								+ '<td id="prezzo">'
								+ c.products[i].prezzo
								* c.products[i].quantita
								+ '</td>\n'
								+ '<td><a><i class="fas fa-times"></i></a></td>\n'
								+ '<td><button type="button" onclick="c.removeProduct(c.products['
								+ i
								+ '].barcode)" class="btn btn-danger">Rimuovi</button></td>\n');
	}
	$("#listaProdottiCarrello").append(
			'<td></td><td></td><td class="hidden-xs text-center"><strong>Totale: '
					+ c.totalPrice + '</strong></td>\n</tr>');

}
function Cart() {
	this.products = new Array();
	this.totalPrice = 0;
	
	this.updatePrice = function(prezzo) {
		//alert(prezzo);
		//alert(this.totalPrice);
		this.totalPrice += prezzo;
	}
	this.updateQuantity = function(barcode) {
		for (var i = 0; i < this.products.length; i += 1) {
			if (this.products[i].barcode == barcode) {
				this.products[i].quantita += 1;
				return;
			}
		}
	}
	this.addProduct = function(barcode, supermercato, quantita, prezzo) {
		if (this.giaContenuto(barcode) == false) {
			var p = new Product(barcode, supermercato, quantita, prezzo);
			this.products.push(p);
		} else {
			this.updateQuantity(barcode);
		}
		this.updatePrice(prezzo);
		updateCartDOM();
	}
	this.giaContenuto = function(barcode) {
		for (var i = 0; i < c.products.length; i += 1) {
			if (c.products[i].barcode == barcode) {
				return true;
			}
		}
		return false;
	}
	this.removeProduct = function(barcode) {
		for (var i = 0; i < c.products.length; i += 1) {
			if (c.products[i].barcode == barcode) {
				if (c.products[i].quantita == 1) {
					prezzo = c.products[i].prezzo;
					c.products.splice(i, 1);
					break;
				} else {
					prezzo = c.products[i].prezzo;
					c.products[i].quantita--;
					break;
				}
			}
		}
		alert(prezzo);
		prezzo = -prezzo;
		alert(prezzo);
		this.updatePrice(prezzo);
		updateCartDOM();
		//alert(2.0+(-1.1));
	}
}

c = new Cart();
