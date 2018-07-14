package com.ps.produce.order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
	@RequestMapping(value = "/addShipInfo", method = RequestMethod.POST)
	public int addShipInfo( @RequestBody Order order) throws IOException {
    	int result=orderService.addShipInfo(order);
		return result;
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
    public int cancalOrder(@RequestParam(value="orderId")String orderId,ServletRequest request) {
    	String[] orderNo=orderId.split(",");
    	int ret =orderService.canalOrder(OrderStatus.cancel.getValue(),orderNo);
    	return ret;
    }
    
    @ResponseBody
	@RequestMapping(value = "/confirmOrder")
    public int confirmOrder(String orderId,ServletRequest request) {
    	String[] orderNo=orderId.split(",");
    	int ret=orderService.confirmOrder(orderNo);
    	return ret;
    }
    @ResponseBody
	@RequestMapping(value = "/waitMakeOrder")
    public String waitMakeOrder(@RequestParam(value="orderNo")String orderNo,ServletRequest request) {
    	int result=orderService.addWaitMakeOrder(orderNo);
    	System.out.println(result);
    	return result+"";
    }
    @ResponseBody
	@RequestMapping(value = "/makeOrder")
    public String makeOrder(@RequestParam(value="orderId")String orderId,ServletRequest request) {
    	String[] orderNo=orderId.split(",");
    	ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    	String userName=u.getOsUsername();
    	long userId=u.getId();
    	orderService.addMakeOrder(OrderStatus.make.getValue(),orderNo,userName,userId);
    	return null;
    }
    @ResponseBody
	@RequestMapping(value = "/waitShippingOrder")
    public String waitShippingOrder(@RequestParam(value="orderNo")String orderNo,ServletRequest request) {
    	int result =orderService.addWaitShippingOrder(orderNo);
    	return result+"";
    }
    @ResponseBody
	@RequestMapping(value = "/ShippingOrder")
    public int  ShippingOrder(@RequestParam(value="orderId")String orderIds,ServletRequest request) {
    	String[] orderId=orderIds.split(",");
    	int ret =orderService.addShipOrder( orderId);
    	return ret;
    }
    @ResponseBody
    @RequestMapping(value="/downImg")
    public String downImg(@RequestParam(value="productId")String productId,ServletRequest  request,HttpServletResponse response) {
    	String imgUrls=orderService.querProductImg(productId);
    	ZipImgs.zipImgs(response, imgUrls);
    	return null;
    }
    }
