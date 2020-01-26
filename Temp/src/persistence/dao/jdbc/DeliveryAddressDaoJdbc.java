package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			String insert = "insert into delivery_address(id, country, city, address ,zipcode) values (?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, deliveryAddress.getId());
			statement.setString(2, deliveryAddress.getCountry());
			statement.setString(3, deliveryAddress.getCity());
			statement.setString(4, deliveryAddress.getAddress());
			statement.setString(5, deliveryAddress.getZipcode());
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
						resultSet.getString("city"), resultSet.getString("address"), resultSet.getString("zipcode")));
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
						resultSet.getString("city"), resultSet.getString("address"), resultSet.getString("zipcode"));
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

			String update = "update delivery_address set country=?, city=?, address=?, zipcode=? where id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, deliveryAddress.getCountry());
			statement.setString(2, deliveryAddress.getCity());
			statement.setString(3, deliveryAddress.getAddress());
			statement.setString(4, deliveryAddress.getZipcode());
			statement.setLong(5, deliveryAddress.getId());
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

}
