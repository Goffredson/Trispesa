package persistence.dao;

import java.util.ArrayList;

import exceptions.DBOperationException;
import model.PaymentMethod;

public interface PaymentMethodDao {

	// CREATE
	public void insert(PaymentMethod paymentMethod);

	// RETRIEVE
	public ArrayList<PaymentMethod> retrieveAll();

	public PaymentMethod retrieveByPrimaryKey(Long id);

	// UPDATE
	public void update(PaymentMethod paymentMethod);

	// DELETE
	public void delete(PaymentMethod paymentMethod);

	public boolean checkPaymentData(Long paymentId, String expirationDate, Long securityCode);

	public void dereferCustomerPaymentMethod(long customerId, long paymentMethodId);

	public void addPaymentMethod(long customerId, PaymentMethod paymentMethod) throws DBOperationException;

	public void modPaymentMethod(PaymentMethod paymentMethod) throws DBOperationException;

}
