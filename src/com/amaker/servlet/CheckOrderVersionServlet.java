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

import com.amaker.bean.Order;
import com.amaker.dao.OrderDao;
import com.amaker.dao.impl.OrderDaoImpl;
import com.amaker.util.DBUtil;

public class CheckOrderVersionServlet extends HttpServlet {
	private OrderDao dao = new OrderDaoImpl();

	public CheckOrderVersionServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy();
	}

	/**
	 * 客户端返回数据统计
	 * exception 为服务器错误
	 * error 服务器无数据
	 *  新   改      删    错
	 * new,update,delete,error
	 * 点菜
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
        String data = request.getParameter("data").trim();
		
		int new_c = 0;
		int update_c = 0;
		int delete_c = 0;
		int error_c = 0;
		
		Connection conn = DBUtil.getConnForMySql();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder result = new StringBuilder();;
		try {
			
			String has = "select count(*) count from orders where is_delete=0";
			int n = 0;
			pstmt = DBUtil.getPreparedStatemnt(conn, has);
			rs = pstmt.executeQuery();
			if(rs.next())
				n = rs.getInt("count");
			if(0==n){
				out.print("error");
				return;
			}
			
			if("".equals(data)){ // 客户端没有菜单信息
				new_c = n;

			}else{ // 客户端数据不为空 orderid-version,orderid-version
				StringBuilder ids = new StringBuilder();
				String[] orders = data.split(",");
				Order o = new Order();
				for(int i = 0; i < orders.length; i ++){
					o.setId(orders[i].split("-")[0]);
					o.setVersion(orders[i].split("-")[1]);
					Order temp = dao.getOrderById(o.getId());
					if(null == temp){ // ID 所在的数据在服务器没有数据 设置为删除
						error_c ++;
					} else if("1".equals(temp.getIs_delete())){ // ID对应的数据已经删除
						delete_c ++;
					} else if(!o.getVersion().equals(temp.getVersion())){ // 版本不一致的数据
						update_c ++;
					}
					ids.append("'").append(o.getId()).append("'").append(",");
				}
				if(ids.length() > 0){
					new_c += dao.getNewOrder(ids.toString().substring(0, ids.length()-1));
				}
			}
			result.append(new_c).append(",").append(update_c).append(",").append(delete_c).append(",").append(error_c);
		} catch (SQLException e) {
			result.append("exception");
			e.printStackTrace();
		}
		
		if((new_c + update_c + delete_c + error_c) == 0)
			out.print("010010101101110101000100");
		else
			out.print(result.toString());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

	public void init() throws ServletException {
		
	}

}
