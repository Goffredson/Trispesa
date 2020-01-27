package controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
		} else {
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
		sendEmail(customer);
	}

	public void sendEmail(Customer customer) {
		final String username = "trispesaStaff@gmail.com";
		final String password = "trispesa2020";
		String host = "smtp.gmail.com";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.secure", "ssl");
		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		session.setDebug(true);
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("trispesaStaff@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customer.getEmail()));
			message.setSubject("Conferma ordine");
			message.setText(
					"La ringrazio per aver ordinato su trispesa,il suo ordine è stato confermato ed è in fase di processamento,può controllare il suo stato nella sezione ordini."
							+ "Cordiali saluti,trispesa staff");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
