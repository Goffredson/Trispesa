package persistence.dao;

import java.util.ArrayList;

import model.PaymentMethod;

public interface PaymentMethodDao {

	// CREATE
	public void save(PaymentMethod paymentMethod);

	// RETRIEVE
	public ArrayList<PaymentMethod> findAll();

	public PaymentMethod findByPrimaryKey(Long id);

	// UPDATE
	public void update(PaymentMethod paymentMethod);

	// DELETE
	public void delete(PaymentMethod paymentMethod);

}
