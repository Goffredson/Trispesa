package persistence.dao;

import java.util.ArrayList;

import model.Customer;
import model.Product;

public interface CustomerDao {

	// CREATE
	public void insert(Customer customer);

	// RETRIEVE
	public ArrayList<Customer> retrieveAll();
	public Customer checkIfExists(String username,String password);

	public Customer retrieveByPrimaryKey(Long id);

	// UPDATE
	public void update(Customer customer);

	// DELETE
	public void delete(Customer customer);
	
	
	public void insertProductIntoCart(Product product, long idCustomer);


}
