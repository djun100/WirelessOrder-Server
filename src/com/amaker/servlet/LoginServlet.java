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

public class LoginServlet extends HttpServlet {
   private UserDao dao=new UserDaoImpl();

	public LoginServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	
	}

	/**
	 * 登录
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        response.setContentType("text/html");
		PrintWriter out = response.getWriter();
        String loginid = request.getParameter("loginid").trim();
		String password = request.getParameter("password").trim();
		System.out.println(loginid+"----------"+password);
		/**
		 * 判断用户是否存在
		 * */
		User u;
		boolean isExist = false;
		isExist = dao.isLoginIdExists(loginid);
		if(true==isExist){
			u= dao.getUserByIdAndPwd(loginid, password);
			System.out.println("user的loginid:"+u.getLoginid());
			out.print(user2String(u));
			System.out.println(user2String(u));
		}else{
			out.print("-1");
			System.out.println("用户登录不正确");
			out.flush();
		}
		out.flush();
		out.close();
	}
	private String user2String(User u) {
		StringBuilder s = new StringBuilder();
		s.append(u.getId()).append(",")
			.append(u.getLoginid()).append(",")
			.append(u.getPassword()).append(",")
			.append(u.getNikename()==null?"昵称":u.getNikename()).append(",")
			.append(u.getPhone()==null?"手机":u.getPhone()).append(",")
			.append(u.getEmail()).append(",")
			.append(u.getGender());
		
		return s.toString();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	
	}

	public void init() throws ServletException {
	}

}
