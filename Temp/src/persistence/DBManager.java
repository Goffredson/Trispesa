package persistence;

import java.util.ArrayList;

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
		superMarkets.add(new SuperMarket("conad", "cosenza", "via della banana, 33", true));
		superMarkets.add(new SuperMarket("coop", "cosenza", "via della vita, 33", false));
		superMarkets.add(new SuperMarket("lidl", "cosenza", "via della morte, 33", true));
		superMarkets.add(new SuperMarket("auchan", "cosenza", "via della citre, 33", true));
		superMarkets.add(new SuperMarket("conad", "rende", "via ciccio bello, 33", false));
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
		products.add(new Product(1, "ciqo", 1.1, 123.0, conad, true, pastaIntegrale, 9));
		products.add(new Product(2, "wesdrtf", 1.1, 123, conad, true, pastaIntegrale, 9));
		products.add(new Product(3, "igwhw", 1.1, 123, conad, true, pastaIntegrale, 9));
		products.add(new Product(4, "ciqo", 1.1, 123, conad, true, pastaIntegrale, 9));
		products.add(new Product(5, "iewhvoiw", 1.1, 123, conad, true, pastaIntegrale, 9));
		products.add(new Product(6, "ciqo", 1.1, 123, conad, true, pastaIntegrale, 9));
		products.add(new Product(7, "ciqo", 1.1, 123, conad, true, pastaIntegrale, 9));
		products.add(new Product(8, "ciao che bello", 1000, 123, conad, true, pastaIntegrale, 9));
		products.add(new Product(9, "ciqo", 1.1, 123, conad, true, pastaIntegrale, 9));
		products.add(new Product(10, "funziona", 1.1, 123, conad, true, pastaIntegrale, 9));
	}

	public boolean addSupermarket(SuperMarket superMarket) {
		// se lo posso aggiungere ecc
		for (SuperMarket temp : superMarkets) {
			if (temp.equals(superMarket)) {
				return false;
			}
		}
		superMarkets.add(superMarket);
		return true;
	}

	public boolean addProduct(Product product) {
		// se lo posso aggiungere ecc
		for (Product temp : products) {
			if (temp.getBarcode() == product.getBarcode() && temp.getSuperMarket().equals(product.getSuperMarket())) {
				return false;
			}
		}
		products.add(product);
		return true;
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

	public boolean removeProductByID(int barcode, SuperMarket superMarket) {
		for (Product temp : products) {
			if (temp.getBarcode() == barcode && temp.getSuperMarket().equals(superMarket)) {
				products.remove(temp);
				return true;
			}
		}
		return false;
	}

	public Product getProductByID(int barcode, SuperMarket superMarket) {
		for (Product temp : products) {
			if (temp.getBarcode() == barcode && temp.getSuperMarket().equals(superMarket)) {
				return temp;
			}
		}
		return null;
	}

	public boolean modifyProduct(Product product) {
		Product temp = getProductByID(product.getBarcode(), product.getSuperMarket());
		if (temp == null) {
			return false;
		}
		temp.setPrice(product.getPrice());
		temp.setQuantity(product.getQuantity());
		temp.setOffBrand(product.isOffBrand());
		return true;
	}

	public boolean removeAffiliateSuperMarketByID(String superMarketString) {
		SuperMarket temp = getSuperMarketByID(superMarketString);
		if (temp == null) {
			return false;
		}
		temp.setAffiliate(false);
		return true;
	}

	public boolean modifySuperMarket(String oldSuperMarketString, SuperMarket superMarket) {
		SuperMarket temp = getSuperMarketByID(oldSuperMarketString);
		if (temp == null) {
			return false;
		}
		temp.setName(superMarket.getName());
		temp.setCity(superMarket.getCity());
		temp.setAddress(superMarket.getAddress());
		return true;
	}

	public boolean addAffiliateSuperMarketByID(String superMarketString) {
		SuperMarket temp = getSuperMarketByID(superMarketString);
		if (temp == null) {
			return false;
		}
		temp.setAffiliate(true);
		return true;
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