package com.amaker.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.bean.Order;
import com.amaker.dao.OrderDao;
import com.amaker.dao.impl.OrderDaoImpl;

public class QueryOrderServlet extends HttpServlet {
	private OrderDao dao = new OrderDaoImpl();
	public QueryOrderServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
        String orderId = request.getParameter("orderId");
		
		Order order = dao.getOrderById(orderId);
		
		request.setAttribute("order", order);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/updateOrder.jsp");
		
		dispatcher .forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
	
	public void init() throws ServletException {
	}

}
