package com.sitech.billing.customization.table.handler.fieldmapping;

import com.alibaba.fastjson.JSONObject;
import com.sitech.billing.customization.table.model.FieldMapping;

import java.util.List;

/**
 * TODO
 *
 * @author sunzhen
 * @date 2019/1/16 13:41
 */
public interface BaseFieldMappingHandler {
    List<FieldMapping> handle(JSONObject jsonObject);
}
