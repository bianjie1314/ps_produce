package com.ps.produce.model.dao;

import com.ps.produce.model.po.Template;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TemplateMapper {
    
    List<Template>list(@Param("productType")String productType,@Param("productName")String ProductName,@Param("startDate")String startDate,@Param("endDate")String endDate);
    int update(Template record);
    int insert(Template record);
    int delete(@Param("id")int id);
}