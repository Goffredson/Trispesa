package controller;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import exceptions.DBOperationException;
import model.Category;
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
		RequestDispatcher rd;
		boolean result = true;

		try {
			switch (req.getParameter("action")) {
			case "add": {
				// gets absolute path of the web application
				String applicationPath = req.getServletContext().getRealPath("");
				// constructs path of the directory to save uploaded file
				String uploadFilePath = applicationPath + UPLOAD_DIR;

				// creates the save directory if it does not exists
				File fileSaveDir = new File(uploadFilePath);
				if (!fileSaveDir.exists()) {
					System.out.println("ciao");
					fileSaveDir.mkdirs();
				}
				System.out.println("Upload File Directory=" + fileSaveDir.getAbsolutePath());
				String fileName = null;
				String imagePath = null;
				// Get all the parts from request and write it to the file on server
				for (Part part : req.getParts()) {
					if (part.getContentType() != null && Pattern.matches("image/.+", part.getContentType())) {
						String ext = part.getContentType().substring(6);
						fileName = req.getParameter("barcode") + "." + ext;
						imagePath = uploadFilePath + File.separator + fileName;
						part.write(imagePath);
						System.out.println(imagePath);
					}
				}
				if (fileName == null) {
					fileName = "imageNotFound.png";
					imagePath = uploadFilePath + File.separator + fileName;
					System.out.println(imagePath);
				}

				req.setAttribute("op", "Aggiungi prodotto");
				Product product;
				int barcode = Integer.parseInt(req.getParameter("barcode"));
				String name = (String) req.getParameter("name");
				double weight = Double.parseDouble(req.getParameter("weight"));
				double price = Double.parseDouble(req.getParameter("price"));
				int quantity = Integer.parseInt(req.getParameter("quantity"));
				String superMarketString = (String) req.getParameter("superMarket");
				SuperMarket superMarket = DBManager.getIstance().getSuperMarketByID(superMarketString);
				String categoryString = (String) req.getParameter("category");
				Category category = DBManager.getIstance().getCategoryByFamilyName(categoryString);
				String offbrand = (String) req.getParameter("offbrand");
				if (offbrand.equals("yes"))
					product = new Product(barcode, name, price, weight, superMarket, true, category, quantity,
							fileName);
				else
					product = new Product(barcode, name, price, weight, superMarket, false, category, quantity,
							fileName);

				DBManager.getIstance().addProduct(product);

				req.setAttribute("result", result);
				req.setAttribute("object", product.toString());

				rd = req.getRequestDispatcher("../operationResult.jsp");
				rd.forward(req, resp);
			}
				break;

			case "mod": {
				// gets absolute path of the web application
				String applicationPath = req.getServletContext().getRealPath("");
				// constructs path of the directory to save uploaded file
				String uploadFilePath = applicationPath + UPLOAD_DIR;

				// creates the save directory if it does not exists
				File fileSaveDir = new File(uploadFilePath);
				if (!fileSaveDir.exists()) {
					System.out.println("ciao");
					fileSaveDir.mkdirs();
				}
				System.out.println("Upload File Directory=" + fileSaveDir.getAbsolutePath());
				String fileName = null;
				String imagePath = null;
				// Get all the parts from request and write it to the file on server
				for (Part part : req.getParts()) {
					if (part.getContentType() != null && Pattern.matches("image/.+", part.getContentType())) {
						String ext = part.getContentType().substring(6);
						fileName = req.getParameter("barcode") + "." + ext;
						imagePath = uploadFilePath + File.separator + fileName;
						part.write(imagePath);
						System.out.println(imagePath);
					}
				}
				int barcode = Integer.parseInt(req.getParameter("barcode"));
				String superMarketString = (String) req.getParameter("superMarket");
				SuperMarket superMarket = DBManager.getIstance().getSuperMarketByID(superMarketString);
				Product oldProduct = DBManager.getIstance().getProductByID(barcode, superMarket);
				if (fileName == null) {
					fileName = oldProduct.getImagePath().substring(16);
				}

				req.setAttribute("op", "Modifica prodotto");
				Product product;
				
				String name = (String) req.getParameter("name");
				double weight = Double.parseDouble(req.getParameter("weight"));
				double price = Double.parseDouble(req.getParameter("price"));
				int quantity = Integer.parseInt(req.getParameter("quantity"));
				String categoryString = (String) req.getParameter("category");
				Category category = DBManager.getIstance().getCategoryByFamilyName(categoryString);
				String offbrand = (String) req.getParameter("offbrand");
				if (offbrand.equals("yes"))
					product = new Product(barcode, name, price, weight, superMarket, true, category, quantity, fileName);
				else
					product = new Product(barcode, name, price, weight, superMarket, false, category, quantity, fileName);

				DBManager.getIstance().modifyProduct(product);

				req.setAttribute("result", result);
				req.setAttribute("object", product.toString());

				rd = req.getRequestDispatcher("../operationResult.jsp");
				rd.forward(req, resp);
			}
				break;

			case "del": {
				req.setAttribute("op", "Elimina prodotto");
				int barcode = Integer.parseInt(req.getParameter("barcode"));
				String superMarketString = (String) req.getParameter("superMarket");
				SuperMarket superMarket = DBManager.getIstance().getSuperMarketByID(superMarketString);
				Product product = DBManager.getIstance().getProductByID(barcode, superMarket);

				DBManager.getIstance().removeProductByID(barcode, superMarket);

				req.setAttribute("result", result);
				req.setAttribute("object", product.toString());

				rd = req.getRequestDispatcher("../operationResult.jsp");
				rd.forward(req, resp);
			}
				break;

			}
		} catch (DBOperationException e) {
			result = false;
			req.setAttribute("result", result);
			req.setAttribute("exception", e);
			rd = req.getRequestDispatcher("../operationResult.jsp");
			rd.forward(req, resp);
		}
	}

}
