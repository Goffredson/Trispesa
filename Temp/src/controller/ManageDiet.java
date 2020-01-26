package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Product;
import persistence.DBManager;

public class ManageDiet extends HttpServlet {
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
		Type hashMapType = new TypeToken<HashMap<Long, Long>>() {
		}.getType();
		ArrayList<Product> spesa=new ArrayList<Product>();
		HashMap<Long, Long> productsInDiet = gson.fromJson(jsonReceived.toString(), hashMapType);
		for (Map.Entry<Long, Long> p : productsInDiet.entrySet()) {
			ArrayList<Product> productByLeaf=DBManager.getInstance().getProductsByCategoryAndWeight(p.getKey(),p.getValue());
			spesa.add(getCheapestProduct(productByLeaf));
		}
		String spesaJson=gson.toJson(spesa);
		PrintWriter out = resp.getWriter();
    	resp.setContentType("application/json");
    	resp.setCharacterEncoding("UTF-8");
    	out.print(spesaJson);
    	out.flush();
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd=req.getRequestDispatcher("diet.jsp");
		req.setAttribute("leafCategoriesList",DBManager.getInstance().getLeafCategoriesForDiet());
		rd.forward(req, resp);
	}
	
	public Product getCheapestProduct(ArrayList<Product> prodotti) {
		double minPrice=10000;
		Product cheapest=null;
		for(Product p: prodotti) {
			if(p.getPrice()<minPrice) {
				minPrice=p.getPrice();
				cheapest=p;
			}
		}
		return cheapest;
	}

}
