package com.ps.produce.system.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.ps.produce.support.pair.DictType;
import com.ps.produce.support.utils.NoUtil;
import com.ps.produce.system.dao.CourseDao;
import com.ps.produce.system.entity.Course;



@Service
@Transactional(rollbackFor = Exception.class)
public class CourseService {
	
	@Autowired
	private CourseDao courseDao;
	    
    //获取课程列表
	public List<Course> find(Map<String, String> parameters, String searchParameter, int start,
			int pageSize, String sortType, String sortCol) {
		 if (StringUtils.isEmpty(sortCol)) {
	            sortCol = "state";
	        } else if (sortCol.equalsIgnoreCase("5")) {
	            sortCol = "state";
	        } else {
	            sortCol = "id";
	        }

		return courseDao.findList(parameters,searchParameter,start,pageSize,sortType,sortCol);
		
	}

   //课程数
	public long count(Map<String, String> parameters, String searchParameter) {
		return courseDao.count(parameters,searchParameter);
	}

  // 保存课程信息
	public void insert(Course course) {
		String type="NO";//定义单据类型，预订单
        String DbBillCode=courseDao.getMaxNO(type);//获取最大的预订单号
        course.setCourseNo(NoUtil.generateCode(type,DbBillCode));
        course.setCreateDate(new Date(System.currentTimeMillis()));
		course.preInsert();
		courseDao.insert(course);
		
	}

    //更新课程信息
	public void update(Course course) {
		course.setUpdateDate(new Date(System.currentTimeMillis()));
		this.courseDao.update(course);
		
	}

    //删除课程
	public void delete(String ids) {
		for(String id:ids.split(",")) {
			courseDao.delete(id);

		}
		
	}


	

    //获取全部教师信息
	public List<Map<String, String>> getTeaDTO() {
		 List<Map<String, String>> list =courseDao.getTeaDTO();
		return list;
	}

	//获取全部课程
	public  List<Map<String, String>> getCouDTO() {
		
		List<Map<String, String>> list =courseDao.getCouDTO();
		return list;
	}

	//获取全部专业信息
	public  List<Map<String, String>> getProDTO() {
		List<Map<String, String>> list =courseDao.getProDTO();
		return list;
	}

    //根据教师id获取课程
	public List<Course> getCourse(long l) {
		return courseDao.getCourse(l);
	}


	public List<Course> getCourse() {
		// TODO Auto-generated method stub
		return courseDao.getCourseAll();
	}

   

}
