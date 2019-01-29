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
import com.sitech.billing.customization.table.sql.MultiSqlBuilder;
import com.sitech.billing.customization.table.sql.SampleSqlBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 目前是：两张表关联查询，由于分布式mysql和内存库的关联SQL查询存在问题，故需要做特殊查询处理
 *
 * @author sunzhen
 * @date 2019/1/15 15:18
 * <p>
 * 分析：两张数据表的基本数据关系：一对一，一对多，多对多
 */
public class MultiExcute extends BaseExecute {

    public MultiExcute(TableConfiguration tableConfiguration, JdbcTemplate jdbcTemplate, List<FieldValue> fieldValues,
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
        throw new IopException(ErrorMsgEnum.OPERATION_NOT_SUPPORT);
    }

    @Override
    public PageInfo<Map<String, Object>> queryByPage() {
        Map<String, String> sqlMap = querySqlInit();
        String sql1 = sqlMap.get("sql1");
        String sql2 = sqlMap.get("sql2");
        String linkedField1 = sqlMap.get("linkedField1");
        PageResultHandler pageHandler = new PageResultHandler(sql2, pageInfo, dbDialect, jdbcTemplate);
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            List<Map<String, Object>> list1 = pageHandler.pageResult();
            for (int i = 0; i < list1.size(); i++) {
                Map<String, Object> map = list1.get(i);
                Object obj = map.get(linkedField1);
                Map<String, Object> linkedMap = jdbcTemplate.queryForMap(sql1, obj.toString());
                map.putAll(linkedMap);
                list1.remove(i);
                list1.add(i, map);
            }
            PageInfo pageInfo = new PageInfo(list1);
            return pageInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    private Map<String, String> querySqlInit() {
        Map<String, String> sqlMap = MultiSqlBuilder.initQuerySql(this.tableConfiguration, this.fieldValues,
                this.fieldOrders, this.pageInfo);
        return sqlMap;
    }
}
