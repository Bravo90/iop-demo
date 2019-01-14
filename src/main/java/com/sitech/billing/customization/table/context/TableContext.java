package com.sitech.billing.customization.table.context;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sitech.billing.customization.table.configuration.TableConfiguration;
import com.sitech.billing.customization.table.configuration.TableConfigurationBuilder;
import com.sitech.billing.customization.table.excute.JDBCExecute;
import com.sitech.billing.customization.table.model.request.FieldOrder;
import com.sitech.billing.customization.table.model.request.FieldValue;
import com.sitech.billing.customization.table.model.request.RequestPageInfo;
import com.sitech.billing.customization.table.pagehelper.PageHandler;
import com.sitech.billing.customization.table.sql.SampleSqlBuilder;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class TableContext {

    private JdbcTemplate jdbcTemplate;

    private TableConfiguration tableConfiguration;

    private List<FieldValue> fieldValues;

    private List<FieldOrder> fieldOrders;

    private RequestPageInfo pageInfo;

    private String dbDialect;

    private String querySql;

    private String insertSql;

    private String deleteSql;

    private String updateSql;

    private TableContext(Integer id, JdbcTemplate jdbcTemplate, List<FieldValue> fieldValues,
                         List<FieldOrder> fieldOrders, RequestPageInfo pageInfo, String dbDialect) {
        this.tableConfiguration = TableConfigurationBuilder.build(id);
        this.jdbcTemplate = jdbcTemplate;
        this.fieldValues = fieldValues;
        this.fieldOrders = fieldOrders;
        this.pageInfo = pageInfo;
        this.dbDialect = dbDialect;
    }

    public TableContext querySqlInit() {
        this.querySql = SampleSqlBuilder.initQuerySql(this.tableConfiguration, this.fieldValues,
                this.fieldOrders, this.pageInfo);
        return this;
    }

    public TableContext insertSqlInit() {
        return this;
    }


    public TableContext deleteSqlInit() {
        return this;
    }


    public TableContext updateSqlInit() {
        return this;
    }

    public void insert() {
    }

    public void update() {
    }

    public void delete() {
    }

    public List<List<String>> query() {
        return null;
    }

    public PageInfo<Map<String, Object>> queryByPage() throws Exception {
        log.debug(querySql);
        PageHandler pageHandler = new PageHandler(this.querySql, pageInfo, dbDialect, jdbcTemplate);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo(pageHandler.pageResult());
        return pageInfo;
    }

    public static class Builder {

        private JdbcTemplate jdbcTemplate;
        private String dbDialect;

        private Integer id;

        private List<FieldValue> fieldValues;

        private List<FieldOrder> fieldOrders;

        private RequestPageInfo pageInfo;


        public Builder dbDialect(String dbDialect) {
            this.dbDialect = dbDialect;
            return this;
        }

        public Builder jdbc(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
            return this;
        }

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
            return new TableContext(this.id, this.jdbcTemplate, this.fieldValues,
                    this.fieldOrders, this.pageInfo, this.dbDialect);
        }
    }
}
