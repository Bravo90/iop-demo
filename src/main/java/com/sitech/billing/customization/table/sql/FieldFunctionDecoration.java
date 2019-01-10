package com.sitech.billing.customization.table.sql;

import com.sitech.billing.customization.table.model.Field;

/**
 * 字段装饰
 * @author sunzhen
 * @date 2019/1/10 9:36
 */
public class FieldFunctionDecoration {

    public static Field decorate(Field field) {
        addMarkToFieldName(field);
        return field;
    }

    private static Field addMarkToFieldName(Field field) {
        String fieldName = field.getFieldName();
        field.setFieldName("`" + fieldName + "`");
        return field;
    }

}
