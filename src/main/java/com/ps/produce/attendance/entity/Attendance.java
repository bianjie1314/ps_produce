package com.ps.produce.attendance.entity;

import com.ps.produce.base.entity.AbstractEntity;

public class Attendance extends AbstractEntity {
	//学生id
   private long stuId;
   //操作员id
   private long optId;
   //学生姓名
   private String stuName;
   //缺勤时间
   private String absenceTime;
   //专业id
   private long officeId;
   //z专业名称
   private String officeName;
   //课程名称
   private String courseName;
   //课程id
   private long courseId;
   //班级id
   private long clazzId;
   
   
   
   
   
public long getClazzId() {
	return clazzId;
}

public void setClazzId(long clazzId) {
	this.clazzId = clazzId;
}

public String getCourseName() {
	return courseName;
}

public void setCourseName(String courseName) {
	this.courseName = courseName;
}

public long getCourseId() {
	return courseId;
}

public void setCourseId(long courseId) {
	this.courseId = courseId;
}

public String getOfficeName() {
	return officeName;
}

public void setOfficeName(String officeName) {
	this.officeName = officeName;
}

public long getStuId() {
	return stuId;
}

public void setStuId(long stuId) {
	this.stuId = stuId;
}

public long getOptId() {
	return optId;
}

public void setOptId(long optId) {
	this.optId = optId;
}

public String getStuName() {
	return stuName;
}

public void setStuName(String stuName) {
	this.stuName = stuName;
}

public String getAbsenceTime() {
	return absenceTime;
}

public void setAbsenceTime(String absenceTime) {
	this.absenceTime = absenceTime;
}

public long getOfficeId() {
	return officeId;
}

public void setOfficeId(long officeId) {
	this.officeId = officeId;
}
   
   
}
