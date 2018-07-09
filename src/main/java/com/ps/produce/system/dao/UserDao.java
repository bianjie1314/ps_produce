package com.ps.produce.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ps.produce.base.dao.BaseDao;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.system.entity.User;

public interface UserDao extends BaseDao<User, PageBean<User>>{

	User findByLoginName(String username);

	void saveByLogin(User user);

	void insert(User user);
	
	void deleteUserRole(User user);
	void saveOSInfo(User user);
	
	void insertUserRole(User user);

	void deleteBatch(@Param("ids") List<String> ids, @Param("delFlag") String delFlag);

	List<User> getUserAll(String username);

	User getById(Long id);

	void updatePassword(User oldUser);

}
