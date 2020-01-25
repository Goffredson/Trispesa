package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.RequestDispatcher;
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
					operationResult.setObject("Il metodo di pagamento è stato eliminato con successo!");
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
					StringBuffer stringBuffer = new StringBuffer();
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
					String line = bufferedReader.readLine();
					while (line != null) {
						stringBuffer.append(line);
						line = bufferedReader.readLine();
					}
					long id = gson.fromJson(stringBuffer.toString(), JsonObject.class).get("deliveryAddress").getAsLong();
					
					Customer customer = (Customer) req.getSession().getAttribute("customer");
					DBManager.getInstance().deleteDeliveryAddress(customer, id);
					
					operationResult.setResult(true);
					operationResult.setObject("L'indirizzo di consegna è stato eliminato con successo!");
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
