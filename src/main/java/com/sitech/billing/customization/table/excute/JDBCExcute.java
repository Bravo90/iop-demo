package com.sitech.billing.customization.table.excute;

/**
 * TODO
 *
 * @author sunzhen
 * @date 2019/1/8 13:46
 */
public interface JDBCExcute<T> {
    T excute(String sql);
}
