package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Category;
import persistence.DataSource;
import persistence.dao.CategoryDao;

public class CategoryDaoJdbc implements CategoryDao {

	private DataSource dataSource;
	private final String sequenceName = "category_sequence";

	public CategoryDaoJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(Category category) {
		// TODO In teoria non serve a nulla, in quanto non modifichiamo niente da
		// codice!
	}

	@Override
	public ArrayList<Category> retrieveAll() {
		Connection connection = null;
		ArrayList<Category> categories = new ArrayList<Category>();
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from category";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long parentId = resultSet.getLong("parent");
				if (parentId == 0) {
					categories.add(new Category(resultSet.getLong("id"), resultSet.getString("name"), null));
				} else {
					categories.add(new Category(resultSet.getLong("id"), resultSet.getString("name"),
							new CategoryDaoJdbc(dataSource).retrieveByPrimaryKey(parentId)));
				}
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
		return categories;
	}

	@Override
	public Category retrieveByPrimaryKey(Long id) {
		Connection connection = null;
		Category category = null;
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from category where id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				long parentId = resultSet.getLong("parent");
				if (parentId == 0) {
					category = new Category(resultSet.getLong("id"), resultSet.getString("name"), null);
				} else {
					category = new Category(resultSet.getLong("id"), resultSet.getString("name"),
							new CategoryDaoJdbc(dataSource).retrieveByPrimaryKey(parentId));
				}
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
		return category;
	}

	@Override
	public void update(Category category) {
		// TODO In teoria non serve a nulla, in quanto non modifichiamo niente da
		// codice!
	}

	@Override
	public void delete(Category category) {
		// TODO In teoria non serve a nulla, in quanto non modifichiamo niente da
		// codice!
	}

}
