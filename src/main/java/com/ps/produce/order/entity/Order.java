/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ps.produce.order.entity;

import java.util.List;

import com.ps.produce.base.entity.AbstractEntity;

/**
 * 订单
 * @author
 * @version 
 */
public class Order extends AbstractEntity {
	
	private String orderNo;
	private String orderUsername;
	private String  orderCreateTime;
	private String expressUsername;
	private String expressTel;
	private String expressAddress;
	private Integer status;
	private Long optUseranme;
	private String optUserId;
	private String callbackUrl;
	private List<Product> products;
	private String expressName;
	private String expressNo;
	
	public String getExpressName() {
		return expressName;
	}
	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderUsername() {
		return orderUsername;
	}
	public void setOrderUsername(String orderUsername) {
		this.orderUsername = orderUsername;
	}
	public String getOrderCreateTime() {
		return orderCreateTime;
	}
	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}
	public String getExpressUsername() {
		return expressUsername;
	}
	public void setExpressUsername(String expressUsername) {
		this.expressUsername = expressUsername;
	}
	public String getExpressTel() {
		return expressTel;
	}
	public void setExpressTel(String expressTel) {
		this.expressTel = expressTel;
	}
	public String getExpressAddress() {
		return expressAddress;
	}
	public void setExpressAddress(String expressAddress) {
		this.expressAddress = expressAddress;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getOptUseranme() {
		return optUseranme;
	}
	public void setOptUseranme(Long optUseranme) {
		this.optUseranme = optUseranme;
	}
	public String getOptUserId() {
		return optUserId;
	}
	public void setOptUserId(String optUserId) {
		this.optUserId = optUserId;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	
	

}