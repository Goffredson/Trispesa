package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
				boolean success = DBManager.getInstance().insertProductIntoCart(product, loggedCustomer);
				if (!success) 
					resp.setStatus(400);
			} 
			else if (operation.equals("remove")) 
				DBManager.getInstance().removeProductFromCart(product, loggedCustomer);
			
		} 
		else {
			if (req.getSession().getAttribute("anonymousCart") == null) {
				anonymousCart = new HashMap<>();
				req.getSession().setAttribute("anonymousCart", anonymousCart);
			} 
			else 
				anonymousCart = (HashMap<Product, Long>) req.getSession().getAttribute("anonymousCart");
			
			// Aggiunta
			if (operation.equals("add")) {
				long quantityOfProduct = DBManager.getInstance().getQuantityOfProduct(productId);
				if (quantityOfProduct == 0)
					resp.setStatus(400);
				else {
					if (anonymousCart.containsKey(product))
						anonymousCart.replace(product, anonymousCart.get(product) + 1);
					else
						anonymousCart.put(product, 1L);
				}
			}
			// Rimozione
			else if (operation.equals("remove")) {
				if (anonymousCart.get(product) == 1)
					anonymousCart.remove(product);
				else
					anonymousCart.replace(product, anonymousCart.get(product) - 1);
			}
		}
	}
}
