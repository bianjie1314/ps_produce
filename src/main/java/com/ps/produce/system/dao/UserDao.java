package com.ps.produce.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import com.ps.produce.base.dao.BaseDao;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.system.entity.Distribution;
import com.ps.produce.system.entity.User;

public interface UserDao extends BaseDao<User, PageBean<User>>{

	User findByLoginName(String username);

	void saveByLogin(User user);

	void insert(User user);
	
	void deleteUserRole(User user);
//	void saveOSInfo(User user);
	
	void insertUserRole(User user);

	void deleteBatch(@Param("ids") List<String> ids, @Param("delFlag") String delFlag);

	List<User> getUserAll(String username);

	User getById(Long id);

	void updatePassword(User oldUser);
    
	public List<User> findByRoleId(PageBean<User> pageBean,@Param("roleId")int roleId);

	void insertRoleAndUser(@Param("id")Long id, @Param("roleId")int roleId);

	List<User> findCou();

	int distribution(Distribution d);

	List<Map<String,Object>> queryTea(@Param("courseId")long courseId);

	int findD(Distribution d);
    List<Map<String,Object>> queryclazz(Distribution d);
    
	List<Map<String, Object>> queryTea1(@Param("id")long id, @Param("courseId")long courseId);

	Object updateDis(Distribution d);

	int findDis(Distribution d);

    Integer findOne(@Param("username") String username, @Param("roleId")Integer roleId);
}
