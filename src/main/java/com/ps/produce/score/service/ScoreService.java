package com.ps.produce.score.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.produce.base.entity.query.model.LeaveQuery;
import com.ps.produce.base.entity.query.model.OrderQuery;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.exception.BusinessException;
import com.ps.produce.leave.dao.LeaveDao;
import com.ps.produce.leave.entity.Leave;

import com.ps.produce.score.dao.ScoreDao;
import com.ps.produce.score.entity.Score;
import com.ps.produce.support.Response;
import com.ps.produce.support.ResponseCode;
import com.ps.produce.support.pair.OrderStatus;


@Service
@Transactional(rollbackFor = Exception.class)
public class ScoreService {

	@Autowired
	private ScoreDao scoreDao;

	
    //获取成绩
	public List<Map<String,Object>> findScores(Long tId, Long officeId, Map<String, String> parameters, String searchParameter,
			int iDisplayStart, int iDisplayLength, String sortType, String sortCol) {
		// TODO Auto-generated method stub
		return scoreDao.findScore(tId,officeId,parameters,searchParameter,iDisplayStart,iDisplayLength,sortType,sortCol);
	}


     //保存成绩
	public Map<String,Object> saveScore(Score score) {
		Map<String,Object> map=new HashMap<String,Object>();
		int ret=0;
		String msg="添加成功";
		List<Score> old=scoreDao.find(score);
		if(old.size()==0&&score.getState()==1) {
		ret= scoreDao.saveScore(score);
		
		}else
		if(old.size()==1&&score.getState()==2&&old.get(0).getState()==1&&old.get(0).getScore()<60) {
			ret= scoreDao.saveScore(score);
			
		  }else 
		if(old.size()==2&&score.getState()==3) {
		     if(old.get(0).getScore()<60&&old.get(1).getScore()<60)
			 ret= scoreDao.saveScore(score);
		     else {
		    	 msg="添加失败";
		     }
		  }else {
			  ret=0;
			  msg="添加失败";
		  }
		map.put("ret", ret);
		map.put("msg", msg);
		return map;
	}


    //获取个人成绩 id学生id
	public List<Map<String,Object>> getScore(long id,List<Integer> list) {
		return scoreDao.getScore(id,list);
	}


    //编辑成绩
	public int editScore(Score score) {
		
		return scoreDao.editScore(score);
	}


   //删除成绩
	public void delete(String ids) {
		// TODO Auto-generated method stub
		for(String id:ids.split(",")) {
			scoreDao.delete(id);
		}
	}

	
}
