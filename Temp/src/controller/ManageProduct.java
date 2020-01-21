package controller;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import drive.CreateGoogleFile;
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
		try {
			switch (req.getParameter("action")) {
			case "add": {
				req.getSession().setAttribute("op", "Aggiungi prodotto");

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

				req.getSession().setAttribute("result", true);
				req.getSession().setAttribute("object", product.toString());
				resp.sendRedirect("../product");
			}
				break;

			case "mod": {
				req.getSession().setAttribute("op", "Modifica prodotto");

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

				long id = Long.parseLong(req.getParameter("old"));
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

				req.getSession().setAttribute("result", true);
				req.getSession().setAttribute("object", product.toString());
				resp.sendRedirect("../product");
			}
				break;

			case "del": {
				req.getSession().setAttribute("op", "Elimina prodotto");

				long id = Long.parseLong(req.getParameter("id"));

				DBManager.getInstance().removeProductById(id);

				req.getSession().setAttribute("result", true);
				req.getSession().setAttribute("object", DBManager.getInstance().getProductById(id).toString());
				resp.sendRedirect("../product");
			}
				break;
			}
		} catch (DBOperationException e) {
			req.getSession().setAttribute("result", false);
			req.getSession().setAttribute("exception", e);
			resp.sendRedirect("../product");
		}
	}

}
