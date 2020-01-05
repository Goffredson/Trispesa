package model;

import java.util.Date;

public class PaymentMethod {

	private int cardNumber;
	private String owner;
	private int expirationMonth;
	private int expirationYear;
	private int securityCode;

	public PaymentMethod(int cardNumber, String owner, int expirationMonth, int expirationYear, int securityCode) {
		super();
		this.cardNumber = cardNumber;
		this.owner = owner;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.securityCode = securityCode;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public int getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}

	public int getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PaymentMethod) {
			PaymentMethod paymentMethod = (PaymentMethod) obj;
			return this.cardNumber == paymentMethod.cardNumber && this.owner.equals(paymentMethod.owner)
					&& this.expirationMonth == paymentMethod.expirationMonth
					&& this.expirationYear == paymentMethod.expirationYear
					&& this.securityCode == paymentMethod.securityCode;
		}
		return false;
	}

}
