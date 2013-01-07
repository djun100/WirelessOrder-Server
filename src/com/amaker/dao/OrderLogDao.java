package com.amaker.dao;

import java.util.List;

import com.amaker.bean.OrderLog;


public interface OrderLogDao {
	
	/**

	 */
	List<OrderLog> getAllOrderLog();
	
	/**

	 */
	int updateOrDeleteOrderLog(String id, String opType);
	
	/**
	
	 */
	OrderLog getOrderLogById(String id);
}
