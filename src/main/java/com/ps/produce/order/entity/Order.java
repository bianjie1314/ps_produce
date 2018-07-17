/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ps.produce.order.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 订单
 * @author
 * @version 
 */
public class Order  {
	private Long id;
	private String orderNo;
	private String orderUsername;
	@DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
	private Date  orderCreateTime;
	private String expressUsername;
	private String expressTel;
	private String expressAddress;
	private String expressZipCode;
	private String expressFixedPhone;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Date getOrderCreateTime() {
		return orderCreateTime;
	}
	public void setOrderCreateTime(Date orderCreateTime) {
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
	public String getExpressZipCode() {
		return expressZipCode;
	}
	public void setExpressZipCode(String expressZipCode) {
		this.expressZipCode = expressZipCode;
	}
	public String getExpressFixedPhone() {
		return expressFixedPhone;
	}
	public void setExpressFixedPhone(String expressFixedPhone) {
		this.expressFixedPhone = expressFixedPhone;
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
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
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
	
	

}