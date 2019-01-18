package com.sitech.billing.customization.table.excute;

import com.github.pagehelper.PageInfo;
import com.sitech.billing.customization.table.configuration.TableConfiguration;
import com.sitech.billing.customization.table.model.request.FieldOrder;
import com.sitech.billing.customization.table.model.request.FieldValue;
import com.sitech.billing.customization.table.model.request.RequestPageInfo;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * 执行增删改查的父类
 *
 * @author sunzhen
 * @date 2019/1/15 13:46
 */
public abstract class BaseExecute {

    protected JdbcTemplate jdbcTemplate;

    protected TableConfiguration tableConfiguration;

    protected List<FieldValue> fieldValues;

    protected List<FieldOrder> fieldOrders;

    protected RequestPageInfo pageInfo;

    protected String dbDialect;

    public abstract List<Map<String, Object>> query();

    public abstract PageInfo<Map<String, Object>> queryByPage();

    public abstract void insert();

    public abstract void update();

    public abstract void delete();
}