package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SuperMarket;
import persistence.DBManager;

public class ManageSupermarket extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd;
		boolean result;

		switch (req.getParameter("action")) {
		case "add": {
			SuperMarket superMarket;
			String name = (String) req.getParameter("name");
			String city = (String) req.getParameter("city");
			String address = (String) req.getParameter("address");
			String affiliate = (String) req.getParameter("affiliate");
			if (affiliate.equals("yes"))
				superMarket = new SuperMarket(name, city, address, true);
			else
				superMarket = new SuperMarket(name, city, address, false);

			if (DBManager.getIstance().addSupermarket(superMarket))
				result = true;
			else
				result = false;

			req.setAttribute("result", result);

			rd = req.getRequestDispatcher("../operationResult.jsp");
			rd.forward(req, resp);
		}
			break;

		case "mod": {
			String name = (String) req.getParameter("name");
			String city = (String) req.getParameter("city");
			String address = (String) req.getParameter("address");
			SuperMarket superMarket = new SuperMarket(name, city, address, true);

			String oldSuperMarketString = (String) req.getParameter("old");

			if (DBManager.getIstance().modifySuperMarket(oldSuperMarketString, superMarket))
				result = true;
			else
				result = false;

			req.setAttribute("result", result);

			rd = req.getRequestDispatcher("../operationResult.jsp");
			rd.forward(req, resp);
		}
			break;

		case "del": {
			String superMarketString = (String) req.getParameter("superMarket");
			if (DBManager.getIstance().removeAffiliateSuperMarketByID(superMarketString))
				result = true;
			else
				result = false;

			req.setAttribute("result", result);

			rd = req.getRequestDispatcher("../operationResult.jsp");
			rd.forward(req, resp);
		}
			break;

		case "aff":
			String superMarketString = (String) req.getParameter("superMarket");
			if (DBManager.getIstance().addAffiliateSuperMarketByID(superMarketString))
				result = true;
			else
				result = false;

			req.setAttribute("result", result);

			rd = req.getRequestDispatcher("../operationResult.jsp");
			rd.forward(req, resp);
			break;
		}
	}

}
