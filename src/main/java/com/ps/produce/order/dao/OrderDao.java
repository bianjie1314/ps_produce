package com.ps.produce.order.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ps.produce.base.entity.query.model.OrderQuery;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.order.entity.Order;


public interface OrderDao {
    
	
	

	public void insert(Order Order);

	public void delete(String id);

	public void update(Order Order);



	public List<Order> findList(@Param("pageBean") PageBean<Order> pageBean, @Param("query") OrderQuery query);

	public int count(@Param("pageBean") PageBean<Order> pageBean,@Param("query")  OrderQuery query);

	public Order findOne(@Param("id") Long id);
	
	public List<String> queryOrderUser();
}
