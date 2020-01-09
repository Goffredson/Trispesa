function Product(barcode, supermercato, quantita, prezzo) {
	this.barcode = barcode;
	this.supermercato = supermercato;
	this.quantita = quantita;
	this.prezzo = prezzo;
}
function addProductToDOM() {
	var inner = document.getElementById("listaProdottiCarrello");
	inner.innerHTML = ""
	for (var i = 0; i < c.products.length; i += 1) {
		//window.alert("entrato");
		inner.innerHTML = inner.innerHTML
				+ '<tr>\n <th scope="row" id="quantita">' + c.products[i].quantita
				+ '</th>\n' + '<td>' + c.products[i].barcode + '</td>\n'
				+ '<td id="prezzo">' + c.products[i].prezzo + '</td>\n'
				+ '<td><a><i class="fas fa-times"></i></a></td>\n' + '</tr>';
	}
}
function Cart() {
	this.products = new Array();
	this.totalPrice = 0;
	this.updatePrice = function() {
		this.totalPrice = 0;
		for (var i = 0; i < this.products.length; i += 1) {
			this.totalPrice += this.products[i].prezzo
					* this.products[i].quantita;
		}
	}
	this.updateQuantity = function(barcode) {
		for (var i = 0; i < this.products.length; i += 1) {
			if (this.products[i].barcode == barcode) {
				this.products[i].quantita += 1;
			}
		}
	}
	this.addProduct = function(barcode, supermercato, quantita, prezzo) {
		if (this.giaContenuto(barcode) == false) {
			var p = new Product(barcode, supermercato, quantita, prezzo);
			this.products.push(p);
			addProductToDOM();
			this.updatePrice();
			// window.alert("Nuovo prezzo " + this.totalPrice);
		} else {
			//window.alert("ciaoooooo");
			this.updateQuantity(barcode);
			addProductToDOM();
			this.updatePrice();
		}
	}
	this.giaContenuto = function(barcode) {
		for (var i = 0; i < c.products.length; i += 1) {
			if (c.products[i].barcode == barcode) {
				return true;
			}
		}
		return false;
	}
}

c = new Cart();
