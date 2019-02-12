package com.sitech.billing.customization.table.sql.builder;

import com.sitech.billing.common.exception.IopException;
import com.sitech.billing.customization.table.configuration.TableConfiguration;
import com.sitech.billing.customization.table.handler.operator.OperatorHandler;
import com.sitech.billing.customization.table.model.Field;
import com.sitech.billing.customization.table.model.Searcher;
import com.sitech.billing.customization.table.model.Table;
import com.sitech.billing.customization.table.model.request.FieldOrder;
import com.sitech.billing.customization.table.model.request.FieldValue;
import com.sitech.billing.customization.table.model.request.RequestPageInfo;
import com.sitech.billing.customization.table.sql.FieldDecoration;
import com.sitech.billing.customization.table.sql.OrderDecoration;
import com.sitech.billing.customization.table.sql.WhereDecoration;
import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 多表关联查询，且不支持多表关联sql语句
 *
 * @author sunzhen
 * @date 2019/1/15 11:23
 */
public class MultiSqlBuilder {

    public static Map<String, String> initQuerySql(TableConfiguration cfg, List<FieldValue> fieldValues,
                                                   List<FieldOrder> fieldOrders, RequestPageInfo pageInfo) {

        List<Table> tables = cfg.getTables();
        if (tables.size() != 2) {
            throw new RuntimeException("配置信息错误");
        }

        Map<String, FieldValue> fieldValueMap = FieldValue.toMap(fieldValues);
        Map<String, FieldOrder> fieldOrderMap = FieldOrder.toMap(fieldOrders);

        SQL sql = new SQL();
        List<Field> fields1 = tables.get(0).getFields();
        if (fieldValueMap != null && fieldValueMap.size() > 0) {
            select(fieldValueMap, fieldOrderMap, sql, fields1, false);
        }
        sql.FROM(tables.get(0).getTableName());

        SQL sql2 = new SQL();
        List<Field> fields2 = tables.get(1).getFields();
        select(fieldValueMap, fieldOrderMap, sql2, fields2, true);
        sql2.FROM(tables.get(1).getTableName());

        String linkField1 = getLinkedFieldName(fields1);
        String linkField2 = getLinkedFieldName(fields2);

        if (sql != null && !"".equals(sql.toString())) {
            StringBuffer sb = new StringBuffer(linkField2);
            sb.append(" IN ").append("(").append(sql.toString()).append(")");
            sql2.WHERE(sb.toString());
        }

        SQL sql1 = new SQL();
        for (Field field : fields1) {
            sql1.SELECT(field.getFieldName());
        }
        sql1.FROM(tables.get(0).getTableName());

        Map<String, String> map = new HashMap<>();
        map.put("sql1", sql1.toString() + " WHERE " + linkField1 + " = ?");
        map.put("sql2", sql2.toString());
        map.put("linkedField1", linkField1);
        map.put("linkedField2", linkField2);
        return map;
    }

    private static String getLinkedFieldName(List<Field> fields) {
        for (Field field : fields) {
            if (field.getLinked() != null && field.getLinked()) {
                return field.getFieldName();
            }
        }
        throw new IopException("配置信息解析错误");
    }

    private static void select(Map<String, FieldValue> fieldValueMap,
                               Map<String, FieldOrder> fieldOrderMap,
                               SQL sql, List<Field> fields, boolean isAllField) {
        for (Field field : fields) {
            FieldValue fieldValue = fieldValueMap.get(field.getFieldName());
            FieldOrder fieldOrder = fieldOrderMap.get(field.getFieldName());
            if (fieldValue != null && fieldValueMap.size() > 0) {
                Field fd = FieldDecoration.decorateWhere(field);
                WhereDecoration.decoration(sql, fieldValue, fd);
            }
            //处理order
            if (fieldOrder != null && fieldOrderMap.size() > 0) {
                OrderDecoration.decoration(sql, fieldOrder);
            }

            if (isAllField) {
                if (field.getViewable() != null && field.getViewable()) {
                    Field fd = FieldDecoration.decorate(field);
                    sql.SELECT(fd.getFieldName());
                }
            } else {
                if (field.getLinked() != null && field.getLinked()) {
                    sql.SELECT(field.getFieldName());
                }
            }
        }
    }
}
