package com.ps.produce.attendance.controller;

import java.io.UnsupportedEncodingException;
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
//考勤
@Controller
@RequestMapping(value="/attendance")
public class AttendanceController {
	@Autowired
	private ClazzService clazzService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserService userService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private AttendanceService attendanceService;
    //根据roleId跳转页面
	@RequestMapping(value="/list" ,method=RequestMethod.GET)
	public String user(Model model,PageBean<User> userPageBean) {
		model.addAttribute("title", "考勤管理");
		List<Office> offices=Lists.newArrayList();
		List<Course> courses=Lists.newArrayList();
		List<Clazz> list=clazzService.find();
		offices = officeService.getOffice();
		//获取角色id获取全部学生
		List<User> users=userService.findUsers(14);
		ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if(u.getRoles().contains(13)){
			courses=courseService.getCourse(u.getId());
		}else {
			courses=courseService.getCourse();
		}
		Gson gson=new Gson();
		model.addAttribute("clazzs",gson.toJson(list));
		model.addAttribute("courses",gson.toJson(courses));
		model.addAttribute("offices", gson.toJson(offices));
		model.addAttribute("students",gson.toJson(users));
		return "/attendance/Attendance";
	}
	//根据roleId获取用户列表
	
	@RequestMapping(value = "/source", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<Attendance> sourec(@RequestParam(value = "iDisplayStart", defaultValue = "0") int iDisplayStart,
            @RequestParam(value = "iDisplayLength", defaultValue = "10000") int iDisplayLength, Model model,
            ServletRequest request) throws UnsupportedEncodingException {
		ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		List<Attendance> attendances = attendanceService.findAttendances();
        JsonObject<Attendance> result = new JsonObject<Attendance>();
        result.setiTotalDisplayRecords(0);
        result.setiTotalRecords(0);
        result.setAaData(attendances);
        return result;
    }
	
	
	
	//添加考勤
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> save(@RequestBody AttQuery query) {
		Map<String,Object> map =new HashMap<String,Object>();
		ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Attendance attendance=new Attendance();
		attendance.setClazzId(query.getClazzId());
		attendance.setStuId(query.getStuId());
		attendance.setOfficeName(query.getOfficeName());
		attendance.setStuName(query.getStuName());
		attendance.setOfficeId(query.getOfficeId());
    	attendance.setCreateDate(new Date());
    	attendance.setRemarks(query.getRemarks());
    	attendance.setOptId(u.getId());
		attendance.setAbsenceTime(query.getAbsenceTime());
		attendance.setCourseId(query.getCourseId());
		attendance.setCourseName(query.getCourseName());
		int ret=0;
		try {
			ret = attendanceService.saveAtt(attendance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("ret", ret);
		return map;
	}
	//更新考勤
	@RequestMapping(value="update",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> update(@RequestBody Attendance attendance) {
		Map<String,Object> map =new HashMap<String,Object>();
		ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		attendance.setOptId(u.getId());
		int ret=0;
		try {
			ret = attendanceService.editAtt(attendance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("ret", ret);
		return map;
	}
	
	//删除考勤
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public Response delete(String ids, RedirectAttributes redirectAttributes) {
		Response response = new Response();
		try {
			attendanceService.delete(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
	
	

	  

	  

}
