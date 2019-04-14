package com.ps.produce.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ps.produce.system.entity.Course;


public interface CourseDao {
    
	
	public List<Course> findList(@Param("param") Map<String, String> param,@Param("search") String search, @Param("beginIndex")int beginIndex,@Param("pageSize") int pageSize,@Param("sortType")String sortType,@Param("sortCol") String sortCol);
	
	public long count(Map<String, String> map, String searchParameter);

	public void insert(Course Course);

	public void delete(String id);

	public void update(@Param("course")Course Course);

	public List<Course> findAllList();

	public List<Map<String, String>> getTeaDTO();

	public String getMaxNO(@Param("type")String type);

	public List<Map<String, String>> getCouDTO();

	public List<Map<String, String>> getProDTO();

	public List<Course> getCourse(@Param("teaId")long l);

	public List<Course> getCourseAll();
}
