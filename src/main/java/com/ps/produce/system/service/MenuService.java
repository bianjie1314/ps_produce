package com.ps.produce.system.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ps.produce.base.entity.BaseEntity;
import com.ps.produce.base.service.BaseService;
import com.ps.produce.support.ISecurityUtils;
import com.ps.produce.system.dao.MenuDao;
import com.ps.produce.system.entity.Menu;

@Service
public class MenuService extends BaseService<MenuDao, Menu>{

	public List<Menu> findAllMenu() {
		return ISecurityUtils.getMenuList();
	}

	/**
	 * 修改menu
	 * @param menu
	 * @throws Exception 
	 */
	public void updateMenu(Menu menu) throws Exception {
		// 获取父节点实体
		menu.setParent(dao.get(menu.getParent().getId()));
		
		// 设置新的父节点串
		menu.setParentIds(menu.getParent().getParentIds()+menu.getParent().getId()+",");
		
		// 获取修改前的parentIds，用于更新子节点的parentIds
		String oldParentIds = null;
		if (menu.getId() != null) {
			Menu oldMenu = dao.get(menu.getId());
			oldParentIds = oldMenu.getParentIds(); 
			dao.update(menu);
		} else {
			dao.save(menu);
			return;
		}
		
		// 更新子节点 parentIds
		Menu m = new Menu();
		m.setParentIds("%,"+menu.getId()+",%");
		List<Menu> list = dao.findByParentIdsLike(m);
		for (Menu e : list){
			e.setParentIds(e.getParentIds().replace(oldParentIds, menu.getParentIds()));
			dao.updateParentIds(e);
		}
	}

	/**
	 * 批量删除
	 * @param ids
	 */
	public void deleteBatch(String ids) {
		dao.deleteBatch(Arrays.asList(ids.split(",")),BaseEntity.DEL_FLAG_DELETE);
	}
	

}
