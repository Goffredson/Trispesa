package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import javafx.util.Pair;
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
		Pair<String, String> credenziali;
		credenziali = gson.fromJson(jsonReceived.toString(), Pair.class);
		// Faremo poi la query
		Customer customer = DBManager.getInstance().checkIfExists(credenziali.getKey(),credenziali.getValue());
		if (customer != null) {
			req.getSession().setAttribute("username", credenziali.getKey());
		} else {
			resp.sendError(401);
		}
	}

}
