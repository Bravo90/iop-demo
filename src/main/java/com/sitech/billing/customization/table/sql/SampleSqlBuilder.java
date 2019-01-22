package com.sitech.billing.customization.table.sql;

import com.sitech.billing.customization.table.configuration.TableConfiguration;
import com.sitech.billing.customization.table.handler.operator.OperatorHandler;
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
 * 单表时，SQL处理
 *
 * @author sunzhen
 * @date 2019/1/8 16:19
 */
public class SampleSqlBuilder {

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
            //FieldFunctionDecoration.decorate()对字段进行处理,如:函数添加
            FieldValue fieldValue = fieldValueMap.get(field.getFieldName());
            FieldOrder fieldOrder = fieldOrderMap.get(field.getFieldName());
            //处理字段
            if (field.getViewable() != null && field.getViewable()) {
                Field fd = FieldDecoration.decorate(field);
                sql.SELECT(fd.getFieldName());
            }
            //处理where
            if (fieldValue != null) {
                Field fd = FieldDecoration.decorateWhere(field);
                where(sql, fieldValue, fd);
            }
            //处理order
            if (fieldOrder != null) {
                order(sql, fieldOrder);
            }
        }
        //sql拦截器，可对sql进行自定义处理
        SqlFilter.doFilter(sql);
        return sql.toString();
    }

    //处理where语句
    private static SQL where(SQL sql, FieldValue fieldValue, Field field) {
        StringBuffer sb = new StringBuffer();
        Searcher searcher = field.getSearcher();
        if (searcher.getSearchable() != null && searcher.getSearchable()) {
            if (fieldValue.getValue() != null && fieldValue.getValue().size() >= 1) {
                String where = OperatorHandler.handler(field, fieldValue);
                sql.WHERE(where);
            }
        }
        return sql;
    }

    //处理排序
    private static SQL order(SQL sql, FieldOrder fieldOrder) {
        sql.ORDER_BY(fieldOrder.getField() + " " + fieldOrder.getType());
        return sql;
    }
}
