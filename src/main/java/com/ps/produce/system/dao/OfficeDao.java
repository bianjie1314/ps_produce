package com.ps.produce.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ps.produce.base.dao.BaseDao;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.system.entity.Office;

public interface OfficeDao extends BaseDao<Office, PageBean<Office>>{

	List<Office> findAllList(PageBean<Office> pageBean);

	List<Office> findList(PageBean<Office> pageBean);

	List<Office> findByParentIdsLike(Office office);

	void updateParentIds(Office c);

	List<Office> getOffice();

	Office find(@Param("officeId")long officeId);


}
