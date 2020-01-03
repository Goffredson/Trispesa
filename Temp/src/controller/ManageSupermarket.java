package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.DBOperationException;
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
		boolean result = true;

		try {
			switch (req.getParameter("action")) {
			case "add": {
				req.setAttribute("op", "Aggiungi supermercato");
				SuperMarket superMarket;
				String name = (String) req.getParameter("name");
				String city = (String) req.getParameter("city");
				String address = (String) req.getParameter("address");
				String affiliate = (String) req.getParameter("affiliate");
				double latitude = Double.parseDouble(req.getParameter("latitude"));
				double longitude = Double.parseDouble(req.getParameter("longitude"));
				if (affiliate.equals("yes"))
					superMarket = new SuperMarket(name, city, address, true, latitude, longitude);
				else
					superMarket = new SuperMarket(name, city, address, false, latitude, longitude);

				DBManager.getIstance().addSupermarket(superMarket);

				req.setAttribute("result", result);
				req.setAttribute("object", superMarket.toString());

				rd = req.getRequestDispatcher("../operationResult.jsp");
				rd.forward(req, resp);
			}
				break;

			case "mod": {
				req.setAttribute("op", "Modifica supermercato");
				String name = (String) req.getParameter("name");
				String city = (String) req.getParameter("city");
				String address = (String) req.getParameter("address");
				double latitude = Double.parseDouble(req.getParameter("latitude"));
				double longitude = Double.parseDouble(req.getParameter("longitude"));
				SuperMarket superMarket = new SuperMarket(name, city, address, true, latitude, longitude);

				String oldSuperMarketString = (String) req.getParameter("old");

				DBManager.getIstance().modifySuperMarket(oldSuperMarketString, superMarket);

				req.setAttribute("result", result);
				req.setAttribute("object", superMarket.toString());

				rd = req.getRequestDispatcher("../operationResult.jsp");
				rd.forward(req, resp);
			}
				break;

			case "del": {
				req.setAttribute("op", "Rimuovi affiliazione supermercato");
				String superMarketString = (String) req.getParameter("superMarket");
				SuperMarket superMarket = DBManager.getIstance().getSuperMarketByID(superMarketString);

				DBManager.getIstance().removeAffiliateSuperMarketByID(superMarketString);

				req.setAttribute("result", result);
				req.setAttribute("object", superMarket.toString());

				rd = req.getRequestDispatcher("../operationResult.jsp");
				rd.forward(req, resp);
			}
				break;

			case "aff": {
				req.setAttribute("op", "Aggiungi affiliazione supermercato");
				String superMarketString = (String) req.getParameter("superMarket");
				SuperMarket superMarket = DBManager.getIstance().getSuperMarketByID(superMarketString);

				DBManager.getIstance().addAffiliateSuperMarketByID(superMarketString);

				req.setAttribute("result", result);
				req.setAttribute("object", superMarket.toString());

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
