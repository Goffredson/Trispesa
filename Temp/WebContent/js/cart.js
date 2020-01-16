function Product(id, name, supermercato, quantita, prezzo) {
	this.id = id
	this.name = name;
	this.supermercato = supermercato;
	this.quantita = quantita;
	this.prezzo = prezzo;
	this.quantityInTheCart = 0;
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
								+ c.products[i].quantityInTheCart
								+ '</th>\n'
								+ '<td id="'
								+ c.products[i].name
								+ '">'
								+ c.products[i].name
								+ '</td>\n'
								+ '<td id="prezzo">'
								+ c.products[i].prezzo
								* c.products[i].quantityInTheCart
								+ '</td>\n'
								+ '<td><a><i class="fas fa-times"></i></a></td>\n'
								+ '<td><button type="button" onclick="c.removeProduct(c.products['
								+ i
								+ '].id)" class="btn btn-danger">Rimuovi</button></td>\n');
	}
	if (c.products.length > 0) {
		$("#listaProdottiCarrello").append(
				'<td></td><td></td><td class="hidden-xs text-center"><strong>Totale: '
						+ c.totalPrice + '</strong></td>\n</tr>');
	}
}
function Cart() {
	this.products = new Array();
	this.totalPrice = 0;

	this.updatePrice = function(prezzo) {
		this.totalPrice += prezzo;
	}
	this.updateQuantity = function(id) {
		for (var i = 0; i < this.products.length; i += 1) {
			if (this.products[i].id == id) {
				this.products[i].quantityInTheCart += 1;
				return;
			}
		}
	}
	this.addProduct = function(id, name, supermercato, quantita, prezzo) {
		if (this.giaContenuto(id) == false) {
			var p = new Product(id, name, supermercato, quantita, prezzo);
			this.products.push(p);
			this.updateQuantity(id);
		} else {
			this.updateQuantity(id);
		}
		this.updatePrice(prezzo);
		updateCartDOM();
	}
	this.giaContenuto = function(id) {
		for (var i = 0; i < c.products.length; i += 1) {
			if (c.products[i].id == id) {
				return true;
			}
		}
		return false;
	}
	this.removeProduct = function(id) {
		for (var i = 0; i < c.products.length; i += 1) {
			if (c.products[i].id == id) {
				if (c.products[i].quantityInTheCart == 1) {
					this.totalPrice -= c.products[i].prezzo;
					c.products.splice(i, 1);
					break;
				} else {
					c.products[i].quantityInTheCart -= 1;
					this.totalPrice -= c.products[i].prezzo;
					break;
				}
			}
		}
		updateCartDOM();
	}
}

c = new Cart();
