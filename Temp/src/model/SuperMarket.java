package model;

public class SuperMarket {

	private String name;
	private String city;
	private String address;
	private boolean affiliate;

	public SuperMarket(String name, String city, String address, boolean affiliate) {
		super();
		this.name = name;
		this.city = city;
		this.address = address;
		this.affiliate = affiliate;
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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SuperMarket) {
			SuperMarket superMarket = (SuperMarket) obj;
			return name.equalsIgnoreCase(superMarket.name) && city.equalsIgnoreCase(superMarket.city)
					&& address.equalsIgnoreCase(superMarket.address);
		}
		return false;
	}

}
