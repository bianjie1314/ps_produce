package com.ps.produce.system.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.produce.system.dao.DictDao;
import com.ps.produce.system.entity.Dict;


@Service
@Transactional(rollbackFor = Exception.class)
public class DictService {
	
	@Autowired
	private DictDao dictDao;
	    

	public List<Dict> find(Map<String, String> parameters, String searchParameter, int start,
			int pageSize, String sortType, String sortCol) {
		 if (StringUtils.isEmpty(sortCol)) {
	            sortCol = "state";
	        } else if (sortCol.equalsIgnoreCase("5")) {
	            sortCol = "state";
	        } else {
	            sortCol = "id";
	        }

		return dictDao.findList(parameters,searchParameter,start,pageSize,sortType,sortCol);
		
	}


	public long count(Map<String, String> parameters, String searchParameter) {
		return dictDao.count(parameters,searchParameter);
	}


	public void insert(Dict dict) {
		dict.preInsert();
		dictDao.insert(dict);
		
	}


	public void update(Dict dict) {
		dict.setUpdateDate(new Date(System.currentTimeMillis()));
		this.dictDao.update(dict);
		
	}


	public void delete(String ids) {
		for(String id:ids.split(",")) {
			dictDao.delete(id);

		}
		
	}

   

}
