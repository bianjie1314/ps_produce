package com.ps.produce.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.produce.model.dao.TemplateMapper;
import com.ps.produce.model.po.Template;

@Service
public class TemplateService {
   @Autowired
   private TemplateMapper templateMapper;
   
   
   /*public List<Template> list(String productType,String productName,String startDate,String endDate){
	   return  templateMapper.list(productType, productName, startDate, endDate);
   }*/
   public int update(Template template) {
	   return templateMapper.update(template);
   }
   public int delete(int id) {
	   return templateMapper.delete(id);
   }
   public int insert(Template template) {
	   return templateMapper.insert(template);
   }
   
  public List<Map<String,Object>> queryType() {
	// TODO Auto-generated method stub
	return templateMapper.queryType();
}
public long count(String productType, String productName, String startDate, String endDate) {
	// TODO Auto-generated method stub
	return 0;
}
public List<Template> list(String productType, String productName,String sku, String startDate, String endDate, int iDisplayStart,
		int iDisplayLength, String sortType, String sortCol) {
	   return  templateMapper.list(productType, productName,sku, startDate, endDate,iDisplayStart,iDisplayLength,sortType,sortCol);
}
}
