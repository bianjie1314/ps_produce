package com.ps.produce.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ps.produce.base.dao.BaseDao;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.system.entity.Flow;
import com.ps.produce.system.entity.FlowLine;

public interface FlowDao extends BaseDao<Flow, PageBean<Flow>>{

	void saveFlowLine(@Param("flowLines") List<FlowLine> flowLines);

	List<FlowLine> getFlowLine(Flow flow);

	List<Flow> findAll(PageBean<Flow> pageBean);

	void deleteBatch(@Param("ids") List<String> ids, @Param("delFlag") String delFlag);

	FlowLine getFirstApprover(Flow flow);

	void deleteFlowLine(Long flowId);

	FlowLine getByPrevNodeId(String prevNodeId);

}
