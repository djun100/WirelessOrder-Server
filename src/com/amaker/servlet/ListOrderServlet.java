package com.amaker.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.bean.Order;
import com.amaker.dao.OrderDao;
import com.amaker.dao.impl.OrderDaoImpl;

public class ListOrderServlet extends HttpServlet {
	private OrderDao dao = new OrderDaoImpl();

	public ListOrderServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	/**
	 * 所有菜列表
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		List<Order> list = dao.getAllOrder();
		
		request.setAttribute("list", list);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/listOrder.jsp");
		
		dispatcher .forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	public void init() throws ServletException {
	
	}

}
