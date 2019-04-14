package com.ps.produce.support.pair;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public enum LeaveStatus {
	
	waitConfirm("待确认",0),
	confirm("已确认",1),
	waitMake("被驳回",2),
	
    ;

    private final String label;
    private final int value;

    private LeaveStatus(String label,int value) {
        this.label = label;
        this.value=value;
    }

	public String getLabel() {
		return label;
	}

	public int getValue() {
		return value;
	}

    
}
