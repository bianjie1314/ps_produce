package com.ps.produce.attendance.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ps.produce.attendance.dao.AttendanceDao;
import com.ps.produce.attendance.entity.Attendance;
import com.ps.produce.base.service.BaseService;
import com.ps.produce.system.dao.UserDao;

import com.ps.produce.system.entity.User;

@Service
public class AttendanceService extends BaseService<UserDao, User> {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	

	@Autowired
	private AttendanceDao  attendanceDao;
 
	//获取缺勤list
	public List<Attendance> findAttendances() {
		
		return attendanceDao.findAttendances();
	}
    //保存缺勤信息
	public int saveAtt(Attendance attendance) {
		// TODO Auto-generated method stub
		return attendanceDao.saveAtt(attendance);
	}
    //编辑缺勤信息
	public int editAtt(Attendance attendance) {
		// TODO Auto-generated method stub
		return attendanceDao.eidtAtt(attendance);
	}
    //删除缺勤信息根据id
	public void delete(String ids) {
		// TODO Auto-generated method stub
		for(String id:ids.split(",")) {
			attendanceDao.detele(id);
		}
	}
}
