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
	private String imageId;
	private boolean deleted;

	public Product(long id, long barcode, String name, String brand, double weight, SuperMarket superMarket,
			Category category, boolean offBrand, double price, long quantity, double discount, String imageId,
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
		this.imageId = "https://drive.google.com/uc?export=view&id=" + imageId;
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
		return imageId;
	}

	public void setImagePath(String imagePath) {
		this.imageId = imagePath;
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
	public String toString() {
		return barcode + ", " + name + ", " + superMarket + ", " + category;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (barcode ^ (barcode >>> 32));
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + (deleted ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(discount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((imageId == null) ? 0 : imageId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (offBrand ? 1231 : 1237);
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (quantity ^ (quantity >>> 32));
		result = prime * result + ((superMarket == null) ? 0 : superMarket.hashCode());
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Product) {
			Product p = (Product) obj;
			return this.id == p.id;
		}
		return false;
	}

	
}
