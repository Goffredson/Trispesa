package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Administrator;
import persistence.DataSource;
import persistence.dao.AdministratorDao;

public class AdministratorDaoJdbc implements AdministratorDao {

	private DataSource dataSource;
	private final String sequenceName = "administrator_sequence";

	public AdministratorDaoJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(Administrator administrator) {
		// TODO In teoria non serve a nulla, in quanto non modifichiamo niente da
		// codice!
	}

	@Override
	public ArrayList<Administrator> retrieveAll() {
		Connection connection = null;
		ArrayList<Administrator> administrators = new ArrayList<Administrator>();
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from administrator";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				administrators.add(new Administrator(resultSet.getLong("id"), resultSet.getString("username"),
						resultSet.getString("password")));
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
		return administrators;
	}

	@Override
	public Administrator retrieveByPrimaryKey(Long id) {
		Connection connection = null;
		Administrator administrator = null;
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from administrator where id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				administrator = new Administrator(resultSet.getLong("id"), resultSet.getString("username"),
						resultSet.getString("password"));
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
		return administrator;
	}

	@Override
	public void update(Administrator administrator) {
		// TODO In teoria non serve a nulla, in quanto non modifichiamo niente da
		// codice!
	}

	@Override
	public void delete(Administrator administrator) {
		// TODO In teoria non serve a nulla, in quanto non modifichiamo niente da
		// codice!
	}

	@Override
	public Administrator checkIfExists(String username, String password) {
		Connection connection = null;
		Administrator admin = null;
		try {
			connection = this.dataSource.getConnection();
			String exists = "select * from administrator where username=? and password=?";
			PreparedStatement statement = connection.prepareStatement(exists);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				admin = retrieveByPrimaryKey(resultSet.getLong("id"));
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
		return admin;
	}
}
