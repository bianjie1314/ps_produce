package com.ps.produce.support.pair;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public enum ApproveStatus {
	REJECT(-1,"驳回"),
	PENDING(0,"待审批"),
	PASS(2,"待采购"), //通过
	
	
	PURCHASED(3,"已采购"),
	STOCKPURCHASED(4,"库存发货"),
	ARRIVED(5,"已到货");
	
	
	private ApproveStatus(int value,String label) {
		this.label = label;
		this.value = value;
	}
	
	private final int value;
	private final String label;


	public int value() {
		return value;
	}


	public String label() {
		return label;
	}


	public static List<Map<String, String>> getApproveStatusAll() {
		List<Map<String, String>> list = Lists.newArrayList();
		for (ApproveStatus r : ApproveStatus.values()) {
			Map<String, String> approve = Maps.newHashMap();
			approve.put("label", r.label);
			approve.put("value", r.value+"");
			list.add(approve);
		}
		return list;
	}
}
