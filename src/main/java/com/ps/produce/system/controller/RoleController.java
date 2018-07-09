package com.ps.produce.system.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ps.produce.support.ISecurityUtils;
import com.ps.produce.support.JsonObject;
import com.ps.produce.support.Response;
import com.ps.produce.support.ResponseCode;
import com.ps.produce.system.entity.Role;
import com.ps.produce.system.service.RoleService;

@Controller
@RequestMapping(value="/system/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	@RequiresPermissions("sys:role:view")
	@RequestMapping(method=RequestMethod.GET)
	public String role(Model model) {
		model.addAttribute("title", "角色管理");
		return "/system/Role";
	}
	
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "/source", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<Role> sourec(@RequestParam(value = "iDisplayStart", defaultValue = "0") int iDisplayStart,
            @RequestParam(value = "iDisplayLength", defaultValue = "10000") int iDisplayLength, Model model,
            ServletRequest request) throws UnsupportedEncodingException {
        List<Role> roles = ISecurityUtils.getAllRoleList();
        JsonObject<Role> result = new JsonObject<Role>();
        result.setiTotalDisplayRecords(0);
        result.setiTotalRecords(0);
        result.setAaData(roles);
        return result;
    }
	
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String getRole(Model model,Role role) {
		if (role.getId() != null) {
			role = roleService.get(role.getId());
			model.addAttribute("role", role);
		}
		model.addAttribute("menuList", ISecurityUtils.getMenuList());
		return "/system/RoleForm";
	}
	
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(Role role,RedirectAttributes redirectAttributes) {
		try {
			roleService.saveRole(role);
			redirectAttributes.addFlashAttribute("success", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "操作失败");
		}
		return "redirect:/system/role/form?id="+role.getId();
	}
	
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	@ResponseBody
	public Response delete(String ids,RedirectAttributes redirectAttributes) {
		Response response = new Response();
		try {
			roleService.deleteBatch(ids);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResponseCode(ResponseCode.ERROR);
		}
		return response;
	}
}
