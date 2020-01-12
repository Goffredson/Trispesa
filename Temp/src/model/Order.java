package model;

import java.util.ArrayList;

import javafx.util.Pair;

public class Order {

	private long id;
	private String currentState;
	private double totalPrice;
	private Customer customer;
	private DeliveryAddress deliveryAddress;
	private PaymentMethod paymentMethod;
	private ArrayList<Pair<Product, Integer>> products;

	public Order(long id, String currentState, double totalPrice, Customer customer, DeliveryAddress deliveryAddress,
			PaymentMethod paymentMethod, ArrayList<Pair<Product, Integer>> products) {
		super();
		this.id = id;
		this.currentState = currentState;
		this.totalPrice = totalPrice;
		this.customer = customer;
		this.deliveryAddress = deliveryAddress;
		this.paymentMethod = paymentMethod;
		this.products = products;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public DeliveryAddress getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public ArrayList<Pair<Product, Integer>> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Pair<Product, Integer>> products) {
		this.products = products;
	}

}
