package com.ps.produce.order.controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ps.produce.base.entity.query.model.OrderQuery;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.order.entity.Order;
import com.ps.produce.order.service.OrderService;
import com.ps.produce.support.Response;
import com.ps.produce.support.ResponseCode;


@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "list" ,produces = "text/html;charset=UTF-8")
    public String index(PageBean<Order> pageBean, OrderQuery query,Model model) {

    	String start=null;
    	String end=null;
    	if(StringUtils.isNoneEmpty(query.getTime())) {
    		String[] times = query.getTime().split("~");
    		start=times[0];
    		end=times[1];
    	}
    	query.setEnd(end);
    	query.setStart(start);
    	System.out.println(query.getOrderUsername()+"11111111111111");
    	pageBean = orderService.find(pageBean,query);
    	model.addAttribute("pageBean", pageBean);
    	model.addAttribute("query", query);
        return "produce/Order";
    }
    @RequestMapping(value = "printList" ,produces = "text/html;charset=UTF-8")
    public String printIndex(PageBean<Order> pageBean, OrderQuery query,Model model) {

    	String start=null;
    	String end=null;
    	if(StringUtils.isNoneEmpty(query.getTime())) {
    		String[] times = query.getTime().split("~");
    		start=times[0];
    		end=times[1];
    	}
    	query.setEnd(end);
    	query.setStart(start);
    	pageBean = orderService.find(pageBean,query);
    	model.addAttribute("pageBean", pageBean);
    	model.addAttribute("query", query);
        return "produce/PrintOrder";
    }
    @RequestMapping(value = "makeList" ,produces = "text/html;charset=UTF-8")
    public String makeIndex(PageBean<Order> pageBean, OrderQuery query,Model model) {

    	String start=null;
    	String end=null;
    	if(StringUtils.isNoneEmpty(query.getTime())) {
    		String[] times = query.getTime().split("~");
    		start=times[0];
    		end=times[1];
    	}
    	query.setEnd(end);
    	query.setStart(start);
    	pageBean = orderService.find(pageBean,query);
    	model.addAttribute("pageBean", pageBean);
    	model.addAttribute("query", query);
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
    public String DeliveryIndex(PageBean<Order> pageBean, OrderQuery query,Model model) {

    	String start=null;
    	String end=null;
    	if(StringUtils.isNoneEmpty(query.getTime())) {
    		String[] times = query.getTime().split("~");
    		start=times[0];
    		end=times[1];
    	}
    	query.setEnd(end);
    	query.setStart(start);
    	pageBean = orderService.find(pageBean,query);
    	model.addAttribute("pageBean", pageBean);
    	model.addAttribute("query", query);
        return "produce/DeliveryOrder";
    }
    }
