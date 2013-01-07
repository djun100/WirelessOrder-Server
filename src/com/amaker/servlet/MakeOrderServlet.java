package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.util.DBUtil;

public class MakeOrderServlet extends HttpServlet {



	public MakeOrderServlet() {
		super();
	}


	public void destroy() {
		super.destroy(); 
	}

	/**
	 * 点菜成功返回 0
	 * 点菜失败返回 -1
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String orderId = request.getParameter("oid");
		String deskId = request.getParameter("did");
		String loginId = request.getParameter("uid");
		
		Connection conn = DBUtil.getConnForMySql();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = -1;
		
		try {
			String sqlDistinct = "select count(*) count from order_log where order_id=? and login_id=? and (op_type=0 or op_type=1)";
			pstmt = DBUtil.getPreparedStatemnt(conn, sqlDistinct, new String[]{orderId,loginId});
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt("count");
			}
		} catch (SQLException e1) {			
			e1.printStackTrace();
		} finally {
			DBUtil.CloseResources(rs, pstmt);
		}
		
		if(count>0){
			out.println(count);
			return;
		}

		String sql = "insert into order_log(order_id,desk_id,login_id)values(?,?,?)";
		
		pstmt = DBUtil.getPreparedStatemnt(conn, sql, new String[]{orderId,deskId,loginId});
		count = -1;
		try {
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			count = -1;
			e.printStackTrace();
		}

		if(count==-1)
			out.print(count);
		else 
			out.print(0);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void init() throws ServletException {
	}

}
