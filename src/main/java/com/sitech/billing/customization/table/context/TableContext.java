package com.sitech.billing.customization.table.context;

import com.sitech.billing.customization.table.configuration.TableConfiguration;
import com.sitech.billing.customization.table.model.Field;
import com.sitech.billing.customization.table.model.request.FieldOrder;
import com.sitech.billing.customization.table.model.request.FieldValue;
import com.sitech.billing.customization.table.model.request.RequestPageInfo;
import org.apache.ibatis.jdbc.SQL;

import javax.security.auth.login.Configuration;
import java.util.List;

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

    private TableContext(TableConfiguration cfg, List<FieldValue> fieldValues, List<FieldOrder> fieldOrders, RequestPageInfo pageInfo) {
        this.tableConfiguration = cfg;
        this.fieldValues = fieldValues;
        this.fieldOrders = fieldOrders;
        this.pageInfo = pageInfo;
    }


    public TableContext querySqlInit() {
        SQL sql = new SQL();
        this.querySql = sql.toString();
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

    public void query(){}
    public void insert(){}
    public void update(){}
    public void delete(){}

    public static class Builder {

        private TableConfiguration tableConfiguration;

        private List<FieldValue> fieldValues;

        private List<FieldOrder> fieldOrders;

        private RequestPageInfo pageInfo;


        public Builder tableConfig(TableConfiguration configuration) {
            this.tableConfiguration = configuration;
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
            return new TableContext(this.tableConfiguration, this.fieldValues,
                    this.fieldOrders, this.pageInfo);
        }
    }

}
