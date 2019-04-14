package com.ps.produce.attendance.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ps.produce.attendance.entity.Attendance;
import com.ps.produce.base.entity.query.model.LeaveQuery;
import com.ps.produce.base.entity.query.model.OrderQuery;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.leave.entity.Leave;


public  interface AttendanceDao {
	public long insert(Leave leave);
	
	public long updateCanal(@Param("rejection")String rejection,@Param("leaveId")String leaveId,@Param("updateDate")Date updateDate);

	
    public List<Leave> findList(@Param("pageBean") PageBean<Leave> pageBean, @Param("query") LeaveQuery query,@Param("id")Long id);

	public int count(@Param("pageBean") PageBean<Leave> pageBean,@Param("query")  LeaveQuery query,@Param("id")Long id);



	public List<Leave> findListbyCou(@Param("pageBean")PageBean<Leave> pageBean, @Param("query")LeaveQuery query, @Param("id")long id);



	public int countbyCou(@Param("pageBean")PageBean<Leave> pageBean, @Param("query")LeaveQuery query, @Param("id")long id);

	public long sureLeave(@Param("leaveId")String leaveId,@Param("date") Date date);

	public List<Attendance> findAttendances();

	public int saveAtt(Attendance attendance);

	public int eidtAtt(Attendance attendance);

	public void detele( @Param("id")String id);


	
	
}
