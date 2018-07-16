package com.ps.produce.model.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.model.po.Template;
import com.ps.produce.model.service.TemplateService;
import com.ps.produce.support.JsonObject;
import com.ps.produce.system.entity.User;

@Controller
@RequestMapping(value = "/template")
public class TemplateController {
	@Autowired
	public TemplateService templateService;
	
	@RequiresPermissions("sys:user:view")
	@RequestMapping(method=RequestMethod.GET)
	public String user(Model model,PageBean<User> userPageBean) {
		return "/produce/Template";
	}
	@RequestMapping(value = "/source")
	@RequiresPermissions("sys:user:view")
	@ResponseBody
    public JsonObject<Template> source(@RequestParam(value = "iDisplayStart", defaultValue = "0") int iDisplayStart,
            @RequestParam(value = "iDisplayLength", defaultValue = "10000") int iDisplayLength, Model model,ServletRequest request) throws UnsupportedEncodingException{
		
		String productType=request.getParameter("productType");
		String productName=request.getParameter("productName");
		String date=request.getParameter("date");
		productType= productType==null?null:URLDecoder.decode(productType,"UTF-8");
		productName=productName==null?null:URLDecoder.decode(productName, "UTF-8");
		date=date==null?null:URLDecoder.decode(date,"UTF-8");
		String startDate="";
		String endDate="";
		if(!StringUtils.isEmpty(date)) {
		String[] dates=date.split("~");
		startDate=dates[0];
		endDate=dates[1]+" 23:59:59";
		}
		
		List<Template> list=templateService.list(productType, productName, startDate, endDate);
		JsonObject<Template> result = new JsonObject<Template>();
		result.setiTotalDisplayRecords(0);
		result.setiTotalRecords(0);
		result.setAaData(list);
		return result;
		
	} 
	@RequestMapping(value = "/queryType")
	@RequiresPermissions("sys:user:view")
	@ResponseBody
	public List<Map<String,Object>> queryType(@RequestParam(value = "iDisplayStart", defaultValue = "0") int iDisplayStart,
    @RequestParam(value = "iDisplayLength", defaultValue = "10000") int iDisplayLength, Model model,ServletRequest request){
		List<Map<String,Object>>list=templateService.queryType();
		return list;
	}
    	
}
