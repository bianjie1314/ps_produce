
package com.ps.produce.leave.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ps.produce.base.entity.query.model.LeaveQuery;
import com.ps.produce.base.entity.query.model.OrderQuery;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.leave.entity.Leave;
import com.ps.produce.leave.service.LeaveService;

import com.ps.produce.shiro.ShiroUser;
import com.ps.produce.support.Response;
import com.ps.produce.support.ResponseCode;
import com.ps.produce.support.SignatureUtils;
import com.ps.produce.support.pair.OrderStatus;
import com.ps.produce.support.utils.ZipImgs;
import com.ps.produce.system.entity.Office;
import com.ps.produce.system.service.OfficeService;
import com.ps.produce.system.service.UserService;


//请假管理
@Controller
@RequestMapping(value = "/leave")
public class LeaveController {

    @Autowired
    LeaveService leaveService;
    @Autowired
    UserService userService;
    @Autowired
    OfficeService officeService;
    //获取学生的list
    @RequestMapping(value = "list" ,produces = "text/html;charset=UTF-8")
    public String index(PageBean<Leave> pageBean, LeaveQuery query,Model model,ServletRequest request) {
        String status=request.getParameter("status");
        ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        
    	String start=null;
    	String end=null;
    	if(StringUtils.isNoneEmpty(query.getTime())) {
    		String[] times = query.getTime().split("~");
    		start=times[0];
    		end=times[1];
    	}
    	query.setStart(start);
    	query.setEnd(end+" 23:59:59");
    	query.setStatus(status);
    	try {
			pageBean = leaveService.find(pageBean,query,u.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	model.addAttribute("pageBean", pageBean);
    	model.addAttribute("query", query);
    	model.addAttribute("status",status);
        return "leave/Leave1";
    }
    //待管理员审批的请假list
    @RequestMapping(value = "couList" ,produces = "text/html;charset=UTF-8")
    public String couList(PageBean<Leave> pageBean, LeaveQuery query,Model model,ServletRequest request) {
        String status=request.getParameter("status");
        ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        
    	String start=null;
    	String end=null;
    	if(StringUtils.isNoneEmpty(query.getTime())) {
    		String[] times = query.getTime().split("~");
    		start=times[0];
    		end=times[1];
    	}
    	query.setStart(start);
    	query.setEnd(end+" 23:59:59");
    	query.setStatus(status);
    	try {
			pageBean = leaveService.findbyCou(pageBean,query,u.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	model.addAttribute("pageBean", pageBean);
    	model.addAttribute("query", query);
    	model.addAttribute("status",status);
        return "leave/Leave2";
    }
    //添加假条
    @RequestMapping(value = "add" ,method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addOrder(@RequestBody LeaveQuery query) {
		ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String name=userService.findByLoginName(u.getUsername()).getName();
		long officeId=u.getOffice().getId();
		Office office=officeService.find(officeId);
		Leave leave=new Leave();
		leave.setOptId(Long.parseLong(office.getMaster()));
		String start=null;
    	String end=null;
    	if(StringUtils.isNoneEmpty(query.getTime())) {
    		String[] times = query.getTime().split("~");
    		start=times[0];
    		end=times[1];
    	}
    	leave.setStuId(u.getId());
		leave.setStuName(name);
		leave.setCreateDate(new Date());
		leave.setRemarks(query.getRemarks());
		leave.setStartTime(start);
		leave.setEndTime(end);
		long ret=0;
		try {
			ret = leaveService.insert(leave);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("ret", ret);
		return map;
	}
	//取消请假申请
    @RequestMapping(value = "cancalLeave" ,method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> cancalLeave(HttpServletRequest request) {
		String rejection =request.getParameter("rejection");
		String leaveId=request.getParameter("id");
		long ret=0;
		try {
			ret = leaveService.updateCanal(rejection,leaveId,new Date());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("ret", ret);
		return map;
	}
	//辅导员审批
    @ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "/sureLeave")
    public  Map<String,Object> sureLeave(@RequestParam(value="leaveId")String leaveId) {
    	long ret=0;
		try {
			ret = leaveService.sureLeave(leaveId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Map<String,Object>map=new HashMap<String,Object>();
 		map.put("ret", ret);
 		return map;
    }

    
    
    }
