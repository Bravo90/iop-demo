package com.sitech.billing.customization.table.excute;

/**
 * 执行sql的接口，需要自己实现
 *
 * @author sunzhen
 * @date 2019/1/8 13:46
 */
public interface JDBCExcute<T> {
    T excute(String sql);
}
