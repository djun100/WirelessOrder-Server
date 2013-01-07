package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.UserDao;
import com.amaker.dao.impl.UserDaoImpl;

public class UserModifyPasswordServlet extends HttpServlet {
	private UserDao dao = new UserDaoImpl();

	public UserModifyPasswordServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		/**
		 * res 结果判断
		 * -1 修改错误
		 * 0 修改成功
		 * 1 原密码错误
		 */
		String loginid = request.getParameter("loginid");
		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd");
		String res = "";
		
		if(dao.isOldPasswordError(loginid, oldpwd))
			res = "1";
		else if(dao.modifyUserPassword(loginid, newpwd) != -1)
			res = "0";
		else 
			res = "-1";
		
		out.print(res);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	
	public void init() throws ServletException {
	
	}

}
