package com.sitech.billing.common.enums;

/**
 * 系统自定义异常错误信息
 *
 * @author sunzhen
 * @date 2018-12-25 13:39:21
 */
public enum ErrorMsgEnum {

    SYSTEM_ERROR(9999, "系统内部异常，请联系管理员"),
    OBJECT_EMPTY_ERROR(9001, "对象为空"),
    COLLECTION_EMPTY_ERROR(9002, "集合为空"),

    USERNAME_OR_PASSWORD_IS_EMPTY(1001, "用户名密码为空"),
    UNKNOWN_ACCOUNT(1002, "用户不存在"),
    INCORRECT_CREDENTIALS(1003, "用户密码不正确"),
    UNAUTHORIZED(1004, "用户权限不足"),
    LOGIN_TIMEOUT(1005, "登录超时"),
    USER_ID_IS_NULL(1005, "用户ID为空"),
    ROLE_ID_IS_NULL(1006, "角色ID为空"),
    AUTHC_ID_IS_NULL(1007, "权限ID为空"),
    USERNAME_ALREADY_EXIST(1008, "用户已存在"),
    ROLE_NAME_ALREADY_EXIST(1009, "角色名称已存在"),
    AUTHC_NAME_ALREADY_EXIST(1010, "权限名称已存在"),

    CONFIGURATION_NOT_EXIST(2001, "表配置信息不存在"),
    FIELD_MAPPING_HANDLER_NOT_EXIST(2002, "字段映射解析类不存在"),
    OPERATION_NOT_SUPPORT(2003, "不支持该操作"),

    FIELD_NOT_MATCH_DATA(3001,"字段数量和数据集不匹配");


    private Integer errorCode;
    private String errorMsg;

    ErrorMsgEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
