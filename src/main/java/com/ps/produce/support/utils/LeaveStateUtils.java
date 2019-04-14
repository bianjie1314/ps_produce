/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ps.produce.support.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ps.produce.support.SpringContextHolder;
import com.ps.produce.support.pair.AmazonSite;
import com.ps.produce.support.pair.LeaveStatus;
import com.ps.produce.support.pair.OrderStatus;


/**
 * 字典工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class LeaveStateUtils {
	public static String getLabel(int value){
		String label="";
		for (LeaveStatus status : LeaveStatus.values()) {
			if(value==status.getValue() ){
				return status.getLabel();
			}
		}
		return label;
	}
public static String checkStatus(int value) {
		
		if(value==LeaveStatus.waitConfirm.getValue()) {
			return"<button class=\" btn btn-success sure\">确认</button><button class=\" btn btn-primary cancle\">驳回</button>";
		}
		return "";
	}
	
	
	
}
