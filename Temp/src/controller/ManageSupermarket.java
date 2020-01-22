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
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
				String line = bufferedReader.readLine();
				while (line != null) {
					System.out.println(line);
					stringBuffer.append(line);
					line = bufferedReader.readLine();
				}
				SuperMarket superMarket = gson.fromJson(stringBuffer.toString(), SuperMarket.class);

				DBManager.getInstance().addSupermarket(superMarket);

				operationResult.setResult(true);
				operationResult.setObject(superMarket.toString());
				operationResult.setState("Completato");

				PrintWriter writer = resp.getWriter();
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				writer.println(gson.toJson(operationResult));
				writer.flush();
			}
				break;

			case "mod": {
				req.getSession().setAttribute("op", "Modifica supermercato");

				SuperMarket superMarket;
				long id = Long.parseLong(req.getParameter("old"));
				String name = (String) req.getParameter("name");
				String country = (String) req.getParameter("country");
				String city = (String) req.getParameter("city");
				String address = (String) req.getParameter("address");
				double latitude = Double.parseDouble(req.getParameter("latitude"));
				double longitude = Double.parseDouble(req.getParameter("longitude"));
				String affiliate = (String) req.getParameter("affiliate");
				if (affiliate.equals("yes"))
					superMarket = new SuperMarket(id, name, country, city, address, latitude, longitude, true);
				else
					superMarket = new SuperMarket(id, name, country, city, address, latitude, longitude, false);

				DBManager.getInstance().modifySuperMarket(superMarket);

				req.getSession().setAttribute("result", true);
				req.getSession().setAttribute("object", superMarket.toString());
				resp.sendRedirect("../supermarket");
			}
				break;

			case "del": {
				req.getSession().setAttribute("op", "Rimuovi affiliazione supermercato");
				long id = Long.parseLong(req.getParameter("id"));

				DBManager.getInstance().removeAffiliateSuperMarketById(id);

				req.getSession().setAttribute("result", true);
				req.getSession().setAttribute("object", DBManager.getInstance().getSupermarketById(id).toString());
				resp.sendRedirect("../supermarket");
			}
				break;

			case "aff": {
				req.getSession().setAttribute("op", "Aggiungi affiliazione supermercato");
				long id = Long.parseLong(req.getParameter("id"));

				DBManager.getInstance().addAffiliateSuperMarketById(id);

				req.getSession().setAttribute("result", true);
				req.getSession().setAttribute("object", DBManager.getInstance().getSupermarketById(id).toString());
				resp.sendRedirect("../supermarket");
			}
				break;
			}
		} catch (DBOperationException e) {
			operationResult.setResult(false);
			operationResult.setObject(e.getMessage());
			operationResult.setState("Annullato");

			PrintWriter writer = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			writer.println(gson.toJson(operationResult));
			writer.flush();
		}
	}

}
