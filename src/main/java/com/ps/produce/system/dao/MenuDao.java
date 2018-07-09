package com.ps.produce.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ps.produce.base.dao.BaseDao;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.system.entity.Menu;

public interface MenuDao extends BaseDao<Menu, PageBean<Menu>>{

	List<Menu> findAllList(Menu menu);

	List<Menu> findByUserId(Menu m);

	List<Menu> findAllMenu();

	List<Menu> findByParentIdsLike(Menu m);

	void updateParentIds(Menu e);

	void deleteBatch(@Param("ids") List<String> ids, @Param("delFlag") String delFlag);

}
