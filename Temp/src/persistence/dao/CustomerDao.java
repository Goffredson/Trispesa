package persistence.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.DBOperationException;
import model.Customer;
import model.Order;

public interface CustomerDao {

	// CREATE
	public void insert(Customer customer);

	void insertProductIntoCart(long idCustomer, long idProduct, long quantity);

	// RETRIEVE
	public ArrayList<Customer> retrieveAll();

	public Customer checkIfExists(String username, String password);

	public Customer retrieveByPrimaryKey(Long id);

	// UPDATE
	public void update(Customer customer);

	public void updateCartProductAmount(long idCustomer, long idProduct, boolean increase);

	// DELETE
	public void delete(Customer customer);

	public void deleteProductFromCart(long idCustomer, long idProduct);

	public void clearCart(long id);

	public void modUsername(long id, String username) throws DBOperationException;

	public boolean checkIfUsernameExists(String username);

	public void modPassword(long id, String passwordNew);

	public void modName(long id, String name);

	public void modSurname(long id, String surname);

	public void modEmail(long id, String email);

	public void modBirthDate(long id, LocalDate date);

	public String retrieveEmail(String username);

	public void updatePassword(String username, String newPassword);

}
