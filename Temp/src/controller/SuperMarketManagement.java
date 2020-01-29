package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SuperMarket;
import persistence.DBManager;

public class SuperMarketManagement extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("administrator") == null) {
			resp.sendError(401);
			return;
		}

		ArrayList<SuperMarket> superMarkets = DBManager.getInstance().getSuperMarkets();
		req.setAttribute("superMarkets", superMarkets);

		RequestDispatcher rd = req.getRequestDispatcher("superMarketManagement.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
