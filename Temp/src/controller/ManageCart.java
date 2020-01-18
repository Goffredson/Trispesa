package controller;

import java.io.IOException;

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

		// Se c'è qualcuno loggato
		if (req.getSession().getAttribute("customer") != null) {
			Customer loggedCustomer = (Customer) req.getSession().getAttribute("customer");
			Product product = DBManager.getInstance().getProductById(productId);

			if (operation.equals("add")) {
				boolean success = DBManager.getInstance().insertProductIntoCart(product, loggedCustomer);
				if (!success) {
					resp.setStatus(400);
				}
			} else if (operation.equals("remove")) {
				DBManager.getInstance().removeProductFromCart(product, loggedCustomer);
			}
		}

	}

}
