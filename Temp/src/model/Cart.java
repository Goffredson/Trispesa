package model;

import java.util.ArrayList;

public class Cart {

	private ArrayList<Product> prodotti;
	private float costoTotale;
	
	public Cart(ArrayList<Product> prodotti, float costoTotale) {
		super();
		this.prodotti = prodotti;
		this.costoTotale = costoTotale;
	}

	public ArrayList<Product> getProdotti() {
		return prodotti;
	}

	public void setProdotti(ArrayList<Product> prodotti) {
		this.prodotti = prodotti;
	}

	public float getCostoTotale() {
		return costoTotale;
	}

	public void setCostoTotale(float costoTotale) {
		this.costoTotale = costoTotale;
	}

	

}
