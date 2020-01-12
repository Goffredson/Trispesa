package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IdBroker {

	private static final String query = "SELECT nextval(?) AS id";

	public static Long getId(Connection connection, String sequenceName) throws SQLException {
		Long id = null;
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, sequenceName);
		ResultSet result = statement.executeQuery();
		result.next();
		id = result.getLong("id");
		return id;
	}

}
