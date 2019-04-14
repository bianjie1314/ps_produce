package com.ps.produce.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.base.service.BaseService;
import com.ps.produce.support.ISecurityUtils;
import com.ps.produce.system.dao.OfficeDao;
import com.ps.produce.system.entity.Office;

@Service
public class OfficeService extends BaseService<OfficeDao, Office>{
    @Autowired
    private OfficeDao officeDao;
	public PageBean<Office> findOffice(PageBean<Office> officePageBean) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		officePageBean.getSqlMap().put("dsf", dataScopeFilter(ISecurityUtils.getCurrUser(), "o", "a"));
		List<Office> offices = dao.findByPage(officePageBean);
		officePageBean.setContent(offices);
		return officePageBean;
	}

	public List<Office> findList(Boolean isAll) {
		if (isAll != null && isAll){
			return ISecurityUtils.getOfficeAllList();
		}else{
			return ISecurityUtils.getOfficeList();
		}
	}

	
	public List<Office> findList(Office office){
		if(office != null){
			office.setParentIds(office.getParentIds()+"%");
			return dao.findByParentIdsLike(office);
		}
		return  new ArrayList<Office>();
	}

	/**
	 * 添加 修改office
	 * @param office
	 * @throws Exception 
	 */
	public void saveOffice(Office office) throws Exception {
		Date createDate = new Date();
		if (office.getId() != null) {
			office = updateOffice(office);
		} else {
			office.setCreateDate(createDate);
		}
		office.setUpdateDate(createDate);
		//添加根节点
		if (office.getParent() == null) {
			Office parent = new Office();
			parent.setId(0l);
			parent.setParentIds("");
			office.setParent(parent);
		} else {
			office.setParent(dao.get(office.getParentId()));
		}
		
		String oldParentIds = office.getParentIds();
		//设置新的父节点串
		office.setParentIds(office.getParent().getParentIds() + office.getParentId() + ",");
		//新增或修改
		if (office.getId() == null) {
			dao.save(office);
		} else {
			dao.update(office);
		}
		
		//修改子节点的父节点串
		Office o = new Office();
		o.setParentIds("%,"+office.getId()+",%");
		List<Office> childs = dao.findByParentIdsLike(o);
		for (Office c : childs) {
			c.setParentIds(o.getParentIds().replace(oldParentIds, office.getParentIds()));
			dao.updateParentIds(c);
		}
	}

	private Office updateOffice(Office newOffice) {
		Office office = dao.get(newOffice.getId());
		office.setCode(newOffice.getCode());
		office.setEmail(newOffice.getEmail());
		office.setMaster(newOffice.getMaster());
		office.setPhone(newOffice.getPhone());
		office.setName(newOffice.getName());
		office.setParent(newOffice.getParent());
		return office;
	}

	public List<Office> getOffice() {
		
		List<Office> list=dao.getOffice();
		List<Office> newList=Lists.newArrayList();
		for(Office office:list) {
			if(office.getParentIds().split(",").length>2) {
				newList.add(office);
			}
		}
		return newList;
	}

	public Office find(long officeId) {
		// TODO Auto-generated method stub
		return officeDao.find(officeId);
	}
}
