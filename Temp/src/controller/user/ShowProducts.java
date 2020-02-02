package controller.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import persistence.DBManager;

public class ShowProducts extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("showProducts.jsp");
		long categoryId = Long.parseLong(req.getParameter("categoria"));
		ArrayList<Product> prodotti = DBManager.getInstance().getProductsByCategory(categoryId);
		req.setAttribute("listaProdotti", prodotti);
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("showProducts.jsp");
		String nomeProdotto = req.getParameter("nomeProdotto");
		ArrayList<Product> prodotti = DBManager.getInstance().getProductsByName(nomeProdotto);
		req.setAttribute("listaProdotti", prodotti);
		rd.forward(req, resp);

	}
}
