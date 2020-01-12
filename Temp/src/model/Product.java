package model;

public class Product {

	private long id;
	private long barcode;
	private String name;
	private String brand;
	private double weight;
	private SuperMarket superMarket;
	private Category category;
	private boolean offBrand; // sottomarca
	private double price;
	private long quantity;
	private double discount;
	private String imagePath;
	private boolean deleted;

	public Product(long id, long barcode, String name, String brand, double weight, SuperMarket superMarket,
			Category category, boolean offBrand, double price, long quantity, double discount, String imagePath,
			boolean deleted) {
		super();
		this.id = id;
		this.barcode = barcode;
		this.name = name;
		this.brand = brand;
		this.weight = weight;
		this.superMarket = superMarket;
		this.category = category;
		this.offBrand = offBrand;
		this.price = price;
		this.quantity = quantity;
		this.discount = discount;
		this.imagePath = "images/products/" + imagePath;
		this.deleted = deleted;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public long getBarcode() {
		return barcode;
	}

	public void setBarcode(long barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price - this.discount;
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

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Product) {
			Product product = (Product) obj;
			return this.id == product.id;
		}
		return false;
	}

	@Override
	public String toString() {
		return barcode + ", " + name + ", " + superMarket + ", " + category;
	}

}
