/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ps.produce.system.entity;

import java.util.List;

import com.ps.produce.base.entity.AbstractEntity;

/**
 * 菜单Entity
 */
public class Menu extends AbstractEntity {

	private Menu parent;	// 父级菜单
	private String parentIds; // 所有父级编号
	private String name; 	// 名称
	private String href; 	// 链接
	private String target; 	// 目标（ mainFrame、_blank、_self、_parent、_top）
	private String icon; 	// 图标
	private Integer sort; 	// 排序
	private String isShow; 	// 是否在菜单中显示（1：显示；0：不显示）
	private String permission; // 权限标识
	
	private Long userId;
	
	public Menu() {
	}
	
	public Menu(Long id) {
		super(id);
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public String getParentIds() {
		return parentIds;
	}

	public Long getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : 0l;
	}
	
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public static Long getRootId(){
		return 1l;
	}

	public static void sortList(List<Menu> list, List<Menu> sourcelist, Long parentId, boolean cascade) {
		for (int i=0; i<sourcelist.size(); i++){
			Menu e = sourcelist.get(i);
			if (e.getParent()!=null && e.getParent().getId()!=null&& e.getParent().getId().toString().equals(parentId.toString())){
				list.add(e);
				if (cascade){
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j=0; j<sourcelist.size(); j++){
						Menu child = sourcelist.get(j);
						if (child.getParent()!=null && child.getParent().getId()!=null
								&& child.getParent().getId().equals(e.getId())){
							sortList(list, sourcelist, e.getId(), true);
							break;
						}
					}
				}
			}
		}
	}
}