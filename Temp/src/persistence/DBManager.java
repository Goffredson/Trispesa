package persistence;

import java.util.ArrayList;

import controller.AddSupermarket;
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
		superMarkets.add(new SuperMarket(1, "conad", "cosenza", "via della banana, 33", true));
		superMarkets.add(new SuperMarket(1, "coop", "cosenza", "via della vita, 33", false));
		superMarkets.add(new SuperMarket(1, "lidl", "cosenza", "via della morte, 33", true));
		superMarkets.add(new SuperMarket(1, "auchan", "cosenza", "via della citre, 33", true));
		superMarkets.add(new SuperMarket(1, "conad", "rende", "via ciccio bello, 33", false));
		SuperMarket conad = superMarkets.get(0);
		products = new ArrayList<Product>();
		products.add(new Product(0, 1, "ciqo", 1.1, 123.0, conad, true, "schifo"));
		products.add(new Product(0, 2, "wesdrtf", 1.1, 123, conad, true, "schifo"));
		products.add(new Product(0, 3, "igwhw", 1.1, 123, conad, true, "schifo"));
		products.add(new Product(0, 4, "ciqo", 1.1, 123, conad, true, "schifo"));
		products.add(new Product(0, 5, "iewhvoiw", 1.1, 123, conad, true, "schifo"));
		products.add(new Product(0, 6, "ciqo", 1.1, 123, conad, true, "schifo"));
		products.add(new Product(0, 7, "ciqo", 1.1, 123, conad, true, "schifo"));
		products.add(new Product(0, 8, "ciao che bello", 1000, 123, conad, true, "schifo"));
		products.add(new Product(0, 9, "ciqo", 1.1, 123, conad, true, "schifo"));
		products.add(new Product(0, 10, "funziona", 1.1, 123, conad, true, "schifo"));

		categories = new ArrayList<Category>();
		categories.add(new Category(0, "pasta", null, null));
		categories.add(new Category(0, "pane", null, null));
		categories.add(new Category(0, "acqua", null, null));
		categories.add(new Category(0, "ceci", null, null));

	}

	public boolean addSupermarket(SuperMarket superMarket) {
		// se lo posso aggiungere ecc
		superMarkets.add(superMarket);
		return true;
	}

	public boolean addProduct(Product product) {
		// se lo posso aggiungere ecc
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