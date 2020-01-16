package controller;

import java.io.IOException;
import java.util.ArrayList;

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

public class ManageProductForm extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<SuperMarket> supeMarkets = DBManager.getInstance().getAffiliateSuperMarkets();
		req.setAttribute("superMarkets", supeMarkets);
		// TODO dovremmo importare solo le categorie foglia
		ArrayList<Category> categories = DBManager.getInstance().getCategories();
		req.setAttribute("categories", categories);

		switch (req.getParameter("action")) {
		case "add":
			req.setAttribute("action", "add");
			break;

		case "mod":
			req.setAttribute("action", "mod");
			long id = Long.parseLong(req.getParameter("id"));
			Product product;
			try {
				product = DBManager.getInstance().getProductById(id);
				req.setAttribute("product", product);
			} catch (DBOperationException e) {
				e.printStackTrace();
			}
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
