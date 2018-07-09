/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ps.produce.system.entity;

import com.ps.produce.base.entity.AbstractEntity;


/**
 * 机构Entity
 */
public class Office extends AbstractEntity {

	private Office parent;	// 父级编号
	private String parentIds; // 所有父级编号
	private String code; 	// 机构编码
	private String name; 	// 机构名称
//	private Integer sort;		// 排序
	private String master;  //负责人
	private String phone; 	// 电话
	private String email; 	// 邮箱
	private String useable;//是否可用
	
	public final static Long companyId=0l;//公司id
	
	public Office getParent() {
		return parent;
	}
	public void setParent(Office parent) {
		this.parent = parent;
	}
	public String getParentIds() {
		return parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUseable() {
		return useable;
	}
	public void setUseable(String useable) {
		this.useable = useable;
	}
	
	public Long getParentId() {
		Long id = null;
		if (parent != null){
			id = parent.getId();
		}
		return id!=null ? id : 0l;
	}
}