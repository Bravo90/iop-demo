package com.sitech.billing.customization.table.excute;

import com.sitech.billing.customization.table.configuration.TableConfiguration;
import com.sitech.billing.customization.table.model.request.FieldOrder;
import com.sitech.billing.customization.table.model.request.FieldValue;
import com.sitech.billing.customization.table.model.request.RequestPageInfo;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * TODO
 *
 * @author sunzhen
 * @date 2019/1/15 14:25
 */
public class ExecuteBuilder {

    public static BaseExecute build(TableConfiguration tableConfiguration, JdbcTemplate jdbcTemplate, List<FieldValue> fieldValues,
                                    List<FieldOrder> fieldOrders, RequestPageInfo pageInfo, String dbDialect) {

        return new SingleTableExecute(tableConfiguration, jdbcTemplate, fieldValues, fieldOrders, pageInfo, dbDialect);
    }
}
