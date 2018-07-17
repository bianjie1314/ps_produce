package com.ps.produce.order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ps.produce.base.entity.query.model.OrderQuery;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.order.entity.Order;
import com.ps.produce.order.entity.OrderLog;
import com.ps.produce.order.service.OrderService;
import com.ps.produce.shiro.ShiroUser;
import com.ps.produce.support.Response;
import com.ps.produce.support.ResponseCode;
import com.ps.produce.support.pair.OrderStatus;
import com.ps.produce.support.utils.ZipImgs;



@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    
    @RequestMapping(value = "list" ,produces = "text/html;charset=UTF-8")
    public String index(PageBean<Order> pageBean, OrderQuery query,Model model,ServletRequest request) {
        String status=request.getParameter("status");
    	String start=null;
    	String end=null;
    	if(StringUtils.isNoneEmpty(query.getTime())) {
    		String[] times = query.getTime().split("~");
    		start=times[0];
    		end=times[1];
    	}
    	query.setStart(start);
    	query.setEnd(end+" 23:59:59");
    	query.setStatus(status);
    	pageBean = orderService.find(pageBean,query);
    	model.addAttribute("pageBean", pageBean);
    	model.addAttribute("query", query);
    	model.addAttribute("status",status);
        return "produce/Order";
    }
    
    @RequestMapping(value = "detail" ,produces = "text/html;charset=UTF-8")
    public String index(Long id,Model model) {
    	Order order = orderService.findOne(id);
    	List<OrderLog> orderLogs =orderService.findOrderLogByOrderId(id);
    	model.addAttribute("order", order);
    	model.addAttribute("orderLogs", orderLogs);
        return "produce/Detail";
    }
    
    @RequestMapping(value = "printList" ,produces = "text/html;charset=UTF-8")
    public String printIndex(PageBean<Order> pageBean, OrderQuery query,Model model,ServletRequest request) {
    	String status=request.getParameter("status");
    	String start=null;
    	String end=null;
    	if(StringUtils.isNoneEmpty(query.getTime())) {
    		String[] times = query.getTime().split("~");
    		start=times[0];
    		end=times[1];
    	}
    	query.setStart(start);
    	query.setEnd(end+" 23:59:59");
    	if(!StringUtils.isEmpty(status)) 
    	query.setStatus(status);
    	pageBean = orderService.find(pageBean,query);
    	model.addAttribute("pageBean", pageBean);
    	model.addAttribute("query", query);
    	model.addAttribute("status",status);
        return "produce/PrintOrder";
    }
    @RequestMapping(value = "makeList" ,produces = "text/html;charset=UTF-8")
    public String makeIndex(PageBean<Order> pageBean, OrderQuery query,Model model,ServletRequest request) {
    	ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    	long userId=u.getId();
    	String status=request.getParameter("status");
    	String start=null;
    	String end=null;
    	if(StringUtils.isNoneEmpty(query.getTime())) {
    		String[] times = query.getTime().split("~");
    		start=times[0];
    		end=times[1];
    	}
    	query.setId(userId);
    	query.setStart(start);
    	query.setEnd(end+" 23:59:59");
    	query.setStatus(status);
    	pageBean = orderService.find(pageBean,query);
    	model.addAttribute("pageBean", pageBean);
    	model.addAttribute("query", query);
    	model.addAttribute("status",status);
        return "produce/MakeOrder";
    }
    
    
    
    @ResponseBody
	@RequestMapping(value = "/addShipInfo", method = RequestMethod.POST)
	public int addShipInfo( @RequestBody Order order) throws IOException {
    	int result=orderService.addShipInfo(order);
    	ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    	long userId=u.getId();
    	String userName=u.getUsername();
    	OrderLog orderLog=new OrderLog();
		orderLog.setOptUserId(userId);
		orderLog.setOptUsername(userName);
		orderLog.setOrderId(order.getId());
		orderLog.setStatus(OrderStatus.cancel.getValue());
		orderLog.setRemarks("物流公司："+order.getExpressName()+"  物流编号:"+order.getExpressName());
		orderLog.setFlag(0);
		orderService.addLog(orderLog);
     return result;
	}
    @ResponseBody
	@RequestMapping(value = "/queryOrderUser")
	public List<String> queryOrderUser() throws IOException {
		List<String>list=orderService.queryOrderUser();
		return list;
	}
    
    @RequestMapping(value = "deliveryList" ,produces = "text/html;charset=UTF-8")
    public String DeliveryIndex(PageBean<Order> pageBean, OrderQuery query,Model model,ServletRequest request) {
    	String status=request.getParameter("status");
    	String start=null;
    	String end=null;
    	if(StringUtils.isNoneEmpty(query.getTime())) {
    		String[] times = query.getTime().split("~");
    		start=times[0];
    		end=times[1];
    	}
    	query.setStart(start);
    	query.setEnd(end+" 23:59:59");
    	if(!StringUtils.isEmpty(status)) 
    	query.setStatus(status);
    	pageBean = orderService.find(pageBean,query);
    	model.addAttribute("pageBean", pageBean);
    	model.addAttribute("query", query);
    	model.addAttribute("status",status);
        return "produce/DeliveryOrder";
    }
    @ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "/cancalOrder")
    public int cancalOrder(@RequestParam(value="orderId")String orderId,ServletRequest request) {
    	String[] orderNo=orderId.split(",");
    	ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    	long userId=u.getId();
    	String userName=u.getUsername();
    	for(int i=0;i<orderNo.length;i++) {
    		OrderLog orderLog=new OrderLog();
        	orderLog.setOptUserId(userId);
        	orderLog.setOptUsername(userName);
        	orderLog.setOrderId(Long.parseLong(orderNo[i]));
        	orderLog.setStatus(OrderStatus.cancel.getValue());
        	orderLog.setRemarks("取消订单");
        	orderLog.setFlag(0);
        	orderService.addLog(orderLog);
    	}
    	int ret =orderService.canalOrder(OrderStatus.cancel.getValue(),orderNo);
    	return ret;
    }
    
    @ResponseBody
	@RequestMapping(value = "/confirmOrder")
    public int confirmOrder(String orderId,ServletRequest request) {
    	String[] orderNo=orderId.split(",");
    	ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    	long userId=u.getId();
    	String userName=u.getUsername();
    	for(int i=0;i<orderNo.length;i++) {
    		OrderLog orderLog=new OrderLog();
        	orderLog.setOptUserId(userId);
        	orderLog.setOptUsername(userName);
        	orderLog.setOrderId(Long.parseLong(orderNo[i]));
        	orderLog.setStatus(OrderStatus.confirm.getValue());
        	orderLog.setRemarks("确认订单");
        	orderLog.setFlag(0);
        	orderService.addLog(orderLog);
    	}
    	int ret=orderService.confirmOrder(orderNo);
    	return ret;
    }
    @ResponseBody
	@RequestMapping(value = "/waitMakeOrder")
    public Response waitMakeOrder(@RequestParam(value="orderNo")String orderNo,ServletRequest request) {
    	Response response = new Response();
    	int result=orderService.addWaitMakeOrder(orderNo);
    	Long orderId=orderService.findOrderIdbyOrderNo(orderNo);
    	if(orderId==null){
    		response.setRet(-1);
    		response.setMsg("订单不存在");
    		return response;
    	}
    	ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    	long userId=u.getId();
    	String userName=u.getUsername();
    	OrderLog orderLog=new OrderLog();
        orderLog.setOptUserId(userId);
        orderLog.setOptUsername(userName);
        orderLog.setOrderId(orderId);
        orderLog.setStatus(OrderStatus.waitMake.getValue());
        orderLog.setRemarks("添加等待制作订单");
        orderLog.setFlag(0);
        orderService.addLog(orderLog);
        if(result==0) {
        	String msg="";
        	int status=orderService.findStatusByOrderId(orderId);
        	if(status==OrderStatus.cancel.getValue()) {
        		msg="该订单已取消";
        	}else if(status==OrderStatus.waitConfirm.getValue()) {
        		msg="该订单未打印";
        	} if(status==OrderStatus.waitMake.getValue()) {
        		msg="该订单已添加";
        	}
        	if(status>=OrderStatus.make.getValue()) {
        		msg="该订单已制作";
        	}
        	response.setRet(1);
        	response.setMsg(msg);
        }
    	return response;
    }
    @ResponseBody
	@RequestMapping(value = "/makeOrder")
    public String makeOrder(@RequestParam(value="orderId")String orderId,ServletRequest request) {
    	String[] orderNo=orderId.split(",");
    	ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    	String userName=u.getOsUsername();
    	long userId=u.getId();
    	for(int i=0;i<orderNo.length;i++) {
    		OrderLog orderLog=new OrderLog();
        	orderLog.setOptUserId(userId);
        	orderLog.setOptUsername(userName);
        	orderLog.setOrderId(Long.parseLong(orderNo[i]));
        	orderLog.setStatus(OrderStatus.make.getValue());
        	orderLog.setRemarks("添加到已制作订单");
        	orderLog.setFlag(0);
        	orderService.addLog(orderLog);
    	}
    	
    	orderService.addMakeOrder(OrderStatus.make.getValue(),orderNo,userName,userId);
    	return null;
    }
    @ResponseBody
	@RequestMapping(value = "/waitShippingOrder")
    public Response waitShippingOrder(@RequestParam(value="orderNo")String orderNo,ServletRequest request) {
    	int result =orderService.addWaitShippingOrder(orderNo);
    	Response response = new Response();
    	Long orderId=orderService.findOrderIdbyOrderNo(orderNo);
    	if(orderId==null) {
    		response.setRet(-1);
    		response.setMsg("暂无该订单发，请于订单管理员联系");
    		return response;
    	}
    	ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    	long userId=u.getId();
    	String userName=u.getUsername();
    	OrderLog orderLog=new OrderLog();
        orderLog.setOptUserId(userId);
        orderLog.setOptUsername(userName);
        orderLog.setOrderId(orderId);
        orderLog.setStatus(OrderStatus.waitShipping.getValue());
        orderLog.setRemarks("添加等待发货订单");
        orderLog.setFlag(0);
        orderService.addLog(orderLog);
        if(result==0) {
        	String msg="";
        	int status=orderService.findStatusByOrderId(orderId);
        	if(status==OrderStatus.cancel.getValue()) {
        		msg="该订单已取消";
        	}else if(status==OrderStatus.waitConfirm.getValue()) {
        		msg="该订单未打印";
        	}else if(status==OrderStatus.confirm.getValue()) {
        		msg="该订单未制作完成";
        	}else if(status==OrderStatus.waitMake.getValue()) {
        		msg="该订单未制作完成";
        	}else if(status==OrderStatus.waitShipping.getValue()) {
        		msg="该订单已添加";
        	}else if(status==OrderStatus.shipping.getValue()) {
        		msg="该订单已发货";
        	}
        	response.setRet(1);
        	response.setMsg(msg);
        }else {
        	response.setRet(0);
        	}
        return response;
    }
    @ResponseBody
	@RequestMapping(value = "/ShippingOrder")
    public Response ShippingOrder(@RequestParam(value="orderId")String orderIds,ServletRequest request) {
    	String[] orderNo=orderIds.split(",");
    	 Response response =orderService.addShipOrder(orderNo);
    	ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    	String userName=u.getOsUsername();
    	long userId=u.getId();
    	for(int i=0;i<orderNo.length;i++) {
    		OrderLog orderLog=new OrderLog();
        	orderLog.setOptUserId(userId);
        	orderLog.setOptUsername(userName);
        	orderLog.setOrderId(Long.parseLong(orderNo[i]));
        	orderLog.setStatus(OrderStatus.shipping.getValue());
        	orderLog.setRemarks("添加到已发货订单");
        	orderLog.setFlag(0);
        	orderService.addLog(orderLog);
    	}
    	return response;
    }
    @ResponseBody
    @RequestMapping(value="/downImg")
    public String downImg(@RequestParam(value="productId")String productId,ServletRequest  request,HttpServletResponse response) {
    	String imgUrls=orderService.querProductImg(productId);
    	ZipImgs.zipImgs(response, imgUrls);
    	return null;
    }
    }
