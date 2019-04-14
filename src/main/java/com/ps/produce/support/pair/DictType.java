package com.ps.produce.support.pair;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public enum DictType {
	site("站点"),
	matrixTag("母体标签"),
    color("颜色"),
    sellType("售卖方式"),
    material("材质"),
    packingType("包装方式"),
    shipmentPlanStatus("备货计划状态"),
    declareType("申报产品类型"),
    technology("工艺"),
    expressType("运输方式"),
    logisticsArrangement("物流安排"),
    overseasWarehouseType("海外仓类型"),
    encyclopediaType("百科主题"),
    infringementType("侵权类型"),
    favorLevelType("关注等级"),
    platformn("平台"),
    bindNumberType("绑定账号类型")
    ;

    private final String label;

    private DictType(String label) {
        this.label = label;
    }

    public static List<Map<String, String>> getDictType() {
        List<Map<String, String>> list = Lists.newArrayList();
        for (DictType r : DictType.values()) {
            Map<String, String> scope = Maps.newHashMap();
            scope.put("label", r.label);
            scope.put("value", r.name());
            list.add(scope);
        }
        return list;
    }
}
