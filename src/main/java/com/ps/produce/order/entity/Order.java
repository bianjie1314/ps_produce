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
public class Order  {
	private Long id;
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
	private Date updateDate;
	private Date createDate;
	private String remarks;
	private int printCount;
	private String lastPrintDate;
	
	
	public int getPrintCount() {
		return printCount;
	}
	public void setPrintCount(int printCount) {
		this.printCount = printCount;
	}
	public String getLastPrintDate() {
		return lastPrintDate;
	}
	public void setLastPrintDate(String lastPrintDate) {
		this.lastPrintDate = lastPrintDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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