package com.ps.produce.order.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.produce.base.entity.query.model.OrderQuery;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.exception.BusinessException;
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
		for (String id : ids.split(",")) {
			orderDao.delete(id);

		}

	}

	public PageBean<Order> find(PageBean<Order> pageBean, OrderQuery query) {
		List<Order> orders = orderDao.findList(pageBean, query);
		int totalCount = orderDao.count(pageBean, query);
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

	public void changOrderStatus(int confirm, String[] orderId) {
		for (int i = 0; i < orderId.length; i++)
			orderDao.changOrderStatus(confirm, orderId[i]);

	}

	public int addWaitMakeOrder(String orderNo, String userName, long userId) {
		if (orderDao.findOneByOrderNo(orderNo) == 0) {
			return -1;
		}
		return orderDao.addWaitMakeOrder(orderNo,new Date(),userName,userId);
	}

	public int addWaitShippingOrder(String orderNo,String optUsername,Long optUserId) {
		if (orderDao.findOneByOrderNo(orderNo) == 0) {
			return -1;
		}
		return orderDao.addWaitShippingOrder(orderNo,optUsername,optUserId);
	}

	public Response addShipInfo(Order order) {
		Order oldOrder = orderDao.findOne(order.getId());
		Response response=new Response();
		int orderCount=orderDao.findOneExpressNo(order.getExpressNo());
		if(orderCount>0) {
			response.setRet(1);
			response.setMsg("该物流信息已添加");
			return response;
		}
		if (response.getRet() == ResponseCode.SUCCESS.value()) {
			int count=orderDao.addShipInfo(order);
			if(count==1) {
			response.setRet(0);}
		}else {
			response.setRet(1);
			response.setMsg("订单同步异常");
		}
		return response;
	}

	public void addMakeOrder(int value, String[] orderNo, String userName, long userId) {
		for (int i = 0; i < orderNo.length; i++) {
			orderDao.addMakeOrder(value, orderNo[i], userName, userId,new Date());
		}
	}

	public String querProductImg(String productId) {
		return orderDao.queryProductImg(productId);
	}

	public int cancle(Order order) {
		return orderDao.cancle(order);

	}

	public int cancleOrder(int value, String[] orderNo) {
		int ret = ResponseCode.SUCCESS.value();
		for (int i = 0; i < orderNo.length; i++) {
			Order order = orderDao.findOne(Long.parseLong(orderNo[i]));
			Response response = StateUtils.changState(order.getOrderNo(), "4", "", "",order.getCallbackUrl());
			if (response.getRet() == ResponseCode.SUCCESS.value()) {
				orderDao.changOrderStatus(OrderStatus.cancel.getValue(), orderNo[i]);

			}
			if (response.getRet() == ResponseCode.ERROR.value()) {
				ret = ResponseCode.ERROR.value();
			}

		}
		return ret;
	}

	public Response confirmOrder(String[] orderNo) {
		Response res = new Response();
		for (int i = 0; i < orderNo.length; i++) {
			Order order = orderDao.findOne(Long.parseLong(orderNo[i]));
			Response response = StateUtils.changState(order.getOrderNo(), "2", "", "",order.getCallbackUrl());
			if(response.getRet()!=ResponseCode.SUCCESS.value())
				throw new BusinessException(ResponseCode.ERROR);
			orderDao.addPrintOrder(order.getStatus(), orderNo[i],new Date());
		}
		return res;
	}

	public Response addShipOrder(String[] orderId) {
		Response oldResponse=new Response();
		for (int i = 0; i < orderId.length; i++) {
			Order order = orderDao.findOne(Long.parseLong(orderId[i]));
			if(StringUtils.isEmpty(order.getExpressName())&&StringUtils.isEmpty(order.getExpressNo())) {
				 oldResponse.setRet(-1);
				 oldResponse.setMsg("请先添加物流信息");
				 return oldResponse;
			}
			Response response = StateUtils.changState(order.getOrderNo(), "3", order.getExpressNo(),order.getExpressName(),order.getCallbackUrl());
			if (response.getRet() == ResponseCode.SUCCESS.value()) {
				orderDao.addShippingOrder(OrderStatus.shipping.getValue(), orderId[i],new Date());
				oldResponse.setRet(0);
            }else {
            	oldResponse.setRet(-1);
            	oldResponse.setMsg("订单同步异常");
        		return oldResponse;

            }
			
			
		}
		return oldResponse;

	}

	public List<OrderLog> findOrderLogByOrderId(Long id) {
		return this.orderDao.findOrderLogByOrderId(id);
	}

	public List<Order> findOrderByOrderIds(String orderIds) {
		return this.orderDao.findOrderByOrderIds(orderIds);
	}

	public void addLog(OrderLog orderLog) {
		orderDao.addLog(orderLog);
	}

	public Long findOrderIdbyOrderNo(String orderNo) {
		return orderDao.findOrderIdbyOrderNo(orderNo);
	}

	public int findStatusByOrderId(Long orderId) {
		// TODO Auto-generated method stub
		return orderDao.findStatusByOrderId(orderId);
	}
}
