package persistence.dao;

import java.util.ArrayList;

import model.Category;

public interface CategoryDao {

	// CREATE
	public void save(Category category);

	// RETRIEVE
	public ArrayList<Category> findAll();

	public Category findByPrimaryKey(Long id);

	// UPDATE
	public void update(Category category);

	// DELETE
	public void delete(Category category);

}
