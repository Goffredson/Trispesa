package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Administrator;
import model.Customer;
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

		Gson gson = new Gson();
		Customer temp = gson.fromJson(jsonReceived.toString(), Customer.class);
		System.out.println(temp.getUsername());
		System.out.println(temp.getPassword());

		// TODO: Ripulire questo pezzo di servlet senza usare customer.class
		Customer customer = DBManager.getInstance().checkIfCustomerExists(temp.getUsername(), temp.getPassword());
		Administrator administrator = DBManager.getInstance().checkIfAdministratorExists(temp.getUsername(), temp.getPassword());

		String response = "{\"redirect\":false}";
		
		if (customer != null) {
			req.getSession().setAttribute("customer", customer);
		} else if (administrator != null) {
			req.getSession().setAttribute("administrator", administrator);
			response = "{\"redirect\":true,\"redirect_url\":\"administration\"}"; 
		} else {
			resp.sendError(401);
		}
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(response);
		out.flush();
	}

}
