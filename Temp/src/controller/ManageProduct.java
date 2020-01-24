package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import drive.CreateGoogleFile;
import exceptions.DBOperationException;
import model.Category;
import model.OperationResult;
import model.Product;
import model.SuperMarket;
import persistence.DBManager;

@MultipartConfig
public class ManageProduct extends HttpServlet {

	private static final String UPLOAD_DIR = "images" + File.separator + "products";

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
				operationResult.setType("Aggiungi prodotto");

				// gets absolute path of the web application
				String applicationPath = req.getServletContext().getRealPath("");
				// constructs path of the directory to save uploaded file
				String uploadFilePath = applicationPath + UPLOAD_DIR;

				// creates the save directory if it does not exists
				File fileSaveDir = new File(uploadFilePath);
				if (!fileSaveDir.exists()) {
					fileSaveDir.mkdirs();
				}
				String fileName = null;
				String imageId = null;
				// Get all the parts from request and write it to the file on server
				com.google.api.services.drive.model.File googleFile = null;
				for (Part part : req.getParts()) {
					if (part.getContentType() != null && Pattern.matches("image/.+", part.getContentType())) {
						String ext = part.getContentType().substring(6);
						fileName = req.getParameter("barcode") + "." + ext;

						googleFile = CreateGoogleFile.createGoogleFile(null, part.getContentType(), fileName,
								part.getInputStream());
						imageId = googleFile.getId();

//						imagePath = uploadFilePath + File.separator + fileName;
//						part.write(imagePath);
					}
				}
				if (imageId == null) {
					imageId = "1DbMKHR-mObaG56QAVDqGHoO4XoXStC2M";
				}

				Product product;
				long barcode = Long.parseLong(req.getParameter("barcode"));
				String name = (String) req.getParameter("name");
				String brand = (String) req.getParameter("brand");
				double weight = Double.parseDouble(req.getParameter("weight"));
				SuperMarket superMarket = DBManager.getInstance()
						.getSupermarketById(Long.parseLong(req.getParameter("superMarket")));
				Category category = DBManager.getInstance()
						.getCategoryById(Long.parseLong(req.getParameter("category")));
				double price = Double.parseDouble(req.getParameter("price"));
				double discount = Double.parseDouble(req.getParameter("discount"));
				long quantity = Integer.parseInt(req.getParameter("quantity"));
				String offbrand = (String) req.getParameter("offbrand");
				if (offbrand.equals("yes"))
					product = new Product(0, barcode, name, brand, weight, superMarket, category, true, price, quantity,
							discount, imageId, false);
				else
					product = new Product(0, barcode, name, brand, weight, superMarket, category, false, price,
							quantity, discount, imageId, false);

				DBManager.getInstance().addProduct(product);

				operationResult.setResult(true);
				operationResult.setObject(product.toString());
				operationResult.setState("Completato");
			}
				break;

			case "mod": {
				operationResult.setType("Modifica prodotto");

				// gets absolute path of the web application
				String applicationPath = req.getServletContext().getRealPath("");
				// constructs path of the directory to save uploaded file
				String uploadFilePath = applicationPath + UPLOAD_DIR;

				// creates the save directory if it does not exists
				File fileSaveDir = new File(uploadFilePath);
				if (!fileSaveDir.exists()) {
					fileSaveDir.mkdirs();
				}
				String fileName = null;
				String imageId = null;
				// Get all the parts from request and write it to the file on server
				com.google.api.services.drive.model.File googleFile = null;
				for (Part part : req.getParts()) {
					if (part.getContentType() != null && Pattern.matches("image/.+", part.getContentType())) {
						String ext = part.getContentType().substring(6);
						fileName = req.getParameter("barcode") + "." + ext;

						googleFile = CreateGoogleFile.createGoogleFile(null, part.getContentType(), fileName,
								part.getInputStream());
						imageId = googleFile.getId();

//						imagePath = uploadFilePath + File.separator + fileName;
//						part.write(imagePath);
					}
				}

				long id = Long.parseLong(req.getParameter("id"));
				Product product = DBManager.getInstance().getProductById(id);
				if (imageId == null) {
					imageId = product.getImagePath().substring(43);
				}
				long barcode = Long.parseLong(req.getParameter("barcode"));
				String name = (String) req.getParameter("name");
				String brand = (String) req.getParameter("brand");
				double weight = Double.parseDouble(req.getParameter("weight"));
				SuperMarket superMarket = DBManager.getInstance()
						.getSupermarketById(Long.parseLong(req.getParameter("superMarket")));
				Category category = DBManager.getInstance()
						.getCategoryById(Long.parseLong(req.getParameter("category")));
				double price = Double.parseDouble(req.getParameter("price"));
				double discount = Double.parseDouble(req.getParameter("discount"));
				long quantity = Integer.parseInt(req.getParameter("quantity"));
				String offbrand = (String) req.getParameter("offbrand");
				if (offbrand.equals("yes"))
					product = new Product(id, barcode, name, brand, weight, superMarket, category, true, price,
							quantity, discount, imageId, false);
				else
					product = new Product(id, barcode, name, brand, weight, superMarket, category, false, price,
							quantity, discount, imageId, false);

				DBManager.getInstance().modifyProduct(product);

				operationResult.setResult(true);
				operationResult.setObject(product.toString());
				operationResult.setState("Completato");
			}
				break;

			case "del": {
				operationResult.setType("Rimuovi prodotto");

				StringBuffer stringBuffer = new StringBuffer();
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
				String line = bufferedReader.readLine();
				while (line != null) {
					stringBuffer.append(line);
					line = bufferedReader.readLine();
				}
				long id = gson.fromJson(stringBuffer.toString(), JsonObject.class).get("product").getAsLong();

				DBManager.getInstance().removeProductById(id);

				Product product = DBManager.getInstance().getProductById(id);

				operationResult.setResult(true);
				operationResult.setObject(product.toString());
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
