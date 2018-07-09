package com.ps.produce.base.entity;

public class BaseEntity {

	protected String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）

	public BaseEntity() {
		this.delFlag = DEL_FLAG_NORMAL;
	}
	
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * 删除标记（0：正常；1：删除；2：审核；）
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";
}
