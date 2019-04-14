package com.ps.produce.score.entity;

import com.ps.produce.base.entity.AbstractEntity;

public class Score extends AbstractEntity{
	//分数
   private long score;
   //课程id
   private long courseId;
   //学生id
   private long stuId;
   //操作员id
   private long optId;
   //专业id
   private long officeId;
   //班级id
   private long clazzId;
   //状态 0 及格。1不及格
   private int state;
   
   public final static int FIRST=1;
   
   public final static int REBUILD=3;
   
   public final static int MAKE_UP=2;
   
   

public int getState() {
	return state;
}

public void setState(int state) {
	this.state = state;
}

public long getClazzId() {
	return clazzId;
}

public void setClazzId(long clazzId) {
	this.clazzId = clazzId;
}

public long getScore() {
	return score;
}

public void setScore(long score) {
	this.score = score;
}

public long getCourseId() {
	return courseId;
}

public void setCourseId(long courseId) {
	this.courseId = courseId;
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

public long getOfficeId() {
	return officeId;
}

public void setOfficeId(long officeId) {
	this.officeId = officeId;
}
   
   
	
	
}
