package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.SuperMarket;
import persistence.DataSource;
import persistence.dao.SuperMarketDao;

public class SuperMarketDaoJdbc implements SuperMarketDao {

	private DataSource dataSource;
	private final String sequenceName = "supermarket_sequence";

	public SuperMarketDaoJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(SuperMarket supermarket) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			Long id = IdBroker.getId(connection, sequenceName);
			supermarket.setId(id);
			String insert = "insert into supermarket(id, name, country, city, address, latitude, longitude, affiliate) values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, supermarket.getId());
			statement.setString(2, supermarket.getName());
			statement.setString(3, supermarket.getCountry());
			statement.setString(4, supermarket.getCity());
			statement.setString(5, supermarket.getAddress());
			statement.setDouble(6, supermarket.getLatitude());
			statement.setDouble(7, supermarket.getLongitude());
			statement.setBoolean(8, supermarket.isAffiliate());
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
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public ArrayList<SuperMarket> retrieveAll() {
		Connection connection = null;
		ArrayList<SuperMarket> supermarkets = new ArrayList<SuperMarket>();
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from supermarket";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				supermarkets.add(new SuperMarket(resultSet.getLong("id"), resultSet.getString("name"),
						resultSet.getString("country"), resultSet.getString("city"), resultSet.getString("address"),
						resultSet.getDouble("latitude"), resultSet.getDouble("longitude"),
						resultSet.getBoolean("affiliate")));
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
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return supermarkets;
	}

	@Override
	public SuperMarket retrieveByPrimaryKey(Long id) {
		Connection connection = null;
		SuperMarket supermarket = null;
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from supermarket where id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				supermarket = new SuperMarket(resultSet.getLong("id"), resultSet.getString("name"),
						resultSet.getString("country"), resultSet.getString("city"), resultSet.getString("address"),
						resultSet.getDouble("latitude"), resultSet.getDouble("longitude"),
						resultSet.getBoolean("affiliate"));
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
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return supermarket;
	}

	@Override
	public void update(SuperMarket supermarket) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String update = "update supermarket set name=?, country=?, city=?, address=?, latitude=?, longitude=?, affiliate=? where id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, supermarket.getName());
			statement.setString(2, supermarket.getCountry());
			statement.setString(3, supermarket.getCity());
			statement.setString(4, supermarket.getAddress());
			statement.setDouble(5, supermarket.getLatitude());
			statement.setDouble(6, supermarket.getLongitude());
			statement.setBoolean(7, supermarket.isAffiliate());
			statement.setLong(8, supermarket.getId());
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
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public void delete(SuperMarket supermarket) {
		// TODO In teoria non serve a nulla, in quanto non modifichiamo niente da
		// codice!
	}

}
