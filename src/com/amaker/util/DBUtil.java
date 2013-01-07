package com.amaker.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	private static Connection conn;
	private static PreparedStatement pstmt;
	
	private static String driverClass = "";
	private static String driverUrl = "";
	private static String username = "";
	private static String password = "";
	
	/**
	 *���MySql���ݿ�����
	 */
	public static Connection getConnForMySql() {
		
		new DBUtil().init();
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(driverUrl, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	private void init() {
		Properties pro = new Properties();
		try {
			pro.load(this.getClass().getClassLoader().getResourceAsStream("DBConfig.properties"));
			driverClass = pro.getProperty("driver");
			driverUrl = pro.getProperty("url");
			username = pro.getProperty("username");
			password = pro.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * ��õ�ǰ���ӵ�Ԥ׼����Statement
	 * ����SQL��������[�ʺ�]
	 */
	public static PreparedStatement getPreparedStatemnt(Connection conn, String sql) {
		try {
			pstmt = conn.prepareStatement(sql);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}

	/**
	 * ��õ�ǰ���ӵ�Ԥ�����Statement
	 * ���� SQL �������Ĳ�ѯ ���ʺŵĲ�����˳��һ�δ��� ���� params
	 */
	public static PreparedStatement getPreparedStatemnt(Connection conn , String sql, String params[]) {
		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++){
				pstmt.setString(i + 1, params[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}

	/**
	 * ��õ�ǰ���ӵ�Ԥ�����Statement
	 * ���� SQL �������Ĳ�ѯ ���ʺŵĲ�����˳��һ�δ��� ���� params
	 */
	public static PreparedStatement getPreparedStatemnt(Connection conn , String sql,
			Object params[]) {
		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++){
				pstmt.setObject(i + 1, params[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}
	
	/**
	 * ���ط��� �ر� ����
	 */
	public static void CloseResources(Connection conn) {
		try {
			if (conn != null && !conn.isClosed())				
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���ط��� �ر� ��������
	 */
	public static void CloseResources(Statement stmt) {
		try {
			if (stmt != null)				
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���ط��� �ر� �����
	 */
	public static void CloseResources(ResultSet rs) {
		try {
			if (rs != null)				
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���ط��� �ر� �����,��������
	 */
	public static void CloseResources(ResultSet rs , Statement stmt) {
		CloseResources(rs);
		CloseResources(stmt);
	}

	/**
	 * ���ط������ر� ���Ӻͳ�������
	 */
	public static void CloseResources(Connection conn, Statement stmt) {
		CloseResources(stmt);
		CloseResources(conn);
	}
	
	/**
	 * ���ط������ر� ���Ӻͽ����
	 */
	public static void CloseResources(Connection conn, ResultSet rs) {
		CloseResources(rs);
		CloseResources(conn);
	}

	/**
	 * ���ط������ر� ���ӡ���������ͽ����
	 */
	public static void CloseResources(Connection conn, Statement stmt,
			ResultSet rs) {
		CloseResources(rs);
		CloseResources(conn ,stmt);
	}
}
