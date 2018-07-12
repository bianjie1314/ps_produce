package com.ps.produce.base.entity.query.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.google.common.collect.Maps;
import com.ps.produce.base.entity.BaseEntity;
import com.ps.produce.system.entity.User;



public class OrderQuery implements Serializable{
	private String keyword;
	private String time;
	private  String orderUsername;
	private Integer statu;
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getOrderUsername() {
		return orderUsername;
	}
	public void setOrderUsername(String orderUsername) {
		this.orderUsername = orderUsername;
	}
	public Integer getStatu() {
		return statu;
	}
	public void setStatu(Integer statu) {
		this.statu = statu;
	}
	
	
}
