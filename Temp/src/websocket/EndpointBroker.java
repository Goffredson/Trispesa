package websocket;

import java.util.Vector;

import model.Customer;

public class EndpointBroker {

	private static Vector<ChatEndpoint> availableAdmins = null;
	private static Vector<Customer> queuedCustomers = null;
	private static EndpointBroker instance = null;

	public static EndpointBroker getInstance() {
		if (instance == null)
			instance = new EndpointBroker();
		return instance;
	}

	private EndpointBroker() {
		availableAdmins = new Vector<>();
		queuedCustomers = new Vector<>();
	}

	public int processCustomer(Customer customer) {
		if (availableAdmins.isEmpty()) {
			if (queuedCustomers.contains(customer) == false)
				queuedCustomers.add(customer);
		} else
			queuedCustomers.remove(customer);
		return queuedCustomers.size();

	}

	public ChatEndpoint takeAdmin() {
		return availableAdmins.remove(0);
	}
	
	public void addAdmin(ChatEndpoint adminEndpoint) {
		availableAdmins.add(adminEndpoint);
	}

}