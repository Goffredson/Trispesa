package model;

public class DeliveryAddress {

	private String country;
	private String city;
	private String address;

	public DeliveryAddress(String country, String city, String address) {
		super();
		this.country = country;
		this.city = city;
		this.address = address;
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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DeliveryAddress) {
			DeliveryAddress deliveryAddress = (DeliveryAddress) obj;
			return this.country.equals(deliveryAddress.country) && this.city.equals(deliveryAddress.city)
					&& this.address.equals(deliveryAddress.address);
		}
		return false;
	}

}
