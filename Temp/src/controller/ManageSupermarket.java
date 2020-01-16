package controller;

import java.io.IOException;

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
				String country = (String) req.getParameter("country");
				String city = (String) req.getParameter("city");
				String address = (String) req.getParameter("address");
				double latitude = Double.parseDouble(req.getParameter("latitude"));
				double longitude = Double.parseDouble(req.getParameter("longitude"));
				String affiliate = (String) req.getParameter("affiliate");
				if (affiliate.equals("yes"))
					superMarket = new SuperMarket(0, name, country, city, address, latitude, longitude, true);
				else
					superMarket = new SuperMarket(0, name, country, city, address, latitude, longitude, false);

				DBManager.getInstance().addSupermarket(superMarket);

				req.getSession().setAttribute("result", true);
				req.getSession().setAttribute("object", superMarket.toString());
				resp.sendRedirect("../supermarket");
			}
				break;

			case "mod": {
				req.getSession().setAttribute("op", "Modifica supermercato");

				SuperMarket superMarket;
				long id = Long.parseLong(req.getParameter("old"));
				String name = (String) req.getParameter("name");
				String country = (String) req.getParameter("country");
				String city = (String) req.getParameter("city");
				String address = (String) req.getParameter("address");
				double latitude = Double.parseDouble(req.getParameter("latitude"));
				double longitude = Double.parseDouble(req.getParameter("longitude"));
				String affiliate = (String) req.getParameter("affiliate");
				if (affiliate.equals("yes"))
					superMarket = new SuperMarket(id, name, country, city, address, latitude, longitude, true);
				else
					superMarket = new SuperMarket(id, name, country, city, address, latitude, longitude, false);

				DBManager.getInstance().modifySuperMarket(superMarket);

				req.getSession().setAttribute("result", true);
				req.getSession().setAttribute("object", superMarket.toString());
				resp.sendRedirect("../supermarket");
			}
				break;

			case "del": {
				req.getSession().setAttribute("op", "Rimuovi affiliazione supermercato");
				long id = Long.parseLong(req.getParameter("id"));

				DBManager.getInstance().removeAffiliateSuperMarketById(id);

				req.getSession().setAttribute("result", true);
				req.getSession().setAttribute("object", DBManager.getInstance().getSupermarketById(id).toString());
				resp.sendRedirect("../supermarket");
			}
				break;

			case "aff": {
				req.getSession().setAttribute("op", "Aggiungi affiliazione supermercato");
				long id = Long.parseLong(req.getParameter("id"));

				DBManager.getInstance().addAffiliateSuperMarketById(id);

				req.getSession().setAttribute("result", true);
				req.getSession().setAttribute("object", DBManager.getInstance().getSupermarketById(id).toString());
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
