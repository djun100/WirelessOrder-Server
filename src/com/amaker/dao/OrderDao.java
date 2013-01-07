package com.amaker.dao;

import java.util.List;

import com.amaker.bean.Order;



public interface OrderDao {

	/**
	 * 添加菜肴
	 */
	int addOrder(Order order);

	/**
     获取所有菜肴
	 * @return List<Order>
	 */
	List<Order> getAllOrder();

	/**
     删除菜肴
	 * @param id
	 */
	int deleteOrderById(String id);

	/**
	* 修改菜单
	 */
	int updateOrder(Order order);

	
	/**
 * 查询菜单
	 */
	Order getOrderById(String orderId);
	
	/**
	查询最新数据
	 */
	int getNewOrder(String substring);

}
