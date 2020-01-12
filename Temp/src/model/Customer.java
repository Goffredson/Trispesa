package model;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.util.Pair;

public class Customer {

	private long id;
	private String username;
	private String password;
	private String name;
	private String surname;
	private String email;
	private LocalDate birthDate;
	private LocalDate registrationDate;
	private ArrayList<DeliveryAddress> deliveryAddresses;
	private ArrayList<PaymentMethod> paymentMethods;
	private ArrayList<Pair<Product, Long>> cart;

	public Customer(long id, String username, String password, String name, String surname, String email,
			LocalDate birthDate, LocalDate registrationDate, ArrayList<DeliveryAddress> deliveryAddresses,
			ArrayList<PaymentMethod> paymentMethods, ArrayList<Pair<Product, Long>> cart) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.birthDate = birthDate;
		this.registrationDate = registrationDate;
		this.deliveryAddresses = deliveryAddresses;
		this.paymentMethods = paymentMethods;
		this.cart = cart;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public ArrayList<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(ArrayList<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public ArrayList<Pair<Product, Long>> getCart() {
		return cart;
	}

	public void setCart(ArrayList<Pair<Product, Long>> cart) {
		this.cart = cart;
	}

}
