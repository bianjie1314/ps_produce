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
import com.ps.produce.order.entity.OrderLog;
import com.ps.produce.support.Response;
import com.ps.produce.support.ResponseCode;
import com.ps.produce.support.pair.OrderStatus;
import com.ps.produce.support.utils.StateUtils;


@Service
@Transactional(rollbackFor = Exception.class)
public class OrderService {
	
	@Autowired
	private OrderDao orderDao;
	    

	



	public void insert(Order order) {
		order.setStatus(OrderStatus.waitConfirm.getValue());
		orderDao.insert(order);
		this.orderDao.insertProduct(order.getProducts(), order.getId());
		
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
	Order order1=orderDao.findOne(order.getId());
    Response response=StateUtils.changState(order1.getOrderNo(), "3", order1.getExpressNo(),order1.getExpressName());
	if(response.getRet()==ResponseCode.SUCCESS.value()) {
		orderDao.addShipInfo(order);
      }
	return response.getRet();
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


	public int cancle(Order order) {
		return orderDao.cancle(order);
		
	}
public int canalOrder(int value, String[] orderNo) {
	// TODO Auto-generated method stub
	int ret=ResponseCode.SUCCESS.value();
	for(int i=0;i<orderNo.length;i++) {
		Order order=orderDao.findOne(Long.parseLong(orderNo[i]));
		Response response=StateUtils.changState(order.getOrderNo(), "4", "","");
		if(response.getRet()==ResponseCode.SUCCESS.value()) {
			orderDao.changOrderStatus(OrderStatus.cancel.getValue(), orderNo[i]);
			
	      }
		if(response.getRet()==ResponseCode.ERROR.value()) {
			ret=ResponseCode.ERROR.value();
		}
		
	}
	return ret;
}


public int confirmOrder(String[] orderNo) {
	// TODO Auto-generated method stub
	int ret=ResponseCode.SUCCESS.value();
	for(int i=0;i<orderNo.length;i++) {
		Order order=orderDao.findOne(Long.parseLong(orderNo[i]));
		Response response=StateUtils.changState(order.getOrderNo(), "2", "","");
		if(response.getRet()==ResponseCode.SUCCESS.value()) {
			orderDao.changOrderStatus(OrderStatus.confirm.getValue(), orderNo[i]);
		   }
		if(response.getRet()==ResponseCode.ERROR.value()) {
			ret=ResponseCode.ERROR.value();
		}
		
	}
	return ret;
}


public int addShipOrder(String[] orderId) {
	// TODO Auto-generated method stub
	int ret=ResponseCode.SUCCESS.value();
	for(int i=0;i<orderId.length;i++) {
		Order order=orderDao.findOne(Long.parseLong(orderId[i]));
		Response response=StateUtils.changState(order.getOrderNo(), "3", "","");
		if(response.getRet()==ResponseCode.SUCCESS.value()) {
			orderDao.changOrderStatus(OrderStatus.shipping.getValue(), orderId[i]);
			
	      }
		if(response.getRet()==ResponseCode.ERROR.value()) {
			ret=ResponseCode.ERROR.value();
		}
		
	}
	return ret;
	
}





public void addLog(OrderLog orderLog) {
	// TODO Auto-generated method stub
       orderDao.addLog(orderLog);
}





public long findOrderIdbyOrderNo(String orderNo) {
	// TODO Auto-generated method stub
	return orderDao.findOrderIdbyOrderNo(orderNo);
}
   

}
