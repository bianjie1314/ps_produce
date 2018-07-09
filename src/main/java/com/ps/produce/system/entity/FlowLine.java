package com.ps.produce.system.entity;

import com.ps.produce.base.entity.AbstractEntity;
/**
 * 审批流程线
 * @author yuyanzheng
 * 
 * 2018年1月5日
 */
public class FlowLine extends AbstractEntity{
	private Long flowId; //流程id
	private String nodeId; //唯一编号
	private String prevNode;  //前一个节点
	private User current; //当前审批人
	private String nextNode;    //下一个节点
	
	public Long getFlowId() {
		return flowId;
	}
	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getPrevNode() {
		return prevNode;
	}
	public void setPrevNode(String prevNode) {
		this.prevNode = prevNode;
	}
	public User getCurrent() {
		return current;
	}
	public void setCurrent(User current) {
		this.current = current;
	}
	public String getNextNode() {
		return nextNode;
	}
	public void setNextNode(String nextNode) {
		this.nextNode = nextNode;
	}
}
