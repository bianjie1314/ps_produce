package com.ps.produce.system.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
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
import com.ps.produce.support.ISecurityUtils;
import com.ps.produce.support.JsonObject;
import com.ps.produce.support.utils.StringUtils;
import com.ps.produce.system.entity.Menu;
import com.ps.produce.system.service.MenuService;

@Controller
@RequestMapping(value="/system/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;

	@RequiresPermissions("sys:menu:view")
	@RequestMapping(method=RequestMethod.GET)
	public String menu(Model model) {
		 List<Menu> meuns = Lists.newArrayList();
		List<Menu> sourcelist = menuService.findAllMenu();
		Menu.sortList(meuns, sourcelist, Menu.getRootId(), true);
		model.addAttribute("list", meuns);
		model.addAttribute("title", "菜单管理");
		return "/system/Menu";
	}
	
	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = "/source", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<Menu> sourec(@RequestParam(value = "iDisplayStart", defaultValue = "0") int iDisplayStart,
            @RequestParam(value = "iDisplayLength", defaultValue = "10000") int iDisplayLength, Model model,
            ServletRequest request) throws UnsupportedEncodingException {
        List<Menu> meuns = Lists.newArrayList();
		List<Menu> sourcelist = menuService.findAllMenu();
		Menu.sortList(meuns, sourcelist, Menu.getRootId(), true);
        JsonObject<Menu> result = new JsonObject<Menu>();
        result.setiTotalDisplayRecords(0);
        result.setiTotalRecords(0);
        result.setAaData(meuns);
        return result;
    }
	
	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value="form",method=RequestMethod.GET)
	public String form(Model model,Menu menu) {
		if (menu.getId() != null) {
			menu = menuService.get(menu.getId());
		}
		if (menu.getParent()==null||menu.getParent().getId()==null){
			menu.setParent(new Menu(Menu.getRootId()));
		}
		
		menu.setParent(menuService.get(menu.getParent().getId()));
		// 获取排序号，最末节点排序号+30
		if (StringUtils.isBlank(menu.getId()+"")){
			List<Menu> list = Lists.newArrayList();
			List<Menu> sourcelist = menuService.findAllMenu();
			Menu.sortList(list, sourcelist, menu.getParentId(), false);
			if (list.size() > 0){
				menu.setSort(list.get(list.size()-1).getSort() + 30);
			}
		}
		model.addAttribute("menu", menu);
		
		//TODO
		Map<String, String> showMap =new HashMap<String,String>();  
		showMap.put("1", "显示");  
		showMap.put("0", "隐藏");  
        model.addAttribute("showMap", showMap);
		return "/system/MenuForm";
	}
	
	@RequiresPermissions("sys:menu:edit")
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String updateMenu(Menu menu,RedirectAttributes redirectAttributes) {
		try {
			menuService.updateMenu(menu);
			redirectAttributes.addFlashAttribute("success", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "操作失败");
		}
		return "redirect:/system/menu";
	}
	
	@RequiresPermissions("sys:menu:edit")
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String delete(String ids,RedirectAttributes redirectAttributes) {
		try {
			menuService.deleteBatch(ids);
			redirectAttributes.addFlashAttribute("success", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "操作失败");
		}
		return "redirect:/system/menu";
	}
	
	/**
	 * isShowHide是否显示隐藏菜单
	 * @param extId
	 * @param isShowHidden
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId,@RequestParam(required=false) String isShowHide, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Menu> list = ISecurityUtils.getMenuList();
		for (int i=0; i<list.size(); i++){
			Menu e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				if(isShowHide != null && isShowHide.equals("0") && e.getIsShow().equals("0")){
					continue;
				}
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
}
