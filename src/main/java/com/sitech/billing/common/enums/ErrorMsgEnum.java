package com.sitech.billing.common.enums;

/**
 * 系统自定义异常错误信息
 *
 * @author sunzhen
 * @date 2018-12-25 13:39:21
 */
public enum ErrorMsgEnum {

    USERNAME_OR_PASSWORD_IS_EMPTY(10001,"用户名密码为空"),
    UNKNOWN_ACCOUNT(10002,"用户不存在"),
    INCORRECT_CREDENTIALS(10003,"用户密码不正确"),
    UNAUTHORIZED(10004,"用户权限不足"),
    LOGIN_TIMEOUT(10005,"登录超时"),
    USER_ID_IS_NULL(10005,"用户ID为空"),
    ROLE_ID_IS_NULL(10006,"角色ID为空"),
    AUTHC_ID_IS_NULL(10007,"权限ID为空"),

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
