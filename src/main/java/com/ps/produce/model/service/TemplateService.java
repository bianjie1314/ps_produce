package com.ps.produce.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.produce.model.dao.TemplateMapper;
import com.ps.produce.model.po.Template;

@Service
public class TemplateService {
   @Autowired
   private TemplateMapper templateMapper;
   
   
   public List<Template> list(String productType,String productName,String startDate,String endDate){
	   return  templateMapper.list(productType, productName, startDate, endDate);
   }
   public int update(Template template) {
	   return templateMapper.update(template);
   }
   public int delete(int id) {
	   return templateMapper.delete(id);
   }
   public int insert(Template template) {
	   return templateMapper.insert(template);
   }
}
