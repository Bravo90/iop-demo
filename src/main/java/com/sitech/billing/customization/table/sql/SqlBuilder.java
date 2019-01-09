package com.sitech.billing.customization.table.sql;

import com.sitech.billing.customization.table.configuration.TableConfiguration;
import com.sitech.billing.customization.table.model.Field;
import com.sitech.billing.customization.table.model.Searcher;
import com.sitech.billing.customization.table.model.Table;
import com.sitech.billing.customization.table.model.request.FieldOrder;
import com.sitech.billing.customization.table.model.request.FieldValue;
import com.sitech.billing.customization.table.model.request.RequestPageInfo;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author sunzhen
 * @date 2019/1/8 16:19
 */
public class SqlBuilder {

    public static String initQuerySql(TableConfiguration cfg, List<FieldValue> fieldValues,
                                      List<FieldOrder> fieldOrders, RequestPageInfo pageInfo) {

        SQL sql = new SQL();
        List<Table> tables = cfg.getTables();
        if (tables.size() != 1) {
            throw new RuntimeException("配置信息错误");
        }
        Table table = tables.get(0);
        String tableName = table.getTableName();
        sql.FROM(tableName);
        List<Field> fields = table.getFields();
        Map<String, FieldValue> fieldValueMap = FieldValue.toMap(fieldValues);
        Map<String, FieldOrder> fieldOrderMap = FieldOrder.toMap(fieldOrders);
        for (Field field : fields) {
            if (field.isViewable()) {
                sql.SELECT(field.getFieldName());
            }
            //处理where
            FieldValue fieldValue = fieldValueMap.get(field.getFieldName());
            if (fieldValue != null) {
                where(sql, fieldValue, field.getSearcher());
            }
            //处理order
            FieldOrder fieldOrder = fieldOrderMap.get(field.getFieldName());
            if (fieldOrder != null) {
                order(sql, fieldOrder);
            }
        }


        return sql.toString();
    }

    private static SQL where(SQL sql, FieldValue fieldValue, Searcher searcher) {

        StringBuffer sb = new StringBuffer();
        if (searcher.isSearchable()) {
            if (fieldValue.getValue() != null && fieldValue.getValue().size() >= 1) {
                sb.append(fieldValue.getName()).append("=").append(fieldValue.getValue().get(0));
                sql.WHERE(sb.toString());
            }
        }
        return sql;
    }

    private static SQL order(SQL sql, FieldOrder fieldOrder) {
        sql.ORDER_BY(fieldOrder.getField() + " " + fieldOrder.getType());
        return sql;
    }
}
