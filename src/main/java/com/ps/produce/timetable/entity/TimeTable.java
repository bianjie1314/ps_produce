package com.ps.produce.timetable.entity;

public class TimeTable {
private int id;
//课程id
private int courseId;
//班级id
private long clazzId;
//专业id
private int officeId;
//班级
private String classroom;
//星期几
private int day;
//排序（第几节）
private int sort;
//删除标识
private int delFlag;
//学期
private int term;
//教师id
private long teaId;




public long getTeaId() {
	return teaId;
}
public void setTeaId(long teaId) {
	this.teaId = teaId;
}
public int getTerm() {
	return term;
}
public void setTerm(int term) {
	this.term = term;
}
public long getClazzId() {
	return clazzId;
}
public void setClazzId(long clazzId) {
	this.clazzId = clazzId;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getCourseId() {
	return courseId;
}
public void setCourseId(int courseId) {
	this.courseId = courseId;
}
public int getOfficeId() {
	return officeId;
}
public void setOfficeId(int officeId) {
	this.officeId = officeId;
}
public String getClassroom() {
	return classroom;
}
public void setClassroom(String classroom) {
	this.classroom = classroom;
}
public int getDay() {
	return day;
}
public void setDay(int day) {
	this.day = day;
}
public int getSort() {
	return sort;
}
public void setSort(int sort) {
	this.sort = sort;
}
public int getDelFlag() {
	return delFlag;
}
public void setDelFlag(int delFlag) {
	this.delFlag = delFlag;
}



}
