package com.ps.produce.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.ps.produce.base.dao.BaseDao;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.shiro.ShiroUser;
import com.ps.produce.support.pair.RoleDataScope;
import com.ps.produce.support.utils.StringUtils;
import com.ps.produce.system.dao.UserDao;
import com.ps.produce.system.entity.Office;
import com.ps.produce.system.entity.Role;

/**
 * 基础Service
 * */
public abstract class BaseService<D extends BaseDao<T, PageBean<T>>,T> {
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	
	@Autowired
	protected UserDao userDao;
	
	/**
	 * 根据主键值获取对象
	 * @param id
	 * */
	public T get(Long id){
		return dao.get(id);
	}

	/**
	 * 查找是否存在
	 * @param queryModel 查询条件
	 * */
	public boolean isExist(PageBean<T> queryModel){
		return dao.isExist(queryModel)>0;
	}

	/** 
	 * 保存
	 * @param entity 保存对象
	 * @throws Exception
	 * */
	public boolean save(T entity) throws Exception{
		return dao.save(entity)>0;
	}

	/** 
	 * 更新 
	 * @param entity 修改对象
	 * @throws Exception
	 * */
	public boolean update(T entity) throws Exception{
		return dao.update(entity)>0;
	}

	/**
	 * 删除 
	 * @param entity 删除对象
	 * @throws Exception
	 * */
	public boolean delete(T entity) throws Exception{
		return dao.delete(entity)>0;
	}

	/**
	 * 分页查询
	 * @param queryModel 查询条件
	 *  */
	public List<T> findByPage(PageBean<T> queryModel){
		return dao.findByPage(queryModel);
	}

	/**
	 * 统计
	 * @param queryModel 查询条件
	 *  */
	public int count(PageBean<T> queryModel){
		return dao.count(queryModel);
	}

	/**
	 * 查询
	 * @param queryModel 查询条件
	 *  */
	public List<T> query(PageBean<T> queryModel){
		return dao.query(queryModel);
	}
	
	
	/**
	 * 数据范围过滤
	 * @param user 当前用户对象，通过“entity.getCurrentUser()”获取
	 * @param officeAlias 机构表别名
	 * @param userAlias 用户表别名
	 * @return 标准连接条件对象
	 */
	public static String dataScopeFilter(ShiroUser user, String officeAlias, String userAlias) {

		StringBuilder sqlString = new StringBuilder();
		
		// 进行权限过滤，多个角色权限范围之间为或者关系。
		List<String> dataScope = Lists.newArrayList();
		
		String oa = officeAlias;
		// 超级管理员，跳过权限过滤
		if (!user.isAdmin()){
			boolean isDataScopeAll = false;
			for (Role r : user.getRoles()){
				if (!dataScope.contains(r.getDataScope()) && StringUtils.isNotBlank(oa)){
					if (RoleDataScope.DATA_SCOPE_ALL.value().equals(r.getDataScope())){
						isDataScopeAll = true;
					}
					else if (RoleDataScope.DATA_SCOPE_COMPANY_AND_CHILD.value().equals(r.getDataScope())){
						sqlString.append(" OR " + oa + ".id = '" + Office.companyId + "'");
						sqlString.append(" OR " + oa + ".parent_ids LIKE '" + Office.companyId + ",%'");
					}
					else if (RoleDataScope.DATA_SCOPE_COMPANY.value().equals(r.getDataScope())){
						sqlString.append(" OR " + oa + ".id = '" + Office.companyId + "'");
						// 包括本公司下的部门 （type=1:公司；type=2：部门）
						sqlString.append(" OR (" + oa + ".parent_id = '" + Office.companyId + "') " );
					}
					else if (RoleDataScope.DATA_SCOPE_OFFICE_AND_CHILD.value().equals(r.getDataScope())){
						sqlString.append(" OR " + oa + ".id = '" + user.getOffice().getId() + "'");
						sqlString.append(" OR " + oa + ".parent_ids LIKE '" + user.getOffice().getParentIds() + user.getOffice().getId() + ",%'");
					}
					else if (RoleDataScope.DATA_SCOPE_OFFICE.value().equals(r.getDataScope())){
						sqlString.append(" OR " + oa + ".id = '" + user.getOffice().getId() + "'");
					}
					//else if (Role.DATA_SCOPE_SELF.equals(r.getDataScope())){
					dataScope.add(r.getDataScope());
				}
			}
			// 如果没有全部数据权限，并设置了用户别名，则当前权限为本人；如果未设置别名，当前无权限为已植入权限
			if (!isDataScopeAll){
				if (StringUtils.isNotBlank(userAlias)){
					for (String ua : StringUtils.split(userAlias, ",")){
						sqlString.append(" OR " + ua + ".id = '" + user.getId() + "'");
					}
				}else {
					//sqlString.append(" OR " + oa + ".id  = " + user.getOffice().getId());
					sqlString.append(" OR " + oa + ".id IS NULL");
				}
			}else{
				// 如果包含全部权限，则去掉之前添加的所有条件，并跳出循环。
				sqlString = new StringBuilder();
			}
		}
		System.out.println(sqlString);
		if (StringUtils.isNotBlank(sqlString.toString())){
			return " AND (" + sqlString.substring(4) + ")";
		}
		return "";
	}

	/**
	 * 数据范围过滤（符合业务表字段不同的时候使用，采用exists方法）
	 * @param entity 当前过滤的实体类
	 * @param sqlMapKey sqlMap的键值，例如设置“dsf”时，调用方法：${sqlMap.sdf}
	 * @param officeWheres office表条件，组成：部门表字段=业务表的部门字段
	 * @param userWheres user表条件，组成：用户表字段=业务表的用户字段
	 * @example
	 * 		dataScopeFilter(user, "dsf", "id=a.office_id", "id=a.create_by");
	 * 		dataScopeFilter(entity, "dsf", "code=a.jgdm", "no=a.cjr"); // 适应于业务表关联不同字段时使用，如果关联的不是机构id是code。
	 */
//	public static String dataScopeFilter(User user, String sqlMapKey, String officeWheres, String userWheres) {
//
//		// 如果是超级管理员，则不过滤数据
//		if (user.isAdmin()) {
//			return "";
//		}
//
//		// 数据范围（1：所有数据；2：所在公司及以下数据；3：所在公司数据；4：所在部门及以下数据；5：所在部门数据；8：仅本人数据；9：按明细设置）
//		StringBuilder sqlString = new StringBuilder();
//		
//		// 获取到最大的数据权限范围
//		int dataScopeInteger = 8;
//		for (Role r : user.getRoleList()){
//			int ds = Integer.valueOf(r.getDataScope());
//			if (ds == 9){
//				dataScopeInteger = ds;
//				break;
//			}else if (ds < dataScopeInteger){
//				dataScopeInteger = ds;
//			}
//		}
//		String dataScopeString = String.valueOf(dataScopeInteger);
//		
//		// 生成部门权限SQL语句
//		for (String where : StringUtils.split(officeWheres, ",")){
//			if (RoleDataScope.DATA_SCOPE_COMPANY_AND_CHILD.value().equals(dataScopeString)){
//				// 包括本公司下的部门 （type=1:公司；type=2：部门）
//				sqlString.append(" AND EXISTS (SELECT 1 FROM SYS_OFFICE");
//				sqlString.append(" WHERE ");
//				sqlString.append(" (id = '" + Office.companyId + "'");
//				sqlString.append(" OR parent_ids LIKE '" + Office.companyId + ",%')");
//				sqlString.append(" AND " + where +")");
//			}
////			else if (RoleDataScope.DATA_SCOPE_COMPANY.value().equals(dataScopeString)){
////				sqlString.append(" AND EXISTS (SELECT 1 FROM SYS_OFFICE");
////				sqlString.append(" WHERE ");
////				sqlString.append(" id = '" + Office.companyId + "'");
////				sqlString.append(" AND " + where +")");
////			}
//			else if (RoleDataScope.DATA_SCOPE_OFFICE_AND_CHILD.value().equals(dataScopeString)){
//				sqlString.append(" AND EXISTS (SELECT 1 FROM SYS_OFFICE");
//				sqlString.append(" WHERE (id = '" + user.getOffice().getId() + "'");
//				sqlString.append(" OR parent_ids LIKE '" + user.getOffice().getParentIds() + user.getOffice().getId() + ",%')");
//				sqlString.append(" AND " + where +")");
//			}
////			else if (RoleDataScope.DATA_SCOPE_OFFICE.value().equals(dataScopeString)){
////				sqlString.append(" AND EXISTS (SELECT 1 FROM SYS_OFFICE");
////				sqlString.append(" WHERE id = '" + user.getOffice().getId() + "'");
////				sqlString.append(" AND " + where +")");
////			}
//
//		}
//		// 生成个人权限SQL语句
//		for (String where : StringUtils.split(userWheres, ",")){
//			if (RoleDataScope.DATA_SCOPE_SELF.equals(dataScopeString)){
//				sqlString.append(" AND EXISTS (SELECT 1 FROM sys_user");
//				sqlString.append(" WHERE id='" + user.getId() + "'");
//				sqlString.append(" AND " + where + ")");
//			}
//		}
//
////		System.out.println("dataScopeFilter: " + sqlString.toString());
//
//		// 设置到自定义SQL对象
//		return sqlString.toString();
//		
//	}
}
