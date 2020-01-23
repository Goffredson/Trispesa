package model;

import java.util.HashMap;

public class Order {

	private long id;
	private double totalPrice;
	private Customer customer;
	private DeliveryAddress deliveryAddress;
	private PaymentMethod paymentMethod;
	private CurrentState currentState;
	private HashMap<Product, Long> products;

	public Order(long id, double totalPrice, Customer customer, DeliveryAddress deliveryAddress,
			PaymentMethod paymentMethod, CurrentState currentState, HashMap<Product, Long> products) {
		super();
		this.id = id;
		this.totalPrice = totalPrice;
		this.customer = customer;
		this.deliveryAddress = deliveryAddress;
		this.paymentMethod = paymentMethod;
		this.currentState = currentState;
		this.products = products;
	}
	
	public Order(double totalPrice, Customer customer, DeliveryAddress deliveryAddress,
			PaymentMethod paymentMethod, HashMap<Product, Long> products) {
		super();
		this.totalPrice = totalPrice;
		this.customer = customer;
		this.deliveryAddress = deliveryAddress;
		this.paymentMethod = paymentMethod;
		this.products = products;
		this.currentState = CurrentState.ORDERED;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public CurrentState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(CurrentState currentState) {
		this.currentState = currentState;
	}

	public HashMap<Product, Long> getProducts() {
		return products;
	}

	public void setProducts(HashMap<Product, Long> products) {
		this.products = products;
	}

}
