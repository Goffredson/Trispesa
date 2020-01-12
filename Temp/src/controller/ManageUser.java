package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.DBOperationException;
import model.Customer;
import model.DeliveryAddress;
import model.PaymentMethod;
import persistence.DBManager;

public class ManageUser extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			switch (req.getParameter("type")) {
			case "credentials": {
				switch (req.getParameter("action")) {
				case "add": {
				}
					break;
				case "mod": {
				}
					break;
				case "del": {
				}
					break;
				}
			}
				break;
			case "paymentMethod": {
				switch (req.getParameter("action")) {
				case "add": {
				}
					break;
				case "mod": {
				}
					break;
				case "del": {
					Customer customer = (Customer) req.getSession().getAttribute("customer");
					int cardNumber = Integer.parseInt(req.getParameter("card"));
					PaymentMethod paymentMethod = customer.getPaymentMethodByCardNumber(cardNumber);

					DBManager.getInstance().removePaymentMethod(customer, paymentMethod);

					req.getSession().setAttribute("result", "true");
					req.getSession().setAttribute("message", "Il metodo di pagamento è stato eliminato con successo!");
					resp.sendRedirect("../user?page=profile");
				}
					break;
				}
			}
				break;
			case "deliveryAddress": {
				switch (req.getParameter("action")) {
				case "add": {
				}
					break;
				case "mod": {
				}
					break;
				case "del": {
					Customer customer = (Customer) req.getSession().getAttribute("customer");
					String deliveryAddressString = req.getParameter("deliveryAddress");
					DeliveryAddress deliveryAddress = customer.getDeliveryAddressByID(deliveryAddressString);

					DBManager.getInstance().removeDeliveryAddress(customer, deliveryAddress);

					req.getSession().setAttribute("result", "true");
					req.getSession().setAttribute("message", "L'indirizzo di consegna è stato eliminato con successo!");
					resp.sendRedirect("../user?page=profile");
				}
					break;
				}
			}
				break;
			}
		} catch (DBOperationException e) {
			req.getSession().setAttribute("result", true);
			req.getSession().setAttribute("message", e.getMessage());
			resp.sendRedirect("../user?page=profile");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
