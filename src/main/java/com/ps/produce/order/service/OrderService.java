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
import com.ps.produce.support.pair.OrderStatus;


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


	public Order findOne(Long id) {
		return orderDao.findOne(id);
	}
	public List<String> queryOrderUser() {
		
		return orderDao.queryOrderUser();
	}


	public void  changOrderStatus(int confirm,String[] orderId) {
		// TODO Auto-generated method stub
           for(int i=0;i<orderId.length;i++)
           orderDao.changOrderStatus(confirm,orderId[i]);
		
	}


	public int addWaitMakeOrder(String orderNo) {
		// TODO Auto-generated method stub
		if(orderDao.findOneByOrderNo(orderNo)==0) {
			return -1;
		}
		return orderDao.addWaitMakeOrder(orderNo);
	}
   public int addWaitShippingOrder(String orderNo) {
	   if(orderDao.findOneByOrderNo(orderNo)==0) {
			return -1;
		}
		return orderDao.addWaitShippingOrder(orderNo);
   }


public int addShipInfo(Order order) {
	// TODO Auto-generated method stub
	return orderDao.addShipInfo(order);
}


public void addMakeOrder(int value, String[] orderNo, String userName, long userId) {
	// TODO Auto-generated method stub
	for(int i=0;i<orderNo.length;i++) {
	  orderDao.addMakeOrder(value,orderNo[i],userName,userId);
	  }
}


public String querProductImg(String productId) {
	// TODO Auto-generated method stub
	return orderDao.queryProductImg(productId);
}
   

}
