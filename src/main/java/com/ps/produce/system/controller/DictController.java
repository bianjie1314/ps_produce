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
import com.ps.produce.system.entity.Dict;
import com.ps.produce.system.service.DictService;


@Controller
@RequestMapping(value = "/system/dict")
public class DictController {

    @Autowired
    DictService dictService;

    @RequestMapping(value = "")
    public String index(Model model) {
    	Gson g = new Gson();
    	model.addAttribute("dictType", g.toJson(DictType.getDictType()));
        return "system/Dict";
    }

    @RequestMapping(value = "/source", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<Dict> sourec(@RequestParam(value = "iDisplayStart", defaultValue = "0") int iDisplayStart,
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

        List<Dict> dicts = dictService.find(parameters, searchParameter, iDisplayStart, iDisplayLength,
                sortType, sortCol);
        long count = dictService.count(parameters, searchParameter);
        JsonObject<Dict> result = new JsonObject<Dict>();
        result.setiTotalDisplayRecords(count);
        result.setiTotalRecords(count);
        result.setAaData(dicts);
        return result;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ResponseBody
    public Response add(@RequestBody Dict dict) {
     
       dictService.insert(dict);
        
       return new Response().setResponseCode(ResponseCode.SUCCESS);
       
    } 
    
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    @ResponseBody
    public Response update(@RequestBody Dict dict) {     
       dictService.update(dict);
        
       return new Response().setResponseCode(ResponseCode.SUCCESS);
       
    }
    
    @ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public Response walkaround( String ids) throws IOException {
		
		Response response = new Response();
		dictService.delete(ids);
		
		return response;
	}
    
}
