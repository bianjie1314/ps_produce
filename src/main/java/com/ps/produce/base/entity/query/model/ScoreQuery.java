package com.ps.produce.base.entity.query.model;

import java.io.Serializable;

public class ScoreQuery implements Serializable {
private long courseId;

private long officeId;

private long stuId;

private String score;

private String remarks;

private long clazzId;

private int state;




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

public long getCourseId() {
	return courseId;
}

public void setCourseId(long courseId) {
	this.courseId = courseId;
}

public long getOfficeId() {
	return officeId;
}

public void setOfficeId(long officeId) {
	this.officeId = officeId;
}



public long getStuId() {
	return stuId;
}

public void setStuId(long stuId) {
	this.stuId = stuId;
}





public String getScore() {
	return score;
}

public void setScore(String score) {
	this.score = score;
}

public String getRemarks() {
	return remarks;
}

public void setRemarks(String remarks) {
	this.remarks = remarks;
}


}
