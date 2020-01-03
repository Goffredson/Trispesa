package model;

public class Product {

	private int barcode;
	private String name;
	private double price;
	private double weight;
	private SuperMarket superMarket;
	private boolean offBrand; // sottomarca
	private Category category;
	private int quantity;

	public Product(int barcode, String name, double price, double weight, SuperMarket superMarket, boolean offBrand,
			Category category, int quantity) {
		super();
		this.barcode = barcode;
		this.name = name;
		this.price = price;
		this.weight = weight;
		this.superMarket = superMarket;
		this.offBrand = offBrand;
		this.category = category;
		this.quantity = quantity;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Product) {
			Product product = (Product) obj;
			return barcode == product.barcode && superMarket.equals(product.superMarket);
		}
		return false;
	}

	@Override
	public String toString() {
		return barcode + ", " + name + ", " + superMarket + ", " + category;
	}
	
	

	// inserendo un prodotto si genera nel db una tupla di prodotto e una di
	// descrittore prodotto (GIORGIO)

}
