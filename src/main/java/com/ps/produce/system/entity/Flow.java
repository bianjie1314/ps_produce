package com.ps.produce.system.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.ps.produce.base.entity.AbstractEntity;
/**
 * 审批流程
 * @author yuyanzheng
 * 
 * 2018年1月5日
 */
public class Flow extends AbstractEntity{
	private String name;  //流程名称
	private List<FlowLine> flowLines = Lists.newArrayList();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FlowLine> getFlowLines() {
		return flowLines;
	}

	public void setFlowLines(List<FlowLine> flowLines) {
		this.flowLines = flowLines;
	}
}
