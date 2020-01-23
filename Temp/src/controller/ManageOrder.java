package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import persistence.DBManager;

public class ManageOrder extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		if (req.getParameter("operation") == null) {
			RequestDispatcher rd = req.getRequestDispatcher("makeOrder.jsp");
			rd.forward(req, resp);			
		}
		else {
			Long paymentId = Long.parseLong(req.getParameter("paymentId"));
			String expirationDate = req.getParameter("expirationDate");
			Long securityCode = Long.parseLong(req.getParameter("securityCode"));
			System.out.println("Entrato");
			if (DBManager.getInstance().checkPaymentData(paymentId, expirationDate, securityCode) == false)
				resp.setStatus(401);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Customer customer = (Customer) req.getSession().getAttribute("customer");
		String paymentId = req.getParameter("paymentId");
		String deliveryAddressId = req.getParameter("deliveryAddressId");
		DBManager.getInstance().createOrder(customer, paymentId, deliveryAddressId);
		
	}
}
