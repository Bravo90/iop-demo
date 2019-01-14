package com.sitech.billing.customization.table.sql;

import com.sitech.billing.customization.table.model.Field;
import org.springframework.beans.BeanUtils;

/**
 * 字段装饰
 *
 * @author sunzhen
 * @date 2019/1/10 9:36
 */
public class FieldDecoration {

    public static Field decorate(Field field) {
        Field fd = new Field();
        BeanUtils.copyProperties(field, fd);
        fd.setFieldName("`" + fd.getFieldName() + "`");
        if (field.getFieldType() == 3) {
            String fieldName = fd.getFieldName();
            fieldName = "date_format(" + fieldName + ", '%Y-%m-%d %H:%i:%s') as " + fieldName;
            fd.setFieldName(fieldName);
        }
        return fd;
    }

    public static Field decorateWhere(Field field) {
        Field fd = new Field();
        BeanUtils.copyProperties(field, fd);
        fd.setFieldName("`" + fd.getFieldName() + "`");
        if (fd.getFieldType() == 3) {
            String fieldName = fd.getFieldName();
            fieldName = "date_format(" + fieldName + ", '%Y-%m-%d %H:%i:%s')";
            fd.setFieldName(fieldName);
        }
        return fd;
    }
}
