package com.sitech.billing.customization.table.type;

/**
 * 配置表的数据来源
 *
 * @author sunzhen
 * @date 2019/1/16 10:34
 */
public abstract class DataSourceType {
    //单表
    public static final int SINGLE_TABLE = 1;
    //sql语句
    public static final int SQL_STATEMENT = 2;
    //多表
    public static final int MULTI_TABLE = 3;
    //文件类
    public static final int FILE = 4;
    //命令类
    public static final int COMMAND = 5;
}
