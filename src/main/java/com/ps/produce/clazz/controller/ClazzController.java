package com.ps.produce.clazz.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.ps.produce.attendance.dto.AttQuery;
import com.ps.produce.attendance.entity.Attendance;
import com.ps.produce.attendance.service.AttendanceService;
import com.ps.produce.base.entity.query.model.LeaveQuery;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.clazz.entity.Clazz;
import com.ps.produce.clazz.service.ClazzService;
import com.ps.produce.exception.BusinessException;
import com.ps.produce.leave.entity.Leave;
import com.ps.produce.shiro.ShiroUser;
import com.ps.produce.support.ISecurityUtils;
import com.ps.produce.support.JsonObject;
import com.ps.produce.support.Password;
import com.ps.produce.support.Response;
import com.ps.produce.support.ResponseCode;
import com.ps.produce.system.entity.Course;
import com.ps.produce.system.entity.Office;
import com.ps.produce.system.entity.User;
import com.ps.produce.system.service.CourseService;
import com.ps.produce.system.service.OfficeService;
import com.ps.produce.system.service.RoleService;
import com.ps.produce.system.service.UserService;
//班级管理
@Controller
@RequestMapping(value="/clazz")
public class ClazzController {
	@Autowired
	private UserService userService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private ClazzService clazzService;
    //跳转到班级页面
	@RequestMapping(value="/list" ,method=RequestMethod.GET)
	public String user(Model model,PageBean<User> userPageBean) {
		model.addAttribute("title", "班级管理");
		List<Office> offices=Lists.newArrayList();
		offices = officeService.getOffice();
		Gson gson=new Gson();
		model.addAttribute("offices", gson.toJson(offices));
		return "/clazz/Clazz";
	}

	//获取班级list
	@RequestMapping(value = "/source", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<Clazz> sourec(@RequestParam(value = "iDisplayStart", defaultValue = "0") int iDisplayStart,
            @RequestParam(value = "iDisplayLength", defaultValue = "10000") int iDisplayLength, Model model,
            ServletRequest request) throws UnsupportedEncodingException {
		String sortCol = request.getParameter("iSortCol_0");
        String sortType = request.getParameter("sSortDir_0");

        String searchParameter = request.getParameter("sSearch");
        Map<String, String> parameters = new HashMap<String, String>(16);
        if (!StringUtils.isEmpty(searchParameter)) {
            searchParameter = URLDecoder.decode(searchParameter, "UTF-8");
            searchParameter = searchParameter.trim();
        }
        parameters.put("searchParameter", searchParameter);

        
		List<Clazz> attendances = clazzService.findClazz(parameters, searchParameter, iDisplayStart, iDisplayLength,
                sortType, sortCol);
        JsonObject<Clazz> result = new JsonObject<Clazz>();
        result.setiTotalDisplayRecords(0);
        result.setiTotalRecords(0);
        result.setAaData(attendances);
        return result;
    }
	
	
	
	
	//保存班级
	@RequestMapping(value="save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> save(@RequestBody Clazz clazz) {
		Map<String,Object> map =new HashMap<String,Object>();
		int ret=0;
		try {
			ret = clazzService.saveAtt(clazz);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("ret", ret);
		return map;
	}
	//更新班级信息
	@RequestMapping(value="update",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> update(@RequestBody  Clazz clazz) {
		Map<String,Object> map =new HashMap<String,Object>();
		int ret=0;
		try {
		ret=clazzService.update(clazz);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("ret", ret);
		return map;
	}
	
	//删除班级
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public Response delete(String ids, RedirectAttributes redirectAttributes) {
		Response response = new Response();
		try {
			clazzService.delete(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
	

}
