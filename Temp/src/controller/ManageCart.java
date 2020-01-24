package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Customer;
import model.Product;
import persistence.DBManager;

public class ManageCart extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long productId = Long.parseLong(req.getParameter("productId"));
		String operation = req.getParameter("operation");
		Product product = DBManager.getInstance().getProductById(productId);
		HashMap<Product, Long> anonymousCart = null;

		// Se c'è qualcuno loggato
		if (req.getSession().getAttribute("customer") != null) {
			Customer loggedCustomer = (Customer) req.getSession().getAttribute("customer");

			if (operation.equals("add")) {
				DBManager.getInstance().decreaseProductQuantity(productId, 1L);
				boolean success = DBManager.getInstance().insertProductIntoCart(product, loggedCustomer);
				if (!success)
					resp.setStatus(400);
			} else if (operation.equals("remove")) {
				DBManager.getInstance().increaseProductQuantity(productId, 1L);
				DBManager.getInstance().removeProductFromCart(product, loggedCustomer);
			}

		} else {
			if (req.getSession().getAttribute("anonymousCart") == null) {
				anonymousCart = new HashMap<>();
				req.getSession().setAttribute("anonymousCart", anonymousCart);
			} else
				anonymousCart = (HashMap<Product, Long>) req.getSession().getAttribute("anonymousCart");

			// Aggiunta
			if (operation.equals("add")) {
				DBManager.getInstance().decreaseProductQuantity(productId, 1L);
				if (anonymousCart.containsKey(product))
					anonymousCart.replace(product, anonymousCart.get(product) + 1);
				else
					anonymousCart.put(product, 1L);

			}
			// Rimozione
			else if (operation.equals("remove")) {
				DBManager.getInstance().increaseProductQuantity(productId, 1L);
				if (anonymousCart.get(product) == 1)
					anonymousCart.remove(product);
				else
					anonymousCart.replace(product, anonymousCart.get(product) - 1);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuffer jsonReceived = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String line = reader.readLine();
		while (line != null) {
			jsonReceived.append(line);
			line = reader.readLine();
		}
		Gson gson = new Gson();
		Type hashMapType = new TypeToken<HashMap<Long,Long>>(){}.getType();
		HashMap<Long,Long> productsInCart=gson.fromJson(jsonReceived.toString(),hashMapType);
		for (Entry<Long, Long> p : productsInCart.entrySet()) {
			DBManager.getInstance().increaseProductQuantity(p.getKey(),p.getValue());
		}
		if(req.getSession().getAttribute("anonymousCart")!=null) {
			HashMap<Product,Long> anonymousCart=(HashMap<Product, Long>) req.getSession().getAttribute("anonymousCart");
			anonymousCart.clear();
		}
		else {
			Customer c=(Customer) req.getSession().getAttribute("customer");
			c.getCart().clear();
			DBManager.getInstance().emptyCustomerCart(c.getId());
			
		}
	}
}
