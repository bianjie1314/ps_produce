package com.ps.produce.order.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.produce.order.dao.OrderDao;
import com.ps.produce.order.entity.Order;


@Service
@Transactional(rollbackFor = Exception.class)
public class OrderService {
	
	@Autowired
	private OrderDao orderDao;
	    

	


	public long count(Map<String, String> parameters, String searchParameter) {
		return orderDao.count(parameters,searchParameter);
	}


	public void insert(Order order) {
		order.preInsert();
		orderDao.insert(order);
		
	}


	public void update(Order order) {
		order.setUpdateDate(new Date(System.currentTimeMillis()));
		this.orderDao.update(order);
		
	}


	public void delete(String ids) {
		for(String id:ids.split(",")) {
			orderDao.delete(id);

		}
		
	}


	public List<Order> find(String keyword, String start, String end, String orderUsername, Integer status,Integer pageNumber) {
		return orderDao.findList(keyword, start, end, orderUsername, status,pageNumber);
	}

   

}
