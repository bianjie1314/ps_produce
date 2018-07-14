package com.ps.produce.order.controller;

import java.io.IOException;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ps.produce.order.entity.Order;
import com.ps.produce.order.service.OrderService;
import com.ps.produce.support.Response;
import com.ps.produce.support.SignatureUtils;
import com.ps.produce.support.utils.HttpClientUtil;


@Controller
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    OrderService orderService;
    
    @RequestMapping(value = "addOrder" ,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Response addOrder(Order order,Model model,ServletRequest request) {
    	  Response res = new Response();
    	  
    
        return res;
    }
    
    public static void main(String [] args) {
    	 Gson gson = new Gson();
    	 Order order = new Order();
    	 order.setOrderNo("N12149679874");
    	 order.setExpressName("收件人");
		 String data = gson.toJson(order);
		 String url ="http://127.0.0.1:8080/ps_produce/api/addOrder";

		url = SignatureUtils.signatureUrl(url, data, "dce7b60efaee20cc");
		System.out.println(url);
		String reponse = HttpClientUtil.doPostJson(url, data);
    }
    
}
