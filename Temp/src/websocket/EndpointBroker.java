package websocket;

import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

import model.Customer;

public class EndpointBroker {

	private static EndpointBroker instance = null;
	private Vector<ChatEndpoint> availableAdmins = null;
	private Vector<Customer> queuedCustomers = null;
	private Vector<ChatEndpoint> transitionAdmins = null;
	private ReentrantLock rLock = null;

	public static EndpointBroker getInstance() {
		if (instance == null)
			instance = new EndpointBroker();
		return instance;
	}

	private EndpointBroker() {
		availableAdmins = new Vector<>();
		queuedCustomers = new Vector<>();
		transitionAdmins = new Vector<>();
		rLock = new ReentrantLock();
	}

	public int processCustomer(Customer customer) {
		rLock.lock();
		try {
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
				transitionAdmins.add(availableAdmins.remove(0));
			}
			return retVal;
		} finally {
			rLock.unlock();
		}
	}

	public ChatEndpoint takeAdmin() {
		rLock.lock();
		try {
			return transitionAdmins.remove(0);
		} finally {
			rLock.unlock();
		}
	}

	public void addAdmin(ChatEndpoint adminEndpoint) {
		rLock.lock();
		try {
			System.out.println("Aggiungo in availableAdmins " + adminEndpoint);
			availableAdmins.add(adminEndpoint);
		} finally {
			rLock.unlock();
		}
	}

	public void cancelCustomerProcessing(Customer customer) {
		rLock.lock();
		try {
			queuedCustomers.remove(customer);
			System.out.println("Ho rimosso " + customer.getUsername());
		} finally {
			rLock.unlock();
		}
	}

}