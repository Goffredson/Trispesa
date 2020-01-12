package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Product;
import persistence.DataSource;
import persistence.dao.ProductDao;

public class ProductDaoJdbc implements ProductDao {

	private DataSource dataSource;
	private final String sequenceName = "product_sequence";

	public ProductDaoJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(Product product) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			Long id = IdBroker.getId(connection, sequenceName);
			product.setId(id);
			String insert = "insert into product(id, barcode, name, brand, weight, supermarket, category, offbrand, price, quantity, discount, image_path, deleted) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, product.getId());
			statement.setLong(2, product.getBarcode());
			statement.setString(3, product.getName());
			statement.setString(4, product.getBrand());
			statement.setDouble(5, product.getWeight());
			statement.setLong(6, product.getSuperMarket().getId());
			statement.setLong(7, product.getCategory().getId());
			statement.setBoolean(8, product.isOffBrand());
			statement.setDouble(9, product.getPrice());
			statement.setLong(10, product.getQuantity());
			statement.setDouble(11, product.getDiscount());
			statement.setString(12, product.getImagePath());
			statement.setBoolean(13, product.isDeleted());
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
	public ArrayList<Product> retrieveAll() {
		Connection connection = null;
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from product";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				products.add(new Product(resultSet.getLong("id"), resultSet.getLong("barcode"),
						resultSet.getString("name"), resultSet.getString("brand"), resultSet.getDouble("weight"),
						new SuperMarketDaoJdbc(dataSource).retrieveByPrimaryKey(resultSet.getLong("supermarket")),
						new CategoryDaoJdbc(dataSource).retrieveByPrimaryKey(resultSet.getLong("category")),
						resultSet.getBoolean("offbrand"), resultSet.getDouble("price"), resultSet.getLong("quantity"),
						resultSet.getDouble("discount"), resultSet.getString("image_path"),
						resultSet.getBoolean("deleted")));
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
		return products;
	}

	@Override
	public Product retrieveByPrimaryKey(Long id) {
		Connection connection = null;
		Product product = null;
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from product where id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				product = new Product(resultSet.getLong("id"), resultSet.getLong("barcode"),
						resultSet.getString("name"), resultSet.getString("brand"), resultSet.getDouble("weight"),
						new SuperMarketDaoJdbc(dataSource).retrieveByPrimaryKey(resultSet.getLong("supermarket")),
						new CategoryDaoJdbc(dataSource).retrieveByPrimaryKey(resultSet.getLong("category")),
						resultSet.getBoolean("offbrand"), resultSet.getDouble("price"), resultSet.getLong("quantity"),
						resultSet.getDouble("discount"), resultSet.getString("image_path"),
						resultSet.getBoolean("deleted"));
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
		return product;
	}

	@Override
	public ArrayList<Product> retrieveByName(String name) {
		// TODO Implementarlo
		return null;
	}

	@Override
	public void update(Product product) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String update = "update product set barcode=?, name=?, brand=?, weight=?, supermarket=?, category=?, offbrand=?, price=?, quantity=?, discount=?, image_path=?, deleted=? where id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setLong(1, product.getBarcode());
			statement.setString(2, product.getName());
			statement.setString(3, product.getBrand());
			statement.setDouble(4, product.getWeight());
			statement.setLong(5, product.getSuperMarket().getId());
			statement.setLong(6, product.getCategory().getId());
			statement.setBoolean(7, product.isOffBrand());
			statement.setDouble(8, product.getPrice());
			statement.setLong(9, product.getQuantity());
			statement.setDouble(10, product.getDiscount());
			statement.setString(11, product.getImagePath());
			statement.setBoolean(12, product.isDeleted());
			statement.setLong(13, product.getId());
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
	public void delete(Product product) {
		// TODO In teoria non serve a nulla, in quanto non modifichiamo niente da
		// codice!
	}

}
