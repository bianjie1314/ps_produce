package com.ps.produce.system.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.exception.BusinessException;
import com.ps.produce.shiro.ShiroUser;
import com.ps.produce.support.ISecurityUtils;
import com.ps.produce.support.JsonObject;
import com.ps.produce.support.Password;
import com.ps.produce.support.Response;
import com.ps.produce.support.ResponseCode;
import com.ps.produce.system.entity.User;
import com.ps.produce.system.service.RoleService;
import com.ps.produce.system.service.UserService;

@Controller
@RequestMapping(value="/system/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@RequiresPermissions("sys:user:view")
	@RequestMapping(method=RequestMethod.GET)
	public String user(Model model,PageBean<User> userPageBean) {
		model.addAttribute("title", "用户管理");
		return "/system/User";
	}
	
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "/source", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<User> sourec(@RequestParam(value = "iDisplayStart", defaultValue = "0") int iDisplayStart,
            @RequestParam(value = "iDisplayLength", defaultValue = "10000") int iDisplayLength, Model model,
            ServletRequest request) throws UnsupportedEncodingException {
		List<User> users = userService.findUsers();
        JsonObject<User> result = new JsonObject<User>();
        result.setiTotalDisplayRecords(0);
        result.setiTotalRecords(0);
        result.setAaData(users);
        return result;
    }
	
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "form")
	public String form(User user, Model model) {
		user = userService.get(user.getId());
		user.setRoleList(roleService.findUserRoleList(user.getId()));
		model.addAttribute("user", user);
		model.addAttribute("allRoles", ISecurityUtils.getAllRoleList());
		return "/system/UserForm";
	}
	
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(User user, RedirectAttributes redirectAttributes) {
		try {
			userService.updateUser(user);
			redirectAttributes.addFlashAttribute("success", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "操作失败");
		}
		return "redirect:/system/user/form?id="+user.getId();
	}
	
	@RequiresPermissions("sys:user:edit")
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
	
	@RequiresPermissions("sys:flow:edit")
	@RequestMapping(value="getUserAll",method=RequestMethod.GET)
	@ResponseBody
	public List<User> getUserAll(String username, RedirectAttributes redirectAttributes) {
		List<User> users = userService.getUserAll(username);
		return users;
	}
	
	
	  @RequestMapping(method = RequestMethod.POST, value = "/add")
	    @ResponseBody
	    public Response add(@RequestBody User user) {     
		  userService.add(user);
	        
	       return new Response().setResponseCode(ResponseCode.SUCCESS);
	       
	    }
	  
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
	  
		@RequestMapping(method=RequestMethod.GET,value = "/changePassword")
		public String changePassword(Model model,PageBean<User> userPageBean) {
			model.addAttribute("title", "修改密码");
			return "/system/ChangePassword";
		}
}
