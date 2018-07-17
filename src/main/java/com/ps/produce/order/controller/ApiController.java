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

	public static void main(String [] arg) {
		Gson gson =new Gson();
		String body ="{\"orderNo\":\"18071712112850\",\"orderUsername\":\"商户1\",\"orderCreateTime\":\"2018-07-17 12:11:29\",\"expressTel\":\"我热污染\",\"expressAddress\":\"马来西亚 福建 丫耶丫耶\",\"callbackUrl\":\"http://10.101.10.112:8080/ps\",\"expressUsername\":\"222222\",\"expressFixedPhone\":\"34图34\",\"epressZipCode\":\"43她43他\",\"products\":[{\"templateName\":\"厂商1泳衣\",\"name\":\"厂商1泳衣  beach\",\"color\":\"\",\"size\":\"\",\"quantity\":1,\"ean\":\"213154\",\"image\":\"http://s3.cn-north-1.amazonaws.com.cn/photo-center-prov/output/1d0b38c405756f424ab3cf1c6f1c654a/946ee6a84911bb008bb14f8236648d04_res.jpg\",\"sku\":\"LNL-kUdPATqw3\",\"materials\":\"http://s3.cn-north-1.amazonaws.com.cn/photo-center-prov/images/91rr3AHARTasVhdqqVyNm4TGH9ub5wHb8VhZiE45/ODhhMDczZjVkMGYyMTI1ZWY1OGJlNDBiOGY4MTkxZjI.png,http://s3.cn-north-1.amazonaws.com.cn/photo-center-prov/images/91rr3AHARTasVhdqqVyNm4TGH9ub5wHb8VhZiE45/MTdjOWY4MDEyZjc1YjU5ZTBlMTQyYzUzMmY1OWM5Njg.png\"},{\"templateName\":\"厂商1泳衣\",\"name\":\"厂商1泳衣 rthy beach\",\"color\":\"\",\"size\":\"\",\"quantity\":1,\"ean\":\"\",\"image\":\"http://s3.cn-north-1.amazonaws.com.cn/photo-center-prov/output/8fc647b4f88f275443cc59e18aba79db/946ee6a84911bb008bb14f8236648d04_res.jpg\",\"sku\":\"LNL-6SA4oBrG7H45345346365435\",\"materials\":\"http://s3.cn-north-1.amazonaws.com.cn/photo-center-prov/images/91rr3AHARTasVhdqqVyNm4TGH9ub5wHb8VhZiE45/ODhhMDczZjVkMGYyMTI1ZWY1OGJlNDBiOGY4MTkxZjI.png,http://s3.cn-north-1.amazonaws.com.cn/photo-center-prov/images/91rr3AHARTasVhdqqVyNm4TGH9ub5wHb8VhZiE45/ODhhMDczZjVkMGYyMTI1ZWY1OGJlNDBiOGY4MTkxZjI.png,http://s3.cn-north-1.amazonaws.com.cn/photo-center-prov/images/91rr3AHARTasVhdqqVyNm4TGH9ub5wHb8VhZiE45/YTM5YTY5YTI5ZGZkYWIwYjc5NzQ2NmUxMmI4YjlhMTA.png,http://s3.cn-north-1.amazonaws.com.cn/photo-center-prov/images/91rr3AHARTasVhdqqVyNm4TGH9ub5wHb8VhZiE45/YjBjZjI0Yzc4ZDA2OWY0MWIzYjI1MTQ1ODg2ZTdhYTc.png,http://s3.cn-north-1.amazonaws.com.cn/photo-center-prov/images/91rr3AHARTasVhdqqVyNm4TGH9ub5wHb8VhZiE45/YjBjZjI0Yzc4ZDA2OWY0MWIzYjI1MTQ1ODg2ZTdhYTc.png\"}]}";
		Order order = gson.fromJson(body, Order.class);
		System.out.println(order);
	}
}
