package controller;

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
import com.google.gson.JsonObject;

import exceptions.DBOperationException;
import model.OperationResult;
import model.SuperMarket;
import persistence.DBManager;

public class ManageSupermarket extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		OperationResult operationResult = new OperationResult();
		Gson gson = new Gson();
		try {
			switch (req.getParameter("action")) {
			case "add": {
				operationResult.setType("Aggiungi supermercato");

				StringBuffer stringBuffer = new StringBuffer();
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
				String line = bufferedReader.readLine();
				while (line != null) {
					stringBuffer.append(line);
					line = bufferedReader.readLine();
				}
				SuperMarket superMarket = gson.fromJson(stringBuffer.toString(), SuperMarket.class);

				DBManager.getInstance().addSupermarket(superMarket);

				operationResult.setResult(true);
				operationResult.setObject(superMarket.toString());
				operationResult.setState("Completato");
			}
				break;

			case "mod": {
				operationResult.setType("Modifica supermercato");

				StringBuffer stringBuffer = new StringBuffer();
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
				String line = bufferedReader.readLine();
				while (line != null) {
					stringBuffer.append(line);
					line = bufferedReader.readLine();
				}
				SuperMarket superMarket = gson.fromJson(stringBuffer.toString(), SuperMarket.class);
				superMarket.setAffiliate(DBManager.getInstance().getSupermarketById(superMarket.getId()).isAffiliate());

				DBManager.getInstance().modifySuperMarket(superMarket);

				operationResult.setResult(true);
				operationResult.setObject(superMarket.toString());
				operationResult.setState("Completato");
			}
				break;

			case "del": {
				operationResult.setType("Rimuovi affiliazione supermercato");

				StringBuffer stringBuffer = new StringBuffer();
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
				String line = bufferedReader.readLine();
				while (line != null) {
					stringBuffer.append(line);
					line = bufferedReader.readLine();
				}
				long id = gson.fromJson(stringBuffer.toString(), JsonObject.class).get("supermarket").getAsLong();

				DBManager.getInstance().removeAffiliateSuperMarketById(id);

				SuperMarket superMarket = DBManager.getInstance().getSupermarketById(id);

				operationResult.setResult(true);
				operationResult.setObject(superMarket.toString());
				operationResult.setState("Completato");
			}
				break;

			case "aff": {
				operationResult.setType("Aggiungi affiliazione supermercato");

				StringBuffer stringBuffer = new StringBuffer();
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
				String line = bufferedReader.readLine();
				while (line != null) {
					stringBuffer.append(line);
					line = bufferedReader.readLine();
				}
				long id = gson.fromJson(stringBuffer.toString(), JsonObject.class).get("supermarket").getAsLong();

				DBManager.getInstance().addAffiliateSuperMarketById(id);

				SuperMarket superMarket = DBManager.getInstance().getSupermarketById(id);

				operationResult.setResult(true);
				operationResult.setObject(superMarket.toString());
				operationResult.setState("Completato");
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
			writer.println(gson.toJson(operationResult));
			writer.flush();
		}
	}

}
