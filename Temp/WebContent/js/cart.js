function Product(barcode, supermercato, quantita, prezzo) {
	this.barcode = barcode;
	this.supermercato = supermercato;
	this.quantita = quantita;
	this.prezzo = prezzo;
}

function Cart(){
	this.products = new Array();
	this.totalPrice = 0;
	this.updatePrice = function() {
		this.totalPrice = 0;
		for (var i = 0; i < this.products.length; i += 1) {
			this.totalPrice += this.products[i].prezzo;
		}
	}
	this.addProduct = function (barcode, supermercato, quantita, prezzo) {
		var p = new Product(barcode, supermercato, quantita, prezzo);
		this.products.push(p);
		this.updatePrice();
		//window.alert("Nuovo prezzo " + this.totalPrice);
	}
}

c = new Cart();

function addProductsToDOM() {
	var inner = document.getElementById("listaProdottiCarrello");
	for (var i = 0; i < c.products.length; i += 1) {


		
		inner.innerHTML = inner.innerHTML + '<tr>\n <th scope="row">'+i+'</th>\n'+
		'<td>' + c.products[i].barcode + '</td>\n' + 
		'<td>' + c.products[i].prezzo + '</td>\n' +
		'<td><a><i class="fas fa-times"></i></a></td>\n' + 
		'</tr>';
			
	}
}