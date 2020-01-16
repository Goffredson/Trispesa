package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import model.Product;
import persistence.DBManager;

public class CartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long productId = Long.parseLong(req.getParameter("productId"));
		Long quantityAvailable = DBManager.getInstance().getQuantityOfProduct(productId);
		
		// Ricorda qt=0
		
		if (req.getSession().getAttribute("username") != null) {
			Customer loggedCustomer = (Customer) req.getSession().getAttribute("username");
			long idCustomer = loggedCustomer.getId();
			Product product = DBManager.getInstance().getProductById(productId);
			DBManager.getInstance().insertProductIntoCart(product, idCustomer);
		}
		
		PrintWriter out = resp.getWriter();
    	resp.setCharacterEncoding("UTF-8");
    	out.print(quantityAvailable);
    	out.flush();
	}

}
