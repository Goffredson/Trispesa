package controller;

import java.io.IOException;
import java.util.ArrayList;

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
		RequestDispatcher rd=req.getRequestDispatcher("showProducts.jsp");
		String categoryName=req.getParameter("categoria");
		ArrayList<Product> prodotti=DBManager.getIstance().getProductsByCategory(categoryName);
		req.setAttribute("listaProdotti",prodotti);
		System.out.println(prodotti.get(0).getImagePath());
		rd.forward(req,resp);
	}
	
}
