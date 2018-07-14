package com.ps.produce.order.dto;

public class OrderParam {
private String order_no;

private String carriers;

private String state;

private String tracking_number;

public String getOrder_no() {
	return order_no;
}

public void setOrder_no(String order_no) {
	this.order_no = order_no;
}

public String getCarriers() {
	return carriers;
}

public void setCarriers(String carriers) {
	this.carriers = carriers;
}



public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public String getTracking_number() {
	return tracking_number;
}

public void setTracking_number(String tracking_number) {
	this.tracking_number = tracking_number;
}



}
