package com.amaker.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.bean.OrderLog;
import com.amaker.dao.OrderLogDao;
import com.amaker.dao.impl.OrderLogDaoImpl;

public class OrderLogUpdateServlet extends HttpServlet {
	private OrderLogDao dao = new OrderLogDaoImpl();

	public OrderLogUpdateServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String id = request.getParameter("log");
		String type = request.getParameter("op");
		dao.updateOrDeleteOrderLog(id, type);
		
		List<OrderLog> list = dao.getAllOrderLog();
		request.setAttribute("orderLogList", list);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/orderLogList.jsp");		
		dispatcher .forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	
	public void init() throws ServletException {
	}

}
