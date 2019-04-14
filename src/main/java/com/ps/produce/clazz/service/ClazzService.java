package com.ps.produce.clazz.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.ps.produce.attendance.dao.AttendanceDao;
import com.ps.produce.attendance.entity.Attendance;
import com.ps.produce.base.entity.BaseEntity;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.base.service.BaseService;
import com.ps.produce.clazz.dao.ClazzDao;
import com.ps.produce.clazz.entity.Clazz;
import com.ps.produce.exception.BusinessException;
import com.ps.produce.shiro.ShiroUser;
import com.ps.produce.support.CipherUtil;
import com.ps.produce.support.Digests;
import com.ps.produce.support.Encodes;
import com.ps.produce.support.ISecurityUtils;
import com.ps.produce.support.Password;
import com.ps.produce.system.dao.RoleDao;
import com.ps.produce.system.dao.UserDao;
import com.ps.produce.system.entity.Office;
import com.ps.produce.system.entity.User;

@Service
public class ClazzService extends BaseService<UserDao, User> {

	
	@Autowired
	private ClazzDao  clazzDao;
 
	
     //添加班级
	public int saveAtt(Clazz clazz) {
		// TODO Auto-generated method stub
		return clazzDao.saveCla(clazz);
	}


   //获取班级list
	public List<Clazz> findClazz(Map<String, String> parameters, String searchParameter, int start,
			int pageSize, String sortType, String sortCol) {
		if (StringUtils.isEmpty(sortCol)) {
            sortCol = "state";
        } else if (sortCol.equalsIgnoreCase("5")) {
            sortCol = "state";
        } else {
            sortCol = "id";
        }

	return clazzDao.findList(parameters,searchParameter,start,pageSize,sortType,sortCol);
	}


    //更新班级信息
	public int update(Clazz clazz) {
		// TODO Auto-generated method stub
		return clazzDao.update(clazz);
	}


    //删除班级
	public void delete(String ids) {
		// TODO Auto-generated method stub
		String[] arr=ids.split(",");
		for(String id:arr) {
			clazzDao.delete(id);
		}
	}



	public List<Clazz> find() {
		return clazzDao.find();
	}



	public void insert(User user) {
		// TODO Auto-generated method stub
		clazzDao.deleteUser(user.getId());
		clazzDao.insert(user);
	}



	public long getClazzId(long id) {
		// TODO Auto-generated method stub
		return clazzDao.getClazzId(id);
	}

	
}
