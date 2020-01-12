package persistence.dao;

import java.util.ArrayList;

import model.DeliveryAddress;

public interface DeliveryAddressDao {

	// CREATE
	public void save(DeliveryAddress deliveryAddress);

	// RETRIEVE
	public ArrayList<DeliveryAddress> findAll();

	public DeliveryAddress findByPrimaryKey(Long id);

	// UPDATE
	public void update(DeliveryAddress deliveryAddress);

	// DELETE
	public void delete(DeliveryAddress deliveryAddress);

}
