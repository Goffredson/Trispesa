package model;

import java.time.LocalDate;

public class PaymentMethod {

	private long id;
	private String cardNumber;
	private String owner;
	private LocalDate expirationDate;
	private int securityCode;
	private boolean deleted;

	public PaymentMethod(long id, String cardNumber, String owner, LocalDate expirationDate, int securityCode,
			boolean deleted) {
		super();
		this.id = id;
		this.cardNumber = cardNumber;
		this.owner = owner;
		this.expirationDate = expirationDate;
		this.securityCode = securityCode;
		this.deleted = deleted;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
