package persistence.dao;

import java.util.ArrayList;

import model.Category;
import model.Product;

public interface ProductDao {

	// CREATE
	public void insert(Product product);

	// RETRIEVE
	public ArrayList<Product> retrieveAll();

	public Product retrieveByPrimaryKey(Long id);
	
	public ArrayList<Product> retrieveByName(String name);
	
	public ArrayList<Product> retrieveByCategory(long category);
	
	public Long retrieveAvailableQuantity(Long productId);
	
	// UPDATE
	public void update(Product product);

	// DELETE
	public void delete(Product product);



}
