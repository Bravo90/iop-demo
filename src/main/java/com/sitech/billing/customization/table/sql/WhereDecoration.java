package com.sitech.billing.customization.table.sql;

import com.sitech.billing.customization.table.handler.operator.OperatorHandler;
import com.sitech.billing.customization.table.model.Field;
import com.sitech.billing.customization.table.model.Searcher;
import com.sitech.billing.customization.table.model.request.FieldValue;
import org.apache.ibatis.jdbc.SQL;

/**
 * 拼接where条件
 *
 * @author sunzhen
 * @date 2019/2/12 16:50
 */
public class WhereDecoration {
    public static void decoration(SQL sql, FieldValue fieldValue, Field field) {
        Searcher searcher = field.getSearcher();
        if (searcher.getSearchable() != null && searcher.getSearchable()) {
            if (fieldValue.getValue() != null && fieldValue.getValue().size() >= 1) {
                String where = OperatorHandler.handler(field, fieldValue);
                sql.WHERE(where);
            }
        }
    }
}
