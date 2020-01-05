package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import model.DeliveryAddress;
import model.PaymentMethod;
import persistence.DBManager;

public class UserManagement extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getParameter("page")) {
		case "profile": {
			Customer customer = DBManager.getIstance().getCustomerByID("Goffredson");
			req.getSession().setAttribute("customer", customer);
			RequestDispatcher rd = req.getRequestDispatcher("user/profile.jsp");
			rd.forward(req, resp);
		}
			break;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
