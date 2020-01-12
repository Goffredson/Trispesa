package persistence.dao;

import java.util.ArrayList;

import model.Customer;

public interface CustomerDao {

	// CREATE
	public void save(Customer customer);

	// RETRIEVE
	public ArrayList<Customer> findAll();

	public Customer findByPrimaryKey(Long id);

	// UPDATE
	public void update(Customer customer);

	// DELETE
	public void delete(Customer customer);

}
