package persistence.dao;

import java.util.ArrayList;

import model.Administrator;

public interface AdministratorDao {

	// CREATE
	public void save(Administrator administrator);

	// RETRIEVE
	public ArrayList<Administrator> findAll();

	public Administrator findByPrimaryKey(Long id);

	// UPDATE
	public void update(Administrator administrator);

	// DELETE
	public void delete(Administrator administrator);

}
