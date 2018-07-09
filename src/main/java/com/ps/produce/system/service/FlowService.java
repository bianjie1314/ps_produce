package com.ps.produce.system.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.ps.produce.base.entity.BaseEntity;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.base.service.BaseService;
import com.ps.produce.exception.BusinessException;
import com.ps.produce.support.ResponseCode;
import com.ps.produce.support.utils.UUidUtil;
import com.ps.produce.system.dao.FlowDao;
import com.ps.produce.system.entity.Flow;
import com.ps.produce.system.entity.FlowLine;
import com.ps.produce.system.entity.User;
/**
 * 
 * @author yuyanzheng
 * 
 * 2018年1月5日
 */
@Service
public class FlowService extends BaseService<FlowDao, Flow>{

	/**
	 * 
	 * @param flow
	 * @param userIds
	 */
	@Transactional(rollbackFor=Exception.class)
	public void saveFlow(Flow flow, Long[] userIds) {
		if (userIds == null || userIds.length == 0) {
			throw new BusinessException(ResponseCode.ERROR);
		}
		Date now = new Date();
		flow.setUpdateDate(now);
		if (flow.getId() != null) {
			dao.update(flow);
		} else {
			//保存审批流程
			flow.setCreateDate(now);
			dao.save(flow);
		}
		List<FlowLine> flowLines = Lists.newArrayList();
		FlowLine flowLine = null;
		String prevNodeId = null;
		String currentNodeId = UUidUtil.getUuid();;
		String nextNodeId = UUidUtil.getUuid();;
		for (int i=0; i<userIds.length; i++) {
			flowLine = new FlowLine();
			flowLine.setFlowId(flow.getId());
			flowLine.setPrevNode(prevNodeId);
			flowLine.setNodeId(currentNodeId);
			flowLine.setNextNode(nextNodeId);
			
			prevNodeId = currentNodeId;
			currentNodeId = nextNodeId;
			nextNodeId = UUidUtil.getUuid();
			if (i == userIds.length-2) {
				nextNodeId = null;
			}
			
			User current = new User();
			current.setId(userIds[i]);
			flowLine.setCurrent(current);
			flowLine.setUpdateDate(now);
			flowLine.setCreateDate(now);
			flowLines.add(flowLine);
		}
		
		dao.deleteFlowLine(flow.getId());
		//保存审批线
		if (! flowLines.isEmpty()) {
			dao.saveFlowLine(flowLines);
		}
	}

	/**
	 * 获取所有设置的审批流程
	 * @return
	 */
	public List<Flow> findAll() {
		PageBean<Flow> pageBean = new PageBean<Flow>();
		return dao.findAll(pageBean);
	}

	/**
	 * 批量删除
	 * @param ids
	 */
	public void deleteBatch(String ids) {
		dao.deleteBatch(Arrays.asList(ids.split(",")),BaseEntity.DEL_FLAG_DELETE);
	}
	
}
