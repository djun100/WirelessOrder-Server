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

public class ClientUpdateOrderServlet extends HttpServlet {
	private OrderDao dao = new OrderDaoImpl();
	
	public ClientUpdateOrderServlet() {
		super();
	}


	public void destroy() {
		super.destroy(); 
	}

	/**
	 * �ͻ��˷�������ͳ��
	 * error Ϊ����������
	 * ������Ӧ���������
	 * new update delete error
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// id-version,id-version
		String data = request.getParameter("data").trim();		
		// new update delete error
		String type = request.getParameter("type").trim();
		
//		System.out.println("data: " + data);
//		System.out.println("type: " + type);
		
		Connection conn = DBUtil.getConnForMySql();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder result = new StringBuilder();
		String sql = "";
		
		/**
		 *  new ��dataΪ "" ���򷵻ص�ǰ�������˵�����û��ɾ������Ϣ�����data��Ϊ�գ���������������
		 *  
		 *  update �ȶԿͻ��˵�ID�Ͱ汾�����������û��ɾ�������Ұ汾��Լ�ͻ��˰汾�����ظ��µĲ˵���Ϣ
		 *  
		 *  delete �ȶԿͻ��˵�ID�����������ɾ�����򷵻���Ӧ��ID
		 *  
		 *  error �ȶԿͻ���ID����������ڣ��򷵻�ID���ڿͻ��˽���ɾ�� ��������̴��������ɳ��ִ������ݡ�		 *  
		 */
		if("new".equals(type)){
			if("".equals(data)){ // �ͻ���������
				try {
					sql = "select * from orders where is_delete=0";
					conn = DBUtil.getConnForMySql();
					pstmt = DBUtil.getPreparedStatemnt(conn, sql);
					rs = pstmt.executeQuery();
					while(rs.next()){
						result.append(rs.getString("id")).append("@");
						result.append(rs.getString("name")).append("@");
						result.append(rs.getString("description")).append("@");
						result.append(rs.getString("type")).append("@");
						result.append(rs.getString("order_version")).append("@");
						result.append(rs.getString("image_path")).append("@");
						result.append(rs.getString("price")).append("#");
					}
					if(result.length()>0)
						out.print(result.substring(0, result.length()-1));
					else
						out.print("");
					return;
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DBUtil.CloseResources(conn, pstmt, rs);
				}
				
			}else{// �ͻ���������				
				String datas[] = data.split(",");
				StringBuilder ids = new StringBuilder();
				for(int i = 0; i < datas.length; i ++){
					ids.append("'").append(datas[i].split("-")[0]).append("'").append(",");
				}
				try {
					sql = "select * from orders where is_delete=0 and id not in(" + ids.substring(0, ids.length()-1) + ")";
					System.out.println(sql);
					conn = DBUtil.getConnForMySql();
					pstmt = DBUtil.getPreparedStatemnt(conn, sql);
					rs = pstmt.executeQuery();
					while(rs.next()){
						result.append(rs.getString("id")).append("@");
						result.append(rs.getString("name")).append("@");
						result.append(rs.getString("description")).append("@");
						result.append(rs.getString("type")).append(",");
						result.append(rs.getString("order_version")).append("@");
						result.append(rs.getString("image_path")).append("@");
						result.append(rs.getString("price")).append("#");
					}
					if(result.length()>0)
						out.print(result.substring(0, result.length()-1));
					else
						out.print("");
					return;
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DBUtil.CloseResources(conn, pstmt, rs);
				}				
			}
		} else if ("update".equals(type)){
			String datas[] = data.split(",");
			String id = "";
			String version = "";
			for(int i = 0; i < datas.length; i ++){
				id = datas[i].split("-")[0];
				version = datas[i].split("-")[1];
				Order o = dao.getOrderById(id);
				if(!o.getVersion().equals(version)&&"0".equals(o.getIs_delete())){
					result.append(o.getId()).append("@");
					result.append(o.getName()).append("@");
					result.append(o.getDescription()).append("@");
					result.append(o.getType()).append("@");
					result.append(o.getVersion()).append("@");
					result.append(o.getImgage_path()).append("@");
					result.append(o.getPrice()).append("#");
				}
			}
			if(result.length()>0)
				out.print(result.substring(0, result.length()-1));
			else
				out.print("");
			return;
		} else if ("delete".equals(type)){
			String datas[] = data.split(",");
			String id = "";
			for(int i = 0; i < datas.length; i ++){
				id = datas[i].split("-")[0];
				Order o = dao.getOrderById(id);
				if("1".equals(o.getIs_delete())){
					result.append(o.getId()).append(",");
				}
			}
			if(result.length()>0)
				out.print(result.substring(0, result.length()-1));
			else
				out.print("");
		} else if("error".equals(type)){
			String datas[] = data.split(",");
			String id = "";
			for(int i = 0; i < datas.length; i ++){
				id = datas[i].split("-")[0];
				Order o = dao.getOrderById(id);
				if(null == o)
					result.append(id).append(",");
			}
			if(result.length()>0)
				out.print(result.substring(0, result.length()-1));
			else
				out.print("");
		} else {
			result.append("error");
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
              doGet(request, response);
	}

	public void init() throws ServletException {
		
	}

}
