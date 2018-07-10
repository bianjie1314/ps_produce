package com.ps.produce.order.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ps.produce.order.entity.Order;


public interface OrderDao {
    
	
	
	public long count(Map<String, String> map, String searchParameter);

	public void insert(Order Order);

	public void delete(String id);

	public void update(Order Order);

	public List<Order> findAllList();

	public List<Order> findList(String keyword, Date start, Date end, String orderUsername, Integer status);
}
