package persistence.dao;

import java.util.ArrayList;

import model.Product;

public interface ProductDao {

	// CREATE
	public void save(Product product);

	// RETRIEVE
	public ArrayList<Product> findAll();

	public Product findByPrimaryKey(Long id);

	// UPDATE
	public void update(Product product);

	// DELETE
	public void delete(Product product);

}
