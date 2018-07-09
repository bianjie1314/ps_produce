package com.ps.produce.support;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.google.common.collect.Lists;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.base.service.BaseService;
import com.ps.produce.exception.BusinessException;
import com.ps.produce.shiro.ShiroUser;
import com.ps.produce.support.utils.JsonMapper;
import com.ps.produce.system.dao.MenuDao;
import com.ps.produce.system.dao.OfficeDao;
import com.ps.produce.system.dao.RoleDao;
import com.ps.produce.system.dao.UserDao;
import com.ps.produce.system.entity.Menu;
import com.ps.produce.system.entity.Office;
import com.ps.produce.system.entity.Role;
import com.ps.produce.system.entity.User;


public class ISecurityUtils {
	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);
	private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);
	private static OfficeDao officeDao = SpringContextHolder.getBean(OfficeDao.class);
	
	public static Long getCurrUserId() {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		if(shiroUser==null){
			throw new BusinessException(ResponseCode.USER_NOT_LOGIN);
		}
		return shiroUser.getId();
		
	}
	
	public static Long getCurrUserIdForStatistics() {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		if(shiroUser==null){
			return  -1000l;
		}
		return shiroUser.getId();
		
	}
	public static ShiroUser getCurrUser() {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		if(shiroUser==null){
			throw new BusinessException(ResponseCode.USER_NOT_LOGIN);
		}
		return shiroUser;
		
	}
	
	public static User getUser() {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		if(shiroUser==null){
			throw new BusinessException(ResponseCode.USER_NOT_LOGIN);
		}
		User user = new User();
		user.setId(shiroUser.getId());
		return user;
	}
	
	public static boolean isAuthenticated(){
		return SecurityUtils.getSubject().isAuthenticated()||SecurityUtils.getSubject().isRemembered();
	}

	/**
	 * 获取当前用户角色列表
	 * @return
	 */
	public static List<Role> getRoleList(){
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		return shiroUser.getRoles();
	}
	
	public static List<Role> getAllRoleList(){
		List<Role> roleList = Lists.newArrayList();
		PageBean<Role> pageBean = new PageBean<Role>();
		roleList = roleDao.findAllList(pageBean);
		return roleList;
	}
	
	/**
	 * 获取当前用户菜单
	 * @return
	 */
	public static List<Menu> getMenuList() {
		List<Menu> menuList = Lists.newArrayList();
		ShiroUser suser = getCurrUser();
		User user = userDao.get(suser.getId());
		if (user.isAdmin()){
			menuList = menuDao.findAllList(new Menu());
		}else{
			Menu m = new Menu();
			m.setUserId(user.getId());
			menuList = menuDao.findByUserId(m);
		}
		return menuList;
	}
	
	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 */
	public static List<Office> getOfficeList(){
		List<Office> officeList = Lists.newArrayList();
		ShiroUser suser = getCurrUser();
		User user = userDao.get(suser.getId());
		PageBean<Office> pageBean = new PageBean<Office>();
		if (user.isAdmin()){
			officeList = officeDao.findAllList(pageBean);
		}else{
			pageBean.getSqlMap().put("dsf", BaseService.dataScopeFilter(ISecurityUtils.getCurrUser(), "a", ""));
			officeList = officeDao.findList(pageBean);
		}
		return officeList;
	}

	public static List<Office> getOfficeAllList() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 当前用户所有权限
	 * @return
	 */
	public static String getPermissions() {
		List<String> s =((ShiroUser)SecurityUtils.getSubject().getPrincipal()).getPermissions();
		return JsonMapper.getInstance().toJson(s);
	}
}
