package com.amaker.dao;

import java.util.List;

import com.amaker.bean.Order;



public interface OrderDao {

	/**
	 * ��Ӳ���
	 */
	int addOrder(Order order);

	/**
     ��ȡ���в���
	 * @return List<Order>
	 */
	List<Order> getAllOrder();

	/**
     ɾ������
	 * @param id
	 */
	int deleteOrderById(String id);

	/**
	* �޸Ĳ˵�
	 */
	int updateOrder(Order order);

	
	/**
 * ��ѯ�˵�
	 */
	Order getOrderById(String orderId);
	
	/**
	��ѯ��������
	 */
	int getNewOrder(String substring);

}
