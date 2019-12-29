package model;

public class Product {

	private int ID; // lo identifica univocamente nel db (autoincrement)
	private int barcode;
	private String name;
	private float price;
	private float weight;
//	private SuperMarket superMarket;
	private boolean offBrand; // sottomarca
	private Category category;

	public int getBarcode() {
		return barcode;
	}

	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	// get and set superMarket

	public boolean isOffBrand() {
		return offBrand;
	}

	public void setOffBrand(boolean offBrand) {
		this.offBrand = offBrand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getID() {
		return ID;
	}

	// inserendo un prodotto si genera nel db una tupla di prodotto e una di
	// descrittore prodotto (GIORGIO)

}
