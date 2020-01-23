package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
		//System.out.println(product.hashCode());
		HashMap<Product, Long> anonymousCart = null;

		// Se c'è qualcuno loggato
		if (req.getSession().getAttribute("customer") != null) {
			Customer loggedCustomer = (Customer) req.getSession().getAttribute("customer");
			if (operation.equals("add")) {
				DBManager.getInstance().decreaseProductQuantity(product.getId(), 1L);
				if (anonymousCart.containsKey(product))
					anonymousCart.replace(product, anonymousCart.get(product) + 1);
				else
					anonymousCart.put(product, 1L);
			}
			else {
				DBManager.getInstance().increaseProductQuantity(product.getId(), 1L);
				anonymousCart.remove(product);
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
					anonymousCart.replace(product, anonymousCart.get(product) + 1L);
				else
					anonymousCart.put(product, 1L);

			}
			// Rimozione
			else if (operation.equals("remove")) {
				DBManager.getInstance().increaseProductQuantity(productId, 1L);
				System.out.println(product.hashCode());
				for (Map.Entry<Product, Long> p : anonymousCart.entrySet())
					System.out.println(p.getKey().hashCode());
				if (anonymousCart.get(product) == 1) {
					anonymousCart.remove(product);
				} else
					anonymousCart.replace(product, anonymousCart.get(product) - 1);
			}
		}
	}
}
