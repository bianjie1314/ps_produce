package com.ps.produce.support.pair;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public enum RoleDataScope {
	// 数据范围（1：所有数据；2：所在公司及以下数据；3：所在公司数据；4：所在部门及以下数据；5：所在部门数据；8：仅本人数据；9：按明细设置）
	DATA_SCOPE_ALL("所有数据","1"),
	DATA_SCOPE_COMPANY_AND_CHILD("所在公司及以下数据","2"),
	DATA_SCOPE_COMPANY("所在公司数据","3"),
	DATA_SCOPE_OFFICE_AND_CHILD("所在部门及以下数据","4"),
	DATA_SCOPE_OFFICE("所在部门数据","5"),
	DATA_SCOPE_SELF("仅本人数据","8");
	
	private final String label;
	private final String value;
	
	private RoleDataScope(String label,String value) {
		this.label = label;
		this.value = value;
	}
	
	public String value() {
		return value;                                     
	}
	
	public static Map<String, String> getRoleDataScopeAllMap() {
		Map<String, String> scope = Maps.newHashMap();
		for (RoleDataScope r : RoleDataScope.values()) {
			scope.put(r.value, r.label);
		}
		return scope;
	}
	
	public static List<Map<String, String>> getRoleDataScopeAll() {
		List<Map<String, String>> list = Lists.newArrayList();
		for (RoleDataScope r : RoleDataScope.values()) {
			Map<String, String> scope = Maps.newHashMap();
			scope.put("label", r.label);
			scope.put("value", r.value);
			list.add(scope);
		}
		return list;
	}
}
