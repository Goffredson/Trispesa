package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.DBOperationException;
import model.Category;
import model.Product;
import model.SuperMarket;
import persistence.DBManager;

public class ManageProduct extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd;
		boolean result = true;

		try {
			switch (req.getParameter("action")) {
			case "add": {
				req.setAttribute("op", "Aggiungi prodotto");
				Product product;
				int barcode = Integer.parseInt(req.getParameter("barcode"));
				String name = (String) req.getParameter("name");
				double weight = Double.parseDouble(req.getParameter("weight"));
				double price = Double.parseDouble(req.getParameter("price"));
				int quantity = Integer.parseInt(req.getParameter("quantity"));
				String superMarketString = (String) req.getParameter("superMarket");
				SuperMarket superMarket = DBManager.getIstance().getSuperMarketByID(superMarketString);
				String categoryString = (String) req.getParameter("category");
				Category category = DBManager.getIstance().getCategoryByFamilyName(categoryString);
				String offbrand = (String) req.getParameter("offbrand");
				if (offbrand.equals("yes"))
					product = new Product(barcode, name, price, weight, superMarket, true, category, quantity);
				else
					product = new Product(barcode, name, price, weight, superMarket, false, category, quantity);

				DBManager.getIstance().addProduct(product);

				req.setAttribute("result", result);
				req.setAttribute("object", product.toString());

				rd = req.getRequestDispatcher("../operationResult.jsp");
				rd.forward(req, resp);
			}
				break;

			case "mod": {
				req.setAttribute("op", "Modifica prodotto");
				Product product;
				int barcode = Integer.parseInt(req.getParameter("barcode"));
				String name = (String) req.getParameter("name");
				double weight = Double.parseDouble(req.getParameter("weight"));
				double price = Double.parseDouble(req.getParameter("price"));
				int quantity = Integer.parseInt(req.getParameter("quantity"));
				String superMarketString = (String) req.getParameter("superMarket");
				SuperMarket superMarket = DBManager.getIstance().getSuperMarketByID(superMarketString);
				String categoryString = (String) req.getParameter("category");
				Category category = DBManager.getIstance().getCategoryByFamilyName(categoryString);
				String offbrand = (String) req.getParameter("offbrand");
				if (offbrand.equals("yes"))
					product = new Product(barcode, name, price, weight, superMarket, true, category, quantity);
				else
					product = new Product(barcode, name, price, weight, superMarket, false, category, quantity);

				DBManager.getIstance().modifyProduct(product);

				req.setAttribute("result", result);
				req.setAttribute("object", product.toString());

				rd = req.getRequestDispatcher("../operationResult.jsp");
				rd.forward(req, resp);
			}
				break;

			case "del": {
				req.setAttribute("op", "Elimina prodotto");
				int barcode = Integer.parseInt(req.getParameter("barcode"));
				String superMarketString = (String) req.getParameter("superMarket");
				SuperMarket superMarket = DBManager.getIstance().getSuperMarketByID(superMarketString);
				Product product = DBManager.getIstance().getProductByID(barcode, superMarket);
				
				DBManager.getIstance().removeProductByID(barcode, superMarket);

				req.setAttribute("result", result);
				req.setAttribute("object", product.toString());

				rd = req.getRequestDispatcher("../operationResult.jsp");
				rd.forward(req, resp);
			}
				break;

			}
		} catch (DBOperationException e) {
			result = false;
			req.setAttribute("result", result);
			req.setAttribute("exception", e);
			rd = req.getRequestDispatcher("../operationResult.jsp");
			rd.forward(req, resp);
		}
	}

}
