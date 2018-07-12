package com.ps.produce.order.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.produce.base.entity.query.model.OrderQuery;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.order.dao.OrderDao;
import com.ps.produce.order.entity.Order;


@Service
@Transactional(rollbackFor = Exception.class)
public class OrderService {
	
	@Autowired
	private OrderDao orderDao;
	    

	



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


	public PageBean<Order> find(PageBean<Order> pageBean, OrderQuery query) {
		List<Order> orders= orderDao.findList(pageBean,query);
		int totalCount = orderDao.count(pageBean,query);
		pageBean.setTotalCount(totalCount);		
		pageBean.setContent(orders);
		return pageBean;
	}

   

}
