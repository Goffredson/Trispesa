package persistence.dao;

import java.util.ArrayList;

import model.Administrator;

public interface AdministratorDao {

	// CREATE
	public void insert(Administrator administrator);

	// RETRIEVE
	public ArrayList<Administrator> retrieveAll();

	public Administrator retrieveByPrimaryKey(Long id);

	// UPDATE
	public void update(Administrator administrator);

	// DELETE
	public void delete(Administrator administrator);

}
