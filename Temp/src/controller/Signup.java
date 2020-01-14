package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import persistence.DBManager;

public class Signup extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("firstName");
		String lastName=req.getParameter("lastName");
		String email=req.getParameter("email");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		LocalDate birthDate=LocalDate.parse(req.getParameter("birthDate"));
		Customer customer=new Customer(username,password,name,lastName,email,birthDate);
		DBManager.getInstance().getCustomerDao().insert(customer);
		//req.getSession().setAttribute("customer",customer);
		//resp.sendRedirect("home");
	}

}
