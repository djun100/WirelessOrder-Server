package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.bean.User;
import com.amaker.dao.UserDao;
import com.amaker.dao.impl.UserDaoImpl;

public class RegisterServlet extends HttpServlet {
	private UserDao dao = new UserDaoImpl();
	
	public RegisterServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String loginid = request.getParameter("loginId");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		
		User u = new User();
		u.setLoginid(loginid);
		u.setPassword(password);
		u.setEmail(email);
		u.setGender(gender);
		
		if(!dao.isLoginIdExists(u.getLoginid())){
			if(!dao.isEmailExists(u.getEmail())){
				int flag = dao.addUsers(u);
				if(flag > 0)
					out.print("1");
				else
					out.print("0");
			} else{
				out.print("2");
			}
		} else {
			out.print("3");
		}

		out.flush();
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	public void init() throws ServletException {
	
	}

}
