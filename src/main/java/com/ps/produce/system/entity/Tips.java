package com.ps.produce.system.entity;

import com.ps.produce.base.entity.AbstractEntity;
/**
 * 系统提示
 * @author yuyanzheng
 * 
 * 2018年1月26日
 */
public class Tips extends AbstractEntity{
	private String name;
	private String content;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
