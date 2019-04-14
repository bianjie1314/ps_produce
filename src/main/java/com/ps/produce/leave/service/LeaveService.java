package com.ps.produce.leave.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.produce.base.entity.query.model.LeaveQuery;
import com.ps.produce.base.entity.query.model.OrderQuery;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.exception.BusinessException;
import com.ps.produce.leave.dao.LeaveDao;
import com.ps.produce.leave.entity.Leave;

import com.ps.produce.support.Response;
import com.ps.produce.support.ResponseCode;
import com.ps.produce.support.pair.OrderStatus;


@Service
@Transactional(rollbackFor = Exception.class)
public class LeaveService {

	@Autowired
	private LeaveDao leaveDao;
	//获取假条list
	public PageBean<Leave> find(PageBean<Leave> pageBean, LeaveQuery query,long id) {
		List<Leave> orders = leaveDao.findList(pageBean, query,id);
		int totalCount = leaveDao.count(pageBean, query,id);
		pageBean.setTotalCount(totalCount);
		pageBean.setContent(orders);
		return pageBean;
	}
    //添加假条
	public long insert(Leave leave) {
		// TODO Auto-generated method stub
		return leaveDao.insert(leave);
	}
    //获取待辅导员审批的假条
	public PageBean<Leave> findbyCou(PageBean<Leave> pageBean, LeaveQuery query, long id) {
		List<Leave> orders = leaveDao.findListbyCou(pageBean, query,id);
		int totalCount = leaveDao.countbyCou(pageBean, query,id);
		pageBean.setTotalCount(totalCount);
		pageBean.setContent(orders);
		return pageBean;
	}

	
    //取消假条
	public long updateCanal(String rejection, String leaveId, Date date) {
		// TODO Auto-generated method stub
		return leaveDao.updateCanal(rejection,leaveId,date);
	}
   //确认审批
	public long sureLeave(String leaveId) {
		// TODO Auto-generated method stub
		return leaveDao.sureLeave(leaveId,new Date());
	}

	/*public Order findOne(Long id) {
		return leaveDao.findOne(id);
	}

	public List<String> queryOrderUser() {

		return leaveDao.queryOrderUser();
	}

	public void changOrderStatus(int confirm, String[] orderId) {
		for (int i = 0; i < orderId.length; i++)
			leaveDao.changOrderStatus(confirm, orderId[i]);

	}

	public int addWaitMakeOrder(String orderNo, String userName, long userId) {
		if (leaveDao.findOneByOrderNo(orderNo) == 0) {
			return -1;
		}
		return leaveDao.addWaitMakeOrder(orderNo,new Date(),userName,userId);
	}

	public int addWaitShippingOrder(String orderNo,String optUsername,Long optUserId) {
		if (leaveDao.findOneByOrderNo(orderNo) == 0) {
			return -1;
		}
		return leaveDao.addWaitShippingOrder(orderNo,optUsername,optUserId);
	}

	public Response addShipInfo(Order order) {
		Order oldOrder = leaveDao.findOne(order.getId());
		Response response=new Response();
		int orderCount=leaveDao.findOneExpressNo(order.getExpressNo());
		if(orderCount>0) {
			response.setRet(1);
			response.setMsg("该物流信息已添加");
			return response;
		}
		if (response.getRet() == ResponseCode.SUCCESS.value()) {
			int count=leaveDao.addShipInfo(order);
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
			leaveDao.addMakeOrder(value, orderNo[i], userName, userId,new Date());
		}
	}

	public String querProductImg(String productId) {
		return leaveDao.queryProductImg(productId);
	}

	public int cancle(Order order) {
		return leaveDao.cancle(order);

	}

	public int cancleOrder(int value, String[] orderNo) {
		int ret = ResponseCode.SUCCESS.value();
		for (int i = 0; i < orderNo.length; i++) {
			Order order = leaveDao.findOne(Long.parseLong(orderNo[i]));
			Response response = StateUtils.changState(order.getOrderNo(), "4", "", "",order.getCallbackUrl());
			if (response.getRet() == ResponseCode.SUCCESS.value()) {
				leaveDao.changOrderStatus(OrderStatus.cancel.getValue(), orderNo[i]);

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
			Order order = leaveDao.findOne(Long.parseLong(orderNo[i]));
			Response response = StateUtils.changState(order.getOrderNo(), "2", "", "",order.getCallbackUrl());
			if(response.getRet()!=ResponseCode.SUCCESS.value())
				throw new BusinessException(ResponseCode.ERROR);
			leaveDao.addPrintOrder(order.getStatus(), orderNo[i],new Date());
		}
		return res;
	}

	public Response addShipOrder(String[] orderId) {
		Response oldResponse=new Response();
		for (int i = 0; i < orderId.length; i++) {
			Order order = leaveDao.findOne(Long.parseLong(orderId[i]));
			if(StringUtils.isEmpty(order.getExpressName())&&StringUtils.isEmpty(order.getExpressNo())) {
				 oldResponse.setRet(-1);
				 oldResponse.setMsg("请先添加物流信息");
				 return oldResponse;
			}
			Response response = StateUtils.changState(order.getOrderNo(), "3", order.getExpressNo(),order.getExpressName(),order.getCallbackUrl());
			if (response.getRet() == ResponseCode.SUCCESS.value()) {
				leaveDao.addShippingOrder(OrderStatus.shipping.getValue(), orderId[i],new Date());
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
		return this.leaveDao.findOrderLogByOrderId(id);
	}

	public List<Order> findOrderByOrderIds(String orderIds) {
		return this.leaveDao.findOrderByOrderIds(orderIds);
	}

	public void addLog(OrderLog orderLog) {
		leaveDao.addLog(orderLog);
	}

	public Long findOrderIdbyOrderNo(String orderNo) {
		return leaveDao.findOrderIdbyOrderNo(orderNo);
	}

	public int findStatusByOrderId(Long orderId) {
		// TODO Auto-generated method stub
		return leaveDao.findStatusByOrderId(orderId);
	}*/
}
