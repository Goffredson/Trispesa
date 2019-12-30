package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SuperMarket;
import persistence.DBManager;

public class AddSupermarket extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SuperMarket superMarket;
		String name = (String) req.getParameter("name");
		String city = (String) req.getParameter("city");
		String address = (String) req.getParameter("address");
		String affiliate = (String) req.getParameter("affiliate");
		if (affiliate.equals("yes"))
			superMarket = new SuperMarket(0, name, city, address, true);
		else
			superMarket = new SuperMarket(0, name, city, address, false);

		boolean result;
		if (DBManager.getIstance().addSupermarket(superMarket))
			result = true;
		else
			result = false;

		req.setAttribute("result", result);

		RequestDispatcher rd = req.getRequestDispatcher("../superMarketOperationResult.jsp");
		rd.forward(req, resp);
	}

}
