package com.ps.produce.support.utils;

import java.util.List;
import java.util.Map;

import com.ps.produce.support.SpringContextHolder;
import com.ps.produce.support.pair.ApproveStatus;
import com.ps.produce.support.pair.RoleDataScope;
import com.ps.produce.system.entity.User;
import com.ps.produce.system.service.UserService;

/**
 * 系统数据工具类
 * @author yuyanzheng
 * 
 * 2018年1月11日
 */
public class SystemDataUtil {
	
	private static UserService userService = SpringContextHolder.getBean(UserService.class);
	/**
	 * 角色数据范围
	 * @return
	 */
	public static String getDataScopeJson(){
		return JsonMapper.toJsonString(RoleDataScope.getRoleDataScopeAll());
	}
	
	public static Map<String, String> getDataScopeMap(){
		return RoleDataScope.getRoleDataScopeAllMap();
	}
	
	/**
	 * 获取采购审批状态
	 * @return
	 */
	public static String getApproveStatusJson() {
		return JsonMapper.toJsonString(ApproveStatus.getApproveStatusAll());
	}
	
	public static String getUsers(){
		List<User> users = userService.findUsers();
		return JsonMapper.toJsonString(users);
	}
	
	public static String getUsersAll(){
		List<User> users = userService.findUsersAll();
		return JsonMapper.toJsonString(users);
	}
}
