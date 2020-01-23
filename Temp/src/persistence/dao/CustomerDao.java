package persistence.dao;

import java.util.ArrayList;

import model.Customer;

public interface CustomerDao {

	// CREATE
	public void insert(Customer customer);
	void insertProductIntoCart(long idCustomer, long idProduct, long quantity);

	// RETRIEVE
	public ArrayList<Customer> retrieveAll();
	public Customer checkIfExists(String username,String password);
	public Customer retrieveByPrimaryKey(Long id);

	// UPDATE
	public void update(Customer customer);
	public void updateCartProductAmount(long idCustomer, long idProduct, boolean increase);

	// DELETE
	public void delete(Customer customer);
	public void deleteProductFromCart(long idCustomer, long idProduct);
	public void clearCart(long id);
	
	







}
