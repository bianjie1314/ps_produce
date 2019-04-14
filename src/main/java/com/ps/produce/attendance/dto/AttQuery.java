package com.ps.produce.attendance.dto;

public class AttQuery {
	private long id;
	private String keyword;
	private String CreateDate;
	private String absenceTime;
	private long  officeId;
	private String officeName;
	private long stuId;
	private String stuName;
	private String remarks;
	private String courseName;
	private long courseId;
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
	public long getOfficeId() {
		return officeId;
	}
	public void setOfficeId(long officeId) {
		this.officeId = officeId;
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
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}
	public String getAbsenceTime() {
		return absenceTime;
	}
	public void setAbsenceTime(String absenceTime) {
		this.absenceTime = absenceTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
}
