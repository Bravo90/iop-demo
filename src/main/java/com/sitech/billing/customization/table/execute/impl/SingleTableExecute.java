package com.sitech.billing.customization.table.execute.impl;

import com.github.pagehelper.PageInfo;
import com.sitech.billing.customization.table.configuration.TableConfiguration;
import com.sitech.billing.customization.table.execute.BaseExecute;
import com.sitech.billing.customization.table.handler.type.FieldTypeHandler;
import com.sitech.billing.customization.table.model.Field;
import com.sitech.billing.customization.table.model.Table;
import com.sitech.billing.customization.table.model.request.FieldOrder;
import com.sitech.billing.customization.table.model.request.FieldValue;
import com.sitech.billing.customization.table.model.request.RequestPageInfo;
import com.sitech.billing.customization.table.model.request.UpdateAndInsertParam;
import com.sitech.billing.customization.table.pagehelper.PageResultHandler;
import com.sitech.billing.customization.table.sql.builder.SampleSqlBuilder;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * 单表的增删改查
 *
 * @author sunzhen
 * @date 2019/1/15 14:06
 */
public class SingleTableExecute extends BaseExecute {


    public SingleTableExecute(TableConfiguration tableConfiguration, JdbcTemplate jdbcTemplate, List<FieldValue> fieldValues,
                              List<FieldOrder> fieldOrders, RequestPageInfo pageInfo, String dbDialect) {
        super();
        this.dbDialect = dbDialect;
        this.jdbcTemplate = jdbcTemplate;
        this.tableConfiguration = tableConfiguration;
        this.fieldValues = fieldValues;
        this.fieldOrders = fieldOrders;
        this.pageInfo = pageInfo;
    }

    @Override
    public List<Map<String, Object>> query() {
        return null;
    }

    @Override
    public PageInfo<Map<String, Object>> queryByPage() {
        String sql = querySqlInit();
        PageResultHandler pageHandler = new PageResultHandler(sql, pageInfo, dbDialect, jdbcTemplate);
        try {
            PageInfo<Map<String, Object>> pageInfo = new PageInfo(pageHandler.pageResult());
            return pageInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(List<UpdateAndInsertParam> list) {
        Table table = this.tableConfiguration.getTables().get(0);
        String tableName = table.getTableName();
        SQL sql = new SQL();
        sql.INSERT_INTO(tableName);
        for (UpdateAndInsertParam param : list) {
            Field field = table.getFieldByFieldName(param.getFieldName());
            String fieldValue = param.getFieldValue();
            if (fieldValue != null) {
                sql.VALUES(field.getFieldName(), FieldTypeHandler.handler(field.getFieldType(), fieldValue));
            }
        }
        System.out.println(sql.toString());
        this.jdbcTemplate.update(sql.toString());
    }

    @Override
    public void update(List<UpdateAndInsertParam> list) {
        Table table = this.tableConfiguration.getTables().get(0);
        String tableName = table.getTableName();
        SQL sql = new SQL();
        sql.UPDATE(tableName);
        for (UpdateAndInsertParam param : list) {
            Field field = table.getFieldByFieldName(param.getFieldName());
            String fieldValue = param.getFieldValue();
            String oldValue = param.getOldValue();
            sql.SET(field.getFieldName() + "=" + FieldTypeHandler.handler(field.getFieldType(), fieldValue));
            sql.WHERE(field.getFieldName() + "=" + FieldTypeHandler.handler(field.getFieldType(), oldValue));
        }
        System.out.println(sql);
        this.jdbcTemplate.update(sql.toString());
    }

    @Override
    public void delete(List<List<UpdateAndInsertParam>> list) {
        Table table = this.tableConfiguration.getTables().get(0);
        String tableName = table.getTableName();
        for (List<UpdateAndInsertParam> li : list) {
            System.out.println(li);
            SQL sql = new SQL();
            sql.DELETE_FROM(tableName);
            for (UpdateAndInsertParam param : li) {
                Field field = table.getFieldByFieldName(param.getFieldName());
                String fieldValue = param.getFieldValue();
                sql.WHERE(field.getFieldName() + "=" + FieldTypeHandler.handler(field.getFieldType(), fieldValue));
            }
            System.out.println(sql);
            this.jdbcTemplate.update(sql.toString());
        }
    }

    private String querySqlInit() {
        String sql = SampleSqlBuilder.initQuerySql(this.tableConfiguration, this.fieldValues,
                this.fieldOrders, this.pageInfo);
        return sql;
    }


    private String insertSqlInit() {
        return null;
    }


    private String deleteSqlInit() {
        return null;
    }


    private String updateSqlInit() {
        return null;
    }
}
