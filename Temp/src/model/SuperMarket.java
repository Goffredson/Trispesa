package model;

public class SuperMarket {

	private int ID;
	private String name;
	private String city;
	private String address;
	private boolean affiliate;

	public SuperMarket(int ID, String name, String city, String address, boolean affiliate) {
		super();
		this.ID = ID;
		this.name = name;
		this.city = city;
		this.address = address;
		this.affiliate = affiliate;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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

}
