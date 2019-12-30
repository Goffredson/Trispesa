package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.SuperMarket;
import persistence.DBManager;

public class AddProduct extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SuperMarket s = new SuperMarket(0, "ciao", "ciao", "ciao", true);
		
		Product product;
		int barcode = Integer.parseInt(req.getParameter("barcode"));
		String name = (String) req.getParameter("name");
		double weight = Double.parseDouble(req.getParameter("weight"));
		double price = Double.parseDouble(req.getParameter("price"));
		String superMarketString = (String) req.getParameter("superMarket");
		SuperMarket superMarket = DBManager.getIstance().getSuperMarketByID(superMarketString);
		String category = (String) req.getParameter("category");
		String offbrand = (String) req.getParameter("offbrand");
		if (offbrand.equals("yes"))
			product = new Product(0, barcode, name, price, weight, superMarket, true, category);
		else
			product = new Product(0, barcode, name, price, weight, superMarket, false, category);

		boolean result;
		if (DBManager.getIstance().addProduct(product))
			result = true;
		else
			result = false;

		req.setAttribute("result", result);

		RequestDispatcher rd = req.getRequestDispatcher("../productOperationResult.jsp");
		rd.forward(req, resp);
	}

}
