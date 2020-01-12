package model;

public class DeliveryAddress {

	private long id;
	private String country;
	private String city;
	private String address;
	private boolean deleted;

	public DeliveryAddress(long id, String country, String city, String address, boolean deleted) {
		super();
		this.id = id;
		this.country = country;
		this.city = city;
		this.address = address;
		this.deleted = deleted;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

}
