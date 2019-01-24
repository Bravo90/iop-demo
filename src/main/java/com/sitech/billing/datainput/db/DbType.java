package com.sitech.billing.datainput.db;

/**
 * 数据库类型枚举
 *
 * @author sunzhen
 * @date 2019/1/24 17:12
 */
public abstract class DbType {
    //mysql
    public static final int MYSQL = 1;
    //oracle
    public static final int ORACLE = 2;
    //内存库
    public static final int DMDB = 3;
}
