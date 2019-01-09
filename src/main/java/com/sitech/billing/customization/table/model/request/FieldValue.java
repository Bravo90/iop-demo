package com.sitech.billing.customization.table.model.request;

import com.alibaba.fastjson.JSONArray;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字段键值
 *
 * @author sunzhen
 * @date 2019/1/9 11:22
 */
@Getter
@Setter
@ToString
public class FieldValue {

    private String name;
    private List<String> value;

    public static Map<String, FieldValue> toMap(List<FieldValue> fieldValues) {
        Map<String, FieldValue> map = new HashMap<>();
        if (fieldValues != null) {
            for (FieldValue fieldValue : fieldValues) {
                map.put(fieldValue.getName(), fieldValue);
            }
        }
        return map;
    }
}
