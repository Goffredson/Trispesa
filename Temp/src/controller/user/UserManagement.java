package controller.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import model.Order;
import persistence.DBManager;

public class UserManagement extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("customer") == null) {
			resp.sendError(401);
			return;
		}

		switch (req.getParameter("page")) {
		case "profile": {
			RequestDispatcher rd = req.getRequestDispatcher("user/profile.jsp");
			rd.forward(req, resp);
		}
			break;

		case "orders": {
			Customer customer = (Customer) req.getSession().getAttribute("customer");
			ArrayList<Order> orders = DBManager.getInstance().getOrdersOfCustomer(customer);
			req.setAttribute("orders", orders);
			RequestDispatcher rd = req.getRequestDispatcher("user/orders.jsp");
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
