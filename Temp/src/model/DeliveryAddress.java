package model;

public class DeliveryAddress {

	private long id;
	private String country;
	private String city;
	private String address;
	private boolean deleted;
	private String zipcode;

	public DeliveryAddress(long id, String country, String city, String address, boolean deleted, String zipcode) {
		this.id = id;
		this.country = country;
		this.city = city;
		this.address = address;
		this.deleted = deleted;
		this.zipcode = zipcode;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String toString() {
		return this.address + ", " + this.country + ", " + this.city + ", " + this.zipcode;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DeliveryAddress) {
			DeliveryAddress deliveryAddress = (DeliveryAddress) obj;
			return this.id == deliveryAddress.id;
		}
		return false;
	}

}
