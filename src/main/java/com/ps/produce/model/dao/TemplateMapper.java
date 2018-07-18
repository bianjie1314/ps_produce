package com.ps.produce.model.dao;

import com.ps.produce.model.po.Template;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TemplateMapper {
    
    List<Template>list(@Param("productType")String productType,@Param("productName")String ProductName,@Param("sku")String sku,@Param("startDate")String startDate,@Param("endDate")String endDate, @Param("beginIndex")int beginIndex,@Param("pageSize") int pageSize,@Param("sortType")String sortType,@Param("sortCol") String sortCol);
    int update(Template record);
    int insert(Template record);
    int delete(@Param("id")int id);
	List<Map<String,Object>> queryType();
}