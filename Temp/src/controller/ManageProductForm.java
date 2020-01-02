package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;
import model.Product;
import model.SuperMarket;
import persistence.DBManager;

public class ManageProductForm extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<SuperMarket> supeMarkets = DBManager.getIstance().getSuperMarkets();
		req.setAttribute("superMarkets", supeMarkets);
		ArrayList<Category> categories = DBManager.getIstance().getCategories();
		req.setAttribute("categories", categories);


		switch (req.getParameter("action")) {
		case "add":
			req.setAttribute("action", "add");
			break;

		case "mod":
			req.setAttribute("action", "mod");
			int barcode = Integer.parseInt(req.getParameter("barcode"));
			String superMarketString = (String) req.getParameter("superMarket");
			SuperMarket superMarket = DBManager.getIstance().getSuperMarketByID(superMarketString);
			Product product = DBManager.getIstance().getProductByID(barcode, superMarket);
			req.setAttribute("product", product);
			break;
		}

		RequestDispatcher rd = req.getRequestDispatcher("../manageProduct.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
