package persistence.dao;

import java.util.ArrayList;

import model.Cart;

public interface CartDao {

	// CREATE
	public void insert(Cart cart);

	// RETRIEVE
	public ArrayList<Cart> retrieveAll();

	public Cart retrieveByPrimaryKey(Long id);

	// UPDATE
	public void update(Cart cart);

	// DELETE
	public void delete(Cart cart);

}
