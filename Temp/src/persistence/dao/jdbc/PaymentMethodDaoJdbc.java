package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exceptions.DBOperationException;
import model.PaymentMethod;
import persistence.DataSource;
import persistence.dao.PaymentMethodDao;
import sun.security.action.GetLongAction;

public class PaymentMethodDaoJdbc implements PaymentMethodDao {

	private DataSource dataSource;
	private final String sequenceName = "payment_method_sequence";

	public PaymentMethodDaoJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(PaymentMethod paymentMethod) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			connection.setAutoCommit(false);

			Long id = IdBroker.getId(connection, sequenceName);
			paymentMethod.setId(id);
			String insert = "insert into payment_method(id, card_number, owner, expiration_date, security_code, company) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, paymentMethod.getId());
			statement.setString(2, paymentMethod.getCardNumber());
			statement.setString(3, paymentMethod.getOwner());
			statement.setString(4, paymentMethod.getExpirationDate());
			statement.setInt(5, paymentMethod.getSecurityCode());
			statement.setString(6, paymentMethod.getCompany());
			statement.executeUpdate();

			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException(e.getMessage());
				}
			}
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public ArrayList<PaymentMethod> retrieveAll() {
		Connection connection = null;
		ArrayList<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from payment_method";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				paymentMethods.add(new PaymentMethod(resultSet.getLong("id"), resultSet.getString("card_number"),
						resultSet.getString("owner"), resultSet.getString("expiration_date"),
						resultSet.getInt("security_code"), resultSet.getString("company")));
			}
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException(e.getMessage());
				}
			}
		} finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				throw new RuntimeException(e.getMessage());
//			}
		}
		return paymentMethods;
	}

	@Override
	public PaymentMethod retrieveByPrimaryKey(Long id) {
		Connection connection = null;
		PaymentMethod paymentMethod = null;
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from payment_method where id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				paymentMethod = new PaymentMethod(resultSet.getLong("id"), resultSet.getString("card_number"),
						resultSet.getString("owner"), resultSet.getString("expiration_date"),
						resultSet.getInt("security_code"), resultSet.getString("company"));
			}
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException(e.getMessage());
				}
			}
		} finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				throw new RuntimeException(e.getMessage());
//			}
		}
		return paymentMethod;
	}

	@Override
	public void update(PaymentMethod paymentMethod) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			connection.setAutoCommit(false);

			String update = "update payment_method set card_number=?, owner=?, expiration_date=?, security_code=?, company=? where id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, paymentMethod.getCardNumber());
			statement.setString(2, paymentMethod.getOwner());
			statement.setString(3, paymentMethod.getExpirationDate());
			statement.setInt(4, paymentMethod.getSecurityCode());
			statement.setString(5, paymentMethod.getCompany());
			statement.setLong(6, paymentMethod.getId());
			statement.executeUpdate();

			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException(e.getMessage());
				}
			}
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public void delete(PaymentMethod paymentMethod) {
	}

	@Override
	public boolean checkPaymentData(Long paymentId, String expirationDate, Long securityCode) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from payment_method where id=? and expiration_date=? and security_code=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, paymentId);
			statement.setString(2, expirationDate);
			statement.setLong(3, securityCode);
			ResultSet resultSet = statement.executeQuery();
			return resultSet.next();

		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException(e.getMessage());
				}
			}
		} finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				throw new RuntimeException(e.getMessage());
//			}
		}
		return false;
	}

	@Override
	public void dereferCustomerPaymentMethod(long customerId, long paymentMethodId) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			connection.setAutoCommit(false);

			String delete = "delete from payment_method_refers_to_customer where payment_method=? and customer=?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, paymentMethodId);
			statement.setLong(2, customerId);
			statement.executeUpdate();

			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException(e.getMessage());
				}
			}
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public void addPaymentMethod(long customerId, PaymentMethod paymentMethod) throws DBOperationException {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			connection.setAutoCommit(false);

			String select = "select * from payment_method where card_number=? and owner=? and expiration_date=? and security_code=? and company=?";
			PreparedStatement selectStatement = connection.prepareStatement(select);
			selectStatement.setString(1, paymentMethod.getCardNumber());
			selectStatement.setString(2, paymentMethod.getOwner());
			selectStatement.setString(3, paymentMethod.getExpirationDate());
			selectStatement.setInt(4, paymentMethod.getSecurityCode());
			selectStatement.setString(5, paymentMethod.getCompany());
			ResultSet selectResultSet = selectStatement.executeQuery();

			if (!(selectResultSet.next())) {
				Long id = IdBroker.getId(connection, sequenceName);
				System.out.println(id);
				paymentMethod.setId(id);
				String insertPaymentMethod = "insert into payment_method(id, card_number, owner, expiration_date, security_code, company) values (?, ?, ?, ?, ?, ?)";
				PreparedStatement insertStatement = connection.prepareStatement(insertPaymentMethod);
				insertStatement.setLong(1, paymentMethod.getId());
				insertStatement.setString(2, paymentMethod.getCardNumber());
				insertStatement.setString(3, paymentMethod.getOwner());
				insertStatement.setString(4, paymentMethod.getExpirationDate());
				insertStatement.setInt(5, paymentMethod.getSecurityCode());
				insertStatement.setString(6, paymentMethod.getCompany());
				insertStatement.executeUpdate();

				id = IdBroker.getId(connection, "payment_method_refers_to_customer_sequence");
				String insertRefer = "insert into payment_method_refers_to_customer(id, payment_method, customer) values (?, ?, ?)";
				PreparedStatement referStatement = connection.prepareStatement(insertRefer);
				referStatement.setLong(1, id);
				referStatement.setLong(2, paymentMethod.getId());
				referStatement.setLong(3, customerId);
				referStatement.executeUpdate();

				connection.commit();
			} else {
				long id = selectResultSet.getLong("id");

				String selectRefer = "select * from payment_method_refers_to_customer where payment_method=? and customer=?";
				PreparedStatement selectReferStatement = connection.prepareStatement(selectRefer);
				selectReferStatement.setLong(1, id);
				selectReferStatement.setLong(2, customerId);
				ResultSet selectReferResultSet = selectReferStatement.executeQuery();

				if (!(selectReferResultSet.next())) {
					long tableId = IdBroker.getId(connection, "payment_method_refers_to_customer_sequence");
					String insertRefer = "insert into payment_method_refers_to_customer(id, pyment_method, customer) values (?, ?, ?)";
					PreparedStatement referStatement = connection.prepareStatement(insertRefer);
					referStatement.setLong(1, tableId);
					referStatement.setLong(2, id);
					referStatement.setLong(3, customerId);
					referStatement.executeUpdate();

					connection.commit();
				} else {
					throw new DBOperationException(
							"Stai cercando di inserire un metodo di pagamento che è già presente!", "");
				}
			}
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
					System.out.println("rollback");
				} catch (SQLException excep) {
					throw new RuntimeException(e.getMessage());
				}
			}
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

}
