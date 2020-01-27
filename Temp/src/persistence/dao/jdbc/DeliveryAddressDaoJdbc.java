package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exceptions.DBOperationException;
import model.DeliveryAddress;
import persistence.DataSource;
import persistence.dao.DeliveryAddressDao;

public class DeliveryAddressDaoJdbc implements DeliveryAddressDao {

	private DataSource dataSource;
	private final String sequenceName = "delivery_address_sequence";

	public DeliveryAddressDaoJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(DeliveryAddress deliveryAddress) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			connection.setAutoCommit(false);

			Long id = IdBroker.getId(connection, sequenceName);
			deliveryAddress.setId(id);
			String insert = "insert into delivery_address(id, country, city, address ,zipcode, province) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, deliveryAddress.getId());
			statement.setString(2, deliveryAddress.getCountry());
			statement.setString(3, deliveryAddress.getCity());
			statement.setString(4, deliveryAddress.getAddress());
			statement.setString(5, deliveryAddress.getZipcode());
			statement.setString(6, deliveryAddress.getProvince());
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
	public ArrayList<DeliveryAddress> retrieveAll() {
		Connection connection = null;
		ArrayList<DeliveryAddress> deliveryAddresses = new ArrayList<DeliveryAddress>();
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from delivery_address";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				deliveryAddresses.add(new DeliveryAddress(resultSet.getLong("id"), resultSet.getString(""),
						resultSet.getString("city"), resultSet.getString("address"), resultSet.getString("zipcode"),
						resultSet.getString("province")));
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
		return deliveryAddresses;
	}

	@Override
	public DeliveryAddress retrieveByPrimaryKey(Long id) {
		Connection connection = null;
		DeliveryAddress deliveryAddress = null;
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from delivery_address where id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				deliveryAddress = new DeliveryAddress(resultSet.getLong("id"), resultSet.getString("country"),
						resultSet.getString("city"), resultSet.getString("address"), resultSet.getString("zipcode"),
						resultSet.getString("province"));
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
		return deliveryAddress;
	}

	@Override
	public void update(DeliveryAddress deliveryAddress) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			connection.setAutoCommit(false);

			String update = "update delivery_address set country=?, city=?, address=?, zipcode=? province=? where id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, deliveryAddress.getCountry());
			statement.setString(2, deliveryAddress.getCity());
			statement.setString(3, deliveryAddress.getAddress());
			statement.setString(4, deliveryAddress.getZipcode());
			statement.setString(5, deliveryAddress.getProvince());
			statement.setLong(6, deliveryAddress.getId());
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
	public void delete(DeliveryAddress deliveryAddress) {
		// TODO In teoria non serve a nulla, in quanto non modifichiamo niente da
		// codice!
	}

	@Override
	public void dereferCustomerDeliveryAddress(long customerId, long deliveryAddressId) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			connection.setAutoCommit(false);

			String delete = "delete from delivery_address_refers_to_customer where delivery_address=? and customer=?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, deliveryAddressId);
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
	public void addDeliveryAddress(long customerId, DeliveryAddress deliveryAddress) throws DBOperationException {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			connection.setAutoCommit(false);

			String select = "select * from delivery_address where country=? and city=? and address=? and zipcode=? and province=?";
			PreparedStatement selectStatement = connection.prepareStatement(select);
			selectStatement.setString(1, deliveryAddress.getCountry());
			selectStatement.setString(2, deliveryAddress.getCity());
			selectStatement.setString(3, deliveryAddress.getAddress());
			selectStatement.setString(4, deliveryAddress.getZipcode());
			selectStatement.setString(5, deliveryAddress.getProvince());
			ResultSet selectResultSet = selectStatement.executeQuery();

			if (!(selectResultSet.next())) {
				Long id = IdBroker.getId(connection, sequenceName);
				deliveryAddress.setId(id);
				String insertDeliveryAddress = "insert into delivery_address(id, country, city, address, zipcode, province) values (?, ?, ?, ?, ?, ?)";
				PreparedStatement insertStatement = connection.prepareStatement(insertDeliveryAddress);
				insertStatement.setLong(1, deliveryAddress.getId());
				insertStatement.setString(2, deliveryAddress.getCountry());
				insertStatement.setString(3, deliveryAddress.getCity());
				insertStatement.setString(4, deliveryAddress.getAddress());
				insertStatement.setString(5, deliveryAddress.getZipcode());
				insertStatement.setString(6, deliveryAddress.getProvince());
				insertStatement.executeUpdate();

				id = IdBroker.getId(connection, "delivery_address_refers_to_customer_sequence");
				String insertRefer = "insert into delivery_address_refers_to_customer(id, delivery_address, customer) values (?, ?, ?)";
				PreparedStatement referStatement = connection.prepareStatement(insertRefer);
				referStatement.setLong(1, id);
				referStatement.setLong(2, deliveryAddress.getId());
				referStatement.setLong(3, customerId);
				referStatement.executeUpdate();

				connection.commit();
			} else {
				long id = selectResultSet.getLong("id");

				String selectRefer = "select * from delivery_address_refers_to_customer where delivery_address=? and customer=?";
				PreparedStatement selectReferStatement = connection.prepareStatement(selectRefer);
				selectReferStatement.setLong(1, id);
				selectReferStatement.setLong(2, customerId);
				ResultSet selectReferResultSet = selectReferStatement.executeQuery();

				if (!(selectReferResultSet.next())) {
					long tableId = IdBroker.getId(connection, "delivery_address_refers_to_customer_sequence");
					String insertRefer = "insert into delivery_address_refers_to_customer(id, delivery_address, customer) values (?, ?, ?)";
					PreparedStatement referStatement = connection.prepareStatement(insertRefer);
					referStatement.setLong(1, tableId);
					referStatement.setLong(2, id);
					referStatement.setLong(3, customerId);
					referStatement.executeUpdate();

					connection.commit();
				} else {
					throw new DBOperationException(
							"Stai cercando di inserire un indirizzo di consegna che è già presente!", "");
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

	@Override
	public void modDeliveryAddress(DeliveryAddress deliveryAddress) throws DBOperationException {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			connection.setAutoCommit(false);

			String select = "select * from delivery_address where id=?";
			PreparedStatement selectStatement = connection.prepareStatement(select);
			selectStatement.setLong(1, deliveryAddress.getId());
			ResultSet selectResultSet = selectStatement.executeQuery();

			if (!(selectResultSet.next())) {
				throw new DBOperationException("L'indirizzo di consegna non è stato trovato", "");
			} else {
				String update = "update delivery_address set country=?, city=?, address=?, zipcode=?, province=? where id=?";
				PreparedStatement updateStatement = connection.prepareStatement(update);
				updateStatement.setString(1, deliveryAddress.getCountry());
				updateStatement.setString(2, deliveryAddress.getCity());
				updateStatement.setString(3, deliveryAddress.getAddress());
				updateStatement.setString(4, deliveryAddress.getZipcode());
				updateStatement.setString(5, deliveryAddress.getProvince());
				updateStatement.setLong(6, deliveryAddress.getId());
				updateStatement.executeUpdate();
			}
		} catch (

		SQLException e) {
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
