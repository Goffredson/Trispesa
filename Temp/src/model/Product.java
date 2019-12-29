package model;

public class Product {

	private int ID; // lo identifica univocamente nel db (autoincrement)
	private int barcode;
	private String name;
	private double price;
	private double weight;
	private SuperMarket superMarket;
	private boolean offBrand; // sottomarca
	private String category;

	public Product(int ID, int barcode, String name, double price, double weight, SuperMarket superMarket,
			boolean offBrand, String category) {
		super();
		this.ID = ID;
		this.barcode = barcode;
		this.name = name;
		this.price = price;
		this.weight = weight;
		this.superMarket = superMarket;
		this.offBrand = offBrand;
		this.category = category;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public SuperMarket getSuperMarket() {
		return superMarket;
	}

	public void setSuperMarket(SuperMarket superMarket) {
		this.superMarket = superMarket;
	}

	public boolean isOffBrand() {
		return offBrand;
	}

	public void setOffBrand(boolean offBrand) {
		this.offBrand = offBrand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	// inserendo un prodotto si genera nel db una tupla di prodotto e una di
	// descrittore prodotto (GIORGIO)

}
