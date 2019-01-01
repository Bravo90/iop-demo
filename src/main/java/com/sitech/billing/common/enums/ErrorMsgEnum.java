package com.sitech.billing.common.enums;

/**
 * 系统自定义异常错误信息
 *
 * @author sunzhen
 * @date 2018-12-25 13:39:21
 */
public enum ErrorMsgEnum {

    SYSTEM_ERROR(99999, "系统内部异常，请联系管理员"),

    USERNAME_OR_PASSWORD_IS_EMPTY(1001001, "用户名密码为空"),
    UNKNOWN_ACCOUNT(1001002, "用户不存在"),
    INCORRECT_CREDENTIALS(1001003, "用户密码不正确"),
    UNAUTHORIZED(1001004, "用户权限不足"),
    LOGIN_TIMEOUT(1001005, "登录超时"),
    USER_ID_IS_NULL(1001005, "用户ID为空"),
    ROLE_ID_IS_NULL(1001006, "角色ID为空"),
    AUTHC_ID_IS_NULL(1001007, "权限ID为空"),
    USERNAME_ALREADY_EXIST(1001008,"用户已存在"),
    ROLE_NAME_ALREADY_EXIST(1001009,"角色名称已存在"),
    AUTHC_NAME_ALREADY_EXIST(1001010,"权限名称已存在"),
    ;
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
