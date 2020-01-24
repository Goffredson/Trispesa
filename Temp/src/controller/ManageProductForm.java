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
		String result = null;

		try {
			switch (req.getParameter("action")) {
			case "add": {
				supermarkets = DBManager.getInstance().getAffiliateSuperMarkets();
				if (supermarkets.isEmpty())
					throw new DBOperationException("Non ci sono supermercati affiliati", "");
				categories = DBManager.getInstance().getLeafCategories();
				if (categories.isEmpty())
					throw new DBOperationException("Non ci sono categorie disponibili", "");

				operationResult.setResult(true);
				result = "{\"result\" : " + gson.toJson(operationResult) + ", \"supermarkets\" : "
						+ gson.toJson(supermarkets) + ", \"categories\" : " + gson.toJson(categories) + "}";
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
				result = "{\"result\" : " + gson.toJson(operationResult) + ", \"product\" : " + gson.toJson(product)
						+ "}";
			}
				break;

			case "prod": {
				long barcode = Long.parseLong(req.getParameter("term"));
				ArrayList<Product> products = DBManager.getInstance().getProductsContainsBarcode(barcode);

				if (products.isEmpty()) {
					result = "[]";
				} else {
					StringBuilder tempResult = new StringBuilder();
					tempResult.append("[");
					for (Product temp : products) {
						tempResult.append("{\"id\" : \"" + temp.getId() + "\", \"label\" : \"" + temp.getBarcode()
								+ ", " + temp.getName() + ", " + temp.getBrand() + ", " + temp.getCategory()
								+ "\", \"value\" : \"" + temp.getBarcode() + ", " + temp.getName() + ", "
								+ temp.getBrand() + ", " + temp.getCategory() + "\"},");
					}
					tempResult.deleteCharAt(tempResult.length() - 1);
					tempResult.append("]");
					result = tempResult.toString();
				}
			}
				break;

			case "fill": {
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

				supermarkets = DBManager.getInstance().getAffiliateSuperMarketsDontSellProduct(product.getBarcode());
				if (supermarkets.isEmpty())
					throw new DBOperationException("Non ci sono supermercati affiliati", "");

				operationResult.setResult(true);
				result = "{\"result\" : " + gson.toJson(operationResult) + ", \"supermarkets\" : "
						+ gson.toJson(supermarkets) + ", \"product\" : " + gson.toJson(product) + "}";
			}
				break;

			case "eq": {
				long barcode = Long.parseLong(req.getParameter("term"));
				ArrayList<Product> products = DBManager.getInstance().getProductsByBarcode(barcode);

				if (products.isEmpty()) {
					result = "[]";
				} else {
					StringBuilder tempResult = new StringBuilder();
					tempResult.append("[");
					for (Product temp : products) {
						tempResult.append("{\"id\" : \"" + temp.getId() + "\", \"label\" : \"" + temp.getBarcode()
								+ ", " + temp.getName() + ", " + temp.getBrand() + ", " + temp.getCategory()
								+ "\", \"value\" : \"" + temp.getBarcode() + ", " + temp.getName() + ", "
								+ temp.getBrand() + ", " + temp.getCategory() + "\"},");
					}
					tempResult.deleteCharAt(tempResult.length() - 1);
					tempResult.append("]");
					result = tempResult.toString();
				}
			}
				break;
			}

		} catch (DBOperationException e) {
			operationResult.setResult(false);
			operationResult.setObject(e.getMessage());
			operationResult.setState("Annullato");
			result = "{\"result\" : " + gson.toJson(operationResult) + "}";
		} finally {
			PrintWriter writer = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			writer.println(result);
			writer.flush();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
