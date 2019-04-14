
package com.ps.produce.score.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ps.produce.attendance.dto.AttQuery;
import com.ps.produce.attendance.entity.Attendance;
import com.ps.produce.base.entity.query.model.LeaveQuery;
import com.ps.produce.base.entity.query.model.OrderQuery;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.base.entity.query.model.ScoreQuery;
import com.ps.produce.clazz.entity.Clazz;
import com.ps.produce.clazz.service.ClazzService;
import com.ps.produce.leave.entity.Leave;

import com.ps.produce.score.entity.Score;
import com.ps.produce.score.service.ScoreService;
import com.ps.produce.shiro.ShiroUser;
import com.ps.produce.support.JsonObject;
import com.ps.produce.support.Response;
import com.ps.produce.support.ResponseCode;
import com.ps.produce.support.SignatureUtils;
import com.ps.produce.support.pair.OrderStatus;
import com.ps.produce.support.utils.ZipImgs;
import com.ps.produce.system.entity.Course;
import com.ps.produce.system.entity.Office;
import com.ps.produce.system.entity.User;
import com.ps.produce.system.service.CourseService;
import com.ps.produce.system.service.OfficeService;
import com.ps.produce.system.service.UserService;
import com.ps.produce.timetable.service.TimeTableService;

//分数管理
@Controller
@RequestMapping(value = "/score")
public class ScoreController {
	@Autowired
	private TimeTableService  timeTableService;
	@Autowired
	private ClazzService clazzService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserService userService;
	@Autowired
	private OfficeService officeService;
    @Autowired
    private ScoreService scoreService;
    //跳转到成绩管理界面
	@RequestMapping(value="list" ,method=RequestMethod.GET)
	public String user(Model model,PageBean<User> userPageBean) {
		model.addAttribute("title", "成绩管理");
		List<Office> offices=Lists.newArrayList();
		List<Course> courses=Lists.newArrayList();
		List<Clazz> list=clazzService.find();
		offices = officeService.getOffice();
		//获取角色id获取全部学生
		List<User> users=userService.findUsers(14);
		ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if(u.getRoles().contains(13)){
			courses=courseService.getCourse(u.getId());
		}else {
			courses=courseService.getCourse();
		}
		Gson gson=new Gson();
		model.addAttribute("clazzs", gson.toJson(list));
		model.addAttribute("courses",gson.toJson(courses));
		model.addAttribute("offices", gson.toJson(offices));
		model.addAttribute("students",gson.toJson(users));
		return "/score/Score";
	}
	
	//获取成绩list
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

		ShiroUser user=(ShiroUser) SecurityUtils.getSubject().getPrincipal();
		//如果用户为导师只获取所教课程的成绩
	    Long tId=null;
	    Long officeId=null;
		if(user.getRoles().contains(13)) {
			tId=user.getId();
		}
		//如果用户为f辅导员只获取该专业的学生成绩
		if(user.getRoles().contains(11)) {
			officeId=user.getOffice().getId();
		}
		List<Map<String,Object>> scores=Lists.newArrayList();
		try {
			scores = scoreService.findScores(tId,officeId,parameters,searchParameter, iDisplayStart, iDisplayLength,sortType, sortCol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JsonObject<Map<String,Object>> result = new JsonObject<Map<String,Object>>();
        result.setiTotalDisplayRecords(0);
        result.setiTotalRecords(0);
        result.setAaData(scores);
        return result;
    }
    //跳转到个人成绩页面
	@RequestMapping(value="/personScore",method=RequestMethod.GET)
	public String personList(HttpServletRequest request,Model model) {
		//int term=Integer.parseInt(request.getParameter("term"));
		model.addAttribute("title","个人成绩");
        return "/score/personScore";
	}
	//个人成绩list
	@RequestMapping(value="/personList",method=RequestMethod.GET)
	@ResponseBody
	public JsonObject<Map<String,Object>>personScore(HttpServletRequest request,Model model) {
		int term=Integer.parseInt(request.getParameter("term"));
		ShiroUser user=(ShiroUser) SecurityUtils.getSubject().getPrincipal();
		long clazzId=clazzService.getClazzId(user.getId());
		List<Integer> list=timeTableService.getCourseId(clazzId,term);
		JsonObject<Map<String,Object>> result = new JsonObject<Map<String,Object>>();
		if(list.size()==0) {
			List<Map<String,Object>>list1=Lists.newArrayList();
			result.setAaData(list1);
		}else {
		List<Map<String,Object>> scores=scoreService.getScore(user.getId(),list);
		 result.setAaData(scores);
		}
        result.setiTotalDisplayRecords(0);
        result.setiTotalRecords(0);
        return result;
	}
	//保存成绩
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> save(@RequestBody ScoreQuery query) {
		Map<String,Object> map =new HashMap<String,Object>();
		ShiroUser user=(ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Score score =new Score();
		score.setCourseId(query.getCourseId());
		score.setScore(Long.parseLong(query.getScore()));
		score.setCreateDate(new Date());
		score.setClazzId(query.getClazzId());
		score.setOfficeId(query.getOfficeId());
		score.setRemarks(query.getRemarks());
		score.setStuId(query.getStuId());
		score.setOptId(user.getId());
		score.setState(query.getState());
		
		try {
			map=scoreService.saveScore(score);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	//编辑成绩
	@RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> update(@RequestBody Score score) {
		Map<String,Object> map =new HashMap<String,Object>();
		ShiroUser u = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		score.setOptId(u.getId());
		score.setUpdateDate(new Date());
		int ret=0;
		try {
			ret = scoreService.editScore(score);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("ret", ret);
		return map;
	}
	//根据id删除成绩数据
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public Response delete(String ids, RedirectAttributes redirectAttributes) {
		Response response = new Response();
		try {
			scoreService.delete(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
    
    }
