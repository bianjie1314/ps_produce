package com.ps.produce.system.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ps.produce.support.JsonObject;
import com.ps.produce.system.entity.Flow;
import com.ps.produce.system.entity.User;
import com.ps.produce.system.service.FlowService;
import com.ps.produce.system.service.UserService;

/**
 * 审批流程
 * @author yuyanzheng
 * 
 * 2018年1月5日
 */
@Controller
@RequestMapping("/system/flow")
public class FlowController {
	
	@Autowired
	private FlowService flowService;
	@Autowired
	private UserService userService;
	
	@RequiresPermissions("sys:flow:view")
	@RequestMapping(method=RequestMethod.GET)
	public String flow(Model model) {
		model.addAttribute("title", "审批流程");
		return "system/Flow";
	}
	
	@RequiresPermissions("sys:flow:view")
	@RequestMapping(value="form",method=RequestMethod.GET)
	public String form(Flow flow,Model model) {
		List<User> users = userService.getUserAll(null);
		if (flow.getId() != null) {
			model.addAttribute("flow", flowService.get(flow.getId()));
		}
		model.addAttribute("users", users);
		return "system/FlowForm";
	}
	
	@RequiresPermissions("sys:flow:view")
	@RequestMapping(value = "/source", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<Flow> sourec(@RequestParam(value = "iDisplayStart", defaultValue = "0") int iDisplayStart,
            @RequestParam(value = "iDisplayLength", defaultValue = "10000") int iDisplayLength, Model model,
            ServletRequest request) throws UnsupportedEncodingException {
        List<Flow> flows = flowService.findAll();
        JsonObject<Flow> result = new JsonObject<Flow>();
        result.setiTotalDisplayRecords(0);
        result.setiTotalRecords(0);
        result.setAaData(flows);
        return result;
    }
	
	@RequiresPermissions("sys:flow:edit")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Flow flow,Long[] userIds,RedirectAttributes redirectAttributes) {
		try {
			flowService.saveFlow(flow,userIds);
			redirectAttributes.addFlashAttribute("success", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "操作失败");
		}
		return "redirect:/system/flow";
	}
	
	@RequiresPermissions("sys:flow:edit")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Flow flow,Long[] userIds,RedirectAttributes redirectAttributes) {
		try {
			flowService.saveFlow(flow,userIds);
			redirectAttributes.addFlashAttribute("success", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "操作失败");
		}
		return "redirect:/system/flow";
	}

	@RequiresPermissions("sys:flow:edit")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(String ids,RedirectAttributes redirectAttributes) {
		try {
			flowService.deleteBatch(ids);
			redirectAttributes.addFlashAttribute("success", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "操作失败");
		}
		return "redirect:/system/flow";
	}
	
	/**
	 * 获取所以已提交的流程
	 * @return
	 */
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	@ResponseBody
	public List<Flow> findAll(HttpServletRequest request) {
		return flowService.findAll();
	}
}
