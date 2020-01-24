package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import exceptions.DBOperationException;
import model.Category;
import model.OperationResult;
import model.Product;
import model.SuperMarket;
import persistence.DBManager;

public class ManageProductForm extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		OperationResult operationResult = new OperationResult();
		Gson gson = new GsonBuilder().serializeNulls().create();
		ArrayList<SuperMarket> supermarkets = null;
		ArrayList<Category> categories = null;
		Product product = null;

		try {
			switch (req.getParameter("action")) {
			case "add": {
				supermarkets = DBManager.getInstance().getSuperMarkets();
				if (supermarkets.isEmpty())
					throw new DBOperationException("Non ci sono supermercati affiliati", "");
				categories = DBManager.getInstance().getLeafCategories();
				if (categories.isEmpty())
					throw new DBOperationException("Non ci sono categorie disponibili", "");
				operationResult.setResult(true);
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
				long id = gson.fromJson(stringBuffer.toString(), JsonObject.class).get("product").getAsLong();

				product = DBManager.getInstance().getProductById(id);
				
				operationResult.setResult(true);
			}
				break;
			}

		} catch (DBOperationException e) {
			operationResult.setResult(false);
			operationResult.setObject(e.getMessage());
			operationResult.setState("Annullato");
		} finally {
			PrintWriter writer = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			String result = "{\"result\" : " + gson.toJson(operationResult) + ", \"supermarkets\" : "
					+ gson.toJson(supermarkets) + ", \"categories\" : " + gson.toJson(categories) + ", \"product\" : "
					+ gson.toJson(product) + "}";
			writer.println(result);
			writer.flush();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
