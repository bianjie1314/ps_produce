package com.ps.produce.order.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String index( String keyword,String time,String orderUsername,Integer status ,Integer pageNumber ,Model model) {

    	String start=null;
    	String end=null;
    	if(StringUtils.isNoneEmpty(time)) {
    		String[] times = time.split("~");
    		start=times[0];
    		end=times[1];
    	}
    	if(StringUtils.isNoneEmpty(keyword)) {
    		keyword=	URLDecoder.decode(keyword);
    	}
    	List<Order> orders = orderService.find(keyword, start, end, orderUsername, status,pageNumber);
    	model.addAttribute("orders", orders);
    	model.addAttribute("keyword", keyword);
    	model.addAttribute("time", time);
        return "produce/Order";
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
    
}
