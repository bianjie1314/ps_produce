package com.ps.produce.clazz.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ps.produce.attendance.entity.Attendance;
import com.ps.produce.base.entity.query.model.LeaveQuery;
import com.ps.produce.base.entity.query.model.OrderQuery;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.clazz.entity.Clazz;
import com.ps.produce.leave.entity.Leave;
import com.ps.produce.system.entity.User;


public  interface ClazzDao {
	

	public int saveCla(Clazz clazz);

	public List<Clazz> findList(@Param("param") Map<String, String> param,@Param("search") String search, @Param("beginIndex")int beginIndex,@Param("pageSize") int pageSize,@Param("sortType")String sortType,@Param("sortCol") String sortCol);

	public int update(Clazz clazz);

	public void delete(@Param("id")String id);

	public List<Clazz> find();

	public void insert(User user);

	public void deleteUser(@Param("id")Long id);

	public long getClazzId(@Param("id")long id);


	
	
}
