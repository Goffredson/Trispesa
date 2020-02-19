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

	public synchronized int processCustomer(Customer customer) {
		int retVal = 0;
		if (availableAdmins.isEmpty()) {
			System.out.println("No admin disponibili");
			if (queuedCustomers.contains(customer) == false) {
				queuedCustomers.add(customer);
			}
			int nCustomerBeforeThis = 1;
			for (Customer c : queuedCustomers) {
				if (c.equals(customer))
					break;
				nCustomerBeforeThis++;
			}
			retVal = nCustomerBeforeThis;
		} else {
			System.out.println("Admin disponibili");
			queuedCustomers.remove(customer);
		}
		return retVal;
	}

	public ChatEndpoint takeAdmin() {
		return availableAdmins.remove(0);
	}

	public void addAdmin(ChatEndpoint adminEndpoint) {
		availableAdmins.add(adminEndpoint);
	}

	public void cancelCustomerProcessing(Customer customer) {
		queuedCustomers.remove(customer);
		System.out.println("Ho rimosso " + customer.getUsername());

	}

}