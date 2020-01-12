package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.util.Pair;
import model.CurrentState;
import model.Order;
import model.Product;
import persistence.DataSource;
import persistence.dao.OrderDao;

public class OrderDaoJdbc implements OrderDao {

	private DataSource dataSource;
	private final String sequenceName = "order_sequence";

	public OrderDaoJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(Order order) {
		Connection connection = null;
		try {
			// TODO svuotare carrello con trigger
			connection = this.dataSource.getConnection();
			Long id = IdBroker.getId(connection, sequenceName);
			order.setId(id);
			String insert = "insert into order(id, total_price, customer, delivery_address, payment_method, current_state) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, order.getId());
			statement.setDouble(2, order.getTotalPrice());
			statement.setLong(3, order.getCustomer().getId());
			statement.setLong(4, order.getDeliveryAddress().getId());
			statement.setLong(5, order.getPaymentMethod().getId());
			statement.setString(6, order.getCurrentState().toString());
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
	public ArrayList<Order> retrieveAll() {
		Connection connection = null;
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from order";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ArrayList<Pair<Product, Long>> products = new ArrayList<Pair<Product, Long>>();
				Order order = new Order(resultSet.getLong("id"), resultSet.getDouble("total_price"),
						new CustomerDaoJdbc(dataSource).retrieveByPrimaryKey(resultSet.getLong("customer")),
						new DeliveryAddressDaoJdbc(dataSource)
								.retrieveByPrimaryKey(resultSet.getLong("delivery_address")),
						new PaymentMethodDaoJdbc(dataSource).retrieveByPrimaryKey(resultSet.getLong("payment_method")),
						CurrentState.valueOf(resultSet.getString("current_state")), products);
				// retrieve all products
				String productsQuery = "select product, amount from order_contains_product where order=?";
				PreparedStatement productsStatement = connection.prepareStatement(productsQuery);
				productsStatement.setLong(1, order.getId());
				ResultSet productsResultSet = statement.executeQuery();
				while (productsResultSet.next()) {
					products.add(new Pair<Product, Long>(
							new ProductDaoJdbc(dataSource).retrieveByPrimaryKey(productsResultSet.getLong("id")),
							productsResultSet.getLong("amount")));
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
		return orders;
	}

	@Override
	public Order retrieveByPrimaryKey(Long id) {
		Connection connection = null;
		Order order = null;
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from order where id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				ArrayList<Pair<Product, Long>> products = new ArrayList<Pair<Product, Long>>();
				order = new Order(resultSet.getLong("id"), resultSet.getDouble("total_price"),
						new CustomerDaoJdbc(dataSource).retrieveByPrimaryKey(resultSet.getLong("customer")),
						new DeliveryAddressDaoJdbc(dataSource)
								.retrieveByPrimaryKey(resultSet.getLong("delivery_address")),
						new PaymentMethodDaoJdbc(dataSource).retrieveByPrimaryKey(resultSet.getLong("payment_method")),
						CurrentState.valueOf(resultSet.getString("current_state")), products);
				// retrieve all products
				String productsQuery = "select product, amount from order_contains_product where order=?";
				PreparedStatement productsStatement = connection.prepareStatement(productsQuery);
				productsStatement.setLong(1, order.getId());
				ResultSet productsResultSet = statement.executeQuery();
				while (productsResultSet.next()) {
					products.add(new Pair<Product, Long>(
							new ProductDaoJdbc(dataSource).retrieveByPrimaryKey(productsResultSet.getLong("id")),
							productsResultSet.getLong("amount")));
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
		return order;
	}

	@Override
	public void update(Order order) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String update = "update order set total_price=?, customer=?, delivery_address=?, payment_method=?, current_state=? where id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setDouble(1, order.getTotalPrice());
			statement.setLong(2, order.getCustomer().getId());
			statement.setLong(3, order.getDeliveryAddress().getId());
			statement.setLong(4, order.getPaymentMethod().getId());
			statement.setString(5, order.getCurrentState().toString());
			statement.setLong(6, order.getId());
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
	public void delete(Order order) {
	}

}
