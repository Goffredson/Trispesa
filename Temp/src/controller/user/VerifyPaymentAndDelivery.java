package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;

// Super grezza, però funziona.
public class VerifyPaymentAndDelivery extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getSession().getAttribute("customer") != null) {
			Customer loggedCustomer = (Customer) req.getSession().getAttribute("customer");
			if (loggedCustomer.getDeliveryAddresses().isEmpty() || loggedCustomer.getPaymentMethods().isEmpty()) {
				resp.setStatus(401);
				return;
			}
		}
	}
}
