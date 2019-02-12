package com.sitech.billing.customization.table.execute.impl;

import com.github.pagehelper.PageInfo;
import com.sitech.billing.common.enums.ErrorMsgEnum;
import com.sitech.billing.common.exception.IopException;
import com.sitech.billing.customization.table.configuration.TableConfiguration;
import com.sitech.billing.customization.table.execute.BaseExecute;
import com.sitech.billing.customization.table.model.request.FieldOrder;
import com.sitech.billing.customization.table.model.request.FieldValue;
import com.sitech.billing.customization.table.model.request.RequestPageInfo;
import com.sitech.billing.customization.table.pagehelper.PageResultHandler;
import com.sitech.billing.customization.table.sql.builder.StatementBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * 查询语句处理器
 *
 * @author sunzhen
 * @date 2019/1/15 15:17
 */
public class SqlStatementExecute extends BaseExecute {

    public SqlStatementExecute(TableConfiguration tableConfiguration, JdbcTemplate jdbcTemplate, List<FieldValue> fieldValues,
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

    private String querySqlInit() {
        return StatementBuilder.initQuerySql(this.tableConfiguration, this.fieldValues,
                this.fieldOrders, this.pageInfo);
    }

    @Override
    public void insert() {
        throw new IopException(ErrorMsgEnum.OPERATION_NOT_SUPPORT);
    }

    @Override
    public void update() {
        throw new IopException(ErrorMsgEnum.OPERATION_NOT_SUPPORT);
    }

    @Override
    public void delete() {
        throw new IopException(ErrorMsgEnum.OPERATION_NOT_SUPPORT);
    }
}
