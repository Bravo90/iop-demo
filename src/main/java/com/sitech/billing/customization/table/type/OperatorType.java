package com.sitech.billing.customization.table.type;

/**
 * 运算类型
 * @author sunzhen
 * @date 2019/1/10 10:04
 */
public abstract class OperatorType {
    public static final int EQUALS = 1;
    public static final int NOT_EQUALS = 2;
    public static final int MORE_THAN_EQUALS = 3;
    public static final int LESS_THAN_EQUALS = 4;
    public static final int MORE_THAN = 5;
    public static final int LESS_THAN = 6;
    public static final int LIKE_ALL = 7;
    public static final int LIKE_LEFT = 8;
    public static final int LIKE_RIGHT = 9;
    public static final int BETWEEN_AND = 10;
    public static final int IN = 11;
}
