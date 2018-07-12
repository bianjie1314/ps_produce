package com.ps.produce.support.pair;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public enum OrderStatus {
	cancel("取消订单",-1),
	waitConfirm("待确认",0),
	confirm("已确认",1),
	waitMake("待制作",2),
	make("已制作",3),
	waitShipping ("待发货",4),
	shipping ("已发货",5),

    ;

    private final String label;
    private final int value;

    private OrderStatus(String label,int value) {
        this.label = label;
        this.value=value;
    }

    public static List<Map<String, String>> getDictType() {
        List<Map<String, String>> list = Lists.newArrayList();
        for (OrderStatus r : OrderStatus.values()) {
            Map<String, String> scope = Maps.newHashMap();
            scope.put("label", r.label);
            scope.put("value", r.name());
            list.add(scope);
        }
        return list;
    }
}
