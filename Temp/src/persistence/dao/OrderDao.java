package persistence.dao;

import java.util.ArrayList;

import model.Order;

public interface OrderDao {

	// CREATE
	public void insert(Order order);

	// RETRIEVE
	public ArrayList<Order> retrieveAll();

	public Order retrieveByPrimaryKey(Long id);

	// UPDATE
	public void update(Order order);

	// DELETE
	public void delete(Order order);

	public ArrayList<Order> getOrdersOfCustomer(long id);

}
