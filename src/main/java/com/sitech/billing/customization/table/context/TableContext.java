package com.sitech.billing.customization.table.context;

import com.alibaba.druid.pool.DruidDataSource;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.dialect.helper.MySqlDialect;
import com.github.pagehelper.dialect.helper.OracleDialect;
import com.github.pagehelper.page.PageAutoDialect;
import com.github.pagehelper.parser.CountSqlParser;
import com.github.pagehelper.parser.SqlServerParser;
import com.sitech.billing.customization.table.configuration.TableConfiguration;
import com.sitech.billing.customization.table.configuration.TableConfigurationBuilder;
import com.sitech.billing.customization.table.excute.JDBCExecute;
import com.sitech.billing.customization.table.model.Field;
import com.sitech.billing.customization.table.model.Table;
import com.sitech.billing.customization.table.model.request.FieldOrder;
import com.sitech.billing.customization.table.model.request.FieldValue;
import com.sitech.billing.customization.table.model.request.RequestPageInfo;
import com.sitech.billing.customization.table.sql.SampleSqlBuilder;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * 定制化图表上下文
 *
 * @author sunzhen
 * @date 2019/1/8 14:30
 */
public class TableContext {

    private TableConfiguration tableConfiguration;

    private List<FieldValue> fieldValues;

    private List<FieldOrder> fieldOrders;

    private RequestPageInfo pageInfo;

    private String querySql;

    private String insertSql;

    private String deleteSql;

    private String updateSql;

    private TableContext(Integer id, List<FieldValue> fieldValues, List<FieldOrder> fieldOrders, RequestPageInfo pageInfo) {
        this.tableConfiguration = TableConfigurationBuilder.build(id);
        System.err.println(this.tableConfiguration);
        this.fieldValues = fieldValues;
        this.fieldOrders = fieldOrders;
        this.pageInfo = pageInfo;
    }


    public TableContext querySqlInit() {
        SQL sql = new SQL();
        this.querySql = SampleSqlBuilder.initQuerySql(this.tableConfiguration, this.fieldValues,
                this.fieldOrders, this.pageInfo);


        return this;
    }

    public TableContext insertSqlInit() {
        SQL sql = new SQL();
        this.querySql = sql.toString();
        return this;
    }


    public TableContext deleteSqlInit() {
        SQL sql = new SQL();
        this.querySql = sql.toString();
        return this;
    }


    public TableContext updateSqlInit() {
        SQL sql = new SQL();
        this.querySql = sql.toString();
        return this;
    }

    public void insert() {
    }

    public void update() {
    }

    public void delete() {
    }

    public List<List<String>> query(JDBCExecute<List<Map<String, Object>>> execute) {

        List<Map<String, Object>> resultMap = execute.execute(this.querySql);

        return null;
    }

    public PageInfo<List<List<String>>> queryByPage(JdbcTemplate jdbcTemplate) {

        //1、统计总数
        CountSqlParser countSqlParser = new CountSqlParser();
        String countSql = countSqlParser.getSmartCountSql(this.querySql);
        Integer count = jdbcTemplate.queryForObject(countSql, int.class);

        Page page = new Page(pageInfo.getPageNum(), pageInfo.getPageSize());

        System.out.println(page.getStartRow());

        MySqlDialect mySqlDialect = new MySqlDialect();
        CacheKey cacheKey = new CacheKey();
        String pageSql = mySqlDialect.getPageSql(this.querySql, page, cacheKey);

        System.err.println(pageSql);
        if (page.getStartRow() == 0) {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(pageSql, page.getPageSize());
            System.out.println(result);
        } else {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(pageSql, page.getStartRow(), page.getPageSize());
            System.out.println(result);
        }

        return null;
    }

    public static class Builder {

        private Integer id;

        private List<FieldValue> fieldValues;

        private List<FieldOrder> fieldOrders;

        private RequestPageInfo pageInfo;


        public Builder tableConfig(Integer id) {
            this.id = id;
            return this;
        }

        public Builder fieldValues(List<FieldValue> fieldValues) {
            this.fieldValues = fieldValues;
            return this;
        }

        public Builder fieldOrders(List<FieldOrder> fieldOrders) {
            this.fieldOrders = fieldOrders;
            return this;
        }

        public Builder pageInfo(RequestPageInfo pageInfo) {
            this.pageInfo = pageInfo;
            return this;
        }

        public TableContext build() {
            return new TableContext(this.id, this.fieldValues,
                    this.fieldOrders, this.pageInfo);
        }
    }
}
