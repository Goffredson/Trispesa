package controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.DBManager;

public class Home extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.setAttribute("listaCategorieFoglia", DBManager.getInstance().getLeafCategories());
		req.setAttribute("listaMacroCategorie", DBManager.getInstance().getMacroCategories());
		req.setAttribute("prodottiScontati", DBManager.getInstance().getDiscountedProducts());
		

		RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
