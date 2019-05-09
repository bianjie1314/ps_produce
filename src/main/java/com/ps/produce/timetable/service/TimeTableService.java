package com.ps.produce.timetable.service;

import com.ps.produce.timetable.dao.TimeTableDao;
import com.ps.produce.timetable.entity.TimeTable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;



@Service
@Transactional(rollbackFor = Exception.class)
public class TimeTableService {
	
	@Autowired
	private TimeTableDao timeTableDao;
	    
    
	

    //根据专业，获取获取课程表
	public List<Map<String,Object>> getStuTimeTable(Long officeId,int term) {
		// TODO Auto-generated method stub
		return timeTableDao.getStuTimeTable(officeId,term);
	}
	//根据教师id获取 教师课程安排
	public List<Map<String,Object>> getTeaTimeTable(Long tId,Integer term) {
		// TODO Auto-generated method stub
		return timeTableDao.getTeaTimeTable(tId,term);
	}

    //
	public List<Map<String,Object>> getTimeTable(String searchParameter, Map<String, String> parameters, int  start,
			int pageSize, String sortType, String sortCol) {
		if (StringUtils.isEmpty(sortCol)) {
            sortCol = "state";
        } else if (sortCol.equalsIgnoreCase("5")) {
            sortCol = "state";
        } else {
            sortCol = "id";
        }

	return timeTableDao.findList(parameters,searchParameter,start,pageSize,sortType,sortCol);
		
	}

    //保存班级课程表
	public int saveAtt(TimeTable timeTable) {
		
		return timeTableDao.save(timeTable);
	}
   //保存专业课程
   public int saveAtt2(TimeTable timeTable) {
		
		return timeTableDao.saveDe(timeTable);
	}

	public void delete(String ids) {
		for(String id:ids.split(",")) {
			timeTableDao.delete(id);

		}
	}

     //更新课程表
	public int update(TimeTable timeTable) {
		return timeTableDao.update(timeTable);
		
	}
	//更细专业课程
	public int updateDe(TimeTable timeTable) {
		return timeTableDao.updateDe(timeTable);
		
	}
     //g根据班级id 和学期获取课程id
	public List<Integer> getCourseId(long clazzId, int term) {
		// TODO Auto-generated method stub
		return timeTableDao.getCourseId(clazzId,term);
	}

    //获取全部的课程安排
	public List<Map<String, Object>> getAllTimeTable(String term, String clazzId) {
		// TODO Auto-generated method stub
		return timeTableDao.getAllTimeTable(term,clazzId);
	}

   //获取专业课程安排
	public List<Map<String, Object>> getTimeTable1(String searchParameter, Map<String, String> parameters, int  start,
			int pageSize, String sortType, String sortCol) { 
		return timeTableDao.findTimeList(parameters,searchParameter,start,pageSize,sortType,sortCol);
	}

    //删除课程表
	public void delete1(String ids) {
		for(String id:ids.split(",")) {
			timeTableDao.delete1(id);

		}
	}

    //根据班级查询可安排的课程
	public List<Map<String, Object>> queryCourse(TimeTable d) {
		try {
			return timeTableDao.queryCourse(d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	

}
