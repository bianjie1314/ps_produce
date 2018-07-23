package com.ps.produce.order.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ps.produce.model.po.Template;
import com.ps.produce.model.service.TemplateService;
import com.ps.produce.order.entity.Order;
import com.ps.produce.order.service.OrderService;
import com.ps.produce.support.Response;
import com.ps.produce.support.ResponseCode;
import com.ps.produce.support.SignatureUtils;
import com.ps.produce.support.utils.HttpClientUtil;

@Controller
@RequestMapping(value = "/api")
public class ApiController {


	@Autowired
	OrderService orderService;

	@Autowired
	TemplateService templateService;

	@RequestMapping(value = "order/add")
	@ResponseBody
	public Response addOrder( HttpServletRequest request) {
		
		Response res = new Response();
		try {
			String body = IOUtils.toString(request.getInputStream(),"utf-8" );
			System.out.println("revice order "+body);
			SignatureUtils.checkSign(request, body);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			Order order = gson.fromJson(body, Order.class);
			orderService.insert(order);
		} catch (IOException e) {
			res.setMsg(e.getMessage());
			res.setResponseCode(ResponseCode.ERROR);
			e.printStackTrace();
		}
		
		return res;
	}

	@RequestMapping(value = "order/cancle")
	@ResponseBody
	public Response cancle( HttpServletRequest request) {
		Response res = new Response();
		try {
			String body = IOUtils.toString(request.getInputStream(),"utf-8" );
			System.out.println("cancle order "+body);
			SignatureUtils.checkSign(request, body);
			Gson gson = new Gson();
			Order order = gson.fromJson(body, Order.class);
			int i = orderService.cancle(order);
			if(i==0) {
				res.setMsg("取消订单失败");
				res.setResponseCode(ResponseCode.ERROR);
			}
		} catch (IOException e) {
			res.setMsg(e.getMessage());
			res.setResponseCode(ResponseCode.ERROR);
			e.printStackTrace();
		}
		
		return res;
	} 
    
	@RequestMapping(value = "template/add")
	@ResponseBody
	public Response addTemplate(  HttpServletRequest request) {
		Response res = new Response();
		try {
			String body = IOUtils.toString(request.getInputStream(),"utf-8" );
			System.out.println("revice template "+body);
			SignatureUtils.checkSign(request, body);
			Gson gson = new Gson();
			Template template = gson.fromJson(body, Template.class);
			templateService.insert(template);
		} catch (IOException e) {
			res.setMsg(e.getMessage());
			res.setResponseCode(ResponseCode.ERROR);
			e.printStackTrace();
		}

	
		return res;
	}
	
	@RequestMapping(value = "template/delete")
	@ResponseBody
	public Response deleteTemplate(  HttpServletRequest request) {
		Response res = new Response();
		try {
			String body = IOUtils.toString(request.getInputStream(),"utf-8" );
			System.out.println("revice template "+body);
			SignatureUtils.checkSign(request, body);
			Gson gson = new Gson();
			Template template = gson.fromJson(body, Template.class);
			templateService.delete(template);
		} catch (IOException e) {
			res.setMsg(e.getMessage());
			res.setResponseCode(ResponseCode.ERROR);
			e.printStackTrace();
		}

	
		return res;
	}

	public static void main(String [] arg) {
		
		test();
	}
	
	  public static void test() {
	    	Gson gson = new Gson();
	    	Template t=new Template();
	    	t.setProductName("产品名称12");
	    	t.setProductType("产品类别12");
	    	t.setSku("sku");
	    	t.setTempImgs("https://assets.gitee.com/assets/qrcode-weixin-8ab7378f5545710bdb3ad5c9d17fedfe.jpg,https://assets.gitee.com/assets/qrcode-weixin-8ab7378f5545710bdb3ad5c9d17fedfe.jpg");
			 String data = gson.toJson(t);
			 String url ="http://127.0.0.1:8080/ps_produce/api/template/add";

			url = SignatureUtils.signatureUrl(url, data, "dce7b60efaee20cc");
			System.out.println(url);
			System.out.println(data);
			String reponse = HttpClientUtil.doPostJson(url, data);
			System.out.println(reponse);
	    }
	
	  public static void test2() {
	    	Gson gson = new Gson();
	    	Template t=new Template();
	    	t.setSku("厂商1黑胶地垫");
			 String data = gson.toJson(t);
			 String url ="http://127.0.0.1:8080/ps_produce/api/template/delete";

			url = SignatureUtils.signatureUrl(url, data, "dce7b60efaee20cc");
			System.out.println(url);
			System.out.println(data);
			String reponse = HttpClientUtil.doPostJson(url, data);
			System.out.println(reponse);
	    }
}
