package controller.administration;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import drive.GoogleDriveUtils;

public class AdminDashboard extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("administrator") == null) {
			resp.sendError(401);
			return;
		}
		
		GoogleDriveUtils.setContextPath(getServletContext().getRealPath("/"));

		RequestDispatcher rd = req.getRequestDispatcher("administration/dashboard.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
