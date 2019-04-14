package com.ps.produce.system.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

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
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.base.entity.query.model.ScoreQuery;
import com.ps.produce.clazz.entity.Clazz;
import com.ps.produce.clazz.service.ClazzService;
import com.ps.produce.exception.BusinessException;
import com.ps.produce.shiro.ShiroUser;
import com.ps.produce.support.ISecurityUtils;
import com.ps.produce.support.JsonObject;
import com.ps.produce.support.Password;
import com.ps.produce.support.Response;
import com.ps.produce.support.ResponseCode;
import com.ps.produce.system.entity.Distribution;
import com.ps.produce.system.entity.User;
import com.ps.produce.system.service.CourseService;
import com.ps.produce.system.service.RoleService;
import com.ps.produce.system.service.UserService;

@Controller
@RequestMapping(value="/system/user")
public class UserController {
	@Autowired
	private ClazzService clazzService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private CourseService courseService;
    //根据roleId跳转页面
	@RequestMapping(value="/{roleId}" ,method=RequestMethod.GET)
	public String user(@PathVariable int roleId,Model model,PageBean<User> userPageBean) {
		String title="";
		String url="";
		if(roleId==14) {
			title="学生管理";
			url="/system/Stu";
		}else if(roleId==11) {
			title="辅导员管理";
			url="/system/Cou";
		}else if(roleId==13) {
			title="教师管理";
			url="/system/Tea";
			Gson gson=new Gson();
			model.addAttribute("course", gson.toJson(courseService.getCourse()));
		}
		
		model.addAttribute("title", title);
		return url;
	}
	//根据roleId获取用户列表
	
	@RequestMapping(value = "/source/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<User> sourec(@PathVariable int roleId,@RequestParam(value = "iDisplayStart", defaultValue = "0") int iDisplayStart,
            @RequestParam(value = "iDisplayLength", defaultValue = "10000") int iDisplayLength, Model model,
            ServletRequest request) throws UnsupportedEncodingException {
		List<User> users = userService.findUsers(roleId);
        JsonObject<User> result = new JsonObject<User>();
        result.setiTotalDisplayRecords(0);
        result.setiTotalRecords(0);
        result.setAaData(users);
        return result;
    }
	//分配班级
	@RequestMapping(value="/distribution")
	@ResponseBody
	public Map<String,Object> distribution(@RequestBody  Distribution d){
		int	ret=0;
		try {
			ret=userService.distribution(d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    Map<String,Object> map=new HashMap<String,Object>();
    map.put("ret", ret);
    return map;
	}
	
	@RequestMapping(value="/querrTea")
	@ResponseBody
	public String querrTea(@RequestBody  Distribution d){
	ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	List<Map<String,Object>> datas=Lists.newArrayList();
	if(u.getRoles().contains(13)){
		datas=userService.queryTea(u.getId(),d.getCourseId());
	}
	datas=userService.queryTea(d.getCourseId());
	Gson gson=new Gson();
    return gson.toJson(datas);
	}
	@RequestMapping(value="/querrClazz")
	@ResponseBody
	public String querrClazz(@RequestBody  Distribution d){
	ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	if(u.getRoles().contains(13)) {
	d.setTeaId(u.getId());
	}
	List<Map<String,Object>> datas=Lists.newArrayList();
	try {
		datas=userService.queryClazz(d);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Gson gson=new Gson();
    return gson.toJson(datas);
	}
	//根据角色id跳转页面
	@RequestMapping(value = "form/{roleId}")
	public String form(@PathVariable int roleId,User user, Model model) {
		user = userService.get(user.getId());
		List<Clazz> list=clazzService.find();
		user.setRoleList(roleService.findUserRoleList(user.getId()));
		model.addAttribute("user", user);
		model.addAttribute("allRoles", ISecurityUtils.getAllRoleList());
		
		if(roleId==14) {
			Gson gson=new Gson();
			model.addAttribute("clazzs", gson.toJson(list));
			return "/system/UserForm";
		}
		if(roleId==13) {
			return "/system/TeaForm";
		}
		else {
			return "/system/CouForm";
		}
		
	}
	
	//根据角色id保存用户
	@RequestMapping(value="save/{roleId}",method=RequestMethod.POST)
	public String save(@PathVariable int roleId,User user, RedirectAttributes redirectAttributes) {
		
		try {
			userService.updateUser(user);
			if(roleId==14) {
				clazzService.insert(user);
			}
			redirectAttributes.addFlashAttribute("success", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "操作失败");
		}
		return "redirect:/system/user/form/"+roleId+"?id="+user.getId();
	}
	
	//删除用户
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String delete(String ids, RedirectAttributes redirectAttributes) {
		try {
			userService.deleteBatch(ids);
			redirectAttributes.addFlashAttribute("success", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "操作失败");
		}
		return "redirect:/system/user";
	}
	

	/*@RequestMapping(value="getUserAll",method=RequestMethod.GET)
	@ResponseBody
	public List<User> getUserAll(String username, RedirectAttributes redirectAttributes) {
		List<User> users = userService.getUserAll(username);
		return users;
	}*/
	
	
	  /*@RequestMapping(method = RequestMethod.POST, value = "/add/{roleId}")
	    @ResponseBody
	    public Response add(@RequestBody User user,@PathVariable int roleId) {     
		  userService.add(user,roleId);
		  return new Response().setResponseCode(ResponseCode.SUCCESS);
	       
	    }*/
	  //修改密码
	  @RequestMapping(value="cp",method = RequestMethod.POST)
		public String changePassword(Model model,Password password) {
			ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();			
			model.addAttribute("username", u.getUsername());	
			try{
					userService.changePassword(u.getId(),password);
			}catch(BusinessException e){
				model.addAttribute("error",e.getMessage());
				return "/system/ChangePassword";
			}
			
			model.addAttribute("success", "修改密码成功");
			return "/system/ChangePassword";
		}
	   //跳转修改密码界面
		@RequestMapping(method=RequestMethod.GET,value = "/changePassword")
		public String changePassword(Model model,PageBean<User> userPageBean) {
			model.addAttribute("title", "修改密码");
			return "/system/ChangePassword";
		}
}
