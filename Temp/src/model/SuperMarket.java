package model;

public class SuperMarket {

	private String name;
	private String city;
	private String address;
	private boolean affiliate;
	private double latitude;
	private double longitude;

	public SuperMarket(String name, String city, String address, boolean affiliate, double latitude, double longitude) {
		super();
		this.name = name;
		this.city = city;
		this.address = address;
		this.affiliate = affiliate;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean isAffiliate() {
		return affiliate;
	}

	public void setAffiliate(boolean affiliate) {
		this.affiliate = affiliate;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SuperMarket) {
			SuperMarket superMarket = (SuperMarket) obj;
			return name.equalsIgnoreCase(superMarket.name) && city.equalsIgnoreCase(superMarket.city)
					&& address.equalsIgnoreCase(superMarket.address);
		}
		return false;
	}

	@Override
	public String toString() {
		return name + ", " + city + ", " + address;
	}

}
