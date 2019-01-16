package com.sitech.billing.customization.table.excute;

import com.sitech.billing.customization.table.configuration.TableConfiguration;
import com.sitech.billing.customization.table.model.request.FieldOrder;
import com.sitech.billing.customization.table.model.request.FieldValue;
import com.sitech.billing.customization.table.model.request.RequestPageInfo;
import com.sitech.billing.customization.table.type.DataSourceType;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 执行增删改查类的工厂类；
 *
 * @author sunzhen
 * @date 2019/1/15 14:25
 */
public class ExecuteBuilder {

    //TODO：尚未完善
    public static BaseExecute build(TableConfiguration tableConfiguration,
                                    JdbcTemplate jdbcTemplate,
                                    List<FieldValue> fieldValues,
                                    List<FieldOrder> fieldOrders,
                                    RequestPageInfo pageInfo,
                                    String dbDialect) {
        BaseExecute execute = null;
        switch (tableConfiguration.getDataSourceType()) {
            case DataSourceType.SINGLE_TABLE:
                execute = new SingleTableExecute(tableConfiguration, jdbcTemplate,
                        fieldValues, fieldOrders, pageInfo, dbDialect);
                break;
            case DataSourceType.SQL_STATEMENT:
                execute = new SqlExcute();
                break;
            case DataSourceType.MULTI_TABLE:
                execute = new MultiExcute();
                break;
            case DataSourceType.FILE:
                execute = new FileExecute();
                break;
            case DataSourceType.COMMAND:
                execute = new CommandExecute();
                break;
        }
        return execute;
    }
}
