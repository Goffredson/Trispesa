package persistence.dao;

import java.util.ArrayList;

import model.Order;

public interface OrderDao {

	// CREATE
	public void save(Order order);

	// RETRIEVE
	public ArrayList<Order> findAll();

	public Order findByPrimaryKey(Long id);

	// UPDATE
	public void update(Order order);

	// DELETE
	public void delete(Order order);

}
