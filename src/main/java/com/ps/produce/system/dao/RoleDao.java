package com.ps.produce.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ps.produce.base.dao.BaseDao;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.system.entity.Role;

public interface RoleDao extends BaseDao<Role, PageBean<Role>>{

	List<Role> findAllList(PageBean<Role> pageBean);

	void deleteRoleMenu(Role role);

	void insertRoleMenu(Role role);

	void deleteRoleOffice(Role role);

	void insertRoleOffice(Role role);

	List<Role> findUserRoleList(Long userId);

	void deleteBatch(@Param("ids") List<String> ids, @Param("delFlag") String delFlag);

}
