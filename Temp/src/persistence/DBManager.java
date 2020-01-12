package persistence;

import java.util.ArrayList;
import java.util.ListIterator;

import exceptions.DBOperationException;
import model.Category;
import model.Customer;
import model.DeliveryAddress;
import model.PaymentMethod;
import model.Product;
import model.SuperMarket;
import persistence.dao.AdministratorDao;
import persistence.dao.CategoryDao;
import persistence.dao.CustomerDao;
import persistence.dao.DeliveryAddressDao;
import persistence.dao.OrderDao;
import persistence.dao.PaymentMethodDao;
import persistence.dao.ProductDao;
import persistence.dao.SuperMarketDao;
import persistence.dao.jdbc.AdministratorDaoJdbc;
import persistence.dao.jdbc.CategoryDaoJdbc;
import persistence.dao.jdbc.CustomerDaoJdbc;
import persistence.dao.jdbc.DeliveryAddressDaoJdbc;
import persistence.dao.jdbc.OrderDaoJdbc;
import persistence.dao.jdbc.PaymentMethodDaoJdbc;
import persistence.dao.jdbc.ProductDaoJdbc;
import persistence.dao.jdbc.SuperMarketDaoJdbc;

public class DBManager {

	private static DBManager istance = null;
	private static DataSource dataSource = null;

//	private ArrayList<Customer> customers;
//	private ArrayList<Product> products;
//	private ArrayList<SuperMarket> superMarkets;
//	private ArrayList<Category> categories;
//	private ArrayList<Category> macroCategories;

	public static DBManager getInstance() {
		if (istance == null)
			istance = new DBManager();
		return istance;
	}

	private DBManager() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			dataSource = new DataSource("jdbc:postgresql://rogue.db.elephantsql.com:5432/zqnyocaq", "zqnyocaq",
					"DJ8nD9eyeT4VjZAvTnAvUDcc-ExoZTN_");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		for (Category category : getCategoryDao().retrieveAll()) {
			if (category.getParent() != null) {
				System.out.println(category.getId() + " " + category.getName() + " " + category.getParent().getName());
			} else {
				System.out.println(category.getId() + " " + category.getName() + " null");
			}
		}

//		// cazzate sul db
//		superMarkets = new ArrayList<SuperMarket>();
//		superMarkets.add(new SuperMarket("conad", "cosenza", "via della banana, 33", true, 1, 1));
//		superMarkets.add(new SuperMarket("coop", "cosenza", "via della vita, 33", false, 2, 2));
//		superMarkets.add(new SuperMarket("lidl", "cosenza", "via della morte, 33", true, 3, 3));
//		superMarkets.add(new SuperMarket("auchan", "cosenza", "via della citre, 33", true, 4, 4));
//		superMarkets.add(new SuperMarket("conad", "rende", "via ciccio bello, 33", false, 5, 5));
//		SuperMarket conad = superMarkets.get(0);
//
//		categories = new ArrayList<Category>();
//		macroCategories = new ArrayList<Category>();
//		Category pasta = new Category("pasta", null);
//		Category pastaIntegrale = new Category("integrale", pasta);
//		Category pastaBianca = new Category("bianca", pasta);
//		Category pane = new Category("pane", null);
//		Category paneBianco = new Category("bianco", pane);
//		Category paneIntegrale = new Category("integrale", pane);
//		categories.add(pastaIntegrale);
//		categories.add(pastaBianca);
//		categories.add(paneBianco);
//		categories.add(paneIntegrale);
//		macroCategories.add(pasta);
//		macroCategories.add(pane);
//
//		products = new ArrayList<Product>();
//		products.add(new Product(1, "pasta", 1.1, 123.0, conad, true, pastaIntegrale, 1, ""));
//		products.add(new Product(2, "pasta", 1.1, 123.0, conad, true, pastaIntegrale, 1, ""));
//		products.add(new Product(3, "pasta", 1.1, 123.0, conad, true, pastaIntegrale, 1, ""));
//		products.add(new Product(4, "a", 1.1, 123, conad, true, pastaIntegrale, 1, ""));
//		products.add(new Product(5, "b", 1.1, 123, conad, true, pastaIntegrale, 1, ""));
//		products.add(new Product(6, "c", 1.1, 123, conad, true, pastaIntegrale, 1, ""));
//		products.add(new Product(7, "d", 1.1, 123, conad, true, pastaIntegrale, 1, ""));
//		products.add(new Product(8, "e", 1.1, 123, conad, true, pastaIntegrale, 1, ""));
//		products.add(new Product(9, "f", 1.1, 123, conad, true, pastaIntegrale, 1, ""));
//		products.add(new Product(10, "pasta", 1000, 123, conad, true, paneBianco, 1, ""));
//		products.add(new Product(11, "pasta", 1.1, 123, conad, true, pastaIntegrale, 1, ""));
//		products.add(new Product(12, "pasta", 1.1, 123, superMarkets.get(1), true, pastaIntegrale, 1, ""));
//
//		Customer customer = new Customer("Goffredson", "bellecose", "Alfredo", "Aloi", "a.a@a.a",
//				LocalDate.of(1999, 11, 3), LocalDate.of(2019, 3, 3), null, null);
//		customer.addDeliveryAddress(new DeliveryAddress("Iatlia", "Cosenza", "via della banana"));
//		customer.addDeliveryAddress(new DeliveryAddress("Iatlia", "43253", "via della banana"));
//		customer.addDeliveryAddress(new DeliveryAddress("Iatlia", "Cos5436enza", "via della banana"));
//		customer.addDeliveryAddress(new DeliveryAddress("Iat42lia", "Cosenza", "via del4352435la banana"));
//
//		customer.addPaymentMethod(new PaymentMethod(1232345, "Boh", 11, 2021, 950));
//		customer.addPaymentMethod(new PaymentMethod(12345, "Bociaoh", 14321, 2021, 950));
//		customer.addPaymentMethod(new PaymentMethod(123345, "Boh", 11, 20221, 950));
//		customers = new ArrayList<Customer>();
//		customers.add(customer);
	}

	public AdministratorDao getAdministratorDao() {
		return new AdministratorDaoJdbc(dataSource);
	}

	public CategoryDao getCategoryDao() {
		return new CategoryDaoJdbc(dataSource);
	}

	public CustomerDao getCustomerDao() {
		return new CustomerDaoJdbc(dataSource);
	}

	public DeliveryAddressDao getDeliveryAddressDao() {
		return new DeliveryAddressDaoJdbc(dataSource);
	}

	public OrderDao getOrderDao() {
		return new OrderDaoJdbc(dataSource);
	}

	public PaymentMethodDao getPaymentMethodDao() {
		return new PaymentMethodDaoJdbc(dataSource);
	}

	public ProductDao getProductDao() {
		return new ProductDaoJdbc(dataSource);
	}

	public SuperMarketDao getSuperMarketDao() {
		return new SuperMarketDaoJdbc(dataSource);
	}

	public void addSupermarket(SuperMarket superMarket) throws DBOperationException {
		for (SuperMarket temp : getSuperMarketDao().retrieveAll()) {
			if (temp.getId() == superMarket.getId()) {
				throw new DBOperationException("Il supermercato è già presente nel database", superMarket.toString());
			}
		}
		getSuperMarketDao().insert(superMarket);
	}

	public void addProduct(Product product) throws DBOperationException {
		for (Product temp : getProductDao().retrieveAll()) {
			if (temp.getId() == product.getId()) {
				throw new DBOperationException("Il prodotto è già presente nel database", product.toString());
			}
		}
		getProductDao().insert(product);
	}

	public SuperMarket getSupermarketById(long id) throws DBOperationException {
		SuperMarket superMarket = getSuperMarketDao().retrieveByPrimaryKey(id);
		if (superMarket == null) {
			throw new DBOperationException("Il supermercato con id " + id + " non è stato trovato", id + "");
		}
		return superMarket;
	}

	// TODO da eliminare ora che abbiamo gli id nei bean
//	public SuperMarket getSuperMarketByID(String superMarketString) {
//		for (SuperMarket superMarket : superMarkets) {
//			String temp = "(" + superMarket.getName() + "," + superMarket.getCity() + "," + superMarket.getAddress()
//					+ ")";
//			if (superMarketString.equals(temp)) {
//				return superMarket;
//			}
//		}
//		return null;
//	}

	public void removeProductById(long id) throws DBOperationException {
		Product product = getProductDao().retrieveByPrimaryKey(id);
		if (product == null) {
			throw new DBOperationException("Il prodotto con id " + id + " non è stato trovato", id + "");
		}
		product.setDeleted(true);
		getProductDao().update(product);
	}

	// TODO da eliminare ora che abbiamo gli id nei bean
//	public void removeProductByID(int barcode, SuperMarket superMarket) throws DBOperationException {
//		for (Product temp : products) {
//			if (temp.getBarcode() == barcode && temp.getSuperMarket().equals(superMarket)) {
//				products.remove(temp);
//				return;
//			}
//		}
//		throw new DBOperationException("Il prodotto da eliminare non ï¿½ stato trovato", "null");
//	}

	public Product getProductById(long id) throws DBOperationException {
		Product product = getProductDao().retrieveByPrimaryKey(id);
		if (product == null) {
			throw new DBOperationException("Il prodotto con id " + id + " non è stato trovato", id + "");
		}
		return product;
	}

	// TODO da eliminare ora che abbiamo gli id nei bean
//	public Product getProductByID(long barcode, SuperMarket superMarket) {
//		for (Product temp : products) {
//			if (temp.getBarcode() == barcode && temp.getSuperMarket().equals(superMarket)) {
//				return temp;
//			}
//		}
//		return null;
//	}

	public void modifyProduct(Product product) throws DBOperationException {
		if (getProductDao().retrieveByPrimaryKey(product.getId()) == null) {
			throw new DBOperationException("Il prodotto con id " + product.getId() + " non è stato trovato",
					product.getId() + "");
		}
		getProductDao().update(product);
	}

	public void removeAffiliateSuperMarketById(long id) throws DBOperationException {
		SuperMarket superMarket = getSuperMarketDao().retrieveByPrimaryKey(id);
		if (superMarket == null) {
			throw new DBOperationException("Il supermercato con id " + id + " non è stato trovato", id + "");
		}
		superMarket.setAffiliate(false);
		getSuperMarketDao().update(superMarket);
	}

	// TODO da eliminare ora che abbiamo gli id nei bean
//	public void removeAffiliateSuperMarketByID(String superMarketString) throws DBOperationException {
//		SuperMarket temp = getSuperMarketByID(superMarketString);
//		if (temp == null) {
//			throw new DBOperationException("Il supermercato da rimuovere non ï¿½ stato trovato", "null");
//		}
//		temp.setAffiliate(false);
//
//		ArrayList<Product> tempProducts = new ArrayList<Product>();
//		for (Product product : products) {
//			if (!(product.getSuperMarket().equals(temp))) {
//				tempProducts.add(product);
//			}
//		}
//		products = tempProducts;
//	}

	public void addAffiliateSuperMarketById(long id) throws DBOperationException {
		SuperMarket superMarket = getSuperMarketDao().retrieveByPrimaryKey(id);
		if (superMarket == null) {
			throw new DBOperationException("Il supermercato con id " + id + " non è stato trovato", id + "");
		}
		superMarket.setAffiliate(true);
		getSuperMarketDao().update(superMarket);
	}

	// TODO da eliminare ora che abbiamo gli id nei bean
//	public void addAffiliateSuperMarketByID(String superMarketString) throws DBOperationException {
//		SuperMarket temp = getSuperMarketByID(superMarketString);
//		if (temp == null) {
//			throw new DBOperationException("Il supermercato da affiliare non ï¿½ stato trovato", "null");
//		}
//		temp.setAffiliate(true);
//	}

	// TODO da vedere
	public void modifySuperMarket(String oldSuperMarketString, SuperMarket superMarket) throws DBOperationException {
		SuperMarket temp = getSuperMarketByID(oldSuperMarketString);
		if (temp == null) {
			throw new DBOperationException("Il supermercato da modificare non ï¿½ stato trovato", "null");
		}
		temp.setName(superMarket.getName());
		temp.setCity(superMarket.getCity());
		temp.setAddress(superMarket.getAddress());
		temp.setLatitude(superMarket.getLatitude());
		temp.setLongitude(superMarket.getLongitude());
	}

	// TODO nuova funzione nel dao che torna solo gli affiliati
	public ArrayList<SuperMarket> getAffiliateSuperMarkets() {
		return getSuperMarketDao().retrieveAll();
	}

	// TODO va fatto il metodo nel dao
	public ArrayList<Product> getProductsByCategory(String category) {
		ArrayList<Product> productsByCategory = new ArrayList<Product>();
		for (Product i : products) {
			if (i.getCategory().getName().equals(category)) {
				productsByCategory.add(i);
			}
		}
		return productsByCategory;
	}

	public ArrayList<Customer> getCustomers() {
		return getCustomerDao().retrieveAll();
	}

	public ArrayList<Product> getProducts() {
		return getProductDao().retrieveAll();
	}

	public ArrayList<Category> getCategories() {
		return getCategoryDao().retrieveAll();
	}

	// TODO metodo nel dao
	public ArrayList<Category> getMacroCategories() {
		return macroCategories;
	}

	public ArrayList<Category> getLeafCategories() {
		ArrayList<Category> leafCategories = new ArrayList<Category>();
		for (Category category : getCategoryDao().retrieveAll()) {
			boolean isLeaf = true;
			for (Category temp : getCategoryDao().retrieveAll()) {
				if (temp.getParent() != null && temp.getParent().getId() == category.getId()) {
					isLeaf = false;
					break;
				}
			}
			if (isLeaf) {
				leafCategories.add(category);
			}
		}
		return leafCategories;
	}

	public ArrayList<SuperMarket> getSuperMarkets() {
		return getSuperMarketDao().retrieveAll();
	}

	// TODO metodo nel dao
	public ArrayList<Product> getProductsByName(String nomeProdotto) {
		ArrayList<Product> prodotti = new ArrayList<Product>();
		for (Product p : this.products)
			if (p.getName().matches(".*" + nomeProdotto + ".*"))
				prodotti.add(p);
		return prodotti;
	}

	// TODO alfredo e ciccio
	public void removePaymentMethod(Customer customer, PaymentMethod paymentMethod) throws DBOperationException {
		for (Customer temp : customers) {
			if (temp.equals(customer)) {
				temp.removePaymentMethod(paymentMethod);
				return;
			}
		}
		throw new DBOperationException("Non ï¿½ stato possibile eliminare il metodo di pagamento",
				paymentMethod.toString());
	}

	// TODO alfredo e ciccio
	public void removeDeliveryAddress(Customer customer, DeliveryAddress deliveryAddress) throws DBOperationException {
		for (Customer temp : customers) {
			if (temp.equals(customer)) {
				temp.removeDeliveryAddress(deliveryAddress);
				return;
			}
		}
		throw new DBOperationException("Non ï¿½ stato possibile eliminare l'indirizzo di consegna",
				deliveryAddress.toString());
	}

	public void escludiProdotti(String categoria, ArrayList<Product> prodotti) {
		ListIterator<Product> iter = prodotti.listIterator();
		while (iter.hasNext()) {
			// System.out.println("Il prodotto " + iter.next().getName() + "Ã¨ di categoria
			// " + iter.next().getCategory().getName());
			if (!(iter.next().getCategory().getName().equals(categoria))) {
				iter.remove();
			}
		}

	}

}