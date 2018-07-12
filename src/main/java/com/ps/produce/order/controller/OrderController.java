package com.ps.produce.order.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
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

    @RequestMapping(value = "list")
    public String index(String keyword,Date start,Date end,String orderUsername,Integer status ,Model model) {
    	List<Order> orders = orderService.find(keyword, start, end, orderUsername, status);
    	model.addAttribute("orders", orders);
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
    @ResponseBody
    @RequestMapping(value="/zipImgs")
    public void download(HttpServletRequest request, HttpServletResponse response){
    	 
        
    
}
    }
