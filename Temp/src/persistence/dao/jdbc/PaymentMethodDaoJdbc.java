package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PaymentMethod;
import persistence.DataSource;
import persistence.dao.PaymentMethodDao;

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
			Long id = IdBroker.getId(connection, sequenceName);
			paymentMethod.setId(id);
			String insert = "insert into payment_method(id, card_number, owner, expiration_date, security_code, deleted, company) values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, paymentMethod.getId());
			statement.setString(2, paymentMethod.getCardNumber());
			statement.setString(3, paymentMethod.getOwner());
			statement.setDate(4, Date.valueOf(paymentMethod.getExpirationDate()));
			statement.setInt(5, paymentMethod.getSecurityCode());
			statement.setBoolean(6, paymentMethod.isDeleted());
			statement.setString(7, paymentMethod.getCompany());
			statement.executeUpdate();
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
						resultSet.getString("owner"), resultSet.getDate("expiration_date").toLocalDate(),
						resultSet.getInt("security_code"), resultSet.getBoolean("deleted"),  resultSet.getString("company")));
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
						resultSet.getString("owner"), resultSet.getDate("expiration_date").toLocalDate(),
						resultSet.getInt("security_code"), resultSet.getBoolean("deleted"), resultSet.getString("company"));
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
			String update = "update payment_method set card_number=?, owner=?, expiration_date=?, security_code=?, deleted=?, company=? where id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, paymentMethod.getCardNumber());
			statement.setString(2, paymentMethod.getOwner());
			statement.setDate(3, Date.valueOf(paymentMethod.getExpirationDate()));
			statement.setInt(4, paymentMethod.getSecurityCode());
			statement.setBoolean(5, paymentMethod.isDeleted());
			statement.setLong(6, paymentMethod.getId());
			statement.setString(7, paymentMethod.getCompany());
			statement.executeUpdate();
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
	}

	@Override
	public void delete(PaymentMethod paymentMethod) {
	}

}
