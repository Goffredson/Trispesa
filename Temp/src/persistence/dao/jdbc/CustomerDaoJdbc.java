package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.util.Pair;
import model.Customer;
import model.DeliveryAddress;
import model.PaymentMethod;
import model.Product;
import persistence.DataSource;
import persistence.dao.CustomerDao;

public class CustomerDaoJdbc implements CustomerDao {

	private DataSource dataSource;
	private final String sequenceName = "customer_sequence";
	private final String cartSequenceName = "cart_sequence";

	public CustomerDaoJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(Customer customer) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			Long id = IdBroker.getId(connection, sequenceName);
			customer.setId(id);
			String insert = "insert into customer(id, username, password, name, surname, email, birth_date, registration_date) values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, customer.getId());
			statement.setString(2, customer.getUsername());
			statement.setString(3, customer.getPassword());
			statement.setString(4, customer.getName());
			statement.setString(5, customer.getSurname());
			statement.setString(6, customer.getEmail());
			statement.setDate(7, Date.valueOf(customer.getBirthDate()));
			statement.setDate(8, Date.valueOf(customer.getRegistrationDate()));
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
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				throw new RuntimeException(e.getMessage());
//			}
		}
	}

	@Override
	public ArrayList<Customer> retrieveAll() {
		Connection connection = null;
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from customer";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ArrayList<DeliveryAddress> deliveryAddresses = new ArrayList<DeliveryAddress>();
				ArrayList<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();
				ArrayList<Pair<Product, Long>> cart = new ArrayList<Pair<Product, Long>>();
				Customer customer = new Customer(resultSet.getLong("id"), resultSet.getString("username"),
						resultSet.getString("password"), resultSet.getString("name"), resultSet.getString("surname"),
						resultSet.getString("email"), resultSet.getDate("birth_date").toLocalDate(),
						resultSet.getDate("registration_date").toLocalDate(), deliveryAddresses, paymentMethods, cart);
				// retrieve all delivery addresses
				String deliveryAddressesQuery = "select delivery_address from delivery_address_refers_to_customer where customer=?";
				PreparedStatement deliveryAddressesStatement = connection.prepareStatement(deliveryAddressesQuery);
				deliveryAddressesStatement.setLong(1, customer.getId());
				ResultSet deliveryAddressesResultSet = statement.executeQuery();
				while (deliveryAddressesResultSet.next()) {
					deliveryAddresses.add(new DeliveryAddressDaoJdbc(dataSource)
							.retrieveByPrimaryKey(deliveryAddressesResultSet.getLong("delivery_address")));
				}
				// retrieve all payment methods
				String paymentMethodsQuery = "select payment_method from payment_method_refers_to_customer where customer=?";
				PreparedStatement paymentMethodsStatement = connection.prepareStatement(paymentMethodsQuery);
				paymentMethodsStatement.setLong(1, customer.getId());
				ResultSet paymentMethodsResultSet = statement.executeQuery();
				while (paymentMethodsResultSet.next()) {
					paymentMethods.add(new PaymentMethodDaoJdbc(dataSource)
							.retrieveByPrimaryKey(paymentMethodsResultSet.getLong("payment_method")));
				}
				// retrieve the cart
				String cartQuery = "select product, amount from cart where customer=?";
				PreparedStatement cartStatement = connection.prepareStatement(cartQuery);
				cartStatement.setLong(1, customer.getId());
				ResultSet cartResultSet = statement.executeQuery();
				while (cartResultSet.next()) {
					cart.add(new Pair<Product, Long>(
							new ProductDaoJdbc(dataSource).retrieveByPrimaryKey(cartResultSet.getLong("id")),
							cartResultSet.getLong("amount")));
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
		return customers;
	}

	@Override
	public Customer retrieveByPrimaryKey(Long id) {
		Connection connection = null;
		Customer customer = null;
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from customer where id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				ArrayList<DeliveryAddress> deliveryAddresses = new ArrayList<DeliveryAddress>();
				ArrayList<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();
				ArrayList<Pair<Product, Long>> cart = new ArrayList<Pair<Product, Long>>();
				customer = new Customer(resultSet.getLong("id"), resultSet.getString("username"),
						resultSet.getString("password"), resultSet.getString("name"), resultSet.getString("surname"),
						resultSet.getString("email"), resultSet.getDate("birth_date").toLocalDate(),
						resultSet.getDate("registration_date").toLocalDate(), deliveryAddresses, paymentMethods, cart);
				// retrieve all delivery addresses
				String deliveryAddressesQuery = "select delivery_address from delivery_address_refers_to_customer where customer=?";
				PreparedStatement deliveryAddressesStatement = connection.prepareStatement(deliveryAddressesQuery);
				deliveryAddressesStatement.setLong(1, customer.getId());
				ResultSet deliveryAddressesResultSet = deliveryAddressesStatement.executeQuery();
				while (deliveryAddressesResultSet.next()) {
					deliveryAddresses.add(new DeliveryAddressDaoJdbc(dataSource)
							.retrieveByPrimaryKey(deliveryAddressesResultSet.getLong("delivery_address")));
				}
				// retrieve all payment methods
				String paymentMethodsQuery = "select payment_method from payment_method_refers_to_customer where customer=?";
				PreparedStatement paymentMethodsStatement = connection.prepareStatement(paymentMethodsQuery);
				paymentMethodsStatement.setLong(1, customer.getId());
				ResultSet paymentMethodsResultSet = paymentMethodsStatement.executeQuery();
				while (paymentMethodsResultSet.next()) {
					paymentMethods.add(new PaymentMethodDaoJdbc(dataSource)
							.retrieveByPrimaryKey(paymentMethodsResultSet.getLong("payment_method")));
				}
				// retrieve the cart
				String cartQuery = "select product, amount from cart where customer=?";
				PreparedStatement cartStatement = connection.prepareStatement(cartQuery);
				cartStatement.setLong(1, customer.getId());
				ResultSet cartResultSet = cartStatement.executeQuery();
				while (cartResultSet.next()) {
					cart.add(new Pair<Product, Long>(
							new ProductDaoJdbc(dataSource).retrieveByPrimaryKey(cartResultSet.getLong("id")),
							cartResultSet.getLong("amount")));
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
		return customer;
	}

	@Override
	public void update(Customer customer) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String update = "update customer set username=?, password=?, name=?, surname=?, email=?, birth_date=?, registration_date=? where id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, customer.getUsername());
			statement.setString(2, customer.getPassword());
			statement.setString(3, customer.getName());
			statement.setString(4, customer.getSurname());
			statement.setString(5, customer.getEmail());
			statement.setDate(6, Date.valueOf(customer.getBirthDate()));
			statement.setDate(7, Date.valueOf(customer.getRegistrationDate()));
			statement.setLong(8, customer.getId());
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
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				throw new RuntimeException(e.getMessage());
//			}
		}
	}

	@Override
	public void delete(Customer customer) {
	}

	@Override
	public Customer checkIfExists(String username, String password) {
		Connection connection = null;
		Customer customer = null;
		try {
			connection = this.dataSource.getConnection();
			String exists = "select * from customer where username=? and password=?";
			PreparedStatement statement = connection.prepareStatement(exists);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				customer = retrieveByPrimaryKey(resultSet.getLong("id"));
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
		return customer;
	}

	@Override
	public void insertProductIntoCart(Product product, long idCustomer) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			Long idCart = IdBroker.getId(connection, cartSequenceName);
			//customer.setId(id);
			String insert = "insert into cart(id, customer, product, amount) values (?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, idCart);
			statement.setLong(2, idCustomer);
			statement.setLong(3, product.getId());
			statement.setLong(4, 1);
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
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				throw new RuntimeException(e.getMessage());
//			}
		}

	}

}
