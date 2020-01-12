package model;

public class SuperMarket {

	private long id;
	private String name;
	private String country;
	private String city;
	private String address;
	private double latitude;
	private double longitude;
	private boolean affiliate;

	public SuperMarket(long id, String name, String country, String city, String address, double latitude,
			double longitude, boolean affiliate) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.city = city;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.affiliate = affiliate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean isAffiliate() {
		return affiliate;
	}

	public void setAffiliate(boolean affiliate) {
		this.affiliate = affiliate;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SuperMarket) {
			SuperMarket superMarket = (SuperMarket) obj;
			return this.id == superMarket.id;
		}
		return false;
	}

	@Override
	public String toString() {
		return name + ", " + country + ", " + city + ", " + address;
	}

}
