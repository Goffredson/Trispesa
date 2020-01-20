package persistence.dao;

import java.util.ArrayList;

import exceptions.DBOperationException;
import model.SuperMarket;

public interface SuperMarketDao {

	// CREATE
	public void insert(SuperMarket SuperMarket) throws DBOperationException;

	// RETRIEVE
	public ArrayList<SuperMarket> retrieveAll();

	public SuperMarket retrieveByPrimaryKey(Long id);

	// UPDATE
	public void update(SuperMarket SuperMarket) throws DBOperationException;

	public void setAffiliate(long id, boolean b) throws DBOperationException;

	// DELETE
	public void delete(SuperMarket SuperMarket);

}
