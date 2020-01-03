package persistence;

import java.util.ArrayList;

import exceptions.DBOperationException;
import model.Category;
import model.Customer;
import model.Product;
import model.SuperMarket;

public class DBManager {

	private static DBManager istance = null;

	private ArrayList<Customer> customers;
	private ArrayList<Product> products;
	private ArrayList<SuperMarket> superMarkets;
	private ArrayList<Category> categories;

	public static DBManager getIstance() {
		if (istance == null)
			istance = new DBManager();
		return istance;
	}

	private DBManager() {
		// cazzate sul db
		superMarkets = new ArrayList<SuperMarket>();
		superMarkets.add(new SuperMarket("conad", "cosenza", "via della banana, 33", true, 1, 1));
		superMarkets.add(new SuperMarket("coop", "cosenza", "via della vita, 33", false, 2, 2));
		superMarkets.add(new SuperMarket("lidl", "cosenza", "via della morte, 33", true, 3, 3));
		superMarkets.add(new SuperMarket("auchan", "cosenza", "via della citre, 33", true, 4, 4));
		superMarkets.add(new SuperMarket("conad", "rende", "via ciccio bello, 33", false, 5, 5));
		SuperMarket conad = superMarkets.get(0);

		categories = new ArrayList<Category>();
		Category pasta = new Category("pasta", null);
		Category pastaIntegrale = new Category("integrale", pasta);
		Category pastaBianca = new Category("bianca", pasta);
		Category pane = new Category("pane", null);
		Category paneBianco = new Category("bianco", pane);
		Category paneIntegrale = new Category("integrale", pane);
		categories.add(pastaIntegrale);
		categories.add(pastaBianca);
		categories.add(paneBianco);
		categories.add(paneIntegrale);

		products = new ArrayList<Product>();
		products.add(new Product(1, "pasta", 1.1, 123.0, conad, true, pastaIntegrale, 9));
		products.add(new Product(1, "pasta", 1.1, 123.0, conad, true, pastaIntegrale, 9));
		products.add(new Product(1, "pasta", 1.1, 123.0, conad, true, pastaIntegrale, 9));
		products.add(new Product(2, "a", 1.1, 123, conad, true, pastaIntegrale, 9));
		products.add(new Product(3, "b", 1.1, 123, conad, true, pastaIntegrale, 9));
		products.add(new Product(4, "c", 1.1, 123, conad, true, pastaIntegrale, 9));
		products.add(new Product(5, "d", 1.1, 123, conad, true, pastaIntegrale, 9));
		products.add(new Product(6, "e", 1.1, 123, conad, true, pastaIntegrale, 9));
		products.add(new Product(7, "f", 1.1, 123, conad, true, pastaIntegrale, 9));
		products.add(new Product(8, "pasta", 1000, 123, conad, true, paneBianco, 9));
		products.add(new Product(9, "pasta", 1.1, 123, conad, true, pastaIntegrale, 9));
		products.add(new Product(10, "pasta", 1.1, 123, superMarkets.get(1), true, pastaIntegrale, 9));
	}

	public void addSupermarket(SuperMarket superMarket) throws DBOperationException {
		for (SuperMarket temp : superMarkets) {
			if (temp.equals(superMarket)) {
				throw new DBOperationException("Il supermercato � gi� presente nel database", superMarket.toString());
			}
		}
		superMarkets.add(superMarket);
	}

	public void addProduct(Product product) throws DBOperationException {
		for (Product temp : products) {
			if (temp.getBarcode() == product.getBarcode() && temp.getSuperMarket().equals(product.getSuperMarket())) {
				throw new DBOperationException("Il prodotto � gi� presente nel database", product.toString());
			}
		}
		products.add(product);
	}

	public SuperMarket getSuperMarketByID(String superMarketString) {
		for (SuperMarket superMarket : superMarkets) {
			String temp = "(" + superMarket.getName() + "," + superMarket.getCity() + "," + superMarket.getAddress()
					+ ")";
			if (superMarketString.equals(temp)) {
				return superMarket;
			}
		}
		return null;
	}

	public Category getCategoryByFamilyName(String familyName) {
		for (Category category : categories) {
			if (category.getFamilyName().equals(familyName)) {
				return category;
			}
		}
		return null;
	}

	public void removeProductByID(int barcode, SuperMarket superMarket) throws DBOperationException {
		for (Product temp : products) {
			if (temp.getBarcode() == barcode && temp.getSuperMarket().equals(superMarket)) {
				products.remove(temp);
				return;
			}
		}
		throw new DBOperationException("Il prodotto da eliminare non � stato trovato", "null");
	}

	public Product getProductByID(int barcode, SuperMarket superMarket) {
		for (Product temp : products) {
			if (temp.getBarcode() == barcode && temp.getSuperMarket().equals(superMarket)) {
				return temp;
			}
		}
		return null;
	}

	public void modifyProduct(Product product) throws DBOperationException {
		Product temp = getProductByID(product.getBarcode(), product.getSuperMarket());
		if (temp == null) {
			throw new DBOperationException("Il prodotto da modificare non � stato trovato", "null");
		}
		temp.setPrice(product.getPrice());
		temp.setQuantity(product.getQuantity());
		temp.setOffBrand(product.isOffBrand());
	}

	public void removeAffiliateSuperMarketByID(String superMarketString) throws DBOperationException {
		SuperMarket temp = getSuperMarketByID(superMarketString);
		if (temp == null) {
			throw new DBOperationException("Il supermercato da rimuovere non � stato trovato", "null");
		}
		temp.setAffiliate(false);
		
		ArrayList<Product> tempProducts = new ArrayList<Product>();
		for (Product product : products) {
			if (!(product.getSuperMarket().equals(temp))) {
				tempProducts.add(product);
			}
		}
		products = tempProducts;
	}

	public void modifySuperMarket(String oldSuperMarketString, SuperMarket superMarket) throws DBOperationException {
		SuperMarket temp = getSuperMarketByID(oldSuperMarketString);
		if (temp == null) {
			throw new DBOperationException("Il supermercato da modificare non � stato trovato", "null");
		}
		temp.setName(superMarket.getName());
		temp.setCity(superMarket.getCity());
		temp.setAddress(superMarket.getAddress());
		temp.setLatitude(superMarket.getLatitude());
		temp.setLongitude(superMarket.getLongitude());
	}

	public void addAffiliateSuperMarketByID(String superMarketString) throws DBOperationException {
		SuperMarket temp = getSuperMarketByID(superMarketString);
		if (temp == null) {
			throw new DBOperationException("Il supermercato da affiliare non � stato trovato", "null");
		}
		temp.setAffiliate(true);
	}

	public ArrayList<SuperMarket> getAffiliateSuperMarkets() {
		ArrayList<SuperMarket> temp = new ArrayList<SuperMarket>();
		for (SuperMarket superMarket : superMarkets) {
			if (superMarket.isAffiliate()) {
				temp.add(superMarket);
			}
		}
		return temp;
	}
	public ArrayList<Product> getProductsByCategory(String category){
		ArrayList<Product> productsByCategory=new ArrayList<Product>();
		for(Product i:products) {
			if(i.getCategory().getName().equals(category)) {
				productsByCategory.add(i);
			}
		}
		return productsByCategory;
	}
	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}

	public ArrayList<SuperMarket> getSuperMarkets() {
		return superMarkets;
	}

}