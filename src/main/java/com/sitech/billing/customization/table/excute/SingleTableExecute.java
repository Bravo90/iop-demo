package com.sitech.billing.customization.table.excute;

import com.github.pagehelper.PageInfo;
import com.sitech.billing.customization.table.configuration.TableConfiguration;
import com.sitech.billing.customization.table.model.request.FieldOrder;
import com.sitech.billing.customization.table.model.request.FieldValue;
import com.sitech.billing.customization.table.model.request.RequestPageInfo;
import com.sitech.billing.customization.table.pagehelper.PageHandler;
import com.sitech.billing.customization.table.sql.SampleSqlBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import sun.rmi.runtime.Log;

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
        System.err.println(sql);
        PageHandler pageHandler = new PageHandler(sql, pageInfo, dbDialect, jdbcTemplate);
        try {
            PageInfo<Map<String, Object>> pageInfo = new PageInfo(pageHandler.pageResult());
            return pageInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

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
