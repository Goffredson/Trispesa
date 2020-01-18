package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javafx.util.Pair;
import model.Customer;
import model.Product;
import persistence.DBManager;

public class ManageCart extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long productId = Long.parseLong(req.getParameter("productId"));
		String operation = req.getParameter("operation");
		Product product = DBManager.getInstance().getProductById(productId);
		ArrayList<Pair<Product, Long>> anonymousCart = null;

		// Se c'è qualcuno loggato
		if (req.getSession().getAttribute("customer") != null) {
			Customer loggedCustomer = (Customer) req.getSession().getAttribute("customer");

			if (operation.equals("add")) {
				boolean success = DBManager.getInstance().insertProductIntoCart(product, loggedCustomer);
				if (!success) {
					resp.setStatus(400);
				}
			} else if (operation.equals("remove")) {
				DBManager.getInstance().removeProductFromCart(product, loggedCustomer);
			}
		} else {

			if (req.getSession().getAttribute("anonymousCart") == null) {
				anonymousCart = new ArrayList<>();
				req.getSession().setAttribute("anonymousCart", anonymousCart);
			} else {
				anonymousCart = (ArrayList<Pair<Product, Long>>) req.getSession().getAttribute("anonymousCart");
				System.out.println("Deserializzato");
			}

			if (operation.equals("add")) {
				long quantityOfProduct = DBManager.getInstance().getQuantityOfProduct(productId);
				if (quantityOfProduct == 0) {
					resp.setStatus(400);
				}
				// Aggiunta
				else {
					
					boolean productAlreadyInCart = false;
					
					// Se c'è già incremento
					for (Pair<Product, Long> p : anonymousCart) {
						if (p.getKey().getId() == productId) {
							System.out.println("Incremento il prodotto " + p.getKey().getName());
							long newQuantity = p.getValue() + 1;
							System.out.println(newQuantity);
							p = new Pair<Product, Long>(product, newQuantity);
							productAlreadyInCart = true;
							System.out.println("Value è ora: " + p.getValue());
							break;
						}
					}
					// Altrimenti creo e aggiungo
					if (!productAlreadyInCart)
						anonymousCart.add(new Pair<Product, Long>(product, 1L));
					
				}
			} 
			// Rimozione
			else if (operation.equals("remove")) {
				for (Pair<Product, Long> p : anonymousCart) {
					if (p.getKey().getId() == product.getId()) {
						if (p.getValue() == 1) {
							anonymousCart.remove(p);
							break;
						} else {
							long newQuantity = p.getValue() - 1;
							p = new Pair<Product, Long>(product, newQuantity);
							break;
						}
					}
				}
			}
		}
	}
}
