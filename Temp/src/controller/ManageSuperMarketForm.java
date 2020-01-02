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
			String superMarketString = (String) req.getParameter("superMarket");
			SuperMarket superMarket = DBManager.getIstance().getSuperMarketByID(superMarketString);
			req.setAttribute("superMarket", superMarket);
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
