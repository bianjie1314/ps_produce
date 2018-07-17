package com.ps.produce.system.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ps.produce.shiro.ShiroUser;
import com.ps.produce.support.ISecurityUtils;
import com.ps.produce.support.MessageType;
import com.ps.produce.support.ShowMessage;
import com.ps.produce.support.utils.StringUtils;
import com.ps.produce.system.entity.Menu;
import com.ps.produce.system.entity.User;
import com.ps.produce.system.service.UserService;

/**
 * 登录Controller
 */
@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	/**
	 * 用户登录
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		Subject subject = SecurityUtils.getSubject();  
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		// 如果已经登录，则跳转到管理首页
	
		if(shiroUser != null){
			subject.hasRole("userSB");
			List<Menu> menus= ISecurityUtils.getMenuList();
			String redirect="/home";
			if(menus!=null&&!menus.isEmpty()) {
				for(int i=menus.size()-1;i>=0;i--) {
					if(StringUtils.isNoneBlank(menus.get(i).getHref())&&menus.get(i).getIsShow().equalsIgnoreCase("1"))
					redirect=menus.get(i).getHref();
				}
				
				
			}
			return "redirect:"+redirect;
		}
		return "/system/Login";
	}
	
	/**
	 * home
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "Home";
	}


	
	

	@RequestMapping(value = "/loginAction")
	public String login(User user, Model model, RedirectAttributes ra) {
		if(StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())){
			ShowMessage.show(ra, MessageType.ISWARNSHOW, "请输入用户名或密码");
			return "redirect:login";
		}
		Subject subUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try {
			subUser.login(token);
			ShiroUser shiroUser = ISecurityUtils.getCurrUser();
			subUser.hasRole("userSB");
			List<Menu> menus= ISecurityUtils.getMenuList();
			String redirect="/home";
			if(menus!=null&&!menus.isEmpty()) {
				for(int i=menus.size()-1;i>=0;i--) {
					if(StringUtils.isNoneBlank(menus.get(i).getHref())&&menus.get(i).getIsShow().equalsIgnoreCase("1"))
					redirect=menus.get(i).getHref();
				}
				
				
			}
			return "redirect:"+redirect;
		} catch (DisabledAccountException e1) {
			e1.printStackTrace();
			ShowMessage.show(ra, MessageType.ISDANGERSHOW, "账号异常,请联系管理员");
		} catch (IncorrectCredentialsException e2) {
			e2.printStackTrace();
			ShowMessage.show(ra, MessageType.ISDANGERSHOW, "用户名或密码错误,请联系管理员");
		} catch (AuthenticationException e3) {
			e3.printStackTrace();
			ShowMessage.show(ra, MessageType.ISDANGERSHOW, "登陆验证失败,请刷新后重试");
		}
		return "redirect:/login";
				
	}
	
	@RequestMapping(value = "/bind")
	public String bind(Model model ) {
		ShiroUser user = ISecurityUtils.getCurrUser();
		  if(user==null||StringUtils.isNotBlank(user.getOsUsername())) {
			  return "redirect:login";  		  
		  }else {
			  model.addAttribute("username", user.getUsername());
				return "/system/Bind";
		  }
	}
	
	
}
