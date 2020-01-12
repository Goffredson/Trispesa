package persistence.dao;

import java.util.ArrayList;

import model.Cart;

public interface CartDao {

	// CREATE
	public void save(Cart cart);

	// RETRIEVE
	public ArrayList<Cart> findAll();

	public Cart findByPrimaryKey(Long id);

	// UPDATE
	public void update(Cart cart);

	// DELETE
	public void delete(Cart cart);

}
