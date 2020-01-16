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

public class ManageSuperMarketForm extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd;
		boolean result;

		switch (req.getParameter("action")) {
		case "add":
			req.setAttribute("action", "add");
			break;

		case "mod":
			req.setAttribute("action", "mod");
			long id = Long.parseLong(req.getParameter("id"));
			SuperMarket superMarket;
			try {
				superMarket = DBManager.getInstance().getSupermarketById(id);
				req.setAttribute("superMarket", superMarket);
			} catch (DBOperationException e) {
				e.printStackTrace();
			}
			break;
		}

		rd = req.getRequestDispatcher("../manageSuperMarket.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
