package controller.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
	    Type arrayListType = new TypeToken<ArrayList<String>>(){}.getType();
	    ArrayList<String> credentials = gson.fromJson(jsonReceived.toString(), arrayListType);
	    
	    if (credentials.get(2).equals("login")) {
	    	Customer customer = DBManager.getInstance().checkIfCustomerExists(credentials.get(0), credentials.get(1));
	    	Administrator administrator = DBManager.getInstance().checkIfAdministratorExists(credentials.get(0), credentials.get(1));
	    	
	    	String response = "{\"redirect\" : true}";
	    	
	    	if (customer != null) {
	    		req.getSession().setAttribute("customer", customer);
	    		req.getSession().setMaxInactiveInterval(-1);
	    		System.out.println(customer.getDeliveryAddresses());
	    		
	    		if (req.getSession().getAttribute("anonymousCart") != null) {
	    			HashMap<Product, Long> anonymousCart = (HashMap<Product, Long>) req.getSession().getAttribute("anonymousCart");
	    			//customer.setCart(anonymousCart);
	    			System.out.println("Faccio il fill");
	    			DBManager.getInstance().fillCartFromAnonymous(customer, anonymousCart);
	    			req.getSession().removeAttribute("anonymousCart");
	    		}
	    		response = gson.toJson(customer.getCart(), new TypeToken<HashMap<Product, Long>>(){}.getType());
	    	} 
	    	else if (administrator != null) {
	    		req.getSession().setAttribute("administrator", administrator);
	    	}
	    	else {
	    		resp.sendError(401);
	    	}
	    	PrintWriter out = resp.getWriter();
	    	resp.setContentType("application/json");
	    	resp.setCharacterEncoding("UTF-8");
	    	out.print(response);
	    	out.flush();
	    }
	    else {
	    	req.getSession().removeAttribute("customer");
	    }

	}

}
