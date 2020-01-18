package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ListIterator;

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
	// TODO: Probabilmente è da rimuovere
	private ArrayList<Pair<Product, Long>> cart;

	// Per costruire dal db
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

	// Per costruire dall'inizio
	public Customer(String username, String password, String name, String surname, String email, LocalDate birthDate) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.birthDate = birthDate;
		this.registrationDate = LocalDate.now();
		this.deliveryAddresses = new ArrayList<DeliveryAddress>();
		this.paymentMethods = new ArrayList<PaymentMethod>();
		this.cart = new ArrayList<Pair<Product, Long>>();
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

	public boolean addProductToCart(Product product) {
		for (Pair<Product, Long> p : this.cart) {
			if (p.getKey().getId() == product.getId()) {
				long newQuantity = p.getValue() + 1;
				// Per forza va fatta la new perchè i pair sono immutabili
				p = new Pair<Product, Long>(product, newQuantity);
				return true;
			}
		}
		this.cart.add(new Pair<Product, Long>(product, 1L));
		return false;
	}

	public boolean removeProductFromCart(Product product) {
		boolean ultimoPezzo = false;
		for (Pair<Product, Long> p : this.cart) {

			if (p.getKey().getId() == product.getId()) {
				// Decremento o rimuovo del tutto

				if (p.getValue() == 1) {
					this.cart.remove(p);
					ultimoPezzo = true;
					break;
				} else {
					long newQuantity = p.getValue() - 1;
					p = new Pair<Product, Long>(product, newQuantity);
					ultimoPezzo = false;
					break;
				}
			}
		}
		return ultimoPezzo;
	}
}
