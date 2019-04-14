package com.ps.produce.leave.entity;

import java.util.Date;

import com.ps.produce.base.entity.AbstractEntity;





public class Leave extends AbstractEntity {
//学生id
private Long stuId;
//操作员id
private Long optId;
//假条状态
private int status;
//请假开始时间
private String startTime;
//请假结束时间
private String endTime;
//驳回理由
private String rejection;
//学生姓名
private String stuName;
//c操作员姓名
private String optName;

public String getStuName() {
	return stuName;
}
public void setStuName(String stuName) {
	this.stuName = stuName;
}
public String getOptName() {
	return optName;
}
public void setOptName(String optName) {
	this.optName = optName;
}

public Long getStuId() {
	return stuId;
}
public void setStuId(Long stuId) {
	this.stuId = stuId;
}
public Long getOptId() {
	return optId;
}
public void setOptId(Long optId) {
	this.optId = optId;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}


public String getStartTime() {
	return startTime;
}
public void setStartTime(String startTime) {
	this.startTime = startTime;
}
public String getEndTime() {
	return endTime;
}
public void setEndTime(String endTime) {
	this.endTime = endTime;
}
public String getRejection() {
	return rejection;
}
public void setRejection(String rejection) {
	this.rejection = rejection;
}


}
