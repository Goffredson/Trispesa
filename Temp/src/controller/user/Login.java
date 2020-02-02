package controller.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mifmif.common.regex.Generex;

import model.Administrator;
import model.Customer;
import model.Product;
import persistence.DBManager;

public class Login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		StringBuffer jsonReceived = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String line = reader.readLine();
		while (line != null) {
			jsonReceived.append(line);
			line = reader.readLine();
		}

		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();
		Type arrayListType = new TypeToken<ArrayList<String>>() {
		}.getType();
		ArrayList<String> credentials = gson.fromJson(jsonReceived.toString(), arrayListType);

		if (credentials.get(2).equals("login")) {
			Customer customer = DBManager.getInstance().checkIfCustomerExists(credentials.get(0), credentials.get(1));
			Administrator administrator = DBManager.getInstance().checkIfAdministratorExists(credentials.get(0),
					credentials.get(1));

			String response = "{\"redirect\" : true}";

			if (customer != null) {
				req.getSession().setAttribute("customer", customer);
				req.getSession().setMaxInactiveInterval(-1);

				if (req.getSession().getAttribute("anonymousCart") != null) {
					HashMap<Product, Long> anonymousCart = (HashMap<Product, Long>) req.getSession()
							.getAttribute("anonymousCart");
					// customer.setCart(anonymousCart);
					System.out.println("Faccio il fill");
					DBManager.getInstance().fillCartFromAnonymous(customer, anonymousCart);
					req.getSession().removeAttribute("anonymousCart");
				}
				response = gson.toJson(customer.getCart(), new TypeToken<HashMap<Product, Long>>() {
				}.getType());
			} else if (administrator != null) {
				req.getSession().setAttribute("administrator", administrator);
			} else {
				resp.sendError(401);
			}
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(response);
			out.flush();
		} else {
			req.getSession().removeAttribute("customer");
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (DBManager.getInstance().checkIfUsernameExists(req.getParameter("username"))) {
			String email = DBManager.getInstance().getCustomerEmail(req.getParameter("username"));
			String newPassword = new Generex("(\\d){2}([a-z]){3}([A-Z]){4}").random();
			DBManager.getInstance().updateCustomerPassword(req.getParameter("username"), newPassword);
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
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
				message.setSubject("Trispesa - Recupero Password");
				message.setText("Ecco la tua nuova password.\n" + newPassword
						+ "\nCordiali saluti, TriSpesa Staff");
				Transport.send(message);
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			PrintWriter out = resp.getWriter();
			resp.setCharacterEncoding("UTF-8");
			out.print(email);
			out.flush();
		} else {
			resp.setStatus(401);
		}

	}

}
