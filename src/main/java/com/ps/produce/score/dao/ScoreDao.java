package com.ps.produce.score.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ps.produce.base.entity.query.model.LeaveQuery;
import com.ps.produce.base.entity.query.model.OrderQuery;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.leave.entity.Leave;

import com.ps.produce.score.entity.Score;


public  interface ScoreDao {
	
	public List<Map<String,Object>> findScore(@Param("tId")Long tId, @Param("officeId")Long officeId, @Param("param") Map<String, String> param,@Param("search") String search, @Param("beginIndex")int beginIndex,@Param("pageSize") int pageSize,@Param("sortType")String sortType,@Param("sortCol") String sortCol);

	public int saveScore(Score score);

	public List<Map<String,Object>> getScore(@Param("stuId")long id,@Param("ids")List<Integer> list);

	public int editScore(Score score);

	public void delete(@Param("id")String id);

	

	public List<Score> find(Score score);


	
	
}
