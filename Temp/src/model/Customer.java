package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Customer {

	private String username;
	private String password;
	private String name;
	private String surname;
	private String email;
	private LocalDate birthDate;
	private LocalDate registrationDate;
	// arraylist di ordini (chiusi e/o in consegna)
	// carrello
	private ArrayList<DeliveryAddress> deliveryAddresses;
	private ArrayList<PaymentMethod> paymentMethods;

	public Customer(String username, String password, String name, String surname, String email, LocalDate birthDate,
			LocalDate registrationDate, ArrayList<DeliveryAddress> deliveryAddresses,
			ArrayList<PaymentMethod> paymentMethods) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.birthDate = birthDate;
		this.registrationDate = registrationDate;
		this.deliveryAddresses = deliveryAddresses;
		if (deliveryAddresses == null) {
			this.deliveryAddresses = new ArrayList<DeliveryAddress>();
		}
		this.paymentMethods = paymentMethods;
		if (paymentMethods == null) {
			this.paymentMethods = new ArrayList<PaymentMethod>();
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public ArrayList<DeliveryAddress> getDeliveryAddresses() {
		return deliveryAddresses;
	}

	public void setDeliveryAddresses(ArrayList<DeliveryAddress> deliveryAddresses) {
		this.deliveryAddresses = deliveryAddresses;
	}

	public void addDeliveryAddress(DeliveryAddress deliveryAddress) {
		this.deliveryAddresses.add(deliveryAddress);
	}

	public void removeDeliveryAddress(DeliveryAddress deliveryAddress) {
		this.deliveryAddresses.remove(deliveryAddress);
	}

	public ArrayList<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(ArrayList<PaymentMethod> payementMethods) {
		this.paymentMethods = payementMethods;
	}

	public void addPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethods.add(paymentMethod);
	}

	public void removePaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethods.remove(paymentMethod);
	}

	public PaymentMethod getPaymentMethodByCardNumber(int cardNumber) {
		for (PaymentMethod paymentMethod : paymentMethods) {
			if (paymentMethod.getCardNumber() == cardNumber) {
				return paymentMethod;
			}
		}
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Customer) {
			Customer customer = (Customer) obj;
			return this.username.equals(customer.username);
		}
		return false;
	}

	public DeliveryAddress getDeliveryAddressByID(String deliveryAddressString) {
		for (DeliveryAddress deliveryAddress : deliveryAddresses) {
			String temp = "(" + deliveryAddress.getCountry() + "," + deliveryAddress.getCity() + ","
					+ deliveryAddress.getAddress() + ")";
			if (temp.equals(deliveryAddressString)) {
				return deliveryAddress;
			}
		}
		return null;
	}

}
