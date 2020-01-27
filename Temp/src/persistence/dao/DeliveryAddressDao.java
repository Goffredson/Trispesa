package persistence.dao;

import java.util.ArrayList;

import exceptions.DBOperationException;
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

	public void dereferCustomerDeliveryAddress(long id, long deliveryAddressId);

	public void addDeliveryAddress(long id, DeliveryAddress deliveryAddress) throws DBOperationException;

	public void modDeliveryAddress(DeliveryAddress deliveryAddress) throws DBOperationException;

}
