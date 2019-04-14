package com.ps.produce.system.entity;

import com.ps.produce.base.entity.AbstractEntity;
import com.ps.produce.base.entity.BaseEntity;

public class Course extends AbstractEntity {
//课程编号
private String courseNo;
//课程名称
private String courseName;
//教师id
private int   tId;

public String getCourseNo() {
	return courseNo;
}
public void setCourseNo(String courseNo) {
	this.courseNo = courseNo;
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public int gettId() {
	return tId;
}
public void settId(int tId) {
	this.tId = tId;
}

}
