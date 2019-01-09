package com.sitech.billing.customization.table.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 排序字段
 *
 * @author sunzhen
 * @date 2019/1/9 11:23
 */
@Getter
@Setter
@ToString
public class FieldOrder {
    private String field;
    private String type;

    public static Map<String, FieldOrder> toMap(List<FieldOrder> fieldOrders) {
        Map<String, FieldOrder> map = new HashMap<>();
        if (fieldOrders != null) {
            for (FieldOrder fieldOrder : fieldOrders) {
                map.put(fieldOrder.getField(), fieldOrder);
            }
        }
        return map;
    }
}
