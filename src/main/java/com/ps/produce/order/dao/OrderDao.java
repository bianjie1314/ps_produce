package com.ps.produce.order.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ps.produce.base.entity.query.model.OrderQuery;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.order.entity.Order;
import com.ps.produce.order.entity.OrderLog;
import com.ps.produce.order.entity.Product;


public interface OrderDao {
    
	
	

	public void insert(Order Order);
	public void insertProduct(@Param("products")List<Product> products,@Param("orderId")long orderId);

	
	public void delete(String id);

	public void update(Order Order);
    public List<Order> findList(@Param("pageBean") PageBean<Order> pageBean, @Param("query") OrderQuery query);

	public int count(@Param("pageBean") PageBean<Order> pageBean,@Param("query")  OrderQuery query);

	public Order findOne(@Param("id") Long id);
	
	public List<String> queryOrderUser();

	public int changOrderStatus(@Param("status")int confirm, @Param("orderId")String orderId);

	public int addWaitMakeOrder(@Param("orderNo")String orderNo, @Param("date")Date date);
	
	public int findOneByOrderNo(@Param("orderNo")String orderNo);

	public int addWaitShippingOrder(@Param("orderNo")String orderNo);

	public int addShipInfo(@Param("order")Order order);

	public void addMakeOrder(@Param("status")int value ,@Param("orderNo")String orderNo, @Param("userName")String userName, @Param("userId")long userId,@Param("date")Date date);

	public String queryProductImg(@Param("productId")String productId);

	public int cancle(@Param("order") Order order);
	
	public void addLog(@Param("orderLog")OrderLog orderLog);
	public Long findOrderIdbyOrderNo(@Param("orderNo")String orderNo);

	public List<OrderLog> findOrderLogByOrderId(@Param("orderId")Long orderId);
	public List<Order> findOrderByOrderIds(@Param("orderIds") String orderIds);
	public int findStatusByOrderId(@Param("orderId")Long orderId);
	public void addPrintOrder(@Param("status")int value,@Param("orderId") String orderId, @Param("date")Date string);
	public void addShippingOrder(@Param("status")int value, @Param("orderId")String string, @Param("date")Date format);
	
}
