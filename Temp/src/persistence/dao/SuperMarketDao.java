package persistence.dao;

import java.util.ArrayList;

import model.SuperMarket;

public interface SuperMarketDao {

	// CREATE
	public void insert(SuperMarket SuperMarket);

	// RETRIEVE
	public ArrayList<SuperMarket> retrieveAll();

	public SuperMarket retrieveByPrimaryKey(Long id);

	// UPDATE
	public void update(SuperMarket SuperMarket);

	// DELETE
	public void delete(SuperMarket SuperMarket);

}
