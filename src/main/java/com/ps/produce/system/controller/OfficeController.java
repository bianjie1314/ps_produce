package com.ps.produce.system.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ps.produce.base.entity.query.model.PageBean;
import com.ps.produce.support.ISecurityUtils;
import com.ps.produce.support.utils.StringUtils;
import com.ps.produce.system.entity.Office;
import com.ps.produce.system.entity.User;
import com.ps.produce.system.service.OfficeService;
import com.ps.produce.system.service.UserService;
//专业管理
@Controller
@RequestMapping(value="/system/office")
public class OfficeController {
	
	@Autowired
	private OfficeService officeService;
	@Autowired
	private UserService userService;
     //跳转专业管理列表
	@RequestMapping(method=RequestMethod.GET)
	public String office(Model model,PageBean<Office> officePageBean) {
		model.addAttribute("title", "机构管理");
		return "/system/Office";
	}
	//获取专业list
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String officeList(Model model,Office office) {
		List<Office> list = officeService.findList(office);
        model.addAttribute("list", list);
		return "/system/OfficeList";
	}
	
	/**修改专业信息
	 * @param office
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "/form")
	public String form(Office office, Model model) {
		User user = userService.get(ISecurityUtils.getCurrUserId());
		if (office.getId() != null) {
			office = officeService.get(office.getId());
		}
		
		if (office.getParent()==null || office.getParent().getId()==null){
			office.setParent(user.getOffice());
		}
		office.setParent(officeService.get(office.getParent().getId()));
		
		// 自动获取排序号
		if (office.getId() == null && office.getParent()!=null){
			int size = 0;
			List<Office> list = officeService.findList(Boolean.FALSE);
			for (int i=0; i<list.size(); i++){
				Office e = list.get(i);
				if (e.getParent()!=null && e.getParent().getId()!=null
						&& e.getParent().getId().equals(office.getParent().getId())){
					size++;
				}
				office.setCode(office.getParent().getCode() + StringUtils.leftPad(String.valueOf(size > 0 ? size+1 : 1), 3, "0"));
			}
		}
		List<User> cous=userService.findCou();
		model.addAttribute("cous",cous);
		model.addAttribute("office", office);
		return "/system/OfficeForm";
	}
	
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "save")
	public String save(Office office, Model model, RedirectAttributes redirectAttributes) {
		try {
			officeService.saveOffice(office);
			redirectAttributes.addFlashAttribute("success", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "操作失败");
		}
		String id = "0".equals(office.getParentId()+"") ? "" : office.getParentId()+"";
		return "redirect:/system/office/list?id="+id+"&parentIds="+office.getParentIds();
	}
	
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "delete")
	public String delete(Office office, RedirectAttributes redirectAttributes) {
		try {
			office = officeService.get(office.getId());
			redirectAttributes.addFlashAttribute("success", "操作成功");
			officeService.delete(office);
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "操作失败");
		}
		return "redirect:/system/office/list?id="+office.getParentId()+"&parentIds="+office.getParentIds();
	}
	
	/**
	 * 获取专业JSON数据。
	 * @param extId 排除的ID
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId,
			 @RequestParam(required=false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Office> list = officeService.findList(isAll);
		for (int i=0; i<list.size(); i++){
			Office e = list.get(i);
			if ((StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1))){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());
//				map.put("isParent", true); 文件夹图标
				mapList.add(map);
			}
		}
		return mapList;
	}
}
