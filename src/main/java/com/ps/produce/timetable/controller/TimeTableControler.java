package com.ps.produce.timetable.controller;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.clazz.entity.Clazz;
import com.ps.produce.clazz.service.ClazzService;
import com.ps.produce.shiro.ShiroUser;
import com.ps.produce.support.JsonObject;
import com.ps.produce.support.Response;
import com.ps.produce.system.entity.Distribution;
import com.ps.produce.system.entity.Office;
import com.ps.produce.system.service.CourseService;
import com.ps.produce.system.service.OfficeService;
import com.ps.produce.system.service.UserService;
import com.ps.produce.timetable.entity.TimeTable;
import com.ps.produce.timetable.service.TimeTableService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//课程表管理
@Controller
@RequestMapping(value = "/timeTable")
public class TimeTableControler {
	
    @Autowired
    ClazzService clazzService;
    @Autowired
    CourseService courseService;
    @Autowired
    TimeTableService timeTableService;
    @Autowired
    OfficeService officeService;
    @Autowired
    UserService userService;
    //跳转到课程表界面
    @RequestMapping(value = "/list")
    public String index2(Model model) {
    	List<Clazz> list=clazzService.find();
    	Gson g = new Gson();
    	try {
    		model.addAttribute("clazzs",g.toJson(list));
    		model.addAttribute("course",g.toJson(courseService.getCourse()));
			model.addAttribute("office", g.toJson(officeService.getOffice()));
			model.addAttribute("title", "课程表管理");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        return "timetable/AdmTimeTable";
    }
    @RequestMapping(value = "/list1")
    public String index3(Model model) {
    	List<Clazz> list=clazzService.find();
    	Gson g = new Gson();
    	try {
    		model.addAttribute("clazzs",g.toJson(list));
    		model.addAttribute("course",g.toJson(courseService.getCourse()));
			model.addAttribute("office", g.toJson(officeService.getOffice()));
			model.addAttribute("title", "课程表管理");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        return "timetable/AdmTimeTable1";
    }
     //获取课程表根据学期
    @RequestMapping(value = "/List/{term}")
    public String index(HttpServletRequest request,PageBean<Map<String,Object>> pageBean,Model model) {
    	ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    	Gson g = new Gson();
    	String term=request.getParameter("term");
    	String officeId=request.getParameter("office");
    	String clazzId=request.getParameter("clazz");
    	List<Office> offices=Lists.newArrayList();
    	offices = officeService.getOffice();
		List<Clazz> list=clazzService.find();
    	try {
			if(u.getRoles().get(0).getId()==14) {
		    long id=clazzService.getClazzId(u.getId());
		    if(StringUtils.isEmpty(term))
		    pageBean.setContent(timeTableService.getStuTimeTable(id,1));
		    else
			pageBean.setContent(timeTableService.getStuTimeTable(id,Integer.parseInt(term)));
			}else if(u.getRoles().get(0).getId()==13) {
				if(StringUtils.isEmpty(term))
					term="1";
			  pageBean.setContent(timeTableService.getTeaTimeTable(u.getId(),Integer.parseInt(term)));
			}else {
				model.addAttribute("clazzs",g.toJson(list));
				model.addAttribute("offices", offices);
				pageBean.setContent(timeTableService.getAllTimeTable(term,clazzId));
			}
			model.addAttribute("clazz", clazzService.find());
			model.addAttribute("pageBean",pageBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	model.addAttribute("term", term);
        return "timetable/TimeTable";
    }

    @RequestMapping(value = "/source", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<Map<String,Object>> sourec(@RequestParam(value = "iDisplayStart", defaultValue = "0") int iDisplayStart,
            @RequestParam(value = "iDisplayLength", defaultValue = "10000") int iDisplayLength, Model model,
            ServletRequest request) throws UnsupportedEncodingException {
        
        String sortCol = request.getParameter("iSortCol_0");
        String sortType = request.getParameter("sSortDir_0");
        String searchParameter = request.getParameter("sSearch");
        Map<String, String> parameters = new HashMap<String, String>(16);
        if (!StringUtils.isEmpty(searchParameter)) {
            searchParameter = URLDecoder.decode(searchParameter, "UTF-8");
            searchParameter = searchParameter.trim();
        }
        parameters.put("searchParameter", searchParameter);
        List<Map<String,Object>>data=timeTableService.getTimeTable1(searchParameter,parameters,iDisplayStart, iDisplayLength,sortType, sortCol);
        JsonObject<Map<String,Object>> result = new JsonObject<Map<String,Object>>();
        result.setAaData(data);
        return result;
    }
    //根据学期和专业查询课程列表
    @RequestMapping(value="/querrCourse")
	@ResponseBody
	public String querrTea(@RequestBody  TimeTable d){
    	ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    	if(u.getRoles().contains(13)) {
    	d.setTeaId(u.getId());
    	}
    List<Map<String,Object>> datas=timeTableService.queryCourse(d);
	Gson gson=new Gson();
    return gson.toJson(datas);
	}
  
    //专业课程列表
    @RequestMapping(value = "/source1", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<Map<String,Object>> sourec1(@RequestParam(value = "iDisplayStart", defaultValue = "0") int iDisplayStart,
            @RequestParam(value = "iDisplayLength", defaultValue = "10000") int iDisplayLength, Model model,
            ServletRequest request) throws UnsupportedEncodingException {
        
        String sortCol = request.getParameter("iSortCol_0");
        String sortType = request.getParameter("sSortDir_0");
        String searchParameter = request.getParameter("sSearch");
        Map<String, String> parameters = new HashMap<String, String>(16);
        if (!StringUtils.isEmpty(searchParameter)) {
            searchParameter = URLDecoder.decode(searchParameter, "UTF-8");
            searchParameter = searchParameter.trim();
        }
        parameters.put("searchParameter", searchParameter);
        List<Map<String, Object>> data=Lists.newArrayList();
		try {
			data = timeTableService.getTimeTable(searchParameter,parameters,iDisplayStart, iDisplayLength,sortType, sortCol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JsonObject<Map<String,Object>> result = new JsonObject<Map<String,Object>>();
        result.setAaData(data);
        return result;
    }
    //保存课程安排
    @RequestMapping(value="save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> save(@RequestBody TimeTable timeTable) {
		int ret=0;
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			
			ret = timeTableService.saveAtt2(timeTable);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("ret", ret);
		return map;
	}
    //修改课程安排
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    @ResponseBody
    public Map<String,Object> update(@RequestBody TimeTable timeTable) { 
    	int ret=0;
    	Map<String,Object> map=new HashMap<String, Object>();
       try {
    	ret=  timeTableService.updateDe(timeTable);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      map.put("ret", ret);
	return map;
       
    }
    //根据id删除课程安排
    @ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Response walkaround( String ids) throws IOException {
		
		Response response = new Response();
		try {
			timeTableService.delete(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
    @RequestMapping(value="save1",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> save1(@RequestBody TimeTable timeTable) {
		int ret=0;
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			ret = timeTableService.saveAtt(timeTable);
			Distribution d=new Distribution();
			d.setClazzId(timeTable.getClazzId());
			d.setCourseId(timeTable.getCourseId());
			d.setTeaId(timeTable.getTeaId());
			userService.updateDis(d);
			ret=userService.distribution(d);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("ret", ret);
		return map;
	}
    
    @RequestMapping(method = RequestMethod.POST, value = "/update1")
    @ResponseBody
    public Map<String,Object> update1(@RequestBody TimeTable timeTable) { 
    	int ret=0;
    	Distribution d=new Distribution();
		d.setClazzId(timeTable.getClazzId());
		d.setCourseId(timeTable.getCourseId());
		d.setTeaId(timeTable.getTeaId());
		userService.updateDis(d);
    	Map<String,Object> map=new HashMap<String, Object>();
       try {
    	ret=  timeTableService.update(timeTable);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      map.put("ret", ret);
	return map;
       
    }
    
    @ResponseBody
	@RequestMapping(value = "/delete1", method = RequestMethod.POST)
	public Response walkaround1( String ids) throws IOException {
		
		Response response = new Response();
		try {
			timeTableService.delete1(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
    
}
