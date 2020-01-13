package persistence.dao;

import java.util.ArrayList;

import model.Category;

public interface CategoryDao {

	// CREATE
	public void insert(Category category);

	// RETRIEVE
	public ArrayList<Category> retrieveAll();
	public Category retrieveByPrimaryKey(Long id);
	ArrayList<Category> retrieveMacroCategories();

	// UPDATE
	public void update(Category category);

	// DELETE
	public void delete(Category category);

}
