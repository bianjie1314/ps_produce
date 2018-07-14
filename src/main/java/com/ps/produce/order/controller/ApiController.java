package com.ps.produce.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ps.produce.model.po.Template;
import com.ps.produce.order.entity.Order;
import com.ps.produce.order.entity.Product;
import com.ps.produce.order.service.OrderService;
import com.ps.produce.support.Response;
import com.ps.produce.support.SignatureUtils;
import com.ps.produce.support.utils.HttpClientUtil;


@Controller
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    OrderService orderService;
    
    @RequestMapping(value = "order/add" )
    @ResponseBody
    public Response addOrder(Order order,Model model,ServletRequest request) {
    	 Response res = new Response();
    	  
    
        return res;
    }
    
    @RequestMapping(value = "order/cancle" )
    @ResponseBody
    public Response status(Order order,Model model,ServletRequest request) {
    	 Response res = new Response();
    	  
    
        return res;
    }
    
    
    @RequestMapping(value = "template/add" )
    @ResponseBody
    public Response addTemplate(Template template,Model model,ServletRequest request) {
    	 Response res = new Response();
    	  
    
        return res;
    }
    
    
    public static void main(String [] args) {
    	 test3();
    }
    public static void test3() {
    	Gson gson = new Gson();
    	Order t=new Order();
    	t.setOrderNo("订单编号");
		 String data = gson.toJson(t);
		 String url ="http://127.0.0.1:8080/ps_produce/api/order/cancle";

		url = SignatureUtils.signatureUrl(url, data, "dce7b60efaee20cc");
		System.out.println(url);
		System.out.println(data);
		String reponse = HttpClientUtil.doPostJson(url, data);
		System.out.println(reponse);
    }
    public static void test2() {
    	Gson gson = new Gson();
    	Template t=new Template();
    	t.setProductName("产品名称");
    	t.setProductType("产品类别");
    	t.setSku("sku");
    	t.setTempImgs("https://assets.gitee.com/assets/qrcode-weixin-8ab7378f5545710bdb3ad5c9d17fedfe.jpg,https://assets.gitee.com/assets/qrcode-weixin-8ab7378f5545710bdb3ad5c9d17fedfe.jpg");
		 String data = gson.toJson(t);
		 String url ="http://127.0.0.1:8080/ps_produce/api/addOrder";

		url = SignatureUtils.signatureUrl(url, data, "dce7b60efaee20cc");
		System.out.println(url);
		System.out.println(data);
		String reponse = HttpClientUtil.doPostJson(url, data);
		System.out.println(reponse);
    }
    
    public static void test() {
    	Gson gson = new Gson();
   	 List<Product> products = new ArrayList<Product>();
   	 Product p = new Product();
   	 p.setColor("红色");
   	 p.setEan("EAN1316");
   	 p.setImage("https://assets.gitee.com/assets/qrcode-weixin-8ab7378f5545710bdb3ad5c9d17fedfe.jpg");
   	 p.setName("我是产品名称");
   	 p.setQuantity(1);
   	 p.setSize("大号");
   	 p.setSku("我是sku");
   	 p.setTemplateName("我是磨具名称");
   	 p.setMaterials("https://assets.gitee.com/assets/qrcode-weixin-8ab7378f5545710bdb3ad5c9d17fedfe.jpg,https://assets.gitee.com/assets/qrcode-weixin-8ab7378f5545710bdb3ad5c9d17fedfe.jpg");
   	 products.add(p);
   	 Order order = new Order();
   	 order.setOrderNo("N12149679874");
   	 order.setExpressName("收件人");
   	 order.setExpressTel("13599999");
   	 order.setExpressAddress("收件地址");
   	 order.setCallbackUrl("http://127.0.0.1:8080/ps_produce/api");
   	 order.setOrderUsername("下单商户");
   	 order.setOrderCreateTime("2017-01-01");
   	 order.setRemarks("我是备注备注 备注");
   	 order.setProducts(products);
		 String data = gson.toJson(order);
		 String url ="http://127.0.0.1:8080/ps_produce/api/addOrder";

		url = SignatureUtils.signatureUrl(url, data, "dce7b60efaee20cc");
		System.out.println(url);
		System.out.println(data);
		String reponse = HttpClientUtil.doPostJson(url, data);
		System.out.println(reponse);
    }
    
}
