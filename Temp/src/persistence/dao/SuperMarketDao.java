package persistence.dao;

import java.util.ArrayList;

import model.SuperMarket;

public interface SuperMarketDao {

	// CREATE
	public void save(SuperMarket SuperMarket);

	// RETRIEVE
	public ArrayList<SuperMarket> findAll();

	public SuperMarket findByPrimaryKey(Long id);

	// UPDATE
	public void update(SuperMarket SuperMarket);

	// DELETE
	public void delete(SuperMarket SuperMarket);

}
