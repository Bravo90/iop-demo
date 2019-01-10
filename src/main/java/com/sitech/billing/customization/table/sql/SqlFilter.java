package com.sitech.billing.customization.table.sql;

import org.apache.ibatis.jdbc.SQL;

/**
 * sql拦截处理
 * @author sunzhen
 * @date 2019/1/9 16:07
 */
public class SqlFilter {
    public static SQL doFiletr(SQL sql) {
        return sql;
    }
}
