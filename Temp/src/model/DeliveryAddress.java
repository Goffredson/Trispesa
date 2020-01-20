package model;

public class DeliveryAddress {

	private long id;
	private String provincia;
	private String comune;
	private String address;
	private boolean deleted;
	private String recipient;
	private String cap;

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public DeliveryAddress(long id, String country, String city, String address, boolean deleted, String recipient,String cap) {
		super();
		this.id = id;
		this.provincia = country;
		this.comune = city;
		this.address = address;
		this.deleted = deleted;
		this.recipient = recipient;
		this.cap=cap;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public DeliveryAddress(long id, String country, String city, String address, boolean deleted) {
		super();
		this.id = id;
		this.provincia = country;
		this.comune = city;
		this.address = address;
		this.deleted = deleted;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
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
		return this.address+", "+this.provincia+", "+this.comune+", "+this.cap;
	}

}
