package com.sitech.billing.customization.table.handler.fieldmapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sitech.billing.customization.table.model.FieldMapping;
import sun.awt.SunHints;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * TODO
 *
 * @author sunzhen
 * @date 2019/1/16 13:59
 */
public class FieldMappingHandler1 implements BaseFieldMappingHandler {
    @Override
    public List<FieldMapping> handle(JSONObject jsonObject) {
        List<FieldMapping> list = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("map");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            Set<String> set = object.keySet();
            for (String key : set) {
                String value = object.getString(key);
                FieldMapping fieldMapping = new FieldMapping();
                fieldMapping.setValue(value);
                fieldMapping.setKey(key);
                list.add(fieldMapping);
            }
        }
        return list;
    }
}
