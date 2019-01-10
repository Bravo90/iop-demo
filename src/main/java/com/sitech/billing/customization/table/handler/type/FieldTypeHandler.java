package com.sitech.billing.customization.table.handler.type;

import com.sitech.billing.customization.table.type.FieldType;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理字段类型
 * @author sunzhen
 * @date 2019/1/10 10:36
 */
public class FieldTypeHandler {

    public static List<String> handler(int fieldType, List<String> values) {
        List<String> list = new ArrayList<>();
        switch (fieldType) {
            case FieldType.NUMBER:
                list.addAll(values);
                break;
            case FieldType.STRING:
            case FieldType.DATETIME:
                for (String value : values) {
                    list.add("'" + value + "'");
                }
                break;
        }
        return list;
    }
}
