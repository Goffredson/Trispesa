package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Category;
import model.Product;
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
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				throw new RuntimeException(e.getMessage());
//			}
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
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				throw new RuntimeException(e.getMessage());
//			}
		}
		return category;
	}

	@Override
	public ArrayList<Category> retrieveMacroCategories() {
		Connection connection = null;
		ArrayList<Category> categories = new ArrayList<Category>();
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from category where parent=? or parent=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, 1);
			statement.setLong(2, 13);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getLong("parent") == 1) {
					categories.add(new Category(resultSet.getLong("id"), resultSet.getString("name"),
							new Category(1, "Alimentare", null)));
				} else {
					categories.add(new Category(resultSet.getLong("id"), resultSet.getString("name"),
							new Category(13, "Non alimentare", null)));
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
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				throw new RuntimeException(e.getMessage());
//			}
		}
		return categories;
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

	@Override
	public ArrayList<Category> retrieveLeafCategories() {
		Connection connection = null;
		ArrayList<Category> categories = new ArrayList<Category>();
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from category as C1 where not exists (select * from category as C2 where C2.parent = C1.id)";
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
		}
		return categories;

	}

	@Override
	public ArrayList<Category> retrieveLeafCategoriesForDiet() {
		Connection connection = null;
		ArrayList<Category> categories = new ArrayList<Category>();
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from category as C1 where not exists (select * from category as C2 where C2.parent = C1.id) and exists(select * from category as C3,category as C2 where C2.id=C1.parent and C2.parent=C3.id and C3.name='Alimentare') and C1.name not in(select * from nonindieta)";
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
		}
		return categories;
	}

}
