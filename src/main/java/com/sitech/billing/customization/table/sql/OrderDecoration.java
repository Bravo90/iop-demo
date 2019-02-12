package com.sitech.billing.customization.table.sql;

import com.sitech.billing.customization.table.model.request.FieldOrder;
import org.apache.ibatis.jdbc.SQL;

/**
 * 拼接order排序
 *
 * @author sunzhen
 * @date 2019/2/12 16:49
 */
public class OrderDecoration {
    //处理排序
    public static void decoration(SQL sql, FieldOrder fieldOrder) {
        sql.ORDER_BY(fieldOrder.getField() + " " + fieldOrder.getType());
    }
}
