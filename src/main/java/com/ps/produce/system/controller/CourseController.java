package com.ps.produce.system.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ps.produce.support.JsonObject;
import com.ps.produce.support.Response;
import com.ps.produce.support.ResponseCode;
import com.ps.produce.support.pair.DictType;
import com.ps.produce.system.entity.Course;
import com.ps.produce.system.service.CourseService;


//课程管理
@Controller
@RequestMapping(value = "/system/course")
public class CourseController {

    @Autowired
    CourseService courseService;
    //跳转到课程管理界面
    @RequestMapping(value = "")
    public String index(Model model) {
    	Gson g = new Gson();
    	model.addAttribute("teacher", g.toJson(courseService.getTeaDTO()));
        return "system/Course";
    }
    //获取课程管理list
    @RequestMapping(value = "/source", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<Course> sourec(@RequestParam(value = "iDisplayStart", defaultValue = "0") int iDisplayStart,
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

        List<Course> Courses = courseService.find(parameters, searchParameter, iDisplayStart, iDisplayLength,
                sortType, sortCol);
        long count = courseService.count(parameters, searchParameter);
        JsonObject<Course> result = new JsonObject<Course>();
        result.setiTotalDisplayRecords(count);
        result.setiTotalRecords(count);
        result.setAaData(Courses);
        return result;
    }
    //添加课程
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ResponseBody
    public Response add(@RequestBody Course course) {
     
       try {
		courseService.insert(course);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        
       return new Response().setResponseCode(ResponseCode.SUCCESS);
       
    } 
    //更新课程
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    @ResponseBody
    public Response update(@RequestBody Course course) {     
       try {
		courseService.update(course);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       return new Response().setResponseCode(ResponseCode.SUCCESS);
       
    }
    //删除课程
    @ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public Response walkaround( String ids) throws IOException {
		
		Response response = new Response();
		courseService.delete(ids);
		
		return response;
	}
    
}
