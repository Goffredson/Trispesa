package persistence;

import java.util.ArrayList;

import model.Category;
import model.Customer;
import model.Product;

public class DBManager {

	private DBManager istance = null;

	private ArrayList<Customer> customers;
	private ArrayList<Product> products;
	private ArrayList<Category> categories;

	public DBManager getIstance() {
		if (istance == null)
			istance = new DBManager();
		return istance;
	}

	private DBManager() {
		// cazzate sul db
		customers.add(new Customer());
		customers.add(new Customer());
		customers.add(new Customer());
		customers.add(new Customer());
		customers.add(new Customer());
		customers.add(new Customer());
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}

}