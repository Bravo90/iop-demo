package com.sitech.billing.customization.table.handler.fieldmapping;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author sunzhen
 * @date 2019/1/16 13:44
 */
public class FieldMappingHandlerFactory {

    private static Map<String, Class<?>> handlerMap = new HashMap();

    public static BaseFieldMappingHandler getHandler(JSONObject json) throws Exception {
        String type = "type" + json.getInteger("type");
        Class<?> clazz = handlerMap.get(type);
        BaseFieldMappingHandler handler = (BaseFieldMappingHandler) clazz.newInstance();
        return handler;
    }

    static {
        handlerMap.put("type" + 1, FieldMappingHandler1.class);
    }


}
