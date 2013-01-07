package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amaker.bean.OrderLog;
import com.amaker.dao.OrderLogDao;
import com.amaker.util.DBUtil;

public class OrderLogDaoImpl implements OrderLogDao {
	/**
	 * 获取点菜日志列表
	 */
	public List<OrderLog> getAllOrderLog() {
		List<OrderLog> list = new ArrayList<OrderLog>();
		String sql = "select log.id,log.order_id,log.desk_id,log.login_id,log.op_type," +
				"log.create_at,o.name from order_log log,orders o where log.order_id=o.id and " +
				"(log.op_type=0 or log.op_type=1) order by log.op_type,log.create_at desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql);
			rs = pstmt.executeQuery();

			while(rs.next()){
				OrderLog o = new OrderLog();
				o.setId(rs.getString("log.id"));
				o.setOrderId(rs.getString("log.order_id"));
				o.setOrderName(rs.getString("o.name"));
				o.setDeskId(rs.getString("log.desk_id"));
				o.setLoginId(rs.getString("log.login_id"));
				o.setOpType(rs.getString("log.op_type"));
				o.setCreate_at(rs.getString("log.create_at"));
				String t = rs.getString("log.create_at");
				o.setCreate_at(t.substring(0, t.lastIndexOf(".")));
				list.add(o);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt, rs);
		}
		
		return list;
	}

	public OrderLog getOrderLogById(String id) {
		OrderLog ol = null;
		String sql = "select log.id,log.order_id,log.desk_id,log.login_id,log.op_type," +
			"log.create_at,o.name from order_log log,orders o where log.order_id=o.id and " +
			"(log.op_type=0 or log.op_type=1) where log.id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnForMySql();
			pstmt = DBUtil.getPreparedStatemnt(conn, sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);
			while(rs.next()){
				ol = new OrderLog();
				ol.setId(rs.getString("log.id"));
				ol.setOrderId(rs.getString("log.order_id"));
				ol.setOrderName(rs.getString("o.name"));
				ol.setDeskId(rs.getString("log.desk_id"));
				ol.setLoginId(rs.getString("log.login_id"));
				ol.setOpType(rs.getString("log.op_type"));
				String t = rs.getString("log.create_at");
				ol.setCreate_at(t.substring(0, t.lastIndexOf(".")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt, rs);
		}
		
		return ol;
	}
	/**
	 * 修改/删除 点菜日志
	 */
	public int updateOrDeleteOrderLog(String id, String opType) {
		int flag = 0;
		String sql = "update order_log set op_type=? where id=?";
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnForMySql();

			pstmt = DBUtil.getPreparedStatemnt(conn, sql, new String[]{opType, id});

			flag = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.CloseResources(conn, pstmt);
		}
		
		return flag;
	}

}
