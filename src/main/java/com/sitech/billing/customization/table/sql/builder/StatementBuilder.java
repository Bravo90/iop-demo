package com.sitech.billing.customization.table.sql.builder;

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
import com.sitech.billing.customization.table.sql.SqlUtil;
import com.sitech.billing.customization.table.sql.WhereDecoration;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 * 配置为sql语句时，处理类
 *
 * @author sunzhen
 * @date 2019/1/21 16:52
 */
public class StatementBuilder {

    public static String initQuerySql(TableConfiguration cfg, List<FieldValue> fieldValues,
                                      List<FieldOrder> fieldOrders, RequestPageInfo pageInfo) {

        if (cfg.getTables().size() != 1) {
            throw new RuntimeException("配置信息错误");
        }

        Table table = cfg.getTables().get(0);
        String sqlStatement = table.getTableName();
        SQL sql = SqlUtil.parseSQLObject(sqlStatement);

        List<Field> fields = table.getFields();
        Map<String, FieldValue> fieldValueMap = FieldValue.toMap(fieldValues);
        Map<String, FieldOrder> fieldOrderMap = FieldOrder.toMap(fieldOrders);
        for (Field field : fields) {
            FieldValue fieldValue = fieldValueMap.get(field.getFieldName());
            FieldOrder fieldOrder = fieldOrderMap.get(field.getFieldName());
            //处理字段
            if (field.getViewable() != null && field.getViewable()) {
                Field fd = FieldDecoration.statementDecorate(field);
                String fieldAlias = fd.getFieldAlias();
                String fieldName = fd.getFieldName();
                String column = fieldAlias + " AS '" + fieldName + "'";
                sql.SELECT(column);
            }
            //处理where
            if (fieldValue != null) {
                Field fd = FieldDecoration.statementDecorateWhere(field);
                WhereDecoration.decoration(sql, fieldValue, fd);
            }
            //处理order
            if (fieldOrder != null) {
                String fieldName = fieldOrder.getField();
                Field fd = table.getFieldByFieldName(fieldName);
                fieldOrder.setField(fd.getFieldAlias());
                OrderDecoration.decoration(sql, fieldOrder);
            }
        }
        return sql.toString();
    }
}
