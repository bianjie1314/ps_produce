/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ps.produce.order.entity;

import com.ps.produce.base.entity.AbstractEntity;

/**
 * 订单
 * @author
 * @version 
 */
public class Order extends AbstractEntity {
	private String templateName;
	private String orderNo;
	private String orderUsername;
	private String  orderCreateTime;
	private String expressUsername;
	private String expressTel;
	private String expressAddress;
	private String productName;
	private String productColor;
	private String productSize;
	private Integer productQuantity;
	private String ean;//商品EAN 条形码
	private String images;
	private Integer status;
	private Long optUseranme;
	private String optUserId;
	private String callbackUrl;
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductColor() {
		return productColor;
	}
	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}
	public String getProductSize() {
		return productSize;
	}
	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}
	public Integer getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}
	public String getEan() {
		return ean;
	}
	public void setEan(String ean) {
		this.ean = ean;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
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