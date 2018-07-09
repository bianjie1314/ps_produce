package com.ps.produce.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ps.produce.system.entity.Dict;


public interface DictDao {
    
	
	public List<Dict> findList(@Param("param") Map<String, String> param,@Param("search") String search, @Param("beginIndex")int beginIndex,@Param("pageSize") int pageSize,@Param("sortType")String sortType,@Param("sortCol") String sortCol);
	
	public long count(Map<String, String> map, String searchParameter);

	public void insert(Dict dict);

	public void delete(String id);

	public void update(Dict dict);

	public List<Dict> findAllList();
}
