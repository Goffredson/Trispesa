package persistence.dao;

import java.util.ArrayList;

import model.Category;
import model.Product;

public interface CategoryDao {

	// CREATE
	public void insert(Category category);

	// RETRIEVE
	public ArrayList<Category> retrieveAll();
	public Category retrieveByPrimaryKey(Long id);
	ArrayList<Category> retrieveMacroCategories();
	public ArrayList<Category> retrieveLeafCategories();


	// UPDATE
	public void update(Category category);

	// DELETE
	public void delete(Category category);

	public ArrayList<Category> retrieveLeafCategoriesForDiet();


}
