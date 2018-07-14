/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ps.produce.order.entity;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ps.produce.base.entity.AbstractEntity;

/**
 * 订单
 * @author
 * @version 
 */
public class OrderLog  {
	private Long id;
	private String optUsername;
	private Long optUserId;
	private String remarks;
	private int status;//订单状态
	private int flag;//操作状态  0 成功 1失败
	private Date createDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOptUsername() {
		return optUsername;
	}
	public void setOptUsername(String optUsername) {
		this.optUsername = optUsername;
	}
	public Long getOptUserId() {
		return optUserId;
	}
	public void setOptUserId(Long optUserId) {
		this.optUserId = optUserId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}