package controller.administration;

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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import exceptions.DBOperationException;
import model.OperationResult;
import model.SuperMarket;
import persistence.DBManager;

public class ManageSuperMarketForm extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		OperationResult operationResult = new OperationResult();
		Gson gson = new GsonBuilder().serializeNulls().create();
		SuperMarket superMarket = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
			String line = bufferedReader.readLine();
			while (line != null) {
				stringBuffer.append(line);
				line = bufferedReader.readLine();
			}
			long id = gson.fromJson(stringBuffer.toString(), JsonObject.class).get("supermarket").getAsLong();
			superMarket = DBManager.getInstance().getSupermarketById(id);
			operationResult.setResult(true);
		} catch (DBOperationException e) {
			operationResult.setResult(false);
			operationResult.setObject(e.getMessage());
			operationResult.setState("Annullato");
		} finally {
			PrintWriter writer = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			String result = "{\"result\" : " + gson.toJson(operationResult) + ", \"supermarket\" : " + gson.toJson(superMarket)
					+ "}";
			writer.println(result);
			writer.flush();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
