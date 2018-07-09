/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ps.produce.system.entity;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.ps.produce.base.entity.AbstractEntity;

/**
 * 角色Entity
 */
public class Role extends AbstractEntity {
	
//	private Office office;	// 归属机构
	private String name; 	// 角色名称
	private String roleType;// 权限类型
	private String dataScope;// 数据范围
	
	private String useable; 		//是否是可用
//	private User user;		// 根据用户ID查询角色列表

	private List<Menu> menuList = Lists.newArrayList(); // 拥有菜单列表
//	private List<Office> officeList = Lists.newArrayList(); // 按明细设置数据范围

	// 数据范围（1：所有数据；2：所在公司及以下数据；3：所在公司数据；4：所在部门及以下数据；5：所在部门数据；8：仅本人数据；9：按明细设置）
//	public static final String DATA_SCOPE_ALL = "1";
//	public static final String DATA_SCOPE_COMPANY_AND_CHILD = "2";
//	public static final String DATA_SCOPE_COMPANY = "3";
//	public static final String DATA_SCOPE_OFFICE_AND_CHILD = "4";
//	public static final String DATA_SCOPE_OFFICE = "5";
//	public static final String DATA_SCOPE_SELF = "8";
//	public static final String DATA_SCOPE_CUSTOM = "9";
	
//	public Office getOffice() {
//		return office;
//	}
//	public void setOffice(Office office) {
//		this.office = office;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getDataScope() {
		return dataScope;
	}
	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}
	public String getUseable() {
		return useable;
	}
	public void setUseable(String useable) {
		this.useable = useable;
	}
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
	public List<Menu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	

	public List<String> getMenuIdList() {
		List<String> menuIdList = Lists.newArrayList();
		for (Menu menu : menuList) {
			menuIdList.add(menu.getId()+"");
		}
		return menuIdList;
	}

	public void setMenuIdList(List<String> menuIdList) {
		menuList = Lists.newArrayList();
		for (String menuId : menuIdList) {
			Menu menu = new Menu();
			menu.setId(Long.valueOf(menuId));
			menuList.add(menu);
		}
	}
	public String getMenuIds() {
		return StringUtils.join(getMenuIdList(), ",");
	}
	
	public void setMenuIds(String menuIds) {
		menuList = Lists.newArrayList();
		if (menuIds != null){
			String[] ids = StringUtils.split(menuIds, ",");
			setMenuIdList(Lists.newArrayList(ids));
		}
	}
//	public List<Office> getOfficeList() {
//		return officeList;
//	}
//	public void setOfficeList(List<Office> officeList) {
//		this.officeList = officeList;
//	}
//	
//	public List<String> getOfficeIdList() {
//		List<String> officeIdList = Lists.newArrayList();
//		for (Office office : officeList) {
//			officeIdList.add(office.getId()+"");
//		}
//		return officeIdList;
//	}
//
//	public void setOfficeIdList(List<String> officeIdList) {
//		officeList = Lists.newArrayList();
//		for (String officeId : officeIdList) {
//			Office office = new Office();
//			office.setId(Long.valueOf(officeId));
//			officeList.add(office);
//		}
//	}
//
//	public String getOfficeIds() {
//		return StringUtils.join(getOfficeIdList(), ",");
//	}
//	
//	public void setOfficeIds(String officeIds) {
//		officeList = Lists.newArrayList();
//		if (officeIds != null){
//			String[] ids = StringUtils.split(officeIds, ",");
//			setOfficeIdList(Lists.newArrayList(ids));
//		}
//	}
	
}
