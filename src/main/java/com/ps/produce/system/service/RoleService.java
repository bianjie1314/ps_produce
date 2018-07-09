package com.ps.produce.system.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ps.produce.base.entity.BaseEntity;
import com.ps.produce.base.service.BaseService;
import com.ps.produce.system.dao.RoleDao;
import com.ps.produce.system.entity.Role;

@Service
public class RoleService extends BaseService<RoleDao, Role>{
	
	/**
	 * 更新role
	 * @param role
	 * @throws Exception 
	 */
	public void saveRole(Role role) throws Exception {
		Date now = new Date();
		role.setUpdateDate(now);
		if(role.getId() == null) {
			//新增
			role.setCreateDate(now);
			dao.save(role);
		} else {
			//修改
			dao.update(role);
		}
		
		// 更新角色与菜单关联
		dao.deleteRoleMenu(role);
		if (role.getMenuList().size() > 0){
			dao.insertRoleMenu(role);
		}
	}

	/**
	 * 获取用户角色
	 * @param id
	 * @return
	 */
	public List<Role> findUserRoleList(Long userId) {
		return dao.findUserRoleList(userId);
	}

	/**
	 * 批量删除角色
	 * @param ids
	 */
	public void deleteBatch(String ids) {
		dao.deleteBatch(Arrays.asList(ids.split(",")),BaseEntity.DEL_FLAG_DELETE);
	}
}
