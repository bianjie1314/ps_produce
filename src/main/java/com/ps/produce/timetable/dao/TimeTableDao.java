package com.ps.produce.timetable.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.google.gson.JsonElement;
import com.ps.produce.system.entity.Course;
import com.ps.produce.timetable.entity.TimeTable;


public interface TimeTableDao {
    
	
	

	public List<TimeTable> getTimeTable(@Param("id")Long id);

	public List<Map<String,Object>> getStuTimeTable(@Param("clazzId")Long officeId,@Param("term")int term);

	public List<Map<String, Object>> getTeaTimeTable(@Param("tId")Long tId);

	public List<Map<String,Object>> findList(@Param("param") Map<String, String> param,@Param("search") String search, @Param("beginIndex")int beginIndex,@Param("pageSize") int pageSize,@Param("sortType")String sortType,@Param("sortCol") String sortCol);
	public List<Map<String,Object>> findTimeList(@Param("param") Map<String, String> param,@Param("search") String search, @Param("beginIndex")int beginIndex,@Param("pageSize") int pageSize,@Param("sortType")String sortType,@Param("sortCol") String sortCol);
    
	
	public int save(TimeTable timeTable);
	
	public int saveDe(TimeTable timeTable);

	public void delete(@Param("id")String id);

	public int update(TimeTable timeTable);
	
	public int updateDe(TimeTable timeTable);

	public List<Integer> getCourseId(@Param("clazzId")long clazzId, @Param("term")int term);

	public List<Map<String, Object>> getAllTimeTable(@Param("term")String term, @Param("clazzId")String clazzId);

	public void delete1(@Param("id")String id);

	public List<Map<String, Object>> queryCourse(TimeTable d);
}
