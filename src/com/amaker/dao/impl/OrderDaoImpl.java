package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amaker.bean.Order;
import com.amaker.dao.OrderDao;
import com.amaker.util.DBUtil;

public class OrderDaoImpl implements OrderDao {

	/**
	 * 添加菜肴
	 */
	public int addOrder(Order order) {
		int flag = 0;
		String sql = "insert into orders(name,description,type,image_path,price)values(?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql, new String[]{order.getName(), order.getDescription(), order.getType(), order.getImgage_path(), order.getPrice()});
			flag = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			flag = 0;
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt);
		}
		
		return flag;
	}

	/**
	 * 获得所有菜肴
	 */
	public List<Order> getAllOrder() {
		List<Order> list = new ArrayList<Order>();
		String sql = "select * from orders where is_delete=0 order by create_at desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Order o = new Order();
				o.setId(rs.getString("id"));
				o.setName(rs.getString("name"));
				o.setDescription(rs.getString("description"));
				o.setType(rs.getString("type"));
				o.setPrice(rs.getString("price"));
				o.setImgage_path(rs.getString("image_path"));
				o.setUpdate_at(rs.getString("update_at"));
				o.setCreate_at(rs.getString("create_at"));
				list.add(o);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt, rs);
		}
		
		return list;
	}

	/**
	 * 删除菜肴
	 */
	public int deleteOrderById(String id) {
		int flag = 0;
		String sql = "update orders set is_delete=1 where id=" + id;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql);
			flag = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			flag = 0;
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt);
		}
		
		return flag;
	}

	/**
	 *更新菜肴
	 */
	public int updateOrder(Order order) {
		int flag = 0;
		String sql = "update orders set name=?,description=?,type=?,image_path=?,price=?,order_version=order_version+1,update_at=NOW() where id=?";
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnForMySql();

			pstmt = DBUtil.getPreparedStatemnt(conn, sql, new String[]{order.getName(),order.getDescription(),order.getType(),order.getImgage_path(),order.getPrice(),order.getId()});
			flag = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(order.getName() + " ");
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt);
		}
		
		return flag;
	}

	/**
	 *获得菜
	 */
	public Order getOrderById(String orderId) {
		Order order = null;
		String sql = "select * from orders where is_delete=0 and id="+ orderId +" order by create_at desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				order = new Order();
				order.setId(rs.getString("id"));
				order.setName(rs.getString("name"));
				order.setDescription(rs.getString("description"));
				order.setType(rs.getString("type"));
				order.setPrice(rs.getString("price"));
				order.setIs_delete(rs.getString("is_delete"));
				order.setImgage_path(rs.getString("image_path"));
				order.setVersion(rs.getString("order_version"));
				order.setUpdate_at(rs.getString("update_at"));
				order.setCreate_at(rs.getString("create_at"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt, rs);
		}
		
		return order;
	}
	
	/**
	 *获得新的菜单
	 */
	public int getNewOrder(String substring) {
		int count = 0;
		String sql = "select count(*) count from orders where is_delete=0 and id not in(" + substring + ")";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt("count");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt, rs);
		}
		return count;
	}

}
