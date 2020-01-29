package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

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
		Type tripleType = new TypeToken<ArrayList<ArrayList<String>>>() {
		}.getType();
		ArrayList<Product> spesa = new ArrayList<Product>();
		ArrayList<ArrayList<String>> productsInDiet = gson.fromJson(jsonReceived.toString(), tripleType);
		System.out.println(productsInDiet);
		for (ArrayList<String> p : productsInDiet) {
			ArrayList<Product> productByLeaf = DBManager.getInstance().getProductsForDiet(p.get(0),
					Boolean.parseBoolean(p.get(2)), Long.parseLong(p.get(1)));
			if (productByLeaf.isEmpty() == false)
				spesa.add(getCheapestProduct(productByLeaf));
			else {
				resp.setStatus(401);
				break;
			}
		}
		if (resp.getStatus() != 401) {
			String spesaJson = gson.toJson(spesa);
			System.out.println(spesaJson);
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(spesaJson);
			out.flush();
			
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("customer") == null) {
			resp.sendRedirect("home");
			return;			
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("diet.jsp");
		req.setAttribute("leafCategoriesList", DBManager.getInstance().getLeafCategoriesForDiet());
		rd.forward(req, resp);
	}

	public Product getCheapestProduct(ArrayList<Product> prodotti) {
		double minPrice = Integer.MAX_VALUE;
		Product cheapest = null;
		for (Product p : prodotti) {
			if (p.getPrice() < minPrice) {
				minPrice = p.getPrice();
				cheapest = p;
			}
		}
		return cheapest;
	}

}
