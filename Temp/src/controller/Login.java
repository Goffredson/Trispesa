package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

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
			
		try {
			JSONObject json = new JSONObject(jsonReceived.toString());	
			String username = json.getString("username");
			String password = json.getString("password");
			
			// Faremo poi la query
			boolean esisteUtente = true;
			if (esisteUtente) {
				req.getSession().setAttribute("username", username);
			}
			else {
				resp.sendError(401);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
