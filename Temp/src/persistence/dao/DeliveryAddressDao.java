package persistence.dao;

import java.util.ArrayList;

import model.DeliveryAddress;

public interface DeliveryAddressDao {

	// CREATE
	public void insert(DeliveryAddress deliveryAddress);

	// RETRIEVE
	public ArrayList<DeliveryAddress> retrieveAll();

	public DeliveryAddress retrieveByPrimaryKey(Long id);

	// UPDATE
	public void update(DeliveryAddress deliveryAddress);

	// DELETE
	public void delete(DeliveryAddress deliveryAddress);

}
