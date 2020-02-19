package controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import websocket.EndpointBroker;

public class WaitingRoom extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("waitingRoom.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Customer customer = (Customer) req.getSession().getAttribute("customer");
		System.out.println("Entra " + customer.getUsername());
		if (req.getParameter("operation").equals("add")) {
			int nQueuedCustomers = EndpointBroker.getInstance().processCustomer(customer);
			String response = "{\"nQueued\" : " + nQueuedCustomers + "}";
			System.out.println("e si prende " + response); 
			PrintWriter out = resp.getWriter();
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			out.print(response);
			out.flush();			
		}
		else {
			EndpointBroker.getInstance().cancelCustomerProcessing(customer);
		}
	}
	
}
