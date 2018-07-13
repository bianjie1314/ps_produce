package com.ps.produce.order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.OrderUtils;
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
import com.ps.produce.order.service.OrderService;
import com.ps.produce.support.Response;
import com.ps.produce.support.ResponseCode;
import com.ps.produce.support.pair.OrderStatus;
import com.ps.produce.support.utils.OrderStatusUtils;


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
    	query.setEnd(end);
    	if(!StringUtils.isEmpty(status)) 
    	query.setStatu(Integer.parseInt(status));
    	pageBean = orderService.find(pageBean,query);
    	model.addAttribute("pageBean", pageBean);
    	model.addAttribute("query", query);
    	model.addAttribute("status",status);
        return "produce/Order";
    }
    
    @RequestMapping(value = "detail" ,produces = "text/html;charset=UTF-8")
    public String index(Long id,Model model) {
    	Order order = orderService.findOne(id);
    	model.addAttribute("order", order);
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
    	query.setEnd(end);
    	if(!StringUtils.isEmpty(status)) 
    	query.setStatu(Integer.parseInt(status));
    	pageBean = orderService.find(pageBean,query);
    	model.addAttribute("pageBean", pageBean);
    	model.addAttribute("query", query);
    	model.addAttribute("status",status);
        return "produce/PrintOrder";
    }
    @RequestMapping(value = "makeList" ,produces = "text/html;charset=UTF-8")
    public String makeIndex(PageBean<Order> pageBean, OrderQuery query,Model model,ServletRequest request) {
    	String status=request.getParameter("status");
    	String start=null;
    	String end=null;
    	if(StringUtils.isNoneEmpty(query.getTime())) {
    		String[] times = query.getTime().split("~");
    		start=times[0];
    		end=times[1];
    	}
    	query.setStart(start);
    	query.setEnd(end);
    	if(!StringUtils.isEmpty(status)) 
    	query.setStatu(Integer.parseInt(status));
    	pageBean = orderService.find(pageBean,query);
    	model.addAttribute("pageBean", pageBean);
    	model.addAttribute("query", query);
    	model.addAttribute("status",status);
        return "produce/MakeOrder";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ResponseBody
    public Response add(@RequestBody Order order) {
     
       orderService.insert(order);
        
       return new Response().setResponseCode(ResponseCode.SUCCESS);
       
    } 
    
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    @ResponseBody
    public Response update(@RequestBody Order order) {     
       orderService.update(order);
        
       return new Response().setResponseCode(ResponseCode.SUCCESS);
       
    }
    
    @ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public Response walkaround( String ids) throws IOException {
		
		Response response = new Response();
		orderService.delete(ids);
		
		return response;
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
    	query.setEnd(end);
    	if(!StringUtils.isEmpty(status)) 
    	query.setStatu(Integer.parseInt(status));
    	pageBean = orderService.find(pageBean,query);
    	model.addAttribute("pageBean", pageBean);
    	model.addAttribute("query", query);
    	model.addAttribute("status",status);
        return "produce/DeliveryOrder";
    }
    @ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "/cancalOrder")
    public String cancalOrder(@RequestParam(value="orderId")String orderId,ServletRequest request) {
    	orderId=request.getParameter("orderId");
    	String[] orderNo=orderId.split(",");
    	orderService.changOrderStatus(OrderStatus.cancel.getValue(),orderNo);
    	return null;
    }
    @ResponseBody
	@RequestMapping(value = "/confirmOrder")
    public String confirmOrder(String orderId,ServletRequest request) {
    	String[] orderNo=orderId.split(",");
    	orderService.changOrderStatus(OrderStatus.confirm.getValue(),orderNo);
    	return null;
    }
    @ResponseBody
	@RequestMapping(value = "/waitMakeOrder")
    public String waitMakeOrder(@RequestParam(value="orderId")String orderId,ServletRequest request) {
    	String[] orderNo=orderId.split(",");
    	orderService.changOrderStatus(OrderStatus.waitMake.getValue(),orderNo);
    	return null;
    }
    @ResponseBody
	@RequestMapping(value = "/makeOrder")
    public String makeOrder(@RequestParam(value="orderId")String orderId,ServletRequest request) {
    	String[] orderNo=orderId.split(",");
    	orderService.changOrderStatus(OrderStatus.make.getValue(),orderNo);
    	return null;
    }
    @ResponseBody
	@RequestMapping(value = "/waitShippingOrder")
    public String waitShippingOrder(@RequestParam(value="orderId")String orderId,ServletRequest request) {
    	String[] orderNo=orderId.split(",");
    	orderService.changOrderStatus(OrderStatus.waitShipping.getValue(),orderNo);
    	return null;
    }
    @ResponseBody
	@RequestMapping(value = "/ShippingOrder")
    public String ShippingOrder(@RequestParam(value="orderId")String orderId,ServletRequest request) {
    	String[] orderNo=orderId.split(",");
    	orderService.changOrderStatus(OrderStatus.shipping.getValue(),orderNo);
    	return null;
    }
    }
