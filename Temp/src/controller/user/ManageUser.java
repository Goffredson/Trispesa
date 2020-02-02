package controller.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import exceptions.DBOperationException;
import model.Customer;
import model.DeliveryAddress;
import model.OperationResult;
import model.PaymentMethod;
import persistence.DBManager;

public class ManageUser extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		OperationResult operationResult = new OperationResult();
		Gson gson = new Gson();
		try {
			switch (req.getParameter("type")) {
			case "credentials": {
				switch (req.getParameter("action")) {
				case "paymentAndAddressCheck":
					Customer loggedCustomer = (Customer) req.getSession().getAttribute("customer");
					if (loggedCustomer.getDeliveryAddresses().isEmpty() || loggedCustomer.getPaymentMethods().isEmpty()) {
						resp.setStatus(401);
						return;
					}
					break;
				
				case "usernameCheck": {
					StringBuffer stringBuffer = new StringBuffer();
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
					String line = bufferedReader.readLine();
					while (line != null) {
						stringBuffer.append(line);
						line = bufferedReader.readLine();
					}
					String username = gson.fromJson(stringBuffer.toString(), JsonObject.class).get("username")
							.getAsString();

					if (DBManager.getInstance().checkIfUsernameExists(username)) {
						operationResult.setResult(false);
					} else {
						operationResult.setResult(true);
					}
				}
					break;

				case "username": {
					StringBuffer stringBuffer = new StringBuffer();
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
					String line = bufferedReader.readLine();
					while (line != null) {
						stringBuffer.append(line);
						line = bufferedReader.readLine();
					}
					String username = gson.fromJson(stringBuffer.toString(), JsonObject.class).get("username")
							.getAsString();

					Customer customer = (Customer) req.getSession().getAttribute("customer");

					DBManager.getInstance().modUsername(customer, username);

					operationResult.setResult(true);
					operationResult.setObject("Lo username � stato aggiornato con successo!");
				}
					break;

				case "password": {
					StringBuffer stringBuffer = new StringBuffer();
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
					String line = bufferedReader.readLine();
					while (line != null) {
						stringBuffer.append(line);
						line = bufferedReader.readLine();
					}
					String passwordOld = gson.fromJson(stringBuffer.toString(), JsonObject.class).get("passwordOld")
							.getAsString();
					String passwordNew = gson.fromJson(stringBuffer.toString(), JsonObject.class).get("passwordNew")
							.getAsString();

					Customer customer = (Customer) req.getSession().getAttribute("customer");

					if (customer.getPassword().equals(passwordOld) && customer.getPassword().equals(passwordNew)) {
						throw new DBOperationException(
								"La password che sta cercando di inserire corrisponde alla vecchia password", "");
					} else if (!(customer.getPassword().equals(passwordOld))) {
						throw new DBOperationException("La vecchia password non � corretta!", "");
					}

					DBManager.getInstance().modPassword(customer, passwordNew);

					operationResult.setResult(true);
					operationResult.setObject("La password � stata aggiornata con successo!");
				}
					break;

				case "name": {
					StringBuffer stringBuffer = new StringBuffer();
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
					String line = bufferedReader.readLine();
					while (line != null) {
						stringBuffer.append(line);
						line = bufferedReader.readLine();
					}
					String name = gson.fromJson(stringBuffer.toString(), JsonObject.class).get("name").getAsString();

					Customer customer = (Customer) req.getSession().getAttribute("customer");

					DBManager.getInstance().modName(customer, name);

					operationResult.setResult(true);
					operationResult.setObject("Il nome � stato aggiornato con successo!");
				}
					break;

				case "surname": {
					StringBuffer stringBuffer = new StringBuffer();
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
					String line = bufferedReader.readLine();
					while (line != null) {
						stringBuffer.append(line);
						line = bufferedReader.readLine();
					}
					String surname = gson.fromJson(stringBuffer.toString(), JsonObject.class).get("surname")
							.getAsString();

					Customer customer = (Customer) req.getSession().getAttribute("customer");

					DBManager.getInstance().modSurname(customer, surname);

					operationResult.setResult(true);
					operationResult.setObject("Il cognome � stato aggiornato con successo!");
				}
					break;

				case "email": {
					StringBuffer stringBuffer = new StringBuffer();
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
					String line = bufferedReader.readLine();
					while (line != null) {
						stringBuffer.append(line);
						line = bufferedReader.readLine();
					}
					String email = gson.fromJson(stringBuffer.toString(), JsonObject.class).get("email").getAsString();

					Customer customer = (Customer) req.getSession().getAttribute("customer");

					DBManager.getInstance().modEmail(customer, email);

					operationResult.setResult(true);
					operationResult.setObject("L'indirizzo email � stato aggiornato con successo!");
				}
					break;

				case "birthDate": {
					StringBuffer stringBuffer = new StringBuffer();
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
					String line = bufferedReader.readLine();
					while (line != null) {
						stringBuffer.append(line);
						line = bufferedReader.readLine();
					}
					String birthDate = gson.fromJson(stringBuffer.toString(), JsonObject.class).get("birthDate")
							.getAsString();

					Customer customer = (Customer) req.getSession().getAttribute("customer");

					DBManager.getInstance().modBirthDate(customer, birthDate);

					operationResult.setResult(true);
					operationResult.setObject("La data di nascita � stata modificata con successo!");
				}
					break;
				}
				break;
			}
			case "paymentMethod": {
				switch (req.getParameter("action")) {
				case "add": {
					StringBuffer stringBuffer = new StringBuffer();
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
					String line = bufferedReader.readLine();
					while (line != null) {
						stringBuffer.append(line);
						line = bufferedReader.readLine();
					}
					PaymentMethod paymentMethod = gson.fromJson(stringBuffer.toString(), PaymentMethod.class);
					Customer customer = (Customer) req.getSession().getAttribute("customer");

					DBManager.getInstance().addPaymentMethod(customer, paymentMethod);

					operationResult.setResult(true);
					operationResult.setObject("Il metodo di pagamento � stato aggiunto con successo!");
				}
					break;
				case "mod": {
					StringBuffer stringBuffer = new StringBuffer();
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
					String line = bufferedReader.readLine();
					while (line != null) {
						stringBuffer.append(line);
						line = bufferedReader.readLine();
					}
					PaymentMethod paymentMethod = gson.fromJson(stringBuffer.toString(), PaymentMethod.class);
					Customer customer = (Customer) req.getSession().getAttribute("customer");

					DBManager.getInstance().modPaymentMethod(customer, paymentMethod);

					operationResult.setResult(true);
					operationResult.setObject("Il metodo di pagamento � stato modificato con successo!");
				}
					break;
				case "del": {
					StringBuffer stringBuffer = new StringBuffer();
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
					String line = bufferedReader.readLine();
					while (line != null) {
						stringBuffer.append(line);
						line = bufferedReader.readLine();
					}
					long id = gson.fromJson(stringBuffer.toString(), JsonObject.class).get("paymentMethod").getAsLong();

					Customer customer = (Customer) req.getSession().getAttribute("customer");
					DBManager.getInstance().deletePaymentMethod(customer, id);

					operationResult.setResult(true);
					operationResult.setObject("Il metodo di pagamento � stato eliminato con successo!");
				}
					break;
				}
			}
				break;
			case "deliveryAddress": {
				switch (req.getParameter("action")) {
				case "add": {
					StringBuffer stringBuffer = new StringBuffer();
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
					String line = bufferedReader.readLine();
					while (line != null) {
						stringBuffer.append(line);
						line = bufferedReader.readLine();
					}
					DeliveryAddress deliveryAddress = gson.fromJson(stringBuffer.toString(), DeliveryAddress.class);
					Customer customer = (Customer) req.getSession().getAttribute("customer");

					DBManager.getInstance().addDeliveryAddress(customer, deliveryAddress);

					operationResult.setResult(true);
					operationResult.setObject("L'indirizzo di consegna � stato aggiunto con successo!");
				}
					break;
				case "mod": {
					StringBuffer stringBuffer = new StringBuffer();
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
					String line = bufferedReader.readLine();
					while (line != null) {
						stringBuffer.append(line);
						line = bufferedReader.readLine();
					}
					DeliveryAddress deliveryAddress = gson.fromJson(stringBuffer.toString(), DeliveryAddress.class);
					Customer customer = (Customer) req.getSession().getAttribute("customer");

					DBManager.getInstance().modDeliveryAddress(customer, deliveryAddress);

					operationResult.setResult(true);
					operationResult.setObject("L'indirizzo di consegna � stato modificato con successo!");
				}
					break;
				case "del": {
					StringBuffer stringBuffer = new StringBuffer();
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
					String line = bufferedReader.readLine();
					while (line != null) {
						stringBuffer.append(line);
						line = bufferedReader.readLine();
					}
					long id = gson.fromJson(stringBuffer.toString(), JsonObject.class).get("deliveryAddress")
							.getAsLong();

					Customer customer = (Customer) req.getSession().getAttribute("customer");
					DBManager.getInstance().deleteDeliveryAddress(customer, id);

					operationResult.setResult(true);
					operationResult.setObject("L'indirizzo di consegna � stato eliminato con successo!");
				}
					break;
				}
			}
				break;
			}
		} catch (DBOperationException e) {
			operationResult.setResult(false);
			operationResult.setObject(e.getMessage());
		} finally {
			PrintWriter writer = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			writer.println(gson.toJson(operationResult));
			writer.flush();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
