package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;

import exceptions.DBOperationException;
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
				HashMap<Product, Long> cart = new HashMap<Product, Long>();
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
					cart.put(new ProductDaoJdbc(dataSource).retrieveByPrimaryKey(cartResultSet.getLong("id")),
							cartResultSet.getLong("amount"));

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
				HashMap<Product, Long> cart = new HashMap<Product, Long>();
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
					cart.put(new ProductDaoJdbc(dataSource).retrieveByPrimaryKey(cartResultSet.getLong("product")),
							cartResultSet.getLong("amount"));
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
	public void insertProductIntoCart(long idProduct, long idCustomer, long quantity) {
		Connection connection = null;
		try {
			System.out.println("DB: Inserisco pID: " + idProduct + " cID: " + idCustomer);
			connection = this.dataSource.getConnection();
			Long idCart = IdBroker.getId(connection, cartSequenceName);
			String insert = "insert into cart(id, customer, product, amount) values (?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, idCart);
			statement.setLong(2, idCustomer);
			statement.setLong(3, idProduct);
			statement.setLong(4, quantity);
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
	public void deleteProductFromCart(long idProduct, long idCustomer) {
		Connection connection = null;
		try {
			System.out.println("DB: Rimuovo pID: " + idProduct + " cID: " + idCustomer);
			connection = this.dataSource.getConnection();
			String insert = "delete from cart where product = ? and customer = ?";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, idProduct);
			statement.setLong(2, idCustomer);
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
	public void updateCartProductAmount(long idProduct, long idCustomer, boolean increase) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String insert;
			if (increase) {
				System.out.println("DB: Incremento, cId:" + idCustomer + ", pID: " + idProduct);
				insert = "update cart set amount = amount+1 where customer=? and product=?;";
			} else {
				System.out.println("DB: Decremento, cId:" + idCustomer + ", pID: " + idProduct);
				insert = "update cart set amount = amount-1 where customer=? and product=?;";
			}
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, idCustomer);
			statement.setLong(2, idProduct);
			statement.executeUpdate();
			System.out.println("DB: Query eseguita");
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
	public void clearCart(long customerId) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String insert = "delete from cart where customer = ?";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, customerId);
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
	public void modUsername(long customerId, String username) throws DBOperationException {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			connection.setAutoCommit(false);

			String select = "select * from customer where username=?";
			PreparedStatement selectStatement = connection.prepareStatement(select);
			selectStatement.setString(1, username);
			ResultSet selectResultSet = selectStatement.executeQuery();
			if (selectResultSet.next()) {
				throw new DBOperationException(
						"Attenzione, lo username che stai cercando di inserire appartiene gi� ad un altro cliente", "");
			}

			String update = "update customer set username=? where id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, username);
			statement.setLong(2, customerId);
			statement.executeUpdate();

			connection.commit();
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
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public boolean checkIfUsernameExists(String username) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();

			String select = "select * from customer where username=?";
			PreparedStatement selectStatement = connection.prepareStatement(select);
			selectStatement.setString(1, username);
			ResultSet selectResultSet = selectStatement.executeQuery();
			if (selectResultSet.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException(e.getMessage());
				}
			}
			return false;
		}
	}

	@Override
	public void modPassword(long customerId, String passwordNew) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			connection.setAutoCommit(false);

			String update = "update customer set password=? where id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, passwordNew);
			statement.setLong(2, customerId);
			statement.executeUpdate();

			connection.commit();
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
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public void modName(long customerId, String name) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			connection.setAutoCommit(false);

			String update = "update customer set name=? where id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, name);
			statement.setLong(2, customerId);
			statement.executeUpdate();

			connection.commit();
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
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public void modSurname(long customerId, String surname) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			connection.setAutoCommit(false);

			String update = "update customer set surname=? where id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, surname);
			statement.setLong(2, customerId);
			statement.executeUpdate();

			connection.commit();
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
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public void modEmail(long customerId, String email) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			connection.setAutoCommit(false);

			String update = "update customer set email=? where id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, email);
			statement.setLong(2, customerId);
			statement.executeUpdate();

			connection.commit();
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
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public void modBirthDate(long customerId, LocalDate date) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			connection.setAutoCommit(false);

			String update = "update customer set birth_date=? where id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setDate(1, Date.valueOf(date));
			statement.setLong(2, customerId);
			statement.executeUpdate();

			connection.commit();
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
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public String retrieveEmail(String username) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();

			String select = "select email from customer where username=?";
			PreparedStatement selectStatement = connection.prepareStatement(select);
			selectStatement.setString(1, username);
			ResultSet selectResultSet = selectStatement.executeQuery();
			if (selectResultSet.next()) {
				return selectResultSet.getString("email");
				
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
		return null;
	}

	@Override
	public void updatePassword(String username, String newPassword) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			connection.setAutoCommit(false);

			String update = "update customer set password=? where username=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, newPassword);
			statement.setString(2, username);
			statement.executeUpdate();

			connection.commit();
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
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
	}

	@Override
	public Customer getCustomer(String email) {
		Connection connection = null;
		Customer customer = null;
		try {
			connection = this.dataSource.getConnection();
			String query = "select * from customer where email=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				ArrayList<DeliveryAddress> deliveryAddresses = new ArrayList<DeliveryAddress>();
				ArrayList<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();
				HashMap<Product, Long> cart = new HashMap<Product, Long>();
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
					cart.put(new ProductDaoJdbc(dataSource).retrieveByPrimaryKey(cartResultSet.getLong("product")),
							cartResultSet.getLong("amount"));
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

}
