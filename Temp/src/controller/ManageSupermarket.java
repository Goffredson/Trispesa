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
		try {
			switch (req.getParameter("action")) {
			case "add": {
				req.getSession().setAttribute("op", "Aggiungi supermercato");

				SuperMarket superMarket;
				String name = (String) req.getParameter("name");
				String city = (String) req.getParameter("city");
				String address = (String) req.getParameter("address");
				String affiliate = (String) req.getParameter("affiliate");
				double latitude = Double.parseDouble(req.getParameter("latitude"));
				double longitude = Double.parseDouble(req.getParameter("longitude"));
				if (affiliate.equals("yes"))
					superMarket = new SuperMarket(0, name, "Italy", city, address, latitude, longitude, true);
				else
					superMarket = new SuperMarket(0, name, "Italy", city, address, latitude, longitude, false);

				DBManager.getInstance().getSuperMarketDao().insert(superMarket);

				req.getSession().setAttribute("result", true);
				req.getSession().setAttribute("object", superMarket.toString());
				resp.sendRedirect("../supermarket");
			}
				break;

			case "mod": {
				req.getSession().setAttribute("op", "Modifica supermercato");

				String name = (String) req.getParameter("name");
				String city = (String) req.getParameter("city");
				String address = (String) req.getParameter("address");
				double latitude = Double.parseDouble(req.getParameter("latitude"));
				double longitude = Double.parseDouble(req.getParameter("longitude"));
				SuperMarket superMarket = new SuperMarket(0, name, "Italy", city, address, latitude, longitude, false);

				String oldSuperMarketString = (String) req.getParameter("old");

				DBManager.getInstance().modifySuperMarket(oldSuperMarketString, superMarket);

				req.getSession().setAttribute("result", true);
				req.getSession().setAttribute("object", superMarket.toString());
				resp.sendRedirect("../supermarket");
			}
				break;

			case "del": {
				req.getSession().setAttribute("op", "Rimuovi affiliazione supermercato");
				String superMarketString = (String) req.getParameter("superMarket");
				SuperMarket superMarket = DBManager.getInstance().getSuperMarketByID(superMarketString);

				DBManager.getInstance().removeAffiliateSuperMarketByID(superMarketString);

				req.getSession().setAttribute("result", true);
				req.getSession().setAttribute("object", superMarket.toString());
				resp.sendRedirect("../supermarket");
			}
				break;

			case "aff": {
				req.getSession().setAttribute("op", "Aggiungi affiliazione supermercato");
				String superMarketString = (String) req.getParameter("superMarket");
				SuperMarket superMarket = DBManager.getInstance().getSuperMarketByID(superMarketString);

				DBManager.getInstance().addAffiliateSuperMarketByID(superMarketString);

				req.getSession().setAttribute("result", true);
				req.getSession().setAttribute("object", superMarket.toString());
				resp.sendRedirect("../supermarket");
			}
				break;
			}
		} catch (DBOperationException e) {
			req.getSession().setAttribute("result", false);
			req.getSession().setAttribute("exception", e);
			resp.sendRedirect("../supermarket");
		}
	}

}
