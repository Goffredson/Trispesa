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
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			Long id = IdBroker.getId(connection, sequenceName);
			administrator.setId(id);
			String insert = "insert into administrator(id, username, password) values (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, administrator.getId());
			statement.setString(2, administrator.getUsername());
			statement.setString(3, administrator.getPassword());
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
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String update = "update administrator set username=?, password=? where id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, administrator.getUsername());
			statement.setString(2, administrator.getPassword());
			statement.setLong(3, administrator.getId());
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
	public void delete(Administrator administrator) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String delete = "delete from administrator where id=?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, administrator.getId());
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

}
