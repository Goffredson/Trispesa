package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	private String uri;
	private String username;
	private String password;
	private Connection connection;

	public DataSource(String uri, String username, String password) {
		this.uri = uri;
		this.username = username;
		this.password = password;
		this.connection = null;
	}

	public Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(uri, username, password);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		}
		return connection;
	}
}
